package mark_down_editor.domain.service.articles;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java_itamae_connection.domain.model.ConnectionInfo;
import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.repository.articles.ArticlesRepository;
import mark_down_editor.domain.repository.articles.ArticlesRepositoryImpl;

public class ArticlesServiceImpl extends ArticlesService {
    private final ConnectionInfo cnInfo;
    private final ArticlesRepository repository;

    /**
     * @param cnInfo DB の接続情報情報を納めた ConnectionInfo を指定する。
     */
    public ArticlesServiceImpl(ConnectionInfo cnInfo) {
        this.cnInfo = cnInfo;
        repository = new ArticlesRepositoryImpl();
    }

    @Override
    public List<Articles> findAll() throws Exception {
        List<Articles> recordset = new ArrayList<>();

        try (Connection connection = getConnection(cnInfo)) {
            recordset = repository.findAll(connection);
        }

        return recordset;
    }

    @Override
    public Articles findById(long id) throws Exception {
        Articles record = new Articles();

        try (Connection connection = getConnection(cnInfo)) {
            record = repository.findById(connection, id);
        }

        return record;
    }

    @Override
    public void create(Articles articles) throws Exception {
        final Connection connection = getConnection(cnInfo);

        try {
            connection.setAutoCommit(false);
            repository.create(connection, articles);
            connection.commit();
        } catch (final Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(Articles articles) throws Exception {
        final Connection connection = getConnection(cnInfo);

        try {
            connection.setAutoCommit(false);
            repository.update(connection, articles);
            connection.commit();
        } catch (final Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(Articles articles) throws Exception {
        final Connection connection = getConnection(cnInfo);

        try {
            connection.setAutoCommit(false);
            repository.delete(connection, articles);
            connection.commit();
        } catch (final Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        final Connection connection = getConnection(cnInfo);

        try {
            connection.setAutoCommit(false);
            repository.deleteAll(connection);
            connection.commit();
        } catch (final Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

}
