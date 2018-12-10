import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';

export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': '',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  isAdmin = false;
  user: User;
  redirectUrl: string;

  private usersUrl = 'http://localhost:8080/users';

  constructor(
    private http: HttpClient
  ) { }

  async login(username: string, password: string): Promise<boolean> {
    const token = btoa(`${username}:${password}`);
    httpOptions.headers =
      httpOptions.headers.set(
        'Authorization',
        `Basic ${token}`
      );
    try {
      const user = await this.http.post<User>(
        `${this.usersUrl}/login`,
        {},
        httpOptions
      ).toPromise();

      this.isLoggedIn = true;
      this.user = user;
      this.isAdmin = user.role == 'ROLE_ADMIN';
      return Promise.resolve(true);
    } catch (e) {
      console.log('hiba', e);
      return Promise.resolve(false);
    }
  }

  async register(username: string, password: string, email: string): Promise<Boolean> {
    const newUser = this.setupUserForRegistration(username, password, email);
    httpOptions.headers.set(
      'Authorization', null,
    )
    try{
       const response = await this.http.post<User> (
       `${this.usersUrl}/register`,
       newUser,
       httpOptions
       ).subscribe(
        res => {},
        err => {
            console.log(err.status); 
            return false;
        });

      return Promise.resolve(false);
   } catch (e) {
      console.log('hiba', e);
      return Promise.resolve(false);
  }
}

  logout() {
    this.isLoggedIn = false;
    this.isAdmin = false;
    this.user = null;
    this.redirectUrl = null;
  }

  async getBalance() {
    this.user.balance = await this.http.get<number>(
      `${this.usersUrl}/balance`,
      httpOptions
    ).toPromise();
  }

  setupUserForRegistration(username: string, password: string, email: string): User {
      const userToRegister = new User();
      userToRegister.username = username;
      userToRegister.password = password;
      userToRegister.email = email;

      return userToRegister;
  }
}
