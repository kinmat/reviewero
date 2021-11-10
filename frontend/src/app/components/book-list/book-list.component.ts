import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  @Input() books: Book[];
  @Output() newRoute = new EventEmitter<string>();
  
  constructor(private bookService: BookService, private router: Router) {
  }

  ngOnInit(): void {
  }

  goToBookDetails(id: number) {
    this.newRoute.emit(`/books/${id}`)
  }

  onAuthorClick(id: number) {
    this.newRoute.emit(`/authors/${id}`)
  }

}
