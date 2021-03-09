import { AppService } from './app.service';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { ConfigService } from './config.service';
import { takeUntil } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  protected ngUnsubscribe: Subject<void> = new Subject<void>();
  tableMessage = "Enter hostname and click search button to start using this service.";
  hostnameList:string[];
  disabledBtn:boolean = false;
  loadingP:boolean = false;
  eventListener;
  dnsApiUrl:string;
  errorMessage:string = "";

  @ViewChild('hostname') hostname;

  @ViewChild('mcc') mcc;
  @ViewChild('mnc') mnc;
  @ViewChild('lac') lac;
  @ViewChild('cellID') cellID;

  constructor(
    private configService: ConfigService,
    private appService: AppService
  ) { }
  

  ngOnInit() {
    this.dnsApiUrl = this.configService.dnsApiUrl;
    this.initListener();
  }
  
  startDNSQuery(ipAddress:string){
    this.hostnameList = [];
    this.errorMessage = "";
    this.tableMessage = "Enter hostname and click search button to start using this service.";

    if(ipAddress != null && ipAddress != ""){
      this.disabledBtn = true;
      this.loadingP = true;

      let link = this.dnsApiUrl+"?hostname="+ipAddress;

      this.appService.callBEApi(link).pipe(takeUntil(this.ngUnsubscribe)).subscribe((data) => {
        this.disabledBtn = false;
        this.loadingP = false;

        this.hostnameList = data.body.addresses;
        if(this.hostnameList.length > 0){
          this.tableMessage = "";
        } else {
          this.errorMessage = "Query returned empty result set!";
        }
      }, (error: HttpErrorResponse) => {
        this.loadingP = false;
        this.disabledBtn = false;

        console.log(error);
        this.errorMessage = "Error occured while retreiving ip address!"
        this.tableMessage = "";
      });
    } else {
      this.errorMessage = "Query returned empty result set!";
      this.tableMessage = "";
    }
  }

  clearDnsField(){
    this.hostname.nativeElement.value = '';
    
    this.tableMessage = "Enter hostname and click search button to start using this service.";
    this.errorMessage = "";
    this.hostnameList = [];
  }

  //============================================================================================

  lon:string;
  lat:string;
  mapsLink:string;
  
  cellImplMessage = "Enter required values in order to get cell tower location.";
  cellApiError = "";

  getCellTowerLocation(mcc:string, mnc:string, lac:string, cellID:string){
    this.lon = "";
    this.lat = "";
    this.mapsLink = "";
    this.cellApiError = "";
    this.cellImplMessage = "Enter required values in order to get cell tower location.";

    if(mcc != null && mcc != "" &&
        mnc != null && mnc != "" &&
        lac != null && lac != "" &&
        cellID != null && cellID != "") {
          this.disabledBtn = true;
          this.loadingP = true;
          let link = this.configService.cellTowerAPI + "?mcc="+mcc+"&mnc="+mnc+"&lac="+lac+"&cellid="+cellID;

          this.appService.callBEApi(link).pipe(takeUntil(this.ngUnsubscribe)).subscribe((data) => {
            this.disabledBtn = false;
            this.loadingP = false;
            
            console.log(data.body);
            if(data.body.lon != null && data.body.lon != "" && data.body.lat != null && data.body.lat != "") {
              this.cellImplMessage = "";
              
              this.lon = data.body.lon;
              this.lat = data.body.lat;
              this.mapsLink = "https://maps.google.com/maps?q="+this.lat+", "+this.lon+"&z=16&ie=UTF8&hl=sr&output=embed";
            } else {
              this.cellApiError = "No cell tower found!";
            }
          }, (error: HttpErrorResponse) => {
            this.loadingP = false;
            this.disabledBtn = false;
            this.cellImplMessage = "";
    
            console.log(error);
            this.cellApiError = "Error occured while locating cell tower!";
            if(error != null){
              if(error.error != null){
                if(error.error.error != null && error.error.error != ""){
                  this.cellApiError = error.error.error;
                }
              }
            }
          });
    } else {
      this.cellApiError = "No cell tower found!";
      this.cellImplMessage = "";
    }
  }

  clearCellIdQueryField(fieldName:string){
    if(fieldName === 'mcc')
      this.mcc.nativeElement.value = '';
    if(fieldName === 'mnc')
      this.mnc.nativeElement.value = '';
    if(fieldName === 'lac')
      this.lac.nativeElement.value = '';
    if(fieldName === 'cellID')
      this.cellID.nativeElement.value = '';

      this.mapsLink = "";
      this.lon = "";
      this.lat = "";
      this.cellApiError = "";
      this.cellImplMessage = "Enter required values in order to get cell tower location.";
  }

  getSafeUrl(){
    
  }

  //============================================================================================

  initListener() {
    this.eventListener = () => this.reset();

    document.body.addEventListener('click', this.eventListener);
    document.body.addEventListener('mouseover', this.eventListener);
    document.body.addEventListener('mouseout', this.eventListener);
    document.body.addEventListener('keydown', this.eventListener);
    document.body.addEventListener('keyup', this.eventListener);
    document.body.addEventListener('keypress', this.eventListener);
  }

  reset() { }

  ngOnDestroy(){
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();

    document.body.removeEventListener('click', this.eventListener);
    document.body.removeEventListener('mouseover',this.eventListener);
    document.body.removeEventListener('mouseout',this.eventListener);
    document.body.removeEventListener('keydown',this.eventListener);
    document.body.removeEventListener('keyup',this.eventListener);
    document.body.removeEventListener('keypress',this.eventListener);
  }
}
