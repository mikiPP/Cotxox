import { Driver } from './Driver.model';
import { DriverService } from './driver.service';
import { User } from './User.model';
import {UserService} from './user.service';

export class Fare {
  private id: number;
  private origin: string;
  private destination: string;
  private mileage: number;
  private creditCard: string;
  private cost: number;
  private driver: Driver;
  private user: User;
  private time: number;
  private rate: number;
  private tip: number;

  constructor(origin: string, destination: string, creditCard: string, driver: string,  private userService: UserService, private driverService: DriverService) {
    this.origin = origin;
    this.destination = destination;
    this.creditCard = creditCard;
    this.driver = driverService.getDriverByName(driver);
    this.user = this.userService.getUser();
  }

  getId(): number {
    return this.id;
  }

  getTip(): number {
    return this.tip;
  }

  getCreditCard(): string {
    return this.creditCard;
  }

  getOrigin(): string {
    return this.origin;
  }

  getDestination(): string {
    return this.destination;
  }

  getMileage(): number {
    return this.mileage;
  }

  getCost(): number {
    return this.cost;
  }

  getDriver(): Driver {
    return this.driver;
  }

  getUser(): User {
    return this.user;
  }

  getTime(): number {
    return this.time;
  }

  getRate(): number {
    return this.rate;
  }

  setOrigin(value: string) {
    this.origin = value;
  }

  setDestination(value: string) {
    this.destination = value;
  }

  setMileage(value: number) {
    this.mileage = value;
  }

  setDriver(value: Driver) {
    this.driver = value;
  }

  setUser(value: User) {
    this.user = value;
  }

  setCost(value: number) {
    this.cost = value;
  }

  setTime(value: number) {
    this.time = value;
  }

  setRate(value: number) {
    this.rate = value;
  }

  setCreditCard(value: string) {
    this.creditCard = value;
  }

  setId(value: number) {
    this.id = value;
  }

  setTip(value: number) {
    this.tip = value;
  }
}
