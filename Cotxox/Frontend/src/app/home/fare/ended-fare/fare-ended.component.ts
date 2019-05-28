import { Component, OnInit } from '@angular/core';
import { FareService } from '../../../Shared/Fare.service';

@Component({
  selector: 'app-fare-ended',
  templateUrl: './fare-ended.component.html',
  styleUrls: ['./fare-ended.component.css']
})
export class FareEndedComponent implements OnInit {

  constructor(private carreraService: FareService) { }

  ngOnInit() {
  }

}
