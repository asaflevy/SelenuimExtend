package com.outbrain.selenium.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTestWaitCondition {
  private final String host;
  private final int port;
  private final int timeout;

  public SocketTestWaitCondition(final String host, final int port, final int timeout) {
    this.host = host;
    this.port = port;
    this.timeout = timeout;
  }

  public boolean checkCondition(final long elapsedTimeInMs) {
    Socket socket = null;
    try {
      socket = new Socket();
      socket.bind(null);
      socket.connect(new InetSocketAddress(host, port), timeout);
      socket.close();
      return true;
    } catch (final IOException e) {
      return false;
    } finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (final IOException e) {
          // ignore
        }
      }
    }
  }
}