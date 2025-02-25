import {Component, computed, signal} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {NgClass, NgIf} from "@angular/common";
import {isEmail} from "@dwtechs/checkhard";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    FormsModule,
    NgIf
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  public messageSignal = signal<string>('');
  public remainingChars = computed(() => (300 - this.messageSignal().length));
  public contactForm: FormGroup;
  public contactData: any;

  constructor(private formBuilder: FormBuilder, private messageService: MessageService) {
    this.contactForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      message: ['', [Validators.required, Validators.maxLength(300)]],
    })
  }

  ngOnInit() {
    this.contactForm.get('message')?.valueChanges.subscribe(value => {
      this.messageSignal.set(value || '');
    });
  }


  onSubmit(): void {
    if (this.contactForm.valid) {
      this.contactData = this.contactForm.value;
      this.contactForm.reset();

      this.messageService.add({
        severity: 'success',
        summary: 'Message envoyé',
        detail: 'Demande de contact envoyée avec succès à ' + this.contactData.email, // Partie 2 : Demande de contact envoyée avec succès
        icon: 'pi pi-send',
      });
    }
  }
}
