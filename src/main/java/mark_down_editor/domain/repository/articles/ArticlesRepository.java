package mark_down_editor.domain.repository.articles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mark_down_editor.domain.model.Articles;

/**
 * 記事の検索と登録・更新・削除を管理する。
 */
@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    /**
     * @param id 検索対象とする記事の id を指定する。
     * @return article 指定した id を保持する Articles エンティティを返す。
     */
    public Optional<Articles> findById(long id);
}
