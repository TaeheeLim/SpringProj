package kr.or.ddit.vo;

public class AddressVO {
	private String postCode;
	private String location;
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "AddressVO [postCode=" + postCode + ", location=" + location + "]";
	}
	
	
}
