package mark_down_editor.app.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.service.articles.ArticlesService;

/**
 * 記事の閲覧と登録・更新・削除を実行する。
 */
@RestController
public class ArticlesController {
    @Autowired
    private ArticlesService service;

    /**
     * @return List&lt;Articles&gt; 登録されている全記事の一覧を JSON 形式で返す。
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    @ResponseBody
    public List<Articles> findAll() {
        final List<Articles> articles = service.findAll();
        return articles;
    }

    /**
     * @param id 更新対象とする記事の id を指定する。
     * @return Optional&lt;Articles&gt; 条件に合致する記事を取得し、 JSON 形式で返す。
     */
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Articles findById(@PathVariable long id) {
        final Articles articles = service.findById(id);
        return articles;
    }

    /**
     * @param article 登録または更新の対象とする Articles エンティティを指定する。
     */
    @RequestMapping(value = "/articles/edit", method = RequestMethod.POST)
    public void edit(@RequestBody Articles article) {
        service.edit(article);
    }

    /**
     * @param article 削除対象とする Articles エンティティを指定する。
     */
    @RequestMapping(value = "/articles/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Articles article) {
        service.delete(article);
    }
}
