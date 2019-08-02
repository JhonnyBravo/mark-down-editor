package mark_down_editor;

import java.util.ArrayList;
import java.util.List;

public class Links {
    private Link brand;
    private List<Link> links;

    public Links() {
        this.links = new ArrayList<Link>();
    }

    public Link getBrand() {
        return brand;
    }

    public void setBrand(Link brand) {
        this.brand = brand;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLinks(Link link) {
        this.links.add(link);
    }
}
