;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;USINE : ACCUEIL;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;.DEFINITION_CONSTANTES
HAUTEUR_PLAFOND#			=3.50
H_step#						=1.25
H_stair#					=H_step#/8.0

;;.CHARGEMENT_TEXTURE
tex_cuivre1$				="textures/loran/Cuivre_neuf.bmp"
tex_sol_couloir$				="textures/loran/MarbleWhite2.jpg"
tex_sol_accueil$				="textures/loran/FloorBoisBleu.jpg"
tex_sol_champs$					="textures/loran/Champs.jpg"
tex_sol_usine$					="textures/loran/sol_usine.jpg"
tex_sol_passerelle$				="textures/loran/passerelle.bmp"
tex_sol_poste$					="textures/loran/WoodWall2.jpg"


tex_plafond_couloir$			="textures/loran/tapisserie1.bmp"
;tex_plafond_ciel$				="objets/canyon/canyon.jpg"
tex_sol_canyon$					="objets/canyon/canyon.jpg"
tex_plafond_accueil$			="textures/loran/WoodWall3.jpg"
tex_plafond_usine$				="textures/loran/toit_usine1.jpg"
tex_plafond_poste$				="textures/loran/WoodWall3.jpg"


tex_mur_garage$       			="textures/loran/WoodWall3.jpg" 
tex_mur_accueil$       			="textures/loran/papier-peint2.jpg"
tex_mur_usine$					="textures/loran/mur_usine.jpg"
tex_mur_fontaine$				="textures/loran/BALMORAL.JPG"
tex_mur_infirmerie$				="textures/loran/mur_infirmerie.jpg"
tex_mur_sdp$					="textures/loran/papier-peint3.jpg";"textures/loran/MarbreMulticolor.jpg"
tex_mur_poste$					="textures/loran/papier-peint1.jpg"

tex_porte_parking$    			="textures/loran/Porte_Parking_1.jpg"
tex_dale_couloir$				="textures/loran/MarbleWhite.jpg"
tex_tablette_murale$			="textures/loran/MarbleWhite2.jpg"
tex_supertable$					="textures/loran/velour_petit.jpg"
tex_porte_sortie1$				="textures/loran/DoorsWood1.jpg"
tex_vitre$						="textures/loran/blanc.jpg"
tex_porte_armure$				="textures/loran/METAL.BMP"
tex_porte_arme$					="textures/loran/blanc.jpg";"textures/loran/MarbreMulticolor.jpg"
tex_porte_uc$					="textures/loran/MetalDoors3.JPG"
tex_porte_poste$				="textures/loran/DoorsMetal2.jpg"

tex_tapis_roulant$				="textures/loran/tapis_roulant.jpg"
tex_bord_tapis_roulant$			="textures/loran/METAL.BMP"
tex_tuyau$						="textures/loran/MetalDoors3.JPG"
tex_table$						="objets/tables_chaises/chair_02.jpg"
tex_labo$						="textures/loran/METAL.BMP"
tex_contour_porte$				="textures/loran/METAL.BMP"

img_dale_couloir$				="textures/loran/MarbleWhite.jpg"
img_tete_fontaine$				="sprites/loran/tête_fontaine.bmp"
img_rideau$						="sprites/loran/rideau.bmp"
img_symbole_pharmacie$			="sprites/loran/logo_pharmacie.bmp"
img_trape_escalier$				="sprites/loran/DoorsMetal1.bmp"
img_message$					="sprites/loran/Message Parchemin.jpg"
img_ContourSteamPorte$			="sprites/loran/porte ascenceur.bmp"
img_ContourSteamPorte2$			="sprites/loran/porte ascenceur2.bmp"


Texture_porte_sortie1=LoadTexture(tex_porte_sortie1$)
Texture_mur_fontaine=LoadTexture(tex_mur_fontaine$)
Texture_porte_armure=LoadTexture(tex_porte_armure$)
Texture_porte_arme=LoadTexture(tex_porte_arme$)
Texture_porte_poste=LoadTexture(tex_porte_poste$)
texture_labo=LoadTexture(tex_labo$)

;.CREATION_MONDE

;ciel type canyon
canyonMap=LoadMesh("objets\canyon\Canyon.x")
tex=LoadTexture(tex_sol_canyon$)
EntityTexture canyonMap,tex
ScaleEntity canyonMap,0.06,0.02,0.06
TurnEntity canyonMap,180,0,0
PositionEntity canyonMap,20,HAUTEUR_PLAFOND#*4,0
EntityType canyonMap,3

; SOL (et mur invisible):
;.sol
sol=					CreerMur(0.01,55,40,		27.5,0,20,				tex_sol_couloir$,2,1,		type_sol(1,1))
RotateEntity sol,0,0,90
EntityPickMode sol,2	
;ciel=					CreerMur(0.01,10,10,		27,HAUTEUR_PLAFOND#*3,16.5,				tex_plafond_ciel$)
;RotateEntity ciel,0,0,90
;EntityFX ciel,1

sol_escalier=			CreerMur(1,8.25,1.4,		    15.2,HAUTEUR_PLAFOND#/2-0.4,25.2,				"",2,1,		type_sol(2,1))
RotateEntity sol_escalier,0,45,-65	
mur_escalier=			CreerMur(1.5,8,0.01,		    14.25,HAUTEUR_PLAFOND#/2+0.6,25,				"",2,1,		type_block)
mur_escalier2=			CreerMur(2.0,1,0.01,		    15.45,HAUTEUR_PLAFOND#-1.0,24.9,				"",2,1,		type_block)
RotateEntity mur_escalier,0,45,-65
RotateEntity mur_escalier2,0,-45,0
mur_fauteuil1=			CreerMur(1,1,5.5,		    13.5,0.5,32.3,				"",2,1,		type_block)
mur_fauteuil2=			CreerMur(1,1,5.5,		    15.5,0.5,32.3,				"",2,1,		type_block)
mur_table_accueil=		CreerMur(3.5,1,5.5,		    9.5,0.5,29.1,				"",2,1,		type_block)
mur_armoire=			CreerMur(1.5,2.7,0.5,		12,1.4,36.7,				"",2,1,		type_block)
mur_lit1=				CreerMur(2,1,5.9,		    18.2,0.6,33.7,				"",2,1,		type_block)
mur_lit2=				CreerMur(2,1,1,		  	  	18.8,0.6,30.4,				"",2,1,		type_block)
mur_lit3=				CreerMur(2,1,6.5,		    24.5,0.46,33.3,				"",2,1,		type_block)
mur_porte_armure1=		CreerMur(11,2,0.5,		    31.5,2.42,39.6,				"",2,1,		type_block)
mur_porte_armure2=		CreerMur(0.5,2,11,		    35.6,2.42,33.8,				"",2,1,		type_block)
mur_table_sdp=			CreerMur(2,1,3,			    31,1.6,33.6,				"",2,1,		type_block)
mur_silo_uc=			CreerMur(3.5,4.4,11,		53,2.2,5.7,					"",2,1,		type_block)
mur_silo2_uc=			CreerMur(3.5,4.4,11,		53,2.2,21.3,				"",2,1,		type_block)
mur_engrenage1_uc=		CreerMur(0.75,1.5,0.75,			52.5,0.7,15.7,				"",2,1,		type_block)
mur_engrenage2_uc=		CreerMur(0.75,1.5,0.75,			51,3.78,24.6,				"",2,1,		type_block)
mur_engrenage3_uc=		CreerMur(0.75,1.5,0.75,			46,0.7,10.6,				"",2,1,		type_block)
mur_alambic1_uc=		CreerMur(6,2,3,				24.5,1,1.5,				"",2,1,		type_block)
mur_alambic2_uc=		CreerMur(2,2,1,				24.4,1,3,				"",2,1,		type_block)
mur_garde1=				CreerMur(0.75,1.5,0.75,			5,0.7,1,				"",2,1,		type_block)
mur_garde2=				CreerMur(0.75,1.5,0.75,			5,0.7,3,				"",2,1,		type_block)
mur_protecteur1=		CreerMur(0.75,1.5,0.75,			35.3,2.0,35.2,				"",2,1,		type_block)
mur_protecteur2=		CreerMur(0.75,1.5,0.75,			28,2.0,38,				"",2,1,		type_block)
mur_protecteur3=		CreerMur(0.75,1.5,0.75,			29.6,2.0,33.2,				"",2,1,		type_block)
mur_Stan=				CreerMur(0.75,1.5,0.75,			31.1,2.0,31.4,				"",2,1,		type_block)


mur_fauteuil_uc=					CreerMur(0.75,0.5,4,			32,0.25,2,				"",2,1,		type_block)

mur_pente_corridor_rampe1=			CreerMur(7,0.1,2.8,			16.5,H_stair#*2+0.3,9.5,				"",2,1,		type_sol(2,1))
mur_pente_corridor_rampe2=			CreerMur(7,0.1,2.8,			16.5,H_stair#*2+0.3,23.5,				"",2,1,		type_sol(2,1))
mur_pente_corridor_rampe3=			CreerMur(7,0.1,2.8,			37.5,H_step#/2+H_stair#*2+0.9,9.5,		"",2,1,		type_sol(2,1))
mur_pente_corridor_rampe4=			CreerMur(7,0.1,2.8,			37.5,H_step#/2+H_stair#*2+0.9,23.5,		"",2,1,		type_sol(2,1))
mur_pente1_passelle_uc=				CreerMur(2,0.1,2.5,			45,H_step#+H_stair#*4+0.2,23.6,				"",2,1,		type_sol(2,1))
mur_pente2_passelle_uc=				CreerMur(4.5,0.1,2,			41.9,H_stair#*4+0.2,25.5,				"",2,1,		type_sol(2,1))
mur_pente3_passelle_uc=				CreerMur(2.0,0.1,2,			46.1,2*H_step#+H_stair#*1.5,12.5,				"",2,1,		type_sol(2,1))
mur_pente4_passelle_uc=				CreerMur(2.0,0.1,2,			49.9,2*H_step#+H_stair#*1.5,12.5,				"",2,1,		type_sol(2,1))

RotateEntity mur_pente_corridor_rampe1,0,-45,10
RotateEntity mur_pente_corridor_rampe2,0,45,10
RotateEntity mur_pente_corridor_rampe3,0,45,10
RotateEntity mur_pente_corridor_rampe4,0,-45,10
RotateEntity mur_pente1_passelle_uc,18,0,0
RotateEntity mur_pente2_passelle_uc,0,0,18
RotateEntity mur_pente3_passelle_uc,0,0,21
RotateEntity mur_pente4_passelle_uc,0,0,-21

RotateEntity mur_lit1,0,11.3,0
;passerelle5_uc=						CreerMur(0.01,1,2,		46.5,2*H_step#+H_stair#*2,12.5,		tex_sol_passerelle$,1,4,		type_none)
;passerelle6_uc=						CreerMur(0.01,1,2,		49.5,2*H_step#+H_stair#*2,12.5,		tex_sol_passerelle$,1,4,		type_none)


EntityAlpha mur_pente1_passelle_uc,0
EntityAlpha mur_pente2_passelle_uc,0
EntityAlpha mur_pente3_passelle_uc,0
EntityAlpha mur_pente4_passelle_uc,0
EntityAlpha mur_pente_corridor_rampe1,0
EntityAlpha mur_pente_corridor_rampe2,0
EntityAlpha mur_pente_corridor_rampe3,0
EntityAlpha mur_pente_corridor_rampe4,0
EntityAlpha mur_fauteuil_uc,0


EntityAlpha mur_escalier,0
EntityAlpha mur_escalier2,0
EntityAlpha sol_escalier,0
EntityAlpha mur_fauteuil1,0
EntityAlpha mur_fauteuil2,0
EntityAlpha mur_table_accueil,0
EntityAlpha mur_armoire,0
EntityAlpha mur_porte_armure1,0
EntityAlpha mur_porte_armure2,0
EntityAlpha mur_lit1,0
EntityAlpha mur_lit2,0
EntityAlpha mur_lit3,0
EntityAlpha mur_table_sdp,0
EntityAlpha mur_silo2_uc,0
EntityAlpha mur_silo_uc,0
EntityAlpha mur_engrenage1_uc,0
EntityAlpha mur_engrenage2_uc,0
EntityAlpha mur_engrenage3_uc,0
EntityAlpha mur_alambic1_uc,0
EntityAlpha mur_alambic2_uc,0
EntityAlpha mur_garde1,0
EntityAlpha mur_garde2,0
EntityAlpha mur_Stan,0
EntityAlpha mur_protecteur1,0
EntityAlpha mur_protecteur2,0
EntityAlpha mur_protecteur3,0

RotateEntity mur_alambic1_uc,0,12,0
RotateEntity mur_alambic2_uc,0,12,0

;.boite_au_lettre
mur_ouest_boite_au_lettre=		CreerMur(0.01,HAUTEUR_PLAFOND#,12,					0,HAUTEUR_PLAFOND#/2,6,								tex_mur_garage$,3)
mur_est_boite_au_lettre=		CreerMur(0.01,HAUTEUR_PLAFOND#,8,					4,HAUTEUR_PLAFOND#/2,8,								tex_mur_garage$,3)
mur_sud_boite_au_lettre=		CreerMur(11,HAUTEUR_PLAFOND#,0.01,					5.5,HAUTEUR_PLAFOND#/2,0,							tex_mur_garage$,3)
porte_nord_boite_au_lettre=		CreerMur(4,HAUTEUR_PLAFOND#,0.01,					2,HAUTEUR_PLAFOND#/2,12,							tex_porte_parking$,1,0)
porte_est1_boite_au_lettre=		CreerMur(0.04,HAUTEUR_PLAFOND#,2,					4,HAUTEUR_PLAFOND#/2,1,								tex_porte_sortie1$,HAUTEUR_PLAFOND#,0)
porte_est2_boite_au_lettre=		CreerMur(0.04,HAUTEUR_PLAFOND#,2,					4,HAUTEUR_PLAFOND#/2,3,								tex_porte_sortie1$,HAUTEUR_PLAFOND#,0)

RotateEntity porte_est2_boite_au_lettre,0,0,180
;porte_distributeur=				CreerMur(3,HAUTEUR_PLAFOND#+2*H_stair#,0.01,		12.5,HAUTEUR_PLAFOND#/2+H_stair#,10,				tex_porte_parking$,1,0,	type_block)
EntityTexture porte_est1_boite_au_lettre,Texture_porte_sortie1
EntityTexture porte_est2_boite_au_lettre,Texture_porte_sortie1

;POSITION DES PORTES OUVERTES
;PositionEntity porte_est1_boite_au_lettre,3,HAUTEUR_PLAFOND#/2,0.02
;PositionEntity porte_est2_boite_au_lettre,3,HAUTEUR_PLAFOND#/2,3.98
;RotateEntity porte_est1_boite_au_lettre,0,90,0
;RotateEntity porte_est2_boite_au_lettre,0,90,180

;.couloir
mur_sud1_couloir=				CreerMur(4,HAUTEUR_PLAFOND#,0.01,			6,HAUTEUR_PLAFOND#/2,4,							tex_mur_garage$,3 )
mur_est1_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#,10,			11,HAUTEUR_PLAFOND#/2,5,						tex_mur_garage$,3 )
mur_ouest12_couloir_temp=		CreerMur(0.01,HAUTEUR_PLAFOND#,8.16,		8,HAUTEUR_PLAFOND#/2,8.08,						tex_mur_garage$,3 )
mur_ouest3_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#,5.1,			8,HAUTEUR_PLAFOND#/2,17.47,						tex_mur_garage$,3 )
sur_mur_ouest23_couloir=		CreerMur(0.01,HAUTEUR_PLAFOND#-2.7,3.5,		7.99,HAUTEUR_PLAFOND#/2+1.35,13.5,				tex_mur_garage$,3 )
mur_nord1_couloir=				CreerMur(2,HAUTEUR_PLAFOND#,0.01,			12,HAUTEUR_PLAFOND#/2,20,						tex_mur_garage$,3 )
mur_nord2_couloir=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,			8.5,HAUTEUR_PLAFOND#/2,20,						tex_mur_garage$,3 )
 
;mur_ouest4_couloir=			CreerMur(0.01,HAUTEUR_PLAFOND#,2,			14,HAUTEUR_PLAFOND#/2,9,						tex_mur_garage$,3 )
mur_ouest5_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#,2,			13,HAUTEUR_PLAFOND#/2,21,						tex_mur_garage$,3 )
mur_ouest6_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#,1,			11,HAUTEUR_PLAFOND#/2,10.5,						tex_mur_garage$,3 )


plafond_centre1_couloir=		CreerMur(0.01,5,10,						10.5,HAUTEUR_PLAFOND#,15,			tex_plafond_accueil$,3)
plafond_centre2_couloir=		CreerMur(0.01,5,10,						10.5,HAUTEUR_PLAFOND#,5,			tex_plafond_accueil$,3)
plafond_centre3_couloir=		CreerMur(0.01,4,4,						6,HAUTEUR_PLAFOND#,2,				tex_plafond_accueil$,3)

RotateEntity plafond_centre1_couloir,0,0,90
RotateEntity plafond_centre2_couloir,0,0,90
RotateEntity plafond_centre3_couloir,0,0,90

;autour de la cour intérieur
mur_nord3_couloir=				CreerMur(6.8,HAUTEUR_PLAFOND#+H_step#,0.01,			21.4,(HAUTEUR_PLAFOND#+H_step#)/2,27,				tex_mur_garage$,3 )
mur_nord_sur_porte1_couloir=	CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,					25.3,2+H_step#+(HAUTEUR_PLAFOND#-2)/2,27,				tex_mur_garage$,3 )
mur_nord4_couloir=				CreerMur(5.7,HAUTEUR_PLAFOND#+H_step#,0.01,			28.65,(HAUTEUR_PLAFOND#+H_step#)/2,27,					tex_mur_garage$,3 )
mur_nord_sur_porte2_couloir=	CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,					32,2+H_step#+(HAUTEUR_PLAFOND#-2)/2,27,				tex_mur_garage$,3 )
mur_nord5_couloir=				CreerMur(3.5,HAUTEUR_PLAFOND#+H_step#,0.01,			34.25,(HAUTEUR_PLAFOND#+H_step#)/2,27,				tex_mur_garage$,3 )
mur_sud2_sur_porte3_couloir=	CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,					21.5,HAUTEUR_PLAFOND#/2+H_step#+1,6,				tex_mur_garage$,3 )
mur_sud2_couloir=				CreerMur(14,HAUTEUR_PLAFOND#+H_step#,0.01,			29,(HAUTEUR_PLAFOND#+H_step#)/2,6,				tex_mur_garage$,3 )
mur_sud3_couloir=				CreerMur(3,HAUTEUR_PLAFOND#+H_step#,0.01,			19.5,(HAUTEUR_PLAFOND#+H_step#)/2,6,				tex_mur_garage$,3 )

mur_est2_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,5,			41,HAUTEUR_PLAFOND#/2+H_step#,13.5,				tex_mur_garage$,3 )
mur_est3_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,5,			41,HAUTEUR_PLAFOND#/2+H_step#,19.5,				tex_mur_garage$,3 )
mur_est_sur_porte_couloir=		CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,					41,H_step#*2+HAUTEUR_PLAFOND#/2+1,16.5,			tex_mur_garage$,3 )
mur_est_sous_porte_couloir=		CreerMur(0.01,2*H_step#,1,							41,H_step#,16.5,								tex_mur_garage$,3 )
mur_nord_est_couloir=			CreerMur(7.07,HAUTEUR_PLAFOND#+2*H_step#,0.01,		38.5,HAUTEUR_PLAFOND#/2+H_step#,24.5,			tex_mur_garage$,3 )
mur_nord_ouest_couloir=			CreerMur(7.07,HAUTEUR_PLAFOND#+H_step#,0.01,		15.5,(HAUTEUR_PLAFOND#+H_step#)/2,24.5,			tex_mur_garage$,3 )
mur_sud_est_couloir=			CreerMur(7.07,HAUTEUR_PLAFOND#+2*H_step#,0.01,		38.5,HAUTEUR_PLAFOND#/2+H_step#,8.5,			tex_mur_garage$,3 )
mur_sud_ouest_couloir=			CreerMur(7.07,HAUTEUR_PLAFOND#+H_step#,0.01,		15.5,(HAUTEUR_PLAFOND#+H_step#)/2,8.5,				tex_mur_garage$,3 )


vitre_ouest_couloir=			CreerMur(0.01,HAUTEUR_PLAFOND#,9,			16,HAUTEUR_PLAFOND#/2,16.5,			tex_vitre$,1,0,		type_block)
vitre_est_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#,9,			38,HAUTEUR_PLAFOND#/2,16.5,			tex_vitre$,1,0,		type_block)
vitre_sud_couloir=				CreerMur(16,HAUTEUR_PLAFOND#,0.01,		27,HAUTEUR_PLAFOND#/2,9,			tex_vitre$,1,0,		type_block)
vitre_nord_couloir=				CreerMur(16,HAUTEUR_PLAFOND#,0.01,		27,HAUTEUR_PLAFOND#/2,24,			tex_vitre$,1,0,		type_block)
vitre_sud_est_couloir=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		36.5,HAUTEUR_PLAFOND#/2,10.5,		tex_vitre$,1,0,		type_block)
vitre_sud_ouest_couloir=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		17.5,HAUTEUR_PLAFOND#/2,10.5,		tex_vitre$,1,0,		type_block)
vitre_nord_est_couloir=			CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		36.5,HAUTEUR_PLAFOND#/2,22.5,		tex_vitre$,1,0,		type_block)
vitre_nord_ouest_couloir=		CreerMur(4.25,HAUTEUR_PLAFOND#,0.01,		17.5,HAUTEUR_PLAFOND#/2,22.5,		tex_vitre$,1,0,		type_block)

plafond_ouest_couloir=			CreerMur(0.01,11,3,					39.5,HAUTEUR_PLAFOND#+2*H_step#,16.5,			tex_plafond_couloir$,3)
plafond_nord_couloir=			CreerMur(0.01,18,3,					27,HAUTEUR_PLAFOND#+H_step#,25.5,				tex_plafond_couloir$,3)
plafond_sud_couloir=			CreerMur(0.01,18,3,					27,HAUTEUR_PLAFOND#+H_step#,7.5,				tex_plafond_couloir$,3)
plafond_sud_est_couloir=		CreerMur(0.01,7.07,2.83,			16.5,HAUTEUR_PLAFOND#+H_step#/2,23.5,			tex_plafond_couloir$,3)
plafond_sud_ouest_couloir=		CreerMur(0.01,7.07,2.83,			37.5,HAUTEUR_PLAFOND#+H_step#*1.5,23.5,			tex_plafond_couloir$,3)
plafond_nord_est_couloir=		CreerMur(0.01,7.07,2.83,			16.5,HAUTEUR_PLAFOND#+H_step#/2,9.5,			tex_plafond_couloir$,3)
plafond_nord_ouest_couloir=		CreerMur(0.01,7.07,2.83,			37.5,HAUTEUR_PLAFOND#+H_step#*1.5,9.5,			tex_plafond_couloir$,3)
plafond_est_couloir=			CreerMur(0.01,11,3,					14.5,HAUTEUR_PLAFOND#,16.5,						tex_plafond_couloir$,3)

sur_vitre_ouest_couloir=			CreerMur(0.01,HAUTEUR_PLAFOND#*4,9,			16,HAUTEUR_PLAFOND#*6/2,16.5,		tex_sol_couloir$)
sur_vitre_est_couloir=				CreerMur(0.01,HAUTEUR_PLAFOND#*4,9,			38,HAUTEUR_PLAFOND#*6/2,16.5,		tex_sol_couloir$)
sur_vitre_sud_couloir=				CreerMur(16,HAUTEUR_PLAFOND#*4,0.01,		27,HAUTEUR_PLAFOND#*6/2,9,			tex_sol_couloir$)
sur_vitre_nord_couloir=				CreerMur(16,HAUTEUR_PLAFOND#*4,0.01,		27,HAUTEUR_PLAFOND#*6/2,24,			tex_sol_couloir$)
sur_vitre_sud_est_couloir=			CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		36.5,HAUTEUR_PLAFOND#*6/2,10.5,		tex_sol_couloir$)
sur_vitre_sud_ouest_couloir=		CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		17.5,HAUTEUR_PLAFOND#*6/2,10.5,		tex_sol_couloir$)
sur_vitre_nord_est_couloir=			CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		36.5,HAUTEUR_PLAFOND#*6/2,22.5,		tex_sol_couloir$)
sur_vitre_nord_ouest_couloir=		CreerMur(4.25,HAUTEUR_PLAFOND#*4,0.01,		17.5,HAUTEUR_PLAFOND#*6/2,22.5,		tex_sol_couloir$)


EntityAlpha vitre_ouest_couloir,0.25
EntityAlpha vitre_sud_couloir,0.25
EntityAlpha vitre_nord_couloir,0.25
EntityAlpha vitre_est_couloir,0.25
EntityAlpha vitre_sud_ouest_couloir,0.25
EntityAlpha vitre_sud_est_couloir,0.25
EntityAlpha vitre_nord_ouest_couloir,0.25
EntityAlpha vitre_nord_est_couloir,0.25

RotateEntity mur_nord_est_couloir,0,-45,0
RotateEntity mur_nord_ouest_couloir,0,45,0
RotateEntity mur_sud_est_couloir,0,45,0
RotateEntity mur_sud_ouest_couloir,0,-45,0
RotateEntity vitre_nord_est_couloir,0,-45,0
RotateEntity vitre_nord_ouest_couloir,0,45,0
RotateEntity vitre_sud_est_couloir,0,45,0
RotateEntity vitre_sud_ouest_couloir,0,-45,0
RotateEntity plafond_nord_couloir,0,0,90
RotateEntity plafond_ouest_couloir,0,90,90
RotateEntity plafond_sud_couloir,0,0,90
RotateEntity plafond_est_couloir,0,90,90
RotateEntity plafond_sud_est_couloir,0,45,-ATan(7.07/H_step#)
RotateEntity plafond_sud_ouest_couloir,0,-45,-ATan(7.07/H_step#)
RotateEntity plafond_nord_est_couloir,0,-45,-ATan(7.07/H_step#)
RotateEntity plafond_nord_ouest_couloir,0,45,-ATan(7.07/H_step#)
RotateEntity sur_vitre_nord_est_couloir,0,-45,0
RotateEntity sur_vitre_nord_ouest_couloir,0,45,0
RotateEntity sur_vitre_sud_est_couloir,0,45,0
RotateEntity sur_vitre_sud_ouest_couloir,0,-45,0
;le corridor
;.Corridor
sol_surelever1_couloir=			CreerMur(H_step#,16,3,				27,H_step#/2,7.5,				tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever1bis_couloir=		CreerMur(H_step#,1,1,				18.5,H_step#/2,6.5,				tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever1ter_couloir=		CreerMur(H_step#,1,1,				35.5,H_step#/2,6.5,				tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever2_couloir=			CreerMur(H_step#,16,3,				27,H_step#/2,25.5,				tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever2bis_couloir=		CreerMur(H_step#,1,1,				18.5,H_step#/2,26.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever2ter_couloir=		CreerMur(H_step#,1,1,				35.5,H_step#/2,26.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever3_couloir=			CreerMur(2*H_step#,3,9,				39.5,H_step#,16.5,				tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever3bis_couloir=		CreerMur(2*H_step#,1,1,				40.5,H_step#,11.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
sol_surelever3ter_couloir=		CreerMur(2*H_step#,1,1,				40.5,H_step#,21.5,			tex_dale_couloir$,1,1,		type_sol(1,1))

marche1_rampe1_couloir=			CreerMur(H_stair#,2.8,1,			14.4,H_stair#/2,11.6,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche2_rampe1_couloir=			CreerMur(H_stair#*2,2.8,1,			15.1,H_stair#*2/2,10.9,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche3_rampe1_couloir=			CreerMur(H_stair#*3,2.8,1,			15.8,H_stair#*3/2,10.2,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche4_rampe1_couloir=			CreerMur(H_stair#*4,2.8,1,			16.5,H_stair#*4/2,9.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche5_rampe1_couloir=			CreerMur(H_stair#*5,2.8,1,			17.2,H_stair#*5/2,8.8,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche6_rampe1_couloir=			CreerMur(H_stair#*6,2.8,1,			17.9,H_stair#*6/2,8.1,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche7_rampe1_couloir=			CreerMur(H_stair#*7,2.8,1,			18.6,H_stair#*7/2,7.4,			tex_dale_couloir$,1,1,		type_sol(1,1))

marche1_rampe2_couloir=			CreerMur(H_stair#,2.8,1,			14.4,H_stair#/2,21.4,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche2_rampe2_couloir=			CreerMur(H_stair#*2,2.8,1,			15.1,H_stair#*2/2,22.1,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche3_rampe2_couloir=			CreerMur(H_stair#*3,2.8,1,			15.8,H_stair#*3/2,22.8,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche4_rampe2_couloir=			CreerMur(H_stair#*4,2.8,1,			16.5,H_stair#*4/2,23.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche5_rampe2_couloir=			CreerMur(H_stair#*5,2.8,1,			17.2,H_stair#*5/2,24.2,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche6_rampe2_couloir=			CreerMur(H_stair#*6,2.8,1,			17.9,H_stair#*6/2,24.9,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche7_rampe2_couloir=			CreerMur(H_stair#*7,2.8,1,			18.6,H_stair#*7/2,25.6,			tex_dale_couloir$,1,1,		type_sol(1,1))

marche1_rampe3_couloir=			CreerMur(H_stair#+H_step#,2.8,1,			35.4,(H_stair#+H_step#)/2,7.4,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche2_rampe3_couloir=			CreerMur(H_stair#*2+H_step#,2.8,1,			36.1,(H_stair#*2+H_step#)/2,8.1,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche3_rampe3_couloir=			CreerMur(H_stair#*3+H_step#,2.8,1,			36.8,(H_stair#*3+H_step#)/2,8.8,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche4_rampe3_couloir=			CreerMur(H_stair#*4+H_step#,2.8,1,			37.5,(H_stair#*4+H_step#)/2,9.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche5_rampe3_couloir=			CreerMur(H_stair#*5+H_step#,2.8,1,			38.2,(H_stair#*5+H_step#)/2,10.2,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche6_rampe3_couloir=			CreerMur(H_stair#*6+H_step#,2.8,1,			38.9,(H_stair#*6+H_step#)/2,10.9,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche7_rampe3_couloir=			CreerMur(H_stair#*7+H_step#,2.8,1,			39.6,(H_stair#*7+H_step#)/2,11.6,			tex_dale_couloir$,1,1,		type_sol(1,1))

marche1_rampe4_couloir=			CreerMur(H_stair#+H_step#,2.8,1,			35.4,(H_stair#+H_step#)/2,25.6,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche2_rampe4_couloir=			CreerMur(H_stair#*2+H_step#,2.8,1,			36.1,(H_stair#*2+H_step#)/2,24.9,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche3_rampe4_couloir=			CreerMur(H_stair#*3+H_step#,2.8,1,			36.8,(H_stair#*3+H_step#)/2,24.2,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche4_rampe4_couloir=			CreerMur(H_stair#*4+H_step#,2.8,1,			37.5,(H_stair#*4+H_step#)/2,23.5,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche5_rampe4_couloir=			CreerMur(H_stair#*5+H_step#,2.8,1,			38.2,(H_stair#*5+H_step#)/2,22.8,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche6_rampe4_couloir=			CreerMur(H_stair#*6+H_step#,2.8,1,			38.9,(H_stair#*6+H_step#)/2,22.1,			tex_dale_couloir$,1,1,		type_sol(1,1))
marche7_rampe4_couloir=			CreerMur(H_stair#*7+H_step#,2.8,1,			39.6,(H_stair#*7+H_step#)/2,21.4,			tex_dale_couloir$,1,1,		type_sol(1,1))


RotateEntity sol_surelever1_couloir,0,0,90
RotateEntity sol_surelever1bis_couloir,0,0,90
RotateEntity sol_surelever1ter_couloir,0,0,90
RotateEntity sol_surelever2_couloir,0,0,90
RotateEntity sol_surelever2bis_couloir,0,0,90
RotateEntity sol_surelever2ter_couloir,0,0,90
RotateEntity sol_surelever3_couloir,0,0,90
RotateEntity sol_surelever3bis_couloir,0,0,90
RotateEntity sol_surelever3ter_couloir,0,0,90
RotateEntity marche1_rampe1_couloir,0,45,90
RotateEntity marche2_rampe1_couloir,0,45,90
RotateEntity marche3_rampe1_couloir,0,45,90
RotateEntity marche4_rampe1_couloir,0,45,90
RotateEntity marche5_rampe1_couloir,0,45,90
RotateEntity marche6_rampe1_couloir,0,45,90
RotateEntity marche7_rampe1_couloir,0,45,90
RotateEntity marche1_rampe2_couloir,0,-45,90
RotateEntity marche2_rampe2_couloir,0,-45,90
RotateEntity marche3_rampe2_couloir,0,-45,90
RotateEntity marche4_rampe2_couloir,0,-45,90
RotateEntity marche5_rampe2_couloir,0,-45,90
RotateEntity marche6_rampe2_couloir,0,-45,90
RotateEntity marche7_rampe2_couloir,0,-45,90
RotateEntity marche1_rampe3_couloir,0,-45,90
RotateEntity marche2_rampe3_couloir,0,-45,90
RotateEntity marche3_rampe3_couloir,0,-45,90
RotateEntity marche4_rampe3_couloir,0,-45,90
RotateEntity marche5_rampe3_couloir,0,-45,90
RotateEntity marche6_rampe3_couloir,0,-45,90
RotateEntity marche7_rampe3_couloir,0,-45,90
RotateEntity marche1_rampe4_couloir,0,45,90
RotateEntity marche2_rampe4_couloir,0,45,90
RotateEntity marche3_rampe4_couloir,0,45,90
RotateEntity marche4_rampe4_couloir,0,45,90
RotateEntity marche5_rampe4_couloir,0,45,90
RotateEntity marche6_rampe4_couloir,0,45,90
RotateEntity marche7_rampe4_couloir,0,45,90



;.Usine_Central;UC
sol_uc=								CreerMur(0.01,27,34,					38,0.001,13.5,								tex_sol_usine$,0.5,1,		type_sol(1,1))
plafond1_uc=						CreerMur(0.01,27,14,					48,HAUTEUR_PLAFOND#+2*H_step#,13.5,			tex_plafond_usine$,5,1,		type_sol(1,1))
plafond2_uc=						CreerMur(0.01,6,20,						31,HAUTEUR_PLAFOND#+2*H_step#,3,			tex_plafond_usine$,5,1,		type_sol(1,1))
plafond3_uc=						CreerMur(0.01,7.07,7.07,				41,HAUTEUR_PLAFOND#+2*H_step#+0.01,6,		tex_plafond_usine$,5,1,		type_sol(1,1))
plafond4_uc=						CreerMur(0.01,7.07,7.07,				41,HAUTEUR_PLAFOND#+2*H_step#+0.01,27,		tex_plafond_usine$,5,1,		type_sol(1,1))

mur_nord1_uc=						CreerMur(19,HAUTEUR_PLAFOND#+2*H_step#,0.01,		45.7,HAUTEUR_PLAFOND#/2+H_step#,27,			tex_mur_usine$,3)
mur_sud1_uc=						CreerMur(34,HAUTEUR_PLAFOND#+2*H_step#,0.01,		38,HAUTEUR_PLAFOND#/2+H_step#,0,			tex_mur_usine$,3)
mur_nord2_uc=						CreerMur(14,HAUTEUR_PLAFOND#+2*H_step#,0.01,		29.2,HAUTEUR_PLAFOND#/2+H_step#,5.99,			tex_mur_usine$,3)
mur_nord2_sur_porte_uc=				CreerMur(1,HAUTEUR_PLAFOND#+H_step#-2,0.01,			21.7,HAUTEUR_PLAFOND#/2+H_step#*1.5+1,5.99,			tex_mur_usine$,3)
mur_nord2_sous_porte_uc=			CreerMur(1,H_step#,0.01,							21.7,H_step#/2,5.99,							tex_mur_usine$,3)

mur_ouest1_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,5,			41.21,HAUTEUR_PLAFOND#/2+H_step#,13.5,		tex_mur_usine$,3)
mur_ouest2_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,5,			41.21,HAUTEUR_PLAFOND#/2+H_step#,19.5,		tex_mur_usine$,3)
mur_ouest3_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,6,			21.01,HAUTEUR_PLAFOND#/2+H_step#,3,				tex_mur_usine$,3)
mur_ouest4_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,3,			28.0,HAUTEUR_PLAFOND#/2+H_step#,1.5,			tex_mur_usine$,3)
mur_ouest5_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#/2+H_step#,3,			28.0,HAUTEUR_PLAFOND#*3/4+H_step#*1.5,4.5,		tex_mur_usine$,3)
porte_ouest_uc=						CreerMur(0.3,2,1,									41.1,1+H_step#*2,16.5,							tex_porte_uc$,1)
porte_ouest2_uc=					CreerMur(0.2,HAUTEUR_PLAFOND#/2+H_step#,3,			27.9,HAUTEUR_PLAFOND#*1/4+H_step#*0.5,4.5,		tex_porte_uc$,3)
porte_nord_uc=						CreerMur(1,2,0.2,									21.5,H_step#+1,6,								tex_porte_uc$,1)

;POSITION DES PORTES OUVERTE
;PositionEntity porte_ouest_uc,41.0,10+1+H_step#*2,15.5
;PositionEntity porte_ouest2_uc,27.9,10+HAUTEUR_PLAFOND#*1/4+H_step#*0.5,1.5
;PositionEntity porte_nord_uc,22.5,10+H_step#+1,6

mur_ouest_sur_porte_uc=				CreerMur(0.01,HAUTEUR_PLAFOND#-2,1,					41.21,HAUTEUR_PLAFOND#/2+2*H_step#+1,16.5,		tex_mur_usine$,3)
mur_ouest_sous_porte_uc=			CreerMur(0.01,2*H_step#,1,							41.21,H_step#,16.5,								tex_mur_usine$,3)

contour_porte_haut_uc=				CreerMur(0.01,1,0.2,								41.11,2+2*H_step#,16.5,							tex_contour_porte$,2,1)
contour_porte_gauche_uc=			CreerMur(0.2,2,0.01,								41.11,1+2*H_step#,16.0,							tex_contour_porte$,2,1)
contour_porte_droite_uc=			CreerMur(0.2,2,0.01,								41.11,1+2*H_step#,17.0,							tex_contour_porte$,2,1)


mur_est1_uc=						CreerMur(0.01,HAUTEUR_PLAFOND#+2*H_step#,27,		55,HAUTEUR_PLAFOND#/2+H_step#,13.5,				tex_mur_usine$,3)
mur_nord_ouest_uc=					CreerMur(7.28,HAUTEUR_PLAFOND#+2*H_step#,0.01,		38.75,HAUTEUR_PLAFOND#/2+H_step#,8.55,		tex_mur_usine$,3)
mur_sud_ouest_uc=					CreerMur(7.39,HAUTEUR_PLAFOND#+2*H_step#,0.01,		38.61,HAUTEUR_PLAFOND#/2+H_step#,24.59,		tex_mur_usine$,3)

tapis_roulant_base=					CreerMur(1,3,20,						48,0.5,15,					tex_tapis_roulant$,3,1,				type_sol(1,1))
tapis_roulant_ext1=					CreerTuyau(0.99,3,						48,0.5,5,					tex_tapis_roulant$,3,0,	 "", 0.98,		type_sol(1,1))
tapis_roulant_ext2=					CreerTuyau(0.99,3,						48,0.5,25,					tex_tapis_roulant$,3,0,	 "", 0.98,		type_sol(1,1))
tapis_roulant_bord_ouest=			CreerMur(0.1,1.3,21,					46.5,0.65,15,				tex_bord_tapis_roulant$,3,1,		type_block)
tapis_roulant_bord_est=				CreerMur(0.1,1.3,21,					49.5,0.65,15,				tex_bord_tapis_roulant$,3,1,		type_block)

passerelle1_uc=						CreerMur(0.01,3,2,		42.5,2*H_step#,16.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))	
passerelle2_uc=						CreerMur(0.01,2,11,		45,2*H_step#,17,					tex_sol_passerelle$,1,4,		type_sol(1,1))	
passerelle3_uc=						CreerMur(0.01,1,24,		50.5,2*H_step#,14.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle4_uc=						CreerMur(0.01,2,2,		48,2*H_step#+H_stair#*4,12.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle5_uc=						CreerMur(0.01,1,2,		46.5,2*H_step#+H_stair#*2,12.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle6_uc=						CreerMur(0.01,1,2,		49.5,2*H_step#+H_stair#*2,12.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle7_uc=						CreerMur(0.01,2,1,		45,H_step#+H_stair#*6,23,			tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle8_uc=						CreerMur(0.01,2,1,		45,H_step#+H_stair#*4,24,			tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle9_uc=						CreerMur(0.01,2,2,		45,H_step#+H_stair#*2,25.5,			tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle10_uc=					CreerMur(0.01,1,2,		43.5,H_step#,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle11_uc=					CreerMur(0.01,1,2,		42.5,H_stair#*6,25.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle12_uc=					CreerMur(0.01,1,2,		41.5,H_stair#*4,25.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle13_uc=					CreerMur(0.01,1,2,		40.5,H_stair#*2,25.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
passerelle14_uc=					CreerMur(0.01,4,2,		53,2*H_step#,12.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))


rebord1_passerelle1_uc=				CreerMur(3,0.5,0.01,	42.5,2*H_step#+0.25,15.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle1_uc=				CreerMur(3,0.5,0.01,	42.5,2*H_step#+0.25,17.5,				tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle2_uc=				CreerMur(0.01,0.5,5,	44,2*H_step#+0.25,20,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle2_uc=				CreerMur(0.01,0.5,4,	44,2*H_step#+0.25,13.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle2_uc=				CreerMur(0.01,0.5,9,	46,2*H_step#+0.25,18,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord4_passerelle2_uc=				CreerMur(2,0.5,0.01,	45,2*H_step#+0.25,11.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle3_uc=				CreerMur(0.01,0.5,13,	50,2*H_step#+0.25,20,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle3_uc=				CreerMur(0.01,0.5,9,	50,2*H_step#+0.25,7,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle3_uc=				CreerMur(0.01,0.5,13,	51,2*H_step#+0.25,20,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord4_passerelle3_uc=				CreerMur(0.01,0.5,9,	51,2*H_step#+0.25,7,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle4_uc=				CreerMur(2,0.5,0.01,	48,2*H_step#+H_stair#*4+0.25,13.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle4_uc=				CreerMur(2,0.5,0.01,	48,2*H_step#+H_stair#*4+0.25,11.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle5_uc=				CreerMur(1,0.5,0.01,	46.5,2*H_step#+H_stair#*2+0.25,13.5,	tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle5_uc=				CreerMur(1,0.5,0.01,	46.5,2*H_step#+H_stair#*2+0.25,11.5,	tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle6_uc=				CreerMur(1,0.5,0.01,	49.5,2*H_step#+H_stair#*2+0.25,13.5,	tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle6_uc=				CreerMur(1,0.5,0.01,	49.5,2*H_step#+H_stair#*2+0.25,11.5,	tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle7_uc=				CreerMur(0.01,0.5,1,	44,H_step#+H_stair#*6+0.25,23,			tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle7_uc=				CreerMur(0.01,0.5,1,	46,H_step#+H_stair#*6+0.25,23,			tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle8_uc=				CreerMur(0.01,0.5,1,	44,H_step#+H_stair#*4+0.25,24,			tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle8_uc=				CreerMur(0.01,0.5,1,	46,H_step#+H_stair#*4+0.25,24,			tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle9_uc=				CreerMur(0.01,0.5,2,	46,H_step#+H_stair#*2+0.25,25.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle9_uc=				CreerMur(2,0.5,0.01,	45,H_step#+H_stair#*2+0.25,26.5,		tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle10_uc=			CreerMur(1,0.5,0.01,	43.5,H_step#+0.25,24.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle10_uc=			CreerMur(1,0.5,0.01,	43.5,H_step#+0.25,26.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle11_uc=			CreerMur(1,0.5,0.01,	42.5,H_stair#*6+0.25,24.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle11_uc=			CreerMur(1,0.5,0.01,	42.5,H_stair#*6+0.25,26.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle12_uc=			CreerMur(1,0.5,0.01,	41.5,H_stair#*4+0.25,24.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle12_uc=			CreerMur(1,0.5,0.01,	41.5,H_stair#*4+0.25,26.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle13_uc=			CreerMur(1,0.5,0.01,	40.5,H_stair#*2+0.25,24.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle13_uc=			CreerMur(1,0.5,0.01,	40.5,H_stair#*2+0.25,26.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord1_passerelle14_uc=			CreerMur(3,0.5,0.01,	52.5,2*H_step#+0.25,13.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord2_passerelle14_uc=			CreerMur(4,0.5,0.01,	53,2*H_step#+0.25,11.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))

rebord4_passerelle2_uc=				CreerMur(2,H_stair#*2,0.01,			45,H_stair#*15,22.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle4_uc=				CreerMur(0.01,H_stair#*2,2,			47,H_stair#*19,12.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord4_passerelle4_uc=				CreerMur(0.01,H_stair#*2,2,			49,H_stair#*19,12.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle5_uc=				CreerMur(0.01,H_stair#*2,2,			46,H_stair#*17,12.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle6_uc=				CreerMur(0.01,H_stair#*2,2,			50,H_stair#*17,12.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle7_uc=				CreerMur(2,H_stair#*2,0.01,			45,H_stair#*13,23.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle8_uc=				CreerMur(2,H_stair#*2,0.01,			45,H_stair#*11,24.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle9_uc=				CreerMur(0.01,H_stair#*2,2,			44,H_stair#*9,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle10_uc=			CreerMur(0.01,H_stair#*2,2,			43,H_stair#*7,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle11_uc=			CreerMur(0.01,H_stair#*2,2,			42,H_stair#*5,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle12_uc=			CreerMur(0.01,H_stair#*2,2,			41,H_stair#*3,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))
rebord3_passerelle13_uc=			CreerMur(0.01,H_stair#*2,2,			40,H_stair#,25.5,					tex_sol_passerelle$,1,4,		type_sol(1,1))


RotateEntity contour_porte_haut_uc,0,90,90

;Partie Bureau Teddy
sol_petit_escalier_uc=			CreerMur(H_step#,1.5,1,				21.75,H_step#/2-0.01,5.6,			tex_dale_couloir$,1,1,		type_sol(1,1))
petit_escalier=LoadMesh("objets\petit_escalier\escalier.3ds")
EntityType petit_escalier,type_sol(2,1)
message_enigme_uc=			CollerImage(0,90,0,		28.1,1.5,1.5,		img_message$,1,4)

;autres
fauteuil1_uc=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil2_uc=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil3_uc=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil4_uc=LoadMesh("objets\tables_chaises\CHAIR3.X")
reservoir1=LoadMesh("objets\reservoir\reservoir.3ds")
reservoir2=LoadMesh("objets\reservoir\reservoir.3ds")
laboratoire_uc=LoadMesh("objets\labo\labo.3ds")

PositionEntity fauteuil1_uc,32,0.4,0.5
PositionEntity fauteuil2_uc,32,0.4,1.5
PositionEntity fauteuil3_uc,32,0.4,2.5
PositionEntity fauteuil4_uc,32,0.4,3.5
PositionEntity reservoir1,52.5,-0.2,21.5
PositionEntity reservoir2,52.5,-0.2,5.5
PositionEntity petit_escalier,22.7,0.5,5
PositionEntity laboratoire_uc,24.3,0,1.9

ScaleEntity reservoir1,0.0018,0.0017,0.0018
ScaleEntity reservoir2,0.0018,0.0017,0.0018
ScaleEntity laboratoire_uc,0.08,0.08,0.08
ScaleEntity petit_escalier,0.18,0.18,0.18
ScaleSprite message_enigme_uc,0.21,0.28


RotateEntity sol_petit_escalier_uc,0,0,90
RotateEntity reservoir1,0,-90,0
RotateEntity reservoir2,0,-90,0
RotateEntity fauteuil1_uc,0,-90,0
RotateEntity fauteuil2_uc,0,-90,0
RotateEntity fauteuil3_uc,0,-90,0
RotateEntity fauteuil4_uc,0,-90,0
RotateEntity sol_uc,0,90,90
RotateEntity passerelle1_uc,0,0,90
RotateEntity passerelle2_uc,0,0,90
RotateEntity passerelle3_uc,0,0,90
RotateEntity passerelle4_uc,0,0,90
RotateEntity passerelle5_uc,0,0,90
RotateEntity passerelle6_uc,0,0,90
RotateEntity passerelle7_uc,0,0,90
RotateEntity passerelle8_uc,0,0,90
RotateEntity passerelle9_uc,0,0,90
RotateEntity passerelle10_uc,0,0,90
RotateEntity passerelle11_uc,0,0,90
RotateEntity passerelle12_uc,0,0,90
RotateEntity passerelle13_uc,0,0,90
RotateEntity passerelle14_uc,0,0,90
RotateEntity tapis_roulant_base,0,0,90
RotateEntity tapis_roulant_ext1,60,0,90
RotateEntity tapis_roulant_ext2,80,0,90
RotateEntity plafond1_uc,0,90,90
RotateEntity plafond2_uc,0,90,90
RotateEntity plafond3_uc,0,45,90
RotateEntity plafond4_uc,0,45,90
RotateEntity mur_nord_ouest_uc,0,45,0
RotateEntity mur_sud_ouest_uc,0,-45,0
RotateEntity petit_escalier,0,48,0

Texture_escalier=LoadTexture(tex_dale_couloir$)

EntityTexture petit_escalier,Texture_escalier

EntityColor laboratoire_uc, 142,214,229
EntityAlpha laboratoire_uc,0.60

;.Poste
sol_poste1=							CreerMur(0.01,6,10,					16,0.001,3,							tex_sol_poste,2,1,		type_sol(1,1))
sol_poste2=							CreerMur(0.01,5,8,					15,0.001,8.5,						tex_sol_poste,2,1,		type_sol(1,1))

plafond_poste1=						CreerMur(0.01,8,6,					17,HAUTEUR_PLAFOND#-0.01,3,			tex_plafond_poste$,3,1,		type_sol(1,1))
plafond_poste2=						CreerMur(0.01,7.07,7.07,			13,HAUTEUR_PLAFOND#+0.01,6,			tex_plafond_poste$,3,1,		type_sol(1,1))
plafond_poste3=						CreerMur(0.01,2,11,					12,HAUTEUR_PLAFOND#-0.02,5.5,			tex_plafond_poste$,3,1,		type_sol(1,1))


;porte_ouest_sdp=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,			26.01,HAUTEUR_PLAFOND#/2+H_step#,38.5,		tex_mur_sdp$,0.7)
mur_est1_poste=						CreerMur(0.01,HAUTEUR_PLAFOND#,6,			21,HAUTEUR_PLAFOND#/2,3,			tex_mur_poste$,HAUTEUR_PLAFOND#)
mur_est2_poste=						CreerMur(7.09,HAUTEUR_PLAFOND#,0.01,		15.49,HAUTEUR_PLAFOND#/2,8.49,		tex_mur_poste$,HAUTEUR_PLAFOND#)
mur_nord_poste=						CreerMur(3,HAUTEUR_PLAFOND#,0.01,			19.5,HAUTEUR_PLAFOND#/2,5.99,		tex_mur_poste$,HAUTEUR_PLAFOND#)
mur_ouest_poste=					CreerMur(0.01,HAUTEUR_PLAFOND#,11,			11.01,HAUTEUR_PLAFOND#/2,5.5,		tex_mur_poste$,HAUTEUR_PLAFOND#)
mur_sud1_poste=						CreerMur(10,HAUTEUR_PLAFOND#,0.01,			16,HAUTEUR_PLAFOND#/2,0,			tex_mur_poste$,HAUTEUR_PLAFOND#)

sur_porte_poste=					CreerMur(2,HAUTEUR_PLAFOND#-2,0.01,			12,HAUTEUR_PLAFOND#/2+1,10.99,			tex_mur_poste$,HAUTEUR_PLAFOND#)
sur_porte_poste2=					CreerMur(2,HAUTEUR_PLAFOND#-2,0.01,			12,HAUTEUR_PLAFOND#/2+1,11.01,			tex_mur_garage$,3)

porte_poste=						CreerMur(2,2,0.04,							12,1,11)


RotateEntity mur_est2_poste,0,-45,0
RotateEntity sol_poste1,0,90,90
RotateEntity sol_poste2,0,90,90
RotateEntity plafond_poste1,0,0,90
RotateEntity plafond_poste2,0,45,90
RotateEntity plafond_poste3,0,0,90

trieuse_poste=LoadMesh("objets\trieuse\trieuse.3ds")

PositionEntity trieuse_poste,16.3,3.7,4.9
ScaleEntity trieuse_poste,0.15,0.15,0.15
RotateEntity trieuse_poste,0,-150,180

EntityTexture trieuse_poste, texture_labo
EntityTexture porte_poste,Texture_porte_poste

;POSITION DES PORTES OUVERTE

;PositionEntity porte_poste,13.7,10+1.75,11.7
; //0,45,0


;.SalleDesProtecteurs
sol_sdp1=							CreerMur(0.01,13,10,					31,0.001+H_step#,33.5,						tex_sol_accueil$,2,1,		type_sol(1,1))
mur_nord1_sdp=						CreerMur(10,HAUTEUR_PLAFOND#,0.01,		31,HAUTEUR_PLAFOND#/2+H_step#,40,			tex_mur_sdp$,HAUTEUR_PLAFOND#)
mur_sud1_sdp=						CreerMur(5.5,HAUTEUR_PLAFOND#,0.01,		28.75,HAUTEUR_PLAFOND#/2+H_step#,27.2,		tex_mur_sdp$,HAUTEUR_PLAFOND#)
mur_sud_sur_porte=					CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,		32,(HAUTEUR_PLAFOND#-2)/2+2+H_step#,27.2,	tex_mur_sdp$,HAUTEUR_PLAFOND#)
mur_sud2_sdp=						CreerMur(3.5,HAUTEUR_PLAFOND#,0.01,		34.25,HAUTEUR_PLAFOND#/2+H_step#,27.2,		tex_mur_sdp$,HAUTEUR_PLAFOND#)

mur_ouest1_sdp=						CreerMur(0.01,HAUTEUR_PLAFOND#,10,		26.01,HAUTEUR_PLAFOND#/2+H_step#,32,		tex_mur_sdp$,HAUTEUR_PLAFOND#)
mur_est1_sdp=						CreerMur(0.01,HAUTEUR_PLAFOND#,13,		36,HAUTEUR_PLAFOND#/2+H_step#,33.5,			tex_mur_sdp$,HAUTEUR_PLAFOND#)
plafond_sdp1=						CreerMur(0.01,13,10,					31,HAUTEUR_PLAFOND#+H_step#,33.5,			tex_plafond_accueil$,3,1,		type_sol(1,1))
porte_ouest_sdp=					CreerMur(0.01,HAUTEUR_PLAFOND#,3,		26.01,HAUTEUR_PLAFOND#/2+H_step#,38.5,		tex_mur_sdp$,HAUTEUR_PLAFOND#)

contour_porte_sud_haut_sdp=					CreerMur(0.01,0.2,1,					32,2+H_step#,27.1,						tex_contour_porte$,2,1)
contour_porte_sud_gauche_sdp=				CreerMur(0.01,2,0.2,					31.5,1+H_step#,27.1,					tex_contour_porte$,2,1)
contour_porte_sud_droite_sdp=				CreerMur(0.01,2,0.2,					32.5,1+H_step#,27.1,					tex_contour_porte$,2,1)



RotateEntity sol_sdp1,0,90,90
RotateEntity plafond_sdp1,0,90,90
RotateEntity contour_porte_sud_haut_sdp,0,90,90

;rack_armor1_sdp=LoadMesh("objets\presentoire_armure\armure.3DS")
rack_armor2_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
rack_armor3_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
rack_armor4_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
;rack_armor5_sdp=LoadMesh("objets\presentoire_armure\armure.3DS")
rack_armor6_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
rack_armor7_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
;rack_armor8_sdp=LoadMesh("objets\presentoire_armure\armure.3DS")
rack_armor9_sdp=LoadMesh("objets\presentoire_vide\armure.3DS")
table1_sdp=LoadMesh("objets\table\table_strog.3DS")
table2_sdp=LoadMesh("objets\table\table_strog.3DS")
table3_sdp=LoadMesh("objets\table\table_strog.3DS")

;PositionEntity rack_armor1_sdp,27.6,H_step#,39.6
PositionEntity rack_armor2_sdp,30,H_step#+1.37,39.9
PositionEntity rack_armor3_sdp,32.4,H_step#+1.37,39.9
PositionEntity rack_armor4_sdp,34.8,H_step#+1.37,39.9
;PositionEntity rack_armor5_sdp,35.6,H_step#,38.5
PositionEntity rack_armor6_sdp,35.9,H_step#+1.37,36.2
PositionEntity rack_armor7_sdp,35.9,H_step#+1.37,33.9
;PositionEntity rack_armor8_sdp,35.6,H_step#,31.6
PositionEntity rack_armor9_sdp,35.9,H_step#+1.37,29.3
PositionEntity table1_sdp,31,H_step#,32.5
PositionEntity table2_sdp,30.5,H_step#,34.1
PositionEntity table3_sdp,31.6,H_step#,34.1

;ScaleEntity rack_armor1_sdp,0.015,0.015,0.015
ScaleEntity rack_armor2_sdp,0.177,0.177,0.177
ScaleEntity rack_armor3_sdp,0.177,0.177,0.177
ScaleEntity rack_armor4_sdp,0.177,0.177,0.177
;ScaleEntity rack_armor5_sdp,0.015,0.015,0.015
ScaleEntity rack_armor6_sdp,0.177,0.177,0.177
ScaleEntity rack_armor7_sdp,0.177,0.177,0.177
;ScaleEntity rack_armor8_sdp,0.015,0.015,0.015
ScaleEntity rack_armor9_sdp,0.177,0.177,0.177
ScaleEntity table1_sdp,0.022,0.022,0.022
ScaleEntity table2_sdp,0.022,0.022,0.022
ScaleEntity table3_sdp,0.022,0.022,0.022

;RotateEntity rack_armor1_sdp,0,-90,0
;RotateEntity rack_armor5_sdp,0,180,0
RotateEntity rack_armor6_sdp,0,-90,0
RotateEntity rack_armor7_sdp,0,-90,0
;RotateEntity rack_armor8_sdp,0,180,0
RotateEntity rack_armor9_sdp,0,-90,0
RotateEntity table2_sdp,0,-90,0
RotateEntity table3_sdp,0,-90,0

;EntityTexture rack_armor1_sdp,Texture_porte_armure
EntityTexture rack_armor2_sdp,Texture_porte_armure
EntityTexture rack_armor3_sdp,Texture_porte_armure
EntityTexture rack_armor4_sdp,Texture_porte_armure
;EntityTexture rack_armor5_sdp,Texture_porte_armure
EntityTexture rack_armor6_sdp,Texture_porte_armure
EntityTexture rack_armor7_sdp,Texture_porte_armure
;EntityTexture rack_armor8_sdp,Texture_porte_armure
EntityTexture rack_armor9_sdp,Texture_porte_armure


;.infirmerie
logo1_infirmerie=					CollerImage(0,0,0,			25.3,3.5,26.9,			img_symbole_pharmacie$,1)
ScaleSprite logo1_infirmerie,0.2,0.2
logo2_infirmerie=					CollerImage(0,-78.7,0,		17.72,2.3,28.07,			img_symbole_pharmacie$,1)
ScaleSprite logo2_infirmerie,0.2,0.2



sol_infirmerie1=					CreerMur(0.01,10,8,		22,0.004,32,						tex_mur_infirmerie$,0.7,1,		type_sol(1,1))
sol_infirmerie2=					CreerMur(0.01,10.5,2,	18,0.003,32,						tex_mur_infirmerie$,0.7,1,		type_sol(0,0))
plafond_infirmerie1=				CreerMur(0.01,10,8,		22,HAUTEUR_PLAFOND#-0.02,32,		tex_mur_infirmerie$,0.7,1,		type_sol(1,1))
plafond_infirmerie2=				CreerMur(0.01,10.2,2,	18,HAUTEUR_PLAFOND#-0.01,32.2,		tex_mur_infirmerie$,0.7,1,		type_sol(0,0))


mur_ouest1_infirmerie=				CreerMur(0.01,HAUTEUR_PLAFOND#,8.8,		17.1,HAUTEUR_PLAFOND#/2,32.9,				tex_mur_infirmerie$,0.7)
mur_ouest_sur_porte_infirmerie=		CreerMur(0.03,HAUTEUR_PLAFOND#-2,1.2,	18.07,2+(HAUTEUR_PLAFOND#-2)/2,28.1,		tex_mur_infirmerie$,0.7)
mur_ouest2_infirmerie=				CreerMur(0.01,HAUTEUR_PLAFOND#,0.4,		18.2,HAUTEUR_PLAFOND#/2,27.41,				tex_mur_infirmerie$,0.7)

mur_nord_infirmerie=				CreerMur(10,HAUTEUR_PLAFOND#,0.01,		21,HAUTEUR_PLAFOND#/2,37,					tex_mur_infirmerie$,0.7)
mur_est_infirmerie=					CreerMur(0.01,HAUTEUR_PLAFOND#,10,		26,HAUTEUR_PLAFOND#/2,32,					tex_mur_infirmerie$,0.7)
mur_sud1_infirmerie=				CreerMur(6.8,HAUTEUR_PLAFOND#,0.01,		21.4,HAUTEUR_PLAFOND#/2,27.2,				tex_mur_infirmerie$,0.7)
mur_sud2_infirmerie=				CreerMur(0.2,HAUTEUR_PLAFOND#,0.01,		25.9,HAUTEUR_PLAFOND#/2,27.2,				tex_mur_infirmerie$,0.7)
mur_sud_sous_porte_infirmerie=		CreerMur(1,H_step#,0.01,				25.3,H_step#/2,27.2,							tex_mur_infirmerie$,0.7)
mur_sud_sur_porte_infirmerie=		CreerMur(1,HAUTEUR_PLAFOND#-2,0.01,		25.3,2+(HAUTEUR_PLAFOND#-2)/2+H_step#,27.2,	tex_mur_infirmerie$,0.7)

contour_porte_sud_haut_inf=			CreerMur(0.01,0.2,1,					25.3,2+H_step#,27.1,						tex_contour_porte$,2,1)
contour_porte_sud_gauche_inf=		CreerMur(0.01,2,0.2,					25.8,1+H_step#,27.1,					tex_contour_porte$,2,1)
contour_porte_sud_droite_inf=		CreerMur(0.01,2,0.2,					24.8,1+H_step#,27.1,					tex_contour_porte$,2,1)

contour_porte_ouest_haut_inf=		CreerMur(0.01,0.3,1.2,					17.92,2,28.1,						tex_contour_porte$,2,1)
contour_porte_ouest_gauche_inf=		CreerMur(0.32,2,0.01,					18.00,1,27.6,					tex_contour_porte$,2,1)
contour_porte_ouest_droite_inf=		CreerMur(0.3,2,0.01,					17.82,1,28.58,					tex_contour_porte$,2,1)


rampe1_infirmerie=					CreerMur(H_step#,H_step#,H_step#+0.2,	25.3,H_step#/2,27.1+H_step#/2,					tex_mur_infirmerie$,0.7,1,	type_sol(1,1))
rampe2_infirmerie=					CreerMur(4,H_step#,H_step#+0.2,			23.195,-0.1,27.09+H_step#/2,					tex_mur_infirmerie$,0.7,1,	type_sol(2,1))
rampe2Bord_infirmerie=				CreerMur(3+H_step#,2*H_step#,0.01,		23.695,H_step#,27.09+H_step#,				tex_sol_accueil$,0.7,1,	type_block)


RotateEntity rampe2_infirmerie,0,0,20
RotateEntity contour_porte_sud_haut_inf,0,90,90

lit_infirmerie1=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie2=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie3=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie4=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie5=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie6=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie7=LoadMesh("objets\lit_infirmerie\BED2.3ds")
lit_infirmerie8=LoadMesh("objets\lit_infirmerie\BED2.3ds")
;lit_infirmerie9=LoadMesh("objets\lit_infirmerie\BED2.3ds")

PositionEntity lit_infirmerie1,17.75,0.6,36.2
PositionEntity lit_infirmerie2,18.15,0.6,34.3
PositionEntity lit_infirmerie3,18.55,0.6,32.4
PositionEntity lit_infirmerie4,18.95,0.6,30.5
PositionEntity lit_infirmerie5,24.7,0.6,36.2
PositionEntity lit_infirmerie6,24.7,0.6,34.3
PositionEntity lit_infirmerie7,24.7,0.6,32.4
PositionEntity lit_infirmerie8,24.7,0.6,30.5
;PositionEntity lit_infirmerie9,24.7,0.6,28.6

ScaleEntity lit_infirmerie1,0.015,0.015,0.017
ScaleEntity lit_infirmerie2,0.015,0.015,0.017
ScaleEntity lit_infirmerie3,0.015,0.015,0.017
ScaleEntity lit_infirmerie4,0.015,0.015,0.017
ScaleEntity lit_infirmerie5,0.015,0.015,0.017
ScaleEntity lit_infirmerie6,0.015,0.015,0.017
ScaleEntity lit_infirmerie7,0.015,0.015,0.017
ScaleEntity lit_infirmerie8,0.015,0.015,0.017
;ScaleEntity lit_infirmerie9,0.015,0.015,0.017

RotateEntity lit_infirmerie1,0,-90,0
RotateEntity lit_infirmerie2,0,-90,0
RotateEntity lit_infirmerie3,0,-90,0
RotateEntity lit_infirmerie4,0,-90,0
RotateEntity lit_infirmerie5,0,90,0
RotateEntity lit_infirmerie6,0,90,0
RotateEntity lit_infirmerie7,0,90,0
RotateEntity lit_infirmerie8,0,90,0
;RotateEntity lit_infirmerie9,0,90,0

RotateEntity mur_ouest1_infirmerie,0,11.3,0
RotateEntity mur_ouest2_infirmerie,0,11.3,0
RotateEntity mur_ouest_sur_porte_infirmerie,0,11.3,0
RotateEntity contour_porte_ouest_haut_inf,0,11.3,90
RotateEntity sol_infirmerie1,0,90,90
RotateEntity sol_infirmerie2,0,101.3,90
RotateEntity plafond_infirmerie1,0,90,90
RotateEntity plafond_infirmerie2,0,101.3,90


EntityAlpha rampe2Bord_infirmerie,0

;.accueil
;accueil
sol_accueil1=					CreerMur(0.01,10,10,		13,0.002,32,						tex_sol_accueil$,2,1,		type_sol(1,1))
sol_accueil2=					CreerMur(0.01,7,5,			10.5,0.002,23.5,					tex_sol_accueil$,2,1,		type_sol(1,1))
sol_accueil3=					CreerMur(0.01,5,5,			15.5,0.001,24.5,					tex_sol_accueil$,2,1,		type_sol(1,1))
plafond1_accueil=				CreerMur(0.01,10,10,		13,HAUTEUR_PLAFOND#,32,				tex_plafond_accueil$,3,1,		type_sol(1,1))
plafond2_accueil=				CreerMur(0.01,5,7,			10.5,HAUTEUR_PLAFOND#,23.5,			tex_plafond_accueil$,3,1,		type_sol(1,1))
plafond3_accueil=				CreerMur(0.01,7.07,7.07,	13,HAUTEUR_PLAFOND#+0.02,27,		tex_plafond_accueil$,3,1,		type_sol(1,1))


RotateEntity sol_accueil1,0,90,90
RotateEntity sol_accueil2,0,90,90
RotateEntity sol_accueil3,0,90,90
RotateEntity plafond1_accueil,0,0,90
RotateEntity plafond2_accueil,0,0,90
RotateEntity plafond3_accueil,0,45,90

mur_est1_accueil=				CreerMur(0.03,HAUTEUR_PLAFOND#,8.8,		16.80,HAUTEUR_PLAFOND#/2,32.9,			tex_mur_accueil$,3)
mur_est_sur_porte=				CreerMur(0.01,HAUTEUR_PLAFOND#-2,1.2,	17.75,2+(HAUTEUR_PLAFOND#-2)/2,28.1,			tex_mur_accueil$,3)
mur_est0_accueil=				CreerMur(0.01,HAUTEUR_PLAFOND#,0.64,	17.86,HAUTEUR_PLAFOND#/2,27.31,			tex_mur_accueil$,3)

mur_est2_accueil=				CreerMur(0.01,HAUTEUR_PLAFOND#,2,		12.99,HAUTEUR_PLAFOND#/2,21.01,		tex_mur_accueil$,3)
mur_est3_accueil=				CreerMur(7.09,HAUTEUR_PLAFOND#,0.01,	15.49,HAUTEUR_PLAFOND#/2,24.51,		tex_mur_accueil$,3)
mur_nord_accueil=				CreerMur(8,HAUTEUR_PLAFOND#,0.01,		12,HAUTEUR_PLAFOND#/2,37,			tex_mur_accueil$,3)
mur_ouest_accueil=				CreerMur(0.01,HAUTEUR_PLAFOND#,17,		8,HAUTEUR_PLAFOND#/2,28.5,			tex_mur_accueil$,3)

mur_sud1_accueil=				CreerMur(2,HAUTEUR_PLAFOND#,0.01,		12,HAUTEUR_PLAFOND#/2,20.20,		tex_mur_accueil$,3)
mur_sud2_accueil=				CreerMur(1,HAUTEUR_PLAFOND#,0.01,		8.5,HAUTEUR_PLAFOND#/2,20.20,		tex_mur_accueil$,3)

contour_mur_sud1_accueil=		CreerMur(0.01,HAUTEUR_PLAFOND#,0.2,		11,HAUTEUR_PLAFOND#/2,20.10,		tex_mur_accueil$,3)
contour_mur_sud2_accueil=		CreerMur(0.01,HAUTEUR_PLAFOND#,0.2,		9,HAUTEUR_PLAFOND#/2,20.10,		tex_mur_accueil$,3)


RotateEntity mur_est3_accueil,0,45,0
RotateEntity mur_est0_accueil,0,11.3,0
RotateEntity mur_est1_accueil,0,11.3,0
RotateEntity mur_est_sur_porte,0,11.3,0

;table1=LoadMesh("objets\tables_chaises\DESK3.X")
;table2=LoadMesh("objets\tables_chaises\DESK3.X")
;table3=LoadMesh("objets\tables_chaises\DESK3.X")
chaise1=LoadMesh("objets\tables_chaises\CHAIR16.X")
chaise2=LoadMesh("objets\tables_chaises\CHAIR16.X")
fauteuil1=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil2=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil3=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil4=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil5=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil6=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil7=LoadMesh("objets\tables_chaises\CHAIR3.X")
fauteuil8=LoadMesh("objets\tables_chaises\CHAIR3.X")
tablette1=LoadMesh("objets\tablette_murale\Decor shelf N090512.3DS")
armoire1=LoadMesh("objets\etagere\bookshelf.3DS")
SuperTable1=LoadMesh("objets\superTable\Table N160511.3DS")
escalier1=LoadMesh("objets\stair\escalier1.X")
rangement1=LoadMesh("objets\etagere3\spektr_shelf.3DS")

;PositionEntity table1,9,0.35,26.8
;PositionEntity table2,10.4,0.35,28.3
;PositionEntity table3,10.4,0.35,30.23
PositionEntity chaise1,8.8,0.45,28.6
PositionEntity chaise2,8.4,0.45,27.7
PositionEntity fauteuil1,15.5,0.45,30
PositionEntity fauteuil2,15.5,0.45,31.5
PositionEntity fauteuil3,15.5,0.45,33
PositionEntity fauteuil4,15.5,0.45,34.5
PositionEntity fauteuil5,13.5,0.45,30
PositionEntity fauteuil6,13.5,0.45,31.5
PositionEntity fauteuil7,13.5,0.45,33
PositionEntity fauteuil8,13.5,0.45,34.5
PositionEntity tablette1,9.2,2.5,36.99
PositionEntity armoire1,12,0.01,36.66
PositionEntity SuperTable1,10,0.01,29
PositionEntity escalier1,12.6,0,22.6
PositionEntity rangement1,8.3,2.4,30.2

;ScaleEntity table1,0.4,0.4,0.4
;ScaleEntity table2,0.4,0.4,0.4
;ScaleEntity table3,0.4,0.4,0.4
ScaleEntity tablette1,0.035,0.035,0.035
ScaleEntity armoire1,0.01,0.01,0.01
ScaleEntity SuperTable1,0.009,0.007,0.009
ScaleEntity escalier1,8.2,8.2,8
ScaleEntity rangement1,0.03,0.02,0.03

;RotateEntity table1,0,180,0
;RotateEntity table2,0,-90,0
;RotateEntity table3,0,-90,0
RotateEntity chaise1,0,90,0
RotateEntity chaise2,0,60,0
RotateEntity fauteuil1,0,-90,0
RotateEntity fauteuil2,0,-90,0
RotateEntity fauteuil3,0,-90,0
RotateEntity fauteuil4,0,-90,0
RotateEntity fauteuil5,0,-90,0
RotateEntity fauteuil6,0,-90,0
RotateEntity fauteuil7,0,-90,0
RotateEntity fauteuil8,0,-90,0
RotateEntity SuperTable1,0,150,0
RotateEntity escalier1,0,45,0


texture_tablette=LoadTexture(tex_tablette_murale$)
texture_superTable=LoadTexture(tex_supertable$)
EntityTexture tablette1, texture_tablette
EntityTexture SuperTable1, texture_superTable
ScaleTexture texture_tablette,15/0.035,20/0.035

trape_escalier1=			CollerImage(-90,0,45,	16.6,HAUTEUR_PLAFOND#-0.1,26.6,			img_trape_escalier$,1)
ScaleSprite trape_escalier1,1,2

;;.rideau
Fenetre1=					CollerImage(0,0,0,		10.2,1.9,36.99,			img_rideau$,1)
ScaleSprite Fenetre1,1.2,0.8

Fenetre2=					CollerImage(0,0,0,		14.2,1.9,36.99,			img_rideau$,1)
ScaleSprite Fenetre2,1.2,0.8

FlipMesh tablette1

;.champs
;LE THE champs
sol_champs1=					CreerMur(0.01,22,15,		27,0.002,16.5,				tex_sol_champs$,4,1,		type_sol(1,1))
RotateEntity sol_champs1,0,0,90
Fontaine_tete=					CollerImage(0,-90,0,		37.9,H_step#,16.5,			img_tete_fontaine$,0.75)
Fontaine_base=LoadMesh("objets\fontaine\Fountain N071210.3DS")
PositionEntity Fontaine_base, 27,1.4,16.5
ScaleEntity Fontaine_base,0.007,0.007,0.007
EntityTexture Fontaine_base,Texture_mur_fontaine

;VehiculeDeTest
;.vehicule1
;poste_tir=LoadMesh("objets\poste_tir\poste_tir.X")
;PositionEntity poste_tir,40.5,HAUTEUR_PLAFOND#*2-1.8,16.5
;ScaleEntity poste_tir,2.5,2.5,2.5

CreerSteamPorte(tranche1_porteAscenceur, tranche2_porteAscenceur, tranche3_porteAscenceur, tranche4_porteAscenceur, tranche5_porteAscenceur, tranche6_porteAscenceur, tranche7_porteAscenceur, 7.93,14,0)
;CreerSteamPorte(tranche1_porteAscenceur, tranche2_porteAscenceur, tranche3_porteAscenceur, tranche4_porteAscenceur, tranche5_porteAscenceur, tranche6_porteAscenceur, tranche7_porteAscenceur, 12.50,10,90)
;CreerSteamPorte(tranche1_porteAscenceur, tranche2_porteAscenceur, tranche3_porteAscenceur, tranche4_porteAscenceur, tranche5_porteAscenceur, tranche6_porteAscenceur, tranche7_porteAscenceur, 9.5,20,90)


;.Personnage
Garde1=LoadMD2("objets\garde\tris.md2")
tex_Garde1=LoadTexture("objets\garde\garde.pcx")
EntityTexture Garde1,tex_Garde1
ScaleEntity Garde1,0.03,0.03,0.03
PositionEntity Garde1,5,0.7,1
TurnEntity Garde1,0,-70,0
AnimateMD2 Garde1,1,0.1,20,40

Garde2=LoadMD2("objets\garde\tris.md2")
EntityTexture Garde2,tex_Garde1
ScaleEntity Garde2,0.03,0.03,0.03
PositionEntity Garde2,5,0.7,3
TurnEntity Garde2,0,-80,0
AnimateMD2 Garde2,1,0.1,0,20

Secretaire1=LoadMD2("objets\Standardiste\tris.md2")
tex_Secretaire1=LoadTexture("objets\Standardiste\Standardiste.pcx")
EntityTexture Secretaire1,tex_Secretaire1
ScaleEntity Secretaire1,0.03,0.03,0.03
PositionEntity Secretaire1,8.8,0.7,27.8
TurnEntity Secretaire1,0,-170,0
AnimateMD2 Secretaire1,1,0.1,115,121

Secretaire2=LoadMD2("objets\Standardiste\tris.md2")
EntityTexture Secretaire2,tex_Secretaire1
ScaleEntity Secretaire2,0.03,0.03,0.03
PositionEntity Secretaire2,9,0.7,28.9
TurnEntity Secretaire2,0,-90,0
AnimateMD2 Secretaire2,1,0.1,105,114

Infirmiere=LoadMD2("objets\Standardiste\tris.md2")
EntityTexture Infirmiere,tex_Secretaire1
ScaleEntity Infirmiere,0.03,0.03,0.03
PositionEntity Infirmiere,21.2,0.7,36.0
TurnEntity Infirmiere,0,-210,0
AnimateMD2 Infirmiere,1,0.1,115,121

Engrenage1=LoadMD2("objets\waste\tris.md2")
tex_Engrenage1=LoadTexture("objets\waste\waste.pcx")
EntityTexture Engrenage1,tex_Engrenage1
ScaleEntity Engrenage1,0.032,0.032,0.032
PositionEntity Engrenage1,52.5,0.7,15.7
TurnEntity Engrenage1,0,-40,0
AnimateMD2 Engrenage1,1,0.1,46,54

Engrenage2=LoadMD2("objets\waste\tris.md2")
EntityTexture Engrenage2,tex_Engrenage1
ScaleEntity Engrenage2,0.032,0.032,0.032
PositionEntity Engrenage2,51,3.78,24.6
TurnEntity Engrenage2,0,180,0
AnimateMD2 Engrenage2,1,0.1,20,30

Engrenage3=LoadMD2("objets\waste\tris.md2")
EntityTexture Engrenage3,tex_Engrenage1
ScaleEntity Engrenage3,0.032,0.032,0.032
PositionEntity Engrenage3,46,0.7,10.6
TurnEntity Engrenage3,0,-110,0
AnimateMD2 Engrenage3,0.5,0.1,95,98;100,105

Teddy=LoadMD2("objets\Teddy\tris.md2")
tex_Teddy=LoadTexture("objets\Teddy\Teddy.pcx")
EntityTexture Teddy,tex_Teddy
ScaleEntity Teddy,0.032,0.032,0.032
PositionEntity Teddy,26.7,0.8,2.6
TurnEntity Teddy,0,-30,0
AnimateMD2 Teddy,1,0.1,0,30


;.lumiere
lumiere=CreateLight(2)
LightColor lumiere,125,125,125
PositionEntity lumiere,30,5,20
LightRange lumiere,20

;lumiere2=CreateLight(2)
;LightColor lumiere2,255,255,255
;PositionEntity lumiere2,10.2,1.5,36.5
;LightRange lumiere2,1

;lumiere3=CreateLight(2)
;LightColor lumiere3,255,255,255
;PositionEntity lumiere3,14.2,1.5,36.5
;LightRange lumiere3,1


entiteTest=mur_ouest2_infirmerie
;mode_debug=True

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Rajout Nico;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

For p.porte=Each porte
	Select p\num
		Case 201
			p\pivot=porte_ouest_uc
			p\pos_init#[1]=41.0
			p\pos_init#[2]=1+H_step#*2
			p\pos_init#[3]=16.5
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=41
			p\pos_final#[2]=1+H_step#*2
			p\pos_final#[3]=15.5
			p\pos_final#[4]=0
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=0
			p\speed#[2]=0
			p\speed#[3]=-1
			p\speed#[4]=0
			p\speed#[5]=0
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 202
			p\pivot=porte_ouest2_uc
			p\pos_init#[1]=27.9 
			p\pos_init#[2]=HAUTEUR_PLAFOND#*1/4+H_step#*0.5
			p\pos_init#[3]=4.5
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=27.9
			p\pos_final#[2]=HAUTEUR_PLAFOND#*1/4+H_step#*0.5
			p\pos_final#[3]=1.5
			p\pos_final#[4]=0
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=0
			p\speed#[2]=0
			p\speed#[3]=-0.5
			p\speed#[4]=0
			p\speed#[5]=0
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 203
			p\pivot=porte_nord_uc
			p\pos_init#[1]=21.5
			p\pos_init#[2]=1+H_step#
			p\pos_init#[3]=6
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=22.5
			p\pos_final#[2]=1+H_step#
			p\pos_final#[3]=6
			p\pos_final#[4]=0
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=1
			p\speed#[2]=0
			p\speed#[3]=0
			p\speed#[4]=0
			p\speed#[5]=0
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 204
			p\pivot=porte_poste
			p\pos_init#[1]=12
			p\pos_init#[2]=1
			p\pos_init#[3]=11
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=13.7
			p\pos_final#[2]=1
			p\pos_final#[3]=10.1
			p\pos_final#[4]=0
			p\pos_final#[5]=-45
			p\pos_final#[6]=0
			p\speed#[1]=0.85
			p\speed#[2]=0
			p\speed#[3]=-0.45
			p\speed#[4]=0
			p\speed#[5]=-22.5
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 205
			p\pivot=porte_est1_boite_au_lettre
			p\pos_init#[1]=4
			p\pos_init#[2]=HAUTEUR_PLAFOND#/2
			p\pos_init#[3]=1
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=3
			p\pos_final#[2]=HAUTEUR_PLAFOND#/2
			p\pos_final#[3]=0.02
			p\pos_final#[4]=0
			p\pos_final#[5]=90
			p\pos_final#[6]=0
			p\speed#[1]=-0.5
			p\speed#[2]=0
			p\speed#[3]=-0.5
			p\speed#[4]=0
			p\speed#[5]=45
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 206
			p\pivot=porte_est2_boite_au_lettre
			p\pos_init#[1]=4
			p\pos_init#[2]=HAUTEUR_PLAFOND#/2
			p\pos_init#[3]=3
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=180
			p\pos_final#[1]=3
			p\pos_final#[2]=HAUTEUR_PLAFOND#/2
			p\pos_final#[3]=3.98
			p\pos_final#[4]=0
			p\pos_final#[5]=90
			p\pos_final#[6]=180
			p\speed#[1]=-0.5
			p\speed#[2]=0
			p\speed#[3]=0.5
			p\speed#[4]=0
			p\speed#[5]=45
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
	End Select
Next

play_music(04,1)

Select entrance
	Case 1 ; en venant du garage
		pos_entrance#(1)=9.5;9.5
		pos_entrance#(2)=0.2
		pos_entrance#(3)=14;14
	Case 2 ; en venant du 1er étage
		pos_entrance#(1)=14;9.5
		pos_entrance#(2)=2.2
		pos_entrance#(3)=24
	Default
		pos_entrance#(1)=9.5
		pos_entrance#(2)=0.2
		pos_entrance#(3)=14
End Select