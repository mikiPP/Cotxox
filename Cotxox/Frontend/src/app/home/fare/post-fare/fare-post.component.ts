import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FareService } from '../../../Shared/Fare.service';

@Component({
  selector: 'app-fare-post',
  templateUrl: './fare-post.component.html',
  styleUrls: ['./fare-post.component.css']
})
export class FarePostComponent implements OnInit {

  constructor(private fareService: FareService,
              private router: Router) { }

  ngOnInit() {
  }

  onFarePost(form: NgForm) {
    const { rate,  tip} = form.value;
    this.fareService.getFare().setRate(rate);
    this.fareService.getFare().setTip(tip);
    this.fareService.putFare();
    this.router.navigateByUrl('fare/ended');

  }
}
