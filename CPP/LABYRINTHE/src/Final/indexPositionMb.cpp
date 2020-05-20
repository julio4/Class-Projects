/**
 * @file indexPositionMb. pp
 * @brief Permet de rechercher les positions initiales du Dragon et des Plans du Monde
 * Projet SDA 1
 */
#include "indexPositionMb.h"
#include <iostream>

/**
 * @brief Initialise la position du Dragon et du PDM dans le labyrinthe
 * @see initialiserDrag
 * @param[in-out] lab : le labyrinthe
 * @param[in-out] drag : le dragon
 * @pre tab est initialisé, in est ouvert
 */
void initialiserPos(Lab& lab, Dragon& drag) {
	bool initDrag = false;
	bool initPDM = false;
	for (unsigned int i = 0; i < NB_DAMIERS; ++i) {
			for (unsigned int j = 0; j < lab.lin; ++j) {
				for (unsigned int l = 0; l < lab.col; ++l) {
					if (lab.tab[i][j][l].car == 'D') {
						drag.pos.z = i;
						drag.pos.y = j;
						drag.pos.x = l;
						initDrag = true;
					}
					if (lab.tab[i][j][l].car == 'P') {
						lab.PDM.z = i;
						lab.PDM.y = j;
						lab.PDM.x = l;
						initPDM = true;
					}
					if (initDrag && initPDM) {
						return;
					}
				}
			}
		}
	cerr << "Erreur! Impossible de trouver le Dragon Et/Ou les Plans du Monde" << endl;
	return;
}

/**
 * @brief Détermine si 2 Position sont connexe dans le labyrinthe
 * @param[in] lab : le labyrinthe
 * @param[in] a : première position
 * @param[in] b : deuxième position
 * @return true si Connexe, false sinon
 */
bool estConnexe(const Lab& lab, Position a, Position b) {
	//Orientation

	if (a.z == b.z) { // a et b sur le meme paneau
		if (a.y == b.y) { // a et b meme ligne
			if ((a.x - b.x == 1) || (b.x - a.x == 1)) {
				return true;
			}
			else
				return false;
		}
		else { // a et b pas meme ligne
			if (a.x == b.x) { // a et b meme colonne et pas meme ligne
				if ((a.y - b.y == 1) || (b.y - a.y == 1)) {
					return true;
				}
				else
					return false;
			}
			else {// a et b pas meme ligne, pas meme colonne
				if (a.x - b.x == 1) {
					if ((a.y - b.y == 1) || (b.y - a.y == 1)) {
						return true;
					}
					else
						return false;
				}
				else if (b.x - a.x == 1) {
					if ((a.y - b.y == 1) || (b.y - a.y == 1)) {
						return true;
					}
					else
						return false;
				}
				return false;
			}
		}
	}
	else {// a et b PAS sur la même face
		if (a.x == 0) {
			if (b.x != lab.col - 1) {
				return false;
			}
		}
		else if (b.x == 0) {
			if (a.x != lab.col - 1) {
				return false;
			}
			else {
				Position temp = b;
				b = a;
				a = temp;
			}
		}

		//on a a.x = 0 et b.x = dernière ligne
		if ((b.y == (lab.lin - 2 - a.y)) || (b.y == (lab.lin - 1 - a.y)) || (b.y == (lab.lin - a.y))) {
			return true;
		}
		else
			return false;
	}
	return false;
}