import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DocumentType } from './entity/DocumentType';
import { IdentityDocument } from './entity/IdentityDocument';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getDocumentTypes() {
    return this.http.get<DocumentType[]>(this.apiURL + '/documentType');
  }

  getDocumentByUserName(userName) {
    return this.http.get<IdentityDocument>(this.apiURL + '/identityDocument/userName/' + userName);
  }

}
