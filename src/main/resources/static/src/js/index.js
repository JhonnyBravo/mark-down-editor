import Vue from "vue";
import VueRouter from "vue-router";
import Navbar from "./components/Navbar";
import ReadRecord from "./pages/ReadRecord";
import EditRecord from "./pages/EditRecord";
import DeleteRecord from "./pages/DeleteRecord";

Vue.use(VueRouter);

const routes = [
    { path: "/", component: ReadRecord },
    { path: "/edit", component: EditRecord },
    { path: "/edit/:id", component: EditRecord },
    { path: "/delete/:id", component: DeleteRecord }
];

const router = new VueRouter({
    routes
});

const app = new Vue({
    router,
    components: {
        Navbar
    }
}).$mount("#app");
