import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { FileChooser } from '@ionic-native/file-chooser/ngx';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage {

  countryDrp: any = [];
  documentTypeDrp: any = [];
  phoneTypeDrp: any = [];

  itemOneObj: any = {
    "id": "",
    "email": "",
    "firstName": "",
    "middleName": "",
    "lastName": "",
    "dateOfBirth": "",
    "nationality": ""
  }

  itemTwoObj: any = {
    "id": "",
    "documentId": "",
    "file": ""
  }

  itemThreeObj: any = {
    "id": "",
    "userId": "",
    "houseNumber": "",
    "street": "",
    "city": "",
    "zipCode": "",
    "state": "",
    "countryCode": ""
  }

  itemFourObj: any = {
    "id": "",
    "userId": "",
    "phoneType": "",
    "countryCode": "",
    "number": ""
  }

  isEditOne: any = false;
  isEditTwo: any = false;
  isEditThree: any = false;
  isEditFour: any = false;

  constructor(public toastController: ToastController, private fileChooser: FileChooser) {
    this.countryDrp = [
      {
        "code": '1', "name": "India", "phoneCode": "91"
      },
      {
        "code": '2', "name": "USA", "phoneCode": "92"
      },
      {
        "code": '3', "name": "UK", "phoneCode": "93"
      }
    ]
    this.documentTypeDrp = [
      {
        "code": "1", "description": "Aadhaar card"
      },
      {
        "code": "2", "description": "Passport"
      },
      {
        "code": "3", "description": "Driving licence"
      },
      {
        "code": "4", "description": "Birth certificate"
      },
      {
        "code": "5", "description": "Pan card"
      }
    ]
  }
  stepOneSubmit() {
    if (this.isInvalid(this.itemOneObj.firstName) ||
      this.isInvalid(this.itemOneObj.lastName) ||
      this.isInvalid(this.itemOneObj.middleName) ||
      this.isInvalid(this.itemOneObj.dateOfBirth)) {
      let msg = 'Fill In the Required Information.'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      let msg = 'Save Success'
      this.presentToast(msg, 'success');
      this.isEditOne = false;
    }
  }
  stepTwoSubmit() {
    if (this.isInvalid(this.itemOneObj.documentId)) {
      let msg = 'Fill In the Required Information.'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      let msg = 'Save Success.'
      this.presentToast(msg, 'success');
      this.isEditTwo = false;
    }
  }
  stepThreeSubmit() {
    if (this.isInvalid(this.itemThreeObj.houseNumber) ||
      this.isInvalid(this.itemThreeObj.street) ||
      this.isInvalid(this.itemThreeObj.city) ||
      this.isInvalid(this.itemThreeObj.zipCode) ||
      this.isInvalid(this.itemThreeObj.state) ||
      this.isInvalid(this.itemThreeObj.countryCode)) {
      let msg = 'Fill In the Required Information.'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      let msg = 'Save Success'
      this.presentToast(msg, 'success');
      this.isEditThree = false;
    }
  }
  stepFourSubmit() {
    if (this.isInvalid(this.itemOneObj.home_phone_number) ||
      this.isInvalid(this.itemOneObj.mobile_number) ||
      this.isInvalid(this.itemOneObj.office_phone_number)) {
      let msg = 'Fill In the Required Information.'
      this.presentToast(msg, 'danger');
      return false;
    } else {
      let msg = 'Save Success.'
      this.presentToast(msg, 'success');
      this.isEditFour = false;
    }
  }
  chooseFile() {
    this.fileChooser.open().then(uri => {
      console.log(uri)
    }).catch(e => {
      console.log(e)
    });
  }
  isEdit(type) {
    if (type == 1) {
      this.isEditOne = !this.isEditOne;
    } else if (type == 2) {
      this.isEditTwo = !this.isEditTwo;
    } else if (type == 3) {
      this.isEditThree = !this.isEditThree;
    } else {
      this.isEditFour = !this.isEditFour;
    }
  }
  isInvalid(field) {
    if (field == '' || field == null || field == undefined) {
      return true;
    }
    return false;
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
}
