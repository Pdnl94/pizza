import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
//import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { delay } from 'q';

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  message: string;
  hidePassword = true;
  form = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
  });

  get username() { return this.form.get('username'); }
  get password() { return this.form.get('password'); }
  get email() { return this.form.get('email'); }
  

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onSubmit() {
    this.message = "Registering with: " + this.username.value + ", " + this.email.value + ", " + this.password.value;
    const success = this.sendRegistrationRequest();
    if (!success) {
      this.message = "Could not register!"
      console.log("NOP")
    } else {
      console.log("YEP")
      const url = this.authService.redirectUrl
        ? this.authService.redirectUrl
        : '/login';
      this.router.navigate([url]) 
    }
  }

  async sendRegistrationRequest(): Promise<Boolean> {
    return await this.authService.register(
      this.username.value, 
      this.password.value, 
      this.email.value);
  }
}
