/**
 * @file dragonGame.h
 * @brief Initialisation du Dragon et recherche du PDM dans le labyrinthe
 * Projet SDA 1
 */
#pragma once

#include <stack>
#include "indexPositionMb.h"

/**
 * @brief Initialise le dragon dans une structure Dragon
 * @see detruireDrag, pour réinitialiser les positions du dragon en fin d'utilisation
 * @param[in-out] lab : le labyrinthe
 * @return Dragon
 */
Dragon initialiserDrag(Lab& lab);

/**
 * @brief Réinitialise à zéro les position du dragon
 * @see initialiserDrag, pour initialiser les positions du Dragon
 * @param[in-out] drag : le dragon
 * @pre drag est initialisé
 */
void detruireDrag(Dragon& drag);

/**
 * @brief Execute le sprint 5 et l'affiche
 * @see initialiserDrag, initialiserTab, initialiserLab, affichersp5
 * @param[in-out] lab : le labyrinthe
 * @param[in-out] drag : le dragon
 * @pre Lab est initialisé, drag est initialisé
 */
void missionDragonSp5(Lab& lab, Dragon& drag);

/** 
 * @brief Affiche le mécontentement du dragon si il n'y a pas de chemin
 * @see missionDragonSp5
 * @param[in] lab : le labyrinthe (constant)
 */
void mecontentement(const Lab lab);