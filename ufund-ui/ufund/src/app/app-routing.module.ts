import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckoutComponent } from './checkout/checkout.component';
import { HelperDashboardComponent } from './helper-dashboard/helper-dashboard.component';
import { LoginComponent } from './login/login.component';
import { UpdateNeedComponent } from './update-need/update-need.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminBrowseNeedsComponent } from './admin-browse-needs/admin-browse-needs.component';

const routes: Routes = [
  {path:'checkout',component:CheckoutComponent},
  {path:'helperDashboard',component:HelperDashboardComponent},
  {path:'login',component:LoginComponent},
  {path:'updateNeed/:id',component:UpdateNeedComponent},
  {path:'adminDashboard',component:AdminDashboardComponent},
  {path:'admin-browse', component: AdminBrowseNeedsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
