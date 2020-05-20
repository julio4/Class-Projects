/**
 * @file labyrintheMb.h
 * @brief Permet d'initialiser et de réinitialiser le labyrinthe, et d'afficher le sprint 5
 * Projet SDA 1
 */
#pragma once

#include "header.h"

/**
 * @brief Initialise le labyrinthe dans un tableau 3D
 * @see detruireLab, pour réinitialiser le tableau en fin d'utilisation
 * @param[in] in : le flux d'entrée afin d'initialiser le labyrinthe
 * @param[in-out] lab : le labyrinthe à initialiser
 * @pre tab est initialisé, in est ouvert
 */
void initialiserLab(ifstream& in, Lab& lab);

/**
 * @brief Réinitialise le tableau 3D à des valeures nulles.
 * @see initialiserLab, pour initialiser le labyrinthe dans un tableau 3D
 * @param[in,out] lab : Labyrinthe contenant le tableau 3D et ses dimensions
 * @pre lab(.tab, .col, .lin) est initialisé
 */
void detruireLab(Lab& lab);

/**
 * @brief Affiche le Sprint 5
 * @see missionDragonSp5, afin d'effectuer le sprint 5 et provoquer son affichage par l'appel de cette fonction
 * @param[in] lab : Labyrinthe contenant le tableau 3D et ses dimensions
 * @param[in] chemin : La pile contenant les coordonnées successive correspondant au chemin réalisé par le dragon
 * @pre lab(.tab, .col, .lin) est initialisé
 */
void affichersp5(Lab& lab, stack<Position>& chemin);