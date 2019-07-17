package mark_down_editor;

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
     * @param id id として登録する数値を指定する。
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return id 記事の id を返す。
     */
    public long getId() {
        return id;
    }

    /**
     * @param title 記事のタイトルとして登録する文字列を指定する。
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 記事のタイトルを返す。
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param contents 記事の本文として登録する文字列を指定する。
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * @return contents 記事の本文を返す。
     */
    public String getContents() {
        return contents;
    }
}
