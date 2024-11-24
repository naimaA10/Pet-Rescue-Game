Pet Rescue Saga (2020)
Jeu consistant à sauver les animaux en détruisant les blocs de même couleurs associés 
par pairs ou plus nombreux, mais qui restent voisins

Informations générales pour faire fonctionner le jeu

Version Interface Graphique
Placer vous dans le répertoire projet.
Ouvrer votre terminal à partir de se répertoire .
Lancer avec la commande "javac *.java ; java Accueil"
Un joueur est déjà créé dans cette classe.
Il faut ensuite cliquer sur Jouer et une fenêtre avec l'affichage des différents niveaux.
Cliquer sur le niveau accessible qui sera au départ, le premier niveau.
Lors du jeu, cliquer sur les blocs de couleurs autre que les blanches.
Les voisins sont calculés avec la méthode voisinsAutour(int l, int c)
Le tableau est ensuite ajuster (ajuster()), on sauve les animaux si utile (sauverAnimaux())
et on décale le plateau (decaler()).
Le bouton robot qui s'affiche ne fonctionne pas.
Si l'utilisateur souhaite quitter une partie en cours, il peut cliquer sur le bouton croix située en bas à gauche.
Un message s'affichera. 
Si l'utilisateur perd, il sera forcé de quitter la partie automatiquement. De même, s'il gagne.

Version sur terminal
Lancer avec la commande "javac *.java ; java JeuTerminal" et jouer selon les questions posées
On demandera d'abord un pseudo, puis si l'utilisateur veut jouer.
Les réponses acceptées sont notées dans la question et le plateau est numéroté de sorte qu'il est facile de rentrer les coordonnées d'un bloc sans se tromper.
Le plateau est affiché régulièrement afin de pouvoir faciliter la visualisation. De même, que les numéros de blocs ont été espacés de manière lisible.
Il n'est pas possible de quitter le jeu en cours de manière non brutale.
