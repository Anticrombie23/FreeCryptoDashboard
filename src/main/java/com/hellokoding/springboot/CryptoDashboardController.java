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
		Map<String, String> cryptoValues = delegate.getCryptoValues();
		List<String> cryptoListToReturn = CryptoCurrencySymbolList.cryptoList;
		model.addAttribute("fullCryptoList", cryptoListToReturn);
		model.addAttribute("cryptomap", cryptoValues);
		return "cryptoDashboard";
	}

	@RequestMapping(value = "/cryptodashboard", method = RequestMethod.POST)
	public String retrieveCryptoValues(Model model, @RequestParam("crypto1") String currency1,
			@RequestParam("crypto2") String currency2, @RequestParam("currency1Value") String currency1Value,
			@RequestParam("currency2Value") String currency2Value, @RequestParam("crypto3") String currency3,
			@RequestParam("currency3Value") String currency3Value, @RequestParam("crypto4") String currency4,
			@RequestParam("currency4Value") String currency4Value, @RequestParam("crypto5") String currency5,
			@RequestParam("currency5Value") String currency5Value) throws JsonProcessingException, IOException {

		List<String> currencyList = new ArrayList<String>();
		// Currency 1
		if (currency1 != null && !currency1.trim().equalsIgnoreCase("") && currency1Value != null
				&& !currency1Value.trim().equals("")) {
			currencyList.add(currency1);
		}

		// Currency 2
		if (currency2 != null && !currency2.trim().equalsIgnoreCase("") && currency2Value != null
				&& !currency2Value.trim().equals("")) {
			currencyList.add(currency2);
		}

		// Currency 3
		if (currency3 != null && !currency3.trim().equalsIgnoreCase("") && currency3Value != null
				&& !currency3Value.trim().equals("")) {
			currencyList.add(currency3);
		}

		// Currency 4
		if (currency4 != null && !currency4.trim().equalsIgnoreCase("") && currency4Value != null
				&& !currency4Value.trim().equals("")) {
			currencyList.add(currency4);
		}

		// Currency 5
		if (currency5 != null && !currency5.trim().equalsIgnoreCase("") && currency5Value != null
				&& !currency5Value.trim().equals("")) {
			currencyList.add(currency5);
		}

		Map<String, String> cryptoValues = delegate.getSelectedCryptoValues(currencyList);

		// only perform calculations if there is something to perform calculations on --
		// not null
		if (cryptoValues != null && cryptoValues.size() != 0) {
			String currency1Total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency1Value),
					currency1);
			String currency2total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency2Value),
					currency2);
			String currency3total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency3Value),
					currency3);
			String currency4total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency4Value),
					currency4);
			String currency5total = delegate.calculateHoldingValue(cryptoValues, Double.parseDouble(currency5Value),
					currency5);

			List<String> cryptosToTotal = new ArrayList<String>();
			cryptosToTotal.add(currency1Total);
			cryptosToTotal.add(currency2total);
			cryptosToTotal.add(currency3total);
			cryptosToTotal.add(currency4total);
			cryptosToTotal.add(currency5total);
			String cryptoPortfolioTotal = delegate.calculateCryptoTotalWorth(cryptosToTotal);

			// Total and Calculations
			model.addAttribute("totalValue", cryptoPortfolioTotal);
			model.addAttribute("currency1total", currency1Total);
			model.addAttribute("currency2total", currency2total);
			model.addAttribute("currency3total", currency3total);
			model.addAttribute("currency4total", currency4total);
			model.addAttribute("currency5total", currency5total);

			// coin values
			model.addAttribute("coin1Value", cryptoValues.get(currency1));
			model.addAttribute("coin2Value", cryptoValues.get(currency2));
			model.addAttribute("coin3Value", cryptoValues.get(currency3));
			model.addAttribute("coin4Value", cryptoValues.get(currency4));
			model.addAttribute("coin5Value", cryptoValues.get(currency5));

			// Update currency values into model
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

		// Maintain top part Big 3 Cryptos
		Map<String, String> cryptoMap = delegate.getCryptoValues();
		model.addAttribute("cryptomap", cryptoMap);

		// Return full list of cryptos for dropdowns
		List<String> cryptoListToReturn = CryptoCurrencySymbolList.cryptoList;
		model.addAttribute("fullCryptoList", cryptoListToReturn);

		return "cryptoDashboard";
	}

}
