package com.db.mapper;

import com.db.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
Advanced scanning
        MyBatis-Boot-Starter will search, by default, for mappers marked with the @Mapper annotation.
        You may want to specify a custom annotation or a marker interface for scanning. If so, you must use the @MapperScan annotation. See more about it in the MyBatis-Spring reference page.
        MyBatis-Boot-Starter will not start the scanning process if it finds at least one MapperFactoryBean in the Spring's context so if you want to stop the scanning at all you should register your mappers explicitly with @Bean methods.

        Using an SqlSession
        An instance of a SqlSessionTemplate is created and added to the context, so you can use the MyBatis API letting it be injected into your beans like follows
            @Component
            public class CityDao {

                @Autowired
                private SqlSession sqlSession;

                public City selectCityById(long id) {
                    return this.sqlSession.selectOne("selectCityById", id);
                }

            }
http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
*/
@Mapper
public interface CityMapper {
    @Select("SELECT * FROM CITY WHERE state = #{state}")
    List<City> findByState(@Param("state") String state);

}