# Jeu du Morpion en Java - Version Console

## Ce jeu est un TP pour les cours Java SE

### Java POO

### JUnit pour tester le modèle et le comportement

### Packaging automatisé avec utilisation de Maven & Ant

## Installations
    - Telechargez la version correspondante à votre système 
    - Dezippez dans un répertoire 
    - Lancez le script morpion (.bat|.sh) en fonction de votre système

[download windows install](https://github.com/tarhack/morpion/tree/main/builds/install_windows.zip)  
[download Linux/Mac install](https://github.com/tarhack/morpion/tree/main/builds/install_mac_linux.zip)

### Rappel des règles du jeu
- Affichage de la grille de jeu (début)
- ![cas de gagne ](./images/morpion-jeu-cases.jpg "Cas de gagne - le joueur 1 a gagné")
- le premier joueur à réaliser une ligne, une colonne ou une diagonale sur la grille a gagné
- Chaque joueur joue à son tour, une case déjà jouée ne peut plus être rejouée
- Il est possible que les deux joueurs se neutralisent dans ce cas la partie s'arrête

- ### Le joueur 1 gagne, il a réalisé une diagonale
- ![cas de gagne ](./images/morpion-gagne-diagonale.jpg "Cas de gagne - le joueur 1 a gagné")
- - ### Le joueur 2 qui gagne, il a réalisé une ligne
- ![cas de gagne ](./images/morpion-gagne-ligne.jpg "Cas de gagne - le joueur 2 a gagné")
- ### Les deux joueurs se sont neutralisés, aucun gagnant !
- ![cas de blocage ](./images/morpion-blocage.jpg "Cas de blocage - aucun joueur ne gagne")