import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeedsDetailComponent } from './needs-detail/needs-detail.component';

const routes: Routes = [
  { path: 'needsDetail', component: NeedsDetailComponent },
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
