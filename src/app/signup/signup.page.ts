import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {

  itemObj: any = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    copassword: ""
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

  signup() {
    if (this.isInvalid(this.itemObj.firstName) ||
      this.isInvalid(this.itemObj.email) ||
      this.isInvalid(this.itemObj.password)) {
      let msg = 'Fill In the Required Information'
      this.presentToast(msg, 'danger');
      return false;
    } else if (this.itemObj.password.length < 6) {
      let msg = 'Password Length Must Be Grether Than Six Character';
      this.presentToast(msg, 'danger');
      return false;
    }
    else if (this.checkpwdStrength["type"] == 'Week') {
      let msg = 'Password Must be \nOne Lowercase\nOne Uppercase \nOne Number \nOne Special Character';
      this.presentToast(msg, 'danger');
      return false;
    }
    else if (this.itemObj.password != this.itemObj.copassword) {
      let msg = 'Password Must be Match'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      let msg = 'Sign Up Success'
      this.presentToast(msg, 'success');
      this.router.navigate(['home']);
    }
  }

  isInvalid(field) {
    if (field == '' || field == null || field == undefined) {
      return true;
    }
    return false;
  }
  gotosingin() {
    this.router.navigate(['login']);
  }

  checkpwdStrength = {
    'type': '',
    'margin-left': '12px'
  };

  validateEmail(email) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (reg.test(email) == false) {
      let msg = 'Invalid Email Address: '+ email
      this.presentToast(msg, 'danger');
      this.itemObj.email = "";
      return false;
    }
    return true;
  }

  checkPasswordStroness() {
    let strongRegularExp = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    let mediumRegularExp = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
    if (strongRegularExp.test(this.itemObj.password)) {
      this.checkpwdStrength["color"] = "green";
      this.checkpwdStrength["type"] = "Strong";
    } else if (mediumRegularExp.test(this.itemObj.password)) {
      this.checkpwdStrength["color"] = "orange";
      this.checkpwdStrength["type"] = "Medium";
    } else {
      this.checkpwdStrength["color"] = "red";
      this.checkpwdStrength["type"] = "Week";
    }
    if (this.itemObj.password == "" || this.itemObj.password == null) {
      this.checkpwdStrength["color"] = "";
      this.checkpwdStrength["type"] = "";
    }
  }
}
