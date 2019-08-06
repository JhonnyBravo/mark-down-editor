import Axios from "axios";

export default {
    template: `
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <router-link class="navbar-brand" :to="brand.url">{{brand.title}}</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-default" aria-controls="navbar-default" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar-default">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item" v-for="link in links">
                    <router-link class="nav-link" :to="link.url">{{link.title}}</router-link>
                </li>
            </ul>
        </div>
    </nav>
    `,
    data() {
        return {
            brand: { url: "", title: "" },
            links: []
        }
    },
    methods: {
        getLinks() {
            Axios.get("/links")
                .then(response => {
                    const data = response.data;
                    this.brand = data.brand;
                    this.links = data.links;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    created() {
        this.getLinks();
    }
}
