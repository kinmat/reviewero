import { BookListItem } from './../model/book-list-item';
import { Friendship } from './../model/friendship';
import { User } from './../model/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Book } from '../model/book';


const API_URL = 'http://localhost:8081/api/test/';
const URL = 'http://localhost:8081/api/user/';
const F_URL = 'http://localhost:8081/api/friend/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient, private authService: AuthService) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }


  public getAcceptedFriends(user: User) {
    let friends: User[] = [];
    for (var f of user.requester)
      if (f.accepted) friends.push(f.requestee)
    for (var r of user.requestee)
      if (r.accepted) friends.push(r.requester)   
    return friends;
  }

  public getFriendRequests(user: User) {
    let friends: User[] = [];
    for (var f of user.requestee)
      if (!f.accepted) friends.push(f.requester)
    return friends;
  }

  public addFriendship(requester: User, requestee: User) {
    return this.http.post(F_URL + `add`, {
      requester: requester,
      requestee: requestee,
      accepted: false
    },httpOptions)
  }

  public setFriendshipAccepted(requester: User, requestee: User) : Observable<User>{
    return this.http.post<User>(F_URL + `accept`, {
      requester: requester,
      requestee: requestee,
    },httpOptions)
  }

  public getBookListByState(user: User, state: string): Book[] {
    let books: Book[]=[];
    for (var b of user.addedBookList) {
      if(b.state.name==state)  books.push(b.book)
    }
    return books;
  }

  public getIsBookAsInUsersList(user: User, book: Book): BookListItem {
    for (var b of user.addedBookList) {
      if (b.book.id == book.id) return b;
    }
    return null;
  }
}