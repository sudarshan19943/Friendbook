package com.macs.groupone.friendbookapplication.service.countryandstate;

import java.util.ArrayList;
import java.util.Arrays;

public enum CAProvince implements StateProvinceEnum{
	//Provinces
	ALBERTA("CA-AB","Alberta"),
	BRITISH_COLUMBIA("CA-BC","British Columbia"),
	MANITOBA("CA-MB","Manitoba"),
	NEW_BRUNSWICK("CA-NB","New Brunswick"),
	NEWFOUNDLAND_AND_LABRADOR("CA-NL","Newfoundland and Labrador"),
	NOVA_SCOTIA("CA-NS","Nova Scotia"),
	ONTARIO("CA-ON","Ontario"),
	PRINCE_EDWARD_ISLAND("CA-PE","Prince Edward Island"),
	QUEBEC("CA-QC","Québec"),
	SASKATCHEWAN("CA-SK","Saskatchewan"),
	//Territories
	NORTHWEST_TERRITORIES("CA-NT","Northwest Territories"),
	NUNAVUT("CA-NU","Nunavut"),
	YUKON("CA-YT","Yukon");
	
	private final String provinceCode;
	private final String provinceName;

	private CAProvince(String provinceCode, String provinceName) {
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
	}
	
	@Override
	public String getCode(){
		return provinceCode;
	}
	
	@Override
	public String getName(){
		return provinceName;
	}
	

	public static CAProvince getProvince(String code){
		if (code!=null || !code.equalsIgnoreCase("")){
			String codeUpper = code.toUpperCase().trim();
			for (CAProvince province : CAProvince.values()){
				if (codeUpper.equals(province.provinceCode)){
					return province;
				}
			}
		}
		return null;
	}
	
	
	public static ArrayList<String> getListOfProvinces(){
		ArrayList<String> provincesnames=new ArrayList<String>();
		for (CAProvince p  : CAProvince.values()) {
			provincesnames.add(p.getName());
		}
		return provincesnames;
		
	}

	
}

