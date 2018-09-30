package com.tangcheng.learning.syntax;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/09/30 09:55
 */
@Slf4j
public class PerformanceAnalysisUseExceptionTest {

    /**
     * 相同条件下，同样的业务逻辑和IO下，比较抛异常和不抛异常场景下，
     * 在性能上有什么区别
     */
    @Test
    public void givenTwoScenario_whenOneHasExceptionAndAnotherNot_thenGetTheElapsedTime() {
        int length = 1000000;
        StopWatch stopWatch = new StopWatch("OneHasExceptionAndAnotherNot");
        stopWatch.start("hasNoException");
        for (int i = 0; i < length; i++) {
            doBizHasNoException(String.valueOf(i));
        }
        stopWatch.stop();

        stopWatch.start("hasException");
        for (int i = 0; i < length; i++) {
            doBizHasException(String.valueOf(i));
        }
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());
    }

    /**
     * 没有抛异常的场景【也打印个日志】
     *
     * @param i
     */
    public void doBizHasNoException(String i) {
        try {
            log.info("result:{}", i);
        } catch (Exception e) {
            log.warn("{}", e.getMessage());
        }
    }

    /**
     * 抛异常的场景【打印个日志】
     *
     * @param i
     */
    public void doBizHasException(String i) {
        try {
            throw new IllegalArgumentException(i);
        } catch (Exception e) {
            log.warn("{}", e.getMessage());
        }
    }


}
