package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.service.countryandstate.CAProvince;
import com.macs.groupone.friendbookapplication.service.countryandstate.USState;

@Service
public class CountryAndStateService {
	
	public ArrayList<String> getListOfCountries(Locale locale) {
		ArrayList<String> countryList=new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			System.out.println(
					"Country Code = " + obj.getCountry() + ", Country Name = " + obj.getDisplayCountry(locale));
			countryList.add(obj.getCountry());
		}
		return countryList;

	}
	
	public ArrayList<String> getCities(String Country)
	{
		if(Country.equalsIgnoreCase("CANADA"))
		{
			return CAProvince.getListOfProvinces();
		}else if(Country.equalsIgnoreCase("USA"))
		{
			return USState.getListOfStates();
		}
		return null;
		
	}
	
	
}
