import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FileItem, FileUploader} from "ng2-file-upload";
import {MatTableDataSource} from "@angular/material";
import {Cookie} from "ng2-cookies";
import {SelectionModel} from "@angular/cdk/collections";
import {Image} from "../../entity/image";

const URL = 'http://localhost:8081/resourceServer_war_exploded/images';

@Component({
  selector: 'app-file-dropzone',
  templateUrl: './file-drop-zone.component.html',
  styleUrls: ['./file-drop-zone.component.scss']
})
export class FileDropZoneComponent implements OnInit {

  private displayedColumns: string[] = ['select', 'position', 'name', 'size', 'progress', 'status', 'actions'];
  private uploader: FileUploader;
  private dataTable: MatTableDataSource<FileItem>;
  private hasBaseDropZoneOver: boolean = false;
  private selection: SelectionModel<FileItem>;


  @Output() imagePathEvent = new EventEmitter<Image>();

  ngOnInit() {
    this.uploader = new FileUploader({
      url: URL,
      authToken: 'Bearer ' + Cookie.get('access_token')
    });
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      this.sendImagePath(item, response);
    };
    this.selection = new SelectionModel<FileItem>(false, []);
  }

  private uploadAll() {
    this.uploader.uploadAll();
    this.dataTable = new MatTableDataSource<FileItem>(this.uploader.queue);
  }

  private cancelAll() {
    this.uploader.clearQueue();
    this.dataTable = new MatTableDataSource<FileItem>(this.uploader.queue);
  }

  private removeFile(fileItem:FileItem) {
    this.uploader.removeFromQueue(fileItem);
    this.dataTable = new MatTableDataSource<FileItem>(this.uploader.queue);
  }

  fileSelected(event) {
    this.dataTable = new MatTableDataSource<FileItem>(this.uploader.queue);
  }

  private sendImagePath(item: FileItem, imagePath: string) {
    this.imagePathEvent.emit(new Image(imagePath, this.selection.isSelected(item)));
  }


  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
    this.dataTable = new MatTableDataSource<FileItem>(this.uploader.queue);
  }

  checkboxLabel(row?: FileItem): string {
    if (!this.dataTable) {
      return;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.index}`;
  }


}
