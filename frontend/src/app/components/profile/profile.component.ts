import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from './../../services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  constructor(private authService: AuthService, private router: Router) {
    this.currentUser = new User();
  }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
    })
  }

  goToPublicProfile() {
    this.router.navigateByUrl(`user/${this.currentUser.id}`)
  }

}
