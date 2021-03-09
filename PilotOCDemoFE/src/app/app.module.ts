import { AppService } from './app.service';
import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ConfigService } from './config.service';
import { SafePipe } from './safe.pipe';

@NgModule({
  declarations: [
    AppComponent,
    SafePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AppService, {
    provide: APP_INITIALIZER,
    useFactory: (appConfigService: ConfigService) => {
    return () => {
       return appConfigService.loadAppConfig();
      };
  },
  deps: [ConfigService],
  multi: true
  }],
    bootstrap: [AppComponent]
  })
  export class AppModule { }
