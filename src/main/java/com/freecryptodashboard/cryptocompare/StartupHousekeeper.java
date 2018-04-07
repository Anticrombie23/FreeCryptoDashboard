package com.freecryptodashboard.cryptocompare;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	CryptoCompareClient client;
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
	  
	  
    try {
		client.getCoinList();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
