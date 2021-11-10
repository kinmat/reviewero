import { Book } from 'src/app/model/book';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {
  id: number
  book: Book

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) {
    this.book=new Book()
    this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.setBook(this.id)
    });
  }

  setBook(id: number) {
    this.bookService.getBookByID(id).subscribe(data => {
      this.book = data;
      console.log(data.authors)
    })
  }

  onAuthorClick(id: number) {
    this.router.navigateByUrl(`/authors/${id}`);
  }


}
