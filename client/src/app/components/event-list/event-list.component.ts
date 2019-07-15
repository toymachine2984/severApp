import {Component, OnInit, ViewChild} from '@angular/core';
import {EventService} from "../../services/event.service";
import {HttpParams} from "@angular/common/http";
import {MatPaginator, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {

  resultsLength = 1000;
  params = new HttpParams()
    .set('start', '1')
    .set('length', '10');

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns: string[] = ['name', 'email', 'phone'];
  dataTable: MatTableDataSource<Event>;


  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.eventService.getEvents();
  }
}
