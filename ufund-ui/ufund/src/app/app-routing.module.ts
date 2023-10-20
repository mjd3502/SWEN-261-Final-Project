import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CheckoutComponent } from './checkout/checkout.component';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'checkout',component:CheckoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
