package mark_down_editor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class LinksController {
    private Links links;

    public LinksController() {
        this.links = new Links();
        this.links.setBrand(new Link("Mark Down Editor", "/"));
        this.links.addLinks(new Link("New Article", "/edit"));
    }

    @RequestMapping(value = "/links", method = { RequestMethod.GET })
    @ResponseBody
    public Links getLinks() {
        return links;
    }
}
