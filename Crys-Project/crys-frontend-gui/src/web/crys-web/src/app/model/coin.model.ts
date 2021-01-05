export interface Coin {
  id: string;
  name: string;
  rank: number;
  symbol: string;
  logoURL: string;
  supply: number;
  maxSupply: number;
  marketCapUsd: number;
  volumeUsd24hr: number;
  priceUsd: number;  //  USD
  changePercentage24hr: number;
  vwap24hr: number;
}
