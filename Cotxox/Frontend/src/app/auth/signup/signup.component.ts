import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../../Shared/Data.service';
import { UserService } from '../../Shared/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  tried: boolean;

  constructor(private dataStorage: DataService, private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.tried = false;
  }

  onSingup(form: NgForm): boolean {
    const {name, username , email, password} = form.value;
    this.dataStorage.signup(this.userService.createUser(name, username, email, password))
      .subscribe(
        (response: string) => {
          if (response === "Added") {
            this.router.navigateByUrl('/login');
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
