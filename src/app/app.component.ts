import { Component, OnInit } from '@angular/core';
import { Auth } from 'aws-amplify';
import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { UserService } from '../app/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit {

  userEmail: string;
  userFirstName: string;
  isUserLoggedIn: boolean;

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private userService: UserService,
    private router: Router
  ) {
    this.initializeApp();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  ngOnInit() {

    Auth.currentAuthenticatedUser({
      bypassCache: false
    }).then(async user => {
      this.userEmail = user.attributes.email;
      this.isUserLoggedIn = true;
    }).catch(err => console.log(err));

    this.userService.find()
      .then(obs => obs.subscribe(user => this.userFirstName = user.firstName));
  }

  logOut() {
    Auth.signOut({ global: true })
    .then(data => {
      this.router.navigate(['/login']);
    })
    .catch(err => console.log(err));
  }

  public getIsUserLoggedIn(): boolean {
    return this.isUserLoggedIn;
  }

  public getUserHeader(): string {
    if(this.isUserLoggedIn){
      return this.userFirstName ? this.userFirstName : this.userEmail;
    }
    return "";   
  }
}
