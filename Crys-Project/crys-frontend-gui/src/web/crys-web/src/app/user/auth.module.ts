import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AccountDetailsComponent } from './account-details/account-details.component';
import { AccountAlertsComponent } from './account-alerts/account-alerts.component';



@NgModule({
  declarations: [LoginComponent, RegisterComponent, AccountDetailsComponent, AccountAlertsComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
