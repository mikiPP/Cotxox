import { Injectable } from '@angular/core';
import { User } from './User.model';

@Injectable()
export class UserService {
  private users: Map<string, User>;
  user: User;

  constructor() {
    this.users = new Map<string, User>();

  }

  getUserByUsername(username: string): User {
    return this.getUsers().get(username);
  }

  add(user: User) {
    this.users.set(user.getUsername(), user);
  }

  getUsers(): Map<string, User> {
    return this.users;
  }

  createUserFromGetPetition(user: JSON): User {
    const newUser = new User();
    newUser.setId(user['id']);
    newUser.setName(user['name']);
    newUser.setUsername(user['username']);
    newUser.setEmail(user['email']);
    newUser.setPassword(user['password']);
    return newUser;
  }

  createUser(name, username, email, password): User {
    if (name !== null && username !== null && email !== null && password != null) {
      this.user = new User();
      this.user.setName(name);
      this.user.setUsername(username);
      this.user.setEmail(email);
      this.user.setPassword(password);
      this.add(this.user);
      return this.user;
    } else {
      return null;
    }
  }

  getUser(): User {
    return  this.user;
  }

  setUser(user: User) {
    this.user = user;
  }


}


