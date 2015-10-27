package com.asto.move.domain.req;

import java.io.Serializable;

public class SysUploadFileReq implements Serializable{
	
	private static final long serialVersionUID = -4505568134579577751L;

	//数据条数
	private Integer row;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}
    
}