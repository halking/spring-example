package com.hal.sample.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectorTest {

  @Test
  public void registerChannel() throws Exception {
    //new Selector
    Selector selector = Selector.open();
    //open SocketChannel
    SocketChannel channel = SocketChannel.open(new InetSocketAddress("http://localhost", 80));
    //Register Channel
    channel.configureBlocking(false);
    channel.register(selector, SelectionKey.OP_READ);
    
    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
    while (keyIterator.hasNext()) {
      SelectionKey key = keyIterator.next();
      if (key.isConnectable()) {

      } else if (key.isAcceptable()) {

      } else if (key.isReadable()) {

      } else if (key.isWritable()) {

      }
      keyIterator.remove();
    }


  }
}
