import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConfirmationService} from 'primeng/api';
import {ActivatedRoute, Router} from '@angular/router';
import {PageNotificationService} from '@nuvem/primeng-components';
import {UserMesages} from './user-mesages';
import {UsuarioService} from '../../../shared-services/usuario-service';
import {finalize} from 'rxjs/operators';
import {BaseEntityForm} from '../../../utils/base-entity-form';
import {UsuarioModel} from '../../../shared-models/usuario-model';
import {ModalService} from '../../../utils/modal.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['user.component.scss']
})
export class UserComponent extends BaseEntityForm<UsuarioModel> implements OnInit {
    MSG = UserMesages;
    form: FormGroup;
    @BlockUI() blockUI: NgBlockUI;
    title = 'Novo Usuario';
    SERVICE = this.usuarioService;
    @Output() saveUser = new EventEmitter<void>();

    constructor(
        private usuarioService: UsuarioService,
        protected confirmationService: ConfirmationService,
        protected router: Router,
        protected pageNotificationService: PageNotificationService,
        protected formBuilder: FormBuilder,
        protected route: ActivatedRoute,
        protected modalService: ModalService
    ) {
        super(confirmationService, router, pageNotificationService, formBuilder, route, modalService);
    }

    ngOnInit() {
        this.form = this.buildReactiveForm();
    }

    buildReactiveForm() {
        return this.formBuilder.group({
            id: [null],
            hash: [null],
            nome: [null, [Validators.required, Validators.maxLength(255)]],
            email: [null, [Validators.required, Validators.maxLength(255)]],
        }, {updateOn: 'change'});
    }

    clearForm() {
        this.form.reset();
        this.title = 'Novo Usuario';
    }

    salvarUsuario(usuario: UsuarioModel) {
        this.blockUI.start();
        this.usuarioService.insert(usuario)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(UserMesages.USUARIO_SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    editarUsuario(usuario: UsuarioModel) {
        this.blockUI.start();
        this.usuarioService.update(usuario)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(UserMesages.USUARIO_SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    navegarParaDashboard() {
        this.saveUser.emit();
    }

    onLoadEntity(entity: UsuarioModel) {
        this.form.patchValue(entity);
    }

    sendForm(entity: UsuarioModel) {
        entity.id ? this.editarUsuario(entity) : this.salvarUsuario(entity);
    }
}
