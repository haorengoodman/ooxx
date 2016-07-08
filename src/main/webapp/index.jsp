<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        $(function(){
            //alert($("#one").text());
        });
    </script>
    <body>
        <h2 id="one">Hello World!!!!</h2>
        <form action="${pageContext.request.contextPath}/login" >
            <input name="username" type="text" />
            <input name="password" type="password" />
            <input name="submit" type="submit" value="登陆(chapter7)"/>
        </form>
    </body>

</html>
