import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { FileChooser } from '@ionic-native/file-chooser/ngx';
import { CountryService } from '../country.service';
import { DocumentTypeService } from '../document.service';
import { PhoneService } from '../phone.service';
import { UserService, AddressService, PhoneNumberService } from '../user.service';
import { User } from '../entity/User';

import { HttpClient,  HttpEventType  } from '@angular/common/http';

import { FormBuilder, FormGroup } from '@angular/forms';

import { ViewChild } from '@angular/core';
import { MultiFileUploadComponent } from '../components/multi-file-upload/multi-file-upload.component';
import { Auth } from 'aws-amplify';
import { Router } from '@angular/router';
import { observable } from 'rxjs';

import { PhoneNumber } from '../entity/PhoneNumber';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  @ViewChild(MultiFileUploadComponent) fileField: MultiFileUploadComponent;

  countries: any = [];
  documentTypes: any = [];
  phoneTypes: any = [];
  userEmail: string;

  basicDetails: any = {
    'id': '',
    'firstName': '',
    'middleName': '',
    'lastName': '',
    'dateOfBirth': '',
    'nationality': ''
  };

  itemTwoObj: any = {
    'id': '',
    'documentTypeCode': '',
    'fileId': ''
  };

  addressDetails: any = {
    'id': '',
    'userId': '',
    'houseNumber': '',
    'street': '',
    'city': '',
    'zipCode': '',
    'state': '',
    'countryCode': ''
  };

  homeNumber: any = {
    'id': '',
    'userId': '',
    'phoneType': 2,
    'countryCode': '',
    'number': '',
  };

  mobileNumber: any = {
    'id': '',
    'userId': '',
    'phoneType': 1,
    'countryCode': '',
    'number': ''
  };

  officeNumber: any = {
    'id': '',
    'userId': '',
    'phoneType': 3,
    'countryCode': '',
    'number': ''
  };

  userId: any ;

  isEditOne: any = false;
  isEditTwo: any = false;
  isEditThree: any = false;
  isEditFour: any = false;

  user: any = {
    'id': '',
    'email': '',
    'firstName': '',
    'middleName': '',
    'lastName': '',
    'dateOfBirth': '',
    'nationality': ''
  };

  ngOnInit() {
      Auth.currentAuthenticatedUser({
        bypassCache: false
      }).then(async user => {
        this.userEmail = user.attributes.email;
        this.userId = user.attributes.sub;
      }).catch(err => console.log(err));


     this.countryService.getAll()
      .then(obs => obs.subscribe(countries => this.countries = countries));

     this.phoneService.getAll()
      .then(obs => obs.subscribe(phoneTypes => this.phoneTypes = phoneTypes));

     this.documentTypeService.getAll()
      .then(obs => obs.subscribe(documentTypes => this.documentTypes = documentTypes));

   /*  this.documentService.getDocumentByUserName(this.getLoggedInUser())
      .subscribe(document => {
       this.itemTwoObj.id = document[0].id;
       this.itemTwoObj.documentTypeCode = document[0].documentTypeCode;
       this.itemTwoObj.fileId = document[0].mediaFileId;
    }); */

    this.userService.find()
      .then(obs => obs.subscribe(
        user => {
          this.basicDetails.firstName = user.firstName;
          this.basicDetails.middleName = user.middleName;
          this.basicDetails.lastName = user.lastName;
          this.basicDetails.dateOfBirth = user.dateOfBirth;
          this.basicDetails.nationality = user.nationality;
          this.basicDetails.id = user.id;
          this.basicDetails.email = user.email;
    }));

    this.addressService.find()
      .then(obs => obs.subscribe(
        address => {
          if (address !== null) {
            this.addressDetails.id = address.id;
            this.addressDetails.userId = this.userId;
            this.addressDetails.houseNumber = address.houseNumber;
            this.addressDetails.street = address.street;
            this.addressDetails.city = address.city;
            this.addressDetails.zipCode = address.zipcode;
            this.addressDetails.state = address.state;
            this.addressDetails.countryCode = address.countryCode;
          }
        }));

    this.phoneNumberService.findAll()
      .then(obs => obs.subscribe(
        phoneNumbers => {
          phoneNumbers.forEach(phoneNumber => {
            if (phoneNumber.phoneType === 1) {
              this.mobileNumber.id = phoneNumber.id;
              this.mobileNumber.userId = phoneNumber.userId;
              this.mobileNumber.phoneType = phoneNumber.phoneType;
              this.mobileNumber.countryCode = phoneNumber.countryCode;
              this.mobileNumber.number = phoneNumber.number;
            }
            if (phoneNumber.phoneType === 2) {
              this.homeNumber.id = phoneNumber.id;
              this.homeNumber.userId = phoneNumber.userId;
              this.homeNumber.phoneType = phoneNumber.phoneType;
              this.homeNumber.countryCode = phoneNumber.countryCode;
              this.homeNumber.number = phoneNumber.number;
            }
            if (phoneNumber.phoneType === 3) {
              this.officeNumber.id = phoneNumber.id;
              this.officeNumber.userId = phoneNumber.userId;
              this.officeNumber.phoneType = phoneNumber.phoneType;
              this.officeNumber.countryCode = phoneNumber.countryCode;
              this.officeNumber.number = phoneNumber.number;
            }
          });
       }));
  }

  constructor(public toastController: ToastController, private fileChooser: FileChooser, private countryService: CountryService,
    private documentTypeService: DocumentTypeService, private phoneService: PhoneService,
    private phoneNumberService: PhoneNumberService, private userService: UserService,
    private addressService: AddressService, private http: HttpClient,
    public router: Router) {
  }
  updateBasicInfo() {
    if (this.isInvalid(this.basicDetails.firstName) ||
      this.isInvalid(this.basicDetails.lastName) ||
      this.isInvalid(this.basicDetails.middleName) ||
      this.isInvalid(this.basicDetails.dateOfBirth)) {
      const msg = 'Please fill in the required information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success';
      this.userService.update(this.basicDetails);
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
    if (this.isInvalid(this.addressDetails.houseNumber) ||
      this.isInvalid(this.addressDetails.street) ||
      this.isInvalid(this.addressDetails.city) ||
      this.isInvalid(this.addressDetails.zipCode) ||
      this.isInvalid(this.addressDetails.state)) {
      const msg = 'Fill In the Required Information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success';
      this.addressDetails.userId = this.userId;
      this.addressService.update(this.addressDetails);
      this.presentToast(msg, 'success');
      this.isEditThree = false;
    }
  }
  updatePhoneNumbers() {
    if (false) {
      const msg = 'Fill In the Required Information.';
      this.presentToast(msg, 'danger');
      return false;
    } else {
      const msg = 'Save Success.';
      this.homeNumber.userId = this.userId;
      this.phoneNumberService.update(this.homeNumber);

      this.mobileNumber.userId = this.userId;
      this.phoneNumberService.update(this.mobileNumber);

      this.officeNumber.userId = this.userId;
      this.phoneNumberService.update(this.officeNumber);
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

  upload() {

    const files = this.fileField.getFiles();
    const formData = new FormData();

    files.forEach((file) => {
      formData.append('document', file.rawFile);
      formData.append('documentTypeCode', this.itemTwoObj.documentTypeCode);
      formData.append('documentName', file.name);
      formData.append('userId', this.basicDetails.id);

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

  getLoggedInUser() {
    return this.userEmail;
  }
}
