import Marked from "marked";

export default {
    methods: {
        isEmpty(value) {
            let result = false;

            if (value === "" || value === null) {
                result = true;
            }

            return result;
        },
        convertToHtml(markdown) {
            let result = null;

            if (!this.isEmpty(markdown)) {
                const renderer = new Marked.Renderer();

                renderer.table = (header, body) => {
                    const table = `
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">${header}</thead>
                        <tbody>${body}</tbody>
                    </table>
                    `;

                    return table;
                }

                result = Marked(markdown, { sanitize: true, renderer });
            }

            return result;
        }
    }
}
