package mark_down_editor.domain.service.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.repository.articles.ArticlesRepository;

@Service
@Transactional
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticlesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Articles> findAll() {
        final List<Articles> result = repository.findAll();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Articles findById(long id) {
        final Articles result = repository.findById(id).orElse(new Articles());
        return result;
    }

    @Override
    public Articles edit(Articles articles) {
        final Articles result = repository.saveAndFlush(articles);
        return result;
    }

    @Override
    public void delete(Articles articles) {
        repository.delete(articles);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
