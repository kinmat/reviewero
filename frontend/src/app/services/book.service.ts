import { BookState } from './../model/book-state';
import { Author } from './../model/author';
import { Book } from './../model/book';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookListItem } from '../model/book-list-item';
import { Review } from '../model/review';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const URL = 'http://localhost:8081/api/books/'
const L_URL = 'http://localhost:8081/api/list/'
const R_URL = 'http://localhost:8081/api/review/'


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

  public changeBookListItemState(item: BookListItem) {
    return this.http.post<BookListItem>(L_URL + `change-book-state`, {
      user: item.user,
      book: item.book,
      state: item.state,
      dateAdded: item.dateAdded
    },httpOptions)
  }

  public getAllBookStates() {
    return this.http.get<BookState[]>(L_URL + `states`, httpOptions)
  }

  public getReviewsByBookId(id: number): Observable<Review[]> {
    const params = new HttpParams().append('book_id', id.toString());
    return this.http.get<Review[]>(R_URL, {params})
  }

  public addReview(review: Review) {
    return this.http.post<Review>(R_URL + `add`, {
      user: review.user,
      book: review.book,
      rating: review.rating,
      text: review.text,
      dateAdded: review.dateAdded
    },httpOptions)
  }

  
}
