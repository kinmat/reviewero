import { User } from "./user";

export class Friendship {
    requester: User;
    requestee: User;
    accepted: boolean;
}
