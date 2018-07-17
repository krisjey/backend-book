
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;

import org.junit.Test;

import chapter02.RequestHandler;

public class RequestHandlerTest {

  @Test
  public void testEmployeeInfo() throws SQLException {
    try {
      Socket socket = mock(Socket.class);

      // 테스트 그리고 목업.
      when(socket.getRemoteSocketAddress()).thenReturn(new SocketAddress() {
      });
      when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(
          "GET /get_employee_info?emp_no=10240\n\n".getBytes()));
      when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

      RequestHandler handler = new RequestHandler(socket);
      handler.execute();

      ByteArrayOutputStream outputStream = (ByteArrayOutputStream) socket
          .getOutputStream();

      assertThat(outputStream).isNotNull();
      assertThat(outputStream.size()).isGreaterThan(0);

      ByteArrayInputStream inputStream = new ByteArrayInputStream(
          outputStream.toString().getBytes());
      InputStreamReader streamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(streamReader);

      while (true) {
        String line = bufferedReader.readLine();

        if (line.equals("")) {
          break;
        }
      }

      String line = bufferedReader.readLine();
      assertThat(line).isEqualTo(
          "10240|Remko Maccarone|Engineer|Quality Management|1998-10-06|78218");
    }
    catch (IOException e) {
      fail("Test Fail");
    }
  }

//"/get_department_info"
//"/get_department_employee_info"
}
