package jp.co.netdev.worktimemng.business.service;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.netdev.worktimemng.business.model.WorkDTO;
import jp.co.netdev.worktimemng.dao.MembersDAO;

public class WorkService {

	public Connection connection = null;

	//DTOクラスのインスタンスを取得
	WorkDTO dto = new WorkDTO();

	public void checkdata(String scode,String passwd) throws Exception {

		try {
			if (connection == null || connection.isClosed()) {
				//JNDI参照コンテキスト取得
				InitialContext initCtx = new InitialContext();

				//Tomcatで管理されたデータベース接続をJNDI経由で取得
				DataSource ds
					= (DataSource) initCtx.lookup("java:comp/env/localDB");

				//データベース接続を取得
				connection = ds.getConnection();

				//自動コミットを行わず、更新系処理では常にトランザクション管理を行うように設定する。
				connection.setAutoCommit(false);

				//画面で入力された社員コードとパスワードの空チェック
				if (scode.length() != 0 && passwd.length() != 0) {

					//社員コードとパスワードが入力されていれば、DAOを呼び出す
					MembersDAO dao = new MembersDAO();

					//ユーザ情報をDBと照合し、その結果をdtoのresultに保存
					dto.setResult(dao.memberSerch(scode, passwd));

					//照合結果のチェック
					if(dto.getResult() == 0){
						dto.setErrmsg("社員コードまたはパスワードが正しくありません！");
					}
				}else {
					dto.setErrmsg("社員コードまたはパスワードが空です！");
				}

				System.out.println(dto.getErrmsg());

				//コミット
				connection.commit();

				//DBとの接続を切断
				connection.close();
			}
		} catch (Exception e) {
			//もし接続取得で例外が出た場合は、connection=nullにし、発生した例外はそのまま送出する。
			e.printStackTrace();

			connection = null;

			throw e;
		}
	}
}