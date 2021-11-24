export class ChatMessage {
    id: number;
    sender: string;
    reciever: string;
    content: string;
    dateSent: Date;
    
    constructor(sender, reciever, content, dateSent) {
        this.sender = sender;
        this.reciever = reciever;
        this.content = content;
        this.dateSent = dateSent;
    }
}