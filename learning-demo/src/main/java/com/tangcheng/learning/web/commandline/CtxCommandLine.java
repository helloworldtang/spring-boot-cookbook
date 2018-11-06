package com.tangcheng.learning.web.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/06/25 11:28
 */
@Slf4j
@Service
public class CtxCommandLine {


    /**
     * stream不能复用
     * java.lang.IllegalStateException: stream has already been operated upon or closed
     *
     * @param ctx
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.info("来看看 SpringBoot 默认为我们提供的 Bean：");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(System.out::println);
            Arrays.stream(beanNames).forEach(log::info);
        };
    }


}
