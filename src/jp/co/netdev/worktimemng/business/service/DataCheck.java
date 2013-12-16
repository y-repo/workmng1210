package jp.co.netdev.worktimemng.business.service;

import java.io.Serializable;

import jp.co.netdev.worktimemng.business.model.WorkDTO;

/**
 * 社員コード及びパスワードのデータチェックを行うクラス。
 * @author yoshi.
 */
public class DataCheck implements Serializable {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	WorkDTO dto = new WorkDTO();

	/**
	 * 社員コード及びパスワードの空チェック。
	 * 社員コードとパスワードが空かどうかをチェックし、どちらかが空だった場合は、
	 * エラーメッセージを作成してサーブレットに渡す。
	 */
	public void checkvalues(String scode,String passwd) {
		//データチェック
		if (scode.length() == 0 || passwd.length() == 0) {
			dto.setErrmsg("パスワードまたはIDが空です！");
		}
	}
}
