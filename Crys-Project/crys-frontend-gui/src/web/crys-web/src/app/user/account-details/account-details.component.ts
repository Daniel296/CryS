import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user.model";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {

  private currentUser: User;

  private detailsForm = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', /*[Validators.required, Validators.email]*/],
    telephone: ['', /*[Validators.required, Validators.minLength(10), Validators.maxLength(10)]*/],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authService: AuthService) {
    this.authService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
  }

  onSubmit() {

  }

}
