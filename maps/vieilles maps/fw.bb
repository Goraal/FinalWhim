.CHARGEMENT_TEXTURE
t_porte_garage=LoadTexture("maps/texture/porte_garage.jpg") 
t_metaporte=LoadTexture("maps/texture/MetalDoors3.jpg") 
t_porte_parking=LoadTexture("maps/texture/Porte_Parking_1.jpg")
ScaleTexture t_porte_parking,1.0/8.0,1.0/2.0
ScaleTexture t_metaporte,0.5,1 ;fait semblant qu'il y ai 2 partie => fait beaucoup moins faux

tex_porte_garage$     	="maps/texture/porte_garage.jpg" 
tex_mur_garage$       	="maps/texture/mur_brique.jpg" 
tex_plafond_garage$   	="maps/texture/blanc.jpg" 
tex_mur_parking$      	="maps/texture/mur_agglo.jpg"
tex_plafond_parking$  	="maps/texture/gris.jpg"
tex_verrin$				="maps/texture/gris.jpg"
tex_porte_parking$    	="maps/texture/Porte_Parking_1.jpg"
tex_metal_plate$		="maps/texture/METAL.bmp"
tex_debrit$						="maps/texture/l_zarb.bmp"
tex_sol_parking$				="maps/texture/gravel.jpg"
tex_cuir_noir$					="maps/texture/cuir-noir.jpg"
tex_cuivre1$					="maps/texture/Cuivre_neuf.bmp"
tex_velourRouge$				="maps/texture/velour_petit.jpg"
tex_parquet$					="maps/texture/parquet.jpg"
img_attention_engrenage$		="maps/sprite/attention2.bmp"
img_trou_noir$					="maps/sprite/rond_noir_fond_noir.bmp"
img_visageMetaPorte$			="maps/sprite/VisageMetaPorte.bmp"
img_VerrinExt$					="maps/sprite/rond_gris_fond_noir.bmp"
img_VerrouSteamille$			="maps/sprite/rond_steamille_fond_noir.bmp"
img_MetalCylinderExt$			="maps/sprite/rond_metal_fond_noir.bmp"
img_CuivreCylinderExt$			="maps/sprite/rond_cuivre_fond_noir.bmp"
img_steamPorte$					="maps/sprite/steamDoor.bmp"
img_ContourSteamPorte$			="maps/sprite/porte ascenceur.bmp"
img_ContourSteamPorte2$			="maps/sprite/porte ascenceur2.bmp"
img_caniveau$					="maps/sprite/grille_canniveau1.jpg"
img_caniveau2$					="maps/sprite/grille_canniveau2.jpg"
img_lierre1$					="maps/sprite/liere2.png"
img_message$					="maps/sprite/Message Parchemin.jpg"

.CREATION_MONDE
;CreerMur(dx#=1,h#=1,dz#=1,			x#=0,y#=0,z#=0,		tex$="",scaltext#=1,flag=0,		collisiontype=type_mur,		rot#=0,)
;CollerImage(rx#=0,ry#=0,rz#=0,		x#=0,y#=0,z#=0,		path$="",scaleImg#=1,flag=4,	viewMode=2)
;CreerTuyau(r#=1,h#=1,				x#=0,y#=0,z#=0,		tex$="",scaltext#=1,flag=0, 	imgExt$="", scaleImg#=0.98, 	collisiontype=type_block)		

; SOL mieux:
.sol
sol=					CreerMur(0.01,100,40,		26,0,20,				tex_sol_parking$,1,1,		type_sol(1,1))
RotateEntity sol,0,0,90
EntityPickMode sol,2
;EntityColor sol,60,60,60

sol_caniveau1=			CollerImage(0,0,90,			20,0.05,20,		img_caniveau$)
sol_caniveau2=			CollerImage(0,0,90,			2,0.05,20,		img_caniveau$)
sol_caniveau3=			CollerImage(0,0,90,			4,0.05,20,		img_caniveau$)
sol_caniveau4=			CollerImage(0,0,90,			6,0.05,20,		img_caniveau$)
sol_caniveau5=			CollerImage(0,0,90,			8,0.05,20,		img_caniveau$)
sol_caniveau6=			CollerImage(0,0,90,			10,0.05,20,		img_caniveau$)
sol_caniveau7=			CollerImage(0,0,90,			12,0.05,20,		img_caniveau$)
sol_caniveau8=			CollerImage(0,0,90,			14,0.05,20,		img_caniveau$)
sol_caniveau9=			CollerImage(0,0,90,			16,0.05,20,		img_caniveau$)
sol_caniveau10=			CollerImage(0,0,90,			18,0.05,20,		img_caniveau$)
sol_caniveau11=			CollerImage(0,0,90,			22,0.05,20,		img_caniveau$)
sol_caniveau12=			CollerImage(0,0,90,			24,0.05,20,		img_caniveau$)
sol_caniveau13=			CollerImage(0,0,90,			26,0.05,20,		img_caniveau$)
sol_caniveau14=			CollerImage(0,0,90,			27.25,0.05,20,		img_caniveau2$)
ScaleSprite sol_caniveau1, 1,0.1
ScaleSprite sol_caniveau2, 1,0.1
ScaleSprite sol_caniveau3, 1,0.1
ScaleSprite sol_caniveau4, 1,0.1
ScaleSprite sol_caniveau5, 1,0.1
ScaleSprite sol_caniveau6, 1,0.1
ScaleSprite sol_caniveau7, 1,0.1
ScaleSprite sol_caniveau8, 1,0.1
ScaleSprite sol_caniveau9, 1,0.1
ScaleSprite sol_caniveau10, 1,0.1
ScaleSprite sol_caniveau11, 1,0.1
ScaleSprite sol_caniveau12, 1,0.1
ScaleSprite sol_caniveau13, 1,0.1
ScaleSprite sol_caniveau14, 0.25,0.25
RotateEntity sol_caniveau1, 90,0,0
RotateEntity sol_caniveau2, 90,0,0
RotateEntity sol_caniveau3, 90,0,0
RotateEntity sol_caniveau4, 90,0,0
RotateEntity sol_caniveau5, 90,0,0
RotateEntity sol_caniveau6, 90,0,0
RotateEntity sol_caniveau7, 90,0,0
RotateEntity sol_caniveau8, 90,0,0
RotateEntity sol_caniveau9, 90,0,0
RotateEntity sol_caniveau10, 90,0,0
RotateEntity sol_caniveau11, 90,0,0
RotateEntity sol_caniveau12, 90,0,0
RotateEntity sol_caniveau13, 90,0,0
RotateEntity sol_caniveau14, 90,0,0

; SORTIE
.sortie
sol_sortie_parking=				CreerMur(0.01,10.5,13,		-5.4,1.7,20,		tex_sol_parking$,1,1,		type_sol(1,1))
plafond_sortie_parking=			CreerMur(0.01,10.5,13,		-5.4,5.7,20,		tex_plafond_parking$,1,1,		type_sol(1,1))
mur_sud_sortie_parking=			CreerMur(9.5,8,0.01,		-5.25,4,13.5,		tex_mur_parking$)
mur_nord_sortie_parking=		CreerMur(9.5,8,0.01,		-5.25,4,26.5,		tex_mur_parking$)

;EntityColor sol_sortie_parking,60,60,60
RotateEntity sol_sortie_parking, 0,0,70
RotateEntity plafond_sortie_parking, 0,0,70

;LUMIERE
.lumiere
AmbientLight 100,100,100
lumiere1_garage=		CreerLumiereRond(20,20,30,			28,5.4,20,			10)
lumiere2_garage=		CreerLumiereRond(80,80,120,			18,5.4,20,			10)
lumiere3_garage=		CreerLumiereRond(0,0,10,			8,5.4,20,			10)
lumiere4_garage=		CreerLumiereRond(20,20,30,			38,5.4,20,			10)
lumiere5_garage=		CreerLumiereRond(40,40,60,			48,5.4,20,			10)

;CELLULE1
.cellules

mur_ouest_cellule1=			CreerMur(0.1,3,8,		0,1.5,4,		tex_mur_garage$,3)
mur_est_cellule1=			CreerMur(0.1,3,8,		6,1.5,4,		tex_mur_garage$,3)
panneau_cellule1=			CollerImage(0,180,0,	0.5,1.8,0.06,	img_attention_engrenage$,0.25)
message_cellule1=			CollerImage(0,90,0,		0.1,1.5,5,		img_message$,1,4)
ScaleSprite message_cellule1,0.21,0.28

roue_cellule1=LoadMesh("maps\Objet\roue.X")
PositionEntity roue_cellule1,3,1.22,0.2
RotateEntity roue_cellule1,90,0,0
ScaleEntity roue_cellule1,2,2,2
EntityType roue_cellule1,type_none
EntityFX roue_cellule1,1

;CELLULE STALE
mur_ouest_cellule_stale=		CreerMur(0.1,5.5,8,		6.5,2.75,4,			tex_mur_garage$,3)
mur_est_cellule_stale=			CreerMur(0.1,5.5,8,		12.5,2.75,4,		tex_mur_garage$,3)


;PARKING MURS
.parking_murs
mur_cellule0_cellule1=			CreerMur(0.5,3,0.1,			-0.25,1.5,8,		tex_mur_parking$,3)
mur_cellule1_cellule2=			CreerMur(0.5,3,0.1,			6.25,1.5,8,			tex_mur_parking$,3)
FAKE_mur_cellule2_escalier=		CreerMur(40,3,0.1,			32.5,1.5,8,			tex_mur_parking$,3)
mur_haut_cellule1_cellule2=		CreerMur(7,2.5,0.1,			3,4.25,8,			tex_mur_parking$,3)
mur_haut_cellule3_escalier=		CreerMur(40,2.5,0.1,		32.5,4.25,8,		tex_mur_parking$,3)
mur_sud_cellules_sud=			CreerMur(53,5.5,0.1,		26,2.75,0,			tex_mur_garage$,3)
plafond_parking=				CreerMur(0.1,53,48,			26,5.5,20,			tex_plafond_parking$)
plafond_cellules_sud=			CreerMur(0.1,6,8,			3,3,4,				tex_plafond_garage$,3)
mur_ouest1_parking=				CreerMur(0.1,5.5,5.5,		-0.5,2.75,10.75,	tex_mur_parking$,3)
mur_ouest2_parking=				CreerMur(0.1,5.5,5.5,		-0.5,2.75,29.25,	tex_mur_parking$,3)
FAKE_mur_nord_parking=			CreerMur(53,5.5,0.1,		26,2.75,32,			tex_mur_parking$,3)
mur_est_haut_parking=			CreerMur(0.18,2.8,24,		52.46,4.1,20,		tex_mur_parking$,3)
mur_est1_parking=				CreerMur(0.18,2.7,10.6,		52.46,1.35,13.3,	tex_mur_parking$,3)
mur_est2_parking=				CreerMur(0.18,2.7,10.6,		52.46,1.35,26.7,		tex_mur_parking$,3)

RotateEntity plafond_parking,0,0,90
RotateEntity plafond_cellules_sud,0,0,90

;PARKING PORTES
.parking_portes
porte_parking=					CreerMur(0.1,4,13,			-0.5,5.3,20)
porte_sortie_parking=			CreerMur(0.1,4,13,			-10,5.45,20)
porte_cellule3=					CreerMur(6,3,0.1,			9.5+6.5,1.5,8.1)
porte_cellule4=					CreerMur(6,3,0.1,			9.5+6.5*2,1.5,8.1)
porte_cellule5=					CreerMur(6,3,0.1,			9.5+6.5*3,1.5,8.1)
porte_cellule6=					CreerMur(6,3,0.1,			9.5+6.5*4,1.5,8.1)
porte_cellule7=					CreerMur(6,3,0.1,			9.5+6.5*5,1.5,8.1)
porte_cellule8=					CreerMur(6,3,0.1,			9.5+6.5*6,1.5,8.1)

porte_cellule9=					CreerMur(6,3,0.1,			3,1.5,31.9)
porte_cellule10=				CreerMur(6,3,0.1,			9.5,1.5,31.9)
porte_cellule11=				CreerMur(6,3,0.1,			9.5+6.5,1.5,31.9)
porte_cellule12=				CreerMur(6,3,0.1,			9.5+6.5*2,1.5,31.9)
porte_cellule13=				CreerMur(6,3,0.1,			9.5+6.5*3,1.5,31.9)
porte_cellule14=				CreerMur(6,3,0.1,			9.5+6.5*4,1.5,31.9)
porte_cellule15=				CreerMur(6,3,0.1,			9.5+6.5*5,1.5,31.9)
porte_cellule16=				CreerMur(6,3,0.1,			9.5+6.5*6,1.5,31.9)

EntityTexture porte_cellule3,t_porte_garage
EntityTexture porte_cellule4,t_porte_garage
EntityTexture porte_cellule5,t_porte_garage
EntityTexture porte_cellule6,t_porte_garage
EntityTexture porte_cellule7,t_porte_garage
EntityTexture porte_cellule8,t_porte_garage
EntityTexture porte_cellule9,t_porte_garage
EntityTexture porte_cellule10,t_porte_garage
EntityTexture porte_cellule11,t_porte_garage
EntityTexture porte_cellule12,t_porte_garage
EntityTexture porte_cellule13,t_porte_garage
EntityTexture porte_cellule14,t_porte_garage
EntityTexture porte_cellule15,t_porte_garage
EntityTexture porte_cellule16,t_porte_garage
EntityTexture porte_parking,t_porte_parking
EntityTexture porte_sortie_parking,t_porte_parking




;Décoration
.Decoration
toile1_sol_parking=				CreerMur(0.5,0.8,0.05,			32.35,0.025,31.8,		tex_debrit$)
RotateEntity toile1_sol_parking, 85,45,0

arbre_parking=LoadMesh("maps\Objet\a3dtree.3ds")
ScaleEntity arbre_parking,0.010,0.010,0.010
PositionEntity arbre_parking,27.25,0,20.25

lierre1_parking=				CollerImage(0,90,0,			-0.4,2,12,		img_lierre1$)
ScaleSprite lierre1_parking,0.5,2



;Ascenseur
.ascenseur
mur_ascenseur_sud=			CreerMur(0.1,3,3,			54.95,1.5,20,			tex_velourRouge$,3)
mur_ascenseur_est=			CreerMur(3,3,0.1,			54.05,1.5,21.4,			tex_velourRouge$,3)
mur_ascenseur_ouest=		CreerMur(3,3,0.1,			54.05,1.5,18.6,			tex_velourRouge$,3)
plafond_ascenseur=			CreerMur(3,3,0.1,			54.05,3,20,				tex_velourRouge$,3)
sol_ascenseur=				CreerMur(3,3,0.1,			54.05,0,20,				tex_parquet$,3)

RotateEntity plafond_ascenseur,90,0,0 
RotateEntity sol_ascenseur,90,0,0 

message_ascenseur=			CollerImage(0,180,0,		53.7,1.5,18.71,		img_message$,1,4)
ScaleSprite message_ascenseur,0.21,0.28


;Porte ascenceur
.SteamPorteAscenceur
centre_porteAscenceur=				CreerTuyau(0.3,0.30,			52.46,1.4,20.45,		tex_cuivre1$,1,0, 			img_CuivreCylinderExt$)
tranche1_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.40,1.4+0.9*Sin(290),20.45+0.9*Cos(290),			tex_cuivre1$)
tranche2_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.42,1.4+0.9*Sin(243),20.45+0.9*Cos(243),			tex_cuivre1$)
tranche3_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.44,1.4+0.9*Sin(196),20.45+0.9*Cos(196),			tex_cuivre1$)
tranche4_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.46,1.4+0.9*Sin(149),20.45+0.9*Cos(149),			tex_cuivre1$)
tranche5_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.48,1.4+0.9*Sin(196),20.45+0.9*Cos(196),			tex_cuivre1$)
tranche6_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.50,1.4+0.9*Sin(243),20.45+0.9*Cos(243),			tex_cuivre1$)
tranche7_porteAscenceur=				CreerSphere(0.02,1.8,1.8,		52.52,1.4+0.9*Sin(290),20.45+0.9*Cos(290),			tex_cuivre1$)

Contour_porteAscenceur=						CollerImage(0,-90,0,			52.38,1.3,20,		img_ContourSteamPorte$,1.4)
Contour2_porteAscenceur=					CollerImage(0,-90,0,			52.54,1.3,20,		img_ContourSteamPorte$,1.4)
Contour3_porteAscenceur=					CollerImage(0,90,0,				52.38,1.3,20,		img_ContourSteamPorte2$,1.4)
Contour4_porteAscenceur=					CollerImage(0,90,0,				52.54,1.3,20,		img_ContourSteamPorte2$,1.4)

RotateEntity centre_porteAscenceur,0,0,90

CreerAnimationRotRoll(tranche1_porteAscenceur,47, 1, 0, 1.4, 20.45, 290)
CreerAnimationRotRoll(tranche2_porteAscenceur,47, 2, 0, 1.4, 20.45, 243)
CreerAnimationRotRoll(tranche3_porteAscenceur,47, 3, 0, 1.4, 20.45, 196)
CreerAnimationRotRoll(tranche4_porteAscenceur,47, 4, 0, 1.4, 20.45, 149)
CreerAnimationRotRoll(tranche5_porteAscenceur,47, 3, 0, 1.4, 20.45, 196)
CreerAnimationRotRoll(tranche6_porteAscenceur,47, 2, 0, 1.4, 20.45, 243)
CreerAnimationRotRoll(tranche7_porteAscenceur,47, 1, 0, 1.4, 20.45, 290)

;META_PORTE!!!!!!!! Global car animé par AnimateMetaPorte()
.metaporte
porte_cellule2=				CreerMur(6,3,0.9,			9.5,1.5,7.7)
porte2_cellule2=				CreerMur(6,3,0.9,			9.5,1.5,0.5)
porte3_cellule2=				CreerMur(0.9,3,8,			7,1.5,4.1)
porte4_cellule2=				CreerMur(0.9,3,8,			12,1.5,4.1)
porte_plafond_metaPorte=		CreerMur(6,8,0.9,			9.5,2.4,4.1)

EntityTexture porte_cellule2, t_metaporte
EntityTexture porte2_cellule2, t_metaporte
EntityTexture porte3_cellule2, t_metaporte
EntityTexture porte4_cellule2, t_metaporte
EntityTexture porte_plafond_metaPorte, t_metaporte
RotateEntity porte_plafond_metaPorte,90,0,0 

Verrin1_metaPorte=			CreerTuyau(0.75,0.75,		12,0.25,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin2_metaPorte=			CreerTuyau(0.40,1.6,		12,0.55,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin3_metaPorte=			CreerTuyau(0.25,1,			12,2.2,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin4_metaPorte=			CreerTuyau(0.1,1.2,			12,2.3,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin5_metaPorte=			CreerTuyau(0.15,0.40,		11.95,2.95,8.27,	tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
Verrin6_metaPorte=			CreerTuyau(0.75,0.75,		7,0.25,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin7_metaPorte=			CreerTuyau(0.40,1.6,		7,0.55,8.33,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin8_metaPorte=			CreerTuyau(0.25,1,			7,2.2,8.33,			tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin9_metaPorte=			CreerTuyau(0.1,1.2,			7,2.3,8.33,			tex_metal_plate$,1,0, 		img_MetalCylinderExt$)		
Verrin10_metaPorte=			CreerTuyau(0.15,0.40,		7.05,2.95,8.25,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
Verrou1_metaPorte=			CreerTuyau(2,0.8,			9.5,2.10,8.25,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
Verrou2_metaPorte=			CreerTuyau(1.5,1.2,			9.5,1.4,8.3,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
Tuyau1_metaPorte=			CreerTuyau(0.3,5,			9.5,1,8.3,			tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
Tuyau2_metaPorte=			CreerTuyau(0.3,5,			9.5,1.85,8.3,		tex_metal_plate$,1,0, 		img_MetalCylinderExt$)
tete_metaPorte=				CreerSphere(1,1,1,			9.5,2.95,8.25,		tex_metal_plate$)
base_chapeau_metaPorte=		CreerTuyau(1.2,0.04,		9.5,3.05,8.3,		tex_cuir_noir$,1,0, 		img_VerrinExt$)
forme_chapeau_metaPorte=		CreerTuyau(1,0.7,			9.42,3.43,8.3,		tex_cuir_noir$,1,0, 		img_trou_noir$)

pivot_nez_metaPorte=CreatePivot()
nez_metaPorte=CreateCone(8,True,pivot_nez_metaPorte)
main1_metaPorte=LoadMesh("maps\Objet\main.X")
main2_metaPorte=LoadMesh("maps\Objet\main.X")

PositionEntity pivot_nez_metaPorte,9.5,2.42,8.27
ScaleEntity nez_metaPorte,0.025,0.1,0.025
PositionEntity nez_metaPorte,0,0.05,0
RotateEntity pivot_nez_metaPorte,0,0,-50

PositionEntity main1_metaPorte,6.99,2.8,8.32
RotateEntity main1_metaPorte,0,0,180
ScaleEntity main1_metaPorte,0.3,0.3,0.3
EntityFX main1_metaPorte,1
PositionEntity main2_metaPorte,12.01,2.8,8.31
RotateEntity main2_metaPorte,0,180,180
ScaleEntity main2_metaPorte,0.3,0.3,0.3
EntityFX main2_metaPorte,1

RotateEntity Verrin5_metaPorte,90,10,0
RotateEntity Verrin10_metaPorte,90,20,0
RotateEntity Verrou1_metaPorte,0,-110,0
RotateEntity Tuyau1_metaPorte,5,0,90
RotateEntity Tuyau2_metaPorte,90,0,90
RotateEntity tete_metaPorte,90,180,0
RotateEntity base_chapeau_metaPorte,0,0,10
RotateEntity forme_chapeau_metaPorte,0,0,10

CreerAnimationMovRot(porte_cellule2,50,				0,0.05,0)
CreerAnimationMovRot(porte2_cellule2,50,			0,0.05,0)
CreerAnimationMovRot(porte3_cellule2,50,			0,0.05,0)
CreerAnimationMovRot(porte4_cellule2,50,			0,0.05,0)
CreerAnimationMovRot(porte_plafond_metaPorte,50,	0,0.05,0)
CreerAnimationMovRot(Verrin2_metaPorte,50,			0,0.0175,0)
CreerAnimationMovRot(Verrin3_metaPorte,50,			0,0.03,0)
CreerAnimationMovRot(Verrin4_metaPorte,50,			0,0.05,0)
CreerAnimationMovRot(Verrin5_metaPorte,50,			0,0.05,0)
CreerAnimationMovRot(Verrin7_metaPorte,50,			0,0.0175,0)
CreerAnimationMovRot(Verrin8_metaPorte,50,			0,0.03,0)
CreerAnimationMovRot(Verrin9_metaPorte,50,			0,0.05,0)
CreerAnimationMovRot(Verrin10_metaPorte,50,			0,0.05,0)
CreerAnimationMovRot(Verrou1_metaPorte,50,			0,0.02,0)
CreerAnimationMovRot(Verrou2_metaPorte,50,			0,0.0175,0)
CreerAnimationMovRot(Tuyau1_metaPorte,50,			0,0.0175,0)
CreerAnimationMovRot(Tuyau2_metaPorte,50,			0,0.03,0)
CreerAnimationMovRot(tete_metaPorte,50,				0,0.02,0)
CreerAnimationMovRot(base_chapeau_metaPorte,50,		0,0.02,0)
CreerAnimationMovRot(forme_chapeau_metaPorte,50,	0,0.02,0)
CreerAnimationMovRot(pivot_nez_metaPorte,50,		0,0.03,0,	0,0,-5.2)
CreerAnimationMovRot(main1_metaPorte,50,			0,0.05,0)
CreerAnimationMovRot(main2_metaPorte,50,			0,0.05,0)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Rajout Nico;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
new_porte(101) ; porte de la cellule d'origine
For p.porte=Each porte
	Select p\num
		Case 101
			p\pivot=CreatePivot()
			p\manikin=CreateCube(p\pivot)
			ScaleEntity p\manikin,3,1.5,0.05
			tex=LoadTexture("textures/loran/porte_garage.jpg")
			If tex<>0 Then EntityTexture p\manikin,tex
			EntityType p\manikin,type_mur
			p\pos_init#[1]=3
			p\pos_init#[2]=1.5
			p\pos_init#[3]=8.1
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=3
			p\pos_final#[2]=2.75
			p\pos_final#[3]=7.2
			p\pos_final#[4]=-81
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=0
			p\speed#[2]=0.36
			p\speed#[3]=-0.26
			p\speed#[4]=-23
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
	End Select
Next

For gr.groupe=Each groupe
	If gr\num=107 ; roue
		gr\script[2]=roue_cellule1
	EndIf
Next


StopChannel ch_bmg
If bgm<>0 FreeSound bgm
bgm=LoadSound("musiques\WindyCave.mp3")
If bgm<>0
	LoopSound bgm
	ch_bgm=PlaySound(bgm)
	ChannelVolume ch_bgm,0.2
EndIf


Select entrance
	Default
		pos_entrance#(1)=3
		pos_entrance#(2)=0
		pos_entrance#(3)=4
End Select