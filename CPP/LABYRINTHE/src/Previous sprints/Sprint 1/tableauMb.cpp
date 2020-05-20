/**
 * @file tableauMb.h
 * @brief Permet d'initialiser/désallouer en mémoire le tableau 3D avant/après affection du labyrinthe
 * Projet SDA 1
 */
#include "tableauMb.h"

/**
* @brief Initialise un tableau 3D allouée en mémoire dynamique et récupère sa taille
* @see detruireTab, pour une désallocation en fin d'utilisation
* @param[in] in : flux d'entrée
* @param[in,out] x : taille d'une ligne du labyrinthe
* @param[in,out] y : nombre de lignes du labyrinthe
* @return tab : le tableau 3D d'ItemMb
*/
ItemMb*** initialiserTab(ifstream& in, int& x, int& y) {
	in >> x;
	in >> y;

	ItemMb*** tab = new char**[NB_DAMIERS];
	for (int i = 0; i < NB_DAMIERS; ++i) {
		tab[i] = new char* [y];
		for (int j = 0; j < y; ++j) {
			tab[i][j] = new char[x];
		}
	}
	return tab;
}

/**
* @brief Désalloue tab
* @see initialiserTab, pour l'initialisation du tableau
* @param[in,out] tab : tableauMb le tableau 3D
* @param[in,out] x : taille d'une ligne du labyrinthe
* @param[in,out] y : nombre de lignes du labyrinthe
* @pre tab est initialisé
*/
void detruireTab(ItemMb*** tab, int& x, int& y) {
	for (int i = 0; i < NB_DAMIERS; ++i) {
		for (int j = 0; j < y; ++j) {
			delete[] tab[i][j];
		}
		delete[] tab[i];
	}
	delete[] tab;
	x = 0;
	y = 0;
}

