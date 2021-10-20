import {Component, OnInit, ViewChild} from '@angular/core';
import {UserComponent} from './user.component';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng';

@Component({
    selector: 'app-modal-user',
    template: '<app-user (done)="ref.close($event)"></app-user>'
})
export class UserFormModalComponent implements OnInit {
    constructor(public config: DynamicDialogConfig, public ref: DynamicDialogRef) {
    }

    @ViewChild(UserComponent) form: UserComponent;

    ngOnInit() {
        if (this.config.data) {
            // this.form.entityId = this.config.data.id;
        }
    }
}
