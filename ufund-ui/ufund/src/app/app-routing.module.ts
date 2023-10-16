import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowseNeedsComponent } from './browse-needs/browse-needs.component';

const routes: Routes = [
  {path: 'browse-needs', component: BrowseNeedsComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
