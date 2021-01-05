import { Injectable } from '@angular/core';
import {Coin} from "../../model/coin.model";
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  private baseUrl: string = environment.baseUrl;

  // coins: Coin[] = [
  //   { id: '123', name: 'Bitcoin', rank: 1, symbol: 'BTC', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 6931.505855566662, changePercentage24hr: -0.8101417214350335, vwap24hr: 7175.0663247679233209 },
  //   { id: '1243', name: 'Ethereum', rank: 2, symbol: 'ETH', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 154.79, changePercentage24hr: 0.4014174350335, vwap24hr: 7175.0663247679233209 },
  //   { id: '125', name: 'XRP', rank: 3, symbol: 'XRP', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 0.23, changePercentage24hr: -0.8101350335, vwap24hr: 7175.0663247679233209 },
  //   { id: '126', name: 'Tether', rank: 4, symbol: 'USDT', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 223.91, changePercentage24hr: 1.8101417214350335, vwap24hr: 7175.0663247679233209 },
  // ];
  //
  // favoriteCoins: Coin[] = [
  //   // { id: '123', name: 'Bitcoin', rank: 1, symbol: 'BTC', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 6931.505855566662, changePercentage24hr: -0.8101417214350335, vwap24hr: 7175.0663247679233209 },
  //   // { id: '126', name: 'Tether', rank: 4, symbol: 'USDT', logoURL: "", supply: 17193925, maxSupply: 21000000, marketCapUsd: 119179791817.67401, volumeUsd24hr: 2928356777.6066666, priceUsd: 223.91, changePercentage24hr: 1.8101417214350335, vwap24hr: 7175.0663247679233209 },
  // ];

  private headers: HttpHeaders = new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
    'Accept': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getAllCoins() {
    return this.http.get(this.baseUrl + "api/coin", { headers: this.headers });
  }

  getFavoriteCoins(user_id: string) {
    return this.http.get(this.baseUrl + "api/favorite/" + user_id, { headers: this.headers });
  }

  addCoinToFavorite(user_id: string, coin_id: string) {
    this.http.put(this.baseUrl + "api/favorite/add", {userId: user_id, coinId: coin_id}, { headers: this.headers })
      .subscribe();
  }

  removeCoinFromFavorite(user_id: string, coin_id: string) {
    this.http.put(this.baseUrl + "api/favorite/remove", {userId: user_id, coinId: coin_id}, { headers: this.headers })
      .subscribe();
  }
}
