import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { User } from './User.model';
import { UserService } from './user.service';
import {Fare} from './Fare';

@Injectable()
export class DataService {



  constructor(private http: HttpClient
            , private userService: UserService) {
    this.getAllUsers();
  }

  signup(user: User) {
    console.log(user);
    return this.http.post('/cotxox/api/signup',
      {
        'name': user.getName(),
        'username': user.getUsername(),
        'email': user.getEmail(),
        'password': user.getPassword()
      },
      {responseType: 'text'});
  }

  login(username: string, password: string) {
    return this.http.post('cotxox/api/login',
      {
        "username": username,
        "password": password
    }, {responseType: 'text'});
  }


  getAllUsers() {
    return this.http.get('cotxox/api/all')
      .subscribe(
        (response: Promise<User[]>) => {
          // @ts-ignore
          for (const userFetched of response) {
            const user = this.userService.createUserFromGetPetition(userFetched);
            this.userService.add(user);
          }
        }
      );
  }


  getAllFees() {
    return this.http.get('cotxox/api/fee');
  }

  getAllDrivers() {
    return this.http.get('cotxox/api/driver');
  }

  getDriverById(id: number) {
    return this.http.get('cotxox/api/driver/' + id);
  }

  getActiveDrivers() {
    return this.http.get('cotxox/api/driver/active');
  }

  addFare(fare: Fare) {
    return this.http.post('/cotxox/api/fare',
      {
        "userId" : fare.getUser().getId(),
        "driverId" : fare.getDriver().getId(),
        "creditCard" : fare.getCreditCard(),
        "origin" : fare.getOrigin(),
        "destination" : fare.getDestination(),
        "mileage": fare.getMileage(),
        "time" : fare.getTime()
      }
      );
  }

  putFare(fare: Fare) {
    console.log(fare);
    return this.http.put('/cotxox/api/fare',
      {
        "id" : fare.getId(),
        "userId" : fare.getUser().getId(),
        "driverId" : fare.getDriver().getId(),
        "creditCard" : fare.getCreditCard(),
        "origin" : fare.getOrigin(),
        "destination" : fare.getDestination(),
        "mileage": fare.getMileage(),
        "time" : fare.getTime(),
        "tip" : fare.getTip(),
        "rate" : fare.getRate()
      }
    );
  }
}
