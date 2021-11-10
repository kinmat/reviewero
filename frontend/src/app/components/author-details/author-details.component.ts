import { BookService } from 'src/app/services/book.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/services/author.service';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent implements OnInit {
  author = new Author()
  id: number;
  books: Book[]

  constructor(private route: ActivatedRoute, private authorService: AuthorService,
  private bookService: BookService, private router:Router) {
    this.author=new Author()
    this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.setAuthor(this.id)
    });
  }

  setAuthor(id: number) {
    this.authorService.getAuthorByID(id).subscribe(data => {
      this.author = data;
      this.getBooks();
      })
  }

  getBooks() {
    this.bookService.getBooksByAuthor(this.id).subscribe(data => {
      this.books = data;
      console.log(data)
    })
  }

  changeRoute(event) {
    let url = String(event);
    this.router.navigateByUrl(url)
  }

  ngOnInit(): void {
  }

}
