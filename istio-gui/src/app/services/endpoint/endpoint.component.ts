import { HttpClient } from '@angular/common/http';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Endpoint, Error } from './endpoint';

@Component({
  selector: 'app-endpoint',
  templateUrl: './endpoint.component.html',
  styleUrls: ['./endpoint.component.css']
})
export class EndpointComponent implements OnInit, OnDestroy {
  @Input() host: string;
  @Input() url: string;
  @Input() sleep: number;

  endpoint: Endpoint;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    if (!this.endpoint) {
      this.endpoint = new Endpoint(this.http);
      Object.assign(this.endpoint, this);
    }
  }

  ngOnDestroy() {
    this.stop();
  }

  start() {
    this.ngOnInit();
    this.endpoint.start();
  }

  pause() {
    if (this.endpoint) {
      this.endpoint.pause();
    }
  }

  stop() {
    if (this.endpoint) {
      this.endpoint.stop();
      this.ngOnInit();
    }
  }

  sethost(host: string) {
    if (this.endpoint) {
      this.endpoint.host = host;
    }
  }

  seturl(url: string) {
    if (this.endpoint) {
      this.endpoint.url = url;
    }
  }

  setsleep(sleep: number) {
    if (this.endpoint) {
      this.endpoint.sleep = sleep;
    }
  }

  seterror(error: Error[]) {
    if (this.endpoint) {
      for (let e of error) {
        this.endpoint.seterror(e).subscribe(
          response => {
            console.log(`Error on: ${e}`);
          });
      }
    }
  }

  setdelay(delay: number) {
    if (this.endpoint) {
      this.endpoint.setdelay(delay).subscribe(
        response => {
          console.log(`Delay of: ${delay}`);
        });
    }
  }
}