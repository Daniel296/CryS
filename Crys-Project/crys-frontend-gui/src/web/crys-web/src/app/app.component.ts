import {Component, OnInit} from '@angular/core';
import {AngularBootstrapToastsService} from "angular-bootstrap-toasts";
import {Form, FormControl, FormGroup} from "@angular/forms";
import {interval} from "rxjs";
import {AlertService} from "./alert/services/alert.service";
import {AuthService} from "./user/services/auth.service";
import {Alert} from "./model/alert.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'CryS';

  toastForm: FormGroup;
  containerPlacementForm: FormGroup;

  constructor(private toastsService: AngularBootstrapToastsService,
              private alertService: AlertService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.createContainerForm();
    this.createMainForm();

    interval(15 * 1000).subscribe( x => {
      this.showAlerts();
    });
  }

  public showAlerts(): void {

    this.alertService.getAlertsForUser(this.authService.getUserId()).subscribe( data => {

      const alerts = data as Alert[];

      for(let i = 0; i < alerts.length; i++ ) {

        const title = "Alert for " + alerts[i].coin.name;
        const text = alerts[i].coin.name + " price is <b>$" + alerts[i].coin.priceUsd.toFixed(2)
          + "</b>. Go to coin <a href=\"localhost:4200/" + alerts[i].coin.id + "\" class='text-primary'>page</a>";

        this.toastForm.value.title = title;
        this.toastForm.value.text = text;

        this.toastsService.showSimpleToast(this.toastForm.value);
      }

    });
  }


  private createContainerForm (): void {
    const defaultMargin: string = '10px';

    this.containerPlacementForm = new FormGroup({
      position: new FormControl('topRight'),
      marginLeft: new FormControl(defaultMargin),
      marginRight: new FormControl(defaultMargin),
      marginTop: new FormControl('30px'),
      marginBottom: new FormControl(defaultMargin)
    });
  }

  private createMainForm (): void {
    this.toastForm = new FormGroup({
      text: new FormControl('<b>Bitcoin is under your alert price</b>'),
      title: new FormControl('Alert for bitcoin'),
      duration: new FormControl(10 * 1000),
      moment: new FormControl('just now'),
      iconClass: new FormControl('fa fa-bell text-danger'),
      titleClass: new FormControl(''),
      bodyClass: new FormControl('body-size'),
      toastClass: new FormControl(''),
      progressLineClass: new FormControl('text-danger'),
      toolbarClass: new FormControl(''),
      closeButtonClass: new FormControl(''),
      showProgressLine: new FormControl(true),
      closeByClick: new FormControl(false),
      pauseDurationOnMouseEnter: new FormControl(true),
    });
  }
}
