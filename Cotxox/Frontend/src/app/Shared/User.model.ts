export class User {
  private id: number;
  private name: string;
  private username: string;
  private email: string;
  private password: string;
  private active: boolean;




  constructor() {

  }

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getUsername(): string {
    return this.username;
  }

  getEmail(): string {
    return this.email;
  }

  getPassword(): string {
    return this.password;
  }

  getActive(): boolean {
    return this.active;
  }

  setActive(active: boolean) {
    this.active = active;
  }

  setId(id: number) {
    this.id = id;
  }

  setPassword(value: string) {
    this.password = value;
  }

  setName(value: string) {
    this.name = value;
  }

  setUsername(value: string) {
    this.username = value;
  }

  setEmail(value: string) {
    this.email = value;
  }
}
