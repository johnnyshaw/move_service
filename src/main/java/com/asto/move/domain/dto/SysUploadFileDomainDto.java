/**
 * www.yuanbaopu.com
 */
package com.asto.move.domain.dto;

import java.util.List;

/**
 * 文件相关数据库DTO
 * @author xiaobao
 *
 * 2015年9月1日
 */
public class SysUploadFileDomainDto {
	
	/**
	 * 行数
	 */
	private Integer row;
	
	/**
	 * 总数
	 */
	private int all;
	
	/**
	 * 页数
	 */
	private int pageCount;
	
	/**
	 * 需要查询的list集合
	 */
	private List<String> list;
	
	private int pageSize;
	
	/**
	 * 文件类型
	 */
	private String type;
	
	private Integer begin;
	private Integer end;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}
	
	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	
}
