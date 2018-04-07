package com.freecryptodashboard.cryptocompare;

public class CoinListWrapper {
	String response;
	String message;
	String baseImageUrl;
	String baseLinkUrl;
	Data data;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBaseImageUrl() {
		return baseImageUrl;
	}

	public void setBaseImageUrl(String baseImageUrl) {
		this.baseImageUrl = baseImageUrl;
	}

	public String getBaseLinkUrl() {
		return baseLinkUrl;
	}

	public void setBaseLinkUrl(String baseLinkUrl) {
		this.baseLinkUrl = baseLinkUrl;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
