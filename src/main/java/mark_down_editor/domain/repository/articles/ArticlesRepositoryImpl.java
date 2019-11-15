package mark_down_editor.domain.repository.articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mark_down_editor.domain.model.Articles;

public class ArticlesRepositoryImpl implements ArticlesRepository {

    @Override
    public List<Articles> findAll(Connection connection) throws Exception {
        final List<Articles> recordset = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles;");
                ResultSet resultset = statement.executeQuery();) {
            while (resultset.next()) {
                final Articles record = new Articles();

                record.setId(resultset.getLong("id"));
                record.setTitle(resultset.getString("title"));
                record.setContents(resultset.getString("contents"));

                recordset.add(record);
            }
        }

        return recordset;
    }

    @Override
    public Articles findById(Connection connection, long id) throws Exception {
        final Articles record = new Articles();
        ResultSet resultset = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles WHERE id=?;");) {
            statement.setLong(1, id);
            resultset = statement.executeQuery();

            if (resultset.next()) {
                record.setId(resultset.getLong("id"));
                record.setTitle(resultset.getString("title"));
                record.setContents(resultset.getString("contents"));
            }
        } finally {
            if (resultset != null) {
                resultset.close();
            }
        }

        return record;
    }

    @Override
    public void create(Connection connection, Articles articles) throws Exception {
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO articles (title, contents) VALUES(?, ?);")) {
            statement.setString(1, articles.getTitle());
            statement.setString(2, articles.getContents());

            statement.execute();
        }
    }

    @Override
    public void update(Connection connection, Articles articles) throws Exception {
        try (PreparedStatement statement = connection
                .prepareStatement("UPDATE articles SET title=?, contents=? WHERE id=?;")) {
            statement.setString(1, articles.getTitle());
            statement.setString(2, articles.getContents());
            statement.setLong(3, articles.getId());

            statement.execute();
        }
    }

    @Override
    public void delete(Connection connection, Articles articles) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM articles WHERE id=?;")) {
            statement.setLong(1, articles.getId());
            statement.execute();
        }
    }

    @Override
    public void deleteAll(Connection connection) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM articles;")) {
            statement.addBatch();
            statement.addBatch("ALTER TABLE articles auto_increment=1;");

            statement.executeBatch();
        }
    }

}
