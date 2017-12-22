Global num_version$="0.43"
date_version$="2017-10-06" 

AppTitle Str("Final Whim "+num_version$)

.demarrage
Const screenwidth=800
Const screenheight=600
Const screendepth=32
Const screenx#=Float(800)/(1366.)
Const screeny#=Float(600)/(768.)
Global screenscale#
Const screenxf#=1
Const screenyf#=1
If screeny#>=1
	screenscale#=Floor(screeny#)
ElseIf screeny#>0.75
	screenscale#=1
Else
	screenscale#=0.5	
EndIf
Graphics3D 800,600,32,2
SetBuffer BackBuffer()
SeedRnd (MilliSecs())
HidePointer

.var_global

;Global musique_de_fond=0 ; le fond sonore qui ne s'arrête qu'en pause
Const type_none=0
Const type_joueur=1
Const type_perso=2
Const type_mur=8
Const type_block=9 ; mur qui laisse passer la caméra.
Const type_cam=10
Const type_rideau=11 ; "mur" qui laisse passer le joueur mais pas la caméra

Const COEFF_MARCHAND=2
Const NB_CLASSE_DISPO=8
Const NB_ASTUCES=19 ; nombre total d'astuces
Global DIFFICULTY=1 ; 1 Facile, 2 Moyen, 3 Difficile
Const LIMITE_QUEST=100 ; nb max d'objets de quête (inclus les quêtes en cours et terminée (?))
Const NB_ACTION_PAT=30
Const min_grav#=-2;-1.47 ; la vitesse à partir de laquelle on ne controle plus le perso
Const chute_grav#=-5 ; vitesse à partir de laquelle on est considéré comme en train de chuter
Const TIMER_DGTS = 20
Const MAX_FRAME_FIGHTER_GFX = 64 ; l'image la plus haute dans les combattants (pour éviter de charger du vide pour rien)

Const TEMPS_ROUND=100000 ; en millisecondes, le temps max pour choisir une action en combat
Const MIN_BONUS_DEF=-6 ; le minimum et maximum de bonus de def possible en chaque instant (+2 et -3 boucliers)
Const MAX_BONUS_DEF=4
Const ATD_MOD_BONUS_DEF=0 ; combien une attaque à distance enlève de défense
Const ACC_MOD_BONUS_DEF=2 ; combien une attaque de corps à corps enlève de défense
Const BONUS_DEFENDRE=6 ; combien on gagne de def avec l'action 'Défense'
Const DEFENSE_REGEN_ACTIVE=0
Const DEFENSE_REGEN=0 ; combien on regen de bonus de défense par tour
Const DEFENSE_MAX_REGEN=0 ; à combien maximum on peut monter en défense grace à la regen
Const LIMITE_BLESSURE#=0.3 ; à partir de combien on est considéré comme blessé (-2 en à peu près tout)
Const ATT_SOURNOISE=4 ; le bonus de dégâts de l'attaque sournoise par bouclier
Const BONUS_MEDIC#=0.25 ; le ratio de pv rendus par le médecin à la fin d'un combat

Const NB_LANGUES=2 ; le nombre de langues supportées par Final Whim

Global time_fps
Global old_time_fps ; nécessaire pour le calcul des fps
Global frame_fps
Const frame_lim_fps=25
			
Global nb_frame ; nombre de frame max par seconde
Global coeff_frame# ; nb de frame qu'il y aurait dû y avoir depuis la dernière
Global delta_frame ; la différence en millisecs entre les deux frames
Global tour_frame ; nb frames visuelles qu'il va y avoir ce tour ci pour compenser
Global reste_frame# ; le reste de frame qu'il faudra faire la prochaine fois.
Global frame_a
Global frame_b

Global mode_de_jeu=1
Global Pas_de_Combat=0
Global quitter_jeu=0
Global player_in_control=True
Global player_map
Dim player_avatar(3) ; les persos choisis au début par le joueur
Global player_name$
Global player_drop
Global player_leader ; lequel des perso du triumvira agit lorsque seul un perso peut agir (arène, épreuve protecteur...) [1-3]
Global player_junk
Global player_caps

;Global grey=LoadImage("sprites\menu\grey.bmp")
Global cam
Global cam_pivot
Global cam_parent
Global zoom_cam#
Global cas_cam
Global sky
Global old_pl_grp_y#
Global grav#
Global lock_action=True
Global choix_qcm
Global chat_mode
Global nouvelle_partie=0
Global master_id
Global player_id
Global current_map
Global pl_grp_pivot
Global pl_grp_manikin
Global event_action=0
Global interaction_avec=0
Global interaction_script=0
Global map_entrance
Global loaded_map
Global agressed
Global groupe_agresseur

Global leader_script
Global leader_event_action
Global leader_choix_qcm

Global sortie_combat ; 0 rien, 1 victoire, 2 défaite, 3 fuite
Global image_load_combat
Global num_combat
Global combat_action
Global combat_menu_action
Global combat_temp_anim
Global temps_restant
Global combat_from
Global combat_target
Dim action_target(9)
Dim action_reussite(9)
Global combat_place
Global combat_react
Global combat_att_double
Global combat_reussite
Global combat_var1
Global combat_var2
Global combat_old_frame
Global combat_frame
Global combat_vainqueur
Dim combat_dgts(9)
Dim atd_cible(3)
Dim atd_coeff#(3)
Global atd_contact

Global ch_bgm1
Global num_bgm1
Global vol_bgm1#
Global tar_bgm1#
Global spd_bgm1#

Global ch_bgm2
Global num_bgm2
Global vol_bgm2#
Global tar_bgm2#
Global spd_bgm2#

Global activator ; un sprite qui va servir pour indiquer avec qui on interagit
Global activator_actif

Global loading=LoadAnimImage("sprites\menu\loading.jpg",400,400,0,4)
Global curseur=LoadImage("sprites\menu\curseur.bmp")
Global little_wheel=LoadAnimImage("sprites\menu\wheel20-08.bmp",20,20,0,8)
Global gfx_coffre=LoadImage("sprites\menu\butin.bmp")
MidHandle gfx_coffre
Global gfx_Menu_on=LoadImage("sprites\menu\menu(on).jpg")
MidHandle gfx_Menu_on
Global gfx_Pause_on=LoadImage("sprites\menu\pause(on).jpg")
MidHandle gfx_Pause_on
Global gfx_alerte=LoadImage("sprites\gfx\alerte.bmp")
MidHandle gfx_alerte
Global gfx_bouclier=LoadAnimImage("sprites\gfx\defense.bmp",16,16,0,2)
Global gfx_chiffre=LoadAnimImage("sprites\gfx\chiffres.bmp",16,20,0,12)
Global timer_animation# ; roule en continu (pour les animations de fond)
Global timer_animation2# ; est remis à zéro à chaque nouvel action à animer
Global drawmouse
Global message_curseur$=""
Global old_message_curseur$=""
Global message_action$=""
Global ch_clic
Global msg_radio$=""
Global mode_debug=0 ;LBA to reset mode_debug=0
Global nb_tour
Global nb_partie=1

Global big_font
Global little_font
Global small_font
Global middle_font

Global disc_len#
Global onglet ;  quel onglet du menu

Global bouton_combat
Global bouton_combat_sombre
Global combat_barre_vide
Global combat_barre_pleine
Global combat_barretexte
Global combat_roue
Global fight_faces=LoadAnimImage("sprites\combattants\faces.jpg",25,25,0,30)
Global fight_faces_sombres=LoadAnimImage("sprites\combattants\faces_sombres.jpg",25,25,0,30)
Global classe_tete=LoadAnimImage("sprites\combattants\classe_tete.png",40,40,0,9)
Global fight_ring_1=LoadAnimImage("sprites\gfx\fight_ring_1.bmp",128,64,0,8) 
Global fight_ring_2=LoadAnimImage("sprites\gfx\fight_ring_2.bmp",128,64,0,8)
Global fight_ring_3=LoadAnimImage("sprites\gfx\fight_ring_3.bmp",128,64,0,8)
Global fight_ring_4=LoadAnimImage("sprites\gfx\fight_ring_4.bmp",128,64,0,8)
MidHandle fight_ring_1
MidHandle fight_ring_2
MidHandle fight_ring_3
MidHandle fight_ring_4

Global fond_grey2=LoadImage("sprites\menu\test_fond.png")
Global miniblock_tl=LoadImage("sprites\menu\miniblock_tl.bmp")
Global miniblock_tr=LoadImage("sprites\menu\miniblock_tr.bmp")
Global miniblock_bl=LoadImage("sprites\menu\miniblock_bl.bmp")
Global miniblock_br=LoadImage("sprites\menu\miniblock_br.bmp")
Global miniblock_top=LoadImage("sprites\menu\miniblock_top.bmp")
Global miniblock_btm=LoadImage("sprites\menu\miniblock_btm.bmp")
Global miniblock_left=LoadImage("sprites\menu\miniblock_left.bmp")
Global miniblock_right=LoadImage("sprites\menu\miniblock_right.bmp")
Global smallblock_tl=LoadImage("sprites\menu\smallblock_tl.bmp")
Global smallblock_tr=LoadImage("sprites\menu\smallblock_tr.bmp")
Global smallblock_bl=LoadImage("sprites\menu\smallblock_bl.bmp")
Global smallblock_br=LoadImage("sprites\menu\smallblock_br.bmp")
Global bigblock_tl=LoadImage("sprites\menu\bigblock_tl.bmp")
Global bigblock_tr=LoadImage("sprites\menu\bigblock_tr.bmp")
Global bigblock_bl=LoadImage("sprites\menu\bigblock_bl.bmp")
Global bigblock_br=LoadImage("sprites\menu\bigblock_br.bmp")
Global bigblock_top1=LoadImage("sprites\menu\bigblock_top1.bmp")
Global bigblock_top2=LoadImage("sprites\menu\bigblock_top2.bmp")
Global bigblock_btm1=LoadImage("sprites\menu\bigblock_btm1.bmp")
Global bigblock_btm2=LoadImage("sprites\menu\bigblock_btm2.bmp")
Global bigblock_left1=LoadImage("sprites\menu\bigblock_left1.bmp")
Global bigblock_left2=LoadImage("sprites\menu\bigblock_left2.bmp")
Global bigblock_right1=LoadImage("sprites\menu\bigblock_right1.bmp")
Global bigblock_right2=LoadImage("sprites\menu\bigblock_right2.bmp")
Global bordure_tl=LoadImage("sprites\menu\bordure_tl.bmp")
Global bordure_tr=LoadImage("sprites\menu\bordure_tr.bmp")
Global bordure_bl=LoadImage("sprites\menu\bordure_bl.bmp")
Global bordure_br=LoadImage("sprites\menu\bordure_br.bmp")
Global bordure_top=LoadImage("sprites\menu\bordure_top.png")
Global bordure_btm=LoadImage("sprites\menu\bordure_btm.png")
Global bordure_right=LoadImage("sprites\menu\bordure_right.png")
Global bordure_left=LoadImage("sprites\menu\bordure_left.png")

Global fond_hud=LoadImage("sprites\menu\HUD.bmp")
Global non_icone=LoadImage("sprites\objets\non_icone_1.jpg")
Global non_icone2=LoadImage("sprites\objets\non_icone_2.jpg")


;global Loran
Global centre_porteAscenceur
Global tranche1_porteAscenceur
Global tranche2_porteAscenceur
Global tranche3_porteAscenceur
Global tranche4_porteAscenceur
Global tranche5_porteAscenceur
Global tranche6_porteAscenceur
Global tranche7_porteAscenceur
Global centre_porteAscenceur1
Global tranche1_porteAscenceur1
Global tranche2_porteAscenceur1
Global tranche3_porteAscenceur1
Global tranche4_porteAscenceur1
Global tranche5_porteAscenceur1
Global tranche6_porteAscenceur1
Global tranche7_porteAscenceur1
Global centre_porteAscenceur2
Global tranche1_porteAscenceur2
Global tranche2_porteAscenceur2
Global tranche3_porteAscenceur2
Global tranche4_porteAscenceur2
Global tranche5_porteAscenceur2
Global tranche6_porteAscenceur2
Global tranche7_porteAscenceur2
Global porte_cellule7
Global porte2_cellule7
Global porte3_cellule7
Global porte4_cellule7
Global porte_plafond_metaPorte
Global Verrin1_metaPorte
Global Verrin2_metaPorte
Global Verrin3_metaPorte
Global Verrin4_metaPorte
Global Verrin5_metaPorte
Global Verrin6_metaPorte
Global Verrin7_metaPorte
Global Verrin8_metaPorte
Global Verrin9_metaPorte
Global Verrin10_metaPorte
Global Verrou1_metaPorte
Global Verrou2_metaPorte
Global Tuyau1_metaPorte
Global Tuyau2_metaPorte
Global tete_metaPorte
Global base_chapeau_metaPorte
Global forme_chapeau_metaPorte
Global pivot_nez_metaPorte
Global main1_metaPorte
Global main2_metaPorte
Global boitierSteamille
Global tex_cuivre1$
Global img_ContourSteamPorte$
Global img_ContourSteamPorte2$
Global nomDuScriptEnCours$
Global xml_ScriptEnCours
Global xml_Avancement

Global g_bHUDactif=1 ;1 pour activer l'HUD, 0 pour la désactiver
Global fond_Dialogue=LoadImage("sprites\menu\HUD_dialogue.png")
Global fond_DialogueQCM=LoadImage("sprites\menu\HUD_dialogueQCM.png")
Global fond_BoutonQCM=LoadImage("sprites\menu\HUD_dialogue_bouton.png")
Global fond_Combat=LoadImage("sprites\menu\HUD_combat.png")
Global fond_animation
Global xml_ScriptPartiel=""
Global phrasesIndex=0
Dim variableTemporaire(16) ; Permet d'utiliser un tableau pour des variables locales ATTENTION a bien les réinitialiser.
Global entiteTest
Global pas#=1

.var_array
Dim keys(90,4) ; scancode,hited,released,oldhited
keys(1,1)=-1  ; bgs
keys(2,1)=-2  ; bds
keys(3,1)=16  ; A
keys(4,1)=17  ; Z
keys(5,1)=18  ; E
keys(6,1)=30  ; Q
keys(7,1)=31  ; S
keys(8,1)=32  ; D
keys(9,1)=15  ; Tab
keys(10,1)=42 ; shift (left)
keys(11,1)=56 ; alt
keys(12,1)=57 ; space
keys(13,1)=29 ; ctr (left)
keys(14,1)=28 ; return
keys(15,1)=2  ; &
keys(16,1)=3  ; é
keys(17,1)=4  ; "
keys(18,1)=5  ; '
keys(19,1)=6  ; (
keys(20,1)=7  ; -
keys(21,1)=8  ; è
keys(22,1)=9  ; _
keys(23,1)=10 ; ç
keys(24,1)=11 ; à
keys(25,1)=12 ; )
keys(26,1)=13 ; =
keys(27,1)=14 ; backspace
keys(28,1)=46 ; C
keys(29,1)=33 ; F
keys(30,1)=34 ; G
keys(31,1)=35 ; H
keys(32,1)=36 ; J
keys(33,1)=37 ; K
keys(34,1)=19 ; R
keys(35,1)=59 ; F1
keys(36,1)=60 ; F2
keys(37,1)=61 ; F3
keys(38,1)=62 ; F4
keys(39,1)=63 ; F5
keys(40,1)=64 ; F6
keys(41,1)=65 ; F7
keys(42,1)=49 ; N
keys(43,1)=88 ; F12
keys(44,1)=44 ; W

keys(47,1)=200 ; up
keys(48,1)=203 ; left
keys(49,1)=205 ; right
keys(50,1)=208 ; down

keys(51,1)=21 ; Y
keys(52,1)=22 ; U
keys(53,1)=23 ; I
keys(54,1)=24 ; O
keys(55,1)=25 ; P
keys(56,1)=26 ; ^
keys(57,1)=27 ; $
keys(58,1)=38 ; L
keys(59,1)=39 ; M
keys(60,1)=40 ; ù
keys(61,1)=43 ; *
keys(62,1)=86 ; <
keys(63,1)=45 ; X
keys(64,1)=47 ; V
keys(65,1)=48 ; B 
keys(66,1)=50 ; ,
keys(67,1)=51 ; ;
keys(68,1)=52 ; :
keys(69,1)=53 ; !
keys(70,1)=54 ; shift_droit
keys(71,1)=184 ; Alternate Graphics
keys(72,1)=83 ; .
keys(73,1)=82 ; 0
keys(74,1)=79 ; 1
keys(75,1)=80 ; 2
keys(76,1)=81 ; 3
keys(77,1)=75 ; 4
keys(78,1)=76 ; 5
keys(79,1)=77 ; 6
keys(80,1)=71 ; 7
keys(81,1)=72 ; 8
keys(82,1)=73 ; 9
keys(83,1)=181 ; /
keys(84,1)=55 ; *
keys(85,1)=74 ; -
keys(86,1)=78 ; +
keys(87,1)=156 ; Entrée num
keys(88,1)=41 ; ²
keys(89,1)=20 ; T
keys(90,1)=01 ; Esc

Dim engrenage_deco(90)
Dim PJ_big(3)
PJ_big(1)=LoadImage("sprites\combattants\visage_major.png")
PJ_big(2)=LoadImage("sprites\combattants\visage_leopold.png")
PJ_big(3)=LoadImage("sprites\combattants\visage_adeline.png")
Dim PJ_small(3)
PJ_small(1)=LoadImage("sprites\combattants\visage_major_small.png")
PJ_small(2)=LoadImage("sprites\combattants\visage_leopold_small.png")
PJ_small(3)=LoadImage("sprites\combattants\visage_adeline_small.png")

Dim type_sol(5,2) ; 1:entity_type, 2:son_pas
type_sol(1,1)=3
type_sol(2,1)=4
type_sol(3,1)=5
type_sol(4,1)=6
type_sol(5,1)=7
Dim options#(10) ;
Dim options_buffer#(10) ; pour ne pas changer les paramètres si on s'échappe
Dim sons_menu(15)
Dim sons_battle(50)

Dim nom_musique_de_fond$(20) ; nom_fichier$
Dim musique_de_fond(20,2) ; 1 musique chargée 2 volume_de_base (en %)

Dim gfx_onglets(2,NB_LANGUES) ; onglets dans le menu en jeu et dans l'interface de marchand.
Dim aide_contextuelle$(NB_LANGUES) ; aides contextuelles multilingues
Dim mult_mess$(NB_LANGUES) ; messages en multilangues (pour éviter les "Select Int(options#(7))" de partout
Dim oui$(NB_LANGUES) ; oui dans les différentes langues supportées
oui$(1)="Oui"
oui$(2)="Yes"
Dim non$(NB_LANGUES)
non$(1)="Non"
non$(2)="No"

Dim pos_entrance#(3)
Dim var_script_int(20)
Dim var_script_float#(20)
Dim var_script_str$(20)
Dim var_analyse_str$(40)
Dim var_analyse_int(40)
Dim var_analyse_float#(40)
Dim impliques(10)
Dim choix$(9) ; utilisé pour les fenetres qcm
Dim disc_ligne$(15) ; les 7 lignes de texte de discussion (et 15 pour les objets de quêtes)
Dim disc_ligne_bis$(15) ; les lignes réellement affichées dans les discussions (le défilement) 
Dim rules_description$(15)
Dim disc_faces(20,5) ; les visages que l'ont verra lors des discussions
Dim fighters_gfx(30,2) ; les images animées des combattants
Dim fighters_tete(30,2) ; les têtes (1 pour petit et 2 pour les grandes)
Dim fighters_utilises(18) ; les num des catégories de fighters qui seront utilisées
Dim initiative#(18,2)
Dim inactif_under_control(9)
Dim ligne_min(3)
Dim nb_ds_ligne(3)
Dim target_possible(3)
Dim pv_target(3)
Dim n_target(3)
Dim upgrade(3,2) ; num et coût d'une upgrade
Dim liste_tempo(LIMITE_QUEST*4)
Dim stat_cible$(3)

Dim armure_temp#(3)
Dim bonus_equi(11)

;Dim jet_att#(25,25,3) ; attaque, défense, normale/crit/parfait ([0;1])
;If FileType("jets.dat")<>1 Then RuntimeError "Le fichier ''jets.dat'' est manquant."
;file=ReadFile("jets.dat")
;For x=1 To 25
;	For y=1 To 25
;		For t=1 To 3
;			jet_att#(x,y,t)=ReadFloat#(file)	
;		Next
;	Next
;Next

Dim log_mess$(200,2) ; les 200 derniers messages du log et leur heure
Dim log_color(200,3) ; les couleurs du message du log

Dim str_PJ$(9); 0:Nom de celui qui parle, 1:Réponse N°1 .... 8:Réponse N°8

.var_type

Type map
	Field num
	Field stat[25]
End Type

Type bouton_du_menu
	Field num ; par habitude aussi
	Field action ; à quel niveau il apparait
	Field effet ; à quel niveau il envoit
	Field nom$[NB_LANGUES] ; le truc affiché
	Field desc$[NB_LANGUES] ; description en bas de l'écran
	Field x ; 
	Field y ; position du centre du bouton
	Field ina_couleur[3] ; rgb inactif
	Field act_couleur[3] ; rgb actif
End Type

Type bouton_option
	Field num ; par habitude aussi
	Field action ; à quel niveau il apparait
	Field effet ; à quel niveau il envoit
	Field clic2 ; si le bouton a un effet au clic droit
	Field nom$[NB_LANGUES] ; le truc affiché
	Field len_max ; la longueur du plus grand nom possible
	Field desc$[NB_LANGUES] ; description en bas de l'écran
	Field x ; 
	Field y ; position du centre du bouton
	Field ina_couleur[3] ; rgb inactif
	Field act_couleur[3] ; rgb actif
End Type

Type groupe
	Field num ; immatriculation (-1 si joueur)
	Field name$ ; le nom du "groupe"
	
	Field map ; la carte sur laquelle est actuellement le groupe
	Field position#[4] ; sa position sur la map
	
	Field pivot ; le pivot pour positionner le groupe
	Field manikin[3] ; les objets 3D	
	Field animation ; l'animation actuelle des manikins
	Field old_animation ; l'animation à la frame précedente des manikins
	Field not_md2 ; pour que les animations soient les bonnes, en fonction du type de manikin (0 pour md2, une autre valeur pour les autres) (cf fontion animation() dans functions.bb)
	
	Field activator ; pivot sur lequel on positionne l'activateur (le sprite de roue dentée qui tourne)
	Field act_type ; orientation de l'activateur (1 vers la caméra, 2 vers le 'haut', 3 mur vers la caméra)
	Field act_scale# ; la taille moyenne de l'activateur
	
	Field script[4] ; num script lancé en cas d'interaction
	Field trigger[4] ; type de déclenchement d'interaction (0 impossible : 1 perpétuel : 2 souris : 3 clic : 4 distance : 5 distance+A : 6 serveur)
	Field nom_action$[4*NB_LANGUES] ; Int(id_script+4*(options#(7)-1))
	Field range_trigger[4]; la portée du déclencheur
	Field detector ; le pivot pour tester les lignes de vue
	
	Field formation[9] ; les num_avatar selon l'ordre 1.2.3//4.5.6//7.8.9 où 1,4 et 7 sont les premiers rangs. 0 si vide
End Type

Type avatar
	Field num ; négatif si jouable
	Field prop ; num du player qui le joue (1 pour solo, 0 si IA)
	Field groupe ; num du groupe dont il fait parti
	Field name$[NB_LANGUES]
	Field classe$[NB_LANGUES]
	Field description$[NB_LANGUES]
	Field tactique$[NB_LANGUES]
	Field cat ; catégorie (sert pour savoir quel sprite on utilise pdt les combats)
	
	Field faiblesse#[3]
	Field att[3]
	Field def[3] ; attaque, défense et bonus de dégâts des armes légères, lourdes et à distance
	Field deg[3]
	Field pv[2] ; actuels/max
	Field init ; valeur d'initiative
	Field steam ; la vapeur consommée
	
	Field cmpt[15] ; regroupe les règles spéciales
	Field equi[8] ;4 = armure
	Field charge[3] ; les charges de ses trois armes
	Field caps
	Field junk
	Field set ; pour les méchants faits par random
	
	Field animation ; en 2D, pour les combats
	Field target
	Field defense
	Field activated
	Field last_action
End Type

Type player
	Field num
	Field id
	Field name$
	Field avatar
	Field drop ; nombre de butin déjà laché dans la nature
End Type

Type agresseur ; déclencheur de combat dans une zone quadrilatère
	Field num
	Field map
	Field name$ ; pour mieux s'y retrouver en commentant
	Field position#[3] ; position du grp aggresseur et référence pour les distances (pour ne pas calculer in_polygone tout le temps)
	Field radius#
	Field groupe ; le groupe associé
	;Field animation[3] ; idle, attaque, meurt
	Field actif ; 0 inactif 1 actif
	Field polyx#[4] ; les coordonnées x et z du sommet du polygône d'aggression
	Field polyz#[4]
End Type

Type patrouilleur ; déplaceur de groupe et d'agresseur
	Field num
	Field map
	Field name$ ; pour s'y retrouver en commentant
	Field agresseur ; l'agresseur associé
	Field en_cours ; pointeur des actions
	Field actif ; 0 inactif, 1 actif
	Field vivant ; 1 vivant, -1 mort
	Field actions[NB_ACTION_PAT] ; actions (1 déplacer, 2 attendre, 3 se tourner vers angle, 4 se tourner vers point fixe, 5 changer cone et range, 6 voler, 7 téléporter,
	;8 toggle agressivité, 9 Goto, 10 déclencher script sans modifier event_action, 11 déclencher un script à la première frame puis attendre en modifiant event_action
	;12 déclencher un script pdt une période en modifiant event_action )
	Field var1#[NB_ACTION_PAT]
	Field var2#[NB_ACTION_PAT] ; les variables des actions
	Field var3#[NB_ACTION_PAT]
	Field var4#[NB_ACTION_PAT]
	Field cone# ; angle d'ouverture du cone d'agression (du centre vers le bord)
	Field range# ; portée de l'agression
	Field sprite[3] ; marqueurs pour les tests
End Type

Type combat
	Field num ; immatriculation
	Field groupe[2] ; les groupes protagonistes
	Field phase
	Field tour ; le nombre de tour effectué
	Field ordre[19] ; l'ordre de jeu (avec un 0 pour séparer les actifs des en-attente)
	Field last_action ; date (millisecs) de la dernière action
	Field anim_time ; temps avant la prochaine action d'IA
	Field qui ; num avatar de celui qui doit jouer
	Field old_qui ; num du précédent
End Type

Type arme ; c_arme de DMa (mais comme les armes ne sont pas séparées comme pour DMa, pas de pb)
	Field num
	Field name$[NB_LANGUES]
	Field cat ; (épée, pistolet, ...)
	Field classe ; en cc/en tir pour les armes de tir (mains nues, légère, lourde, dist, dist inesquivable)
	Field charge ; Le nombre de fois qu'elle peut être utilisée (-1=infini)
	Field cadence ; nb de charge utilisées en rafale
	Field degats$ ; la formule donnée au joueur
	Field scr_degats ; numéro de la fonction dégâts()
	Field degat_min ; pour rendre inutile la variable scr_degats (scr_degats=num)
	Field degat_max ;
	Field rules[8] ; règles spéciales à l'arme (contient aussi les bonus de catégories)
	Field description$[NB_LANGUES]
	Field icone[2] ; en un la petite, en deux la grande
	Field junk ; en combien de junk elle se transforme
	Field caps ; sa valeur monétaire
	Field upgrade[3]
	Field up_cost[3]
	
	Field att[3]
	Field def[3] ; attaque, défense et bonus de dégâts des armes légères, lourdes et à distance
	Field deg[3]
	Field pv ; bonus de PV
	Field init ; bonus d'initiative
End Type

Type armure
	Field num
	Field name$[NB_LANGUES]
	Field description$[NB_LANGUES]
	Field val#[3]
	Field rules[3]
	Field icone[2] ; en un la petite, en deux la grande
	Field junk
	Field caps
	Field upgrade[3]
	Field up_cost[3]
	
	Field att[3]
	Field def[3] ; bonus d'attaque, de défense et de dégâts des armes légères, lourdes et à distance
	Field deg[3]
	Field pv ; bonus de PV
	Field init ; bonus d'initiative
End Type

Type boiler ; chaudière
	Field num
	Field name$[NB_LANGUES]
	Field description$[NB_LANGUES]
	Field capacite
	Field rules[3]
	Field icone[2] ; en un la petite, en deux la grande
	Field junk
	Field caps
	Field upgrade[3]
	Field up_cost[3]

	Field att[3]
	Field def[3] ; bonus d'attaque, de défense et de dégâts des armes légères, lourdes et à distance
	Field deg[3]
	Field pv ; bonus de PV
	Field init ; bonus d'initiative
End Type

Type special
	Field num
	Field name$[NB_LANGUES]
	Field description$[NB_LANGUES]
	Field rules[5]
	Field icone[2] ; en un la petite, en deux la grande
	Field junk
	Field caps
	Field upgrade[3]
	Field up_cost[3]
	
	Field att[3]
	Field def[3] ; bonus d'attaque, de défense et de dégâts des armes légères, lourdes et à distance
	Field deg[3]
	Field pv ; bonus de PV
	Field init ; bonus d'initiative
End Type

Type quest_item
	Field num
	Field name$[NB_LANGUES]
	Field description$[NB_LANGUES]
	Field shareable ; 0 si non (comme un objet), 1 si oui (comme une information), -1 Quête
	Field icone[2]
End Type

Type butin
	Field num
	Field prop ; 0 si libre, si <>0 alors on ne peut pas le selectionner
	Field pivot
	Field manikin
	Field kind
	Field map
	Field position#[4]
	Field loot[250] ; les num_types des objets du butin
	Field consommable[10] ; la quantité des 10 consommables
	Field quest[LIMITE_QUEST] ; jusqu'à LIMITE_QUEST objets/info de quête
	Field caps
	Field junk
	Field hidden ; pour pouvoir cacher son épée
End Type

Type rules ; description des règles spéciales
	Field num
	Field name$[NB_LANGUES]
	Field description$[NB_LANGUES]
End Type

Type smoke_source
	Field num
	Field pos#[3]
	Field orientation#[2]
	Field dispersion#[2]
	Field speed# ; en m/s
	Field freq ; en millisecs
	Field timer ; en millisecs
	Field lifespan_smoke ; en millisecs
	Field couleur[3]
	Field scale#
	Field scale_final#
	Field sprite_flag ; l'option lors du LoadSprite (color, alpha, masked, ...)
	Field cycle ; durée du cycle
	Field a# ; portion du cycle sur lequel la source émet de la fumée
	Field lifespan_source ; en cycle
	Field cycle_timer ; en millisecs
End Type

Type smoke
	Field pivot
	Field sprite
	Field speed#[3] ; en m/s
	Field scale#
	Field scale_final#
	Field lifespan ; en millisecs
	Field timer ; en millisecs
End Type

Type nb_dgts
	Field valeur$
	Field timer
	Field x
	Field y
	Field direction ; -1 vers la gauche, +1 vers la droite
End Type

Type chgt_equi
	Field image ; image de l'arme (0 si main nue)
	Field timer
	Field x
	Field y
	Field direction ; -1 vers la gauche, +1 vers la droite
End Type

Type porte
	Field num
	Field groupe ; le groupe proprio/déclencheur de la porte
	Field pivot
	Field manikin
	Field etat ;(0 fermé, 1 ouvert)
	Field pos_init#[6] ;(x,y,z,pitch,yaw,roll)
	Field pos_final#[6] ;
	Field pos_act#[6] ; pour éviter les pbs de pitch et roll
	Field speed#[6]
End Type


.includation
Include "function.bb"
Include "function_loran.bb"
Include "permanent.bb"
Include "armes.bb"
Include "ScriptXml.bb"
Include "script.bb"
Include "script_serveur.bb"
Include "bestaire.bb"
Include "regles.bb"
Include "combat.bb"
Include "groupe.bb"


;;lire les options.
If FileType("options.dat")=1
	file_option=ReadFile("options.dat")
	For t=1 To 10
		options#(t)=ReadFloat#(file_option)
	Next 
	CloseFile file_option
	
	Select options#(2) ; vérifier que les valeurs de framerate sont acceptées
	Case 2
		nb_frame=50
	Default
		nb_frame=30
		options#(2)=1
	End Select
	
	options#(1)=min(1,max(3,options#(1))) ; type de fond
	options#(4)=min(1,max(3,options#(4))) ; caméra
	
	options#(5)=minf#(1,maxf#(0,options#(5))) ; normaliser les volumes
	options#(6)=minf#(1,maxf#(0,options#(6)))
	
	options#(7)=Int(options#(7)) ; vérifier que la langue est gérée
	If Int(options#(7))<1 Or Int(options#(7))>NB_LANGUES
		first_language_choice()
	EndIf
Else
	action=0
	options#(1)=1 ; type de fond (noir ?)
	options#(2)=1 ; framerate (30 fps)
	options#(3)=2.5 ; vitesse de défilement de texte
	options#(4)=1 ; caméra (mode liée par défaut)
	options#(5)=1 ; volume musique (100%)
	options#(6)=1 ; volume sfx (100%)
	first_language_choice()
	export_options()
EndIf

Global frame_timer=CreateTimer(nb_frame)

.menu_principal
;load les trucs du menu
action=1
sortie=0
reinit_keyboard()

;A enlever
If mode_debug<>0
	action=12
	sortie=1
EndIf

mult_mess$(1)="Chargement du menu"
mult_mess$(2)="Loading"
mess$=mult_mess$(Int(options#(7)))
aff_loading(0,mess$)

If sortie=0
	If mode_debug=0
		For i=0 To 89
			If engrenage_deco(i)=0 Then engrenage_deco(i)=LoadImage("textures\loran\menu\Animation_rouage"+i+".jpg")
			MidHandle engrenage_deco(i)
			If KeyDown(keys(12,1))
				i=89
			EndIf
			If i=25
				aff_loading(1,mess$)
			ElseIf i=50
				aff_loading(2,mess$)
			ElseIf i=75
				aff_loading(3,mess$)
			EndIf
		Next
	EndIf
	cuir1=LoadImage("textures\loran\fond-cuir-noir.jpg")
	gfxTitle=LoadImage("sprites\menu\gfx_title.bmp")
	MidHandle gfxTitle
	ScaleImage gfxTitle,screeny#,screeny#
	big_font=LoadFont("Constantia",30*screeny#)
	little_font=LoadFont("Constantia",20*screeny#)
	middle_font=LoadFont("Constantia",25*screeny#)
	play_music(1)
EndIf

frame=0

While sortie=0
	start_loop("Head_Menu")
	
	old_action=action
	
	mouseclic1=MouseHit(01)
	mouseclic2=MouseHit(02)
	description$=""
	
	Color 150,150,150
	Rect 0,0,screenwidth,screenheight,0
	
	DrawImage cuir1,0,-5
	frame=reste(Int(timer_animation#*0.25),90)
	If engrenage_deco(frame)>0 Then DrawImage engrenage_deco(frame),31,GraphicsHeight()/2
	
	
	DrawImage gfxTitle,screenwidth*0.5,100*screeny#
	
	If MouseY()<150*screeny# Then description$="Promis, après celui-là on change de logiciel"
		
	If mouseclic2=1
		mouseclic2=0
		If action<10 
			action=1
		Else
			action=Floor(action*0.1)
		EndIf
	EndIf
	
	For b.bouton_du_menu = Each bouton_du_menu
		If b\action=action
			;calcul du rectangle
			temp=Int(Len(b\nom$[Int(options#(7))])*6*screeny#)
			drawgrey(b\x*screenx#-temp-1,(b\y-15)*screeny#-1,temp*2+1,30*screeny#+1,0.66,2)
			If MouseX()>b\x*screenx#-temp And MouseX()<b\x*screenx#+temp And MouseY()>(b\y-13)*screeny# And MouseY()<(b\y+13)*screeny#
				Color b\act_couleur[1],b\act_couleur[2],b\act_couleur[3]
				If mouseclic1 Then action=b\effet:mouseclic1=0
				description$=b\desc$[Int(options#(7))]
			Else
				Color b\ina_couleur[1],b\ina_couleur[2],b\ina_couleur[3]
			EndIf
			Rect b\x*screenx#-temp,(b\y-15)*screeny#,temp*2,30*screeny#,0
			SetFont big_font
			Text b\x*screenx#,b\y*screeny#,b\nom$[Int(options#(7))],1,1
		EndIf
	Next
	
	Color 200,200,200
	SetFont little_font
	;Text 5,screenheight-20*screeny#,description$
	Text 400,screenheight-20*screeny#,description$,1
		
	;actions spéciales
	Select action
		Case 0
			End
		Case 14 ; à propos
			SetFont big_font
			drawgrey(screenwidth*0.5-300*screeny#-1,250*screeny#-1,600*screeny#+1,400*screeny#+1,0.66,2)
			Color 150,150,150
			Rect screenwidth*0.5-300*screeny#,250*screeny#,600*screeny#,400*screeny#,0
			Text screenwidth*0.5,260*screeny#,"FINAL WHIM",1,0
			SetFont middle_font
			Text screenwidth*0.5,318*screeny#,"Une production BillyTeam Core",1,0
			Text screenwidth*0.5,354*screeny#,"Programmation : Luris",1,0
			Text screenwidth*0.5,372*screeny#,"Scénario, Design : Loran",1,0
			Text screenwidth*0.5,390*screeny#,"Musique : Kevin MacLeod (pour la plupart)",1,0
			Text screenwidth*0.5,512*screeny#,"Ce jeu est un freeware donc si vous avez",1,0
			Text screenwidth*0.5,530*screeny#,"payé pour ce jeu, vous vous êtes fait rouler.",1,0
			Text screenwidth*0.5,580*screeny#,"Version "+num_version$,1,0
			Text screenwidth*0.5,598*screeny#,date_version$,1,0
		Case 13
			PlaySound2(sons_menu(01))
			menu_option()
			action=1
		Case 111 ; facile
			DIFFICULTY=1
			action=-11
		Case 112 ; normal
			DIFFICULTY=2
			action=-11
		Case 113 ; difficile
			DIFFICULTY=3
			action=-11
		Case 161
			tutoriel(1)
			action=16
	End Select
	
	If action<1 Then action=-action:sortie=1
	
	If action<>old_action Then PlaySound2(sons_menu(01))
	
	DrawImage curseur,MouseX(),MouseY()
	
	;cheat
	;If action=0 Then End
	;Text 5,5,action
	
	Flip
	compensation_lag()
Wend


If sons_menu(4)<>0 Then PlaySound2(sons_menu(4))

Select action
	Case 0 ; quitter
		play_music(0)
		End
	Case 11 ; nouvelle partie
		play_music(3)
		nouvelle_partie=1
	Case 12 ; charger partie
		play_music(0)
		nouvelle_partie=0
	Case 15 ; bac à sable
		play_music(0)
		nouvelle_partie=2
End Select

aff_loading(0)

nombre_de_partie=nombre_de_partie+1

reinit_keyboard()
reinit_groupe()
;Include "groupe.bb"
Include "playersolo.bb"

clean_world()
clean_universe()
Goto menu_principal

End 