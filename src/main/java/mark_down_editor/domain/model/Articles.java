package mark_down_editor.domain.model;

/**
 * 記事を永続化する為のエンティティ。
 */
public class Articles {
    private long id;
    private String title;
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
