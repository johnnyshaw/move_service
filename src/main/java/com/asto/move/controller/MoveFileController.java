package com.asto.move.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asto.move.constant.MoveConstant;
import com.asto.move.domain.SysUploadFileDomain;
import com.asto.move.domain.dto.SysUploadFileDomainDto;
import com.asto.move.domain.res.SysUploadFileResp;
import com.asto.move.service.ISysUploadFileService;
import com.asto.move.util.qcloud.FileUtil;
import com.asto.move.util.qcloud.cos.CosUtil;
import com.asto.move.util.qcloud.pic.PicUtil;
import com.asto.move.util.qcloud.pic.UploadResult;

/**
* @ClassName: MoveFileController 
* @Description: 文件迁移腾讯云控制器
* @Author xiaob
* @Date 2015年8月5日 上午11:51:04 
* @Modify  
* @CopyRight 杭州阿思拓电子商务有限公司
 */
@RestController
@RequestMapping("/move")
public class MoveFileController {
	
	public static final Logger logger = LoggerFactory.getLogger(MoveFileController.class);
	
	@Resource
	private ISysUploadFileService sysUploadFileService;
	
	/**
	 * 图片迁移至腾讯云万象优图
	 */
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public SysUploadFileResp imageMove(@RequestParam("row") Integer row) throws Exception{
		long beginTime = System.currentTimeMillis();
		logger.info("开始时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info("开始读取图片数据,格式为jpg,bmp,jpeg,gif,png.");
		SysUploadFileResp sysUploadFileResp = new SysUploadFileResp();
		List<String> extenList = getImageList();
		SysUploadFileDomainDto fileDomainDto = new SysUploadFileDomainDto();
		//需要迁移的文件扩展名
		fileDomainDto.setList(extenList);
		int all = sysUploadFileService.selectCountFileList(fileDomainDto);
		String msg = "一共"+all+"条图片数据!";
		logger.info(msg);
//		String url = MoveConstant.TEST_IMAGE_PATH;
		int moveCount = 0;
		if(all > 0){
			//测试地址
//			if(MoveConstant.DOWNLOAD_IMAGE_URL.equals("")){
//				url = "http://static.yuanbaopu.com/ec/images/news/error.jpg";
//			}else{
//				url = MoveConstant.DOWNLOAD_IMAGE_URL;
//			}
			UploadResult result = null;
			msg+="\n开始迁移图片至腾讯云!";
			logger.info("开始迁移图片至腾讯云!");
			FileUtil.writeFile(msg);
			StringBuffer sb = new StringBuffer();
			List<SysUploadFileDomain> updateList = new ArrayList<SysUploadFileDomain>();
			SysUploadFileDomain sysUploadFileDomain;
			
			//需要迁移数量
			fileDomainDto.setRow(row);
			//可供迁移的总数
			fileDomainDto.setAll(all);
			fileDomainDto = setPage(fileDomainDto);
			int pageSize = 0;
			double allFileSize = 0;
			//开始上传数据至腾讯万象优图
			for (int i = 0; i < fileDomainDto.getPageCount(); i++) {
				//设置开始
				fileDomainDto.setBegin(i*fileDomainDto.getPageSize());
				//是否为最后一页并且是否能模尽,如果不能就设置最后一页的数量
				if(fileDomainDto.getPageCount() >1 && i==fileDomainDto.getPageCount()-1 && fileDomainDto.getAll()%MoveConstant.PAGE_SIZE != 0){
					//如果最后一页的数量小于分页参数
					pageSize = fileDomainDto.getAll() % MoveConstant.PAGE_SIZE;
					fileDomainDto.setPageSize(pageSize);
				}
				//每页迁移条数
				fileDomainDto.setEnd(fileDomainDto.getPageSize());
				fileDomainDto.setRow(fileDomainDto.getPageSize());
				List<SysUploadFileDomain> list = sysUploadFileService.selectFileList(fileDomainDto);
				for (int j = 0; j < list.size(); j++) {
					sysUploadFileDomain = list.get(j);
					result = new UploadResult();
					 logger.info("unicode:"+sysUploadFileDomain.getUniqueCode()+" ,url:"+sysUploadFileDomain.getUrl()+" ,path:"+sysUploadFileDomain.getPath());
					// 下载图片(测试使用)
//					PicUtil.testFile(url,MoveConstant.DOWNLOAD_IMAGE_SAVE_URL+sysUploadFileDomain.getPath());
					File file = new File(MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
							+ sysUploadFileDomain.getPath());
					//判断需要上传的文件是否存在
					if (!file.exists()) {
						sb.append("[");
						sb.append(sysUploadFileDomain.getUniqueCode());
						sb.append(",");
						sb.append(sysUploadFileDomain.getPath());
						sb.append("]");
						sb.append(",");
						msg = "该文件不存在："+sysUploadFileDomain.getPath()+" ,该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
						logger.info(msg);
						FileUtil.writeFile(msg);
					} else {
						// 上传图片至腾讯云万象优图
						logger.info(sysUploadFileDomain.getPath()+",文件大小为:["+file.length()/1024+"]KB");
						double fileSize = (file.length()/1024/1024);
						PicUtil.uploadPic(MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
								+ sysUploadFileDomain.getPath(), result);
						// 设置上传至腾讯云上fileid
						logger.info("---fileid----:"+result.fileid+",url:"+result.url);
						sysUploadFileDomain.setFileid(result.fileid);
						if(sysUploadFileDomain.getFileid() != null && !"".equals(sysUploadFileDomain.getFileid())){
							allFileSize+=fileSize;
							logger.info("上传成功，fileid:"+sysUploadFileDomain.getFileid()+", 文件路径："+sysUploadFileDomain.getPath());
							updateList.add(sysUploadFileDomain);
						}else{
							msg = "文件上传失败："+sysUploadFileDomain.getPath()+"，文件大小：["+fileSize+"] MB，该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
							FileUtil.writeFile(msg);
						}
					}
				}
				// 批量更新fileid
				if (updateList != null && updateList.size() > 0) {
					sysUploadFileService
							.batchUpdateSysUploadFile(updateList);
					moveCount+=updateList.size();
					updateList.clear();
				}
			}
			if(sb != null && sb.length() > 0){
				sb.append("文件不存在,无法上传!");
			}
			msg = "迁移结果,一共需要迁移["+fileDomainDto.getAll()+"]张图片,  迁移成功["+moveCount+"]张图片,  迁移失败["+(fileDomainDto.getAll()-moveCount)+"]张图片!，图片大小为：["+allFileSize+"] MB!";
			sysUploadFileResp.setResult(msg);
			sysUploadFileResp.setError(sb.toString());
			logger.info(sysUploadFileResp.getResult());
			FileUtil.writeFile(msg);
		}else{
			sysUploadFileResp.setResult("没有需要迁移的图片数据!");
		}
		logger.info(sysUploadFileResp.getResult());
		logger.info("结束迁移图片至腾讯云万象优图!");
		logger.info("结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		long endTime = System.currentTimeMillis();
		logger.info("共花费时间："+((endTime-beginTime)/1000/60)+"分钟");
		msg = "结束迁移文件至腾讯云COS!"+" \n 结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" \n共花费时间："+((endTime-beginTime)/1000/60)+"分钟";
		FileUtil.writeFile(msg);
		return sysUploadFileResp;
	}
	
	
	/**
	 * 文件迁移至腾讯云COS
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public SysUploadFileResp fileMove(@RequestParam("row") Integer row) throws Exception{
		long beginTime = System.currentTimeMillis();
		logger.info("开始时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info("开始读取文件数据,格式为除去jpg,bmp,jpeg,gif,png");
		SysUploadFileResp sysUploadFileResp = new SysUploadFileResp();
		List<String> extenList = getImageList();
		SysUploadFileDomainDto fileDomainDto = new SysUploadFileDomainDto();
		fileDomainDto.setType("file");
		//需要迁移的文件扩展名
		fileDomainDto.setList(extenList);
		int all = sysUploadFileService.selectCountFileList(fileDomainDto);
		String msg = "一共"+all+"条文件数据!";
		logger.info(msg);
//		String url = MoveConstant.TEST_FILE_PATH;
		int moveCount = 0;
		if(all > 0){
			//测试地址
//			if(MoveConstant.DOWNLOAD_IMAGE_URL.equals("")){
//				url = "http://static.yuanbaopu.com/ec/images/news/error.jpg";
//			}else{
//				url = MoveConstant.DOWNLOAD_IMAGE_URL;
//			}
			msg+="\n开始迁移文件至腾讯云COS!";
			logger.info("开始迁移文件至腾讯云COS!");
			FileUtil.writeFile(msg);
			StringBuffer sb = new StringBuffer();
			List<SysUploadFileDomain> updateList = new ArrayList<SysUploadFileDomain>();
			SysUploadFileDomain sysUploadFileDomain;
			//需要迁移数量
			fileDomainDto.setRow(row);
			//可供迁移的总数
			fileDomainDto.setAll(all);
			fileDomainDto = setPage(fileDomainDto);
			
			int pageSize = 0;
			long allFileSize = 0;//文件总大小
			//开始上传数据至腾讯COS
			for (int i = 0; i < fileDomainDto.getPageCount(); i++) {
				//设置开始
				fileDomainDto.setBegin(i*fileDomainDto.getPageSize());
				//是否为最后一页并且是否能模尽,如果不能就设置最后一页的数量
				if(fileDomainDto.getPageCount() >1 && i==fileDomainDto.getPageCount()-1 && fileDomainDto.getAll()%MoveConstant.PAGE_SIZE != 0){
					//如果最后一页的数量小于分页参数
					pageSize = fileDomainDto.getAll() % MoveConstant.PAGE_SIZE;
					fileDomainDto.setPageSize(pageSize);
				}
				//每页迁移条数
				fileDomainDto.setEnd(fileDomainDto.getPageSize());
				fileDomainDto.setRow(fileDomainDto.getPageSize());
				List<SysUploadFileDomain> list = sysUploadFileService.selectFileList(fileDomainDto);
				for (int j = 0; j < list.size(); j++) {
					sysUploadFileDomain = list.get(j);
					logger.info("unicode:"+sysUploadFileDomain.getUniqueCode()+" ,extendsion:"+sysUploadFileDomain.getExtension()+" ,url:"+sysUploadFileDomain.getUrl()+" ,path:"+sysUploadFileDomain.getPath());
					// 下载图片(测试使用)
//					PicUtil.testFile(url,MoveConstant.DOWNLOAD_IMAGE_SAVE_URL+sysUploadFileDomain.getPath());
					File file = new File(MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
							+ sysUploadFileDomain.getPath());
					
					//判断需要上传的文件是否存在
					if (!file.exists()) {
						sb.append("[");
						sb.append(sysUploadFileDomain.getUniqueCode());
						sb.append(",");
						sb.append(sysUploadFileDomain.getPath());
						sb.append("]");
						sb.append(",");
						msg = "该文件不存在："+sysUploadFileDomain.getPath()+" ,该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
						logger.info(msg);
						FileUtil.writeFile(msg);
					} else {
						// 上传图片至腾讯云COS
						logger.info(sysUploadFileDomain.getPath()+",文件大小为:["+file.length()/1024+"]KB");
						String floder = sysUploadFileDomain.getPath().substring(0,sysUploadFileDomain.getPath().lastIndexOf("/")+1);
						logger.info("创建文件夹："+floder+", 返回："+new String(CosUtil.createFolder(floder).getBytes("iso-8859-1"),"utf-8"));
						double fileSize = (file.length()/1024/1024);
						String fileid = CosUtil.uploadFile(sysUploadFileDomain.getPath(), MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
								+ sysUploadFileDomain.getPath(),fileSize);
						allFileSize+=fileSize;
						fileid = new String(fileid.getBytes("iso-8859-1"),"utf-8");
						logger.info("---fileid----:"+fileid);
						// 设置上传至腾讯云COS上fileid
						sysUploadFileDomain.setFileid(CosUtil.jsonUrlAnalysis(fileid));
						if(sysUploadFileDomain.getFileid() != null && !"".equals(sysUploadFileDomain.getFileid())){
							logger.info("上传成功，fileid:"+sysUploadFileDomain.getFileid()+", 文件路径："+sysUploadFileDomain.getPath());
							updateList.add(sysUploadFileDomain);
						}else{
							msg = "文件上传失败："+sysUploadFileDomain.getPath()+"，文件大小：["+fileSize+"] MB，该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
							FileUtil.writeFile(msg);
						}
					}
				}
				// 批量更新fileid
				if (updateList != null && updateList.size() > 0) {
					sysUploadFileService
							.batchUpdateSysUploadFile(updateList);
					moveCount+=updateList.size();
					updateList.clear();
				}
			}
			if(sb != null && sb.length() > 0){
				sb.append("文件不存在,无法上传!");
			}
			msg = "迁移结果,一共需要迁移["+fileDomainDto.getAll()+"]个文件,  迁移成功["+moveCount+"]个文件,  迁移失败["+(fileDomainDto.getAll()-moveCount)+"]个文件，文件大小为："+allFileSize+" MB!";
			sysUploadFileResp.setResult(msg);
			sysUploadFileResp.setError(sb.toString());
			logger.info(sysUploadFileResp.getResult());
			FileUtil.writeFile(msg);
		}else{
			sysUploadFileResp.setResult("没有需要迁移的文件数据!");
		}
		logger.info(sysUploadFileResp.getResult());
		logger.info("结束迁移文件至腾讯云COS!");
		logger.info("结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		long endTime = System.currentTimeMillis();
		logger.info("共花费时间："+((endTime-beginTime)/1000/60)+"分钟");
		msg = "结束迁移文件至腾讯云COS!"+" \n 结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" \n共花费时间："+((endTime-beginTime)/1000/60)+"分钟";
		FileUtil.writeFile(msg);
		return sysUploadFileResp;
	}
	
	/**
	 * 补充迁移文件(USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTY)迁移至腾讯云COS
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.GET)
	public SysUploadFileResp copyMoveCos(@RequestParam("row") Integer row) throws Exception{
		long beginTime = System.currentTimeMillis();
		logger.info("开始时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info("开始读取文件数据,格式为USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL的文件复制至COS上!");
		SysUploadFileResp sysUploadFileResp = new SysUploadFileResp();
		SysUploadFileDomainDto fileDomainDto = new SysUploadFileDomainDto();
		//需要迁移的文件扩展名
		int all = sysUploadFileService.selectCountCopyList(fileDomainDto);
		String msg = "一共"+all+"条文件数据!";
		logger.info(msg);
//		String url = MoveConstant.TEST_IMAGE_PATH;
		int moveCount = 0;
		if(all > 0){
			//测试地址
//			if(MoveConstant.DOWNLOAD_IMAGE_URL.equals("")){
//				url = "http://static.yuanbaopu.com/ec/images/news/error.jpg";
//			}else{
//				url = MoveConstant.DOWNLOAD_IMAGE_URL;
//			}
			msg+="\n开始迁移文件至腾讯云COS!";
			logger.info("开始迁移文件至腾讯云COS!");
			FileUtil.writeFile(msg);
			StringBuffer sb = new StringBuffer();
			List<SysUploadFileDomain> updateList = new ArrayList<SysUploadFileDomain>();
			SysUploadFileDomain sysUploadFileDomain;
			//需要迁移数量
			fileDomainDto.setRow(row);
			//可供迁移的总数
			fileDomainDto.setAll(all);
			fileDomainDto = setPage(fileDomainDto);
			
			int pageSize = 0;
			long allFileSize = 0;//文件总大小
			//开始上传数据至腾讯COS
			for (int i = 0; i < fileDomainDto.getPageCount(); i++) {
				//设置开始
				fileDomainDto.setBegin(i*fileDomainDto.getPageSize());
				//是否为最后一页并且是否能模尽,如果不能就设置最后一页的数量
				if(fileDomainDto.getPageCount() >1 && i==fileDomainDto.getPageCount()-1 && fileDomainDto.getAll()%MoveConstant.PAGE_SIZE != 0){
					//如果最后一页的数量小于分页参数
					pageSize = fileDomainDto.getAll() % MoveConstant.PAGE_SIZE;
					fileDomainDto.setPageSize(pageSize);
				}
				//每页迁移条数
				fileDomainDto.setEnd(fileDomainDto.getPageSize());
				fileDomainDto.setRow(fileDomainDto.getPageSize());
				List<SysUploadFileDomain> list = sysUploadFileService.selectCopyList(fileDomainDto);
				for (int j = 0; j < list.size(); j++) {
					sysUploadFileDomain = list.get(j);
					logger.info("unicode:"+sysUploadFileDomain.getUniqueCode()+" ,extendsion:"+sysUploadFileDomain.getExtension()+" ,url:"+sysUploadFileDomain.getUrl()+" ,path:"+sysUploadFileDomain.getPath());
					// 下载图片(测试使用)
//					PicUtil.testFile(url,MoveConstant.DOWNLOAD_IMAGE_SAVE_URL+sysUploadFileDomain.getPath());
					File file = new File(MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
							+ sysUploadFileDomain.getPath());
					
					//判断需要上传的文件是否存在
					if (!file.exists()) {
						sb.append("[");
						sb.append(sysUploadFileDomain.getUniqueCode());
						sb.append(",");
						sb.append(sysUploadFileDomain.getPath());
						sb.append("]");
						sb.append(",");
						msg = "该文件不存在："+sysUploadFileDomain.getPath()+" ,该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
						logger.info(msg);
						FileUtil.writeFile(msg);
					} else {
						logger.info(sysUploadFileDomain.getPath()+",文件大小为:["+file.length()/1024+"]KB");
						// 上传图片至腾讯云COS
						String floder = sysUploadFileDomain.getPath().substring(0,sysUploadFileDomain.getPath().lastIndexOf("/")+1);
						double fileSize = (file.length()/1024/1024);
						logger.info("创建文件夹："+floder+", 返回："+new String(CosUtil.createFolder(floder).getBytes("iso-8859-1"),"utf-8"));
						String fileid = CosUtil.uploadFile(sysUploadFileDomain.getPath(), MoveConstant.DOWNLOAD_IMAGE_SAVE_URL
								+ sysUploadFileDomain.getPath(),fileSize);
						//计算所有文件大小
						allFileSize+=fileSize;
						fileid = new String(fileid.getBytes("iso-8859-1"),"utf-8");
						logger.info("---fileid----:"+fileid);
						// 设置上传至腾讯云COS上fileid
						sysUploadFileDomain.setFileid(CosUtil.jsonUrlAnalysis(fileid));
						if(sysUploadFileDomain.getFileid() != null && !"".equals(sysUploadFileDomain.getFileid())){
							logger.info("上传成功，fileid:"+sysUploadFileDomain.getFileid()+", 文件路径："+sysUploadFileDomain.getPath());
							updateList.add(sysUploadFileDomain);
						}else{
							msg = "文件上传失败："+sysUploadFileDomain.getPath()+"，文件大小：["+fileSize+"] MB，该条数据uniqueCode：["+sysUploadFileDomain.getUniqueCode()+"]";
							FileUtil.writeFile(msg);
						}
					}
				}
				// 批量更新fileid
				if (updateList != null && updateList.size() > 0) {
					sysUploadFileService.batchUpdateSysUploadFile(updateList);
					moveCount+=updateList.size();
					updateList.clear();
				}
			}
			if(sb != null && sb.length() > 0){
				sb.append("文件不存在,无法上传!");
			}
			msg = "迁移结果,一共需要迁移["+fileDomainDto.getAll()+"]个文件,  迁移成功["+moveCount+"]个文件,  迁移失败["+(fileDomainDto.getAll()-moveCount)+"]个文件，文件大小为："+allFileSize+" MB!";
			sysUploadFileResp.setResult(msg);
			sysUploadFileResp.setError(sb.toString());
			logger.info(sysUploadFileResp.getResult());
			FileUtil.writeFile(msg);
		}else{
			sysUploadFileResp.setResult("没有需要迁移的文件数据!");
		}
		logger.info(sysUploadFileResp.getResult());
		logger.info("结束迁移文件至腾讯云COS!");
		logger.info("结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		long endTime = System.currentTimeMillis();
		logger.info("共花费时间："+((endTime-beginTime)/1000/60)+"分钟");
		msg = "结束迁移文件至腾讯云COS!"+" \n 结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" \n共花费时间："+((endTime-beginTime)/1000/60)+"分钟";
		FileUtil.writeFile(msg);
		return sysUploadFileResp;
	}
	
	
	/**
	 * 设置分页参数
	 * @param dto
	 * @return
	 */
	private SysUploadFileDomainDto setPage(SysUploadFileDomainDto dto){
		//查询需要上传至腾讯云的图片文件
		//当需要上传数量大于0,并且设置了该次需要上传多少条数据,就使用设置的参数
		//如果未使用传递参数,就上传所有
		if(dto.getRow() != null && dto.getRow() > 0 && dto.getRow() <= dto.getAll()){
			dto.setAll(dto.getRow());
		}
		//页数
		int count = 0;
		//分页参数
		int pageSize = MoveConstant.PAGE_SIZE;
		//开始进行分页处理
		if(dto.getAll()>pageSize){
			//判断需要上传的总数,%分页参数是否能模尽
			if(dto.getAll() % pageSize ==0){
				count = dto.getAll()/pageSize;
			}else{
				//剩余的页数就需要加1
				count = dto.getAll()/pageSize+1;
			}
			dto.setPageSize(pageSize);
		}else{
			//如果总数小于分页参数,页数等于1
			count+=1;
			dto.setPageSize(dto.getAll());
		}
		dto.setPageCount(count);
		return dto;
	}
	
	/*
	 * 图片格式
	 */
	private List<String> getImageList(){
		List<String> extenList = new ArrayList<String>();
		extenList.add("bmp");
		extenList.add("gif");
		extenList.add("jpeg");
		extenList.add("jpg");
		extenList.add("png");
		extenList.add(".bmp");
		extenList.add(".gif");
		extenList.add(".jpeg");
		extenList.add(".jpg");
		extenList.add(".png");
		return extenList;
	}
	
//	/**
//	 * 文件格式
//	 * @return
//	 */
//	private List<String> getFileList(){
//		List<String> extenList = new ArrayList<String>();
//		extenList.add("apk");
//		extenList.add(".apk");
//		extenList.add("avi");
//		extenList.add(".avi");
//		extenList.add("doc");
//		extenList.add(".doc");
//		extenList.add("docx");
//		extenList.add(".docx");
//		extenList.add("xls");
//		extenList.add(".xls");
//		extenList.add("xlsx");
//		extenList.add(".xlsx");
//		extenList.add("ipa");
//		extenList.add(".ipa");
//		extenList.add("mp3");
//		extenList.add(".mp3");
//		extenList.add("mp4");
//		extenList.add(".mp4");
//		extenList.add("pdf");
//		extenList.add(".pdf");
//		extenList.add("rar");
//		extenList.add(".rar");
//		extenList.add("wma");
//		extenList.add(".wma");
//		extenList.add("wmv");
//		extenList.add(".wmv");
//		extenList.add("zip");
//		extenList.add(".zip");
//		return extenList;
//	}
	
}
