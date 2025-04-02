export enum NotificationType {
    error = 'error',
    warn = 'warn',
    info = 'info',
    log = 'log'
  }
  
  export class NotificationModel{
  
    private Id: number;
    private Type: NotificationType;
    private Message: string;
  
    constructor(Id: number, Type: NotificationType, Message: string){
      this.Id = Id;
      this.Type = Type;
      this.Message = Message;
    }
  
    getId(): number{
      return this.Id;
    }
  
    getType(): NotificationType{
      return this.Type;
    }
  
    getMessage(): string{
      return this.Message;
    }
  }