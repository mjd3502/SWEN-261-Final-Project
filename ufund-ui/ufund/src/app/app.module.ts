import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';
import { NeedsDetailComponent } from './needs-detail/needs-detail.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HelperDashboardComponent,
    BrowseNeedsComponent,
    NeedsDetailComponent,
    CheckoutComponent,
    SearchBarComponent,
    AdminDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
