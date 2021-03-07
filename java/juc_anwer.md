### 并发编程

- 程序的同步异步设计，MES在等待APS结果的时候采用异步设计

- tomcat 的异步servlet，用户线程处理耗时较长操作，比较tomcat工作线程阻塞
  - 在service方法中业务逻辑如果碰到io操作时间比较长的操作，这样这个service方法就会长时间占用tomcat容器线程池中的线程，这样是不利于其他请求的处理的，当线程池中的线程处理任务时，任务由于长时间io操作，肯定会阻塞线程处理其他任务，引入异步servlet的目的就是将容器线程池和业务线程池分离开。在处理大io的业务操作的时候，把这个操作移动到业务线程池中进行，释放容器线程，使得容器线程处理其他任务，在业务逻辑执行完毕之后，然后在通知tomcat容器线程池来继续后面的操作，这个操作应该是把处理结果commit到客户端或者是dispatch到其他servlet上。
- ui程序开线程处理其他程序

- Runnable更容易和线程池中高级API配合，且脱离了Thread的继承体系更加灵活

- Future除了继承Runnable还继承了另一个接口Callable，可以获取返回值，方便不同线程通信

#### 相关指令

- sleep是讲线程从Running到Timed Waiting，之后CPU资源一定时间内不会给此线程了，而yield则是Running到Runnalble,没有其他线程的情况下，还是会给CPU资源

- .join()就是线程同步
- .interrupted（）可以判断线程被打断以后是否继续运行，也可以用于代码中停止线程

- .stop()会真正杀死线程，但是锁不会释放，其资源其他线程也拿不到
- .System.exit(int)会直接杀死进程

#### 二阶段终止模式

- 两阶段线程重新设置打断标记是为了复用代码

- isInterrupted()不会清楚标记，interrupted()会清除标记变false

- park()在被interrupt（）之后不会再能打断线程了

#### 常见方法

- stop() suspend() resume()几个方法不推荐使用，原因是可能导致同步方法块的问题，锁资源拿不到导致死锁
- 垃圾回收器线程是守护线程，Tomcat负责发送和接受的Poller/Accepter也是守护线程，Tomcat收到shutDown命令以后，不会等待他们处理完当前请求，就会强行结束

- 五种状态六种状态

![image-20210303145407813](C:\Users\liuzhiji\AppData\Roaming\Typora\typora-user-images\image-20210303145407813.png)

### 线程安全

- 临界区 Critical Section
  一个程序运行多个线程本身是没有问题的
  问题出在多个线程访问共享资源
  多个线程读共享资源其实也没有问题
  在多个线程对共享资源读写操作时发生指令交错，就会出现问题
  一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区

- 竞态条件 Race Condition
  多个线程在临界区内执行，由于代码的执行序列不同而导致结果无法预测，称之为发生了竞态条件

- synchronized原理是用对象锁保证了临界区代码的原子性

- sysynchronized加在方法上相当于锁住this对象

- sysynchronized加在static方法上相当于锁住类对象

- 局部变量本身是线程安全的但是它引用的堆内存变量有可能不是线程安全，总体来说，变量范围只要逃离方法的作用范围，就不是线程安全，反之就是线程安全

- 方法的private 和类final修饰符某种程度上能提高线程安全 

- java 线程安全类
  - String
  - Integer...
  - StringBuffer
  - Random
  - Vector
  - HashTable
  - juc包下

- 线程安全指的是多个线程调用这些类的方法的时候，是线程安全的，也就是说即便单个方法能保证原子性，合在一起也不一定能保证原子性

- Integer和String线程安全的原因是，他们是不可变类，其只可读不可改

#### Synchronized底层原理

- Monitor原理
  - 当施加Synchronized的时候，会将被锁对象的markworlds设置为Monitor指针，并且获得锁的对象会被记录再Monitor的Owner之中，如果没能获取锁，则会进入Monitor的entryList队列，等待被唤醒之后进行竞争再争取锁，运行完代码，再将markword的指针重置成hashword,换型entryList中的其他线程

- 优化原理
  - 