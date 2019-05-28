import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FareEndedComponent } from './home/fare/ended-fare/fare-ended.component';
import { FarePostComponent } from './home/fare/post-fare/fare-post.component';
import { FareComponent } from './home/fare/fare.component';
import { DriverDataComponent } from './home/driver/driver-Data/driver-Data.component';
import { DriverComponent } from './home/driver/driver.component';
import { HomeComponent } from './home/home.component';
import { FeeComponent } from './home/fees/fee.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: 'fees', component: FeeComponent },
  {    path: 'drivers', children: [
      { path: ':id', component: DriverDataComponent },
      { path: '', component: DriverComponent }
    ]
  },
  { path: 'fare', children: [
      { path: 'post', component: FarePostComponent },
      { path: 'ended', component: FareEndedComponent },
      { path: '', component: FareComponent }
    ]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
