import { Coin } from './coin.model';
import { Alert } from "./alert.model";

export interface User {
   uuid: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: string;
  emailNotification: boolean;
  favoriteCoins: Coin[];
  alerts: Alert[];
}
