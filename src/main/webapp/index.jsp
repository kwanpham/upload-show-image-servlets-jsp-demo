<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>JSP-Servlet Upload file</title>
</head>
<body>
<h2>Demo JSP-Servlet Upload File</h2>
<form method="post" action="/UploadImageServlet" enctype="multipart/form-data">
    Select file to upload:
    <input type="file" name="image" size="60" />
    <br />
    <br />
    <input type="submit" value="Upload" />
</form>
</body>
</html>