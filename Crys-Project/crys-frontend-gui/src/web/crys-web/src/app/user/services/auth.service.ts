import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { User } from "../../model/user.model";
import {environment} from "../../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl: string = environment.baseUrl;

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  public isLogged: boolean = false;
  private user: User;

  private headers: HttpHeaders = new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
    'Accept': 'application/json',
  });

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem("currentUser")));
    this.currentUser = this.currentUserSubject.asObservable();

    if(this.currentUserSubject.value !== null) {
      this.isLogged = true;
    }
  }

  register(user: User) {
    user.emailNotification = true;
    return this.http.post(this.baseUrl + 'api/user/register', user, {headers: this.headers});
  }

  login(credentials: { email: '', password: '' }) {
    return this.http.post<any>(this.baseUrl + 'api/user/login', credentials)
      .pipe(map(user => {
        if(user && user.token) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('token', JSON.stringify(user.token));
          this.currentUserSubject.next(user);
          this.isLogged = true;
          this.user = user;
        }
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    this.isLogged = false;
    this.currentUserSubject.next(null);
    this.user = undefined;
  }

  getUserDetails() {
    if(this.getUserId() !== null) {
      return this.http.get(this.baseUrl + "api/user/details/" + this.getUserId());
    }
  }

  getUserId(): string {
    if(this.currentUserSubject.value !== null) {
      return this.currentUserSubject.value.uuid;
    }
    return null;
  }

}
