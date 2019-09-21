import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentType } from './entity/DocumentType';
import { AuthenticatedHttpCaller } from './authenticatedhttpcaller.service';

@Injectable({
  providedIn: 'root'
})
export class DocumentTypeService extends AuthenticatedHttpCaller<DocumentType> {

  private _servicePath = '/documentType';

  public getAll(): Promise <Observable<DocumentType[]>> {
    return super._getAll(this._servicePath);
  }
}
