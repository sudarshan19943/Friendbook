package com.macs.groupone.friendbookapplication.service.countryandstate;

import java.util.ArrayList;
import java.util.Arrays;

public enum USState implements StateProvinceEnum {

	ALABAMA("US-AL", "Alabama"),
	ALASKA("US-AK", "Alaska"),
	ARIZONA("US-AZ", "Arizona"),
	ARKANSAS("US-AR", "Arkansas"),
	CALIFORNIA("US-CA", "California"),
	COLORADO("US-CO", "Colorado"),
	CONNECTICUT("US-CT", "Connecticut"),
	DELAWARE("US-DE", "Delaware"),
	FLORIDA("US-FL", "Florida"),
	GEORGIA("US-GA", "Georgia"),
	HAWAII("US-HI", "Hawaii"),
	IDAHO("US-ID", "Idaho"),
	ILLINOIS("US-IL", "Illinois"),
	INDIANA("US-IN", "Indiana"),
	IOWA("US-IA", "Iowa"),
	KANSAS("US-KS", "Kansas"),
	KENTUCKY("US-KY", "Kentucky"),
	LOUISIANA("US-LA", "Louisiana"),
	MAINE("US-ME", "Maine"),
	MARYLAND("US-MD", "Maryland"),
	MASSACHUSETTS("US-MA", "Massachusetts"),
	MICHIGAN("US-MI", "Michigan"),
	MINNESOTA("US-MN", "Minnesota"),
	MISSISSIPPI("US-MS", "Mississippi"),
	MISSOURI("US-MO", "Missouri"),
	MONTANA("US-MT", "Montana"),
	NEBRASKA("US-NE", "Nebraska"),
	NEVADA("US-NV", "Nevada"),
	NEW_HAMPSHIRE("US-NH", "New Hampshire"),
	NEW_MEXICO("US-NM", "New Mexico"),
	NEW_YORK("US-NY", "New York"),
	NEW_JERSEY("US-NJ", "New Jersey"),
	NORTH_CAROLINA("US-NC", "North Carolina"),
	NORTH_DAKOTA("US-ND", "North Dakota"),
	OHIO("US-OH", "Ohio"),
	OKLAHOMA("US-OK", "Oklahoma"),
	OREGON("US-OR", "Oregon"),
	PENNSYLVANIA("US-PA", "Pennsylvania"),
	RHODE_ISLAND("US-RI", "Rhode Island"),
	SOUTH_CAROLINA("US-SC", "South Carolina"),
	SOUTH_DAKOTA("US-SD", "South Dakota"),
	TENNESSEE("US-TN", "Tennessee"),
	TEXAS("US-TX", "Texas"),
	UTAH("US-UT", "Utah"),
	VERMONT("US-VT", "Vermont"),
	VIRGINIA("US-VA", "Virginia"),
	WASHINGTON("US-WA", "Washington"),
	WEST_VIRGINIA("US-WV", "West Virginia"),
	WISCONSIN("US-WI", "Wisconsin"),
	WYOMING("US-WY", "Wyoming"),
	//District
	WASHINGTON_DC("US-DC", "Washington D.C."),
	//Outlying territory
	PR("US-PR", "Puerto Rico");
	
	private final String stateCode;
	private final String stateName;
	
	private USState(String stateCode, String stateName) {
		this.stateCode = stateCode;
		this.stateName = stateName;
	}
	
	@Override
	public String getCode(){
		return stateCode;
	}
	
	@Override
	public String getName(){
		return stateName;
	}
	
	
	public static ArrayList<String> getListOfStates(){
		ArrayList<String> stateNames=new ArrayList<String>();
		for (USState state  : USState.values()) {
			stateNames.add(state.getName());
		}
		return stateNames;
		
	}

}
