import {Component, Inject, OnInit} from '@angular/core';
import {Coin} from "../../model/coin.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Alert} from "../../model/alert.model";
import {AuthService} from "../../user/services/auth.service";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html',
  styleUrls: ['./alert-dialog.component.scss']
})
export class AlertDialogComponent implements OnInit {

  alert: Alert;
  alertValue: string;
  shouldBeDisabled: boolean;

  constructor( private authService: AuthService,
               private alertService: AlertService,
               public dialogRef: MatDialogRef<AlertDialogComponent>,
               @Inject(MAT_DIALOG_DATA) public data: Coin) { }

  ngOnInit(): void {

    this.alertValue = "";

    this.alert = {} as Alert;
    this.alert.coin = this.data;
    this.alert.userId = this.authService.getUserId();
  }

  onNoClick(): void {

    this.dialogRef.close();
  }

  numberOnly() {

    for(let i = 0; i < this.alertValue.length; i++) {
      if((this.alertValue.charCodeAt(i) > 31 && (this.alertValue.charCodeAt(i) < 48 || this.alertValue.charCodeAt(i) > 57)) &&  this.alertValue.charAt(i) != '.') {
        this.shouldBeDisabled = false;
        return;
      }
    }

    this.shouldBeDisabled = true;
  }

  addAlertForUser() {
    this.alert.value = Number(this.alertValue);
    this.alertService.addAlert(this.alert);
    this.dialogRef.close();
  }
}
