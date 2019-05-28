export class Mileage {
  private mileage: number;
  private time: number;


  constructor(mileage: number, time: number) {
    this.mileage = mileage;
    this.time = time;
  }


  setMileage(value: number) {
    this.mileage = value;
  }

  setTime(value: number) {
    this.time = value;
  }


  getMileage(): number {
    return this.mileage;
  }

  getTime(): number {
    return this.time;
  }

}
