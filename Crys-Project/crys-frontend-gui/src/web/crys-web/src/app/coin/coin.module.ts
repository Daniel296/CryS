import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoinsListComponent } from './coins-list/coins-list.component';
import { CoinViewComponent } from './coin-view/coin-view.component';



@NgModule({
  declarations: [CoinsListComponent, CoinViewComponent],
  exports: [
    CoinsListComponent
  ],
  imports: [
    CommonModule
  ]
})
export class CoinModule { }
