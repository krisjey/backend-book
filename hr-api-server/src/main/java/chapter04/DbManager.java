
package chapter04;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbManager {
  private static final DbManager dbManager = new DbManager();

  private SqlSessionFactory sqlSessionFactory;

  /**
   * 싱글톤 객체를 가져온다
   * 
   * @return DbManager의 싱글톤 객체
   */
  public static DbManager getInstance() {
    return dbManager;
  }

  /**
   * XML로부터 SqlSessionFactory 객체를 생성한다
   */
  private DbManager() {
    try {
      InputStream mybatisFile = ClassLoader.getSystemResourceAsStream("mybatis-config.xml");

      sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisFile, "backend");
    }
    catch (Exception e) {
      throw new RuntimeException("Can not build mybatis connection!", e);
    }
  }

  /**
   * SqlSessionFactory 객체로부터 SqlSession 객체를 생성한다.
   * SqlSession의 autoCommit 값은 true다.
   * @return SqlSession
   */
  public SqlSession getSession() {
    return sqlSessionFactory.openSession(true);
  }
}
