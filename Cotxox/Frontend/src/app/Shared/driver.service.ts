import { Injectable } from '@angular/core';
import { Driver } from './Driver.model';
import { DataService } from './Data.service';

@Injectable()
export class DriverService {
  private driver: Driver;
  private activeDrivers: Driver[];

  constructor(private dataService: DataService) {
    this.activeDrivers = [];
    this.setActiveDrivers();
  }


  createDriverFromJson(driverFetch: JSON): Driver {
    const driver = new Driver();
    driver.setId(driverFetch['id']);
    driver.setName(driverFetch['name']);
    driver.setModel(driverFetch['model']);
    driver.setCarPlate(driverFetch['carPlate']);
    driver.setRate(driverFetch['rate']);
    driver.setBussy(driverFetch['bussy']);
    return driver;
  }

  getConductor(): Driver {
    return this.driver;
  }

  setDriver(id: number) {

    this.dataService.getDriverById(id)
      .subscribe(
      (response: Response) => {
        // @ts-ignore
        this.driver  = this.createDriverFromJson(response);
      }
    );
  }
  setActiveDrivers() {
    this.dataService.getActiveDrivers()
      .subscribe(
        (response: Promise<Driver[]>) => {
          // @ts-ignore
          for (const activeDriver of response) {
            const driver = this.createDriverFromJson(activeDriver);
            this.getActiveDrivers().push(driver);
          }
        }
      );
  }

  getActiveDrivers(): Driver[] {
    return this.activeDrivers;
  }

  getDriverByName(name: string): Driver {

    for (const driver of this.getActiveDrivers()) {
        if (driver.getName() === name) {
          return driver;
        }
    }
    return null;
  }
}
