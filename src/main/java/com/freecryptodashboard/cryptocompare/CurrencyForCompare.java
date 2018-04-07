package com.freecryptodashboard.cryptocompare;

public class CurrencyForCompare {

	String currencyName;

	String bitCoinPrice;
	String USDPrice;
	String EuroPrice;

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getBitCoinPrice() {
		return bitCoinPrice;
	}

	public void setBitCoinPrice(String bitCoinPrice) {
		this.bitCoinPrice = bitCoinPrice;
	}

	public String getUSDPrice() {
		return USDPrice;
	}

	public void setUSDPrice(String uSDPrice) {
		USDPrice = uSDPrice;
	}

	public String getEuroPrice() {
		return EuroPrice;
	}

	public void setEuroPrice(String euroPrice) {
		EuroPrice = euroPrice;
	}

}
