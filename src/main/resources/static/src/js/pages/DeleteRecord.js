import Axios from "axios";

export default {
    template: `
    <div>
        <div class="alert alert-danger text-center">記事を削除します。よろしいですか?</div>
        <form>
            <input type="hidden" v-model="id">
            <button type="button" class="btn btn-danger btn-block" v-on:click="deleteRecord">Delete</button>
            <router-link class="btn btn-secondary btn-block" to="/">Cancel</router-link>
        </form>
    </div>
    `,
    data() {
        return {
            id: this.$route.params.id
        }
    },
    methods: {
        deleteRecord() {
            Axios.post("/card/delete", {
                id: this.id,
                title: "",
                contents: ""
            })
                .then(response => {
                    console.log("削除しました。");
                    window.alert("削除しました。");
                    this.$router.push("/");
                })
                .catch(error => {
                    console.log(error);
                    window.alert(`エラーが発生しました。\n${error}`);
                });
        }
    }
}
