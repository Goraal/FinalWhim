;renvoit le premier num libre pour un avatar
;peu utile à priori
Function num_avatar()
	num=0
	good=0
	While good=0
		num=num+1
		If avatar_exist(num)=0 Then good=1
	Wend
	Return num
End Function

;renvoit 1 si un avatar avec ce num existe, 0 sinon.
Function avatar_exist(num)
	reponse=0
	For a.avatar=Each avatar
		If a\num=num Then reponse=1
	Next
	Return reponse	
End Function

; num : immatriculation de l'avatar
; cat : la catégorie d'avatar (garde 1, rat, boss ...)
; grp : le groupe auquel appartient l'avatar
; set : les résultats des tirages aléatoires successifs dans le cas où une partie du perso est aléatoire.
; prop : 0 si IA, num_joueur (1 en solo) si controlé par le joueur

Function new_avatar(num,cat,grp=0,set=11356,prop=0)
;	For a.avatar=Each avatar
;		If a\num=num
;			Delete a
;		EndIf
;	Next

	If avatar_exist(num)=0	
		a.avatar=New avatar
		a\num=num
		a\prop=prop
		a\groupe=grp
		a\set=set
		
		a\defense=0
		a\animation=1
		a\target=0
		a\activated=0
		
		
		Select cat
			Case 11 ; rat
				a\name$[1]="Rat"
				a\name$[2]="Rat"
				a\cat=11 ; rat
				a\att[1]=-1
				a\att[2]=-2
				a\att[3]=0
				a\def[1]=8
				a\def[2]=10
				a\def[3]=15
				a\deg[1]=1
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=6
				a\pv[1]=a\pv[2]
				a\init=0;6
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=2 ; parfait
				a\cmpt[1]=0
				For t=2 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				a\caps=1
				a\junk=Rand(0,2)

			Case 12 ; fillette

			Case 13 ; Protecteur avec une grosse hache
				a\name$[1]="Prtkt Hache"
				a\cat=13 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				a\equi[1]=2
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)

			Case 14 ; Protecteur avec une bonne armure
				a\name$[1]="Prtkt Mur"
				a\cat=14 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				a\equi[1]=4
				a\equi[4]=111
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)

			Case 15 ; Protecteur avec épée
				a\name$[1]="Protecteur"
				a\cat=15 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=17
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				a\equi[1]=3
				a\equi[4]=101
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
			
			Case 16 ; Stan
				a\name$[1]="Stan"
				a\cat=16 ; Louis
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				If map_stat(2,3)=0 Then a\equi[1]=6
				a\equi[4]=112
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
			
			Case 17 ; Systeme de Sécurité
				a\name$[1]="Boss"
				a\cat=17 ; SS
				a\att[1]=0
				a\att[2]=0
				a\att[3]=3
				a\def[1]=14
				a\def[2]=10
				a\def[3]=14
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=40
				a\pv[1]=a\pv[2]
				a\init=2
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=1.5 ; critique
				a\faiblesse#[3]=4 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=21 ; fusil puissant
				a\equi[2]=24 ; fusil à clou
						
				For t=3 To 8
					a\equi[t]=0
				Next
				a\equi[4]=111 ; armure
				a\caps=Rand(10,20)
				a\junk=Rand(0,2)
			
			Case 18
				a\name$[1]="Chasseur A"
				a\cat=18 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=13
				a\equi[2]=1
				a\equi[4]=103
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
				
			Case 19
				a\name$[1]="Chasseur B"
				a\cat=19 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=14
				a\equi[2]=4
				a\equi[4]=105
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
			
			Case 20
				a\name$[1]="Aude"
				a\cat=20 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=2 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=12
				a\equi[2]=17
				a\equi[4]=104
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
				
			Case 21
				a\name$[1]="Arsène"
				a\cat=21 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=20
				a\equi[4]=103
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
			
			Case 22
				a\name$[1]="Larbin"
				a\cat=22 ; Protecteur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=0
				a\def[1]=10
				a\def[2]=10
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=11
				a\pv[1]=a\pv[2]
				a\init=0
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=3 ; parfait
				For t=1 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=20
				a\equi[4]=103
				a\caps=Rand(30,40)
				a\junk=Rand(20,25)
			
			Case 30 ; mongolfière
				a\name$[1]="Patrouilleur"
				a\cat=30 ; patrouilleur
				a\att[1]=0
				a\att[2]=0
				a\att[3]=5
				a\def[1]=14
				a\def[2]=14
				a\def[3]=10
				a\deg[1]=0
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=40
				a\pv[1]=a\pv[2]
				a\init=3
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=5 ; parfait
				a\cmpt[1]=1
				For t=2 To 15
					a\cmpt[t]=0
				Next
				a\equi[1]=24 ; Fusil à clou ^^ => arme à distance
				For t=2 To 8
					a\equi[t]=0
				Next
				a\equi[4]=101; Armure de cuir
				a\caps=Rand(10,20)
				a\junk=Rand(0,2)

			Default ; rat
				a\name$[1]="Rat"
				a\cat=11 ; rat
				a\att[1]=-1
				a\att[2]=-2
				a\att[3]=0
				a\def[1]=8
				a\def[2]=10
				a\def[3]=15
				a\deg[1]=1
				a\deg[2]=0
				a\deg[3]=0
				a\pv[2]=5
				a\pv[1]=a\pv[2]
				a\init=0;6
				a\faiblesse#[1]=1 ; normal
				a\faiblesse#[2]=2 ; critique
				a\faiblesse#[3]=2 ; parfait
				a\cmpt[1]=1
				For t=2 To 15
					a\cmpt[t]=0
				Next
				For t=1 To 8
					a\equi[t]=0
				Next
				a\caps=1
				a\junk=1		
		End Select
	EndIf	
End Function


Function cat_to_ia(cat)
	Select cat
		Case 11 ; rat
			Return 1
		Case 12 ; fillette
			Return 2
		Case 15 ; protecteur flingueur
			Return 2
		Case 17 ; Boss
			Return 2
		Case 18 ; Chasseur A
			Return 2
		Case 19 ; Chasseur B
			Return 2
		Case 20 ; Aude
			Return 2
		Case 30 ; mongolfière
			Return 2
		Default
			Return 1
	End Select
End Function

Function IA(num_combat,num_avatar,type_ia=1)
	For combat.combat=Each combat
		If combat\num=num_combat
			For av.avatar=Each avatar
				If av\num=num_avatar
					Select type_ia
						Case 1 ; IA "zombie cc" : attaque une cible au hasard puis n'en change plus jusqu'à ce que la cible soit invalide (auquel cas on change de cible)
							;checker si la cible actuelle est valide
							If target_valide(av\num,av\target,combat\num)=0
								target=0
								;liste des cibles valides
								For t=1 To 9 
									impliques(t)=0
								Next
								k=0
								
								For g=1 To 2
									If av\groupe=combat\groupe[g]
										For gr.groupe=Each groupe
											If gr\num=combat\groupe[Abs(g-2)+1]
												For t=1 To 9
													If gr\formation[t]<>0
														If target_valide(av\num,gr\formation[t],combat\num)>0
															k=k+1
															impliques(k)=gr\formation[t]
															;new_log("Impliqués ("+t+") : "+target_valide(av\num,gr\formation[t],combat\num))
														EndIf
													EndIf
												Next
												If k<>0
													k=Rand(1,k)
													target=impliques(k)
												EndIf
											EndIf
										Next
									EndIf
								Next
								;choisir 1 parmi cette liste
								av\target=target
								;new_log("New target : "+av\target)
							EndIf
							
							If av\target<>0 ;si une cible est valide au final
								mess$=combat\num+"#"
								mess$=mess$+av\num+"#11#"+av\target+"#s#s#s#s#s#"
								analyse(23,mess$)								
							Else
								;sinon
									;vérifier qu'on est pas déjà devant
								contact=1
								For g=1 To 2
									If av\groupe=combat\groupe[g]
										For gr.groupe=Each groupe
											If gr\num=combat\groupe[g]
												k=0
												For t=1 To 9
													If gr\formation[t]=av\num Then k=t
												Next
												For t=(Floor(k*0.333))*3+1 To k
													If gr\formation[t]<>0 And gr\formation[t]<>av\num
														For av_t.avatar=Each avatar
															If av_t\num=gr\formation[t]
																If av_t\pv[1]>0 Then contact=0
															EndIf
														Next
													EndIf
												Next
												
											EndIf
										Next
									EndIf
								Next
									
								If contact=0 ; pas au premier rang
									ai=0
									For gr.groupe=Each groupe
										If gr\num=av\groupe
											If gr\formation[7]=0
												ai=7
											Else
												For av_t.avatar=Each avatar
													If av_t\num=gr\formation[7]
														If Not(av_t\pv[1]>0) Then ai=7
													EndIf
												Next
											EndIf
											If gr\formation[4]=0
												ai=4
											Else
												For av_t.avatar=Each avatar
													If av_t\num=gr\formation[4]
														If Not(av_t\pv[1]>0) Then ai=4
													EndIf
												Next
											EndIf
											If gr\formation[1]=0
												ai=1
											Else
												For av_t.avatar=Each avatar
													If av_t\num=gr\formation[1]
														If Not(av_t\pv[1]>0) Then ai=1
													EndIf
												Next
											EndIf
	
										EndIf
									Next
									If ai<>0 
										analyse(23,combat\num+"#"+av\num+"#41#"+Str(ai)+"#s#s#s#s#s#")
									Else ;sinon se mettre en défensive
										analyse(23,combat\num+"#"+av\num+"#21#s#s#s#s#s#s#")
									EndIf
								Else ; déjà au premier mais ne peut pas attaquer parce que c'est le premier tour
									analyse(23,combat\num+"#"+av\num+"#21#s#s#s#s#s#s#")
								EndIf
							EndIf
							
						Case 2 ; IA_dist pas trop conne
							;voir si on est équipé d'une arme à distance
							a_dist=0
							If av\equi[1]<>0
								For arme.arme=Each arme
									If arme\num=av\equi[1]
										For k=1 To 8
											If arme\rules[k]=100 Then a_dist=1
										Next
									EndIf
								Next
							EndIf
							
							If a_dist=0 ; déjà équipé d'une arme de cc
								IA(num_combat,num_avatar,1)						
							Else ; arme à distance	
								If av\charge[1]=0 ; si l'arme est vide
									;voir si y'a une arme à distance encore chargée
									good=0
									For t=2 To 3
										If av\charge[t]<>0
											For arme.arme=Each arme
												If arme\num=av\equi[t]
													a_dist=0
													For k=1 To 8
														If arme\rules[k]=100 Then a_dist=1
													Next
													If a_dist=1 Then good=t								
												EndIf
											Next
										EndIf
									Next
									If good>0 ; une atd est dispo
										analyse(23,combat\num+"#"+av\num+"#42#"+good+"#s#s#s#s#s#s#")								
									Else ; Si il n'y a pas d'arme à distance encore chargée, chercher si il y a une acc dispo
										For t=2 To 3
											If good=0
												If av\equi[t]<>0
													a_dist=0
													For arme.arme=Each arme
														If arme\num=av\equi[t]
															For k=1 To 8
																If arme\rules[k]=100 Then a_dist=1
															Next
															If a_dist=0 Then good=t
														EndIf
													Next
												EndIf
											EndIf
										Next
									EndIf
									If good>0 ; une acc est dispo
										analyse(23,combat\num+"#"+av\num+"#42#"+good+"#s#s#s#s#s#s#")
									Else ; passer à mains nues
										For t=2 To 3
											If av\equi[t]=0 And good=0
												good=1
												analyse(23,combat\num+"#"+av\num+"#42#"+good+"#s#s#s#s#s#s#")
											EndIf
										Next
										If good=0 ; on ne peux pas passer à mains nues alors on se met en défense
											analyse(23,combat\num+"#"+av\num+"#21#s#s#s#s#s#s")
										EndIf
									EndIf	
								Else ; il reste encore des munitions dans cette arme
									For t=1 To 2
										If combat\groupe[t]=av\groupe Then gr_t_num=combat\groupe[3-t]
									Next
									For arme.arme=Each arme
										If arme\num=av\equi[1] Then cadence=arme\cadence
									Next
									If ( population(gr_t_num,1)<1.5*population(av\groupe,1) ) Or (cadence<2) Or (av\charge[1]<cadence) Or (population(gr_t_num,1)<4)
										; tir simple
										cible=selection_cible_distance(gr_t_num,1)
										target_valide_distance(av\num,cible,combat\num)
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
										av\charge[1]=av\charge[1]-1
										analyse(23,mess$+"s#s#s#s#")
									Else
										; rafale
										cible=selection_cible_distance(gr_t_num,2)
										av\charge[1]=av\charge[1]-cadence
										analyse(23,combat\num+"#"+av\num+"#15#"+cible+"#"+cadence+"#s#s#s#s#s#s#")
									EndIf
								EndIf
							EndIf
							
						Case 3 ; IA du Boss	
							
							
						Default
							Return -1
					End Select
				EndIf
			Next
		EndIf
	Next
	Return 1
End Function

Function selection_cible_distance(gr_t_num,kind=1) ; le groupe ciblé : si l'attaque est un tir simple ou une rafale
	For gr.groupe=Each groupe
		If gr\num=gr_t_num
			Select kind
				Case 1 ; tir ordinaire, visant selon la difficulté le plus faible ou le plus fort de la ligne la moins remplie
					; chercher la ligne la moins remplie
					;cmb de gars dans chaque ligne
						For t=0 To 2	
							nb_ds_ligne(t+1)=0
							ds_ligne=0
							For k=1 To 3
								If gr\formation[t*3+k]<>0
									For av_t.avatar=Each avatar
										If av_t\num=gr\formation[3*t+k]
											If av_t\pv[1]>0 Then ds_ligne=ds_ligne+1
										EndIf
									Next
								EndIf
							Next
							If ds_ligne=0 Then ds_ligne=4
							nb_ds_ligne(t+1)=ds_ligne
						Next
					;choisir la ligne
						minimum=4
						min_ligne=0
						For t=1 To 3
							If nb_ds_ligne(t)<minimum Then minimum=nb_ds_ligne(t)
							ligne_min(t)=0 ; parce que je voulais pas faire une autre boucle d'initialisation
						Next
						For t=1 To 3
							If nb_ds_ligne(t)=minimum
								min_ligne=min_ligne+1
								ligne_min(min_ligne)=t
							EndIf
						Next
						ligne=ligne_min(Rand(1,min_ligne))-1 ; -1 pour rester dans [0;2] plutôt que [1;3]
					;chercher les pv_max de ceux qui sont dans la ligne et en faire une liste ordonnée croissante
						target=0
						For t=1 To 3
							target_possible(t)=0
							pv_target(t)=0
							n_target(t)=0
						Next
						For k=1 To 3				
							If gr\formation[ligne*3+k]<>0
								For av_t.avatar=Each avatar
									If av_t\num=gr\formation[ligne*3+k]
										If av_t\pv[1]>0
											target=target+1
											target_possible(target)=av_t\num
											pv_target(target)=av_t\pv[2]
										EndIf
									EndIf
								Next
							EndIf
						Next
						;trier la liste
						min_place=0
						minimum=1000000
						max_place=0
						maximum=-10
						For k=1 To target
							If pv_target(k)<minimum Then min_place=k:minimum=pv_target(k)
						Next
						For k=1 To target
							If pv_target(k)>maximum And k<>min_place
								max_place=k:maximum=pv_target(k)
							EndIf
						Next
						If target=3
							n_target(1)=target_possible(min_place)
							n_target(2)=target_possible(6-min_place-max_place)
							n_target(3)=target_possible(max_place)
						Else
							n_target(1)=target_possible(min_place)
							n_target(target)=target_possible(max_place)
						EndIf
					;donner la cible en fonction de la difficulté
						Select difficulty
							Case 1
								Return target_possible(target) ; le + de pv_max
							Case 2
								Return target_possible(Rand(1,target)) ; aléatoire
							Case 3
								Return target_possible(1) ; le - de pv_max
						End Select
				Case 2 ; rafale, visant le plus au fond de la ligne la plus remplie (+aléa)
					; chercher la ligne la plus remplie
					;cmb de gars dans chaque ligne
						For t=0 To 2	
							nb_ds_ligne(t+1)=0
							ds_ligne=0
							For k=1 To 3
								If gr\formation[t*3+k]<>0
									For av_t.avatar=Each avatar
										If av_t\num=gr\formation[3*t+k]
											If av_t\pv[1]>0 Then ds_ligne=ds_ligne+1
										EndIf
									Next
								EndIf
							Next
							;If ds_ligne=0 Then ds_ligne=4
							nb_ds_ligne(t+1)=ds_ligne
						Next
					;choisir la ligne
						maximum=0
						max_ligne=0
						For t=1 To 3
							If nb_ds_ligne(t)>maximum Then maximum=nb_ds_ligne(t)
							ligne_min(t)=0 ; parce que je voulais pas faire une autre boucle d'initialisation
						Next
						For t=1 To 3
							If nb_ds_ligne(t)=maximum
								max_ligne=max_ligne+1
								ligne_min(max_ligne)=t
							EndIf
						Next
						ligne=ligne_min(Rand(1,max_ligne))-1 ; -1 pour être dans [0;2] plutôt [1;3]
					;viser celui du fond (et ne PAS PRENDRE UN MORT !!!)
						dernier=0
						For k=1 To 3
							If gr\formation[ligne*3+k]<>0
								For av_t.avatar=Each avatar
									If av_t\num=gr\formation[ligne*3+k]
										If av_t\pv[1]>0 Then dernier=gr\formation[ligne*3+k]
									EndIf
								Next
							Endif
						Next
						Return dernier					
			End Select
		EndIf
	Next
End Function