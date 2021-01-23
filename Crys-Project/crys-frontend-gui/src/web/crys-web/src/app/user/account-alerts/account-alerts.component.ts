import { Component, OnInit } from '@angular/core';
import {Alert} from "../../model/alert.model";
import {AlertService} from "../../alert/services/alert.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-account-alerts',
  templateUrl: './account-alerts.component.html',
  styleUrls: ['./account-alerts.component.scss']
})
export class AccountAlertsComponent implements OnInit {

  alerts: Alert[];

  constructor(private alertService: AlertService,
              private authService: AuthService) { }

  ngOnInit(): void {

    this.alerts = [] as Alert[];

    this.alertService.getAlertsForUser(this.authService.getUserId()).subscribe( data => {
      this.alerts = data as Alert[];
    })
  }

  removeAlert(id: string) {

    this.alertService.removeAlert(id);

    this.alerts = this.alerts.filter( alert => alert.id != id);
  }
}
