import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { LoginComponent } from './login/login.component';
import { UpdateNeedComponent } from './update-need/update-need.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminBrowseNeedsComponent } from './admin-browse-needs/admin-browse-needs.component';
import { AddNeedComponent } from './add-need/add-need.component';
import { NeedsDetailComponent } from './needs-detail/needs-detail.component';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';
import { SignupComponent } from './signup/signup.component';

import { CheckoutComponent } from './checkout/checkout.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AboutComponent } from './about/about.component';


const routes: Routes = [
  {path:'funding-basket',component:FundingBasketComponent},
  {path:'helperDashboard',component:HelperDashboardComponent},
  {path:'login',component:LoginComponent},
  {path:'updateNeed/:id',component:UpdateNeedComponent},
  {path:'adminDashboard',component:AdminDashboardComponent},
  {path:'admin-browse', component: AdminBrowseNeedsComponent},
  {path:'admin-create-need', component: AddNeedComponent},
  {path: 'detail/:id', component: NeedsDetailComponent },
  {path: 'browse-needs', component: BrowseNeedsComponent},
  {path: 'signup', component: SignupComponent},
  {path:"checkout",component:CheckoutComponent},
  {path:"home",component:LandingPageComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path:"about",component:AboutComponent},
  {path:"contact",component:AboutComponent},
  {path:"howItWorks",component:AboutComponent},
];


@NgModule({
imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
