package com.predicate;

/**
 * 将类型抽象化
 *
 * @param <T>
 */
public interface IPredicate<T> {
    boolean check(T t);
}
