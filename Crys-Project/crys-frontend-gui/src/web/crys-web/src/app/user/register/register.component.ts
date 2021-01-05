import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import { AuthService } from "../services/auth.service";
import { first } from "rxjs/operators";
import { Router } from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private registerForm = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', /*[Validators.required, Validators.email]*/],
    phone: ['', /*[Validators.required, Validators.minLength(10), Validators.maxLength(10)]*/],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', Validators.required]
  });
  // }, {
  //   validator: MustMatch('password', 'confirmPassword')
  // });

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authService: AuthService)
  { }

  ngOnInit() {
  }

  onSubmit() {
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    console.log("register component ->>" + this.registerForm);

    this.authService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['/login']);
          console.log(data);
        },
        error => {
          console.log(error);
        }
    );
  }

}
