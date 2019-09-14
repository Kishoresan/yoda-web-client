import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { LoginPageModule } from './login/login.module';
import { AuthGuard } from './auth.guard';
import { ProfilePageModule } from './profile/profile.module';

const routes: Routes = [
  { path: 'login', loadChildren: () => LoginPageModule },
  { path: 'tabs', loadChildren: './tabs/tabs.module#TabsPageModule' },
  { path: 'profile', loadChildren: () => ProfilePageModule, canActivate: [AuthGuard] },
  { path: 'new-request', loadChildren: './new-request/new-request.module#NewRequestPageModule' },
  { path: 'settings', loadChildren: './settings/settings.module#SettingsPageModule' },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
