
package chapter04;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbManager {
  private static final DbManager dbHandler = new DbManager();

  private SqlSessionFactory sqlSessionFactory;

  /**
   * get instance for singleton
   * 
   * @return
   */
  public static DbManager getInstance() {
    return dbHandler;
  }

  /**
   * properties로 XML 로드
   */
  private DbManager() {
    try {
      InputStream mybatisFile = ClassLoader.getSystemResourceAsStream("mybatis-config.xml");

      sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisFile, "backend");
    }
    catch (Exception e) {
      throw new RuntimeException("Can not build mybatis connection from zookeeper!", e);
    }
  }

  /**
   * get mybatis SqlSession
   * @return
   */
  public SqlSession getSession() {
    return sqlSessionFactory.openSession(true);
  }
}
