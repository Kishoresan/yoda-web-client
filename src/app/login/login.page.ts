import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

import { AmplifyAngularModule, AmplifyIonicModule, AmplifyService } from 'aws-amplify-angular';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(public amplifyService: AmplifyService, public router: Router) {
    this.amplifyService = amplifyService;
    this.amplifyService.authStateChange$
      .subscribe(authState => {
        console.log('state ' + authState.state);
        if (authState.state === 'signedIn') {
          console.log('state 2' + authState.state);
          this.router.navigate(['profile']);
        }
      });
}
  ngOnInit() {
  }

}
