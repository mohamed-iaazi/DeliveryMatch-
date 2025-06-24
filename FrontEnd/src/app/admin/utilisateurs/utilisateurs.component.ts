import { Component } from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-utilisateurs',
  imports: [
    NgForOf
  ],
  templateUrl: './utilisateurs.component.html',
  styleUrl: './utilisateurs.component.css'
})
export class UtilisateursComponent {
  users = [
    { id: 1, name: 'John Doe', email: 'john@example.com', role: 'Admin' },
    { id: 2, name: 'Jane Doe', email: 'jane@example.com', role: 'User' },
    // Add more users here...
  ];

  editUser(user: any) {
    // Implement edit user logic here...
    console.log('Edit user:', user);
  }

  deleteUser(user: any) {
    // Implement delete user logic here...
    console.log('Delete user:', user);
    this.users = this.users.filter(u => u.id !== user.id);
  }
}
