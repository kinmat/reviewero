import { BookListItem } from './book-list-item';
import { Friendship } from './friendship';
export class User {
    id: number;
    username: string;
    password: string;
    email: string;
    addedBookList: BookListItem[];
    requester: Friendship[];
    requestee: Friendship[];

}
