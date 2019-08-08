export default {
    template: `
    <div>
        <label :for="id">{{label}}</label>
        <textarea class="form-control" :id="id" :name="id" :placeholder="placeholder" :value="value" v-on:input="$emit('input',$event.target.value)" :rows="rows"></textarea>
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
        "value",
        "rows"
    ]
}
