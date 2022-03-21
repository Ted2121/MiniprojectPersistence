package model;

public class Customer {
	
	private static int id;
	private String name;
	private String address;
	private String city;
	private int zipcode;
	private String phoneNo;
	private String type;
	
	public Customer(String name, String address, String city, int zipcode, String phoneNo, String type) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.phoneNo = phoneNo;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


}
