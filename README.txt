# Pet Rescue Saga (2020)

**Description :**
Jeu consistant à sauver des animaux en détruisant des blocs de même couleur associés par paires ou plus nombreux, mais qui restent voisins.

## Informations Générales

### Version Interface Graphique

1. **Installation :**
   - Placez-vous dans le répertoire du projet.
   - Ouvrez votre terminal à partir de ce répertoire.
   - Lancez le jeu avec la commande :
     ```
     javac *.java ; java Accueil
     ```
   
2. **Démarrer le jeu :**
   - Un joueur est déjà créé dans la classe `Accueil`.
   - Cliquez sur le bouton "Jouer" pour afficher les niveaux disponibles.
   - Au départ, seul le premier niveau est accessible.

3. **Gameplay :**
   - Cliquez sur les blocs de couleur (autres que les blancs) pour les détruire.
   - Le jeu calcule les voisins avec la méthode `voisinsAutour(int l, int c)`.
   - Le tableau est ajusté avec `ajuster()`, et les animaux sont sauvés si nécessaire via `sauverAnimaux()`.
   - Si besoin, les colonnes sont déplacées avec `decaler()`.

4. **Fonctionnalités supplémentaires :**
   - Le bouton **robot** n'est pas fonctionnel.
   - Pour quitter une partie, cliquez sur la croix située en bas à gauche.
   - Si vous perdez, la partie se termine automatiquement.
   - Un message s'affiche pour vous avertir de la fin du jeu (gagné ou perdu).
---

### Version Terminal

1. **Installation :**
   - Lancez le jeu avec la commande :
     ```
     javac *.java ; java JeuTerminal
     ```

2. **Démarrer le jeu :**
   - Le terminal demandera d'abord un pseudonyme.
   - Ensuite, il vous posera si vous souhaitez jouer.

3. **Gameplay :**
   - Vous devrez entrer les coordonnées d'un bloc, qui seront affichées sur le plateau.
   - Le tableau est numéroté pour faciliter la saisie des coordonnées.
   - Le plateau est mis à jour régulièrement pour une meilleure lisibilité.
   - Les réponses sont acceptées selon les instructions données dans les questions.

4. **Restrictions :**
   - Il n'est pas possible de quitter le jeu en cours autrement qu'en fermant le terminal de manière brutale.

---

### Remarques

- Ce jeu ne permet pas d'acheter de boosters ni de sauvegarder les joueurs et leurs scores.
- Certaines fonctionnalités comme le robot et l'achat de boosters n'ont pas été implémentées.
