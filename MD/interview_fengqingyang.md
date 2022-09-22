###### 视频地址：https://www.bilibili.com/video/BV1k74118721?p=1&vd_source=b60fb1a1a8f06e334bf9ca2665cdd202
##### == 和 equals区别？
    都是比较 栈 里边的，基本数据类型直接存在栈里边，就是比较的值，引用数据类型在栈里边是地址就比较的是地址
##### String, StringBuffer, StringBuilder区别？
    String是不可变的，StringBuffer是线程安全的，StringBuilder是线程不安全的
    优先选用StringBuilder, 因为平时方法里边写的，不涉及多线程
##### 08 Integer特性？
    Integer i = 100; 实际上是 Integer i = Integer.valueOf(100);
    Integer 缓存了 -128~127 的值，所以 i1 == i2 为 true
##### 16 Hashtable, HashMap, ConcurrentHashMap区别？
    HashMap是线程不安全的，Hashtable是线程安全的，ConcurrentHashMap是线程安全的
##### 35 cookie 和 session区别？
    cookie是客户端的，session是服务端的
    cookie 会存一个sessionId，sessionId是服务端生成的
    token 服务端计算生成，每次重新计算确认用户身份
##### 36 转发和重定向区别？
    转发是服务器行为，重定向是客户端行为
    转发是一次请求，重定向是两次请求
    转发地址栏不变，重定向地址栏会变
    转发可以访问WEB-INF下的资源，重定向不可以
##### 41 数据库三大范式？
    第一范式：字段不可拆分
    第二范式：非主键字段必须完全依赖于主键，不能只依赖于主键的一部分
    第三范式：非主键不能存在传递依赖
##### 