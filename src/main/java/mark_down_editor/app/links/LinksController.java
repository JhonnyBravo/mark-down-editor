package mark_down_editor.app.links;

import mark_down_editor.domain.model.Link;
import mark_down_editor.domain.model.Links;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * navbar へ渡すリンクデータの一覧を管理する。
 */
@RestController
public class LinksController {
  private final Links links;

  /**
   * 初期化処理を実行する。
   */
  public LinksController() {
    this.links = new Links();
    this.links.setBrand(new Link("Mark Down Editor", "/"));
    this.links.addLinks(new Link("New Article", "/edit"));
  }

  /**
   * リンクデータの一覧を返す。
   *
   * @return links {@link Links}
   */
  @RequestMapping(value = "/links", method = {RequestMethod.GET})
  @ResponseBody
  public Links getLinks() {
    return links;
  }
}
