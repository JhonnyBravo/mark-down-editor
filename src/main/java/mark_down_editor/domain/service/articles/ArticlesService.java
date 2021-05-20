package mark_down_editor.domain.service.articles;

import java.util.List;
import mark_down_editor.domain.model.Articles;

/**
 * 記事の取得、登録、更新、削除を管理する。
 */
public interface ArticlesService {
  /**
   * 登録されている全記事を取得する。
   *
   * @return articles {@link Articles} の {@link List}
   */
  List<Articles> findAll();

  /**
   * ID をキーに記事を検索する。
   *
   * @param id 取得対象とする記事の ID を指定する。
   * @return articles 条件に合致する ID を持つ {@link Articles}
   */
  Articles findById(long id);

  /**
   * 記事を登録または更新する。
   *
   * @param articles 登録または更新の対象とする {@link Articles} を指定する。
   * @return articles 登録された {@link Articles}
   */
  Articles edit(Articles articles);

  /**
   * 記事を削除する。
   *
   * @param articles 削除対象とする {@link Articles} を指定する。
   */
  void delete(Articles articles);

  /**
   * 登録されている全記事を削除する。
   */
  void deleteAll();
}
