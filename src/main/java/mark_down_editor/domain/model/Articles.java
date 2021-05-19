package mark_down_editor.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 記事を永続化する為のエンティティ。
 */
@Entity
@Table(name = "articles")
public class Articles {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private long id;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 400, nullable = false)
  private String contents;

  /**
   * レコード ID を設定する。
   *
   * @param id id として登録する数値を指定する。
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * レコード ID を返す。
   *
   * @return id レコード ID
   */
  public long getId() {
    return id;
  }

  /**
   * 記事のタイトルを設定する。
   *
   * @param title 記事のタイトルとして登録する文字列
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
   * @param contents 記事の本文として登録する文字列
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
