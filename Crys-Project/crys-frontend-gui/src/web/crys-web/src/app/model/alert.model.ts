import {Coin} from "./coin.model";

export interface Alert {
  id: string;
  value: number;
  operator: number;
  userId: string;
  coin: Coin;
}
