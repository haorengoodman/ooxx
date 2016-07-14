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
点4：添加 mvc:resources 配置静态资源访问 引发的问题：springmvc.xml文件中配置的controller 失效。
     解决方法：添加配置项 <mvc:annotation-driven />
点5：shiro的文件全部放在shiro文件夹下，以后如果不需要的话可以直接删除shiro文件目录。
点6：<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
      第一个编码 pageEncoding="UTF-8" 是针对 jsp 页面的编码。就是在我们的操作系统中jsp源文件的编码。容器会检测这个值对源文件进行编译。
      第二个编码contentType="text/html;charset=UTF-8" 是指jsp 输出结果的编码，同时浏览器会将编码设成相应的编码，当浏览器发送请求时，会使用此编码对发送的内容编码。
点7：插件启动web项目
     <plugin>
              <groupId>org.mortbay.jetty</groupId>
              <artifactId>jetty-maven-plugin</artifactId>
              <version>8.1.8.v20121106</version>
              <configuration>
                  <webAppConfig>
                      <contextPath>/${project.build.finalName}</contextPath>
                  </webAppConfig>
              </configuration>
          </plugin>
          mvn jetty:run
点8：hibernate 多对多 的 双向关联 & 单向关联 配置
     Permission.java
     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(name = "gt_role_permission",
             joinColumns = @JoinColumn(name = "permission_Id"),
             inverseJoinColumns = @JoinColumn(name = "role_id")
     )
     private Set<Role> roles;
     下面： 配置了有两种，注释掉的是 单项关联，未注释的是双向关联.
     Role.java
     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(name = "gt_role_permission",
             joinColumns = @JoinColumn(name = "role_id"),
             inverseJoinColumns = @JoinColumn(name = "permission_Id")
     )
     //@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
     private Set<Permission> permissions;
点9：hibernate 懒加载异常  fetch = FetchType.EAGER
     貌似 OpenSessionInViewFilter 并没有生效,目前已经去除该 filter
点10： shiro 异常拦截 例如：未登录异常时 可以使用注解：@ExceptionHandler({UnauthorizedException.class})
                     来捕捉异常。避免异常抛给用户。

