import {
    Component,
    ViewChild,
} from '@angular/core';
import {UserComponent} from './user.component';
import {DynamicDialogConfig, DynamicDialogRef} from 'primeng';

@Component({
    selector: 'app-modal-user',
    template: '<app-user #form [modal]="true" (done)="ref.close($event)"></app-user>'
})
export class UserFormModalComponent  {
    constructor(public config: DynamicDialogConfig, public ref: DynamicDialogRef) {
    }
    @ViewChild('form',  { static: true }) form!: UserComponent;

    ngAfterViewInit(): void {
        setTimeout(() => this.initAtributes(), 100);
    }

    private initAtributes(): void {
        if (!this.config.data) {
            return;
        }

        this.form.entityId = this.config.data.id;
    }


}
