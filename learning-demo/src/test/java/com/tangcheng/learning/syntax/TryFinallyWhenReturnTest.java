package com.tangcheng.learning.syntax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2020/4/5
 * @see
 * @since
 */
@Slf4j
public class TryFinallyWhenReturnTest {


    /**
     * 返回值 为java基本数据类型
     * finally语句块对try语句块返回值的更改无效
     */
    @Test
    public void try_with_return_and_finally_with_return_primitive_type() {
        int result = tryWithReturnAndFinallyWithReturnPrimitiveType();
        assertThat(result).isEqualTo(1001);
    }

    private int tryWithReturnAndFinallyWithReturnPrimitiveType() {
        int result = 1000;
        try {
            result = result + 1;
            log.info("try block result={}", result);
            return result;
        } finally {
            log.info("finally block before result={}", result);
            result = result + 1;
            log.info("finally after block result={}", result);
        }
    }


    /**
     * 返回值为java基本数据类型对应的包装类
     * finally语句块对try语句块返回值的更改无效
     */
    @Test
    public void try_with_return_and_finally_with_return_Java_primitive_type_wrapper() {
        Integer result = tryWithReturnAndFinallyWithReturnPrimitiveTypeWrapper();
        assertThat(result).isEqualTo(1001);
    }

    private Integer tryWithReturnAndFinallyWithReturnPrimitiveTypeWrapper() {
        Integer result = 1000;
        try {
            result = result + 1;
            log.info("try block result={}", result);
            return result;
        } finally {
            log.info("finally block before result={}", result);
            result = result + 1;
            log.info("finally after block result={}", result);
        }
    }


    /**
     * 返回值为引用数据类型时
     * finally语句块对try语句块返回值的更改有效
     */
    @Test
    public void try_with_return_and_finally_with_return_reference_type_List() {
        List<String> result = tryWithReturnAndFinallyWithReturnReferenceTypeList();
        assertThat(result).contains("try", "finally");
    }

    private List<String> tryWithReturnAndFinallyWithReturnReferenceTypeList() {
        List<String> result = new ArrayList<>();
        try {
            result.add("try");
            log.info("try block result={}", result);
            return result;
        } finally {
            log.info("finally before block result={}", result);
            result.add("finally");
            log.info("finally after block result={}", result);
        }
    }


    /**
     * 返回值为引用数据类型时
     * finally语句块对try语句块返回值的更改有效
     */
    @Test
    public void try_with_return_and_finally_with_return_reference_type_Custom_Data_type() {
        CustomDataType result = tryWithReturnAndFinallyWithReturnReferenceTypeCustomDataType();
        assertThat(result).isEqualTo(new CustomDataType(Long.valueOf("2"), "finally"));
    }

    private CustomDataType tryWithReturnAndFinallyWithReturnReferenceTypeCustomDataType() {
        CustomDataType customDataType = new CustomDataType();
        try {
            customDataType.setId(Long.valueOf("1"));
            customDataType.setName("try");
            log.info("try block ={}", customDataType);
            return customDataType;
        } finally {
            log.info("finally before block customDataType={}", customDataType);
            customDataType.setId(Long.valueOf("2"));
            customDataType.setName("finally");
            log.info("finally after block customDataType={}", customDataType);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CustomDataType {
        private Long id;
        private String name;
    }


}
