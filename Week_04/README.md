### 第七课：
#### <font color=red>第一题：(选做）</font>把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。
#### <font color=red>第二题：(必做）</font>思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程
具体代码请参考work_0702包下面的实现方式：
- Future实现，代码：ThreadPollAndFutureWork.java
- FutureTask实现，代码:FutureTaskWork
- CountDownLatch实现，代码：CountDownLatchWork.java
- CyclicBarrier实现，代码：CyclicBarrierWork.java
- ObjectWait实现，代码：ObjectWaitAndNotiry.java
- Phaser实现，代码：PhaserWork.java
- Semaphore实现，代码：SemaphoreWork.java
- CompletableFuture实现，代码：CompletableFutureWork

### 第八课
#### <font color=red>第一题（选做）</font>列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。
#### <font color=red>第二题（选做）</font>请思考：什么是并发？什么是高并发？实现高并发高可用系统需要考虑哪些因素，对于这些你是怎么理解的？
- 并发、并行、高并发的理解
1、并发针对的是处理，并行说的是执行
2、高并发说的是在单位时间内能够系统处理能力提升，如：缩短服务端响应时间、流量拆分

- 高并发面临的问题
1、小流量范围内的问题都不是问题，但是放大很多倍就是问题了如Redis缓存，单个丢失那么重新搜索一下数据问题不大，但是大面积丢失，那么可能数据库、缓存服务器就会崩溃
2、数据一致性问题，面对系统拆解，在单体应用中可以使用数据库事务，但是在分布式中就不是很好控制了
3、多线程领域下，任务拆解增加CPU使用率，同时编码复杂度变高，线程间数据同步、线程间任务调度就是问题了
4、面对集群越来越大，可能还跨国、跨省集群部署，人工部署已经不太可能了，需要依赖自动化部署工具，技术复杂度又要增加

#### <font color=red>第三题（选做）</font>请思考：还有哪些跟并发类似/有关的场景和问题，有哪些可以借鉴的解决办法。
#### <font color=red>第四题（必做）</font>把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github上

请参考《并发编程知识总结.png》
