import { Component } from '@angular/core';
import { DriverService } from '../../../Shared/driver.service';
import { Driver } from '../../../Shared/Driver.model';


@Component({
  selector: 'app-driver-data',
  templateUrl: './driver-Data.component.html',
  styleUrls: ['./driver-Data.component.css']
})
export class DriverDataComponent  {
  driver: Driver;

  constructor(private driverService: DriverService) {
    this.driver = driverService.getConductor();
  }
}
