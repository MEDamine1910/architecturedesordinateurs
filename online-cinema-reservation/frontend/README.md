# Cinema Reservation Frontend

Application React moderne pour la réservation de places de cinéma en ligne.

## 🚀 Démarrage Rapide

### Installation
```bash
npm install
```

### Démarrage du serveur de développement
```bash
npm run dev
```

L'application sera accessible sur **http://localhost:3000**

## 📋 Prérequis

Assurez-vous que tous les services backend sont démarrés :
1. Discovery Service (port 8761)
2. Gateway Service (port 8080)
3. Film Service (port 8083)
4. Reservation Service (port 8081)
5. Payment Service (port 8082)

## 🛠️ Technologies

- **React 18** - Bibliothèque UI
- **Vite** - Build tool moderne
- **React Router** - Navigation
- **Axios** - Client HTTP
- **Tailwind CSS** - Framework CSS

## 📁 Structure

```
src/
├── components/     # Composants réutilisables
├── pages/          # Pages de l'application
├── services/       # Services API
└── App.jsx         # Composant principal
```

## 🎨 Fonctionnalités

- ✅ Liste des films disponibles
- ✅ Détails d'un film
- ✅ Réservation de places
- ✅ Paiement en ligne (simulation)
- ✅ Liste des réservations
