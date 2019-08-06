package mark_down_editor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private ArticlesRepository repository;

    /**
     * @return List&lt;Articles&gt; 登録されている全記事の一覧を JSON 形式で返す。
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    @ResponseBody
    public List<Articles> findAll() {
        List<Articles> articles = repository.findAll();
        return articles;
    }

    /**
     * @param id 更新対象とする記事の id を指定する。
     * @return Optional&lt;Articles&gt; 条件に合致する記事を取得し、 JSON 形式で返す。
     */
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Optional<Articles> findById(@PathVariable long id) {
        Optional<Articles> articles = repository.findById(id);
        return articles;
    }

    /**
     * @param article 登録または更新の対象とする Articles エンティティを指定する。
     */
    @RequestMapping(value = "/articles/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void edit(@RequestBody Articles article) {
        repository.saveAndFlush(article);
    }

    /**
     * @param article 削除対象とする Articles エンティティを指定する。
     */
    @RequestMapping(value = "/articles/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void delete(@RequestBody Articles article) {
        repository.delete(article);
    }
}
