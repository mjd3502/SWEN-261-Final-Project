import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { LoginComponent } from './login/login.component';
import { NeedsDetailComponent } from './needs-detail/needs-detail.component';

import { NeedsDetailComponent } from './needs-detail/needs-detail.component';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [

  {path:'checkout',component:CheckoutComponent},
  {path: 'needsDetail/:id', component: NeedsDetailComponent },
  {path:'helperDashboard',component:HelperDashboardComponent},
  {path:'login',component:LoginComponent},
  {path: 'browse-needs', component: BrowseNeedsComponent}
  
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
