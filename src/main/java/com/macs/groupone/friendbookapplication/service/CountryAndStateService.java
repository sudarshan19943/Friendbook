package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.service.countryandstate.CAProvince;
import com.macs.groupone.friendbookapplication.service.countryandstate.USState;

@Service
public class CountryAndStateService {
	
	public static final String CANADA="canada";
	public static final String USA="usa";
	
	private static final Logger log = LoggerFactory.getLogger(CountryAndStateService.class);
	
	public ArrayList<String> getListOfCountries(Locale locale) {
		ArrayList<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale localObject = new Locale("", countryCode);
			log.info(localObject.getDisplayCountry());
			countryList.add(localObject.getDisplayCountry());
		}
		return countryList;

	}
	
	public ArrayList<String> getCities(String Country)
	{
		if(Country.equalsIgnoreCase(CANADA))
		{
			return CAProvince.getListOfProvinces();
		}else if(Country.equalsIgnoreCase(USA))
		{
			return USState.getListOfStates();
		}
		return null;
		
	}
	
	
}
