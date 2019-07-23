import Article from "./article";
import Marked from "marked";

export default class ArticleController extends Article {
    constructor() {
        super();
    }

    public isEmpty(value: string): boolean {
        let result: boolean = false;

        if (value === "" || value === null) {
            result = true;
        }

        return result;
    }

    public getMarkdown(): string {
        let markdown: string = "";
        const title: string = this.getTitle();
        const contents: string = this.getContents();

        if (!this.isEmpty(title) && !this.isEmpty(contents)) {
            markdown = `# ${title}\n${contents}`;
        }

        return markdown;
    }

    public converToHtml(markdown: string): string {
        let result: string = "";

        if (!this.isEmpty(markdown)) {
            const renderer = new Marked.Renderer();

            renderer.table = (header: string, body: string) => {
                const table: string = `
                <table class="table table-bordered table-hover">
                    <thead class="thead-dark">${header}</thead>
                    <tbody>${body}</tbody>
                </table>
                `;

                return table;
            };

            result = Marked(markdown, { sanitize: true, renderer });
        }

        return result;
    }

    public setHtml(html: string): void {
        const output: HTMLDivElement = document.getElementById("html-output") as HTMLDivElement;
        output.innerHTML = html;
    }

    public clear(): void {
        this.setTitle("");
        this.setContents("");
        this.setHtml("");
    }
}
