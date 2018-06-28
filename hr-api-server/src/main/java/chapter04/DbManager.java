
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
   * properties�� XML �ε�
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
   * mybatis SqlSession
   * FIXME �������̽��� �и�? �ƴ� Factory�� �и�? Object Pool
   * @return
   */
  public SqlSession getSession(boolean autoCommit) {
    return sqlSessionFactory.openSession(autoCommit);
  }
}
