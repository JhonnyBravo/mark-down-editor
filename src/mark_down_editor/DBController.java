package mark_down_editor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context_resource.ConnectionResource;
import context_resource.StatementResource;
import status_resource.Status;

/**
 * DB 操作を管理する為のコントローラ。
 */
public class DBController extends Status {
    private ConnectionResource cr;
    private StatementResource sr;

    /**
     * @param path DB の接続情報を記述したプロパティファイルのパスを指定する。
     */
    public DBController(String path) {
        this.cr = new ConnectionResource(path);
    }

    /**
     * @return List&lt;Articles&gt; DB に登録されている全記事を納めたリストを返す。
     */
    public List<Articles> findAll() {
        ResultSet rs = null;
        List<Articles> list = new ArrayList<Articles>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return list;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM articles;";

        this.sr = new StatementResource(connection, sql);
        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return list;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        long count = 0;

        try {
            rs = statement.executeQuery();

            while (rs.next()) {
                Articles articles = new Articles();

                articles.setId(rs.getLong("id"));
                articles.setTitle(rs.getString("title"));
                articles.setContents(rs.getString("contents"));

                list.add(articles);
                count++;
            }

            if (count > 0) {
                this.setCode(2);
            } else {
                this.initStatus();
            }
        } catch (SQLException e) {
            this.setCode(1);
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        return list;
    }

    /**
     * @param id 検索対象とする記事の id 番号を指定する。
     * @return Articles 指定した id に該当する記事を取得して返す。
     */
    public Articles findById(long id) {
        ResultSet rs = null;
        Articles result = null;
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM articles WHERE id=?;";

        this.sr = new StatementResource(connection, sql);
        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();

        try {
            statement.setLong(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                result = new Articles();
                result.setId(rs.getLong("id"));
                result.setTitle(rs.getString("title"));
                result.setContents(rs.getString("contents"));

                this.setCode(2);
            }
        } catch (SQLException e) {
            this.setCode(1);
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        return result;
    }

    /**
     * 記事を DB へ新規登録する。
     * 
     * @param articles 登録対象とする Articles を指定する。
     */
    public void create(Articles articles) {
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return;
        }

        Connection connection = (Connection) cr.getContext();
        cr.disableAutoCommit();

        String sql = "INSERT INTO articles (title,contents) VALUES(?,?);";

        this.sr = new StatementResource(connection, sql);
        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.enableAutoCommit();
            cr.closeContext();
            return;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();

        try {
            statement.setString(1, articles.getTitle());
            statement.setString(2, articles.getContents());
            statement.execute();
            cr.commit();
            this.setCode(2);
        } catch (SQLException e) {
            this.setCode(1);
            this.errorTerminate("エラーが発生しました。 " + e);
            cr.rollback();
        } finally {
            cr.enableAutoCommit();
            sr.closeContext();
            cr.closeContext();
        }
    }

    /**
     * DB に登録されている既存記事を更新する。
     * 
     * @param articles 更新対象とする Articles を指定する。
     */
    public void updateById(Articles articles) {
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return;
        }

        Connection connection = (Connection) cr.getContext();
        cr.disableAutoCommit();

        String sql = "UPDATE articles SET title=?,contents=? WHERE id=?;";

        this.sr = new StatementResource(connection, sql);
        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.enableAutoCommit();
            cr.closeContext();
            return;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();

        try {
            statement.setString(1, articles.getTitle());
            statement.setString(2, articles.getContents());
            statement.setLong(3, articles.getId());
            statement.execute();
            cr.commit();
            this.setCode(2);
        } catch (SQLException e) {
            this.setCode(1);
            this.errorTerminate("エラーが発生しました。 " + e);
            cr.rollback();
        } finally {
            cr.enableAutoCommit();
            sr.closeContext();
            cr.closeContext();
        }
    }

    /**
     * DB に登録されている記事を削除する。
     * 
     * @param id 削除対象とする記事の id 番号を指定する。
     */
    public void deleteById(long id) {
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return;
        }

        Connection connection = (Connection) cr.getContext();
        cr.disableAutoCommit();

        String sql = "DELETE FROM articles WHERE id=?;";

        this.sr = new StatementResource(connection, sql);
        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.enableAutoCommit();
            cr.closeContext();
            return;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();

        try {
            statement.setLong(1, id);
            statement.execute();
            cr.commit();
            this.setCode(2);
        } catch (SQLException e) {
            this.setCode(1);
            this.errorTerminate("エラーが発生しました。 " + e);
            cr.rollback();
        } finally {
            cr.enableAutoCommit();
            sr.closeContext();
            cr.closeContext();
        }
    }
}
