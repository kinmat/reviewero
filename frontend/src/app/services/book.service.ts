import { BookState } from './../model/book-state';
import { Author } from './../model/author';
import { Book } from './../model/book';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookListItem } from '../model/book-list-item';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const URL = 'http://localhost:8081/api/books/'
const L_URL = 'http://localhost:8081/api/list/'


@Injectable({
  providedIn: 'root'
})
export class BookService {

  

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Book[]> {
    console.log("all")
    return this.http.get<Book[]>(URL);
  }

  public getBookByID(id: number): Observable<Book> {
    return this.http.get<Book>(URL+`${id}`);
  }

  public getBooksByAuthor(id: number): Observable<Book[]> {
    return this.http.get<Book[]>(URL + `author/${id}`)
  }

  public getBooksByTitle(title: string): Observable<Book[]> {
    const params = new HttpParams().append('title', title);
    console.log("title")
    return this.http.get<Book[]>(URL + `search/`, {params})
  }

  public addBookListItem(item: BookListItem) {
    return this.http.post<BookListItem>(L_URL + `add-book`, {
      user: item.user,
      book: item.book,
      state: item.state,
      dateAdded: item.dateAdded
    },httpOptions)
  }

  public getAllBookStates() {
    return this.http.get<BookState[]>(L_URL + `states`, httpOptions)
  }
  
}
