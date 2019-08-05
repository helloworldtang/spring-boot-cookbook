package com.tangcheng.learning.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Bean {
    @Max(value = 100)
    @Min(value = 1)
    private final int number;

    @NotNull
    private final String text;

    public Bean(final int number, final String text) {
        this.number = number;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}