import {Coin} from "./coin.model";
import {User} from "./user.model";

export interface Alert {
  id: string;
  alertValue: number;
  operator: number;
  user: User;
  coin: Coin;
}
