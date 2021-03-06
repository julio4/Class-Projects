/**
 * @file labyrintheMb.cpp
 * @brief Permet d'affecter et de r�initialiser le labyrinthe, et d'afficher le sprint 3
 * Projet SDA 1
 */
#include "labyrintheMb.h"

/**
 * @brief Initialise le labyrinthe dans un tableau 3D
 * @see detruireLab, pour r�initialiser le tableau en fin d'utilisation
 * @param[in] in : le flux d'entr�e afin d'initialiser le labyrinthe
 * @param[in-out] lab : le labyrinthe � initialiser
 * @pre tab est initialis�, in est ouvert
 */
void initialiserLab(ifstream& in, Lab& lab) {
	for (unsigned int i = 0; i < NB_DAMIERS; ++i) {
		//Ecrit tableau[i]
		for (unsigned int j = 0; j < lab.lin; ++j) {
			//Ecrit ligne j
			for (unsigned int l = 0; l < lab.col; ++l) {
				in >> lab.tab[i][j][l].car;
			}
		}
	}
}

/**
 * @brief R�initialise le tableau 3D � des valeures nulles.
 * @see initialiserLab, pour initialiser le labyrinthe dans un tableau 3D
 * @param[in,out] lab : Labyrinthe contenant le tableau 3D et ses dimensions
 * @pre lab(.tab, .col, .lin) est initialis�
 */
void detruireLab(Lab& lab) {
	for (unsigned int i = 0; i < NB_DAMIERS; ++i) {
		//Supprime tab[i]
		for (unsigned int j = 0; j < lab.lin; ++j) {
			//Supprime ligne j
			for (unsigned int l = 0; l < lab.col; ++l) {
				lab.tab[i][j][l].car = NULL;
				lab.tab[i][j][l].estLu = false;
			}
		}
	}
}

/**
 * @brief Affiche le Sprint 3
 * @see missionDragonSp3, afin d'effectuer le sprint 3 et provoquer son affichage par l'appel de cette fonction
 * @param[in] lab : Labyrinthe contenant le tableau 3D et ses dimensions
 * @pre lab(.tab, .col, .lin) est initialis�
 */
void affichersp3(Lab& lab) {

	ofstream iwrite;
	iwrite.open(file_out, ofstream::out | ofstream::trunc);

	iwrite << lab.col << " " << lab.lin << endl;
	for ( unsigned int i = 0; i < NB_DAMIERS; ++i) {
		//Lire tab[i]
		for (unsigned int j = 0; j < lab.lin; ++j) {
			//Lire ligne j
			for (unsigned int l = 0; l < lab.col; ++l) {
				iwrite << lab.tab[i][j][l].car;
			}
			iwrite << endl;
		}
		iwrite << endl;
	}
}
