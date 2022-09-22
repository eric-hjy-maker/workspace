# java 基础
##### == 和 equals 的区别
    == 和 equals 都是比较栈里边的数据，基本类型直接放在栈里边，就是比较的值
    引用类型在栈里边放的是地址，就是比较的地址
    equals一般要重写，比较对象的内容
##### String, StringBuffer, StringBuilder 的区别？
    String 是不可变的
    StringBuffer 是线程安全的
    StringBuilder 是线程不安全的
    优先使用 StringBuilder, 一般方法里边的为局部变量，不涉及多线程
##### Integer 的特性？
    Integer i = 100; 实际上是 Integer i = Integer.valueOf(100);
    Integer 缓存了 -128~127 的值
    Integer 和 int 比较，会自动拆箱，比较的是值
##### Hashtable, HashMap, ConcurrentHashMap 的区别？
    HashMap 是线程不安全的
    Hashtable 是线程安全的
    ConcurrentHashMap 是线程安全的
##### HashMap 的底层实现？
    数组 + 链表、红黑树
    put 的过程：通过 hashcode & (length - 1),计算下标，然后看是否有值
    没值就放当前位置，有值，是链表就依次比较，key相同就覆盖，不同就放到链表最后边
    如果是红黑树，就比较 key 的 hashcode，小的放左边，大的放右边
    如果数组长度超过 16，链表长度超过 8，就会转成红黑树
##### ConcurrentHashMap 如何实现多线程安全的？
    采用了分段加锁的机制，以数组里的链表为加锁单位，多线程在不同分段加锁，不会产生冲突，效率就比较高
##### 线程的创建方式？
    1. 继承 Thread 类
    2. 实现 Runnable 接口
    3. 实现 Callable 接口
    这些其实都是 Thread 一种，Runnable 接口类传入 Thread ，在 Thread run方法调用了方法
    Callable 也是一样，只不过将返回值放到 FutureTask 的一个属性，线程执行完，通过 get 方法获取
    4. 通过线程池获取
##### 线程池的参数？
    核心线程数：当 正在运行的线程数 < 核心线程数 时，创建新的线程
    阻塞队列：当 正在运行的线程数 > 核心线程数 时，往阻塞队里边放
    最大线程数：当阻塞队列满了，开启新的线程
    拒绝策略：当最大线程数也满了，就执行拒绝策略
    休眠时间：如果没有新的任务进来，超过休眠时间，就终止线程
    

# 数据库
##### 事务的隔离级别？
    1. 读未提交：会出现脏读的情况
    2. 读已提交 RC：出现不可重复读
    3. 可重复读 RR：在2的基础上，要么读快照，要么对记录加锁，但还是会在列上出现幻读
    4. 串行化：不会出现幻读
##### 事务的四大特性？
    1. A 原子性：靠undo log实现
    2. C 一致性：靠其他几个特性实现
    3. I 隔离性：靠MVCC实现
    4. D 持久性：靠redo log实现
##### mysql 使用的索引数据结构？
    InnoDB用的是 B+ 树，索引和数据存放在一个文件，叶子节点上存数据，并且使用双向链表连接，非叶子节点不存数据
    MyISAM用的是 B+ 树，索引和数据存放在两个文件
##### mysql explain？
    type：表示连接类型，如ALL、index、range、ref、eq_ref、const、system
    All：全表扫描
    index：全索引扫描
    range：索引范围扫描
    ref：非唯一索引扫描 (连接查询时，关联键的数据不是一条)
    eq_ref：唯一索引扫描 (连接查询时，关联键的数据是一条)
    const：主键索引扫描

# spring
##### spring 的数据传播行为？
    1. REQUIRED：如果当前存在事务，则加入该事务；如果没有则创建一个
    2. REQUIRED_NEW：创建一个新的事务，如果存在事务，则挂起当前事务
    3. NESTED：如果存在事务，则嵌套在当前事；如果没有则创建一个
##### REQUIRED 和 NESTED 的区别？
    REQUIRED：被调用方法抛出异常，无论是否捕获，都会导致方法回滚
    NESTED：被调用方法抛出异常，如果被捕获，调用方法不会回滚
##### 说说对 spring IOC 的理解？
    首先，IOC 是一个容器，负责管理 bean 的创建、销毁、依赖注入
    使用对象的时候，从容其中获取即可，不需要创建
##### bean 的生命周期？
    1. 实例化
    2. 属性赋值
    3. 织入 aware
    4. 初始化前
    5. 初始化
    6. 初始化后，也是在此时返回的代理对象
##### spring 如何解决循环依赖？
    循环依赖产生的原因，一个对象创建过程中，属性填充另外一个对象，
    而另外一个对象还没有创建，那么就创建另外一个对象，创建这个对象的过程中，
    发现属性又要填充原来那个对象，然而那个独享还没有创建完毕，
    所以就会产生一直循环的问题
    解决: 需要属性的时候，去创建对象，填充属性之前，已经实例化了，只是没有填充属性
    返回这个不完整的对象就可以了，但是这个对象不是代理对象，所以要提前走一下动态代理
    返回这个代理对象
##### AOP 的应用？
    1. 日志
    2. 事务


# mybatis
##### mybatis 的缓存？
    一级缓存：默认开启，sqlSession级别的缓存，同一个sqlSession中，相同sql只查一次
    二级缓存：需要手动开启，mapper级别的缓存，同一个mapper中，相同sql只查一次
    先查二级缓存，再查一级缓存，最后查数据库
    查过后，先放一级缓存，再放二级缓存


# JVM
##### JVM 的内存结构？
    1. 方法区：存储类信息、常量、静态变量等
    2. 堆：存储对象
    3. 虚拟机栈：局部变量、操作数栈、动态链接、方法出口等
    4. 本地方法栈：本地方法使用的内存
    5. 程序计数器：当前线程执行的字节码行号
##### 类的加载过程？
    1. 加载：将累的 .class 文件加载到内存
    2. 链接-验证：验证文件格式是否符合 java 规范
    3. 链接-准备：为属性分配内存
    4. 链接-解析：将符号引用转为直接引用
    5. 初始化：属性赋值
##### 堆的内存分配？
    1. 新生代：eden、s0、s1
    2. 老年代
##### 怎么判断是垃圾的？
    引用计数法：判断当前对象引用的个数，判断是否是垃圾，但是有循环引用的问题
    可达性分析法：从 GC Root 开始，能够访问到的就是有用对象
##### GC Root 有哪些？
    一般创建的对象都是 GC root
    像 方法里边的局部变量，对象的属性，静态属性，常量等等
##### 垃圾回收算法？
    1. 标记清除：标记出垃圾，清除垃圾
    2. 标记整理：在同一块内存标记出垃圾，整理内存
    3. 复制算法：将内存分成两块，将存活的对象复制到另外一块空的内存
##### 垃圾回收算法适用场景？
    1. 标记清除：适用于存活率低的场景，比如年轻代
    2. 标记整理：适用于存活率高的场景，比如老年代
    3. 复制算法：适用于存活率高的场景，比如老年代
##### 垃圾回收器？
    1. Serial：单线程，新生代
    2. ParNew：多线程，新生代
    3. Parallel Scavenge：多线程，新生代
    4. Serial Old：单线程，老年代
    5. Parallel Old：多线程，老年代
    6. CMS：多线程，老年代
    7. G1：多线程，新生代、老年代

    

# 数据结构和算法
