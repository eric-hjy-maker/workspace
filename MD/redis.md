###### video_url:https://www.bilibili.com/video/BV1cr4y1671t/?spm_id_from=333.337.search-card.all.click&vd_source=b60fb1a1a8f06e334bf9ca2665cdd202

#### redis value 常见数据类型
- string
- hash {name:"jack", age:18}
- list [A -> B -> C]
- set {A, B, C}
- zset {A: 1, B: 2, C: 3}
#### redis 常见命令
- set key value
- get key
- keys * 不建议生产上使用
- del key
- exists key
- expire key seconds
- ttl key
#### string 命令
- set key value
- get key
- mset key1 value1 key2 value2
- mget key1 key2
- incr key
- incrby key num
- incrbyfloat key num
- decr key
- decrby key num
- setnx key value
- setex key seconds value
#### hash 命令
- hset key field value
- hget key field
- hmset key field1 value1 field2 value2
- hmget key field1 field2
- hgetall key
- hdel key field
#### list 命令 可以看做一个双向链表
- lpush key value1 value2
- rpush key value1 value2
- lpop key
- rpop key
- lrange key start end
#### set 命令 类似 hashset, 可以看做 value 为 null 的 hashmap
- sadd key value1 value2
- srem key value1 value2
- scard key 返回集合中元素的个数
- sismember key value 判断 value 是否是集合 key 的成员
- smembers key 返回集合中的所有成员
#### zset 命令 类似 set, 但是每个元素都会关联一个 double 类型的分数, 通过分数来为集合中的成员进行从小到大的排序
- zadd key score1 value1 score2 value2
- zrem key value1 value2
- zcard key 返回集合中元素的个数
- zscore key value 返回 value 的分数
- zrange key start end 返回有序集合中指定区间内的成员, 通过索引, 分数从小到大排序
- zrank key value 返回有序集合中指定元素的排名
#### redis 客户端
    Jedis 类似 redis 命令，学习成本低，但线程不安全
    Lettuce 基于 Netty 的异步框架，线程安全，支持 redis 的邵明模式、哨兵模式、集群模式
    Redisson 基于 redis 实现的分布式、可伸缩的 Java 数据结构集合，包含诸如Map、Queue、Lock、AtomicLong、Semaphore等强大功能
#### SpringDataRedis 封装了 Jedis 和 Lettuce，提供了更加简单的操作方式
    redisTemplate.opsForValue() 返回值类型 ValueOperations 操作String类型数据
    redisTemplate.opsForHash() 返回值类型 HashOperations 操作Hash类型数据
    redisTemplate.opsForList() 返回值类型 ListOperations 操作List类型数据
    redisTemplate.opsForSet() 返回值类型 SetOperations 操作Set类型数据
    redisTemplate.opsForZSet() 返回值类型 ZSetOperations 操作ZSet类型数据
#### redisTemplate 使用
```java
class Code{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
        // 必要配置：序列化
        // GenericJackson2JsonRedisSerializer 序列化后 json 中带有 @class，一个全类名，省空间就用 String方式序列化
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // key 序列化
        redisTemplate.setkeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // value 序列化
        redisTemplate.setvalueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
    }
}
```
### Redis 企业实战
#### 黑马点评 - 验证码及登录
    redis 存对象，string方式，比较直观；hash方式，比较节省空间
    用户信息的 key，可以用手机号作为 key，但是不太隐蔽，可以用一个随机字符串作为 key，称之为 token, 请求里边放在authorization字段里边
#### 商户查询缓存
##### 缓存的作用
    1. 降低后端负载
    2. 提高读写效率，降低响应时间
##### 缓存的成本
    1. 数据一致性成本
    2. 代码维护成本（调用缓存）
    3. 运维成本（分布式的缓存）
##### 缓存的更新策略
    内存淘汰：利用redis内存淘汰机制，内存不足时，自动淘汰数据，一致性：差，维护成本：无
    超时剔除：设置超时时间，一致性：一般，维护成本：低
    主动更新：修改业务逻辑的同时，更新缓存，一致性：好，维护成本：高
##### 主动更新
    1. 先删除缓存，再更新数据库
    2. 先更新数据库，再删除缓存
    先更新数据库，再删缓存，更优
##### 缓存的策略
    1. 缓存穿透：查询一个不存在的数据，导致数据库压力过大
        解决方案：布隆过滤器，将所有可能存在的数据哈希到一个足够大的 bitmap 中，一个一定不存在的数据会被这个 bitmap 拦截掉，从而避免了对底层存储系统的查询压力
    2. 缓存击穿：热点key过期，大量并发请求这个key，导致数据库压力过大
        解决方案：加锁，只允许一个线程访问数据库，构建缓存，其他线程等待，等待时间过长，可以设置超时时间，超时后，直接返回
        解决方案：热点数据永不过期
        解决方案：设置逻辑过期（自己记录过期时间，redis不设置过期时间），快过期，另起一个线程，更新缓存
    3. 缓存雪崩：大量 key 同时过期，导致数据库压力过大
        解决方案：加随机值，每个 key 的过期时间不一样

### 应用场景
#### 应用1：基于 session 实现短信登录
    1. 登录：将用户信息存入 session
    2. 自定义拦截器：编写类 LoginInterceptor，实现 HandlerInterceptor 接口，重写 preHandle 方法
        说明：直接从 session 中获取用户信息即可
    3. 注册拦截器：编写配置类 MvcConfig，继承 WebMvcConfigurerAdapter，重写 addInterceptors 方法, 注册 LoginInterceptor
    4. 问题：集群模式下，session 无法共享，负载到另外一台服务器，就访问不到用户信息
    解决方案：将 session 存入 redis，多台服务器从 redis 中获取 session
#### 应用1加入 redis 后
    1. 验证码：验证码存入 redis，key：手机号，value：验证码
    2. 登录时，存用户信息：redis 中：key：随机字符串，value：用户信息；java返回前段时，token: 随机字符串
    3. 前端获取到 token 字段，存入 authorization 字段
### 缓存
#### 缓存更新策略 - 主动更新
    1. 更新数据库的同时，更新缓存（实际开发中用的更多）
        先删缓存，再更新数据库：会出现问题，删缓存之后，其他线程来查询数据，并存缓存，就更新到了旧数据
        先更新数据库，再操作缓存：其他线程先查，再存入缓存，这两个操作中间，先更新数据库，再存入缓存，就更新到了旧数据
    2. 缓存和数据库整合为一个服务，由这个服务去维护一致性，成本高，市面上没有这样的产品
    3. 业务操作只操作数据库，由其他线程异步的将缓存持久化到数据库中
### 全局唯一ID
#### 实现
    思想：类似雪花算法，时间戳 + 5位机房id + 5位机器id + 序列号
    此处实现：32位时间戳 + 32序列号
    redis 只增长 32 序列号，然后拼接 32 位时间戳




