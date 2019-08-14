package mark_down_editor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 記事の管理を行う為の REST API コントローラ。
 */
@WebServlet("/articles")
public class ArticlesController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBController dbc = null;
    private Gson gson = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticlesController() {
        super();
        dbc = new DBController("WebContent/WEB-INF/classes/connection.properties");
        gson = new Gson();
    }

    /**
     * DB に登録されている記事を JSON 形式でクライアントへ送信する。
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = null;

        if (request.getParameterMap().containsKey("id")) {
            long id = Long.parseLong(request.getParameter("id"));
            Articles articles = dbc.findById(id);
            json = gson.toJson(articles);
        } else {
            List<Articles> articles = dbc.findAll();
            json = gson.toJson(articles);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(json);
    }

    /**
     * 記事を DB へ新規登録する。
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
        Articles articles = gson.fromJson(json, Articles.class);
        dbc.create(articles);
    }

    /**
     * DB に登録されている既存記事を更新する。
     * 
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
        Articles articles = gson.fromJson(json, Articles.class);
        dbc.updateById(articles);
    }

    /**
     * DB に登録されている記事を削除する。
     * 
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        dbc.deleteById(id);
    }
}
