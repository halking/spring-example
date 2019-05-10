package com.hal.sample.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BufferTest {

  @Autowired
  private ResourceLoader resourceLoader;

  @Test
  public void byteBufferReadTest() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:document/user.csv");
    try (RandomAccessFile accessFile = new RandomAccessFile(resource.getFile(), "rw")) {

      FileChannel fileChannel = accessFile.getChannel();

      ByteBuffer buffer = ByteBuffer.allocate(1024);

      System.out.println("position:" + buffer.position());
      System.out.println("limit:" + buffer.limit());
      System.out.println("capacity:" + buffer.capacity());

      int sentine = fileChannel.read(buffer);

      while (sentine != -1) {
        buffer.flip();

        System.out.println("<--------------------------------- flip : " + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        while (buffer.hasRemaining()) {
          System.out.println((char) buffer.get());
        }

        buffer.clear();
        sentine = fileChannel.read(buffer);
      }

      System.out.println("<--------------------------------->" + buffer.mark());
      System.out.println("position:" + buffer.position());
      System.out.println("limit:" + buffer.limit());
      System.out.println("capacity:" + buffer.capacity());

    }

  }

  @Test
  public void byteBufferWriterTest() throws Exception {

  }


  @Test
  public void charBufferTest() {

  }
}
