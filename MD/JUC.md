###### video_url:https://www.bilibili.com/video/BV16J411h7Rd/?p=3&spm_id_from=pageDriver&vd_source=b60fb1a1a8f06e334bf9ca2665cdd202

### 同步和异步
    调用方，和被调用方，在调用时，是否需要等待，判断是否为同步
    调用被调用方时，调用方需要等待，就是同步
    调用被调用方时，调用方不需要等待，就是异步
### windows 查看进程线程的方法
    tasklist 查看进程
    tasklist | findstr java 查找java进程
    taskkill 杀死进程
    taskkill /F /PID 33600 强制杀死进程编号33600
### linux 查看进程线程的方法
    ps -fe | grep java 查看java进程
    jps 查看java进程
    kill pid 杀死进程
    top 动态查看进程
    top -H -p pid 查看pid的线程情况
    jstack pid java自带的查看线程信息
### jconsole 查看进程线程
    可以查看远程java程序
### yield
    yield 让出CPU时间，从Running到Runnable状态
### 同步相关方法
    t1.join t1执行完，调用方继续执行
### interrupt
    t1.interrupt() 打断 sleep，wait, join, t1.isInterrupted()为false
    只有当前线程能获取自己是否被打断，用于停止自己的程序，并不是来表示当前线程的状态（表示是否要停止）
    另外一个停止方法 stop()杀死线程，如果这个线程持有锁，那么没有机会释放锁，所以这个方法废弃了
    t1.interrupt() 还可以打断LockSupport.park(),打断后他t1.isInterrupted()为true，不能够再次park，需要从新设置打断标记
### 几个不推荐的方法,锁没法释放
    stop() 停止线程
    suspend() 暂停线程
    resume() 恢复线程
### 应用之统筹
```java
@Slf4j()
class Solution{
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            sleep(1);
            log.debug("烧开水");
            sleep(15);
        }, "老王");
        Thread t1 = new Thread(() -> {
            log.debug("准备茶叶");
            sleep(1);
        }, "老李");
    }
}
```
### 常见的线程安全类
    调用他们的方法，不需要加锁：他们的每个方法是原子的，注意多个方法组合不是原子的
    String
    Integer
    StringBuffer
    Random
    Vector
    Hashtable
    java.util.concurrent包下的类
### 对象头
#### 普通对象
    Object header 64bits + Mark Word 32bits + Klass Word 32bits
#### 数组对象
    Object header 64bits + Mark Word 32bits + Klass Word 32bits + length 32bits
![mark word结构](images/.JUC_images/aa2643f5.png)
#### synchronized优化原理-轻量级锁
    多线程访问时间错开，没有竞争，记录一下被占用
    采用cas替换markword信息
    通过线程中的锁记录个数判断重入了几次
    解锁：cas替换markword信息（线程和锁对象）
#### synchronized优化原理-锁膨胀
    申请monitor,object指向重量级锁地址
#### synchronized优化原理-自旋优化
    自旋一定次数，如果还没有获取到锁，才会进入阻塞
#### synchronized优化原理-偏向锁
    markword记录线程id，下次再进来，如果是同一个线程，就不需要cas了
#### synchronized优化原理-偏向锁-状态
    markword倒数第三位记录是否加上偏向锁,默认是1，前面也会记录线程的id
#### synchronized优化原理-锁消除
    JIT对字节码进一步优化，如果锁不会被其他线程访问到，那么就不需要加锁了
#### 小故事 wait notify
    小南工作需要烟，没有烟的时候wait，小明拿来烟的时候notify，小南开始竞争锁
#### wait notify 工作原理
    条件不满足，调用wait，进入wait set，释放锁
    正在竞争锁的处于Blocked，在EntryList中
    notify，或者notifyAll，从wait set中唤醒一个或者全部线程，进入EntryList
#### API
    obj.wait)
    obj.notify() 使用同一个锁的，唤醒一个
    obj.notifyAll()
    这几个方法必须持有锁




    


    
    
    
    


    

    