# spring-boot-mybatis-rest-service-demo
Spring Boot + Mybatis + Mysql + logback日志输出 demo例子

特点：
1. 把所有东西都封装在一个单独的，可执行的JAR文件中，由Java main()方法驱动，无须打包传统WAR文件；
2. 直接嵌入Tomcat，Jetty或Undertow（无需部署WAR文件）；
3. 较少配置；

参考：

https://projects.spring.io/spring-boot/

http://spring.io/guides/gs/consuming-rest/

http://www.mybatis.org/spring/index.html#Requirements


Mybatis简单的增删改查及分页使用demo
访问： 
http://localhost:8081/category/queryForManage?page=2&rows=1

得到结果：
{"list":[{"id":2,"parentid":0,"name":"name2","code":"code2","remark":"p"}],"pagination":{"total":2,"current":2,"pageSize":2}}
