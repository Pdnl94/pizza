import { Injectable } from '@angular/core';
import { Income } from './income';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { httpOptions } from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private incomeURL = "http://localhost:8080/incomes"

  constructor(
    private http: HttpClient
  ) { }

  getIncomes(): Promise<Income[]> {
    return this.http.get<Income[]>(
      this.incomeURL,
      httpOptions
    ).toPromise();
  }

  getIncome(id: number): Promise<Income> {
    return this.http.get<Income>(
      `${this.incomeURL}/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyIncome(id: number, income: Income): Promise<Income> {
    return this.http.put<Income>(
      `${this.incomeURL}/${id}`,
      income,
      httpOptions
    ).toPromise();
  }

  addIncome(income: Income): Promise<Income> {
    return this.http.post<Income>(
      this.incomeURL,
      income,
      httpOptions
    ).toPromise();
  }

  deleteIncome(id: number): Promise<Object> {
    return this.http.delete(
      `${this.incomeURL}/${id}`,
      httpOptions
    ).toPromise();
  }
}
