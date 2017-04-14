package com.tangcheng.config;

import com.github.pagehelper.PageInterceptor;
import com.tangcheng.db.util.MyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by tang.cheng on 2017/4/11.
 */
@Configuration
public class MybatisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //此处使用的是tk.mybatis.spring.mapper.MapperScannerConfigurer
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setMarkerInterface(MyMapper.class);
        mapperScannerConfigurer.setBasePackage("com.tangcheng.db.mapper");
        Properties properties = new Properties();
        properties.put("notEmpty", false);//notEmpty：insert和update中，是否判断字符串类型!=''，少数方法会用到
        properties.put("IDENTITY", "MYSQL");//取回主键的方式，
        properties.put("style", "camelhump");//实体和表转换时的约定规则，默认驼峰转下划线。注解@Table和@Column优先级高于此配置
        //http://git.oschina.net/free/Mapper/blob/master/wiki/mapper3/2.Integration.md
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    /**
     * PageHelper Parameters
     * <p>
     * PageHelper provides several optional parameters, these parameters when used in accordance with the above two examples to configuration.
     * <p>
     * Optional parameters as follows:
     * <p>
     * dialect: Default paging using PageHelper way, if you want to implement your own page logic, you can implement Dialect(com.github.pagehelper.Dialect) interface, and then configure the attribute to the fully qualified name of the implementing class.
     * The following parameters are the parameters for the default dialect case. When implemented using a custom dialect, the following parameter has no effect.
     * <p>
     * helperDialect: PageHelper will detect the current database url by default, automatically select the corresponding database dialect. You can configure helperDialect Property to specify the dialect. You can use the following abbreviations :
     * oracle, mysql, mariadb, sqlite, hsqldb, postgresql, db2, sqlserver, informix, h2, sqlserver2012, derby.
     * You can also implement AbstractHelperDialect, and then configure the attribute to achieve the fully qualified class name.
     * Special note : When using the SqlServer2012 database, you need to manually specify for sqlserver2012, otherwise it will use the SqlServer2005 for paging.
     * <p>
     * offsetAsPageNum: Default value is false, This parameter is valid for RowBounds as a pagination parameter. When this parameter is set to true, theoffset parameter in RowBounds is used aspageNum.
     * <p>
     * rowBoundsWithCount: Default value is false, When this parameter is set to true, PageHelper will execute count query.
     * <p>
     * pageSizeZero: Default value is false, When this parameter is set to true, if pageSize=0 or RowBounds.Limit = 0 will query all the results (the equivalent of a Paged query did not execute, but the return type of the result is still Page).
     * <p>
     * reasonable: Rationalization of paging parameters, Default value is false。 When this parameter is set to true,pageNum <= 0 will query the first page, PageNum> pages (over the total number), will query the last page. Default false, the query directly based on parameters.
     * <p>
     * params: In support of startPage(Object params) method, The parameter is added to configure the parameter mapping for the value from the object based on the attribute name, you can configure pageNum,pageSize,count,pageSizeZero,reasonable, Default value is pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
     * <p>
     * supportMethodsArguments: Support via the Mapper interface parameters to pass the page parameter, the default value is 'false'. The use of methods can refer to the test code in the com.github.pagehelper.test.basic package under theArgumentsMapTest and ArgumentsObjTest.
     * <p>
     * autoRuntimeDialect: Default value is false。When set to true, it is possible to automatically recognize pagination of the corresponding dialect at run time from multiple data sources (Does not support automatic selection of sqlserver2012, can only usesqlserver), usage and precautions refer to the following Scene 5.
     * <p>
     * closeConn: Default value is true。 When you use a runtime dynamic data source or do not set the helperDialect property, PageHelper will automatically get the database type, then a database connection is automatically obtained, This property is used to set whether to close the connection, the default true close. When 'false' is set, It will not close the connection.
     * <p>
     * 4. How to choose Configure these parameters
     * <p>
     * Here are a few examples for some of the parameters may be used.
     * <p>
     * Scene 1
     * <p>
     * If you are still in with a way to call a namespace like iBATIS, you might use rowBoundsWithCount. If you want to count when the paging query query, you need to set this parameter to true.
     * <p>
     * Note: PageRowBounds also need true.
     * <p>
     * Scene 2
     * <p>
     * If you are still in with a way to call a namespace like iBATIS, If you think RowBounds in the two parametersoffset, limit not as good as pageNum, pageSize easy to understand. You can use the offsetAsPageNum parameter, when the parameter is set to true, offset as pageNum, limit and pageSize mean the same thing.
     * <p>
     * Scene 3
     * <p>
     * If you feel you have to paginate a page somewhere and you still want to query all the results with control parameters. You can configure pageSizeZero totrue, After configuration, when pageSize = 0 or RowBounds.limit = 0 will query all the results.
     * <p>
     * Scene 4
     * <p>
     * If you want the user to enter the page number is not in the legal scope (the first page to the last page) to correctly respond to the correct results page, Then you can configure reasonable totrue, and if pageNum <= 0 will query the first page, the pageNum> pages(total pages) will query the last page.
     * <p>
     * Scene 5
     * <p>
     * If you configure dynamic data sources in Spring and connect different types of databases, you can configure autoRuntimeDialect totrue, which will use matching pagination queries when using different data sources. In this case, you also need to pay attention to the closeConn parameter, because the type of access to the data source will get a database connection, so the need to control this parameter to obtain a connection, whether to close the connection.
     * <p>
     * Default is true, and some database connections can not be closed after the follow-up database operations. And some database connections will not be closed soon because the number of connections out of the database caused no response. Therefore, when using this feature, in particular, you need to pay attention to whether the use of the data source needs to close the database connection.
     * <p>
     * When you do not use dynamic data sources but only automatically get helperDialect, the database connection will only get once, so there is no need to worry about whether this connection will lead to a database error, but also according to the characteristics of the data source to choose whether to close the connection.
     * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
     * <p>
     * <p>
     * 分页插件参数介绍
     * <p>
     * 分页插件提供了多个可选参数，这些参数使用时，按照上面两种配置方式中的示例配置即可。
     * <p>
     * 分页插件可选参数如下：
     * <p>
     * dialect：默认情况下会使用 PageHelper 方式进行分页，如果想要实现自己的分页逻辑，可以实现 Dialect(com.github.pagehelper.Dialect) 接口，然后配置该属性为实现类的全限定名称。
     * 下面几个参数都是针对默认 dialect 情况下的参数。使用自定义 dialect 实现时，下面的参数没有任何作用。
     * <p>
     * helperDialect：分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。 你可以配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：
     * oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
     * 特别注意：使用 SqlServer2012 数据库时，需要手动指定为 sqlserver2012，否则会使用 SqlServer2005 的方式进行分页。
     * 你也可以实现 AbstractHelperDialect，然后配置该属性为实现类的全限定名称即可使用自定义的实现方法。
     * <p>
     * offsetAsPageNum：默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
     * <p>
     * rowBoundsWithCount：默认值为false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为true时，使用 RowBounds 分页会进行 count 查询。
     * <p>
     * pageSizeZero：默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是 Page 类型）。
     * <p>
     * reasonable：分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
     * <p>
     * params：为了支持startPage(Object params)方法，增加了该参数来配置参数映射，用于从对象中根据属性名取值， 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
     * <p>
     * supportMethodsArguments：支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。 使用方法可以参考测试代码中的 com.github.pagehelper.test.basic 包下的 ArgumentsMapTest 和 ArgumentsObjTest。
     * <p>
     * autoRuntimeDialect：默认值为 false。设置为 true 时，允许在运行时根据多数据源自动识别对应方言的分页 （不支持自动选择sqlserver2012，只能使用sqlserver），用法和注意事项参考下面的场景五。
     * <p>
     * closeConn：默认值为 true。当使用运行时动态数据源或没有设置 helperDialect 属性自动获取数据库类型时，会自动获取一个数据库连接， 通过该属性来设置是否关闭获取的这个连接，默认true关闭，设置为 false 后，不会关闭获取的连接，这个参数的设置要根据自己选择的数据源来决定。
     * <p>
     * 重要提示：
     * <p>
     * 当 offsetAsPageNum=false 的时候，由于 PageNum 问题，RowBounds查询的时候 reasonable 会强制为 false。使用 PageHelper.startPage 方法不受影响。
     * <p>
     * 4. 如何选择配置这些参数
     * <p>
     * 单独看每个参数的说明可能是一件让人不爽的事情，这里列举一些可能会用到某些参数的情况。
     * <p>
     * 场景一(rowBoundsWithCount)
     * <p>
     * 如果你仍然在用类似ibatis式的命名空间调用方式，你也许会用到rowBoundsWithCount， 分页插件对RowBounds支持和 MyBatis 默认的方式是一致，默认情况下不会进行 count 查询，如果你想在分页查询时进行 count 查询， 以及使用更强大的 PageInfo 类，你需要设置该参数为 true。
     * <p>
     * 注： PageRowBounds 想要查询总数也需要配置该属性为 true。
     * <p>
     * 场景二(offsetAsPageNum)
     * <p>
     * 如果你仍然在用类似ibatis式的命名空间调用方式，你觉得 RowBounds 中的两个参数 offset,limit 不如 pageNum,pageSize 容易理解， 你可以使用 offsetAsPageNum 参数，将该参数设置为 true 后，offset会当成 pageNum 使用，limit 和 pageSize 含义相同。
     * <p>
     * 场景三(pageSizeZero)
     * <p>
     * 如果觉得某个地方使用分页后，你仍然想通过控制参数查询全部的结果，你可以配置 pageSizeZero 为 true， 配置后，当 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果。
     * <p>
     * 场景四(reasonable)
     * <p>
     * 如果你分页插件使用于类似分页查看列表式的数据，如新闻列表，软件列表， 你希望用户输入的页数不在合法范围（第一页到最后一页之外）时能够正确的响应到正确的结果页面， 那么你可以配置 reasonable 为 true，这时如果 pageNum<=0 会查询第一页，如果 pageNum>总页数 会查询最后一页。
     * <p>
     * 场景五(autoRuntimeDialect,closeConn)
     * <p>
     * 如果你在 Spring 中配置了动态数据源，并且连接不同类型的数据库，这时你可以配置 autoRuntimeDialect 为 true，这样在使用不同数据源时，会使用匹配的分页进行查询。 这种情况下，你还需要特别注意 closeConn 参数，由于获取数据源类型会获取一个数据库连接，所以需要通过这个参数来控制获取连接后，是否关闭该连接。 默认为 true，有些数据库连接关闭后就没法进行后续的数据库操作。而有些数据库连接不关闭就会很快由于连接数用完而导致数据库无响应。所以在使用该功能时，特别需要注意你使用的数据源是否需要关闭数据库连接。
     * <p>
     * 当不使用动态数据源而只是自动获取 helperDialect 时，数据库连接只会获取一次，所以不需要担心占用的这一个连接是否会导致数据库出错，但是最好也根据数据源的特性选择是否关闭连接。
     * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
     * eg:http://www.jianshu.com/p/b0af2c0a7a9d
     *
     * @return
     */
//    @Bean
    public PageInterceptor pageInterceptor() {
        /**
         * The MyBatis-Spring-Boot-Starter will detects beans that implements following interface provided by MyBatis.
         Interceptor
         DatabaseIdProvider
         http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
         */
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");//配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：oracle,mysql,mariadb
        properties.put("offsetAsPageNum", true);//offsetAsPageNum：默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
        properties.put("rowBoundsWithCount", false);//默认值为false,使用 RowBounds 分页不会进行 count 查询
        properties.put("pageSizeZero", false);//默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果
        properties.put("reasonable", false);//默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        properties.put("supportMethodsArguments", true);//支持通过 Mapper 接口参数来传递分页参数，默认值false
        interceptor.setProperties(properties);
        return interceptor;
    }

}
