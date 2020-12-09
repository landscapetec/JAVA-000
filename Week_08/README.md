## 第 15 节课作业实践
#### 2、(必做)设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。并在新结构在演示常见的增删改查操作。代码、sql和配置文件，上传到github。
- 第一步：配置数据源
```
spring.shardingsphere.datasource.names=ds0,ds1
# 数据源：ds0
spring.shardingsphere.datasource.ds0.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds0.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.ds0.jdbc-url=jdbc:mysql://localhost:3307/order_db
spring.shardingsphere.datasource.ds0.username=root
spring.shardingsphere.datasource.ds0.password=
# 数据源：ds1
spring.shardingsphere.datasource.ds1.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds1.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.ds1.jdbc-url=jdbc:mysql://localhost:3308/order_db
spring.shardingsphere.datasource.ds1.username=root
spring.shardingsphere.datasource.ds1.password=
```
- 第二步：配置分片规则
```
spring.shardingsphere.sharding.tables.t_member_info.actual-data-nodes=ds$->{0..1}.t_member_info$->{0..15}
spring.shardingsphere.sharding.tables.t_member_info.database-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.t_member_info.database-strategy.inline..algorithm-expression=ds$->{id % 2}
spring.shardingsphere.sharding.tables.t_member_info.table-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.t_member_info.table-strategy.inline..algorithm-expression=t_member_info$->{id % 15}
```

<font color=red>备注：如果库是%2 表是%16 会出现ds0 奇数表数据为空，ds1 偶数表数据为空</font>

## 第 15 节课作业实践
#### 2、(必做)基于hmily TCC或ShardingSphere的Atomikos XA实现一个简单的分布式事务应用demo（二选一），提交到github。
<br/>参考Shardingsphere官网的样例
- 采用XANamespace的方式
```
TransactionTypeHolder.set(TransactionType.XA);
Connection connection = dataSource.getConnection();
connection.setAutoCommit(false);
try {
    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (id, status) VALUES (?, ?)");
    for (int i = 0; i < count; i++) {
        preparedStatement.setObject(1, i);
        preparedStatement.setObject(2, "init");
        preparedStatement.executeUpdate();
    }
    throw new Exception("asdfasdfasdfasdfasdfasdf");
} catch (Exception ex) {
    connection.rollback();
}
finally {
    TransactionTypeHolder.clear();
}
```

- 采用注解的方式
```
@Transactional
@ShardingTransactionType(TransactionType.XA)
public void insertOrderFail(final int count) {
    jdbcTemplate.execute("INSERT INTO t_order (id, status) VALUES (?, ?)",
            (PreparedStatementCallback<TransactionType>) preparedStatement -> {
                doInsert(count, preparedStatement);
                throw new SQLException("mock transaction failed");
            });
}
```
#### 未完成作业
第 15 节课作业实践
- 1、(选做)分析前面作业设计的表，是否可以做垂直拆分。
- 3、(选做)模拟1000万的订单单表数据，迁移到上面作业2的分库分表中。
- 4、(选做)重新搭建一套4个库各64个表的分库分表，将作业2中的数据迁移到新分库。

第 16 课作业实践
- 1、(选做)列举常见的分布式事务，简单分析其使用场景和优缺点。

- 3、(选做)基于ShardingSphere narayana XA实现一个简单的分布式事务demo。
- 4、(选做)基于seata框架实现TCC或AT模式的分布式事务demo。
- 5、(选做☆)设计实现一个简单的XA分布式事务框架demo，只需要能管理和调用2个MySQL的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。
- 6、(选做☆)设计实现一个TCC分布式事务框架的简单Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。
- 7、(选做☆)设计实现一个AT分布式事务框架的简单Demo，仅需要支持根据主键id进行的单个删改操作的SQL或插入操作的事务