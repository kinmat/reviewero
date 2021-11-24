import { ChatMessage } from 'src/app/model/chat-message';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs/esm6';
import * as SockJS from 'sockjs-client';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';

const F_URL = 'http://localhost:8081/api/';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public stompClient;
  public msg = [];
  private user: User;

  constructor(private authService: AuthService, private http: HttpClient) {
    this.authService.currentUser.subscribe(data => {
      this.user = data;
      this.initializeWebSocketConnection();
    })
  }

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8081/socketjs';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
  }

  

  sendMessage(message) {
    this.stompClient.send('/app/chat', {}, JSON.stringify(message));
  }

  getLastMessages(userOne, userTwo) {
    return this.http.post<ChatMessage[]>(F_URL + 'last-messages', {
      requester: {id: userOne},
      requestee: {id: userTwo},
    })
  }
}