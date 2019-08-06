import Axios from "axios";

export default {
    methods: {
        getData(url, vm) {
            Axios.get(url)
                .then(response => {
                    vm.fetchedData = response.data;
                })
                .catch(error => {
                    const msg = `エラーが発生しました。\n${error}`;
                    console.log(msg);
                    window.alert(msg);
                });
        },
        postData(url, vm) {
            Axios.post(url, {
                id: vm.id,
                title: vm.title,
                contents: vm.contents
            })
                .then(response => {
                    const msg = "送信しました。";
                    console.log(msg);
                    window.alert(msg);
                    vm.$router.push("/");
                })
                .catch(error => {
                    vm.status = error.response.status;

                    const msg = `エラーが発生しました。\n${error}`;
                    console.log(msg);
                    window.alert(msg);
                });
        }
    }
}
