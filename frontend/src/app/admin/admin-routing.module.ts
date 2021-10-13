import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AdminComponent} from './admin.component';

const routes: Routes = [
    { path: '', component: AdminComponent, children: [
            { path: '',  component: HomeComponent, pathMatch: 'full' },
            { path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule) },
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
