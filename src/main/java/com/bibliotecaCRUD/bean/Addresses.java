package com.bibliotecaCRUD.bean;

public class Addresses {

	private static int AUTOINCREMENT = 1;
	private int addressID;
	private String street;
	private String numberAddress;
	private String CEP;
	
	public Addresses() {
		
	}
	public Addresses(String street, String numberAddress, String cEP) {
		this.addressID = AUTOINCREMENT++;
		this.street = street;
		this.numberAddress = numberAddress;
		CEP = cEP;
	}
	
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumberAddress() {
		return numberAddress;
	}
	public void setNumberAddress(String numberAddress) {
		this.numberAddress = numberAddress;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
	
	
	
}
