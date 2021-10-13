import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {UsuarioModel} from '../shared-models/usuario-model';

@Injectable({
    providedIn: 'root'
})export class UsuarioService {

    private apiUrl = 'api/usuarios';

    constructor(protected http: HttpClient) {
    }

    insert(entity: UsuarioModel): Observable<UsuarioModel> {
        return this.http.post<UsuarioModel>(this.apiUrl, entity);
    }

    findById(id: number): Observable<UsuarioModel> {
        return this.http.get<UsuarioModel>(this.apiUrl + '/' + id);
    }

    findAll(): Observable<UsuarioModel[]> {
        return this.http.get<UsuarioModel[]>(this.apiUrl);
    }

    update(entity: UsuarioModel): Observable<UsuarioModel> {
        return this.http.put<UsuarioModel>(this.apiUrl, entity);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(this.apiUrl + '/' + id);
    }

    login(hash: string) {
        return this.http.get<UsuarioModel>(this.apiUrl + '/obter-por-hash/' + hash);
    }

}
