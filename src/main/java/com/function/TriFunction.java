package com.function;

/**
 * 自定义三个参数的构造函数
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <R>
 */
@FunctionalInterface
public interface TriFunction<T,U,V,R> {
    R apply(T t,U u,V v);
}
