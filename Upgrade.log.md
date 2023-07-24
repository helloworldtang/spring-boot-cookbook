升级的内容：     
1、Spring Boot从1.5.18.RELEASE升级到2.7.14    
2、spring-boot-cookbook的版本从1.0.1升级1.0.2     
3、flyway版本从4.1.2升级到9.21.0      
4、mybatis-spring-boot-starter从1.3.0升级到2.3.1     
5、mysql-connector-java升级到mysql-connector-j

##### java.lang.NoClassDefFoundError: com/fasterxml/jackson/annotation/JsonKey

报错1：

```
Caused by: java.lang.NullPointerException: null
	at springfox.documentation.spi.service.contexts.Orderings$8.compare(Orderings.java:113)
```

报错2：

```
Caused by: java.lang.NoClassDefFoundError: com/fasterxml/jackson/annotation/JsonKey
	at com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector.hasAsKey(JacksonAnnotationIntrospector.java:1094)
	at com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector._addFields(POJOPropertiesCollector.java:496)
	at com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector.collectAll(POJOPropertiesCollector.java:421)
	at com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector.getPropertyMap(POJOPropertiesCollector.java:386)
	at com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector.getProperties(POJOPropertiesCollector.java:233)
	at com.fasterxml.jackson.databind.introspect.BasicBeanDescription._properties(BasicBeanDescription.java:164)
	at com.fasterxml.jackson.databind.introspect.BasicBeanDescription.findProperties(BasicBeanDescription.java:239)
	at springfox.documentation.schema.property.OptimizedModelPropertiesProvider.propertiesFor(OptimizedModelPropertiesProvider.java:124)
	at springfox.documentation.schema.property.OptimizedModelPropertiesProvider.propertiesFor(OptimizedModelPropertiesProvider.java:118)
	at springfox.documentation.schema.property.CachingModelPropertiesProvider$1.load(CachingModelPropertiesProvider.java:56)
	at springfox.documentation.schema.property.CachingModelPropertiesProvider$1.load(CachingModelPropertiesProvider.java:54)
	at com.google.common.cache.LocalCache$LoadingValueReference.loadFuture(LocalCache.java:3571)
	at com.google.common.cache.LocalCache$Segment.loadSync(LocalCache.java:2313)
	at com.google.common.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2190)
	at com.google.common.cache.LocalCache$Segment.get(LocalCache.java:2080)
	... 37 common frames omitted
Caused by: java.lang.ClassNotFoundException: com.fasterxml.jackson.annotation.JsonKey
	at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:355)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
	... 52 common frames omitted
```

解决办法：
长期方案： 使用最新版本的swagger3

短期方案：
不启用swagger

```java
//@EnableSwagger2
```

##### org/mybatis/spring/support/SqlSessionDaoSupport has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to 52.0

```
java.lang.UnsupportedClassVersionError: org/mybatis/spring/support/SqlSessionDaoSupport has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to 52.0
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:473)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
```

解决办法：
将mybatis-spring-boot-starter的版本从1.3.0升级到2.3.1
本次不升级到3.0.2 。
3.0需要java 17 or higher
http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/

```java
Caused by:org.springframework.beans.factory.BeanCreationException:
        Error creating bean with name'flywayInitializer'defined in

class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]:Invocation of init method failed;nested exception is org.flywaydb.core.api.FlywayException:Unsupported Database:MySQL8.0
```

原因：
8.2.0

```properties
Flyway 8.2.0 (2021-11-30)
Breaking changes
Removing MySQL Driver from inclusion in Flyway distribution due to License. MariaDB will be used as fallback driver if no MySQL driver is present on the Classpath.
https://documentation.red-gate.com/fd/release-notes-for-flyway-engine-179732572.html#8.2.1
```

解决办法:
方法1：
增加插件：
Flyway 8.2.1 (2021-12-07)
Bug fixes
Escape db name during doExists in MSSQL
Changes
Extract MySQL code to plugin. This will need to be added as a new dependency. See MySQL documentation.
Flyway 8.2.0 (2021-11-30)
Breaking changes
Removing MySQL Driver from inclusion in Flyway distribution due to License. MariaDB will be used as fallback driver if no MySQL driver is present on the Classpath.

方法2：
使用8.2.0及之前的flyway版本

本次采用增加依赖的办法：

```xml

<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
    <version>9.21.0</version>
</dependency>
```