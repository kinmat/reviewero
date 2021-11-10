import { UserService } from './../../services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-public-profile',
  templateUrl: './public-profile.component.html',
  styleUrls: ['./public-profile.component.css'],
})
export class PublicProfileComponent implements OnInit {
  loggedInUser: User = new User();
  profileUser: User = new User();
  requested: boolean;
  isFriend: boolean;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private userService: UserService
  ) {
    this.authService.currentUser.subscribe((data) => {
      this.loggedInUser = data;
      this.route.params.subscribe((params) => {
        let id = +params['id']; // (+) converts string 'id' to a number
        this.authService.getUserById(id).subscribe((profile) => {
          this.profileUser = profile;
          this.areFriends();
          this.setRequested();
        });
      });
     
    });
  }

  areFriends() {
    let friends = this.userService.getAcceptedFriends(this.loggedInUser);
    for (var f of friends) {
      if (f.id == this.profileUser.id) {
        this.isFriend = true;
        return;
      }
      else if (this.loggedInUser.id == this.profileUser.id) this.isFriend = true;
      else this.isFriend = false;
    }
  }

  setRequested() {
    this.requested = false;
    for (var f of this.loggedInUser.requester)
      if (f.requestee.id == this.profileUser.id && !f.accepted) {
        this.requested = true; break;
      }
  }

  addFriend() {
    this.userService.addFriendship(this.loggedInUser, this.profileUser).subscribe(() =>
      this.authService.refreshSave())
      this.requested = true;
  }

  ngOnInit(): void {}
}
