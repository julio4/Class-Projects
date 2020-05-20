/**
 * @file indexPositionMb.h
 * @brief Permet de rechercher les positions initiales du Dragon et des Plans du Monde
 * Projet SDA 1
 */
#pragma once

#include "tableauMb.h"

/**
 * @brief Initialise la position du Dragon et du PDM dans le labyrinthe
 * @see initialiserDrag
 * @param[in-out] lab : le labyrinthe
 * @param[in-out] drag : le dragon
 * @pre tab est initialisé, in est ouvert
 */
void initialiserPos(Lab& lab, Dragon& drag);