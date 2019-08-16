package com.hal.sample.util;

import static com.hal.sample.common.Constant.SESSION_STRICT_HOST_KEY_CHECKING;

import com.hal.sample.exception.BaseException;
import com.hal.sample.properties.ServerProperties.ServerInfo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/15
 */
@Slf4j
public class JschUtil {

  public static ChannelSftp createConnection(JSch jsch, ServerInfo serverInfo) {
    log.info("Try to connect sftp host {}, username {}", serverInfo.getHost(), serverInfo.getUsername());
    try {
      Session session = createSession(jsch, serverInfo);
      session.setPassword(serverInfo.getPassword());
      session.connect(serverInfo.getSessionTimeout());

      Channel channel = session.openChannel(serverInfo.getProtocol());
      channel.connect(serverInfo.getConnectionTimeout());

      return (ChannelSftp) channel;
    } catch (JSchException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }
  }

  /**
   * 加密秘钥方式登陆
   */
  public static ChannelSftp connectByKey(JSch jsch, ServerInfo serverInfo) {
    try {
      // 设置密钥和密码 ,支持密钥的方式登陆
      if (StringUtils.isNotBlank(serverInfo.getPrivateKey())) {
        if (StringUtils.isNotBlank(serverInfo.getPassphrase())) {
          // 设置带口令的密钥
          jsch.addIdentity(serverInfo.getPrivateKey(), serverInfo.getPassphrase());
        } else {
          // 设置不带口令的密钥
          jsch.addIdentity(serverInfo.getPrivateKey());
        }
      }

      log.info("Try to connect sftp host {}, username {}, privateKey {}, passphrase {}", serverInfo.getHost(),
        serverInfo.getUsername(), serverInfo.getPrivateKey(), serverInfo.getPassphrase());

      Session session = createSession(jsch, serverInfo);
      session.connect(serverInfo.getSessionTimeout());

      // 创建sftp通信通道
      Channel channel = session.openChannel(serverInfo.getProtocol());
      channel.connect(serverInfo.getConnectionTimeout());

      return (ChannelSftp) channel;
    } catch (JSchException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }
  }

  /**
   * 关闭连接
   */
  public static void disconnect(ChannelSftp sftp) {
    try {
      if (sftp != null) {
        if (sftp.isConnected()) {
          sftp.disconnect();
        } else if (sftp.isClosed()) {
          log.info("Sftp is closed already");
        }
        if (null != sftp.getSession()) {
          sftp.getSession().disconnect();
        }
      }
    } catch (JSchException e) {
      log.error("Close connection is failure");
      throw new BaseException(e.getMessage(), e);
    }
  }

  /**
   * create session
   */
  private static Session createSession(JSch jsch, ServerInfo serverInfo) {
    try {
      Session session = null;

      if (serverInfo.getPort() == null) {
        session = jsch.getSession(serverInfo.getUsername(), serverInfo.getHost());
      } else {
        session = jsch.getSession(serverInfo.getUsername(), serverInfo.getHost(), serverInfo.getPort());
      }

      if (session == null) {
        throw new BaseException("Create session failure");
      }

      session.setConfig(SESSION_STRICT_HOST_KEY_CHECKING, serverInfo.getStrictHostKeyChecking());

      return session;
    } catch (JSchException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }

  }
}
