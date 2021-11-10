import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../model/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private URL='http://localhost:8081/api/authors/'

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Author[]> {
    return this.http.get<Author[]>(this.URL);
  }

  public getAuthorByID(id: number): Observable<Author> {
    return this.http.get<Author>(this.URL+`${id}`);
  }

  public getAuthorByName(name: string): Observable<Author[]> {
    const params = new HttpParams().append('name', name);
    return this.http.get<Author[]>(this.URL + `search/`, {params})
  }
}
