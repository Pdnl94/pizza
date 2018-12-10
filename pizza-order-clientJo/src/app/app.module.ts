import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './main-page/main-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import {
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatListModule,
  MatOptionModule,
  MatSelectModule
} from '@angular/material';
import { RouterModule } from '@angular/router';
import { OutlaysListComponent } from './outlays-list/outlays-list.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { ProviderListComponent } from './provider-list/provider-list.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { IncomeListComponent } from './income-list/income-list.component';
import { IncomeEditComponent } from './income-edit/income-edit.component';
import { IncomeDeleteComponent } from './income-delete/income-delete.component';
import { IncomeFormComponent } from './income-form/income-form.component';
import { OutlayEditComponent } from './outlay-edit/outlay-edit.component';
import { OutlayFormComponent } from './outlay-form/outlay-form.component';
import { OutlayDeleteComponent } from './outlay-delete/outlay-delete.component';
import { ProviderEditComponent } from './provider-edit/provider-edit.component';
import { ProviderFormComponent } from './provider-form/provider-form.component';
import { ProviderDeleteComponent } from './provider-delete/provider-delete.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    OutlaysListComponent,
    RegistrationComponent,
    LoginComponent,
    ProviderListComponent,
    CategoryListComponent,
    IncomeListComponent,
    IncomeEditComponent,
    IncomeDeleteComponent,
    IncomeFormComponent,
    OutlayEditComponent,
    OutlayFormComponent,
    OutlayDeleteComponent,
    ProviderEditComponent,
    ProviderFormComponent,
    ProviderDeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
