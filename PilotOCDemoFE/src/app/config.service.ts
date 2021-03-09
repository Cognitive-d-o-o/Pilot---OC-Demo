import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
  export class ConfigService {
  private appConfig: any;
  
  constructor(private http: HttpClient) {}
  
  loadAppConfig() {
    return this.http.get('./assets/config/appconfig.json')
      .toPromise()
      .then(config => {
        this.appConfig = config;
      });
  }
  
  get dnsApiUrl() {
      if (!this.appConfig) {
        throw Error('Config file not loaded!');
      }
      return this.appConfig.dnsApiUrl;
    }

  get cellTowerAPI() {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }
    return this.appConfig.cellTowerAPI;
  }
}