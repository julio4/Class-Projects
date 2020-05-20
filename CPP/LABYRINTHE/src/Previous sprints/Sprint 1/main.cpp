/**
 * @file main.cpp
 * @author Jules Doumèche, Martin Gwenole
 * @version 1 - 2019
 * @brief  Projet SDA - Sprint 1
 */

/*
ARBORESCENCE DES #INCLUDE:

							+----------+
							| main.cpp |
							+----+-----+
								 ^
								 |
								 |
						   +-----+-------+        +---------------+
						   | tableauMb.h +<-------+ tableauMb.cpp |
						   +-----+-------+        +---------------+
		 +-----------+           ^
		 | <fstream> +------+    |
		 +-----------+      v    |
						 +--+----+--------+     +------------------+
						 | labyrintheMb.h +<----+ labyrintheMb.cpp |
						 +--+----+--------+     +------------------+
		+------------+      ^    ^
		| <iostream> +------+    |
		+------------+           |
							 +---+----+
							 | item.h |
							 +--------+

*/

#include "tableauMb.h"

const char FICHIER[STRING_SIZE] = "inSmall.txt";

int main() {

	// Initialisation variable + input stream

	int x = 0; // x, colonne
	int y = 0; // y, ligne

	ifstream in;
	/* Ouverture du fichier .txt via la console
	char fic[STRING_SIZE] = { NULL };
	do {
		cout << "Entrez fichier à ouvrir:";
		cin >> fic;
		cout << endl;
		in.open(fic);
		if (!in) {
			cout << "Erreur!" << endl;
			cerr << "Fichier introuvable" << endl;
			//return 1;
		}
	} while (!in); */
	in.open(FICHIER);
	if (!in) {
		cout << "Erreur!" << endl;
		cerr << "Fichier introuvable" << endl;
		return 1;
	}

	// Execution Sprint1

	ItemMb*** tableauMb = initialiserTab(in, x, y);
	initialiserLab(in, tableauMb, x, y);

	affichersp1(tableauMb, x, y);

	in.close();
	detruireLab(tableauMb, x, y);
	detruireTab(tableauMb, x, y);
	return 0;
}