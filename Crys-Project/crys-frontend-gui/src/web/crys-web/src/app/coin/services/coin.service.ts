import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  private baseUrl: string = environment.baseUrl;

  private headers: HttpHeaders = new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
    'Accept': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getCoinById(coinId: string) {
    return this.http.get(this.baseUrl + "api/coin/" + coinId, { headers: this.headers });
  }

  getAllCoins() {
    return this.http.get(this.baseUrl + "api/coin", { headers: this.headers });
  }

  getFavoriteCoins(user_id: string) {
    return this.http.get(this.baseUrl + "api/favorite/" + user_id, { headers: this.headers });
  }

  getCoinHistory(coinId: string) {
    return this.http.get(this.baseUrl + "api/coin/" + coinId + "/history", { headers: this.headers });
  }

  addCoinToFavorite(user_id: string, coin_id: string) {
    this.http.post(this.baseUrl + "api/favorite/add", {userId: user_id, coinId: coin_id}, { headers: this.headers })
      .subscribe();
  }

  removeCoinFromFavorite(user_id: string, coin_id: string) {
    this.http.post(this.baseUrl + "api/favorite/remove", {userId: user_id, coinId: coin_id}, { headers: this.headers })
      .subscribe();
  }
}
