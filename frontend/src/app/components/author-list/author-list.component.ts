import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/services/author.service';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  @Input() authors: Author[];
  @Output() newRoute = new EventEmitter<string>();
  
  constructor(private authorService: AuthorService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onAuthorClick(id: number) {
    this.newRoute.emit(`/authors/${id}`)
  }

}
