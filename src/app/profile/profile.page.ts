import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { FileChooser } from '@ionic-native/file-chooser/ngx';
import { CountryService } from '../country.service';
import { Country } from '../entity/Country';
import { DocumentService } from '../document.service';
import { PhoneService } from '../phone.service';
import { UserService } from '../user.service';
import { User } from '../entity/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

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

  ngOnInit() {

    this.countryService.getCountries()
      .subscribe(lists => {
        this.countryDrp  = lists;
    });

    this.documentService.getDocumentTypes()
      .subscribe(lists => {
        this.documentTypeDrp = lists;
    });

    this.phoneService.getPhoneTypes()
      .subscribe(lists => {
        this.phoneTypeDrp = lists;
    });
  }

  constructor(public toastController: ToastController, private fileChooser: FileChooser, private countryService: CountryService,
    private documentService: DocumentService, private phoneService: PhoneService, private userService: UserService) {
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
      let msg = 'Save Success';
      this.userService.saveUser(this.itemOneObj);
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
