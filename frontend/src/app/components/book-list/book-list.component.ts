import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  @Input() books: Book[];
  @Output() newRoute = new EventEmitter<string>();
  
  constructor() {
  }

  ngOnInit(): void {
  }

  onBookClick(id: number) {
    this.newRoute.emit(`/books/${id}`)
  }

  onAuthorClick(id: number) {
    this.newRoute.emit(`/authors/${id}`)
  }

}
