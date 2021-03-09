import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigService } from "./config.service";


@Injectable()
export class AppService{
    constructor(
        private httpclient: HttpClient
    ){};

    callBEApi(link:string){
        const options = this.generateOptions();
        return this.httpclient.get<any>(link, options);
    }

    public generateOptions(){
        const headers = new HttpHeaders()
            .set("Content-Type","application/json");

        const options = { headers: headers, observe:"response" as "response"};

        return options;
    }
}