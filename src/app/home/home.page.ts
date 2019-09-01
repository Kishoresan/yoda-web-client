import { Component } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(public toastController: ToastController, private authenticationService: AuthenticationService) { }

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

  getLoggedInUser() {
    return this.authenticationService.getLoggedInUser();
  }

  logout() {
    this.authenticationService.logOut();
  }

  isInvalid(field) {
    if (field === '' || field == null || field === undefined) {
      return true;
    }
    return false;
  }

}
