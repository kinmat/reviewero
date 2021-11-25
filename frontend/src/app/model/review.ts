
import { Book } from "./book";
import { User } from "./user";

export class Review {
    user: User;
    book: Book;
    rating: number;
    text: string;
    dateAdded: Date;
}
