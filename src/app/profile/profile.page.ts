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

import { HttpClient,  HttpEventType  } from '@angular/common/http';

import { FormBuilder, FormGroup } from '@angular/forms';

import { ViewChild } from '@angular/core';
import { MultiFileUploadComponent } from '../components/multi-file-upload/multi-file-upload.component';
import { CognitoService } from '../cognito.service';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  @ViewChild(MultiFileUploadComponent) fileField: MultiFileUploadComponent;

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
    "documentTypeCode": "",
    "fileId": ""
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

  homeNumber: any = {
    "id": "",
    "userId": "",
    "phoneType": "",
    "countryCode": "",
    "number": ""
  }

  mobileNumber: any = {
    "id": "",
    "userId": "",
    "phoneType": "",
    "countryCode": "",
    "number": ""
  }

  officeNumber: any = {
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

    this.documentService.getDocumentByUserName(this.getLoggedInUser())
      .subscribe(document => {
       this.itemTwoObj.id = document[0].id;
       this.itemTwoObj.documentTypeCode = document[0].documentTypeCode;
       this.itemTwoObj.fileId = document[0].mediaFileId;
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

      this.userService.getPhoneNumber(this.getLoggedInUser())
       .subscribe(phoneNumber => {
         for (let i = 0; i < phoneNumber.length; i++) {
          if (phoneNumber[i].phoneType === 1) {
            this.mobileNumber.id = phoneNumber[i].id;
            this.mobileNumber.userId = phoneNumber[i].userId;
            this.mobileNumber.phoneType = phoneNumber[i].phoneType;
            this.mobileNumber.countryCode = phoneNumber[i].countryCode;
            this.mobileNumber.number = phoneNumber[i].number;
          }

          if (phoneNumber[i].phoneType === 2) {
            this.homeNumber.id = phoneNumber[i].id;
            this.homeNumber.userId = phoneNumber[i].userId;
            this.homeNumber.phoneType = phoneNumber[i].phoneType;
            this.homeNumber.countryCode = phoneNumber[i].countryCode;
            this.homeNumber.number = phoneNumber[i].number;
          }

          if (phoneNumber[i].phoneType === 3) {
            this.officeNumber.id = phoneNumber[i].id;
            this.officeNumber.userId = phoneNumber[i].userId;
            this.officeNumber.phoneType = phoneNumber[i].phoneType;
            this.officeNumber.countryCode = phoneNumber[i].countryCode;
            this.officeNumber.number = phoneNumber[i].number;
          }

         }
       });



  }

  constructor(public toastController: ToastController, private fileChooser: FileChooser, private countryService: CountryService,
    private documentService: DocumentService, private phoneService: PhoneService, private userService: UserService,
    private authenticationService: AuthenticationService, private http: HttpClient, private cognitoService: CognitoService) {
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
    if (false) {
      const msg = 'Fill In the Required Information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success.';
      this.upload();
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
    if (false) {
      const msg = 'Fill In the Required Information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success.';
      this.userService.saveOrUpdatePhoneNumber(this.itemOneObj.id, this.mobileNumber, 1);
      this.userService.saveOrUpdatePhoneNumber(this.itemOneObj.id, this.homeNumber, 2);
      this.userService.saveOrUpdatePhoneNumber(this.itemOneObj.id, this.officeNumber, 3);
      this.presentToast(msg, 'success');
      this.isEditFour = false;
    }
  }

  isEdit(type) {
    if (type === 1) {
      this.isEditOne = !this.isEditOne;
    } else if (type === 2) {
      this.isEditTwo = !this.isEditTwo;
    } else if (type === 3) {
      this.isEditThree = !this.isEditThree;
    } else {
      this.isEditFour = !this.isEditFour;
    }
  }

  isInvalid(field) {
    if (field === '' || field === null || field === undefined) {
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
    return this.cognitoService.getLoggedUser();
  }

  upload() {

    let files = this.fileField.getFiles();
    let formData = new FormData();

    files.forEach((file) => {
      formData.append('document', file.rawFile);
      formData.append('documentTypeCode', this.itemTwoObj.documentTypeCode);
      formData.append('documentName', file.name);
      formData.append('userId', this.itemOneObj.id);

      // POST formData to Server
      this.http.post('http://localhost:8080/api/identityDocument', formData, {
        reportProgress: true,
        observe: 'events'
      })
      .subscribe(events => {
        if (events.type === HttpEventType.UploadProgress) {
            console.log('Upload progress: ', Math.round(events.loaded / events.total * 100) + '%');
        } else if (events.type === HttpEventType.Response) {
            console.log(events);
        }
      });
    });
  }

}
