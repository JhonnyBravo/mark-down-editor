package mark_down_editor.domain.service.articles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import mark_down_editor.domain.model.Articles;

@RunWith(Enclosed.class)
public class ArticlesServiceTest {
    public static class レコードが空である場合 {
        private ArticlesService as;

        @Before
        public void setUp() throws Exception {
            final ContentsAttribute attr = new ContentsAttribute();
            attr.setPath("src/test/resources/connection.properties");

            final ConnectionInfoService cis = new ConnectionInfoServiceImpl();
            final ConnectionInfo cnInfo = cis.getConnectionInfo(attr);
            as = new ArticlesServiceImpl(cnInfo);

            final List<Articles> recordset = as.findAll();

            if (recordset.size() > 0) {
                as.deleteAll();
            }
        }

        @After
        public void tearDown() throws Exception {
            as.deleteAll();
        }

        @Test
        public void findAll実行時に空のListを取得できること() throws Exception {
            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(0));
        }

        @Test
        public void findById実行時に空のArticlesを取得できること() throws Exception {
            final Articles record = as.findById(1);
            assertThat(record.getId(), is((long) 0));
            assertThat(record.getTitle(), is(nullValue()));
            assertThat(record.getContents(), is(nullValue()));
        }

        @Test
        public void create実行時にレコードの追加ができること() throws Exception {
            final Articles record = new Articles();
            record.setTitle("登録テスト");
            record.setContents("新規レコード");
            as.create(record);

            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getId(), is((long) 1));
            assertThat(recordset.get(0).getTitle(), is("登録テスト"));
            assertThat(recordset.get(0).getContents(), is("新規レコード"));
        }
    }

    public static class レコードが空ではない場合 {
        private ArticlesService as;

        @Before
        public void setUp() throws Exception {
            final ContentsAttribute attr = new ContentsAttribute();
            attr.setPath("src/test/resources/connection.properties");

            final ConnectionInfoService cis = new ConnectionInfoServiceImpl();
            final ConnectionInfo cnInfo = cis.getConnectionInfo(attr);
            as = new ArticlesServiceImpl(cnInfo);

            final List<Articles> recordset = as.findAll();

            if (recordset.size() > 0) {
                as.deleteAll();
            }

            {
                final Articles record = new Articles();
                record.setTitle("1 つ目のレコード");
                record.setContents("1 つ目");

                as.create(record);
            }
            {
                final Articles record = new Articles();
                record.setTitle("2 つ目のレコード");
                record.setContents("2 つ目");

                as.create(record);
            }

        }

        @After
        public void tearDown() throws Exception {
            as.deleteAll();
        }

        @Test
        public void findAll実行時に登録済みレコードを取得できること() throws Exception {
            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(2));
        }

        @Test
        public void findById実行時に指定したIDを持つレコードを取得できること() throws Exception {
            final Articles record = as.findById(1);
            assertThat(record.getId(), is((long) 1));
            assertThat(record.getTitle(), is("1 つ目のレコード"));
            assertThat(record.getContents(), is("1 つ目"));
        }

        @Test
        public void create実行時にレコードの追加ができること() throws Exception {
            final Articles record = new Articles();
            record.setTitle("登録テスト");
            record.setContents("新規レコード");
            as.create(record);

            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(3));

            assertThat(recordset.get(2).getId(), is((long) 3));
            assertThat(recordset.get(2).getTitle(), is("登録テスト"));
            assertThat(recordset.get(2).getContents(), is("新規レコード"));
        }

        @Test
        public void update実行時にレコードの更新ができること() throws Exception {
            final Articles record = new Articles();
            record.setId(1);
            record.setTitle("更新テスト");
            record.setContents("更新レコード");
            as.update(record);

            final Articles updated = as.findById(1);
            assertThat(updated.getId(), is((long) 1));
            assertThat(updated.getTitle(), is("更新テスト"));
            assertThat(updated.getContents(), is("更新レコード"));
        }

        @Test
        public void delete実行時にレコードを削除できること() throws Exception {
            final Articles record = new Articles();
            record.setId(1);
            as.delete(record);

            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(1));
        }

        @Test
        public void deleteAll実行時に全レコードを削除できること() throws Exception {
            as.deleteAll();

            final List<Articles> recordset = as.findAll();
            assertThat(recordset.size(), is(0));
        }
    }
}
