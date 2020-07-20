package com.resto.commonModel.response;

import java.util.List;

public class Response<T> {
	private String type;
	private List<T> result;

	public Response(String type, List<T> result) {
		this.type = type;
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
