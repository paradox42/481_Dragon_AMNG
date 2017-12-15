import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfileComponent} from './profile.component';
// import {SessionDetailComponent} from './session-detail/session-detail.component';
// import {SessionsComponent} from './sessions/sessions.component';

const routes: Routes = [
  {
    path: '',
    component: ProfileComponent,
    children: [
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule {}