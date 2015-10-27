package com.asto.move.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asto.move.domain.SysUploadFileDomain;
import com.asto.move.domain.dto.SysUploadFileDomainDto;
import com.asto.move.persistence.SysUploadFileDomainMapper;
import com.asto.move.service.ISysUploadFileService;

/**
 * 
* @ClassName: FileServiceImpl 
* @Author maojx
* @Date 2015年4月14日 下午9:21:43 
* @Modify  
* @CopyRight 杭州阿思拓电子商务有限公司
 */
@Component
@Transactional
public class SysUploadFileServiceImpl implements ISysUploadFileService {
	
	@Resource
	private SysUploadFileDomainMapper sysUploadFileDomainMapper;
	

	/**
	 * 根据fileCode查询证件地址信息
	* @Title: selectByUniqueCode 
	* @Param @param fileCode
	* @Param @return
	* @Return BizSysUploadFileDomain
	* @Throws
	 */
	@Override
	public SysUploadFileDomain selectByUniqueCode(String fileCode){
		return sysUploadFileDomainMapper.selectByUniqueCode(fileCode);
	}
	
	/**
	 * 查询所有的文件
	 * @return
	 */
	public List<SysUploadFileDomain> selectFileList(SysUploadFileDomainDto domainDto){
		return sysUploadFileDomainMapper.selectFileList(domainDto);
	}
	
	/**
	 * 图片迁移更改fileid
	 * @param sysUploadFileDomain
	 * @return
	 */
	public int updateSysUploadFile(SysUploadFileDomain sysUploadFileDomain){
		return sysUploadFileDomainMapper.updateSysUploadFile(sysUploadFileDomain);
	}
	
	/**
	 * 批量图片迁移更改fileid
	 * @param sysUploadFileDomain
	 * @return
	 */
	public int batchUpdateSysUploadFile(List<SysUploadFileDomain> list){
		return sysUploadFileDomainMapper.batchUpdateSysUploadFile(list);
	}

	/**
	 * 查询需要上传的图片数量
	 * @return
	 */
	public int selectCountFileList(SysUploadFileDomainDto domainDto){
		return sysUploadFileDomainMapper.selectCountFileList(domainDto);
	}
	
	/**
	 * USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTY，复制补迁COS上
	 * 查询总数
	 * @param domainDto
	 * @return
	 */
	public int selectCountCopyList(SysUploadFileDomainDto domainDto){
		return sysUploadFileDomainMapper.selectCountCopyList(domainDto);
	}
	
	/**
	 * USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTY，复制补迁COS上
	 * 查询需要迁移的文件
	 * @param domainDto
	 * @return
	 */
	public List<SysUploadFileDomain> selectCopyList(SysUploadFileDomainDto domainDto){
		return sysUploadFileDomainMapper.selectCopyList(domainDto);
	}

}
