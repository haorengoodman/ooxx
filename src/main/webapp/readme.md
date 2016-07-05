点1：springmvc 配置文件和 applicationContext 分开。事务只能传播到service层。
点2：关于静态文件的访问方式问题
     1.静态文件放在 webapp/WEB-INF/ 下，用户无权限直接访问。如果需要访问，可以使用如下方式：
       <mvc:resources mapping="/jquery/**" location="/WEB-INF/jquery/"></mvc:resources>
       http://localhost/ooxx/jquery/jquery-3.0.0.js  该文件会被映射到 webapp/WEB-INF/jquery/jquery-3.0.0.js
       达到直接访问的目的
     2.静态文件存放在 webapp/ 下，dispatcherServlet 配置如下：
         <servlet-mapping>
             <servlet-name>dispatcherServlet</servlet-name>
             <url-pattern>/</url-pattern>
         </servlet-mapping>
       该配置拦截所有的url，根目录下的静态文件也不例外。所以无法直接访问
       解决方法：web.xml文件中配置 使用Tomcat的defaultServlet来处理静态文件， dispatcherServlet 不拦截以md后缀文件
        <servlet-mapping>
            <servlet-name>default</servlet-name>
            <url-pattern>*.md</url-pattern>
        </servlet-mapping>
点3：关于javascript代码的位置和写法。
     1.位置。一般放在title的后面
     2.书写方法。无论哪种方法，js代码都是在整个页面文档加载完成之后才开始执行。
        2.1 javascript原生写法
            <script type="text/javascript">
                window.onload = function(){
                    var xx = document.getElementById("one")
                    alert(xx.firstChild.nodeValue)
                }
            </script>
        2.2 jquery 写法
            <script type="text/javascript">
                $(function(){
                    alert($("#one").text());
                });
            </script>
点4：shiro的文件全部放在shiro文件夹下，以后如果不需要的话可以直接删除shiro文件目录。
点5：web.xml 文件中配置iniShiroFilter 作为shiro的入口



