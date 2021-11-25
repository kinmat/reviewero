import { BookListItem } from './../../model/book-list-item';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Book } from 'src/app/model/book';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';
import { User } from 'src/app/model/user';
import { BookState } from 'src/app/model/book-state';
import { Review } from 'src/app/model/review';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css'],
})
export class BookDetailsComponent {
  id: number;
  book: Book;
  isBookAdded: boolean;
  usersBookItem: BookListItem;
  currentUser: User;
  stateId: number;
  options: BookState[];
  reviews: Review[] = [];
  userHasReview: boolean;
  newReview: Review= new Review();

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router,
    private userService: UserService,
    private authService: AuthService
  ) {
    this.book = new Book();
    this.route.params.subscribe((params) => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.setCurrentUser();
      this.setStates();
    });
  }
  setCurrentUser() {
    this.authService.currentUser.subscribe((data) => {
      let user_id = data.id;
      this.authService.getUserById(user_id).subscribe((user) => {
        this.currentUser = user;
        this.setBook(this.id);
      });
    });
  }

  setBook(id: number) {
    this.bookService.getBookByID(id).subscribe((data) => {
      this.book = data;
      this.setIsBookAdded();
      this.setReviews();
    });
  }
  setReviews() {
    this.bookService.getReviewsByBookId(this.book.id).subscribe(data => {
      this.reviews = data;
      let rev = data.find(r => r.user.id == this.currentUser.id)
      console.log(rev)
      if(rev) this.userHasReview = true;
      else this.userHasReview = false;
      console.log(this.userHasReview)
      
   })
  }

  formatDate(date) {
    return new Date(date).toLocaleString()
  }

  addReview() {
    this.newReview.user = this.currentUser;
    this.newReview.book = this.book;
    this.newReview.dateAdded = new Date();
    this.bookService.addReview(this.newReview).subscribe(func => {
      this.setReviews()
    })
  }

  onAuthorClick(id: number) {
    this.router.navigateByUrl(`/authors/${id}`);
  }

  setIsBookAdded() {
    this.usersBookItem = this.userService.getIsBookAsInUsersList(
      this.currentUser,
      this.book
    );
    if (this.usersBookItem) this.isBookAdded = true;
    else this.isBookAdded = false;
  }

  addBook() {
    this.isBookAdded = true;
    this.userHasReview = false;
    let state = this.options.find(o => o.id == this.stateId);
    let item = new BookListItem(
      this.currentUser,
      this.book,
      state,
      new Date()
    );
    this.bookService.addBookListItem(item).subscribe(data => {
      this.usersBookItem=data
    });
  }

  setStates() {
    this.bookService.getAllBookStates().subscribe((data) => {
      this.options = data;
      this.stateId = data[0].id;
    });
  }

  changeState() {
    let state = this.options.find(o => o.id == this.stateId);
    this.usersBookItem.state = state;
    let item = new BookListItem(
      this.currentUser,
      this.book,
      state,
      new Date()
    );
    this.bookService.changeBookListItemState(item).subscribe();
  }
}
