package mark_down_editor.domain.service.articles;

import java.util.List;
import java_itamae_connection.domain.service.connection.ConnectionService;
import mark_down_editor.domain.model.Articles;

/**
 * articles テーブルへのレコード入出力を管理する。
 */
public abstract class ArticlesService extends ConnectionService {
  /**
   * テーブルからレコードを全件取得し、 {@link Articles} の {@link List} に変換して返す。
   *
   * @return recordset {@link Articles} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract List<Articles> findAll() throws Exception;

  /**
   * ID をキーにレコードを検索し、取得する。
   *
   * @param id 検索対象とする記事の ID を指定する。
   * @return record 指定した ID を持つ {@link Articles} を返す。
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract Articles findById(long id) throws Exception;

  /**
   * 記事を新規登録する。
   *
   * @param articles 登録対象とする {@link Articles} を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract void create(Articles articles) throws Exception;

  /**
   * 既存の記事を更新する。
   *
   * @param articles 更新対象とする {@link Articles} を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract void update(Articles articles) throws Exception;

  /**
   * 記事を一件削除する。
   *
   * @param articles 削除対象とする {@link Articles} を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract void delete(Articles articles) throws Exception;

  /**
   * 既存の記事を全件削除する。
   *
   * @throws Exception {@link java.lang.Exception}
   */
  public abstract void deleteAll() throws Exception;
}
