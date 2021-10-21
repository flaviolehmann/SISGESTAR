import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {UserRoutingModule} from './user-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {UserComponent} from './user-form/user.component';
import {UserFormModalComponent} from './user-form/user-form-modal.component';


@NgModule({
    declarations: [UserComponent, UserFormModalComponent],
    entryComponents: [UserFormModalComponent],
    exports: [
        UserComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        ReactiveFormsModule,
        UserRoutingModule
    ]
})
export class UserModule { }
