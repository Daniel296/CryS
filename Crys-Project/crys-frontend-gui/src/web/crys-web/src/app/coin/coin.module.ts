import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoinsListComponent } from './coins-list/coins-list.component';
import { CoinViewComponent } from './coin-view/coin-view.component';
import { CoinOverviewComponent } from './coin-overview/coin-overview.component';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [CoinsListComponent, CoinViewComponent, CoinOverviewComponent],
  exports: [
    CoinsListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ]
})
export class CoinModule { }
