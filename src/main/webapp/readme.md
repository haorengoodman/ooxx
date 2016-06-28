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
点3：