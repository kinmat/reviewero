import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs';
import { ChatMessage } from 'src/app/model/chat-message';
import { WebSocketService } from 'src/app/services/web-socket.service';
import { Message } from 'stompjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent  {
  input;
  otherUserId: number;
  constructor(public messageService: WebSocketService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.otherUserId = +params['id']; // (+) converts string 'id' to a number
    });
  }
  sendMessage() {
    if (this.input) {
      this.messageService.sendMessage(this.input, this.otherUserId);
      this.input = '';
    }
  }
 /* input: string = '';

  constructor(public webSocketService: WebSocketService, private rxStompService: RxStompService) { }

 /* sendMessage() {
    if (this.input) {
      this.webSocketService.sendMessage(this.input);
      this.input = '';
    }
  }
  */

 /* public receivedMessages: string[] = [];
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) {}

  ngOnInit() {
    this.topicSubscription = this.rxStompService
      .watch('/app/message')
      .subscribe((message: Message) => {
        this.receivedMessages.push(message.body);
      });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage() {
    const message = `Message generated at ${new Date()}`;
    this.rxStompService.publish({ destination: '/app/send/message', body: message });
  }
  */
}


