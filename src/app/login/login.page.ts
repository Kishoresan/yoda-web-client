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

  itemObj: any = {
    email: 'test@gmail.com',
    password: '123456'
  };

  constructor(public toastController: ToastController, private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  async presentToast(message, color) {
    const toast = await this.toastController.create({
      message: message,
      color: color,
      duration: 2000,
      position: 'top',
      showCloseButton: true
    });
    toast.present();
  }

  signin() {
    if (this.isInvalid(this.itemObj.email) ||
      this.isInvalid(this.itemObj.password)) {
      const msg = 'Invalid Email Or Password';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      if (this.authenticationService.authenticate(this.itemObj.email, this.itemObj.password)) {
        const msg = 'Sign In Success';
        this.presentToast(msg, 'success');
        this.router.navigate(['tabs']);
      } else {
        const msg = 'Invalid Email Or Password';
        this.presentToast(msg, 'danger');
        return false;
      }
    }
  }

  gotoSignup() {
    this.router.navigate(['signup']);
  }

  isInvalid(field) {
    if (field === '' || field == null || field === undefined) {
      return true;
    }
    return false;
  }

}
