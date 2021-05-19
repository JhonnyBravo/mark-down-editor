package mark_down_editor.app.articles;

import java.util.List;
import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.service.articles.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 記事の閲覧と登録・更新・削除を実行する。
 */
@RestController
public class ArticlesController {
  @Autowired
  private ArticlesService service;

  /**
   * テーブルに登録されている全記事の一覧を取得する。
   *
   * @return articles {@link Articles} の {@link List}
   */
  @RequestMapping(value = "/articles", method = RequestMethod.GET)
  @ResponseBody
  public List<Articles> findAll() {
    final List<Articles> articles = service.findAll();
    return articles;
  }

  /**
   * ID をキーに記事を検索する。
   *
   * @param id 検索対象とする記事の id を指定する。
   * @return articles {@link Articles}
   */
  @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
  public Articles findById(@PathVariable long id) {
    final Articles articles = service.findById(id);
    return articles;
  }

  /**
   * 記事を新規登録または更新する。
   *
   * @param article 登録または更新の対象とする {@link Articles} を指定する。
   */
  @RequestMapping(value = "/articles/edit", method = RequestMethod.POST)
  public void edit(@RequestBody Articles article) {
    service.edit(article);
  }

  /**
   * 記事を削除する。
   *
   * @param article 削除対象とする {@link Articles} を指定する。
   */
  @RequestMapping(value = "/articles/delete", method = RequestMethod.POST)
  public void delete(@RequestBody Articles article) {
    service.delete(article);
  }
}
