import { environment } from './../../environments/environment';
import { ChatMessage } from 'src/app/model/chat-message';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs/esm6';
import * as SockJS from 'sockjs-client';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';

const URL = environment.API_URL;
const WS_URL = environment.WS_URL;

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

  initializeWebSocketConnection() {;
    const ws = new SockJS(WS_URL);
    this.stompClient = Stomp.over(ws);
  }

  

  sendMessage(message) {
    this.stompClient.send('/app/chat', {}, JSON.stringify(message));
  }

  getLastMessages(userOne, userTwo) {
    return this.http.post<ChatMessage[]>(URL + 'last-messages', {
      requester: {id: userOne},
      requestee: {id: userTwo},
    })
  }
}