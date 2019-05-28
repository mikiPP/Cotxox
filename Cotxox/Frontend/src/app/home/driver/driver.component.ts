import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { last } from 'rxjs/operators';
import { DriverService } from '../../Shared/driver.service';
import { DataService } from '../../Shared/Data.service';
import { Driver } from '../../Shared/Driver.model';

@Component({
  selector: 'app-conductores',
  templateUrl: './driver.component.html',
  styleUrls: ['./driver.component.css']
})
export class DriverComponent implements OnInit {
  private drivers: Driver[];


  constructor(private dataService: DataService,
              private router: Router,
              private driverService: DriverService) { }

  ngOnInit() {
    this.drivers = [];
    this.setDrivers();
  }

  setDrivers() {
    this.dataService.getAllDrivers()
      .pipe(last())
      .subscribe(
        (response: Promise<Driver[]>) => {
          // @ts-ignore
          for (const driverFetch of response) {
            const driver = this.driverService.createDriverFromJson(driverFetch);
            this.getDrivers().push(driver);
          }
        }
      );
  }

  getDrivers(): Driver[] {
    return this.drivers;
  }

  onDriver(id: number) {
    this.driverService.setDriver(id);
    this.router.navigateByUrl('drivers/' + id);
  }
}
