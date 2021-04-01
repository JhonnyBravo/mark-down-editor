package mark_down_editor.domain.repository.articles;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mark_down_editor.domain.model.Articles;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticlesRepositoryTest {
    @Autowired
    private ArticlesRepository repository;
    private Articles record1;
    private Articles record2;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        record1 = new Articles();
        record1.setTitle("記事1");
        record1.setContents("1つ目の記事");

        record2 = new Articles();
        record2.setTitle("記事2");
        record2.setContents("2つ目の記事");
    }

    // テーブル上にレコードが存在しない場合
    @Test
    public void testFindAllレコードが存在しない場合() {
        final List<Articles> recordset = repository.findAll();
        assertThat(recordset.size(), is(0));
    }

    @Test
    public void testFindByIdレコードが存在しない場合() {
        final Optional<Articles> record = repository.findById(1);
        assertThat(record.isPresent(), is(false));
    }

    @Test
    public void testSaveAndFlushレコードを新規登録する場合() {
        final Articles result = repository.saveAndFlush(record1);
        assertThat(result.getTitle(), is(record1.getTitle()));
        assertThat(result.getContents(), is(record1.getContents()));
    }

    // テーブル上にレコードが存在する場合
    @Test
    public void testFindAllレコードが存在する場合() {
        repository.saveAndFlush(record1);
        repository.saveAndFlush(record2);

        final List<Articles> recordset = repository.findAll();
        assertThat(recordset.size(), is(2));
    }

    @Test
    public void testFindByIdレコードが存在する場合() {
        final Articles result = repository.saveAndFlush(record1);

        final Optional<Articles> record = repository.findById(result.getId());
        assertThat(record.get().getId(), is(result.getId()));
        assertThat(record.get().getTitle(), is(result.getTitle()));
        assertThat(record.get().getContents(), is(result.getContents()));
    }

    @Test
    public void testSaveAndFlushレコードを追加する場合() {
        repository.saveAndFlush(record1);
        final Articles result = repository.saveAndFlush(record2);

        final Optional<Articles> record = repository.findById(result.getId());

        assertThat(record.get().getId(), is(result.getId()));
        assertThat(record.get().getTitle(), is(result.getTitle()));
        assertThat(record.get().getContents(), is(result.getContents()));
    }

    @Test
    public void testSaveAndFlushレコードを更新する場合() {
        final Articles original = repository.saveAndFlush(record1);
        original.setTitle("タイトルの更新");
        original.setContents("内容の更新");

        final Articles updated = repository.saveAndFlush(original);
        assertThat(updated.getId(), is(original.getId()));
        assertThat(updated.getTitle(), is(original.getTitle()));
        assertThat(updated.getContents(), is(original.getContents()));
    }

    @Test
    public void testDelete() {
        final Articles result = repository.saveAndFlush(record1);
        repository.delete(result);

        final Optional<Articles> record = repository.findById(result.getId());
        assertThat(record.isPresent(), is(false));
    }

}
