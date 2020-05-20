/**
 * @file tableauMb.h
 * @brief Permet d'initialiser/désallouer en mémoire le tableau 3D avant/après affection du labyrinthe
 * Projet SDA 1
 */
#pragma once

#include "labyrintheMb.h"

/**
 * @brief Initialise le tableau 3D lab.tab allouée en mémoire dynamique
 * @see detruireTab, pour une désallocation en fin d'utilisation
 * @param[in,out] lab : structure lab, contenant la dimension du labyrinthe et le tableau 3D associé
 */
void initialiserTab(Lab& lab);

/**
 * @brief Désalloue lab.tab
 * @see initialiserTab, pour l'initialisation du tableau
 * @param[in,out] lab : structure lab, contenant la dimension du labyrinthe et le tableau 3D associé
 * @pre lab.tab est initialisé
 */
void detruireTab(Lab& lab);