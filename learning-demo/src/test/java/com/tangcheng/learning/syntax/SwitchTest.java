package com.tangcheng.learning.syntax;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/09/14 17:05
 */
@Slf4j
public class SwitchTest {

    @Test
    public void givenByte_then() {
        Byte flag = 1;
        switchMethod(flag);
        flag = 2;
        switchMethod(flag);
        flag = 3;
        switchMethod(flag);
    }

    /**
     * 如果switch表达式是Byte类型，
     * case后面的数字也会自动转型为Byte类型
     * https://www.cnblogs.com/softidea/p/9647943.html
     *
     * @param flag
     */
    private void switchMethod(Byte flag) {
        switch (flag) {
            case 1:
                log.info("is 1");
                break;
            case 2:
                log.info("is 2");
                break;
            default:
                log.info("not match. value:" + flag);
        }
        log.info("success:" + flag);
    }

    @Test
    public void givenSwitchInFor_whenSwitchContinue_thenForContinue() {

        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0:
                    log.info("0 {}", i);
                    break;
                default:
                    log.info("default. {}", i);
                    continue;
            }
            log.info("continue to do for ");
        }

    }


}
