package mark_down_editor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * リンクデータをクライアントへ送信する為の REST API コントローラ。
 */
@WebServlet("/links")
public class LinksController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinksController() {
        super();
    }

    /**
     * クライアントへリンクデータを送信する。
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Links links = new Links();
        links.setBrand(new Link("Mark Down Editor", "/"));
        links.addLinks(new Link("New Article", "/edit"));

        Gson gson = new Gson();
        String json = null;

        json = gson.toJson(links);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(json);
    }
}
