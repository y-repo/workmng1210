package jp.co.netdev.worktimemng.business.model;

import java.io.Serializable;

/**
 * 社員コード及びパスワードのデータチェックを行うクラス。
 * @author yoshi.
 */
public class Datacheck implements Serializable {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 社員コード。
	 */
	private String scode;

	/**
	 * パスワード。
	 */
	private String passwd;
	
	
	/**
	 * エラーメッセージ。
	 */
	private String errmsg;

	/**
	 * 社員コード及びパスワードの空チェック。
	 * 社員コードとパスワードが空かどうかをチェックし、どちらかが空だった場合は、
	 * エラーメッセージを作成してサーブレットに渡す。
	 */
	public void checkvalues() {
		//データチェック
		if (scode.length() == 0 || passwd.length() == 0) {
			errmsg = "パスワードまたはIDが空です！";
		}
	}

	/**
	 * 社員コード取得メソッド。
	 * @return 社員コード。
	 */
	public String getScode() {
		return scode;
	}

	/**
	 * 社員コードセットメソッド。
	 * @param shcode 社員コード。
	 */
	public void setScode(String shcode) {
		this.scode = shcode;
	}

	/**
	 * パスワード取得メソッド。
	 * @return パスワード
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * パスワードセットメソッド。
	 * @param password パスワード
	 */
	public void setPasswd(String password) {
		this.passwd = password;
	}

	/**
	 * エラーメッセージ取得メソッド。
	 * @return エラーメッセージ
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * エラーメッセージセットメソッド。
	 * @param errormsg エラーメッセージ
	 */
	public void setErrmsg(String errormsg) {
		this.errmsg = errormsg;
	}
}
