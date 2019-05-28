import { Component,  OnInit } from '@angular/core';
import { DataService } from '../../Shared/Data.service';
import { Fee } from './fee.model';

@Component({
  selector: 'app-tarifas',
  templateUrl: './fee.component.html',
  styleUrls: ['./fee.component.css']
})
export class FeeComponent implements OnInit {
  private fees: Fee[];

  constructor(private dataStorage: DataService) { }

  ngOnInit() {
    this.fees = [];
    this.setTarifas();
    }

  setTarifas() {
    this.dataStorage.getAllFees()
    .subscribe(
      (response: Promise<Fee[]>) => {
        // @ts-ignore
        for (const feeFetched of response) {
          const fee = new Fee();
          fee.setCost(feeFetched['cost']);
          fee.setName(feeFetched['name']);
          this.fees.push(fee);
        }
      }
    );
  }

  getFees(): Fee[] {
    return this.fees;
  }

}
