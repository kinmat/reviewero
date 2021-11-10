import { UserService } from './../../services/user.service';
import { User } from './../../model/user';
import { AuthService } from './../../services/auth.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  @Output() newRoute = new EventEmitter<string>();
  
  currentUser: User=new User()
  loggedIn: boolean = false;
  areFriends: User[] = []
  areRequests: User[]= []

  constructor(private authService: AuthService, private userService: UserService) {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data
      if (this.currentUser) {
        this.loggedIn = true;
        this.setFriends()
        this.setRequests()
      }
      else {
        this.loggedIn = false;
        this.areFriends=[]
      }

      
    })
   }
  setFriends() {
    this.areFriends=this.userService.getAcceptedFriends(this.currentUser);
  }

  setRequests() {
    this.areRequests=this.userService.getFriendRequests(this.currentUser)
  }

  acceptRequest(user) {
    this.userService.setFriendshipAccepted(user, this.currentUser).subscribe(() => {
      this.authService.refreshSave()
    });
  }

  goToProfile(id) {
    this.newRoute.emit(`/user/${id}`)
  }

  openChat(id) {
    this.newRoute.emit(`chat/user/${id}`)
  }

  ngOnInit(): void {
  }

  

  
  

}
