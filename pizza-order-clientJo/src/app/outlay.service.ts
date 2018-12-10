import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Outlay } from './outlay';
import { httpOptions } from './auth.service';
import { Provider } from './provider';

@Injectable({
  providedIn: 'root'
})
export class OutlayService {

  private outlayURL = "http://localhost:8080/outlays"
  private providerURL = "http://localhost:8080/providers"
  private categoriesURL = "http://localhost:8080/categories"

  constructor(
    private http: HttpClient
  ) { }

  getOutlays(): Promise<Outlay[]> {
    return this.http.get<Outlay[]>(
      this.outlayURL,
      httpOptions
    ).toPromise();
  }

  getOutlay(id: number): Promise<Outlay> {
    return this.http.get<Outlay>(
      `${this.outlayURL}/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyOutlay(id: number, outlay: Outlay): Promise<Outlay> {
    return this.http.put<Outlay>(
      `${this.outlayURL}/${id}`,
      outlay,
      httpOptions
    ).toPromise();
  }

  addOutlay(outlay: Outlay): Promise<Outlay> {
    return this.http.post<Outlay>(
      this.outlayURL,
      outlay,
      httpOptions
    ).toPromise();
  }

  deleteOutlay(id: number): Promise<Object> {
    return this.http.delete(
      `${this.outlayURL}/${id}`,
      httpOptions
    ).toPromise();
  }

  getProviders(): Promise<Provider[]> {
    return this.http.get<Provider[]>(
      this.providerURL,
      httpOptions
    ).toPromise();
  }

  getProvider(id: number): Promise<Provider> {
    return this.http.get<Provider>(
      `${this.providerURL}/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyProvider(id: number, provider: Provider): Promise<Provider> {
    return this.http.put<Provider>(
      `${this.providerURL}/${id}`,
      provider,
      httpOptions
    ).toPromise();
  }

  addProvider(provider: Provider): Promise<Provider> {
    return this.http.post<Provider>(
      this.providerURL,
      provider,
      httpOptions
    ).toPromise();
  }

  deleteProvider(id: number): Promise<Object> {
    return this.http.delete(
      `${this.providerURL}/${id}`,
      httpOptions
    ).toPromise();
  }
}
