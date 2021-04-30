package com.yolyn.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/30 9:18 PM
 * @project netty-learning
 *
 * 1。 MappedByteBuffer 可以让文件直接在内存（对外内存）修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数 1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数 2: 0 : 可以直接修改的起始位置
         * 参数 3: 5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存 * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        mappedByteBuffer.put(5, (byte) 'Y');//IndexOutOfBoundsException
        randomAccessFile.close();
        System.out.println("修改成功~~");
    }


}
