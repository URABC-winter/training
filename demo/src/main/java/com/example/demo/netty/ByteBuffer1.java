package com.example.demo.netty;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBuffer1 {
    public static void main(String[] args) {
        // FileChannel
        // 1.输入输出流, 2.RandomAccessFile
        try (FileChannel channel = new FileInputStream("data1.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
//            // 从 channel 中读取数据, 向 buffer 中写入数据
//            channel.read(buffer);
//            //打印buffer的内容
//            buffer.flip(); // 切换到读模式

            int len = 0;
            while (len != -1) {
                len = channel.read(buffer);
                //打印buffer的内容
                buffer.flip(); // 切换到读模式
                System.out.println("remain: " + buffer.remaining());
            }

            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                System.out.println((char) b);
            }
        } catch (Exception e) {

        }
    }
}
