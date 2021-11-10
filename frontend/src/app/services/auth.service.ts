import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../model/user';
import { UserService } from './user.service';

const URL = 'http://localhost:8081/api/auth/';
const user_URL= 'http://localhost:8081/api/user/';
const USER_KEY = 'auth-user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  
  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem(USER_KEY)));
    this.currentUser = this.currentUserSubject.asObservable();
  }
  
   public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  
  login(username: string, password: string): Observable<any> {
    return this.http.post(URL + 'signin', {
      username,
      password
    }, httpOptions).pipe();
  }

  register(user: User): Observable<any> {
    return this.http.post(URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }

  public saveUser(user: any): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  public refreshSave(): void {
    this.getUserById(this.currentUserSubject.value.id).subscribe(data => {
      let updatedUser = this.currentUserSubject.value;
      updatedUser.requestee = data.requestee;
      updatedUser.requester = data.requester;
      this.saveUser(updatedUser)

    })
    
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem(USER_KEY);
    this.currentUserSubject.next(null);
  }
  
  public getUserByName(name: string): Observable<User[]> {
    const params = new HttpParams().append('username', name);
    return this.http.get<User[]>(user_URL + `search/`, {params})
  }

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(user_URL + `${id}`)
  }




}