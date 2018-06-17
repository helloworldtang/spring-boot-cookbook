package com.tangcheng.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-08-07  18:59
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Byte age;

    public Optional<Byte> getAge() {
        return Optional.ofNullable(age);
    }
}
