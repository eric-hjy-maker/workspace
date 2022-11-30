###### video_url: https://www.bilibili.com/video/BV1v5411X7C5/?p=2&spm_id_from=pageDriver&vd_source=b60fb1a1a8f06e334bf9ca2665cdd202

#### 常见分布式事务解决方案
    共同特点：都是两阶段（2PC）
   1. seata 阿里分布式事务框架 （AT）    
   2. 消息队列 （TCC）
   3. saga （saga）
   4. XA （XA）

#### 2PC 的问题
    同步阻塞 commit之前都是处于阻塞状态
    单点故障 一切请求都来自协调者，如果宕机，则会导致所有参与者处于阻塞状态
    数据不一致 commit/rollback 部分参与者执行成功
    网络可靠性依赖 协调者发出 prepare 后，如果有一个参与者网络中断，协调者等待一段时间后，触发事务中断

#### seata 中的三个角色
    事务管理者（TM）：控制所有事务的开启、提交、回滚，通知TC
    事务协调者（TC）：控制RM，开启、提交、回滚
    资源管理器（RM）：具体事务的开启、提交、回滚，向TC注册分支事务，上报状态

#### AT 模式 auto transcation
    无侵入式，自动生成事务二阶段提交和回滚
    一阶段：记录回滚日志，提交事务，释放本地锁和连接资源，对数据库加上行锁
    二阶段：如果一阶段没有异常，事务提交，如果有异常，通过回滚日志对事务回滚
#### TCC 模式 Try、Confirm、Cancel
    没有锁，但是侵入性比较强，自己实现事务控制逻辑
    第三方框架：BeyeTCC、TCC-transaction、Himly
    一阶段：try，业务检查及资源预留，如尝试查看剩余库存是否够减
    二阶段：如果一阶段有失败，发起Cancel操作，如果全部成功，则 Confirm
#### seata 快速开始
    @GlobalTransactional
#### seata server TC 搭建
    存储模式有三种
    file
    db
    redis
