
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
      logger.info("API 서버가 " + SERVICE_PORT + "포트로 시작되었습니다.");

      // 클라이언트가 연결될때까지 대기한다.
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
