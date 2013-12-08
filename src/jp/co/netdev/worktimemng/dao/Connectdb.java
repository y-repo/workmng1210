package jp.co.netdev.worktimemng.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * データベースとの接続を取得するクラス。
 * @author yoshi。
 */
public class Connectdb {
	/**
	 * connection DBとの接続を格納する。
	 */
	private Connection connection = null;

	/**
	 * データベースとの接続を取得する。
	 * 接続取得していた場合は既存の接続を利用し、取得していない場合は新たにコンテナから取得する。
	 *
	 * @return コネクション
	 * @throws Exception 例外
	 */
	public Connection getConnection() throws Exception {
		//NamingException,SQLExceptionがスローされる
		try {
			if (connection == null || connection.isClosed()) {
				//JNDI参照コンテキストを取得
				InitialContext initCtx = new InitialContext();

				//Tomcatで管理されたデータベース接続をJNDI経由で取得
				DataSource ds 
					= (DataSource) initCtx.lookup("java:comp/env/localDB");

				//データベース接続を取得する
				connection = ds.getConnection();

				//自動コミットを行わず、更新系処理では常にトランザクション管理を行うように設定する。
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			//もし接続取得で例外が出た場合は、connection=nullにし、発生した例外はそのまま送出する。
			e.printStackTrace();
			connection = null;
			throw e;
		}

		return connection;
	}

	/**
	 * 接続を閉じる。確実に接続を開放するためfinallyでconnection=nullを行う。
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
}