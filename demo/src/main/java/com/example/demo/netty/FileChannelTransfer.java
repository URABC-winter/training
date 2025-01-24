package com.example.demo.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileChannelTransfer {
    public static void main(String[] args) {
        try {
            FileChannel from = new FileInputStream("data1.txt").getChannel();
            FileChannel to = new FileOutputStream("to1.txt").getChannel();
            from.transferTo(1, from.size(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
