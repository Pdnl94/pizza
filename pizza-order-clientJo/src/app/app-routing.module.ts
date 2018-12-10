import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainPageComponent } from './main-page/main-page.component';
import { IncomeListComponent } from './income-list/income-list.component'
import { OutlaysListComponent } from './outlays-list/outlays-list.component'
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';
import { ProviderListComponent } from './provider-list/provider-list.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { IncomeEditComponent } from './income-edit/income-edit.component';
import { IncomeDeleteComponent } from './income-delete/income-delete.component';
import { OutlayEditComponent } from './outlay-edit/outlay-edit.component';
import { OutlayDeleteComponent } from './outlay-delete/outlay-delete.component';
import { ProviderEditComponent } from './provider-edit/provider-edit.component';
import { ProviderDeleteComponent } from './provider-delete/provider-delete.component';

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'incomes',
    component: IncomeListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'incomes/add',
    component: IncomeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'incomes/:id/edit',
    component: IncomeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'incomes/:id/delete',
    component: IncomeDeleteComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'outlays',
    component: OutlaysListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'outlays/add',
    component: OutlayEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'outlays/:id',
    component: OutlaysListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'outlays/:id/edit',
    component: OutlayEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'outlays/:id/delete',
    component: OutlayDeleteComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'providers',
    component: ProviderListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'providers/:id/edit',
    component: ProviderEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'providers/:id/delete',
    component: ProviderDeleteComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'providers/add',
    component: ProviderEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'categories',
    component: CategoryListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
