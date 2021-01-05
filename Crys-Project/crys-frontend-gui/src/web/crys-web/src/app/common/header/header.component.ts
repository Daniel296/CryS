import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user.model";
import {Router} from "@angular/router";
import {AuthService} from "../../user/services/auth.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  private currentUser: User;
  private userDetails;

  constructor(private router: Router,
              private authService: AuthService)
  {
      this.authService.currentUser.subscribe(x => this.currentUser = x );
  }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
    // this.router.navigate(['/login']);
  }

  getUserDetails() {
    this.authService.getUserDetails()
      .pipe(first())
      .subscribe(
        data => {
          this.userDetails = data;
        },
        error => {
          console.log(error);
        }
      );
  }

}
