// import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
// import {LoginService} from "../../services/login.service";
// import {MatPaginator, MatTableDataSource} from "@angular/material";
// import {Document} from "../../entity/document";
// import {HttpParams} from "@angular/common/http";
// // import {DocumentService} from "../../services/document.service";
//
// @Component({
//   selector: 'app-document-list',
//   templateUrl: './document-list.component.html',
//   styleUrls: ['./document-list.component.scss']
// })
// export class DocumentListComponent implements OnInit {
//
//   resultsLength = 1000;
//   params = new HttpParams()
//     .set('start', '1')
//     .set('length', '10');
//
//   @ViewChild(MatPaginator) paginator: MatPaginator;
//
//   displayedColumns: string[] = ['name', 'email', 'phone'];
//   dataTable: MatTableDataSource<Document>;
//
//
//   constructor(private userService: LoginService, private documentService: DocumentService) {
//   }
//
//
//
//   ngOnInit() {
//     this.documentService.getDocuments();
//   }
//
// }
