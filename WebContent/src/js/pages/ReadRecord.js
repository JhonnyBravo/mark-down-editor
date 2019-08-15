import Card from "../components/Card";
import Rest from "../mixins/Rest";

export default {
    template: `
    <div>
        <card v-for="data in fetchedData" :id="data.id" :title="data.title" :contents="data.contents" :key="data.id"></card>
    </div>
    `,
    components: {
        "card": Card
    },
    mixins: [Rest],
    data() {
        return {
            fetchedData: []
        }
    },
    methods: {
        findAll() {
            this.getData("/mark-down-editor/articles", this);
        }
    },
    created() {
        this.findAll();
    }
}
