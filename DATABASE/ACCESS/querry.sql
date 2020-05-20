--Requête 01 :
SELECT Fournisseur.Ville, Fournisseur.Pays
FROM Fournisseur;

--Requête 02 :
SELECT Client.CodeCli, Client.Societe, Client.Contact
FROM Client
WHERE (((Client.Societe) Like "*Store*" Or (Client.Societe) Like "*Shop*"));

--Requête 03 :
SELECT Produit.Refprod, Produit.Nomprod, Produit.PrixUnit
FROM Categorie INNER JOIN (Fournisseur INNER JOIN Produit ON Fournisseur.NoFour = Produit.NoFour) ON Categorie.CodeCateg = Produit.CodeCateg
WHERE (((Fournisseur.Pays)="France" Or (Fournisseur.Pays)="Canada") AND ((Categorie.NomCateg)="Boissons" Or (Categorie.NomCateg)="Desserts"));

--Requête 04a :
SELECT Fournisseur.NoFour
FROM Categorie INNER JOIN (Fournisseur INNER JOIN Produit ON Fournisseur.NoFour = Produit.NoFour) ON Categorie.CodeCateg = Produit.CodeCateg
WHERE (((Categorie.NomCateg)<>"Boissons"));

--Requête 04b :
SELECT DISTINCT Fournisseur.NoFour, Fournisseur.Societe, Fournisseur.Contact
FROM Categorie INNER JOIN ((Fournisseur LEFT JOIN [Question 04a] ON Fournisseur.NoFour = [Question 04a].NoFour) INNER JOIN Produit ON Fournisseur.NoFour = Produit.NoFour) ON Categorie.CodeCateg = Produit.CodeCateg
WHERE ((([Question 04a].NoFour) Is Null) AND ((Categorie.NomCateg)="Boissons"));

--Requête 05a :
SELECT Client.Ville
FROM Client
WHERE (((Client.Societe)="North/South"));
Requête 05b :
SELECT Client.CodeCli, Client.Societe, Client.Adresse, Client.Tel, TypeClient.NomType, Client.Ville
FROM TypeClient INNER JOIN (Client INNER JOIN [Question 05a] ON Client.Ville = [Question 05a].Ville) ON TypeClient.CodeType = Client.CodeType
WHERE ((Not (Client.Societe)="North/South"));

--Requête 06 :
SELECT DISTINCT Fournisseur.NoFour, Fournisseur.Societe, Fournisseur.Contact
FROM Fournisseur INNER JOIN Produit ON Fournisseur.NoFour = Produit.NoFour
WHERE (((Fournisseur.Pays)="France") AND ((Produit.UnitesCom)=0));

--Requête 07 :
SELECT Categorie.NomCateg, Sum([DetailCommande.PrixUnit]*(1-[Remise%])*[Qte]) AS CA
FROM (Categorie INNER JOIN Produit ON Categorie.CodeCateg = Produit.CodeCateg) INNER JOIN DetailCommande ON Produit.Refprod = DetailCommande.Refprod
GROUP BY Categorie.NomCateg;

--Requête 08a :
SELECT Client.CodeCli, Produit.Nomprod
FROM Produit INNER JOIN ((Client INNER JOIN Commande ON Client.CodeCli = Commande.CodeCli) INNER JOIN DetailCommande ON Commande.NoCom = DetailCommande.Nocom) ON Produit.Refprod = DetailCommande.Refprod
WHERE (((Produit.Nomprod)="Konbu"));

--Requête 08b :
SELECT DISTINCT Client.*, Client.Societe, Produit.Nomprod
FROM Produit INNER JOIN (((Client LEFT JOIN [Question 08a] ON Client.CodeCli = [Question 08a].CodeCli) INNER JOIN Commande ON Client.CodeCli = Commande.CodeCli) INNER JOIN DetailCommande ON Commande.NoCom = DetailCommande.Nocom) ON Produit.Refprod = DetailCommande.Refprod
WHERE (((Produit.Nomprod)="Tofu") AND (([Question 08a].CodeCli) Is Not Null));

--Requête 09 :
SELECT Employe.Nom, Employe.prenom, Count(Employe_1.RendCompteA) AS CompteDeRendCompteA1
FROM Employe INNER JOIN Employe AS Employe_1 ON Employe.NoEmp = Employe_1.RendCompteA
GROUP BY Employe.Nom, Employe.prenom, Employe.Fonction
HAVING (((Employe.Fonction)="Chef des ventes"));
