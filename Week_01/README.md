### 课后作业说明
- 第一题：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，分析一下对应的字节码。
<br/><br/>清参考doc文件中字节码章节

- 第二题：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内 容是一个 Hello.class 文件所有字节(x=255-x)处理后的文件。文件群里提供。
<br/><br/> 
<font color=#0099ff size=12>请参考类文件：MyClassLoader.java文件</font>

- 第三题：画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的 关系。
<br/><br/>清参考doc文件夹中1.JVM核心技术-基础知识.md  JVM内存模型章节

- 第四题：检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下 详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
<br/><br/>清参考doc文件中3.JVM核心技术-内存管理.md 