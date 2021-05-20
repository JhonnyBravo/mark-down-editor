package mark_down_editor.domain.service.articles;

import java.util.List;
import mark_down_editor.domain.model.Articles;
import mark_down_editor.domain.repository.articles.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticlesServiceImpl implements ArticlesService {
  @Autowired
  private ArticlesRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<Articles> findAll() {
    final List<Articles> recordset = repository.findAll();
    return recordset;
  }

  @Override
  @Transactional(readOnly = true)
  public Articles findById(long id) {
    final Articles record = repository.findById(id).orElse(new Articles());
    return record;
  }

  @Override
  public Articles edit(Articles articles) {
    final Articles record = repository.saveAndFlush(articles);
    return record;
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
