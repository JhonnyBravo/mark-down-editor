package mark_down_editor.domain.service.articles;

import java.util.List;

import mark_down_editor.domain.model.Articles;

public interface ArticlesService {
    /**
     * @return articles 登録されている全記事の一覧を取得し、 {@link List} に変換して返す。
     */
    List<Articles> findAll();

    /**
     * @param id 取得対象とする記事の ID を指定する。
     * @return articles 条件に合致する記事を取得して返す。
     */
    Articles findById(long id);

    /**
     * 記事を登録または更新する。
     *
     * @param articles 登録または更新の対象とする {@link Articles} を指定する。
     * @return articles 登録された記事を返す。
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
