import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';
import { NeedsDetailComponent } from './needs-detail/needs-detail.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AddNeedComponent } from './add-need/add-need.component';
import { UpdateNeedComponent } from './update-need/update-need.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminBrowseNeedsComponent } from './admin-browse-needs/admin-browse-needs.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { FileUploadComponent } from './file-upload/file-upload.component';

import { SignupComponent } from './signup/signup.component';

import { CheckoutComponent } from './checkout/checkout.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LandingPageNavbarComponent } from './landing-page-navbar/landing-page-navbar.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HelperDashboardComponent,
    BrowseNeedsComponent,
    NeedsDetailComponent,
    FundingBasketComponent,
    SearchBarComponent,
    UpdateNeedComponent,
    AdminDashboardComponent,
    AdminBrowseNeedsComponent,
    AddNeedComponent,
    NavbarComponent,
    AdminNavbarComponent,
    FileUploadComponent,
    SignupComponent,
    CheckoutComponent,
    LandingPageComponent,
    HowItWorksComponent,
    AboutComponent,
    ContactComponent,
    LandingPageNavbarComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
