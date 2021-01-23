import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CoinViewComponent} from "./coin/coin-view/coin-view.component";
import {LoginComponent} from "./user/login/login.component";
import {RegisterComponent} from "./user/register/register.component";
import {AccountDetailsComponent} from "./user/account-details/account-details.component";
import {AuthGuard} from './user/services/auth.guard';
import {AccountAlertsComponent} from "./user/account-alerts/account-alerts.component";


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'coins', component: CoinViewComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'alerts', component: AccountAlertsComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountDetailsComponent, canActivate: [AuthGuard] },
  { path: '**', component: HomeComponent, redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled', relativeLinkResolution: 'legacy' })], // this config to scroll to the top when we redirect to another route
  exports: [RouterModule]
})
export class AppRoutingModule { }
