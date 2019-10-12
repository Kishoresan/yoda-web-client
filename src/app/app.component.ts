import { Component, OnInit } from '@angular/core';
import { Auth } from 'aws-amplify';
import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { UserService } from '../app/user.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit {

  userEmail: string;
  userFirstName: string;

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private userService: UserService
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
    }).catch(err => console.log(err));

    this.userService.find()
      .then(obs => obs.subscribe(user => this.userFirstName = user.firstName));
  }

  getUserHeader() {
    if(this.userFirstName){
      return this.userFirstName;
    }
    if(this.userEmail){
      return this.userEmail;
    }
    return "";   
  }
}
