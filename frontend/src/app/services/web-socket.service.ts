import { ChatMessage } from 'src/app/model/chat-message';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs/esm6';
import * as SockJS from 'sockjs-client';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public stompClient;
  public msg = [];
  private user: User;

  constructor(private authService: AuthService) {
    this.authService.currentUser.subscribe(data => {
      this.user = data;
      this.initializeWebSocketConnection();
    })
  }

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8081/socketjs';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/user/" + that.user.id + "/queue/messages", (message) => {
        that.msg.push(JSON.parse(message.body));
        
      });
    });
  }

  

  sendMessage(message, id) {
    var newMsg = {
      sender: this.user.id,
      reciever: id,
      content: message
    }
    this.stompClient.send('/app/chat', {}, JSON.stringify(newMsg));
    this.msg.push(message)
  }
}