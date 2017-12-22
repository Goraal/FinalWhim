Function fn_combat()
	UpdateWorld
	RenderWorld
	If g_bHUDactif=1 Then HUD()
	Flip
	SaveBuffer(FrontBuffer(),"temp.bmp")
	image_load_combat=LoadImage("temp.bmp")
	
	decalage_hud=0;15
	
	FlushKeys
	aff_load_combat(0.1)
	sortie_combat=-1
	;combat_vainqueur=0
	;load le son
	PauseChannel ch_bgm1
	if options#(5)>0
		For combat.combat=Each combat
			If combat\num=num_combat
				num_battle = abs(combat\groupe[1]*combat\groupe[2])
				Select num_battle
					case 202 ; combat boss final
						mus_fight=LoadSound("musiques\noise attack.mp3")
					default
						mus_fight=LoadSound("sons\battle08.mp3")
				end select
			EndIf
		Next	
		LoopSound mus_fight
		ch_fight=PlaySound(mus_fight)
		ChannelVolume ch_fight,0.5*options#(5)
	endif
	;;chargement en fonction des infos
	Select player_map
		case 1
			background=LoadImage("sprites\background\default.jpg")
		case 2
			background=LoadImage("sprites\background\MAP02.jpg")
		case 4
			background=LoadImage("sprites\background\MAP04.jpg")
		Default
			background=LoadImage("sprites\background\default.jpg")
	End Select
	
	
	If combat_roue=0 Then combat_roue=LoadImage("sprites\Menu\combat_roue.png"):MidHandle combat_roue
	If combat_barre_pleine=0 Then combat_barre_pleine=LoadImage("sprites\Menu\combat_barre_pleine.png")
	If combat_barre_vide=0 Then combat_barre_vide=LoadImage("sprites\Menu\combat_barre_vide.png")
	If combat_barretexte=0 Then combat_barretexte=LoadImage("sprites\Menu\combat_barretexte.png")
	;; load des permanents
	
	aff_load_combat(0.3)
	
	For combat.combat=Each combat
		If combat\num=num_combat
			;test				
			For t=1 To 18
				fighters_utilises(t)=0
			Next
			ai=0
			For av.avatar=Each avatar
				If av\groupe=combat\groupe[1] Or av\groupe=combat\groupe[2]
					av\animation=1
					If av\prop=1
						;charger les armes
						For t=1 To 3
							If av\equi[t]<>0
								For arme.arme=Each arme
									If arme\num=av\equi[t]
										av\charge[t]=arme\charge
									EndIf
								Next
							EndIf
						Next
					EndIf
				
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
		
			For t=1 To ai
				load_combattant(fighters_utilises(t))
				aff_load_combat(0.3+t/(Float(ai))*0.5)
			Next
			
			;attente pré-combat

			time_depart=MilliSecs()
			FlushKeys
			action=0	
			While action=0
				start_loop()
				If KeyHit(keys(12,1)) Then action=1
				;A enlever
				If mode_debug=1 And KeyDown(keys(35,1))
					action=1
					For av.avatar=Each avatar
						If av\groupe=combat\groupe[1]
							av\pv[1]=-1							
						EndIf
					Next
				EndIf
				;;
				aff_load_combat(1,(Cos(timer_animation#*10)+2)*0.33)
				compensation_lag()
			Wend
			
			combat_menu_action=0
			temps_restant=TEMPS_ROUND
			frame_a=MilliSecs()
		EndIf
	Next
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;boucle combat;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	sortie_combat=0
	While sortie_combat=0
		start_loop()
		lire_clavier()
		call_serveur()
		Cls							
		DrawImage background,0,0
		
		

		
		;aff des combattants haut
		For t=1 To 9
			inactif_under_control(t)=0
		Next
		
		For combat.combat=Each combat
			If combat\num=num_combat
				;; afficher l'ordre
				type_ordre=0
				;compter combien d'actif
				; et cherche si les inactifs appartiennent au joueur
				actifs=0
				inactifs=0
				type_ordre=0
				inactif_controled=0
				For t=1 To 19
					If combat\ordre[t]=0
						type_ordre=1
					Else
						If type_ordre=0
							actifs=actifs+1
						Else
							inactifs=inactifs+1
							For av.avatar=Each avatar
								If av\num=combat\ordre[t]
									If av\prop=1 And av\pv[1]>0
										inactif_controled=inactif_controled+1
										inactif_under_control(inactif_controled)=av\num
									EndIf
								EndIf
							Next							
						EndIf
					EndIf
				Next
				
				Color 255,255,255
				Rect 400-Floor(actifs*0.5)*30-14,30-14,29,29,0
				
				;HUD combat.LBA : à faire aussi en EN !
				;image de l'armure et arme sous hud fond...
				If icone_armor<>0 then DrawImage icone_armor,691,526
				If icone_arme<>0 then DrawImage icone_arme,67,527
				;HUD fond
				DrawImage fond_Combat,0,417
				;PJ actif :
				t=combat\qui
				For av.avatar=Each avatar
					If av\num=combat\ordre[t]
						
						If av\equi[1]=0
								DebugLog("Main nue")
								;LBA cheat arme 1
								av\equi[1]=8
						Endif
						
						For arme.arme=Each arme
							If arme\num=av\equi[1]
								;image de l'arme sous hud fond...
								icone_arme=arme\icone[1]
								
								
								Color 0,0,0
								SetFont little_font
								Text 95,444,av\name$[Int(options#(7))],true,true
								
								
								;(1=mains nues, 2=légère, 3=lourde, 4=distance, 5=à feu)
								;transformer {1,2,3,4,5} en {1,1,2,3,3}
								style_att=min(3,max(1,arme\classe-1))
								dgts_min=arme\degat_min+av\deg[style_att]+bonus_equi(6+style_att)
								dgts_max=arme\degat_max+av\deg[style_att]+bonus_equi(6+style_att)
								dgts_arme$ = Str$(dgts_min)+"-"+Str$(dgts_max)
								Text 135,582,dgts_arme$,true,true
																												
								bonusAttaque = arme\att[style_att] + av\att[style_att]
								Text 44,582,"1D20+"+Str$(bonusAttaque),true,true
								;tête
								If fighters_tete(av\cat,1)<>0 Then DrawBlock fighters_tete(av\cat,1),5,426
								
								
								If Int(options#(7))=1 ;français
									Text 38,513,"Attaque",true,true
									Text 137,513,"Dégâts",true,true
									;Type d'attaque
									If style_att=1 Then nomAttaque$ = "Attaque arme legère"
									If style_att=2 Then nomAttaque$ = "Attaque arme lourde"
									If style_att=3 Then nomAttaque$ = "Attaque à distance"
								Else ;anglais par défaut
									Text 38,513,"Attack",true,true
									Text 137,513,"Damage",true,true
									;Type d'attaque
									If style_att=1 Then nomAttaque$ = "Light weapon attack"
									If style_att=2 Then nomAttaque$ = "Heavy weapon attack"
									If style_att=3 Then nomAttaque$ = "Range attack"	
								Endif
								Text 88,480,nomAttaque$,true,true
							EndIf
						Next
						
					EndIf
					
				;Cible :
					If combat_target=0 then icone_armor = 0
					If av\num=combat_target
						TargetHasArmor=0
						For armure.armure=Each armure
							If armure\num=av\equi[4]
								
								icone_armor = armure\icone[1]						
								bonusResistance = armure\def[style_att] + av\def[style_att]
								bonusArmure$ = Str$(Int(armure\val#[1]))+"/"+Str$(Int(armure\val#[2]))+"/"+Str$(Int(armure\val#[3]))
								TargetHasArmor=1
							EndIf
						Next
						
						Color 0,0,0
						SetFont little_font
						Text 699,444,av\name$[Int(options#(7))],true,true
						
						
										
						;tête
						If fighters_tete(av\cat,1)<>0 Then DrawBlock fighters_tete(av\cat,1),750,426
						
						;armor
						if TargetHasArmor=0
							bonusResistance=0
							bonusArmure$="0/0/0"
							;LBA cheat armure 1
							av\equi[4]=102
							
						Endif
						Text 666,582,Str$(bonusResistance),true,true
						Text 762,582,Str$(bonusArmure$),true,true
						
						If Int(options#(7))=1 ;français
							Text 664,513,"Défense",true,true
							Text 760,513,"Armure",true,true
							;Type de def
							If style_att=1 Then nomDefense$ = "Défense legère"
							If style_att=2 Then nomDefense$ = "Défense lourde"
							If style_att=3 Then nomAttaque$ = "Défense à distance"
						Else ;anglais par défaut
							Text 664,513,"Defense",true,true
							Text 760,513,"Armor",true,true
							;Type de def
							If style_att=1 Then nomAttaque$ = "Light defense"
							If style_att=2 Then nomAttaque$ = "Heavy defense"
							If style_att=3 Then nomAttaque$ = "Range defense"	
						Endif
						Text 710,480,nomDefense$,true,true
					Endif
				Next					
				;fin HUD combat
				
				
				If combat_temp_anim<0 ; agir
					If combat_vainqueur<>0
						If combat_vainqueur=-1
							sortie_combat=1
						Else
							sortie_combat=2
						EndIf
					EndIf
								
					type_ordre=0
					For t=1 To 19
						If combat\ordre[t]=0 Then type_ordre=1
						If type_ordre=0 ; ceux dans la chaine
							For av.avatar=Each avatar
								If av\num=combat\ordre[t]
									If t<combat\qui
										DrawBlock fight_faces,400+((t+actifs-combat\qui)-Floor(actifs*0.5))*30-12,30-12,av\cat-1							
									Else
										DrawBlock fight_faces,400+((t-combat\qui)-Floor(actifs*0.5))*30-12,30-12,av\cat-1
									EndIf
								EndIf
							Next
						ElseIf type_ordre=1 ; ceux qui attendent
							For av.avatar=Each avatar
								If av\num=combat\ordre[t]
									DrawBlock fight_faces_sombres,400+((19-t)-Floor(inactifs*0.5))*30-12,60-12,av\cat-1
								EndIf
							Next
						EndIf
					Next
			
			;		Color 0,0,0
			;		Rect 0,0,50,80
			;		Color 255,255,255
			;		Text 5,5,"Act : "+combat_menu_action + " , "+num_combat
			;		Text 5,20,"Actif : "+actifs
			;		Text 5,35,"Ina : "+inactifs
			;		Text 5,50,"Qui : "+combat\qui
			;		Text 5,65,"Tem : "+Str(temps_restant)
			;		For t=1 To 19
			;			Text 5,5+15*t,combat\ordre[t]
			;		Next
					
					combat_place=0
					combat_reussite=0
					combat_from=0
					combat_target=0
								
					;; afficher les persos
					For groupe.groupe=Each groupe
						If groupe\num=combat\groupe[1] ; groupe de gauche (ennemis)
							For i=0 To 2 ; ligne
								For j=1 To 3 ; colonne
									t=i*3+j	
									If groupe\formation[t]<>0
										For av.avatar=Each avatar
											If Not(av\pv[1]>0)
												;If DEFENSE_REGEN_ACTIVE=1
				;euh, pk c'est là ??			;	av\defense=max(min(min(av\defense+DEFENSE_REGEN,DEFENSE_MAX_REGEN),MAX_BONUS_DEF),MIN_BONUS_DEF)
												;EndIf
												av\animation=4
												av\defense=0
											EndIf
											If av\num=groupe\formation[t]
												ai=(100*(3-j)+50)
												bi=(100*i+175)
												; DebugLog "test_H:"+ImageHeight(fight_ring_1)
												If combat\ordre[combat\qui]=av\num
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_1,ai,bi,frame
												EndIf
												If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80 And target_actif=1
													combat_target=av\num
												EndIf
												If av\num=combat_target
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_2,ai,bi,frame
												EndIf
	
												;petite correction pour le boss : à améliorer = casser ce qui a été fait ^^
												If av\cat=17; Systeme de Sécurité
													ai = ai - (ImageWidth(fighters_gfx(av\cat,1)) - ImageWidth(fight_ring_1)) * 0.5 ;
													bi = bi - (ImageHeight(fighters_gfx(av\cat,1)) ) * 0.5 ;
												EndIf
												;fin correction
												
												frame=0
												Select av\animation
													Case 3 ; defensive
														frame=8
													Case 4 ; mort
														frame=26
													Default ; also Case 1 idle
														frame=reste(Int(timer_animation#*0.1),6)
														If frame>3 Then frame=6-frame
														If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
														If Not(av\pv[1]>0) Then av\animation=4
												End Select
												
												
												DrawImage fighters_gfx(av\cat,1),ai,bi,frame
												;defense

												If av\defense<0
													ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
													di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
													For k=1 To Floor(Abs(av\defense*0.5))
														DrawImage gfx_bouclier,ci,di+18*k,1
													Next
												ElseIf av\defense>0
													ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
													di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
													For k=1 To Floor(av\defense*0.5)
														DrawImage gfx_bouclier,ci,di+18*k,0
													Next
												EndIf
												; name
												SetFont middle_font
												Color 255,255,255
												Text ai,bi+20,av\name$[Int(options#(7))],1,1

											EndIf
										Next
									EndIf
								Next	
							Next
						EndIf
						If groupe\num=combat\groupe[2] ; groupe de droite (grp_joueur)
							For i=0 To 2 ; ligne
								For j=1 To 3 ; colonne
									t=i*3+j
									ai=screenwidth-(100*(3-j)+50)
									bi=(100*i+175)
									If target_actif=2 And MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80
										frame=reste(Int(timer_animation#*0.1),8)
										combat_place=t
										DrawImage fight_ring_3,ai,bi,frame
									EndIf
									If groupe\formation[t]<>0
										For av.avatar=Each avatar
											If av\num=groupe\formation[t]
												If combat\ordre[combat\qui]=av\num
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_1,ai,bi,frame
												EndIf
												If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80
													If target_actif=1 Then combat_target=av\num
													If target_actif=3 Then combat_react=av\num
												EndIf
												If av\num=combat_target
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_2,ai,bi,frame
												EndIf
												If av\num=combat_react
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_4,ai,bi,frame
												EndIf
												If t=combat_place
													frame=reste(Int(timer_animation#*0.1),8)
													DrawImage fight_ring_3,ai,bi,frame
												EndIf
												frame=0
												Select av\animation
													Case 3 ; defensive
														frame=8
													Case 4 ; mort
														frame=26
													Default ; also Case 1 idle
														frame=reste(Int(timer_animation#*0.1),6)
														If frame>3 Then frame=6-frame
														If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
														If Not(av\pv[1]>0) Then av\animation=4
												End Select
												DrawImage fighters_gfx(av\cat,2),ai,bi,frame
												
												;defense
												If av\defense<0
													ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
													di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
													For k=1 To Floor(Abs(av\defense*0.5))
														DrawImage gfx_bouclier,ci,di+18*k,1
													Next
												ElseIf av\defense>0
													ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
													di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
													For k=1 To Floor(av\defense*0.5)
														DrawImage gfx_bouclier,ci,di+18*k,0
													Next
												EndIf
												
												;pv
												If av\prop=1
													calcul_bonus_equi(av\num)
													af#=av\pv[1]/Float(av\pv[2]+bonus_equi(10))
													Color 200,0,0
													ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
													height=ImageHeight(fighters_gfx(av\cat,1))*0.8
													Rect ci,bi-height,10,height
													Color 0,200,0
													Rect ci,bi-height*af#,10,height*af#
												EndIf
												;name
												SetFont middle_font
												Color 255,255,255
												Text ai,bi+20,av\name$[Int(options#(7))],1,1
											EndIf
										Next
									EndIf
								Next	
							Next
						EndIf
					Next
					
					target_actif=0
					mess_action$=""
					;;mettre ici la partie combat (duh !)
					For av.avatar=Each avatar
						If av\num=combat\ordre[combat\qui]
							If DEFENSE_REGEN_ACTIVE=1
								av\defense=max(min(min(av\defense+DEFENSE_REGEN,DEFENSE_MAX_REGEN),MAX_BONUS_DEF),MIN_BONUS_DEF)
							EndIf
							If av\prop=1 And av\pv[1]>0 And react_mode=0 And sortie_combat=0 ;si le joueur controle le gars, qu'il est encore vivant et qu'on est pas dans l'interface de réactivation
								av\animation=1
								If keys(2,2)=50
									combat_menu_action=Floor(Float(combat_menu_action)*0.1)
								EndIf
								
							;	If combat_menu_action>0
							;		a_int=400
							;		b_int=210
							;		DrawBlock bouton_combat,a_int,b_int,29
							;		If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25 And keys(1,2)=50 Then combat_menu_action=Floor(combat_menu_action*0.1)								
							;	EndIf
								
								If combat_menu_action<10 Then DrawImage combat_roue,400,213
								
								Select combat_menu_action
									Case 0 ; premier menu
										mess_action$="Choisissez votre action"

										For k=0 To 3
											a_int=400-Cos(90*k)*60
											b_int=(210-Sin(90*k)*60)
											DrawBlock bouton_combat,a_int,b_int,k
											;attaque cc grisée premier tour
											atd=0
											If av\equi[1]<>0
												For arme.arme=Each arme
													If arme\num=av\equi[1]
														For u=1 To 8
															If arme\rules[u]=100 Then atd=1
														Next
													EndIf
												Next
											EndIf
											if atd=0 and k=0
												If combat\phase<>2
													If have_rule(av\num,12)=0 ; pas de swift
														DrawBlock bouton_combat_sombre,a_int,b_int,27
													EndIf
												EndIf
											ElseIf atd=1 and k=0
												DrawBlock bouton_combat,a_int,b_int,20
											EndIf
											peut_agir=1
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														mess_action="Attaquer"
														If atd=0
															If combat\phase<>2
																If have_rule(av\num,12)=0 ; pas de swift
																	peut_agir=0
																	mess_action="Pas de corps à corps au premier tour"
																Else
																	mess_action="Attaquer (Swift)"
																EndIf
															EndIf
														EndIf
													Case 1
														mess_action="Défendre"
													Case 2
														mess_action="Ordre et Formation"
													Case 3
														mess_action="Inventaire"
												End Select
												If keys(1,2)=50 and peut_agir=1
													combat_menu_action=k+1
												EndIf
											EndIf
										Next
										
									Case 1 ; action d'attaque
										mess_action$="Choisissez votre attaque"
										For k=0 To 3
											a_int=400-Cos(90*k)*60
											b_int=(210-Sin(90*k)*60)
											DrawBlock bouton_combat_sombre,a_int,b_int,k
								
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														mess_action="Attaquer"
													Case 1
														mess_action="Défendre"
													Case 2
														mess_action="Ordre et Formation"
													Case 3
														mess_action="Inventaire"
												End Select
												If keys(1,2)=50
													combat_menu_action=k+1
												EndIf
											EndIf
										Next
										DrawBlock bouton_combat,400-60,210,0
										atd=0
										If av\equi[1]<>0
											For arme.arme=Each arme
												If arme\num=av\equi[1]
													For u=1 To 8
														If arme\rules[u]=100 Then atd=1
													Next
												EndIf
											Next
										EndIf
										If atd=1
											DrawBlock bouton_combat,400-60,210,20
										EndIf
										If av\equi[1]<>0
											For arme.arme=Each arme
												If arme\num=av\equi[1]
													For k=1 To 8
														If arme\rules[k]=100 Then atd=1
													Next
												EndIf
											Next
										EndIf
										
										If atd=0
											nb_bouton=2
											bouton_start=4
											For k=0 To nb_bouton-1
												a_int=400-(2)*60
												b_int=(210+(k-(nb_bouton*0.5-0.5))*60)
												DrawBlock bouton_combat,a_int,b_int,bouton_start+k
												
												If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
													Select k
														Case 0
															mess_action$="Attaque normale"
														Case 1
															mess_action$="Attaque Stratégique"
													End Select
													If keys(1,2)=50
														combat_menu_action=11+k
													EndIf
												EndIf
											Next
										Else
											For arme.arme=Each arme
												If arme\num=av\equi[1]
													charge_max=arme\charge
													cadence=arme\cadence
												EndIf
											Next
											If cadence>1
												nb_bouton=3
											Else
												nb_bouton=2
											EndIf
											bouton_start=20
											For k=0 To nb_bouton-1
												a_int=400-(2)*60
												b_int=(210+(k-(nb_bouton*0.5-0.5))*60)
												Select k
													Case 0
														If av\charge[1]=0
															DrawBlock bouton_combat_sombre,a_int,b_int,bouton_start+k
														Else
															DrawBlock bouton_combat,a_int,b_int,bouton_start+k
														EndIf
													Case 1
														If av\charge[1]=0
															DrawBlock bouton_combat_sombre,a_int,b_int,bouton_start+k
														Else
															DrawBlock bouton_combat,a_int,b_int,bouton_start+k
														EndIf
													Case 2
														If av\charge[1]>-1 And av\charge[1]<cadence
															DrawBlock bouton_combat_sombre,a_int,b_int,bouton_start+k
														Else
															DrawBlock bouton_combat,a_int,b_int,bouton_start+k
														EndIf
												End Select
												
												
												If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
													Select k
														Case 0
															mess_action$="Attaque normale (1/"+av\charge[1]+")"
														Case 1
															mess_action$="Attaque Stratégique (1/"+av\charge[1]+")"
														Case 2
															mess_action$="Rafale ("+cadence+"/"+av\charge[1]+")"
													End Select
													If keys(1,2)=50
														If k<2 
															If av\charge[1]<>0
																combat_menu_action=13+k
															Else
																PlaySound2(sons_menu(2))
															EndIf
														Else
															If av\charge[1]>-1 And av\charge[1]<cadence
																PlaySound2(sons_menu(2))
															Else
																combat_menu_action=15
															EndIf
														EndIf
													EndIf
												EndIf
											Next

										EndIf
	
									
									Case 2 ; action défensive
									;	mess_action$="Choisissez votre défense"
									;	For k=0 To 3
									;		a_int=400-Cos(90*k)*60
									;		b_int=(210-Sin(90*k)*60)
									;		DrawBlock bouton_combat_sombre,a_int,b_int,k
									;
									;		If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
									;			Select k
									;				Case 0
									;					mess_action="Attaquer"
									;				Case 1
									;					mess_action="Défendre"
									;				Case 2
									;					mess_action="Ordre et Formation"
									;				Case 3
									;					mess_action="Inventaire"
									;			End Select
									;			If keys(1,2)=50
									;				combat_menu_action=k+1
									;			EndIf
									;		EndIf
									;	Next
									;	DrawBlock bouton_combat,400,210-60,1
									;	nb_bouton=2
									;	For k=0 To nb_bouton-1
									;		a_int=400+(k-(nb_bouton*0.5-0.5))*60
									;		b_int=(210-2*60)
									;		DrawBlock bouton_combat,a_int,b_int,8+k
									;		
									;		If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
									;			Select k
									;				Case 0
									;					mess_action$="Position Défensive"
									;				Case 1
									;					mess_action$="Se rendre"
									;			End Select
									;			If keys(1,2)=50
									;				combat_menu_action=21;+k
									;			EndIf
									;		EndIf
									;	Next
										analyse(23,combat\num+"#"+av\num+"#21#s#s#s#s#s#s",player_id)
										av\last_action=21										
										;combat\qui=combat\qui+1
										If combat\ordre[combat\qui]=0 Then combat\qui=1
										combat_menu_action=0
										
									Case 4 ; action d'Inventaire
										mess_action$="Choisissez votre action"
										For k=0 To 3
											a_int=400-Cos(90*k)*60
											b_int=(210-Sin(90*k)*60)
											DrawBlock bouton_combat_sombre,a_int,b_int,k
											
											;attaque cc grisée premier tour
											atd=0
											If av\equi[1]<>0
												For arme.arme=Each arme
													If arme\num=av\equi[1]
														For u=1 To 8
															If arme\rules[u]=100 Then atd=1
														Next
													EndIf
												Next
											EndIf
											if atd=0 And k=0
												If combat\phase<>2
													If have_rule(av\num,12)=0 ; pas de swift
														DrawBlock bouton_combat_sombre,a_int,b_int,27
													EndIf
												EndIf
											ElseIf atd=1 And k=0
												DrawBlock bouton_combat_sombre,a_int,b_int,20
											EndIf
											peut_agir=1
											
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														mess_action="Attaquer"
														If atd=0
															If combat\phase<>2
																If have_rule(av\num,12)=0 ; pas de swift
																	peut_agir=0
																	mess_action="Pas de corps à corps au premier tour"
																Else
																	mess_action="Attaquer (Swift)"
																	If keys(1,2)=50 Then combat_menu_action=1
																EndIf
															Else
																If keys(1,2)=50 Then combat_menu_action=1
															EndIf
														Else
															If keys(1,2)=50 Then combat_menu_action=1
														EndIf
													Case 1
														mess_action="Défendre"
														If keys(1,2)=50 Then combat_menu_action=2
													Case 2
														mess_action="Ordre et Formation"
														If keys(1,2)=50 Then combat_menu_action=3
													Case 3
														mess_action="Inventaire"
														If keys(1,2)=50 Then combat_menu_action=4
												End Select
											EndIf
										Next
										
										DrawBlock bouton_combat,400,210+60,3
										nb_bouton=2
										If av\equi[6]=0
											test_gearbot=0
										Else
											num_bot=(av\equi[6]-180)*-1 ; passer de 201 à -21
											For av_bot.avatar=Each avatar
												If av_bot\num=num_bot
													If av_bot\groupe=-1
														test_gearbot=2
													Else
														test_gearbot=1
													EndIf
												EndIf
											Next											
										EndIf
										For k=0 To nb_bouton-1
											a_int=400+(k-(nb_bouton*0.5-0.5))*60
											b_int=(210+2*60)
											Select k
												Case 0
													Select test_gearbot
														Case 0 ;cas vide
															DrawBlock bouton_combat_sombre,a_int,b_int,6
														Case 1 ;cas prêt
															DrawBlock bouton_combat,a_int,b_int,6
														Case 2 ;cas déployé
															DrawBlock bouton_combat,a_int,b_int,7
													End Select
												Case 1
													DrawBlock bouton_combat,a_int,b_int,13
											End Select
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														Select test_gearbot
															Case 0
																mess_action$="Aucun Gearbot équipé"
															Case 1
																mess_action$="Déployer le Gearbot"
															Case 2
																mess_action$="Démonter le Gearbot"
														End Select
														If keys(1,2)=50
															If test_gearbot=1
																combat_menu_action=51
															ElseIf test_gearbot=2
																;envoyer l'ordre directement de ranger le bot (av\equi[6]-180)*-1
																analyse(23,combat\num+"#"+av\num+"#52#s#s#s#s#s#s#s#s#s#",player_id,master_id)
																av\last_action=52
																combat_menu_action=0
																;combat\qui=combat\qui+1
																If combat\ordre[combat\qui]=0 Then combat\qui=1
															EndIf
														Endif
													Case 1
														mess_action$="Changer d'arme"
														If keys(1,2)=50 Then combat_menu_action=42
												End Select
											EndIf
										Next
									
									Case 51 ;Déployer le GearBot
										mess_action$="Choisissez une place libre pour le GearBot"
										target_actif=2
										If keys(1,2)=50 And combat_place<>0
											place_libre=0
											For grp.groupe=Each groupe
												If grp\num=-1
													If grp\formation[combat_place]=0 Then place_libre=1
												EndIf
											Next
											If place_libre=0
												Playsound2(sons_menu(2))
											Else
												analyse(23,combat\num+"#"+av\num+"#51#"+combat_place+"#s#s#s#s#s#s#",player_id,master_id)
												av\last_action=51
												combat_menu_action=0
												combat\qui=combat\qui+1
												If combat\ordre[combat\qui]=0 Then combat\qui=1
											EndIf
										EndIf
									
	
									Case 3 ; attendre
										mess_action$="Choisissez votre action"
										For k=0 To 3
											a_int=400-Cos(90*k)*60
											b_int=(210-Sin(90*k)*60)
											DrawBlock bouton_combat_sombre,a_int,b_int,k
											
											;attaque cc grisée premier tour
											atd=0
											If av\equi[1]<>0
												For arme.arme=Each arme
													If arme\num=av\equi[1]
														For u=1 To 8
															If arme\rules[u]=100 Then atd=1
														Next
													EndIf
												Next
											EndIf
											if atd=0 and k=0
												If combat\phase<>2
													If have_rule(av\num,12)=0 ; pas de swift
														DrawBlock bouton_combat_sombre,a_int,b_int,27
													EndIf
												EndIf
											ElseIf atd=1 And k=0
												DrawBlock bouton_combat_sombre,a_int,b_int,20
											EndIf
											peut_agir=1
											
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														mess_action="Attaquer"
														If atd=0
															If combat\phase<>2
																If have_rule(av\num,12)=0 ; pas de swift
																	peut_agir=0
																	mess_action="Pas de corps à corps au premier tour"
																Else
																	mess_action="Attaquer (Swift)"
																	If keys(1,2)=50 Then combat_menu_action=1
																EndIf
															Else
																If keys(1,2)=50 Then combat_menu_action=1
															EndIf
														Else
															If keys(1,2)=50 Then combat_menu_action=1
														EndIf
													Case 1
														mess_action="Défendre"
														If keys(1,2)=50 Then combat_menu_action=2
													Case 2
														mess_action="Ordre et Formation"
														If keys(1,2)=50 Then combat_menu_action=3
													Case 3
														mess_action="Inventaire"
														If keys(1,2)=50 Then combat_menu_action=4
												End Select
											EndIf
										Next
										DrawBlock bouton_combat,400+60,210,2
										nb_bouton=3
										For k=0 To nb_bouton-1
											a_int=400+(2)*60
											b_int=(210+(k-(nb_bouton*0.5-0.5))*60)
											Select k
												Case 0
													DrawBlock bouton_combat,a_int,b_int,16
												Case 1
													DrawBlock bouton_combat,a_int,b_int,17
												Case 2
													DrawBlock bouton_combat,a_int,b_int,12
											End Select
											
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												Select k
													Case 0
														mess_action$="Attendre"
														If keys(1,2)=50
															;combat_menu_action=31
															analyse(23,combat\num+"#"+av\num+"#31#s#s#s#s#s#s#",player_id)
															av\last_action=31
															;Playsound2(sons_menu(2))
															combat_menu_action=0
														EndIf
													Case 1
														mess_action$="Passer son tour"
														If keys(1,2)=50
															;combat_menu_action=32
															analyse(23,combat\num+"#"+av\num+"#32#s#s#s#s#s#s#",player_id)
															av\last_action=32
															combat_menu_action=0
														EndIf
													Case 2
														mess_action$="Changer de place"
														If keys(1,2)=50 Then combat_menu_action=41
												End Select
											EndIf
										Next
										
									Case 11 ; attaque de base
										target_actif=1
										mess_action$="Choisissez votre cible"
										If combat_target<>0
											;voir les stats à l'avance
											calcule_stat_cible(av\num,combat_target,combat\num,0,0,0)
											;selectionner
											If keys(1,2)=50
												If target_valide(av\num,combat_target,combat\num)
													;attaque
													analyse(23,combat\num+"#"+av\num+"#11#"+combat_target+"#s#s#s#s#s#",player_id,master_id)
													av\last_action=11
													combat_menu_action=0
													;combat\qui=combat\qui+1
													If combat\ordre[combat\qui]=0 Then combat\qui=1
												Else
													Playsound2(sons_menu(2))
												EndIf
											EndIf
										EndIf
										
									Case 12 ; Attaque Stratégique
										target_actif=1
										mess_action$="Choisissez votre cible"
										If combat_target<>0
											;voir les stats à l'avance
											calcule_stat_cible(av\num,combat_target,combat\num,0,1,0)
											;selectionner
											If keys(1,2)=50
												If target_valide(av\num,combat_target,num_combat)
													;attaque
													analyse(23,combat\num+"#"+av\num+"#12#"+combat_target+"#s#s#s#s#s#",player_id,master_id)
													av\last_action=12
													combat_menu_action=0
													;combat\qui=combat\qui+1
													If combat\ordre[combat\qui]=0 Then combat\qui=1
												Else
													Playsound2(sons_menu(2))
												EndIf
											EndIf
										EndIf

									Case 13 ; attaque dist
										target_actif=1
										mess_action$="Choisissez votre cible"
										If combat_target<>0
											;voir les stats à l'avance
											calcule_stat_cible(av\num,combat_target,combat\num,1,0,0)
											;selectionner
											If keys(1,2)=50
												If target_valide_distance(av\num,combat_target,combat\num)
													;attaque
													mess$=combat\num+"#"+av\num+"#13#"
													For t=1 To 3
														mess$=mess$+atd_cible(t)+"#"
													Next
													For t=1 To 3
														If atd_contact=0
															mess$=mess$+Str(atd_coeff#(t))+"#"
														Else
															mess$=mess$+Str(-atd_coeff#(t))+"#"
														EndIf
													Next
													av\last_action=13
													av\charge[1]=av\charge[1]-1
													combat_menu_action=0
													;combat\qui=combat\qui+1
													If combat\ordre[combat\qui]=0 Then combat\qui=1
													analyse(23,mess$+"s#s#s#s#",player_id,master_id)
												Else
													Playsound2(sons_menu(2))
												EndIf
											EndIf
										EndIf
										
									Case 14 ; attaque dist visée
										target_actif=1
										mess_action$="Choisissez votre cible"
										If combat_target<>0
											;voir les stats à l'avance
											calcule_stat_cible(av\num,combat_target,combat\num,1,1,0)
											;selectionner
											If keys(1,2)=50
												If target_valide_distance(av\num,combat_target,combat\num)
													;attaque
													mess$=combat\num+"#"+av\num+"#14#"
													For t=1 To 3
														mess$=mess$+atd_cible(t)+"#"
													Next
													For t=1 To 3
														If atd_contact=0
															mess$=mess$+atd_coeff#(t)+"#"
														Else
															mess$=mess$+Str(-atd_coeff#(t))+"#"
														EndIf
													Next
													av\last_action=14
													av\charge[1]=av\charge[1]-1
													combat_menu_action=0
													;combat\qui=combat\qui+1
													If combat\ordre[combat\qui]=0 Then combat\qui=1
													analyse(23,mess$+"s#s#s#s#",player_id,master_id)
												Else
													Playsound2(sons_menu(2))
												EndIf
											EndIf
										EndIf

									Case 15 ; rafale
										target_actif=1
										mess_action$="Choisissez votre cible"
										If combat_target<>0
											;voir les stats à l'avance
											For arme.arme=Each arme
												If arme\num=av\equi[1]
													calcule_stat_cible(av\num,combat_target,combat\num,1,0,arme\cadence)											
												EndIf
											Next
											;selectionner
											If keys(1,2)=50 And combat_target<>0
												If target_valide_distance(av\num,combat_target,combat\num)
													;attaque
													mess$=combat\num+"#"+av\num+"#15#"+combat_target+"#"
													For arme.arme=Each arme
														If arme\num=av\equi[1]
															mess$=mess$+arme\cadence+"#"
															av\charge[1]=av\charge[1]-arme\cadence
														EndIf
													Next
													av\last_action=15
													combat_menu_action=0
													;combat\qui=combat\qui+1
													If combat\ordre[combat\qui]=0 Then combat\qui=1
													analyse(23,mess$+"s#s#s#s#s#s#",player_id,master_id)
												Else
													Playsound2(sons_menu(2))
												EndIf
											EndIf
										EndIf
									
								;	Case 21 ; position défensive
								;		analyse(23,combat\num+"#"+av\num+"#21#s#s#s#s#s#s",player_id)
								;		av\last_action=21										
								;		;combat\qui=combat\qui+1
								;		If combat\ordre[combat\qui]=0 Then combat\qui=1
								;		combat_menu_action=0
																			
								;	Case 31 ; attendre
								;		analyse(23,combat\num+"#"+av\num+"#31#s#s#s#s#s#s#",player_id)
								;		av\last_action=31
								;	;	Playsound2(sons_menu(2))
								;		combat_menu_action=0
									
								;	Case 32 ; passer son tour
								;		analyse(23,combat\num+"#"+av\num+"#32#s#s#s#s#s#s#",player_id)
								;		av\last_action=32
								;		combat_menu_action=0
									
									Case 41 ; changer de position
										mess_action$="Choisissez votre nouvelle place"
										target_actif=2
										If keys(1,2)=50 And combat_place<>0
											analyse(23,combat\num+"#"+av\num+"#41#"+combat_place+"#s#s#s#s#s#s#",player_id,master_id)
											av\last_action=41
											combat_menu_action=0
											;combat\qui=combat\qui+1
											If combat\ordre[combat\qui]=0 Then combat\qui=1
										EndIf
									
									Case 42 ; changer d'arme
										;Playsound2(sons_menu(02))
										mess_action$="Choisissez votre nouvelle arme"
										For t=1 To 3
											atd_cible(t)=0
											If av\equi[t]<>0
												For arme.arme=Each arme
													If arme\num=av\equi[t]
														atd_cible(t)=arme\icone[1]
													EndIf
												Next
											EndIf
										Next									
										For t=1 To 3
											a_int=400+(t-2)*60
											b_int=(150)
											If atd_cible(t)=0
												DrawBlock bouton_combat_sombre,a_int,b_int,28
											Else
												DrawBlock bouton_combat,a_int,b_int,10
												DrawBlock atd_cible(t),a_int-20,b_int-20
											EndIf
											
											If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25
												If keys(1,2)=50
													If t=1
														combat_menu_action=0
													ElseIf av\equi[1]=0 And av\equi[t]=0
														combat_menu_action=0													
													Else 
														mess$=combat\num+"#"+av\num+"#42#"+t+"#s#s#s#s#s#s#"
														analyse(23,mess$,player_id,master_id)
														combat_menu_action=0
														If combat\ordre[combat\qui]=0 Then combat\qui=1
														Playsound2(sons_menu(6))
													EndIf
												EndIf
											EndIf	
										Next
																			
									Default
										combat_menu_action=0
								
								End Select
								
								;si temps épuisé alors passer à qq1 d'autre
								temps_restant=temps_restant-delta_frame
					
					; RAZ du temps_restant. Vérifier si ça passe bien au bon endroit. Mettre au début de la frame ?			
								
								;If combat\qui<>combat\old_qui Then temps_restant=TEMPS_ROUND:combat_menu_action=0:combat_target=0
								;combat\old_qui=combat\qui
								
								If temps_restant<0 And temps_restant>-1000
									mess$=combat\num+"#"+av\num+"#0#s#s#s#s#s#s#"
									analyse(23,mess$,player_id)
									;new_log(var_pl_av_str$(1,"name")+" est complètement perdu et passe son tour.",180,180,0)
									temps_restant=TEMPS_ROUND
								EndIf
								
								;barre de temps
								DrawImage combat_barre_vide,290,360-decalage_hud
								DrawImageRect combat_barre_pleine,300,370-decalage_hud,10,10,200*temps_restant/Float(TEMPS_ROUND),10
								
								
							;	Color 0,0,0
							;	Rect 400-100-4,370-4,200+8,10+8
							;	Color 180,0,0
							;	Rect 400-100-3,370-3,200+6,10+6,0
							;	af#=temps_restant/Float(TEMPS_ROUND)
							;	If af#>0.5
							;		Color 180,0,0
							;	Else
							;		If reste(Int(timer_animation#*0.2),2)
							;			Color 255,255,255
							;		Else
							;			Color 180,0,0
							;		EndIf
							;	EndIf
							;	Rect 400-100,370,200*af#,10
															
							Else
								combat_menu_action=0
							EndIf
						EndIf
					Next
					
				Else ; animation
				;	Color 0,0,0
				;	Rect 0,0,50,80
				;	Color 255,255,255
				;	Text 5,5,"Frame : "+combat_frame
				;	Text 5,20,"Timer : "+timer_animation#
				;	Text 5,35,"Timer : "+timer_animation2#
					
					;ordre (à partir de l'ancien joueur)
					type_ordre=0
					For t=1 To 19
						If combat\ordre[t]=0 Then type_ordre=1
						qui=1
						For k=1 To 19
							If combat_from=combat\ordre[k] Then qui=k
						Next
						if combat_action=31 then qui=combat\qui
						If type_ordre=0 ; ceux dans la chaine
							For av.avatar=Each avatar
								If av\num=combat\ordre[t]
									If t<qui
										DrawBlock fight_faces,400+((t+actifs-qui)-Floor(actifs*0.5))*30-12,30-12,av\cat-1							
									Else
										DrawBlock fight_faces,400+((t-qui)-Floor(actifs*0.5))*30-12,30-12,av\cat-1
									EndIf
								EndIf
							Next
						ElseIf type_ordre=1 ; ceux qui attendent
							For av.avatar=Each avatar
								If av\num=combat\ordre[t]
									DrawBlock fight_faces_sombres,400+((19-t)-Floor(inactifs*0.5))*30-12,60-12,av\cat-1
								EndIf
							Next
						EndIf
					Next
					
					
					;afficher tous les gars normalement, sauf pour l'acteur et la cible
					For groupe.groupe=Each groupe
						If groupe\num=combat\groupe[1] ; groupe de gauche (ennemis)
							For i=0 To 2 ; ligne
								For j=1 To 3 ; colonne
									t=i*3+j
									ai=(100*(3-j)+50)
									bi=(100*i+175)			
									If groupe\formation[t]<>0
										For av.avatar=Each avatar
											If av\num=groupe\formation[t]
												actif=0
												For u=1 To 9
													If av\num=action_target(u) Then actif=u
												Next
												
												;LBA) petite correction pour le boss : à améliorer = casser ce qui a été fait ^^
												If av\cat=17; Systeme de Sécurité
													ai = ai - (ImageWidth(fighters_gfx(av\cat,1)) - ImageWidth(fight_ring_1)) * 0.5 ;
													bi = bi - (ImageHeight(fighters_gfx(av\cat,1)) ) * 0.5 ;
												EndIf
												;fin correction
												
												If combat_from=av\num Then actif=-1
												If actif=0
													frame=0
													Select av\animation
														Case 3 ; defensive
															frame=8
														Case 4 ; mort
															frame=26
														Default ; also Case 1 idle
															frame=reste(Int(timer_animation#*0.1),6)
															If frame>3 Then frame=6-frame
															If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
															If Not(av\pv[1]>0) Then av\animation=4
													End Select
													DrawImage fighters_gfx(av\cat,1),ai,bi,frame
													If av\defense<0
														ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
														di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
														For k=1 To Floor(Abs(av\defense*0.5))
															DrawImage gfx_bouclier,ci,di+18*k,1
														Next
													ElseIf av\defense>0
														ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
														di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
														For k=1 To Floor(av\defense*0.5)
															DrawImage gfx_bouclier,ci,di+18*k,0
														Next
													EndIf
												Else
													Select combat_action
														Case 0 ; confus
															frame=reste(Int(timer_animation2#*0.1),7)
															If frame>3 Then frame=6-frame
															side=reste(Int(timer_animation2#*0.2),2)+1
															DrawImage fighters_gfx(av\cat,side),ai,bi,frame
															If av\defense<0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf			

														Case 11 ; attaque
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=27+Floor(timer_animation2#*0.1)
																	If frame>29
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																combat_frame=Floor(timer_animation2#*0.1)
																If action_reussite(actif)>10 ; 2 coups
																	If combat_frame=0
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	ElseIf combat_frame<3
																		If retournedice(Str(action_reussite(actif)),1)=1 ; echec
																			If combat_old_frame=0 Then Playsound2(sons_battle(1))
																			frame=min(7+combat_frame,10)
																		Else ; hit
																			If combat_old_frame=0 Then Playsound2(sons_battle(2))
																			frame=15+min(combat_frame,2)
																		EndIf
																	Else
																		If retournedice(Str(action_reussite(actif)),2)=1 ; echec
																			frame=min(7+(combat_frame-2),10)
																			If combat_old_frame=2 
																				Playsound2(sons_battle(1))
																				if retournedice(Str(action_reussite(actif)),1)<>1 then new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																			endif
																		Else ; hit
																			If combat_old_frame=2
																				Playsound2(sons_battle(2))
																				new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																			endif
																			If av\pv[1]>0
																				frame=14+min((combat_frame-2),3)
																			Else
																				frame=22+min((combat_frame-2),4)
																				av\animation=4
																			EndIf
																		EndIf

																	EndIf
																Else ; 1 coup
																	If combat_frame>0
																		If action_reussite(actif)=1 ; echec
																			frame=10+min(floor(combat_frame*1.28),4)
																			If combat_old_frame=0 Then Playsound2(sons_battle(Rand(3,4)))
																		Else ; hit
																			If combat_old_frame=0
																				Playsound2(sons_battle(Rand(5,6)))
																			endif
																			if combat_dgts(actif)>-1
																				new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																				combat_dgts(actif)=-1
																			endif
																			If av\pv[1]>0
																				frame=14+min(combat_frame,3)
																			Else
																				frame=min(22+min(combat_frame,4),26)
																				av\animation=4
																			EndIf
																		EndIf
																	Else
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
															If av\defense<0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
															
														Case 12 ; atd (animation de l'attaquant à modifier un jour)
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=30+Floor(timer_animation2#*0.1)
																	If frame>32
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues (normalement inutile mais sait-on jamais)
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																combat_frame=Floor(timer_animation2#*0.1)
																If action_reussite(actif)>10 ; 2 coups
																	If combat_frame=0
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	ElseIf combat_frame<3
																		If retournedice(Str(action_reussite(actif)),1)=1 ; echec
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																			frame=min(7+combat_frame,10)
																		Else ; hit
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																			frame=15+min(combat_frame,2)
																		EndIf
																	Else
																		If retournedice(Str(action_reussite(actif)),2)=1 ; echec
																			frame=min(7+(combat_frame-2),10)
																			If combat_old_frame=2
																				Playsound2(sons_battle(10))
																				if retournedice(Str(action_reussite(actif)),1)<>1 then new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																			endif
																		Else ; hit
																			If combat_old_frame=2
																				Playsound2(sons_battle(10))
																				new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																			endif
																			If av\pv[1]>0
																				frame=14+min((combat_frame-2),3)
																			Else
																				frame=22+min((combat_frame-2),4)
																				av\animation=4
																			EndIf
																		EndIf
																	EndIf
																Else ; 1 coup
																	If combat_frame>0
																		If action_reussite(actif)=1 ; echec
																			frame=10+min(floor(combat_frame*1.28),4)
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																		Else ; hit
																			If combat_old_frame=0 
																				Playsound2(sons_battle(10))
																			endif
																			if combat_dgts(actif)>-1
																				new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																				combat_dgts(actif)=-1
																			endif
																			If av\pv[1]>0
																				frame=14+min(combat_frame,3)
																			Else
																				frame=min(22+min(combat_frame,4),26)
																				av\animation=4
																			EndIf
																		EndIf
																	Else
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
															If av\defense<0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
														
														Case 15 ; bangbangbang (animation de l'attaquant à modifier un jour)
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=30+Floor(timer_animation2#*0.1)
																	If frame>32
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues (normalement inutile mais sait-on jamais)
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																combat_frame=Floor(timer_animation2#*0.1)
																; 1 coup
																If combat_frame>0
																	If action_reussite(actif)=1 ; echec
																		frame=10+min(floor(combat_frame*1.28),4)
																		If combat_old_frame=0
																			For av_cadence.avatar=Each avatar
																				If av_cadence\num=combat_from
																					cadence=1
																					For ar_cadence.arme = Each arme
																						If ar_cadence\num=av_cadence\equi[1]
																							Playsound2(sons_battle(9+ar_cadence\cadence))
																						Endif
																					Next
																				EndIf
																			Next
																		EndIf
																	Else ; hit
																		If combat_old_frame=0
																			For av_cadence.avatar=Each avatar
																				If av_cadence\num=combat_from
																					cadence=1
																					For ar_cadence.arme = Each arme
																						If ar_cadence\num=av_cadence\equi[1]
																							Playsound2(sons_battle(9+ar_cadence\cadence))
																						Endif
																					Next
																				EndIf
																			Next
																		EndIf
																		If combat_dgts(actif)>-1
																			new_dgts(combat_dgts(actif),ai-10,bi-30,-1)
																			combat_dgts(actif)=-1
																		EndIf
																		If av\pv[1]>0
																			frame=14+min(combat_frame,3)
																		Else
																			frame=min(22+min(combat_frame,4),26)
																			av\animation=4
																		EndIf
																	EndIf
																Else
																	frame=reste(Int(timer_animation#*0.1),6)
																	If frame>3 Then frame=6-frame
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
															If av\defense<0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
														
														Case 21 ; defensive
															If av\num=combat_from
																combat_old_frame=combat_frame
																combat_frame=Floor(timer_animation2#*0.1)
																If combat_frame>1
																	DrawImage fighters_gfx(av\cat,1),ai,bi,8
																	If combat_old_frame=1 Then Playsound2(sons_battle(9))
																	ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
																	di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																	If av\defense<0
																		For k=1 To Floor(Abs(av\defense*0.5))
																			DrawImage gfx_bouclier,ci,di+18*k,1
																		Next
																	ElseIf av\defense>0
																		For k=1 To Floor(av\defense*0.5)
																			DrawImage gfx_bouclier,ci,di+18*k,0
																		Next
																	Endif
																Else
																	DrawImage fighters_gfx(av\cat,1),ai,bi,0
																EndIf														
															EndIf
															
														Case 31 ; attendre
															frame=reste(Int(timer_animation#*0.1),6)
															If frame>3 Then frame=6-frame
															If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
															If Not(av\pv[1]>0) Then av\animation=4
															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
															
														Case 32 ; passer son tour
															frame=reste(Int(timer_animation2#*0.1),7)
															If frame>3 Then frame=6-frame
															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
														Case 41 ; chgt formation
															a_int=Floor((combat_var1-1)/3.) ; ligne ancienne place
															b_int=reste((combat_var1-1),3)+1 ; colonne ancienne place
															c_int=Floor((combat_var2-1)/3.) ; ligne nouvelle place
															d_int=reste((combat_var2-1),3)+1 ; colonne nouvelle place
															ai_dep=(100*(3-b_Int)+50)
															bi_dep=(100*a_int+175)
															ai_fin=(100*(3-d_Int)+50)
															bi_fin=(100*c_int+175)
															If av\num=combat_from
																af#=minf#(combat_temp_anim*0.001,1)
																ai=ai_dep*af#+ai_fin*(1-af#)
																bi=bi_dep*af#+bi_fin*(1-af#)-400*af#*(1-af#) ;déplacement + "saut"
																frame=max(45-max(Floor(af#*5),0),41)	
															ElseIf actif>0;av\num=combat_target
																If av\pv[1]>0	
																	af#=minf#(combat_temp_anim*0.001,1)
																	ai=ai_fin*af#+ai_dep*(1-af#)
																	bi=bi_fin*af#+bi_dep*(1-af#)
																	frame=max(49-max(Floor(af#*4),0),46)
																Else
																	af#=minf#(combat_temp_anim*0.001,1)
																	ai=ai_fin*af#+ai_dep*(1-af#)
																	bi=bi_fin*af#+bi_dep*(1-af#)
																	frame=26
																EndIf
															EndIf

															DrawImage fighters_gfx(av\cat,1),ai,bi,frame
														
														Case 51 ; déployer gearbot (numéro des frames à changer) ;icikonbosse
															frame = max(0,min(Int(timer_animation2#*0.2),7))+1 ; 4 frame/sec
															If av\num=combat_from ; déployeur
																DrawImage fighters_gfx(av\cat,1),ai,bi,26+min(min(3,8-frame),frame) ; remplacer 26/27 par 49/50)
															ElseIf actif>0 ; gearbot
																If frame>2
																	If av\animation=0
																		Playsound2(sons_battle(15))
																		av\animation=1
																	Endif
																	DrawImage fighters_gfx(av\cat,1),ai,bi-(max(0,8-frame))*80,0
																EndIf
															EndIf
														
														Case 52 ; ranger gearbot
															frame = max(0,min(Int(timer_animation2#*0.2),7))+1 ; 4 frame/sec
															If av\num=combat_from ; déployeur
																DrawImage fighters_gfx(av\cat,1),ai,bi,26+min(min(3,8-frame),frame) ; remplacer 26/27 par 49/50)
															ElseIf actif>0 ; gearbot
																If frame>2
																	If av\animation=0
																		Playsound2(sons_battle(15))
																		av\animation=1
																	Endif
																	DrawImage fighters_gfx(av\cat,1),ai,bi-(max(0,frame-2))*80,0
																EndIf
															EndIf
														
													End Select
													SetFont middle_font
													Color 255,255,255
													Text ai,bi+20,av\name$[Int(options#(7))],1,1
												EndIf
											EndIf
										Next
									EndIf
								Next	
							Next
						EndIf
						If groupe\num=combat\groupe[2] ; groupe de droite (grp_joueur)
							For i=0 To 2 ; ligne
								For j=1 To 3 ; colonne
									t=i*3+j
									ai=800-(100*(3-j)+50)
									bi=(100*i+175)
									If groupe\formation[t]<>0
										For av.avatar=Each avatar
											If av\num=groupe\formation[t]
												actif=0
												mess$=combat_from
												For u=1 To 9
													If av\num=action_target(u) Then actif=u
													mess$=mess$+"-"+action_target(u)
												Next
												If combat_from=av\num Then actif=-1
												If actif=0
													If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80 And target_actif=3 
														combat_react=av\num
													EndIf
													If av\num=combat_react
														frame=reste(Int(timer_animation#*0.1),8)
														DrawImage fight_ring_4,ai,bi,frame
													EndIf
													frame=0
													Select av\animation
														Case 3 ; defensive
															frame=8
														Case 4 ; mort
															frame=26
														Default ; also Case 1 idle
															frame=reste(Int(timer_animation#*0.1),6)
															If frame>3 Then frame=6-frame
															If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
															If Not(av\pv[1]>0) Then av\animation=4
													End Select
													DrawImage fighters_gfx(av\cat,2),ai,bi,frame
													;defense
													If av\defense<0
														ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
														di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
														For k=1 To Floor(Abs(av\defense*0.5))
															DrawImage gfx_bouclier,ci,di+18*k,1
														Next
													ElseIf av\defense>0
														ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
														di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
														For k=1 To Floor(av\defense*0.5)
															DrawImage gfx_bouclier,ci,di+18*k,0
														Next
													EndIf
													;pv
													If av\prop=1
														calcul_bonus_equi(av\num)
														af#=av\pv[1]/Float(av\pv[2]+bonus_equi(10))
														Color 200,0,0
														ci=ai+ImageWidth(fighters_gfx(av\cat,1))*0.5
														height=ImageHeight(fighters_gfx(av\cat,1))*0.8
														Rect ci,bi-height,10,height
														Color 0,200,0
														Rect ci,bi-height*af#,10,height*af#
													EndIf
												Else
													Select combat_action
														Case 0 ; confus
															frame=reste(Int(timer_animation2#*0.1),7)
															If frame>3 Then frame=6-frame
															side=reste(Int(timer_animation2#*0.2),2)+1
															DrawImage fighters_gfx(av\cat,side),ai,bi,frame
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf

														Case 11 ; attaque
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=27+Floor(timer_animation2#*0.1)
																	If frame>29
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80 And target_actif=3 
																	combat_react=av\num
																EndIf
																If av\num=combat_react
																	frame=reste(Int(timer_animation#*0.1),8)
																	DrawImage fight_ring_4,ai,bi,frame
																EndIf
																combat_frame=Floor(timer_animation2#*0.1)
																If action_reussite(actif)>10 ; 2 coups
																	If combat_frame=0
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	ElseIf combat_frame<3
																		If retournedice(Str(action_reussite(actif)),1)=1 ; echec
																			If combat_old_frame=0 Then Playsound2(sons_battle(1))
																			frame=min(7+combat_frame,10)
																		Else ; hit
																			If combat_old_frame=0 Then Playsound2(sons_battle(2))
																			frame=15+min(combat_frame,2)
																		EndIf
																	Else
																		If retournedice(Str(action_reussite(actif)),2)=1 ; echec
																			frame=min(7+(combat_frame-2),10)
																			If combat_old_frame=2 
																				Playsound2(sons_battle(1))
																				if retournedice(Str(action_reussite(actif)),1)<>1 then new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																			endif
																		Else ; hit
																			If combat_old_frame=2 
																				Playsound2(sons_battle(2))
																				new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																			endif
																			If av\pv[1]>0
																				frame=14+min((combat_frame-2),3)
																			Else
																				frame=22+min((combat_frame-2),4)
																				av\animation=4
																			EndIf
																		EndIf

																	EndIf
																Else ; 1 coup
																	If combat_frame>0
																		If action_reussite(actif)=1 ; echec
																			frame=10+min(floor(combat_frame*1.28),4)
																			If combat_old_frame=0 Then Playsound2(sons_battle(Rand(3,4)))
																		Else ; hit
																			If combat_old_frame=0 
																				Playsound2(sons_battle(Rand(5,6)))
																			endif
																			if combat_dgts(actif)>-1
																				new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																				combat_dgts(actif)=-1
																			endif
																			If av\pv[1]>0
																				frame=14+min(combat_frame,3)
																			Else
																				frame=min(22+min(combat_frame,4),26)
																				av\animation=4
																			EndIf
																		EndIf
																	Else
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
															
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
															
														Case 12 ; atd (mofifier l'animation d'attaquant)
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=30+Floor(timer_animation2#*0.1)
																	If frame>32
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80 And target_actif=3 
																	combat_react=av\num
																EndIf
																If av\num=combat_react
																	frame=reste(Int(timer_animation#*0.1),8)
																	DrawImage fight_ring_4,ai,bi,frame
																EndIf
																combat_frame=Floor(timer_animation2#*0.1)
																If action_reussite(actif)>10 ; 2 coups
																	If combat_frame=0
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	ElseIf combat_frame<3
																		If retournedice(Str(action_reussite(actif)),1)=1 ; echec
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																			frame=min(7+combat_frame,10)
																		Else ; hit
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																			frame=15+min(combat_frame,2)
																		EndIf
																	Else
																		If retournedice(Str(action_reussite(actif)),2)=1 ; echec
																			frame=min(7+(combat_frame-2),10)
																			If combat_old_frame=2 
																				Playsound2(sons_battle(10))
																				if retournedice(Str(action_reussite(actif)),1)<>1 then new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																			endif
																		Else ; hit
																			If combat_old_frame=2 
																				Playsound2(sons_battle(10))
																				new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																			endif
																			If av\pv[1]>0
																				frame=14+min((combat_frame-2),3)
																			Else
																				frame=22+min((combat_frame-2),4)
																				av\animation=4
																			EndIf
																		EndIf
																	EndIf
																Else ; 1 coup
																	If combat_frame>0
																		If action_reussite(actif)=1 ; echec
																			frame=10+min(floor(combat_frame*1.28),4)
																			If combat_old_frame=0 Then Playsound2(sons_battle(10))
																		Else ; hit
																			If combat_old_frame=0 
																				Playsound2(sons_battle(10))
																			endif
																			if combat_dgts(actif)>-1
																				new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																				combat_dgts(actif)=-1
																			endif
																			If av\pv[1]>0
																				frame=14+min(combat_frame,3)
																			Else
																				frame=min(22+min(combat_frame,4),26)
																				av\animation=4
																			EndIf
																		EndIf
																	Else
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
															
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
														
														Case 15 ; bangbangbang (mofifier l'animation d'attaquant)
															If av\num=combat_from
																If av\equi[1]<>0
																	frame=30+Floor(timer_animation2#*0.1)
																	If frame>32
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																Else ; mains nues
																	frame=4+Floor(timer_animation2#*0.1)
																	If frame>7
																		frame=reste(Int(timer_animation#*0.1),6)
																		If frame>3 Then frame=6-frame
																	EndIf
																EndIf
															Else;If av\num=combat_target
																If MouseX()>ai-40 And MouseX()<ai+40 And MouseY()<bi+20 And MouseY()>bi-80 And target_actif=3 
																	combat_react=av\num
																EndIf
																If av\num=combat_react
																	frame=reste(Int(timer_animation#*0.1),8)
																	DrawImage fight_ring_4,ai,bi,frame
																EndIf
																combat_frame=Floor(timer_animation2#*0.1)
																If combat_frame>0
																	If action_reussite(actif)=1 ; echec
																		frame=10+min(floor(combat_frame*1.28),4)
																		If combat_old_frame=0
																			For av_cadence.avatar=Each avatar
																				If av_cadence\num=combat_from
																					cadence=1
																					For ar_cadence.arme = Each arme
																						If ar_cadence\num=av_cadence\equi[1]
																							Playsound2(sons_battle(9+ar_cadence\cadence))
																						Endif
																					Next
																				EndIf
																			Next
																		EndIf
																	Else ; hit
																		If combat_old_frame=0
																			For av_cadence.avatar=Each avatar
																				If av_cadence\num=combat_from
																					cadence=1
																					For ar_cadence.arme = Each arme
																						If ar_cadence\num=av_cadence\equi[1]
																							Playsound2(sons_battle(9+ar_cadence\cadence))
																						Endif
																					Next
																				EndIf
																			Next
																		EndIf
																		If combat_dgts(actif)>-1
																			new_dgts(combat_dgts(actif),ai+10,bi-30,1)
																			combat_dgts(actif)=-1
																		EndIf
																		If av\pv[1]>0
																			frame=14+min(combat_frame,3)
																		Else
																			frame=min(22+min(combat_frame,4),26)
																			av\animation=4
																		EndIf
																	EndIf
																Else
																	frame=reste(Int(timer_animation#*0.1),6)
																	If frame>3 Then frame=6-frame
																EndIf
																combat_old_frame=combat_frame
															EndIf
															
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
															
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
														
														Case 21 ; defensive
															If av\num=combat_from
																combat_frame=Floor(timer_animation2#*0.1)
																If combat_frame>1
																	DrawImage fighters_gfx(av\cat,2),ai,bi,8
																	If combat_old_frame=1 Then Playsound2(sons_battle(9))
																	ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																	di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																	If av\defense<0
																		For k=1 To Floor(Abs(av\defense*0.5))
																			DrawImage gfx_bouclier,ci,di+18*k,1
																		Next
																	ElseIf av\defense>0
																		For k=1 To Floor(av\defense*0.5)
																			DrawImage gfx_bouclier,ci,di+18*k,0
																		Next
																	Endif
																Else
																	DrawImage fighters_gfx(av\cat,2),ai,bi,0
																EndIf
																combat_old_frame=combat_frame													
															EndIf
															
														Case 31 ; attendre
															frame=reste(Int(timer_animation#*0.1),6)
															If frame>3 Then frame=6-frame
															If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then frame=frame+33
															If Not(av\pv[1]>0) Then av\animation=4
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
															
														Case 32 ; passer son tour
															frame=reste(Int(timer_animation2#*0.1),7)
															If frame>3 Then frame=6-frame
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
															If av\defense<0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(Abs(av\defense*0.5))
																	DrawImage gfx_bouclier,ci,di+18*k,1
																Next
															ElseIf av\defense>0
																ci=ai-ImageWidth(fighters_gfx(av\cat,1))*0.5
																di=bi-ImageHeight(fighters_gfx(av\cat,1))*0.9-18
																For k=1 To Floor(av\defense*0.5)
																	DrawImage gfx_bouclier,ci,di+18*k,0
																Next
															EndIf
															
														Case 41 ; chgt formation
															a_int=Floor((combat_var1-1)/3.) ; ligne ancienne place
															b_int=reste((combat_var1-1),3)+1 ; colonne ancienne place
															c_int=Floor((combat_var2-1)/3.) ; ligne nouvelle place
															d_int=reste((combat_var2-1),3)+1 ; colonne nouvelle place
															ai_dep=screenwidth-(100*(3-b_Int)+50)
															bi_dep=(100*a_int+175)
															ai_fin=screenwidth-(100*(3-d_Int)+50)
															bi_fin=(100*c_int+175)
															If av\num=combat_from
																af#=minf#(combat_temp_anim*0.001,1)
																ai=ai_dep*af#+ai_fin*(1-af#)
																bi=bi_dep*af#+bi_fin*(1-af#)-400*af#*(1-af#)
																frame=max(45-max(Floor(af#*5),0),41)	
															Else;If actif>0
																;runtimeerror action_target(1)
																If av\pv[1]>0	
																	af#=minf#(combat_temp_anim*0.001,1)
																	ai=ai_fin*af#+ai_dep*(1-af#)
																	bi=bi_fin*af#+bi_dep*(1-af#)
																	frame=max(49-max(Floor(af#*4),0),46)
																Else
																	af#=minf#(combat_temp_anim*0.001,1)
																	ai=ai_fin*af#+ai_dep*(1-af#)
																	bi=bi_fin*af#+bi_dep*(1-af#)
																	frame=26
																EndIf
															EndIf
															DrawImage fighters_gfx(av\cat,2),ai,bi,frame
														
														Case 51 ; déployer gearbot (numéro des frames à changer) ;icikonbosse
															frame = max(0,min(Int(timer_animation2#*0.1),7))+1 ; 4 frame/sec
															If av\num=combat_from ; déployeur
																DrawImage fighters_gfx(av\cat,2),ai,bi,26+max(1,min(min(3,8-frame),frame)) ; remplacer 26/27 par 49/50)
															ElseIf actif>0 ; gearbot
																If frame>2
																	If av\animation=0
																		Playsound2(sons_battle(15))
																		av\animation=1
																	Endif
																	DrawImage fighters_gfx(av\cat,2),ai,bi-(max(0,8-frame))*80,0
																EndIf
															EndIf
														
														Case 52 ; ranger gearbot
															frame = max(0,min(Int(timer_animation2#*0.1),7))+1 ; 4 frame/sec
															;new_log(frame)
															If av\num=combat_from ; déployeur
																DrawImage fighters_gfx(av\cat,2),ai,bi,26+max(1,min(min(3,8-frame),frame)) ; remplacer 26/27 par 49/50)
															ElseIf actif>0 ; gearbot
																If frame>1
																	If av\animation=1
																		Playsound2(sons_battle(16))
																		av\animation=0
																	Endif
																	DrawImage fighters_gfx(av\cat,2),ai,bi-(max(0,frame-2))*80,0
																EndIf
																If frame=8
																	action_target(1)=0 ; n'est plus actif dans l'animation
																	For init=1 to 19 ; sortir de l'ordre de jeu
																		If combat\ordre[init]=av\num
																			For init2=init to 18
																				combat\ordre[init2]=combat\ordre[init2+1]
																			Next
																		EndIf
																	Next
																	groupe\formation[t]=0 ; sortir du groupe
																	av\groupe=0
																EndIf
															EndIf
														
													End Select
												EndIf
												SetFont middle_font
												Color 255,255,255
												Text ai,bi+20,av\name$[Int(options#(7))],1,1
											EndIf
										Next
									EndIf
								Next	
							Next
						EndIf
					Next				
				EndIf
				
				If combat\qui<>combat\old_qui Then temps_restant=TEMPS_ROUND:combat_menu_action=0:combat_target=0
				combat\old_qui=combat\qui
				
				combat_temp_anim=combat_temp_anim-delta_frame
										
				;message de LOG LBA 
				SetFont little_font
				For t=1 To 9
					Color 0,0,0
					Text 160+80*screeny,screenheight-(t-1)*18*screeny-25,log_mess$(t,1)
					;;EndIf
				Next
				
				If chat_mode=1
					Color 0,0,0
					Rect 5+80*screeny-4,screenheight-31,406,18+6
					Color 255,255,255
					Rect 5+80*screeny-3,screenheight-30,404,18+4,0
				
					msg_radio$=input_text(msg_radio$)
					If Floor(timer_animation#*0.05) Mod 2 = 0
						char$="#"
					Else
						char$="_"
					EndIf
					Text 5,screenheight-28,"["+CurrentTime()+"]"
					Text 5+80*screeny,screenheight-28,msg_radio$+char$
					
					If keys(14,2)=50 Or keys(87,2)=50
						If msg_radio$=""
							chat_mode=0
						ElseIf Len(msg_radio$)<4
							new_log("Vous : "+msg_radio$,50,50,255)
							analyse(99,msg_radio$,player_id)
							msg_radio$=""
							chat_mode=0
						Else
							If Left(msg_radio$,2)="/w"
								new_log("To "+Mid(msg_radio$,3,1)+" : "+Mid(msg_radio$,4),255,75,255)
								analyse(98,Mid(msg_radio$,3,1)+"#"+Mid(msg_radio$,4),player_id)
								msg_radio$=Left(msg_radio,3)
								chat_mode=0
							Else
								new_log("Vous : "+msg_radio$,50,50,255)
								analyse(99,msg_radio$,player_id)
								msg_radio$=""
								chat_mode=0
							EndIf
						EndIf	
					EndIf
				Else
					If keys(14,2)=50 Or keys(82,2)=50 Then chat_mode=1					
					If inactif_controled>0
						If react_mode=0
							mess$="Appuyez sur Espace pour faire agir les personnages en attente"
							ai=max(Len(mess$)*8,200)
						;	Color 0,0,0
						;	Rect (screenwidth-ai)*0.5-4,(450-10)-4,ai+8,20+8,1
						;	Color 180,0,0
						;	Rect (screenwidth-ai)*0.5-3,(450-10)-3,ai+6,20+6,0							
						;	SetFont middle_font
						;	Text 400,450,mess$,1,1
							DrawImageRect combat_barretexte,396-ai*0.5,436-decalage_hud,0,0,(ai+8)*0.5,28
							DrawImageRect combat_barretexte,400,436-decalage_hud,608-(ai+8)*0.5,0,(ai+8)*0.5,28
							Color 50,50,50
							SetFont middle_font
							Text 400,450-decalage_hud,mess$,1,1
							
							If keys(12,2)=50
								keys(12,2)=49
								react_mode=1
							EndIf
						EndIf
					EndIf
					
				EndIf
				
				If react_mode=1
					If keys(12,2)=50 Or keys(2,2)=50 Then react_mode=0
					mess_action$="Cliquez sur le personnage à faire agir"
					target_actif=3
					a_int=400
					b_int=210
					DrawBlock bouton_combat,a_int,b_int,29
					If MouseX()<a_int+25 And MouseX()>a_int-25 And MouseY()>b_int-25 And MouseY()<b_int+25 And keys(1,2)=50 Then react_mode=0								
								
					If keys(1,2)=50 And combat_react<>0
						;verifier que le perso selectionné est bien au joueur et inactif
						For av.avatar=Each avatar
							If av\num=combat_react
								If av\prop=1
									inactifs=0
									type_ordre=0
									For t=1 To 19
										If combat\ordre[t]=0
											type_ordre=1
										Else
											If type_ordre=1
												If av\num=combat\ordre[t] Then inactifs=1
											EndIf
										EndIf
									Next
					
									If inactifs=1
										;Playsound2(sons_menu(1))
										react_mode=0
										analyse(23,combat\num+"#"+av\num+"#311#s#s#s#s#s#s#",player_id)
									Else
										Playsound2(sons_menu(2))
									EndIf
								Else
									Playsound2(sons_menu(2))
								EndIf
							EndIf
						Next
					EndIf

				EndIf
				combat_react=0
				
				;text mess_action
				If mess_action$<>""
					ai=min(max(Len(mess_action$)*8,200),600)
					;	Color 0,0,0
					;	Rect (screenwidth-ai)*0.5-4,(420-10)-4,ai+8,20+8,1
					;	Color 180,0,0
					;	Rect (screenwidth-ai)*0.5-3,(420-10)-3,ai+6,20+6,0
					DrawImageRect combat_barretexte,396-ai*0.5,406-decalage_hud,0,0,(ai+8)*0.5,28
					DrawImageRect combat_barretexte,400,406-decalage_hud,608-(ai+8)*0.5,0,(ai+8)*0.5,28
					Color 50,50,50
					SetFont middle_font
					Text 400,420-decalage_hud,mess_action$,1,1
				EndIf
				mess_action$=""
				
				;A enlever
				If mode_debug=1 And KeyDown(keys(35,1))
					action=1
					For av.avatar=Each avatar
						If av\groupe=combat\groupe[1]
							av\pv[1]=-1							
						EndIf
					Next
				EndIf
				;;
				
				update_dgts()
				update_chgt_equi()
				
				DrawImage curseur,MouseX(),MouseY()
				;affichage des chance de toucher la cible
				Color 200,200,200
				Text MouseX()+35,MouseY()+10,stat_cible$(1)
				Text MouseX()+35,MouseY()+25,stat_cible$(2)
				Text MouseX()+35,MouseY()+40,stat_cible$(3)
				For t=1 To 3
					stat_cible$(t)=""
				Next
				Flip
				compensation_lag()
			EndIf
		Next
		
		If sortie_combat<>0
			FreeImage image_load_combat
			SaveBuffer(FrontBuffer(),"temp.bmp")
			image_load_combat=LoadImage("temp.bmp")
		EndIf
		
	Wend
	
	;phase 3 profit !
	If combat_vainqueur=-1
		;victoire
		new_log("Vous êtes sortis victorieux de ce combat !",200,100,0)
		StopChannel ch_fight
		FreeSound mus_fight
		mus_fight=LoadSound("musiques\victoire.wav")
		ch_fight=PlaySound(mus_fight)
		ChannelVolume ch_fight,0.75*options#(5)
		If gfx_victoire=0 Then gfx_victoire=LoadImage("sprites\menu\victoire.bmp")
		
		;gains !!
		butin_junk=0
		butin_caps=0
		For combat.combat=Each combat
			If combat\num=num_combat
				For t=1 To 2
					If combat\groupe[t]<>-1
						For av.avatar=Each avatar
							If av\groupe=combat\groupe[t]
								butin_junk=butin_junk+max(av\junk,0)
								butin_caps=butin_caps+max(av\caps,0)
							EndIf
						Next
					EndIf
				Next
			EndIf
		Next
		player_caps=player_caps+butin_caps
		player_junk=player_junk+butin_junk
		new_log("Vous obtenez "+butin_caps+" caps et "+butin_junk+" junk.",200,100,0)
				
		time_dep=MilliSecs()
		sortie=0
		While sortie=0
			start_loop()
			lire_clavier()
			
			;A enlever
			If mode_debug=1 And KeyDown(keys(35,1))
				sortie=1
			EndIf
			;;
			
			DrawImage image_load_combat,0,0
			af#=minf((MilliSecs()-time_dep)/1000.,1)
			
			Color 5,5,5
			;Rect 0,screenheight*0.5-60*maxf(0.5,af#),2*af#*screenwidth,120*maxf(0.5,af#)
			;120*((maxf(0.75,af#)-0.75)*3+0.25)
			drawgrey(0,screenheight*0.5-60*((maxf(0.75,af#)-0.75)*3+0.25),min(2*af#*screenwidth,screenwidth),140*((maxf(0.75,af#)-0.75)*3+0.25),0.75)
			a_int=maxf(0,af#*3-1)*screenheight*0.25
			
			DrawImageRect gfx_victoire,(screenwidth-ImageWidth(gfx_victoire))*0.5,a_int-ImageHeight(gfx_victoire)*0.5,0,0,ImageWidth(gfx_victoire),ImageHeight(gfx_victoire)*0.5
			DrawImageRect gfx_victoire,(screenwidth-ImageWidth(gfx_victoire))*0.5,screenheight-a_int,0,ImageHeight(gfx_victoire)*0.5,ImageWidth(gfx_victoire),ImageHeight(gfx_victoire)*0.5

			If MilliSecs()-time_dep>2000
				af#=(Cos(timer_animation#*10)+2)*0.33
				Color 220*af#,180*af#,80*af#
				SetFont middle_font
				Text 400,screenheight*0.5+68,"Appuyez sur Espace pour continuer",1,1
				Color 220,180,80
				Text 400,screenheight*0.5+50,"Vous gagnez "+butin_caps+" caps et "+butin_junk+" junk",1,1
				If keys(12,2)=50 Then sortie=1
			EndIf
			If KeyHit(01) Then sortie=1

			Flip
			compensation_lag()
		Wend
	Else
		new_log("Vous avez été vaincu !",200,0,0)
		StopChannel ch_fight
		FreeSound mus_fight
		mus_fight=0
		FreeImage image_load_combat
		If event_action=0 Then script(12)
	EndIf
	
	If mus_fight<>0
		StopChannel ch_fight
		FreeSound mus_fight
		FreeImage image_load_combat
	EndIf
	mode_de_jeu=1
	ResumeChannel ch_bgm1
	
	For combat.combat=Each combat
		If combat\num=num_combat
			Delete combat
		EndIf
	Next
	
	;si ce combat vient d'un type "agresseur", animer le groupe associé
	agressed=0
	If groupe_agresseur<>0
		For grp.groupe=Each groupe
			If grp\num=groupe_agresseur Then grp\animation=3+10*grp\not_md2
		Next
	EndIf
	groupe_agresseur=0

	; libérer les assets
	Freeimage image_load_combat
	image_load_combat=0
	
	; supprimer les dégâts flottants restants
	For nb.nb_dgts=Each nb_dgts
		Delete nb
	Next
	
	;ranger les gearbots
	For grp.groupe=Each groupe
		For t=1 to 9
			If grp\formation[t]<-20 Then grp\formation[t]=0
		Next
	Next
	For av_bot.avatar=Each avatar
		If av_bot\num<-20 And av_bot\num>-31 ; les 10 gearbots disponibles (de -21 à -30)
			av_bot\groupe=0
		EndIf
	Next
	
	;Reset des bonus de défense
	For av.avatar=Each avatar
		av\defense=0
	Next

End function