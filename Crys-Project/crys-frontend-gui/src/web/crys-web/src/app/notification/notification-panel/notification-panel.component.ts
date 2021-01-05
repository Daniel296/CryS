import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notification-panel',
  templateUrl: './notification-panel.component.html',
  styleUrls: ['./notification-panel.component.scss']
})
export class NotificationPanelComponent implements OnInit {

  private status: boolean = false;
  constructor() { }

  ngOnInit() {
  }

  clickEvent(){
    this.status = !this.status;
  }
}
