package mark_down_editor.domain.model;

/**
 * リンクデータを管理する。
 */
public class Link {
    private String title;
    private String url;

    /**
     * @param title リンクラベルとして設定する文字列を指定する。
     * @param url   URL として設定する文字列を指定する。
     */
    public Link(String title, String url) {
        this.title = title;
        this.url = url;
    }

    /**
     * @return title リンクラベルを返す。
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return url URL を返す。
     */
    public String getUrl() {
        return url;
    }
}
