;load font
big_font=LoadFont("Constantia",30*screeny#)
little_font=LoadFont("Constantia",20*screeny#)
small_font=LoadFont("Constantia",15*screeny#)
middle_font=LoadFont("Constantia",25*screeny#)

SetFont middle_font
numero_astuce=Rand(1,NB_ASTUCES)


;;;;A SUPPRIMER;;;;;
;new_log("Test un deux !")
;new_log("C'est un trou de verdure où coule une rivière, accrochant follement aux herbes des haillons d'argent")
;new_log("Test deux trois !")
;new_log("WAAAAAAGH !")


	
	If nouvelle_partie=1
	   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Lancer une nouvelle partie;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		
		;If xml_Avancement<>0 Then xmlNodeDelete(xml_Avancement)
		For Node.xmlNode=Each xmlNode
			Delete Node
		Next
		
		xml_Avancement=xmlLoad("Script/New game.xml")
		event_action=0
		quitter_jeu=0
		numero_astuce=1 ; mettre les astuces "utiles" en premier lors d'une nouvelle partie
		
.selection_avatar ; + cinématique à intégrer

		;a enlever
		If mode_debug=1
			quitter_jeu=1
			selection_avatar(1,4,2)
			selection_avatar(3,7,3)
			selection_avatar(2,1,4)
			rechoisir_avatar()
		endif
		
		While quitter_jeu=0
			start_loop()
			Gosub entree

			script(111)	
			
			Color 250,250,250
			SetFont little_font
			Text 5,600-30*screeny#,Left(fps#(),5)
			Flip
			
			compensation_lag()	
		Wend
		
		quitter_jeu=0
		
		player_avatar(1)=1
		player_avatar(3)=3
		player_avatar(2)=2
		player_leader=1
		
		For pl_grp.groupe=Each groupe
			If pl_grp\num=-1
				pl_grp\name$="Groupe des héros"
				pl_grp\map=1
				player_map=pl_grp\map
			EndIf
		Next
		
	ElseIf nouvelle_partie=2
		;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Bac à sable;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		;choix du PJ (à modifier ?)
		player_avatar(1)=1
		selection_avatar(1,4,3)
		player_avatar(3)=3
		selection_avatar(3,7,3)
		player_avatar(2)=2
		selection_avatar(2,1,3)
		player_leader=1
		
		For pl_grp.groupe=Each groupe
			If pl_grp\num=-1
				pl_grp\name$="Groupe des héros"
				pl_grp\map=315
				player_map=pl_grp\map
			EndIf
		Next
	Else
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Charger une partie;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		load_server()
	EndIf

AppTitle Str("Final Whim "+num_version$+" : Solo")

map_entrance=0

;charger les images des PJs
For t=1 To 3
	fighters_utilises(t)=0
Next
ai=0
For t=1 To 3
	For av.avatar=Each avatar
		If av\num=player_avatar(t)
			good=0
			For u=1 To 3
				If fighters_utilises(u)=av\cat Then good=1
			Next
			If good=0
				ai=ai+1
				fighters_utilises(ai)=av\cat
			EndIf
		EndIf
	Next
Next
For t=1 To ai
	load_combattant(fighters_utilises(t))
Next

.load_map
clean_world()
mode_de_jeu=1
sortie=0
level=player_map
numero_astuce=numero_astuce+1
If numero_astuce>NB_ASTUCES Then numero_astuce=1
mult_mess$(1)="Création du niveau"
mult_mess$(2)="Building level"
aff_loading(1,mult_mess$(Int(options#(7))),numero_astuce)
load_map(level,map_entrance)
loaded_map=level
current_map=loaded_map

;If nouvelle_partie=1
	For butin.butin=Each butin
		If butin\map=current_map
			load_butin(butin\num)
			PositionEntity butin\pivot,butin\position#[1],butin\position#[2],butin\position#[3],1
			RotateEntity butin\pivot,0,butin\position#[4],0,1
		EndIf
	Next
;EndIf


mult_mess$(1)="Création du joueur"
mult_mess$(2)="Building player"
aff_loading(2,mult_mess$(Int(options#(7))),numero_astuce)
;create groupe\model et les autres groupes à côtés
For pl_grp.groupe=Each groupe
	If pl_grp\num=-1
		pl_grp\pivot=CreatePivot()
		load_groupe(-1,1)
		pl_grp_pivot=pl_grp\pivot
		EntityType pl_grp\pivot,type_joueur
		pl_grp_manikin=pl_grp\manikin[1]
		If nouvelle_partie>0
			PositionEntity pl_grp\pivot,pos_entrance#(1),pos_entrance#(2)+0.5,pos_entrance#(3)
		Else
			PositionEntity pl_grp\pivot,pl_grp\position#[1],pl_grp\position#[2]+0.45,pl_grp\position#[3]
			RotateEntity pl_grp\pivot,0,pl_grp\position#[4],0
		EndIf
		ResetEntity pl_grp\pivot
	ElseIf pl_grp\map=current_map
		pl_grp\pivot=CreatePivot()
		load_groupe(pl_grp\num,1)
		PositionEntity pl_grp\pivot,pl_grp\position#[1],pl_grp\position#[2],pl_grp\position#[3],1
		RotateEntity pl_grp\pivot,0,pl_grp\position#[4],0,1
		ResetEntity pl_grp\pivot
	EndIf
Next	

nouvelle_partie=1

;create cam
cam_parent=CreatePivot()
cam_pivot=CreatePivot(cam_parent)
cam=CreateCamera(cam_pivot)
PositionEntity cam,0,0,-8
CameraViewport cam,0,0,800,500
zoom_cam#=8
angle_cam#=35
RotateEntity cam_pivot,angle_cam#,0,0
a_int=MouseXSpeed() ; reinit pour éviter d'avoir une cam de travers au démarrage
a_int=MouseYSpeed()
EntityType cam,type_cam
EntityRadius cam,0.3
CameraRange cam,0.3,300
CreateListener(cam,0.3)
;activator
activator=LoadSprite("sprites\gfx\activator_01.bmp")

mult_mess$(1)="Chargement sons"
mult_mess$(2)="Loading sounds"
aff_loading(3,mult_mess$(Int(options#(7))),numero_astuce)

;load sound


;collision
For t=1 To 5
	Collisions type_cam,type_sol(t,1),2,1
	Collisions type_joueur,type_sol(t,1),2,3
	Collisions type_perso,type_sol(t,1),2,1
Next
Collisions type_cam,type_mur,2,1
Collisions type_joueur,type_perso,1,2
Collisions Type_joueur,type_mur,2,2
Collisions Type_joueur,type_block,2,2
Collisions type_perso,type_mur,2,2

;Main Loop
.main_loop

quitter_jeu=0
alpha#=0
reinit_keyboard()
nb_tour=0
coeff_frame#=1
frame_a=MilliSecs()
reste_frame#=0 
delta_frame=20
cas_cam=1
If mode_de_jeu=5 Then mode_de_jeu=1


;a enlever
;If level=1 Then AnimateSteamPorte(3,0.2*0.02*2500/Float(nb_frame),1)


While quitter_jeu=0
	start_loop()
	
	Gosub entree
			
	Select mode_de_jeu
		Case 1 ; carte
			Gosub map
		Case 2 ; combat
			Gosub fight
		Case 3 ; menu
			Gosub menu_player
		Case 4 ; marchand
			Gosub marchand
		Case 5 ; chgt map
			Goto load_map
		Default
			RuntimeError "Mode_de_jeu incorrect"
	End Select

	call_serveur()
	animation()
	If loaded_map<>current_map Then Goto load_map

	If mode_debug=1
		Color 250,250,250
		SetFont little_font
		Text 5,screenheight-30*screeny#,Left(fps#(),5)
	EndIf
	
	Flip
	compensation_lag()	
Wend

Goto retour_menu

.entree
;;detecte quelles touches sont frappées pour pouvoir calculer les combos, ou simplement utiliser des équivalents 
;;de KeyHit() sans les inconvenients
	sourisx#=MouseXSpeed()
	sourisy#=MouseYSpeed()
	sourisz#=MouseZSpeed()

	lire_clavier()
	
;	If cam<>0
;		cible_camera=CameraPick(cam,MouseX(),MouseY())
;		ciblepick_x#=PickedX#()
;		ciblepick_y#=PickedY#()
;		ciblepick_z#=PickedZ#()
;	EndIf
Return

.map ; se déplacer sur la carte
	drawmouse=1
	old_message_curseur$=message_curseur$
	message_curseur$=""
	message_action$=""
	
	player_in_control=True
	
	old_pl_grp_x#=EntityX#(pl_grp_pivot,1)
	old_pl_grp_y#=EntityY#(pl_grp_pivot,1)
	old_pl_grp_z#=EntityZ#(pl_grp_pivot,1)
	
	If event_action=0 ; faire bouger le gars
		If grav#>min_grav#
			If chat_mode=0
				If KeyDown(keys(4,1)) Or KeyDown(keys(47,1)); Z ou up
					vit_z=1
				ElseIf KeyDown(keys(7,1)) Or KeyDown(keys(50,1)); S ou down
					vit_z=-1
				Else
					vit_z=0
				EndIf
				If KeyDown(keys(8,1)) Or KeyDown(keys(49,1)); D ou right
					vit_x=1
				ElseIf KeyDown(keys(6,1)) Or KeyDown(keys(48,1)); Q ou left
					vit_x=-1
				Else
					vit_x=0
				EndIf
			Else
				vit_x=0
				vit_z=0
			EndIf
		EndIf
		
		vitesse_max#=0.10
		If Abs(vit_z)+Abs(vit_x)>0 
			alpha#=pointeryaw(0,0,vit_x,vit_z)-90
			vitesse#=vitesse_max#*coeff_frame#*(50/Float(nb_frame))
			var_pl_grp(num_player,"animation",True,2)
		Else
			vitesse#=0
			If var_pl_grp(num_player,"animation")=2 Then var_pl_grp(num_player,"animation",True,1)
		EndIf
		
		; gestion de la gravité et des "falaises glissantes"
		grav#=grav#-minf#(9.81*delta_frame*0.001,Abs(chute_grav#)*0.5)
		If grav#<chute_grav#
			;chute
			var_pl_grp(num_player,"animation",True,4)
		EndIf		
		glissant=0
		vitesse2#=0
		nb_collision=CountCollisions(pl_grp_pivot)
		;chercher les collisions trop penchées
		repoussex#=0
		repoussez#=0
		touche_un_sol=0
		If nb_collision>0
			For c=1 to nb_collision
				;checker si c'est bien un sol et pas un mur (le mur n'est pas une falaise)
				collision_type=GetEntityType(CollisionEntity(pl_grp_pivot,c))
				If collision_type>2 and collision_type<8
					vecty#=CollisionNY#(pl_grp_pivot,c)
					If vecty#<0.7 ; dans une pente (d'inclinaison Arcsin(0.7) soit environ 45°)
						repoussex#=repoussex#+CollisionNX#(pl_grp_pivot,c)
						repoussez#=repoussez#+CollisionNZ#(pl_grp_pivot,c)
					Else
						touche_un_sol=1
					EndIf
				EndIf
			Next
			If touche_un_sol=0 And Abs(repoussex#)+Abs(repoussez#)>0 ; touche une pente trop inclinée sans toucher le sol
				;new_log(repoussex#+" "+repoussez#)
				vitesse#=maxf#(vitesse#,0.5*vitesse_max#*coeff_frame#*(50/Float(nb_frame)))
				deltax#=vitesse#*Cos(alpha#+EntityYaw#(pl_grp_pivot,1))
				deltaz#=vitesse#*Sin(alpha#+EntityYaw#(pl_grp_pivot,1))
				ps#=(repoussex#*deltax#+repoussez#*deltaz#)/sqr(repoussex#*repoussex#+repoussez#*repoussez#) ; la division pour que repousse ait pour norme 1
				gammax#=deltax#-ps#*repoussex#
				gammaz#=deltaz#-ps#*repoussez#
				vitesse2#=Sqr(gammax#*gammax#+gammaz#*gammaz#)
				alpha2#=pointeryaw(0,0,gammax#,gammaz#)-90
				vitesse#=0
				
			ElseIf touche_un_sol=1
				If grav#<chute_grav#
					;reception de chute
					;Playsound2(sons_menu(15))
					var_pl_grp(num_player,"animation",True,5)
					new_smoke_source(0,EntityX#(pl_grp_pivot,1),EntityY#(pl_grp_pivot,1)-0.5,EntityZ#(pl_grp_pivot,1),60,500,1,0.5,1,-90,0,0,0,200,200,100,100,1,1)
				EndIf
				;old_grav#=grav#
				grav#=0
			EndIf
		EndIf
		MoveEntity pl_grp_pivot,vitesse#*Cos(alpha#),0,vitesse#*Sin(alpha#)
		TranslateEntity pl_grp_pivot,vitesse2#*Cos(alpha2#),grav#*delta_frame*0.001,vitesse2#*Sin(alpha2#)
	EndIf
	
	If keys(43,2)=50 Then reinit_ennemis()
	
	If entiteTest<>0 And mode_debug
		If keys(85,2)=50;-
			ModifierPas(False)
		EndIf
		If keys(86,2)=50;+
			ModifierPas(True)
		EndIf
		If keys(74,2)=50;1
			InGamePosition(entiteTest,	-pas#,0,0)
		EndIf
		If keys(75,2)=50;2
			InGamePosition(entiteTest,	0,-pas#,0)
		EndIf
		If keys(76,2)=50;3
			InGamePosition(entiteTest,	0,0,-pas#)
		EndIf
		If keys(77,2)=50;4
			InGamePosition(entiteTest,	pas#,0,0)
		EndIf
		If keys(78,2)=50;5
			InGamePosition(entiteTest,	0,pas#,0)
		EndIf
		If keys(79,2)=50;6
			InGamePosition(entiteTest,	0,0,pas#)
		EndIf
		If keys(80,2)=50;7
			InGamePosition(entiteTest,	0,0,0,	pas#,0,0)
		EndIf
		If keys(81,2)=50;8
			InGamePosition(entiteTest,	0,0,0,	0,pas#,0)
		EndIf
		If keys(82,2)=50;9
			InGamePosition(entiteTest,	0,0,0,	0,0,pas#)
		EndIf
	EndIf

	;fausse filiation caméra
	PositionEntity cam_parent,old_pl_grp_x#,old_pl_grp_y#+1,old_pl_grp_z#,1
	RotateEntity cam_parent,0,EntityYaw#(pl_grp_pivot,1),0,1
	;MoveEntity cam_parent,vitesse#*Cos(alpha#),0,vitesse#*Sin(alpha#)
	;TranslateEntity cam_parent,vitesse2#*Cos(alpha2#),0,vitesse2#*Sin(alpha2#)

	;gestion caméra
	If cas_cam=1 And g_bHUDactif=1; mode liée
		If grav#>min_grav#
			TurnEntity pl_grp_pivot,0,-sourisx#*0.2,0
		EndIf
		angle_cam#=angle_cam#+sourisy#*0.2
		angle_cam#=max(min(angle_cam#,85),-55)		
		RotateEntity cam_pivot,angle_cam#,0,0
		MoveMouse screenwidth*0.5,screenheight*0.5
		alpha#=alpha#+sourisx#*0.2
		drawmouse=0
	EndIf

	If cas_cam>0
		Select options#(4)
			Case 1 ; Liée par défaut, alt pour fixe
				If KeyDown(keys(11,1))
					cas_cam=2
				Else
					cas_cam=1
				EndIf
			
			Case 2 ; Alt pout le toggle
				If keys(11,2)=50
					cas_cam=3-cas_cam
				EndIf

			Case 3 ; Fixe par défaut, alt pour liée (mode d'origine)
				If KeyDown(keys(11,1))
					cas_cam=1
				Else
					cas_cam=2
				EndIf
		End Select
	EndIf
	cas_cam=Abs(cas_cam)

	zoom_cam#=zoom_cam#-sourisz#
	zoom_cam#=min(max(zoom_cam#,4),20)
	HideEntity cam
	PositionEntity cam,0,0,0.15
	ShowEntity cam
	PositionEntity cam,0,0,-zoom_cam#*0.5
	RotateEntity cam,0,0,0,0
	
	;ciel
	if sky<>0
		positionentity sky,entityx#(cam,1),entityy#(cam,1),entityz#(cam,1),1
	endif
	
	;update du player
	RotateEntity pl_grp_manikin,0,alpha#-90,0
	
	;updateworld
	update_smoke_source()
	update_smoke()
	update_porte()
	update_patrouilleur()
	;script(11)
	
	UpdateWorld
	RenderWorld

	g_bHUDactif=1
	
	;interaction avec les 'groupes'
	;DebugLog "interaction groupe"
	lock_action=0
	leader_script=0
	For gr.groupe=Each groupe
		If gr\num<>-1
			If gr\map=player_map
				For t=1 To 4
					var_script_str$(1)=gr\nom_action$[t]
					var_script_int(1)=gr\num
					var_script_int(2)=t
					Select gr\trigger[t]
						Case 1 ; permanent
							script(gr\script[t])
						Case 2 ; souris
							If cible_camera=gr\manikin[1] Then script(gr\script[t])
						Case 3 ; souris+clic
							If cible_camera=gr\manikin[1] And keys(1,2)=50 Then script(gr\script[t])
						Case 4 ; distance
							If gr\pivot>0
								If dist2d(pl_grp_pivot,gr\pivot)<gr\range_trigger[t]
									If Abs(gr\position#[2]-EntityY(pl_grp_pivot))<gr\range_trigger[t] Then script(gr\script[t])
								EndIf
							Else
								If (gr\position#[1]-EntityX(pl_grp_pivot))^2+(gr\position#[3]-EntityZ(pl_grp_pivot))^2<gr\range_trigger[t]^2
									If Abs(gr\position#[2]-EntityY(pl_grp_pivot))<gr\range_trigger[t] Then script(gr\script[t])
								EndIf
							EndIf
						Case 5 ; distance+A
							If gr\pivot>0
								If dist2d(pl_grp_pivot,gr\pivot)<gr\range_trigger[t] And lock_action=0 And (keys(14,2)=50 Or keys(12,2)=50)
									script(gr\script[t])
									lock_action=1
								EndIf
							EndIf
						Case 6 ; serveur
							script_serveur(gr\script[t])
						Default
							;do nothing
					End Select
				Next
			EndIf
		EndIf
		If gr\num=interaction_avec
			; DebugLog gr\name$
			if gr\map=player_map And gr\pivot>0
				If dist2d(pl_grp_pivot,gr\pivot)>gr\range_trigger[interaction_script]
					interaction_avec=0
					interaction_script=0
					event_action=0
				EndIf
			Else
				interaction_avec=0
				interaction_script=0
				event_action=0
			EndIf
		EndIf
	Next
	
	;agresseurs
	If Pas_de_Combat=0
		For agr.agresseur=Each agresseur
			If agr\actif=1 And agr\map=player_map And agressed=0
				If (EntityX#(pl_grp_pivot)-agr\position#[1])^2+(EntityZ#(pl_grp_pivot)-agr\position#[3])^2<agr\radius^2
					If Abs(EntityY#(pl_grp_pivot)-agr\position#[2])<1.5
						If in_polygone(4,EntityX#(pl_grp_pivot),EntityZ#(pl_grp_pivot),agr\polyx#[1],agr\polyz#[1],agr\polyx#[2],agr\polyz#[2],agr\polyx#[3],agr\polyz#[3],agr\polyx#[4],agr\polyz#[4])
							agressed=1
							For pat.patrouilleur=Each patrouilleur
								If pat\agresseur=agr\num
									pat\vivant=-1 ; soit il meurt, soit les PJ meurent et c'est réinit
								EndIf
							Next
							agr\actif=-1 ; pour différencier d'un ennemi désactivé par l'histoire
							attaque_pokemon(agr\groupe,2)
						EndIf
					EndIf
				EndIf
			EndIf
		Next
	EndIf
	Pas_de_Combat=0
	
	;butin
	For butin.butin=Each butin
		If butin\map=player_map And butin\prop=0
			If butin\pivot<>0
				If dist2d(pl_grp_pivot,butin\pivot)<1.5 And lock_action=0
					lock_action=1
					If butin\hidden=0
						mult_mess$(1)="prendre ce butin"
						mult_mess$(2)="loot"
						message_action$=mult_mess$(Int(options#(7)))
						aff_activator(butin\position#[1],butin\position#[2]+0.1,butin\position#[3],1,2)
					EndIf
					If keys(12,2)=50
						keys(12,2)=49
						butin\prop=1
						stack_butin(1)
						stack_butin(butin\num)
						Playsound2(sons_menu(7))
						;donner les objets de quêtes au leader
						;Les surplus sont laissés sur place et les doubles sont supprimés.
						For butin2.butin=Each butin
							If butin2\num=1
								good=0
								k=0
								While good=0
									k=k+1
									If k=99 Then good=1
									If butin\quest[k]<>0
										good2=0
										For t=1 To 100
											If butin2\quest[t]=butin\quest[k] Then good2=1
										Next
										For t=1 To 100
											If butin2\quest[t]=0 And good2=0 Then butin2\quest[t]=butin\quest[k]:good2=1
										Next
										If good2=0
											good=-1
										Else
											butin\quest[k]=0
										EndIf
									EndIf
								Wend
							EndIf
						Next
						;stack les consommables, caps et junk
						For butin2.butin=Each butin
							If butin2\num=1 
								autre_butin=1
								good=1
								player_caps=player_caps+butin\caps
								player_junk=player_junk+butin\junk
								butin\caps=0
								butin\junk=0
								For t=1 To 10
									butin2\consommable[t]=butin2\consommable[t]+butin\consommable[t]
									butin\consommable[t]=0
								Next
							EndIf
						Next
						;stack les loots (les surplus sont laissés sur place)
						good=0
						For butin2.butin=Each butin
							If butin2\num=1
								good2=0
								For t=1 To 250
									If butin2\loot[t]=0 And good2=0
										k=t
										good2=1
									EndIf
								Next
								If good2=1
									For t=1 To 250
										If butin\loot[t]<>0 And good2=1
											If k+t<251
												butin2\loot[k+t]=butin\loot[t]
												butin\loot[t]=0
											Else
												good2=0
											EndIf
										EndIf
									Next
									If good2=0
										mult_mess$(1)="Attention : votre inventaire est plein."
										mult_mess$(2)="Warning: Your inventory is full."
										new_log(mult_mess$(Int(options#(7))),250,0,0)
									EndIf
								Else
									mult_mess$(1)="Attention : votre inventaire est plein."
									mult_mess$(2)="Warning: Your inventory is full."
									new_log(mult_mess$(Int(options#(7))),250,0,0)
								EndIf
							EndIf
						Next
						;delete si vide
						vide=1
						For t=1 To 250
							If butin\loot[t]<>0 Then vide=0
						Next
						For t=1 To 50
							If butin\quest[t]<>0 Then vide=0
						Next
						;pas besoin de faire les consommables puisqu'ils ont déjà été pris par le leader
						If vide=1; or autre_butin=0
							delete_butin(butin\num)
							Delete butin
							;new_log("vide")
						Else
							butin\prop=0
						EndIf
						stack_butin(1,1)
					EndIf
				EndIf
			EndIf
		EndIf
	Next
	
	;maj des maps
	If mode_de_jeu<5
		map_script(player_map)
	EndIf

	
	If activator_actif=0 Then PositionEntity activator,0,-1000,0
		
	If g_bHUDactif=1 Then HUD()
	;g_bHUDactif=1
	
Return

.fight ; les combats FF-like
	fn_combat()
Return

.menu_player ; pour gerer l'inventaire, les soins, etc...
	aff_load_menu(0)
	;; Verifier la formation
	pl_groupe_num=-1
	For gr.groupe=Each groupe
		If gr\num=pl_groupe_num
			For k=1 To 8
				For i=k+1 To 9
					If gr\formation[k]=gr\formation[i]
						gr\formation[k]=0
					EndIf
				Next
			Next
			;verifier que tous ceux de la formation appartiennent bien au groupe
			For k=1 To 9
				av_exist=0
				For av.avatar=Each avatar
					If av\num=gr\formation[k]
						av_exist=1
						If av\groupe<>gr\num Then gr\formation[k]=0
					EndIf
				Next
				If av_exist=0 Then gr\formation[k]=0
			Next
			;verifier que tous ceux qui appartiennent au groupe sont dans la formation
			For av.avatar=Each avatar
				If av\groupe=gr\num
					good=0
					For k=1 To 9
						If gr\formation[k]=av\num Then good=1
					Next
					If good=0 ; mettre dans la première case vide
						For k=1 To 9
							If good=0 And gr\formation[k]=0 Then gr\formation[k]=av\num:good=1
						Next
					EndIf
				EndIf
			Next
		EndIf
	Next	
	
	;;load les items du menu
		; les onglets et le curseur
	If gfx_onglets(1,Int(options#(7)))=0
		Select Int(options#(7))
			Case 1
				gfx_onglets(1,Int(options#(7)))=LoadImage("sprites\menu\onglets.bmp")
			Case 2				
				gfx_onglets(1,Int(options#(7)))=LoadImage("sprites\menu\onglets_en.png")
		End Select
	EndIf
	If face_poubelle=0
		face_poubelle=LoadImage("sprites\menu\face_poubelle.bmp")
	EndIf
	If gfx_curseur_onglets=0
		gfx_curseur_onglets=LoadImage("sprites\menu\curseur_onglets.bmp")
	EndIf
	If gfx_cuir=0 Then gfx_cuir=LoadImage("textures\loran\fond-cuir-noir.jpg")
	If fond_book=0 
		fond_book=LoadImage("sprites\menu\fond_book.jpg")
	EndIf
	If gfx_signet=0 Then gfx_signet=LoadImage("sprites\menu\marque-page.bmp")
	If gfx_signet_sombre=0 Then gfx_signet_sombre=LoadImage("sprites\menu\marque-page_sombre.bmp")
	If non_icone=0 Then non_icone=LoadImage("sprites\objets\non_icone_1.jpg")
	If non_icone2=0 Then non_icone2=LoadImage("sprites\objets\non_icone_2.jpg")
	aff_load_menu(0.3)
	If gfx_xiao=0 
		gfx_xiao=LoadImage("sprites\objets\xiao.bmp")
	EndIf
;	If bouton_panel_1=0
;		bouton_panel_1=LoadAnimImage("sprites\objets\panel-1.bmp",100,50,0,2)
;		MidHandle bouton_panel_1
;		ScaleImage bouton_panel_1,0.8,0.8
;	EndIf
;	If bouton_panel_2=0
;		bouton_panel_2=LoadAnimImage("sprites\objets\panel-2.bmp",100,50,0,2)
;		MidHandle bouton_panel_2
;		ScaleImage bouton_panel_2,0.8,0.8
;	EndIf
	
	If fleche_1g=0
		fleche_1g=LoadImage("sprites\menu\fleche-1g.bmp")
		fleche_1d=LoadImage("sprites\menu\fleche-1d.bmp")
		fleche_10g=LoadImage("sprites\menu\fleche-10g.bmp")
		fleche_10d=LoadImage("sprites\menu\fleche-10d.bmp")
		fleche_allg=LoadImage("sprites\menu\fleche-allg.bmp")
		fleche_alld=LoadImage("sprites\menu\fleche-alld.bmp")
	EndIf
	
	If bouton_paire=0
		bouton_paire=LoadAnimImage("sprites\Menu\Pairedeflingue.png",40,40,0,2)
	EndIf
	
	msg_erreur$=""
	msg_erreur_timing=0

		; les combattants
	For t=1 To 18
		fighters_utilises(t)=0
	Next
	ai=0
	For av.avatar=Each avatar
		If av\groupe=pl_groupe_num
			good=0
			For t=1 To 18
				If fighters_utilises(t)=av\cat Then good=1
			Next
			If good=0
				ai=ai+1
				fighters_utilises(ai)=av\cat
			EndIf
		EndIf
	Next
	
	aff_load_menu(0.5)
	For t=1 To ai
		load_combattant(fighters_utilises(t))
		aff_load_menu(0.5+t/(Float(ai))*0.5)
	Next
		
	aff_load_menu(1)
	
	If onglet<1 Or onglet>7 Then onglet=1
	sortie_menu=0
	cible=1
	pos_curseur=pos_finale
	pos_finale=(onglet-1)*75
	combat_place=0
	combat_from=0
	inventaire_cible=0 ; (-[1;8] pour l'inventaire sur le gars, >0 pour le numéro dans le butin)
	degre_roulette#=0
	degre_roulette2#=0
	msg_radio$=""
	avatar_num=-1
	stack_butin(1,1)
	
	While sortie_menu=0
		start_loop("sans pause")
		lire_clavier()
		If mode_de_jeu=2 Then sortie_menu=1
		
		aide_contextuelle$(1)=""
		aide_contextuelle$(2)=""

		If MouseDown(02) or keys(90,2)=50 Then sortie_menu=1:keys(90,2)=min(49,keys(90,2))
		sourisz#=MouseZSpeed()
		
		;draw le fond
		DrawImage gfx_cuir,0,-5
		
		;draw les onglets
		pos_finale=(onglet-1)*75
		If pos_curseur<>pos_finale
			vitesse=max(1,min(0.1*Abs(pos_curseur-pos_finale),40))
			If pos_curseur>pos_finale
				pos_curseur=pos_curseur-vitesse
			Else
				pos_curseur=pos_curseur+vitesse			
			EndIf
		EndIf
		If gfx_onglets(1,Int(options#(7)))=0
			Select Int(options#(7))
				Case 1
					gfx_onglets(1,Int(options#(7)))=LoadImage("sprites\menu\onglets.bmp")
				Case 2				
					gfx_onglets(1,Int(options#(7)))=LoadImage("sprites\menu\onglets_en.png")
			End Select
		EndIf
		DrawImage gfx_onglets(1,Int(options#(7))),0,0
		DrawImage gfx_curseur_onglets,0,pos_curseur
		
		;selection des onglets
		If MouseX()<150
			For t=1 To 8
				If MouseY()<t*75 And MouseY()>(t-1)*75
					Select t
						Case 1
							aide_contextuelle$(1)="Voir les caractéristiques des personnages."
							aide_contextuelle$(2)="See the attributes and abilities of your team members."
						Case 2
							aide_contextuelle$(1)="Voir l'équipement des personnages."
							aide_contextuelle$(2)="Manage the equipment of your team members, and the inventory."
						Case 3
							aide_contextuelle$(1)="Gérer le placement des personnages en combat."
							aide_contextuelle$(2)="Manage in-fight positions of the team members."
						Case 4
							aide_contextuelle$(1)="Voir les objets de quêtes et les informations glanées au cours de la partie."
							aide_contextuelle$(2)="See the key items and information gleaned during the game"
						Case 5
							aide_contextuelle$(1)="Consulter le chat et les dernières informations."
							aide_contextuelle$(2)="Read the log of the last actions and conversations."
						Case 6
							aide_contextuelle$(1)="Changer les options et sauvegarder votre partie."
							aide_contextuelle$(2)="Change the settings, save, or quit the game."
						Case 7
							aide_contextuelle$(1)="Quelques astuces pour s'en sortir."
							aide_contextuelle$(2)="A few tips on how to get by."
						Case 8
							aide_contextuelle$(1)="Quitter le menu et revenir au jeu."
							aide_contextuelle$(2)="Quit the menu and return to the game."
					End Select
					If keys(1,2)=50; And t<>4
						If onglet<>t
							degre_roulette#=0
							degre_roulette2#=0
							If t<8
								Playsound2(sons_menu(5))
							Else
								Playsound2(sons_menu(6))
							EndIf
						EndIf
						onglet=t						
					EndIf
				EndIf
			Next
		EndIf
		
		;action des onglets
		For gr.groupe = Each groupe
			If gr\num=pl_groupe_num
				If cible<10
					good=0
					While good=0
						If cible<10
							If gr\formation[cible]=0
								cible=cible+1
							Else
								good=1
							EndIf
						Else
							good=1
						EndIf
					Wend
				EndIf
				
				If onglet<>2 
					If cible>9 Then cible=1
				Else
					If cible>10 Then cible=1
				EndIf
				If onglet<>3 Then combat_from=0:combat_place=0
					
				Select onglet
					Case 1 ; Caractéristique
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495
						
						; onglets têtes
							k=0
							For t=1 To 9
								If t=cible
									k=k+1
									If t<10
										DrawImage gfx_signet,(155+64*(k-1))+13,5
									Else
										DrawImage gfx_signet,(155+64*(9))+13,5
									EndIf
								ElseIf gr\formation[t]<>0 
									k=k+1
									DrawImage gfx_signet_sombre,(155+64*(k-1))+13,5
								EndIf
								If MouseY()<75 And MouseX()>(155+64*(k-1)) And MouseX()<(155+64*(k)) And keys(1,2)=50
									If gr\formation[t]<>0 And cible<>t Then Playsound2(sons_menu(6)):cible=t:inventaire_cible=0										
								EndIf
								If gr\formation[t]<>0
									For av.avatar=Each avatar
										If av\num=gr\formation[t]
											if av\num>3
												If fighters_tete(av\cat,1)<>0 Then DrawBlock fighters_tete(av\cat,1),(155+64*(k-1))+18,18
											Else
												DrawBlock PJ_small(av\num),(155+64*(k-1))+18,18
											Endif
										EndIf
									Next
								EndIf

							Next
							
						Color 5,5,5							
						For av.avatar = Each avatar
							If av\num=gr\formation[cible]
								;afficher photo
								If av\num>3
									If fighters_tete(av\cat,2)<>0 Then DrawBlock fighters_tete(av\cat,2),180,120
								Else
									DrawBlock PJ_big(av\num),180,120
								EndIf
								SetFont middle_font
								mult_mess$(1)="Nom : "
								mult_mess$(2)="Name: "
								Text 310,120,mult_mess$(Int(options#(7)))+av\name$[Int(options#(7))]+" ("+av\classe$[Int(options#(7))]+")"
								;description
								For t=1 To 7
									disc_ligne(t)=""
								Next
								mess$=av\description$[Int(options#(7))]
								t=0
								k=0
								amax=Len(mess$)	
								max_ligne=Int(62)
								For a_int=1 To amax
									t=t+1
									If Mid$(mess$,t,1)="#"
										k=k+1
										disc_ligne$(k)=Left$(mess$,t-1)
										mess$=Right$(mess$,Len(mess$)-t)
										t=0
									EndIf
									If t=max_ligne
										t=lastspace(Left$(mess$,max_ligne),max_ligne)
										k=k+1
										disc_ligne$(k)=Left$(mess$,t-1)
										mess$=Right$(mess$,Len(mess$)-t)
										t=0
									EndIf
								Next
								If mess$<>""
									k=k+1
									disc_ligne$(k)=mess$
								EndIf
								For t=1 To 5
									disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
									Text 310,(125+20*t),disc_ligne$(t)
								Next
								
								;carac (PV, INIT, STM)
								;SetFont middle_font
								
								;Récupération des bonus/malus des équipements
								calcul_bonus_equi(av\num)
								
								;SetFont big_font
								For t=1 To 4
									mess$=""
									car$=""
									Select t
										Case 1
											mult_mess$(1)="Points de Vie : Lorsque les points de vie arrivent à 0, le personnage est vaincu."
											mult_mess$(2)="Hit Points: If hit points drop to 0, the character is knocked out."
											mess$=mult_mess$(Int(options#(7)))
											mult_mess$(1)="PV"
											mult_mess$(2)="HP"
											car$=mult_mess$(Int(options#(7)))
										Case 2
											mult_mess$(1)="Initiative : Détermine l'ordre d'action des combattants."
											mult_mess$(2)="Initiative: Determine the rank in a battle's turn order."
											mess$=mult_mess$(Int(options#(7)))
											mult_mess$(1)="Init"
											mult_mess$(2)="Init"
											car$=mult_mess$(Int(options#(7)))
										Case 3
											mult_mess$(1)="Armure : Le nombre dégâts absorbés (coup normal / coup crtique / coup parfait)."
											mult_mess$(2)="Armour: The number of damages absorbed (normal hit / critical hit / perfect hit)."
											mess$=mult_mess$(Int(options#(7)))
											mult_mess$(1)="Armure"
											mult_mess$(2)="Armour"
											car$=mult_mess$(Int(options#(7)))
										Case 4
											mult_mess$(1)="Vapeur (Steam) : La pression utilisée pour l'équipement, et celle que produit la chaudière."
											mult_mess$(2)="Steam: The amount of pressure available for steam powered equipment (used / total)."
											mess$=mult_mess$(Int(options#(7)))
											mult_mess$(1)="Stm"
											mult_mess$(2)="Stm"
											car$=mult_mess$(Int(options#(7)))
									End Select
									If MouseX()>190 And MouseX()<300 And MouseY()>(t*20+250) And MouseY()<((t+1)*20+275)
										aide_contextuelle$(1)=mess$
										aide_contextuelle$(2)=mess$
									EndIf
										
									Text 190,(t*20+250),car$
								Next
								
								If bonus_equi(10)<>0
									b_str$=av\pv[2]+" "+signed_str$(bonus_equi(10),1)
								Else
									b_str$=Str(av\pv[2])
								EndIf
								Text 255,270,max(av\pv[1],0)+" / "+b_str$
								If bonus_equi(11)<>0
									b_str$=av\init+" "+signed_str$(bonus_equi(11),1)
								Else
									b_str$=Str(av\init)
								EndIf
								Text 255,290,b_str$
								
								If av\equi[4]=0
									Text 255,310,"- / - / -"
								Else
									For armure.armure=Each armure
										If armure\num=av\equi[4]
											Text 255,310,Int(armure\val#[1])+" / "+Int(armure\val#[2])+" / "+Int(armure\val#[3])
										EndIf
									Next
								EndIf
								
								If av\equi[5]=0
									Text 255,330,"- / -"
								Else
									a_int=0
									For boiler.boiler=Each boiler
										If boiler\num=av\equi[5]
											Text 255,330,(calcul_pression(av\num,1))+" / "+Str(boiler\capacite)
										EndIf
									Next
								EndIf						
								
								encombrement=calcul_encombrement(av\num)
								;carac (Att, Def, Dgts)
								Select Int(options#(7))
									Case 1
										Text 340,270,"Armes légères"
										Text 340,290,"Att"
										Text 340,310,"Def"
										Text 340,330,"Dgts"
									Case 2
										Text 340,270,"Light Weapons"
										Text 340,290,"Att"
										Text 340,310,"Def"
										Text 340,330,"Dmg"
								End Select
								If MouseX() > 340 and MouseX() < 470
									If MouseY() > 270 and MouseY() < 291
										aide_contextuelle$(1)="Les armes de corps à corps légères (couteau, épée, lance, ...)."
										aide_contextuelle$(2)="Light melee weapons (knife, sword, spear, ...)."
									ElseIf MouseY() > 290 and MouseY() < 311
										aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes légères (Naturel + Equipement)."
										aide_contextuelle$(2)="Attack rating with light weapons (Base + Equipment)."
									ElseIf MouseY() > 310 and MouseY() < 331
										aide_contextuelle$(1)="La défense du personnage contre les armes légères (Naturel + Equipement)."
										aide_contextuelle$(2)="Defense rating against light weapons (Base + Equipment)."
									ElseIf MouseY() > 330 and MouseY() < 351
										aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes légères (Naturel + Equipement)."
										aide_contextuelle$(2)="Bonus to the damages done with light weapons (Base + Equipement)."
									EndIf									
								EndIf
								rText(395,290,Str(av\att[1]))
								If (bonus_equi(1)-encombrement)<>0 Then Text 395,290," "+signed_str$(bonus_equi(1)-encombrement,1)
								rText(395,310,Str(av\def[1]))
								If (bonus_equi(2)-encombrement)<>0 Then Text 395,310," "+signed_str$(bonus_equi(2)-encombrement,1)
								rText(395,330,Str(av\deg[1]))
								If bonus_equi(3)<>0 Then Text 395,330," "+signed_str$(bonus_equi(3),1)
								
								
								Select Int(options#(7))
									Case 1
										Text 480,270,"Armes lourdes"
										Text 480,290,"Att"
										Text 480,310,"Def"
										Text 480,330,"Dgts"								
									Case 2
										Text 480,270,"Heavy Weapons"
										Text 480,290,"Att"
										Text 480,310,"Def"
										Text 480,330,"Dmg"								
								End Select
								If MouseX() > 480 and MouseX() < 610
									If MouseY() > 270 and MouseY() < 291
										aide_contextuelle$(1)="Les armes de corps à corps lourdes (hache, marteau, tronçonneuse, ...)."
										aide_contextuelle$(2)="Heavy melee weapons (axe, warhammer, chainsaw, ...)."
									ElseIf MouseY() > 290 and MouseY() < 311
										aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes lourdes (Naturel + Equipement)."
										aide_contextuelle$(2)="Attack rating with heavy weapons (Base + Equipment)."
									ElseIf MouseY() > 310 and MouseY() < 331
										aide_contextuelle$(1)="La défense du personnage contre les armes lourdes (Naturel + Equipement)."
										aide_contextuelle$(2)="Defense rating against heavy weapons (Base + Equipment)."
									ElseIf MouseY() > 330 and MouseY() < 351
										aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes lourdes (Naturel + Equipement)."
										aide_contextuelle$(2)="Bonus to the damages done with heavy weapons (Base + Equipement)."
									EndIf									
								EndIf
								rText(535,290,Str(av\att[2]))
								If (bonus_equi(4)-encombrement)<>0 Then Text 535,290," "+signed_str$(bonus_equi(4)-encombrement,1)
								rText(535,310,Str(av\def[2]))
								If (bonus_equi(5)-encombrement)<>0 Then Text 535,310," "+signed_str$(bonus_equi(5)-encombrement,1)
								rText(535,330,Str(av\deg[2]))
								If bonus_equi(6)<>0 Then Text 535,330," "+signed_str$(bonus_equi(6),1)
								
								Select Int(options#(7))
									Case 1
										Text 620,270,"Armes à distance"
										Text 620,290,"Att"
										Text 620,310,"Def"
										Text 620,330,"Dgts"
									Case 2
										Text 620,270,"Ranged Weapons"
										Text 620,290,"Att"
										Text 620,310,"Def"
										Text 620,330,"Dmg"
								End Select
								If MouseX() > 620 and MouseX() < 750
									If MouseY() > 270 and MouseY() < 291
										aide_contextuelle$(1)="Les armes à distance (pistolet, fusil, lance-piere, ...)."
										aide_contextuelle$(2)="Ranged weapons (handgun, rifle, slingshot, ...)."
									ElseIf MouseY() > 290 and MouseY() < 311
										aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes à distance (Naturel + Equipement)."
										aide_contextuelle$(2)="Attack rating with ranged weapons (Base + Equipment)."
									ElseIf MouseY() > 310 and MouseY() < 331
										aide_contextuelle$(1)="La défense du personnage contre les armes à distance (Naturel + Equipement)."
										aide_contextuelle$(2)="Defense rating against ranged weapons (Base + Equipment)."
									ElseIf MouseY() > 330 and MouseY() < 351
										aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes à distance (Naturel + Equipement)."
										aide_contextuelle$(2)="Bonus to the damages done with ranged weapons (Base + Equipement)."
									EndIf									
								EndIf
								rText(675,290,Str(av\att[3]))
								If (bonus_equi(7)-encombrement)<>0 Then Text 675,290," "+signed_str$(bonus_equi(7)-encombrement,1)
								rText(675,310,Str(av\def[3]))
								If (bonus_equi(8)-encombrement)<>0 Then Text 675,310," "+signed_str$(bonus_equi(8)-encombrement,1)
								rText(675,330,Str(av\deg[3]))
								If bonus_equi(9)<>0 Then Text 675,330," "+signed_str$(bonus_equi(9),1)
								
								
								;règles spéciales
								;SetFont big_font
								nb_rules=1
								effet$=""
								For t=1 To 7
									rules_description$(t)=""
								Next
								For k=1 To 15
									If av\cmpt[k]<>0
										For rules.rules=Each rules
											If rules\num=av\cmpt[k]
												If nb_rules>1
													effet$=effet$+",#"+rules\name$[Int(options#(7))]
												Else
													effet$="#"+rules\name$[Int(options#(7))]
												EndIf
												nb_rules=nb_rules+1
												rules_description$(nb_rules)=rules\description$[Int(options#(7))]
											EndIf
										Next
									EndIf
								Next
								
								For t=1 To 7
								disc_ligne(t)=""
								Next
								mult_mess$(1)="Talent(s) : "
								mult_mess$(2)="Special abilities: "
								mess$=mult_mess$(Int(options#(7)))+effet$
								t=0
								k=0
								amax=Len(mess$)	
								max_ligne=Int(50)
								For a_int=1 To amax
									t=t+1
									If Mid$(mess$,t,1)="#"
										k=k+1
										disc_ligne$(k)=Left$(mess$,t-1)
										mess$=Right$(mess$,Len(mess$)-t)
										t=0
									EndIf
									If t=max_ligne
										t=lastspace(Left$(mess$,max_ligne),max_ligne)
										k=k+1
										If k>7
											mess$=""
										Else
											disc_ligne$(k)=Left$(mess$,t-1)
											mess$=Right$(mess$,Len(mess$)-t)
											t=0
										EndIf
									EndIf
								Next
								If mess$<>""
									k=k+1
									disc_ligne$(k)=mess$
								EndIf
								For t=1 To 7
									disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
									Text 200,(368+17*t),disc_ligne$(t)
									If MouseX()>200 And MouseX()<400 And MouseY()>368+17*t And MouseY()<368+17*(t+1) Then aide_contextuelle$(Int(options#(7)))=rules_description$(t)
								Next
							EndIf
						Next
					
					Case 2 ; Equipement
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495
						; onglets têtes
							k=0
							For t=1 To 10
								If t=cible
									k=k+1
									If t<10
										DrawImage gfx_signet,(155+64*(k-1))+13,5
									Else
										DrawImage gfx_signet,(155+64*(9))+13,5
									EndIf
								Else
									If t<10
										If gr\formation[t]<>0 
											k=k+1
											DrawImage gfx_signet_sombre,(155+64*(k-1))+13,5
										Endif
									Else
										k=k+1
										DrawImage gfx_signet_sombre,(155+64*(9))+13,5
									EndIf
								EndIf
								If MouseY()<75
									If MouseX()>(155+64*(k-1)) And MouseX()<(155+64*(k)) And keys(1,2)=50 And t<10
										If gr\formation[t]<>0 And cible<>t 
											Playsound2(sons_menu(6))
											If cible=10
												reverser_butin(-1,1)
												stack_butin(1,1)
											EndIf
											cible=t
											inventaire_cible=0
											
										EndIf
									EndIf
									If MouseX()>(155+64*9) And MouseX()<(155+640) And keys(1,2)=50
										If cible<>10 Then Playsound2(sons_menu(6)):cible=10:inventaire_cible=0
									EndIf
								EndIf
								If t<10
									If gr\formation[t]<>0
										For av.avatar=Each avatar
											If av\num=gr\formation[t]
												if av\num>3
													If fighters_tete(av\cat,1)<>0 Then DrawBlock fighters_tete(av\cat,1),(155+64*(k-1))+18,18
												else
													DrawBlock PJ_small(av\num),(155+64*(k-1))+18,18
												endif
											EndIf
										Next
									EndIf
								Else
									DrawImage face_poubelle,(155+64*(9))+18,18
								EndIf
							Next
							
							Color 5,5,5
							If cible<10
								If gr\formation[cible]<>0
									For av.avatar=Each avatar
										If av\num=gr\formation[cible]
											SetFont middle_font
											Color 0,0,0
											Text 180,110,av\name$[Int(options#(7))]
											
											Rect 165,195,200,360,0	
									
											;afficher les valuables
											SetFont middle_font
											Color 0,0,0
											drift_caps=StringWidth(player_caps)
											drift_junk=StringWidth(player_junk)
											drift=max(drift_caps,max(drift_junk,10))
											mult_mess$(1)="Caps :"
											mult_mess$(2)="Caps:"
											Text 180,150,mult_mess$(Int(options#(7)))											
											Text 242+drift-drift_caps,150,player_caps
											If MouseX()<320 And MouseX()>180 And MouseY()<170 And MouseY()>150
												aide_contextuelle$(1)="La monnaie utilisée à FactoryTec."
												aide_contextuelle$(2)="The currency used in FactoryTec."
										
											EndIf
											mult_mess$(1)="Junk :"
											mult_mess$(2)="Junk:"
											Text 180,170,mult_mess$(Int(options#(7)))
											Text 242+drift-drift_junk,170,player_junk
										
											If MouseX()<320 And MouseX()>180 And MouseY()<190 And MouseY()>170
												aide_contextuelle$(1)="Pièces détachées utilisées pour améliorer les armes et équipements."
												aide_contextuelle$(2)="Spare parts used to improve weapons and equipment."
											EndIf

											;dessiner le bonhomme
											DrawImage gfx_xiao,225,210
											
											;dessiner les armes
											For t=1 To 3
												Color 0,0,0
												Rect 180-2,(170+t*65)-2,44,44,0
												If av\equi[t]<>0
													For arme.arme=Each arme
														If arme\num=av\equi[t]
															If arme\icone[1]<>0
																DrawBlock arme\icone[1],180,(170+t*65)
															Else
																DrawBlock non_icone,180,(170+t*65)
															EndIf
														EndIf
													Next
												EndIf
												If MouseX()<220 And MouseX()>180 And MouseY()>(170+t*65) And MouseY()<(225+t*65)
													aide_contextuelle$(1)="Arme"
													aide_contextuelle$(2)="Weapon"
													If t=1
														aide_contextuelle$(1)="Arme équipée"
														aide_contextuelle$(2)="Equiped weapon"
													EndIf
													Color 180,0,0
													Rect 180-4,(170+t*65)-4,48,48,0
													If keys(1,2)=50
														If inventaire_cible<>-t Then Playsound2(sons_menu(6))
														inventaire_cible=-t
														old_inventaire_cible=-100
													ElseIf keys(1,3)=49
														If inventaire_cible<>-t
															; vérifier qu'il s'agit bien d'une arme
															If inventaire_cible<0
																If inventaire_cible>-4
																	calcul_bonus_equi(av\num)
																	old_bonus_pv=bonus_equi(10)
																	ai=av\equi[t]
																	av\equi[t]=av\equi[-inventaire_cible]
																	av\equi[-inventaire_cible]=ai
																	calcul_bonus_equi(av\num)
																	new_bonus_pv=bonus_equi(10)
																	If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																	Playsound2(sons_menu(6))
																	inventaire_cible=-t
																ElseIf inventaire_cible<>0
																	Playsound2(sons_menu(2))
																	mult_mess$(1)="Emplacement réservé aux armes"
																	mult_mess$(2)="This slot is for weapons only"
																	msg_erreur$=mult_mess$(Int(options#(7)))
																	msg_erreur_timing=90
																EndIf
															Else
																; gerer avec le butin
																good=0
																For butin.butin=Each butin
																	If butin\num=1 And good=0
																		good=1
																		If butin\loot[inventaire_cible]<100 And butin\loot[inventaire_cible]>0; c'est une arme
																			calcul_bonus_equi(av\num)
																			old_bonus_pv=bonus_equi(10)
																			ai=av\equi[t]
																			av\equi[t]=butin\loot[inventaire_cible]
																			If calcul_pression(av\num)
																				butin\loot[inventaire_cible]=ai
																				stack_butin(butin\num)
																				calcul_bonus_equi(av\num)
																				new_bonus_pv=bonus_equi(10)
																				If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																				Playsound2(sons_menu(6))
																				inventaire_cible=-t
																			Else
																				av\equi[t]=ai
																				Playsound2(sons_menu(2))
																				mult_mess$(1)="Pas assez de Vapeur"
																				mult_mess$(2)="Not enough Steam"
																				msg_erreur$=mult_mess$(Int(options#(7)))
																				msg_erreur_timing=90
																			EndIf
																		ElseIf inventaire_cible<>0
																			Playsound2(sons_menu(2))
																			mult_mess$(1)="Emplacement réservé aux armes"
																			mult_mess$(2)="This slot is for weapons only"
																			msg_erreur$=mult_mess$(Int(options#(7)))
																			msg_erreur_timing=90																	
																		EndIf
																	EndIf
																Next
															EndIf														
														EndIf
													Elseif MouseDown(1)=False and av\equi[t]<>0
														;If inventaire_cible<>-t Then Playsound2(sons_menu(6))
														inventaire_cible=-t
													EndIf
												EndIf
	 										Next
											
											;dessiner l'armure
											Color 0,0,0
											Rect 255-2,295-2,44,44,0
											If av\equi[4]<>0
												For armure.armure=Each armure
													If armure\num=av\equi[4]
														If armure\icone[1]<>0
															DrawBlock armure\icone[1],255,295
														Else
															DrawBlock non_icone,255,295
														EndIf
													EndIf
												Next
											EndIf
											If MouseX()>255 And MouseX()<295 And MouseY()>295 And MouseY()<335
												aide_contextuelle$(1)="Armure"
												aide_contextuelle$(2)="Armour"
												Color 180,0,0
												Rect 255-4,295-4,48,48,0
												If keys(1,2)=50
													If inventaire_cible<>-4 Then Playsound2(sons_menu(6))
													inventaire_cible=-4
													old_inventaire_cible=-100
												ElseIf keys(1,3)=49
													If inventaire_cible<>-4
														; vérifier qu'il s'agit bien d'une armure
														If inventaire_cible<0
															Playsound2(sons_menu(2))
															mult_mess$(1)="Emplacement réservé aux armures"
															mult_mess$(2)="This slot is for armours only"
															msg_erreur$=mult_mess$(Int(options#(7)))
															msg_erreur_timing=90
														Else
															; gerer avec le butin
															good=0
															For butin.butin=Each butin
																If butin\num=1 And good=0
																	good=1
																	If butin\loot[inventaire_cible]>99 And butin\loot[inventaire_cible]<150 ; c'est une armure
																		calcul_bonus_equi(av\num)
																		old_bonus_pv=bonus_equi(10)
																		ai=av\equi[4]
																		av\equi[4]=butin\loot[inventaire_cible]
																		trop_petit=0
																		For armure.armure=Each armure
																			If armure\num=butin\loot[inventaire_cible]
																				For t=1 To 3
																					If armure\rules[t]=314 Then trop_petit=1
																				Next
																			EndIf
																		Next
																		If trop_petit=0
																			If calcul_pression(av\num)	
																				butin\loot[inventaire_cible]=ai
																				stack_butin(butin\num)
																				calcul_bonus_equi(av\num)
																				new_bonus_pv=bonus_equi(10)
																				If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																				Playsound2(sons_menu(6))
																				inventaire_cible=-4
																			Else
																				av\equi[4]=ai
																				Playsound2(sons_menu(2))
																				mult_mess$(1)="Pas assez de Vapeur"
																				mult_mess$(2)="Not enough Steam"
																				msg_erreur$=mult_mess$(Int(options#(7)))
																				msg_erreur_timing=90
																			EndIf
																		Else
																			Playsound2(sons_menu(2))
																			mult_mess$(1)="Equipement trop petit"
																			mult_mess$(2)="Too small"
																			msg_erreur$=mult_mess$(Int(options#(7)))
																			msg_erreur_timing=90
																		EndIf
																	ElseIf inventaire_cible<>0
																		Playsound2(sons_menu(2))
																		mult_mess$(1)="Emplacement réservé aux armures"
																		mult_mess$(2)="This slot is for armours only"
																		msg_erreur$=mult_mess$(Int(options#(7)))
																		msg_erreur_timing=90
																	EndIf
																EndIf
															Next
														EndIf											
													EndIf
												ElseIf Mousedown(1)=False and av\equi[4]<>0
													;If inventaire_cible<>-4 Then Playsound2(sons_menu(6))
													inventaire_cible=-4
												EndIf
											EndIf

											;dessiner la chaudière
											Color 0,0,0
											Rect 310-2,295-2,44,44,0
											If av\equi[5]<>0
												For boiler.boiler=Each boiler
													If boiler\num=av\equi[5]
														If boiler\icone[1]<>0
															DrawBlock boiler\icone[1],310,295
														Else
															DrawBlock non_icone,310,295
														EndIf
													EndIf
												Next
											EndIf
											If MouseX()>310 And MouseX()<350 And MouseY()>295 And MouseY()<335
												aide_contextuelle$(1)="Chaudière"
												aide_contextuelle$(2)="Boiler"
												Color 180,0,0
												Rect 310-4,295-4,48,48,0
												If keys(1,2)=50
													If inventaire_cible<>-5 Then Playsound2(sons_menu(6))
													inventaire_cible=-5
													old_inventaire_cible=-100
												ElseIf keys(1,3)=49
													If inventaire_cible<>-5
														; vérifier qu'il s'agit bien d'une chaudière
														If inventaire_cible<0
															Playsound2(sons_menu(2))
															mult_mess$(1)="Emplacement réservé aux chaudières"
															mult_mess$(2)="This slot is for boilers only"
															msg_erreur$=mult_mess$(Int(options#(7)))
															msg_erreur_timing=90
														Else
															;gerer avec le butin
															good=0
															For butin.butin=Each butin
																If butin\num=1 And good=0
																	good=1
																	If butin\loot[inventaire_cible]>149 And butin\loot[inventaire_cible]<200 ; c'est une chaudière
																		calcul_bonus_equi(av\num)
																		old_bonus_pv=bonus_equi(10)
																		ai=av\equi[5]
																		av\equi[5]=butin\loot[inventaire_cible]
																		If calcul_pression(av\num)	
																			butin\loot[inventaire_cible]=ai
																			stack_butin(butin\num)
																			calcul_bonus_equi(av\num)
																			new_bonus_pv=bonus_equi(10)
																			If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																			Playsound2(sons_menu(6))
																			inventaire_cible=-5
																		Else
																			av\equi[5]=ai
																			Playsound2(sons_menu(2))
																			mult_mess$(1)="Plus assez de Vapeur"
																			mult_mess$(2)="Not enough Steam anymore"
																			msg_erreur$=mult_mess$(Int(options#(7)))
																			msg_erreur_timing=90
																		EndIf
																	ElseIf inventaire_cible<>0
																		Playsound2(sons_menu(2))
																		mult_mess$(1)="Emplacement réservé aux chaudières"
																		mult_mess$(2)="This slot is for boilers only"
																		msg_erreur$=mult_mess$(Int(options#(7)))
																		msg_erreur_timing=90
																	EndIf
																EndIf
															Next
														EndIf											
													EndIf
												ElseIf MouseDown(1)=False and av\equi[5]<>0
													;If inventaire_cible<>-5 Then Playsound2(sons_menu(6))
													inventaire_cible=-5
												EndIf
											EndIf

											;dessiner les spéciaux
											Color 0,0,0
											Rect 310-2,240-2,44,44,0
											If av\equi[6]<>0
												For special.special=Each special
													If special\num=av\equi[6]
														If special\icone[1]<>0
															DrawBlock special\icone[1],310,240
														Else
															DrawBlock non_icone,310,240
														EndIf
													EndIf
												Next
											EndIf
											If MouseX()>310 And MouseX()<350 And MouseY()>240 And MouseY()<280
												aide_contextuelle$(1)="GearBot"
												aide_contextuelle$(2)="GearBot"
												Color 180,0,0
												Rect 310-4,240-4,48,48,0
												If keys(1,2)=50
													If inventaire_cible<>-6 Then Playsound2(sons_menu(6))
													inventaire_cible=-6
													old_inventaire_cible=-100
												ElseIf keys(1,3)=49
													If inventaire_cible<>-6
														; vérifier qu'il s'agit bien d'un spécial
														If inventaire_cible<0
															Playsound2(sons_menu(2))
															mult_mess$(1)="Emplacement réservé aux GearBots"
															mult_mess$(2)="This slot is for GearBots only"
															msg_erreur$=mult_mess$(Int(options#(7)))
															msg_erreur_timing=90
														Else
															; gerer avec le butin
															good=0
															For butin.butin=Each butin
																If butin\num=1 And good=0
																	good=1
																	If butin\loot[inventaire_cible]>199 And butin\loot[inventaire_cible]<250 ; c'est un GearBot
																		calcul_bonus_equi(av\num)
																		old_bonus_pv=bonus_equi(10)
																		ai=av\equi[6]
																		av\equi[6]=butin\loot[inventaire_cible]
																		If calcul_pression(av\num)	
																			butin\loot[inventaire_cible]=ai
																			stack_butin(butin\num)
																			calcul_bonus_equi(av\num)
																			new_bonus_pv=bonus_equi(10)
																			If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																			Playsound2(sons_menu(6))
																			inventaire_cible=-6
																		Else
																			av\equi[6]=ai
																			Playsound2(sons_menu(2))
																			mult_mess$(1)="Pas assez de Vapeur"
																			mult_mess$(2)="Not enough Steam"
																			msg_erreur$=mult_mess$(Int(options#(7)))
																			msg_erreur_timing=90
																		EndIf
																	ElseIf inventaire_cible<>0
																		Playsound2(sons_menu(2))
																		mult_mess$(1)="Emplacement réservé aux GearBots"
																		mult_mess$(2)="This slot is for GearBots only"
																		msg_erreur$=mult_mess$(Int(options#(7)))
																		msg_erreur_timing=90
																	EndIf
																EndIf
															Next
														EndIf												
													EndIf
												ElseIf MouseDown(1)=False and av\equi[6]<>0
													;If inventaire_cible<>-6 Then Playsound2(sons_menu(6))
													inventaire_cible=-6
												EndIf
											EndIf
											
											;dessiner le butin
											Color 0,0,0
											good=0
											For butin.butin=Each butin
												If butin\num=1
													Rect 375,115,105,440,0
													;comptez le nombre d'objets dans l'inventaire
													nb_objets=-1
													For t=1 To 250
														If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
													Next
													If nb_objets=-1 Then nb_objets=250
													If MouseX()>375 And MouseX()<485 And MouseY()>115
														aide_contextuelle$(1)="Inventaire : utilisez la roulette pour faire défiler le butin."
														aide_contextuelle$(2)="Inventory: use the mouse wheel to scroll through the loot."
														If inventaire_cible>0
															aide_contextuelle$(Int(options#(7)))=aide_contextuelle$(Int(options#(7)))+" ("+Abs(inventaire_cible)+"/"+nb_objets+")"
														Else
															aide_contextuelle$(Int(options#(7)))=aide_contextuelle$(Int(options#(7)))+" ("+nb_objets+")"
														EndIf
														degre_roulette#=degre_roulette#-sourisz#
														degre_roulette#=maxf(0,minf(degre_roulette#,Ceil(nb_objets*0.5-9)))
																													
														If keys(1,3)=49 And inventaire_cible<0 ;front descendant
															If nb_objets<250
																nb_objets=nb_objets+1
																calcul_bonus_equi(av\num)
																old_bonus_pv=bonus_equi(10)
																butin\loot[nb_objets]=av\equi[-inventaire_cible]
																av\equi[-inventaire_cible]=0
																calcul_bonus_equi(av\num)
																new_bonus_pv=bonus_equi(10)
																If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
																Playsound2(sons_menu(6))
															Else ; inventaire plein
																Playsound2(sons_menu(2))
																mult_mess$(1)="Inventaire plein"
																mult_mess$(2)="Inventory full"
																msg_erreur$=mult_mess$(Int(options#(7)))
																msg_erreur_timing=90
															EndIf
														EndIf
													EndIf
													
													roulette=Int(degre_roulette#)
													
													If roulette=0
														Rect 375+2,115+2,105-4,15-4,0
													Else
														Rect 375+2,115+2,105-4,15-4,1
													EndIf
													
													If roulette=Ceil(nb_objets*0.5-9)
														Rect 375+2,555-15+2,105-4,15-4,0
													Else
														Rect 375+2,555-15+2,105-4,15-4,1
													EndIf
												
													For u=0 To 8 ; ligne
														For v=0 To 1 ; colonne
															t=roulette*2+u*2+v+1
															If t<251	
																If butin\loot[t]<>0
																	icone=0
																	If butin\loot[t]<100
																		For arme.arme=Each arme
																			If arme\num=butin\loot[t]
																				icone=arme\icone[1]
																			EndIf
																		Next
																	ElseIf butin\loot[t]<150
																		For armure.armure=Each armure
																			If armure\num=butin\loot[t]
																				icone=armure\icone[1]
																			EndIf
																		Next
																	ElseIf butin\loot[t]<200
																		For boiler.boiler=Each boiler
																			If boiler\num=butin\loot[t]
																				icone=boiler\icone[1]
																			EndIf
																		Next
																	Else
																		For special.special=Each special
																			If special\num=butin\loot[t]
																				icone=special\icone[1]
																			EndIf
																		Next
																	EndIf
																	
																	If icone=0
																		DrawBlock non_icone,(375+9+46*v),(115+17+46*u)
																	Else
																		DrawBlock icone,(375+9+46*v),(115+17+46*u)
																	EndIf
																	
																	If MouseX()>(375+9+46*v) And MouseX()<(375+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
																		Rect (375+9+46*v)-2,(115+17+46*u)-2,44,44,0
																		If keys(1,2)=50
																			If t<>inventaire_cible Then Playsound2(sons_menu(6))
																			inventaire_cible=t
																			old_inventaire_cible=-100
																		ElseIf MouseDown(1)=False
																			;If t<>inventaire_cible Then Playsound2(sons_menu(6))
																			inventaire_cible=t
																		EndIf
																	EndIf
																EndIf
															EndIf
														Next
													Next
												EndIf
											Next
																			
											;afficher les infos de l'équipement selectionné
											num_equi=0
											If inventaire_cible<0
												num_equi=av\equi[-inventaire_cible]		
											ElseIf inventaire_cible>0
												;dans le butin
												good=0
												For butin.butin=Each butin
													If butin\num=1 And good=0
														good=1
														num_equi=butin\loot[inventaire_cible]
													EndIf
												Next
											EndIf
											aff_info_equipement(num_equi,"Inventaire")
																
											;création/séparation de paire de pistolet
											For butin.butin=Each butin
												If butin\num=1													
													If num_equi=13 Or num_equi=15 ;paire
														;chercher si il y a la place dans l'inventaire
														good=0
														For u=1 To 250
															If butin\loot[u]=0
																good=u
																Exit
															EndIf
														Next
														If good>0
															;afficher le bouton
															;Rect 490,115,300,440,0
															a=40
															DrawBlock bouton_paire,785-a,550-a,1
															If MouseX()<785 And MouseX()>785-a And MouseY()<550 And MouseY()>550-a
																Color 150,0,0
																aide_contextuelle$(1)="Séparer la paire de pistolets."
																aide_contextuelle$(2)="Separate the handgun pair."
																If keys(1,2)=50
																	keys(1,2)=49
																	If inventaire_cible<0 ; équipé
																		av\equi[-inventaire_cible]=num_equi-1
																	Else
																		butin\loot[inventaire_cible]=num_equi-1
																	EndIf
																	butin\loot[good]=num_equi-1
																	;stack_butin(1,1)
																	no_moving_icone=1
																EndIf
															EndIf
															Rect 785-a,550-a,a,a,0
														EndIf
													ElseIf num_equi=12 Or num_equi=14 ;pistolet seul
														;chercher dans l'inventaire si il y en a un autre
														good=0
														For u=1 To 250
															If butin\loot[u]=num_equi And u<>inventaire_cible
																good=1
																last_flingue=u
															EndIf
														Next
														If good>0
															a=40
															DrawBlock bouton_paire,785-a,550-a,0
															If MouseX()<785 And MouseX()>785-a And MouseY()<550 And MouseY()>550-a
																Color 150,0,0
																aide_contextuelle$(1)="Créer une paire de pistolets."
																aide_contextuelle$(2)="Combine two handuns in a pair"
																If keys(1,2)=50
																	keys(1,2)=49
																	If inventaire_cible<0 ; équipé
																		av\equi[-inventaire_cible]=num_equi+1
																	Else
																		butin\loot[inventaire_cible]=num_equi+1
																	EndIf
																	butin\loot[last_flingue]=0
																	stack_butin(1)
																	no_moving_icone=1
																EndIf
															EndIf
															Rect 785-a,550-a,a,a,0
														EndIf							
													EndIf
												EndIf
											Next																				
										EndIf
									Next
								EndIf
								
								;icône de l'équipement transferé
						 		If keys(1,2)=50 And inventaire_cible=old_inventaire_cible Then inventaire_cible=0
						 		old_inventaire_cible=inventaire_cible
						 		If onglet=2 And MouseDown(1)
						 			If inventaire_cible<>0 And no_moving_icone=0
						 				If inventaire_cible<0 ; le déjà équipé
						 					For av.avatar=Each avatar
						 						If av\num=gr\formation[cible]
						 							If inventaire_cible>-4 ; arme
						 								If av\equi[-inventaire_cible]<>0
						 									For arme.arme=Each arme
						 										If arme\num=av\equi[-inventaire_cible]
						 											DrawBlock arme\icone[1],MouseX()-20,MouseY()-20
						 										EndIf
						 									Next
						 								EndIf
						 							ElseIf inventaire_cible=-4 ; armure
						 								For ar.armure=Each armure
						 									If ar\num=av\equi[4]
						 										DrawBlock ar\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							ElseIf inventaire_cible=-5 ; boiler
						 								For boi.boiler = Each boiler
						 									If boi\num=av\equi[5]
						 										DrawBlock boi\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							Else	
						 								For spe.special = Each special
						 									If spe\num=av\equi[6]
						 										DrawBlock spe\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							EndIf
						 						EndIf
						 					Next							
						 				Else;vient du butin
						 					good=0
						 					For butin.butin=Each butin
						 						If butin\num=1 And good=0
						 							good=1
						 							num=butin\loot[inventaire_cible]
						 							If num<100 ; arme
						 								For arme.arme=Each arme
						 									If arme\num=num
						 										DrawBlock arme\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							ElseIf num<150 ; armure
						 								For ar.armure=Each armure
						 									If ar\num=num
						 										DrawBlock ar\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							ElseIf num<200 ; boiler
						 								For boi.boiler = Each boiler
						  									If boi\num=num
						 										DrawBlock boi\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							Else ; spéciaux
						 								For spe.special = Each special
						 									If spe\num=num
						 										DrawBlock spe\icone[1],MouseX()-20,MouseY()-20
						 									EndIf
						 								Next
						 							EndIf
						 						EndIf
						 					Next
						 				EndIf
						 			EndIf
								Else
									no_moving_icone=0
						 		EndIf
						 		

							Else
;doko_poubelle		
								Color 5,5,5
								;afficher l'inventaire
								inventaire_cible=0
								SetFont big_font
								mult_mess$(1)="Inventaire"
								mult_mess$(2)="Inventory"
								Text 170,100,mult_mess$(Int(options#(7))),0,1
								Rect 170,115,150,440,0 
								For butin.butin=Each butin
									If butin\num=1
										;comptez le nombre d'objets dans l'inventaire
										nb_objets=-1
										For t=1 To 250
											If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
										Next
										If nb_objets=-1 Then nb_objets=250
										If MouseX()>170 And MouseX()<320 And MouseY()>115 and mouseY()<565
											aide_contextuelle$(1)="Inventaire : utilisez la roulette pour faire défiler le butin"
											aide_contextuelle$(2)="Inventory: use the mouse wheel to scroll through the loot."
											If inventaire_cible>0
												aide_contextuelle$(Int(options#(7)))=aide_contextuelle$(Int(options#(7)))+" ("+Abs(inventaire_cible)+"/"+nb_objets+")"
											Else
												aide_contextuelle$(Int(options#(7)))=aide_contextuelle$(Int(options#(7)))+" ("+nb_objets+")"
											EndIf
											degre_roulette#=degre_roulette#-sourisz#
											degre_roulette#=maxf(0,minf(degre_roulette#,Ceil(nb_objets*0.333-9)))
										EndIf
										
										roulette=Int(degre_roulette#)
										
										If roulette=0
											Rect 170+2,115+2,150-4,15-4,0
										Else
											Rect 170+2,115+2,150-4,15-4,1
										EndIf
										
										If roulette=max(Ceil(nb_objets*0.333-9),0)
											Rect 170+2,555-15+2,150-4,15-4,0
										Else
											Rect 170+2,555-15+2,150-4,15-4,1
										EndIf
									
										For u=0 To 8 ; ligne
											For v=0 To 2 ; colonne
												t=roulette*3+u*3+v+1
												If t<251
													If butin\loot[t]<>0
														icone=0
														If butin\loot[t]<100
															For arme.arme=Each arme
																If arme\num=butin\loot[t]
																	icone=arme\icone[1]
																EndIf
															Next
														ElseIf butin\loot[t]<150
															For armure.armure=Each armure
																If armure\num=butin\loot[t]
																	icone=armure\icone[1]
																EndIf
															Next
														ElseIf butin\loot[t]<200
															For boiler.boiler=Each boiler
																If boiler\num=butin\loot[t]
																	icone=boiler\icone[1]
																EndIf
															Next
														Else
															For special.special=Each special
																If special\num=butin\loot[t]
																	icone=special\icone[1]
																EndIf
															Next
														EndIf
														
														If icone=0
															DrawBlock non_icone,(170+9+46*v),(115+17+46*u)
														Else
															DrawBlock icone,(170+9+46*v),(115+17+46*u)
														EndIf
														
														If MouseX()>(170+9+46*v) And MouseX()<(170+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
															Rect (170+9+46*v)-2,(115+17+46*u)-2,44,44,0
															inventaire_cible=t
															If keys(1,2)=50
																keys(1,2)=49
																Playsound2(sons_menu(6))
																;faire passer dans la poubelle
																item_give(-1,butin\loot[t])
																butin\loot[t]=0
																stack_butin(-1)
																stack_butin(1)
															EndIf
														EndIf
													EndIf
												EndIf
											Next
										Next
									EndIf
								Next
								;afficher la poubelle
								mult_mess$(1)="Poubelle"
								mult_mess$(2)="Recycling Bin"
								Text 330,100,mult_mess$(Int(options#(7))),0,1
								rect 330,115,150,350,0 
								For butin.butin=Each butin
									If butin\num=-1
										caps=0
										;comptez le nombre d'objets dans l'inventaire
										nb_objets=-1
										For t=1 To 250
											If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
										Next
										If nb_objets=-1 Then nb_objets=250
										If MouseX()>330 And MouseX()<480 And MouseY()>115 and mouseY()<565
											aide_contextuelle$(1)="Poubelle : utilisez la roulette pour faire défiler la poubelle "+Abs(inventaire_cible)+"/"+nb_objets
											aide_contextuelle$(2)="Recycling Bin: use the mouse wheel to scroll through the bin "+Abs(inventaire_cible)+"/"+nb_objets
											degre_roulette2#=degre_roulette2#-sourisz#
											degre_roulette2#=maxf(0,minf(degre_roulette2#,Ceil(nb_objets*0.3333-7)))
										EndIf
										
										roulette2=Int(degre_roulette2#)
										
										If roulette2=0
											Rect 330+2,115+2,150-4,15-4,0
										Else
											Rect 330+2,115+2,150-4,15-4,1
										EndIf
										
										If roulette2=max(Ceil(nb_objets*0.3333-7),0)
											Rect 330+2,465-15+2,150-4,15-4,0
										Else
											Rect 330+2,465-15+2,150-4,15-4,1
										EndIf
									
										For u=0 To 6 ; ligne
											For v=0 To 2 ; colonne
												t=roulette2*3+u*3+v+1
												If t<251
													If butin\loot[t]<>0
														icone=0
														If butin\loot[t]<100
															For arme.arme=Each arme
																If arme\num=butin\loot[t]
																	icone=arme\icone[1]
																EndIf
															Next
														ElseIf butin\loot[t]<150
															For armure.armure=Each armure
																If armure\num=butin\loot[t]
																	icone=armure\icone[1]
																EndIf
															Next
														ElseIf butin\loot[t]<200
															For boiler.boiler=Each boiler
																If boiler\num=butin\loot[t]
																	icone=boiler\icone[1]
																EndIf
															Next
														Else
															For special.special=Each special
																If special\num=butin\loot[t]
																	icone=special\icone[1]
																EndIf
															Next
														EndIf
														
														If icone=0
															DrawBlock non_icone,(330+9+46*v),(115+17+46*u)
														Else
															DrawBlock icone,(330+9+46*v),(115+17+46*u)
														EndIf
														
														If MouseX()>(330+9+46*v) And MouseX()<(330+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
															Rect (330+9+46*v)-2,(115+17+46*u)-2,44,44,0
															inventaire_cible=-t
															If keys(1,2)=50
																keys(1,2)=49
																Playsound2(sons_menu(6))
																;faire passer dans l'inventaire
																item_give(1,butin\loot[t])
																butin\loot[t]=0
																stack_butin(-1)
																stack_butin(1)
															EndIf
														EndIf
													EndIf
												EndIf
											Next
										Next
									EndIf
								Next
								;afficher les stats
								Rect 490,115,300,440,0
								cadence=0
								nb_rules=1
								icone=0
								num=0
								name$=""
						 		If inventaire_cible<>0
									If inventaire_cible>0
						 				butin_cible=1
						 			Else
						 				butin_cible=-1
						 			EndIf
						 			good=0
						 			For butin.butin=Each butin
										If butin\num=butin_cible And good=0
											good=1
											aff_info_equipement(butin\loot[Abs(inventaire_cible)],"Poubelle")
										EndIf
									Next
								EndIf										
								
								Color 5,5,5
								;bouton Recycler
								Rect 330,475,150,80,0
								setfont big_font
								junk=prix(-1,2)
								mult_mess$(1)="Recycler"
								mult_mess$(2)="Recycle"
								Text 405,515,mult_mess$(Int(options#(7)))+" ("+junk+")",1,1
								If MouseX()>330 And MouseX()<480 And MouseY()>475 And MouseY()<555
									aide_contextuelle$(1)="Recycler les objets dans la poubelle."
									aide_contextuelle$(2)="Recycle all items in the bin."
									If keys(1,2)=50
										keys(1,2)=49
										reponse=0
										While reponse=0
											start_loop()
											lire_clavier()
											disc_len#=10000
											Color 255,255,255
											Select Int(options#(7))
												Case 1
													fenetre_info("Attention : Recycler détruira définitivement tous les objets dans la poubelle pour les transformer en [junk]",0,100,0)
													reponse=fenetreqcm(2,"Recycler ?","Oui","Non")
												Case 2
													fenetre_info("Warning: Recycling will permanently destroy all the items in the bin to turn them into [junk]",0,100,0)
													reponse=fenetreqcm(2,"Recycle?","Yes","No")
											End Select
											DrawImage curseur,MouseX(),MouseY()
											Flip
											compensation_lag()
										Wend
										If reponse=1
											Playsound2(sons_menu(8))
											player_junk=player_junk+junk
											For poubelle.butin=Each butin
												If poubelle\num=-1
													For t=1 to 250
														poubelle\loot[t]=0
													Next
												EndIf
											Next
										EndIf
									EndIf
								EndIf
							EndIf
								
			
					Case 3 ; formation (complet)
						;dessiner le fond
						
						;dessiner les gars
						combat_target=0		
						For i=0 To 2 ; ligne
							For j=1 To 3 ; colonne
								t=i*3+j
								ai=150+(screenwidth-150)*0.5+(150*(j-2))
								bi=(100*i+190)
								If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80
									frame=reste(Int(timer_animation#*0.1),8)
									combat_place=t
									If combat_from<>0
										DrawImage fight_ring_3,ai,bi,frame
									 	If keys(1,2)=50
											ai=gr\formation[combat_from]
											gr\formation[combat_from]=gr\formation[combat_place]
											gr\formation[combat_place]=ai	
											mess$=gr\num+"#"
											For k=1 To 9
												mess$=mess$+gr\formation[k]+"#"
											Next
											analyse(19,mess$,player_id)	
											combat_from=0
											combat_target=0
										EndIf
									EndIf
								EndIf

								If gr\formation[t]<>0
									For av.avatar=Each avatar
										If av\num=gr\formation[t]
											If target_from=0 And MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80
												combat_target=t
												If keys(1,2)=50 Then combat_from=t
											EndIf
											If t=combat_target Or t=combat_from
												frame=reste(Int(timer_animation#*0.1),8)
												DrawImage fight_ring_1,ai,bi,frame
											EndIf
											frame=0
											frame=reste(Int(timer_animation#*0.1),7)
											If frame>3 Then frame=6-frame
											If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
											If Not(av\pv[1]>0) Then av\animation=4
											DrawImage fighters_gfx(av\cat,2),ai,bi,frame
											SetFont middle_font
											Color 255,255,255
											Text ai,bi+20,av\name$[Int(options#(7))],1,1
										EndIf
									Next
								Else
									Color 255,255,255
									Oval ai-50,bi-25,100,50,0
								EndIf
							Next
						Next						
					
						If combat_from=0
							mult_mess$(1)="Choisissez un personnage"
							mult_mess$(2)="Choose a character"
							mess_action$=mult_mess$(Int(options#(7)))
						ElseIf combat_from>0
							mult_mess$(1)="Choisissez sa nouvelle place"
							mult_mess$(2)="Choose their new place"
							mess_action$=mult_mess$(Int(options#(7)))
						Else
							mult_mess$(1)="Vous devez être le leader du groupe pour modifier la formation"
							mult_mess$(2)="Somehow this warning made its way to the final version. Shouldn't have happened."
							mess_action$=mult_mess$(Int(options#(7)))
						EndIf
						
						ai=max(Len(mess_action$)*8,200)
						Color 0,0,0
						Rect (screenwidth-ai)*0.5-4+75,(500-10)-4,ai+8,20+8,1
						Color 180,180,180
						Rect (screenwidth-ai)*0.5-3+75,(500-10)-3,ai+6,20+6,0							
						SetFont middle_font
						Text screenwidth*0.5+75,500,mess_action$,1,1
				
					Case 4 ; Quête
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495
						Color 0,0,0
						Rect 165,115,307,440,0							
						SetFont big_font
						For butin.butin=Each butin
							If butin\num=1	
								;afficher la liste des objets
								good=1
								nb_objets=-1
								name$=""
								quest_item_num=0
								For t=1 To LIMITE_QUEST
									If butin\quest[t]=0 And nb_objets=-1 Then nb_objets=t-1
								Next
								If nb_objets=-1 Then nb_objets=LIMITE_QUEST
								mult_mess$(1)="Objets de Quête et Informations ("+nb_objets+")"
								mult_mess$(2)="Key Items and Intel ("+nb_objets+")"
								Text 318,97,mult_mess$(Int(options#(7))),1,1
								If MouseX()>165 And MouseX()<470 And MouseY()>115
									aide_contextuelle$(1)="Cliquer sur un objet pour en savoir plus."
									aide_contextuelle$(2)="Click on an item to display more informations."
									degre_roulette#=degre_roulette#-sourisz#
									degre_roulette#=maxf(0,minf(degre_roulette#,maxf(Ceil(nb_objets*0.2-7),0)))
								EndIf
								
								roulette=Int(degre_roulette#)
	
								If roulette=0
									Rect 165+2,115+2,307-4,15-4,0
								Else
									Rect 165+2,115+2,307-4,15-4,1
								EndIf
								
								If roulette=maxf(Ceil(nb_objets*0.2-7),0)
									Rect 165+2,555-15+2,307-4,15-4,0
								Else
									Rect 165+2,555-15+2,307-4,15-4,1
								EndIf
							
								For u=0 To 8 ; ligne
									For v=0 To 5 ; colonne
										t=roulette*6+u*6+v+1
										If t<LIMITE_QUEST+1
											If butin\quest[t]<>0
												icone=0
												For q_i.quest_item=Each quest_item
													If q_i\num=butin\quest[t]
														icone=q_i\icone[1]
														If inventaire_cible=t
															name$=q_i\name$[Int(options#(7))]
															description$=q_i\description$[Int(options#(7))]
															If q_i\shareable=1
																mult_mess$(1)="Information"
																mult_mess$(2)="Intel"
																cat$=mult_mess$(Int(options#(7)))
															ElseIf q_i\shareable=0
																mult_mess$(1)="Objet de Quête"
																mult_mess$(2)="Key Item"
																cat$=mult_mess$(Int(options#(7)))
															ElseIf q_i\shareable=-2
																mult_mess$(1)="Mission terminée"
																mult_mess$(2)="Completed mission"
																cat$=mult_mess$(Int(options#(7)))
															Else
																mult_mess$(1)="Mission"
																mult_mess$(2)="Mission"
																cat$=mult_mess$(Int(options#(7)))
															EndIf
															icone2=q_i\icone[2]
															quest_item_num=q_i\num
														EndIf
													EndIf
												Next
																								
												If icone=0
													DrawBlock non_icone,(165+9+46*v),(115+17+46*u)
												Else
													DrawBlock icone,(165+9+46*v),(115+17+46*u)
												EndIf
												
												If MouseX()>(165+9+46*v) And MouseX()<(165+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
													Rect (165+9+46*v)-2,(115+17+46*u)-2,44,44,0
													If keys(1,2)=50
														keys(1,2)=49
														inventaire_cible=t
													EndIf
												EndIf
											EndIf
										EndIf
									Next
								Next
								
								;afficher les infos
								Color 5,5,5
								Rect 490,115,300,385,0
								
								If name$<>""
									Rect 575-2,130-2,130+4,75+4,0
									If icone2<>0
										DrawBlock icone2,575,130
									Else
										DrawBlock non_icone2,575,130
									EndIf
									SetFont little_font
									Text 500,225,name$
									Text 500,242,"("+cat$+")"
									
									For t=1 To 15
										disc_ligne(t)=""
									Next
									mess$=description$
									t=0
									k=0
									amax=Len(mess$)	
									max_ligne=Int(50)
									For a_int=1 To amax
										t=t+1
										If Mid$(mess$,t,1)="#"
											k=k+1
											disc_ligne$(k)=Left$(mess$,t-1)
											mess$=Right$(mess$,Len(mess$)-t)
											t=0
										EndIf
										If t=max_ligne
											t=lastspace(Left$(mess$,max_ligne),max_ligne)
											k=k+1
											If k>15
												mess$=""
											Else
												disc_ligne$(k)=Left$(mess$,t-1)
												mess$=Right$(mess$,Len(mess$)-t)
												t=0
											EndIf
										EndIf
									Next
									If mess$<>""
										k=k+1
										disc_ligne$(k)=mess$
									EndIf
									For t=1 To 15
										disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
										Text 500,(250+15*t),disc_ligne$(t)
									Next
								EndIf						
							EndIf
						Next
					
					Case 5 ; Radio / Journal
						;DrawImageRect fond_book,160,80,0,0,screenwidth-165,495
						Color 0,5,0
						Rect 160,20,screenwidth-165,555
						SetFont little_font
						;Color 180,180,180
						For t=1 To 38
							If log_mess$(t,2)<>""
								Color log_color(t,1),log_color(t,2),log_color(t,3)
								If log_mess$(t,2)<>"#" Then Text 180,560-t*14,"["+log_mess$(t,2)+"]"
								Text 242,560-t*14,log_mess$(t,1)
							EndIf
						Next
						Color 255,255,255
						msg_radio$=input_text(msg_radio$)
						If Floor(timer_animation#*0.05) Mod 2 = 0
							char$="#"
						Else
							char$="_"
						EndIf
						Text 180,screenheight-40,"["+CurrentTime()+"]"
						Text 180+80*screeny,screenheight-40,msg_radio$+char$
						
						If keys(14,2)=50 Or keys(87,2)=50
							mult_mess$(1)="Vous : "
							mult_mess$(2)="You: "
							new_log(mult_mess(Int(options#(7)))+msg_radio$,50,50,255)
							msg_radio$=""	
						EndIf

	
					Case 6 ; Système
					
					;sauvegarde
					Color 0,0,0
					ai=max(Len("Sauvegarder")*8,200)
					frame=reste(Int(timer_animation#*0.1),8)
					
					mult_mess$(1)="* Sauvegarder *"
					mult_mess$(2)="* Save *"
					DrawImageRect fond_book,(screenwidth-ai)*0.5-4+75,(200-20)-4,0,0,ai+8,40+8
					Rect (screenwidth-ai)*0.5-3+75,(200-20)-3,ai+6,40+6,0							
					SetFont big_font
					Text screenwidth*0.5+75,200,mult_mess$(Int(options#(7))),1,1
					
					If MouseX()>(screenwidth-ai)*0.5-4+75 And MouseX()<(screenwidth-ai)*0.5+79+ai And MouseY()>176 And MouseY()<224
						aide_contextuelle$(1)="Cliquer ici pour sauvegarder. Attention : il n'y a qu'un seul fichier de sauvegarde"
						aide_contextuelle$(2)="Save the current game. Warning: there is currently only one savefile."
						DrawImage little_wheel,(screenwidth-ai)*0.5+75+10,190,frame
						DrawImage little_wheel,(screenwidth+ai)*0.5+75-30,190,frame
						If keys(1,2)=50
							save()
						EndIf
					EndIf
									
					
					;Options					
					mult_mess$(1)="* Options *"
					mult_mess$(2)="* Settings *"					
					DrawImageRect fond_book,(screenwidth-ai)*0.5-4+75,(300-20)-4,0,0,ai+8,40+8
					Rect (screenwidth-ai)*0.5-3+75,(300-20)-3,ai+6,40+6,0							
					SetFont big_font
					Text screenwidth*0.5+75,300,mult_mess$(Int(options#(7))),1,1
					
					If MouseX()>(screenwidth-ai)*0.5-4+75 And MouseX()<(screenwidth-ai)*0.5+79+ai And MouseY()>276 And MouseY()<324
						aide_contextuelle$(1)="Modifier les options"
						aide_contextuelle$(2)="Modify settings"
						DrawImage little_wheel,(screenwidth-ai)*0.5+75+10,290,frame
						DrawImage little_wheel,(screenwidth+ai)*0.5+75-30,290,frame
						If keys(1,2)=50
							menu_option()
						EndIf
					EndIf
					
					
					;Quitter et revenir au menu principal
					mult_mess$(1)="* Quitter *"
					mult_mess$(2)="* Quit *"
					DrawImageRect fond_book,(screenwidth-ai)*0.5-4+75,(400-20)-4,0,0,ai+8,40+8
					Rect (screenwidth-ai)*0.5-3+75,(400-20)-3,ai+6,40+6,0							
					SetFont big_font
					Text screenwidth*0.5+75,400,mult_mess$(Int(options#(7))),1,1
					
					If MouseX()>(screenwidth-ai)*0.5-4+75 And MouseX()<(screenwidth-ai)*0.5+79+ai And MouseY()>376 And MouseY()<424
						aide_contextuelle$(1)="Quitter cette partie et revenir au menu principal"
						aide_contextuelle$(2)=""
						DrawImage little_wheel,(screenwidth-ai)*0.5+75+10,390,frame
						DrawImage little_wheel,(screenwidth+ai)*0.5+75-30,390,frame
						If keys(1,2)=50
							sortie_menu=revenir_au_menu()
						EndIf
					EndIf
					
					
					
					Case 7 ; Aide
					
					Case 8 ; Retour
						sortie_menu=1
				End Select
			EndIf
		Next
		
		Color 255,255,255
		SetFont little_font
		Text 160,587,aide_contextuelle$(Int(options#(7))),0,1

		If msg_erreur_timing>0 And msg_erreur$<>""
			msg_erreur_timing=msg_erreur_timing-1
			SetFont middle_font
			a_int=StringWidth(msg_erreur$)+20
			b_int=StringHeight(msg_erreur$)+20
			drawgrey2((800-a_int)*0.5,(400-b_int)*0.5,a_int,b_int)
			Text 400,200,msg_erreur$,1,1
		EndIf
		
		DrawImage curseur,MouseX(),MouseY()
		;If KeyHit(01) Then End
		
		Flip
		compensation_lag()
	Wend
	If mode_de_jeu=3 Then mode_de_jeu=1
	reinit_keyboard()
	reverser_butin(-1,1)
	stack_butin(1,1)
	MouseYSpeed()
	MouseXSpeed()
Return


.marchand
aff_load_menu(0)
	PauseChannel ch_bgm1
;	If sound_marchand=0 
;		sound_marchand=LoadSound("musiques\forgeron.mp3")
;		LoopSound sound_marchand
;		ch_marchand=PlaySound(sound_marchand)
;	Else
;		ResumeChannel ch_marchand
;	EndIf
;	ChannelVolume ch_marchand,0.5*options#(5)
	;l'intégrer dans le système de musique ?
	; mouais, tel que je l'ai je ne peux pas faire de pause et on ne peut de toutes façons pas changer le volume pdt qu'on est chez le marchand
	
	stack_butin(-14,1)
	stack_butin(-13,1)
	stack_butin(-12,1)
	stack_butin(-1,1)
	stack_butin(1,1)
	
	;; Vérifier la formation
	pl_groupe_num=var_pl_grp(num_player,"num")
	For gr.groupe=Each groupe
		If gr\num=pl_groupe_num
			For k=1 To 8
				For i=k+1 To 9
					If gr\formation[k]=gr\formation[i]
						gr\formation[k]=0
					EndIf
				Next
			Next
			;vérifier que tous ceux de la formation appartiennent bien au groupe
			For k=1 To 9
				av_exist=0
				For av.avatar=Each avatar
					If av\num=gr\formation[k]
						av_exist=1
						If av\groupe<>gr\num Then gr\formation[k]=0
					EndIf
				Next
				If av_exist=0 Then gr\formation[k]=0
			Next
			;vérifier que tous ceux qui appartiennent au groupe sont dans la formation
			For av.avatar=Each avatar
				If av\groupe=gr\num
					good=0
					For k=1 To 9
						If gr\formation[k]=av\num Then good=1
					Next
					If good=0 ; mettre dans la première case vide
						For k=1 To 9
							If good=0 And gr\formation[k]=0 Then gr\formation[k]=av\num:good=1
						Next
					EndIf
				EndIf
			Next
		EndIf
	Next	
	
	;;load les items du menu
		; les onglets et le curseur
	If gfx_onglets(2,Int(options#(7)))=0
		Select Int(options#(7))
			Case 1
				gfx_onglets(2,Int(options#(7)))=LoadImage("sprites\menu\onglets2.png")
			Case 2
				gfx_onglets(2,Int(options#(7)))=LoadImage("sprites\menu\onglets2_en.png")
		End Select
	EndIf
	If gfx_curseur_onglets=0
		gfx_curseur_onglets=LoadImage("sprites\menu\curseur_onglets.bmp")
		;ScaleImage gfx_curseur_onglets,screenxf#,screenyf#
	EndIf
	If gfx_cuir=0 Then gfx_cuir=LoadImage("textures\loran\fond-cuir-noir.jpg")
	If fond_book=0 
		fond_book=LoadImage("sprites\menu\fond_book.jpg")
		;ScaleImage fond_book,screenxf#,screenyf#
	EndIf
	If gfx_signet=0 Then gfx_signet=LoadImage("sprites\menu\marque-page.bmp")
	If gfx_signet_sombre=0 Then gfx_signet_sombre=LoadImage("sprites\menu\marque-page_sombre.bmp")
	If non_icone=0 Then non_icone=LoadImage("sprites\objets\non_icone_1.jpg")
	If non_icone2=0 Then non_icone2=LoadImage("sprites\objets\non_icone_2.jpg");ScaleImage non_icone2,screenyf#,screenyf#
	aff_load_menu(0.3)
	If gfx_xiao=0 
		gfx_xiao=LoadImage("sprites\objets\xiao.bmp")
		;ScaleImage gfx_xiao,screenyf#,screenyf#
	EndIf
	If face_inventaire=0
		face_inventaire=LoadImage("sprites\menu\face_inventaire.png")
	EndIf
	If bouton_panel_1=0
		bouton_panel_1=LoadAnimImage("sprites\objets\panel-1.bmp",100,50,0,2)
		MidHandle bouton_panel_1
		ScaleImage bouton_panel_1,0.8,0.8
	EndIf
	If bouton_panel_2=0
		bouton_panel_2=LoadAnimImage("sprites\objets\panel-2.bmp",100,50,0,2)
		MidHandle bouton_panel_2
		ScaleImage bouton_panel_2,0.8,0.8
	EndIf
	
	msg_erreur$=""
	msg_erreur_timing=0

		; les combattants
	For t=1 To 18
		fighters_utilises(t)=0
	Next
	ai=0
	For av.avatar=Each avatar
		If av\groupe=pl_groupe_num
			good=0
			For t=1 To 18
				If fighters_utilises(t)=av\cat Then good=1
			Next
			If good=0
				ai=ai+1
				fighters_utilises(ai)=av\cat
			EndIf
		EndIf
	Next
	
	aff_load_menu(0.5)
	For t=1 To ai
		load_combattant(fighters_utilises(t))
		aff_load_menu(0.5+t/(Float(ai))*0.5)
	Next
		
	aff_load_menu(1)

	onglet=4
	sortie_menu=0
	cible=1
	pos_finale=(onglet-1)*75
	pos_curseur=pos_finale
	avatar_num=-1
	cible_equi=0
	cible_up=0
	cible=0
	degre_roulette#=0
	degre_roulette2#=0
	
	While sortie_menu=0
		start_loop()
		lire_clavier()
		If mode_de_jeu=2 Then sortie_menu=1
		
		aide_contextuelle$(Int(options#(7)))=""
			
		If MouseDown(02) or keys(90,2)=50 Then sortie_menu=1:keys(90,2)=min(49,keys(90,2))
		sourisz#=MouseZSpeed()
		
		;draw le fond
		DrawImage gfx_cuir,0,0
		
		;draw les onglets
		pos_finale=(onglet-1)*75
		If pos_curseur<>pos_finale
			vitesse=max(1,min(0.1*Abs(pos_curseur-pos_finale),40))
			If pos_curseur>pos_finale
				pos_curseur=pos_curseur-vitesse
			Else
				pos_curseur=pos_curseur+vitesse			
			EndIf
		EndIf
		DrawImage gfx_onglets(2,Int(options#(7))),0,0
		DrawImage gfx_curseur_onglets,0,pos_curseur
		
		color 0,0,0
		setfont middle_font
		text 55,178,player_caps
		text 55,196,player_junk
		
		;selection des onglets
		If MouseX()<150
			For t=1 To 8
				If MouseY()<t*75 And MouseY()>(t-1)*75
					good=0
					Select t
						Case 4
							aide_contextuelle$(1)="Acheter de l'équipement.":good=1
							aide_contextuelle$(2)="Buy equipment for your team."
						Case 5
							aide_contextuelle$(1)="Vendre ce que vous avez dans votre inventaire.":good=1
							aide_contextuelle$(2)="Sell items in your inventory."
						Case 6
							aide_contextuelle$(1)="Améliorer votre équipement (« Plus y'a de boulons, moins t'as l'air con ! »).":good=1
							aide_contextuelle$(2)="Upgrade your equipment (''The more bolts, the less dolt you look.'')."
						Case 8	
							aide_contextuelle$(1)="Quitter la boutique du Forgeron.":good=1
							aide_contextuelle$(2)="Exit the blacksmith's shop."
					End Select
					If keys(1,2)=50 And good=1
						If onglet<>t
							If t<8
								Playsound2(sons_menu(5))
							Else
								Playsound2(sons_menu(6))
							EndIf
							reverser_butin(-13,1)
							degre_roulette#=0
							degre_roulette2#=0
						EndIf
						onglet=t
						inventaire_cible=0
						stack_butin(1,1)
						stack_butin(-1,1)
						stack_butin(-12,1)
						stack_butin(-13,1)
						stack_butin(-14,1)
					EndIf
				EndIf
			Next
		EndIf
		
		;action des onglets
		For gr.groupe=Each groupe
			If gr\num=pl_groupe_num
				If cible<10
					good=0
					While good=0
						If cible<10
							If gr\formation[cible]=0
								cible=cible+1
							Else
								good=1
							EndIf
						Else
							good=1
						EndIf
					Wend
				EndIf
				
				If cible>10 Then cible=1
					
				Select onglet	
					Case 4 ; Acheter
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495				
						Color 5,5,5
						;afficher la vitrine
						inventaire_cible=0
						SetFont big_font
						mult_mess$(1)="Vitrine"
						mult_mess$(2)="Shop"
						Text 170,100,mult_mess$(Int(options#(7))),0,1
						Rect 170,115,150,440,0 
						For butin.butin=Each butin
							If butin\num=-12
								;comptez le nombre d'objets dans l'inventaire
								nb_objets=-1
								For t=1 To 250
									If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
								Next
								If nb_objets=-1 Then nb_objets=250
								If MouseX()>170 And MouseX()<320 And MouseY()>115 and mouseY()<565
									aide_contextuelle$(1)="Utilisez la roulette pour faire défiler la vitrine "+Abs(inventaire_cible)+"/"+nb_objets+"."
									aide_contextuelle$(2)="Use the mouse wheel to scroll through the shop "+Abs(inventaire_cible)+"/"+nb_objets+"."

									degre_roulette#=degre_roulette#-sourisz#
									degre_roulette#=maxf(0,minf(degre_roulette#,Ceil(nb_objets*0.333-9)))
								EndIf
								
								roulette=Int(degre_roulette#)
								
								If roulette=0
									Rect 170+2,115+2,150-4,15-4,0
								Else
									Rect 170+2,115+2,150-4,15-4,1
								EndIf
								
								If roulette=max(Ceil(nb_objets*0.333-9),0)
									Rect 170+2,555-15+2,150-4,15-4,0
								Else
									Rect 170+2,555-15+2,150-4,15-4,1
								EndIf
							
								For u=0 To 8 ; ligne
									For v=0 To 2 ; colonne
										t=roulette*3+u*3+v+1
										If t<251
											If butin\loot[t]<>0
												icone=0
												If butin\loot[t]<100
													For arme.arme=Each arme
														If arme\num=butin\loot[t]
															icone=arme\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<150
													For armure.armure=Each armure
														If armure\num=butin\loot[t]
															icone=armure\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<200
													For boiler.boiler=Each boiler
														If boiler\num=butin\loot[t]
															icone=boiler\icone[1]
														EndIf
													Next
												Else
													For special.special=Each special
														If special\num=butin\loot[t]
															icone=special\icone[1]
														EndIf
													Next
												EndIf
												
												If icone=0
													DrawBlock non_icone,(170+9+46*v),(115+17+46*u)
												Else
													DrawBlock icone,(170+9+46*v),(115+17+46*u)
												EndIf
												
												If MouseX()>(170+9+46*v) And MouseX()<(170+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
													Rect (170+9+46*v)-2,(115+17+46*u)-2,44,44,0
													inventaire_cible=t
													If keys(1,2)=50
														keys(1,2)=49
														Playsound2(sons_menu(6))
														;faire passer dans le panier
														item_give(-14,butin\loot[t])
														butin\loot[t]=0
														stack_butin(-14)
														stack_butin(-12)
													EndIf
												EndIf
											EndIf
										EndIf
									Next
								Next
							EndIf
						Next
						;afficher le panier
						mult_mess$(1)="Panier"
						mult_mess$(2)="Shopping cart"
						Text 330,100,mult_mess$(Int(options#(7))),0,1
						rect 330,115,150,350,0 
						For butin.butin=Each butin
							If butin\num=-14
								;caps=0
								;comptez le nombre d'objets dans l'inventaire
								nb_objets=-1
								For t=1 To 250
									If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
								Next
								If nb_objets=-1 Then nb_objets=250
								If MouseX()>330 And MouseX()<480 And MouseY()>115 and mouseY()<565
									aide_contextuelle$(1)="Utilisez la roulette pour faire défiler le panier "+Abs(inventaire_cible)+"/"+nb_objets+"."
									aide_contextuelle$(2)="Use the mouse wheel to scroll through the shopping cart "+Abs(inventaire_cible)+"/"+nb_objets+"."
									degre_roulette2#=degre_roulette2#-sourisz#
									degre_roulette2#=maxf(0,minf(degre_roulette2#,Ceil(nb_objets*0.3333-7)))
								EndIf
								
								roulette2=Int(degre_roulette2#)
								
								If roulette2=0
									Rect 330+2,115+2,150-4,15-4,0
								Else
									Rect 330+2,115+2,150-4,15-4,1
								EndIf
								
								If roulette2=max(Ceil(nb_objets*0.3333-7),0)
									Rect 330+2,465-15+2,150-4,15-4,0
								Else
									Rect 330+2,465-15+2,150-4,15-4,1
								EndIf
							
								For u=0 To 6 ; ligne
									For v=0 To 2 ; colonne
										t=roulette2*3+u*3+v+1
										If t<251
											If butin\loot[t]<>0
												icone=0
												If butin\loot[t]<100
													For arme.arme=Each arme
														If arme\num=butin\loot[t]
															icone=arme\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<150
													For armure.armure=Each armure
														If armure\num=butin\loot[t]
															icone=armure\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<200
													For boiler.boiler=Each boiler
														If boiler\num=butin\loot[t]
															icone=boiler\icone[1]
														EndIf
													Next
												Else
													For special.special=Each special
														If special\num=butin\loot[t]
															icone=special\icone[1]
														EndIf
													Next
												EndIf
												
												If icone=0
													DrawBlock non_icone,(330+9+46*v),(115+17+46*u)
												Else
													DrawBlock icone,(330+9+46*v),(115+17+46*u)
												EndIf
												
												If MouseX()>(330+9+46*v) And MouseX()<(330+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
													Rect (330+9+46*v)-2,(115+17+46*u)-2,44,44,0
													inventaire_cible=-t
													If keys(1,2)=50
														keys(1,2)=49
														Playsound2(sons_menu(6))
														;faire passer dans le panier
														item_give(-12,butin\loot[t])
														butin\loot[t]=0
														stack_butin(-14)
														stack_butin(-12)
													EndIf
												EndIf
											EndIf
										EndIf
									Next
								Next
							EndIf
						Next
						;afficher les stats
						rect 490,115,300,440,0
						nb_rules=0
						If inventaire_cible<>0
							If inventaire_cible>0
								butin_cible=-12
							Else
								butin_cible=-14
							EndIf
							good=0
							For butin.butin=Each butin
								If butin\num=butin_cible And good=0
									good=1
									mult_mess$(1)="Acheter"
									mult_mess$(2)="Buy"
									aff_info_equipement(butin\loot[Abs(inventaire_cible)],mult_mess$(Int(options#(7))))
								EndIf
							Next
						EndIf
						Color 5,5,5
						;bouton Acheter
						Rect 330,475,150,80,0
						setfont big_font
						caps=prix(-14,1)
						caps=caps*COEFF_MARCHAND
						mult_mess$(1)="Acheter ("+caps+" caps)"
						mult_mess$(2)="Buy ("+caps+" caps)"
						Text 405,515,mult_mess$(Int(options#(7))),1,1
						If MouseX()>330 And MouseX()<480 And MouseY()>475 And MouseY()<555
							aide_contextuelle$(1)="Acheter les objets sur le comptoir."
							aide_contextuelle$(2)="Buy all the items in the shopping cart"
							If keys(1,2)=50
								keys(1,2)=49
								reponse=0
								If player_caps>caps-1
									While reponse=0
										start_loop()
										lire_clavier()
										disc_len#=10000
										color 255,255,255
										mult_mess$(1)="Voulez vous vraiment acheter ces objets ? Vous pouvez aussi garder vos caps pour vous rouler dedans quand vous aurez plus de temps."
										mult_mess$(2)="Do you really want to buy these items? You can also keep your caps and roll yourself into it when you'll have more time."
										fenetre_info(mult_mess$(Int(options#(7))),0,100,0)
										;reponse=fenetreqcm(2,"Acheter ?","Oui","Non")
										mult_mess$(1)="Acheter ?"
										mult_mess$(2)="Buy?"
										reponse=fenetreyn(mult_mess$(Int(options#(7))))
										DrawImage curseur,MouseX(),MouseY()
										Flip
										compensation_lag()
									Wend
								Else
									Playsound2(sons_menu(2))
									mult_mess$(1)="Pas assez de Caps"
									mult_mess$(2)="Not enough caps"
									msg_erreur$=mult_mess$(Int(options#(7)))
									msg_erreur_timing=90
								EndIf
								If reponse=1
									For panier.butin=Each butin
										If panier\num=-14
											;compter le nombre d'objets
											nb_objets=0
											For t=1 To 250
												If panier\loot[t]<>0 Then nb_objets=nb_objets+1
											Next
											;compter la place restante
											nb_restant=0
											for butin.butin=Each butin
												If butin\num=1
													;Playsound2(sons_menu(7))
													For t=1 to 250
														if butin\loot[t]=0 Then nb_restant=nb_restant+1
													Next
												EndIf
											Next
											;y'a-t-il assez de place ?
											If nb_restant<nb_objets ; non
												Playsound2(sons_menu(2))
												While reponse2=0
													start_loop()
													lire_clavier()
													disc_len#=10000
													color 255,255,255
													mult_mess$(1)="Vous n'avez pas assez de place dans votre inventaire pour acheter tous ces objets ("+Str(nb_objets-nb_restant)+" objets en trop).#Vendez ou recyclez d'abord des objets dont vous ne voulez plus, ou achetez moins."
													mult_mess$(2)="You don't have enough room in your inventory for all the items in the shopping cart ("+Str(nb_objets-nb_restant)+" items too many).#Sell or recycle first a few indesired items in your inventory, ou buy less."
													reponse2=fenetre_info(mult_mess$(Int(options#(7))),0,100,1)
													DrawImage curseur,MouseX(),MouseY()
													Flip
													compensation_lag()
												Wend
											Else ; oui
												Playsound2(sons_menu(7))
												For t=1 to 250
													If panier\loot[t]<>0
														item_give(1,panier\loot[t])
														panier\loot[t]=0
													EndIf
												Next
												stack_butin(1,1)
												player_caps=player_caps-caps
												caps=0
											EndIf
										EndIf
									Next
								EndIf
							EndIf
						EndIf
						
					Case 5 ; Vendre
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495				
						Color 5,5,5
						;afficher la vitrine
						inventaire_cible=0
						setfont big_font
						mult_mess$(1)="Inventaire"
						mult_mess$(2)="Inventory"
						Text 170,100,mult_mess$(Int(options#(7))),0,1
						Rect 170,115,150,440,0 
						For butin.butin=Each butin
							If butin\num=1
								;comptez le nombre d'objets dans l'inventaire
								nb_objets=-1
								For t=1 To 250
									If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
								Next
								If nb_objets=-1 Then nb_objets=250
								If MouseX()>170 And MouseX()<320 And MouseY()>115 and mouseY()<565
									aide_contextuelle$(1)="Utilisez la roulette pour faire défiler l'inventaire "+Abs(inventaire_cible)+"/"+nb_objets
									aide_contextuelle$(2)="Use the mouse wheel to scroll through the inventory "+Abs(inventaire_cible)+"/"+nb_objets
									degre_roulette#=degre_roulette#-sourisz#
									degre_roulette#=maxf(0,minf(degre_roulette#,Ceil(nb_objets*0.333-9)))
								EndIf
								
								roulette=Int(degre_roulette#)
								
								If roulette=0
									Rect 170+2,115+2,150-4,15-4,0
								Else
									Rect 170+2,115+2,150-4,15-4,1
								EndIf
								
								If roulette=max(Ceil(nb_objets*0.333-9),0)
									Rect 170+2,555-15+2,150-4,15-4,0
								Else
									Rect 170+2,555-15+2,150-4,15-4,1
								EndIf
							
								For u=0 To 8 ; ligne
									For v=0 To 2 ; colonne
										t=roulette*3+u*3+v+1
										If t<251
											If butin\loot[t]<>0
												icone=0
												If butin\loot[t]<100
													For arme.arme=Each arme
														If arme\num=butin\loot[t]
															icone=arme\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<150
													For armure.armure=Each armure
														If armure\num=butin\loot[t]
															icone=armure\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<200
													For boiler.boiler=Each boiler
														If boiler\num=butin\loot[t]
															icone=boiler\icone[1]
														EndIf
													Next
												Else
													For special.special=Each special
														If special\num=butin\loot[t]
															icone=special\icone[1]
														EndIf
													Next
												EndIf
												
												If icone=0
													DrawBlock non_icone,(170+9+46*v),(115+17+46*u)
												Else
													DrawBlock icone,(170+9+46*v),(115+17+46*u)
												EndIf
												
												If MouseX()>(170+9+46*v) And MouseX()<(170+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
													Rect (170+9+46*v)-2,(115+17+46*u)-2,44,44,0
													inventaire_cible=t
													If keys(1,2)=50
														keys(1,2)=49
														Playsound2(sons_menu(6))
														;faire passer dans le comptoir
														item_give(-13,butin\loot[t])
														butin\loot[t]=0
														stack_butin(-13)
														stack_butin(1)
													EndIf
												EndIf
											EndIf
										EndIf
									Next
								Next
							EndIf
						Next
						;afficher le comptoir
						mult_mess$(1)="Comptoir"
						mult_mess$(2)="Counter"
						Text 330,100,mult_mess$(Int(options#(7))),0,1
						Rect 330,115,150,350,0 
						For butin.butin=Each butin
							If butin\num=-13
								caps=0
								;comptez le nombre d'objets dans l'inventaire
								nb_objets=-1
								For t=1 To 250
									If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
								Next
								If nb_objets=-1 Then nb_objets=250
								If MouseX()>330 And MouseX()<480 And MouseY()>115 and mouseY()<565
									aide_contextuelle$(1)="Utilisez la roulette pour faire défiler le comptoir "+Abs(inventaire_cible)+"/"+nb_objets
									aide_contextuelle$(2)="Use the mouse wheel to scroll through the counter "+Abs(inventaire_cible)+"/"+nb_objets
									degre_roulette2#=degre_roulette2#-sourisz#
									degre_roulette2#=maxf(0,minf(degre_roulette2#,Ceil(nb_objets*0.3333-7)))
								EndIf
								
								roulette2=Int(degre_roulette2#)
								
								If roulette2=0
									Rect 330+2,115+2,150-4,15-4,0
								Else
									Rect 330+2,115+2,150-4,15-4,1
								EndIf
								
								If roulette2=max(Ceil(nb_objets*0.3333-7),0)
									Rect 330+2,465-15+2,150-4,15-4,0
								Else
									Rect 330+2,465-15+2,150-4,15-4,1
								EndIf
							
								For u=0 To 6 ; ligne
									For v=0 To 2 ; colonne
										t=roulette2*3+u*3+v+1
										If t<251
											If butin\loot[t]<>0
												icone=0
												If butin\loot[t]<100
													For arme.arme=Each arme
														If arme\num=butin\loot[t]
															icone=arme\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<150
													For armure.armure=Each armure
														If armure\num=butin\loot[t]
															icone=armure\icone[1]
														EndIf
													Next
												ElseIf butin\loot[t]<200
													For boiler.boiler=Each boiler
														If boiler\num=butin\loot[t]
															icone=boiler\icone[1]
														EndIf
													Next
												Else
													For special.special=Each special
														If special\num=butin\loot[t]
															icone=special\icone[1]
														EndIf
													Next
												EndIf
												
												If icone=0
													DrawBlock non_icone,(330+9+46*v),(115+17+46*u)
												Else
													DrawBlock icone,(330+9+46*v),(115+17+46*u)
												EndIf
												
												If MouseX()>(330+9+46*v) And MouseX()<(330+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
													Rect (330+9+46*v)-2,(115+17+46*u)-2,44,44,0
													inventaire_cible=-t
													If keys(1,2)=50
														keys(1,2)=49
														Playsound2(sons_menu(6))
														;faire passer dans le panier
														item_give(1,butin\loot[t])
														butin\loot[t]=0
														stack_butin(-13)
														stack_butin(1)
													EndIf
												EndIf
											EndIf
										EndIf
									Next
								Next
							EndIf
						Next
						;afficher les stats
						rect 490,115,300,440,0
						nb_rules=0
						If inventaire_cible<>0
							If inventaire_cible>0
								butin_cible=1
							Else
								butin_cible=-13
							EndIf
							good=0
							For butin.butin=Each butin
								If butin\num=butin_cible And good=0
									good=1
									aff_info_equipement(butin\loot[Abs(inventaire_cible)],"Vendre")
								EndIf
							Next
						EndIf												
						
						Color 5,5,5
						;bouton Vendre
						Rect 330,475,150,80,0
						setfont big_font
						caps=prix(-13,1)
						caps=Ceil(caps/Float(COEFF_MARCHAND))
						mult_mess$(1)="Vendre ("+caps+")"
						mult_mess$(2)="Sell ("+caps+")"
						Text 405,515,mult_mess$(Int(options#(7))),1,1
						If MouseX()>330 And MouseX()<480 And MouseY()>475 And MouseY()<555
							aide_contextuelle$(1)="Vendre les objets sur le comptoir."
							aide_contextuelle$(2)="Sell all items on the counter."
							If keys(1,2)=50
								keys(1,2)=49
								reponse=0
								While reponse=0
									start_loop()
									lire_clavier()
									disc_len#=10000
									color 255,255,255
									mult_mess$(1)="Voulez vous vraiment vendre ces objets ? Vous pouvez aussi les recycler en [junk] pour améliorer votre équipement."
									mult_mess$(2)="Sell all these items? You can also recycle them for [junk] to upgrade your equipment."
									fenetre_info(mult_mess$(Int(options#(7))),0,100,0)
									mult_mess$(1)="Vendre ?"
									mult_mess$(2)="Sell?"
									reponse=fenetreqcm(2,mult_mess$(Int(options#(7))),oui(Int(options#(7))),non(Int(options#(7))))
									DrawImage curseur,MouseX(),MouseY()
									Flip
									compensation_lag()
								Wend
								If reponse=1
									For panier.butin=Each butin
										If panier\num=-13
											;compter le nombre d'objets
											Playsound2(sons_menu(7))
											For t=1 to 250
												If panier\loot[t]<>0
													a_int=item_give(-12,panier\loot[t])
													If a_int<>0
														panier\loot[t]=0 ; mis correctement dans la vitrine
													else
														panier\loot[t]=0 ; détruit. Faire qqchose avec ?
														;SOALT : chercher l'objet ayant le moins de valeur dans l'inventaire du marchand, et le remplacer par celui-là (ssi il est plus cher)
													endif
												EndIf
											Next
											stack_butin(-12,1)
											player_caps=player_caps+caps
											caps=0
										EndIf
									Next
								EndIf
							EndIf
						EndIf
						
					Case 6 ; Améliorer
						DrawImageRect fond_book,160,80,0,0,screenwidth-165,495
						; onglets têtes
						k=0
						For t=1 To 10
							If t=cible
								k=k+1
								If t<10
									DrawImage gfx_signet,(155+64*(k-1))+13,5
								Else
									DrawImage gfx_signet,(155+64*(9))+13,5
								EndIf
							Else
								If t<10
									If gr\formation[t]<>0 
										k=k+1
										DrawImage gfx_signet_sombre,(155+64*(k-1))+13,5
									Endif
								Else
									k=k+1
									DrawImage gfx_signet_sombre,(155+64*(9))+13,5
								EndIf
							EndIf
							If MouseY()<75
								If MouseX()>(155+64*(k-1)) And MouseX()<(155+64*(k)) And keys(1,2)=50 And t<10
									If gr\formation[t]<>0 And cible<>t Then Playsound2(sons_menu(6)):cible=t:cible_equi=0:cible_up=0									
								EndIf
								If MouseX()>(155+64*9) And MouseX()<(155+640) And keys(1,2)=50
									If cible<>10 Then Playsound2(sons_menu(6)):cible=10:cible_equi=0:cible_up=0
								EndIf
							EndIf
							If t<10
								If gr\formation[t]<>0
									For av.avatar=Each avatar
										If av\num=gr\formation[t]
											If av\num>3
												If fighters_tete(av\cat,1)<>0 Then DrawBlock fighters_tete(av\cat,1),(155+64*(k-1))+18,18
											Else
												DrawBlock PJ_small(av\num),(155+64*(k-1))+18,18
											EndIf
										EndIf
									Next
								EndIf
							Else
								DrawImage face_inventaire,(155+64*(9))+18,18
							EndIf
						Next
							
						Color 5,5,5
						If cible<10
							If gr\formation[cible]<>0
								For av.avatar=Each avatar
									If av\num=gr\formation[cible]
										For t=1 To 3
											upgrade(t,1)=0
											upgrade(t,2)=0
										Next
									
										SetFont middle_font
										Color 0,0,0
										Text 180,110,av\name$[Int(options#(7))]
										mult_mess$(1)="Junk : " ; "Junk disponible : "
										mult_mess$(2)="Junk: "
										Text 180,150,mult_mess$(Int(options#(7)))+player_junk
										
										;dessiner les boutons des panels
										Rect 165,195,200,360,0						
										
										;dessiner le bonhomme
										DrawImage gfx_xiao,225,210
											
										;dessiner les armes
										For t=1 To 3
											Color 0,0,0
											Rect 180-2,(170+t*65)-2,44,44,0
											If cible_equi=t And av\equi[t]<>0
												Color 180,0,0
												Rect 180-2,(170+t*65)-2,44,44,1
											EndIf
											If av\equi[t]<>0
												For arme.arme=Each arme
													If arme\num=av\equi[t]
														If arme\icone[1]<>0
															DrawBlock arme\icone[1],180,(170+t*65)
														Else
															DrawBlock non_icone,180,(170+t*65)
														EndIf
														If t=cible_equi
															For up=1 To 3
																upgrade(up,1)=arme\upgrade[up]
																upgrade(up,2)=arme\up_cost[up]
															Next
														EndIf															
													EndIf
												Next
											EndIf
											If MouseX()<220 And MouseX()>180 And MouseY()>(170+t*65) And MouseY()<(225+t*65)
												aide_contextuelle$(1)="Arme"
												aide_contextuelle$(2)="Weapon"
												If t=1
													aide_contextuelle$(1)="Arme équipée"
													aide_contextuelle$(2)="Equiped Weapon"
												EndIf
												Color 180,0,0
												Rect 180-4,(170+t*65)-4,48,48,0
												If keys(1,2)=50
													If cible_equi<>t Then Playsound2(sons_menu(6))
													cible_equi=t
													cible_up=0
												EndIf
											EndIf
										Next
											
										;dessiner l'armure
										If cible_equi=4 And av\equi[4]<>0
											Color 180,0,0
											Rect 255-2,295-2,44,44,1
										Else
											Color 0,0,0
											Rect 255-2,295-2,44,44,0
										EndIf
										If av\equi[4]<>0
											For armure.armure=Each armure
												If armure\num=av\equi[4]
													If armure\icone[1]<>0
														DrawBlock armure\icone[1],255,295
													Else
														DrawBlock non_icone,255,295
													EndIf
													If cible_equi=4
														For up=1 To 3
															upgrade(up,1)=armure\upgrade[up]
															upgrade(up,2)=armure\up_cost[up]
														Next
													EndIf
												EndIf
											Next
										EndIf
										If MouseX()>255 And MouseX()<295 And MouseY()>295 And MouseY()<335
											aide_contextuelle$(1)="Armure"
											aide_contextuelle$(2)="Armour"
											Color 180,0,0
											Rect 255-4,295-4,48,48,0
											If keys(1,2)=50
												If cible_equi<>4 Then Playsound2(sons_menu(6))
												cible_equi=4
												cible_up=0
											EndIf
										EndIf

										;dessiner la chaudière
										If cible_equi=5 And av\equi[5]<>0
											Color 180,0,0
											Rect 310-2,295-2,44,44,1
										Else
											Color 0,0,0
											Rect 310-2,295-2,44,44,0
										EndIf
										If av\equi[5]<>0
											For boiler.boiler=Each boiler
												If boiler\num=av\equi[5]
													If boiler\icone[1]<>0
														DrawBlock boiler\icone[1],310,295
													Else
														DrawBlock non_icone,310,295
													EndIf
													If cible_equi=5
														For up=1 To 3
															upgrade(up,1)=boiler\upgrade[up]
															upgrade(up,2)=boiler\up_cost[up]
														Next
													EndIf
												EndIf
											Next
										EndIf
										If MouseX()>310 And MouseX()<350 And MouseY()>295 And MouseY()<335
											aide_contextuelle$(1)="Chaudière"
											aide_contextuelle$(2)="Boiler"
											Color 180,0,0
											Rect 310-4,295-4,48,48,0
											If keys(1,2)=50
												If cible_equi<>5 Then Playsound2(sons_menu(6))
												cible_equi=5
												cible_up=0
											EndIf
										EndIf

										;dessiner les spéciaux
																					
										;dessiner les améliorations possibles
										good=0 ; faire bouger le curseur d'upgrade à une position possible
										While good=0
											If cible_up>0 And cible_up<4
												If upgrade(cible_up,1)=0
													cible_up=cible_up-1
													If cible_up<0 Then cible_up=0:good=1
												Else
													good=1
												EndIf
											Else
												cible_up=0
												good=1
											EndIf
										Wend
										;Compter le nombre d'upgrade possible
										nb_upgrade=0
										For t=1 to 3
											If upgrade(t,1)<>0 Then nb_upgrade=nb_upgrade+1
										Next

										Color 0,0,0
										Rect 375,115,105,440,0
										d_int=Ceil(440/Float(nb_upgrade+1))
										
										For up=1 To nb_upgrade
											If upgrade(up,1)<>0
												Color 0,0,0
												Rect 405,93+d_int*up,44,44,0
												If MouseX()>405 And MouseX()<450 And MouseY()>93+d_int*up And MouseY()<137+d_int*up
													Color 180,0,0
													Rect 405,93+d_int*up,44,44,1
													If keys(1,2)=50
														If cible_up<>up Then Playsound2(sons_menu(6))
														keys(1,2)=49
														cible_up=up
													EndIf
												EndIf
													
												If up=cible_up
													Color 180,0,0
													Rect 405,93+d_int*up,44,44,1
												EndIf
													
												If upgrade(up,1)<100 ; arme
													For arme.arme=Each arme
														If arme\num=upgrade(up,1)
															If arme\icone[1]<>0
																DrawBlock arme\icone[1],407,95+d_int*up
															Else
																DrawBlock non_icone,407,95+d_int*up
															EndIf
														EndIf
													Next
												ElseIf upgrade(up,1)<150 ; armure
													For armure.armure=Each armure
														If armure\num=upgrade(up,1)
															If armure\icone[1]<>0
																DrawBlock armure\icone[1],407,95+d_int*up
															Else
																DrawBlock non_icone,407,95+d_int*up
															EndIf
														EndIf
													Next
												ElseIf upgrade(up,1)<200 ; boiler
													For boi.boiler=Each boiler
														If boi\num=upgrade(up,1)
															If boi\icone[1]<>0
																DrawBlock boi\icone[1],407,95+d_int*up
															Else
																DrawBlock non_icone,407,95+d_int*up
															EndIf
														EndIf
													Next
												EndIf
											EndIf		
										Next							
																		
										;afficher les infos
										Color 5,5,5
										Rect 490,115,300,440,0
										If cible_equi<1 Then cible_up=0
										If cible_equi>0 And cible_up=0
											aff_info_equipement(av\equi[cible_equi])
										ElseIf cible_up>0
											;upgrade possibles
											aff_info_equipement(upgrade(cible_up,1))																				
											;bouton upgrade !
											SetFont big_font
											Color 0,0,0
											Rect 495,505,290,45,0
											mult_mess$(1)="Améliorer ("+upgrade(cible_up,2)+" junk)"
											mult_mess$(2)="Upgrade ("+upgrade(cible_up,2)+" junk)"
											Text 636,527,mult_mess$(Int(options#(7))),1,1
											If MouseX()>495 And MouseX()<785 And MouseY()>505 And MouseY()<550
												aide_contextuelle$(1)="Améliorer l'équipement en l'option sélectionnée."
												aide_contextuelle$(2)="Upgrade the equipment into the selected option."
												If keys(1,2)=50
													keys(1,2)=49
													If upgrade(cible_up,2)<player_junk+1
														;tester pour la vapeur
														old_equipement=av\equi[cible_equi]
														av\equi[cible_equi]=upgrade(cible_up,1)
														If calcul_pression(av\num)
															calcul_bonus_equi(av\num)
															old_bonus_pv=bonus_equi(10)
															player_junk=player_junk-upgrade(cible_up,2)
															av\equi[cible_equi]=upgrade(cible_up,1)
															calcul_bonus_equi(av\num)
															new_bonus_pv=bonus_equi(10)
															If av\pv[1]>0 Then av\pv[1]=max(av\pv[1]+(new_bonus_pv-old_bonus_pv),1)
															Playsound2(sons_menu(9))
														Else
															av\equi[cible_equi]=old_equipement
															Playsound2(sons_menu(2))
															;pas assez de pression ("Ta chaudière est pas assez costaude pour ça !")
															mult_mess$(1)="Pas assez de vapeur. Déséquipez avant d'améliorer ou équipez une chaudière plus puissante"
															mult_mess$(2)="Not enough steam. Unequipe the item before the upgrade, or use a more powerful boiler."
															msg_erreur$=mult_mess$(int(options#(7)))
															msg_erreur_timing=90
														Endif		
													Else
														Playsound2(sons_menu(2))
														;pas assez de junk ("Il me faut plus de pièces détachées si tu veux que je l'améliore !")
														mult_mess$(1)="Pas assez de Junk"
														mult_mess$(2)="Not enough Junk"
														msg_erreur$=mult_mess$(Int(options#(7)))
														msg_erreur_timing=90
													EndIf
												EndIf
											EndIf
										EndIf																			
									EndIf
								Next
							EndIf							
						Else ;Amélioration dans Inventaire
							For butin.butin=each butin
								If butin\num=1
									For t=1 To 3
										upgrade(t,1)=0
										upgrade(t,2)=0
									Next
									SetFont middle_font
									Color 0,0,0
									;dessiner les boutons des panels
									Rect 170,115,190,440,0
									
									;dessiner l'inventaire
									mult_mess$(1)="Inventaire"
									mult_mess$(2)="Inventory"
									Text 170,100,mult_mess$(Int(options#(7))),0,1
									caps=0
									;comptez le nombre d'objets dans l'inventaire
									nb_objets=-1
									For t=1 To 250
										If butin\loot[t]=0 And nb_objets=-1 Then nb_objets=t-1
									Next
									If nb_objets=-1 Then nb_objets=250
									If MouseX()>170 And MouseX()<360 And MouseY()>115 and MouseY()<565
										aide_contextuelle$(1)="Utilisez la roulette pour faire défiler l'inventaire "+Abs(cible_equi)+"/"+nb_objets+"."
										aide_contextuelle$(2)="Use the mouse wheel to scroll through the inventory "+Abs(cible_equi)+"/"+nb_objets+"."
										degre_roulette2#=degre_roulette2#-sourisz#
										degre_roulette2#=maxf(0,minf(degre_roulette2#,Ceil(nb_objets*0.25-9)))
									EndIf
									
									roulette2=Int(degre_roulette2#)
									
									If roulette2=0
										Rect 170+2,115+2,190-4,15-4,0
									Else
										Rect 170+2,115+2,190-4,15-4,1
									EndIf
									
									If roulette2=max(Ceil(nb_objets*0.25-7),0)
										Rect 170+2,555-15+2,190-4,15-4,0
									Else
										Rect 170+2,555-15+2,190-4,15-4,1
									EndIf
								
									For u=0 To 8 ; ligne
										For v=0 To 3 ; colonne
											t=(roulette2+u)*4+v+1
											If t<251
												If butin\loot[t]<>0
													icone=0
													If butin\loot[t]<100
														For arme.arme=Each arme
															If arme\num=butin\loot[t]
																icone=arme\icone[1]
																If t=cible_equi
																	For k=1 To 3
																		upgrade(k,1)=arme\upgrade[k]
																		upgrade(k,2)=arme\up_cost[k]
																	Next
																EndIf
															EndIf
														Next
													ElseIf butin\loot[t]<150
														For armure.armure=Each armure
															If armure\num=butin\loot[t]
																icone=armure\icone[1]
																If t=cible_equi
																	For k=1 To 3
																		upgrade(k,1)=armure\upgrade[k]
																		upgrade(k,2)=armure\up_cost[k]
																	Next
																EndIf
															EndIf
														Next
													ElseIf butin\loot[t]<200
														For boiler.boiler=Each boiler
															If boiler\num=butin\loot[t]
																icone=boiler\icone[1]
																If t=cible_equi
																	For k=1 To 3
																		upgrade(k,1)=boiler\upgrade[k]
																		upgrade(k,2)=boiler\up_cost[k]
																	Next
																EndIf
															EndIf
														Next
													Else
														For special.special=Each special
															If special\num=butin\loot[t]
																icone=special\icone[1]
																If t=cible_equi
																	For k=1 To 3
																		upgrade(k,1)=special\upgrade[k]
																		upgrade(k,2)=special\up_cost[k]
																	Next
																EndIf
															EndIf
														Next
													EndIf
													
													If cible_equi=t
														Color 180,0,0
														Rect (167+9+46*v)-2,(115+17+46*u)-2,44,44,1
														Color 0,0,0
													EndIf
													
													If icone=0
														DrawBlock non_icone,(167+9+46*v),(115+17+46*u)
													Else
														DrawBlock icone,(167+9+46*v),(115+17+46*u)
													EndIf
													
													If MouseX()>(167+9+46*v) And MouseX()<(167+9+46*v)+40 And MouseY()>(115+17+46*u) And MouseY()<(115+17+46*u)+40
														Rect (167+9+46*v)-2,(115+17+46*u)-2,44,44,0
														If keys(1,2)=50
															keys(1,2)=49
															Playsound2(sons_menu(6))
															cible_equi=t
															cible_up=0
														EndIf
													EndIf
												EndIf
											EndIf
										Next
									Next

									;dessiner les améliorations possibles
									good=0 ; faire bouger le curseur d'upgrade à une position possible
									While good=0
										If cible_up>0 And cible_up<4
											If upgrade(cible_up,1)=0
												cible_up=cible_up-1
												If cible_up<0 Then cible_up=0:good=1
											Else
												good=1
											EndIf
										Else
											cible_up=0
											good=1
										EndIf
									Wend
									;Compter le nombre d'upgrade possible
									nb_upgrade=0
									For t=1 to 3
										If upgrade(t,1)<>0 Then nb_upgrade=nb_upgrade+1
									Next

									Color 0,0,0
									Rect 375,115,105,440,0
									d_int=Ceil(440/Float(nb_upgrade+1))
									
									For up=1 To nb_upgrade
										If upgrade(up,1)<>0
											Color 0,0,0
											Rect 405,93+d_int*up,44,44,0
											If MouseX()>405 And MouseX()<450 And MouseY()>93+d_int*up And MouseY()<137+d_int*up
												Color 180,0,0
												Rect 405,93+d_int*up,44,44,1
												If keys(1,2)=50
													If cible_up<>up Then Playsound2(sons_menu(6))
													keys(1,2)=49
													cible_up=up
												EndIf
											EndIf
												
											If up=cible_up
												Color 180,0,0
												Rect 405,93+d_int*up,44,44,1
											EndIf
												
											If upgrade(up,1)<100 ; arme
												For arme.arme=Each arme
													If arme\num=upgrade(up,1)
														If arme\icone[1]<>0
															DrawBlock arme\icone[1],407,95+d_int*up
														Else
															DrawBlock non_icone,407,95+d_int*up
														EndIf
													EndIf
												Next
											ElseIf upgrade(up,1)<150 ; armure
												For armure.armure=Each armure
													If armure\num=upgrade(up,1)
														If armure\icone[1]<>0
															DrawBlock armure\icone[1],407,95+d_int*up
														Else
															DrawBlock non_icone,407,95+d_int*up
														EndIf
													EndIf
												Next
											ElseIf upgrade(up,1)<200 ; boiler
												For boi.boiler=Each boiler
													If boi\num=upgrade(up,1)
														If boi\icone[1]<>0
															DrawBlock boi\icone[1],407,95+d_int*up
														Else
															DrawBlock non_icone,407,95+d_int*up
														EndIf
													EndIf
												Next
											EndIf
										EndIf		
									Next							
																	
									;afficher les infos
									Color 5,5,5
									Rect 490,115,300,440,0
									If cible_equi<1 Then cible_up=0
									If cible_equi>0 And cible_up=0
										aff_info_equipement(butin\loot[cible_equi])
									ElseIf cible_up>0
										;upgrade possibles
										aff_info_equipement(upgrade(cible_up,1))
																					
										;bouton upgrade !
										SetFont big_font
										Color 0,0,0
										Rect 495,505,290,45,0
										mult_mess$(1)="Améliorer ("+upgrade(cible_up,2)+" junk)"
										mult_mess$(2)="Upgrade ("+upgrade(cible_up,2)+" junk)"
										Text 636,527,mult_mess$(Int(options#(7))),1,1
										If MouseX()>495 And MouseX()<785 And MouseY()>505 And MouseY()<550
											aide_contextuelle$(1)="Améliorer l'équipement en l'option sélectionnée."
											aide_contextuelle$(2)="Upgrade the equipment into the selected option."
											If keys(1,2)=50
												keys(1,2)=49
												If upgrade(cible_up,2)<player_junk+1
													player_junk=player_junk-upgrade(cible_up,2)
													butin\loot[cible_equi]=upgrade(cible_up,1)
													Playsound2(sons_menu(9))
												Else
													Playsound2(sons_menu(2))
													mult_mess$(1)="Pas assez de Junk"
													mult_mess$(2)="Not enough Junk"
													msg_erreur$=mult_mess$(Int(options#(7)))
													msg_erreur_timing=90
												EndIf
											EndIf
										EndIf
									EndIf											
								EndIf		
							Next
						EndIf
					Case 8 ; Retour
						sortie_menu=1
				End Select
			EndIf
		Next
		
		Color 255,255,255
		SetFont little_font
		Text 165,587,aide_contextuelle$(Int(options#(7))),0,1
		
		If msg_erreur_timing>0 And msg_erreur$<>""
			msg_erreur_timing=msg_erreur_timing-1
			SetFont middle_font
			a_int=StringWidth(msg_erreur$)+20
			b_int=StringHeight(msg_erreur$)+20
			drawgrey2((800-a_int)*0.5,(400-b_int)*0.5,a_int,b_int)
			Text 400,200,msg_erreur$,1,1
		EndIf
		
		DrawImage curseur,MouseX(),MouseY()
		;If KeyHit(01) Then End
		
		Flip
		compensation_lag()
	Wend
	If mode_de_jeu=4 Then mode_de_jeu=1
	reverser_butin(-13,1)
	stack_butin(1,1)
	MouseYSpeed()
	MouseXSpeed()
	ResumeChannel ch_bgm1
	If ch_marchand<>0 Then PauseChannel ch_marchand
Return


.retour_menu
; a enlever
;End
StopChannel ch_bmg
If bgm<>0 Then FreeSound bgm:bgm=0
clean_world()
clean_universe()
Goto menu_principal