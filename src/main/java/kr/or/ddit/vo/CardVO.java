package kr.or.ddit.vo;

public class CardVO {
	//카드번호
	private String no;
	//유효연월
	private String validMonth;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getValidMonth() {
		return validMonth;
	}
	public void setValidMonth(String validMonth) {
		this.validMonth = validMonth;
	}
	@Override
	public String toString() {
		return "CardVO [no=" + no + ", validMonth=" + validMonth + "]";
	}
	
	
	
	
}
