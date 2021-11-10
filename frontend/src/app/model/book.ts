import { Author } from "./author";

export class Book {
    id: number;
    title: string;
    synopsis: string;
    publisher_id: number;
    publication_date: Date;
    pages: number;
    authors: Author[];
}
