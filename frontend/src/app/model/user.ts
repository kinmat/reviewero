import { Friendship } from './friendship';
export class User {
    id: number;
    username: string;
    password: string;
    email: string;
    requester: Friendship[];
    requestee: Friendship[];
}
