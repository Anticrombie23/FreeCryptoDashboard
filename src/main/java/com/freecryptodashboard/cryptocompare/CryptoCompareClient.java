package com.freecryptodashboard.cryptocompare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CryptoCompareClient {

	RestTemplate template = new RestTemplate();

	public static final String URI_PREFIX = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=";

	public List<CurrencyForCompare> cryptoCompareExchange(List<String> currencies)
			throws JsonProcessingException, IOException {

		ResponseEntity<String> response = null;

		String uri = buildURIForSpecificCoins(currencies);

		try {
			response = template.getForEntity(uri, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());

		List<CurrencyForCompare> currencyList = getCurrenciesForCompare(root, currencies);

		return currencyList;

	}

	private List<CurrencyForCompare> getCurrenciesForCompare(JsonNode root, List<String> currencies) {

		List<CurrencyForCompare> currencyList = new ArrayList<>();

		for (String currency : currencies) {
			JsonNode foo = root.get(currency);
			CurrencyForCompare newCurr = new CurrencyForCompare();
			newCurr.setCurrencyName(currency);
			newCurr.setBitCoinPrice(foo.path("BTC").asText());
			newCurr.setEuroPrice(foo.path("EUR").asText());
			newCurr.setUSDPrice(foo.path("USD").asText());
			currencyList.add(newCurr);
		}

		return currencyList;
	}

	private String buildURIForSpecificCoins(List<String> currencies) {

		StringBuilder builder = new StringBuilder();
		builder.append(URI_PREFIX);

		for (String thisCurrency : currencies) {
			builder.append(thisCurrency + ",");
		}

		builder.append("&tsyms=BTC,USD,EUR");

		return builder.toString();
	}

	public List<CurrencyInfo> getCoinList() throws JsonProcessingException, IOException {
		String coinListURI = "https://min-api.cryptocompare.com/data/all/coinlist";
		ResponseEntity<String> response = null;
		try {
			response = template.getForEntity(coinListURI, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode data = root.path("Data");

		List<CurrencyInfo> currencyList = initCurrencies(data);

		return currencyList;
	}

	private List<CurrencyInfo> initCurrencies(JsonNode data) {

		List<CurrencyInfo> currencyList = new ArrayList<>();

		for (JsonNode element : data) {
			CurrencyInfo currency = new CurrencyInfo();
			currency.setCoinName(element.path("CoinName").asText());
			currency.setName(element.path("Name").asText());
			currency.setSymbol(element.path("Symbol").asText());
			currency.setImageURL(element.path("ImageUrl").asText());
			currencyList.add(currency);
		}

		return currencyList;
	}

}
