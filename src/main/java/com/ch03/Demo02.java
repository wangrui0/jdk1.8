package com.ch03;

import com.predicate.IBufferedReaderProcessor;

import java.io.*;

/**
 * 环绕执行模式：
 * 传递行为正是Lambda的拿手好戏
 * 根据不同的行为做不同的事情；
 * 如何利用函数式接口来传递Lambda：
 * 功能：
 * 读文件的第一行。如果你想要返回头两行，甚至是返回使
 * 用最频繁的词，该怎么办呢？
 */
public class Demo02 {
    public static void main(String[] args) {
        try{
            String fileName="D://新建文本文档.txt";
            //读取一行
            String oneLine = processFile((BufferedReader br) -> br.readLine(),fileName);
            System.out.println(oneLine);
            //读取两行
            String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine(),fileName);
            System.out.println(twoLine);
        }catch (IOException exception ){

        }catch (Exception exception){

        }
    }

    /**
     * 处理文件
     *
     * @return
     */
    public static String processFile(IBufferedReaderProcessor bufferedReaderProcessor,String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            return bufferedReaderProcessor.process(bufferedReader);
        }
    }
}
