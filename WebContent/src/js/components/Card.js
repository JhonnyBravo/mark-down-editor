import Markdown from "../mixins/Markdown";

export default {
    template: `
    <div class="card">
        <h5 class="card-header">{{title}}</h5>
        <div class="card-body">
            <div class="card-text" v-html="html"></div>
            <router-link class="btn btn-primary" :to="'/edit/'+id">Edit</router-link>
            <router-link class="btn btn-danger" :to="'/delete/'+id">Delete</router-link>
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
