package mark_down_editor.domain.model;

/**
 * 記事を永続化する為のエンティティ。
 */
public class Articles {
  private long id;
  private String title;
  private String contents;

  /**
   * 記事の ID を設定する。
   *
   * @param id ID として登録する数値を指定する。
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * 記事の ID を返す。
   *
   * @return id 記事の ID
   */
  public long getId() {
    return id;
  }

  /**
   * 記事のタイトルを設定する。
   *
   * @param title 記事のタイトルとして登録する文字列を指定する。
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 記事のタイトルを返す。
   *
   * @return 記事のタイトル
   */
  public String getTitle() {
    return title;
  }

  /**
   * 記事の本文を設定する。
   *
   * @param contents 記事の本文として登録する文字列を指定する。
   */
  public void setContents(String contents) {
    this.contents = contents;
  }

  /**
   * 記事の本文を返す。
   *
   * @return contents 記事の本文
   */
  public String getContents() {
    return contents;
  }
}
