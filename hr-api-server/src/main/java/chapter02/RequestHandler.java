
package chapter02;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class RequestHandler {
  private static final Logger logger = Logger.getLogger(RequestHandler.class);
  private Map<String, String> parameters = new HashMap<String, String>();
  private Socket connection;

  public RequestHandler(Socket connection) {
    this.connection = connection;
  }

  public void execute() throws IOException, SQLException {
    logger.debug("클라이언트 접속됨.");
    SocketAddress remoteAddress = this.connection.getRemoteSocketAddress();
    logger.debug("클라이언트 접속정보 : " + remoteAddress.toString());

    InputStream inputStream = null;
    InputStreamReader streamReader = null;
    BufferedReader bufferedReader = null;

    // 요청 데이터 파싱 처리
    try {
      inputStream = connection.getInputStream();
      streamReader = new InputStreamReader(inputStream);
      bufferedReader = new BufferedReader(streamReader);

      String requestLine = bufferedReader.readLine();
      String httpRequest[] = requestLine.split(" ");
      String requestMethod = httpRequest[0];

      String requestPath = null;

      if (httpRequest[1].contains("?")) {
        requestPath = httpRequest[1].split("[?]")[0];
        parseQueryString(httpRequest[1].split("[?]")[1]);
      }
      else {
        requestPath = httpRequest[1];
      }

      logger.info("HTTP 요청 : " + requestLine);

      while (true) {
        String httpHeader = bufferedReader.readLine();
        if (httpHeader == null || httpHeader.length() == 0) {
          break;
        }
        else {
          logger.info(httpHeader);
        }
      }

      StringBuffer buffer = null;

      // API 요청 URL 분기
      if (requestPath.equals("/get_employee_info")) {
        EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
        buffer = employeeInfoDao.getEmployeeInfoByNo(parameters.get("emp_no"));
      }
      else if (requestPath.equals("/get_department_info")) {
        DepartmentDao departmentDao = new DepartmentDao();
        buffer = departmentDao.getDepartmentInfo();
      }
      else if (requestPath.equals("/get_department_employee_info")) {
        DepartmentDao departmentDao = new DepartmentDao();
        buffer = departmentDao
            .getDepartmentEmployeeInfo(parameters.get("dept_name"), 10);
      }
      else {
        buffer = new StringBuffer(1024);
        buffer.append("404");
        buffer.append("|");
        buffer.append("존재하지 않는 API");
        buffer.append("|");
        buffer.append(requestMethod);
        buffer.append("|");
        buffer.append(requestPath);
        buffer.append("\r\n");
      }

      // 데이터 응답 로그 작성
      logger.info("응답 : " + buffer.toString());

      response200(connection.getOutputStream(), buffer.toString());
    }
    catch (Exception e) {
      response500(connection.getOutputStream());
      logger.error(e.getMessage());
    }
    finally {
      bufferedReader.close();
      connection.close();
    }
  }

  private void parseQueryString(String queryStrings) {
    for (String queryString : queryStrings.split("&")) {
      String keyValue[] = queryString.split("=");
      this.parameters.put(keyValue[0], keyValue[1]);
    }
  }

  private void response500(OutputStream outputStream) {
    try {
      DataOutputStream dos = new DataOutputStream(outputStream);
      dos.writeBytes("HTTP/1.1 500 Internal Server Error \r\n");
      dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
      dos.writeBytes("\r\n");

      dos.write("500 Internal Server Error".getBytes());
      dos.writeBytes("\r\n");
      dos.flush();
    }
    catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

  private void response200(OutputStream outputStream, String body) {
    try {
      byte[] data = body.getBytes();
      DataOutputStream dos = new DataOutputStream(outputStream);
      dos.writeBytes("HTTP/1.1 200 OK \r\n");
      dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
      dos.writeBytes("Content-Length: " + data.length + "\r\n");
      dos.writeBytes("\r\n");

      dos.write(data);
      dos.writeBytes("\r\n");
      dos.flush();
    }
    catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}
