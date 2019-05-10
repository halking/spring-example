package com.hal.sample.nio;

import java.io.RandomAccessFile;
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
 * @Date: 2019/5/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ChannelTest {

  @Autowired
  private ResourceLoader resourceLoader;

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

}
