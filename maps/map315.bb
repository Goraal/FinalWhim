;.CHARGEMENT_TEXTURE
t_porte_garage=LoadTexture("textures/loran/porte_garage.jpg") 
t_metaporte=LoadTexture("textures/loran/MetalDoors3.jpg") 
t_porte_parking=LoadTexture("textures/loran/Porte_Parking_1.jpg")
ScaleTexture t_porte_parking,1.0/8.0,1.0/2.0
ScaleTexture t_metaporte,0.5,1 ;fait semblant qu'il y ai 2 partie => fait beaucoup moins faux

tex_porte_garage$     	="textures/loran/porte_garage.jpg" 
tex_mur_garage$       	="textures/loran/mur_brique.jpg" 
tex_plafond_garage$   	="textures/loran/blanc.jpg" 
tex_mur_parking$      	="textures/loran/mur_agglo.jpg"
tex_plafond_parking$  	="textures/loran/beton06.jpg"
tex_verrin$				="textures/loran/gris.jpg"
tex_porte_parking$    	="textures/loran/Porte_Parking_1.jpg"
tex_metal_plate$		="textures/loran/METAL.bmp"
tex_debrit$						="textures/loran/debris2.jpg"
tex_sol_parking$				="textures/loran/gravel.jpg"
tex_cuir_noir$					="textures/loran/cuir-noir.jpg"
tex_cuivre1$					="textures/loran/Cuivre_neuf.bmp"
tex_velourRouge$				="textures/loran/velour_petit.jpg"
tex_parquet$					="textures/loran/parquet.jpg"
img_attention_engrenage$		="sprites/loran/attention2.bmp"
img_trou_noir$					="sprites/loran/rond_noir_fond_noir.bmp"
img_visageMetaPorte$			="sprites/loran/VisageMetaPorte.bmp"
img_VerrinExt$					="sprites/loran/rond_gris_fond_noir.bmp"
img_VerrouSteamille$			="sprites/loran/rond_steamille_fond_noir.bmp"
img_MetalCylinderExt$			="sprites/loran/rond_metal3_fond_noir.bmp"
img_CuivreCylinderExt$			="sprites/loran/rond_cuivre2_fond_noir.bmp"
img_steamPorte$					="sprites/loran/steamDoor.bmp"
img_ContourSteamPorte$			="sprites/loran/porte ascenceur.bmp"
img_ContourSteamPorte2$			="sprites/loran/porte ascenceur2.bmp"
img_caniveau$					="sprites/loran/grille_canniveau1.jpg"
img_caniveau2$					="sprites/loran/grille_canniveau2.jpg"
img_lierre1$					="sprites/loran/liere2.png"
img_message$					="sprites/loran/Message Parchemin.jpg"
img_deco1$						="sprites/loran/deco1.bmp"
img_nombre0$					="sprites/loran/0.bmp"
img_nombre1$					="sprites/loran/1.bmp"
img_nombre2$					="sprites/loran/2.bmp"
img_nombre3$					="sprites/loran/3.bmp"
img_nombre4$					="sprites/loran/4.bmp"
img_nombre5$					="sprites/loran/5.bmp"
img_nombre6$					="sprites/loran/6.bmp"
img_nombre7$					="sprites/loran/7.bmp"
img_nombre8$					="sprites/loran/8.bmp"
img_nombre9$					="sprites/loran/9.bmp"
img_lvlMoins1$					="sprites/loran/LVL-1.bmp"
img_fondSteamille$				="sprites/loran/fond_steamille_fond_noir.bmp"

;.CREATION_MONDE
;CreerMur(dx#=1,h#=1,dz#=1,			x#=0,y#=0,z#=0,		tex$="",scaltext#=1,flag=0,		collisiontype=type_mur,		rot#=0,)
;CollerImage(rx#=0,ry#=0,rz#=0,		x#=0,y#=0,z#=0,		path$="",scaleImg#=1,flag=4,	viewMode=2)
;CreerTuyau(r#=1,h#=1,				x#=0,y#=0,z#=0,		tex$="",scaltext#=1,flag=0, 	imgExt$="", scaleImg#=0.98, 	collisiontype=type_block)		

; SOL mieux:
;.sol
sol=					CreerMur(0.01,100,40,		26,0,20,				tex_sol_parking$,1,1,		type_sol(1,1))
RotateEntity sol,0,0,90
EntityPickMode sol,2

sol_caniveau1=			CollerImage(90,0,0,			20,0.05,20,		img_caniveau$)
sol_caniveau2=			CollerImage(90,0,0,			2,0.05,20,		img_caniveau$)
sol_caniveau3=			CollerImage(90,0,0,			4,0.05,20,		img_caniveau$)
sol_caniveau4=			CollerImage(90,0,0,			6,0.05,20,		img_caniveau$)
sol_caniveau5=			CollerImage(90,0,0,			8,0.05,20,		img_caniveau$)
sol_caniveau6=			CollerImage(90,0,0,			10,0.05,20,		img_caniveau$)
sol_caniveau7=			CollerImage(90,0,0,			12,0.05,20,		img_caniveau$)
sol_caniveau8=			CollerImage(90,0,0,			14,0.05,20,		img_caniveau$)
sol_caniveau9=			CollerImage(90,0,0,			16,0.05,20,		img_caniveau$)
sol_caniveau10=			CollerImage(90,0,0,			18,0.05,20,		img_caniveau$)
sol_caniveau11=			CollerImage(90,0,0,			22,0.05,20,		img_caniveau$)
sol_caniveau12=			CollerImage(90,0,0,			24,0.05,20,		img_caniveau$)
sol_caniveau13=			CollerImage(90,0,0,			26,0.05,20,		img_caniveau$)
sol_caniveau14=			CollerImage(90,0,0,			27.25,0.05,20,		img_caniveau2$)
sol_caniveau15=			CollerImage(90,0,0,			28.5,0.05,20,		img_caniveau$)
sol_caniveau16=			CollerImage(90,0,0,			30.5,0.05,20,		img_caniveau$)
sol_caniveau17=			CollerImage(90,0,0,			32.5,0.05,20,		img_caniveau$)
sol_caniveau18=			CollerImage(90,0,0,			34.5,0.05,20,		img_caniveau$)
sol_caniveau19=			CollerImage(90,0,0,			36.5,0.05,20,		img_caniveau$)
sol_caniveau20=			CollerImage(90,0,0,			38.5,0.05,20,		img_caniveau$)
sol_caniveau21=			CollerImage(90,0,0,			40.5,0.05,20,		img_caniveau$)
sol_caniveau22=			CollerImage(90,0,0,			42.5,0.05,20,		img_caniveau$)
sol_caniveau23=			CollerImage(90,0,0,			44.5,0.05,20,		img_caniveau$)
sol_caniveau24=			CollerImage(90,0,0,			46.5,0.05,20,		img_caniveau$)
sol_caniveau25=			CollerImage(90,0,0,			48.5,0.05,20,		img_caniveau$)
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
ScaleSprite sol_caniveau15, 1,0.1
ScaleSprite sol_caniveau16, 1,0.1
ScaleSprite sol_caniveau17, 1,0.1
ScaleSprite sol_caniveau18, 1,0.1
ScaleSprite sol_caniveau19, 1,0.1
ScaleSprite sol_caniveau20, 1,0.1
ScaleSprite sol_caniveau21, 1,0.1
ScaleSprite sol_caniveau22, 1,0.1
ScaleSprite sol_caniveau23, 1,0.1
ScaleSprite sol_caniveau24, 1,0.1
ScaleSprite sol_caniveau25, 1,0.1


; SORTIE
;.sortie
sol_sortie_parking=				CreerMur(0.01,10.5,13,		-5.4,1.7,20,		tex_sol_parking$,1,1,		type_sol(1,1))
plafond_sortie_parking=			CreerMur(0.01,10.5,13,		-5.4,5.7,20,		tex_plafond_parking$,6,1,		type_sol(1,1))
mur_sud_sortie_parking=			CreerMur(9.5,8,0.01,		-5.25,4,13.5,		tex_mur_parking$)
mur_nord_sortie_parking=		CreerMur(9.5,8,0.01,		-5.25,4,26.5,		tex_mur_parking$)

;EntityColor sol_sortie_parking,60,60,60
RotateEntity sol_sortie_parking, 0,0,70
RotateEntity plafond_sortie_parking, 0,0,70

;LUMIERE
;.lumiere
AmbientLight 50,50,50

lumiere_chandelier1=	CreerLumiereChandelier(250,150,25,		13,4.5,20,			1)
lumiere_chandelier2=	CreerLumiereChandelier(250,150,25,		33,4.5,20,			1)




;CELLULES
;.cellules

mur_ouest_cellule8=			CreerMur(0.1,3,8,		0,1.5,4,		tex_mur_garage$,3)
mur_est_cellule8=			CreerMur(0.1,3,8,		6,1.5,4,		tex_mur_garage$,3)
plafond_cellule8=				CreerMur(0.1,6,8,			3,3,4,				tex_plafond_garage$,3)
RotateEntity plafond_cellule8,0,0,90
FAKE_mur_roue1=				CreerMur(0.75,3,1,		4.12,1.5,0.5)
FAKE_mur_roue2=				CreerMur(0.75,3,1,		1.87,1.5,0.5)
EntityAlpha FAKE_mur_roue1,0
EntityAlpha FAKE_mur_roue2,0

numero_cellule8=			CollerImage(0,180,0,	3,4,8.11,	img_nombre8$)
panneau_cellule8=			CollerImage(0,180,0,	0.5,1.8,0.06,	img_attention_engrenage$,0.25)
message_cellule8=			CollerImage(0,90,0,		0.1,1.5,5,		img_message$,1,4)
ScaleSprite message_cellule8,0.21,0.28

roue_cellule8=LoadMesh("objets\roue\roue.X")
PositionEntity roue_cellule8,3,1.22,0.2
RotateEntity roue_cellule8,90,0,0
ScaleEntity roue_cellule8,2,2,2
EntityType roue_cellule8,type_none
EntityFX roue_cellule8,1

;cellule13 (débris)
mur_ouest_cellule13=			CreerMur(0.1,5.5,8,		6.5*4,2.75,36,		tex_mur_garage$,3)
mur_est_cellule13=				CreerMur(0.1,5.5,8,		6+6.5*4,2.75,36,	tex_mur_garage$,3)
plafond_cellule13=				CreerMur(0.1,6,8,		3+6.5*4,3,36,		tex_plafond_garage$,3)
porte_cellule13=				CreerMur(6,3,0.1,		9.5+6.5*3,0.05,31.9)
mur_haut_cellule13=				CreerMur(6,2.5,0.1,		3+6.5*4,4.25,32,			tex_mur_parking$,3)
RotateEntity plafond_cellule13,0,0,90
EntityType porte_cellule13,type_sol(1,1)
RotateEntity porte_cellule13,90,40,0
EntityTexture porte_cellule13,t_porte_garage



roue_cellule13=LoadMesh("objets\roue\roue.X")
PositionEntity roue_cellule13,28,1.62,38
RotateEntity roue_cellule13,20,0,150
ScaleEntity roue_cellule13,2,2,2
EntityType roue_cellule13,type_none
EntityFX roue_cellule13,1
FAKE_mur_roue3=				CreerMur(3.3,3.3,3.3,		28,1.62,38)
EntityAlpha FAKE_mur_roue3,0

;CELLULE STALE
mur_ouest_cellule_stale=		CreerMur(0.1,5.5,8,		6.5,2.75,4,			tex_mur_garage$,3)
mur_est_cellule_stale=			CreerMur(0.1,5.5,8,		12.5,2.75,4,		tex_mur_garage$,3)


;PARKING MURS
;.parking_murs
mur_cellule8b_cellule8=			CreerMur(0.5,3,0.1,			-0.25,1.5,8,		tex_mur_parking$,3)
mur_cellule8_cellule9=			CreerMur(0.5,3,0.1,			6.25,1.5,8,			tex_mur_parking$,3)
FAKE_mur_cellule9_escalier=		CreerMur(40,3,0.1,			32.5,1.5,8,			tex_mur_parking$,3)
mur_haut_cellule8_cellule9=		CreerMur(7,2.5,0.1,			3,4.25,8,			tex_mur_parking$,3)
mur_haut_cellule7_escalier=		CreerMur(40,2.5,0.1,		32.5,4.25,8,		tex_mur_parking$,3)
mur_sud_cellules_sud=			CreerMur(53,5.5,0.1,		26,2.75,0,			tex_mur_garage$,3)
plafond_parking=				CreerMur(0.1,53,48,			26,5.5,20,			tex_plafond_parking$,10)
mur_ouest1_parking=				CreerMur(0.1,5.5,5.5,		-0.5,2.75,10.75,	tex_mur_parking$,3)
mur_ouest2_parking=				CreerMur(0.1,5.5,5.5,		-0.5,2.75,29.25,	tex_mur_parking$,3)
FAKE_mur_nord1_parking=			CreerMur(26.5,5.5,0.1,		12.75,2.75,32,		tex_mur_parking$,3)
FAKE_mur_nord2_parking=			CreerMur(21,5.5,0.1,		42.5,2.75,32,		tex_mur_parking$,3)

mur_nord_cellules_nord=			CreerMur(53,5.5,0.1,		26,2.75,40,			tex_mur_garage$,3)

mur_est_haut_parking=			CreerMur(0.18,2.8,24,		52.46,4.1,20,		tex_mur_parking$,3)
mur_est1_parking=				CreerMur(0.18,2.7,10.6,		52.46,1.35,13.3,	tex_mur_parking$,3)
mur_est2_parking=				CreerMur(0.18,2.7,10.6,		52.46,1.35,26.7,		tex_mur_parking$,3)

RotateEntity plafond_parking,0,0,90


;PARKING PORTES
;.parking_portes
porte_parking=					CreerMur(0.1,4,13,			-0.5,5.3,20)
porte_sortie_parking=			CreerMur(0.1,4,13,			-10,5.45,20)
porte_cellule6=					CreerMur(6,3,0.1,			9.5+6.5,1.5,8.1)
porte_cellule5=					CreerMur(6,3,0.1,			9.5+6.5*2,1.5,8.1)
porte_cellule4=					CreerMur(6,3,0.1,			9.5+6.5*3,1.5,8.1)
porte_cellule3=					CreerMur(6,3,0.1,			9.5+6.5*4,1.5,8.1)
porte_cellule2=					CreerMur(6,3,0.1,			9.5+6.5*5,1.5,8.1)
porte_cellule1=					CreerMur(6,3,0.1,			9.5+6.5*6,1.5,8.1)

porte_cellule9=					CreerMur(6,3,0.1,			3,1.5,31.9)
porte_cellule10=				CreerMur(6,3,0.1,			9.5,1.5,31.9)
porte_cellule11=				CreerMur(6,3,0.1,			9.5+6.5,1.5,31.9)
porte_cellule12=				CreerMur(6,3,0.1,			9.5+6.5*2,1.5,31.9)
porte_cellule14=				CreerMur(6,3,0.1,			9.5+6.5*4,1.5,31.9)
porte_cellule15=				CreerMur(6,3,0.1,			9.5+6.5*5,1.5,31.9)
porte_cellule16=				CreerMur(6,3,0.1,			9.5+6.5*6,1.5,31.9)

numero_cellule1=			CollerImage(0,180,0,	9.5+6.5*6,4,8.11,	img_nombre1$)
numero_cellule2=			CollerImage(0,180,0,	9.5+6.5*5,4,8.11,	img_nombre2$)
numero_cellule3=			CollerImage(0,180,0,	9.5+6.5*4,4,8.11,	img_nombre3$)
numero_cellule4=			CollerImage(0,180,0,	9.5+6.5*3,4,8.11,	img_nombre4$)
numero_cellule5=			CollerImage(0,180,0,	9.5+6.5*2,4,8.11,	img_nombre5$)
numero_cellule6=			CollerImage(0,180,0,	9.5+6.5  ,4,8.11,	img_nombre6$)
numero_cellule9=			CollerImage(0,0,0,		3        ,4,31.89,	img_nombre9$)
numero_cellule10_d=			CollerImage(0,0,0,		9        ,4,31.89,	img_nombre1$)
numero_cellule10_u=			CollerImage(0,0,0,		10 	     ,4,31.89,	img_nombre0$)
numero_cellule11_d=			CollerImage(0,0,0,		9+6.5    ,4,31.89,	img_nombre1$)
numero_cellule11_u=			CollerImage(0,0,0,		10+6.5 	 ,4,31.89,	img_nombre1$)
numero_cellule12_d=			CollerImage(0,0,0,		9+6.5*2  ,4,31.89,	img_nombre1$)
numero_cellule12_u=			CollerImage(0,0,0,		10+6.5*2 ,4,31.89,	img_nombre2$)
numero_cellule13_d=			CollerImage(0,0,0,		9+6.5*3  ,4,31.89,	img_nombre1$)
numero_cellule13_u=			CollerImage(0,0,0,		10+6.5*3 ,4,31.89,	img_nombre3$)
numero_cellule14_d=			CollerImage(0,0,0,		9+6.5*4  ,4,31.89,	img_nombre1$)
numero_cellule14_u=			CollerImage(0,0,0,		10+6.5*4 ,4,31.89,	img_nombre4$)
numero_cellule15_d=			CollerImage(0,0,0,		9+6.5*5  ,4,31.89,	img_nombre1$)
numero_cellule15_u=			CollerImage(0,0,0,		10+6.5*5 ,4,31.89,	img_nombre5$)
numero_cellule16_d=			CollerImage(0,0,0,		9+6.5*6  ,4,31.89,	img_nombre1$)
numero_cellule16_u=			CollerImage(0,0,0,		10+6.5*6 ,4,31.89,	img_nombre6$)
EntityTexture porte_cellule1,t_porte_garage
EntityTexture porte_cellule2,t_porte_garage
EntityTexture porte_cellule3,t_porte_garage
EntityTexture porte_cellule4,t_porte_garage
EntityTexture porte_cellule5,t_porte_garage
EntityTexture porte_cellule6,t_porte_garage
EntityTexture porte_cellule9,t_porte_garage
EntityTexture porte_cellule10,t_porte_garage
EntityTexture porte_cellule11,t_porte_garage
EntityTexture porte_cellule12,t_porte_garage

EntityTexture porte_cellule14,t_porte_garage
EntityTexture porte_cellule15,t_porte_garage
EntityTexture porte_cellule16,t_porte_garage
EntityTexture porte_parking,t_porte_parking
EntityTexture porte_sortie_parking,t_porte_parking




;Décoration
;.Decoration
debrit1_sol_parking=				CreerCone(3,1,3,			26,0.53,38.4,		tex_debrit$,0.25)
;CreerMur()
;RotateEntity toile1_sol_parking, 85,45,0

;arbre_parking=LoadMesh("objets\arbre1\a3dtree.3ds")
;ScaleEntity arbre_parking,0.010,0.010,0.010
;PositionEntity arbre_parking,27.25,0,20.25

lierre1_parking=				CollerImage(0,90,0,			-0.4,2,12,		img_lierre1$)
ScaleSprite lierre1_parking,0.5,2



;Ascenseur
;.ascenseur
mur_ascenseur_sud=			CreerMur(0.1,3,3,			54.95,1.5,20,			tex_velourRouge$,3)
mur_ascenseur_est=			CreerMur(3,3,0.1,			54.05,1.5,21.4,			tex_velourRouge$,3)
mur_ascenseur_ouest=		CreerMur(3,3,0.1,			54.05,1.5,18.6,			tex_velourRouge$,3)
plafond_ascenseur=			CreerMur(3,3,0.1,			54.05,3,20,				tex_velourRouge$,3)
sol_ascenseur=				CreerMur(3,3,0.1,			54.05,0,20,				tex_parquet$,3)
EntityType sol_ascenseur,type_sol(1,1)

RotateEntity plafond_ascenseur,90,0,0 
RotateEntity sol_ascenseur,90,0,0 

message_ascenseur=			CollerImage(0,180,0,		53.7,1.5,18.71,		img_message$,1,4)
ScaleSprite message_ascenseur,0.21,0.28

deco1Sud_ascenseur=			CollerImage(180,90,0,		54.89,1.5,18.72,			img_deco1$,1.5,4)
deco2Sud_ascenseur=			CollerImage(0,270,0,		54.89,1.5,21.28,			img_deco1$,1.5,4)

deco1Est_ascenseur=			CollerImage(0,0,0,			52.62,1.52,21.34,			img_deco1$,1.5,4)
deco2Est_ascenseur=			CollerImage(180,180,0,		54.8,1.52,21.34,			img_deco1$,1.5,4)

deco1Ouest_ascenseur=		CollerImage(0,180,0,		54.8,1.48,18.66,			img_deco1$,1.5,4)
deco2Ouest_ascenseur=		CollerImage(180,0,0,		52.62,1.48,18.66,			img_deco1$,1.5,4)
decoLVLMoins1=				CollerImage(0,90,0,			52.56,2.81,20.88,				img_lvlMoins1$,0.2)


;Porte ascenceur
;.SteamPorteAscenceur
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

;META_PORTE!!!!!!!! Global car annimé par AnimateMetaPorte()
;.metaporte
porte_cellule7=				CreerMur(6,3,0.9,			9.5,1.5,7.7)
porte2_cellule7=				CreerMur(6,3,0.9,			9.5,1.5,0.5)
porte3_cellule7=				CreerMur(0.9,3,8,			7,1.5,4.1)
porte4_cellule7=				CreerMur(0.9,3,8,			12,1.5,4.1)
porte_plafond_metaPorte=		CreerMur(6,8,0.9,			9.5,2.4,4.1)


EntityTexture porte_cellule7, t_metaporte
EntityTexture porte2_cellule7, t_metaporte
EntityTexture porte3_cellule7, t_metaporte
EntityTexture porte4_cellule7, t_metaporte
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
forme_chapeau_metaPorte=	CreerTuyau(1,0.7,			9.42,3.43,8.3,		tex_cuir_noir$,1,0, 		img_trou_noir$)
boitierSteamille=			CreerMur(0.25,0.25,0.25,		8.9,2.20,9,			img_fondSteamille$,0.25)			



pivot_nez_metaPorte=CreatePivot()
nez_metaPorte=CreateCone(8,True,pivot_nez_metaPorte)
main1_metaPorte=LoadMesh("objets\main\main.X")
main2_metaPorte=LoadMesh("objets\main\main.X")

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

CreerAnimationMovRot(porte_cellule7,50,				0,0.05,0)
CreerAnimationMovRot(porte2_cellule7,50,			0,0.05,0)
CreerAnimationMovRot(porte3_cellule7,50,			0,0.05,0)
CreerAnimationMovRot(porte4_cellule7,50,			0,0.05,0)
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
CreerAnimationMovRot(boitierSteamille,50,			0,0.02,0)
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

sol(3,3,40,0.5,30)
lit(20,0,10)

For gr.groupe=Each groupe
	If gr\num=107 ; roue
		gr\script[2]=roue_cellule8
	EndIf
Next

play_music(04,1)

If loaded_map=315
	pos_entrance#(1)=20
	pos_entrance#(2)=0
	pos_entrance#(3)=15
Endif