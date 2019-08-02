import Axios from "axios";
import Card from "../components/Card";

export default {
    template: `
    <div>
        <card v-for="card in cards" :id="card.id" :title="card.title" :contents="card.contents" :key="card.id"></card>
    </div>
    `,
    components: {
        "card": Card
    },
    data() {
        return {
            cards: []
        }
    },
    methods: {
        getCards() {
            Axios.get("/card/view")
                .then(response => {
                    this.cards = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    created() {
        this.getCards();
    }
}
