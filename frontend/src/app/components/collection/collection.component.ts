import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent implements OnInit {
  toReadList: Book[] = [];
  readList: Book[] = [];
  loggedInUser: User = new User();
  
  constructor(private route: ActivatedRoute,
    private authService: AuthService,
    private userService: UserService,
    private router: Router) {
    this.authService.currentUser.subscribe((data) => {
      let id = data.id
      this.authService.getUserById(id).subscribe(user => {
        this.loggedInUser = user;
        this.setToReadList();
        this.setReadList();
      })

    });
  }
  
    setToReadList() {
      this.toReadList = this.userService.getBookListByState(this.loggedInUser, "to_read");
    }
  
    setReadList() {
      this.readList = this.userService.getBookListByState(this.loggedInUser, "read");
    }
  
    changeRoute(event) {
      let url = String(event);
      this.router.navigateByUrl(url);
    }

  ngOnInit(): void {
  }

}
