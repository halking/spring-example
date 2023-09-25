package com.hal.example.nio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ChannelTest {

  @Autowired
  private ResourceLoader resourceLoader;

  private Selector selector;
  private ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
  private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化

  String str;

  @Test
  public void transferFrom() throws Exception {
    Resource from = resourceLoader.getResource("classpath:document/from.txt");
    Resource to = resourceLoader.getResource("classpath:document/to.txt");
    try (RandomAccessFile fromFile = new RandomAccessFile(from.getFile(), "rw");
        RandomAccessFile toFile = new RandomAccessFile(to.getFile(), "rw")) {
      FileChannel fromChannel = fromFile.getChannel();
      toFile.getChannel().transferFrom(fromChannel, 0, fromChannel.size());
      System.out.println();
    }
  }

  @Test
  public void transferTo() throws Exception {
    Resource from = resourceLoader.getResource("classpath:document/from.txt");
    Resource to = resourceLoader.getResource("classpath:document/to.txt");
    try (RandomAccessFile fromFile = new RandomAccessFile(from.getFile(), "rw");
        RandomAccessFile toFile = new RandomAccessFile(to.getFile(), "rw")) {
      FileChannel fromChannel = fromFile.getChannel();
      fromChannel.transferTo(0, fromChannel.size(), toFile.getChannel());
    }
  }

  @Test
  public void socketChannel() throws Exception {
    try (SocketChannel socketChannel = SocketChannel.open()) {
      socketChannel.configureBlocking(false);
      socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5454));

      //打开选择器
      Selector selector = Selector.open();
      //注册连接服务器socket的动作
      socketChannel.register(selector, SelectionKey.OP_CONNECT);

      Scanner scanner = new Scanner(System.in);
      while (true) {
        //选择一组键，其相应的通道已为 I/O 操作准备就绪。
        //此方法执行处于阻塞模式的选择操作。
        selector.select();
        //返回此选择器的已选择键集。
        Set<SelectionKey> keys = selector.selectedKeys();
        System.out.println("keys=" + keys.size());
        Iterator<SelectionKey> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
          SelectionKey key = keyIterator.next();
          keyIterator.remove();
          // 判断此通道上是否正在进行连接操作。
          if (key.isConnectable()) {
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            System.out.println("server connected...");
            break;
          } else if (key.isWritable()) { //写数据
            System.out.print("please input message:");
            String message = scanner.nextLine();
            //ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
            writeBuffer.clear();
            writeBuffer.put(message.getBytes());
            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            //注册写操作,每个chanel只能注册一个操作，最后注册的一个生效
            //如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来
            //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
            //使用interest集合
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            socketChannel.register(selector, SelectionKey.OP_READ);

          } else if (key.isReadable()) {//读取数据
            System.out.print("receive message:");
            SocketChannel client = (SocketChannel) key.channel();
            //将缓冲区清空以备下次读取
            readBuffer.clear();
            int num = client.read(readBuffer);
            System.out.println(new String(readBuffer.array(), 0, num));
            //注册读操作，下一次读取
            socketChannel.register(selector, SelectionKey.OP_WRITE);
          }
        }
      }
    }
  }

  @Test
  public void serverSocketChannel() throws Exception {
    try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
      serverChannel.configureBlocking(false);
      serverChannel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), 5454));

      // 通过open()方法找到Selector
      selector = Selector.open();
      // 注册到selector，等待连接
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);

      while (true) {
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
          SelectionKey key = keyIterator.next();
          if (!key.isValid()) {
            continue;
          }
          if (key.isAcceptable()) {
            accept(key);
          } else if (key.isReadable()) {
            read(key);
          } else if (key.isWritable()) {
            write(key);
          }
          keyIterator.remove(); //该事件已经处理，可以丢弃
        }
      }
    }
  }

  @Test
  public void datagramChannelClient() throws Exception {
    try (DatagramChannel datagramChannel = DatagramChannel.open()) {
      datagramChannel.connect(new InetSocketAddress("127.0.0.1", 8099));
      datagramChannel.configureBlocking(false);

      String data = "this is a udp client..." + System.currentTimeMillis();
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      buffer.clear();
      buffer.put(data.getBytes());

      buffer.flip();
      while (buffer.hasRemaining()) {
        datagramChannel.write(buffer);
      }

//      int byteSent = datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 8099));
    }
  }

  @Test
  public void datagramChannelServer() throws Exception {
    try (DatagramChannel datagramChannel = DatagramChannel.open()) {
      datagramChannel.socket().bind(new InetSocketAddress(8099));
      datagramChannel.configureBlocking(false);

      while (true) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();

        datagramChannel.receive(buffer);
//       datagramChannel.read(buffer);
      }
    }
  }

  private void write(SelectionKey key) throws IOException, ClosedChannelException {
    SocketChannel channel = (SocketChannel) key.channel();
    System.out.println("write:" + str);

    writeBuffer.clear();
    writeBuffer.put(str.getBytes());
    writeBuffer.flip();
    channel.write(writeBuffer);
    channel.register(selector, SelectionKey.OP_READ);
  }

  private void read(SelectionKey key) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();

    // Clear out our read buffer so it's ready for new data
    this.readBuffer.clear();
//        readBuffer.flip();
    // Attempt to read off the channel
    int numRead;
    try {
      numRead = socketChannel.read(this.readBuffer);
    } catch (IOException e) {
      // The remote forcibly closed the connection, cancel
      // the selection key and close the channel.
      key.cancel();
      socketChannel.close();

      return;
    }

    str = new String(readBuffer.array(), 0, numRead);
    System.out.println(str);
    socketChannel.register(selector, SelectionKey.OP_WRITE);
  }

  private void accept(SelectionKey key) throws IOException {
    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
    SocketChannel clientChannel = ssc.accept();
    clientChannel.configureBlocking(false);
    clientChannel.register(selector, SelectionKey.OP_READ);
    System.out.println("a new client connected " + clientChannel.getRemoteAddress());
  }


}
