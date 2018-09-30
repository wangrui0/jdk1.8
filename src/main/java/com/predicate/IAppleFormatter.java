package com.predicate;

import com.entity.Apple;

public interface IAppleFormatter {
    /**
     * 以规定的格式输出苹果
     * @param a
     * @return
     */
    String accept(Apple a);
}
