import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Coin} from "../../model/coin.model";

@Component({
  selector: 'app-coins-list',
  templateUrl: './coins-list.component.html',
  styleUrls: ['./coins-list.component.scss']
})
export class CoinsListComponent implements OnInit {

  @Input() coins: Coin[];
  @Input() isLogged: boolean;
  @Input() coinListType: string;

  @Output() removeCoinFromFavoriteEmitter = new EventEmitter();
  @Output() addCoinToFavoriteEmitter = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  removeCoin(coinId: string) {
    this.removeCoinFromFavoriteEmitter.emit(coinId);
  }

  addCoin(coinId: string) {
    this.addCoinToFavoriteEmitter.emit(coinId);
  }

}
