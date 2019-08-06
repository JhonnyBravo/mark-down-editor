import Rest from "../mixins/Rest";

export default {
    template: `
    <div>
        <div class="alert alert-danger text-center">記事を削除します。よろしいですか?</div>
        <form>
            <input type="hidden" v-model="id">
            <button type="button" class="btn btn-danger btn-block" v-on:click="deleteById">Delete</button>
            <router-link class="btn btn-secondary btn-block" to="/">Cancel</router-link>
        </form>
    </div>
    `,
    data() {
        return {
            id: this.$route.params.id
        }
    },
    mixins: [Rest],
    methods: {
        deleteById() {
            this.postData("/articles/delete", this);
        }
    }
}
