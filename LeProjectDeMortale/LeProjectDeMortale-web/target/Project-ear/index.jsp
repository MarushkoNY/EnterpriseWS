<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form method="post" action="resources/Store">
             Choose mode (1 - WS, 2 - Mock): <input type="text" placeholed="mode" name="mode"/>
            Store IP address and time of this request: <button type="submit">Store</button>
        </form>
        <br><hr>
        <form action="resources/GetData" method="get">
             Choose mode (1 - WS, 2 - Mock): <input type="text" placeholed="mode" name="mode"/>
            Get IP and time of the last request: <button type="submit">Get</button>
        </form>
    </body>
</html>
