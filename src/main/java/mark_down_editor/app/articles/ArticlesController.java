package mark_down_editor.app.articles;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.service.articles.ArticlesService;

/**
 * 記事の管理を行う為の REST API コントローラ。
 */
@WebServlet("/articles")
public class ArticlesController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ArticlesHelper helper;
  private ArticlesService service;
  private Gson gson;

  @Override
  public void init() {
    helper = new ArticlesHelper();

    try {
      service = helper.getArticlesService("connection.properties");
    } catch (final Exception e) {
      e.printStackTrace();
    }

    gson = new Gson();
  }

  /**
   * DB に登録されている記事を JSON 形式でクライアントへ送信する。
   *
   * @param request REST クライアントから送信された Get パラメータ
   * @param response REST クライアントへ返す JSON データ
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String json = null;

    try {
      if (request.getParameterMap().containsKey("id")) {
        final long id = Long.parseLong(request.getParameter("id"));
        final Articles articles = service.findById(id);
        json = gson.toJson(articles);
      } else {
        final List<Articles> articles = service.findAll();
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
   * @param request REST クライアントから送信された JSON データ
   * @param response REST クライアントへ返すステータス(未実装)
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
    final Articles articles = gson.fromJson(json, Articles.class);

    try {
      service.create(articles);
    } catch (final Exception e) {
      throw new IOException(e);
    }
  }

  /**
   * DB に登録されている既存記事を更新する。
   *
   * @param request REST クライアントから送信された JSON データ
   * @param response REST クライアントへ返すステータス(未実装)
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   */
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final String json = request.getReader().lines().collect(Collectors.joining("\r\n"));
    final Articles articles = gson.fromJson(json, Articles.class);

    try {
      service.update(articles);
    } catch (final Exception e) {
      throw new IOException(e);
    }
  }

  /**
   * DB に登録されている記事を削除する。
   *
   * @param request REST クライアントから送信された JSON データ
   * @param response REST クライアントへ返すステータス(未実装)
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   */
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final Articles articles = new Articles();

    final long id = Long.parseLong(request.getParameter("id"));
    articles.setId(id);

    try {
      service.delete(articles);
    } catch (final Exception e) {
      throw new IOException(e);
    }
  }
}
