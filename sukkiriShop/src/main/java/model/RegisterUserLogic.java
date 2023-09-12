package model;

import dao.AccountDAO;

public class RegisterUserLogic {
	public boolean execute(Account account) {
		//ACCOUNTテーブルの全レコードを取得
		AccountDAO dao = new AccountDAO();
		//登録有無の確認
		boolean rex = dao.existsByAccount(account);
		if (rex == true) {
			//登録済み
			return false;
		} else {
			//未登録なら新規レコード登録
			dao.createAccount(account);
			return true;
		}
	}
}
