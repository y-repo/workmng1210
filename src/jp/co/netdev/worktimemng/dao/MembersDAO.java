package jp.co.netdev.worktimemng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * データベースのテーブルへのアクセスを行うクラス。
 * @author yoshi.
 *
 */
public class MembersDAO {

	Connection connection = null;

	public MembersDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @param scode ログイン画面で入力された社員コード。
	 * @param password ログイン画面で入力されたパスワード。
	 * @return ユーザデータの照合結果。
	 * @throws Exception テーブル接続時になんらかのエラーが発生した場合。
	 */
	public int memberSerch(String scode, String password) throws Exception {
		//実行するSQLを作成
		String sql = "select count(*) "
				    + "from members "
				    + "where scode = ? and password = ?";

		//ステートメント（SQLを取得・実行するコンテナ）を取得
		PreparedStatement statement = connection.prepareStatement(sql);

		//PreparedStatementのプレースホルダーに社員コードとパスワードを順に格納する。
		statement.setString(1, scode);
		statement.setString(2, password);

		ResultSet rs = statement.executeQuery();

		int result = 0;

		while (rs.next()) {
			result = rs.getInt("count(*)");
		}

		rs.close();
		statement.close();

		return result;
	}
}
