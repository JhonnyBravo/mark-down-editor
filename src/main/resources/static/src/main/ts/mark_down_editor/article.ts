export default class Article {
    private title: HTMLInputElement;
    private contents: HTMLInputElement;

    constructor() {
        this.title = document.getElementById("title") as HTMLInputElement;
        this.contents = document.getElementById("contents") as HTMLInputElement;
    }

    public getTitle(): string {
        return this.title.value;
    }

    public setTitle(title: string): void {
        this.title.value = title;
    }

    public getContents(): string {
        return this.contents.value;
    }

    public setContents(contents: string): void {
        this.contents.value = contents;
    }
}
