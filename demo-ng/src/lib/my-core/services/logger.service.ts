import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggerService {

  constructor() { }

  public log(message: string) : void{
    console.log(message);
  }

  public info(message: string) : void{  
    if (console.info) {
      console.info(message);
    } else {
      console.log(message);
    }
  }

  public warn(message: string) : void{
    if (console.warn) {
      console.warn(message);
    } else {
      console.log(message);
    }
  }

  public error(message: string) : void{
    if (console.error) {
      console.error(message);
    } else {
      console.log(message);
    }
  }
}
