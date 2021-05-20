package mark_down_editor.domain.repository.articles;

import java.util.Optional;
import mark_down_editor.domain.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 記事の検索と登録・更新・削除を管理する。
 */
@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
  /**
   * ID をキーに記事を検索する。
   *
   * @param id 検索対象とする記事の ID を指定する。
   * @return article 指定した ID を保持する {@link Articles}
   */
  Optional<Articles> findById(long id);
}
