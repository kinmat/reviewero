import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs';
import { ChatMessage } from 'src/app/model/chat-message';
import { WebSocketService } from 'src/app/services/web-socket.service';
import { Message } from 'stompjs';
import { User } from 'src/app/model/user';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit  {
  input;
  loggedInUser: User;
  otherUser: User;
  msgs: ChatMessage[]= []
  
  constructor(public websocketService: WebSocketService, private route: ActivatedRoute,
    private authService: AuthService) {
    this.msgs = [];
    this.subscribeToIncomingMessages();
    this.otherUser = new User();
    this.loggedInUser = new User();

  }

  subscribeToIncomingMessages() {
    const that = this;
    this.websocketService.stompClient.connect({}, function(frame) {
      that.websocketService.stompClient.subscribe("/user/" + that.loggedInUser.id + "/queue/messages", (message) => {
        that.msgs=[(JSON.parse(message.body)), ...that.msgs];
      });
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      let otherUserId = +params['id']; // (+) converts string 'id' to a number
      this.setOtherUser(otherUserId);
      this.setLoggedInUser();

    });
  }

  setOtherUser(id) {
    this.authService.getUserById(id).subscribe(data => {
      this.otherUser = data;
      this.setPastMessages();
    })
  }
  sendMessage() {
    if (this.input) {
      var newMsg = new ChatMessage(this.loggedInUser.id, this.otherUser.id,this.input,new Date())
      this.websocketService.sendMessage(newMsg);
      this.msgs = [newMsg, ...this.msgs];
      this.input = '';
    }
  }

  setLoggedInUser() {
    this.authService.currentUser.subscribe(data => {
      this.loggedInUser = data;
    })
  }

  setPastMessages() {
    this.websocketService.getLastMessages(this.loggedInUser.id, this.otherUser.id).subscribe(data => {
      this.msgs=[]
      for (var m of data)
        this.msgs.push(m)
      this.msgs.sort((a, b) => -a.dateSent.toString().localeCompare(b.dateSent.toString()))
    })
  }

  isLoggedInUserReciever(message) {
    let userId = String(this.loggedInUser.id);
    return message.reciever == userId;
  }

  formatDate(date) {
    return new Date(date).toLocaleString()
  }
}


