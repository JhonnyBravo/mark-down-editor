package mark_down_editor;

import java.util.ArrayList;
import java.util.List;

/**
 * リンクデータを管理する。
 */
public class Links {
    private Link brand;
    private List<Link> links;

    public Links() {
        this.links = new ArrayList<Link>();
    }

    /**
     * @return brand navbar へ渡す brand オブジェクトを返す。
     */
    public Link getBrand() {
        return brand;
    }

    /**
     * @param brand brand オブジェクトとして設定する Link オブジェクトを指定する。
     */
    public void setBrand(Link brand) {
        this.brand = brand;
    }

    /**
     * @return links navbar へ渡すリンクデータのリストを返す。
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param link リンクデータのリストへ登録する Link オブジェクトを指定する。
     */
    public void addLinks(Link link) {
        this.links.add(link);
    }
}
