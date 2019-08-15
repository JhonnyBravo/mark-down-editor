import SingleInput from "../components/SingleInput";
import MultipleInput from "../components/MultipleInput";
import Markdown from "../mixins/Markdown";
import Rest from "../mixins/Rest";

export default {
    template: `
    <div class="row">
        <div class="col-6">
            <form>
                <single-input id="title" label="Title" v-model="title"></single-input>
                <multiple-input id="contents" label="Contents" rows="10" v-model="contents"></multiple-input>
                <input type="hidden" v-model="id">
                <button type="button" class="btn btn-primary" v-on:click="save">Save</button>
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
    mixins: [Markdown, Rest],
    data() {
        return {
            id: null,
            title: null,
            contents: null,
            fetchedData: null
        }
    },
    methods: {
        clear() {
            this.id = 0;
            this.title = null;
            this.contents = null;
        },
        findById(id) {
            this.getData(`/mark-down-editor/articles?id=${id}`, this);
        },
        save() {
            if (this.$route.params.id) {
                this.putData("/mark-down-editor/articles", this);
            } else {
                this.postData("/mark-down-editor/articles", this);
            }
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
    watch: {
        "$route": "clear",
        fetchedData() {
            this.id = this.fetchedData.id;
            this.title = this.fetchedData.title;
            this.contents = this.fetchedData.contents;
        }
    },
    created() {
        if (this.$route.params.id) {
            this.findById(this.$route.params.id);
        } else {
            this.id = 0;
        }
    }
}
