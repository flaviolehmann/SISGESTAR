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
import {UserComponent} from './user-form/user.component';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.component.scss']
})
export class HomeComponent implements OnInit {
    MSG = HomeMesages;
    usuarios: UsuarioModel[] = [];
    showDialogUser = false;
    @BlockUI() blockUI: NgBlockUI;
    COLUMNS: GenericTableColumn[] = HomeUtil.COLUMNS;
    @ViewChild(GenericTableComponent) user: GenericTableComponent;
    @ViewChild('form') userFform!: UserComponent;

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
        private confirmationService: ConfirmationService
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
        this.showDialogUser = true;
        this.userFform.entityId = id;
        this.userFform.loadEntity();
    }

    closeUserModal() {
        this.showDialogUser = false;
        this.loadTable();
    }

    updateTable(event: GenericTableUpdateEvent) {
        this.user.table = event.table;
    }
}
