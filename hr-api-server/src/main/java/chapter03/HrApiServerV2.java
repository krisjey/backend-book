
package chapter03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import chapter03.RequestHandler;

public class HrApiServerV2 {
  private static final int SERVICE_PORT = 8088;
  private static Logger logger = Logger.getLogger(HrApiServerV2.class);

  public static void main(String[] args) throws IOException, SQLException {
    ServerSocket listenSocket = new ServerSocket(SERVICE_PORT);
    try {
      logger.info("API ������ " + SERVICE_PORT + "��Ʈ�� ���۵Ǿ����ϴ�.");

      // Ŭ���̾�Ʈ�� ����ɶ����� ����Ѵ�.
      Socket connection = null;
      while (true) {
        connection = listenSocket.accept();
        RequestHandler requestHandler = new RequestHandler(connection);
        requestHandler.execute();
      }
    }
    catch (IOException e) {
      logger.error(e);
    }
    finally {
      listenSocket.close();
    }
  }
}
