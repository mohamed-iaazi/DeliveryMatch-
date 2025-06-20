# 🚚 DeliveryMatch : Application de transport collaboratif de colis

## 📌 Présentation

**DeliveryMatch** est une application web de co-transport collaboratif qui met en relation des conducteurs avec des expéditeurs de colis. L’objectif est de :
- ✅ Optimiser les trajets
- 💰 Réduire les coûts de transport
- 🌱 Limiter l’impact environnemental

---

## 👤 Rôles & Fonctionnalités

### 🔐 Utilisateur
- 🔸 Créer un compte avec nom, prénom, email, mot de passe
- 🔸 Connexion sécurisée
- 🔸 Modifier ses informations personnelles
- 🔸 Se déconnecter

### 🚗 Conducteur
- 📝 Publier une annonce avec :
  - Point de départ, étapes, destination
  - Dimensions maximales acceptées
  - Type de marchandise
  - Capacité disponible
- 📬 Voir les demandes associées à ses trajets
- ✅ Accepter ou refuser une demande d'envoi
- 📜 Consulter l’historique des trajets et colis

### 📦 Expéditeur
- 🔍 Rechercher des trajets selon critères (destination, date, type)
- ✉️ Envoyer une demande de transport (dimensions, poids, type)
- 📜 Voir l’historique des demandes et colis envoyés

### 🛠️ Administrateur
- 📊 Dashboard de gestion : utilisateurs, annonces, demandes
- 🛡️ Valider, suspendre, attribuer un badge “✔️ Vérifié” à un utilisateur
- 🧾 Gérer les annonces (modifier, supprimer)
- 📈 Voir des statistiques via **Chart.js** : 
  - Nombre d’annonces
  - Taux d’acceptation
  - Utilisateurs actifs

### 🎁 Bonus
- 🔔 Notifications en temps réel (acceptation, refus, confirmation)
- 🌟 Système d’évaluations (conducteur ↔ expéditeur)

---

## 🛠️ Technologies utilisées

| Technologie        | Description                                   |
|--------------------|-----------------------------------------------|
| ⚙️ **Backend**       | Spring Boot, Spring Data JPA, Spring Security |
| 🎨 **Frontend**      | Angular 16+, Bootstrap / Tailwind, Angular Material |
| 📊 **Graphiques**    | [Chart.js](https://www.chartjs.org/) pour les statistiques |
| 🗄️ **Base de données** | PostgreSQL / MySQL                         |
| 🧪 **Tests unitaires** | JUnit                                      |
| 📦 **Conteneurisation** | Docker                                   |
| 📚 **Documentation API** | Swagger, Collection Postman           |

---

## 📌 Installation & Lancement

### Prérequis
- Node.js / npm
- Java 17+
- Docker
- Angular CLI
- PostgreSQL ou MySQL

### 🔧 Backend
```bash
cd backend
./mvnw spring-boot:run
