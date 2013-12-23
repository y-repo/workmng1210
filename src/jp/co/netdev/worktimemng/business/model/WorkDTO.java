package jp.co.netdev.worktimemng.business.model;

import java.io.Serializable;

public class WorkDTO implements Serializable{

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	private String scode;
	private String passwd;
	private int result;
	private String f1;
	private String f2;
	private String errmsg;

	public String getF1() {
		return f1;
	}
	public void setF1(String f1) {
		this.f1 = f1;
	}
	public String getF2() {
		return f2;
	}
	public void setF2(String f2) {
		this.f2 = f2;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
