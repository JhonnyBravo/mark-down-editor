package mark_down_editor.app.articles;

import java.util.ArrayList;
import java.util.List;
import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.model.Link;
import mark_down_editor.domain.service.articles.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
  private ArticlesService service;
  private final Link brand;
  private final List<Link> links;

  /**
   * 初期化処理を実行する。
   */
  public ArticlesController() {
    this.brand = new Link("Mark Down Editor", "/");
    this.links = new ArrayList<>();

    this.links.add(new Link("New Article", "/create"));
  }

  /**
   * 登録されている全記事を一覧表示する。
   *
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} index.html
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView doGetIndex(ModelAndView mav) {
    mav.setViewName("index");

    mav.addObject("brand", brand);
    mav.addObject("links", links);

    final List<Articles> cards = service.findAll();
    mav.addObject("cards", cards);

    return mav;
  }

  /**
   * 記事の登録ページを表示する。
   *
   * @param article 登録フォームへ渡す {@link Articles}
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} edit_record.html
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
   * ID をキーに記事を検索し、更新ページへ表示する。
   *
   * @param id 更新対象とする記事の id を指定する。
   * @param article 更新フォームへ渡す {@link Articles}
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} edit_record.html
   */
  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView doGetUpdate(@PathVariable long id, @ModelAttribute Articles article,
      ModelAndView mav) {
    mav.setViewName("edit_record");
    mav.addObject("brand", brand);
    mav.addObject("links", links);

    final Articles data = service.findById(id);
    mav.addObject("article", data);

    return mav;
  }

  /**
   * ID をキーに記事を検索し、削除ページへ送信する。
   *
   * @param id 削除対象とする記事の id を指定する。
   * @param article 削除フォームへ渡す {@link Articles}
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} delete_record.html
   */
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ModelAndView doGetDelete(@PathVariable long id, @ModelAttribute Articles article,
      ModelAndView mav) {
    mav.setViewName("delete_record");
    mav.addObject("brand", brand);
    mav.addObject("links", links);

    final Articles data = service.findById(id);
    mav.addObject("article", data);

    return mav;
  }

  /**
   * 記事の登録または更新を実行する。
   *
   * @param article 登録または更新の対象とする {@link Articles}
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} index.html
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public ModelAndView doPostEdit(@ModelAttribute Articles article, ModelAndView mav) {
    service.edit(article);
    return new ModelAndView("redirect:/");
  }

  /**
   * 記事の削除を実行する。
   *
   * @param id 削除対象とする記事の id を指定する。
   * @param mav {@link ModelAndView}
   * @return page {@link ModelAndView} index.html
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ModelAndView doPostDelete(@RequestParam long id, ModelAndView mav) {
    final Articles articles = service.findById(id);
    service.delete(articles);
    return new ModelAndView("redirect:/");
  }
}
