import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { FileChooser } from '@ionic-native/file-chooser/ngx';
import { CountryService } from '../country.service';
import { Country } from '../entity/Country';
import { DocumentService } from '../document.service';
import { PhoneService } from '../phone.service';
import { UserService } from '../user.service';
import { User } from '../entity/User';
import { AuthenticationService } from '../authentication.service';

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

  userId: any ;

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


    this.userService.getUserByEmail(this.getLoggedInUser())
      .subscribe(user => {
        this.itemOneObj.firstName = user.firstName;
        this.itemOneObj.middleName = user.middleName;
        this.itemOneObj.lastName = user.lastName;
        this.itemOneObj.dateOfBirth = user.dateOfBirth;
        this.itemOneObj.nationality = user.nationality;
        this.itemOneObj.id = user.id;
        this.itemOneObj.email = user.email;
      });

    this.userService.getUserAddress(this.getLoggedInUser())
      .subscribe(address => {
        this.itemThreeObj.id = address.id;
        this.itemThreeObj.userId = this.userId;
        this.itemThreeObj.houseNumber = address.houseNumber;
        this.itemThreeObj.street = address.street;
        this.itemThreeObj.city = address.city;
        this.itemThreeObj.zipCode = address.zipcode;
        this.itemThreeObj.state = address.state;
        this.itemThreeObj.countryCode = address.countryCode;
      });
  }

  constructor(public toastController: ToastController, private fileChooser: FileChooser, private countryService: CountryService,
    private documentService: DocumentService, private phoneService: PhoneService, private userService: UserService,
    private authenticationService: AuthenticationService) {
  }
  updateBasicInfo() {
    if (this.isInvalid(this.itemOneObj.firstName) ||
      this.isInvalid(this.itemOneObj.lastName) ||
      this.isInvalid(this.itemOneObj.middleName) ||
      this.isInvalid(this.itemOneObj.dateOfBirth)) {
      const msg = 'Please fill in the required information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success';
      this.userService.updateUser(this.itemOneObj);
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
  updateAddress() {
    if (this.isInvalid(this.itemThreeObj.houseNumber) ||
      this.isInvalid(this.itemThreeObj.street) ||
      this.isInvalid(this.itemThreeObj.city) ||
      this.isInvalid(this.itemThreeObj.zipCode) ||
      this.isInvalid(this.itemThreeObj.state)) {
      const msg = 'Fill In the Required Information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success';
      this.userService.saveOrUpdateUserAddress(this.itemOneObj.id , this.itemThreeObj);
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


  getLoggedInUser() {
    return this.authenticationService.getLoggedInUser();
  }

}
