export enum NotificationType {
    error = 'error',
    warn = 'warn',
    info = 'info',
    log = 'log'
  }
  
  export class Notification{
  
    private id: number;
    private type: NotificationType;
    private message: string;
  
    constructor(id: number, type: NotificationType, message: string){
      this.id = id;
      this.type = type;
      this.message = message;
    }
  
    getId(): number{
      return this.id;
    }
  
    getType(): NotificationType{
      return this.type;
    }
  
    getMessage(): string{
      return this.message;
    }
  }