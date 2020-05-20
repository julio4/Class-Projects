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
