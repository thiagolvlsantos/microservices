import { NgModule } from '@angular/core';
import { ComumModule } from '../comum/comum.module';
import { EndpointComponent } from './endpoint/endpoint.component';
import { HomeComponent } from './home/home.component';
import { ServicesRoutingModule } from './services-routing.module';


@NgModule({
  imports: [
    ComumModule,
    ServicesRoutingModule
  ],
  declarations: [
    HomeComponent,
    EndpointComponent
  ],
  providers: [
  ]
})
export class ServicesModule { }
