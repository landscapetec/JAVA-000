### 第9节课作业实践
#### <font color=red>1、（选做）使用Java里的动态代理，实现一个简单的AOP。</font>
- 动态代理接口
```
// 第一步：实现接口InvocationHandler
public class DefineProxy implements InvocationHandler {
    private Object realObject;

    public DefineProxy(Object realObject) {
        this.realObject = realObject;
    }

    public void setRealObject(Object realObject) {
        this.realObject = realObject;
    }

    private void before() {
        System.out.println("---------方法增强：执行前---------");
    }

    private void after() {
        System.out.println("---------方法增强：执行后---------");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(realObject, args);
        after();
        return result;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), this);
    }
}

//第二步：调用
Animal animal = new Dog("AQi");
DefineProxy defineProxy = new DefineProxy(animal);

Object proxyObject = defineProxy.createProxy();
Animal proxyAnimal = (Animal) proxyObject;
String result = proxyAnimal.eat("大块牛肉");
System.out.println(result);
```
- 动态代理类
```
//实现类：MethodInterceptor
public class EnhancerProxy implements MethodInterceptor {
    private Object targetObject;

    public EnhancerProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object createObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        Object object = enhancer.create();
        return object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invoke(targetObject, objects);
        after();
        return result;
    }


    private void before() {
        System.out.println("----方法执行-前：增强----");
    }

    private void after() {
        System.out.println("----方法执行-后：增强----");
    }
}
// 调用方
EnhancerProxy enhancerProxy = new EnhancerProxy(new Student("001", "lehman", 50));
        Student student = (Student) enhancerProxy.createObject();
        student.sayHello("good morning");
```

#### <font color=red>2、（必做）写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github。</font>
- 注解实现
```
//添加注解
@Configuration
public class AnnotationConfig {
    @Bean("school01")
    public ISchool school01() {
        return new School("第一个5A学校");
    }

}

//通过AnnotationConfigApplicationContext获取相关Bean
ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
ISchool school = (ISchool) context.getBean("school01");
school.ding();
```
- 配置文件实现<br/>
```
// 配置Bean 并通过属性注入关联对象
<bean id="class01" class="com.lsf.hw_0902.bean.entity.Klass">
    <property name="school" ref="school02"/>
    <property name="name" value="重点班级"/>
</bean>

// 加载使用
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
ISchool school=(ISchool) context.getBean("school02");
school.ding();
System.out.println(school);
```

#### <font color=red>3、（选做）实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School。</font>
#### <font color=red> 4.附加题：</font>
#### <font color=red> 4.1 （挑战）将网关的frontend/backend/filter/router/线程池都改造成Spring配置方式；</font>
#### <font color=red> 4.2 （挑战）基于AOP改造Netty网关，filter和router使用AOP方式实现；</font>
#### <font color=red> 4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用JMS传递消息；</font>
#### <font color=red> 4.4 （中级挑战）尝试使用ByteBuddy实现一个简单的基于类的AOP；</font>
#### <font color=red> 4.5 （超级挑战）尝试使用ByteBuddy与Instrument实现一个简单JavaAgent实现无侵入下的AOP；</font>

### 第10节课作业实践
#### <font color=red> 1. （选做）总结一下，单例的各种写法，比较它们的优劣。</font>
#### <font color=red> 2. （选做）maven/spring的profile机制，都有什么用法？</font>
#### <font color=red> 3. （必做）给前面课程提供的Student/Klass/School实现自动配置和Starter。</font>
- 第一步：构建Starter
```
// Spring boot声明配置前缀
@ConfigurationProperties(prefix = "test.config")
public class TestConfigProperties {
    private String configvar1;
    private int configvar2;
    
    public String getConfigvar1() {
        return configvar1;
    }
    public void setConfigvar1(String configvar1) {
        this.configvar1 = configvar1;
    }
    public int getConfigvar2() {
        return configvar2;
    }
    public void setConfigvar2(int configvar2) {
        this.configvar2 = configvar2;
    }
}
```
- 第二步：声明注册类
```
@Configuration
@EnableConfigurationProperties(TestConfigProperties.class)
public class TestConfigAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TestConfig.class)
    public TestConfig testConfigProperties(TestConfigProperties properties) {
        System.out.println("初始化testConfig");
        return new TestConfig(properties.getConfigvar1(), properties.getConfigvar2());
    }
}
```
- 第三步：声明配置文件：/resources/META-INF/spring.factories
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.lsf.TestConfigAutoConfiguration
```
- 第四步:使用
```
// 文件配置：
test.config.configvar1=ffhjfdasdf
test.config.config-var2=-2390

// 使用autowire
@Autowired
private TestConfig testConfig;
```
#### <font color=red> 4. （选做）总结Hibernate与MyBatis的各方面异同点。</font>
#### <font color=red> 5. （选做）学习MyBatis-generator的用法和原理，学会自定义TypeHandler处理复杂类型。</font>

#### <font color=red> 6. （必做）研究一下JDBC接口和数据库连接池，掌握它们的设计和用法：</font>
本题作业代码全部在/Week_05/main/java/com.lsf.hw_1006 文件夹中
#### <font color=red> 6.1. 使用JDBC原生接口，实现数据库的增删改查操作。</font>
```
public class JDBCUtil {
    // JDBC操作，数据库连接对象
    private Connection connection;
    public JDBCUtil(Connection connection) {
        this.connection = connection;
    }
    // 查询数据并转化为对象方法
    public <T extends Object> List<T> query(String queryString, Class<T> clz) throws SQLException, IllegalAccessException, InstantiationException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryString);
        Field[] fields = clz.getDeclaredFields();
        List<T> result = new ArrayList<>();
        T resultObject = null;
        while (resultSet.next()) {
            resultObject = clz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType() == Integer.class) {
                    field.set(resultObject, Integer.valueOf(resultSet.getInt(field.getName())));
                } else if (field.getType() == int.class) {
                    field.set(resultObject, resultSet.getInt(field.getName()));
                } else if (field.getType() == String.class) {
                    field.set(resultObject, resultSet.getString(field.getName()));
                }
            }
            result.add(resultObject);
        }
        return result;
    }
    // 执行数据库命令，并增加了事物处理
    public int executeSql(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        int result = 0;
        try {
            result = statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
        }
        return result;
    }
    // 插入数据并返回插入数据主键
    public int insert(String sql) throws SQLException {
        int result = -1;
        try {
            PreparedStatement prep = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;
            connection.rollback();
        }

        return result;
    }
}

```
#### <font color=red> 6.2. 使用事务，PrepareStatement方式，批处理方式，改进上述操作。</font>
参考上一题中的代码
#### <font color=red> 6.3. 配置Hikari连接池，改进上述操作。提交代码到Github。</font>
```
// Hikari 连接池使用
public class HikariConnectionUtil {
    private static DataSource dataSource;
    private static Object lockObject = new Object();

    static {
        synchronized (lockObject) {
            if (dataSource == null) {
                HikariConfig config = new HikariConfig("/HikariConfig.properties");
                dataSource = new HikariDataSource(config);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
}
// 配置文件HikariConfig.properties
jdbcUrl=jdbc:mysql://localhost:3306/order_db?useSSL=false&characterEncoding=utf-8
dataSource.user=root
dataSource.password=123456
#连接的闲置时间
dataSource.maxLifetime=604800
#连接池大小
dataSource.maximumPoolSize=18
dataSource.cachePrepStmts=true
dataSource.prepStmtCacheSize=250
dataSource.prepStmtCacheSqlLimit=2048
dataSource.useServerPrepStmts=true
dataSource.useLocalSessionState=true
dataSource.rewriteBatchedStatements=true
dataSource.cacheResultSetMetadata=true
dataSource.cacheServerConfiguration=true
dataSource.elideSetAutoCommits=true
dataSource.maintainTimeStats=false
```

#### <font color=red> 7.附加题（可以后面上完数据库的课再考虑做）：</font>
#### <font color=red> 7.1. (挑战)基于AOP和自定义注解，实现@MyCache(60)对于指定方法返回值缓存60秒。</font>
#### <font color=red> 7.2. (挑战)自定义实现一个数据库连接池，并整合Hibernate/Mybatis/Spring/SpringBoot。</font>
#### <font color=red> 7.3. (挑战)基于MyBatis实现一个简单的分库分表+读写分离+分布式ID生成方案。</font>