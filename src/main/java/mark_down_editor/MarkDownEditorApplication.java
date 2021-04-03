package mark_down_editor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーションを起動する。
 */
@SpringBootApplication(scanBasePackages = "mark_down_editor")
public class MarkDownEditorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkDownEditorApplication.class, args);
    }

}
