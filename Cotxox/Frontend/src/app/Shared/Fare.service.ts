import { Injectable } from '@angular/core';
import { DataService } from './Data.service';
import { Mileage } from './Mileage.model';
import {Fare} from './Fare';

@Injectable()
export class FareService {
  fare: Fare;
  private destinations: Map<string, Mileage>;

  constructor(private data: DataService) {
    this.destinations = new Map<string, Mileage>();
    this.setDestinations();
  }

  addCarrera(fare: Fare) {
    this.fare = fare;
    this.data.addFare(fare)
      .subscribe(
        (response: Response) => {
          // @ts-ignore
          this.setFare(response);
        }
      );
  }

  setFare(fare: JSON) {
    this.fare.setId(fare['id']);
    this.fare.setCost(fare['cost']);
  }

  getFare(): Fare {
    return this.fare;
  }

  putFare() {

    return this.data.putFare(this.getFare())
      .subscribe(
        (response: Response) => {

        }
      );
  }

  getDestinations(): Map<string, Mileage> {
    return this.destinations;
  }

  setDestinations() {
    this.getDestinations().set('LLucmajor', new Mileage(29.9, 31));
    this.getDestinations().set('Sa Ràpita', new Mileage(44.5, 44));
    this.getDestinations().set('Santa Ponsa', new Mileage(22, 28));
    this.getDestinations().set('Andratx', new Mileage(28.7, 33));
    this.getDestinations().set('Calvia', new Mileage(19, 27));
    this.getDestinations().set('Pollença', new Mileage(58.1, 50));
  }

}
