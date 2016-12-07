package com.mcit.kritth.model.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address
{
	@Column(name="street_address")
	private String streetAddress;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="postal")
	private String postal;
	@Column(name="country")
	private String country;
	
	public Address() { }
	
	@Override
	public String toString()
	{
		return streetAddress + ", " + city + ", " + state + ", " + postal + ", " + country;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
