<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>libphonenumber test</title>
</head>
<body>
<h2>GET</h2>
	<form name="getnumber" action="api/phonenumbers/parse/text/{i}" method="get" accept-charset="UTF-8"
          enctype="text/plain">
         Phone number:
         <input type="text" size="25">
         <input type="submit" value="Submit">
    </form>
	<hr/>
	<h2>POST</h2>
	<form name="postnumber" action="api/phonenumbers/parse/file" method="post" accept-charset="UTF-8"
          enctype="multipart/form-data">
         <p>Upload a file here</p>
         <input type="file" name="numberFile" size="30">
         <input type="submit" value="Submit">
    </form>
</body>
</html>