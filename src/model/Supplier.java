package model;

public class Supplier {

	private int id;
	private String name;
	private String address;
	private String country;
	private String phoneNo;
	private String email;

	public Supplier(int id, String name, String address, String country, String phoneNo, String email) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public Supplier(String name, String address, String country, String phoneNo, String email) {

		this.name = name;
		this.address = address;
		this.country = country;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}



