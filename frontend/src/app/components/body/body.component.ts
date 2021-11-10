import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  changeRoute(event) {
    let url = String(event);
    this.router.navigateByUrl(url);
  }

}
