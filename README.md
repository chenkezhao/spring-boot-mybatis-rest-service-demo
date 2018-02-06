# spring-boot-mybatis-rest-service-demo
## 特点：
1. 把所有东西都封装在一个单独的，可执行的JAR文件中，由Java main()方法驱动，无须打包传统WAR文件；
2. 直接嵌入Tomcat，Jetty或Undertow（无需部署WAR文件）；
3. 较少配置；

参考：

https://projects.spring.io/spring-boot/

http://spring.io/guides/gs/consuming-rest/

http://www.mybatis.org/spring/index.html#Requirements


## Mybatis简单的增删改查及分页使用demo

访问： 
http://localhost:8081/category/queryForManage?page=2&rows=1

得到结果：

{"list":[{"id":2,"parentid":0,"name":"name2","code":"code2","remark":"p"}],"pagination":{"total":2,"current":2,"pageSize":2}}

## 代码生成模板，批量生成枯燥重复的代码
连接数据库 - 指定数据字典 - 根据模板生成提前预订好的逻辑代码！

详情查看doc/Code-Generator

更多详情 http://blog.csdn.net/u013474104/article/details/78547017

## 网站api接口请求安全认证，集成spring-security-oauth2
效果：

### 直接访问：

请求 http://localhost:8081/clientinf/category/queryForManage?rows=1&page=1

返回：
Full authentication is required to access this resourceunauthorized

### 获取token再访问：

C:\Users\aaron>curl localhost:8081/oauth/token -d "grant_type=password&scope=read&username=greg&password=turnquist" -u foo:bar

{"access_token":"8f5ef7e2-4da8-42a3-9968-df564f346ddc","token_type":"bearer","refresh_token":"a01d91c9-45b0-4913-9461-0573b3b26a1a","expires_in":42635,"scope":"read"}

C:\Users\aaron>curl -H "Authorization: bearer 8f5ef7e2-4da8-42a3-9968-df564f346ddc" "localhost:8081/clientinf/category/queryForManage?rows=1&page=1"

{"list":[{"id":1,"parentid":null,"name":"name","code":"code","remark":"pp"}],"pagination":{"total":2,"current":1,"pageSize":2}}

C:\Users\aaron>




