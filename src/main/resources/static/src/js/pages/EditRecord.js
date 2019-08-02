import Axios from "axios";
import SingleInput from "../components/SingleInput";
import MultipleInput from "../components/MultipleInput";
import Markdown from "../mixins/Markdown";

export default {
    template: `
    <div class="row">
        <div class="col-6">
            <form>
                <single-input id="title" label="Title" v-model="title"></single-input>
                <multiple-input id="contents" label="Contents" rows="10" v-model="contents"></multiple-input>
                <input type="hidden" v-model="id">
                <button type="button" class="btn btn-primary" v-on:click="postCard">Save</button>
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
    mixins: [Markdown],
    data() {
        return {
            id: null,
            title: null,
            contents: null
        }
    },
    methods: {
        clear() {
            this.id = 0;
            this.title = null;
            this.contents = null;
        },
        getCard(id) {
            Axios.get(`/card/view/${id}`)
                .then(response => {
                    this.id = response.data.id;
                    this.title = response.data.title;
                    this.contents = response.data.contents;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        postCard() {
            Axios.post("/card/edit", {
                id: this.id,
                title: this.title,
                contents: this.contents
            })
                .then(response => {
                    console.log("登録しました。");
                    window.alert("登録しました。");
                    this.$router.push("/");
                })
                .catch(error => {
                    console.log(error);
                    window.alert(`エラーが発生しました。\n${error}`);
                });
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
        "$route": "clear"
    },
    created() {
        if (this.$route.params.id) {
            this.getCard(this.$route.params.id);
        } else {
            this.id = 0;
        }
    }
}
