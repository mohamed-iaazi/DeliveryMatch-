import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrajetDetailComponent } from './trajet-detail.component';

describe('TrajetDetailComponent', () => {
  let component: TrajetDetailComponent;
  let fixture: ComponentFixture<TrajetDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrajetDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrajetDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
