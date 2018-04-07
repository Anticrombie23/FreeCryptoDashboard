package com.hellokoding.springboot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freecryptodashboard.cryptocompare.CoinSet;
import com.freecryptodashboard.cryptocompare.CryptoDelegate;

@Controller
public class CryptoDashboardController {

	CryptoDelegate delegate = new CryptoDelegate();
	CoinSet myCoinSet = new CoinSet();

	@RequestMapping(value = "/cryptodashboard", method = RequestMethod.GET)
	public String getdashboard(Model model) throws JsonProcessingException, IOException {
		maintainStaticCryptoValues(model);
		return "cryptoDashboard";
	}

	@RequestMapping(value = "/cryptodashboard", method = RequestMethod.POST)
	public String retrieveCryptoValues(Model model, @RequestParam("crypto1") String currency1,
			@RequestParam("crypto2") String currency2, @RequestParam("currency1Value") String currency1Value,
			@RequestParam("currency2Value") String currency2Value, @RequestParam("crypto3") String currency3,
			@RequestParam("currency3Value") String currency3Value, @RequestParam("crypto4") String currency4,
			@RequestParam("currency4Value") String currency4Value, @RequestParam("crypto5") String currency5,
			@RequestParam("currency5Value") String currency5Value) throws JsonProcessingException, IOException {

		Map<String, String> cryptoValues = saveSelectedCryptoValues(currency1, currency2, currency1Value,
				currency2Value, currency3, currency3Value, currency4, currency4Value, currency5, currency5Value);

		List<String> cryptosToTotal = new ArrayList<String>();

		if (CryptoDelegate.cryptoHasValue(currency1, currency1Value)) {
			String currency1Total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency1Value),
					currency1);
			cryptosToTotal.add(currency1Total);
			model.addAttribute("currency1total", currency1Total);
			model.addAttribute("coin1Value", cryptoValues.get(currency1));
		}

		if (CryptoDelegate.cryptoHasValue(currency2, currency2Value)) {
			String currency2total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency2Value),
					currency2);
			cryptosToTotal.add(currency2total);
			model.addAttribute("currency2total", currency2total);
			model.addAttribute("coin2Value", cryptoValues.get(currency2));
		}

		if (CryptoDelegate.cryptoHasValue(currency3, currency3Value)) {
			String currency3total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency3Value),
					currency3);
			cryptosToTotal.add(currency3total);
			model.addAttribute("currency3total", currency3total);
			model.addAttribute("coin3Value", cryptoValues.get(currency3));
		}

		if (CryptoDelegate.cryptoHasValue(currency4, currency4Value)) {
			String currency4total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency4Value),
					currency4);
			cryptosToTotal.add(currency4total);
			model.addAttribute("currency4total", currency4total);
			model.addAttribute("coin4Value", cryptoValues.get(currency4));
		}

		if (CryptoDelegate.cryptoHasValue(currency5, currency5Value)) {
			String currency5total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency5Value),
					currency5);
			cryptosToTotal.add(currency5total);
			model.addAttribute("currency5total", currency5total);
			model.addAttribute("coin5Value", cryptoValues.get(currency5));
		}

		// Calculate total given crypto value
		String cryptoPortfolioTotal = delegate.calculateCryptoTotalWorth(cryptosToTotal);
		model.addAttribute("totalValue", cryptoPortfolioTotal);

		maintainSelectedValuesInModel(model, currency1, currency2, currency1Value, currency2Value, currency3,
				currency3Value, currency4, currency4Value, currency5, currency5Value);

		maintainStaticCryptoValues(model);

		return "cryptoDashboard";
	}

	private Map<String, String> saveSelectedCryptoValues(String currency1, String currency2, String currency1Value,
			String currency2Value, String currency3, String currency3Value, String currency4, String currency4Value,
			String currency5, String currency5Value) throws JsonProcessingException, IOException {
		List<String> currencyList = new ArrayList<String>();
		addSingleValidCrypto(currency1, currency1Value, currencyList);
		addSingleValidCrypto(currency2, currency2Value, currencyList);
		addSingleValidCrypto(currency3, currency3Value, currencyList);
		addSingleValidCrypto(currency4, currency4Value, currencyList);
		addSingleValidCrypto(currency5, currency5Value, currencyList);
		Map<String, String> cryptoValues = delegate.getSelectedCryptoValues(currencyList);
		return cryptoValues;
	}

	private void maintainSelectedValuesInModel(Model model, String currency1, String currency2, String currency1Value,
			String currency2Value, String currency3, String currency3Value, String currency4, String currency4Value,
			String currency5, String currency5Value) {
		model.addAttribute("currency1Value", currency1Value);
		model.addAttribute("currency2Value", currency2Value);
		model.addAttribute("currency3Value", currency3Value);
		model.addAttribute("currency4Value", currency4Value);
		model.addAttribute("currency5Value", currency5Value);
		model.addAttribute("crypto1", currency1);
		model.addAttribute("crypto2", currency2);
		model.addAttribute("crypto3", currency3);
		model.addAttribute("crypto4", currency4);
		model.addAttribute("crypto5", currency5);
	}

	private void addSingleValidCrypto(String currency1, String currency1Value, List<String> currencyList) {
		if (CryptoDelegate.cryptoHasValue(currency1, currency1Value)) {
			currencyList.add(currency1);
		}
	}

	private void maintainStaticCryptoValues(Model model) throws JsonProcessingException, IOException {
		Map<String, String> cryptoMap = delegate.getCryptoValues();
		model.addAttribute("cryptomap", cryptoMap);
		List<String> cryptoListToReturn = CryptoCurrencySymbolList.cryptoList;
		model.addAttribute("fullCryptoList", cryptoListToReturn);
	}

}
