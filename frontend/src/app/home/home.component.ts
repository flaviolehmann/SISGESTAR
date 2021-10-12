import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {finalize} from 'rxjs/operators';
import {HomeMesages} from './home-mesages';
import {UsuarioModel} from '../shared-models/usuario-model';
import {UsuarioService} from '../shared-services/usuario-service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.component.scss']
})
export class HomeComponent implements OnInit {
    MSG = HomeMesages;
    usuarios: UsuarioModel[] = [];
    @BlockUI() blockUI: NgBlockUI;

    constructor(private usuarioService: UsuarioService) {
    }

    ngOnInit() {
        this.updateTable();
    }

    updateTable() {
        this.blockUI.start();
        this.usuarioService.findAll()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(usuarios => this.usuarios = usuarios);
    }
}
