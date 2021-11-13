package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.List;

//자바빈 클래스
public class MemberVO {
	//MEMBER테이블
	private String memId;
	private String memName;
	//HOBBY 테이블
	private String[] hobbyList;
	//AddressVO
	private AddressVO addressVO;
	//CardVO
	private List<CardVO> cardList;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String[] getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(String[] hobbyList) {
		this.hobbyList = hobbyList;
	}
	
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<CardVO> getCardList() {
		return cardList;
	}
	public void setCardList(List<CardVO> cardList) {
		this.cardList = cardList;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", hobbyList=" + Arrays.toString(hobbyList)
				+ ", addressVO=" + addressVO + "]";
	}
}
 