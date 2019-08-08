import Markdown from "../mixins/Markdown";

export default {
    template: `
    <div class="card">
        <h5 class="card-header">{{title}}</h5>
        <div class="card-body">
            <div class="card-text" v-html="html"></div>
            <a class="btn btn-primary" :href="'/edit/'+id">Edit</a>
            <a class="btn btn-danger" :href="'/delete/'+id">Delete</a>
        </div>
    </div>
    `,
    mixins: [Markdown],
    props: ["id", "title", "contents"],
    computed: {
        html() {
            return this.convertToHtml(this.contents);
        }
    }
}
