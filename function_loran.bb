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

;A améliorer avec l'angle# et une valeur de retour
Function CreerSteamPorte(tranche1,tranche2, tranche3, tranche4, tranche5, tranche6, tranche7, centreX#, centreZ#, angle#)
	pivot=CreatePivot()
	centre_porte=			CreerTuyau(0.3,0.30,			centreX#     ,1.4             ,centreZ#          ,			tex_cuivre1$,1,0, 			img_CuivreCylinderExt$, 0.98, type_mur, pivot)
	tranche1=				CreerSphere(0.02,1.8,1.8,		centreX#-0.06,1.4+0.9*Sin(290),centreZ#+0.9*Cos(290),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche2=				CreerSphere(0.02,1.8,1.8,		centreX#-0.04,1.4+0.9*Sin(243),centreZ#+0.9*Cos(243),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche3=				CreerSphere(0.02,1.8,1.8,		centreX#-0.02,1.4+0.9*Sin(196),centreZ#+0.9*Cos(196),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche4=				CreerSphere(0.02,1.8,1.8,		centreX#     ,1.4+0.9*Sin(149),centreZ#+0.9*Cos(149),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche5=				CreerSphere(0.02,1.8,1.8,		centreX#+0.02,1.4+0.9*Sin(196),centreZ#+0.9*Cos(196),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche6=				CreerSphere(0.02,1.8,1.8,		centreX#+0.04,1.4+0.9*Sin(243),centreZ#+0.9*Cos(243),			tex_cuivre1$,1,0, type_mur, pivot)
	tranche7=				CreerSphere(0.02,1.8,1.8,		centreX#+0.06,1.4+0.9*Sin(290),centreZ#+0.9*Cos(290),			tex_cuivre1$,1,0, type_mur, pivot)
	Contour_porte =			CollerImage(0,angle#-90,0,			centreX#-0.08,1.3,centreZ#-0.45,		img_ContourSteamPorte$,1.4)
	Contour2_porte=			CollerImage(0,angle#-90,0,			centreX#+0.08,1.3,centreZ#-0.45,		img_ContourSteamPorte$,1.4)
	Contour3_porte=			CollerImage(0,angle#+90,0,				centreX#-0.08,1.3,centreZ#-0.45,		img_ContourSteamPorte2$,1.4)
	Contour4_porte=			CollerImage(0,angle#+90,0,				centreX#+0.08,1.3,centreZ#-0.45,		img_ContourSteamPorte2$,1.4)
	
	RotateEntity centre_porte,0,angle#,90
	RotateEntity tranche1,0,angle#,0
	RotateEntity tranche2,0,angle#,0
	RotateEntity tranche3,0,angle#,0
	RotateEntity tranche4,0,angle#,0
	RotateEntity tranche5,0,angle#,0
	RotateEntity tranche6,0,angle#,0
	RotateEntity tranche7,0,angle#,0

	CreerAnimationRotRoll(tranche1,47, 1, 0, 1.4, centreZ#, 290)
	CreerAnimationRotRoll(tranche2,47, 2, 0, 1.4, centreZ#, 243)
	CreerAnimationRotRoll(tranche3,47, 3, 0, 1.4, centreZ#, 196)
	CreerAnimationRotRoll(tranche4,47, 4, 0, 1.4, centreZ#, 149)
	CreerAnimationRotRoll(tranche5,47, 3, 0, 1.4, centreZ#, 196)
	CreerAnimationRotRoll(tranche6,47, 2, 0, 1.4, centreZ#, 243)
	CreerAnimationRotRoll(tranche7,47, 1, 0, 1.4, centreZ#, 290)
	
	Return(pivot)
End Function

Function AnimateSteamPorte(mode,vitesse#,PorteNumber=1)
	If PorteNumber=1
		Animate tranche1_porteAscenceur,mode,vitesse#
		Animate tranche2_porteAscenceur,mode,vitesse#
		Animate tranche3_porteAscenceur,mode,vitesse#
		Animate tranche4_porteAscenceur,mode,vitesse#
		Animate tranche5_porteAscenceur,mode,vitesse#
		Animate tranche6_porteAscenceur,mode,vitesse#
		Animate tranche7_porteAscenceur,mode,vitesse#
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

Function CreerTuyau(r#=1,h#=1,x#=0,y#=0,z#=0,tex$="",scaltext#=1,flag=0, imgExt$="", scaleImg#=0.98, collisiontype=type_block,plein=True,parent=0)	
	tuyau=CreateCylinder(16,plein,parent)
	ScaleEntity tuyau,r#/2,h#/2,r#/2
	PositionEntity tuyau,x#,y#,z#
	EntityType tuyau,collisiontype
	
	If tex$="" Then tex$="textures/loran/blanc.jpg":
	texture=LoadTexture(tex$,flag):	
	If texture=0 Then RuntimeError "Impossible d'ouvrir la Texture: "+tex$
	ScaleTexture texture,scaltext#/r#,scaltext#/h#
	EntityTexture tuyau,texture
	FreeTexture texture
	
	If plein=True
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
	EndIf	
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
	
	;chandelier=LoadMesh("objets\candle_holder\candle_holder.3ds")
	;ScaleEntity chandelier,0.02,0.02,0.02
	;PositionEntity chandelier,x#,y#,z#
	;EntityColor chandelier,250,250,250
	
	xa#=x#
	ya#=y#+1
	za#=z#
	
	chandelier=LoadMesh("objets\chandelier\chandelier.x")
	;ScaleEntity chandelier,0.5,0.5,0.5
	PositionEntity chandelier,xa#,ya#-1.6,za#
	RotateEntity chandelier,0,22.5,0
	
	chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
	SpriteViewMode chaine1,24
	EntityFX chaine1,16
	EntityAlpha chaine1,1
	ScaleSprite chaine1,0.9,0.9
	PositionEntity chaine1,xa#+0.40,ya#-0.8,za#
	RotateEntity chaine1,0,0,26,6
	chaine2=CopyEntity(chaine1)
	TurnEntity chaine2,180,90,0
	
	chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
	SpriteViewMode chaine1,24
	EntityFX chaine1,16
	EntityAlpha chaine1,1
	ScaleSprite chaine1,0.9,0.9
	PositionEntity chaine1,xa#-0.4,ya#-0.8,za#
	RotateEntity chaine1,0,0,-26,6
	chaine2=CopyEntity(chaine1)
	TurnEntity chaine2,180,90,0
	
	chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
	SpriteViewMode chaine1,24
	EntityFX chaine1,16
	EntityAlpha chaine1,1
	ScaleSprite chaine1,0.9,0.9
	PositionEntity chaine1,xa#,ya#-0.8,za#+.4
	RotateEntity chaine1,-26.6,0,0
	chaine2=CopyEntity(chaine1)
	TurnEntity chaine2,180,90,0
	
	chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
	SpriteViewMode chaine1,24
	EntityFX chaine1,16
	EntityAlpha chaine1,1
	ScaleSprite chaine1,0.9,0.9
	PositionEntity chaine1,xa#,ya#-0.8,za#-0.4
	RotateEntity chaine1,26.6,0,0
	chaine2=CopyEntity(chaine1)
	TurnEntity chaine2,180,90,0
	
	For t=1 To 8
		flamme=LoadSprite("objets\chandelier\shot1.bmp")
		ScaleSprite flamme,0.08,0.08
		alpha#=22.5+45*t
		PositionEntity flamme,xa#+Cos(alpha#)*0.83,ya#-1.32,za#+Sin(alpha#)*0.83
	Next

		;chandel_lumiere4_garage=		Creerchandel_lumiereRond(250,150,25,		13.61,0.71,19.97,			1)
		;2:  13.52/0.71/20.36      3:13.2/0.71/20.6
	down#=0.3 ; 0.69
	chandel_lumiere1=CreateLight(2)
	LightColor chandel_lumiere1,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere1,x#+0.7,y#-down#,z#
	LightRange chandel_lumiere1,range#
	
	chandel_lumiere2=CreateLight(2)
	LightColor chandel_lumiere2,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere2,x#-0.7,y#-down#,z#
	LightRange chandel_lumiere2,range#
	
	chandel_lumiere3=CreateLight(2)
	LightColor chandel_lumiere3,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere3,x#,y#-down#,z#+0.7
	LightRange chandel_lumiere3,range#
	
	chandel_lumiere4=CreateLight(2)
	LightColor chandel_lumiere4,min(rouge,255),min(vert,255),min(bleu,255)
	PositionEntity chandel_lumiere4,x#,y#-0.69,z#-0.7
	LightRange chandel_lumiere4,range#
	
	Return chandelier
End Function