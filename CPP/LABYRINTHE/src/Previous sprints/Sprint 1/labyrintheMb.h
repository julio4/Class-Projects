/**
 * @file labyrintheMb.h
 * @brief Permet d'affecter et de réinitialiser le labyrinthe, et d'afficher le sprint 1
 * Projet SDA 1
 */
#pragma once
#include <iostream>
#include <fstream>

#include "item.h"

/**
* @brief Initialise le labyrinthe dans un tableau 3D
* @see detruireLab, pour réinitialiser le tableau en fin d'utilisation
* @param[in,out] tab : tableauMb le tableau 3D
* @param[in] x : taille d'une ligne du tableau
* @param[in] y : nombre de lignes du tableau
* @pre tab est initialisé, in est ouvert
*/
void initialiserLab(ifstream& in, ItemMb*** tab, int& x, int& y);

/**
* @brief Réinitialise le tableau 3D à des valeures nulles.
* @see initialiserLab, pour initialiser le labyrinthe dans un tableau 3D
* @param[in,out] tab : tableauMb le tableau 3D
* @param[in] x : taille d'une ligne du labyrinthe
* @param[in] y : nombre de lignes du labyrinthe
* @pre tab est initialisé
*/
void detruireLab(ItemMb*** tab, int& x, int& y);

/**
* @brief Affiche la sortie demandée en Sprint 1 (taille du labyrinthe et les 2 labyrinthes)
* @param[in] tab : tableauMb le tableau 3D
* @param[in] x : taille d'une ligne du labyrinthe
* @param[in] y : nombre de lignes du labyrinthe
* @pre tab est initialisé et affecté
*/
void affichersp1(ItemMb*** tab, int& x, int& y);