Function reinit_groupe()
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;GROUPES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;NOTES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; Pour les groupes de PNJ que l'on peut combattre, le script[4] est réservé à la construction des avatars


	;; creer les groupes des joueurs

	For t=1 To 1
		gr.groupe=New groupe
		gr\num=-t
		gr\map=-1
		gr\script[1]=2
		gr\trigger[1]=4
		gr\nom_action$[1]="interagir avec ce groupe"
		gr\range_trigger[1]=1
		gr\old_animation=-12
		gr\animation=1
	Next

	;;TEST
	;;Testeur de script
	g.groupe=New groupe
	g\num=901
	g\name$="Testeur de Script"
	g\map=315
	g\act_scale#=0.75
	g\act_type=2
	g\script[1]=100   ; METTRE ICI LE NUM DU SCRIPT
	g\trigger[1]=4  ; METTRE ICI LE DECLENCHEUR type de déclenchement d'interaction (0 impossible : 1 permanent : 2 survol à la souris : 3 clic : 4 distance : 5 distance+A : 6 serveur)
	g\range_trigger[1]=2
	g\nom_action$[1]="Il faut modifier le groupe 901 dans groupe.bb pour faire les tests des scripts.#Toute la progra déjà faite est incluse dans ce stand-alone donc tu peux tout tester (il manque juste les assets pour prendre moins de place)"
	g\position#[1]=14
	g\position#[2]=0
	g\position#[3]=14
	g\position#[4]=0

	g.groupe=New groupe
	g\num=199
	g\name$="Dresseur de pokémon"
	g\map=315
	g\position#[1]=20
	g\position#[2]=0.45
	g\position#[3]=25
	g\animation=1
	g\script[4]=199

	g.groupe=New groupe
	g\num=999
	g\name$="Système de Sécurité"
	g\map=315
	;g\position#[1]=20
	;g\position#[2]=0.45
	;g\position#[3]=25
	g\animation=1
	g\script[4]=999
	
	g.groupe=New groupe
	g\num=31501
	g\name$="Test Patrouilleur"
	g\map=315
	g\position#[1]=32
	g\position#[2]=0.45
	g\position#[3]=30
	g\animation=1
	g\not_md2=0
	g\script[4]=31501
	
	

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;garage;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	g.groupe=New groupe
	g\num=101
	g\name$="Stale"
	g\map=1
	g\act_scale#=0.75
	g\act_type=2
	g\script[1]=101
	g\trigger[1]=4
	g\range_trigger[1]=2
	g\nom_action$[1]="discuter avec Stale"
	g\script[2]=106
	g\trigger[2]=0	
	g\position#[1]=9.8
	g\position#[2]=.45
	g\position#[3]=5
	g\position#[4]=0
	g\animation=1

	g.groupe=New groupe
	g\num=102
	g\name$="E1"
	g\map=1
	g\script[1]=102
	g\script[4]=103
	g\trigger[1]=4
	g\trigger[4]=1
	g\range_trigger[1]=1
	g\nom_action$[1]="coller son oreille contre le mur"			
	g\position#[1]=5
	g\position#[2]=1
	g\position#[3]=5
	g\trigger[2]=Load3DSound("sons\environnement\toctoc.wav")

	g.groupe=New groupe
	g\num=103
	g\name$="Fillette"
	g\map=1
	g\script[1]=104
	g\script[4]=1030
	g\trigger[1]=4
	g\range_trigger[1]=2
	g\range_trigger[2]=0
	g\script[2]=0
	g\nom_action$[1]="discuter avec la fillette"
	g\position#[1]=28
	g\position#[2]=0.45
	g\position#[3]=34.5
	g\animation=1
	g\act_scale#=0.5
	g\act_type=2

	g.groupe=New groupe
	g\num=104
	g\name$="Serrure SteamImpact(tm)"
	g\map=1
	g\script[1]=105
	g\script[2]=0
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="tripoter la serrure SteamImpact(tm)"
	g\position#[1]=9.5
	g\position#[2]=1
	g\position#[3]=9

	g.groupe=New groupe
	g\num=105
	g\name$="Affiche"
	g\map=1
	g\script[1]=107
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="lire l'affiche"
	g\position#[1]=0.75
	g\position#[2]=0
	g\position#[3]=5

	g.groupe=New groupe
	g\num=106
	g\name$="Ascenceur"
	g\map=1
	g\script[1]=7;-665
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="monter avec l'ascenseur"
	g\range_trigger[2]=4
	g\range_trigger[3]=1
	g\position#[1]=54
	g\position#[2]=1
	g\position#[3]=19


	g.groupe=New groupe
	g\num=107
	g\name$="Roue"
	g\map=1
	g\script[1]=108
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="courir dans la roue"
	g\script[2]=0
	g\range_trigger[2]=3642
	g\position#[1]=3
	g\position#[2]=1
	g\position#[3]=1

	g.groupe=New groupe
	g\num=108
	g\name$="Fillette du combat"
	g\map=-1
	
	g.groupe=New groupe
	g\num=109
	g\name$="Utilisez Z, Q, S, D#pour vous déplacer"
	g\map=1
	g\script[1]=109
	g\trigger[1]=4
	g\range_trigger[1]=5
	g\position#[1]=3
	g\position#[2]=0.45
	g\position#[3]=4
	
	g.groupe=New groupe
	g\num=151
	g\name$="Rat pokémon 1"
	g\map=1
	g\position#[1]=-0.6
	g\position#[2]=0.1
	g\position#[3]=13.6
	g\animation=11
	g\not_md2=1
	g\script[4]=151

	g.groupe=New groupe
	g\num=152
	g\name$="Rat pokémon 2"
	g\map=1
	g\position#[1]=12.6
	g\position#[2]=0.1
	g\position#[3]=31.8
	g\animation=11
	g\not_md2=1
	g\script[4]=152

	g.groupe=New groupe
	g\num=153
	g\name$="Rat pokémon 3"
	g\map=1
	g\position#[1]=27.25
	g\position#[2]=0.1
	g\position#[3]=20
	g\animation=11
	g\not_md2=1
	g\script[4]=153

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;extérieur;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	g.groupe=New groupe
	g\num=4010
	g\name$="Ascenseur prison"
	g\map=4
	g\script[1]=7
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "descendre avec l'ascenseur"
	g\range_trigger[2]=1 ;N° map
	g\range_trigger[3]=2 ;N° entrance
	g\position#[1]=93
	g\position#[2]=-2
	g\position#[3]=43

	g.groupe=New groupe
	g\num=402
	g\name$="Porte Intérieure"
	g\map=4
	g\script[1]=402
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "rentrer dans l'usine"
	g\range_trigger[2]=2 ;N° map
	g\range_trigger[3]=1 ;N° entrance
	g\position#[1]=72
	g\position#[2]=3.1
	g\position#[3]=-34
	

	g.groupe=New groupe
	g\num=403
	g\name$="Porte de sortie"
	g\map=4
	g\script[1]=403
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "sortir de FactoryTech"
	g\script[2]=4030
	g\trigger[2]=0	
	g\position#[1]=95
	g\position#[2]=-5
	g\position#[3]=-43

	g.groupe=New groupe
	g\num=4030
	g\name$="Combat BOSS"
	g\map=-1
	g\trigger[2]=0
	g\script[1]=205
	g\script[4]=4030

	g.groupe=New groupe
	g\num=404
	g\name$="Ascenceur réfugiés"
	g\map=4
	g\script[1]=7
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "descendre avec l'ascenseur"
	g\range_trigger[2]=5 ;N° map
	g\range_trigger[3]=1 ;N° entrance
	g\position#[1]=18
	g\position#[2]=-5
	g\position#[3]=34
	
	
	g.groupe=New groupe
	g\num=405
	g\name$="Cadavre"
	g\map=4
	g\script[1]=405
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="examiner le cadavre"
	g\position#[1]=44.87
	g\position#[2]=0.1
	g\position#[3]=0
	g\position#[4]=45
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=406
	g\name$="MisterJ"
	g\map=4
	g\act_scale#=0.75
	g\act_type=2
	g\script[1]=406
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "regarder la machine humanoïde"
	g\script[2]=4060
	g\trigger[2]=0		
	g\range_trigger[2]=2 
	g\range_trigger[3]=2
	g\position#[1]=64.65
	g\position#[2]=8.9
	g\position#[3]=-18.2
	
	g.groupe=New groupe
	g\num=4060
	g\name$="Combat MJ"
	g\map=-1
	g\trigger[2]=0
	g\script[1]=205
	g\script[4]=4060

	
	g.groupe=New groupe
	g\num=407
	g\name$="Trappe bateau"
	g\map=4
	g\script[1]=7
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]= "retourner dans l'usine"
	g\range_trigger[2]=2 ;N° map
	g\range_trigger[3]=2 ;N° entrance
	g\position#[1]=63.73 
	g\position#[2]=9
	g\position#[3]=-13.87
	
	g.groupe=New groupe
	g\num=480
	g\name$="Patrouille canyon 1"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=92
	g\position#[2]=-4
	g\position#[3]=-14
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=481
	g\name$="Patrouille canyon 2"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=47.6
	g\position#[2]=-4
	g\position#[3]=25.4
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=482
	g\name$="Patrouille canyon 3"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=26.1
	g\position#[2]=-5.2
	g\position#[3]=-41.25
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=483
	g\name$="Patrouille canyon 4"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=43
	g\position#[2]=-4.6
	g\position#[3]=38
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=484
	g\name$="Patrouille canyon 5"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=22.3
	g\position#[2]=-4.9
	g\position#[3]=-41.3
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=485
	g\name$="Patrouille canyon 6"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=43.1
	g\position#[2]=-3.9
	g\position#[3]=-27.1
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=486
	g\name$="Patrouille canyon 7"
	g\map=4
	g\animation=21
	g\not_md2=2
	g\script[4]=4801
	g\position#[1]=69
	g\position#[2]=3.1
	g\position#[3]=-31.5
	g\position#[4]=0
	
	g.groupe=New groupe
	g\num=499
	g\name$="Conseil pour sauvegarder"
	g\map=4
	g\script[1]=499
	g\trigger[1]=1
	g\range_trigger[1]=10
	g\nom_action$[1]="Lire le conseil"
	g\position#[1]=90
	g\position#[2]=-2.5
	g\position#[3]=43
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; RdC
	g.groupe=New groupe
	g\num=201
	g\name$="Ascenceur"
	g\map=2
	g\script[1]=7
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="retourner dans la cour"
	g\range_trigger[2]=4
	g\range_trigger[3]=2
	g\position#[1]=7.5
	g\position#[2]=1
	g\position#[3]=14


	g.groupe=New groupe
	g\num=202
	g\name$="Gardes"
	g\map=2
	g\script[1]=202
	g\trigger[1]=4
	g\script[4]=2020
	g\range_trigger[1]=2
	g\nom_action$[1]="discuter avec les gardes"
	g\position#[1]=5
	g\position#[2]=1
	g\position#[3]=2
	g\act_scale#=0.5
	g\act_type=2

	g.groupe=New groupe
	g\num=203
	g\name$="Secrétaire A"
	g\map=2
	g\script[1]=203
	g\trigger[1]=4
	g\range_trigger[1]=0.75
	g\nom_action$[1]="discuter avec la secrétaire"
	g\position#[1]=9
	g\position#[2]=1
	g\position#[3]=26

	g.groupe=New groupe
	g\num=204
	g\name$="Secrétaire B"
	g\map=2
	g\script[1]=203
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec la secrétaire"
	g\position#[1]=10
	g\position#[2]=1
	g\position#[3]=29
	
	

	g.groupe=New groupe
	g\num=205
	g\name$="Stan"
	g\map=2
	g\script[1]=205
	g\script[4]=2051
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\range_trigger[2]=0
	g\script[2]=0
	g\nom_action$[1]="discuter avec Stan"
	g\position#[1]=31
	g\position#[2]=1.7
	g\position#[3]=31
	g\position#[4]=180
	g\animation=1
	g\trigger[3]=0 ; si vous avez déjà parlé à Stan ou non.
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=2051
	g\name$="Combat Protecteur A"
	g\map=-1
	g\trigger[2]=0
	g\script[1]=205
	g\script[4]=2051

	g.groupe=New groupe
	g\num=2052
	g\name$="Combat Protecteur B"
	g\map=-1
	g\trigger[2]=0
	g\script[1]=205
	g\script[4]=2052

	g.groupe=New groupe
	g\num=2053
	g\name$="Combat Protecteur C"
	g\map=-1
	g\trigger[2]=0
	g\script[1]=205
	g\script[4]=2053

	g.groupe=New groupe
	g\num=2054
	g\name$="Stan seul"
	g\map=-1
	g\trigger[2]=0
	g\script[4]=2054

	g.groupe=New groupe
	g\num=207
	g\name$="Garde 1"
	g\map=2
	g\script[1]=207
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec le Garde"
	g\position#[1]=31
	g\position#[2]=1.7
	g\position#[3]=38
	g\position#[4]=180
	g\animation=1
	g\trigger[3]=0 
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=209
	g\name$="Garde 3"
	g\map=2
	g\script[1]=209
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec le Garde"
	g\position#[1]=34.5
	g\position#[2]=1.7
	g\position#[3]=35
	g\position#[4]=90
	g\animation=1
	g\trigger[3]=0 
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=208
	g\name$="Garde 2"
	g\map=2
	g\script[1]=208
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec le Garde"
	g\position#[1]=34.5
	g\position#[2]=1.7
	g\position#[3]=38.5
	g\position#[4]=135
	g\animation=1
	g\trigger[3]=0 
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=210
	g\name$="Porte principale Engrenage - côté couloir"
	g\map=2
	g\script[1]=210
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="examiner la porte"
	g\position#[1]=40
	g\position#[2]=2
	g\position#[3]=16.5
	g\position#[4]=0
	g\trigger[2]=6
	g\script[2]=210

	g.groupe=New groupe
	g\num=211
	g\name$="Porte principale Engrenage - côté Usine"
	g\map=2
	g\script[1]=211
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="examiner la porte"
	g\position#[1]=42
	g\position#[2]=2
	g\position#[3]=16.5
	g\position#[4]=0

	g.groupe=New groupe
	g\num=212
	g\name$="Enigme Engrenage"
	g\map=2
	g\script[1]=212
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="examiner la porte"
	g\position#[1]=28.5
	g\position#[2]=1
	g\position#[3]=1.5
	g\position#[4]=0


	g.groupe=New groupe
	g\num=213
	g\name$="Porte Formation"
	g\map=2
	g\script[1]=213
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="examiner la porte"
	g\position#[1]=29
	g\position#[2]=1
	g\position#[3]=4.5
	g\position#[4]=0


	g.groupe=New groupe
	g\num=214
	g\name$="Porte secondaire Engrenage"
	g\map=2
	g\script[1]=214
	g\trigger[1]=4
	g\range_trigger[1]=1
	g\nom_action$[1]="ouvrir/fermer la porte"
	g\position#[1]=21
	g\position#[2]=2
	g\position#[3]=6
	g\position#[4]=0
	g\trigger[2]=0
	g\script[2]=5

	g.groupe=New groupe
	g\num=215
	g\name$="Porte Poste"
	g\map=2
	g\script[1]=215
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="ouvrir/fermer la porte"
	g\position#[1]=12
	g\position#[2]=1
	g\position#[3]=11
	g\position#[4]=0
	g\trigger[2]=0
	g\script[2]=5

	g.groupe=New groupe
	g\num=216
	g\name$="Trieuse"
	g\map=2
	g\script[1]=216
	g\script[4]=g\script[1]
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="examiner la trieuse"
	g\position#[1]=13.5
	g\position#[2]=1
	g\position#[3]=6.5
	g\position#[4]=0

	g.groupe=New groupe
	g\num=217
	g\name$="Infirmière"
	g\map=2
	g\script[1]=217
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="parler à l'Infirmière"
	g\position#[1]=21.2
	g\position#[2]=0.5
	g\position#[3]=36
	g\position#[4]=0
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=218
	g\name$="Teddy"
	g\map=2
	g\script[1]=218
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="parler à Teddy"
	g\position#[1]=26.7
	g\position#[2]=0.5
	g\position#[3]=2.6
	g\position#[4]=0
	g\act_scale#=1
	g\act_type=2

	g.groupe=New groupe
	g\num=219
	g\name$="Escalier vers bateau"
	g\map=2
	g\script[1]=219
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="monter à l'étage supérieur"
	g\range_trigger[2]=2
	g\range_trigger[3]=4
	g\position#[1]=14
	g\position#[2]=3.5
	g\position#[3]=24
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;camps de réfugié
	g.groupe=New groupe
	g\num=501
	g\name$="Ascenseur réfugié"
	g\map=5
	g\script[1]=7
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="monter avec l'ascenseur"
	g\range_trigger[2]=4 ;N° map
	g\range_trigger[3]=3 ;N° entrance
	g\position#[1]=51
	g\position#[2]=1
	g\position#[3]=19
	
	g.groupe=New groupe
	g\num=502
	g\name$="La sentinelle"
	g\map=5
	g\script[1]=502
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec la sentinelle"
	g\position#[1]=38.5
	g\position#[2]=0.48
	g\position#[3]=9
	g\position#[4]=-90
	g\act_scale#=1
	g\act_type=2
	
	g.groupe=New groupe
	g\num=503
	g\name$="Arsène"
	g\map=5
	g\script[1]=503
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="discuter avec Arsène"
	g\position#[1]=6.2
	g\position#[2]=0.48
	g\position#[3]=39.5
	g\position#[4]=110
	g\act_scale#=1
	g\act_type=2
	
	g.groupe=New groupe
	g\num=504
	g\name$="Emanuella"
	g\map=5
	g\script[1]=504
	g\trigger[1]=4
	g\range_trigger[1]=3
	g\nom_action$[1]="discuter avec Emanuella"
	g\position#[1]=1
	g\position#[2]=0.38
	g\position#[3]=5
	g\position#[4]=-90
	g\act_scale#=1
	g\act_type=2
	
	g.groupe=New groupe
	g\num=505
	g\name$="Forgeron"
	g\map=5
	g\script[1]=100
	g\trigger[1]=4
	g\range_trigger[1]=1.5
	g\nom_action$[1]="parler avec le forgeron"
	g\position#[1]=48
	g\position#[2]=0.48
	g\position#[3]=37.5
	g\position#[4]=-90
	g\act_scale#=1
	g\act_type=2

	;;;;;;;;;;;;;;;;1er étage


	For gr.groupe=Each groupe
		gr\old_animation=-1
	Next







	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;PORTE;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	new_porte(101) ; porte de la cellule d'origine
	new_porte(201) ; porte engrenage principale
	new_porte(202) ; porte engrenage énigme
	new_porte(203,214) ; porte engrenage secondaire
	new_porte(204,215) ; porte poste
	new_porte(205) ; porte finale 1
	new_porte(206) ; porte finale 2
	new_porte(301) ; porte maitre J
	new_porte(302) ; porte chasseur 1
	new_porte(303) ; porte chasseur 2
	new_porte(501) ; porte1 camps réfugié
	new_porte(502) ; porte2 camps réfugié
	new_porte(503) ; porte2 camps réfugié

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;AVATARS PJ et PNJ Alliés;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

	;équipe du joueur
	a.avatar=New avatar
	a\num=-1
	a\name$[1]="Inventaire quest du joueur"
	a\classe$[1]="Programmation"

	a.avatar=New avatar
	a\num=1
	a\prop=0
	a\set=1
	a\cat=1
	a\name$[1]="Major"
	a\name$[2]="Major"
	a\groupe=-1
	a\description$[1]="Chef par droit de sang d'un petit village caché. Il aime la chasse, prendre des décisions et l'odeur du café au petit matin. Il n'aime pas les protecteurs des animaux, les gens qui n'obéissent pas et les matins sans café."
	a\description$[2]=""
	
	a.avatar=New avatar
	a\num=2
	a\prop=0
	a\set=1
	a\cat=2
	a\name$[1]="Léopold"
	a\name$[2]="Léopold"
	a\groupe=-1
	a\description$[1]="Fils d'un marchand itinérant, il s'est installé dans ce village après avoir eu un coup de foudre pour la fille du Chef. Sa bonne volonté est appréciée des villageois mais sa trop grande gentillesse en fait la cible rêvée de tous les profiteurs."
	a\description$[2]=""

	a.avatar=New avatar
	a\num=3
	a\prop=0
	a\set=1
	a\cat=3
	a\name$[1]="Adeline"
	a\name$[2]="Adeline"
	a\groupe=-1
	a\description$[1]="Fille du Chef du village. Son égoïsme et sa passion pour la manipulation énervent au plus haut point ses parents qui désespèrent de la voir mûrir un peu."
	a\description$[2]=""
	
	;classes possibles pour les PJ
	a.avatar=New avatar
	a\num=-11
	a\prop=0
	a\set=1
	a\cat=1
	a\name$[1]="Classe 1"
	a\classe$[1]="Aspirant"
	a\description$[1]="L'aspirant est un apprenti-garde. Il s'est durement entrainé au combat et a juré de protéger les habitants de son village."
	a\tactique$[1]="Conseils : L'Aspirant est équilibré et s'en sort plutôt bien au corps à corps. Il devrait donc convenir dans la plupart des situations mais son absence de spécialisation peut rendre un peu dure la fin du jeu."
	a\name$[2]="Class 1"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=4
	a\att[2]=4
	a\att[3]=4
	a\def[1]=10
	a\def[2]=10
	a\def[3]=11
	a\deg[1]=1
	a\deg[2]=2
	a\deg[3]=1
	a\pv[2]=28
	a\pv[1]=a\pv[2]
	a\init=4
	a\cmpt[1]=10
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3

	a.avatar=New avatar
	a\num=-12
	a\prop=0
	a\set=1
	a\cat=3
	a\name$[1]="Classe 2"
	a\classe$[1]="Acrobate"
	a\description$[1]="Après de nombreuses années dans un cirque, l'Acrobate peut enchaîner des roulades et des sauts capables aussi bien de captiver un auditoire que de sauver sa vie sur un champ de bataille."
	a\tactique$[1]="Conseils : L'Acrobate est difficile à toucher grâce à sa haute dextérité mais reste très fragile. Profitez de sa rapidité pour frapper dès le premier tour même avec des armes de corps à corps."
	a\name$[2]="Class 2"
	a\classe$[2]="Acrobat"
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=6
	a\att[2]=3
	a\att[3]=4
	a\def[1]=11
	a\def[2]=12
	a\def[3]=12
	a\deg[1]=0
	a\deg[2]=-1
	a\deg[3]=0
	a\pv[2]=17
	a\pv[1]=a\pv[2]
	a\init=7
	a\cmpt[1]=11
	a\cmpt[2]=12
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3

	a.avatar=New avatar
	a\num=-13
	a\prop=0
	a\set=1
	a\cat=1
	a\name$[1]="Classe 3"
	a\classe$[1]="Médecin"
	a\description$[1]="Ancien médecin militaire, c'est un spécialiste de la suture et rebouture avec trois bouts de ficelle. Certes il ne peut pas rivaliser avec un hôpital bien équipé mais bien des gens doivent la vie à ses mains habiles et son professionnalisme."
	a\tactique$[1]="Conseils : Le Médecin est fragile et n'est pas très bon au combat, que ce soit au contact ou à distance. Il faudra donc le protéger et cela vous empêchera aussi de prendre un autre combattant mais sa compétence [Médecin] (qui ne fonctionne que s'il n'est pas inconscient) peut valoir le coup."
	a\name$[2]="Class 3"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=3
	a\att[2]=1
	a\att[3]=3
	a\def[1]=8
	a\def[2]=8
	a\def[3]=10
	a\deg[1]=0
	a\deg[2]=-1
	a\deg[3]=1
	a\pv[2]=20
	a\pv[1]=a\pv[2]
	a\init=2
	a\cmpt[1]=3
	a\cmpt[2]=9
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	
	a.avatar=New avatar
	a\num=-14
	a\prop=0
	a\set=1
	a\cat=3
	a\name$[1]="Classe 4"
	a\classe$[1]="Infirmier"
	a\description$[1]="Encore jeune et plein de bonne volonté, l'Infirmier accompagne souvent un Médecin afin d'apprendre son art (même si il sert le plus souvent de garde du corps et d'aide de camp)."
	a\tactique$[1]="Conseils : L'infirmier est plus utile en combat que le Médecin mais son bonus de soin est moins élevé et il n'a pas d'arme de prédilection."
	a\name$[2]="Class 4"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=4
	a\att[2]=3
	a\att[3]=5
	a\def[1]=10
	a\def[2]=8
	a\def[3]=11
	a\deg[1]=1
	a\deg[2]=-1
	a\deg[3]=1
	a\pv[2]=23
	a\pv[1]=a\pv[2]
	a\init=5
	a\cmpt[1]=2
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	
	a.avatar=New avatar
	a\num=-15
	a\prop=0
	a\set=1
	a\cat=5
	a\name$[1]="Classe 5"
	a\classe$[1]="Brute"
	a\description$[1]="Contrairement à ce que pourrait faire penser son physique impressionnant, la Brute n'est pas idiote et profite même souvent de ce stéréotype pour bluffer les ennemis qui la sous-estiment."
	a\tactique$[1]="Conseils : Pas besoin de beaucoup réfléchir avec la Brute. Profitez de sa grande force et de sa résistance pour l'envoyer en première ligne."
	a\name$[2]="Class 5"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=1
	a\att[2]=5
	a\att[3]=0
	a\def[1]=12
	a\def[2]=14
	a\def[3]=8
	a\deg[1]=2
	a\deg[2]=3
	a\deg[3]=-1
	a\pv[2]=42
	a\pv[1]=a\pv[2]
	a\init=1
	a\cmpt[1]=1
	a\cmpt[2]=13
	a\cmpt[3]=16
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=1.5
	a\faiblesse#[3]=2.25

	a.avatar=New avatar
	a\num=-16
	a\prop=0
	a\set=1
	a\cat=1
	a\name$[1]="Classe 6"
	a\classe$[1]="Assassin"
	a\description$[1]="Pour ce spécialiste des coups bas, tout est une question de timing. Une petite estocade peut faire autant de dégâts qu'un grand coup de hache si la victime ne peut pas se défendre. Et imaginez ce que pourrait faire un grand coup de hache bien placé !"
	a\tactique$[1]="Conseils : Essayez de frapper les cible ayant le moins de défense. Plus la cible a de malus de defense (bouclier rouge), plus vous ferez de dégâts. Attention cependant à ne pas mourir avant de pouvoir frapper."
	a\name$[2]="Class 6"
	a\classe$[2]="Assassin"
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=7
	a\att[2]=2
	a\att[3]=5
	a\def[1]=11
	a\def[2]=9
	a\def[3]=11
	a\deg[1]=2
	a\deg[2]=-1
	a\deg[3]=1
	a\pv[2]=21
	a\pv[1]=a\pv[2]
	a\init=8
	a\cmpt[1]=14
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	
	a.avatar=New avatar
	a\num=-17
	a\prop=0
	a\set=1
	a\cat=3
	a\name$[1]="Classe 7"
	a\classe$[1]="Flingeur"
	a\description$[1]="Les flingues, c'est sa passion depuis son enfance. Pourquoi jouer dehors quand on peut démonter un pistolet~? Pourquoi avoir des amis quand on peut écouter le doux murmure de la mécanique, suivi du rugissement de la poudre~? Pourquoi rester enfermé alors qu'on pourrait flinguer tout le monde~?"
	a\tactique$[1]="Conseils : Faites irradier la passion du Flingeur sur vos ennemis et n'hésitez pas à tirer en rafale. Cependant n'oubliez pas que son bonus d'attaque avec les armes de poings n'est pas appliqué sur les autres armes."
	a\name$[2]="Class 7"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=3
	a\att[2]=2
	a\att[3]=6
	a\def[1]=10
	a\def[2]=9
	a\def[3]=12
	a\deg[1]=0
	a\deg[2]=-1
	a\deg[3]=2
	a\pv[2]=32
	a\pv[1]=a\pv[2]
	a\init=6
	a\cmpt[1]=4
	a\cmpt[2]=8
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	
	

	a.avatar=New avatar
	a\num=-18
	a\prop=0
	a\set=1
	a\cat=1
	a\name$[1]="Classe 8"
	a\classe$[1]="Franc-Tireur"
	a\description$[1]="Le Franc-Tireur fait généralement parti d'un petit groupe de mercenaire, engagé pour protéger un village. Il est spécialement entrainé aux armes à feu, en particulier les fusils semi-automatiques, ce qui en fait un allié précieux mais couteux."
	a\tactique$[1]="Conseils : Le Franc-Tireur fera des ravages avec une arme pouvant tirer plusieurs fois par tour, comme un fusil semi-automatique ou un pistolet mitrailleur. Mais n'oubliez pas qu'il s'agit d'armes de haut niveau, chères et rares."
	a\name$[2]="Class 8"
	a\classe$[2]=""
	a\description$[2]=""
	a\tactique$[2]=""
	a\att[1]=2
	a\att[2]=4
	a\att[3]=7
	a\def[1]=8
	a\def[2]=10
	a\def[3]=11
	a\deg[1]=0
	a\deg[2]=1
	a\deg[3]=2
	a\pv[2]=38
	a\pv[1]=a\pv[2]
	a\init=3
	a\cmpt[1]=15
	a\cmpt[2]=5
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	
	a.avatar=New avatar
	a\num=-21
	a\prop=0
	a\set=1
	a\cat=-21
	a\name$[1]="Aegis-1"
	a\classe$[1]="Gearbot Aegis"
	a\description$[1]="Un GearBot modèle Aegis, immatriculé 1."
	a\tactique$[1]="Blabla, tu vois pas ça !"
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
	a\init=1
	a\cmpt[1]=15
	a\cmpt[2]=5
	For t=1 To 8
		a\equi[t]=0
	Next
	a\groupe=0
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3

	a.avatar=New avatar
	a\num=11
	a\prop=0
	a\set=1
	a\cat=12
	a\name$[1]="Fillette"
	a\classe$[1]="Apprentie Chasseuse"
	a\description$[1]="La fillette est née à FactoryTec et travaille dur pour réaliser son rêve, devenir un membre de la caste très respectée des Chasseurs (ça et manger des chamallows grillés)."
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
	a\init=1
	For t=1 To 15
		a\cmpt[t]=0
	Next
	a\equi[1]=1;13
	a\equi[2]=0;1
	a\equi[4]=100
	a\equi[5]=150
	a\groupe=108
	a\faiblesse#[1]=1
	a\faiblesse#[2]=2
	a\faiblesse#[3]=3
	a\cmpt[1]=2 ; interne en médecine
	
	For a.avatar = Each avatar
		For i=2 To NB_LANGUES
			If a\name$[i]="" Then a\name$[i] = "NTY: "+a\name$[1]
			If a\classe$[i]="" Then a\classe$[i] = "NTY: "+a\classe$[1]
			If a\description$[i]="" Then a\description$[i] = "NTY: "+a\description$[1]
			If a\tactique$[i]="" Then a\tactique$[i] = "NTY: "+a\tactique$[1]
		Next
	Next 

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Agresseurs;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	agr.agresseur=New agresseur
	agr\num=1
	agr\name$="Testeur dans le garage/prison"
	agr\map=315
	agr\groupe=199
;	agr\animation[1]=1
;	agr\animation[2]=2
;	agr\animation[3]=3
	agr\position#[1]=25
	agr\position#[2]=0.45
	agr\position#[3]=25
	agr\radius=5
	agr\actif=1
	agr\polyx#[1]=28
	agr\polyz#[1]=25
	agr\polyx#[2]=25
	agr\polyz#[2]=28
	agr\polyx#[3]=22
	agr\polyz#[3]=25
	agr\polyx#[4]=25
	agr\polyz#[4]=22

	agr.agresseur=New agresseur
	agr\num=151
	agr\name$="Rat poké 1"
	agr\map=1
	agr\groupe=151
	agr\position#[1]=-0.6
	agr\position#[2]=0.1
	agr\position#[3]=13.6
	agr\radius=5
	agr\actif=1
	agr\polyx#[1]=3
	agr\polyz#[1]=13.6
	agr\polyx#[2]=2
	agr\polyz#[2]=12.6
	agr\polyx#[3]=-0.65
	agr\polyz#[3]=13.6
	agr\polyx#[4]=-0.6
	agr\polyz#[4]=16.6

	agr.agresseur=New agresseur
	agr\num=152
	agr\name$="Rat poké 2"
	agr\map=1
	agr\groupe=152
	agr\position#[1]=12.6
	agr\position#[2]=0.1
	agr\position#[3]=31.8
	agr\radius=5
	agr\actif=1
	agr\polyx#[1]=12.6
	agr\polyz#[1]=31.8
	agr\polyx#[2]=10.1
	agr\polyz#[2]=31.5
	agr\polyx#[3]=12.6
	agr\polyz#[3]=28
	agr\polyx#[4]=15.1
	agr\polyz#[4]=31.5


	agr.agresseur=New agresseur
	agr\num=153
	agr\name$="Rat poké 3"
	agr\map=1
	agr\groupe=153
	agr\position#[1]=27.25
	agr\position#[2]=0.1
	agr\position#[3]=20
	agr\radius=5
	agr\actif=1
	agr\polyx#[1]=27.25
	agr\polyz#[1]=17
	agr\polyx#[2]=24.25
	agr\polyz#[2]=20
	agr\polyx#[3]=27.25
	agr\polyz#[3]=23
	agr\polyx#[4]=30.25
	agr\polyz#[4]=20
	
	agr.agresseur=new agresseur
	agr\num=31501
	agr\name$="Test de Patrouilleur"
	agr\map=315
	agr\groupe=31501
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=480
	agr\name$="Patrouille canyon 1"
	agr\map=4
	agr\groupe=480
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=481
	agr\name$="Patrouille canyon 2"
	agr\map=4
	agr\groupe=481
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=482
	agr\name$="Patrouille canyon 3"
	agr\map=4
	agr\groupe=482
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=483
	agr\name$="Patrouille canyon 4"
	agr\map=4
	agr\groupe=483
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=484
	agr\name$="Patrouille canyon 5"
	agr\map=4
	agr\groupe=484
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=485
	agr\name$="Patrouille canyon 6"
	agr\map=4
	agr\groupe=485
	agr\actif=1
	agr\radius=5
	
	agr.agresseur=new agresseur
	agr\num=486
	agr\name$="Patrouille canyon 7"
	agr\map=4
	agr\groupe=486
	agr\actif=1
	agr\radius=5
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Patrouilleurs;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	pat.patrouilleur=New patrouilleur
	pat\num=31501
	pat\name$="Test de Patrouille"
	pat\map=315
	pat\actif=1
	pat\vivant=1
	pat\agresseur=31501
	pat\en_cours=1
	pat\cone#=20
	pat\range#=20
	pat\actions[1]=1
	pat\var1#[1]=32
	pat\var2#[1]=0.45
	pat\var3#[1]=30
	pat\var4#[1]=3 ; vitesse en m/s
	pat\actions[2]=1
	pat\var1#[2]=40
	pat\var2#[2]=0.45
	pat\var3#[2]=30
	pat\var4#[2]=3 ; vitesse en m/s
	pat\actions[3]=1
	pat\var1#[3]=50
	pat\var2#[3]=0.45
	pat\var3#[3]=20
	pat\var4#[3]=3 ; vitesse en m/s
	pat\actions[4]=1
	pat\var1#[4]=40
	pat\var2#[4]=0.45
	pat\var3#[4]=10
	pat\var4#[4]=3 ; vitesse en m/s
	pat\actions[5]=1
	pat\var1#[5]=32
	pat\var2#[5]=0.45
	pat\var3#[5]=10
	pat\var4#[5]=3 ; vitesse en m/s

	
	pat.patrouilleur=New patrouilleur
	pat\num=480
	pat\name$="Patrouille extérieur 1"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=480
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=92
	pat\var2#[1]=-5.1
	pat\var3#[1]=-14
	pat\var4#[1]=3 ; vitesse en m/s
	pat\actions[2]=2
	pat\var1#[2]=3000
	pat\var2#[2]=1
	pat\actions[3]=1
	pat\var1#[3]=87.7
	pat\var2#[3]=-5.5
	pat\var3#[3]=-5
	pat\var4#[3]=3
	pat\actions[4]=1
	pat\var1#[4]=90
	pat\var2#[4]=-6
	pat\var3#[4]=2.4
	pat\var4#[4]=3
	pat\actions[5]=1
	pat\var1#[5]=95
	pat\var2#[5]=-6
	pat\var3#[5]=10.6
	pat\var4#[5]=3
	pat\actions[6]=1
	pat\var1#[6]=98
	pat\var2#[6]=-5.6
	pat\var3#[6]=20
	pat\var4#[6]=3
	pat\actions[7]=1
	pat\var1#[7]=90
	pat\var2#[7]=-3.2
	pat\var3#[7]=32
	pat\var4#[7]=3
	pat\actions[8]=2
	pat\var1#[8]=3000
	pat\var2#[8]=1
	pat\actions[9]=1
	pat\var1#[9]=90
	pat\var2#[9]=-3.2
	pat\var3#[9]=32
	pat\var4#[9]=3
	pat\actions[10]=1
	pat\var1#[10]=98
	pat\var2#[10]=-5.6
	pat\var3#[10]=20
	pat\var4#[10]=3
	pat\actions[11]=1
	pat\var1#[11]=95
	pat\var2#[11]=-6
	pat\var3#[11]=10.6
	pat\var4#[11]=3
	pat\actions[12]=1
	pat\var1#[12]=90
	pat\var2#[12]=-6
	pat\var3#[12]=2.4
	pat\var4#[12]=3
	pat\actions[13]=1
	pat\var1#[13]=87.7
	pat\var2#[13]=-5.5
	pat\var3#[13]=-5
	pat\var4#[13]=3
	
	pat.patrouilleur=New patrouilleur
	pat\num=481
	pat\name$="Patrouille extérieur 2"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=481
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=47.6
	pat\var2#[1]=-4.6
	pat\var3#[1]=25.4
	pat\var4#[1]=2
	pat\actions[2]=1
	pat\var1#[2]=47.5
	pat\var2#[2]=-5
	pat\var3#[2]=36.75
	pat\var4#[2]=2
	pat\actions[3]=1
	pat\var1#[3]=20.4
	pat\var2#[3]=-5.1
	pat\var3#[3]=36.8
	pat\var4#[3]=2
	pat\actions[4]=1
	pat\var1#[4]=15.4
	pat\var2#[4]=-4.6
	pat\var3#[4]=-22.8
	pat\var4#[4]=2
	pat\actions[5]=1
	pat\var1#[5]=26.1
	pat\var2#[5]=-5.2
	pat\var3#[5]=-41.25
	pat\var4#[5]=2
	pat\actions[6]=1
	pat\var1#[6]=37.5
	pat\var2#[6]=-6
	pat\var3#[6]=-40.75
	pat\var4#[6]=2
	pat\actions[7]=1
	pat\var1#[7]=43.7
	pat\var2#[7]=-3.6
	pat\var3#[7]=-26
	pat\var4#[7]=2
	pat\actions[8]=1
	pat\var1#[8]=41.2
	pat\var2#[8]=0
	pat\var3#[8]=0.2
	pat\var4#[8]=2

	pat.patrouilleur=New patrouilleur
	pat\num=482
	pat\name$="Patrouille extérieur 3"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=482
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=26.1
	pat\var2#[1]=-5.2
	pat\var3#[1]=-41.25
	pat\var4#[1]=2
	pat\actions[2]=1
	pat\var1#[2]=37.5
	pat\var2#[2]=-6
	pat\var3#[2]=-40.75
	pat\var4#[2]=2
	pat\actions[3]=1
	pat\var1#[3]=43.7
	pat\var2#[3]=-3.6
	pat\var3#[3]=-26
	pat\var4#[3]=2
	pat\actions[4]=1
	pat\var1#[4]=41.2
	pat\var2#[4]=0
	pat\var3#[4]=0.2
	pat\var4#[4]=2
	pat\actions[5]=1
	pat\var1#[5]=47.6
	pat\var2#[5]=-4.6
	pat\var3#[5]=25.4
	pat\var4#[5]=2
	pat\actions[6]=1
	pat\var1#[6]=47
	pat\var2#[6]=-6
	pat\var3#[6]=36.75
	pat\var4#[6]=2
	pat\actions[7]=1
	pat\var1#[7]=20.4
	pat\var2#[7]=-5.1
	pat\var3#[7]=36.8
	pat\var4#[7]=2
	pat\actions[8]=1
	pat\var1#[8]=15.4
	pat\var2#[8]=-4.6
	pat\var3#[8]=-22.8
	pat\var4#[8]=2
	

	pat.patrouilleur=New patrouilleur
	pat\num=483
	pat\name$="Patrouille extérieur 4"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=483
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=43
	pat\var2#[1]=-4.6
	pat\var3#[1]=38
	pat\var4#[1]=3
	pat\actions[2]=3
	pat\var1#[2]=225
	pat\var2#[2]=20
	pat\var3#[2]=0
	pat\actions[3]=3
	pat\var1#[3]=315
	pat\var2#[3]=20
	pat\var3#[3]=0
	pat\actions[4]=3
	pat\var1#[4]=90
	pat\var2#[4]=90
	pat\var3#[4]=0
	pat\actions[5]=1
	pat\var1#[5]=24
	pat\var2#[5]=-5.8
	pat\var3#[5]=38
	pat\var4#[5]=3
	pat\actions[6]=1
	pat\var1#[6]=18
	pat\var2#[6]=-4.6
	pat\var3#[6]=2
	pat\var4#[6]=3
	pat\actions[7]=3
	pat\var1#[7]=135
	pat\var2#[7]=20
	pat\var3#[7]=0
	pat\actions[8]=3
	pat\var1#[8]=215
	pat\var2#[8]=20
	pat\var3#[8]=0
	pat\actions[9]=3
	pat\var1#[9]=0
	pat\var2#[9]=90
	pat\var3#[9]=0
	pat\actions[10]=1
	pat\var1#[10]=24
	pat\var2#[10]=-5.8
	pat\var3#[10]=38
	pat\var4#[10]=3
	
	pat.patrouilleur=New patrouilleur
	pat\num=484
	pat\name$="Patrouille extérieur 5"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=484
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=22.3
	pat\var2#[1]=-4.9
	pat\var3#[1]=-41.3
	pat\var4#[1]=3
	pat\actions[2]=3
	pat\var1#[2]=270
	pat\var2#[2]=30
	pat\var3#[2]=0
	pat\actions[3]=1
	pat\var1#[3]=37.9
	pat\var2#[3]=-5.9
	pat\var3#[3]=-42
	pat\var4#[3]=3
	pat\actions[4]=3
	pat\var1#[4]=90
	pat\var2#[4]=30
	pat\var3#[4]=0
	
	pat.patrouilleur=New patrouilleur
	pat\num=485
	pat\name$="Patrouille extérieur 6"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=485
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=1
	pat\var1#[1]=43.1
	pat\var2#[1]=-2.9
	pat\var3#[1]=-27.1
	pat\var4#[1]=2
	pat\actions[2]=1
	pat\var1#[2]=36.5
	pat\var2#[2]=-4.8
	pat\var3#[2]=-40
	pat\var4#[2]=2
	pat\actions[3]=1
	pat\var1#[3]=82
	pat\var2#[3]=-4.8
	pat\var3#[3]=-43
	pat\var4#[3]=2
	pat\actions[4]=1
	pat\var1#[4]=36.5
	pat\var2#[4]=-4.8
	pat\var3#[4]=-40
	pat\var4#[4]=2
	
	pat.patrouilleur=New patrouilleur
	pat\num=486
	pat\name$="Patrouille extérieur 7"
	pat\map=4
	pat\actif=1
	pat\vivant=1
	pat\agresseur=486
	pat\en_cours=1
	pat\cone#=45
	pat\range#=5
	pat\actions[1]=3
	pat\var1#[1]=0
	pat\var2#[1]=30
	pat\var3#[1]=0
	pat\actions[2]=3
	pat\var1#[2]=120
	pat\var2#[2]=30
	pat\var3#[2]=0
	pat\actions[3]=3
	pat\var1#[3]=240
	pat\var2#[3]=30
	pat\var3#[3]=0
	
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Butin;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	For t=1 To 1
		;les butins "poubelles"
		butin.butin=New butin
		butin\num=-t
		butin\prop=-1
		butin\map=-1
		;les butins "inventaires"
		butin.butin=New butin
		butin\num=t
		butin\prop=t
		butin\map=-1
	Next
	; vitrine du forgeron
	butin.butin=New butin
	butin\num=-12
	butin\prop=-1
	butin\map=-1
	butin\loot[1]=1
	butin\loot[2]=2
	butin\loot[3]=3
	butin\loot[4]=4
	butin\loot[5]=8
	butin\loot[6]=13
	butin\loot[7]=20
	butin\loot[8]=1
	butin\loot[9]=2
	butin\loot[10]=3
	butin\loot[11]=4
	butin\loot[12]=8
	butin\loot[13]=13
	butin\loot[14]=20
	butin\loot[15]=101
	butin\loot[16]=102
	butin\loot[17]=103
	butin\loot[18]=104
	butin\loot[19]=105
	butin\loot[20]=106
	butin\loot[21]=151
	If mode_debug=1
		butin\loot[22]=201
		butin\loot[23]=201
	Endif
	; comptoir du forgeron
	butin.butin=New butin
	butin\num=-13
	butin\prop=-1
	butin\map=-1
	; panier du forgeron
	butin.butin=new butin
	butin\num=-14
	butin\prop=-1
	butin\map=-1


	If mode_debug=1
		;TEST : A ENLEVER au final
		butin.butin=New butin
		butin\num=11
		butin\prop=0
		butin\map=315
		butin\kind=1
		butin\position#[1]=10
		butin\position#[2]=0
		butin\position#[3]=14
		butin\position#[4]=15
		butin\caps=100
		butin\junk=100
		butin\loot[1]=12
		butin\loot[2]=1
		butin\loot[3]=2
		butin\loot[4]=4
		butin\loot[5]=8
		butin\loot[6]=13
		butin\loot[8]=22
		butin\loot[9]=101
		butin\loot[10]=105
		butin\loot[11]=151
		butin\loot[12]=105
		butin\loot[13]=151
		butin\loot[14]=105
		butin\loot[15]=151
		butin\loot[16]=105
		butin\loot[17]=151
		butin\loot[18]=105
		butin\loot[19]=151
		butin\loot[20]=105
		butin\loot[21]=151
		butin\loot[22]=105
		butin\loot[23]=151
		butin\loot[24]=105
		butin\loot[25]=151
		for t=0 to 24
			for u=1 to 10
				butin\loot[t*10+u]=u
			next
		next
		
		butin\quest[1]=23
	Endif
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;MAP;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	map.map=New map
	map\num=1 ; garage
	map\stat[4]=0

	map.map=New map
	map\num=2 ; Rdc
	;map\stat[10]=1

	map.map=New map
	map\num=3 ; Premier Etage
End Function