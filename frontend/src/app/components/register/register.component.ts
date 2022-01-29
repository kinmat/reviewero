import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  isUsernameNOTValid: boolean;
  form: FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private router: Router,private builder: FormBuilder, private authService: AuthService) {
    this.user = new User();
    this.isUsernameNOTValid = false;
  }


  ngOnInit() {
    this.form = this.builder.group({
    username: ['', [
        Validators.required]],
    email: ['', [
        Validators.required,
        Validators.pattern("[^ @]*@[^ @]*")]],
    password: ['', [
      Validators.required]],
  });}

  register(): void {
      this.authService.register(this.user).subscribe(
        data => {
          this.isSuccessful = true;
          this.isSignUpFailed = false;
          this.router.navigateByUrl('/login')
        },
        err => {
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
        }
      );
  }


}
