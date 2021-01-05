import { Component, OnInit } from '@angular/core';
import {Coin} from "../model/coin.model";
import {CoinService} from "../coin/services/coin.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  coins: Coin[];

  constructor(private coinService: CoinService) { }

  ngOnInit() {
    this.coinService.getAllCoins().subscribe(data=> {
      this.coins = data as Coin[];
      this.coins = this.coins.splice(0,4);
    });
  }

}
