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
import { BrowsePetsComponent } from './browse-pets/browse-pets.component';
import { PetDetailComponent } from './pet-detail/pet-detail.component';
import { AddPetComponent } from './add-pet/add-pet.component';
import { UpdatePetComponent } from './update-pet/update-pet.component';
import { HelperPetPageComponent } from './helper-pet-page/helper-pet-page.component';
import { AdminPetPageComponent } from './admin-pet-page/admin-pet-page.component';
import { AdminBrowsePetsComponent } from './admin-browse-pets/admin-browse-pets.component';
import { FavoritePetsComponent } from './favorite-pets/favorite-pets.component';
import { FavoritePetsPageComponent } from './favorite-pets-page/favorite-pets-page.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
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
  {path: 'browse-pets', component: BrowsePetsComponent},
  {path: 'pet-detail/:id', component: PetDetailComponent},
  {path: 'admin-create-pet', component: AddPetComponent},
  {path:'updatePet/:id',component:UpdatePetComponent},
  {path:'helperPetPage',component:HelperPetPageComponent},
  {path:'adminPetPage',component:AdminPetPageComponent},
  {path: 'admin-browse-pets', component: AdminBrowsePetsComponent},
  {path: 'favorite-pets', component: FavoritePetsComponent},
  {path: 'favorite-pets-page', component: FavoritePetsPageComponent},
  {path: 'upload/:type/:id',component: FileUploadComponent},
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
