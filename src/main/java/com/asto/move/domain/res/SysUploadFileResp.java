package com.asto.move.domain.res;

import java.io.Serializable;

public class SysUploadFileResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7462176688384615460L;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
