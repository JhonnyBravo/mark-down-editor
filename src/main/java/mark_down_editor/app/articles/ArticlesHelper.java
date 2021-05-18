package mark_down_editor.app.articles;

import java.util.Properties;
import java_itamae_connection.domain.model.ConnectionInfo;
import mark_down_editor.domain.service.articles.ArticlesService;
import mark_down_editor.domain.service.articles.ArticlesServiceImpl;

/**
 * {@link ArticlesService} の生成を管理する。
 */
public class ArticlesHelper {
  /**
   * connection.properties から DB の接続情報を読込み、 {@link ConnectionInfo} に変換して返す。
   *
   * @param path connection.properties のパスを指定する。
   * @return connectionInfo {@link ConnectionInfo} を返す。
   * @throws Exception {@link java.lang.Exception}
   */
  private ConnectionInfo getConnectionInfo(String path) throws Exception {
    final Properties properties = new Properties();
    properties.load(this.getClass().getClassLoader().getResourceAsStream(path));

    final ConnectionInfo cnInfo = new ConnectionInfo();
    cnInfo.setHostName(properties.getProperty("hostName"));
    cnInfo.setPortNumber(properties.getProperty("portNumber"));
    cnInfo.setDbName(properties.getProperty("dbName"));
    cnInfo.setUserName(properties.getProperty("userName"));
    cnInfo.setPassword(properties.getProperty("password"));
    cnInfo.setEncoding(properties.getProperty("encoding"));
    cnInfo.setTimeZone(properties.getProperty("timeZone"));

    return cnInfo;
  }

  /**
   * connection.properties から DB の接続情報を読込み、 {@link ArticlesService} を生成する。
   *
   * @param path connection.properties のパスを指定する。
   * @return articlesService {@link ArticlesService} のインスタンスを返す。
   * @throws Exception {@link java.lang.Exception}
   */
  public ArticlesService getArticlesService(String path) throws Exception {
    final ArticlesService service = new ArticlesServiceImpl(getConnectionInfo(path));
    return service;
  }
}
