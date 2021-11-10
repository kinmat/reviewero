import { Router } from '@angular/router';
import { BookService } from './../../services/book.service';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.css']
})
export class AllBooksComponent implements OnInit {
  books: Book[];

  constructor(private bookService: BookService, private router: Router) {
    this.books = [];
  }

  ngOnInit(): void {
    this.bookService.findAll().subscribe(data => {
      this.books = data;
    })
  }
  
  changeRoute(event) {
    let url = String(event);
    this.router.navigateByUrl(url)
  }

}
