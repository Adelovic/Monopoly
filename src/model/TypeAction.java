package model;


public enum TypeAction 
{
    LANCER_PARTIE,
    TIRER_CARTE,
    LANCER_DES,
    ALLER_PRISON,
    PRISON,
    PAYER_LOYER,
    ACHAT,
    RIEN,
    CONSTRUIRE,
    ELIMINER_JOUEUR, // Envoyé en fin de tour quand le joueur est éliminé
    DEBUT_PARTIE,
    FIN_PARTIE,
    REJOUER_DOUBLE_DES,
    REJOUER,
    DEBUT_COUP,
    FIN_COUP,
    TOUCHE_CASE_DEPART,
    DEPLACER_JOUEUR,
    AFFICHER_CONSTRUCTIBLE,
    
    C_LIBERATION,
    C_TRANSACTION_FIXE,
    C_ANNIVERSAIRE,
    C_REPARATION,
    C_DEPLACEMENT_RELATIF,
    C_DEPLACEMENT_ABSOLU;
    
    
    
    
    
    
}
