/**
 * @file dragonGame.cpp
 * @brief Initialisation du Dragon et recherche du PDM dans le labyrinthe
 * Projet SDA 1
 */
#include "dragonGame.h"

/**
 * @brief Initialise le dragon dans une structure Dragon
 * @see detruireDrag, pour réinitialiser les positions du dragon en fin d'utilisation
 * @param[in] lab : le labyrinthe
 * @return Dragon
 */
Dragon initialiserDrag(Lab& lab) {
	Dragon drag;
	initialiserPos(lab, drag);
	return drag;
}

/**
 * @brief Réinitialise à zéro les position du dragon
 * @see initialiserDrag, pour initialiser les positions du Dragon
 * @param[in-out] drag : le dragon
 * @pre drag est initialisé
 */
void detruireDrag(Dragon& drag){
	drag.pos.z = 0;
	drag.pos.y = 0;
	drag.pos.x = 0;
}

/**
 * @brief Execute le sprint 3 et l'affiche
 * @see initialiserDrag, initialiserTab, initialiserLab, affichersp2
 * @param[in-out] lab : le labyrinthe
 * @param[in-out] drag : le dragon
 * @pre Lab est initialisé, drag est initialisé
 */
void missionDragonSp3(Lab& lab, Dragon& drag) {
	stack<Position> move;
	move.push(drag.pos);
	bool atteint = false;
	while (!(move.empty())) {
		drag.pos = move.top();
		lab.tab[drag.pos.z][drag.pos.y][drag.pos.x].estLu = true;
		lab.tab[drag.pos.z][drag.pos.y][drag.pos.x].car = 'V';
		move.pop();

		if (drag.pos.z == lab.PDM.z && drag.pos.y == lab.PDM.y && drag.pos.x == lab.PDM.x) {
			atteint = true;
		}

		if (!atteint) {

			//Orientation
			stack<int> deplacement;
			for (int i = 8; i > 0; --i) {
				switch (i) {
				case 1:
					deplacement.push(i);
					break;
				case 2:
					if (drag.pos.y != (lab.lin - 1))
						deplacement.push(i);
					else
						i = 1;
					break;
				case 3:
					if (drag.pos.y != (lab.lin - 1))
						deplacement.push(i);
					else
						i = 1;
					break;
				case 4:
					if (drag.pos.y != (lab.lin - 1))
						deplacement.push(i);
					else
						i = 1;
					break;
				case 5:
					deplacement.push(i);
					break;
				case 6:
					if (drag.pos.y != 0)
						deplacement.push(i);
					else
						i = 6;
					break;
				case 7:
					if (drag.pos.y != 0)
						deplacement.push(i);
					else
						i = 6;
					break;
				case 8:
					if (drag.pos.y != 0)
						deplacement.push(i);
					else
						i = 6;
					break;
				default:
					cerr << "Erreur" << endl;
				}
			}

			//Recherche et mouvement
			while (!deplacement.empty()) {
				Position mouv = drag.pos;
				switch (deplacement.top()) {
				case 1:
					if (drag.pos.x == 0) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][(lab.lin - 1) - drag.pos.y][lab.col - 1].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = (lab.lin - 1) - drag.pos.y;
							mouv.x = lab.col - 1;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y][drag.pos.x - 1].car != '#') {
						mouv.x--;
					}
					break;
				case 2:
					if (drag.pos.x == 0) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][lab.lin - drag.pos.y - 2][lab.col - 1].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = lab.lin - drag.pos.y - 2;
							mouv.x = lab.col - 1;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y + 1][drag.pos.x - 1].car != '#') {
						mouv.x--;
						mouv.y++;
					}
					break;
				case 3:
					if (lab.tab[drag.pos.z][drag.pos.y + 1][drag.pos.x].car != '#') {
						mouv.y++;
					}
					break;
				case 4:
					if (drag.pos.x == (lab.col - 1)) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][lab.lin - drag.pos.y - 2][0].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = lab.lin - drag.pos.y - 2;
							mouv.x = 0;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y + 1][drag.pos.x + 1].car != '#') {
						mouv.x++;
						mouv.y++;
					}
					break;
				case 5:
					if (drag.pos.x == (lab.col - 1)) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][(lab.lin - 1) - drag.pos.y][0].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = (lab.lin - 1) - drag.pos.y;
							mouv.x = 0;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y][drag.pos.x + 1].car != '#') {
						mouv.x++;
						}
					break;
				case 6:
					if (drag.pos.x == (lab.col - 1)) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][lab.lin - drag.pos.y][0].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = lab.lin - drag.pos.y;
							mouv.x = 0;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y - 1][drag.pos.x + 1].car != '#') {
						mouv.x++;
						mouv.y--;
					}
					break;
				case 7:
					if (lab.tab[drag.pos.z][drag.pos.y - 1][drag.pos.x].car != '#') {
						mouv.y--;
					}
					break;
				case 8:
					if (drag.pos.x == 0) {
						if (lab.tab[(NB_DAMIERS - 1) - drag.pos.z][lab.lin - drag.pos.y][lab.col - 1].car != '#') {
							mouv.z = (NB_DAMIERS - 1) - drag.pos.z;
							mouv.y = lab.lin - drag.pos.y;
							mouv.x = lab.col - 1;
						}
					}
					else if (lab.tab[drag.pos.z][drag.pos.y - 1][drag.pos.x - 1].car != '#') {
						mouv.x--;
						mouv.y--;
					}
					break;
				default:
					cerr << "Erreur lors de la recherche de chemin" << endl;
				}
				if (lab.tab[mouv.z][mouv.y][mouv.x].estLu == false) {
					move.push(mouv);
				}
				deplacement.pop();
			}
		}
		else {
			atteint = true;
			break;
		}
	}
	if (atteint) {
		affichersp3(lab);
	}
	else {
		cout << "Aucun chemin d'accès vers les Plans du Monde" << endl;
		affichersp3(lab);
	}
}
