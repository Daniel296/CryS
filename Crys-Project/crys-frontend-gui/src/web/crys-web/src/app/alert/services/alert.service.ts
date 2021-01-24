import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Alert} from "../../model/alert.model";

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private baseUrl: string = environment.baseUrl;

  private headers: HttpHeaders = new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
    'Accept': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getAlertsForUser(user_id) {
    return this.http.get(this.baseUrl + "api/alert/" + user_id, { headers: this.headers });
  }

  getUserAlertsThatShouldBeTriggered(user_id) {
    return this.http.get(this.baseUrl + "api/alert/trigger" + user_id, { headers: this.headers });
  }

  addAlert(alert: Alert) {
    this.http.post(this.baseUrl + "api/alert", alert, { headers: this.headers })
      .subscribe();
  }

  removeAlert(alert_id: string) {
    this.http.delete(this.baseUrl + "api/alert/remove/" + alert_id, { headers: this.headers })
      .subscribe();
  }
}
