package mark_down_editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 記事の閲覧と登録・更新・削除を実行する。
 */
@Controller
public class ArticlesController {
    @Autowired
    private ArticlesRepository repository;
    private Link brand;
    private List<Link> links;

    public ArticlesController() {
        this.brand = new Link("Mark Down Editor", "/");
        this.links = new ArrayList<Link>();

        this.links.add(new Link("新規作成", "/create"));
    }

    /**
     * @param mav ModelAndView を注入する。
     * @return ModelAndView 登録されている全記事を一覧表示する。
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView doGetIndex(ModelAndView mav) {
        mav.setViewName("index");

        mav.addObject("brand", brand);
        mav.addObject("links", links);

        List<Articles> cards = repository.findAll();
        mav.addObject("cards", cards);

        return mav;
    }

    /**
     * @param article 登録フォームへ渡す Articles エンティティを注入する。
     * @param mav     ModelAndView を注入する。
     * @return ModelAndView 記事の登録フォームを表示する。
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView doGetCreate(@ModelAttribute Articles article, ModelAndView mav) {
        mav.setViewName("edit_record");

        mav.addObject("brand", brand);
        mav.addObject("links", links);
        mav.addObject("article", article);

        return mav;
    }

    /**
     * @param id      更新対象とする記事の id を指定する。
     * @param article 更新フォームへ渡す Articles エンティティを注入する。
     * @param mav     ModelAndView を注入する。
     * @return ModelAndView 記事の更新フォームを表示する。
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView doGetUpdate(@PathVariable long id, @ModelAttribute Articles article, ModelAndView mav) {
        mav.setViewName("edit_record");
        mav.addObject("brand", brand);
        mav.addObject("links", links);

        Optional<Articles> data = repository.findById(id);
        mav.addObject("article", data.get());

        return mav;
    }

    /**
     * @param id      削除対象とする記事の id を指定する。
     * @param article 登録フォームへ渡す Articles エンティティを注入する。
     * @param mav     ModelAndView を注入する。
     * @return ModelAndView 記事の削除フォームを表示する。
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView doGetDelete(@PathVariable long id, @ModelAttribute Articles article, ModelAndView mav) {
        mav.setViewName("delete_record");
        mav.addObject("brand", brand);
        mav.addObject("links", links);

        Optional<Articles> data = repository.findById(id);
        mav.addObject("article", data.get());

        return mav;
    }

    /**
     * @param article 登録または更新の対象とする Articles エンティティを指定する。
     * @param mav     ModelAndView を注入する。
     * @return ModelAndView 記事の一覧ページへリダイレクトする。
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView doPostEdit(@ModelAttribute Articles article, ModelAndView mav) {
        repository.saveAndFlush(article);
        return new ModelAndView("redirect:/");
    }

    /**
     * @param id  削除対象とする記事の id を指定する。
     * @param mav ModelAndView を注入する。
     * @return ModelAndView 記事の一覧ページへリダイレクトする。
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView doPostDelete(@RequestParam long id, ModelAndView mav) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }
}
