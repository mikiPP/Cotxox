import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FareService } from '../../Shared/Fare.service';
import { DriverService } from '../../Shared/driver.service';
import { UserService } from '../../Shared/user.service';
import {Fare} from '../../Shared/Fare';


@Component({
  selector: 'app-fare',
  templateUrl: './fare.component.html',
  styleUrls: ['./fare.component.css']
})
export class FareComponent implements OnInit {
  fare: Fare;

  constructor(private fareService: FareService,
              private driverService: DriverService,
              private router: Router,
              private userService: UserService) {

  }

  ngOnInit() {

  }

  onFare(form: NgForm) {
    const {origin, destination, creditCard, driver} = form.value;
    this.fare = new Fare(origin, destination, creditCard, driver, this.userService, this.driverService);
    this.fare.setMileage(this.fareService.getDestinations().get(destination).getMileage());
    this.fare.setTime(this.fareService.getDestinations().get(destination).getTime());
    this.fareService.addCarrera(this.fare);
    this.router.navigateByUrl('fare/post');
  }


}
