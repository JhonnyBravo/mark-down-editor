package mark_down_editor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import property_resource.PropertyResource;

/**
 * 記事の管理を行う為の REST API コントローラ。
 */
@WebServlet("/articles")
public class ArticlesController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DBController dbc;
    private final Gson gson;

    /**
     * @throws Exception {@link java.lang.Exception}
     * @see HttpServlet#HttpServlet()
     */
    public ArticlesController() throws Exception {
        super();
        final PropertyResource pr = new PropertyResource("WebContent/WEB-INF/classes/connection.properties");
        final Map<String, String> properties = pr.getContent();

        dbc = new DBController(properties);
        gson = new Gson();
    }

    /**
     * DB に登録されている記事を JSON 形式でクライアントへ送信する。
     *
     * @param request  REST クライアントから送信された Get パラメータ
     * @param response REST クライアントへ返す JSON データ
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.Exception}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = null;

        try {
            if (request.getParameterMap().containsKey("id")) {
                final long id = Long.parseLong(request.getParameter("id"));
                final Articles articles = dbc.findById(id);
                json = gson.toJson(articles);
            } else {
                final List<Articles> articles = dbc.findAll();
                json = gson.toJson(articles);
            }
        } catch (final Exception e) {
            throw new IOException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(json);
    }

    /**
     * 記事を DB へ新規登録する。
     * 
     * @param request  REST クライアントから送信された JSON データ
     * @param response REST クライアントへ返すステータス(未実装)
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.Exception}
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
        final Articles articles = gson.fromJson(json, Articles.class);

        try {
            dbc.create(articles);
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * DB に登録されている既存記事を更新する。
     *
     * @param request  REST クライアントから送信された JSON データ
     * @param response REST クライアントへ返すステータス(未実装)
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.Exception}
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
        final Articles articles = gson.fromJson(json, Articles.class);

        try {
            dbc.update(articles);
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * DB に登録されている記事を削除する。
     * 
     * @param request  REST クライアントから送信された JSON データ
     * @param response REST クライアントへ返すステータス(未実装)
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.Exception}
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Articles articles = new Articles();

        final long id = Long.parseLong(request.getParameter("id"));
        articles.setId(id);

        try {
            dbc.delete(articles);
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }
}
