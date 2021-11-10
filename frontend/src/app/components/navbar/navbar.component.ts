import { Router } from '@angular/router';
import { TokenStorageService } from './../../services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean
  searchText: string
  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(data => {
      if (data) this.isLoggedIn = true
      else this.isLoggedIn = false;
    }
     )

  }

  logout(): void {
    this.authService.logout()
  }

  search()
  {
    this.router.navigate(['search'], { queryParams: { param: this.searchText} });
  }


}
