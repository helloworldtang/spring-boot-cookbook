package com.tangcheng.config.paging;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

/**
 * Created by tang.cheng on 2017/4/14.
 */
@Configuration
@ConditionalOnClass(SqlSessionFactory.class)
@EnableConfigurationProperties(PageHelperProperties.class)
public class PageHelperConfig implements EnvironmentAware {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private PageHelperProperties pageHelperProperties;

    private RelaxedPropertyResolver resolver;

    @Override
    public void setEnvironment(Environment environment) {
        resolver = new RelaxedPropertyResolver(environment, "pagehelper.");
    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = pageHelperProperties.getProperties();
        Map<String, Object> subProperties = resolver.getSubProperties("");
        for (String key : subProperties.keySet()) {
            if (!properties.containsKey(key)) {
                properties.setProperty(key, resolver.getProperty(key));
            }
        }
        interceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
    }


}
