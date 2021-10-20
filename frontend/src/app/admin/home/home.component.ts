import {Component, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {finalize} from 'rxjs/operators';
import {HomeMesages} from './home-mesages';
import {UsuarioModel} from '../../shared-models/usuario-model';
import {UsuarioService} from '../../shared-services/usuario-service';
import {Router} from '@angular/router';
import {ConfirmationService} from 'primeng/api';
import {GenericTableColumn} from '../../shared/models/generic-table-column';
import {HomeUtil} from './home-util';
import {GenericTableComponent} from '../../components/generic-table/generic-table.component';
import {GenericTableButton} from '../../shared/models/generic-table-button';
import {GenericTableUpdateEvent} from '../../shared/models/generic-table-update-event';
import {Page} from '../../utils/page';
import {ModalService} from '../../utils/modal.service';
import {UserFormModalComponent} from '../user/user-form/user-form-modal.component';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.component.scss']
})
export class HomeComponent implements OnInit {
    MSG = HomeMesages;
    usuarios: UsuarioModel[] = [];
    @BlockUI() blockUI: NgBlockUI;
    COLUMNS: GenericTableColumn[] = HomeUtil.COLUMNS;
    @ViewChild(GenericTableComponent) user: GenericTableComponent;
    BUTTONS: GenericTableButton<UsuarioModel>[] = [
        {
            icon: 'edit',
            description: 'editar',
            action: row => this.editUser(row.id)
        },
        {
            icon: 'delete',
            description: 'deletar',
            action: row => this.confirmacaoDeletar(row.id)
        }
    ];

    constructor(
        private usuarioService: UsuarioService,
        private router: Router,
        private confirmationService: ConfirmationService,
        private modalService: ModalService
    ) {
    }

    ngOnInit() {
        this.loadTable();
    }

    loadTable() {
        this.blockUI.start();
        this.usuarioService.findAll()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(usuarios => this.user.result =  new Page(usuarios, usuarios.length));
    }

    confirmacaoDeletar(id: number) {
        this.confirmationService.confirm({
                header: 'Deseja deletar esse Usuario?',
                message: 'Essa alteração não poderar ser desfeita!',
                accept: () => this.deletarUsuario(id),
                acceptLabel: 'Sim',
                rejectLabel: 'Não'
        });
    }

    deletarUsuario(id: number) {
        this.blockUI.start();
        this.usuarioService.delete(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => this.loadTable());
    }

    editUser(id: number) {
        this.modalService.openModal(UserFormModalComponent, {}, {id});
    }

    updateTable(event: GenericTableUpdateEvent) {
        this.user.table = event.table;
    }
}
