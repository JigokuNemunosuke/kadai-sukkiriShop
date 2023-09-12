<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スッキリ商店</title>
</head>
<body>
<p>ユーザー登録</p>
<p>登録内容確認</p>
<p>
ユーザーID：${account.getId}<br>
パスワード：${account.getPass}<br>
メールアドレス：${account.getMail}<br>
名前：${account.getName}<br>
年齢：${account.getAge}<br>
</p>
<a href="/sukkiriShop/RegisterUser">戻る</a>
<a href="/sukkiriShop/RegisterUser?action=done">登録</a>
</body>
</html>