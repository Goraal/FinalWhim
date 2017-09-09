.DEFINITION_CONSTANTES
HAUTEUR_PLAFOND#			=3.50
H_step#						=1.25
H_stair#					=H_step#/8.0

.CHARGEMENT_TEXTURE
t_porte_garage=LoadTexture("textures/loran/porte_garage.jpg") 
t_metaporte=LoadTexture("textures/loran/MetalDoors3.jpg") 
t_porte_parking=LoadTexture("textures/loran/Porte_Parking_1.jpg")
ScaleTexture t_porte_parking,1.0/8.0,1.0/2.0
ScaleTexture t_metaporte,0.5,1 ;fait semblant qu'il y ai 2 partie => fait beaucoup moins faux

tex_sol_canyon$				="objets/canyon/canyon.jpg"
tex_ciel$					="textures/loran/Skies2.jpg"
tex_grillage$				="textures/environnement/net.bmp"
tex_porte_passage1$			="textures/loran/DoorsMetal4.jpg";DoorsMetal2.jpg"
tex_mur_passage1$			="textures/loran/MarbleWhite2.jpg"
tex_toit_passage1$			="textures/loran/mur_usine.jpg"
tex_toit_in_passage1$		="sprites/loran/rond_metal2_fond_noir.bmp"
tex_pilier$					="textures/loran/velour_petit.jpg"

tex_sol_passerelle$			="textures/loran/passerelle.bmp"
tex_dale_chemin$			="textures/loran/MarbleWhite2.jpg"
tex_rampe_chemin$			="textures/loran/WoodWall3.jpg"
tex_porte_chemin$			="textures/loran/DoorsMetal2.jpg"
tex_maintient$				="textures/loran/METAL.BMP"

tex_cuir_noir$					="textures/loran/cuir-noir.jpg"
tex_cuivre1$					="textures/loran/Cuivre_neuf.bmp"
tex_velourRouge$				="textures/loran/velour_petit.jpg"
img_ContourSteamPorte$			="sprites/loran/porte ascenceur.bmp"
img_ContourSteamPorte2$			="sprites/loran/porte ascenceur2.bmp"
img_deco1$						="sprites/loran/deco1.bmp"
img_message$					="sprites/loran/Message Parchemin.jpg"


tex_sol$					="textures/environnement/train_floor.jpg"



;img_fondSteamille$		="sprites/loran/fond_steamille_fond_noir.bmp"

















.CREATION_MONDE

;skybox
skybox=CreateSphere(16)
FlipMesh skybox
ScaleEntity skybox,200,200,200 
RotateEntity skybox,60,180,60 
EntityFX skybox,1
EntityOrder skybox,1
tex=LoadTexture(tex_ciel$)
EntityTexture skybox,tex

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



;Lumière du soleil
CreerLumiereRond(220,194,15,		12,150,33,			175)

.grillage
;Grillage extérieur
grillage_sud1=			CreerMur(110,5,0.01,	55,7.9,-55,	tex_grillage$,8,4)	
grillage_nord1=			CreerMur(110,5,0.01,	55,7.7,51,	tex_grillage$,8,4)
grillage_ouest1=		CreerMur(0.01,5,112,	0,7.9,0,	tex_grillage$,8,4)	
grillage_est1=			CreerMur(0.01,5,112,	110,7.9,0,	tex_grillage$,8,4)

RotateEntity grillage_sud1,32,0,0
RotateEntity grillage_nord1,-32,0,0
RotateEntity grillage_est1,0,0,32
RotateEntity grillage_ouest1,0,0,-32


.Sortie
;Passage1
Passage1_est1=			CreerMur(0.01,3.5,5,		17.6,-4.4,34,	tex_mur_passage1$,2)
Passage1_nord1=			CreerMur(4,3.5,0.01,		15.6,-4.4,36.5,	tex_mur_passage1$,2)
Passage1_sud1=			CreerMur(4,3.5,0.01,		15.6,-4.4,31.5,	tex_mur_passage1$,2)
Passage1_plafond=		CreerMur(0.01,4,5,			15.6,-2.65,34,	tex_mur_passage1$,2)
Passage1_porte=			CreerMur(0.01,2.5,2.5,		17.61,-4.9,34,	tex_porte_passage1$,2.5)

RotateEntity Passage1_plafond,0,0,90

pilier1=LoadMesh("objets\pilier\pilier.3DS")
pilier2=LoadMesh("objets\pilier\pilier.3DS")

tex_pilier=LoadTexture(tex_pilier$)
EntityTexture pilier1,tex_pilier
EntityTexture pilier2,tex_pilier
ScaleEntity pilier1,0.0002,0.00018,0.0002
ScaleEntity pilier2,0.0002,0.00018,0.0002

PositionEntity pilier1,17.6,-6,31.5
PositionEntity pilier2,17.6,-6,36.5

Passage1_toit=			CreerTuyau(5,4,		15.4,-3.2,34,		tex_toit_passage1$,1,0,		tex_toit_in_passage1$)
RotateEntity Passage1_toit, 0,0,90


.Chemin_usine
;Passage entre colline
passerelle1_ps=							CreerMur(0.01,16,2,		42.5,5.7,6.5,				tex_sol_passerelle$,1,4,		type_sol(2,1))	
;passerelle2_uc=						CreerMur(0.01,2,11,		45,2*H_step#,17,			tex_sol_passerelle$,1,4,		type_block)	
;passerelle3_uc=						CreerMur(0.01,1,24,		50.5,2*H_step#,14.5,		tex_sol_passerelle$,1,4,		type_block)

RotateEntity passerelle1_ps,0,-20,85

;Chemin/escalier pour monter la colline
rampe1_chemin=			CreerMur(0.2,2,13.5,		45.7,-3.8,-29.6,			tex_rampe_chemin$,2,1,		type_sol(2,1))
rampe2_chemin=			CreerMur(0.2,2,10.3,		53.7,0.5,-23.1,				tex_rampe_chemin$,2,1,		type_sol(2,1))
rampe3_chemin=			CreerMur(0.2,2,20,			59.4,2.5,-23.3,				tex_rampe_chemin$,2,1,		type_sol(2,1))
sol1_chemin=			CreerMur(0.2,10,6,			54,-1.5,-25.0,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol2_chemin=			CreerMur(0.4,4,3,			50,2.4,-18.1,				tex_dale_chemin$,2,1,		type_sol(2,1))
sol3_chemin=			CreerMur(0.2,6,10,			71.1,2.6,-32.4,				tex_dale_chemin$,1,1,		type_sol(2,1))


RotateEntity rampe1_chemin,-20,-50,90
RotateEntity rampe2_chemin,-23,40,90
RotateEntity rampe3_chemin,1,53,90
RotateEntity sol1_chemin,0,-50,90
RotateEntity sol2_chemin,0,40,90
RotateEntity sol3_chemin,0,40,90

Passage1_Tourelle1=LoadMesh("objets\poste_tir\poste_tir.x")
ScaleEntity Passage1_Tourelle1,3.5,3.5,3.5
;TurnEntity Passage1_Tourelle1,-25,-80,0
TurnEntity Passage1_Tourelle1,0,-80,0
PositionEntity Passage1_Tourelle1,53.3,1.0,-31.9
EntityType Passage1_Tourelle1,3

;Passage à Aladin
Chemin1_SP_mask=		LoadMesh("objets\masque_gaz\mask.X")
PositionEntity Chemin1_SP_mask,72.8,4,-28.9			
ScaleEntity Chemin1_SP_mask,4,4,4


Chemin1_Porte=			CreerMur(1.5,1.5,0.05,		72.8,3.8,-33.1,			tex_porte_chemin$,1.5)

TurnEntity Chemin1_SP_mask,15,0,0
TurnEntity Chemin1_Porte,15,0,0

Fixation1_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		76.5,2.5,-34.3,		tex_maintient$)
Fixation2_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		72.0,2.5,-38.0,		tex_maintient$)
Fixation3_sol3_chemin=		CreerSphere(0.15,0.15,0.15,		65.7,2.5,-30.5,		tex_maintient$)


Tube1_sol3_chemin=			CreerTuyau(0.1,4,				76.5,0.5,-34.3,			tex_maintient$)
Tube2_sol3_chemin=			CreerTuyau(0.1,8,				72.0,-1.5,-38.0,		tex_maintient$)
Tube3_sol3_chemin=			CreerTuyau(0.1,4,				65.7,0.5,-30.5,			tex_maintient$)












.Ascenseur1_garage1
;intérieur
mur_Ascenseur1_sud=			CreerMur(0.1,3,3,			95.95,-0.9,43,			tex_velourRouge$,3)
mur_Ascenseur1_est=			CreerMur(3,3,0.1,			95.05,-0.9,44.4,			tex_velourRouge$,3)
mur_Ascenseur1_ouest=		CreerMur(3,3,0.1,			95.05,-0.9,41.6,			tex_velourRouge$,3)
plafond_Ascenseur1=			CreerMur(3,3,0.1,			95.05,0.6,43,				tex_velourRouge$,3)
sol_Ascenseur1=				CreerMur(3,3,0.1,			95.05,-2.4,43,				tex_parquet$,3)

RotateEntity plafond_Ascenseur1,90,0,0 
RotateEntity sol_Ascenseur1,90,0,0 

message_Ascenseur1=			CollerImage(0,180,0,		94.7,-0.9,41.71,		img_message$,1,4)
ScaleSprite message_Ascenseur1,0.21,0.28

deco1Sud_Ascenseur1=			CollerImage(180,90,0,		95.89,-0.9,41.72,			img_deco1$,1.5,4)
deco2Sud_Ascenseur1=			CollerImage(0,270,0,		95.89,-0.9,44.28,			img_deco1$,1.5,4)
deco1Est_Ascenseur1=			CollerImage(0,0,0,			93.62,-0.88,44.34,			img_deco1$,1.5,4)
deco2Est_Ascenseur1=			CollerImage(180,180,0,		95.8,-0.88,44.34,			img_deco1$,1.5,4)
deco1Ouest_Ascenseur1=		CollerImage(0,180,0,		95.8,-0.92,41.66,			img_deco1$,1.5,4)
deco2Ouest_Ascenseur1=		CollerImage(180,0,0,		93.62,-0.92,41.66,			img_deco1$,1.5,4)

;extérieur
Global centre_porteAscenseur1=  				CreerTuyau(0.3,0.30,			93.46,-1,43.45,		tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
Global tranche1_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.40,-1+0.9*Sin(290),43.45+0.9*Cos(290),			tex_cuivre1$)
Global tranche2_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.42,-1+0.9*Sin(243),43.45+0.9*Cos(243),			tex_cuivre1$)
Global tranche3_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.44,-1+0.9*Sin(196),43.45+0.9*Cos(196),			tex_cuivre1$)
Global tranche4_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.46,-1+0.9*Sin(149),43.45+0.9*Cos(149),			tex_cuivre1$)
Global tranche5_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.48,-1+0.9*Sin(196),43.45+0.9*Cos(196),			tex_cuivre1$)
Global tranche6_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.50,-1+0.9*Sin(243),43.45+0.9*Cos(243),			tex_cuivre1$)
Global tranche7_porteAscenseur1=				CreerSphere(0.02,1.8,1.8,		93.52,-1+0.9*Sin(290),43.45+0.9*Cos(290),			tex_cuivre1$)


Contour_porteAscenseur1=					CollerImage(0,-90,0,			93.38,-1.1,43,		img_ContourSteamPorte$,1.4)
Contour2_porteAscenseur1=					CollerImage(0,-90,0,			93.54,-1.1,43,		img_ContourSteamPorte$,1.4)
Contour3_porteAscenseur1=					CollerImage(0,90,0,				93.38,-1.1,43,		img_ContourSteamPorte2$,1.4)
Contour4_porteAscenseur1=					CollerImage(0,90,0,				93.54,-1.1,43,		img_ContourSteamPorte2$,1.4)

RotateEntity centre_porteAscenseur1,0,0,90

CreerAnimationRotRoll(tranche1_porteAscenseur1,47, 1, 0, -1.0, 43.45, 290)
CreerAnimationRotRoll(tranche2_porteAscenseur1,47, 2, 0, -1.0, 43.45, 243)
CreerAnimationRotRoll(tranche3_porteAscenseur1,47, 3, 0, -1.0, 43.45, 196)
CreerAnimationRotRoll(tranche4_porteAscenseur1,47, 4, 0, -1.0, 43.45, 149)
CreerAnimationRotRoll(tranche5_porteAscenseur1,47, 3, 0, -1.0, 43.45, 196)
CreerAnimationRotRoll(tranche6_porteAscenseur1,47, 2, 0, -1.0, 43.45, 243)
CreerAnimationRotRoll(tranche7_porteAscenseur1,47, 1, 0, -1.0, 43.45, 290)

;Mur Ext
murExt_Ascenseur1_sud=			CreerMur(0.1,5,3.7,			96.55,-1.8,43.35,			tex_mur_passage1$,3)
murExt_Ascenseur1_est=			CreerMur(3.2,5,0.1,			94.95,-1.8,45.2,			tex_mur_passage1$,3)
murExt_Ascenseur1_ouest=			CreerMur(3.2,5,0.1,			94.95,-1.8,41.5,			tex_mur_passage1$,3)
murExt_Ascenseur1_nord1=			CreerMur(0.1,5,0.9,			93.35,-1.8,44.75,			tex_mur_passage1$,3)
murExt_Ascenseur1_nord2=			CreerMur(0.1,0.4,2.8,		93.35,0.5,42.9,				tex_mur_passage1$,3)


plafondExt_Ascenseur1=			CreerMur(3.2,3.7,0.1,		94.95,0.7,43.35,			tex_mur_passage1$,3)
;solExt_Ascenseur1=				CreerMur(3,3,0.1,			95.05,-2.4,43,				tex_mur_passage1$,3)
RotateEntity plafondExt_Ascenseur1,90,0,0 
rampe1_Ascenseur1=				CreerMur(2,13.5,3,			86.9,-4.6,43.05,			tex_rampe_chemin$,2,1,		type_sol(2,1))



RotateEntity rampe1_Ascenseur1,0,0,-80


.Ascenseur2_garage1
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
Global centre_porteAscenseur2=  				CreerTuyau(0.3,0.30,			95.46,-4.3,-43.3,									tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
Global tranche1_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.40,-4.3-0.9*Sin(290),-43.3-0.9*Cos(290),			tex_cuivre1$)
Global tranche2_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.42,-4.3-0.9*Sin(243),-43.3-0.9*Cos(243),			tex_cuivre1$)
Global tranche3_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.44,-4.3-0.9*Sin(196),-43.3-0.9*Cos(196),			tex_cuivre1$)
Global tranche4_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.46,-4.3-0.9*Sin(149),-43.3-0.9*Cos(149),			tex_cuivre1$)
Global tranche5_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.48,-4.3-0.9*Sin(196),-43.3-0.9*Cos(196),			tex_cuivre1$)
Global tranche6_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.50,-4.3-0.9*Sin(243),-43.3-0.9*Cos(243),			tex_cuivre1$)
Global tranche7_porteAscenseur2=				CreerSphere(0.02,1.8,1.8,		95.52,-4.3-0.9*Sin(290),-43.3-0.9*Cos(290),			tex_cuivre1$)


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








































;------------------------------------------------------FONCTIONS---------------------------------------------------------
.FONCTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;fonctions pour les animations;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function CreerAnimationMovRot(objet,max=10, dx#=0, dy#=1, dz#=0, rx#=0, ry#=0, rz#=0)
	For frame=1 To max
		TranslateEntity objet,dx#,dy#,dz#
		TurnEntity objet,rx#,ry#,rz#
		SetAnimKey objet,frame
	Next
	seq = AddAnimSeq(objet,max-1) ; total number of frames
	;;Return To initial position
	TranslateEntity objet,-dx#*max,-dy#*max,-dz#*max
	TurnEntity objet,-rx#*max,-ry#*max,-rz#*max
	Return seq
End Function

;RETROUVER COMMENT ON CALCULE UN ANGLE et l'enlever des paramètre d'entrée!!!!!!!!!!!!!!!!!!!! PETIT EFRONTE
Function CreerAnimationRotRoll(objet,max=10, Vangle#=10, Vrayon#=0, Cy#=0, Cz#=0, angle#)
	rayon#= Sqr( (Cz#-EntityZ#(objet))*(Cz#-EntityZ#(objet))+(Cy#-EntityY#(objet))*(Cy#-EntityY#(objet)) )
	;angle#= ?????????????
	Debug_Valeur#=rayon#
	For frame=1 To max
		rayon#=rayon#+Vrayon#
		angle#=angle#+Vangle#
		PositionEntity objet,EntityX(objet),Cy#+rayon#*Sin(angle#),Cz#+rayon#*Cos(angle#)
		SetAnimKey objet,frame
	Next
	seq = AddAnimSeq(objet,max-1) ; total number of frames
	;;Return To initial position
	rayon#=rayon#-max*Vrayon#
	angle#=angle#-max*Vangle#
	PositionEntity objet,EntityX(objet),Cy#+rayon#*Sin(angle#),Cz#+rayon#*Cos(angle#)
	Return seq

End Function


Function AnimateMetaPorte(mode,vitesse#)
	Animate porte_cellule7,mode,vitesse#
	Animate porte2_cellule7,mode,vitesse#
	Animate porte3_cellule7,mode,vitesse#
	Animate porte4_cellule7,mode,vitesse#
	Animate porte_plafond_metaPorte,mode,vitesse#
	Animate Verrin2_metaPorte,mode,vitesse#
	Animate Verrin3_metaPorte,mode,vitesse#
	Animate Verrin4_metaPorte,mode,vitesse#
	Animate Verrin5_metaPorte,mode,vitesse#
	Animate Verrin7_metaPorte,mode,vitesse#
	Animate Verrin8_metaPorte,mode,vitesse#
	Animate Verrin9_metaPorte,mode,vitesse#
	Animate Verrin10_metaPorte,mode,vitesse#
	Animate Verrou1_metaPorte,mode,vitesse#
	Animate Verrou2_metaPorte,mode,vitesse#
	Animate Tuyau1_metaPorte,mode,vitesse#
	Animate Tuyau2_metaPorte,mode,vitesse#
	Animate tete_metaPorte,mode,vitesse#
	Animate base_chapeau_metaPorte,mode,vitesse#
	Animate forme_chapeau_metaPorte,mode,vitesse#
	Animate pivot_nez_metaPorte,mode,vitesse#
	Animate main1_metaPorte,mode,vitesse#
	Animate main2_metaPorte,mode,vitesse#
	Animate boitierSteamille,mode,vitesse#
End Function

Function AnimateSteamPorte(mode,vitesse#,PorteNumber=1)
	If PorteNumber=1
		Animate tranche1_porteAscenseur2,mode,vitesse#
		Animate tranche2_porteAscenseur2,mode,vitesse#
		Animate tranche3_porteAscenseur2,mode,vitesse#
		Animate tranche4_porteAscenseur2,mode,vitesse#
		Animate tranche5_porteAscenseur2,mode,vitesse#
		Animate tranche6_porteAscenseur2,mode,vitesse#
		Animate tranche7_porteAscenseur2,mode,vitesse#
	EndIf
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;fonctions de construction de monde (pour plus de lisibilité);;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function CreerMur(dx#=1,h#=1,dz#=1,x#=0,y#=0,z#=0,tex$="",scaltext#=1,flag=0,	collisiontype=type_mur, rot#=0)	
	mur=CreateCube()
	ScaleEntity mur,dx#/2,h#/2,dz#/2
	PositionEntity mur,x#,y#,z#
	RotateEntity mur,0,rot#,0
	EntityType mur,collisiontype
	
	If tex$="" Then tex$="textures/loran/blanc.jpg":
	texture=LoadTexture(tex$,flag):	
	If texture=0 Then RuntimeError "Impossible d'ouvrir la Texture: "+tex$
	If dx#>dz# Then bz#=dx# Else bz#=dz#
	ScaleTexture texture,scaltext#/bz#,scaltext#/h#
	EntityTexture mur,texture
	FreeTexture texture
	
	Return mur
End Function

Function CreerSphere(dx#=1,h#=1,dz#=0,	x#=0,y#=0,z#=0,tex$="",scaltext#=1,flag=0,collisiontype=type_mur, parent=0)	
	rond=CreateSphere(16,parent)
	ScaleEntity rond,dx#/2,h#/2,dz#/2
	PositionEntity rond,x#,y#,z#
	EntityType rond,collisiontype
	
	If tex$="" Then tex$="textures/loran/blanc.jpg"
	texture=LoadTexture(tex$,flag):	
	If texture=0 Then RuntimeError "Impossible d'ouvrir la Texture: "+tex$
	ScaleTexture texture,scaltext#,scaltext#
	EntityTexture rond,texture
	FreeTexture texture
	
	Return rond
End Function

Function CreerCone(dx#=1,h#=1,dz#=0,	x#=0,y#=0,z#=0,tex$="",scaltext#=1,flag=0,collisiontype=type_mur, parent=0)	
	If parent<>0 Then
		cone=CreateCone(32,True,parent)
		PositionEntity cone,x#-EntityX(parent),y#-EntityY(parent),z#-EntityZ(parent)
	Else
		cone=CreateCone()
		PositionEntity cone,x#,y#,z#
	EndIf
	ScaleEntity cone,dx#/2,h#/2,dz#/2
	EntityType cone,collisiontype
	
	If tex$="" Then tex$="textures/loran/blanc.jpg":
	texture=LoadTexture(tex$,flag):	
	If texture=0 Then RuntimeError "Impossible d'ouvrir la Texture: "+tex$
	ScaleTexture texture,scaltext#,scaltext#
	EntityTexture cone,texture
	FreeTexture texture
	
	Return cone
End Function

Function CollerImage(rx#=0,ry#=0,rz#=0,x#=0,y#=0,z#=0,path$="",scaleImg#=1,flag=5, viewMode=2)
	If path$="" Then path$="textures/loran/blanc.jpg"
	image=LoadSprite(path$,flag)
	If image=0 Then RuntimeError "Impossible d'ouvrir le Sprite: "+path$
	RotateEntity image,rx#,ry#,rz#
	PositionEntity image,x#,y#,z#
	SpriteViewMode image,viewMode
	ScaleSprite image,scaleImg#,scaleImg#
	Return image
End Function

Function CreerTuyau(r#=1,h#=1,x#=0,y#=0,z#=0,tex$="",scaltext#=1,flag=0, imgExt$="", scaleImg#=0.98, collisiontype=type_block)	
	tuyau=CreateCylinder(16)
	ScaleEntity tuyau,r#/2,h#/2,r#/2
	PositionEntity tuyau,x#,y#,z#
	EntityType tuyau,collisiontype
	
	If tex$="" Then tex$="textures/loran/blanc.jpg":
	texture=LoadTexture(tex$,flag):	
	If texture=0 Then RuntimeError "Impossible d'ouvrir la Texture: "+tex$
	ScaleTexture texture,scaltext#/r#,scaltext#/h#
	EntityTexture tuyau,texture
	FreeTexture texture
	
	If imgExt$="" Then imgExt$="sprites/loran/rond_noir_fond_noir.bmp":
	imgHaut=LoadSprite(imgExt$,4,tuyau)
	If imgHaut=0 Then RuntimeError "Impossible d'ouvrir le Sprite: "+imgExt$
	ScaleSprite imgHaut,scaleImg#,scaleImg#
	RotateEntity imgHaut,90,0,0
	PositionEntity imgHaut,0,1.01,0
	SpriteViewMode imgHaut,2
	
	imgBas=LoadSprite(imgExt$,4,tuyau)
	ScaleSprite imgBas,scaleImg#,scaleImg#
	RotateEntity imgBas,-90,0,0
	PositionEntity imgBas,0,-1.01,0
	SpriteViewMode imgBas,2
	
	Return tuyau
End Function

Function CreerLumiereRond(rouge,vert,bleu,		x#,y#,z#,	range#)
	lumiere=CreateLight(2)
	LightColor lumiere,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity lumiere,x#,y#,z#
	LightRange lumiere,range#
	
	;rond=CreateSphere()
	;ScaleEntity rond,0.25,0.1,0.25
	;PositionEntity rond,x#,y#.05,z#
	;EntityType rond,type_none
 	;EntityColor rond,rouge,vert,bleu
	;EntityAlpha rond,0.5
	
	rond=CreateSphere()
	ScaleEntity rond,0.05,0.05,0.05
	PositionEntity rond,x#,y#,z#
	;EntityType rond,type_none
 	EntityColor rond,rouge*3,vert*3,bleu*3
	
	
	Return lumiere
End Function
Function CreerLumiereChandelier(rouge,vert,bleu,		x#,y#,z#,	range#)
	chandelier=LoadMesh("objets\candle_holder\candle_holder.3ds")
	;chandelier=LoadMesh("objets\oldiron\oldiron.3ds")
	ScaleEntity chandelier,0.02,0.02,0.02
	PositionEntity chandelier,x#,y#,z#
	EntityColor chandelier,250,250,250
		;chandel_lumiere4_garage=		Creerchandel_lumiereRond(250,150,25,		13.61,0.71,19.97,			1)
		;2:  13.52/0.71/20.36      3:13.2/0.71/20.6
	chandel_lumiere1=CreateLight(2)
	LightColor chandel_lumiere1,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere1,x#+0.7,y#-0.69,z#
	LightRange chandel_lumiere1,range#
	
	chandel_lumiere2=CreateLight(2)
	LightColor chandel_lumiere2,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere2,x#-0.7,y#-0.69,z#
	LightRange chandel_lumiere2,range#
	
	chandel_lumiere3=CreateLight(2)
	LightColor chandel_lumiere3,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere3,x#,y#-0.69,z#+0.7
	LightRange chandel_lumiere3,range#
	
	chandel_lumiere4=CreateLight(2)
	LightColor chandel_lumiere4,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere4,x#,y#-0.69,z#-0.7
	LightRange chandel_lumiere4,range#
	

Return chandelier
End Function




;Fonction In Game !!!!!!!!!!!!!!!!! Trop fort!
Function InGamePosition(entite, 	x#=0.0,y#=0.0,z#=0.0,		rx#=0.0, ry#=0.0, rz#=0.0)
	PositionEntity entite,EntityX(entite)+x#,EntityY(entite)+y#,EntityZ(entite)+z#
	TurnEntity entite, rx#, ry#, rz#
End Function

Function ModifierPas(augmentation)
	If augmentation Then
		Select pas#
		Case 0.01
			pas#=0.1
		Case 0.1
			pas#=1
		Case 1
			pas#=10
		Case 10
			pas#=100
		Case 100
			pas#=100
		Default
			pas#=1
		End Select
	Else
		Select pas#
		Case 0.01
			pas#=0.01
		Case 0.1
			pas#=0.01
		Case 1
			pas#=0.1
		Case 10
			pas#=1
		Case 100
			pas#=10
		Default
			pas#=1
		End Select
	EndIf
End Function