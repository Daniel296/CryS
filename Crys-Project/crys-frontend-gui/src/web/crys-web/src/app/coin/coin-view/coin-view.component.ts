import { Component, OnInit } from '@angular/core';
import {Coin} from "../../model/coin.model";
import {CoinService} from "../services/coin.service";
import {AuthService} from "../../user/services/auth.service";
import {interval, Observable} from "rxjs";
import {element} from "protractor";

@Component({
  selector: 'app-coin-view',
  templateUrl: './coin-view.component.html',
  styleUrls: ['./coin-view.component.scss']
})
export class CoinViewComponent implements OnInit {

  allCoins: Coin[];
  favoriteCoins: Coin[];

  constructor(private coinService: CoinService,
              private authService: AuthService) {  }

  ngOnInit() {
    // initialize data
    this.initializeCoinsFromAPI();

    // set a loop to do requests
    interval(60 * 1000).subscribe( x => {
      this.initializeCoinsFromAPI();
    });
  }

  initializeCoinsFromAPI() {
    // get all coins
    this.coinService.getAllCoins().subscribe(data=> {
      this.allCoins = data as Coin[];
    });

    //get favorite coins
    let uuid = this.authService.getUserId();
    if(uuid !== null) {
      this.coinService.getFavoriteCoins(uuid).subscribe( data => {
        this.favoriteCoins = data as Coin[];
      });
    }
  }

  isUserLogged(): boolean {
    return this.authService.isLogged;
  }

  addCoinToFavorite($event: any) {
    let coinId = $event;
    let userId = this.authService.getUserId();

    //add to list if it doesn't exist
    const coin = this.allCoins.find(coin => coin.id === coinId);
    if(this.favoriteCoins.findIndex(e => e.id === coin.id) === -1) {
      this.favoriteCoins.push(coin);
      //make request to api
      this.coinService.addCoinToFavorite(userId, coinId);
    }
  }

  removeCoinFromFavorite($event: any) {
    let coinId = $event;
    let userId = this.authService.getUserId();

    // remmove from list
    this.favoriteCoins = this.favoriteCoins.filter(coin => coin.id !== coinId);

    // make request to api
    this.coinService.removeCoinFromFavorite(userId, coinId);
  }
}
