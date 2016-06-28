<html>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/WEB-INF/jquery/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        /**
        window.onload = function(){
            alert("xxxxxxxxxxx")
            var xx = document.getElementById("one")
            alert(xx.firstChild.nodeValue)
        }*/

        $(function(){
            alert($("#one").text());
        });

    </script>
    <body>
        <h2 id="one">Hello World!!!!</h2>
    </body>

</html>
