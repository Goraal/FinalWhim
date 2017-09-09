AppTitle "Essayeur de Carte pour Final Whim by Luris"
Graphics3D 800,600,32,2
SetBuffer BackBuffer()
SeedRnd (MilliSecs())


Dim keys(50,4) ; scancode,hited,released,oldhited
keys(1,1)=-1  ; bgs
keys(2,1)=-2  ; bds

keys(3,1)=16  ; A
keys(4,1)=17  ; Z
keys(5,1)=18  ; E
keys(6,1)=30  ; Q
keys(7,1)=31  ; S
keys(8,1)=32  ; D
;keys(15,1)=74 ; -
;keys(16,1)=78 ; +
keys(11,1)=56 ; alt
keys(12,1)=57 ; space3
keys(14,1)=28 ; return
keys(47,1)=200 ; up
keys(48,1)=203 ; left
keys(49,1)=205 ; right
keys(50,1)=208 ; down

Dim type_sol(5,2) ; 1:entity_type, 2:son_pas

Const type_none=0
Const type_joueur=1
Const type_perso=2
type_sol(1,1)=3
type_sol(2,1)=4
type_sol(3,1)=5
type_sol(4,1)=6
type_sol(5,1)=7
Const type_mur=8
Const type_block=9 ; mur qui laisse passer la caméra.
Const type_cam=10
Const type_rideau=11 ; "mur" qui laisse passer le joueur mais pas la caméra 

Global pl_grp_pivot
Global pl_grp_manikin
Global cam
Global cam_pivot
Const nb_frame=50
Global frame_timer=CreateTimer(nb_frame)
Global pas#=0.1
Include "maps/map02.bb"
;Include "menu.bb"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;ICI IN GAME PROG
Global entiteTest=mur_table_sdp


Global Debug_Valeur#
;AnimateMetaPorte(1,0.15)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;chargez la map ici;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Je m'en fous je la charge plus haut mouahahah
;position de départ du joueur
posx#=0+32
posy#=0+3 ; rajouter 46cm pour éviter de passer à travers le sol (radius du pivot+1cm de sécurité)
posz#=0+28

;sol=CreateCube()
;ScaleEntity sol,100,0.01,100
;EntityColor sol,0,150,0
;EntityType sol,type_sol(1,1)
;EntityPickMode sol,2
;PositionEntity sol,0,0,0

If entiteTest=0 Then RuntimeError "In test programing object does not exist"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;; creation du joueur

pl_grp_pivot=CreatePivot()
EntityRadius pl_grp_pivot,0.45
EntityType pl_grp_pivot,type_joueur
pl_grp_manikin=LoadMD2("objets\heros\tris.md2",pl_grp_pivot)
tex=LoadTexture("objets\heros\armour.pcx")
EntityTexture pl_grp_manikin,tex
FreeTexture tex
ScaleEntity pl_grp_manikin,0.030,0.030,0.030
PositionEntity pl_grp_manikin,-.05,0.3,0.05

;create cam
cam_parent=CreatePivot()
cam_pivot=CreatePivot(cam_parent)
cam=CreateCamera(cam_pivot)
PositionEntity cam,0,0,-8
zoom_cam#=8
RotateEntity cam_pivot,55,0,0
EntityType cam,type_cam
EntityRadius cam,0.4
CameraRange cam,0.3,300

PositionEntity pl_grp_pivot,posx#,posy#,posz#
RotateEntity pl_grp_pivot,0,180,0

UpdateWorld
RenderWorld
Flip
	
;collision
For t=1 To 5
	Collisions type_cam,type_sol(t,1),2,1
	Collisions type_joueur,type_sol(t,1),2,3
Next
Collisions type_cam,type_mur,2,1
Collisions type_cam,type_rideau,2,1
Collisions type_joueur,type_perso,2,2
Collisions Type_joueur,type_mur,2,2
Collisions Type_joueur,type_block,2,2


;;boucle
action=0
While action=0
	Cls
	WaitTimer frame_timer
	If KeyDown(01) Then action=1
	
	Gosub entree
	Gosub map
	
	UpdateWorld
	RenderWorld
		
	Gosub ihm

	Flip
Wend

End


.entree
;;detecte quelles touches sont frappées pour pouvoir calculer les combos, ou simplement utiliser des équivalents 
;;de KeyHit() sans les inconvenients
	sourisx#=MouseXSpeed()
	sourisy#=MouseYSpeed()
	sourisz#=MouseZSpeed()

	For t=1 To 50
		If keys(t,2)>0 Then keys(t,2)=keys(t,2)-1
		If keys(t,4)>0 Then keys(t,4)=keys(t,4)-1
		If KeyHit(keys(t,1))
			keys(t,4)=keys(t,2)
			keys(t,2)=50
		EndIf
		If keys(t,3)>0 Then keys(t,3)=keys(t,3)-1
		If KeyDown(keys(t,1)) And keys(t,2)<50 Then keys(t,3)=50	
		If keys(t,1)=-1 
			If MouseHit(1) Then keys(t,2)=50
			If MouseDown(1) And keys(t,2)<50 Then keys(t,3)=50
		EndIf
		If keys(t,1)=-2 
			If MouseHit(2) Then keys(t,2)=50
			If MouseDown(2) And keys(t,2)<50 Then keys(t,3)=50
		EndIf
	Next
	If cam<>0
		cible_camera=CameraPick(cam,MouseX(),MouseY())
		ciblepick_x#=PickedX#()
		ciblepick_y#=PickedY#()
		ciblepick_z#=PickedZ#()
	EndIf
Return

.map ; se déplacer sur la carte
	If KeyDown(keys(47,1)); up
		vit_z=1
	ElseIf  KeyDown(keys(50,1));down
		vit_z=-1
	Else
		vit_z=0
	EndIf
	If KeyDown(keys(49,1));right
		vit_x=1
	ElseIf KeyDown(keys(48,1));left
		vit_x=-1
	Else
		vit_x=0
	EndIf
	
	If Abs(vit_z)+Abs(vit_x)>0 
		alpha#=pointeryaw(0,0,vit_x,vit_z)-90
		vitesse#=0.5
		animation=1
	ElseIf MouseDown(2) And KeyDown(keys(11,1))=0
		If cible_camera<>0
			alpha#=pointeryaw(EntityX#(pl_grp_pivot,1),EntityZ#(pl_grp_pivot,1),ciblepick_x#,ciblepick_z#)-90
			alpha#=alpha#-EntityYaw#(pl_grp_pivot)
			vitesse#=0.05
			animation=1
		Else
			vitesse#=0
			animation=1
		EndIf
	Else
		vitesse#=0
		If animation=1 Then animation=0
	EndIf
	MoveEntity pl_grp_pivot,vitesse#*Cos(alpha#),-0.05,vitesse#*Sin(alpha#)
	
	
	;fausse filiation caméra
	PositionEntity cam_parent,EntityX#(pl_grp_pivot,1),EntityY#(pl_grp_pivot,1),EntityZ#(pl_grp_pivot,1),1
	RotateEntity cam_parent,0,EntityYaw#(pl_grp_pivot,1),0,1
		
	
	;gestion caméra
	If KeyDown(keys(11,1))
		TurnEntity pl_grp_pivot,0,-sourisx#*0.2,0
		TurnEntity cam_pivot,sourisy#*0.2,0,0
		MoveMouse 400,300
		alpha#=alpha#+sourisx#*0.2
	EndIf
	
	zoom_cam#=zoom_cam#-sourisz#
	zoom_cam#=min(max(zoom_cam#,4),20)
	HideEntity cam
	PositionEntity cam,0,0,0
	ShowEntity cam
	PositionEntity cam,0,0,-zoom_cam#*0.5
	
	;update du player
	RotateEntity pl_grp_manikin,0,alpha#-90,0
	If animation<>old_animation
		Select animation
			Case 0 ;md2 idle
				AnimateMD2 pl_grp_manikin,1,0.05*50/Float(nb_frame),0,5
			Case 1 ;md2 walk
				AnimateMD2 pl_grp_manikin,1,0.1*50/Float(nb_frame),40,46
		End Select
	EndIf
	If mode_debug<>0
		;PERMET DE FAIRE DU IN GAME DESIGN
		If KeyHit(74)>0;-
			ModifierPas(False)
		EndIf
		If KeyHit(78)>0;+
			ModifierPas(True)
		EndIf
		If KeyHit(2)>0;79)>0;1
			InGamePosition(entiteTest,	 -pas#,0,0)
		EndIf
		If KeyHit(3)>0;80)>0;2
			InGamePosition(entiteTest,	 0,-pas#,0)
		EndIf
		If KeyHit(4)>0;81)>0;3
			InGamePosition(entiteTest,	 0,0,-pas#)
		EndIf
		If KeyHit(5)>0;75)>0;4
			InGamePosition(entiteTest,	 pas#,0,0)
		EndIf
		If KeyHit(6)>0;76)>0;5
			InGamePosition(entiteTest,	 0,pas#,0)
		EndIf
		If KeyHit(7)>0;77)>0;6
			InGamePosition(entiteTest,	 0,0,pas#)
		EndIf
		If KeyHit(8)>0;71)>0;7
			InGamePosition(entiteTest,	 0,0,0,		pas#,0,0)
		EndIf
		If KeyHit(9)>0;72)>0;8
			InGamePosition(entiteTest,	 0,0,0,		0,pas#,0)
		EndIf
		If KeyHit(10)>0;73)>0;9
			InGamePosition(entiteTest,	 0,0,0,		0,0,pas#)
		EndIf
		If KeyDown(57)>0;ESPACE
			;AnimateMetaPorte(3,0.15)
			;AnimateSteamPorte(3,0.15,1)
		EndIf
	EndIf
	old_animation=animation
;16  ; A
;17  ; Z
;18  ; E
;30  ; Q
;31  ; S
;32  ; D
;78 ; +
;79 ;1
;80 ;2
;81 ;3
;75 ;4 
;76 ;5
;77 ;6
;71 ;7
;72 ;8
;73 ;9
	;ANIMATION;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;ICI ça bouge

Return

.ihm
Text 5,5,"Posx : "+EntityX(pl_grp_pivot)
Text 5,20,"Posy : "+EntityY(pl_grp_pivot)
Text 5,35,"Posz : "+EntityZ(pl_grp_pivot)
Text 5,130,"PAS :"+pas#
Text 5,145,"Objet px: "+EntityX(entiteTest)+" py: "+EntityY(entiteTest)+" pz: "+EntityZ(entiteTest)
Text 5,160,"Objet rx: "+EntityPitch(entiteTest)+" ry: "+EntityYaw(entiteTest)+" rz: "+EntityRoll(entiteTest)
Text 5,175,"Debug_Valeur#: "+Debug_Valeur#


Return


Function pointeryaw#(xinter#,zinter#,xtarget#,ztarget#)
	a#=xtarget#-xinter#
	b#=ztarget#-zinter#
	c#=Sqr#(a*a+b*b)
	If c#=0 Then Return 0
	sina#=b#/c# 
	If sina#>=0
		alpha#=ACos(a#/c#)
	Else
		alpha#=-ACos(a#/c#)
	EndIf
	;;+90 parce que je calcule dans un ROND ("est") mais que le jeu utilise degrés%"sud" sens radial
	Return alpha#+90
End Function

Function max(a,b)
	If a>b
		Return a
	Else
		Return b
	EndIf
End Function

Function min(a,b)
	If a<b
		Return a
	Else
		Return b
	EndIf
End Function

Function maxf#(a#,b#)
	If a#>b#
		Return a#
	Else
		Return b#
	EndIf
End Function

Function minf#(a#,b#)
	If a#<b#
		Return a#
	Else
		Return b#
	EndIf
End Function