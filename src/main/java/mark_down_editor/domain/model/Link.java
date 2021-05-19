package mark_down_editor.domain.model;

/**
 * リンクデータを管理する。
 */
public class Link {
  private final String title;
  private final String url;

  /**
   * 初期化処理を実行する。
   *
   * @param title リンクラベルとして設定する文字列を指定する。
   * @param url URL として設定する文字列を指定する。
   */
  public Link(String title, String url) {
    this.title = title;
    this.url = url;
  }

  /**
   * リンクラベルを返す。
   *
   * @return title リンクラベル
   */
  public String getTitle() {
    return title;
  }

  /**
   * URL を返す。
   *
   * @return url URL
   */
  public String getUrl() {
    return url;
  }
}
