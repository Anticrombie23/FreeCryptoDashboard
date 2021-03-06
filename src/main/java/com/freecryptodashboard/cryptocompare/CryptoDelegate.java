package com.freecryptodashboard.cryptocompare;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class CryptoDelegate {

	public Map<String, String> getSelectedCryptoValues(List<String> currencies)
			throws JsonProcessingException, IOException {
		CryptoCompareClient client = new CryptoCompareClient();

		List<CurrencyForCompare> coinList = client.cryptoCompareExchange(currencies);

		Map<String, String> myMap = new HashMap<String, String>();
		for (CurrencyForCompare thisCurrency : coinList) {
			myMap.put(thisCurrency.getCurrencyName(), thisCurrency.getUSDPrice());
		}

		if (myMap.size() == 0) {
			return null;
		} else {
			return myMap;
		}
	}

	public Map<String, String> getCryptoValues() throws JsonProcessingException, IOException {
		CryptoCompareClient client = new CryptoCompareClient();

		List<String> currentCurrencies = new ArrayList<String>();
		currentCurrencies.add("BTC");
		currentCurrencies.add("ETH");
		currentCurrencies.add("LTC");

		List<CurrencyForCompare> coinList = client.cryptoCompareExchange(currentCurrencies);

		Map<String, String> myMap = new HashMap<String, String>();
		for (CurrencyForCompare thisCurrency : coinList) {
			myMap.put(thisCurrency.getCurrencyName(), thisCurrency.getUSDPrice());
		}

		if (myMap.size() == 0) {
			return null;
		} else {
			return myMap;
		}

	}

	public String calculateHoldingValue(Map<String, String> cryptoValues, Double cryptoHoldingValue,
			String currencyName) {

		if (cryptoHoldingValue == null || cryptoHoldingValue == 0.0) {
			return "0";
		} else {
			double holdingValue = Double.parseDouble(cryptoValues.get(currencyName)) * cryptoHoldingValue;

			return String.valueOf(holdingValue);
		}
	}

	public String calculateCryptoTotalWorth(List<String> cryptosToTotal) {

		double total = 0;

		for (String thisCrypto : cryptosToTotal) {

			if (cryptosToTotal.equals("0")) {

			} else {

				double currentValue = Double.parseDouble(thisCrypto);
				total += currentValue;
			}

		}

		return String.valueOf(total);
	}

	public List<String> getDropDownListValues() {

		return null;
	}

	public static boolean cryptoHasValue(String symbol, String cryptoValue) {
		
		if(StringUtils.isNumeric(cryptoValue)) {
			return true;
		}else {
			return false;
		}
		
				
//		if(cryptoValue != null && !cryptoValue.trim().equalsIgnoreCase("") && symbol != null && !symbol.trim().equalsIgnoreCase("") && NumberUtils.isNumber(cryptoValue)) {
//			return true;
//		}else {
//			return false;
//		}		
	}

}
