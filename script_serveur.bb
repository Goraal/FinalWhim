Function script_serveur(script_num,info_sup=0)
	
	HAUT_AVANT=1
	HAUT_CENTRE=2
	HAUT_ARRIERE=3
	MILIEU_AVANT=4
	MILIEU_CENTRE=5
	MILIEU_ARRIERE=6
	BAS_AVANT=7
	BAS_CENTRE=8
	BAS_ARRIERE=9
	
	Select script_num
		Case 0
			
		Case 1
			new_log("Test réussi",255,255,0)
		Case 106 ; MAP Bêta : Stale se déplace vers la steam-porte	
			For gr.groupe=Each groupe
				If gr\num=101
					vitesse_stale#=5
					If gr\position#[3]<10.5
						gr\animation=2
						gr\position#[4]=0
						gr\position#[3]=gr\position#[3]+minf#(vitesse_stale#*delta_frame*0.001,0.5)
						If gr\pivot<>0
							PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
							RotateEntity gr\pivot,0,gr\position#[4],0
						EndIf
						
					ElseIf gr\position#[1]<51.5
						alpha#=pointeryaw#(gr\position#[1],gr\position#[3],52,21)+180 ; 51,17.5
						;gr\position#[4]=alpha#
						beta#=mod360(gr\position#[4]-alpha#)
						If beta#>5
							gr\position#[4]=gr\position#[4]-200*delta_frame*0.001
						ElseIf beta#<-5
							gr\position#[4]=gr\position#[4]+200*delta_frame*0.001
						Else
							gr\position#[4]=alpha#
						EndIf
						gr\position#[1]=gr\position#[1]-vitesse_stale#*Sin(gr\position#[4])*delta_frame*0.001
						gr\position#[3]=gr\position#[3]+vitesse_stale#*Cos(gr\position#[4])*delta_frame*0.001
						
						If gr\pivot<>0
							PositionEntity gr\pivot,gr\position#[1],gr\position#[2],gr\position#[3]
							RotateEntity gr\pivot,0,gr\position#[4],0
						EndIf
						
					Else
						beta#=mod360(gr\position#[4]-90)
						;gr\position#[4]=90
						If beta#>10
							gr\position#[4]=gr\position#[4]-400*delta_frame*0.001
						ElseIf beta#<-10
							gr\position#[4]=gr\position#[4]+400*delta_frame*0.001
						Else
							gr\position#[4]=90
							gr\animation=1
							If gr\trigger[2]>0
								gr\trigger[2]=0
								gr\trigger[1]=4
								maj_map(1,6,1)
							EndIf
						EndIf
						If gr\pivot<>0
							RotateEntity gr\pivot,0,gr\position#[4],0
						EndIf
					EndIf
				EndIf
			Next
			
		Case 210 ; porte principale engrenage
				For map.map=Each map
					If map\num=2
						If map\stat[6]>0
							map\stat[6]=map\stat[6]-delta_frame
							If map\stat[6]<0
								For p.porte=Each porte
									If p\num=201
										p\etat=0
										SendNetMsg(10,Str(p\num)+"#0#",master_id)
									EndIf
								Next
							EndIf
						EndIf
					EndIf			
				Next
		
		Case 1030 ; troupes de rat du groupe 103
			For t=1 To 2*DIFFICULTY
				new_avatar(1030+t,11,103)
			Next
			For av.avatar=Each avatar
				If av\num>1030 And av\num<1040
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=103
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=1031
					gr\formation[4]=1032
				EndIf
			Next
		
		
		Case 151 ; rat pokémon sous-sol
			For t=1 To 2*DIFFICULTY
				new_avatar(1510+t,11,151) ; génère un avatar de numéro 1510+t, de type 11 (i.e. un rat), dans le groupe 151
			Next
			For av.avatar=Each avatar
				If av\num>1510 And av\num<1520 ; donner toutes la santée à ces avatars
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=151
					For t=1 To 9 ; effacer la formation précédente (au cas où)
						gr\formation[t]=0
					Next
					gr\formation[1]=1511 ; placer le premier rat au premier rang en haut
					gr\formation[4]=1512 ; placer le second rat au premier rang au milieu
					;les éventuels autres rats sont placés par update_combat() dans la première place libre dans l'ordre (de 1 à 9)
				EndIf
			Next
			
		Case 152 ; rat pokémon sous-sol
			For t=1 To 2*DIFFICULTY
				new_avatar(1520+t,11,152)
			Next
			For av.avatar=Each avatar
				If av\num>1520 And av\num<1530
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=152
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=1521
					gr\formation[4]=1522
				EndIf
			Next
		
		Case 153 ; rat pokémon sous-sol
			For t=1 To 2*DIFFICULTY
				new_avatar(1530+t,11,153)
			Next
			For av.avatar=Each avatar
				If av\num>1530 And av\num<1540
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=153
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=1531
					gr\formation[4]=1532
				EndIf
			Next
		
		Case 199 ; test
			For t=1 To 2*DIFFICULTY
				new_avatar(1990+t,11,199)
			Next
			For av.avatar=Each avatar
				If av\num>1990 And av\num<2000
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=199
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=1991
					gr\formation[4]=1992
				EndIf
			Next

		
		Case 2020 ; Système de Sécurité
			new_avatar(202,17,202)
			For av.avatar=Each avatar
				If av\num=202
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=202
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=202
				EndIf
			Next

			
		Case 2050 ; Combat Stan + Gardes (agressif)
		
		;	haut_avant=1
		;	haut_centre=2
		;	haute_arriere=3
		;	milieu_avant=4
		;	milieu_centre=5
		;	milieu_arriere=6
		;	bas_avant=7
		;	bas_centre=8
		;	bas_arriere=9
		
		Case 2051 ; Combat Protecteur A (tir)
			new_avatar(2051,15,2051)
			new_avatar(2052,15,2051)
			new_avatar(2053,15,2051)
			For av.avatar=Each avatar
				If av\num=2051
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=2052
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=2053
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=2051
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[BAS_CENTRE]=2051
					gr\formation[HAUT_AVANT]=2052
					gr\formation[BAS_AVANT]=2053
				EndIf
			Next
			
			
		Case 2052	
			new_avatar(2052,13,2052)
			For av.avatar=Each avatar
				If av\num=2052
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=2052
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=2052
				EndIf
			Next
		Case 2053 ; Combat Protecteur C (armure)
			new_avatar(2053,14,2053)
			For av.avatar=Each avatar
				If av\num=2053
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=2053
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=2053
				EndIf
			Next
		Case 2054 ; Combat Stan en solo
			new_avatar(2054,16,2054)
			For av.avatar=Each avatar
				If av\num=2052
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=2052
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=2052
				EndIf
			Next
			
		Case 302
			new_avatar(302,21,302)
			new_avatar(3021,12,302)
			If difficulte>1
				new_avatar(3022,22,302)
				If difficulte>2
					new_avatar(3023,22,302)
				EndIf
			EndIf
			For av.avatar=Each avatar
				If av\num=302
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3021
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3022
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3023
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=302
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[5]=302
					gr\formation[4]=3021
				EndIf
			Next

		Case 304 ; Chasseur Porte
			new_avatar(304,18,304)
			For av.avatar=Each avatar
				If av\num=304
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=304
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=304
				EndIf
			Next
		
		Case 305 ; Chasseur Bras droit
			new_avatar(305,19,305)
			For av.avatar=Each avatar
				If av\num=305
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=305
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=305
				EndIf
			Next
			
		Case 306 ; Aude
			new_avatar(306,20,306)
			new_avatar(3061,14,306)
			If difficulte>1
				new_avatar(3062,18,306)
				If difficulte>2
					new_avatar(3063,18,306)
				EndIf
			EndIf
			For av.avatar=Each avatar
				If av\num=306
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3061
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3062
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=3063
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=306
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[5]=306
					gr\formation[4]=3061
				EndIf
			Next
		
		Case 4030 ; SS / Boss
			new_avatar(4030,17,4030)
			
			If TestAvancement("MJ/Combat:NON")
				new_avatar(4031,15,4030)
				new_avatar(4032,15,4030)
				new_avatar(4033,30,4030);30,4030)
				If difficulte>1
					new_avatar(4034,30,4030)
					If difficulte>2
						new_avatar(4035,15,4030)
						new_avatar(4036,15,4030)
					EndIf
				EndIf
			EndIf
			For av.avatar=Each avatar
				If av\num>4030 and av\num<4039
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=4030
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[MILIEU_AVANT]=4030
					
					If TestAvancement("MJ/Combat:NON")
						gr\formation[HAUT_AVANT]=4031
						gr\formation[BAS_AVANT]=4032
						gr\formation[HAUT_ARRIERE]=4033
						If difficulte>1
							gr\formation[BAS_ARRIERE]=4034
							If difficulte>2
								gr\formation[MILIEU_AVANT]=4035
								gr\formation[MILIEU_CENTRE]=4036
							EndIf
						EndIf
					EndIf
				EndIf
			Next
			
		
		Case 4060 ; MJ
			new_avatar(4060,15,4060)
			new_avatar(4061,15,4060)
			new_avatar(4062,30,4060)
			If difficulte>1
				new_avatar(4063,30,406)
				If difficulte>2
					new_avatar(4064,15,406)
					new_avatar(4065,15,406)
				EndIf
			EndIf
			For av.avatar=Each avatar
				If av\num=4060
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=4061
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=4062
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=4063
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=4064
					av\pv[1]=av\pv[2]
				EndIf
				If av\num=4065
					av\pv[1]=av\pv[2]
				EndIf
			Next
			For gr.groupe=Each groupe
				If gr\num=4060
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[HAUT_AVANT]=4060
					gr\formation[BAS_AVANT]=4061
					gr\formation[HAUT_ARRIERE]=4062
					If difficulte>1
						gr\formation[BAS_ARRIERE]=4063
						If difficulte>2
							gr\formation[MILIEU_AVANT]=4064
							gr\formation[MILIEU_CENTRE]=4065
						EndIf
					EndIf
				EndIf
			Next
			
						
		Case 4801 ; Patrouilles extérieures
			gr_num=info_sup
			;enlever ceux qui restent si il y en avait
			For gr.groupe=Each groupe
				If gr\num=gr_num
					For t=1 To 9
						gr\formation[t]=0
					Next
				EndIf
			Next
			For av.avatar=Each avatar
				If av\groupe=gr_num Then av\groupe=0
			Next
			;ajouter les nouveaux ennemis
			For t=1 To 2*DIFFICULTY
				new_avatar(gr_num*1000+010+t,30,gr_num)
			Next
			For av.avatar=Each avatar
				If av\num>gr_num*1000+10 And av\num<gr_num*1000+20
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=gr_num
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=gr_num*1000+11
					gr\formation[4]=gr_num*1000+12
				EndIf
			Next
		
		Case 31501 ; test
			For t=1 To 2*DIFFICULTY
				new_avatar(315010+t,11,31501)
			Next
			For av.avatar=Each avatar
				If av\num>315010 And av\num<315020
					av\pv[1]=av\pv[2]
				EndIf
			Next
			;formation
			For gr.groupe=Each groupe
				If gr\num=31501
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[1]=315011
					gr\formation[4]=315012
				EndIf
			Next
			
		Case 999 ; Système de Sécurité
			new_avatar(999,17,999)
			For gr.groupe=Each groupe
				If gr\num=999
					For t=1 To 9
						gr\formation[t]=0
					Next
					gr\formation[4]=999
				EndIf
			Next
			
		Default
			mess$="WARNING : Numéro de script invalide ("+Str(num)+") in script_serveur"
			new_log(mess$,255,0,0)
	End Select
End Function