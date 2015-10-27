package com.asto.move.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asto.move.domain.SysUploadFileDomain;
import com.asto.move.domain.dto.SysUploadFileDomainDto;

public interface SysUploadFileDomainMapper {
    
    int insertSelective(SysUploadFileDomain record);
    
    SysUploadFileDomain selectByUniqueCode(@Param("uniqueCode") String uniqueCode);
    
    /**
	 * 查询所有的文件
	 * @return
	 */
	public List<SysUploadFileDomain> selectFileList(SysUploadFileDomainDto domainDto);
	
	/**
	 * 图片迁移修改fileid
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
	 * USER_OTHER_MATERIAL/SHOP_OTHER_MATERIAL/OPERATOR_IDENTITY_DEPUTYs，复制补迁COS上
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