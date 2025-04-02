import { Inject, Injectable, InjectionToken } from '@angular/core';

export const LOG_LEVEL = new InjectionToken<number>('LOG_LEVEL');

@Injectable({
  providedIn: 'root'
})

export class LoggerService {

  private level: number = Number.MAX_VALUE;


  constructor(@Inject(LOG_LEVEL) level? : number) {
    if(level != null && level >= 0 && level <= 4){ 
      this.level = level;
    }
   }


  public log(message: string) : void{
    console.log(message);
  }

  public info(message: string) : void{  
    if (this.level > 1) {
      console.info(message);
    } else {
      console.log(message);
    }
  }

  public warn(message: string) : void{
    if (this.level > 2) {
      console.warn(message);
    } else {
      console.log(message);
    }
  }

  public error(message: string) : void{
    if (this.level > 3) {
      console.error(message);
    } else {
      console.log(message);
    }
  }
}
