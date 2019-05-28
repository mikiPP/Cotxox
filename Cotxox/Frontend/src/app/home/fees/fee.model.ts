export class Fee {
  private cost: number;
  private name: string;

  constructor() {
  }

  getCost(): number {
    return this.cost;
  }

  getName(): string {
    return this.name;
  }

  setCost(cost: number) {
    this.cost = cost;
  }

  setName(name: string) {
    this.name = name;
  }
}

