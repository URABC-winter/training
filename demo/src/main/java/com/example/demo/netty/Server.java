package com.example.demo.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Logger;


public class Server {
    public static void main(String[] args) throws IOException {
        Logger log = Logger.getLogger("Server");
        //1. 创建selector， 管理多个 channel
        Selector selector = Selector.open();
        //创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);       //非阻塞模式

        //2. 建立 selector 和 channel 的联系（注册）
        //SelectionKey 就是将来事件发生后，通过它可以知道时间和哪个channel发生事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        //key 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register key: " + sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            //3. select 方法，没有事件发生，线程阻塞，有事件，线程才恢复运行
            selector.select();
            //4. 处理事件， selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                log.info("key: " + key);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel sc = channel.accept();
                log.info("sc: " + sc);
            }
        }
    }
}
