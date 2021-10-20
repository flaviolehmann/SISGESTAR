import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {UsuarioModel} from '../shared-models/usuario-model';
import {BaseEntityService} from '../utils/base-entity-service';

@Injectable({
    providedIn: 'root'
})export class UsuarioService extends BaseEntityService<UsuarioModel, any> {

    getEntity(): string {
        return 'usuarios';
    }

    constructor(protected http: HttpClient) {
        super(http);
    }

    login(hash: string) {
        return this.http.get<UsuarioModel>(this.resourceUrl + '/obter-por-hash/' + hash);
    }

}
