;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;EXTERIEUR : PATROUILLE VOLANTE;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


HAUTEUR_PLAFOND#			=3.50
H_step#						=1.25
H_stair#					=H_step#/8.0

t_porte_garage=LoadTexture("textures/loran/porte_garage.jpg") 
t_metaporte=LoadTexture("textures/loran/MetalDoors3.jpg") 
t_porte_parking=LoadTexture("textures/loran/Porte_Parking_1.jpg")
ScaleTexture t_porte_parking,1.0/8.0,1.0/2.0
ScaleTexture t_metaporte,0.5,1 ;fait semblant qu'il y ai 2 partie => fait beaucoup moins faux

tex_sol_canyon$				="objets/canyon/canyon_old.jpg";"textures/loran/RocksArid0048_2_S.jpg";
tex_ciel$					="textures/loran/Skies2.jpg"
tex_grillage$				="textures/environnement/net.bmp"
tex_porte_passage1$			="textures/loran/DoorsMetal4.jpg";DoorsMetal2.jpg"
tex_mur_passage1$			="textures/loran/MarbleWhite2.jpg"
tex_toit_passage1$			="textures/loran/mur_usine.jpg"
tex_toit_in_passage1$		="sprites/loran/rond_metal2_fond_noir.bmp"
tex_pilier$					="textures/loran/velour_petit.jpg"
tex_mur_chateau$			="textures/loran/ChateauMur.jpg";environnement/brick20.bmp";
tex_sol_chateau$			="textures/loran/ChateauMur.jpg";Gravel.jpg"
tex_renfort_chateau$		="textures/loran/mecha1.bmp";murbronze.jpg"


tex_sol_passerelle$			="textures/loran/passerelle.bmp"
tex_dale_chemin$			="textures/loran/Gravel.jpg";"textures/loran/MarbleWhite2.jpg""textures/loran/MarbleWhite2.jpg";
tex_rampe_chemin$			="textures/loran/WoodWall3.jpg";
tex_porte_chemin$			="textures/loran/DoorsMetal2.jpg"
tex_maintient$				="textures/loran/METAL.BMP"

tex_cuir_noir$					="textures/loran/cuir-noir.jpg"
tex_cuivre1$					="textures/loran/Cuivre_neuf.bmp"
tex_velourRouge$				="textures/loran/velour_petit.jpg"
img_ContourSteamPorte$			="sprites/loran/porte ascenceur.bmp"
img_ContourSteamPorte2$			="sprites/loran/porte ascenceur2.bmp"
img_deco1$						="sprites/loran/deco1.bmp"
img_message$					="sprites/loran/Message Parchemin.jpg"
img_lierre1$					="sprites/loran/liere2.png"


tex_sol$					="textures/environnement/train_floor.jpg"



;img_fondSteamille$		="sprites/loran/fond_steamille_fond_noir.bmp"














;skybox
skybox=CreateSphere(16)
FlipMesh skybox
ScaleEntity skybox,200,200,200 
RotateEntity skybox,60,180,60 
EntityFX skybox,1
EntityOrder skybox,1
tex=LoadTexture(tex_ciel$)
EntityTexture skybox,tex
sky=skybox

;MAP = CANYON
canyonMap=LoadMesh("objets\canyon\Canyon.x")
tex=LoadTexture(tex_sol_canyon$)
EntityTexture canyonMap,tex
ScaleEntity canyonMap,0.06,0.04,0.06
TurnEntity canyonMap,0,180,0
PositionEntity canyonMap,54,0,0
EntityType canyonMap,3

;invisible Canyon collision A FAIRE QUE SOALT
;super_sol = CreerMur(0.5,110,112,	55,-2,0,	tex_grillage$,8,4)
;RotateEntity super_sol,0,0,90


;giga bateau
giga_boat=LoadMesh("objets\Galleon\galleon.3DS")

ScaleEntity giga_boat,2,2,2
PositionEntity giga_boat,62.00,4.7,-11.8
RotateEntity giga_boat,0,40,00
EntityType giga_boat,3


;Lumière du soleil
CreerLumiereRond(220,194,15,		12,150,33,			175)

;Grillage extérieur
grillage_sud1=			CreerMur(110,5,0.01,	55,7.9,-55,	tex_grillage$,8,4)	
grillage_nord1=			CreerMur(110,5,0.01,	55,7.7,51,	tex_grillage$,8,4)
grillage_ouest1=		CreerMur(0.01,5,112,	0,7.9,0,	tex_grillage$,8,4)	
grillage_est1=			CreerMur(0.01,5,112,	110,7.9,0,	tex_grillage$,8,4)

RotateEntity grillage_sud1,32,0,0
RotateEntity grillage_nord1,-32,0,0
RotateEntity grillage_est1,0,0,32
RotateEntity grillage_ouest1,0,0,-32



;Passage entre colline
;passerelle1_ps=							CreerMur(0.01,16,2,		42.5,5.7,6.5,				tex_sol_passerelle$,1,4,		type_sol(2,1))	
;passerelle2_uc=						CreerMur(0.01,2,11,		45,2*H_step#,17,			tex_sol_passerelle$,1,4,		type_block)	
;passerelle3_uc=						CreerMur(0.01,1,24,		50.5,2*H_step#,14.5,		tex_sol_passerelle$,1,4,		type_block)

;RotateEntity passerelle1_ps,0,-18,85

;Chemin/escalier pour monter la colline
rampe1_chemin=			CreerMur(0.2,2,13.6,		45.0,-3.8,-31.1,			tex_rampe_chemin$,1,4,		type_sol(2,1))
rampe2_chemin=			CreerMur(0.2,2,10.3,		54.3,0.4,-23.3,				tex_rampe_chemin$,1,4,		type_sol(2,1))
rampe3_chemin=			CreerMur(0.2,2,20,		59.4,2.5,-23.3,				tex_rampe_chemin$,1,4,		type_sol(2,1))

sol1_1_chemin=			CreerMur(0.2,9,4,		54,-1.5,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol1_2_chemin=			CreerMur(0.2,9,4,		54,-1.502,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol1_3_chemin=			CreerMur(0.2,9,4,		54,-1.501,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol1_4_chemin=			CreerMur(0.2,9,4,		54,-1.502,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))

sol3_1_chemin=			CreerMur(0.2,6,10,		71.1,2.6,-32.4,				tex_dale_chemin$,1,1,		type_sol(2,1))
sol3_2_chemin=			CreerMur(0.2,6,10,		71.1,2.598,-32.4,				tex_dale_chemin$,1,1,		type_sol(2,1))
sol3_3_chemin=			CreerMur(0.2,6,10,		71.1,2.599,-32.4,				tex_dale_chemin$,1,1,		type_sol(2,1))
sol3_4_chemin=			CreerMur(0.2,6,10,		71.1,2.598,-32.4,				tex_dale_chemin$,1,1,		type_sol(2,1))

rampe1_mur=				CreerMur(0.7,2,13.6,	45.0,-4.15,-31.1,			tex_rampe_chemin$,1,4,		type_block)
rampe2_mur=				CreerMur(0.7,2,10.3,	54.3,0.05,-23.3,				tex_rampe_chemin$,1,4,		type_block)
rampe3_mur=				CreerMur(0.7,2,20,		59.4,2.15,-23.3,				tex_rampe_chemin$,1,4,		type_block)
sol1_1_mur=				CreerMur(0.7,9,4,		54,-1.75,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))


RotateEntity rampe1_chemin,-20,-50,90
RotateEntity rampe2_chemin,-23,40,90
RotateEntity rampe3_chemin,1,53,90
RotateEntity rampe1_mur,-20,-50,90
RotateEntity rampe2_mur,-23,40,90
RotateEntity rampe3_mur,1,53,90

RotateEntity sol1_1_chemin,0,-50,90
RotateEntity sol1_2_chemin,0,-5,90
RotateEntity sol1_3_chemin,0,40,90
RotateEntity sol1_4_chemin,0,-95,90
RotateEntity sol1_1_mur,0,-50,90

RotateEntity sol3_1_chemin,0,-50,90
RotateEntity sol3_2_chemin,0,-5,90
RotateEntity sol3_3_chemin,0,40,90
RotateEntity sol3_4_chemin,0,-95,90

EntityAlpha rampe1_mur,0
EntityAlpha rampe2_mur,0
EntityAlpha rampe3_mur,0
EntityAlpha sol1_1_mur,0

;platforme1
Passage1_Tourelle1=LoadMesh("objets\poste_tir\poste_tir.x")
ScaleEntity Passage1_Tourelle1,3.5,3.5,3.5
;TurnEntity Passage1_Tourelle1,-25,-80,0
TurnEntity Passage1_Tourelle1,0,-80,0
PositionEntity Passage1_Tourelle1,53.3,1.0,-31.9
EntityType Passage1_Tourelle1,3




;Platforme3
Chemin1_SP_mask=		LoadMesh("objets\masque_gaz\mask.X")
PositionEntity Chemin1_SP_mask,72.8,4,-28.9			
ScaleEntity Chemin1_SP_mask,4,4,4
EntityType Chemin1_SP_mask,3

Chemin1_Porte=			CreerMur(1.5,1.5,0.05,		72.8,3.8,-33.1,			tex_porte_chemin$,1.5)

TurnEntity Chemin1_SP_mask,15,0,0
TurnEntity Chemin1_Porte,15,0,0

;Fixation1_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		76.5,2.5,-34.3,		tex_maintient$)
;Fixation2_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		72.0,2.5,-38.0,		tex_maintient$)
;Fixation3_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		65.7,2.5,-30.5,		tex_maintient$)


;Tube1_sol3_chemin=			CreerTuyau(0.1,4,				76.5,0.5,-34.3,			tex_maintient$)
;Tube2_sol3_chemin=			CreerTuyau(0.1,8,				72.0,-1.5,-38.0,		tex_maintient$)
;Tube3_sol3_chemin=			CreerTuyau(0.1,4,				65.7,0.5,-30.5,			tex_maintient$)71.1,2.6,-32.4,	

paa_poutre1_partie1=		CreerMur(0.3,4,0.05,			76.35,0.5,-34.4,			tex_sol_passerelle$,0.3,4)
paa_poutre1_partie2=		CreerMur(0.05,4,0.2,			76.25,0.5,-34.5,			tex_maintient$)
paa_poutre1_partie3=		CreerMur(0.05,4,0.2,			76.45,0.5,-34.3,			tex_maintient$)

paa_poutre2_partie1=		CreerMur(0.3,8,0.05,			72.0,-1.5,-37.7,			tex_sol_passerelle$,0.3,4)
paa_poutre2_partie2=		CreerMur(0.05,8,0.2,			71.9,-1.5,-37.8,			tex_maintient$)
paa_poutre2_partie3=		CreerMur(0.05,8,0.2,			72.1,-1.5,-37.6,			tex_maintient$)

paa_poutre3_partie1=		CreerMur(0.3,4,0.05,			65.9,0.5,-30.6,				tex_sol_passerelle$,0.3,4)
paa_poutre3_partie2=		CreerMur(0.05,4,0.2,			65.8,0.5,-30.7,				tex_maintient$)
paa_poutre3_partie3=		CreerMur(0.05,4,0.2,			66.0,0.5,-30.5,				tex_maintient$)


RotateEntity paa_poutre1_partie1,0,45,0
RotateEntity paa_poutre1_partie2,0,45,0
RotateEntity paa_poutre1_partie3,0,45,0
RotateEntity paa_poutre2_partie1,0,45,0
RotateEntity paa_poutre2_partie2,0,45,0
RotateEntity paa_poutre2_partie3,0,45,0
RotateEntity paa_poutre3_partie1,0,45,0
RotateEntity paa_poutre3_partie2,0,45,0
RotateEntity paa_poutre3_partie3,0,45,0

;Second ascenseur

;intérieur
mur_Ascenseur2_sud=			CreerMur(0.1,3,3,			97.95,-4.2,-43.7,			tex_velourRouge$,3)
mur_Ascenseur2_est=			CreerMur(3,3,0.1,			97.05,-4.2,-45.1,			tex_velourRouge$,3)
mur_Ascenseur2_ouest=		CreerMur(3,3,0.1,			97.05,-4.2,-42.3,			tex_velourRouge$,3)
plafond_Ascenseur2=			CreerMur(3,3,0.1,			97.05,-2.87,-43.7,			tex_velourRouge$,3)
sol_Ascenseur2=				CreerMur(3,3,0.1,			97.05,-5.7,-43.7,			tex_parquet$,3)

RotateEntity plafond_Ascenseur2,90,0,0 
RotateEntity sol_Ascenseur2,90,0,0 

message_Ascenseur2=			CollerImage(0,180,0,		96.7,-4.2,-41.71,		img_message$,1,4)
ScaleSprite message_Ascenseur2,0.21,0.28

;extérieur
centre_porteAscenseur2=  				CreerTuyau(0.3,0.30,			95.46,-4.3,-43.3,									tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
tranche1_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.40,-4.3-0.9*Sin(290),-43.3-0.9*Cos(290),			tex_cuivre1$)
tranche2_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.42,-4.3-0.9*Sin(243),-43.3-0.9*Cos(243),			tex_cuivre1$)
tranche3_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.44,-4.3-0.9*Sin(196),-43.3-0.9*Cos(196),			tex_cuivre1$)
tranche4_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.46,-4.3-0.9*Sin(149),-43.3-0.9*Cos(149),			tex_cuivre1$)
tranche5_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.48,-4.3-0.9*Sin(196),-43.3-0.9*Cos(196),			tex_cuivre1$)
tranche6_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.50,-4.3-0.9*Sin(243),-43.3-0.9*Cos(243),			tex_cuivre1$)
tranche7_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.52,-4.3-0.9*Sin(290),-43.3-0.9*Cos(290),			tex_cuivre1$)


Contour_porteAscenseur2=					CollerImage(0,-90,0,			95.38,-4.4,-43.8,		img_ContourSteamPorte$,1.4)
Contour2_porteAscenseur2=					CollerImage(0,-90,0,			95.54,-4.4,-43.8,		img_ContourSteamPorte$,1.4)
Contour3_porteAscenseur2=					CollerImage(0,90,0,				95.38,-4.4,-43.8,		img_ContourSteamPorte2$,1.4)
Contour4_porteAscenseur2=					CollerImage(0,90,0,				95.54,-4.4,-43.8,		img_ContourSteamPorte2$,1.4)

RotateEntity centre_porteAscenseur2,0,0,90

CreerAnimationRotRoll(tranche1_porteAscenseur2,47, 1, 0, -4.3, -43.3, 290)
CreerAnimationRotRoll(tranche2_porteAscenseur2,47, 2, 0, -4.3, -43.3, 243)
CreerAnimationRotRoll(tranche3_porteAscenseur2,47, 3, 0, -4.3, -43.3, 196)
CreerAnimationRotRoll(tranche4_porteAscenseur2,47, 4, 0, -4.3, -43.3, 149)
CreerAnimationRotRoll(tranche5_porteAscenseur2,47, 3, 0, -4.3, -43.3, 196)
CreerAnimationRotRoll(tranche6_porteAscenseur2,47, 2, 0, -4.3, -43.3, 243)
CreerAnimationRotRoll(tranche7_porteAscenseur2,47, 1, 0, -4.3, -43.3, 290)

;Mur extérieur
murExt_Ascenseur2_sud=			CreerMur(0.1,5,3.7,			98.55,-5.1,-43.35,			tex_mur_passage1$,3)
murExt_Ascenseur2_est=			CreerMur(3.2,5,0.1,			96.95,-5.1,-45.2,			tex_mur_passage1$,3)
murExt_Ascenseur2_ouest=		CreerMur(3.2,5,0.1,			96.95,-5.1,-41.5,			tex_mur_passage1$,3)
murExt_Ascenseur2_nord1=		CreerMur(0.1,5,0.9,			95.35,-5.1,-41.95,			tex_mur_passage1$,3)
murExt_Ascenseur2_nord2=		CreerMur(0.1,0.4,2.8,		95.35,-2.8,-43.8,				tex_mur_passage1$,3)


plafondExt_Ascenseur2=			CreerMur(3.2,3.7,0.1,		96.95,-2.6,-43.35,			tex_mur_passage1$,3)
;solExt_Ascenseur2=				CreerMur(3,3,0.1,			95.05,-2.4,43,				tex_mur_passage1$,3)
RotateEntity plafondExt_Ascenseur2,90,0,0 
rampe1_Ascenseur2=				CreerMur(1,2,3,			94.6,-6.3,-43.75,			tex_rampe_chemin$,2,1,		type_sol(2,1))
RotateEntity rampe1_Ascenseur2,0,0,-80

lierre1_Ascenseur2=				CollerImage(0,-90,0,			95.29,-4.56,-41.95,		img_lierre1$)
ScaleSprite lierre1_Ascenseur2,0.5,2



;SteamTower

;st_base_sol1=					CreerTuyau(6,2,				35,6.80,20,				tex_cuivre1$,1,0, 			"",0,type_sol(1,1))
;st_base_acroche1_p1=			CreerMur(0.5,0.5,1,			37.60,6.25,21.5)
;st_base_acroche2_p1=			CreerMur(0.5,0.5,1,			35,6.25,23)
;st_base_acroche3_p1=			CreerMur(0.5,0.5,1,			37.60,6.25,21.5)
;st_base_acroche4_p1=			CreerMur(0.5,0.5,1,			33.40,6.25,-21.5)
;st_base_acroche5_p1=			CreerMur(0.5,0.5,1,			0,6.25,-23)
;st_base_acroche6_p1=			CreerMur(0.5,0.5,1,			33.40,6.25,-21.5)





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Personnage
Many_Cadavre=LoadMD2("objets\manuel\tris.md2")
tex_Cadavre=LoadTexture("objets\manuel\manuel.pcx")
EntityTexture Many_Cadavre,tex_Cadavre
ScaleEntity Many_Cadavre,0.04,0.04,0.04
PositionEntity Many_Cadavre,44.87,0.1,0
TurnEntity Many_Cadavre,42.5,30.7,0
AnimateMD2 Many_Cadavre,1,0.1,189,189




;;;;;;;;;;Ascenseur1

murExt_Ascenseur1_sud=			CreerMur(0.1,5,4.55,		98.35,-3.2,42.95,			tex_mur_passage1$,2)
murExt_Ascenseur1_est=			CreerMur(5,5,0.1,			95.85,-3.2,45.2,			tex_mur_passage1$,2)
murExt_Ascenseur1_ouest=		CreerMur(5,5,0.1,			95.85,-3.2,40.70,			tex_mur_passage1$,2)
murExt_Ascenseur1_nord1=		CreerMur(0.1,5,0.9,			93.35,-3.2,44.75,			tex_mur_passage1$,2)
murExt_Ascenseur1_nord2=		CreerMur(0.1,0.4,2.8,		93.35,-0.9,42.9,			tex_mur_passage1$,2)
murExt_Ascenseur1_nord3=		CreerMur(0.1,5,0.9,			93.35,-3.2,41.15,			tex_mur_passage1$,2)

plafondExt_Ascenseur1=			CreerMur(5,4.55,0.1,		95.85,-0.7,42.95,			tex_mur_passage1$,2)
RotateEntity plafondExt_Ascenseur1,90,0,0 
rampe1_Ascenseur1=				CreerMur(0.3,4,3,			91.79,-4.2,43.05,			tex_rampe_chemin$,2,1,		type_sol(2,1))
faux_sol_rampe1_Ascenseur1=		CreerMur(3,10,3.5,			87.6,-6.16,41.27,			tex_sol_canyon$,6,1,		type_sol(2,1))


pilier3=LoadMesh("objets\pilier\pilier.3DS")
pilier4=LoadMesh("objets\pilier\pilier.3DS")
pilier5=LoadMesh("objets\pilier\pilier.3DS")
pilier6=LoadMesh("objets\pilier\pilier.3DS")

tex_pilier=LoadTexture(tex_pilier$)
EntityTexture pilier3,tex_pilier
EntityTexture pilier4,tex_pilier
EntityTexture pilier5,tex_pilier
EntityTexture pilier6,tex_pilier
ScaleEntity pilier3,0.0002,0.00018,0.0002
ScaleEntity pilier4,0.0002,0.00018,0.0002
ScaleEntity pilier5,0.0002,0.00018,0.0002
ScaleEntity pilier6,0.0002,0.00018,0.0002

PositionEntity pilier3,93.35,-4,45.25
PositionEntity pilier4,93.35,-4,40.65
PositionEntity pilier5,98.35,-4,45.25
PositionEntity pilier6,98.35,-4,40.65

Ascenseur1_toit=			CreerTuyau(4.55,4.8,		95.9,-1.0,42.95,		tex_toit_passage1$,1,0,		tex_toit_in_passage1$)
RotateEntity Ascenseur1_toit, 0,0,90


RotateEntity faux_sol_rampe1_Ascenseur1,20.1,145,-84.14
RotateEntity rampe1_Ascenseur1,0,0,-80

centre_porteAscenseur1=  				CreerTuyau(0.3,0.30,			93.46,-2.4,43.45,		tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
tranche1_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.40,-2.4+0.9*Sin(290),43.45+0.9*Cos(290),			tex_cuivre1$)
tranche2_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.42,-2.4+0.9*Sin(243),43.45+0.9*Cos(243),			tex_cuivre1$)
tranche3_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.44,-2.4+0.9*Sin(196),43.45+0.9*Cos(196),			tex_cuivre1$)
tranche4_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.46,-2.4+0.9*Sin(149),43.45+0.9*Cos(149),			tex_cuivre1$)
tranche5_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.48,-2.4+0.9*Sin(196),43.45+0.9*Cos(196),			tex_cuivre1$)
tranche6_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.50,-2.4+0.9*Sin(243),43.45+0.9*Cos(243),			tex_cuivre1$)
tranche7_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.52,-2.4+0.9*Sin(290),43.45+0.9*Cos(290),			tex_cuivre1$)


Contour_porteAscenseur1=				CollerImage(0,-90,0,			93.38,-2.5,43,		img_ContourSteamPorte$,1.4)
Contour2_porteAscenseur1=				CollerImage(0,-90,0,			93.54,-2.5,43,		img_ContourSteamPorte$,1.4)
Contour3_porteAscenseur1=				CollerImage(0,90,0,				93.38,-2.5,43,		img_ContourSteamPorte2$,1.4)


RotateEntity centre_porteAscenseur1,0,0,90




;Passage1 ( ascenseur rebelles ) 
Passage1_est1=			CreerMur(0.01,3.5,1.1,		17.6,-4.4,32.1,	tex_mur_passage1$,2)
Passage1_est2=			CreerMur(0.01,3.5,1.1,		17.6,-4.4,35.9,	tex_mur_passage1$,2)
Passage1_est3=			CreerMur(0.01,0.57,2.7,		17.6,-2.93,34,	tex_mur_passage1$,2)
Passage1_nord1=			CreerMur(4,3.5,0.01,		15.6,-4.4,36.5,	tex_mur_passage1$,2)
Passage1_sud1=			CreerMur(4,3.5,0.01,		15.6,-4.4,31.5,	tex_mur_passage1$,2)
Passage1_plafond=		CreerMur(0.01,4,5,			15.6,-2.65,34,	tex_mur_passage1$,2)

RotateEntity Passage1_plafond,0,0,90

pilier1=LoadMesh("objets\pilier\pilier.3DS")
pilier2=LoadMesh("objets\pilier\pilier.3DS")

EntityTexture pilier1,tex_pilier
EntityTexture pilier2,tex_pilier
ScaleEntity pilier1,0.0002,0.00018,0.0002
ScaleEntity pilier2,0.0002,0.00018,0.0002

PositionEntity pilier1,17.6,-6,31.5
PositionEntity pilier2,17.6,-6,36.5

centre_porteAscenseur2=  				CreerTuyau(0.3,0.30,			17.54     ,-4.5             ,34.5,		tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
tranche1_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52-0.06,-4.5+0.9*Sin(290),34.5+0.9*Cos(290),			tex_cuivre1$)
tranche2_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52-0.04,-4.5+0.9*Sin(243),34.5+0.9*Cos(243),			tex_cuivre1$)
tranche3_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52-0.02,-4.5+0.9*Sin(196),34.5+0.9*Cos(196),			tex_cuivre1$)
tranche4_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52-0.00,-4.5+0.9*Sin(149),34.5+0.9*Cos(149),			tex_cuivre1$)
tranche5_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52+0.02,-4.5+0.9*Sin(196),34.5+0.9*Cos(196),			tex_cuivre1$)
tranche6_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52+0.04,-4.5+0.9*Sin(243),34.5+0.9*Cos(243),			tex_cuivre1$)
tranche7_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		17.52+0.06,-4.5+0.9*Sin(290),34.5+0.9*Cos(290),			tex_cuivre1$)


Contour_porteAscenseur2=					CollerImage(0,-90,0,		17.54-0.07,-4.5-0.1,34.5-0.5,		img_ContourSteamPorte$,1.4)
Contour2_porteAscenseur2=					CollerImage(0,-90,0,		17.54+0.07,-4.5-0.1,34.5-0.5,		img_ContourSteamPorte$,1.4)
Contour3_porteAscenseur2=					CollerImage(0,90,0,			17.54-0.07,-4.5-0.1,34.5-0.5,		img_ContourSteamPorte2$,1.4)
Contour4_porteAscenseur2=					CollerImage(0,90,0,			17.54+0.07,-4.5-0.1,34.5-0.5, 		img_ContourSteamPorte2$,1.4)

RotateEntity centre_porteAscenseur2,0,0,90


Passage1_toit=			CreerTuyau(5,4,		15.4,-3.2,34,		tex_toit_passage1$,1,0,		tex_toit_in_passage1$)
RotateEntity Passage1_toit, 0,0,90




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;CHATEAU :;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;mur enceinte inferieur
mc1_murInf_sud1= 					CreerMur(0.5,4.3,3.2,			35,5.65,28,			tex_mur_chateau$,3,0,			type_mur, 90)
mc1_murInf_sud2= 					CreerMur(0.5,4.3,4.4,			38.1,5.65,26.5,		tex_mur_chateau$,3,0,			type_mur, 45)
mc1_murInf_sud3= 					CreerMur(0.5,4.3,6,				31.3,5.65,25.9,		tex_mur_chateau$,3,0,			type_mur, -45)
mc1_murInf_sud4= 					CreerMur(0.5,4.3,8,				40.0,5.65,21.0,		tex_mur_chateau$,3,0,			type_mur, 5)

mc1_murInf_est1= 					CreerMur(0.5,4.3,44,			29.2,5.65,1.8,		tex_mur_chateau$,3,0,			type_mur, 0)

mc1_murInf_nord1= 					CreerMur(0.5,4.3,3.5,			33.3,5.65,-22.6,	tex_mur_chateau$,3,0,			type_mur, 90)
mc1_murInf_nord2= 					CreerMur(0.5,4.3,3.5,			30.4,5.65,-21.4,	tex_mur_chateau$,3,0,			type_mur, 45)
mc1_murInf_nord3= 					CreerMur(0.5,7.3,3.5,			36.2,4.15,-21.4,	tex_mur_chateau$,3,0,			type_mur, -45)
mc1_murInf_nord4= 					CreerMur(0.5,7.5,3.5,			37.4,4.05,-18.45,	tex_mur_chateau$,3,0,			type_mur, 0)
mc1_murInf_nord5= 					CreerMur(0.5,7.3,3.5,			36.2,4.15,-15.55,	tex_mur_chateau$,3,0,			type_mur, 45)
mc1_murInf_nord6= 					CreerMur(0.5,4.3,1,				34.5,5.65,-14.35,	tex_mur_chateau$,3,0,			type_mur, 90)

mc1_murInf_ouest1= 					CreerMur(0.5,4.3,23,			35.0,5.65,-2.9,		tex_mur_chateau$,3,0,			type_mur, -5)
mc1_murInf_ouest2= 					CreerMur(0.5,4.3,11,			38.0,5.65,12.1,		tex_mur_chateau$,3,0,			type_mur, 155)


mc1_renfortInf_sud1_sud2= 			CreerMur(0.5,4.3,0.501,			36.59,5.65,28.02,			tex_renfort_chateau$,2,0,		type_mur, 67.5)
mc1_renfortInf_sud1_sud3= 			CreerMur(0.501,4.5,0.5,			33.32,5.55,28.02,			tex_renfort_chateau$,2,0,		type_mur, 22.5)
mc1_renfortInf_sud2_sud4= 			CreerMur(0.5,4.3,0.501,			39.65,5.65,25.00,			tex_renfort_chateau$,2,0,		type_mur, 25)
mc1_renfortInf_sud3_est1= 			CreerMur(0.5,4.5,0.5,			29.23,5.55,23.89,			tex_renfort_chateau$,2,0,		type_mur, -22.5)

mc1_renfortInf_nord2_est1= 			CreerMur(0.5,4.5,0.5,			29.19,5.55,-20.20,			tex_renfort_chateau$,2,0,		type_mur, 22.5)
mc1_renfortInf_nord2_nord1= 		CreerMur(0.5,4.5,0.5,			31.60,5.55,-22.61,			tex_renfort_chateau$,2,0,		type_mur, 67.5)
mc1_renfortInf_nord1_nord3= 		CreerMur(0.5,4.5,0.5,			35.01,5.55,-22.6,			tex_renfort_chateau$,2,0,		type_mur, 22.5)
mc1_renfortInf_nord4_nord3= 		CreerMur(0.5,7.5,0.5,			37.41,4.05,-20.19,			tex_renfort_chateau$,2,0,		type_mur, -22.5)
mc1_renfortInf_nord4_nord5= 		CreerMur(0.5,7.5,0.5,			37.4,4.05,-16.74,			tex_renfort_chateau$,2,0,		type_mur, 22.5)
mc1_renfortInf_nord6_nord5= 		CreerMur(0.5,4.3,0.5,			34.98,5.65,-14.33,			tex_renfort_chateau$,2,0,		type_mur, 67.5)

mc1_renfortInf_ouest2_sud4= 		CreerMur(0.5,4.3,0.5,			40.35,5.65,17.05,			tex_renfort_chateau$,2,0,		type_mur, 80)


;enceinte supérieur 90°+0.7Z (45° +0.5X et +0.5Z) -45° -0.5X +05Z
mc1_murSup_sud1= 					CreerMur(0.5,1.0,3.95,			35.0,8.3,28.7,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_murSup_sud2= 					CreerMur(0.5,1.0,5.05,			38.6,8.3,27,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_murSup_sud3= 					CreerMur(0.5,1.0,6.70,			30.8,8.3,26.4,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_murSup_sud4= 					CreerMur(0.5,1.0,8.65,			40.69,8.3,21.06,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_murSup_est1= 					CreerMur(0.5,1.0,44.8,			28.5,8.3,1.8,		tex_mur_chateau$,3,0,			type_block, 0)

mc1_murSup_nord1= 					CreerMur(0.5,1.0,4.2,			33.3,8.3,-23.3,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_murSup_nord2= 					CreerMur(0.5,1.0,4.2,			29.9,8.3,-21.9,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_murSup_nord3= 					CreerMur(0.5,1.0,4.2,			36.7,8.3,-21.9,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_murSup_nord4= 					CreerMur(0.5,1.0,4.2,			38.1,8.3,-18.45,	tex_mur_chateau$,3,0,			type_block, 0)
mc1_murSup_nord5= 					CreerMur(0.5,1.0,4.2,			36.7,8.3,-15.05,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_murSup_nord6= 					CreerMur(0.5,1.0,1.2,			34.8,8.3,-13.65,	tex_mur_chateau$,3,0,			type_block, 90)
mc1_murSup_ouest1= 					CreerMur(0.5,1.0,20.1,			35.49,8.3,-4.26,	tex_mur_chateau$,3,0,			type_block, -5)
mc1_murSup_ouest2= 					CreerMur(0.5,1.0,10.6,			38.84,8.3,12.13,	tex_mur_chateau$,3,0,			type_block, 155)

;crenaux
mc1_creneauInf1a_sud1= 					CreerMur(0.5,0.5,0.5,			36.0,9.05,28.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf1b_sud1= 					CreerMur(0.5,0.5,0.5,			35.5,9.05,28.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2a_sud1= 					CreerMur(0.5,0.5,0.5,			34.5,9.05,28.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2b_sud1= 					CreerMur(0.5,0.5,0.5,			34.0,9.05,28.7,		tex_mur_chateau$,3,0,			type_block, 0)

mc1_creneauInf1a_sud2= 					CreerMur(0.5,0.5,0.55,			37.2,9.05,28.4,		tex_mur_chateau$,3,0,			type_block, 45);
mc1_creneauInf1b_sud2= 					CreerMur(0.5,0.5,0.6,			37.6,9.05,28.0,		tex_mur_chateau$,3,0,			type_block, 45);+0.4 -0.4
mc1_creneauInf2a_sud2= 					CreerMur(0.5,0.5,0.55,			38.4,9.05,27.2,		tex_mur_chateau$,3,0,			type_block, 45);+0.8 -0.8
mc1_creneauInf2b_sud2= 					CreerMur(0.5,0.5,0.6,			38.8,9.05,26.8,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf3a_sud2= 					CreerMur(0.5,0.5,0.6,			39.6,9.05,26.0,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf3b_sud2= 					CreerMur(0.5,0.5,0.6,			40.0,9.05,25.6,		tex_mur_chateau$,3,0,			type_block, 45)

mc1_creneauInf1a_sud3= 					CreerMur(0.5,0.5,0.5,			32.5,9.05,28.1,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf1b_sud3= 					CreerMur(0.5,0.5,0.5,			32.15,9.05,27.75,	tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf2a_sud3= 					CreerMur(0.5,0.5,0.5,			31.4,9.05,27.0,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf2b_sud3= 					CreerMur(0.5,0.5,0.5,			31.05,9.05,26.65,	tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf3a_sud3= 					CreerMur(0.5,0.5,0.5,			30.3,9.05,25.9,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf3b_sud3= 					CreerMur(0.5,0.5,0.5,			29.95,9.05,25.55,	tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf4a_sud3= 					CreerMur(0.5,0.5,0.5,			29.2,9.05,24.8,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf4b_sud3= 					CreerMur(0.5,0.5,0.5,			28.85,9.05,24.45,	tex_mur_chateau$,3,0,			type_block, -45)

mc1_creneauInf1a_sud4= 					CreerMur(0.5,0.5,0.55,			40.37,9.045,24.71,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf1b_sud4= 					CreerMur(0.5,0.5,0.55,			40.41,9.05,24.20,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf2a_sud4= 					CreerMur(0.5,0.5,0.55,			40.50,9.05,23.16,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf2b_sud4= 					CreerMur(0.5,0.5,0.55,			40.54,9.05,22.62,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf3a_sud4= 					CreerMur(0.5,0.5,0.55,			40.63,9.05,21.56,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf3b_sud4= 					CreerMur(0.5,0.5,0.55,			40.68,9.05,21.02,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf4a_sud4= 					CreerMur(0.5,0.5,0.55,			40.77,9.05,19.95,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf4b_sud4= 					CreerMur(0.5,0.5,0.55,			40.83,9.05,19.41,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf5a_sud4= 					CreerMur(0.5,0.5,0.55,			40.92,9.05,18.31,	tex_mur_chateau$,3,0,			type_block, 5)
mc1_creneauInf5b_sud4= 					CreerMur(0.5,0.5,0.55,			40.96,9.05,17.77,	tex_mur_chateau$,3,0,			type_block, 5)

mc1_creneauInf1b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,22.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf1b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,22.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,21.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,20.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf3a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,19.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf3b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,19.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf4a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,18.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf4b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,17.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf5a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,16.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf5b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,16.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf6a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,15.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf6b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,14.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf7a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,13.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf7b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,13.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf8a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,12.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf8b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,11.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf9a_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,10.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf9b_est1= 						CreerMur(0.5,0.5,0.5,			28.5,9.05,10.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf10a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,9.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf10b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,8.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf11b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,7.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf11b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,7.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf12a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,6.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf12b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,5.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf13a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,4.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf13b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,4.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf14a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,3.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf14b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,2.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf15a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,1.8,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf15b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,1.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf16a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,0.3,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf16b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-0.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf17a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-1.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf17b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-1.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf18a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-2.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf18b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-3.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf19a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-4.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf19b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-4.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf20a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-5.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf20b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-6.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf21b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-7.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf21b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-7.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf22a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-8.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf22b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-9.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf23a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-10.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf23b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-10.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf24a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-11.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf24b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-12.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf25a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-13.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf25b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-13.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf26a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-14.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf26b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-15.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf27a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-16.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf27b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-16.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf28a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-17.7,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf28b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-18.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf29a_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-19.2,		tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf29b_est1= 					CreerMur(0.5,0.5,0.5,			28.5,9.05,-19.7,		tex_mur_chateau$,3,0,			type_block, 0)

mc1_creneauInf1a_nord1= 					CreerMur(0.5,0.5,0.5,			32.1,9.05,-23.3,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_creneauInf1b_nord1= 					CreerMur(0.5,0.5,0.5,			32.6,9.05,-23.3,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_creneauInf2a_nord1= 					CreerMur(0.5,0.5,0.5,			33.6,9.05,-23.3,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_creneauInf2b_nord1= 					CreerMur(0.5,0.5,0.5,			34.1,9.05,-23.3,		tex_mur_chateau$,3,0,			type_block, 90)
mc1_creneauInf1a_nord2= 					CreerMur(0.5,0.5,0.5,			29.2,9.05,-21.2,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf1b_nord2= 					CreerMur(0.5,0.5,0.5,			29.55,9.05,-21.55,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf2a_nord2= 					CreerMur(0.5,0.5,0.5,			30.3,9.05,-22.3,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf2b_nord2= 					CreerMur(0.5,0.5,0.5,			30.65,9.05,-22.65,		tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf1a_nord3= 					CreerMur(0.5,0.5,0.5,			35.6,9.05,-23.0,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf1b_nord3= 					CreerMur(0.5,0.5,0.5,			35.95,9.05,-22.65,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf2a_nord3= 					CreerMur(0.5,0.5,0.5,			36.7,9.05,-21.9,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf2b_nord3= 					CreerMur(0.5,0.5,0.5,			37.05,9.05,-21.55,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf3_nord3= 						CreerMur(0.5,0.5,0.5,			37.8,9.04,-20.8,		tex_mur_chateau$,3,0,			type_block, -45)
mc1_creneauInf1a_nord4= 					CreerMur(0.5,0.5,0.6,			38.1,9.05,-20.0,	tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf1b_nord4= 					CreerMur(0.5,0.5,0.5,			38.1,9.05,-19.45,	tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2a_nord4= 					CreerMur(0.5,0.5,0.6,			38.1,9.05,-18.45,	tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf2b_nord4= 					CreerMur(0.5,0.5,0.5,			38.1,9.05,-17.90,	tex_mur_chateau$,3,0,			type_block, 0)
mc1_creneauInf3_nord4= 						CreerMur(0.5,0.5,0.6,			38.1,9.04,-16.95,	tex_mur_chateau$,3,0,			type_block, 0)

mc1_creneauInf1a_nord5= 					CreerMur(0.5,0.5,0.5,			37.8,9.05,-16.15,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf1b_nord5= 					CreerMur(0.5,0.5,0.5,			37.45,9.05,-15.8,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf2a_nord5= 					CreerMur(0.5,0.5,0.5,			36.7,9.05,-15.05,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf2b_nord5= 					CreerMur(0.5,0.5,0.5,			36.35,9.05,-14.7,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf3a_nord5= 					CreerMur(0.5,0.5,0.5,			35.6,9.05,-13.95,	tex_mur_chateau$,3,0,			type_block, 45)
mc1_creneauInf1a_nord6= 					CreerMur(0.5,0.5,0.5,			35.14,9.06,-13.65,	tex_mur_chateau$,3,0,			type_block, 90)
mc1_creneauInf1b_nord6= 					CreerMur(0.5,0.5,0.5,			34.64,9.06,-13.65,	tex_mur_chateau$,3,0,			type_block, 90)


mc1_creneauInf1a_ouest1= 				CreerMur(0.5,0.5,0.5,			34.69,9.05,-13.46,	tex_mur_chateau$,3,0,			type_block, -5)
mc1_creneauInf1b_ouest1= 				CreerMur(0.5,0.5,0.5,			34.73,9.05,-12.97,	tex_mur_chateau$,3,0,			type_block, -5); +0.04;+0.49
mc1_creneauInf2a_ouest1= 				CreerMur(0.5,0.5,0.5,			34.82,9.05,-11.99,	tex_mur_chateau$,3,0,			type_block, -5); +0.13;+1.47
mc1_creneauInf2b_ouest1= 				CreerMur(0.5,0.5,0.5,			34.86,9.05,-11.50,	tex_mur_chateau$,3,0,			type_block, -5); +0.04;+0.49
mc1_creneauInf3a_ouest1= 				CreerMur(0.5,0.5,0.5,			34.95,9.05,-10.52,	tex_mur_chateau$,3,0,			type_block, -5); +0.13;+1.47
mc1_creneauInf3b_ouest1= 				CreerMur(0.5,0.5,0.5,			34.99,9.05,-10.03,	tex_mur_chateau$,3,0,			type_block, -5); +0.04;+0.49
mc1_creneauInf4a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.08,9.05,-9.05,	tex_mur_chateau$,3,0,			type_block, -5); +0.13;+1.47
mc1_creneauInf4b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.12,9.05,-8.56,	tex_mur_chateau$,3,0,			type_block, -5); +0.04;+0.49
mc1_creneauInf5a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.21,9.05,-7.58,	tex_mur_chateau$,3,0,			type_block, -5); +0.13;+1.47
mc1_creneauInf5b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.25,9.05,-7.09,	tex_mur_chateau$,3,0,			type_block, -5); +0.04;+0.49
mc1_creneauInf6a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.34,9.05,-6.11,	tex_mur_chateau$,3,0,			type_block, -5); +0.13;+1.47
mc1_creneauInf6b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.38,9.05,-5.62,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf7a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.47,9.05,-4.64,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf7b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.51,9.05,-4.15,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf8a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.60,9.05,-3.17,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf8b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.64,9.05,-2.68,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf9a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.73,9.05,-1.70,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf9b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.77,9.05,-1.21,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf10a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.85,9.05,-0.23,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf10b_ouest1= 				CreerMur(0.5,0.5,0.5,			35.89,9.05,0.38,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf11a_ouest1= 				CreerMur(0.5,0.5,0.5,			35.98,9.05,1.36,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf11b_ouest1= 				CreerMur(0.5,0.5,0.5,			36.02,9.05,1.85,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf12a_ouest1= 				CreerMur(0.5,0.5,0.5,			36.11,9.05,2.85,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf12b_ouest1= 				CreerMur(0.5,0.5,0.5,			36.15,9.05,3.34,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49
mc1_creneauInf13a_ouest1= 				CreerMur(0.5,0.5,0.5,			36.24,9.05,4.32,	tex_mur_chateau$,3,0,			type_block, -5);+0.13;+1.47
mc1_creneauInf13b_ouest1= 				CreerMur(0.5,0.5,0.5,			36.28,9.05,4.81,	tex_mur_chateau$,3,0,			type_block, -5);+0.04;+0.49

mc1_creneauInf1a_ouest2= 				CreerMur(0.5,0.5,0.5,			40.88,9.05,16.50,		tex_mur_chateau$,3,0,			type_block, 155);+0.6 -0.5
mc1_creneauInf1b_ouest2= 				CreerMur(0.5,0.5,0.5,			40.67,9.05,16.05,		tex_mur_chateau$,3,0,			type_block, 155)
mc1_creneauInf2a_ouest2= 				CreerMur(0.5,0.5,0.5,			40.25,9.05,15.13,		tex_mur_chateau$,3,0,			type_block, 155)
mc1_creneauInf2b_ouest2= 				CreerMur(0.5,0.5,0.5,			40.04,9.05,14.68,		tex_mur_chateau$,3,0,			type_block, 155)
mc1_creneauInf3a_ouest2= 				CreerMur(0.5,0.5,0.5,			39.59,9.05,13.73,		tex_mur_chateau$,3,0,			type_block, 155)
mc1_creneauInf3b_ouest2= 				CreerMur(0.5,0.5,0.5,			39.38,9.05,13.28,		tex_mur_chateau$,3,0,			type_block, 155)
mc1_creneauInf4a_ouest2= 				CreerMur(0.5,0.5,0.5,			38.93,9.05,12.33,		tex_mur_chateau$,3,0,			type_block, 155); 0.66 , 1.40
mc1_creneauInf4b_ouest2= 				CreerMur(0.5,0.5,0.5,			38.72,9.05,11.88,		tex_mur_chateau$,3,0,			type_block, 155); 0.21 , 0.45
mc1_creneauInf5a_ouest2= 				CreerMur(0.5,0.5,0.5,			38.27,9.05,10.93,		tex_mur_chateau$,3,0,			type_block, 155); -0.66 , -1.40
mc1_creneauInf5b_ouest2= 				CreerMur(0.5,0.5,0.5,			38.06,9.05,10.48,		tex_mur_chateau$,3,0,			type_block, 155); -0.21 , -0.45
mc1_creneauInf6a_ouest2= 				CreerMur(0.5,0.5,0.5,			37.61,9.05,09.53,		tex_mur_chateau$,3,0,			type_block, 155); -0.66 , -1.40
mc1_creneauInf6b_ouest2= 				CreerMur(0.5,0.5,0.5,			37.40,9.05,09.08,		tex_mur_chateau$,3,0,			type_block, 155); -0.21 , -0.45
mc1_creneauInf7a_ouest2= 				CreerMur(0.5,0.5,0.5,			36.98,9.05,08.13,		tex_mur_chateau$,3,0,			type_block, 155); -0.66 , -1.40
mc1_creneauInf7b_ouest2= 				CreerMur(0.5,0.5,0.4,			36.81,9.05,07.78,		tex_mur_chateau$,3,0,			type_block, 155); -0.21 , -0.45


;ici SUPP


;sol
mc1_sol_sud1= 					CreerMur(0.1,4.2,11.0,			35,7.8,23,				tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_sud2= 					CreerMur(0.1,5.0,5.4,			36.7,7.799,25.1,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_sud3= 					CreerMur(0.1,5.0,7.0,			32.7,7.799,24.5,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_sud4= 					CreerMur(0.1,5.0,9.0,			38.1,7.801,20.7,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_est1= 					CreerMur(0.1,6.0,45.0,			31.7,7.802,1.8,			tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord1= 					CreerMur(0.1,4.5,5.0,			33.3,7.799,-20.6,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord2= 					CreerMur(0.1,5.3,4.5,			31.9,7.8,-19.9,			tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord3= 					CreerMur(0.1,5.3,4.5,			34.7,7.801,-20.0,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord4= 					CreerMur(0.1,5.3,4.5,			35.3,7.799,-18.4,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord5= 					CreerMur(0.1,5.3,4.5,			34.7,7.8,-16.9,			tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_nord6= 					CreerMur(0.1,2.0,2.0,			34.5,7.799,-14.65,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_ouest1= 				CreerMur(0.1,4.0,24.0,			33.6,7.801,-2.9,		tex_sol_chateau$,3,0,		type_sol(1,1))
mc1_sol_ouest2= 				CreerMur(0.1,8.0,12.0,			35.2,7.799,13.3,		tex_sol_chateau$,3,0,		type_sol(1,1))




RotateEntity mc1_sol_sud1,0,0,90
RotateEntity mc1_sol_sud2,0,45,90
RotateEntity mc1_sol_sud3,0,-45,90
RotateEntity mc1_sol_sud4,0,5,90
RotateEntity mc1_sol_est1,0,0,90
RotateEntity mc1_sol_nord1,0,0,90
RotateEntity mc1_sol_nord2,0,45,90
RotateEntity mc1_sol_nord3,0,-45,90
RotateEntity mc1_sol_nord4,0,0,90
RotateEntity mc1_sol_nord5,0,45,90
RotateEntity mc1_sol_nord6,0,0,90
RotateEntity mc1_sol_ouest1,0,-5,90
RotateEntity mc1_sol_ouest2,0,155,90

;;;Mini chateau2 :  
;tour1
;mc2_tour1_ouest1= 					CreerMur(0.5,4.3,7,			48.7,6.65,-3.8,			tex_mur_chateau$,3,0,			type_mur,0)
;mc2_tour1_sud_ouest1= 				CreerMur(0.5,4.3,7,			51.1,6.65,2.0,			tex_mur_chateau$,3,0,			type_mur,135)
;mc2_tour1_sud1= 					CreerMur(0.5,4.3,7,			56.9,6.65,4.4,			tex_mur_chateau$,3,0,			type_mur,90)
;mc2_tour1_sud_est1= 				CreerMur(0.5,4.3,7,			62.7,6.65,2.0,			tex_mur_chateau$,3,0,			type_mur,45)
;mc2_tour1_est1= 					CreerMur(0.5,4.3,7,			65.1,6.65,-3.8,			tex_mur_chateau$,3,0,			type_mur,0)
;mc2_tour1_nord_est1=				CreerMur(0.5,4.3,7,			62.7,6.65,-9.6,			tex_mur_chateau$,3,0,			type_mur,-45)
;mc2_tour1_nord1=					CreerMur(0.5,4.3,7,			56.9,6.65,-12.0,			tex_mur_chateau$,3,0,			type_mur,-90)
;mc2_tour1_nord_ouest1=				CreerMur(0.5,4.3,7,			51.1,6.65,-9.6,			tex_mur_chateau$,3,0,			type_mur,-135)

;mc2_tour2_nord_est1= 				CreerMur(0.5,4.3,3,			69.8,7.65,-26.1,			tex_mur_chateau$,3,0,			type_mur,45)
;mc2_tour2_nord1= 					CreerMur(0.5,4.3,3,			72.2,7.65,-27.1,			tex_mur_chateau$,3,0,			type_mur,90)
;mc2_tour2_nord_ouest1=				CreerMur(0.5,4.3,3,			74.6,7.65,-26.1,			tex_mur_chateau$,3,0,			type_mur,135)
;mc2_tour2_ouest1=					CreerMur(0.5,4.3,3,			75.6,7.65,-23.7,			tex_mur_chateau$,3,0,			type_mur,0)
;mc2_tour2_sud_ouest1=				CreerMur(0.5,4.3,3,			74.6,7.65,-21.3,			tex_mur_chateau$,3,0,			type_mur,-135)
;mc2_tour2_sud1=						CreerMur(0.5,4.3,3,			72.2,7.65,-20.3,			tex_mur_chateau$,3,0,			type_mur,-90)
;mc2_tour2_sud_est1=					CreerMur(0.5,4.3,3,			69.8,7.65,-21.3,			tex_mur_chateau$,3,0,			type_mur,-45)
;mc2_tour2_est1=						CreerMur(0.5,4.3,3,			68.8,7.65,-23.7,			tex_mur_chateau$,3,0,			type_mur,0)


;sol
;mc2_tour1_sol1= 					CreerMur(0.1,3.4,7,			72.3,9.8,-23.7,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour1_sol2= 					CreerMur(0.1,3.4,7,			72.3,9.798,-23.7,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour1_sol3= 					CreerMur(0.1,3.4,7,			72.3,9.799,-23.7,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour1_sol4= 					CreerMur(0.1,3.4,7,			72.3,9.798,-23.7,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour2_sol1= 					CreerMur(0.1,7.4,16,			56.9,8.8,-3.8,					tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour2_sol2= 					CreerMur(0.1,7.4,16,			56.9,8.798,-3.8,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour2_sol3= 					CreerMur(0.1,7.4,16,			56.9,8.799,-3.8,				tex_sol_chateau$,3,0,		type_sol(1,1))
;mc2_tour2_sol4= 					CreerMur(0.1,7.4,16,			56.9,8.798,-3.8,				tex_sol_chateau$,3,0,		type_sol(1,1))


;RotateEntity mc2_tour1_sol1,0,0,90
;RotateEntity mc2_tour1_sol2,0,45,90
;RotateEntity mc2_tour1_sol3,0,90,90
;RotateEntity mc2_tour1_sol4,0,-45,90
;RotateEntity mc2_tour2_sol1,0,0,90
;RotateEntity mc2_tour2_sol2,0,45,90
;RotateEntity mc2_tour2_sol3,0,90,90
;RotateEntity mc2_tour2_sol4,0,-45,90

;platforme2
sol2_1_chemin=			CreerMur(0.4,4,2,			50,2.4,-18.1,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol2_2_chemin=			CreerMur(0.4,4,2,			50,2.398,-18.1,			tex_dale_chemin$,2,1,		type_sol(2,1))
sol2_3_chemin=			CreerMur(0.4,4,2,			50,2.399,-18.1,			tex_dale_chemin$,2,1,		type_sol(2,1))
sol2_4_chemin=			CreerMur(0.4,4,2,			50,2.398,-18.1,			tex_dale_chemin$,2,1,		type_sol(2,1))

platforme2_mur_sud= 					CreerMur(0.5,2,1.9,				50,2.4,-16.1,			tex_mur_chateau$,3,0,			type_block, 90)
platforme2_mur_sud_est= 				CreerMur(0.5,2,1.9,				48.6,2.39,-16.7,		tex_mur_chateau$,3,0,			type_block, 135)
platforme2_mur_est= 					CreerMur(0.5,2,1.9,				48,2.4,-18.1,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_mur_nord_est= 			CreerMur(0.5,2,1.9,				48.57,2.39,-19.53,	tex_mur_chateau$,3,0,			type_block, 45)
platforme2_mur_nord= 				CreerMur(0.5,2,1.9,				50,2.4,-20.1,			tex_mur_chateau$,3,0,			type_block, 90)
;platforme2_creneau1a_sud= 				CreerMur(0.5,0.5,0.5,			51.7,2.4,-16.1,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1a_nord= 			CreerMur(0.5,0.5,0.5,			49.7,3.64,-20.1,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1b_nord= 			CreerMur(0.5,0.5,0.5,			50.2,3.64,-20.1,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1a_nord_est= 		CreerMur(0.5,0.5,0.5,			48.77,3.64,-19.73,		tex_mur_chateau$,3,0,			type_block, 45)
platforme2_creneau1b_nord_est= 		CreerMur(0.5,0.5,0.5,			48.42,3.64,-19.38,		tex_mur_chateau$,3,0,			type_block, 45)
platforme2_creneau1a_est= 			CreerMur(0.5,0.5,0.5,			48,3.64,-17.85,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1b_est= 			CreerMur(0.5,0.5,0.5,			48,3.64,-18.35,			tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1a_sud_est= 		CreerMur(0.5,0.5,0.5,			48.4,3.64,-16.9,		tex_mur_chateau$,3,0,			type_block, 45)
platforme2_creneau1b_sud_est= 		CreerMur(0.5,0.5,0.5,			48.74,3.64,-16.54,		tex_mur_chateau$,3,0,			type_block, 45)
platforme2_creneau1a_sud= 			CreerMur(0.5,0.5,0.5,			50.25,3.64,-16.1,		tex_mur_chateau$,3,0,			type_block, 0)
platforme2_creneau1b_sud= 			CreerMur(0.5,0.5,0.5,			49.75,3.64,-16.1,		tex_mur_chateau$,3,0,			type_block, 0)

RotateEntity sol2_1_chemin,0,-50,90
RotateEntity sol2_2_chemin,0,-5,90
RotateEntity sol2_3_chemin,0,40,90
RotateEntity sol2_4_chemin,0,-95,90


;.Personnage MJ
;mis dans la fonction.bb

;MasterJ=LoadAnimMesh("objets\makbot\mak_robotic.x" )
;EntityShininess MasterJ,0.5
;ScaleEntity MasterJ,0.04,0.04,0.04
;PositionEntity MasterJ,64.65,8.9,-18.2
;TurnEntity MasterJ,0,-70,0
;Animate MasterJ,1,.25

invWall1_boat=		CreerMur(0.7,1,1.5,	65.25,9.4,-19,			tex_rampe_chemin$,1,4,		type_block)
invWall2_boat=		CreerMur(0.7,1,1,	64.65,9.2,-18.2,			tex_rampe_chemin$,1,4,		type_block)
RotateEntity invWall1_boat,0,130,0
RotateEntity invWall2_boat,0,130,0
EntityAlpha invWall1_boat,0
EntityAlpha invWall2_boat,0



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;FIN CHATEAU;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;pont suspendu
pont_suspendu=LoadMesh("objets\HangingBridge\HangingBridge.3DS")

tex_pont_suspendu=LoadTexture("objets\HangingBridge\HangingBridge.jpg")
EntityTexture pont_suspendu,tex_pont_suspendu
ScaleEntity pont_suspendu,0.0022,0.002,0.0014
PositionEntity pont_suspendu,39.72,8.24,1.82
RotateEntity pont_suspendu,-13,-111,00
EntityType pont_suspendu,3



;entiteTest=MasterJ
;mode_debug=True
;pour téléportation:
;dans playersolo:
;
;				ElseIf KeyDown(keys(7,1)) Or KeyDown(keys(50,1)); S ou down
;					vit_z=-1
;					PositionEntity pl_grp_pivot, 35,9,24
;				Else



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Rajout_Nico;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
play_music(06,1)

Select entrance
	Case 1 ; en venant du garage
		pos_entrance#(1)=93     ;; <- mets ici tes valeurs
		pos_entrance#(2)=-3.2
		pos_entrance#(3)=43
	Case 2 ; en venant de l'usine
		pos_entrance#(1)=73     ;; <- mets ici tes valeurs
		pos_entrance#(2)=3.1
		pos_entrance#(3)=-34
	Case 3 ; en venant des rebelle
		pos_entrance#(1)=18.5     ;; <- mets ici tes valeurs
		pos_entrance#(2)=-5.6
		pos_entrance#(3)=34
	Case 4 ; en venant de l'usine pour MJ
		pos_entrance#(1)=63.73     ;; <- mets ici tes valeurs
		pos_entrance#(2)=9
		pos_entrance#(3)=-13.87
	Default ; si on a pas d'arrivée normale
		pos_entrance#(1)=93     
		pos_entrance#(2)=-1.8
		pos_entrance#(3)=43
End Select