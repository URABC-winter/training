package com.example.demo.netty;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class FilesWalkFIleTree {
    public static void main(String[] args) throws IOException {
        //测试Path
        /*
        Path path = Paths.get("C:\\Program Files\\Java\\jre1.8.0_261");
        System.out.println(path);
        System.out.println(path.getFileName());
        System.out.println(path.getName(0));
        System.out.println(path.getName(2));
        System.out.println(path.toAbsolutePath());
        */


        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("C:\\Program Files\\Java\\jre1.8.0_261"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("===>"+dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("目录数量："+ dirCount);
        System.out.println("文件数量："+ fileCount);
    }
}
