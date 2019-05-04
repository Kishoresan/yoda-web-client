import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  itemObj: any = {
    email: "test@gmail.com",
    password: "123456"
  }

  constructor(public toastController: ToastController, private router: Router) { }

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
      let msg = 'Invalid Email Or Password'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      if (this.itemObj.email == 'test@gmail.com' && this.itemObj.password == '123456') {
        let msg = 'Sign In Success'
        this.presentToast(msg, 'success');
        this.router.navigate(['tabs']);
      } else {
        let msg = 'Invalid Email Or Password'
        this.presentToast(msg, 'danger');
        return false;
      }
    }
  }

  gotoSignup() {
    this.router.navigate(['signup']);
  }

  isInvalid(field) {
    if (field == '' || field == null || field == undefined) {
      return true;
    }
    return false;
  }

}
