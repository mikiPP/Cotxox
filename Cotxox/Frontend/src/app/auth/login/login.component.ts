import { Component, OnInit} from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../../Shared/Data.service';

import { UserService } from '../../Shared/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  tried: boolean;
  logged: boolean;

  constructor(private dataStorage: DataService
            , private userService: UserService
            , private router: Router) { }

  ngOnInit() {
    this.tried = false;
    this.logged = false;
  }

  onLogin(form: NgForm): boolean {
    const {username , password} = form.value;
    this.dataStorage.login(username, password)
      .subscribe(
        (response: string) => {
          if (response === 'Logged') {
            this.userService.setUser(this.userService.getUserByUsername(username));
            this.logged = true;
            this.router.navigateByUrl('/home');
            this.tried = false;
            return true;
          } else {
            this.tried = true;
            return false;
          }
        }
      );
    return false;
  }

}
