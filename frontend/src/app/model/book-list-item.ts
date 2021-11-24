import { User } from 'src/app/model/user';
import { Book } from "./book";

export class BookListItem {
    user: User;
    book: Book;
    state: any;
    dateAdded: Date;

    constructor(user, book, state, date) {
        this.user = user;
        this.book = book;
        this.state = state;
        this.dateAdded = date;
    }
}
