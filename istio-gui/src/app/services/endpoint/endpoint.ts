import { HttpClient, HttpHeaders } from "@angular/common/http";

export type Error = "always" | "even" | "odd" | "never";

export class Endpoint {
  host: string = "http://localhost:8080";
  url: string = "receita/12345";
  sleep: number = 1000;
  on: boolean;
  event: Event;

  constructor(private http: HttpClient) { }

  start() {
    this.on = true;
    this.cycle();
  }

  cycle() {
    if (this.on) {
      setTimeout(() => { this.run() }, this.sleep);
      console.log(this.url + "-> schedulled in " + this.sleep);
    } else {
      console.log(this.url + "-> stopped");
    }
  }

  call() {
    return this.url.split("|")[0];
  }

  params() {
    return this.url.split("|").splice(1);
  }

  headers() {
    let headers = new HttpHeaders();
    for (let p of this.params()) {
      let s = p.split("=");
      headers = headers.set(s[0], s[1]);
    }
    return headers;
  }

  run() {
    let e = new Event();
    this.http.get<any>(`${this.host}/${this.call()}`, {
      observe: 'response',
      headers: this.headers()
    }).subscribe(
      response => {
        this.check(e, response);
      },
      error => {
        this.check(e, error);
      }
    )
  }

  check(e: Event, response: any) {
    e.response = response;
    e.end = Date.now();
    this.event = e;
    this.cycle();
  }

  pause() {
    this.on = false;
  }

  stop() {
    this.pause();
    this.event = null;
  }

  class() {
    return !this.event ? "" : (this.event.response.status == 200 ? "success" : "error");
  }

  prefix() {
    return this.url.split("/")[0];
  }

  seterror(error: Error) {
    return this.http.get<any>(`${this.host}/${this.prefix()}/seterror/${error}`);
  }

  setdelay(segs: number) {
    return this.http.get<any>(`${this.host}/${this.prefix()}/setdelay/${segs}`);
  }
}

export class Event {
  id: number;
  start: number;
  end: number;
  response: any;

  constructor() {
    this.start = Date.now();
  }

  header(...keys: string[]) {
    for (let k of keys) {
      let v = this.response.headers.get(k);
      if (v) {
        return v;
      }
    }
    return '';
  }

  time() {
    return this.end ? this.end - this.start : "";
  }
}
