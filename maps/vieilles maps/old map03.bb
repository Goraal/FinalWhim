;.DEFINITION_CONSTANTES
HAUTEUR_PLAFOND#			=3.50
H_step#						=1.25
H_stepRing#					=1
H_stair#					=H_stepRing#/8.0
H_lvl2#						=HAUTEUR_PLAFOND#+H_step#*3

;.CHARGEMENT_TEXTURE
tex_sol_champs$					="textures/loran/Champs.jpg"
tex_sol_couloir$				="textures/loran/MarbleWhite2.jpg"
tex_vitre$						="textures/loran/blanc.jpg"
tex_tuyaux$						="textures/loran/BALMORAL.JPG"
tex_rampe_Ring$					="textures/loran/WoodWall2.jpg"


tex_mur_couloir$       				="textures/loran/murbronze.jpg" 
tex_mur_eE$							="textures/loran/papier-peint2.jpg"
tex_mur_Eglise$						="textures/loran/papier-peint2.jpg"
tex_mur_Saloon$						="textures/loran/papier-peint1.jpg"
tex_mur_Mairie$						="textures/loran/papier-peint3.jpg"
tex_mur_CaserneChasseur$			="textures/loran/papier-peint2.jpg"
tex_mur_Labyrinthe$					="textures/loran/Gris.jpg"
tex_mur_Forge$						="textures/loran/papier-peint2.jpg"
tex_mur_Ring$						="textures/loran/WoodWall3.jpg"
taille_tex_Ring#=4

tex_sol_eE$							="textures/loran/FloorBoisBleu.jpg"
tex_sol_Eglise$						="textures/loran/FloorBoisBleu.jpg"
tex_sol_Saloon$						="textures/loran/WoodWall2.jpg"
tex_sol_Mairie$						="textures/loran/FloorBoisBleu.jpg"
tex_sol_CaserneChasseur$			="textures/loran/FloorBoisBleu.jpg"
tex_sol_Labyrinthe$					="textures/loran/Gris3.jpg"
tex_sol_Forge$						="textures/loran/FloorBoisBleu.jpg"

tex_plafond_eE$						="textures/loran/BronzeBeige.jpg"
tex_plafond_Eglise$					="textures/loran/BronzeBeige.jpg"
tex_plafond_Saloon$					="textures/loran/toit_usine1.jpg"
tex_plafond_Mairie$					="textures/loran/BronzeBeige.jpg"
tex_plafond_CaserneChasseur$		="textures/loran/BronzeBeige.jpg"
tex_plafond_Labyrinthe$				="textures/loran/Gris.jpg"
tex_plafond_Forge$					="textures/loran/BronzeBeige.jpg"
tex_plafond_ciel$					="textures/loran/Skies2.jpg"

tex_supertable$						="textures/loran/velour_petit.jpg"
tex_voteuse$						="objets/Voteuse/Voteuse.jpg"
tex_table$                          ="textures/loran/WoodWall2.jpg"


img_tete_fontaine$						="sprites/loran/tête_fontaine.bmp"
img_ContourSteamPorte$					="sprites/loran/porte ascenceur.bmp"
img_ContourSteamPorte2$					="sprites/loran/porte ascenceur2.bmp"
img_trape_escalier$						="sprites/loran/DoorsMetal1.bmp"
tex_sol_step_Ring$						="sprites/loran/grillage.bmp"


Texture_mur_fontaine=LoadTexture(tex_tuyaux$)

;.CREATION_MONDE
; SOL (et mur invisible):
;.sol
sol_nord=					CreerMur(0.01,55,16,			27.5,H_lvl2#,32,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_sud=					CreerMur(0.01,55,9,				27.5,H_lvl2#,4.5,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_ouest=					CreerMur(0.01,16,40,			8,H_lvl2#,20,					tex_sol_couloir$,2,1,		type_sol(1,1))
sol_est=					CreerMur(0.01,17,40,			46.5,H_lvl2#,20,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_nord_ouest=				CreerMur(0.01,4.25,2.82,		16.5,H_lvl2#-0.01,23.5,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_sud_est=				CreerMur(0.01,4.25,2.82,		16.5,H_lvl2#-0.01,9.5,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_sud_ouest=				CreerMur(0.01,4.25,2.82,		37.5,H_lvl2#-0.01,9.5,				tex_sol_couloir$,2,1,		type_sol(1,1))
sol_nord_est=				CreerMur(0.01,4.25,2.82,		37.5,H_lvl2#-0.01,23.5,				tex_sol_couloir$,2,1,		type_sol(1,1))


RotateEntity sol_nord,0,0,90
RotateEntity sol_sud,0,0,90
RotateEntity sol_ouest,0,0,90
RotateEntity sol_est,0,0,90
RotateEntity sol_nord_ouest,0,45,90
RotateEntity sol_sud_est,0,-45,90
RotateEntity sol_sud_ouest,0,45,90
RotateEntity sol_nord_est,0,-45,90

EntityPickMode sol_nord,2	
EntityPickMode sol_sud,2
EntityPickMode sol_ouest,2	
EntityPickMode sol_est,2
EntityPickMode sol_nord_ouest,2	
EntityPickMode sol_sud_est,2
EntityPickMode sol_sud_ouest,2	
EntityPickMode sol_nord_est,2

ciel=					CreerMur(0.01,160,160,		27,HAUTEUR_PLAFOND#*10,16.5,				tex_plafond_ciel$,160)
RotateEntity ciel,0,0,90


;faux_sol=					CreerMur(0.01,55,40,		27.5,H_lvl2#+HAUTEUR_PLAFOND#,20,				tex_sol_couloir$,2,1,		type_sol(1,1))
;RotateEntity faux_sol,0,0,90
;EntityPickMode faux_sol,2
;EntityAlpha faux_sol,0



;.couloir1FlecheEntree;c1FE

mur_nord_est_c1FE_out=			CreerMur(8.485,2*HAUTEUR_PLAFOND#,0.01,		10,H_lvl2#+HAUTEUR_PLAFOND#,27,			tex_mur_couloir$,1)
mur_nord_ouest_c1FE_out=		CreerMur(9.9,HAUTEUR_PLAFOND#,0.01,			3.5,H_lvl2#+HAUTEUR_PLAFOND#/2,26.5,		tex_mur_couloir$,1)
mur_nord1_c1FE_out=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			13.5,H_lvl2#+HAUTEUR_PLAFOND#/2,12,			tex_mur_couloir$,1)
mur_nord2_c1FE_out=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			15.5,H_lvl2#+HAUTEUR_PLAFOND#/2,12,			tex_mur_couloir$,1)
mur_ouest_c1FE_out=				CreerMur(0.01,HAUTEUR_PLAFOND#,20,			0,H_lvl2#+HAUTEUR_PLAFOND#/2,13,			tex_mur_couloir$,1)
mur_est_c1FE_out=				CreerMur(0.01,HAUTEUR_PLAFOND#,12,		13,H_lvl2#+HAUTEUR_PLAFOND#/2,18,					tex_mur_couloir$,1)
mur_sud1_c1FE_out=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			0.5,H_lvl2#+HAUTEUR_PLAFOND#/2,3,			tex_mur_couloir$,1)
mur_sud2_c1FE_out=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			2.5,H_lvl2#+HAUTEUR_PLAFOND#/2,3,			tex_mur_couloir$,1)
mur_sud3_c1FE_out=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,			9.5,H_lvl2#+HAUTEUR_PLAFOND#/2,8,			tex_mur_couloir$,1)
mur_sud_est_c1FE_out=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,		5.5,H_lvl2#+HAUTEUR_PLAFOND#/2,5.5,			tex_mur_couloir$,1)
mur_nord_est_c1FE_in=			CreerMur(4.94,HAUTEUR_PLAFOND#,0.01,		8.25,H_lvl2#+HAUTEUR_PLAFOND#/2,23.75,		tex_mur_couloir$,1)
mur_nord_ouest_c1FE_in=			CreerMur(4.94,HAUTEUR_PLAFOND#,0.01,		4.75,H_lvl2#+HAUTEUR_PLAFOND#/2,23.75,		tex_mur_couloir$,1)
mur_ouest1_c1FE_in=				CreerMur(0.01,HAUTEUR_PLAFOND#,10,			3,H_lvl2#+HAUTEUR_PLAFOND#/2,12,			tex_mur_couloir$,1)
mur_ouest2_c1FE_in=				CreerMur(0.01,HAUTEUR_PLAFOND#,4,			3,H_lvl2#+HAUTEUR_PLAFOND#/2,20,			tex_mur_couloir$,1)
mur_est1_c1FE_in=				CreerMur(0.01,HAUTEUR_PLAFOND#,6,			10,H_lvl2#+HAUTEUR_PLAFOND#/2,14,			tex_mur_couloir$,1)
mur_est2_c1FE_in=				CreerMur(0.01,HAUTEUR_PLAFOND#,4,			10,H_lvl2#+HAUTEUR_PLAFOND#/2,20,			tex_mur_couloir$,1)
mur_sud_c1FE_in=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,			8.5,H_lvl2#+HAUTEUR_PLAFOND#/2,11,			tex_mur_couloir$,1)
mur_sud_est_c1FE_in=			CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,		5,H_lvl2#+HAUTEUR_PLAFOND#/2,9,				tex_mur_couloir$,1)
mur_nord_c1FE_up=				CreerMur(7,HAUTEUR_PLAFOND#,0.01,			3.5,H_lvl2#+1.5*HAUTEUR_PLAFOND#,30,			tex_mur_couloir$,1)
;mur_nord_est_c1FE_up=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		1.5,H_lvl2#+1.5*HAUTEUR_PLAFOND#,31.5,			tex_mur_couloir$,1)

sur_porte_eE_c1FE=				CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,			14.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,12,		tex_mur_couloir$,1)
sur_porte1_Eglise_c1FE=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,			3,H_lvl2#+HAUTEUR_PLAFOND#/2+1,17.5,		tex_mur_couloir$,1)
sur_porte2_Eglise_c1FE=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,			10,H_lvl2#+HAUTEUR_PLAFOND#/2+1,17.5,		tex_mur_couloir$,1)
sur_porte_Saloon_c1FE=			CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,			1.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,3,		tex_mur_couloir$,1)

RotateEntity mur_sud_est_c1FE_out,0,45,0
RotateEntity mur_nord_ouest_c1FE_out,0,45,0
RotateEntity mur_nord_est_c1FE_out,0,-45,0
RotateEntity mur_sud_est_c1FE_in,0,45,0
RotateEntity mur_nord_ouest_c1FE_in,0,45,0
RotateEntity mur_nord_est_c1FE_in,0,-45,0
;RotateEntity mur_nord_est_c1FE_up,0,-45,0


;.couloir2Zigzag
mur_sud_ouest_couloir2Zigzag=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,		13.5,H_lvl2#+HAUTEUR_PLAFOND#/2,5.5,			tex_mur_couloir$,1)
mur_ouest1_couloir2Zigzag=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,			16,H_lvl2#+HAUTEUR_PLAFOND#/2,1.5,			tex_mur_couloir$,1)
mur_ouest2_couloir2Zigzag=				CreerMur(0.01,HAUTEUR_PLAFOND#,6,			19,H_lvl2#+HAUTEUR_PLAFOND#/2,6,			tex_mur_couloir$,1)
mur_est1_couloir2Zigzag=				CreerMur(0.01,HAUTEUR_PLAFOND#,6,			35,H_lvl2#+HAUTEUR_PLAFOND#/2,3,			tex_mur_couloir$,1)
mur_est2_couloir2Zigzag=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,			23,H_lvl2#+HAUTEUR_PLAFOND#/2,7.5,			tex_mur_couloir$,1)
mur_nord_couloir2Zigzag=				CreerMur(12,HAUTEUR_PLAFOND#,0.01,			29,H_lvl2#+HAUTEUR_PLAFOND#/2,6,			tex_mur_couloir$,1)
mur_sud_couloir2Zigzag=					CreerMur(19,HAUTEUR_PLAFOND#,0.01,			25.5,H_lvl2#+HAUTEUR_PLAFOND#/2,0,			tex_mur_couloir$,1)
mur_centre_couloir2Zigzag=				CreerMur(13,HAUTEUR_PLAFOND#,0.01,			25.5,H_lvl2#+HAUTEUR_PLAFOND#/2,3,			tex_mur_couloir$,1)

RotateEntity mur_sud_ouest_couloir2Zigzag,0,-45,0

;.couloir3RingMairie
mur_ouest1_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,		19,H_lvl2#+HAUTEUR_PLAFOND#/2,25.5,			tex_mur_couloir$,1)
mur_ouest2_couloir3RingMairie=				CreerMur(0.01,2*HAUTEUR_PLAFOND#,0.5,	10,H_lvl2#+HAUTEUR_PLAFOND#,36.75,			tex_mur_couloir$,1)
mur_ouest3_couloir3RingMairie=				CreerMur(0.01,2*HAUTEUR_PLAFOND#,2.5,	10,H_lvl2#+HAUTEUR_PLAFOND#,34.25,			tex_mur_couloir$,1)
mur_est1_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,2,		22,H_lvl2#+HAUTEUR_PLAFOND#/2,25,			tex_mur_couloir$,1)
mur_est2_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,		23,H_lvl2#+HAUTEUR_PLAFOND#/2,27.5,			tex_mur_couloir$,1)
mur_est3_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,		13,H_lvl2#+HAUTEUR_PLAFOND#/2,36.5,			tex_mur_couloir$,1)
mur_est4_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,		20,H_lvl2#+HAUTEUR_PLAFOND#/2,37.5,			tex_mur_couloir$,1)
mur_est5_couloir3RingMairie=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,		20,H_lvl2#+HAUTEUR_PLAFOND#/2,39.5,			tex_mur_couloir$,1)
mur_sud1_couloir3RingMairie=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		22.5,H_lvl2#+HAUTEUR_PLAFOND#/2,26,			tex_mur_couloir$,1)
mur_sud2_couloir3RingMairie=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,		17.5,H_lvl2#+HAUTEUR_PLAFOND#/2,27,			tex_mur_couloir$,1)
mur_sud3_couloir3RingMairie=				CreerMur(7,HAUTEUR_PLAFOND#,0.01,		16.5,H_lvl2#+HAUTEUR_PLAFOND#/2,37,			tex_mur_couloir$,1)
mur_nord1_couloir3RingMairie=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,		18.5,H_lvl2#+HAUTEUR_PLAFOND#/2,32,			tex_mur_couloir$,1)
mur_nord2_couloir3RingMairie=				CreerMur(13,HAUTEUR_PLAFOND#,0.01,		13.5,H_lvl2#+HAUTEUR_PLAFOND#/2,40,			tex_mur_couloir$,1)
mur_sud_ouest1_couloir3RingMairie=			CreerMur(8.485,2*HAUTEUR_PLAFOND#,0.01,	13,H_lvl2#+HAUTEUR_PLAFOND#,30,			tex_mur_couloir$,1)
mur_sud_ouest2_couloir3RingMairie=			CreerMur(4.25,2*HAUTEUR_PLAFOND#,0.01,	8.5,H_lvl2#+HAUTEUR_PLAFOND#,38.5,			tex_mur_couloir$,1)
mur_nord_est1_couloir3RingMairie=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	21.5,H_lvl2#+HAUTEUR_PLAFOND#/2,30.5,			tex_mur_couloir$,1)
mur_nord_est2_couloir3RingMairie=			CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	15,H_lvl2#+HAUTEUR_PLAFOND#/2,34,			tex_mur_couloir$,1)

RotateEntity mur_sud_ouest1_couloir3RingMairie,0,-45,0
RotateEntity mur_sud_ouest2_couloir3RingMairie,0,-45,0
RotateEntity mur_nord_est1_couloir3RingMairie,0,-45,0
RotateEntity mur_nord_est2_couloir3RingMairie,0,-45,0

sur_porte_Mairie_couloir3RingMairie=		CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,					20,H_lvl2#+HAUTEUR_PLAFOND#/2+1,38.5,			tex_mur_couloir$,1)
sous_porte_Ring_couloir3RingMairie=		CreerMur(0.01,H_stepRing#*2,1,							10,H_lvl2#+H_stepRing#,36,						tex_mur_couloir$,1)
sur_porte_Ring_couloir3RingMairie=			CreerMur(0.01,2*HAUTEUR_PLAFOND#-2-H_stepRing#*2,1,		10,H_lvl2#+HAUTEUR_PLAFOND#+1+H_stepRing#,36,		tex_mur_couloir$,1)


;.couloir4BoucleTuyaux
mur_ouest1_couloir4BoucleTuyaux=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,		31,H_lvl2#+HAUTEUR_PLAFOND#/2,7.5,			tex_mur_couloir$,1)
mur_ouest2_couloir4BoucleTuyaux=				CreerMur(0.01,HAUTEUR_PLAFOND#,8,		41,H_lvl2#+HAUTEUR_PLAFOND#/2,17,			tex_mur_couloir$,1)
mur_ouest3_couloir4BoucleTuyaux=				CreerMur(0.01,HAUTEUR_PLAFOND#,8,		27,H_lvl2#+HAUTEUR_PLAFOND#/2,28,			tex_mur_couloir$,1)
mur_est1_couloir4BoucleTuyaux=					CreerMur(0.01,HAUTEUR_PLAFOND#,5,		44,H_lvl2#+HAUTEUR_PLAFOND#/2,15.5,			tex_mur_couloir$,1)
mur_est2_couloir4BoucleTuyaux=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,		48,H_lvl2#+HAUTEUR_PLAFOND#/2,23.5,			tex_mur_couloir$,1)
mur_est3_couloir4BoucleTuyaux=					CreerMur(0.01,HAUTEUR_PLAFOND#,5,		30,H_lvl2#+HAUTEUR_PLAFOND#/2,26.5,			tex_mur_couloir$,1)
mur_est4_couloir4BoucleTuyaux=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,		33,H_lvl2#+HAUTEUR_PLAFOND#/2,34.5,			tex_mur_couloir$,1)
mur_nord1_couloir4BoucleTuyaux=					CreerMur(2,HAUTEUR_PLAFOND#,0.01,		40,H_lvl2#+HAUTEUR_PLAFOND#/2,13,			tex_mur_couloir$,1)
mur_nord2_couloir4BoucleTuyaux=					CreerMur(2,HAUTEUR_PLAFOND#,0.01,		28,H_lvl2#+HAUTEUR_PLAFOND#/2,32,			tex_mur_couloir$,1)
mur_nord3_couloir4BoucleTuyaux=					CreerMur(9,HAUTEUR_PLAFOND#,0.01,		37.5,H_lvl2#+HAUTEUR_PLAFOND#/2,33,			tex_mur_couloir$,1)
mur_sud1_couloir4BoucleTuyaux=					CreerMur(6,HAUTEUR_PLAFOND#,0.01,		34,H_lvl2#+HAUTEUR_PLAFOND#/2,6,			tex_mur_couloir$,1)
mur_sud2_couloir4BoucleTuyaux=					CreerMur(8,HAUTEUR_PLAFOND#,0.01,		45,H_lvl2#+HAUTEUR_PLAFOND#/2,10,			tex_mur_couloir$,1)
mur_sud3_couloir4BoucleTuyaux=					CreerMur(1,HAUTEUR_PLAFOND#,0.01,		44.5,H_lvl2#+HAUTEUR_PLAFOND#/2,18,			tex_mur_couloir$,1)
mur_sud4_couloir4BoucleTuyaux=					CreerMur(11,HAUTEUR_PLAFOND#,0.01,		36.5,H_lvl2#+HAUTEUR_PLAFOND#/2,30,			tex_mur_couloir$,1)

mur_nord_ouest1_couloir4BoucleTuyaux=			CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	37.01,H_lvl2#+HAUTEUR_PLAFOND#/2,10.99,			tex_mur_couloir$,1)
mur_sud_est1_couloir4BoucleTuyaux=				CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	39,H_lvl2#+HAUTEUR_PLAFOND#/2,8,			tex_mur_couloir$,1)
mur_nord_ouest2_couloir4BoucleTuyaux=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,	43.5,H_lvl2#+HAUTEUR_PLAFOND#/2,23.5,			tex_mur_couloir$,1)
mur_sud_est2_couloir4BoucleTuyaux=				CreerMur(5,HAUTEUR_PLAFOND#,0.01,		46.5,H_lvl2#+HAUTEUR_PLAFOND#/2,20,			tex_mur_couloir$,1)
mur_nord_ouest3_couloir4BoucleTuyaux=			CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	31,H_lvl2#+HAUTEUR_PLAFOND#/2,34,			tex_mur_couloir$,1)
mur_sud_est3_couloir4BoucleTuyaux=				CreerMur(1.41,HAUTEUR_PLAFOND#,0.01,	30.5,H_lvl2#+HAUTEUR_PLAFOND#/2,29.5,			tex_mur_couloir$,1)
mur_sud_est4_couloir4BoucleTuyaux=				CreerMur(1.41,HAUTEUR_PLAFOND#,0.01,	48.5,H_lvl2#+HAUTEUR_PLAFOND#/2,25.5,			tex_mur_couloir$,1)
mur_nord_est_couloir4BoucleTuyaux=				CreerMur(9.9,HAUTEUR_PLAFOND#,0.01,		45.5,H_lvl2#+HAUTEUR_PLAFOND#/2,29.5,			tex_mur_couloir$,1)
mur_sud_ouest_couloir4BoucleTuyaux=				CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	44,H_lvl2#+HAUTEUR_PLAFOND#/2,28,			tex_mur_couloir$,1)

RotateEntity mur_sud_ouest_couloir4BoucleTuyaux,0,-45,0
RotateEntity mur_nord_est_couloir4BoucleTuyaux,0,-45,0
RotateEntity mur_sud_est1_couloir4BoucleTuyaux,0,45,0
RotateEntity mur_nord_ouest1_couloir4BoucleTuyaux,0,45,0
RotateEntity mur_sud_est2_couloir4BoucleTuyaux,0,53,0
RotateEntity mur_nord_ouest2_couloir4BoucleTuyaux,0,45,0
RotateEntity mur_sud_est3_couloir4BoucleTuyaux,0,45,0
RotateEntity mur_nord_ouest3_couloir4BoucleTuyaux,0,45,0
RotateEntity mur_sud_est4_couloir4BoucleTuyaux,0,45,0


;.couloir5ChasseurForge
mur_ouest1_couloir5ChasseurForge=				CreerMur(0.01,HAUTEUR_PLAFOND#,16,		55,H_lvl2#+HAUTEUR_PLAFOND#/2,24,			tex_mur_couloir$,1)
mur_ouest2_couloir5ChasseurForge=				CreerMur(0.01,HAUTEUR_PLAFOND#,4,		45,H_lvl2#+HAUTEUR_PLAFOND#/2,38,			tex_mur_couloir$,1)
mur_ouest3_couloir5ChasseurForge=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,		36,H_lvl2#+HAUTEUR_PLAFOND#/2,39.5,			tex_mur_couloir$,1)
mur_ouest4_couloir5ChasseurForge=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,		36,H_lvl2#+HAUTEUR_PLAFOND#/2,37.5,			tex_mur_couloir$,1)

mur_est1_couloir5ChasseurForge=					CreerMur(0.01,HAUTEUR_PLAFOND#,1,		48,H_lvl2#+HAUTEUR_PLAFOND#/2,13.5,			tex_mur_couloir$,1)
mur_est2_couloir5ChasseurForge=					CreerMur(0.01,HAUTEUR_PLAFOND#,12,		52,H_lvl2#+HAUTEUR_PLAFOND#/2,24,			tex_mur_couloir$,1)
mur_est3_couloir5ChasseurForge=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,		42,H_lvl2#+HAUTEUR_PLAFOND#/2,35,			tex_mur_couloir$,1)
mur_sud1_couloir5ChasseurForge=					CreerMur(7,HAUTEUR_PLAFOND#,0.01,		45.5,H_lvl2#+HAUTEUR_PLAFOND#/2,33,			tex_mur_couloir$,1)
mur_sud2_couloir5ChasseurForge=					CreerMur(6,HAUTEUR_PLAFOND#,0.01,		39,H_lvl2#+HAUTEUR_PLAFOND#/2,37,			tex_mur_couloir$,1)
mur_nord1_couloir5ChasseurForge=				CreerMur(4,HAUTEUR_PLAFOND#,0.01,		46,H_lvl2#+HAUTEUR_PLAFOND#/2,13,			tex_mur_couloir$,1)
mur_nord2_couloir5ChasseurForge=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		51.5,H_lvl2#+HAUTEUR_PLAFOND#/2,18,			tex_mur_couloir$,1)
mur_nord3_couloir5ChasseurForge=				CreerMur(6,HAUTEUR_PLAFOND#,0.01,		48,H_lvl2#+HAUTEUR_PLAFOND#/2,36,			tex_mur_couloir$,1)
mur_nord4_couloir5ChasseurForge=				CreerMur(9,HAUTEUR_PLAFOND#,0.01,		40.5,H_lvl2#+HAUTEUR_PLAFOND#/2,40,			tex_mur_couloir$,1)
mur_nord_ouest_couloir5ChasseurForge=			CreerMur(5,HAUTEUR_PLAFOND#,0.01,		49.5,H_lvl2#+HAUTEUR_PLAFOND#/2,16,			tex_mur_couloir$,1)
mur_sud_est1_couloir5ChasseurForge=				CreerMur(1.41,HAUTEUR_PLAFOND#,0.01,	54.5,H_lvl2#+HAUTEUR_PLAFOND#/2,15.5,			tex_mur_couloir$,1)
mur_sud_est2_couloir5ChasseurForge=				CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	51,H_lvl2#+HAUTEUR_PLAFOND#/2,12,			tex_mur_couloir$,1)

mur_nord_est_couloir5ChasseurForge=				CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,	53,H_lvl2#+HAUTEUR_PLAFOND#/2,34,			tex_mur_couloir$,1)
mur_sud_ouest_couloir5ChasseurForge=				CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	50.5,H_lvl2#+HAUTEUR_PLAFOND#/2,31.5,			tex_mur_couloir$,1)

sur_porte_Forge_couloir5ChasseurForge=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,		36,H_lvl2#+HAUTEUR_PLAFOND#/2+1,38.5,			tex_mur_couloir$,1)
sur_porte_Chasseur_couloir5ChasseurForge=		CreerMur(1.41,HAUTEUR_PLAFOND#-2,0.01,	53.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,14.5,		tex_mur_couloir$,1)

RotateEntity mur_nord_ouest_couloir5ChasseurForge,0,53,0
RotateEntity mur_sud_est1_couloir5ChasseurForge,0,45,0
RotateEntity mur_nord_est_couloir5ChasseurForge,0,-45,0
RotateEntity mur_sud_ouest_couloir5ChasseurForge,0,-45,0
RotateEntity mur_sud_est2_couloir5ChasseurForge,0,45,0
RotateEntity sur_porte_Chasseur_couloir5ChasseurForge,0,45,0

;.couloir6VersMJ
mur_ouest_couloir6VersMJ=				CreerMur(0.01,HAUTEUR_PLAFOND#,2,		32,H_lvl2#+HAUTEUR_PLAFOND#/2,25,			tex_mur_couloir$,1)
mur_sud1_couloir6VersMJ=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		34.5,H_lvl2#+HAUTEUR_PLAFOND#/2,24.02,			tex_mur_couloir$,1)
mur_sud2_couloir6VersMJ=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		38.5,H_lvl2#+HAUTEUR_PLAFOND#/2,21.01,			tex_mur_couloir$,1)
mur_sud3_couloir6VersMJ=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		40.5,H_lvl2#+HAUTEUR_PLAFOND#/2,21.01,			tex_mur_couloir$,1)
mur_nord_couloir6VersMJ=				CreerMur(4,HAUTEUR_PLAFOND#,0.01,		34,H_lvl2#+HAUTEUR_PLAFOND#/2,26,			tex_mur_couloir$,1)
mur_nord_est_couloir6VersMJ=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,	38.5,H_lvl2#+HAUTEUR_PLAFOND#/2,23.5,			tex_mur_couloir$,1)
mur_sud_ouest_couloir6VersMJ=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	36.51,H_lvl2#+HAUTEUR_PLAFOND#/2,22.51,			tex_mur_couloir$,1)

RotateEntity mur_sud_ouest_couloir6VersMJ,0,-45,0
RotateEntity mur_nord_est_couloir6VersMJ,0,-45,0

sur_porte_couloir6VersMJ=				CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,		39.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,21.01,		tex_mur_couloir$,1)


;.tuyaux
Contour1_porte_sud_Tuyau1=					CollerImage(0,0,0,			21,H_lvl2#+1,9,		img_ContourSteamPorte$)
Contour2_porte_sud_Tuyau1=					CollerImage(0,180,0,		21,H_lvl2#+1,9,		img_ContourSteamPorte2$)
Contour3_porte_sud_Tuyau1=					CollerImage(0,0,0,			21,H_lvl2#+1,24,	img_ContourSteamPorte$)
Contour4_porte_sud_Tuyau1=					CollerImage(0,180,0,		21,H_lvl2#+1,24,	img_ContourSteamPorte2$)
Contour1_porte_sud_Tuyau2=					CollerImage(0,0,0,			25,H_lvl2#+1,9,		img_ContourSteamPorte$)
Contour2_porte_sud_Tuyau2=					CollerImage(0,180,0,		25,H_lvl2#+1,9,		img_ContourSteamPorte2$)
Contour3_porte_sud_Tuyau1=					CollerImage(0,0,0,			25,H_lvl2#+1,24,	img_ContourSteamPorte$)
Contour4_porte_sud_Tuyau1=					CollerImage(0,180,0,		25,H_lvl2#+1,24,	img_ContourSteamPorte2$)
Contour1_porte_sud_Tuyau3=					CollerImage(0,0,0,			29,H_lvl2#+1,9,		img_ContourSteamPorte$)
Contour2_porte_sud_Tuyau3=					CollerImage(0,180,0,		29,H_lvl2#+1,9,		img_ContourSteamPorte2$)
Contour3_porte_sud_Tuyau1=					CollerImage(0,0,0,			29,H_lvl2#+1,24,	img_ContourSteamPorte$)
Contour4_porte_sud_Tuyau1=					CollerImage(0,180,0,		29,H_lvl2#+1,24,	img_ContourSteamPorte2$)
Contour1_porte_sud_Tuyau4=					CollerImage(0,0,0,			33,H_lvl2#+1,9,		img_ContourSteamPorte$)
Contour2_porte_sud_Tuyau4=					CollerImage(0,180,0,		33,H_lvl2#+1,9,		img_ContourSteamPorte2$)
Contour3_porte_sud_Tuyau1=					CollerImage(0,0,0,			33,H_lvl2#+1,24,	img_ContourSteamPorte$)
Contour4_porte_sud_Tuyau1=					CollerImage(0,180,0,		33,H_lvl2#+1,24,	img_ContourSteamPorte2$)

Contour1Tuyau1_lvl2=					CreerTuyau(2.15,15,		20.8,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour2Tuyau1_lvl2=					CreerTuyau(2.2,15,		20.8,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour1Tuyau2_lvl2=					CreerTuyau(2.15,15,		24.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour2Tuyau2_lvl2=					CreerTuyau(2.2,15,		24.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour1Tuyau3_lvl2=					CreerTuyau(2.15,15,		28.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour2Tuyau3_lvl2=					CreerTuyau(2.2,15,		28.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour1Tuyau4_lvl2=					CreerTuyau(2.15,15,		32.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	
Contour2Tuyau4_lvl2=					CreerTuyau(2.2,15,		32.9,H_lvl2#+1.08,16.5,		tex_tuyaux$,1,0, 		"",0.98,		 type_mur,False)	

RotateEntity Contour1Tuyau1_lvl2,90,0,0
RotateEntity Contour2Tuyau1_lvl2,90,0,0
RotateEntity Contour1Tuyau2_lvl2,90,0,0
RotateEntity Contour2Tuyau2_lvl2,90,0,0
RotateEntity Contour1Tuyau3_lvl2,90,0,0
RotateEntity Contour2Tuyau3_lvl2,90,0,0
RotateEntity Contour1Tuyau4_lvl2,90,0,0
RotateEntity Contour2Tuyau4_lvl2,90,0,0
FlipMesh Contour1Tuyau1_lvl2
FlipMesh Contour1Tuyau2_lvl2
FlipMesh Contour1Tuyau3_lvl2
FlipMesh Contour1Tuyau4_lvl2

;.couloirlvl1

vitre_ouest_couloirlvl1=			CreerMur(0.01,HAUTEUR_PLAFOND#,9,			16,HAUTEUR_PLAFOND#/2,16.5,			tex_vitre$,1,0,		type_block)
vitre_est_couloirlvl1=				CreerMur(0.01,HAUTEUR_PLAFOND#,9,			38,HAUTEUR_PLAFOND#/2,16.5,			tex_vitre$,1,0,		type_block)
vitre_sud_couloirlvl1=				CreerMur(16,HAUTEUR_PLAFOND#,0.01,			27,HAUTEUR_PLAFOND#/2,9,			tex_vitre$,1,0,		type_block)
vitre_nord_couloirlvl1=				CreerMur(16,HAUTEUR_PLAFOND#,0.01,			27,HAUTEUR_PLAFOND#/2,24,			tex_vitre$,1,0,		type_block)
vitre_sud_est_couloirlvl1=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		36.5,HAUTEUR_PLAFOND#/2,10.5,		tex_vitre$,1,0,		type_block)
vitre_sud_ouest_couloirlvl1=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		17.5,HAUTEUR_PLAFOND#/2,10.5,		tex_vitre$,1,0,		type_block)
vitre_nord_est_couloirlvl1=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		36.5,HAUTEUR_PLAFOND#/2,22.5,		tex_vitre$,1,0,		type_block)
vitre_nord_ouest_couloirlvl1=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		17.5,HAUTEUR_PLAFOND#/2,22.5,		tex_vitre$,1,0,		type_block)

sur_vitre_ouest_couloirlvl1=			CreerMur(0.01,HAUTEUR_PLAFOND#*4,9,				16,HAUTEUR_PLAFOND#*6/2,16.5,				tex_sol_couloir$)
sur_vitre_est_couloirlvl1=				CreerMur(0.01,HAUTEUR_PLAFOND#*4,9,				38,HAUTEUR_PLAFOND#*6/2,16.5,				tex_sol_couloir$)

sur_vitre_sud1_couloirlvl1=				CreerMur(1,HAUTEUR_PLAFOND#*4,0.01,				19.5,HAUTEUR_PLAFOND#*6/2,9,				tex_sol_couloir$)
sous_tuyau_sud1_2_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		21,H_lvl2#/2+HAUTEUR_PLAFOND#/2,9,			tex_sol_couloir$)
sur_tuyau_sud1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	21,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,9,		tex_sol_couloir$)
sur_vitre_sud2_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				23,HAUTEUR_PLAFOND#*6/2,9,					tex_sol_couloir$)
sous_tuyau_sud2_3_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		25,H_lvl2#/2+HAUTEUR_PLAFOND#/2,9,			tex_sol_couloir$)
sur_tuyau_sud1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	25,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,9,		tex_sol_couloir$)
sur_vitre_sud3_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				27,HAUTEUR_PLAFOND#*6/2,9,					tex_sol_couloir$)
sous_tuyau_sud3_4_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		29,H_lvl2#/2+HAUTEUR_PLAFOND#/2,9,			tex_sol_couloir$)
sur_tuyau_sud1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	29,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,9,		tex_sol_couloir$)
sur_vitre_sud4_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				31,HAUTEUR_PLAFOND#*6/2,9,					tex_sol_couloir$)
sous_tuyau_sud4_5_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		33,H_lvl2#/2+HAUTEUR_PLAFOND#/2,9,			tex_sol_couloir$)
sur_tuyau_sud1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	33,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,9,		tex_sol_couloir$)
sur_vitre_sud5_couloirlvl1=				CreerMur(1,HAUTEUR_PLAFOND#*4,0.01,				34.5,HAUTEUR_PLAFOND#*6/2,9,				tex_sol_couloir$)

sur_vitre_nord1_couloirlvl1=				CreerMur(1,HAUTEUR_PLAFOND#*4,0.01,				19.5,HAUTEUR_PLAFOND#*6/2,24,				tex_sol_couloir$)
sous_tuyau_nord1_2_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		21,H_lvl2#/2+HAUTEUR_PLAFOND#/2,24,			tex_sol_couloir$)
sur_tuyau_nord1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	21,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,24,		tex_sol_couloir$)
sur_vitre_nord2_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				23,HAUTEUR_PLAFOND#*6/2,24,					tex_sol_couloir$)
sous_tuyau_nord2_3_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		25,H_lvl2#/2+HAUTEUR_PLAFOND#/2,24,			tex_sol_couloir$)
sur_tuyau_nord1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	25,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,24,		tex_sol_couloir$)
sur_vitre_nord3_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				27,HAUTEUR_PLAFOND#*6/2,24,					tex_sol_couloir$)
sous_tuyau_nord3_4_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		29,H_lvl2#/2+HAUTEUR_PLAFOND#/2,24,			tex_sol_couloir$)
sur_tuyau_nord1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	29,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,24,		tex_sol_couloir$)
sur_vitre_nord4_couloirlvl1=				CreerMur(2,HAUTEUR_PLAFOND#*4,0.01,				31,HAUTEUR_PLAFOND#*6/2,24,					tex_sol_couloir$)
sous_tuyau_nord4_5_couloirlvl1=			CreerMur(2,H_lvl2#-HAUTEUR_PLAFOND#,0.01,		33,H_lvl2#/2+HAUTEUR_PLAFOND#/2,24,			tex_sol_couloir$)
sur_tuyau_nord1_2_couloirlvl1=			CreerMur(2,HAUTEUR_PLAFOND#*5-H_lvl2#-2,0.01,	33,HAUTEUR_PLAFOND#*2.5+H_lvl2#/2+1,24,		tex_sol_couloir$)
sur_vitre_nord5_couloirlvl1=				CreerMur(1,HAUTEUR_PLAFOND#*4,0.01,				34.5,HAUTEUR_PLAFOND#*6/2,24,				tex_sol_couloir$)

;sur_vitre_nord_couloirlvl1=				CreerMur(16,HAUTEUR_PLAFOND#*4,0.01,		27,HAUTEUR_PLAFOND#*6/2,24,			tex_sol_couloir$)

sur_vitre_sud_est_couloirlvl1=			CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		36.5,HAUTEUR_PLAFOND#*6/2,10.5,		tex_sol_couloir$)
sur_vitre_sud_ouest_couloirlvl1=		CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		17.5,HAUTEUR_PLAFOND#*6/2,10.5,		tex_sol_couloir$)
sur_vitre_nord_est_couloirlvl1=			CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		36.5,HAUTEUR_PLAFOND#*6/2,22.5,		tex_sol_couloir$)
sur_vitre_nord_ouest_couloirlvl1=		CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		17.5,HAUTEUR_PLAFOND#*6/2,22.5,		tex_sol_couloir$)


EntityAlpha vitre_ouest_couloirlvl1,0.25
EntityAlpha vitre_sud_couloirlvl1,0.25
EntityAlpha vitre_nord_couloirlvl1,0.25
EntityAlpha vitre_est_couloirlvl1,0.25
EntityAlpha vitre_sud_ouest_couloirlvl1,0.25
EntityAlpha vitre_sud_est_couloirlvl1,0.25
EntityAlpha vitre_nord_ouest_couloirlvl1,0.25
EntityAlpha vitre_nord_est_couloirlvl1,0.25

RotateEntity vitre_nord_est_couloirlvl1,0,-45,0
RotateEntity vitre_nord_ouest_couloirlvl1,0,45,0
RotateEntity vitre_sud_est_couloirlvl1,0,45,0
RotateEntity vitre_sud_ouest_couloirlvl1,0,-45,0
RotateEntity sur_vitre_nord_est_couloirlvl1,0,-45,0
RotateEntity sur_vitre_nord_ouest_couloirlvl1,0,45,0
RotateEntity sur_vitre_sud_est_couloirlvl1,0,45,0
RotateEntity sur_vitre_sud_ouest_couloirlvl1,0,-45,0


;;.champs
;LE THE champs
sol_champs1=					CreerMur(0.01,22,15,		27,0.002,16.5,				tex_sol_champs$,4,1,		type_sol(1,1))
RotateEntity sol_champs1,0,0,90
Fontaine_tete=					CollerImage(0,-90,0,		37.9,H_step#,16.5,			img_tete_fontaine$,0.75)
Fontaine_base=LoadMesh("objets\fontaine\Fountain N071210.3DS")
PositionEntity Fontaine_base, 27,1.4,16.5
ScaleEntity Fontaine_base,0.007,0.007,0.007
EntityTexture Fontaine_base,Texture_mur_fontaine


;.entreeEscalier;eE

sol1_eE=					CreerMur(0.01,3,12,					14.5,H_lvl2#+0.002,18,						tex_sol_eE$,2,1,		type_sol(1,1))
sol2_eE=					CreerMur(0.01,3,3,					17.5,H_lvl2#+0.002,25.5,					tex_sol_eE$,2,1,		type_sol(1,1))
sol3_eE=					CreerMur(0.01,4.25,4.25,			16,H_lvl2#+0.001,24,						tex_sol_eE$,2,1,		type_sol(1,1))

plafond1_eE=				CreerMur(0.01,3,12,					14.5,H_lvl2#+HAUTEUR_PLAFOND#-0.001,18,				tex_plafond_eE$,2,1,		type_sol(1,1))
plafond2_eE=				CreerMur(0.01,3,3,					17.5,H_lvl2#+HAUTEUR_PLAFOND#-0.001,25.5,			tex_plafond_eE$,2,1,		type_sol(1,1))
plafond3_eE=				CreerMur(0.01,4.25,4.25,			16,H_lvl2#+HAUTEUR_PLAFOND#,24,						tex_plafond_eE$,2,1,		type_sol(1,1))


mur_sud1_eE=			CreerMur(1,HAUTEUR_PLAFOND#,0.01,		13.5,H_lvl2#+HAUTEUR_PLAFOND#/2,12.01,			tex_mur_eE$,1)
mur_sud2_eE=			CreerMur(1,HAUTEUR_PLAFOND#,0.01,		15.5,H_lvl2#+HAUTEUR_PLAFOND#/2,12.01,			tex_mur_eE$,1)
mur_nord_eE=			CreerMur(3,HAUTEUR_PLAFOND#,0.01,		17.5,H_lvl2#+HAUTEUR_PLAFOND#/2,26.99,			tex_mur_eE$,1)
mur_est1_eE=			CreerMur(0.01,HAUTEUR_PLAFOND#,3,		18.99,H_lvl2#+HAUTEUR_PLAFOND#/2,25.5,			tex_mur_eE$,1)
mur_est2_eE=			CreerMur(0.01,HAUTEUR_PLAFOND#,9,		15.99,H_lvl2#+HAUTEUR_PLAFOND#/2,16.5,			tex_mur_eE$,1)
mur_ouest_eE=			CreerMur(0.01,HAUTEUR_PLAFOND#,12,		13.01,H_lvl2#+HAUTEUR_PLAFOND#/2,18,			tex_mur_eE$,1)
mur_sud_est_eE=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	17.49,H_lvl2#+HAUTEUR_PLAFOND#/2,22.51,			tex_mur_eE$,1)
mur_nord_ouest1_eE=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	14.51,H_lvl2#+HAUTEUR_PLAFOND#/2,25.49,			tex_mur_eE$,1)
mur_nord_ouest2_eE=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,	14.5,H_lvl2#+1.5*HAUTEUR_PLAFOND#,25.5,			tex_mur_couloir$,1)

sur_porte_c1FE_eE=				CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,			14.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,12.01,		tex_mur_eE$,1)

RotateEntity sol1_eE,0,0,90
RotateEntity sol2_eE,0,0,90
RotateEntity sol3_eE,0,-45,90
RotateEntity plafond1_eE,0,0,90
RotateEntity plafond2_eE,0,0,90
RotateEntity plafond3_eE,0,-45,90
RotateEntity mur_sud_est_eE,0,45,0;
RotateEntity mur_nord_ouest1_eE,0,45,0
RotateEntity mur_nord_ouest2_eE,0,45,0

trape_escalier2=			CollerImage(90,0,135,	17.3,H_lvl2#+0.05,25.3,			img_trape_escalier$,1)
ScaleSprite trape_escalier2,1,2

;.Eglise
sol1_Eglise=					CreerMur(0.01,7,5,							6.5,H_lvl2#+0.002,19.5,									tex_sol_Eglise$,2,1,		type_sol(1,1))
sol2_Eglise=					CreerMur(0.01,4.94,4.94,					6.5,H_lvl2#+0.001,22,									tex_sol_Eglise$,2,1,		type_sol(1,1))

plafond1_Eglise=					CreerMur(0.01,7,5,							6.5,H_lvl2#+HAUTEUR_PLAFOND#-0.001,19.5,			tex_plafond_Eglise$,2,1,		type_sol(1,1))
plafond2_Eglise=					CreerMur(0.01,4.94,4.94,					6.5,H_lvl2#+HAUTEUR_PLAFOND#,22,					tex_plafond_Eglise$,2,1,		type_sol(1,1))


mur_nord_est_Eglise=			CreerMur(4.94,HAUTEUR_PLAFOND#,0.01,		8.24,H_lvl2#+HAUTEUR_PLAFOND#/2,23.74,		tex_mur_Eglise$,1)
mur_nord_ouest_Eglise=			CreerMur(4.94,HAUTEUR_PLAFOND#,0.01,		4.76,H_lvl2#+HAUTEUR_PLAFOND#/2,23.74,		tex_mur_Eglise$,1)
mur_ouest_Eglise=				CreerMur(0.01,HAUTEUR_PLAFOND#,4,			3.01,H_lvl2#+HAUTEUR_PLAFOND#/2,20,			tex_mur_Eglise$,1)
mur_est_Eglise=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,			9.99,H_lvl2#+HAUTEUR_PLAFOND#/2,20,			tex_mur_Eglise$,1)
mur_sud_Eglise=					CreerMur(7,HAUTEUR_PLAFOND#,0.01,			6.5,H_lvl2#+HAUTEUR_PLAFOND#/2,17,			tex_mur_Eglise$,1)

sur_porte1_c1FE_Eglise=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,			3.01,H_lvl2#+HAUTEUR_PLAFOND#/2+1,17.5,		tex_mur_Eglise$,1)
sur_porte2_c1FE_Eglise=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,			9.99,H_lvl2#+HAUTEUR_PLAFOND#/2+1,17.5,		tex_mur_Eglise$,1)


RotateEntity mur_nord_ouest_Eglise,0,45,0
RotateEntity mur_nord_est_Eglise,0,-45,0
RotateEntity sol1_Eglise,0,0,90
RotateEntity sol2_Eglise,0,-45,90
RotateEntity plafond1_Eglise,0,0,90
RotateEntity plafond2_Eglise,0,-45,90

;.Saloon
sol1_Saloon=					CreerMur(0.01,16,3,			8,H_lvl2#+0.002,1.5,			tex_sol_Saloon$,1,1,		type_sol(1,1))
sol2_Saloon=					CreerMur(0.01,3,5,			9.5,H_lvl2#+0.002,5.5,			tex_sol_Saloon$,1,1,		type_sol(1,1))
sol3_Saloon=					CreerMur(0.01,7.07,3.53,	6.75,H_lvl2#+0.001,4.25,		tex_sol_Saloon$,1,1,		type_sol(1,1))
sol4_Saloon=					CreerMur(0.01,3.53,7.07,	12.25,H_lvl2#+0.001,4.25,		tex_sol_Saloon$,1,1,		type_sol(1,1))
plafond1_Saloon=				CreerMur(0.01,16,3,			8,H_lvl2#+HAUTEUR_PLAFOND#,1.5,			tex_plafond_Saloon$,2,1,		type_sol(1,1))
plafond2_Saloon=				CreerMur(0.01,3,5,			9.5,H_lvl2#++HAUTEUR_PLAFOND#,5.5,		tex_plafond_Saloon$,2,1,		type_sol(1,1))
plafond3_Saloon=				CreerMur(0.01,7.07,3.53,	6.75,H_lvl2#+HAUTEUR_PLAFOND#+0.001,4.25,		tex_plafond_Saloon$,2,1,		type_sol(1,1))
plafond4_Saloon=				CreerMur(0.01,3.53,7.07,	12.25,H_lvl2#+HAUTEUR_PLAFOND#+0.002,4.25,		tex_plafond_Saloon$,2,1,		type_sol(1,1))
mur_nord1_Saloon=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			0.5,H_lvl2#+HAUTEUR_PLAFOND#/2,2.99,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_nord2_Saloon=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			2.5,H_lvl2#+HAUTEUR_PLAFOND#/2,2.99,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_nord3_Saloon=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,			9.5,H_lvl2#+HAUTEUR_PLAFOND#/2,7.99,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_sud_Saloon=					CreerMur(16,HAUTEUR_PLAFOND#,0.01,			8,H_lvl2#+HAUTEUR_PLAFOND#/2,0,				tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_est_Saloon=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,			15.99,H_lvl2#+HAUTEUR_PLAFOND#/2,1.5,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_ouest_Saloon=				CreerMur(0.01,HAUTEUR_PLAFOND#,3,			0,H_lvl2#+HAUTEUR_PLAFOND#/2,1.5,			tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_nord_est_Saloon=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,		13.49,H_lvl2#+HAUTEUR_PLAFOND#/2,5.49,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
mur_nord_ouest_Saloon=			CreerMur(7.07,HAUTEUR_PLAFOND#,0.01,		5.51,H_lvl2#+HAUTEUR_PLAFOND#/2,5.49,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)
sur_porte_Saloon_c1FE=			CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,			1.5,H_lvl2#+HAUTEUR_PLAFOND#/2+1,2.99,		tex_mur_Saloon$,HAUTEUR_PLAFOND#)


RotateEntity sol1_Saloon,0,0,90
RotateEntity sol2_Saloon,0,0,90
RotateEntity sol3_Saloon,0,45,90
RotateEntity sol4_Saloon,0,45,90
RotateEntity plafond1_Saloon,0,0,90
RotateEntity plafond2_Saloon,0,0,90
RotateEntity plafond3_Saloon,0,45,90
RotateEntity plafond4_Saloon,0,45,90
RotateEntity mur_nord_ouest_Saloon,0,45,0
RotateEntity mur_nord_est_Saloon,0,-45,0

table1_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
table2_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
table3_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
table4_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
table5_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
table6_Saloon=LoadMesh("objets\tables_chaises\DESK2.3DS")
piano_Saloon=LoadMesh("objets\piano\piano_01.3DS")
ScaleEntity table1_Saloon,0.03,0.03,0.03
ScaleEntity table2_Saloon,0.03,0.03,0.03
ScaleEntity table3_Saloon,0.03,0.03,0.03
ScaleEntity table4_Saloon,0.03,0.03,0.03
ScaleEntity table5_Saloon,0.03,0.03,0.03
ScaleEntity table6_Saloon,0.03,0.03,0.03
ScaleEntity piano_Saloon,0.03,0.03,0.03
PositionEntity table1_Saloon,8.71,H_lvl2#,3
PositionEntity table2_Saloon,9.6,H_lvl2#,3
PositionEntity table3_Saloon,4.2,H_lvl2#,3.1
PositionEntity table4_Saloon,8.71,H_lvl2#,6.5
PositionEntity table5_Saloon,9.6,H_lvl2#,6.5
PositionEntity table6_Saloon,6.2,H_lvl2#,5.1
PositionEntity piano_Saloon,13.4,H_lvl2#,5.0
RotateEntity piano_Saloon,0,-45,0
RotateEntity table3_Saloon,0,-140,0
RotateEntity table6_Saloon,0,-140,0

texture_table=LoadTexture(tex_table$)
EntityTexture table1_Saloon, texture_table
EntityTexture table2_Saloon, texture_table
EntityTexture table3_Saloon, texture_table
EntityTexture table4_Saloon, texture_table
EntityTexture table5_Saloon, texture_table
EntityTexture table6_Saloon, texture_table


;.Mairie
sol1_Mairie=					CreerMur(0.01,3,5,			21.5,H_lvl2#+0.002,37.5,			tex_sol_Mairie$,2,1,		type_sol(1,1))
sol2_Mairie=					CreerMur(0.01,4,11,			25,H_lvl2#+0.002,34.5,			tex_sol_Mairie$,2,1,		type_sol(1,1))
sol3_Mairie=					CreerMur(0.01,6,4,			30,H_lvl2#+0.002,38,			tex_sol_Mairie$,2,1,		type_sol(1,1))
sol4_Mairie=					CreerMur(0.01,2,4,			28,H_lvl2#+0.002,34,			tex_sol_Mairie$,2,1,		type_sol(1,1))
sol5_Mairie=					CreerMur(0.01,5.65,5.65,	29,H_lvl2#+0.001,36,			tex_sol_Mairie$,2,1,		type_sol(1,1))
sol6_Mairie=					CreerMur(0.01,4.25,4.25,	23,H_lvl2#+0.001,35,			tex_sol_Mairie$,2,1,		type_sol(1,1))
plafond1_Mairie=					CreerMur(0.01,3,5,			21.5,H_lvl2#+HAUTEUR_PLAFOND#+0.002,37.5,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
plafond2_Mairie=					CreerMur(0.01,4,11,			25,H_lvl2#+HAUTEUR_PLAFOND#+0.002,34.5,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
plafond3_Mairie=					CreerMur(0.01,6,4,			30,H_lvl2#+HAUTEUR_PLAFOND#+0.002,38,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
plafond4_Mairie=					CreerMur(0.01,2,4,			28,H_lvl2#+HAUTEUR_PLAFOND#+0.002,34,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
plafond5_Mairie=					CreerMur(0.01,5.65,5.65,	29,H_lvl2#+HAUTEUR_PLAFOND#+0.001,36,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
plafond6_Mairie=					CreerMur(0.01,4.25,4.25,	23,H_lvl2#+HAUTEUR_PLAFOND#+0.001,35,			tex_plafond_Mairie$,2,1,		type_sol(1,1))
mur_sud1_Mairie=					CreerMur(1,HAUTEUR_PLAFOND#,0.01,			23.5,H_lvl2#+HAUTEUR_PLAFOND#/2,29,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_sud2_Mairie=					CreerMur(1,HAUTEUR_PLAFOND#,0.01,			26.5,H_lvl2#+HAUTEUR_PLAFOND#/2,29,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_sud3_Mairie=					CreerMur(2.01,HAUTEUR_PLAFOND#,0.01,		28,H_lvl2#+HAUTEUR_PLAFOND#/2,32.01,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_nord_Mairie=					CreerMur(13,HAUTEUR_PLAFOND#,0.01,			26.5,H_lvl2#+HAUTEUR_PLAFOND#/2,40,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_ouest1_Mairie=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,			23,H_lvl2#+HAUTEUR_PLAFOND#/2,30.5,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_ouest2_Mairie=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,			20.01,H_lvl2#+HAUTEUR_PLAFOND#/2,36.5,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_ouest3_Mairie=					CreerMur(0.01,HAUTEUR_PLAFOND#,1,			20.01,H_lvl2#+HAUTEUR_PLAFOND#/2,39.5,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_est1_Mairie=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,			26.99,H_lvl2#+HAUTEUR_PLAFOND#/2,30.5,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_est2_Mairie=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,			33,H_lvl2#+HAUTEUR_PLAFOND#/2,38,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_sud_est_Mairie=					CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,		30.99,H_lvl2#+HAUTEUR_PLAFOND#/2,34.01,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
mur_sud_ouest_Mairie=				CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		21.5,H_lvl2#+HAUTEUR_PLAFOND#/2,33.5,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)


sur_porte_couloir3RingMairie_Mairie=		CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,					20,H_lvl2#+HAUTEUR_PLAFOND#/2+1,38.5,		tex_mur_Mairie$,HAUTEUR_PLAFOND#)
sur_porte_Tuyau2_Mairie=					CreerMur(2,HAUTEUR_PLAFOND#-2,0.01,					25,H_lvl2#+HAUTEUR_PLAFOND#/2+1,29,			tex_mur_Mairie$,HAUTEUR_PLAFOND#)
sur_porte_Mairie_Tuyau2=					CreerMur(2,HAUTEUR_PLAFOND#-2,0.01,					25,H_lvl2#+HAUTEUR_PLAFOND#/2+1,28.99,		tex_mur_couloir$,1)
mur_sud1_Tuyau2=							CreerMur(1,HAUTEUR_PLAFOND#,0.01,					23.5,H_lvl2#+HAUTEUR_PLAFOND#/2,28.99,		tex_mur_couloir$,1)
mur_sud2_Tuyau2=							CreerMur(1,HAUTEUR_PLAFOND#,0.01,					26.5,H_lvl2#+HAUTEUR_PLAFOND#/2,28.99,		tex_mur_couloir$,1)


RotateEntity sol1_Mairie,0,0,90
RotateEntity sol2_Mairie,0,0,90
RotateEntity sol3_Mairie,0,0,90
RotateEntity sol4_Mairie,0,0,90
RotateEntity sol5_Mairie,0,45,90
RotateEntity sol6_Mairie,0,45,90
RotateEntity plafond1_Mairie,0,0,90
RotateEntity plafond2_Mairie,0,0,90
RotateEntity plafond3_Mairie,0,0,90
RotateEntity plafond4_Mairie,0,0,90
RotateEntity plafond5_Mairie,0,45,90
RotateEntity plafond6_Mairie,0,45,90
RotateEntity mur_sud_ouest_Mairie,0,-45,0
RotateEntity mur_sud_est_Mairie,0,45,0


voteuse_mairie=LoadMesh("objets\Voteuse\SMachine.3DS")
chaise1_mairie=LoadMesh("objets\tables_chaises\CHAIR16.X")
chaise2_mairie=LoadMesh("objets\tables_chaises\CHAIR16.X")
chaise3_mairie=LoadMesh("objets\tables_chaises\CHAIR16.X")
SuperTable1_mairie=LoadMesh("objets\superTable\Table N160511.3DS")

PositionEntity voteuse_mairie,31.0,H_lvl2#,39.4
PositionEntity chaise1_mairie,23.8,H_lvl2#+0.45,34.3
PositionEntity chaise2_mairie,23.1,H_lvl2#+0.45,36.1
PositionEntity chaise3_mairie,23.6,H_lvl2#+0.45,35.3
PositionEntity SuperTable1_mairie,22.4,H_lvl2#+0.01,34.5


ScaleEntity voteuse_mairie,0.023,0.023,0.023
ScaleEntity SuperTable1_mairie,0.005,0.007,0.007


RotateEntity chaise1_mairie,0,-64,0
RotateEntity chaise2_mairie,0,-53,0
RotateEntity chaise3_mairie,0,-60,0
RotateEntity SuperTable1_mairie,0,-175,0

;CreerLumiereRond(200,200,220,		25,35,H_lvl2#+1.4,	2)

texture_superTable=LoadTexture(tex_supertable$)
texture_voteuse=LoadTexture(tex_voteuse$)
EntityTexture SuperTable1_mairie, texture_superTable
EntityTexture voteuse_mairie, texture_voteuse

;.CaserneChasseur
sol1_CaserneChasseur=					CreerMur(0.01,2,15,			54,H_lvl2#+0.002,7.5,				tex_sol_CaserneChasseur$,2,1,		type_sol(1,1))
sol2_CaserneChasseur=					CreerMur(0.01,4,10,			51,H_lvl2#+0.002,5,					tex_sol_CaserneChasseur$,2,1,		type_sol(1,1))
sol3_CaserneChasseur=					CreerMur(0.01,5.65,2.82,	52,H_lvl2#+0.001,11,				tex_sol_CaserneChasseur$,2,1,		type_sol(1,1))
plafond1_CaserneChasseur=					CreerMur(0.01,2,15,			54,H_lvl2#+HAUTEUR_PLAFOND#+0.002,7.5,				tex_plafond_CaserneChasseur$,2,1,		type_sol(1,1))
plafond2_CaserneChasseur=					CreerMur(0.01,4,10,			51,H_lvl2#+HAUTEUR_PLAFOND#+0.002,5,				tex_plafond_CaserneChasseur$,2,1,		type_sol(1,1))
plafond3_CaserneChasseur=					CreerMur(0.01,5.65,2.82,	52,H_lvl2#+HAUTEUR_PLAFOND#+0.001,11,			tex_plafond_CaserneChasseur$,2,1,		type_sol(1,1))

mur_sud_CaserneChasseur=					CreerMur(6,HAUTEUR_PLAFOND#,0.01,			52,H_lvl2#+HAUTEUR_PLAFOND#/2,0,			tex_mur_CaserneChasseur$,1)
mur_est_CaserneChasseur=					CreerMur(0.01,HAUTEUR_PLAFOND#,15,			55,H_lvl2#+HAUTEUR_PLAFOND#/2,7.5,			tex_mur_CaserneChasseur$,1)
mur_ouest1_CaserneChasseur=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,			49,H_lvl2#+HAUTEUR_PLAFOND#/2,2,			tex_mur_CaserneChasseur$,1)
mur_ouest2_CaserneChasseur=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,			49,H_lvl2#+HAUTEUR_PLAFOND#/2,8,			tex_mur_CaserneChasseur$,1)
mur_nord_ouest_CaserneChasseur=				CreerMur(5.65,HAUTEUR_PLAFOND#,0.01,		51.01,H_lvl2#+HAUTEUR_PLAFOND#/2,11.99,	tex_mur_CaserneChasseur$,1)
mur_nord_CaserneChasseur=					CreerMur(1,HAUTEUR_PLAFOND#,0.01,			54.5,H_lvl2#+HAUTEUR_PLAFOND#/2,15,			tex_mur_CaserneChasseur$,1)

sur_porte_CaserneChasseur_Labyrinthe=						CreerMur(0.01,HAUTEUR_PLAFOND#*2-2,2,			49,H_lvl2#+HAUTEUR_PLAFOND#/2+1,5,			tex_mur_CaserneChasseur$,1)
sur_porte_couloir5ChasseurForge_CaserneChasseur=		CreerMur(1.41,HAUTEUR_PLAFOND#-2,0.01,		53.51,H_lvl2#+HAUTEUR_PLAFOND#/2+1,14.49,		tex_mur_CaserneChasseur$,1)


RotateEntity sol1_CaserneChasseur,0,0,90
RotateEntity sol2_CaserneChasseur,0,0,90
RotateEntity sol3_CaserneChasseur,0,45,90
RotateEntity plafond1_CaserneChasseur,0,0,90
RotateEntity plafond2_CaserneChasseur,0,0,90
RotateEntity plafond3_CaserneChasseur,0,45,90
RotateEntity mur_nord_ouest_CaserneChasseur,0,45,0
RotateEntity sur_porte_couloir5ChasseurForge_CaserneChasseur,0,45,0

;.Labyrinthe		
sol1_Labyrinthe=						CreerMur(0.01,6,6,			38,H_lvl2#+0.002,3,								tex_sol_Labyrinthe$,2,1,		type_sol(1,1))
sol2_Labyrinthe=						CreerMur(0.01,8,10,			45,H_lvl2#+0.002,5,								tex_sol_Labyrinthe$,2,1,		type_sol(1,1))
sol3_Labyrinthe=						CreerMur(0.01,6.25,2.82,	39.79,H_lvl2#+0.001,6.8,						tex_sol_Labyrinthe$,2,1,		type_sol(1,1))
sol_pente1_Labyrinthe=					CreerMur(0.01,2.48,6.11,	45.25,H_lvl2#+HAUTEUR_PLAFOND#/2,5,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
sol_pente2_Labyrinthe=					CreerMur(0.01,1.99,6.11,	36,H_lvl2#+HAUTEUR_PLAFOND#/2,2.5,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))

plafond1_Labyrinthe=					CreerMur(0.01,4,6,			39,H_lvl2#+HAUTEUR_PLAFOND#+0.002,3,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond2_Labyrinthe=					CreerMur(0.01,3,10,			42.5,H_lvl2#+HAUTEUR_PLAFOND#+0.002,5,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond3_Labyrinthe=					CreerMur(0.01,6.25,2.82,	39.79,H_lvl2#+HAUTEUR_PLAFOND#+0.001,6.8,			tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond4_Labyrinthe=					CreerMur(0.01,2.5,10,		47.75,H_lvl2#+HAUTEUR_PLAFOND#+0.002,5,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond5_Labyrinthe=					CreerMur(0.01,2.5,2.5,		45.25,H_lvl2#+HAUTEUR_PLAFOND#+0.002,8.75,			tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond6_Labyrinthe=					CreerMur(0.01,2.5,2.5,		45.25,H_lvl2#+HAUTEUR_PLAFOND#+0.002,1.25,			tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond_pente1_Labyrinthe=				CreerMur(0.01,2.48,6.11,	45.25,H_lvl2#+HAUTEUR_PLAFOND#*3/2,5,				tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond1_in_Labyrinthe=					CreerMur(0.01,6,6,			38,H_lvl2#+HAUTEUR_PLAFOND#*2,3,					tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond2_in_Labyrinthe=					CreerMur(0.01,8,10,			45,H_lvl2#+HAUTEUR_PLAFOND#*2,5,					tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))
plafond3_in_Labyrinthe=					CreerMur(0.01,6.25,2.82,	39.79,H_lvl2#+HAUTEUR_PLAFOND#*2+0.01,6.8,			tex_plafond_Labyrinthe$,2,1,		type_sol(1,1))


mur_nord1_Labyrinthe=					CreerMur(8,HAUTEUR_PLAFOND#*2,0.01,			45,H_lvl2#+HAUTEUR_PLAFOND#,9.99,			tex_mur_Labyrinthe$,1)
mur_nord2_Labyrinthe=					CreerMur(2.01,HAUTEUR_PLAFOND#*2,0.01,		36,H_lvl2#+HAUTEUR_PLAFOND#,5.99,			tex_mur_Labyrinthe$,1)
mur_sud_Labyrinthe=						CreerMur(14,HAUTEUR_PLAFOND#*2,0.01,		42,H_lvl2#+HAUTEUR_PLAFOND#,0,			tex_mur_Labyrinthe$,1)
mur_est1_Labyrinthe=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,4,			48.99,H_lvl2#+HAUTEUR_PLAFOND#,2,			tex_mur_Labyrinthe$,1)
mur_est2_Labyrinthe=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,4,			48.99,H_lvl2#+HAUTEUR_PLAFOND#,8,			tex_mur_Labyrinthe$,1)
mur_ouest_Labyrinthe=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,6,			35.01,H_lvl2#+HAUTEUR_PLAFOND#,3,			tex_mur_Labyrinthe$,1)
mur_nord_ouest_Labyrinthe=				CreerMur(5.67,HAUTEUR_PLAFOND#*2,0.01,		39.01,H_lvl2#+HAUTEUR_PLAFOND#,7.99,		tex_mur_Labyrinthe$,1)
mur_in_sud1_Labyrinthe=					CreerMur(5,HAUTEUR_PLAFOND#,0.01,			44,H_lvl2#+HAUTEUR_PLAFOND#/2,2.5,		tex_mur_Labyrinthe$,1)
mur_in_sud2_Labyrinthe=					CreerMur(2.5,HAUTEUR_PLAFOND#/2,0.01,		45.25,H_lvl2#+HAUTEUR_PLAFOND#/4,5,		tex_mur_Labyrinthe$,1)
mur_in_ouest1_Labyrinthe=				CreerMur(0.01,HAUTEUR_PLAFOND#,5,			37,H_lvl2#+HAUTEUR_PLAFOND#/2,2.5,		tex_mur_Labyrinthe$,1)
mur_in_ouest2_Labyrinthe=				CreerMur(0.01,HAUTEUR_PLAFOND#,8,			39,H_lvl2#+HAUTEUR_PLAFOND#/2,4,		tex_mur_Labyrinthe$,1)
mur_in_ouest3_Labyrinthe=				CreerMur(0.01,HAUTEUR_PLAFOND#,2.5,			44,H_lvl2#+HAUTEUR_PLAFOND#/2,6.25,		tex_mur_Labyrinthe$,1)
mur_in_est1_Labyrinthe=					CreerMur(0.01,HAUTEUR_PLAFOND#,7.5,			41.5,H_lvl2#+HAUTEUR_PLAFOND#/2,6.25,		tex_mur_Labyrinthe$,1)
mur_in_est2_Labyrinthe=					CreerMur(0.01,HAUTEUR_PLAFOND#,2.5,			46.5,H_lvl2#+HAUTEUR_PLAFOND#/2,6.25,		tex_mur_Labyrinthe$,1)
mur_in_up_ouest1_Labyrinthe=			CreerMur(0.01,HAUTEUR_PLAFOND#-0.6,4,		46.49,H_lvl2#+HAUTEUR_PLAFOND#,5,			tex_mur_Labyrinthe$,1)
mur_in_up_ouest2_Labyrinthe=			CreerMur(0.01,HAUTEUR_PLAFOND#,5,			37,H_lvl2#+HAUTEUR_PLAFOND#*3/2,3.5,		tex_mur_Labyrinthe$,1)
mur_in_up_est1_Labyrinthe=				CreerMur(0.01,HAUTEUR_PLAFOND#-0.6,4,		44.01,H_lvl2#+HAUTEUR_PLAFOND#,5,		tex_mur_Labyrinthe$,1)
mur_in_up_est2_Labyrinthe=				CreerMur(0.01,HAUTEUR_PLAFOND#-0.6,1,		38,H_lvl2#+HAUTEUR_PLAFOND#*3/2,0.5,		tex_mur_Labyrinthe$,1)



sur_porte_Labyrinthe_CaserneChasseur=						CreerMur(0.01,2*HAUTEUR_PLAFOND#-2,2,			48.99,H_lvl2#+HAUTEUR_PLAFOND#+1,5,			tex_mur_Labyrinthe$,1)


RotateEntity sol1_Labyrinthe,0,0,90
RotateEntity sol2_Labyrinthe,0,0,90
RotateEntity sol3_Labyrinthe,0,45,90
RotateEntity sol_pente1_Labyrinthe,35,0,90
RotateEntity sol_pente2_Labyrinthe,35,0,90
RotateEntity plafond_pente1_Labyrinthe,35,0,90
RotateEntity plafond1_Labyrinthe,0,0,90
RotateEntity plafond2_Labyrinthe,0,0,90
RotateEntity plafond3_Labyrinthe,0,45,90
RotateEntity plafond4_Labyrinthe,0,0,90
RotateEntity plafond5_Labyrinthe,0,0,90
RotateEntity plafond6_Labyrinthe,0,0,90
RotateEntity mur_nord_ouest_Labyrinthe,0,45,0
RotateEntity mur_in_up_ouest1_Labyrinthe,35,0,0
RotateEntity mur_in_up_est1_Labyrinthe,35,0,0
RotateEntity plafond1_in_Labyrinthe,0,0,90
RotateEntity plafond2_in_Labyrinthe,0,0,90
RotateEntity plafond3_in_Labyrinthe,0,45,90



;.Forge
sol1_Forge=						CreerMur(0.01,3,3,			34.5,H_lvl2#+0.002,38.5,				tex_sol_Forge$,2,1,		type_sol(1,1))
sol2_Forge=						CreerMur(0.01,9,4,			37.5,H_lvl2#+0.002,35,					tex_sol_Forge$,2,1,		type_sol(1,1))
plafond1_Forge=					CreerMur(0.01,3,3,			34.5,H_lvl2#+HAUTEUR_PLAFOND#+0.002,38.5,				tex_plafond_Forge$,2,1,		type_sol(1,1))
plafond2_Forge=					CreerMur(0.01,9,4,			37.5,H_lvl2#+HAUTEUR_PLAFOND#+0.002,35,					tex_plafond_Forge$,2,1,		type_sol(1,1))
mur_sud_Forge=					CreerMur(9,HAUTEUR_PLAFOND#,0.01,			37.5,H_lvl2#++HAUTEUR_PLAFOND#/2,33.01,			tex_mur_Forge$,1)
mur_nord1_Forge=				CreerMur(6,HAUTEUR_PLAFOND#,0.01,			39,H_lvl2#+HAUTEUR_PLAFOND#/2,36.99,				tex_mur_Forge$,1)
mur_nord2_Forge=				CreerMur(3,HAUTEUR_PLAFOND#,0.01,			34.5,H_lvl2#+HAUTEUR_PLAFOND#/2,40,				tex_mur_Forge$,1)
mur_ouest_Forge=				CreerMur(0.01,HAUTEUR_PLAFOND#,7,			33.01,H_lvl2#+HAUTEUR_PLAFOND#/2,36.5,				tex_mur_Forge$,1)
mur_est1_Forge=					CreerMur(0.01,HAUTEUR_PLAFOND#,4,			41.99,H_lvl2#+HAUTEUR_PLAFOND#/2,35,				tex_mur_Forge$,1)
mur_est2_Forge=					CreerMur(0.01,HAUTEUR_PLAFOND#,1,			35.99,H_lvl2#+HAUTEUR_PLAFOND#/2,39.5,				tex_mur_Forge$,1)
mur_est3_Forge=					CreerMur(0.01,HAUTEUR_PLAFOND#,1,			35.99,H_lvl2#+HAUTEUR_PLAFOND#/2,37.5,				tex_mur_Forge$,1)

sur_porte_Forge_couloir5ChasseurForge=			CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,		35.99,H_lvl2#+HAUTEUR_PLAFOND#/2+1,38.5,			tex_mur_Forge$,1)

RotateEntity sol1_Forge,0,0,90
RotateEntity sol2_Forge,0,0,90
RotateEntity plafond1_Forge,0,0,90
RotateEntity plafond2_Forge,0,0,90


;.Ring
mur_nord_Ring=					CreerMur(7,HAUTEUR_PLAFOND#*2,0.01,			3.5,H_lvl2#+HAUTEUR_PLAFOND#,40,			tex_mur_Ring$,taille_tex_Ring#)
mur_sud_Ring=					CreerMur(3,HAUTEUR_PLAFOND#*2,0.01,			5.5,H_lvl2#+HAUTEUR_PLAFOND#,30.01,			tex_mur_Ring$,taille_tex_Ring#)

mur_ouest_Ring=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,7,			0,H_lvl2#+HAUTEUR_PLAFOND#,36.5,			tex_mur_Ring$,taille_tex_Ring#)
mur_est1_Ring=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,0.5,		9.99,H_lvl2#+HAUTEUR_PLAFOND#,36.75,			tex_mur_Ring$,taille_tex_Ring#)
mur_est2_Ring=					CreerMur(0.01,HAUTEUR_PLAFOND#*2,2.5,		9.99,H_lvl2#+HAUTEUR_PLAFOND#,34.25,			tex_mur_Ring$,taille_tex_Ring#)
mur_nord_est_Ring=				CreerMur(4.25,HAUTEUR_PLAFOND#*2,0.01,		8.49,H_lvl2#+HAUTEUR_PLAFOND#,38.49,			tex_mur_Ring$,taille_tex_Ring#)
mur_sud_ouest1_Ring=			CreerMur(1.41,HAUTEUR_PLAFOND#*2,0.01,		0.51,H_lvl2#+HAUTEUR_PLAFOND#,32.51,			tex_mur_Ring$,taille_tex_Ring#)
mur_sud_ouest2_Ring=			CreerMur(1.41,HAUTEUR_PLAFOND#*2,0.01,		2.51,H_lvl2#+HAUTEUR_PLAFOND#,30.51,			tex_mur_Ring$,taille_tex_Ring#)
mur_sud_est1_Ring=				CreerMur(1.41,HAUTEUR_PLAFOND#*2,0.01,		7.49,H_lvl2#+HAUTEUR_PLAFOND#,30.51,			tex_mur_Ring$,taille_tex_Ring#)
mur_sud_est2_Ring=				CreerMur(1.41,HAUTEUR_PLAFOND#*2,0.01,		9.49,H_lvl2#+HAUTEUR_PLAFOND#,32.51,			tex_mur_Ring$,taille_tex_Ring#)

sur_porte_sud_est_Ring=			CreerMur(1.42,HAUTEUR_PLAFOND#*2-2.5,0.01,					8.49,H_lvl2#+HAUTEUR_PLAFOND#+1.25,31.51,				tex_mur_Ring$,taille_tex_Ring#)
sur_porte_est_Ring=				CreerMur(0.01,H_stepRing#*2,1,								9.99,H_lvl2#+H_stepRing#,36,							tex_mur_Ring$,taille_tex_Ring#)
sous_porte_est_Ring=			CreerMur(0.01,HAUTEUR_PLAFOND#*2-2-H_stepRing#*2,1,			9.99,H_lvl2#+HAUTEUR_PLAFOND#+H_stepRing#+1,36,			tex_mur_Ring$,taille_tex_Ring#)
sur_porte_sud_ouest_Ring=		CreerMur(1.42,HAUTEUR_PLAFOND#*2-2-H_stepRing#*4,0.01,		1.51,H_lvl2#+HAUTEUR_PLAFOND#+H_stepRing#*2+1,31.51,		tex_mur_Ring$,taille_tex_Ring#)
sous_porte_sud_ouest_Ring=		CreerMur(1.42,H_stepRing#*4,0.01,							1.51,H_lvl2#+H_stepRing#*2,31.51,						tex_mur_Ring$,taille_tex_Ring#)
sur_porte_sud_Ring=				CreerMur(1,HAUTEUR_PLAFOND#*2-2,0.01,					3.5,H_lvl2#+HAUTEUR_PLAFOND#+1,30.01,					tex_mur_Ring$,taille_tex_Ring#)



faux_mur_step2_Ring=						CreerMur(1.41,H_stepRing#,1.40,			9,H_lvl2#+H_stepRing#*0.5,33.04,				"",2,1,		type_block)
faux_mur_step3_Ring=						CreerMur(1.41,H_stepRing#,4.24,			9,H_lvl2#+H_stepRing#*1.5,35.04,				"",2,1,		type_block)
faux_mur_step4_Ring=						CreerMur(1.41,H_stepRing#,4.24,			8,H_lvl2#+H_stepRing#*2.5,36.04,				"",2,1,		type_block)
faux_mur_step5_Ring=						CreerMur(4.24,H_stepRing#,4.24,			6,H_lvl2#+H_stepRing#*3.5,38.04,				"",2,1,		type_block)
faux_mur_step6_Ring=						CreerMur(1.41,H_stepRing#,4.24,			4,H_lvl2#+H_stepRing#*2.5,40.04,				"",2,1,		type_block)
faux_mur_step7_Ring=						CreerMur(1.41,H_stepRing#,4.24,			3,H_lvl2#+H_stepRing#*1.5,41.04,				"",2,1,		type_block)
faux_mur_step2b_Ring=						CreerMur(1.41,H_stepRing#,1.40,			6.96,H_lvl2#+H_stepRing#*0.5,31.06,				"",2,1,		type_block)
faux_mur_step3b_Ring=						CreerMur(1.41,H_stepRing#,4.24,			4.96,H_lvl2#+H_stepRing#*1.5,31.06,				"",2,1,		type_block)
faux_mur_step4b_Ring=						CreerMur(1.41,H_stepRing#,4.24,			3.96,H_lvl2#+H_stepRing#*2.5,32.06,				"",2,1,		type_block)
faux_mur_step5b_Ring=						CreerMur(4.24,H_stepRing#,4.24,			1.96,H_lvl2#+H_stepRing#*3.5,34.06,				"",2,1,		type_block)
faux_mur_step6b_Ring=						CreerMur(1.41,H_stepRing#,4.24,			-0.04,H_lvl2#+H_stepRing#*2.5,36.06,				"",2,1,		type_block)
faux_mur_step7b_Ring=						CreerMur(1.41,H_stepRing#,4.24,			-1.04,H_lvl2#+H_stepRing#*1.5,37.06,				"",2,1,		type_block)
sol_step2_Ring=								CreerMur(0.01,1.41,1.40,				9,H_lvl2#+H_stepRing#,33.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step3_Ring=								CreerMur(0.01,1.41,4.24,				9,H_lvl2#+H_stepRing#*2,35.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step4_Ring=								CreerMur(0.01,1.41,4.24,				8,H_lvl2#+H_stepRing#*3,36.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step5_Ring=								CreerMur(0.01,4.24,4.24,				6,H_lvl2#+H_stepRing#*4,38.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step6_Ring=								CreerMur(0.01,1.41,3.24,				4,H_lvl2#+H_stepRing#*3,40.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step7_Ring=								CreerMur(0.01,1.41,2.24,				3,H_lvl2#+H_stepRing#*2,41.04,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step2b_Ring=								CreerMur(0.01,1.41,1.40,			6.96,H_lvl2#+H_stepRing#,31.06,					tex_sol_step_Ring$,1.5,4,		type_none)
sol_step3b_Ring=								CreerMur(0.01,1.41,4.24,			4.96,H_lvl2#+H_stepRing#*2,31.06,				tex_sol_step_Ring$,1.5,4,		type_none)
sol_step4b_Ring=								CreerMur(0.01,1.41,4.24,			3.96,H_lvl2#+H_stepRing#*3,32.06,				tex_sol_step_Ring$,1.5,4,		type_none)
sol_step5b_Ring=								CreerMur(0.01,4.24,4.24,			1.96,H_lvl2#+H_stepRing#*4,34.06,				tex_sol_step_Ring$,1.5,4,		type_none)
sol_step6b_Ring=								CreerMur(0.01,1.41,4.24,			-0.04,H_lvl2#+H_stepRing#*3,36.06,				tex_sol_step_Ring$,1.5,4,		type_none)
sol_step7b_Ring=								CreerMur(0.01,1.41,4.24,			-1.04,H_lvl2#+H_stepRing#*2,37.06,				tex_sol_step_Ring$,1.5,4,		type_none)
sol_step5t_Ring=								CreerMur(0.01,2.5,1.42,				3.96,H_lvl2#+H_stepRing#*4,36.05,				tex_sol_step_Ring$,1.5,4,		type_block)



rampe1_Ring=					CreerMur(0.01,7,1.41,			6.87,H_lvl2#+H_stepRing#*2,33.16,				tex_rampe_Ring$,1.5,4,		type_sol(1,1))
rampe2_Ring=					CreerMur(0.01,5,1.41,			1.63,H_lvl2#+H_stepRing#*2.5+0.1,38.4,			tex_rampe_Ring$,1.5,4,		type_sol(1,1))


RotateEntity faux_mur_step2_Ring,0,-45,0
RotateEntity faux_mur_step3_Ring,0,-45,0
RotateEntity faux_mur_step4_Ring,0,-45,0
RotateEntity faux_mur_step5_Ring,0,-45,0
RotateEntity faux_mur_step6_Ring,0,-45,0
RotateEntity faux_mur_step7_Ring,0,-45,0
RotateEntity faux_mur_step2b_Ring,0,-45,0
RotateEntity faux_mur_step3b_Ring,0,-45,0
RotateEntity faux_mur_step4b_Ring,0,-45,0
RotateEntity faux_mur_step5b_Ring,0,-45,0
RotateEntity faux_mur_step6b_Ring,0,-45,0
RotateEntity faux_mur_step7b_Ring,0,-45,0
RotateEntity sol_step2_Ring,0,-45,90
RotateEntity sol_step3_Ring,0,-45,90
RotateEntity sol_step4_Ring,0,-45,90
RotateEntity sol_step5_Ring,0,-45,90
RotateEntity sol_step6_Ring,0,-45,90
RotateEntity sol_step7_Ring,0,-45,90
RotateEntity sol_step2b_Ring,0,-45,90
RotateEntity sol_step3b_Ring,0,-45,90
RotateEntity sol_step4b_Ring,0,-45,90
RotateEntity sol_step5b_Ring,0,-45,90
RotateEntity sol_step6b_Ring,0,-45,90
RotateEntity sol_step7b_Ring,0,-45,90
RotateEntity sol_step5t_Ring,0,-45,90
RotateEntity mur_nord_est_Ring,0,-45,0
RotateEntity mur_sud_ouest1_Ring,0,-45,0
RotateEntity mur_sud_ouest2_Ring,0,-45,0
RotateEntity mur_sud_est1_Ring,0,45,0
RotateEntity mur_sud_est2_Ring,0,45,0
RotateEntity sur_porte_sud_est_Ring,0,45,0
RotateEntity sur_porte_sud_ouest_Ring,0,-45,0
RotateEntity sous_porte_sud_ouest_Ring,0,-45,0

RotateEntity rampe1_Ring,0,-45,56
RotateEntity rampe2_Ring,0,-45,-56

EntityAlpha faux_mur_step2_Ring,0
EntityAlpha faux_mur_step3_Ring,0
EntityAlpha faux_mur_step4_Ring,0
EntityAlpha faux_mur_step5_Ring,0
EntityAlpha faux_mur_step6_Ring,0
EntityAlpha faux_mur_step7_Ring,0
EntityAlpha faux_mur_step2b_Ring,0
EntityAlpha faux_mur_step3b_Ring,0
EntityAlpha faux_mur_step4b_Ring,0
EntityAlpha faux_mur_step5b_Ring,0
EntityAlpha faux_mur_step6b_Ring,0
EntityAlpha faux_mur_step7b_Ring,0






;.Personnage

Emanuella=LoadMD2("objets\Emanuella\tris.md2")
tex_ema=LoadTexture("objets\Emanuella\ema.pcx")
EntityTexture Emanuella,tex_ema
ScaleEntity Emanuella,0.028,0.028,0.028
PositionEntity Emanuella,22,H_lvl2#+0.68,34
TurnEntity Emanuella,0,-90,0
AnimateMD2 Emanuella,1,0.1,0,40

Arsene=LoadMD2("objets\Arsene\tris.md2")
tex_Arsene=LoadTexture("objets\Arsene\Arsene.pcx")
EntityTexture Arsene,tex_Arsene
ScaleEntity Arsene,0.032,0.032,0.032
PositionEntity Arsene,10.2,H_lvl2#+0.78,6.5
AnimateMD2 Arsene,1,0.05,0,20

;Secretaire_mairie=LoadMD2("objets\Standardiste\tris.md2")
;tex_Secretaire=LoadTexture("objets\Standardiste\Standardiste.pcx")
;EntityTexture Secretaire_mairie,tex_Secretaire
;ScaleEntity Secretaire_mairie,0.028,0.028,0.028
;PositionEntity Secretaire_mairie,8.8,0.6,27.8
;TurnEntity Secretaire_mairie,0,-170,0
;AnimateMD2 Secretaire_mairie,1,0.1,115,121



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Rajout Nico

Select entrance
	Case 1 ; en venant du RdC
		pos_entrance#(1)=16
		pos_entrance#(2)=7.7
		pos_entrance#(3)=24
	Default
		pos_entrance#(1)=16
		pos_entrance#(2)=7.7
		pos_entrance#(3)=24
End Select





























