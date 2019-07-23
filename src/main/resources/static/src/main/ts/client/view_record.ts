import ArticleController from "../mark_down_editor/article_controller";

window.addEventListener("load", () => {
    const ac = new ArticleController();
    const articles = document.querySelectorAll("div.card-text");

    for (const article of articles) {
        const markdown = article.textContent;
        const html = ac.converToHtml(markdown);
        article.innerHTML = html;
    }
});
