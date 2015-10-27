package com.asto.move.service;

import java.util.List;

import com.asto.move.domain.SysUploadFileDomain;
import com.asto.move.domain.dto.SysUploadFileDomainDto;

/**
 * 
* @ClassName: ISysUploadFileService 
* @Author xiaob
* @Date 2015年8月5日 下午16:22:30 
* @Modify  
* @CopyRight 杭州阿思拓电子商务有限公司
 */
public interface ISysUploadFileService {
	
	/**
	 * 根据fileCode查询证件地址信息
	* @Title: selectByUniqueCode 
	* @Param @param fileCode
	* @Param @return
	* @Return SysUploadFileDomain
	* @Throws
	 */
	public SysUploadFileDomain selectByUniqueCode(String fileCode);
	
	/**
	 * 查询所有的文件
	 * @return
	 */
	public List<SysUploadFileDomain> selectFileList(SysUploadFileDomainDto domainDto);
	
//	public void updateFile(List<SysUploadFileDomainDto> list);
	
	/**
	 * 图片迁移更改fileid
	 * @param sysUploadFileDomain
	 * @return
	 */
	public int updateSysUploadFile(SysUploadFileDomain sysUploadFileDomain);
	
	/**
	 * 批量图片迁移更改fileid
	 * @param sysUploadFileDomain
	 * @return
	 */
	public int batchUpdateSysUploadFile(List<SysUploadFileDomain> list);
	
	/**
	 * 查询需要上传的图片数量
	 * @return
	 */
	public int selectCountFileList(SysUploadFileDomainDto domainDto);
	
	/**
	 * USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTY，复制补迁COS上
	 * 查询总数
	 * @param domainDto
	 * @return
	 */
	public int selectCountCopyList(SysUploadFileDomainDto domainDto);
	
	/**
	 * USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTY，复制补迁COS上
	 * 查询需要迁移的文件
	 * @param domainDto
	 * @return
	 */
	public List<SysUploadFileDomain> selectCopyList(SysUploadFileDomainDto domainDto);
}
