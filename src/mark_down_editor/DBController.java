package mark_down_editor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sql_resource.ConnectionResource;
import sql_resource.CrudResource;

/**
 * DB 操作を管理する為のコントローラ。
 */
public class DBController extends ConnectionResource implements CrudResource<Articles> {
    private final Logger logger;

    /**
     * @param property DB 接続時に使用する接続情報を納めた Map オブジェクトを指定する。
     */
    public DBController(Map<String, String> property) {
        super(property);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * @return recordset DB に登録されている全記事を納めたリストを返す。
     * @throws Exception {@link java.lang.Exception}
     */
    @Override
    public List<Articles> findAll() throws Exception {
        logger.info("DB へ接続しています......");
        final List<Articles> recordset = new ArrayList<Articles>();
        final Connection cn = getConnection();

        logger.info("レコードを取得しています......");
        try (PreparedStatement query = cn.prepareStatement("SELECT * FROM articles;");
                ResultSet rs = query.executeQuery();) {
            while (rs.next()) {
                final Articles record = new Articles();

                record.setId(rs.getLong("id"));
                record.setTitle(rs.getString("title"));
                record.setContents(rs.getString("contents"));

                recordset.add(record);
            }
        } finally {
            cn.close();
        }

        return recordset;
    }

    /**
     * ID をキーに記事を検索する。
     *
     * @param id 検索対象とする記事の id 番号を指定する。
     * @return Articles 指定した id に該当する記事を取得して返す。
     * @throws SQLException {@link java.sql.SQLException}
     */
    public Articles findById(long id) throws SQLException {
        logger.info("DB へ接続しています......");
        final Connection cn = getConnection();
        ResultSet rs = null;
        final Articles record = new Articles();

        logger.info("レコードを取得しています......");
        try (PreparedStatement query = cn.prepareStatement("SELECT * FROM articles WHERE id=?;");) {
            query.setLong(1, id);
            rs = query.executeQuery();

            if (rs.next()) {
                record.setId(rs.getLong("id"));
                record.setTitle(rs.getString("title"));
                record.setContents(rs.getString("contents"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            cn.close();
        }

        return record;
    }

    /**
     * DB へ記事を新規登録する。
     *
     * @param model 登録対象とするモデルオブジェクトを指定する。
     * @return status
     *         <ul>
     *         <li>true: 記事の登録に成功したことを表す。</li>
     *         <li>false: 記事の登録に失敗したことを表す。</li>
     *         </ul>
     */
    @Override
    public boolean create(Articles model) throws Exception {
        logger.info("DB へ接続しています......");
        boolean status = false;
        final Connection cn = getConnection();

        logger.info("新規レコードを登録しています......");
        try (PreparedStatement query = cn.prepareStatement("INSERT INTO articles (title, contents) VALUES(?, ?);")) {
            cn.setAutoCommit(false);

            query.setString(1, model.getTitle());
            query.setString(2, model.getContents());
            query.execute();

            cn.commit();
            status = true;
        } catch (final Exception e) {
            cn.rollback();
            throw e;
        } finally {
            cn.close();
        }

        return status;
    }

    /**
     * DB に登録されている記事を更新する。
     *
     * @param model 更新対象データを納めたモデルオブジェクトを指定する。
     * @return status
     *         <ul>
     *         <li>true: 記事の更新に成功したことを表す。</li>
     *         <li>false: 記事の更新に失敗したことを表す。</li>
     *         </ul>
     */
    @Override
    public boolean update(Articles model) throws Exception {
        logger.info("DB へ接続しています......");
        boolean status = false;
        final Connection cn = getConnection();

        logger.info("レコードを更新しています......");
        try (PreparedStatement query = cn.prepareStatement("UPDATE articles SET title=?, contents=? WHERE id=?;")) {
            cn.setAutoCommit(false);

            query.setString(1, model.getTitle());
            query.setString(2, model.getContents());
            query.setLong(3, model.getId());
            query.execute();

            cn.commit();
            status = true;
        } catch (final Exception e) {
            cn.rollback();
            throw e;
        } finally {
            cn.close();
        }

        return status;
    }

    /**
     * ID をキーに DB に登録されている記事を削除する。
     *
     * @param model 削除情報を納めたモデルオブジェクトを指定する。
     * @return status
     *         <ul>
     *         <li>true: 記事の削除に成功したことを表す。</li>
     *         <li>false: 記事の削除に失敗したことを表す。</li>
     *         </ul>
     */
    @Override
    public boolean delete(Articles model) throws Exception {
        logger.info("DB へ接続しています......");
        boolean status = false;
        final Connection cn = getConnection();

        logger.info("レコードを削除しています......");
        try (PreparedStatement query = cn.prepareStatement("DELETE FROM articles WHERE id=?;")) {
            cn.setAutoCommit(false);

            query.setLong(1, model.getId());
            query.execute();

            cn.commit();
            status = true;
        } catch (final Exception e) {
            cn.rollback();
            throw e;
        } finally {
            cn.close();
        }

        return status;
    }

    /**
     * DB に登録されている全記事を削除する。
     *
     * @return status
     *         <ul>
     *         <li>true: 記事の削除に成功したことを表す。</li>
     *         <li>false: 記事の削除に失敗したことを表す。</li>
     *         </ul>
     */
    @Override
    public boolean deleteAll() throws Exception {
        logger.info("DB へ接続しています......");
        boolean status = false;
        final Connection cn = getConnection();

        logger.info("レコードを削除しています......");
        try (PreparedStatement query = cn.prepareStatement("DELETE FROM articles;")) {
            cn.setAutoCommit(false);
            query.execute();
            cn.commit();

            status = true;
        } catch (final Exception e) {
            cn.rollback();
            throw e;
        } finally {
            cn.close();
        }

        return status;
    }
}
