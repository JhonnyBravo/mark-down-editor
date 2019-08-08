import Vue from "vue";
import SingleInput from "../components/SingleInput";
import MultipleInput from "../components/MultipleInput";
import Markdown from "../mixins/Markdown";

new Vue({
    el: "#app",
    template: `
    <div class="row">
        <div class="col-6">
            <form action="/edit" method="post">
                <single-input id="title" label="Title" v-model="title"></single-input>
                <multiple-input id="contents" label="Contents" rows="10" v-model="contents"></multiple-input>
                <input type="hidden" name="id" v-model="id">
                <button type="submit" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-primary" v-on:click="clear">Clear</button>
            </form>
        </div>
        <div class="col-6" v-html="html"></div>
    </div>
    `,
    components: {
        "single-input": SingleInput,
        "multiple-input": MultipleInput
    },
    mixins: [
        Markdown
    ],
    data() {
        return {
            id: null,
            title: null,
            contents: null
        }
    },
    methods: {
        clear() {
            this.title = null;
            this.contents = null;
        }
    },
    computed: {
        markdown() {
            let result = null;

            if (!this.isEmpty(this.title) && !this.isEmpty(this.contents)) {
                result = `# ${this.title}\n${this.contents}`;
            }

            return result;
        },
        html() {
            return this.convertToHtml(this.markdown);
        }
    },
    created() {
        this.id = document.getElementById("data-id").value;
        this.title = document.getElementById("data-title").value;
        this.contents = document.getElementById("data-contents").value;
    }
});
