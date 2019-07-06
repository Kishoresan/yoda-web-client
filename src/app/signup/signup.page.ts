import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';
import { NavController, NavParams, AlertController } from '@ionic/angular';
import { CognitoService } from '../cognito.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {

  email: string;
  password: string;

  ngOnInit() {}

  constructor(
    public navCtrl: NavController,
    public alertController: AlertController,
    public cognitoService: CognitoService,
    public router: Router
  ) {}

  register() {
    this.cognitoService.signUp(this.email, this.password).then(
      res => {
        this.router.navigate(['profile']);
        // this.promptVerificationCode();
      },
      err => {
        console.log(err);
      }
    );
  }

  async promptVerificationCode() {
    let alert =  await this.alertController.create({
      header: 'Enter Verfication Code',
      inputs: [
        {
          name: "VerificationCode",
          placeholder: "Verification Code"
        }
      ],
      buttons: [
        {
          text: "Cancel",
          role: "cancel",
          handler: data => {
            console.log("Cancel clicked");
          }
        },
        {
          text: "Verify",
          handler: data => {
            this.verifyUser(data.VerificationCode);
          }
        }
      ]
    });
    alert.present();
  }

  verifyUser(verificationCode) {
    this.cognitoService.confirmUser(verificationCode, this.email).then(
      res => {
        this.router.navigate(['profile']);
      },
      err => {
        alert(err.message);
      }
    );
  }
}
