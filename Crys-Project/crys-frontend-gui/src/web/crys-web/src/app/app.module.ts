import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CoinModule} from "./coin/coin.module";
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { HomeComponent } from './home/home.component';
import {AuthModule} from "./user/auth.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./user/services/jwt.interceptor";
import { NotificationPanelComponent } from './notification/notification-panel/notification-panel.component';
import { NotificationItemComponent } from './notification/notification-item/notification-item.component';
import { MatButtonModule } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    NotificationPanelComponent,
    NotificationItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoinModule,
    AuthModule,
    HttpClientModule,
    MatButtonModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
