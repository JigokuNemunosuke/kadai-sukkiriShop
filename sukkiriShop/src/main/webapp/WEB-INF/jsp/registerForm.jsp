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
<form action="/sukkiriShop/LoginServlet" method="post">
ユーザーID：<input type="text" name="userId"><br>
パスワード：<input type="password" name="pass"><br>
メールアドレス：<input type="text" name="mail"><br>
名前：<input type="text" name="name"><br>
年齢：<input type="text" name="age"><br>
<input type="submit" value="登録">
</form>
<a href="/WEB-INF/welcome.jsp>戻る</a>
</body>
</html>