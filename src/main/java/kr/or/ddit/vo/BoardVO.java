package kr.or.ddit.vo;

public class BoardVO {
	private String no;
	private String tit;
	private String cont;
	private String wri;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTit() {
		return tit;
	}
	public void setTit(String tit) {
		this.tit = tit;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getWri() {
		return wri;
	}
	public void setWri(String wri) {
		this.wri = wri;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", tit=" + tit + ", cont=" + cont + ", wri=" + wri + "]";
	}
	
	
}
