package mark_down_editor.domain.repository.articles;

import java.sql.Connection;
import java.util.List;

import mark_down_editor.domain.model.Articles;

public interface ArticlesRepository {
    /**
     * テーブルからレコードを全件取得し、 List に変換して返す。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @return recordset 変換された List
     * @throws Exception {@link java.lang.Exception}
     */
    public List<Articles> findAll(Connection connection) throws Exception;

    /**
     * ID をキーにレコードを検索し、取得する。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @param id         検索対象とする記事の ID を指定する。
     * @return record 指定した ID を持つレコードを返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public Articles findById(Connection connection, long id) throws Exception;

    /**
     * 記事を新規登録する。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @param articles   登録対象とする Articles を指定する。
     * @throws Exception {@link java.lang.Exception}
     */
    public void create(Connection connection, Articles articles) throws Exception;

    /**
     * 既存の記事を更新する。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @param articles   更新対象とする Articles を指定する。
     * @throws Exception {@link java.lang.Exception}
     */
    public void update(Connection connection, Articles articles) throws Exception;

    /**
     * 記事を一件削除する。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @param articles   削除対象とする Articles を指定する。
     * @throws Exception {@link java.lang.Exception}
     */
    public void delete(Connection connection, Articles articles) throws Exception;

    /**
     * 既存の記事を全件削除する。
     *
     * @param connection 操作対象とする Connection を指定する。
     * @throws Exception {@link java.lang.Exception}
     */
    public void deleteAll(Connection connection) throws Exception;
}
