import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SellerAuthComponent} from "./seller-auth/seller-auth.component";
import {HomeComponent} from "./home/home.component";
import {SellerHomeComponent} from "./seller-home/seller-home.component";
import {UserAuthComponent} from "./user-auth/user-auth.component";

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'seller-auth',component:SellerAuthComponent},
  {path:'seller-home',component:SellerHomeComponent},
  {path:'login',component:UserAuthComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
