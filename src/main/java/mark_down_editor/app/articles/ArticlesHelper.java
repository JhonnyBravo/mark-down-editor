package mark_down_editor.app.articles;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import mark_down_editor.domain.service.articles.ArticlesService;
import mark_down_editor.domain.service.articles.ArticlesServiceImpl;

public class ArticlesHelper {
    private final ConnectionInfoService cis;

    public ArticlesHelper() {
        cis = new ConnectionInfoServiceImpl();
    }

    /**
     * @param path DB の接続情報を記述した設定ファイルのパスを指定する。
     * @return connectionInfo DB の接続情報を読込み、 ConnectionInfo に変換して返す。
     * @throws Exception {@link java.lang.Exception}
     */
    private ConnectionInfo getConnectionInfo(String path) throws Exception {
        final ContentsAttribute attr = new ContentsAttribute();
        attr.setPath(path);

        final ConnectionInfo connectionInfo = cis.getConnectionInfo(attr);
        return connectionInfo;
    }

    /**
     * @param path DB の接続情報を記述した設定ファイルのパスを指定する。
     * @return articlesService ArticlesService のインスタンスを返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public ArticlesService getArticlesService(String path) throws Exception {
        final ArticlesService service = new ArticlesServiceImpl(getConnectionInfo(path));
        return service;
    }
}
