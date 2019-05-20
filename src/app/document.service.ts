import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DocumentType } from './entity/DocumentType';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  apiURL = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getDocumentTypes() {
    return this.http.get<DocumentType[]>(this.apiURL + '/documentType');
  }

}
