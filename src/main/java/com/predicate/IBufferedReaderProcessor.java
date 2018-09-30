package com.predicate;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 函数式接口
 */
@FunctionalInterface
public interface IBufferedReaderProcessor {
    /**
     * @param bufferedReader
     *      读入流
     * @return
     *       输出文本信息
     * @throws IOException
     */
    String process(BufferedReader bufferedReader) throws IOException;
}
