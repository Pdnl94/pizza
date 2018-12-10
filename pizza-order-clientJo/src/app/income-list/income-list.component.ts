import { Component, OnInit } from '@angular/core';
import { IncomeService } from '../income.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'income-list',
  templateUrl: './income-list.component.html',
  styleUrls: ['./income-list.component.css']
})
export class IncomeListComponent implements OnInit {

  incomes = []

  constructor(
    private incomeService: IncomeService,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    this.incomes = await this.incomeService.getIncomes();
    this.authService.getBalance();
  }

}
