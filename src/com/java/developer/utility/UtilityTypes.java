package com.java.developer.utility;

public enum UtilityTypes {
	
	SelectAUtility("Select a Utility"),
	CurrentTimestampGenerator("Current Timestamp Generator"),
	CustomTimestampGenerator("Custom Timestamp Generator"),
	CurrentReverseTimestampGenerator("Current Reverse Timestamp Generator"),
	CustomReverseTimestampGenerator("Custom Reverse Timestamp Generator"),
	GUIDGenerator("GUID Generator");
	
	private final String name;
	private UtilityTypes(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
}
