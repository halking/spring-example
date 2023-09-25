package com.hal.example.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/10
 */
public class TcpClient {

  private SocketChannel channel;

  private ByteBuffer buffer;

  public TcpClient(ByteBuffer buffer, SocketAddress address) {
    this.buffer = buffer;
    openChannel(address);
  }

  private void openChannel(SocketAddress address) {
    try {
      this.channel = SocketChannel.open(address);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() {

  }

  public String sendMsg(Object msg) {

    String response = null;
    try {
      buffer = ByteBuffer.wrap(String.valueOf(msg).getBytes());
      buffer.flip();
      while (buffer.hasRemaining()) {
        channel.write(buffer);
      }

      response = new String(buffer.array()).trim();
      System.out.println("response=" + response);
      buffer.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

}
