package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	//DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Account findByLogin(Login login) {
		Account account = null;
		
		//DBへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,  login.getUserId());
			pStmt.setString(2, login.getPass());
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			//一致したユーザーが存在した場合、そのユーザーを表すAccountインスタンスを生成
			if (rs.next()) {
				//結果からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				account = new Account(userId, pass, mail, name, age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		//見つかったユーザーまたはnullを返す
		return account;
	}
	
	public boolean existsByAccount(Account account) {
		//DBへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT "
					+ "WHERE USER_ID = ? AND PASS = ? AND MAIL = ? AND NAME = ? AND AGE = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,  account.getUserId());
			pStmt.setString(2, account.getPass());
			pStmt.setString(3, account.getMail());
			pStmt.setString(4, account.getName());
			pStmt.setInt(5, account.getAge());
					
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
		
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
		return false;
	}
	
	public Account createAccount(Account account) {
		//ユーザー情報をテーブルに追加
		String sql = "INSERT INTO ACCOUNT (USER_ID, PASS, MAIL, NAME, AGE) VALUES (userId, pass, mail, name, age)";
	}
}
