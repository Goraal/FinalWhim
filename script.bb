Function script(script_num)
	If lock_action=0
		message_action$ =""
		For gr.groupe=Each groupe
			;If gr\script[1]=script_num
			If gr\num=var_script_int(1)
				temp$ = gr\nom_action$[Int(options#(7))]
				If temp$<>"" And temp$<>message_action$ Then message_action$ = temp$
			EndIf
		Next   
	Endif	
						
	Select script_num
		Case -1
			message_curseur$="Yaay ! I'm a butterfly !"
		Case -2
			message_curseur$="'' "+message_curseur$+" '', or so she said"
			
		
		Case -665 ; victoire 1
			FlushKeys
			FlushMouse
			StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\mauvaischoixA.mp3")
			PlaySound game_over_snd
			sortie=0
			While sortie<2
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				If sortie=0
					If Int(options#(7))=1 ;français
						sortie=fenetre_info("Félicitation, tu as fini le jeu !#N'hesite pas à nous laisser un mot sous github : https://github.com/Goraal/FinalWhim.")
					Else ;english
						sortie=fenetre_info("Congratulations, you finished the game! #Don't hesitate to leave us a note under github: https://github.com/Goraal/FinalWhim.")
					EndIf
				EndIf
				Flip
				If KeyHit(01) Then sortie=10
				If sortie<>0 Then sortie=10
			Wend
			quitter_jeu=1
			
		Case -666 ; Défaite 1
			FlushKeys
			FlushMouse
			StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\mauvaischoixA.mp3")
			PlaySound game_over_snd
			sortie=0
			While sortie<2
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				If sortie=0
				EndIf
				If sortie=0
					If Int(options#(7))=1 ;français
						sortie=fenetre_info("Stale essaye de se débrouiller sans vous et finit par se faire tuer.#Vous restez enfermés jusqu'à la fin de vos vies avec pour seule occupation de tourner dans la roue.")
					Else ;english
						sortie=fenetre_info("Stale tries to manage without you and ends up getting killed #You stay locked up for the rest of your lives with only one thing to do: turn around in the wheel.")
					EndIf
				EndIf
				Flip
				If KeyHit(01) Then sortie=10
				If sortie<>0 Then sortie=10
			Wend
			quitter_jeu=1
			
		Case -667 ; Défaite 2 dans Alpha
			FlushKeys
			FlushMouse
			StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\defeat.mp3")
			PlaySound game_over_snd
			sortie=0
			While sortie<2
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				;bmg=LoadSound("sons\Environnement\")
				If Int(options#(7))=1 ;français
					Select sortie
						Case 0
							sortie=fenetre_info("Vous avez été tué en combat.")
						Case 1
							sortie=sortie+fenetre_info("Ne désepérez pas, retentez votre chance. La plupart des combats sont faisable mais vous n'êtes pas à l'abri d'un coup de malchance. Vous pouvez aussi diminuer la difficulté dans le menu.")
					End Select
				Else ; english
					Select sortie
						Case 0
							sortie=fenetre_info("You were killed in fight.")
						Case 1
							sortie=sortie+fenetre_info("Don't despise, take your chance. Most fights are doable but you're not immune to a stroke of bad luck. You can also reduce the difficulty in the menu.")
					End Select
				Endif
				
				Flip
				If KeyHit(01) Then sortie=10
			Wend
			quitter_jeu=1
		
		Case -668 ; Faire 5000 tours de roue (Easter Egg)
			FlushKeys
			FlushMouse
			StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\mauvaischoixA.mp3")
			;PlaySound game_over_snd
			sortie=0
			While sortie<1
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				If Int(options#(7))=1 ;français
					sortie=fenetre_info("Vous avez fait 5000 tours de roue. Vous n'aviez rien de mieux à faire~?##Et vous croyez vraiment que les programmer n'ont rien de mieux à faire que de cacher des easter egg!...")
				Else
					sortie=fenetre_info("You made 5,000 turns. You didn't have anything better to do~? ## And you really believe that programming them has nothing better to do than to hide easter egg!...")
				Endif
				Flip
				If KeyHit(01) Then sortie=10
			Wend
			;quitter_jeu=1
			
		Case -669 ; Défaite 0 dans l'intro
			FlushKeys
			FlushMouse
			;StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\mauvaischoixA.mp3")
			;PlaySound game_over_snd
			sortie=0
			While sortie<1
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				If Int(options#(7))=1 ;français
					sortie=fenetre_info("Votre mort fut rapide et sans surprise~! Mais vus qu'on est pas méchant, on vous laisse retentez votre chance. ;)")
				Else
					sortie=fenetre_info("Your death was quick and unsurprising~! But considering we're not bad guys, we'll let you take your chances. ;)")
				Endif
				Flip
				If KeyHit(01) Then sortie=10
			Wend
		
		
		Case 1 ; Afficher "Appuyez sur Espace pour"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				;message_action$=var_script_str$(Int(options#(7)))				
				lock_action=1
			EndIf
					
		Case 4 ; ouvrir une porte
			For gr.groupe=Each groupe
				If gr\num=var_script_int(1)
					If lock_action=0
						lock_action=1
						;message_action$=var_script_str$(Int(options#(7)))				
				
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=49
							For p.porte = Each porte
								If p\groupe=gr\num
									p\etat=1
									;sendnetMsg(10,Str(p\num)+"#1#",player_id)
								EndIf
							Next
							gr\trigger[2]=4
							gr\trigger[1]=0
						EndIf
					EndIf
				EndIf
			Next
			

		Case 5 ; fermer une porte (pas de verrouillage)
			For gr.groupe=Each groupe
				If gr\num=var_script_int(1)
					If lock_action=0
						lock_action=1
						;message_action$=var_script_str$(Int(options#(7)))				
				
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=49
							For p.porte = Each porte
								If p\groupe=gr\num
									p\etat=0
									;sendnetMsg(10,Str(p\num)+"#0#",player_id)
								EndIf
							Next
							gr\trigger[2]=0
							gr\trigger[1]=4
						EndIf
						
												
					EndIf
				EndIf
			Next

		Case 6 ; activer_porte
			For gr.groupe=Each groupe
				If gr\num=var_script_int(1)
					If lock_action=0
						lock_action=1
						;message_action$=var_script_str$(Int(options#(7)))				
				
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=49
							For p.porte = Each porte
								If p\groupe=gr\num
									activer_porte(p\num)
								EndIf
							Next
						EndIf
						
												
					EndIf
				EndIf
			Next
	
		
		Case 7 ; chgt de map
			For gr.groupe=Each groupe
				If gr\num=var_script_int(1)
					If lock_action=0
						lock_action=1
						If player_in_control=0 
							event_action=leader_event_action
							choix_qcm=leader_choix_qcm
						Else
							leader_event_action=event_action
							leader_script=script_num
							leader_choix_qcm=choix_qcm
						EndIf
						
						;message_action$=var_script_str$(Int(options#(7)))				
						
						If event_action=1
							For gro.groupe=Each groupe
								If gro\num=-1
									If Int(options#(7))=1 ;français
										new_log("Vous changez de map")
									Else
										new_log("You change map")
									Endif
									;changer la map
									gro\map=gr\range_trigger[2]
									player_map=gro\map
									;selectionner l'entrée
									map_entrance=gr\range_trigger[3]
									;Goto
									mode_de_jeu=5
									event_action=0									
								EndIf
							Next
						EndIf
				
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=49
							;If gr\num=219
							;	If item_quest(-1,21) Or item_quest(-1,18) Or item_quest(-1,14) Or item_quest(-1,5) Or item_quest(-1,3)
							;		event_action=1
							;	Else
							;		PlaySound sons_menu(2)
							;		new_log("Vous avez besoin d'appartenir à une Caste ou de tenter l'épreuve des Chasseurs pour pouvoir aller au deuxième étage",200,20,20)
							;	EndIf
							;Else
								event_action=1
							;EndIf
						EndIf
					EndIf
				EndIf
			Next	

		Case 10 ; Afficher des informations
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				fenetre_info(var_script_str$(1))
				call_activator(901)
			EndIf
			
		Case 11 ; Verifier si les agressifs ont une ligne de vue (partie 1 -pré Update World) ; je crois pas que ce soit encore utilisé
			If player_in_control=1
				For gr.groupe=Each groupe
					If gr\detector<>0
						PositionEntity gr\detector,gr\position#[1],gr\position#[2]+0.45,gr\position#[3]
						ResetEntity gr\detector		
						;vérifier qu'on est à une distance raisonnable (10m)
						If (gr\position#[1]-EntityX#(pl_grp_pivot))*(gr\position#[1]-EntityX#(pl_grp_pivot))+(gr\position#[3]-EntityZ#(pl_grp_pivot))*(gr\position#[3]-EntityZ#(pl_grp_pivot))<100
							;vérifier l'angle de 2x90°
							alpha#=pointeryaw(gr\position#[1],gr\position#[2],EntityX#(pl_grp_pivot),EntityZ#(pl_grp_pivot))
							If Abs(mod360(alpha#-gr\position#[4]))<90
								;envoyer le detector
								PositionEntity gr\detector,EntityX#(pl_grp_pivot),EntityY#(pl_grp_pivot),EntityZ#(pl_grp_pivot)
							EndIf
						EndIf
					EndIf
				Next
			EndIf
			
		Case 12 ; défaite 'définitive'
			FlushKeys
			FlushMouse
			StopChannel ch_bgm
			Cls
			Color 255,255,255
			game_over_snd=LoadSound("sons\defeat.mp3")
			PlaySound game_over_snd
			sortie=0
			teleporter(-1,-100,-100,-100)
			While sortie<3
				Cls
				lire_clavier()
				timer_animation#=timer_animation#+1
				delta_frame=20
				If Int(options#(7))=1 ;français
					Select sortie
						Case 0
							sortie=fenetre_info("Vous avez été tué en combat.#Vos cadavres seront sûrement fouillés puis probablement jetés dans les égouts pour être mangés par les rats.")
						Case 1
							sortie=1+fenetre_info("Vous allez à présent être renvoyé vers le menu principal où vous pourrez charger votre dernière sauvegarde, si vous avez pensé à sauvegarder ... #Et si le programmeur n'a pas décidé de la supprimer ! MOUAHAHAHAHA !")
						Case 2
							sortie=3
					End Select
				Else
					Select sortie
						Case 0
							sortie=fenetre_info("You were killed in action. #Your corpses will surely be searched and probably thrown into the sewers to be eaten by rats.")
						Case 1
							sortie=1+fenetre_info("You will now be taken back to the main menu where you will be able to load your last backup, if you thought about saving... #And if the programmer didn't decide to delete it! MOUAHAHAHAHA!")
						Case 2
							sortie=3
					End Select
				EndIf
				Flip
				If KeyHit(01) Then sortie=10
			Wend
			quitter_jeu=1

		Case 13 ; défaite 'miséricordieuse'
		
		
		
		Case 99 ; lancer le combat de boss
		
.forgeron		
		Case 100 ; message_action$="visiter la boutique du Forgeron"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If event_action>0 Then var_pl_grp(num_player,"animation",True,1):animation()
						
				Select event_action
					Case 0
						call_activator(901);version test
						call_activator(505);forgeron du garage
						If keys(12,2)=50
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Case 1
						mode_de_jeu=4
						event_action=3
					Case 3
						event_action=0
						
				End Select
			EndIf


		Case 101 ; Stale 2ème partie <Devant l'ascenseur> message_action$="parler avec Stale"
			If lock_action=0
				lock_action=1
				call_activator(101)
	
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				For gr.groupe=Each groupe
					If gr\num=101
						temp_int=gr\range_trigger[2]
					EndIf
				Next
				
				;If temp_int=0
					Select event_action
					Case 0
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Case 100
						For gr.groupe=Each groupe
							If gr\num=101
								gr\range_trigger[2]=1
							EndIf
						Next
						maj_map(1,4,1)
						AnimateSteamPorte(3,0.2*0.02*2500/Float(nb_frame),1)
						PlaySound LoadSound("sons\environnement\sfx_steam_5sec.wav")
						event_action=0
					Case 106
						For gr.groupe=Each groupe
							If gr\num=101
								gr\trigger[2]=6
								gr\trigger[1]=0
							EndIf
						Next
						event_action=0

					Default
       					event_action=Main_ScriptXml(message_action$,event_action)
					End Select

				;Else
				If temp_int>10
					If event_action
						If Int(options#(7))=1 ;français
							mess$="Qu'est-ce que tu attends~? Vas-y~! Il n'y a plus rien à faire ici et la liberté t'attend dehors~!"
						Else
							mess$="What are you waiting for? Go ~! There's nothing left to do here and freedom awaits you outside!"
						EndIf
						If discussion(1,1,"Stale",mess$) And player_in_control Then event_action=0
					Else
						For gr.groupe=Each groupe
							If gr\num=script_num
								temp$ = gr\nom_action$[Int(options#(7))]
								If temp$<>"" And temp$<>message_action$ Then message_action$ = temp$
							EndIf
						Next 
					EndIf
				EndIf
			EndIf

			
		Case 102 ; message_action$="coller l'oreille contre le mur"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
				    Case 0
				        If keys(12,2)=50 And player_in_control And chat_mode=0
				            keys(12,2)=min(49,keys(12,2))
				            interaction_avec=var_script_int(1)
				            interaction_script=var_script_int(2)
				            event_action=1
				            disc_len#=0       
				        EndIf
				    Case 9
						;ouverture de la porte
						PlaySound LoadSound("sons\environnement\little_door_open.mp3")
						activer_porte(101)						
						;E1 ne marche plus
						For g.groupe=Each groupe
							If g\num=102
								g\trigger[1]=0
								g\trigger[4]=0
								If g\activator<>0
									FreeEntity g\activator
									g\activator=0
								EndIf
								event_action=0
								maj_map(1,3,1)
								;delete_groupe(102)
							EndIf
						Next
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
		Case 103 ; affichage Toc Toc
			If event_action=0 And map_stat(1,3)=0
				For g.groupe=Each groupe
					If g\num=102
						If g\activator=0
							load_groupe(102)
							g\script[3]=MilliSecs()
							g\script[2]=g\script[3];-4000
						Else
							g\script[3]=MilliSecs()
							If g\script[3]-g\script[2]>10000
								If g\trigger[2]<>0 Then EmitSound g\trigger[2],g\pivot
								g\script[2]=MilliSecs()
							EndIf
							EntityAlpha g\activator,maxf#(minf#(1,2-(g\script[3]-g\script[2])*0.001),0)
						EndIf
					EndIf
				Next
			EndIf
		Case 104 ; message_action$="discuter avec la fillette"
			If lock_action=0
				lock_action=1
				

		        If map_stat(1,1)=2 
					If Int(options#(7))=1 Then message_action$="fouiller la fillette"
					If Int(options#(7))=2 Then message_action$="search the little girl"
				EndIf
				
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf

				Select event_action
					Case 0
				        call_activator(103)
				       	If keys(12,2)=50 And player_in_control And chat_mode=0
				            keys(12,2)=min(49,keys(12,2))
				            interaction_avec=var_script_int(1)
				            interaction_script=var_script_int(2)
				            event_action=1
				            disc_len#=0       
				        EndIf
				       
				    Case 115
				        g_bHUDactif=1
						;reponse=fenetreqcm(2,"Il s'agit de votre premier combat. Voir le tutoriel ?","Oui","Non")
				        ;If reponse=1 Then tutoriel(1):event_action=116
				        ;If reponse=2 Then event_action=116
						event_action=116
				   
				    Case 116 ; lancer combat
				        event_action=117
				        For av.avatar=Each avatar ;mettre la filette dans le groupe des PJs
				            If av\num=11
				                av\groupe=-1
				            EndIf
				        Next
				                               
				        msg$="-1#103#"
				        analyse(20,msg$,player_id,master_id) ; lancer le combat
				       
				        For gr.groupe=Each groupe ; tempo pour éviter de calculer l'event_action suivant avant même de commencer le combat
				            If gr\num=103
				                gr\script[2]=MilliSecs()
				            EndIf
				        Next
				
				    Case 117
				        For gr.groupe=Each groupe
				            If gr\num=103
				                If MilliSecs()-gr\script[2]>3000
				                    If sortie_combat=1;victoire des PJs
				                        For av.avatar=Each avatar
				                            If av\num=11
				                                If av\pv[1]>0;filette vivante
				                                    event_action=118
				                                Else;filette morte
				                                    event_action=167
				                                EndIf
				                                av\groupe=108
				                            EndIf
				                        Next
				                        var_pl_av(num_player,"capac[8]",1,max(var_pl_av(num_player,"capac[8]"),1))   
				                    Else ; défaite définitive
				                        script(12)
				                    EndIf                                   
				                EndIf
				            EndIf
				        Next                           
				    Case 118
				        PauseChannel ch_bgm1
				        PlaySound sons_menu(10)
						If Int(options#(7))=1 
							mess$="Vous avez obtenu une poignée de steamilles !"
						Else
							mess$="You got yourself some steamilles!"
						Endif
				        new_log(mess$,20,200,20)
				        event_action=119
				    Case 119
				        If fenetre_info(mess$)
				            event_action=0
				            maj_map(1,1,3)
				            ResumeChannel ch_bgm1
				            item_quest(Abs(var_pl_grp(num_player,"num")),1,1)
				        EndIf
				        ChangeAvancement("Inventaire/Steamille:1")
					Case 120
				        For gr.groupe=Each groupe
				            If gr\num=103
								gr\position#[2]=-100
				                PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
				            EndIf
				        Next
						event_action=0
				    Case 165
						g_bHUDactif=1
				        If Int(options#(7))=1 ;français
							reponse=fenetreqcm(2,"Il s'agit de votre premier combat. Voir le tutoriel ?","Oui","Non")
						Else
							reponse=fenetreqcm(2,"This is your first fight. See the tutorial?","Yes","No")
						Endif
				        If reponse=1 Then tutoriel(1):event_action=166
				        If reponse=2 Then event_action=166
				    Case 166 ; duel
				        event_action=167
				        msg$="-1#108#"
				        analyse(20,msg$)
				        For gr.groupe=Each groupe
				            If gr\num=103
				                gr\script[2]=MilliSecs()
				            EndIf
				        Next
				    Case 167
				        For gr.groupe=Each groupe
				            If gr\num=103
				                If MilliSecs()-gr\script[2]>3000
				                    If sortie_combat=1
				                        event_action=0
				                        maj_map(1,1,2)
				                        gr\animation=3
				                        For av.avatar=Each avatar
				                            If av\num=11
				                                av\groupe=108
				                            EndIf
				                        Next
				                    Else
				                        maj_map(1,5,0)
				                        script(12)
				                    EndIf
				                EndIf
				            EndIf
				        Next
					Case 400
						If Int(options#(7))=1 ;français
							mess$="Vous avez obtenu une poignée de steamilles et une arme!"
						Else
							mess$="You got yourself some steamilles and a weapon!"
						Endif
				        If fenetre_info(mess$)
							item_give(1,1);glaive de chasseur
							item_quest(Abs(var_pl_grp(num_player,"num")),1,1)
							ChangeAvancement("Inventaire/Steamille:OUI")
							event_action=0
						EndIf
					Case 401
					
						If Int(options#(7))=1 ;français
							mess$="Vous avez obtenu une poignée de steamilles et une arme!"
						Else
							mess$="You got yourself some steamilles and a weapon!"
						Endif
				        If fenetre_info(mess$)
							item_give(1,1);glaive de chasseur
							item_quest(Abs(var_pl_grp(num_player,"num")),1,1)
							ChangeAvancement("Inventaire/Steamille:OUI")
							event_action=403
						EndIf
					Case 402
						If Int(options#(7))=1 ;français
							mess$="Vous avez obtenu une poignée de steamilles et une arme!"
						Else
							mess$="You got yourself some steamilles, an armor and a weapon!"
						Endif
				        If fenetre_info(mess$)
							item_give(1,1);glaive de chasseur
							item_give(1,100);armure fillette
							item_quest(Abs(var_pl_grp(num_player,"num")),1,1)
							ChangeAvancement("Inventaire/Steamille:OUI")
							event_action=403
						EndIf
					Case 403
				        For gr.groupe=Each groupe
				            If gr\num=103
								gr\position#[2]=-100
				                PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
				            EndIf
				        Next
						event_action=0

			   		Default
        				event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
			
		Case 105 ; message_action$="tripoter la serrure SteamImpact(tm)"
			
			If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
                
				Select event_action
					Case 0                       
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
                    Case 100
                        maj_map(1,2,1)
                        item_quest(var_pl_av(num_player,"num"),1,2)
                        ;truc de malade avec la porte
                        PlaySound LoadSound("sons\environnement\Big_Door_Open.mp3")
                        PositionEntity cam,9.5,1.5,12,1
                        RotateEntity cam,0,180,0,1
                        new_smoke_source(101,6.5,0,8.5,25,1000,7,0.2,0.5,90,0,5,360,255,255,255,7000,1,0)
                        new_smoke_source(102,12.5,0,8.5,25,1000,7,0.2,0.5,90,0,5,360,255,255,255,7000,1,0)
                        action=0
                        timer_event=0
                        While action=0
                            start_loop()
                            animation()
                            timer_event=timer_event+delta_frame
                            If timer_event>900 And event_action=100
                                AnimateMetaPorte(3,0.2*0.02*2500/Float(nb_frame))
                                maj_map(1,2,2)
                                event_action=6
                            EndIf
                            If timer_event>8000 Or KeyHit(01) Then action=1               
                            update_smoke_source()
                            update_smoke()
                            UpdateWorld
                            RenderWorld
							HUD(0)
                            Flip
                            compensation_lag()
                        Wend
                        PositionEntity cam,0,0,0,0
                        event_action=0
                        For gr.groupe=Each groupe
                            If gr\num=104
                                gr\trigger[1]=0
                            EndIf
                        Next                   
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf


		Case 106 ; Stale se déplace vers la bonne porte (serveur)
			
		Case 107 ; panneau d'information	message_action$="lire l'affiche"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
				If event_action>0 Then var_pl_grp(num_player,"animation",True,1):animation()
				
				If Int(options#(7))=1 ;français
					Select event_action
						Case 0
							
							
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 1
							g_bHUDactif=0
							mess$="C'est une affiche qui est là depuis que vous êtes~là, et date même certainement d'avant. Elle donne diverses informations pour aider les nouveaux à s'adapter à leur nouvel environnement."
							If fenetre_info(mess$) And player_in_control Then event_action=2
						Case 2
							g_bHUDactif=0
							mess$="Bienvenue,##Vous êtes nouveau, perdu~? Voici quelques informations utiles pour que votre séjour se passe pour le mieux~:"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=3
						Case 3
							g_bHUDactif=0
							mess$=" — Votre statut : Esclave# — Votre travail : Faire tourner la grande roue blanche# — Votre rémunération :#     - Un repas (breuvage compris) tous les 5 000 tours#     - La Liberté* pour 10 000 000 tours# — En cas de problème : Autonomie !"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=4
						Case 4
							g_bHUDactif=0
							mess$="La société FactoryTech vous souhaite un agréable séjour et vous rappelle que vous n'êtes pas irremplaçable.##                                 FactoryTech##(*Offre soumise à conditions)"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=0					
					End Select
				EndIf
				If Int(options#(7))=2 ;english
					Select event_action
						Case 0
							message_action$="read the message"
							
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 1
							g_bHUDactif=0
							mess$="It's a message that's been there since you were there, and it's probably even dated before. It provides a variety of information to help newcomers adapt to their environment."
							If fenetre_info(mess$) And player_in_control Then event_action=2
						Case 2
							g_bHUDactif=0
							mess$="Welcome, ##You're new, lost~? Here are some useful information to help you make your stay a success:"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=3
						Case 3
							g_bHUDactif=0
							mess$=" — Your status: Slave# - Your work: Rotate the big white wheel# - Your remuneration: # - One meal (brewery included) every 5,000 laps# - Freedom* for 10,000,000 laps# - In case of problems: Autonomy!"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=4
						Case 4
							g_bHUDactif=0
							mess$="The FactoryTech company wishes you a pleasant stay and reminds you that you are not irreplaceable ## FactoryTech## (*Offer subject to conditions)"
							If discussion(2,1,"Message",mess$) And player_in_control Then event_action=0					
					End Select
				EndIf
			EndIf
		
		Case 108 ; roue dans la prison message_action$="monter dans la roue"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
				If event_action>0 Then var_pl_grp(num_player,"animation",True,1):animation()
				Select event_action
					Case 0
						
						
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							event_action=1		
						EndIf
					Case 1
						For gr.groupe=Each groupe
							If gr\num=107
								
								While event_action<>0
									start_loop()
									lire_clavier()
									Cls
									
									
									gr\script[3]=gr\script[3]+72*delta_frame*0.001
									RotateEntity gr\script[2],0,gr\script[3],0
									TurnEntity gr\script[2],90,0,0,1
									If gr\script[3]>360
										gr\script[3]=gr\script[3]-360
										gr\range_trigger[2]=gr\range_trigger[2]+1
									EndIf
									
									var_pl_grp(num_player,"animation",True,2)
									PositionEntity pl_grp_pivot,3,0.45,0.75,1
									RotateEntity pl_grp_manikin,0,270,0,1
									RotateEntity pl_grp_pivot,0,0,0
									ResetEntity pl_grp_pivot
									
									PositionEntity cam,3,1.5,5,1
									RotateEntity cam,0,180,0,1
																	
									If keys(12,2)=50 And player_in_control And chat_mode=0
										keys(12,2)=min(49,keys(12,2))
										PositionEntity pl_grp_pivot,3,0.5,2.5,1
										ResetEntity pl_grp_pivot
										event_action=0
									EndIf
									
									UpdateWorld
									RenderWorld
									
									
									If Int(options#(7))=1 
										message_action$="}"+min_str(Str(gr\range_trigger[2]),4," ",1)+"Appuyez sur Espace pour quitter la roue"
									Else
										message_action$="}"+min_str(Str(gr\range_trigger[2]),4," ",1)+"Press Space to exit the wheel"
									Endif
									g_bHUDactif=1
									
									If gr\range_trigger[2]>5000 Then script(-668)
																		
									If quitter_jeu=1
										event_action=0
										quitter_jeu=0
										gr\range_trigger[2]=0
									EndIf
																		
									animation()
									HUD(g_bHUDactif)
									
									Flip
									
									compensation_lag()
								Wend
							EndIf
						Next
						
										
					End Select
			EndIf
	
		Case 109 ; Message tuto déplacement ("Utilisez Z,Q,S,D pour vous déplacer")
			If lock_action=0
				lock_action=1
				message_action$="~"
				
				For gr.groupe=Each groupe
					If gr\num=109
						If dist2d(pl_grp_pivot,gr\pivot)>1.8
							gr\trigger[1]=0
						EndIf
					EndIf
				Next
			EndIf

			
		Case 110 ; test script		 message_action$="test script"
			If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
                
				Select event_action
					Case 0                       
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
						g_bHUDactif=1                  
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
			
		Case 111 ; +duction	
				;new_log("test11")
				start_loop("Head_Menu")
				If event_action>0 Then var_pl_grp(num_player,"animation",True,1):animation()
				If Int(options#(7))=1 
					message_action$="lancer l'intro"
				Else
					message_action$="start the intro"
				Endif
				g_bHUDactif=0
				Select event_action
					Case 0  
						event_action=1
						event_action=Main_ScriptXml(message_action$,event_action)
					Case 801
						selection_avatar(1,4)
						event_action=2
					Case 802
						selection_avatar(3,7)
						event_action=3
					Case 803
						selection_avatar(2,1)
						event_action=4 
					Case 804
						rechoisir_avatar()
				;		event_action=805
				;	Case 805
						g_bHUDactif=1
						event_action=0 
						cheminImage$=""
						If fond_animation<>0
							FreeImage fond_animation
							fond_animation=0
						EndIf
						quitter_jeu=1
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
				End Select
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;MAP02			
		Case 202 ; discussion avec les gardes message_action$="discuter avec les gardes"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							call_activator(202)
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Default
						event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf

		Case 203 ; 				message_action$="discuter avec la secrétaire"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf



				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(203)
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf

		Case 205 ; 				message_action$="discuter avec Stan"
.Stan			
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(205)
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf

					Case 100
				        g_bHUDactif=1
						event_action=101
				   
				    Case 101 ; lancer combat
				        event_action=102
		                               
				        analyse(20,"-1#2051#",player_id,master_id)
				       
				        For gr.groupe=Each groupe
				            If gr\num=205 
				                gr\script[2]=MilliSecs()
				            EndIf
				        Next
						
						sortie_combat=0
				
					Case 102
				        For gr.groupe=Each groupe
				            If gr\num=205 
				                If MilliSecs()-gr\script[2]>3000
				                    If sortie_combat=1
				              			event_action=4
				                    Else ; défaite
				                        event_action=5
				                    EndIf                                   
				                EndIf
				            EndIf
				        Next
				
					Case 110
				        ;item_quest(Abs(var_pl_grp(num_player,"num")),1,1) TO DO objet clé +1
						event_action=0
						
					Default
						event_action=Main_ScriptXml(message_action$,event_action)					
				End Select
			EndIf

		Case 206 ; Stan (ennemi)
		
		Case 207 ; Armurier 1 (couteau) 				message_action$="discuter avec le spécialiste des armes de corps à corps"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(207)
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Case 100
						;gain équipement
						item_give(1,7);grande hache
						item_give(1,101);"Armure de cuir"
						If TestAvancement("Stan/Postuler:CHOIX3")
							ChangeAvancement("Stan/Postuler:CHOIX2")
							event_action=0
						ElseIf TestAvancement("Stan/Postuler:CHOIX2")
							ChangeAvancement("Stan/Postuler:CHOIX1")
							event_action=0
						Else							
							ChangeAvancement("Stan/Postuler:COMBAT")
							event_action=101
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
		Case 208 ; Armurier 2 (Cobalt SAA) 				message_action$="discuter avec le spécialiste des armes de tir"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(208)
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Case 100
					;gain équipement
						item_give(1,13);double burg
						If TestAvancement("Stan/Postuler:CHOIX3")
							ChangeAvancement("Stan/Postuler:CHOIX2")
							event_action=0
						ElseIf TestAvancement("Stan/Postuler:CHOIX2")
							ChangeAvancement("Stan/Postuler:CHOIX1")
							event_action=0
						Else							
							ChangeAvancement("Stan/Postuler:COMBAT")
							event_action=101
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
		Case 209 ; Armurier 3 (Armure) 				message_action$="discuter avec le spécialiste des armures"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(209)
						If keys(12,2)=50 And event_action=0 And player_in_control And chat_mode=0; Return hit
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
					Case 100
						;gain équipement
						item_give(1,2);épée médiocre simple
						item_give(1,103);"Armure de cuir élimée"
						If TestAvancement("Stan/Postuler:CHOIX3")
							ChangeAvancement("Stan/Postuler:CHOIX2")
							event_action=0
						ElseIf TestAvancement("Stan/Postuler:CHOIX2")
							ChangeAvancement("Stan/Postuler:CHOIX1")
							event_action=0
						Else							
							ChangeAvancement("Stan/Postuler:COMBAT")
							event_action=101
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
.porte			
		Case 210 ; porte principale engrenage 				message_action$="examiner la porte de la production"

			
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
		
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							If item_quest(-1,14) Then event_action=11
						EndIf
					Case 100
							event_action=101
							If ch_bgm<>0 Then StopChannel ch_bgm
							If bgm<>0 FreeSound bgm
							bgm=PlaySound LoadSound("sons\environnement\little_Door_Open.mp3")
							If bgm<>0
								LoopSound bgm
								ch_bgm=PlaySound(bgm)
								ChannelVolume ch_bgm,1*options#(5)
								ChannelPan ch_bgm,0
							EndIf
					Case 101
						If player_in_control
							;maj_map(2,6,30000)
							activer_porte(201,1)
							event_action=0
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
					
				End Select
			EndIf
		
		Case 211 ; porte principale engrenage (coté usine) 				message_action$="examiner la porte de la production"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
						EndIf
					Case 100
							event_action=101
							If ch_bgm<>0 Then StopChannel ch_bgm
							If bgm<>0 FreeSound bgm
							bgm=PlaySound LoadSound("sons\environnement\little_Door_Open.mp3")
							If bgm<>0
								LoopSound bgm
								ch_bgm=PlaySound(bgm)
								ChannelVolume ch_bgm,1*options#(5)
								ChannelPan ch_bgm,0
							EndIf
					Case 101
						If player_in_control
							;maj_map(2,6,30000)
							activer_porte(201,1)
							event_action=0
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)				
				End Select
			EndIf
		
		Case 212 ; enigme engrenage				message_action$="examiner l'énigme"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
					;Case 1 ;Leet speak : 1337 5|*34|< : langage de l'élite
						;mess$="_/3   |\|415   |)3   (0|_||_3|_||2   |\|01|2 #_/3   \/15   3|\|   374|\|7   |20|_|63 #37   _/3   |\/|3|_||25   3|\|   |)3\/3|\|4|\|7   6|215"
						;		JE    NAIS     DE   COULEUR        NOIR      JE     VIS   EN     ETANT     ROUGE    ET     JE     M E U R T   E N     DE VE N A N T   G RIS
						;If fenetre_info(mess$) And player_in_control Then event_action=0		
				End Select
			EndIf
			
		Case 213 ; porte enigme 				message_action$="examiner le mechanisme de la porte"
			; solution : CHARBON = (|-|4|280|\|
			; Car C=( A=4 R=|2 O=0 N=|\| (cf enigme)  ainsi que   H=|-| B=8 (cf aide => TO DO) 
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							mess_enigme$=""
							
						EndIf
						
					Case 2
						event_action=Main_ScriptXml(message_action$,2)
						mess_enigme$=xmlNodeDataGet$(xmlNodeFind("Enigme",xml_Avancement))
						; DebugLog "Mess_enigme='"+mess_enigme$+"'"
						mess_enigme_new$=input_text(mess_enigme$)
						If mess_enigme$<> mess_enigme_new$
							ChangeAvancement("Avancement/Enigme:"+mess_enigme_new$)
							nomDuScriptEnCours="" ;pour obliger à recharger la variable Enigme
						EndIf
						
					Case 100
						mess_enigme$=xmlNodeDataGet$(xmlNodeFind("Enigme",xml_Avancement))
						If Trim$(mess_enigme$)="(|-|4|280|\|" or Trim$(mess_enigme$)="(04|_"
							event_action=102
						Else
							; DebugLog "2/"+mess_enigme$+"/"
							event_action=3
						EndIf
						
					Case 102
						event_action=103
						If ch_bgm<>0 Then StopChannel ch_bgm
						If bgm<>0 FreeSound bgm
						bgm=PlaySound LoadSound("sons\environnement\little_Door_Open.mp3")
						If bgm<>0
							LoopSound bgm
							ch_bgm=PlaySound(bgm)
							ChannelVolume ch_bgm,1*options#(5)
							ChannelPan ch_bgm,0
						EndIf
						
					Case 103
						If player_in_control
							activer_porte(202,1)

							For gr.groupe=Each groupe
					            If gr\num=213
									gr\position#[2]=-100
					                PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
					            EndIf
					        Next

							event_action=0
						EndIf
						
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf

			
		Case 214 ; porte secondaire de l'engrenage message_action$="examiner la porte"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							If item_quest(-1,10)
								event_action=2
							Else
								event_action=1
							EndIf	
						EndIf
					Case 100
							event_action=101
							If ch_bgm<>0 Then StopChannel ch_bgm
							If bgm<>0 FreeSound bgm
							bgm=PlaySound LoadSound("sons\environnement\little_Door_Open.mp3")
							If bgm<>0
								LoopSound bgm
								ch_bgm=PlaySound(bgm)
								ChannelVolume ch_bgm,1*options#(5)
								ChannelPan ch_bgm,0
							EndIf
							
					Case 101
							If player_in_control
								activer_porte(203,1)
								event_action=0
							EndIf

					Default
						event_action=Main_ScriptXml(message_action$,event_action)			
				End Select
			EndIf
			
		Case 215 ; Poste message_action$="examiner la porte"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
				
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							If item_quest(-1,11)
								event_action=2
							Else
								event_action=1
							EndIf	
						EndIf
					Case 100
							event_action=101
							If ch_bgm<>0 Then StopChannel ch_bgm
							If bgm<>0 FreeSound bgm
							bgm=PlaySound LoadSound("sons\environnement\little_Door_Open.mp3")
							If bgm<>0
								LoopSound bgm
								ch_bgm=PlaySound(bgm)
								ChannelVolume ch_bgm,1*options#(5)
								ChannelPan ch_bgm,0
							EndIf
							
					Case 101
							If player_in_control
								activer_porte(204,1)
								event_action=0
							EndIf	
					Default
						event_action=Main_ScriptXml(message_action$,event_action)			
				End Select
			EndIf

		Case 216 ;message_action$="examiner la Trieuse"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf
				
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				
				Select event_action
					Case 0
						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							If item_quest(-1,11)
								event_action=2
							Else
								event_action=1
							EndIf	
						EndIf
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select		
			EndIf

		Case 217 ; 				message_action$="discuter avec l'Infirmière"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(217)

						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							event_action=1
							disc_len#=0		
						EndIf
						
					Case 100
						If player_caps > 30
							player_caps = player_caps-30
							redonner_pv_pj(100,0)
							event_action = 2
						Else
							event_action = 3
						EndIf
						
					Case 101
						redonner_pv_pj(1,2)
						event_action = 4
						
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
					
				End Select
			EndIf	
					
		Case 218 ;				message_action$="discuter avec Teddy"
			If lock_action=0
				lock_action=1

				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				Select event_action
					Case 0
						call_activator(218)

						If keys(12,2)=50 And player_in_control And chat_mode=0
							keys(12,2)=min(49,keys(12,2))
							interaction_avec=var_script_int(1)
							interaction_script=var_script_int(2)
							;If player_in_control=1
							;	If item_quest(-1,9)
							;		For p.porte=Each porte
							;			If p\num=202
							;				If p\etat=1 Then event_action=1
							;			EndIf
							;		Next
							;		If event_action=0 Then event_action=4
							;	Else
							;		event_action=4
							;	EndIf									
							;EndIf
							event_action=1
							disc_len#=0		
						EndIf
					Case 100
						ChangeAvancement("PJ/Triche:OUI")
						event_action=0
					Default
						event_action=Main_ScriptXml(message_action$,event_action)
				End Select
			EndIf
		
		Case 219 ; entrée bateau 				message_action$="monter à l'étage supérieur"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							;call_activator(402)
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 100
							For gr.groupe=Each groupe ; Transforme le Script dialogue "monter à l'étage supérieur" en changement de map
								If gr\num=219
									gr\script[1]=7 
									gr\range_trigger[2]=4 ;N° map
									gr\range_trigger[3]=4 ;N° entrance
									Exit
								EndIf
							Next
							event_action=0
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;EXTERIEUR;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		Case 402 ; entrée usine				message_action$="rentrer dans l'usine"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							call_activator(402)
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
		
		Case 403 ; sortie du jeu				message_action$="sortir de FactoryTech"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 100
							;COMBAT A METTRE ICI
							event_action=101
							msg$="-1#4030#"
				        	analyse(20,"-1#4030#") ; lancer le combat
							For gr.groupe=Each groupe
				            	If gr\num=403 
				                	gr\script[2]=MilliSecs()
				       	    	EndIf
				       		Next
							
							sortie_combat=0
							
						Case 101 ;victoire/défaite
							For gr.groupe=Each groupe
					            If gr\num=403
									If MilliSecs()-gr\script[2]>3000
				                    	If sortie_combat=1
				              				event_action=6
				                    	Else ; défaite définitive
				                    	    script(12)
				                    	EndIf                                   
				                	EndIf
					            EndIf
							Next
						

						Default
						event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				
	Case 405 ; cadavre				message_action$="examiner le cadavre"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							call_activator(405)
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 100
							item_give(1,9);pieu = lance
							player_caps=player_caps+27
							event_action=0
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				
		Case 406 ; MJ Monsieur J Mister J				message_action$="regarder la machine humanoïde"
			If lock_action=0 Or (leader_script=script_num And player_in_control=0)
				lock_action=1
				If player_in_control=0 
					event_action=leader_event_action
					choix_qcm=leader_choix_qcm
				Else
					leader_event_action=event_action
					leader_script=script_num
					leader_choix_qcm=choix_qcm
				EndIf

					If event_action>0 
						var_pl_grp(num_player,"animation",True,1):animation()
						g_bHUDactif=0
					EndIf
					Select event_action
						Case 0
							call_activator(405)
							If keys(12,2)=50 And player_in_control And chat_mode=0
								keys(12,2)=min(49,keys(12,2))
								interaction_avec=var_script_int(1)
								interaction_script=var_script_int(2)
								event_action=1
								disc_len#=0		
							EndIf
						Case 100
							;COMBAT A METTRE ICI
							event_action=101
							msg$="-1#4060#"
				        	analyse(20,"-1#4060#") ; lancer le combat
							For gr.groupe=Each groupe
				            	If gr\num=406 
				                	gr\script[2]=MilliSecs()
				       	    	EndIf
				       		Next
							
							sortie_combat=0
							
						Case 101 ;victoire/défaite MJ
							For gr.groupe=Each groupe
					            If gr\num=406
									If MilliSecs()-gr\script[2]>3000
				                    	If sortie_combat=1
				              				event_action=102
				                    	Else ; défaite définitive
				                    	    script(12)
				                    	EndIf                                   
				                	EndIf
					            EndIf
							Next
						
						Case 102 ; Suppression de MJ
							For gr.groupe=Each groupe
					            If gr\num=406
									;supprimer l'interaction
									gr\position#[2]=-100
					                PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
									;supprimer le manikin
									FreeEntity gr\manikin[1]
									gr\manikin[1]=0
									gr\manikin[2]=0
									gr\manikin[3]=0
								EndIf
							Next
							event_action=2
							
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				
		Case 499 ; conseil de sauvegarder
			If lock_action=0 Or (leader_script=script_num And player_in_control=0); dernière condition a enlever
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
				If Int(options#(7))=1 ;français
					Select event_action
						Case 0
							If mode_debug=1
								event_action=4
							Else
								event_action=1
							EndIf
						Case 1
							mess$="Vous arrivez dans une zone particulièrement dangereuse !#Nous vous conseillons vivement de sauvegarder."
							If fenetre_info(mess$) then event_action=2
						Case 2
							mess$="Pour cela, ouvrez le menu joueur en appuyant sur F1, ou en appuyant sur le bouton ''Menu (F1)'' en bas de l'écran (appuyez sur Alt pour faire apparaitre la souris)."
							If fenetre_info(mess$) then event_action=3
						Case 3
							mess$="Une fois dans le menu, cliquez sur l'onglet ''Système'' puis sur le bouton Sauvegarder.#Attention, vous n'avez qu'un seul fichier de sauvegarde et toute nouvelle sauvegarde effacera les données précédentes."
							If fenetre_info(mess$) then event_action=4
						Case 4
							For gr.groupe=Each groupe
								If gr\num=499
									gr\trigger[1]=0
								EndIf
							Next
							event_action=0
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				If Int(options#(7))=2 ;english				
					Select event_action
						Case 0
							If mode_debug=1
								event_action=4
							Else
								event_action=1
							EndIf
						Case 1
							mess$="You have just arrived in a particularly dangerous area! #We strongly advise you to save."
							If fenetre_info(mess$) then event_action=2
						Case 2
							mess$="To do this, open the player menu by pressing F1, or by pressing the'' Menu (F1)'' button at the bottom of the screen (press Alt to show the mouse)."
							If fenetre_info(mess$) then event_action=3
						Case 3
							mess$="Once in the menu, click on the'' System'' tab and then click on the Save button. #Beware, you only have one backup file and any new backups will erase the previous data."
							If fenetre_info(mess$) then event_action=4
						Case 4
							For gr.groupe=Each groupe
								If gr\num=499
									gr\trigger[1]=0
								EndIf
							Next
							event_action=0
						Default
							event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				Endif
			EndIf
		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;CAMPS;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		Case 502 ; sentinelle					message_action$="discuter avec la sentinelle"; 	
		If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf

                
				Select event_action
					Case 0
						call_activator(502)                     
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
						g_bHUDactif=1
					Case 400
						For gr.groupe=Each groupe
				            If gr\num=502
								gr\position#[1]=gr\position#[1]-3
				                PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
				            EndIf
				        Next
						;ouverture de la porte
						PlaySound LoadSound("sons\environnement\little_door_open.mp3")
						activer_porte(501)
						activer_porte(502)
						activer_porte(503)
             
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				
		Case 503 ; Arsène						message_action$="discuter avec Arsène"
		If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf

                
				Select event_action
					Case 0
						call_activator(script_num)                     
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
						g_bHUDactif=1   
					Case 100
						item_quest(1,24,1);engrenage d'identification
						event_action=0
						ChangeAvancement("PJ/EngrenageID:OUI")
						ChangeAvancement("Manny/Recherche:DONE")
						For gr.groupe=Each groupe ; Transforme le Script dialogue "rentrer dans l'usine" en changement de map
							If gr\num=402
								gr\script[1]=7 
								Exit
							EndIf
						Next
						
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
				
				
		Case 504 ; Emanuella						message_action$="discuter avec Emanuella"
		If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
                
				Select event_action
					Case 0
						call_activator(script_num)                     
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
						g_bHUDactif=1

                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
		Case 506 ; Tavernier		message_action$="discuter avec le tavernier"
		If lock_action=0
				lock_action=1
				If event_action>0 
					var_pl_grp(num_player,"animation",True,1):animation()
					g_bHUDactif=0
				EndIf
		                
				Select event_action
					Case 0
						call_activator(script_num)                     
                        If keys(12,2)=50 And player_in_control And chat_mode=0
                            keys(12,2)=min(49,keys(12,2))
                            interaction_avec=var_script_int(1)
                            interaction_script=var_script_int(2)
                            event_action=1
                            disc_len#=0       
                        EndIf
						g_bHUDactif=1

					Case 102
						If player_caps > 3
							player_caps = player_caps-3
							redonner_pv_pj(2,3)
							event_action = 2
						Else
							event_action = 5
						EndIf
						
					Case 103
						If player_caps > 9
							player_caps = player_caps-9
							redonner_pv_pj(0.15,1)
							event_action = 3
						Else
							event_action = 5
						EndIf
					
					Case 104
						If player_caps > 9
							player_caps = player_caps-9
							redonner_pv_pj(0.5,1)
							event_action = 4
						Else
							event_action = 5
						EndIf
					
                    Default
                        event_action=Main_ScriptXml(message_action$,event_action)
					End Select
				EndIf
		Default
			mess$="WARNING : Numéro de script invalide ("+Str(script_num)+") venant du script "+var_script_int(2)+" du groupe "+var_script_int(1)+"."
			new_log(mess$,255,0,0)
			;RuntimeError mess$
	End Select
End Function



Function map_script(num)
	Select num
		Case 1
			;1 - combat fillette
			;2 - porte Stale
			;3 - TocToc
			;4 - Ascenseur
			;5 - A qui parle la fillette (pour éviter les doublons)
			;6 - On peut parler à Stale
			;porte Stale
			
			;new_log("Now")
			
			
			If map_stat(1,2)=2 And porte_cellule7<>0
				If AnimTime(porte_cellule7)=0 Then AnimateMetaPorte(3,0.2*0.02*2500/Float(nb_frame))
			EndIf
			;porte ascenseur
			If map_stat(1,4)=1
				If tranche1_porteAscenceur<>0
					If AnimTime(tranche1_porteAscenceur)=0 Then AnimateSteamPorte(3,0.2*0.02*2500/Float(nb_frame),1)
				EndIf		
			EndIf
		Case 2
			;1 - secrétaire A morte
			;2 - secrétaire B morte
			;3 - Stan Battu
			;4 - A qui Stan parle
			;5 - A qui le Forgeron parle
			;6 - timer porte engrenage principale
			;7 - Poste ouverte
			;8 - Labo Teddy ouvert
			;10- S-S désactivé
			;11- lettre Emanuella déjà prise


			If map_stat(2,3)=1
				For gr.groupe=Each groupe
					If gr\num=205
						If gr\manikin[2]<>0
							FreeEntity gr\manikin[2]
							gr\manikin[2]=0
						EndIf
					EndIf
				Next
			EndIf
			
			If map_stat(2,7)=1
				For gr.groupe=Each groupe
					If gr\num=215
						gr\trigger[1]=0
						gr\trigger[2]=4
					EndIf
				Next
			EndIf
			
			If map_stat(2,8)=1
				For gr.groupe=Each groupe
					If gr\num=214
						gr\trigger[1]=0
						gr\trigger[2]=4
					EndIf
				Next
			EndIf

		Case 3
			;1 - Machine de vote ok
			;2 - A qui parle à Arsène
			;3 - A qui parle Emanuella
			;4 - A qui parle le Coach
			;5 - A qui parle Ch Porte
			;6 - A qui parle Ch Bras Droit
			;7 - A qui parle Aude
			;8 - Ch_P laisse passer
			;9 - Ch_BD laisse passer
			;10- Tempo épreuve chasseur
			;11- old ch_p
			;12- old ch_bd
			;13- Aude battue, Arsène Battu
			
			For map.map=Each map
				If map\num=3
					If map\stat[8]<>map\stat[11]
						If map\stat[8]=1
							For gr.groupe=Each groupe
								If gr\num=304
									EntityType gr\pivot,999
								EndIf
							Next
						Else
							For gr.groupe=Each groupe
								If gr\num=304
									EntityType gr\pivot,type_mur
								EndIf
							Next
						EndIf
					EndIf
					If map\stat[9]<>map\stat[12]	
						For gr.groupe=Each groupe
							If gr\num=305
								EntityType gr\pivot,999
							EndIf
						Next
					Else
						For gr.groupe=Each groupe
							If gr\num=305
								EntityType gr\pivot,type_mur
							EndIf
						Next
					EndIf
					map\stat[12]=map\stat[9]
					map\stat[13]=map\stat[10]
				EndIf
			Next
			
		Default
	
	End Select

End Function