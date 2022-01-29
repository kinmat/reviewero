import { UserService } from './../../services/user.service';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Author } from 'src/app/model/author';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  param: string;
  books: Book[];
  authors: Author[];
  users: User[];
  state: string;
  bookFilter: string;
  bookFilterOptions: string[] = ['Title', 'Author'];
  currentSearchList: string;
  options: string[] = ['Books', 'Authors', 'Users'];

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router,
    private authorService: AuthorService,
    private authService: AuthService
  ) {
    this.route.queryParams.subscribe((params) => {
      this.param = params['param'];
      this.currentSearchList = this.options[0];
      this.bookFilter = this.bookFilterOptions[0];
      this.state = this.options[0];
      if (this.param) this.setBooks();
    });
  }

  ngOnInit(): void {}

  setBooks() {
    if (this.bookFilter == "Title") {
      this.bookService.getBooksByTitle(this.param).subscribe((data) => {
        this.books = data;
      });
    }
    else if (this.bookFilter == "Author") {
      this.bookService.getBooksByAuthorsName(this.param).subscribe((data) => {
      this.books = data;
    });
    }

  }

  setAuthors() {
    this.authorService.getAuthorByName(this.param).subscribe((data) => {
      this.authors = data;
    });
  }

  setUsers() {
    this.authService.getUserByName(this.param).subscribe((data) => {
      this.users = data;
    });
  }

  changeRoute(event) {
    let url = String(event);
    this.router.navigateByUrl(url);
  }

  onSelectionChanged(event) {
    console.log(this.state);
    this.state = event;
  }

  search() {
    this.currentSearchList=this.state
    if (this.state == 'Books') this.setBooks();
    if (this.state == 'Authors') this.setAuthors();
    if (this.state == 'Users') this.setUsers();
  }

  isStateBooks() {
    return this.state == 'Books'
  }

}
