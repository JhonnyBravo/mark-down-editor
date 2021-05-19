package mark_down_editor.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * リンクデータを管理する。
 */
public class Links {
  private Link brand;
  private final List<Link> links;

  /**
   * 初期化処理を実行する。
   */
  public Links() {
    this.links = new ArrayList<>();
  }

  /**
   * navbar へ渡す brand オブジェクトを返す。
   *
   * @return brand {@link Link}
   */
  public Link getBrand() {
    return brand;
  }

  /**
   * brand オブジェクトを設定する。
   *
   * @param brand brand オブジェクトとして設定する {@link Link}
   */
  public void setBrand(Link brand) {
    this.brand = brand;
  }

  /**
   * navbar へ渡すリンクデータのリストを返す。
   *
   * @return links {@link Link} の {@link List}
   */
  public List<Link> getLinks() {
    return links;
  }

  /**
   * リンクデータを登録する。
   *
   * @param link リンクデータのリストへ登録する {@link Link}
   */
  public void addLinks(Link link) {
    this.links.add(link);
  }
}
