# seckill
秒杀系统

>>秒杀其实主要解决两个问题，一个是并发读写，并发读的核心优化理念是尽量减少用户到服务端来“读”数据，或者让他们读更少的数据；并发写的处理原则也一样，它要求我们在数据库层面独立出来一个库，做特殊的处理。另外，我们还要针对秒杀系统做一些保护，针对意料之外的情况设计兜底方案，以防止最坏的情况发生。

>>软件架构

>>技术	版本	说明
>>Spring Boot	MySQL	MyBatis Plus	Swagger2 Kinfe4j	Spring Boot Redis		

>>秒杀系统优化：
页面缓存
页面静态化
短时间内，下单多次-----采用唯一的id，进行抢购时要验证下id
超卖现象----要做好判断
通过redis预减库存
内存标记 减少对redis的访问
请求进入队列  异步下单
客户端轮询
Redis分布式锁
接口优化：初始化时 把库存加载到redis中
接口限流

实战截图:
>>登陆图

![登陆图](https://github.com/YyXCyj/seckill-system/blob/master/image/1.png)

>>秒杀商量列表

![秒杀商量列表](https://github.com/YyXCyj/seckill-system/blob/master/image/2.png)

>>秒杀详情列表

![秒杀详情列表](https://github.com/YyXCyj/seckill-system/blob/master/image/3.png)
