import ArticleConrtoller from "../mark_down_editor/article_controller";

window.addEventListener("load", function () {
    const ac = new ArticleConrtoller();
    const markdown: string = ac.getMarkdown();
    ac.setHtml(ac.converToHtml(markdown));

    const form = document.forms[0];

    form.addEventListener("input", () => {
        const ac = new ArticleConrtoller();
        const markdown: string = ac.getMarkdown();
        ac.setHtml(ac.converToHtml(markdown));
    });

    const btnClear: HTMLButtonElement = document.getElementById("clear") as HTMLButtonElement;

    btnClear.addEventListener("click", () => {
        const ac = new ArticleConrtoller();
        ac.clear();
    });
});
