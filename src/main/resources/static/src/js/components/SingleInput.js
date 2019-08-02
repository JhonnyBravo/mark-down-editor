export default {
    template: `
    <div>
        <label :for="id">{{label}}</label>
        <input type="text" class="form-control" :id="id" :placeholder="placeholder" :value="value" v-on:input="$emit('input',$event.target.value)">
    </div>
    `,
    model: {
        prop: "value",
        event: "input"
    },
    props: [
        "id",
        "label",
        "placeholder",
        "value"
    ]
}
