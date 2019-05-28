export class Driver {
  private id: number;
  private name: string;
  private model: string;
  private carPlate: string;
  private rate: number;
  private bussy: boolean;

  constructor() {}

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getModel(): string {
    return this.model;
  }

  getCarPlate(): string {
    return this.carPlate;
  }

  getRate(): number {
    return this.rate;
  }

  getBussy(): boolean {
    return this.bussy;
  }

  setName(value: string) {
    this.name = value;
  }

  setModel(value: string) {
    this.model = value;
  }

  setCarPlate(value: string) {
    this.carPlate = value;
  }

  setRate(value: number) {
    this.rate = value;
  }

  setBussy(value: boolean) {
    this.bussy = value;
  }
  setId(value: number) {
    this.id = value;
  }
  
}
