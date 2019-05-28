import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser/';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FareService } from './Shared/Fare.service';
import { DriverService } from './Shared/driver.service';
import { DataService } from './Shared/Data.service';
import { DropdownDirective } from './Shared/dropdown.directive';
import { UserService } from './Shared/user.service';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './home/header/header.component';
import { FeeComponent } from './home/fees/fee.component';
import { DriverComponent } from './home/driver/driver.component';
import { DriverDataComponent } from './home/driver/driver-Data/driver-Data.component';
import { FareComponent } from './home/fare/fare.component';
import { FarePostComponent } from './home/fare/post-fare/fare-post.component';
import { FareEndedComponent } from './home/fare/ended-fare/fare-ended.component';
import { FooterComponent } from './home/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    HeaderComponent,
    FeeComponent,
    DriverComponent,
    DriverDataComponent,
    FareComponent,
    DropdownDirective,
    FarePostComponent,
    FareEndedComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    FormsModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [DataService, UserService, DriverService, FareService],
  bootstrap: [AppComponent]
})
export class AppModule { }
