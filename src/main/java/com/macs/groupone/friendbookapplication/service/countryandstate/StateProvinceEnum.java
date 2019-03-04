package com.macs.groupone.friendbookapplication.service.countryandstate;

/**
 * Public interface for states/provinces of a country.
 * State/province is the name of the next smaller administrative region than country.
 * 
 *
 */
public interface StateProvinceEnum {
	
	/**
	 * Return the ISO 3166-2 code of this state/province.
	 * The code should be in capital letters.
	 * @return
	 */
	public String getCode();
	
	/**
	 * Return the name of this state/province.
	 * The name should be lower case and each part should start with a capital letter.
	 * @return
	 */
	public String getName();
	
	//To be implemented
	//public Country getAssociatedCountry();
}
