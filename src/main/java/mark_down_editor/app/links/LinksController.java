package mark_down_editor.app.links;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mark_down_editor.domain.model.Link;
import mark_down_editor.domain.model.Links;

/**
 * リンクデータをクライアントへ送信する為の REST API コントローラ。
 */
@WebServlet("/links")
public class LinksController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * クライアントへリンクデータを送信する。
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final Links links = new Links();
    links.setBrand(new Link("Mark Down Editor", "/"));
    links.addLinks(new Link("New Article", "/edit"));

    final Gson gson = new Gson();
    String json = null;

    json = gson.toJson(links);

    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    response.getWriter().println(json);
  }
}
