package mark_down_editor.domain.service.articles;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mark_down_editor.domain.model.Articles;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticlesServiceTest {
    @Autowired
    private ArticlesService service;
    private Articles record1;
    private Articles record2;

    @Before
    public void setUp() throws Exception {
        service.deleteAll();

        record1 = new Articles();
        record1.setTitle("記事1");
        record1.setContents("1つ目の記事");

        record2 = new Articles();
        record2.setTitle("記事2");
        record2.setContents("2つ目の記事");
    }

    @After
    public void tearDown() throws Exception {
        service.deleteAll();
    }

    // テーブル上にレコードが存在しない場合
    @Test
    public void testFindAllレコードが存在しない場合() {
        final List<Articles> recordset = service.findAll();
        assertThat(recordset.size(), is(0));
    }

    @Test
    public void testFindByIdレコードが存在しない場合() {
        final Articles record = service.findById(1);
        assertThat(record.getId(), is(0L));
    }

    @Test
    public void testEditレコードを新規登録する場合() {
        final Articles result = service.edit(record1);
        assertThat(result.getTitle(), is(record1.getTitle()));
        assertThat(result.getContents(), is(record1.getContents()));
    }

    // テーブル上にレコードが存在する場合
    @Test
    public void testFindAllレコードが存在する場合() {
        service.edit(record1);
        service.edit(record2);

        final List<Articles> recordset = service.findAll();
        assertThat(recordset.size(), is(2));
    }

    @Test
    public void testFindByIdレコードが存在する場合() {
        final Articles result = service.edit(record1);
        final Articles record = service.findById(result.getId());

        assertThat(record.getId(), is(result.getId()));
        assertThat(record.getTitle(), is(result.getTitle()));
        assertThat(record.getContents(), is(result.getContents()));
    }

    @Test
    public void testEditレコードを追加する場合() {
        service.edit(record1);
        final Articles result = service.edit(record2);
        final Articles record = service.findById(result.getId());

        assertThat(record.getId(), is(result.getId()));
        assertThat(record.getTitle(), is(result.getTitle()));
        assertThat(record.getContents(), is(result.getContents()));
    }

    @Test
    public void testEditレコードを更新する場合() {
        final Articles original = service.edit(record1);
        original.setTitle("タイトルの更新");
        original.setContents("内容の更新");

        final Articles updated = service.edit(original);
        assertThat(updated.getId(), is(original.getId()));
        assertThat(updated.getTitle(), is(original.getTitle()));
        assertThat(updated.getContents(), is(original.getContents()));
    }

    @Test
    public void testDelete() {
        final Articles result = service.edit(record1);
        service.delete(result);

        final Articles record = service.findById(result.getId());
        assertThat(record.getId(), is(0L));
    }

}
