<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <form action="storeData" method ="post">
                Choose operation mode (1 for WS, 2 for Mock): <input type="text" placeholder="mode" name="mode"/>
                <button type="submit">Store data</button>
            </form>
            <br><hr>
            <form action="getData" method="get">
                Choose operation mode (1 for WS, 2 for Mock): <input type="text" placeholder="mode" name="mode"/>
                <button type="submit">Get data</button>
            </form>
            
            
        </center>
    </body>
</html>
