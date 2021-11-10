import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  @Input() users: User[];
  @Output() newRoute = new EventEmitter<string>();
  
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onUserClick(id: number) {
    this.newRoute.emit(`/user/${id}`)
  }

}

