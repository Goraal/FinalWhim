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

Function sign(a#)
	If a#<0
		Return -1
	ElseIf a#=0
		Return 0
	Else
		Return 1
	EndIf
End Function

Function signed_str$(a,mode=0)
	If mode=0
		If a<0
			Return Str(a)
		Else
			Return "+"+Str(a)
		EndIf
	Else
		If a<0
			Return "- "+Str(-a)
		Else
			Return "+ "+Str(a)
		EndIf
	EndIf
End Function

Function signed_norm#(a#,n#)
	If Abs(a#)>Abs(n#)
		Return sign(a#)*Abs(n#)
	Else
		Return a#
	EndIf
End Function

;donne le reste de A divisé par B (q est dans [0;B-1])
Function reste(a,b)
	q=a Mod b
	While q<0
		q=q+b
	Wend
	While q>b
		q=q-b
	Wend
	Return q
End Function

;lance n Dface et somme
Function jetpur(face,n)
	For t=1 To n
		result=result+Rand(face)
	Next
	Return result
End Function

Function retournebit(code,bit)
	If bit>0
		Return Int(((code Mod 2^(bit+1))-(code Mod 2^(bit)))/2^bit)
	Else
		Return code Mod 2
	EndIf
End Function

;; renvoit le dice-ème chiffre du code en partant de la GAUCHE
Function retournedice(code$,dice=1)
	If dice>0 And dice-1<Len(code$)
		a_s$=Mid(code$,dice,1)
		If a_s$<>"!" And a_s$<>"§"
			Return  Int(a_s$)
		Else
			Return a_s$
		EndIf
	Else
		Return -1 ; erreur
	EndIf
End Function

; fonction qui rajoute des caractères devant si la chaine est trop courte (des " " par défault)
Function min_str$(as$,long=3,plus$=" ",reverse=0)
	While Len(as$)<long
		If reverse=0
			as$=plus$+as$
		Else
			as$=as$+plus$
		EndIf
	Wend
	Return as$
End Function

; écrit le texte mess$ à gauche du point x,y (center pour centrer sur y)
Function rText(x,y,mess$,center=0)
	Text x-StringWidth(mess$),y,mess$,0,center
End Function

;;fonctions de construction de monde
Function mur(dx#,dz#,x#,y#,z#,h#,rot#=0,tex$="",scaltext#=1,flag=0,rebord=1)
	If tex$<>"" Then texture=LoadTexture("textures\environnement\"+tex$,flag)
	If dx#>dz# Then bz#=dx# Else bz#=dz#
	If texture<>0 Then ScaleTexture texture,scaltext#/bz#,scaltext#/h#
	mur=CreateCube()
	ScaleEntity mur,dx#/2,h#/2,dz#/2
	PositionEntity mur,x#,y#+h#/2,z#
	RotateEntity mur,0,rot#,0
	If texture<>0 Then EntityTexture mur,texture
	EntityType mur,type_mur
	If texture<>0 Then FreeTexture texture
	Return mur
End Function

Function sol(dx#,dz#,x#,y#,z#,rot#=0,tex$="",scaltext#=1,flag=0,sol_type=1)
	sol=CreateCube()
	ScaleEntity sol,dx#/2,0.01,dz#/2
	PositionEntity sol,x#,y#-0.01,z#
	RotateEntity sol,0,rot#,0
	If tex$<>""
		texture=LoadTexture("textures\environnement\"+tex$,flag)
		ScaleTexture texture,scaltext#/dx#,scaltext#/dz#
		EntityTexture sol,texture
		FreeTexture texture
	EndIf
	EntityType sol,type_sol(sol_type,1)
	;EntityAlpha sol,0.5
	Return sol
End Function

Function lit(x#,y#,z#,pitch#=0,yaw#=0,roll#=0)
	pivot_lit=CreatePivot()
	ScaleEntity pivot_lit,1.2,1.3,1.2
	
	col=CreateCube(pivot_lit)
	EntityType col,type_sol(1,1)
	EntityAlpha col,0
	ScaleEntity col,1,0.2,0.5
	PositionEntity col,0,0.2,0
	
	PositionEntity pivot_lit,x#,y#,z#
	RotateEntity pivot_lit,pitch#,yaw#,roll#
	;tête du lit
	tete1=LoadSprite("textures\environnement\bedframe_tete.png",4,pivot_lit)
	SpriteViewMode tete1,2
	ScaleSprite tete1,0.5,0.39
	PositionEntity tete1,-1,0.39,0
	RotateEntity tete1,0,90,0
	tete2=CopyEntity(tete1,pivot_lit)
	RotateEntity tete2,0,-90,0
	;pied
	pied1=LoadSprite("textures\environnement\bedframe_pied.png",4,pivot_lit)
	SpriteViewMode pied1,2
	ScaleSprite pied1,0.5,0.28
	PositionEntity pied1,1,0.28,0
	RotateEntity pied1,0,90,0
	pied2=CopyEntity(pied1,pivot_lit)
	RotateEntity pied2,0,-90,0
	;sommier
	sommier=CreateCube(pivot_lit)
	EntityColor sommier,20,15,17
	ScaleEntity sommier,0.99,0.02,0.48
	PositionEntity sommier,0,0.20,0
	;matelas
	matelas=CreateCube(pivot_lit)
	ScaleEntity matelas,0.98,0.065,0.48
	tex=LoadTexture("textures\environnement\oreiller.jpg")
	EntityTexture matelas,tex
	PositionEntity matelas,-0.01,0.28,0
	;couette
	couette=CreateCube(pivot_lit)
	ScaleEntity couette,0.7,0.075,0.49
	tex1=LoadTexture("textures\environnement\velour_carré.png")
	EntityTexture couette,tex1
	PositionEntity couette,0.28,0.28,0
	;rebord
	rebord=CreateCube(pivot_lit)
	ScaleEntity rebord,0.20,0.08,0.50
	tex2=LoadTexture("textures\environnement\lit-rebord.png")
	EntityTexture rebord,tex2
	PositionEntity rebord,-0.4,0.28,0
	;oreiller
	oreiller=LoadMesh("objets\oreiller.x",pivot_lit)
	EntityTexture oreiller,tex
	ScaleEntity oreiller,0.4,0.2,0.35
	RotateEntity oreiller,0,0,-10
	PositionEntity oreiller,-0.75,0.365,0
	FreeTexture tex
	FreeTexture tex1
	FreeTexture tex2
End Function

Function dist2d#(mec1,mec2)
	a#=EntityX(mec1,1)-EntityX(mec2,1)
	b#=EntityZ(mec1,1)-EntityZ(mec2,1)
	Return Sqr(a#*a#+b#*b#)
End Function

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

;but : avoir un angle entre -180 et 180
;pk ne pas utiliser 'Mod' ? parce que ça ne marche pas comme je veux (c'est pas un vrai modulo)
;entrée : angle ; sortie : le même angle entre -180 et 180
;note : normalement, l'angle entré est entre -360 et 360 (les valeurs que renvoient les fonctions de Blitz)
;donc 'while' pourrait être remplacé par 'if' . Mais par sécurité, et comme ça consomme pas bcp plus...
Function mod360#(angle#)
	While angle#<-180
		angle#=angle#+360
	Wend
	While angle#>180
		angle#=angle#-360
	Wend
	Return angle#
End Function

Function pointerpitch#(yinter#,ytarget#,c#)
	If c#=0 Then Return 0
	d#=ytarget#-yinter#
	sina#=d#/c#
	Return ASin(sina#)
End Function

Function distance3d#(mec1,mec2)
	a#=EntityX#(mec1,1)-EntityX#(mec2,1)
	c#=EntityZ#(mec1,1)-EntityZ#(mec2,1)
	b#=EntityY#(mec1,1)-EntityY#(mec2,1)
	dist#=Sqr#(a#*a#+b#^2+c#*c#)
	Return dist#
End Function

;en utilisant le produit vectoriel [(a,b)x(c,d)=ad-bc], on voit de quel côté est un sommet par rapport à une droite passant par deux autres sommets
;qui se suivent et si la cible est ce de même coté. Si oui, on refait le test sur les autres coté du polygone.
;le polygones est déterminé par ses sommets et doit être régulier.
;Marche pour triangle, quadrilatère. on verra après si il faut plus (à priori non)
;xt et zt pour la cible, les autres pour les sommets.
;Indique si la cible est à l'intérieur strict du polygone (privé de ses frontières)
Function in_polygone(nb_cote,xt#,zt#,x1#,z1#,x2#,z2#,x3#,z3#,x4#=0,z4#=0)
	;1er coté
	xa#=x2#-x1#
	za#=z2#-z1#
	xb#=x3#-x1#
	zb#=z3#-z1#
	xc#=xt#-x1#
	zc#=zt#-z1#
	cote_sommet#=xa#*zb#-za#*xb#
	cote_target#=xa#*zc#-za#*xc#
	If sign(cote_target#)=sign(cote_sommet)
		xa#=x3#-x2#
		za#=z3#-z2#
		xb#=x1#-x2#
		zb#=z1#-z2#
		xc#=xt#-x2#
		zc#=zt#-z2#
		cote_sommet#=xa#*zb#-za#*xb#
		cote_target#=xa#*zc#-za#*xc#
		If sign(cote_target#)=sign(cote_sommet)
			If nb_cote=3 ; triangle
				xa#=x1#-x3#
				za#=z1#-z3#
				xb#=x2#-x3#
				zb#=z2#-z3#
				xc#=xt#-x3#
				zc#=zt#-z3#
				cote_sommet#=xa#*zb#-za#*xb#
				cote_target#=xa#*zc#-za#*xc#
				If sign(cote_target#)=sign(cote_sommet)
					Return True
				Else
					Return False
				EndIf
			Else
				xa#=x4#-x3#
				za#=z4#-z3#
				xb#=x2#-x3#
				zb#=z2#-z3#
				xc#=xt#-x3#
				zc#=zt#-z3#
				cote_sommet#=xa#*zb#-za#*xb#
				cote_target#=xa#*zc#-za#*xc#
				If sign(cote_target#)=sign(cote_sommet)
					xa#=x1#-x4#
					za#=z1#-z4#
					xb#=x2#-x4#
					zb#=z2#-z4#
					xc#=xt#-x4#
					zc#=zt#-z4#
					cote_sommet#=xa#*zb#-za#*xb#
					cote_target#=xa#*zc#-za#*xc#
					If sign(cote_target#)=sign(cote_sommet)
						Return True
					Else
						Return False
					EndIf
				Else
					Return False
				EndIf			
			EndIf
		Else
			Return False
		EndIf
	Else
		Return False
	EndIf
	Return False
End Function

;distance entre le segment [A,B] et le point X (x,y,z)
Function distance_segment_interne(ax#,ay#,az#,bx#,by#,bz#,x#,y#,z#)
	vx#=bx#-ax#
	vy#=by#-ay# ; V vecteur directeur du segment
	vz#=bz#-az#
	norme#=Sqr(vx#^2+vy#^2+vz#^2)
	vx#=vx#/norme#
	vy#=vy#/norme# ; on normalise V
	vz#=vz#/norme#
	scalaire#=vx#*(x#-ax#)+vy#*(y#-ay#)+vz#*(z#-az#) ; <V,AX>
	If scalaire#<0
		dist#=1500
	ElseIf scalaire#>norme#
		dist#=1500
	Else
		bx#=(x#-ax#)-scalaire#*vx#
		by#=(y#-ay#)-scalaire#*vy# ; AX-V<V,AX>
		bz#=(z#-az#)-scalaire#*vz#
		dist#=Sqr(bx#^2+by#^2+bz#^2)
	EndIf
	;new_messinteraction("#"+Str(dist#))
	Return dist#
End Function

;distance entre le segment [A,B] et le point X (x,y,z)
Function distance_segment(ax#,ay#,az#,bx#,by#,bz#,x#,y#,z#)
	vx#=bx#-ax#
	vy#=by#-ay# ; V vecteur directeur du segment
	vz#=bz#-az#
	norme#=Sqr(vx#^2+vy#^2+vz#^2)
	vx#=vx#/norme#
	vy#=vy#/norme# ; on normalise V
	vz#=vz#/norme#
	scalaire#=vx#*(x#-ax#)+vy#*(y#-ay#)+vz#*(z#-az#) ; <V,AX>
	If scalaire#<0
		dist#=Sqr((x#-ax#)^2+(y#-ay#)^2+(z#-az#)^2)
	ElseIf scalaire#>norme#
		dist#=Sqr((x#-bx#)^2+(y#-by#)^2+(z#-bz#)^2)
	Else
		bx#=(x#-ax#)-scalaire#*vx#
		by#=(y#-ay#)-scalaire#*vy# ; AX-V<V,AX>
		bz#=(z#-az#)-scalaire#*vz#
		dist#=Sqr(bx#^2+by#^2+bz#^2)
	EndIf
	;new_messinteraction("#"+Str(dist#))
	Return dist#
End Function

;renvoie la position du dernier espace rencontré dans mess$ depuis le long-ième caractère
Function lastspace(mess$,long)
For k=long To 1 Step -1
	If Mid$(mess$,k,1)=" " Then Return k
Next
End Function

;donne le nombre d'Images Par Seconde (fps), calculé sur 'frame_lim_fps' frames
Function fps#()
	frame_fps=frame_fps+1
	If frame_fps=frame_lim_fps
		frame_fps=0
		old_time_fps=time_fps
		time_fps=MilliSecs()
	EndIf
	Return 1000*(frame_lim_fps)/Float(time_fps-old_time_fps)
End Function

Function reinit_keyboard()
	For t=1 To 90
		KeyHit(keys(t,1))
		If keys(t,1)=-1 Then MouseHit(1)
		If keys(t,1)=-2 Then MouseHit(2)
		For k=2 To 4
			keys(t,k)=0
		Next
	Next
	FlushKeys
	FlushMouse
End Function

Function lire_clavier()
	For t=1 To 90
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
End Function

Function clear_liste_tempo()
	For t=1 To LIMITE_QUEST*3
		liste_tempo(t)=0
	Next
End Function

Function stack_liste_tempo()
	For t=1 To LIMITE_QUEST*3
		u=0
		action=1
		If liste_tempo(t)=0
			action=0
		Else
			action=1
		EndIf
		While action=0
			u=u+1
			If t+u<LIMITE_QUEST*3
				For k=t To LIMITE_QUEST*3
					liste_tempo(k)=liste_tempo(k+1)
				Next
				If liste_tempo(t)=0
					action=0
				Else
					action=1
				EndIf
			Else
				action=1
				t=LIMITE_QUEST*3+1
			EndIf
		Wend
	Next
End Function


Function aff_loading(frame=3,msg$="",num_astuce=0)
	Cls
	Color 255,255,255
	DrawImage loading,screenwidth*0.5-200,100,frame
	If middle_font<>0 Then SetFont middle_font
	Text screenwidth*0.5,533,msg$,1,1
	;num_astuce=14
	If num_astuce<>0
		If little_font<>0 Then SetFont little_font
		Select num_astuce
			Case 1
				mult_mess$(1)="N'hésitez pas à passer la souris sur un terme que vous ne comprenez pas,#il y aura sûrement une explication qui s'affichera en bas de l'écran."
				mult_mess$(2)="If you don't understand a term, try to move the mouse over it.#An explanation will likely appear at the bottom of the screen."
			Case 2
				;mult_mess$(1)="Méfiez vous des autres joueurs, ils m'ont dit des trucs pas bien sur vous ..."
				mult_mess$(1)="Appuyez sur 1, 2 ou 3 (du haut) pour changer de personnage principal lorsque vous vous déplacez."
				mult_mess$(2)="Hit 1, 2 or 3 (upper keyboard) to change your leading character."
			Case 3
				;mult_mess$(1)="Lorsque vous finirez le jeu, vous pourrez participer à la fête et il y aura du gâteau."
				mult_mess$(1)="Appuyez sur F1, F2, F3, F4, F5, F6 ou F7 pour accéder directement au menu souhaité."
				mult_mess$(2)="Press F1, F2, F3, F4, F5, F6 or F7 to go directly to the desired menu."
			Case 4
				mult_mess$(1)="Vous pouvez aussi utiliser C, E, F, K, J, M et H pour accéder à ces menus."
				mult_mess$(2)="You can also use C, I, F, K, L, M and H to access those menus."
			Case 5
				mult_mess$(1)="Les armes à feu sont puissantes, mais vous ne pouvez les utiliser qu'un certain nombre de fois par combat."
				mult_mess$(2)="Firearms are powerful, but can only be use a few number of times per battle."
			Case 6
				mult_mess$(1)="N'hésitez pas à vous cacher derrière vos alliés. Ils méritent les coups qu'ils vont prendre à votre place après tout ce qu'ils m'ont dit sur vous."
				mult_mess$(2)="Don't hesitate to hide behind your allies. They deserve the blows they're going to take for you after everything they've told me about you."
			Case 7
				mult_mess$(1)="KO ? Allez voir l'Infirmière, le serment d'Hippocrates l'oblige à vous sauver gratuitement.#Par contre il faudra payer pour être complètement soigné."
				mult_mess$(2)="KO? Go to the Nurse, the Hippocratic oath obliges her to save you for free. #On the other hand, you will have to pay to be completely healed."
			Case 8
				mult_mess$(1)="Un grand merci à nos alpha et beta-testeurs~! Que la bave du dieu limace-ours vous bénisse sur 2,64 générations"
				mult_mess$(2)="Many thanks to our alpha and beta-testers! May the drool of the Slug-Bear God bless you on 2,64 generations"
			Case 9
				mult_mess$(1)="Le saviez vous ? : Guy Williams faisait ses cascades lui-même mais ne savait pas jouer de la guitare !"
				mult_mess$(2)="Did you know? Guy Williams did his stunts himself, but didn't know how to play the guitar!"
			Case 10
				mult_mess$(1)="Si votre adversaire porte une lourde armure, essayez de viser ses points faibles. Vous ferez peut-être plus de dégâts."
				mult_mess$(2)="If your opponent is wearing a heavy armour, try to aim for its weak points. You may do more damages."
			Case 11
				mult_mess$(1)="N'oubliez pas qu'au premier tour d'un combat, seules les armes à distance peuvent attaquer."
				mult_mess$(2)="Don't forget that only ranged weapons can attack on the first round of a battle."
			Case 12
				mult_mess$(1)="Donner des cookies au programmeur ne vous fera pas gagner de niveau. Mais j'en veux bien quand même."
				mult_mess$(2)="Giving crips to the programmer will not get you a free level up. But I would still like some."
			Case 13
				mult_mess$(1)="Amenez vos amis en Azeroth, mais n’oubliez pas pour autant de --~ah, on me fait signe en régie que ce n'est pas le bon jeu~--"
				mult_mess$(2)="Bring your friends to Azeroth, but don't forget to ---~wait, I'm being told it is not the right game~---"
			Case 14
				mult_mess$(1)="Le saviez-vous ? : Quand il est tard (ou tôt, c'est selon), je craque et j'écris des astuces pour les temps de chargements~!"
				mult_mess$(2)="Did you know? When it's late (or so late it's early), I snap and write loading screen's tips!"
			Case 15
				mult_mess$(1)="Si jamais vous remontez le temps, faites attention à ne pas tuer vos ancêtres. On est pas encore clair sur ce qui se passerait."
				mult_mess$(2)="If you happen to go back in time, be careful not to kill your ancestors. We're not clear yet on the consequences."
			Case 16
				mult_mess$(1)="Si un ordinateur ne connait pas les Lois Robotiques, ignorez toute promesse de gâteau."
				mult_mess$(2)="If an IA doesn't know about Robotic Laws, ignore any promise of a cake."
			Case 17
				mult_mess$(1)="Proverbe : « Si il fait noir, c'est taquets fois deux »"
				mult_mess$(2)="Tip of the day: Don't use a (french) private joke as a loading tip. It may prove to be way too hard to translate..."
			Case 18
				mult_mess$(1)="« Je ne sais pas avec quelles armes sera faite la Troisième Guerre Mondiale, mais elles vont déchirer grave ! »#Albert Einstein quand il a bu."
				mult_mess$(2)="'' I don't know what weapons WW3 will be fought with, but they gonna rock hard! ''#Albert Einstein, when drunk."
			Default
				mult_mess$(1)="Vous pouvez vous aussi nous proposer des astuces ou autres à mettre sur les écrans de chargement~!"
				mult_mess$(2)="You can also offer us tips and tricks to put on the loading screens!"
		End Select
		
		;Text screenwidth*0.5,screenheight*0.5+266,mult_mess$(1),1,1
		
		For t=1 To 7
			disc_ligne(t)=""
		Next
		mess$=mult_mess$(Int(options#(7)))
		t=0
		k=0
		amax=Len(mess$)	
		max_ligne=Int(120)
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
		k=0
		For t=1 To 7
			disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
			if disc_ligne$(t)<>"" Then k=k+1
		Next
		For t=1 to k
			;a_min=0.5*20*k
			ai=-0.5*20*((k-1)-2*(t-1))
			Text screenwidth*0.5,screenheight*0.5+266+ai,disc_ligne(t),1,1
		Next
		
	EndIf
	Flip
End Function

Function aff_load_combat(frame#=1,couleur#=1)
	Cls
	Color 255*couleur#,255*couleur#,255*couleur#
	SetFont big_font
	Text screenwidth*0.5,screenheight*0.5,"FIGHT !",1,1
	If Int(options#(7)) = 1
		If frame#<1
			Text screenwidth*0.5,screenheight*0.5+30*screeny#,"Chargement ...",1,1
		Else
			Text screenwidth*0.5,screenheight*0.5+30*screeny#,"--> Appuyez sur Espace pour indiquer que vous êtes prêt <--",1,1
		EndIf
	Else
		If frame#<1
			Text screenwidth*0.5,screenheight*0.5+30*screeny#,"Loading ...",1,1
		Else
			Text screenwidth*0.5,screenheight*0.5+30*screeny#,"--> Press Space when you're ready <--",1,1
		EndIf
	Endif
	
	DrawImageRect image_load_combat,0,screenheight*(-frame#)*0.5,0,0,screenwidth,screenheight*0.5	
	DrawImageRect image_load_combat,0,screenheight*(frame#+1)*0.5,0,screenheight*0.5,screenwidth,screenheight*0.5	
	Flip
End Function

Function aff_load_menu(frame#=1,couleur#=1)
	Cls
	Color 255*couleur#,255*couleur#,255*couleur#
	SetFont big_font
	If Int(options#(7)) = 1
		Text screenwidth*0.5,screenheight*0.5,"Chargement du menu...",1,1
	Else
		Text screenwidth*0.5,screenheight*0.5,"Loading the menu...",1,1
	Endif
	Rect screenwidth*0.5-102,screenheight*0.5+20,205,20,0
	Rect screenwidth*0.5-100,screenheight*0.5+22,200*frame#,16,1

	Flip
End Function

Function HUD(actif=1)
	;Photo Leader
;	For av.avatar=Each avatar
;		If av\num=player_avatar(player_leader)
;			If fighters_tete(av\cat,2)<>0 Then DrawBlock fighters_tete(av\cat,2),655,455
;		EndIf
;	Next

	DrawBlock PJ_big(player_leader),655,455

	;Photo PJ1 et 2 + change
	Select player_leader
		Case 1
			pj1=2
			pj2=3
		Case 2
			pj1=1
			pj2=3
		Case 3
			pj1=1
			pj2=2
	End Select

	DrawBlock PJ_small(pj1),635,550
	If MouseX()<675 And MouseX()>635 And MouseY()>550 And MouseY()<590 And actif=1
		If Int(options#(7)) = 1
			message_action$="^Cliquez sur ce personnage pour"
		Else
			message_action$="^Click on this character to"
		Endif
		If keys(1,2)=50
			keys(1,2)=49
			changer_leader(pj1)
		EndIf
	EndIf

	DrawBlock PJ_small(pj2),750,550
	If MouseX()<790 And MouseX()>750 And MouseY()>550 And MouseY()<590 And actif=1
		If Int(options#(7)) = 1
			message_action$="^Cliquez sur ce personnage pour"
		Else
			message_action$="^Click on this character to"
		Endif
		If keys(1,2)=50
			keys(1,2)=49
			changer_leader(pj2)
		EndIf
	EndIf
	
	;hud
	DrawImage fond_hud,0,450

	;boussole
	Color 0,0,0
	a_int=50+3
	b_int=screenheight-50-16-3
	Oval a_int-5,b_int-5,10,10
	beta#=-EntityYaw#(cam,1)
	For t=1 To 4
		Line a_int+Cos(90*t)*5,b_int+Sin(90*t)*5,a_int-Sin(beta#)*35,b_int-Cos(beta#)*35
	Next
	SetFont small_font
	Text a_int+Sin(beta#)*10,b_int+Cos(beta#)*10,"N",1,1

	;menu
	;drawgrey(600,550,105,31)
	
	If actif=1
		a_int=500
		b_int=513
		If MouseX()<a_int+120 And MouseX()>a_int And MouseY()<b_int+30 And MouseY()>b_int
			If keys(1,2)=50 Then mode_de_jeu=3
			DrawImage gfx_Menu_on,a_int+60,b_int+15
			message_curseur$="Menu"
		EndIf
		
		a_int=500
		b_int=556
		If MouseX()<a_int+120 And MouseX()>a_int And MouseY()<b_int+30 And MouseY()>b_int
			If keys(1,2)=50 Then analyse(97)
			DrawImage gfx_Pause_on,a_int+60,b_int+15
			message_curseur$="Pause"
		EndIf
		
		;F1-F7
		If keys(35,2)=50 Then mode_de_jeu=3:onglet=1
		If keys(36,2)=50 Then mode_de_jeu=3:onglet=2
		If keys(37,2)=50 Then mode_de_jeu=3:onglet=3
		If keys(38,2)=50 Then mode_de_jeu=3:onglet=4
		If keys(39,2)=50 Then mode_de_jeu=3:onglet=5
		If keys(40,2)=50 Then mode_de_jeu=3:onglet=6
		If keys(41,2)=50 Then mode_de_jeu=3:onglet=7
		
		;Seconds raccourcis menu_player
		If keys(28,2)=50 Then mode_de_jeu=3:onglet=1 ; C pour fiche de perso
		If keys(05,2)=50 Then mode_de_jeu=3:onglet=2 ; E pour Equipement
		If keys(29,2)=50 Then mode_de_jeu=3:onglet=3 ; F pour Formation
		If keys(33,2)=50 Then mode_de_jeu=3:onglet=4 ; K? pour Quête
		If keys(32,2)=50 Then mode_de_jeu=3:onglet=5 ; J pour Journal
		If keys(59,2)=50 Then mode_de_jeu=3:onglet=6 ; M pour Menu Système
		If keys(31,2)=50 Then mode_de_jeu=3:onglet=7 ; H pour Aide
		
		;changer de perso
		If keys(15,2)=50 Then changer_leader(1)
		If keys(16,2)=50 Then changer_leader(2)
		If keys(17,2)=50 Then changer_leader(3)
	EndIf
		
		
		;cheat progra
		;mode_debug=1
		
		If mode_debug
			Color 255,255,255
			SetFont little_font
			;Text 5,15,"Grav : "+grav#
			Text 5,30,"X : "+EntityX(pl_grp_pivot)
			Text 5,45,"Y : "+EntityY(pl_grp_pivot)
			Text 5,60,"Z : "+EntityZ(pl_grp_pivot)
			Text 5,75,"Mode de jeu : "+mode_de_jeu
			Text 5,95,"Cmap : "+current_map
			Text 5,110,"Mousex : "+MouseX()
			Text 5,125,"Mousey : "+MouseY()
			If entiteTest<>0
				Text 5,150,"///////In Game Prog///////////"
				Text 5,165,"PAS :"+pas#
				Text 5,180,"Objet px: "+EntityX(entiteTest)+" py: "+EntityY(entiteTest)+" pz: "+EntityZ(entiteTest)
				Text 5,195,"Objet rx: "+EntityPitch(entiteTest)+" ry: "+EntityYaw(entiteTest)+" rz: "+EntityRoll(entiteTest)
			Endif
			For pat.patrouilleur=Each patrouilleur
				If pat\num=31501
					Text 5,210,"pat\actif : "+pat\actif
				EndIf
			Next
			
			For porte.porte=Each porte
				If porte\num=101
					Text 5,220,"Porte 101 : "+porte\etat
				Endif
			Next		
		EndIf

	
	If message_action$<>"" And actif=1
		SetFont big_font
		largeur=(max(Len(message_action$)*12+15,300))*screeny#
		hauteur=65*screeny#
		drawgrey2((screenwidth-largeur)*0.5-4,100*screeny#-4,largeur+8,hauteur+8)
		Color 250,250,250
	;	Rect (screenwidth-largeur)*0.5-2,100*screeny#-2,largeur+5,hauteur+5,0
		Select Left(message_action$,1)
			Case "^"
				If Int(options#(7)) = 1
					Text screenwidth*0.5,118*screeny#,"Cliquez sur ce personnage pour",1,1
					Text screenwidth*0.5,149*screeny#,"le choisir comme Leader",1,1
				Else
					Text screenwidth*0.5,118*screeny#,"Click on this character to",1,1
					Text screenwidth*0.5,149*screeny#,"choose him as Leader",1,1
				Endif
				
			Case "~"
				If Int(options#(7)) = 1
					Text screenwidth*0.5,118*screeny#,"Utilisez Z,Q,S,D",1,1
					Text screenwidth*0.5,149*screeny#,"pour vous déplacer",1,1
				Else
					Text screenwidth*0.5,118*screeny#,"Use Z,Q,S,D",1,1
					Text screenwidth*0.5,149*screeny#,"to move yourself",1,1
				Endif

			Case "}" ; roue
				If Int(options#(7)) = 1
					Text screenwidth*0.5,118*screeny#,"Nombre de tours : "+Right(Left(message_action$,5),4),1,1
					Text screenwidth*0.5,149*screeny#,"Appuyez sur Espace pour quitter la roue",1,1
				Else
					Text screenwidth*0.5,118*screeny#,"Number of wheel turn : "+Right(Left(message_action$,5),4),1,1
					Text screenwidth*0.5,149*screeny#,"Hit space to leave the wheel",1,1
				Endif

			Default 
				If Int(options#(7)) = 1
					Text screenwidth*0.5,118*screeny#,"Appuyez sur Espace pour",1,1
					Text screenwidth*0.5,149*screeny#,message_action$,1,1
				Else
					Text screenwidth*0.5,118*screeny#,"Hit space to",1,1
					Text screenwidth*0.5,149*screeny#,message_action$,1,1
				Endif
		End Select
	EndIf
	
	
	;If event_action=0 Or mode_debug=1
		SetFont little_font
		For t=1 To 5
			If log_color(t,1)>200 And log_color(t,2)>200 And log_color(t,3)>200
				log_color(t,1)=max(255-log_color(t,1),30)
				log_color(t,2)=max(255-log_color(t,2),30)
				log_color(t,3)=max(255-log_color(t,3),30)
			EndIf
			Color log_color(t,1),log_color(t,2),log_color(t,3)
			If log_mess$(t,2)<>""
				;If log_mess$(t,2)<>"#" Then Text 105,screenheight-t*18*screeny-18,"["+log_mess$(t,2)+"]"
				;Text 105+80*screeny,screenheight-t*18*screeny-18,log_mess$(t,1)
				Text 118,screenheight-t*18*screeny-14,log_mess$(t,1)
			EndIf
		Next
	;EndIf
	
	;chat	
	If chat_mode=1
		Color 0,0,0
		Rect 2,screenheight-121,406,18+6
		Color 255,255,255
		Rect 3,screenheight-120,404,18+4,0
	
		msg_radio$=input_text(msg_radio$)
		If Floor(timer_animation#*0.05) Mod 2 = 0
			char$="#"
		Else
			char$="_"
		EndIf
		Text 5,screenheight-118,"["+CurrentTime()+"]  |  "+msg_radio$+char$
		;Text 104,screenheight-118,msg_radio$+char$
				
		If keys(14,2)=50 Or keys(87,2)=50
			If msg_radio$=""
				chat_mode=0
			ElseIf Len(msg_radio$)<4
				If Int(options#(7)) = 1
					new_log("Vous : "+msg_radio$,50,50,255)
				Else
					new_log("You : "+msg_radio$,50,50,255)
				Endif
				msg_radio$=""
				chat_mode=0
			Else
				If msg_radio$="/debug" ; toggle l'affichage des données dev
					mode_debug=1-mode_debug
					msg_radio$=""
					chat_mode=0
				Else
					If Int(options#(7)) = 1
						new_log("Vous : "+msg_radio$,50,50,255)
					Else
						new_log("You : "+msg_radio$,50,50,255)
					Endif
					;SendNetMsg(99,msg_radio$,player_id)
					msg_radio$=""
					chat_mode=0
				EndIf
			EndIf	
		EndIf
	Else
		If (keys(14,2)=50 Or keys(82,2)=50) And event_action=0 Then chat_mode=1
	EndIf
	If actif=0
		chat_mode=0
		drawmouse=0
	EndIf
	
	;curseur
	If drawmouse=1 Then DrawImage curseur,MouseX(),MouseY()
	
	If message_curseur$<>old_message_curseur$ ; And ChannelPlaying(ch_clic)=0
		ch_clic=PlaySound(sons_menu(14))
		ChannelVolume ch_clic,0.1*options#(6)
	EndIf
	
;	If message_curseur<>""
;		Color 255,255,255
;		SetFont little_font
;		temp=(7.5*Len(message_curseur$)+10)*screeny#
;		drawgrey2(MouseX()+30,MouseY()+30,temp,21)
;		Rect MouseX()+32,MouseY()+32,temp-3,18,0
;		Text MouseX()+30+temp*0.5,MouseY()+30+14*screeny#,message_curseur$,1,1
;	EndIf
	
	;color 255,0,255
	;rect 399,249,3,3,0
End Function

Function selection_avatar(perso,place=4,forcer=0,classe_depart$="")
	If forcer=0
		FlushKeys
		FlushMouse
	
		;load les gfx
		aff_load_menu(0)
	
		;;load les images du menu
			; les onglets et le curseur
		If gfx_cuir=0 Then gfx_cuir=LoadImage("textures\loran\fond-cuir-noir.jpg")
		If fond_book=0 
			fond_book=LoadImage("sprites\menu\fond_book.jpg")
		EndIf
		fond_bronze=LoadImage("sprites\menu\fond_bronze.jpg")
		If gfx_signet=0 Then gfx_signet=LoadImage("sprites\menu\marque-page.bmp")
		If gfx_signet_sombre=0 Then gfx_signet_sombre=LoadImage("sprites\menu\marque-page_sombre.bmp")
		
		aff_load_menu(0.5)
		Select perso
			Case 1 ; Major
				photo_visage=loadimage("sprites\combattants\visage_major.png")
			Case 2 ; Leopold (Adam)
				photo_visage=loadimage("sprites\combattants\visage_leopold.png")
			Default ; Adeline (Eve)
				photo_visage=loadimage("sprites\combattants\visage_adeline.png")
		End Select
		
		For av.avatar=Each avatar
			If av\num=perso
				perso_name$=av\name$[Int(options#(7))]
				perso_description$=av\description$[Int(options#(7))]
			EndIf
		Next

		aff_load_menu(1)
		
		If classe_depart$=""
			cible=1
		Else
			cible=1
			For av.avatar=Each avatar
				If av\classe$[Int(options#(7))]=classe_depart$
					cible=-av\num-10
				EndIf
			Next
		EndIf
		
		a_pos=800/Float(NB_CLASSE_DISPO+1)
		choisi=0
		
		tetra=0
		While tetra=0
			good=0
			While good=0
				Cls
				start_loop()
				lire_clavier()
				
				aide_contextuelle$(1)=""
				aide_contextuelle$(2)=""
				
				DrawImageRect gfx_cuir,0,0,0,0,800,600
				DrawImageRect fond_book,25,220,0,0,750,355
				;DrawImageRect fond_book,25,80,0,0,750,495
				
				; onglets têtes
				For t=1 To NB_CLASSE_DISPO
					If t=cible
						DrawImage gfx_signet,a_pos*(t)-25,145
					Else
						DrawImage gfx_signet_sombre,a_pos*(t)-25,145
					EndIf
					If MouseY()<215 And MouseY()>140
						If MouseX()>a_pos*(t)-30 And MouseX()<a_pos*(t)+30
							For av.avatar=Each avatar
								If av\num=-10-t
									aide_contextuelle$(Int(options#(7)))=av\classe$[Int(options#(7))]
								EndIf
							Next
							If keys(1,2)=50
								keys(1,2)=49
								If cible<>t Then Playsound2(sons_menu(6))
								cible=t
							EndIf										
						EndIf
					EndIf					
					DrawImage classe_tete,a_pos*(t)-20,158,t-1					
				Next
		
				Color 0,0,0
				For av.avatar=Each avatar
					If av\num=-10-cible
						DrawImageRect fond_bronze,40,15,0,0,720,120						
						;afficher photo
						DrawBlock photo_visage,45,15
						SetFont middle_font
						If Int(options#(7)) = 1
							Text 180,20,"Nom : "+perso_name$
						Else
							Text 180,20,"Name : "+perso_name$
						Endif
						;description du perso
						For t=1 To 7
							disc_ligne(t)=""
						Next
						MiseEnFormeMessageDialogue(" "+perso_description$,0,570)
						For t=1 To 4
							disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
							Text 180,20*t+25,disc_ligne$(t)
						Next
						;drawcadre(37,7,726,136,1)
						drawcadre(37,7,136,136,1)
						Select Int(options#(7))
							Case 1
								MiseEnFormeMessageDialogue(" Classe : "+av\classe$[1]+"  -  "+av\description$[1],0,700)
							Case 2
								MiseEnFormeMessageDialogue(" Class: "+av\classe$[2]+"  -  "+av\description$[2],0,700)
						End Select
						For t=1 To 4
							disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
							Text 50,(224+20*t),disc_ligne$(t)
						Next
						
						ai=70
						If Int(options#(7)) = 1
							Text ai,354,"PV",0,1
						Else
							Text ai,354,"HP",0,1
						EndIf
						rText(ai+80,354,av\pv[2],1)
						If MouseX()>50 And MouseX()<150 And MouseY()>(345) And MouseY()<(367)
							aide_contextuelle$(1)="Points de Vie : Lorsque les points de vie arrivent à 0, le personnage est inconscient."
							aide_contextuelle$(2)="Hit Points: When hit points reach 0, the character is unconscious."
						EndIf
						Text ai,378,"Init",0,1
						rText(ai+80,378,av\init,1)
						If MouseX()>ai And MouseX()<ai+100 And MouseY()>(369) And MouseY()<(387)
							aide_contextuelle$(1)="Initiative : La vitesse de réaction et de reflexion du personnage."
							aide_contextuelle$(2)="Initiative : The speed of reaction and reflection of the character."
						EndIf
						;carac (Att, Def, Dgts)
						ai=250
						If Int(options#(7)) = 1
							Text ai,330,"Armes légères"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dgts"
						Else
							Text ai,330,"Light weapon"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dmg"
						EndIf
						
						If MouseX() > ai and MouseX() < ai+150
							If MouseY() > 330 and MouseY() < 348
								aide_contextuelle$(1)="Les armes de corps à corps légères (couteau, épée, lance, ...)"
								aide_contextuelle$(2)="Light weapons(knife, sword, spear, ...)"
							ElseIf MouseY() > 348 and MouseY() < 366
								aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes légères"
								aide_contextuelle$(2)="The character's ability to attack with light weapons"
							ElseIf MouseY() > 366 and MouseY() < 384
								aide_contextuelle$(1)="La défense du personnage contre les armes légères"
								aide_contextuelle$(2)="The character's defense against small arms and light weapons"
							ElseIf MouseY() > 384 and MouseY() < 402
								aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes légères"
								aide_contextuelle$(2)="The bonus to character damage with small arms"
							EndIf									
						EndIf
						rText(ai+StringWidth("Armes légères"),348,Str(av\att[1]))
						rText(ai+StringWidth("Armes légères"),366,Str(av\def[1]))
						rText(ai+StringWidth("Armes légères"),384,Str(av\deg[1]))
						
						ai=430
						If Int(options#(7)) = 1
							Text ai,330,"Armes lourdes"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dgts"
						Else
							Text ai,330,"Heavy weapon"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dmg"
						EndIf
						If MouseX() > ai and MouseX() < ai+150
							If MouseY() > 270 and MouseY() < 348
								aide_contextuelle$(1)="Les armes de corps à corps lourdes (hache, marteau, tronçonneuse, ...)"
								aide_contextuelle$(2)="Heavy weapons (axes, hammers, chainsaws,...)"
							ElseIf MouseY() > 348 and MouseY() < 366
								aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes lourdes"
								aide_contextuelle$(2)="The character's ability to attack with heavy weapons"
							ElseIf MouseY() > 366 and MouseY() < 384
								aide_contextuelle$(1)="La défense du personnage contre les armes lourdes"
								aide_contextuelle$(2)="The character's defense against heavy weapons"
							ElseIf MouseY() > 384 and MouseY() < 402
								aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes lourdes"
								aide_contextuelle$(2)="The bonus to character damage with heavy weapons"
							EndIf									
						EndIf
						rText(ai+StringWidth("Armes lourdes"),348,Str(av\att[2]))
						rText(ai+StringWidth("Armes lourdes"),366,Str(av\def[2]))
						rText(ai+StringWidth("Armes lourdes"),384,Str(av\deg[2]))
						
						ai=610
						If Int(options#(7)) = 1
							Text ai,330,"Armes à distance"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dgts"
						Else
							Text ai,330,"Ranged weapon"
							Text ai,348,"Att"
							Text ai,366,"Def"
							Text ai,384,"Dmg"
						EndIf
						
						If MouseX() > ai and MouseX() < ai+150
							If MouseY() > 330 and MouseY() < 348
								aide_contextuelle$(1)="Les armes à distance (pistolet, fusil, lance-piere, ...)"
								aide_contextuelle$(2)="Ranged weapons(gun, rifle, pistol, gun,...)"
							ElseIf MouseY() > 348 and MouseY() < 366
								aide_contextuelle$(1)="L'aptitude du personnage à attaquer avec les armes à distance"
								aide_contextuelle$(2)=""
							ElseIf MouseY() > 366 and MouseY() < 384
								aide_contextuelle$(1)="La défense du personnage contre les armes à distance"
								aide_contextuelle$(2)=""
							ElseIf MouseY() > 384 and MouseY() < 402
								aide_contextuelle$(1)="Le bonus aux dégâts du personnage avec les armes à distance"
								aide_contextuelle$(2)=""
							EndIf									
						EndIf
						rText(ai+StringWidth("Armes à distance"),348,Str(av\att[3]))
						rText(ai+StringWidth("Armes à distance"),366,Str(av\def[3]))
						rText(ai+StringWidth("Armes à distance"),384,Str(av\deg[3]))						
						
						;règles
						;SetFont middle_font
						ai=50;180
						ci=180;400
						bi=410;250
						If Int(options#(7)) = 1
							Text ai,bi,"Règles spéciales :"
						Else
							Text ai,bi,"Special ability :"
						EndIf
						For t=1 To 8
							If av\cmpt[t]<>0
								For r.rules=Each rules
									If r\num=av\cmpt[t]
										Text ai,bi+20*t,r\name$[Int(options#(7))]
										If MouseX()>ai And MouseX()<ci And MouseY()>bi+20*t And MouseY()<bi+20*(t+1) Then aide_contextuelle$(Int(options#(7)))=r\description$[Int(options#(7))]
									EndIf
								Next
							EndIf
						Next
						
						;séparation
						Line 212,423,212,498
						
						;tactiques
						ai=230
						If MouseX()>ai And MouseX()<750 And MouseY()>410 And MouseY()<510
							aide_contextuelle$(1)="Conseils pour utiliser ce personnage. Il s'agit bien entendu uniquement de conseils et ne sauraient engager la responsabilité des créateurs."
							aide_contextuelle$(2)="Tips for using this character. This is of course only advice and does not engage the responsibility of the creators."
						EndIf
						MiseEnFormeMessageDialogue(" "+av\tactique$[Int(options#(7))],0,750-ai)
						
						For t=1 To 5
							disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
							Text ai,(390+20*t),disc_ligne$(t)
						Next
		
						;bouton de selection
						Color 0,0,0
						If MouseX()>250 And MouseX()<550 And MouseY()>520 And MouseY()<545
							aide_contextuelle$(1)="Hmmm... Non, je n'aurais pas pris celle là. Mais bon, tout le monde ne peut pas être un génie."
							aide_contextuelle$(2)="Hmmm... No, I wouldn't have taken that one. But well, not everyone can be a genius."
							Color 180,0,0
							If keys(1,2)=50
								keys(1,2)=49
								For av2.avatar=Each avatar
									If av2\num=perso
										av2\prop=1
										av2\groupe=-1
										name$=av2\name$[Int(options#(7))]
										For t=1 To NB_LANGUES
											av2\classe$[t]=av\classe$[t]
											av2\tactique$[t]=av\tactique$[t]
										Next
										For t=1 to 3
											av2\att[t]=av\att[t]
											av2\def[t]=av\def[t]
											av2\deg[t]=av\deg[t]
										Next
										For t=1 to 3
											av2\faiblesse#[t]=av\faiblesse#[t]
										Next
										For t=1 To 15
											av2\cmpt[t]=av\cmpt[t]
										Next
										av2\pv[2]=av\pv[2]
										av2\pv[1]=av2\pv[2]
										av2\init=av\init
										good=1
									EndIf
								Next	
							EndIf
						EndIf
						Rect 250,520,300,25,0
						mult_mess$(1)="Choisir cette classe"
						mult_mess$(2)="Choose this class"
						Text 400,532,mult_mess$(int(options#(7))),1,1
						
					EndIf
				Next
				
				If good=1
					Flip
					SaveBuffer(FrontBuffer(),"temp.bmp")
					image_temp=LoadImage("temp.bmp")
				EndIf
				
				Color 255,255,255
				SetFont little_font
				Text 20,587,aide_contextuelle$(Int(options#(7))),0,1
				DrawImage curseur,MouseX(),MouseY()
				
				Flip
			Wend
			
			;renommer
			action=0
			limite_caractere=20
			While action=0
				Cls
				start_loop()
				lire_clavier()

				DrawImage image_temp,0,0
				;af#=(Cos(timer_animation#*10)+2)*0.33
				;Color 255*af#,255*af#,255*af#
				SetFont big_font
				
				drawgrey2(400-(110+5),150-30,2*(110+5),30+70)
				
				mult_mess$(1)="Nom du personnage : "
				mult_mess$(2)="Character's name: "
				Text screenwidth*0.5,screenheight*0.25,mult_mess$(Int(options#(7))),1,1
				name$=input_text(name$)
				If Len(name$)>limite_caractere
					name$=Left(name$,limite_caractere)
					Playsound2(sons_menu(2))
				Endif
				
				If Floor(timer_animation#*0.05) Mod 2 = 0
					char$="#"
				Else
					char$="_"
				EndIf
				
				If Len(name$)>limite_caractere-1 then char$="|"
		
				Text screenwidth*0.5-100,screenheight*0.25+30,"> "+name$+char$

				;rect retour
				drawgrey2(285,250,100,50)
				mult_mess$(1)="Retour"
				mult_mess$(2)="Cancel"
				Text 335,275,mult_mess$(Int(options#(7))),1,1
				If MouseX()>285 And MouseX()<385 And MouseY()>250 And MouseY()<300 And keys(1,2)=50 And name$<>""
					action=1
					tetra=0
				EndIf
				
				;rect confirmer
				drawgrey2(415,250,100,50)
				mult_mess$(1)="Confirmer"
				mult_mess$(2)="Confirm"
				Text 465,275,mult_mess$(Int(options#(7))),1,1
				If MouseX()>415 And MouseX()<515 And MouseY()>250 And MouseY()<300 And keys(1,2)=50 And name$<>""
					action=1
					tetra=1
				EndIf
				If keys(14,2)=50 And name$<>"" Then action=1:tetra=1
				
				DrawImage curseur,MouseX(),MouseY()
				Flip
				compensation_lag()
			Wend
		Wend
		freeimage image_temp
		freeimage photo_visage
	Else
		For av.avatar=Each avatar
			If av\num=-forcer-10
				For av2.avatar=Each avatar
					If av2\num=perso
						av2\prop=1
						av2\groupe=-1
						name$=av2\name$[Int(options#(7))]
						For t=1 To NB_LANGUES
							av2\classe$[1]=av\classe$[1]
							av2\tactique$[1]=av\tactique$[1]
						Next
						For t=1 to 3
							av2\att[t]=av\att[t]
							av2\def[t]=av\def[t]
							av2\deg[t]=av\deg[t]
						Next
						For t=1 to 3
							av2\faiblesse#[t]=av\faiblesse#[t]
						next
						for t=1 to 15
							av2\cmpt[t]=av\cmpt[t]
						next
						av2\pv[2]=av\pv[2]
						av2\pv[1]=av2\pv[2]
						av2\init=av\init
					EndIf
				Next
			EndIf
		Next
	EndIf
	
	;affection le renommage et placement dans le groupe
	For av.avatar=Each avatar
		If av\num=perso
			av\name$[1]=name$
			av\name$[2]=name$
			DebugLog "N:"+name$+"//"+perso
			Select perso
				Case 1
					ChangeAvancement("Avancement/NomMajor:"+name$)
				Case 2
					ChangeAvancement("Avancement/NomLeopold:"+name$)
				Case 3
					ChangeAvancement("Avancement/NomAdeline:"+name$)
			End Select
		EndIf
	Next
	For gr.groupe=Each groupe
		If gr\num=-1
			good=0
			tout_teste=place
			While good=0
				If gr\formation[place]=0	
					good=1
					gr\formation[place]=perso
				Else
					place=place+1
					If place>9 Then place=1
					If place=tout_teste Then RuntimeError "Pour une raison étrange, votre groupe est déjà plein et ne peut donc pas acceuillir un personnage supplémentaire. Cependant, ça ne devrait pas être possible donc il y a un problème."
				EndIf
			Wend
		EndIf
	Next	
	Return avatar_selected
End Function

Function rechoisir_avatar()
	;charger fond et asset
	FlushKeys
	FlushMouse
	If gfx_cuir=0 Then gfx_cuir=LoadImage("textures\loran\fond-cuir-noir.jpg")
	photo_visage_m=loadimage("sprites\combattants\visage_major.png")
	photo_visage_l=loadimage("sprites\combattants\visage_leopold.png")
	photo_visage_a=loadimage("sprites\combattants\visage_adeline.png")
	;boucle
	action=0
	While action=0
		Cls
		start_loop()
		lire_clavier()
		for t=1 to NB_LANGUES
			aide_contextuelle$(t)=""
		next
		
		DrawImageRect gfx_cuir,0,0,0,0,800,600
		
		SetFont middle_font
		;afficher/cliquer des perso
		DrawBlock photo_visage_m,170-60,280-60
		drawcadre(102,212,136,136,1)
		For av.avatar=Each avatar
			If av\num=1
				nom$=av\name$[Int(options#(7))]
				classe$=av\classe$[Int(options#(7))]
			EndIf
		Next
		Text 170,360,nom$,1,1
		Text 170,380,classe$,1,1
		If MouseX()>110 And MouseX()<230 And MouseY()>220 And MouseY()<340
			aide_contextuelle$(1)="Modifier le nom et/ou la classe de "+nom$+"."
			aide_contextuelle$(2)="Change "+nom$+"'s name of class."
			If keys(1,2)=50
				keys(1,2)=49
				selection_avatar(1,4,0,classe$)
				keys(1,2)=49
			EndIf
		EndIf
		
		DrawBlock photo_visage_a,400-60,280-60
		drawcadre(332,212,136,136,1)
		For av.avatar=Each avatar
			If av\num=3
				nom$=av\name$[Int(options#(7))]
				classe$=av\classe$[Int(options#(7))]
			EndIf
		Next
		Text 400,360,nom$,1,1
		Text 400,380,classe$,1,1
		If MouseX()>340 And MouseX()<460 And MouseY()>220 And MouseY()<340
			aide_contextuelle$(1)="Modifier le nom et/ou la classe de "+nom$+"."
			aide_contextuelle$(2)="Change "+nom$+"'s name of class."
			If keys(1,2)=50
				keys(1,2)=49
				selection_avatar(3,7,0,classe$)
				keys(1,2)=49
			EndIf
		EndIf
		
		DrawBlock photo_visage_l,630-60,280-60
		drawcadre(562,212,136,136,1)
		For av.avatar=Each avatar
			If av\num=2
				nom$=av\name$[Int(options#(7))]
				classe$=av\classe$[Int(options#(7))]
			EndIf
		Next
		Text 630,360,nom$,1,1
		Text 630,380,classe$,1,1
		If MouseX()>570 And MouseX()<690 And MouseY()>220 And MouseY()<340
			aide_contextuelle$(1)="Modifier le nom et/ou la classe de "+nom$+"."
			aide_contextuelle$(2)="Change "+nom$+"'s name of class."
			If keys(1,2)=50
				keys(1,2)=49
				selection_avatar(2,1,0,classe$)
				keys(1,2)=49
			EndIf
		EndIf
		
		;afficher les instructions
		Color 255,255,255
		SetFont big_font
		If Int(options#(7))=1
			Text 400,70,"Voici votre équipe !",1,1
		Else
			Text 400,70,"This is your team !",1,1
		Endif
		SetFont middle_font
		mult_mess$(1)="Cliquer sur le portrait d'un personnage pour changer sa classe"
		mult_mess$(1)="Click on a character's portrait to change their class"
		Text 400,120,mult_mess$(Int(options#(7))),1,1
		mult_mess$(1)="ou sur ''Confirmer'' pour valider votre choix et commencer l'aventure."
		mult_mess$(1)="or ''Confirm'' your choices and start your adventure"
		Text 400,140,mult_mess$(Int(options#(7))),1,1
		
		;afficher confirmer
		drawgrey2(300,465,200,50)
		SetFont big_font
		mult_mess$(1)="Confirmer"
		mult_mess$(2)="Confirm"
		Text 400,490,mult_mess$(Int(options#(7))),1,1
		If MouseX()>300 And MouseX()<500 And MouseY()>465 And MouseY()<515
			aide_contextuelle$(1)="Confirmer la composition de votre équipe et partir à l'aventure !"
			aide_contextuelle$(2)="Confirm the composition of your team and start your adventure!"
			If keys(1,2)=50 Then action=1
		EndIf
		
		Color 255,255,255
		SetFont little_font
		Text 400,587,aide_contextuelle$(Int(options#(7))),1,1
		DrawImage curseur,MouseX(),MouseY()		
		Flip
	Wend
	FreeImage photo_visage_m
	FreeImage photo_visage_l
	FreeImage photo_visage_a
End Function

Function new_dgts(valeur,x,y,direction)
	nb.nb_dgts = New nb_dgts
	If valeur>19999
		nb\valeur$=Str(valeur-20000)+"*"
	ElseIf valeur>9999
		nb\valeur$=Str(valeur-10000)+"!"
	Else
		nb\valeur$=Str(valeur)
	EndIf
	nb\x=x
	nb\y=y
	nb\direction=direction	
End Function

Function update_dgts()
	For nb.nb_dgts=Each nb_dgts
		Color 180,0,0
		tf#=minf#(nb\timer/Float(TIMER_DGTS),1)
		ai=nb\direction*30*(Sqr#(Sqr(tf#)))
		bi=-20*(tf#)
		;Oval nb\x-10+ai,nb\y-10+bi,20,20,0
		u=Len(nb\valeur$)
		For t=1 To u
			If Mid(nb\valeur$,t,1)="!"
				valeur=10
			ElseIf Mid(nb\valeur$,t,1)="*"
				valeur=11
			Else				
				valeur=Int(Mid(nb\valeur$,t,1))
			Endif
			DrawImage gfx_chiffre,nb\x+ai+(t-0.5*u-1)*16,nb\y+bi-10,valeur
		Next	
		nb\timer=nb\timer+1
		If nb\timer>2*TIMER_DGTS
			Delete nb
		EndIf
	Next
End Function

Function new_chgt_equi(valeur,x,y,direction)
	;new_log("on arrive ici ; valeur = "+valeur)
	nb.chgt_equi = New chgt_equi
	nb\image=valeur
	nb\x=x
	nb\y=y
	nb\direction=direction	
End Function

Function update_chgt_equi()
	For nb.chgt_equi=Each chgt_equi
		tf#=minf#(nb\timer/Float(TIMER_DGTS),1)
		ai=nb\x+nb\direction*50*(Sqr#(Sqr(tf#)))
		bi=nb\y-30*(tf#)
		If nb\image>0
			DrawBlock nb\image,ai-20,bi-20
		Else
			DrawBlock bouton_combat_sombre,ai-25,bi-25,28
		EndIf
		nb\timer=nb\timer+1
		If nb\timer>2*TIMER_DGTS
			Delete nb
		EndIf
	Next
End Function

;; recréé l'objet 3d en fonction de son type. change_manikin à 1 si il faut juste changer l'apparence (pour les chgts de PJ leader du joueur)
;; note : cette fonction contient des données en dur correspondant à un scénario précis.
;; Il faut la modifier si on change d'histoire.
Function load_groupe(num,change_manikin=0)
	For gr.groupe=Each groupe
		If gr\num=num
			gr\old_animation=-1
			For t=3 To 1 Step -1
				If gr\manikin[t]>0
					FreeEntity gr\manikin[t]
					gr\manikin[t]=0
				EndIf
			Next
			If change_manikin=0
				If gr\pivot>0
					FreeEntity gr\pivot
					gr\pivot=0
				EndIf
				gr\pivot=CreatePivot()
			EndIf
			If num<0
				Select player_avatar(player_leader)
					Case 1 ; Major				
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						gr\manikin[1]=LoadMD2("objets\Marc\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Marc\Marc.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.030,0.030,0.030
						PositionEntity gr\manikin[1],0,0.30,0    ;;?? 0.1,0.3,-0.15 ??
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
	
					Case 2 ; Leopold
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						gr\manikin[1]=LoadMD2("objets\Leopold\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Leopold\Leopold.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.030,0.030,0.030
						PositionEntity gr\manikin[1],0,0.30,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Case 3 ; Adeline
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						gr\manikin[1]=LoadMD2("objets\Adeline\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Adeline\Adeline.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.030,0.030,0.030
						PositionEntity gr\manikin[1],0,0.30,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					
				End Select
				pl_grp_manikin=gr\manikin[1]
			Else
				Select num		
					
					Case 101 ; Stale
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\hansolo\tris.md2",gr\pivot)
						tex=LoadTexture("objets\hansolo\hansolo.bmp")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.030,0.030,0.030
						PositionEntity gr\manikin[1],0,0.30,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Case 102 ; E1
						gr\manikin[1]=-1
						gr\activator=LoadSprite("sprites\gfx\toctoc.bmp",4)
						ScaleSprite gr\activator,0.5,0.5
						PositionEntity gr\activator,5,2,5,1
						EntityAlpha gr\activator,0
						gr\script[2]=MilliSecs()
						;gr\trigger[2] est le son toctoc.wav
					
					Case 103 ; fillette
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.35
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\kid\tris.md2",gr\pivot)
						tex=LoadTexture("objets\kid\kid.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.0225,0.0225,0.0225
						PositionEntity gr\manikin[1],0,0.10,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Case 151 ; rats pokémon
						gr\manikin[1]=LoadMesh("objets\rat1.x",gr\pivot)
						ScaleEntity gr\manikin[1],4,4,4
						PositionEntity gr\manikin[1],0,0.05,0
						RotateEntity gr\manikin[1],0,180,0,0
						gr\animation=11
					
					Case 152
						gr\manikin[1]=LoadMesh("objets\rat1.x",gr\pivot)
						ScaleEntity gr\manikin[1],4,4,4
						PositionEntity gr\manikin[1],0,0.05,0
						RotateEntity gr\manikin[1],0,180,0,0
						gr\animation=11
						
					Case 153
						gr\manikin[1]=LoadMesh("objets\rat1.x",gr\pivot)
						ScaleEntity gr\manikin[1],4,4,4
						PositionEntity gr\manikin[1],0,0.05,0
						RotateEntity gr\manikin[1],0,180,0,0
						gr\animation=11
						
					Case 199 ; dresseur pokémon
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\garde\tris.md2",gr\pivot)
						tex=LoadTexture("objets\garde\garde.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					
					Case 205 ; Stan
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\Stan\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Stan\Stan.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.035,0.035,0.035
						PositionEntity gr\manikin[1],0,0.35,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						If map_stat(2,3)=0
							gr\manikin[2]=LoadMD2("objets\Stan\weapon.md2",gr\pivot)
							tex=LoadTexture("objets\Stan\weapon.pcx")
							EntityTexture gr\manikin[2],tex
							FreeTexture tex
							ScaleEntity gr\manikin[2],0.035,0.035,0.035
							PositionEntity gr\manikin[2],0,0.35,0
						EndIf

					Case 207
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\garde\tris.md2",gr\pivot)
						tex=LoadTexture("objets\garde\garde.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0

					Case 208
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\garde\tris.md2",gr\pivot)
						tex=LoadTexture("objets\garde\garde.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0

					Case 209
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\garde\tris.md2",gr\pivot)
						tex=LoadTexture("objets\garde\garde.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Case 217 ; Infirmière
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					
					Case 218 ; Teddy
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					
					Case 302 ; Arsène
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\arsene\tris.md2",gr\pivot)
						tex=LoadTexture("objets\arsene\arsene.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						gr\detector=CreatePivot()
						
					Case 303
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\Emanuella\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Emanuella\ema.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						gr\detector=CreatePivot()
	
					Case 304
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						EntityRadius gr\pivot,0.40
						EntityType gr\pivot,type_mur
					Case 305
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						EntityRadius gr\pivot,0.40
						EntityType gr\pivot,type_mur
					Case 306
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						EntityRadius gr\pivot,0.40
						EntityType gr\pivot,type_mur
					Case 307
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					Case 308
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
					
					Case 406 ; MJ
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadAnimMesh("objets\makbot\mak_robotic.x" )
						ScaleEntity gr\manikin[1],0.04,0.04,0.04
						PositionEntity gr\manikin[1],64.65,8.9,-18.2
						TurnEntity gr\manikin[1],0,-70,0
						EntityShininess gr\manikin[1],0.5
						Animate gr\manikin[1],1,.25
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Case 502 ; sentinelle
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.35
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\Chasseur\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Chasseur\Refugies.png")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.032,0.032,0.032
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.47,0
						AnimateMD2 gr\manikin[1],1,0.05,25,40
						
					Case 503 ; Arsène
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\arsene\tris.md2",gr\pivot)
						tex=LoadTexture("objets\arsene\arsene.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.032,0.032,0.032
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.46,0
						gr\detector=CreatePivot()
						AnimateMD2 gr\manikin[1],1,0.05,0,20
						
					Case 504
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\Emanuella\tris.md2",gr\pivot)
						tex=LoadTexture("objets\Emanuella\ema.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						gr\detector=CreatePivot()
						AnimateMD2 gr\manikin[1],1,0.1,0,40
					Case 505 ; Forgeron
						gr\activator=CreatePivot(gr\pivot)
							PositionEntity gr\activator,0,-0.4,0
						
					Case 901 ; testeur Script
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						gr\manikin[1]=createcube(gr\pivot)
						tex=LoadTexture("textures\environnement\palissade.jpg")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						scaleentity gr\manikin[1],0.25,0.25,0.25
						PositionEntity gr\manikin[1],0,0.25,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,0.05,0
						gr\detector=CreatePivot()
						
					Case 31501 ; testeur de Patrouille
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						EntityType gr\pivot,type_perso
						gr\manikin[1]=LoadMD2("objets\garde\tris.md2",gr\pivot)
						tex=LoadTexture("objets\garde\garde.pcx")
						EntityTexture gr\manikin[1],tex
						FreeTexture tex
						ScaleEntity gr\manikin[1],0.03,0.03,0.03
						PositionEntity gr\manikin[1],0,0.3,0
						gr\activator=CreatePivot(gr\pivot)
						PositionEntity gr\activator,0,-0.35,0
						
					Default
						EntityPickMode gr\pivot,1
						EntityRadius gr\pivot,0.45
						
				End Select
				;Selection multiple pour les groupes identiques (genre les patrouilles extérieures)
				If num>479 And num<490
					gr\manikin[1]=LoadMesh("objets\Patrouilleur_volant\Patrouilleur_volant.X",gr\pivot)
					a#=1.5
					ScaleEntity gr\manikin[1],a#,a#,a#
					PositionEntity gr\manikin[1],0,2.5,0
					RotateEntity gr\manikin[1],0,270,0,0
					;gr\animation=21
					EntityRadius gr\pivot,0.45
					EntityType gr\pivot,type_perso
					gr\manikin[2]=LoadSprite("objets\Patrouilleur_volant\helice.png",4,gr\manikin[1])
					PositionEntity gr\manikin[2],0.23/a#,-1.3/a#,0
					TurnEntity gr\manikin[2],0,90,0
					SpriteViewMode gr\manikin[2],2
					ScaleSprite gr\manikin[2],0.3,0.3
					gr\manikin[3]=LoadSprite("objets\Patrouilleur_volant\helice2.png",4,gr\manikin[2])
					TurnEntity gr\manikin[3],0,180,0
					SpriteViewMode gr\manikin[3],2
					ScaleSprite gr\manikin[3],0.3,0.3
					gr\manikin[3]=CreatePivot(gr\manikin[1])
					PositionEntity gr\manikin[3],0.02/a#,0.85/a#,-0.38/a#
				EndIf
				
			EndIf
			Return 1 ; ok
		EndIf
	Next
	Return 0 ; quelque chose s'est mal passé
End Function

Function delete_groupe(num)
	For gr.groupe=Each groupe
		If gr\num=num
			For t=3 To 1 Step -1
				If gr\manikin[t]>0 
					FreeEntity gr\manikin[t]
					gr\manikin[t]=0
				EndIf
			Next
			If gr\pivot>0
				FreeEntity gr\pivot
				gr\pivot=0
			EndIf
			If gr\detector>0
				FreeEntity gr\detector
				gr\detector=0
			EndIf
		EndIf
	Next	
End Function

Function changer_leader(num_leader)
	If num_leader<>player_leader
		player_leader=num_leader
		For av.avatar=Each avatar
			If av\num=player_avatar(player_leader)
				name$=av\name$[Int(options#(7))]
			EndIf
		Next	
		If Int(options#(7)) = 1
				new_log(name$+" est maintenant le leader du groupe.")
			Else
				new_log(name$+" is  the new group' leader.")
			Endif
		
		load_groupe(-1,1)
	EndIf
End Function

Function teleporter(num,x#,y#,z#,yaw#=0)
	For groupe.groupe=Each groupe
		If groupe\num=num
			If groupe\pivot<>0
				PositionEntity groupe\pivot,x#,y#,z#
				RotateEntity groupe\pivot,0,yaw#,0
				ResetEntity groupe\pivot
			EndIf
			groupe\position#[1]=x#
			groupe\position#[2]=y#
			groupe\position#[3]=z#
			groupe\position#[4]=yaw#
		EndIf
	Next
End Function

Function population(gr_num,vivant=0)
	population=0
	For av.avatar=Each avatar
		If av\groupe=gr_num
			If vivant=0
				population=population+1
			Else
				If av\pv[1]>0 Then population=population+1
			EndIf
		EndIf
	Next
	Return population
End Function

Function load_butin(num)
	For butin.butin=Each butin
		If butin\num=num
			Select butin\kind
				;Case 1 (dans le Default)
				
				Case 2 ; Invisible
					butin\pivot=CreatePivot()
					
				Default ; also case 1 ; box
					butin\pivot=CreatePivot()
					butin\manikin=LoadMesh("objets\butin\caisse.x",butin\pivot)
					ScaleEntity butin\manikin,0.05,0.05,0.05
					PositionEntity butin\manikin,0,0.3,0,0
			End Select
		EndIf
	Next
End Function

Function delete_butin(num)
	For butin.butin=Each butin
		If butin\num=num
			If butin\manikin<>0 Then FreeEntity butin\manikin
			butin\manikin=0
			If butin\pivot<>0 Then FreeEntity butin\pivot
			butin\pivot=0
		EndIf
	Next
End Function

Function load_combattant(num=1)
	if num<1 Then runtimeerror "load_combattant : num = "+num
	If fighters_gfx(num,1)=0
		Select num
			Case 1 ; Major
				a$="Major"
				hx=40
				hy=80
				
			Case 2 ; Léopold
				a$="Leopold"
				hx=40
				hy=80

			Case 3 ; Adeline
				a$="Adeline"
				hx=40
				hy=80
			
			Case 11 ; rat
				a$="rat_A"
				hx=40
				hy=60
			Case 12 ; le Kid
				a$="kid"
				hx=40
				hy=80
			;case 13 ; prtkt hache
			Case 14 ; prtkt mur
				a$="prtktmur"
				hx=40
				hy=100
			;case 15 ; Protecteur
			Case 15 ; Stan
				a$="Protecteur"
				hx=40
				hy=80
				
			Case 17 ; S-S
				a$="Boss"
				hx=40;100 si joueur
				hy=117
			
			Case 20 ; Adeline
				a$="kid"
				hx=40
				hy=80
				
			Case 21 ; Arsène
				a$="Stan"
				hx=40
				hy=80
			
			Case 30; Patrouilleur
				a$="Patrouilleur"
				hx=40
				hy=117
			
			Case -21; GearBot 1
				a$="GearBot"
				hx=100
				hy=117
			
			Default
				a$="default_guy_A"
				hx=40
				hy=80
		End Select
		If FileType("sprites\combattants\"+a$+".png")=1
			If a$="prtktmur"
				fighters_gfx(num,1)=LoadAnimImage("sprites\combattants\"+a$+".png",80,100,0,MAX_FRAME_FIGHTER_GFX)
				fighters_gfx(num,2)=LoadAnimImage("sprites\combattants\"+a$+".png",80,100,0,MAX_FRAME_FIGHTER_GFX)
				fighters_tete(num,1)=LoadImage("sprites\combattants\default_guy_A_face1.png")
				fighters_tete(num,2)=LoadImage("sprites\combattants\default_guy_A_face2.png")
			ElseIf a$="Protecteur"
				fighters_gfx(num,1)=LoadAnimImage("sprites\combattants\"+a$+".png",80,80,0,MAX_FRAME_FIGHTER_GFX)
				fighters_gfx(num,2)=LoadAnimImage("sprites\combattants\"+a$+".png",80,80,0,MAX_FRAME_FIGHTER_GFX)
				fighters_tete(num,1)=LoadImage("sprites\combattants\Protecteur_face1.png")
				fighters_tete(num,2)=LoadImage("sprites\combattants\default_guy_A_face2.png")
			ElseIf a$="Boss"
				fighters_gfx(num,1)=LoadAnimImage("sprites\combattants\"+a$+".png",280,240,0,MAX_FRAME_FIGHTER_GFX)
				fighters_gfx(num,2)=LoadAnimImage("sprites\combattants\"+a$+"_mirror.png",280,240,0,MAX_FRAME_FIGHTER_GFX)
				fighters_tete(num,1)=LoadImage("sprites\combattants\BB_face1.png")
				fighters_tete(num,2)=LoadImage("sprites\combattants\default_guy_A_face2.png")
			ElseIf a$="Patrouilleur"
				fighters_gfx(num,1)=LoadAnimImage("sprites\combattants\"+a$+".png",140,120,0,MAX_FRAME_FIGHTER_GFX)
				fighters_gfx(num,2)=LoadAnimImage("sprites\combattants\"+a$+"_mirror.png",140,120,0,MAX_FRAME_FIGHTER_GFX)
				fighters_tete(num,1)=LoadImage("sprites\combattants\patrouilleur_face1.png")
				fighters_tete(num,2)=LoadImage("sprites\combattants\default_guy_A_face2.png")
			Else
				fighters_gfx(num,1)=LoadAnimImage("sprites\combattants\"+a$+".png",80,80,0,MAX_FRAME_FIGHTER_GFX)
				fighters_gfx(num,2)=LoadAnimImage("sprites\combattants\"+a$+"_mirror.png",80,80,0,MAX_FRAME_FIGHTER_GFX)
				fighters_tete(num,1)=LoadImage("sprites\combattants\"+a$+"_face1.png")
				fighters_tete(num,2)=LoadImage("sprites\combattants\"+a$+"_face2.png")
			EndIf
			
			If num<>12
				;ScaleImage fighters_gfx(num,1),screenscale#,screenscale#
				;ScaleImage fighters_gfx(num,2),screenscale#,screenscale#
				If fighters_gfx(num,1)<>0 Then HandleImage fighters_gfx(num,1),hx,hy
				If fighters_gfx(num,2)<>0 Then HandleImage fighters_gfx(num,2),hx,hy
			Else
				If fighters_gfx(num,1)<>0
					ScaleImage fighters_gfx(num,1),screenscale#*0.75,screenscale#*0.75
					HandleImage fighters_gfx(num,1),hx*0.75,hy*0.75
				EndIf
				If fighters_gfx(num,2)<>0
					ScaleImage fighters_gfx(num,2),screenscale#*0.75,screenscale#*0.75
					HandleImage fighters_gfx(num,2),hx*0.75,hy*0.75
				EndIf
			EndIf
			
		Else
			new_log("Err Load_combattant : num="+num+" !",255,0,0)
		EndIf
	EndIf
	If fighters_gfx(num,2)*fighters_gfx(num,1)=0 Then RuntimeError "Image de combattant non chargée : num = "+num
End Function


Function load_map(level,entrance=1)
	entiteTest=0
	Select level
		Case 1 ; garage
			loaded_map=1
			Include "maps\map01.bb"
			
		Case 2 ; Rdc
			loaded_map=2
			Include "maps\map02.bb"	
			
		;Case 3 ; Unused
		;	loaded_map=3
		;	Include "maps\map03.bb"		
		
		Case 4 ; exterieur
			loaded_map=4
			Include "maps\map04.bb"
		
		Case 5 ; camp des esclaves
			loaded_map=5
			Include "maps\map05.bb"
		
		;Case 314
		;	Include "maps\map314.bb"
		;	loaded_map=314
		;
		Case 315
			loaded_map=315
			Include "maps\map315.bb"
			
		Default
			RuntimeError "Numéro de map incorrect "+level
	End Select
	
	;a enlever à la fin
	If mode_debug=1
		For agr.agresseur = Each agresseur
			If agr\map=level
				For t=1 To 4
					borne=LoadSprite("sprites\gfx\shot1.bmp")
					ScaleSprite borne,0.5,0.5
					PositionEntity borne,agr\polyx#[t],agr\position#[2],agr\polyz#[t]
				Next
			EndIf
		Next
		For pat.patrouilleur=Each patrouilleur
			If pat\map=level
				For t=1 to 3
					pat\sprite[t]=LoadSprite("sprites\gfx\shot1.bmp")
					ScaleSprite pat\sprite[t],0.5,0.5
				Next
			EndIf
		Next
	Endif
	
	
	
End Function

;supprime tout ce qui est chargé à chaque début de map
Function clean_world()
	Cls
	Flip
	
	For smoke.smoke = Each smoke
		Delete smoke
	Next
	
	For smoke_source.smoke_source=Each smoke_source
		Delete smoke_source
	Next
	
	For gr.groupe=Each groupe
		gr\pivot=0
		gr\manikin[1]=0
		gr\manikin[2]=0
		gr\manikin[3]=0
		gr\activator=0
		gr\detector=0
	Next
	
	For butin.butin=Each butin
		butin\pivot=0
		butin\manikin=0
	Next
	
	For porte.porte=Each porte
		porte\pivot=0
		porte\manikin=0
	Next

	activator=0
	sky=0
	entiteTest=0
	
	centre_porteAscenceur=0
	tranche1_porteAscenceur=0
	tranche2_porteAscenceur=0
	tranche3_porteAscenceur=0
	tranche4_porteAscenceur=0
	tranche5_porteAscenceur=0
	tranche6_porteAscenceur=0
	tranche7_porteAscenceur=0
	porte_cellule7=0
	porte2_cellule7=0
	porte3_cellule7=0
	porte4_cellule7=0
	porte_plafond_metaPorte=0
	Verrin1_metaPorte=0
	Verrin2_metaPorte=0
	Verrin3_metaPorte=0
	Verrin4_metaPorte=0
	Verrin5_metaPorte=0
	Verrin6_metaPorte=0
	Verrin7_metaPorte=0
	Verrin8_metaPorte=0
	Verrin9_metaPorte=0
	Verrin10_metaPorte=0
	Verrou1_metaPorte=0
	Verrou2_metaPorte=0
 	Tuyau1_metaPorte=0
	Tuyau2_metaPorte=0
 	tete_metaPorte=0
	base_chapeau_metaPorte=0
	forme_chapeau_metaPorte=0
	pivot_nez_metaPorte=0
	main1_metaPorte=0
	main2_metaPorte=0
	boitierSteamille=0
	
	ClearWorld

End Function

;supprime tous les types afin de pouvoir lancer une nouvelle partie propre
Function clean_universe()
	For t=1 To 200
		log_mess$(t,1)=""
		log_mess$(t,2)=""
		log_color(t,1)=0
		log_color(t,2)=0
		log_color(t,3)=0	
	Next
	For map.map=Each map
		Delete map
	Next
	For groupe.groupe=Each groupe
		Delete groupe
	Next
	For avatar.avatar=Each avatar
		Delete avatar
	Next
	For player.player=Each player
		Delete player
	Next
	For agresseur.agresseur=Each agresseur
		Delete agresseur
	Next
	For combat.combat=Each combat
		Delete combat
	Next
	For butin.butin=Each butin
		Delete butin
	Next
	For smoke.smoke=Each smoke
		Delete smoke
	Next
	For smoke_source.smoke_source=Each smoke_source
		Delete smoke_source
	Next
	For porte.porte=Each porte
		Delete porte
	Next	
End Function


Function new_smoke_source(num,posx#,posy#,posz#,freq=25,lifespan=500,speed#=7,scale#=0.1,scale_final#=.5,pitch#=0,yaw#=90,disp_pitch#=5,disp_yaw#=5,couleur1=255,couleur2=255,couleur3=255,cycle=5000,a#=0.5,lifespan_source=-1,sprite_flag=1)
	s.smoke_source=New smoke_source
	s\num=num
	s\pos#[1]=posx#
	s\pos#[2]=posy#
	s\pos#[3]=posz#
	s\freq=freq
	s\lifespan_smoke=lifespan
	s\scale#=scale#
	s\orientation#[1]=pitch#
	s\orientation#[2]=yaw#
	s\dispersion#[1]=disp_pitch#
	s\dispersion#[2]=disp_yaw#
	s\speed#=speed#
	s\couleur[1]=couleur1
	s\couleur[2]=couleur2
	s\couleur[3]=couleur3
	s\cycle=cycle
	s\a#=a#
	s\lifespan_source=lifespan_source
	s\timer#=0
	s\cycle_timer#=0
	s\scale_final#=scale_final#
	s\sprite_flag=sprite_flag
End Function

Function delete_smoke_source(num)
	For s.smoke_source=Each smoke_source
		If s\num=num
			Delete s
			Return
		EndIf
	Next
End Function

Function update_smoke_source()
	For s.smoke_source=Each smoke_source
		s\timer=s\timer+delta_frame
		s\cycle_timer=s\cycle_timer+delta_frame
		;test si il a fini de vivre
		ok=1
		If s\cycle_timer>s\cycle
			s\cycle_timer=s\cycle_timer-s\cycle
			If s\lifespan_source<0
				;source éternelle
			Else
				s\lifespan_source=s\lifespan_source-1
				If s\lifespan_source<0
					Delete s
					ok=0
				EndIf
			EndIf
		EndIf
		;test si il est dans la bonne partie du cycle
		If ok=1
			If s\cycle_timer<s\cycle*s\a#
				;test si il faut émettre un sprite
				If s\timer>s\freq
					s\timer=s\timer-s\freq
					; faire un nouveau smoke
					sm.smoke=New smoke
					sm\pivot=CreatePivot()
					PositionEntity sm\pivot,s\pos#[1],s\pos#[2],s\pos#[3]
					sm\sprite=LoadSprite("sprites\gfx\smoke"+Str(Rand(1,4))+".bmp",s\sprite_flag,sm\pivot)
					ScaleSprite sm\sprite,s\scale#,s\scale#
					sm\scale#=s\scale#
					sm\scale_final#=s\scale_final#
					EntityColor sm\sprite,s\couleur[1],s\couleur[2],s\couleur[3]
					yaw#=s\orientation[2]+Rnd(-s\dispersion[2],s\dispersion[2])
					pitch#=s\orientation[1]+Rnd(-s\dispersion[1],s\dispersion[1])
					sm\speed#[1]=-Sin(yaw#)*Cos(pitch#)*s\speed#
					sm\speed#[2]=Sin(pitch#)*s\speed#
					sm\speed#[3]=Cos(yaw#)*Cos(pitch#)*s\speed#
					sm\lifespan=s\lifespan_smoke
					sm\timer=0
				EndIf
			EndIf
		EndIf
	Next
End Function

Function create_smoke(x#,y#,z#,scale#,scale_final#,speed1#,speed2#,speed3#,lifespan,couleur1=250,couleur2=250,couleur3=250,flag=1)
	sm.smoke=New smoke
	sm\pivot=CreatePivot()
	PositionEntity sm\pivot,x#,y#,z#
	sm\sprite=LoadSprite("sprites\gfx\smoke"+Str(Rand(1,4))+".bmp",flag,sm\pivot)
	ScaleSprite sm\sprite,scale#,scale#
	sm\scale#=scale#
	sm\scale_final#=scale_final#
	EntityColor sm\sprite,couleur1,couleur2,couleur3
	sm\speed#[1]=speed1#
	sm\speed#[2]=speed2#
	sm\speed#[3]=speed3#
	sm\lifespan=lifespan
	sm\timer=0
End Function

Function update_smoke()
	For s.smoke=Each smoke
		s\timer=s\timer+delta_frame
		If s\timer>s\lifespan
			FreeEntity s\sprite
			FreeEntity s\pivot
			Delete s
		Else
			For t=1 To 3
				s\speed#[t]=s\speed#[t]*0.90
			Next
			s\speed#[2]=s\speed#[2]+delta_frame*0.003
			tempo#=delta_frame*0.001
			TranslateEntity s\pivot,s\speed#[1]*tempo#,s\speed#[2]*tempo#,s\speed#[3]*tempo#,1
			tempo#=s\timer/Float(s\lifespan)
			If s\scale#<>s\scale_final#
				scale#=s\scale#*(1-tempo#)+s\scale_final#*tempo#
				ScaleSprite s\sprite,scale#,scale#
			EndIf
			EntityAlpha s\sprite,1-tempo#
		EndIf
	Next
End Function

Function new_porte(num,gr=0,etat=0)
	test=0
	For porte.porte=Each porte
		If porte\num=num
			test=1
			porte\etat=etat
			porte\groupe=gr
		EndIf
	Next
	If test=0
		porte.porte=New porte
		porte\num=num
		porte\etat=etat
		porte\groupe=gr
	EndIf
End Function

Function activer_porte(num,chgt=2)
	For p.porte=Each porte
		If p\num=num
			If chgt<2
				p\etat=chgt
			Else
				p\etat=1-p\etat
			EndIf		
		EndIf
	Next
End Function

Function update_porte()
	For p.porte= Each porte
		If p\pivot<>0
			If p\etat=0
				For t=1 To 6
					If Abs(p\pos_act#[t]-p\pos_init#[t])<Abs(p\speed#[t]*0.05) Or Abs(p\pos_act#[t]-p\pos_init#[t])>2*Abs(p\pos_final#[t]-p\pos_init#[t])
						p\pos_act#[t]=p\pos_init#[t]
					Else
						p\pos_act#[t]=p\pos_act#[t]-p\speed#[t]*delta_frame*0.001
					EndIf
				Next
			Else
				For t=1 To 6
					If Abs(p\pos_act#[t]-p\pos_final#[t])<Abs(p\speed#[t]*0.05) Or Abs(p\pos_act#[t]-p\pos_final#[t])>2*Abs(p\pos_init#[t]-p\pos_final#[t])
						p\pos_act#[t]=p\pos_final#[t]
					Else
						p\pos_act#[t]=p\pos_act#[t]+p\speed#[t]*delta_frame*0.001
					EndIf
				Next
			EndIf
			PositionEntity p\pivot,p\pos_act#[1],p\pos_act#[2],p\pos_act#[3]
			RotateEntity p\pivot,p\pos_act#[4],p\pos_act#[5],p\pos_act#[6]
		EndIf	
	Next
End Function

Function change_bgm(music$,k#=0.2)
	StopChannel ch_bmg
	If bgm<>0 FreeSound bgm
	bgm=LoadSound("musiques\"+music$)
	If bgm<>0
		LoopSound bgm
		ch_bgm=PlaySound(bgm)
		ChannelVolume ch_bgm,k#*options#(5)
	Else
		runtimeerror "La musique "+music$+" n'existe pas"
	EndIf
End Function

Function call_activator(num)
	For g.groupe=Each groupe
		If g\num=num
			If g\activator<>0
				aff_activator(EntityX#(g\activator,1),EntityY#(g\activator,1),EntityZ#(g\activator,1),g\act_scale#,g\act_type)
			Else
				PositionEntity activator,0,-1000,0
			EndIf
		EndIf
	Next
End Function

Function aff_activator(x#,y#,z#,scale#=1,kind=1)
	activator_actif=1
	ScaleSprite activator,scale#*(1+0.1*Cos(Int(timer_animation#*3))),scale#*(1+0.1*Cos(Int(timer_animation#*3)))
	Select kind
		Case 1
			SpriteViewMode activator,1
			PositionEntity activator,x#,y#,z#,1	
			RotateSprite activator,Int(timer_animation#)
		Case 2
			SpriteViewMode activator,2
			PositionEntity activator,x#,y#,z#,1
			RotateEntity activator,90,0,0
			RotateSprite activator,Int(timer_animation#)
		Case 3
			SpriteViewMode activator,2
			PositionEntity activator,x#,y#,z#,1
			alpha#=pointeryaw#(EntityX#(cam,1),EntityZ#(cam,1),x#,z#)
			RotateEntity activator,0,180+alpha#,0
			RotateSprite activator,Int(timer_animation#)		
	End Select
End Function

Function drawgrey(xd,yd,xl,yl,alpha#=0.5,forcer=0)
;RuntimeError options#(1)
	temp=1;Int(options#(1))
	If forcer>0 Then temp=forcer
	alpha#=1-alpha#
	If temp=1
		LockBuffer BackBuffer ()
		For x=xd To (xd+xl)
			For y=yd To (yd+yl)
				rgb = ReadPixelFast (x,y)
				temp_r = (rgb Shr 16 And %11111111)*alpha#
				temp_g = (rgb Shr 8 And %11111111)*alpha#
				temp_b = (rgb And %11111111)*alpha#
				WritePixelFast x,y,temp_b Or (temp_g Shl 8) Or (temp_r Shl 16)
			Next
		Next
		UnlockBuffer BackBuffer ()
	ElseIf temp=2
		Color 0,0,0
		Rect xd,yd,xl+1,yl+1
		;DrawImageRect grey,xd,yd,0,0,xl,yl
	Else
		Color 0,0,0
		Rect xd,yd,xl+1,yl+1
	EndIf
End Function

; fenêtre plus cool, pour les discussions sans intervention (PNJ inutiles, affiches...) ou autre affichage pendant la map3D (type "la porte est fermée" ou "appuyer sur ...")
Function drawgrey2(x,y,width,lenght)
	cas=min(width,lenght)
	;DrawImageRect fond_grey2,x,y,0,0,width,lenght
	drawgrey(x,y,width,lenght)
	If cas<10 ; trop petit
		Color 255,255,255
		Rect x,y,width,lenght,0
	ElseIf cas<30 ; mini
		DrawImage miniblock_tl,x,y
		DrawImage miniblock_tr,x+width-5,y
		DrawImage miniblock_bl,x,y+lenght-5
		DrawImage miniblock_br,x+width-5,y+lenght-5
		a=width-10
		b=0
		While a>b
			DrawImageRect miniblock_top,x+5+b,y,0,0,min(a-b,5),5
			DrawImageRect miniblock_btm,x+5+b,y+lenght-5,0,0,min(a-b,5),5
			b=b+5
		Wend
		b=lenght-10
		a=0
		While b>a
			DrawImageRect miniblock_left,x,y+5+a,0,0,5,min(b-a,5)
			DrawImageRect miniblock_right,x+width-5,y+5+a,0,0,5,min(b-a,5)
			a=a+5
		Wend
 	ElseIf cas<80 ; small
 		DrawImage smallblock_tl,x-10,y-10
 		DrawImage smallblock_tr,x+width-10,y-10
 		DrawImage smallblock_bl,x-10,y+lenght-10
 		DrawImage smallblock_br,x+width-10,y+lenght-10
 		a=width-20
		b=0
		While a>b
			DrawImageRect miniblock_top,x+10+b,y,0,0,min(a-b,5),5
			DrawImageRect miniblock_btm,x+10+b,y+lenght-5,0,0,min(a-b,5),5
			b=b+5
		Wend
		b=lenght-20
		a=0
		While b>a
			DrawImageRect miniblock_left,x,y+10+a,0,0,5,min(b-a,5)
			DrawImageRect miniblock_right,x+width-5,y+10+a,0,0,5,min(b-a,5)
			a=a+5
		Wend
 	Else
 		DrawImage bigblock_tl,x-20,y-20
 		DrawImage bigblock_tr,x+width-20,y-20
 		DrawImage bigblock_bl,x-20,y+lenght-20
 		DrawImage bigblock_br,x+width-20,y+lenght-20
 		a=Ceil((width-39)/2)
 		DrawImageRect bigblock_top1,x+20,y-5,0,0,a,10
 		DrawImageRect bigblock_top2,x+width-20-a,y-5,380-a,0,a,10
 		DrawImageRect bigblock_btm1,x+20,y+lenght-5,0,0,a,10
 		DrawImageRect bigblock_btm2,x+width-20-a,y+lenght-5,380-a,0,a,10
 		b=Ceil((lenght-39)/2)
 		DrawImageRect bigblock_left1,x-5,y+20,0,0,10,b
 		DrawImageRect bigblock_left2,x-5,y+lenght-20-b,0,280-b,10,b
 		DrawImageRect bigblock_right1,x+width-5,y+20,0,0,10,b
 		DrawImageRect bigblock_right2,x+width-5,y+lenght-20-b,0,280-b,10,b
 	EndIf
End Function

Function drawcadre(x,y,width,lenght,style=1)
	Select style
		Case 1 ; bordure façon HUD
			width=max(16,width)
			lenght=max(16,lenght)
			DrawImage bordure_tl,x,y
			DrawImage bordure_tr,x+width-8,y
			DrawImage bordure_bl,x,y+lenght-8
			DrawImage bordure_br,x+width-8,y+lenght-8
			DrawImageRect bordure_top,x+8,y,0,0,width-16,8
			DrawImageRect bordure_btm,x+8,y+lenght-8,0,0,width-16,8
			DrawImageRect bordure_left,x,y+8,0,0,8,lenght-16
			DrawImageRect bordure_right,x+width-8,y+8,0,0,8,lenght-16
 	End Select
End Function


;affiche une fenetre de dialogue avec choix
;les variables utilisées sont dans les var_scripts

Function fenetreyn(mess$="Tu m'aimes ?",yes$="",no$="",af=400,bf=300,font=1)
	retour=0
	If yes$="" And no$=""
		Select Int(options#(7))
			Case 1
				yes$="Oui"
				no$="Non"
			Case 2
				yes$="Yes"
				no$="No"
		End Select
	EndIf
	cas_cam=-Abs(cas_cam)
	If font=1 Then SetFont middle_font
	lenght=max(Int(Len(mess$)*9+20),300)
	drawgrey2(af-lenght*0.5-2,bf,lenght+3,103)
	Color 200,200,200
	;Rect af-lenght*0.5,bf+2,lenght,100,0
	Text af,bf+15,mess$,1,1
	a=af-lenght*0.25-Int(Len(yes$)*5.5+3)
	b=Int(Len(yes$)*11)+6
	c=af+lenght*0.25-Int(Len(no$)*5.5+3)
	d=Int(Len(no$)*11)+6
	
	If (keys(6,2)=50 Or keys(48,2)=50) And player_in_control
		choix_qcm=1
	EndIf
	
	If (keys(8,2)=50 Or keys(49,2)=50) And player_in_control
		choix_qcm=2
	EndIf
	
	If (keys(12,2)=50 Or keys(14,2)=50) And player_in_control
		keys(14,2)=49
		retour=choix_qcm
	EndIf
	
	If MouseX()>a And MouseX()<a+b And MouseY()<bf+80 And MouseY()>bf+60 And player_in_control
		If keys(1,2)=50 Then retour=1
		choix_qcm=1
	EndIf
	
	If choix_qcm=1
		Color 255,255,255
		frame=reste(Int(timer_animation#*0.1),8)
		DrawImage little_wheel,a-25,bf+60,frame
		DrawImage little_wheel,a+b+5,bf+60,frame
	Else
		Color 180,180,180
	EndIf
	Text af-lenght*0.25,bf+70,yes$,1,1
	Rect a,bf+55,b,30,0
	
	If MouseX()>c And MouseX()<c+d And MouseY()<bf+80 And MouseY()>bf+60 And player_in_control
		If keys(1,2)=50 Then retour=2
		choix_qcm=2
	EndIf
	
	If choix_qcm=2
		Color 255,255,255
		frame=reste(Int(timer_animation#*0.1),8)
		DrawImage little_wheel,c-25,bf+60,frame
		DrawImage little_wheel,c+d+5,bf+60,frame
	Else
		Color 180,180,180
	EndIf
	Text af+lenght*0.25,bf+70,no$,1,1
	Rect c,bf+55,d,30,0
	
	If retour<>0 Then choix_qcm=0
	Return retour
End Function

Function fenetreqcm_moche(nb_choix=3,mess$="Qui est le plus beau ?",c1$="Benoît",c2$="Loran",c3$="Je n'arrive pas à choisir !",c4$="",c5$="",c6$="",c7$="")
	
	cas_cam=-Abs(cas_cam)
	
	old_choix_qcm=choix_qcm
	choix$(1)=c1$
	choix$(2)=c2$
	choix$(3)=c3$
	choix$(4)=c4$
	choix$(5)=c5$
	choix$(6)=c6$
	choix$(7)=c7$

	max_len=0
	For t=1 To 7
		If Len(choix$(t))>max_len Then max_len=Len(choix$(t))	
	Next
	
	Color 255,255,255
	
	a_l=Int(max((max_len*10+80),400)*screeny#)
	b_l=Int((nb_choix+2.5)*40*screeny#)
	a=Int((screenwidth-a_l)*0.5)
	b=Int((screenheight-b_l)*0.5)
	
	Color 200,200,200
	Rect a,b,a_l,b_l,0
	
	Text screenwidth*0.5,b+40*screeny#,mess$,1,1
	Line a+100*screeny#,b+70*screeny#,a+a_l-100*screeny#,b+70*screeny#
	
	reponse=0
	For t=1 To nb_choix
		If MouseX()>a And MouseX()<a+a_l And MouseY()>b+((1.2+t)*40)*screeny# And MouseY()<b+((1.8+t)*40)*screeny#
			choix_qcm=t
			If keys(1,2)=50 Then reponse=t
		EndIf
		If choix_qcm=t
			Color 255,255,255
			frame=reste(Int(timer_animation#*0.1),8)
			DrawImage little_wheel,a+10*screeny#,b+((1.5+t)*40)*screeny#-10,frame
			DrawImage little_wheel,a+a_l-10*screeny#-20,b+((1.5+t)*40)*screeny#-10,frame
		Else
			Color 180,180,180
		EndIf
		Text a+40*screeny#,b+((1.5+t)*40)*screeny#,choix$(t),0,1
	Next
	
	If keys(7,2)=50 Or keys(50,2)=50
		choix_qcm=choix_qcm+1
		If choix_qcm>nb_choix Then choix_qcm=1
	EndIf	
	
	If keys(4,2)=50 Or keys(47,2)=50
		choix_qcm=choix_qcm-1
		If choix_qcm<1 Then choix_qcm=nb_choix
	EndIf
	
	If (keys(12,2)=50 Or keys(14,2)=50)
		keys(14,2)=49
		retour=choix_qcm
	EndIf
	
	If choix_qcm<>old_choix_qcm Then Playsound2(sons_menu(1))
	
	If reponse<>0 Then choix_qcm=0
	Return reponse
End Function


Function fenetreqcm_court(nb_choix=3,mess$="Qui est le plus beau ?",c1$="Benoît",c2$="Loran",c3$="Je n'arrive pas à choisir !",c4$="",c5$="",c6$="",c7$="",c8$="",c9$="")
	cas_cam=-Abs(cas_cam)
	
	old_choix_qcm=choix_qcm
	choix$(1)=c1$
	choix$(2)=c2$
	choix$(3)=c3$
	choix$(4)=c4$
	choix$(5)=c5$
	choix$(6)=c6$
	choix$(7)=c7$
	choix$(8)=c8$
	choix$(9)=c9$
	
	nb_lignes=0
	If Instr(mess$,"#")>0 ;plusieurs lignes prévues
		plusieurs_lignes=1
		For t=1 To 15
			disc_ligne$(t)=""
		Next
		While Instr(mess$,"#")>0
			pos_break=Instr(mess$,"#")
			nb_lignes=nb_lignes+1
			disc_ligne$(nb_lignes)=Left(mess$,pos_break-1)
			mess$=Mid(mess$,pos_break+1)
		Wend
		If mess$<>""
			nb_lignes=nb_lignes+1
			disc_ligne$(nb_lignes)=mess$
		EndIf
		;selectionner la ligne la plus longue pour la taille de fenetre
		max_len=1
		For t=1 To nb_lignes
			If Len(disc_ligne$(t))>max_len
				max_len=Len(disc_ligne$(t))
				mess$=disc_ligne$(t)
			EndIf
		Next
	Else
		plusieurs_lignes=0	
	EndIf
	
	max_len=Len(mess$)
	For t=1 To 9
		If Len(choix$(t))>max_len Then max_len=Len(choix$(t))	
	Next
	
	SetFont middle_font
	
	Color 255,255,255
	
	a_l=Int(max((max_len*10+80),400)*screeny#)
	a=Int((screenwidth-a_l)*0.5)
	
	;fenetre et question
	If plusieurs_lignes=1
		b_l=Int(((nb_choix+3.5)*40+(nb_lignes-2)*30)*screeny#)
		b=Int((screenheight-b_l)*0.5)
		drawgrey2(a-2,b-2,a_l+3,b_l+3)
		For t=1 To nb_lignes
			Text screenwidth*0.5,b+(t*30+10)*screeny#,disc_ligne$(t),1,1
		Next
		b=b+Int(((nb_lignes-1)*30+10)*screeny#)
	Else
		b_l=Int((nb_choix+2.5)*40*screeny#)
		b=Int((screenheight-b_l)*0.5)
		drawgrey2(a-2,b-2,a_l+3,b_l+3)
		Text screenwidth*0.5,b+40*screeny#,mess$,1,1
	EndIf
	
	Line a+100*screeny#,b+70*screeny#,a+a_l-100*screeny#,b+70*screeny#
	
	reponse=0
	For t=1 To nb_choix
		If MouseX()>a And MouseX()<a+a_l And MouseY()>b+((1.2+t)*40)*screeny# And MouseY()<b+((1.8+t)*40)*screeny# And player_in_control
			choix_qcm=t
			If keys(1,2)=50 Then reponse=t
		EndIf
		If choix_qcm=t
			Color 255,255,255
			frame=reste(Int(timer_animation#*0.1),8)
			DrawImage little_wheel,a+10*screeny#,b+((1.5+t)*40)*screeny#-10,frame
			DrawImage little_wheel,a+a_l-10*screeny#-20,b+((1.5+t)*40)*screeny#-10,frame
		Else
			Color 180,180,180
		EndIf
		Text a+40*screeny#,b+((1.5+t)*40)*screeny#,choix$(t),0,1
	Next
	
	If (keys(7,2)=50 Or keys(50,2)=50) And player_in_control
		choix_qcm=choix_qcm+1
		If choix_qcm>nb_choix Then choix_qcm=1
	EndIf	
	
	If (keys(4,2)=50 Or keys(47,2)=50) And player_in_control
		choix_qcm=choix_qcm-1
		If choix_qcm<1 Then choix_qcm=nb_choix
	EndIf
	
	If (keys(12,2)=50 Or keys(14,2)=50) And player_in_control
		keys(14,2)=49
		reponse=choix_qcm
	EndIf
	
	If choix_qcm<>old_choix_qcm Then Playsound2(sons_menu(1))
	
	If reponse<>0 Then choix_qcm=0
	Return reponse
End Function

Function fenetreqcm(nb_choix=3,mess$="Qui est le plus beau ?",c1$="Benoît",c2$="Loran",c3$="Je n'arrive pas à choisir !",c4$="",c5$="",c6$="",c7$="",c8$="",c9$="")
	cas_cam=-Abs(cas_cam)
	
	old_choix_qcm=choix_qcm
	choix$(1)=c1$
	choix$(2)=c2$
	choix$(3)=c3$
	choix$(4)=c4$
	choix$(5)=c5$
	choix$(6)=c6$
	choix$(7)=c7$
	choix$(8)=c8$
	choix$(9)=c9$
	
	max_len=Len(mess$)
	For t=1 To 9
		If Len(choix$(t))>max_len Then max_len=Len(choix$(t))	
	Next
	
	;Si on décide de réutiliser qcm_long, il faut supprimer cette partie
	reponse=fenetreqcm_court(nb_choix,mess$,c1$,c2$,c3$,c4$,c5$,c6$,c7$,c8$,c9$)
	Return reponse
	
	If max_len<101
		reponse=fenetreqcm_court(nb_choix,mess$,c1$,c2$,c3$,c4$,c5$,c6$,c7$,c8$,c9$)
	Else
		max_len=100
		SetFont middle_font
		
		Color 255,255,255
		
		a_l=Int(max((max_len*10+80),400)*screeny#)
		b_l=Int((nb_choix+2.5)*40*screeny#)
		a=Int((screenwidth-a_l)*0.5)
		b=Int((screenheight-b_l)*0.5)
		
		drawgrey2(a-2,b-2,a_l+3,b_l+3)
		
	;	Color 200,200,200
	;	Rect a,b,a_l,b_l,0
		
		Text screenwidth*0.5,b+40*screeny#,mess$,1,1
		Line a+100*screeny#,b+70*screeny#,a+a_l-100*screeny#,b+70*screeny#
		
		reponse=0
		For t=1 To nb_choix
			If MouseX()>a And MouseX()<a+a_l And MouseY()>b+((1.2+t)*40)*screeny# And MouseY()<b+((1.8+t)*40)*screeny# And player_in_control
				choix_qcm=t
				If keys(1,2)=50 Then reponse=t
			EndIf
			If choix_qcm=t
				Color 255,255,255
				frame=reste(Int(timer_animation#*0.1),8)
				DrawImage little_wheel,a+10*screeny#,b+((1.5+t)*40)*screeny#-10,frame
				DrawImage little_wheel,a+a_l-10*screeny#-20,b+((1.5+t)*40)*screeny#-10,frame
				dsic_len#=10000
				discussion(0,0,"",choix$(t),0)
			Else
				Color 180,180,180
			EndIf
			SetFont middle_font
			Text a+40*screeny#,b+((1.5+t)*40)*screeny#,Left(choix$(t),100),0,1
		Next
		
		If (keys(7,2)=50 Or keys(50,2)=50) And player_in_control
			choix_qcm=choix_qcm+1
			If choix_qcm>nb_choix Then choix_qcm=1
		EndIf	
		
		If (keys(4,2)=50 Or keys(47,2)=50) And player_in_control
			choix_qcm=choix_qcm-1
			If choix_qcm<1 Then choix_qcm=nb_choix
		EndIf
		
		If (keys(12,2)=50 Or keys(14,2)=50) And player_in_control
			keys(14,2)=49
			reponse=choix_qcm
		EndIf
		
		If choix_qcm<>old_choix_qcm Then Playsound2(sons_menu(1))
		
		If reponse<>0 Then choix_qcm=0
	EndIf
	Return reponse
End Function


;Coupe le texte de façon à ce qu'il défile
Function couper_texte_defilant()
	len_utilisee=0
	For t=1 To 7
		disc_ligne_bis$(t)=""
		len_restante=Floor(disc_len#-len_utilisee)
		len_utilisee=len_utilisee+Len(disc_ligne$(t))
		disc_ligne_bis$(t)=Left(disc_ligne$(t),max(0,len_restante))
	Next
End Function


;suite ; affiche la roue qui tourne ou non
;y : la position y du bord haut de la fenêtre
;defile : si le texte défile (1) ou est directement affiché (0)
;ATTENTION : si le texte ne défile pas, il est impossible de passer à la suite
;            Pour que le texte soit entièrement affiché mais passable, utiliser "disc_len#=10000" plutôt
Function fenetre_info(mess$,suite=1,defile=1,hors_jeu=0)
	If defile=1
		disc_len#=disc_len#+options#(3)*delta_frame*0.02
		If keys(12,2)=50 Or keys(1,2)=50
			If disc_len#>Len(mess$)+10
				disc_len#=0
				reponse=1
			Else
				disc_len#=Len(mess$)
			EndIf
		EndIf
	EndIf
	
	MiseEnFormeMessageDialogue(" "+Replace(mess$,"#","\n"),0)
	;couper_texte_defilant()
	If Int(options#(7)) = 1
		If mode_debug=1
			str_PJ$(0)="Fenêtre Info"
		Else
			str_PJ$(0)="Narrateur"
		EndIf
	Else
		If mode_debug=1
			str_PJ$(0)="Info"
		Else
			str_PJ$(0)="Storyteller"
		EndIf
	Endif
	AfficherDialogue(defile,hors_jeu)
	
	If disc_len#>amax
		If suite=1
			frame=reste(Int(timer_animation#*0.1),8)
			DrawImage little_wheel,screenwidth-45,screenheight-45,frame
		Else
		
		EndIf
	EndIf
	
	Return reponse
End Function

Function discussion(num_gars=1,num_face=1,id$="???",mess$="Mouahaha !#Je ne me souviens plus de mon texte !",suite=1,defile=1,hors_jeu=0)
	decalage=-100
	cas_cam=-Abs(cas_cam)	
	reponse=0
	disc_len#=disc_len#+options#(3)*delta_frame*0.02
	If keys(12,2)=50 Or keys(1,2)=50
		If disc_len#>Len(mess$)+10
			disc_len#=0
			reponse=1
		Else
			disc_len#=10000
		EndIf
	EndIf

	;mettre le texte en forme (à changer)
	MiseEnFormeMessageDialogue(" "+Replace(mess$,"#","\n"),0)
	
	;couper le texte plus tôt
	;couper_texte_defilant()

	str_PJ$(0)=id$
	AfficherDialogue(defile,hors_jeu)
	
	If disc_len#>amax
		If suite=1
			frame=reste(Int(timer_animation#*0.1),8)
			DrawImage little_wheel,screenwidth-45,screenheight-45,frame
		Else
			;mettre une flèche ?
		EndIf
	EndIf
	Return reponse
End Function

Function AfficherDialogue(defile=0,hors_jeu=0)
	;charger l'image de fond si elle existe
	If fond_animation<>0 Then DrawBlock fond_animation,0,0
	;Image de la personne qui parle
	AfficherPortrait(str_PJ$(0),31,569,hors_jeu)
	
	;Image du dialogue
	DrawImage fond_dialogue,0,364
	
	;Nom de la personne qui parle
	Color 0,0,0
	SetFont big_font
	Text 485,404,str_PJ$(0),1,1
		
	;Affichage des paroles
	SetFont middle_font	
	For t=1 To 7
		If defile=0
			Text 210,435+17*t,disc_ligne(t) ; texte fixe (version Loran)
		Else
			couper_texte_defilant()
			Text 210,435+17*t,disc_ligne_bis(t) ; texte défilant (coupé avec couper_texte_defilant())
		EndIf
	Next
	
	;petite roue d'attente (si ce n'est pas un QCM)
	If str_PJ$(2)=""
		next_button=LoadImage("sprites\Menu\bouton_corps.png") 
		DrawImage next_button , 752 , 381
		FreeImage next_button
	EndIf
End Function

Function AfficherDialogueQCM()
	
	;taille des boutons
	taille_bouton_X = 300
	taille_bouton_Y = 40
		
	;Valeur centrale des 'boutons' de bas en haut
	variableTemporaire(0)=480 ;X1
	variableTemporaire(1)=480 ;X2
	variableTemporaire(2)=480 ;X3
	variableTemporaire(3)=480 ;X4
	variableTemporaire(4)=150 ;X5
	variableTemporaire(5)=150 ;X6
	variableTemporaire(6)=150 ;X7
	variableTemporaire(7)=150 ;X8
	variableTemporaire(8)=304 ;Y1
	variableTemporaire(9)=261 ;Y2
	variableTemporaire(10)=177 ;Y3
	variableTemporaire(11)=133 ;Y4
	variableTemporaire(12)=304 ;Y5
	variableTemporaire(13)=261 ;Y6
	variableTemporaire(14)=177 ;Y7
	variableTemporaire(15)=133 ;Y8
	
	;Calcul du nombre de réponse
	Nb_reponse=0
	For t=1 To 8
		If str_PJ$(t)<>""
			Nb_reponse=Nb_reponse+1
		Else
			t=12
		EndIf
	Next
	
	;Image du QCM
	DrawImage fond_dialogueQCM,0,113
	Color 255,255,255
	
	;Bouton avec réponse
	SetFont big_font
	For t=0 To Nb_reponse-1
		DrawImage fond_BoutonQCM,variableTemporaire(t)-taille_bouton_X/2,variableTemporaire(t+8)-taille_bouton_Y/2
		Text variableTemporaire(t),variableTemporaire(t+8),str_PJ$(t+1),1,1
	Next	
	
	;petites roues de préselection
	If choix_qcm>=0;seulement s'il y a une réponse selectionnée
		frame=reste(Int(timer_animation#*0.1),8)
		DrawImage little_wheel , variableTemporaire(choix_qcm)+120, variableTemporaire(choix_qcm+8)-11, frame
		DrawImage little_wheel, variableTemporaire(choix_qcm)-140, variableTemporaire(choix_qcm+8)-11, frame
	EndIf

	;Interaction joueur
	b_SecondRow=False
	If choix_qcm>=4
		b_SecondRow=True
	EndIf
	If choix_qcm<>-10	;seulement s'il y a une réponse selectionnée
		If keys(7,2)=50 Or keys(50,2)=50;si S ou bas
			choix_qcm=choix_qcm-1
		ElseIf (keys(4,2)=50 Or keys(47,2)=50);si Z ou haut
			choix_qcm=choix_qcm+1
		ElseIf (keys(6,2)=50 Or keys(48,2)=50 Or keys(8,2)=50 Or keys(49,2)=50);si Q ou gauche ou D ou droite
			b_SecondRow=Not b_SecondRow
		ElseIf (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50);si espace ou entrée ou clic souris 
			keys(14,2)=49
			Return(True)
		EndIf	
	Else
		If (keys(4,2)=50 Or keys(47,2)=50);si Z ou haut
			choix_qcm=choix_qcm+10
		EndIf
	EndIf
	If b_SecondRow And Nb_reponse>4
		choix_qcm = choix_qcm Mod 4
		N_Modulo = Nb_reponse-4
		choix_qcm=((choix_qcm+N_Modulo) Mod N_Modulo)+4 ;Mod ne peut pas faire un modulo d'un chiffre négatif
	ElseIf choix_qcm<>-10
		N_Modulo=min(Nb_reponse,4)
		choix_qcm=((choix_qcm+N_Modulo) Mod N_Modulo) ;Mod ne peut pas faire un modulo d'un chiffre négatif
	EndIf
	
	For t=0 To Nb_reponse-1 ; s'il y a la souris sur un bouton
		If  MouseX()<(variableTemporaire(t)+taille_bouton_X/2) 
			If MouseX()>(variableTemporaire(t)-taille_bouton_X/2)
				If MouseY()<(variableTemporaire(t+8)+taille_bouton_Y/2)
					If MouseY()>(variableTemporaire(t+8)-taille_bouton_Y/2)
						choix_qcm=t
					EndIf
				EndIf
			EndIf
		EndIf
	Next

	Return(False)

End Function

Function MiseEnFormeMessageDialogue(mess$,ajout_log=1,longueur=440)
		
		;Variable remplace les %Avancement/...% 
		While (Instr(mess$,"%Avancement/")<>0)
			offset=Instr(mess$,"%Avancement/")
			old_offset=Instr(mess$,"/",offset)+1
			offset=Instr(mess$,"%",old_offset)
			If offset=0 Then RuntimeError "Error dans le script, les variables '%Avancement/' doivent finir par '%'"
			Variable$=Mid$(mess$, old_offset, offset-old_offset)
			; LBA correction bug nom : ne marche pas si le nom fini par échappé
			If Variable$="NomMajor"
				For av.avatar=Each avatar
					If av\num = 1
						VariableResultat$ =av\name$[1]
					EndIf
				Next	
			ElseIf Variable$="NomLeopold"
				For av.avatar=Each avatar
					If av\num = 2
						VariableResultat$ =av\name$[1]
					EndIf 
				Next
			ElseIf Variable$="NomAdeline"
				For av.avatar=Each avatar
					If av\num = 3
						VariableResultat$ =av\name$[1]
					EndIf 
				Next
			Else
				; DebugLog "TestLoran :"+Variable$
				;récupérer l'information
			    VariableResultat$=Trim(xmlNodeDataGet$(xmlAdvNodeFind(Variable$, xml_Avancement)))
			EndIf 
			;remplacer l'information
			mess$=Replace$(mess$, "%Avancement/"+Variable$+"%",VariableResultat$)
		Wend
		
		;Afficher le dialogue dans le journal
		If ajout_log=1 Then new_log(str_PJ$(0)+":"+mess$)
		
		;Ligne séparé par \n et de taille max 437 pixel (Max 7 lignes)
		NombreDeLigne=0
		old_offset=2 ;bug de XML (il rajoute un espace au début)
		For t=1 To 7
			offset=Instr(mess$,"\n",old_offset)
			If offset <> 0
				;DebugLog "offset : "+Str(offset)+"////old_offset : "+Str(old_offset)
				disc_ligne(t)=Mid$(mess$, old_offset, offset-old_offset)
				old_offset=offset+2 ; pour ne pas afficher le  \n
				;DebugLog "disc_ligne(t) : "+disc_ligne(t)
			ElseIf NombreDeLigne= 0 
				disc_ligne(t)=Right$(mess$, Len(mess$)-old_offset+1)
				NombreDeLigne = t
			Else
				disc_ligne(t)=""
			EndIf 
			;Retour à la ligne automatique si >= 438 pixel
			While StringWidth(disc_ligne(t)) > longueur ; tant que l'on a pas un résultat OK
				sp_offset=Instr(disc_ligne(t)," ")
				;DebugLog "offset1= "+sp_offset
				While (StringWidth (Left(disc_ligne(t),sp_offset))<longueur+1 And sp_offset<>0) ; on rajoute un mot par un mot jusqu'à que se soit OK
					sp_old_offset=sp_offset
					sp_offset=Instr(disc_ligne(t)," ",sp_old_offset+1)
					;DebugLog "offset= "+sp_offset
					;DebugLog "tailleX= "+StringWidth (Left(disc_ligne(t),sp_offset))
				Wend
				disc_ligne(t+1)=Right(disc_ligne(t),Len(disc_ligne(t))-sp_old_offset)
				disc_ligne(t)=Left(disc_ligne(t),sp_old_offset)
				t=t+1
				;DebugLog "t+1 ="+t+" offset :"+sp_old_offset
			Wend
			;DebugLog "disc_ligne( "+t+").taille :"+StringWidth(disc_ligne(t))
		Next
		
		;nico : gestion des espaces insécables (remplacer ~ par une espace, pour éviter de couper avant un : par exemple)
		For t=1 To 7
			disc_ligne(t)=Replace(disc_ligne(t),"~"," ")
		Next
		
End Function

Function AfficherPortrait(sNomPortrait$,nX,nY,hors_jeu=0)
	
	If hors_jeu=0
		For av.avatar=Each avatar
			Select av\num
				Case 1
					sNomMajor$ =av\name$[Int(options#(7))]
				Case 2
					sNomLeopold$ =av\name$[Int(options#(7))]
				Case 3
					sNomAdeline$ =av\name$[Int(options#(7))]
			End Select
		Next
	
	EndIf
	
	Select (sNomPortrait$)
		Case "Fillette"
			num_gars=5
		Case "Stale"
			num_gars=6
		Case sNomMajor$
			num_gars=2
		Case sNomAdeline$
			num_gars=3
		Case sNomLeopold$
			num_gars=4
		Case "Pillard"
			num_gars=7
		Case "inconnu"
			num_gars=8
		Case "Affiche"
			num_gars=9
		Default
			num_gars=1
	End Select	
	If disc_faces(num_gars,1)<>0 ;Mettre les images de dialogue en 120*120
		DrawBlock disc_faces(num_gars,1),nX,nY
	Else
		DebugLog sNomPortrait$+Str$(num_gars)
	EndIf

End Function


Function play_music(num,transition=1)
	num=min(max(0,num),10) ; que 10 musiques pour l'instant
	Select transition
		Case 1 ; sans transition
			StopChannel ch_bgm1
			num_bgm1=num
			ch_bgm1=0
			num_bgm2=0
			If num>0
				If musique_de_fond(num,1)=0
					musique_de_fond(num,1)=LoadSound(nom_musique_de_fond$(num))
					LoopSound musique_de_fond(num,1)
				EndIf
				ch_bgm1=PlaySound(musique_de_fond(num,1))
				vol_bgm1#=Float(musique_de_fond(num,2))*0.01
				tar_bgm1#=vol_bgm1#
				ChannelVolume ch_bgm1,vol_bgm1#*options#(5)
			EndIf
		Case 2 ; fondu enchainé (1s)
		
		
		Case 3 ; fondu "au noir" (1+1s)
		
	End Select
End Function

Function update_ch_music()

End Function

Function Playsound2(son)
	If son<>0
		ch_son=Playsound(son)
		ChannelVolume ch_son,options#(6)
	EndIf
End Function

Function draw_movie(movie)
	ratio_screen#=screenwidth/Float(screenheight)
	ratio_movie#=MovieWidth(movie)/Float(MovieHeight(movie))
	If ratio_screen#<ration_movie# ; l'écran est moins large que le film
		a=0
		c=screenwidth
		d=Floor(MovieHeight(movie)*screenwidth/Float(MovieWidth(movie)))
		b=(screenheight-d)*0.5
	Else
		b=0
		d=screenheight
		c=Floor(MovieWidth(movie)*screenheight/Float(MovieHeight(movie)))
		a=(screenwidth-c)*0.5
	EndIf

	DrawMovie(movie,a,b,c,d)

End Function


;retourne si l'avatar n°num possède la règle n°rule)
;except : ne pas compter l'équipement (avec except en binaire, donc except=7 -> ne pas compter l'équipement 1 et 2 et 3)
Function have_rule(num,rule,except=0)
	except=2*except ; décaler les bits de 1 pour rendre le code plus lisible
	good=0
	no_good=1
	For av.avatar=Each avatar
		If av\num=num
			no_good=0
			For t=1 To 15
				If av\cmpt[t]=rule Then good=good+1
			Next
			For k=1 To 3
				If av\equi[k]<>0 And retournebit(except,k)=0
				For ca.arme=Each arme
						If ca\num=av\equi[k]
							For t=1 To 8
								If ca\rules[t]=rule Then good=good+1
							Next
						EndIf
					Next
				EndIf
			Next
			If av\equi[4]<>0  And retournebit(except,4)=0
				For ar.armure=Each armure
					If ar\num=av\equi[4]
						For t=1 To 3
							If ar\rules[t]=rule Then good=good+1
						Next
					EndIf
				Next
			EndIf
			If av\equi[5]<>0 And retournebit(except,5)=0
				For boi.boiler=Each boiler
					If boi\num=av\equi[5]
						For t=1 To 3
							If boi\rules[t]=rule Then good=good+1
						Next
					EndIf
				Next
			EndIf
			For k=6 To 8
				If av\equi[k]<>0 And retournebit(except,6)=0
					For spe.special=Each special
						If spe\num=av\equi[k]
							For t=1 To 5
								If spe\rules[t]=rule Then good=good+1
							Next
						EndIf
					Next
				EndIf
			Next			
		EndIf
	Next
	If no_good=1 Then Return -3569
	Return good
End Function


; Renvoie si un avatar peut en attaquer un autre au corps à corps.
; note : la fonction ne vérifie pas si les deux groupes sont bien en train de se battre.
; si les groupes sont les mêmes, l'attaque cc est toujours possible.
Function target_valide(num_from,num_target,num_combat)
	groupe_1=0
	groupe_2=0
	If num_target=0 Then Return 0
	good=0
	For av.avatar=Each avatar
		If av\num=num_from
			; l'attaquant est-il au premier rang ?
			For gr.groupe=Each groupe
				If gr\num=av\groupe
					groupe_1=gr\num
					k=0
					For t=1 To 9
						If gr\formation[t]=num_from Then k=t
					Next
					If k<4
						good=1
						For t=1 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											good=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					ElseIf k<7
						good=1
						For t=4 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											good=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					Else
						good=1
						For t=7 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											good=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					EndIf
				EndIf
			Next
			; la cible est-elle au premier rang ? et a-t-elle encore des pvs ?
			If good<>0
				For av_t.avatar=Each avatar
					If av_t\num=num_target
						If av_t\pv[1]>0
							For gr.groupe=Each groupe
								If gr\num=av_t\groupe
									groupe_2=gr\num
									k=0
									For t=1 To 9
										If gr\formation[t]=num_target Then k=t
									Next
									If k<4
										good=1
										For t=1 To k
											If gr\formation[t]<>0 And gr\formation[t]<>num_target
												;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
												For av_o.avatar=Each avatar
													If av_o\num=gr\formation[t]
														If av_o\pv[1]>0
															good=0
														EndIf
													EndIf
												Next
											EndIf
										Next
									ElseIf k<7
										good=1
										For t=4 To k
											If gr\formation[t]<>0 And gr\formation[t]<>num_target
												;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
												For av_o.avatar=Each avatar
													If av_o\num=gr\formation[t]
														If av_o\pv[1]>0
															good=0
														EndIf
													EndIf
												Next
											EndIf
										Next
									Else
										good=1
										For t=7 To k
											If gr\formation[t]<>0 And gr\formation[t]<>num_target
												;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
												For av_o.avatar=Each avatar
													If av_o\num=gr\formation[t]
														If av_o\pv[1]>0
															good=0
														EndIf
													EndIf
												Next
											EndIf
										Next
									EndIf
								EndIf
							Next
						Else
							Return 0
						EndIf
					EndIf
				Next
			EndIf
		EndIf
	Next
	
	For combat.combat=Each combat
		If combat\num=num_combat
			If combat\phase<>2
				If have_rule(num_from,12)=0 Then good=0; swift
			EndIf
		EndIf
	Next

	if groupe_1=groupe_2
		;vérifier plus tard si ils sont à côté ?
		good=1
	EndIf

	
	Return good
End Function


Function target_valide_distance(num_from,num_target,num_combat)
	If num_target=0 Then Return 0
	
	groupe_1=0
	groupe_2=0

	good=0
	For t=1 To 3
		atd_cible(t)=0
		atd_coeff#(t)=0
	Next
	atd_contact=1
	
	For av.avatar=Each avatar
		If av\num=num_from
			; l'attaquant est-il au premier rang ?
			For gr.groupe=Each groupe
				If gr\num=av\groupe
					groupe_1=gr\num
					k=0
					For t=1 To 9
						If gr\formation[t]=num_from Then k=t
					Next
					If k<4
						For t=1 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											atd_contact=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					ElseIf k<7
						For t=4 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											atd_contact=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					Else
						For t=7 To k
							If gr\formation[t]<>0 And gr\formation[t]<>num_from
								;est-ce que l'obstacle a encore des pvs ? est-il sujet à une règle spéciale
								For av_o.avatar=Each avatar
									If av_o\num=gr\formation[t]
										If av_o\pv[1]>0
											atd_contact=0
										EndIf
									EndIf
								Next
							EndIf
						Next
					EndIf
				EndIf
			Next
			; la cible est-elle au premier rang ? et a-t-elle encore des pvs ?
			cible_inter=0
			coeff#=0
			For av_t.avatar=Each avatar
				If av_t\num=num_target
					If av_t\pv[1]>0
						For gr.groupe=Each groupe
							If gr\num=av_t\groupe
								groupe_2=gr\num
								k=0
								For t=1 To 9
									If gr\formation[t]=num_target Then k=t
								Next
								If k<4
									q=1
								ElseIf k<7
									q=4
								Else
									q=7
								EndIf
								For t=q To k
									If gr\formation[t]<>0 
										If gr\formation[t]<>num_target
											For av_o.avatar=Each avatar
												If av_o\num=gr\formation[t]
													If av_o\pv[1]>0 ; est-ce que l'obstacle a encore des pvs ?
														cible_inter=cible_inter+1
														atd_cible(cible_inter)=av_o\num
														atd_coeff#(cible_inter)=coeff#+0.3*(1-coeff#)
														coeff#=atd_coeff#(cible_inter)
													EndIf
												EndIf
											Next
										Else
											cible_inter=cible_inter+1
											atd_cible(cible_inter)=num_target
											atd_coeff#(cible_inter)=1
										EndIf
									EndIf
								Next
							EndIf
						Next
					Else
						Return 0
					EndIf
				EndIf
			Next
		EndIf
	Next
	
	For combat.combat=Each combat
		If combat\num=num_combat
			If combat\phase=1 Then atd_contact=0
		EndIf
	Next

	if groupe_1=groupe2
		for t=1 to 3
			atd_cible(t)=0
			atd_coeff#(t)=0
		next
		atd_cible(1)=num_target
		atd_coeff#(1)=1
	EndIf
									
	Return 1
End Function

;précalcule les chances de toucher une cible dans un combat (pour afficher)
;dist pour si l'attaque est à distance
;visee pour si l'attaque est visée
;rafale pour le nombre d'attaque de bangbangbang si c'est le cas (0 pour une attaque normale)
Function calcule_stat_cible(num_att,num_target,num_combat,dist=0,visee=0,rafale=0)
	stat_cible$(1)=""
	stat_cible$(2)=""
	stat_cible$(3)=""
	If num_target<>0 And num_att<>0
		For av.avatar=Each avatar
			If av\num=num_att
				For av_t.avatar=Each avatar
					If av_t\num=num_target
						;checker la posibilitée de toucher
						good=0
						If dist=1
							good=1
						ElseIf target_valide(num_att,num_target,num_combat)
							good=1
						EndIf
						If good=1 And av_t\pv[1]>0
							;calcul de l'attaque
								;chercher l'arme équipée
							compt_att=0
							capac_att=0; (1 mains nues -ATL-, 2 ATL, 3 ATH, 4 ATD, 5 arme à feu -ATD-)
							If av\equi[1]<>0
								; chercher la compétence utilisée par l'arme
								For ca.arme=Each arme
									If ca\num=av\equi[1]
										compt_att=ca\cat ;(épée, hache, ...)
										capac_att=ca\classe ;(mains nues, légère, lourde, distance, à feu)
										scr_dgts=ca\scr_degats
										;transformer {1,2,3,4,5} en {1,1,2,3,3}
										style_att=min(3,max(1,capac_att-1))
										scr_dgts=ca\scr_degats
										bonus_att=bonus_att+ca\att[style_att] ; bonus d'attaque de l'arme
										bonus_deg=bonus_deg+av\deg[style_att] ; bonus de dégâts de l'arme
									EndIf
								Next
							Else ; mains nues
								compt_att=1
								capac_att=1
								style_att=1
							EndIf
							;bonus dus aux compétences
							For t=1 To 15
								If av\cmpt[t]=1 And compt_att=1 Then bonus_att=bonus_att+2 ; Mains nues +2
								If av\cmpt[t]=4 And compt_att=8 Then bonus_att=bonus_att+3 ; Pistolero
								If av\cmpt[t]=15 And compt_att=9 Then bonus_att=bonus_att+3 ; Spé fusil (bullet)
								If av\cmpt[t]=9 And compt_att=10 Then bonus_att=bonus_att+3 ; Spé shotgun
								If av\cmpt[t]=7 And compt_att=9 And mult_att=0 Then bonus_att=bonus_att+3 ; Sniper
								If av\cmpt[t]=10 And (compt_att=2 Or compt_att=3) Then bonus_att=bonus_att+2 ; Epée +2
								If av\cmpt[t]=13 And (compt_att=6 Or compt_att=7) Then bonus_att=bonus_att+2 ; Hache +2
								If av\cmpt[t]=11 And (compt_att=4 Or compt_att=5) Then bonus_att=bonus_att+2 ; Lance +2
							Next
							;bonus/malus dus aux états
							calcul_bonus_equi(av\num)
							bonus_att=bonus_att-calcul_encombrement(av\num)-5*visee+bonus_equi(style_att)
							If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then bonus_att=bonus_att-2
							
							;calcul de l'AT(lhd)
							attaque=av\att[style_att]+bonus_att

							;calcul de la défense
							;chercher l'arme équipée
							compt_def=0
							capac_def=0; (1 DCL, 2 DCH, 3 ESQ)
							If av_t\equi[1]<>0
								; chercher la compétence utilisée par l'arme
								For ca.arme=Each arme
									If ca\num=av_t\equi[1]
										bonus_def=bonus_def+ca\def[style_att]
										compt_def=ca\classe
										capac_def=ca\cat
									EndIf
								Next
							Else ; mains nues
								compt_def=1
								capac_def=1
							EndIf
							;bonus dus aux compétences
							Select compt_def
								Case 1
									For t=1 To 15
										If av_t\cmpt[t]=1 And compt_def=1 Then bonus_def=bonus_def+2 ; mains nues +2
									Next
							End Select
							;bonus/malus dus aux états
							calcul_bonus_equi(av_t\num)
							bonus_def=bonus_def+av_t\defense-calcul_encombrement(av_t\num)+bonus_equi(3+style_att)
							If av_t\pv[1]<av_t\pv[2]*LIMITE_BLESSURE# Then bonus_def=bonus_def-2
																																			
							;calcul de l'DC(lhe)
							defense=av_t\def[style_att]+bonus_def
							
							chance_de_toucher=max(1,min(19,20-(defense-attaque)))*5
							stat_cible$(1)=chance_de_toucher+"%"
							
							;calcul de l'armure
							If av_t\equi[4]<>0
								For ar.armure=Each armure
									If ar\num=av_t\equi[4]
										For t=1 To 3
											armure_temp#(t)=ar\val#[t]
										Next
									EndIf
								Next
							Else
								armure_temp#(1)=0
								armure_temp#(2)=0
								armure_temp#(3)=0
							EndIf

							;calcul des dégâts
							;Attaque sournoise
							If have_rule(av\num,14) ; attaque sournoise
								sournoise=max(0,-av_t\defense*0.5)*ATT_SOURNOISE
							EndIf
							calcul_bonus_equi(av\num)
							;min
							dgts_min#=degats_armes#(scr_dgts,av\num,av_t\num,1)+av\deg[style_att]+bonus_equi(6+style_att)
							dgts_min#=max((dgts_min#-armure_temp#(1+visee))*av_t\faiblesse#[1+visee],0)+max(0,min(sournoise,sournoise+dgts_min#-armure_temp#(1+visee)))
							;max
							dgts_max#=degats_armes#(scr_dgts,av\num,av_t\num,2)+av\deg[style_att]+bonus_equi(6+style_att)
							dgts_max#=max((dgts_max#-armure_temp#(1+visee))*av_t\faiblesse#[1+visee],0)+max(0,min(sournoise,sournoise+dgts_max#-armure_temp#(1+visee)))
							
							stat_cible$(2)=Int(dgts_min#)+" - "+Int(dgts_max#)
							If capac_att=1
								stat_cible$(2)="2 x "+stat_cible$(2)
							ElseIf rafale>0
								stat_cible$(2)=rafale+" x "+stat_cible$(2)
							EndIf
						Else
							If Int(options#(7)) = 1
								stat_cible$(1)="Non"
								stat_cible$(2)="Attaquable"
							Else
								stat_cible$(1)="Not"
								stat_cible$(2)="Attackable"
							Endif
							
						EndIf
					EndIf
				Next
			EndIf
		Next
	EndIf
End Function

Function calcul_bonus_equi(av_num)
	For t=1 to 11
		bonus_equi(t)=0;5-t  ; test pour l'affichage
	Next
	For av.avatar = Each avatar
		If av\num=av_num
			;armes
			For t=1 To 3
				If av\equi[t]<>0
					For arme.arme= Each arme
						If arme\num=av\equi[t]
							If t=1 ; arme équipée
								bonus_equi(1)=bonus_equi(1)+arme\att[1]
								bonus_equi(2)=bonus_equi(2)+arme\def[1]
								bonus_equi(3)=bonus_equi(3)+arme\deg[1]
								bonus_equi(4)=bonus_equi(4)+arme\att[2]
								bonus_equi(5)=bonus_equi(5)+arme\def[2]
								bonus_equi(6)=bonus_equi(6)+arme\deg[2]
								bonus_equi(7)=bonus_equi(7)+arme\att[3]
								bonus_equi(8)=bonus_equi(8)+arme\def[3]
								bonus_equi(9)=bonus_equi(9)+arme\deg[3]
								bonus_equi(10)=bonus_equi(10)+arme\pv
								bonus_equi(11)=bonus_equi(11)+arme\init
							Else ; les armes non équipées ne sont pas comptées, sauf les PVs (+ Init ?)
								bonus_equi(10)=bonus_equi(10)+arme\pv
							EndIf
							;encombrement
							encombrement=0
							For u=1 to 8
								If arme\rules[u]<154 And arme\rules[u]>149
									encombrement=max(encombrement,arme\rules[u]-149)
								EndIf
							Next
							bonus_equi(1)=bonus_equi(1)-encombrement
							bonus_equi(2)=bonus_equi(2)-encombrement
							bonus_equi(4)=bonus_equi(4)-encombrement
							bonus_equi(5)=bonus_equi(5)-encombrement
							bonus_equi(7)=bonus_equi(7)-encombrement
							bonus_equi(8)=bonus_equi(8)-encombrement
						EndIf
					Next
				EndIf
			Next
			;armure
			If av\equi[4]<>0
				For armure.armure = Each armure
					If armure\num=av\equi[4]
						bonus_equi(1)=bonus_equi(1)+armure\att[1]
						bonus_equi(2)=bonus_equi(2)+armure\def[1]
						bonus_equi(3)=bonus_equi(3)+armure\deg[1]
						bonus_equi(4)=bonus_equi(4)+armure\att[2]
						bonus_equi(5)=bonus_equi(5)+armure\def[2]
						bonus_equi(6)=bonus_equi(6)+armure\deg[2]
						bonus_equi(7)=bonus_equi(7)+armure\att[3]
						bonus_equi(8)=bonus_equi(8)+armure\def[3]
						bonus_equi(9)=bonus_equi(9)+armure\deg[3]
						bonus_equi(10)=bonus_equi(10)+armure\pv
						bonus_equi(11)=bonus_equi(11)+armure\init
					EndIf
				Next
			EndIf
			;chaudière
			If av\equi[5]<>0
				For boi.boiler = Each boiler
					If boi\num=av\equi[5]
						bonus_equi(1)=bonus_equi(1)+boi\att[1]
						bonus_equi(2)=bonus_equi(2)+boi\def[1]
						bonus_equi(3)=bonus_equi(3)+boi\deg[1]
						bonus_equi(4)=bonus_equi(4)+boi\att[2]
						bonus_equi(5)=bonus_equi(5)+boi\def[2]
						bonus_equi(6)=bonus_equi(6)+boi\deg[2]
						bonus_equi(7)=bonus_equi(7)+boi\att[3]
						bonus_equi(8)=bonus_equi(8)+boi\def[3]
						bonus_equi(9)=bonus_equi(9)+boi\deg[3]
						bonus_equi(10)=bonus_equi(10)+boi\pv
						bonus_equi(11)=bonus_equi(11)+boi\init
					EndIf
				Next
			EndIf
			;spéciaux
			For t=6 to 8
				If av\equi[t]<>0
					For spe.special = Each special
						If spe\num=av\equi[t]
							bonus_equi(1)=bonus_equi(1)+spe\att[1]
							bonus_equi(2)=bonus_equi(2)+spe\def[1]
							bonus_equi(3)=bonus_equi(3)+spe\deg[1]
							bonus_equi(4)=bonus_equi(4)+spe\att[2]
							bonus_equi(5)=bonus_equi(5)+spe\def[2]
							bonus_equi(6)=bonus_equi(6)+spe\deg[2]
							bonus_equi(7)=bonus_equi(7)+spe\att[3]
							bonus_equi(8)=bonus_equi(8)+spe\def[3]
							bonus_equi(9)=bonus_equi(9)+spe\deg[3]
							bonus_equi(10)=bonus_equi(10)+spe\pv
							bonus_equi(11)=bonus_equi(11)+spe\init
						EndIf
					Next
				EndIf
			Next
		EndIf
	Next
End Function

Function aff_info_equipement(num_equi,endroit$="")
	name$=""
	cat$=""
	description$=""
	effet$=""
	charge$=""
	cadence=0
	icone=0
	nb_rules=1
	For t=1 To 8
		rules_description$(t)=""
	Next
	
	Select Int(options#(7))
		Case 1										
			If num_equi>0
				If num_equi<100
					For arme.arme=Each arme
						If arme\num=num_equi
							name$=arme\name$[Int(options#(7))]
							Select arme\cat
								Case 1
									cat$="Mains nues"
								Case 2
									cat$="Epée légère"
								Case 3
									cat$="Epée lourde"
								Case 4
									cat$="Lance légère"
								Case 5
									cat$="Lance lourde"
								Case 6
									cat$="Hache/Masse légère"
								Case 7
									cat$="Hache/Masse lourde"
								Case 8
									cat$="Pistolet"
									cadence=arme\cadence
								Case 9
									cat$="Fusil/PM"
									cadence=arme\cadence
								Case 10
									cat$="Shotgun (fusil âme lisse)"
									cadence=arme\cadence
								Default
									cat$="Inconnue"
							End Select
							description$=arme\description$[Int(options#(7))]
							effet$="Dégats ("+arme\degats$+")"
							rules_description$(1)="Aide à faire des dégâts pendant les combats."
							nb_rules=1
							If cadence>1
								effet$=effet$+",#Rafale ("+cadence+" tirs)"
								rules_description$(2)="L'arme est assez rapide pour tirer plusieurs fois par tour."
								nb_rules=2
							EndIf
							For k=1 To 6
								If arme\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=arme\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											If rules\num=101 Then effet$=effet$+" ("+arme\charge+")"
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If arme\att[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Attaque "+a_str$+" "+signed_str$(arme\att[t])
									nb_rules=nb_rules+1
									If arme\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(arme\att[t])+" attaque "+a_str$+" avec cette arme."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If arme\def[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Défense "+a_str$+" "+signed_str$(arme\def[t])
									nb_rules=nb_rules+1
									If arme\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(arme\def[t])+" en défense contre les armes "+c_Str$+"."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If arme\deg[t]<>0
									Select t
										Case 1
											a_str$="légers":c_str$="légère"
										Case 2
											a_str$="lourds":c_str$="lourde"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Dégâts "+a_str$+" "+signed_str$(arme\deg[t])
									nb_rules=nb_rules+1
									If arme\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(arme\deg[t])+" aux dégâts "+a_str$+" avec cette arme."
								EndIf
							Next
							If arme\pv<>0
								If arme\pv>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#PV "+signed_str$(arme\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(arme\pv)+" aux Points de Vie du porteur."
							EndIf
							If arme\init<>0
								If arme\init>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(arme\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(arme\init)+" en Initiative."
							EndIf
							icone=arme\icone[2]
							stat_caps=arme\caps
							stat_junk=arme\junk
						EndIf
					Next
				ElseIf num_equi<150
					For armure.armure=Each armure
						If armure\num=num_equi
							name$=armure\name$[Int(options#(7))]
							cat$="Armure"
							description$=armure\description$[Int(options#(7))]
							effet$="Protection ("+Int(armure\val#[1])+" / "+Int(armure\val#[2])+" / "+Int(armure\val#[3])+")"
							rules_description$(1)="Réduit les dégâts pendant les combats."
							nb_rules=1
							For k=1 To 3
								If armure\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=armure\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If armure\att[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Attaque "+a_str$+" "+signed_str$(armure\att[t])
									nb_rules=nb_rules+1
									If armure\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(armure\att[t])+" attaque pour toutes les armes "+c_str$+"."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If armure\def[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Défense "+a_str$+" "+signed_str$(armure\def[t])
									nb_rules=nb_rules+1
									If armure\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(armure\def[t])+" en défense contre les armes "+c_Str$+"."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If armure\deg[t]<>0
									Select t
										Case 1
											a_str$="légers":c_str$="légère"
										Case 2
											a_str$="lourds":c_str$="lourde"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Dégâts "+a_str$+" "+signed_str$(armure\deg[t])
									nb_rules=nb_rules+1
									If armure\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(armure\deg[t])+" aux dégâts pour toutes les armes "+c_str$+"."
								EndIf
							Next
							icone=armure\icone[2]
							If armure\pv<>0
								If armure\pv>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#PV "+signed_str$(armure\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(armure\pv)+" aux Points de Vie du porteur."
							EndIf
							If armure\init<>0
								If armure\init>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(armure\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(armure\init)+" en Initiative."
							EndIf
							stat_caps=armure\caps
							stat_junk=armure\junk
						EndIf
					Next
				ElseIf num_equi<200
					For boiler.boiler=Each boiler
						If boiler\num=num_equi
							name$=boiler\name$[Int(options#(7))]
							cat$="Chaudière"
							description$=boiler\description$[Int(options#(7))]
							effet$="Stockage de Vapeur ("+boiler\capacite+")"
							rules_description$(1)="Permet d'avoir de la vapeur à disposition en combat."
							nb_rules=1
							For k=1 To 3
								If boiler\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=boiler\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]						
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If boiler\att[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Attaque "+a_str$+" "+signed_str$(boiler\att[t])
									nb_rules=nb_rules+1
									If boiler\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(boiler\att[t])+" attaque pour toutes les armes "+c_str$+"."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If boiler\def[t]<>0
									Select t
										Case 1
											a_str$="légère":c_str$="légères"
										Case 2
											a_str$="lourde":c_str$="lourdes"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Défense "+a_str$+" "+signed_str$(boiler\def[t])
									nb_rules=nb_rules+1
									If boiler\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(boiler\def[t])+" en défense contre les armes "+c_Str$+"."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If boiler\deg[t]<>0
									Select t
										Case 1
											a_str$="légers":c_str$="légère"
										Case 2
											a_str$="lourds":c_str$="lourde"
										Case 3
											a_str$="à distance"::c_str$="à distance"
									End Select
									effet$=effet$+",#Dégâts "+a_str$+" "+signed_str$(boiler\deg[t])
									nb_rules=nb_rules+1
									If boiler\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Malus"
									EndIf
									rules_description$(nb_rules)=b_str$+" de "+signed_str$(boiler\deg[t])+" aux dégâts pour toutes les armes "+c_str$+"."
								EndIf
							Next
							If boiler\pv<>0
								If boiler\pv>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#PV "+signed_str$(boiler\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(boiler\pv)+" aux Points de Vie du porteur."
							EndIf
							If boiler\init<>0
								If boiler\init>0
									b_str$="Bonus"
								Else
									b_str$="Malus"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(boiler\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" de "+signed_str$(boiler\init)+" en Initiative."
							EndIf
							icone=boiler\icone[2]
							stat_caps=boiler\caps
							stat_junk=boiler\junk
						EndIf
					Next
				Else
					For special.special=Each special
						If special\num=num_equi
							name$=special\name$[Int(options#(7))]
							cat$="GearBot"
							description$=special\description$[Int(options#(7))]
							If num=201 Or num=202
								modele$="Aegis"
							EndIf
							effet$="Robot allié autonome (modèle "+modele$+")"
							rules_description$(1)="Permet d'appeler un robot allié sur le champ de bataille."
							nb_rules=1
							For k=1 To 3
								If special\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=special\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							icone=special\icone[2]
							stat_caps=special\caps
							stat_junk=special\junk
						EndIf
					Next
				EndIf
			EndIf
			
			;afficher les infos collectées
			Color 5,5,5
			Rect 490,115,300,440,0
			
			If name$<>""
				Rect 575-2,130-2,130+4,75+4,0
				If icone<>0
					DrawBlock icone,575,130
				Else
					DrawBlock non_icone2,575,130
				EndIf
				SetFont little_font
				Text 500,225,"Nom : "+name$
				Text 500,242,"Catégorie : "+cat$
				If endroit$="Poubelle"
					Text 500,259,"Valeur à la revente : "+Str(Int(Ceil(stat_caps/Float(COEFF_MARCHAND))))+" caps"
					Text 500,276,"Recyclage : "+stat_junk+" junks"
					decal=34
				ElseIf endroit$="Acheter"
					Text 500,259,"Prix : "+Str(stat_caps*COEFF_MARCHAND)+" caps"
					decal=17
				ElseIf endroit$="Vendre"
					Text 500,259,"Valeur à la revente : "+Str(Int(Ceil(stat_caps/Float(COEFF_MARCHAND))))+" caps"
					decal=17
				Else
					decal=0
				EndIf
				
				For t=1 To 7
					disc_ligne(t)=""
				Next
				mess$="Description : "+description$
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
					Text 500,(250+15*t+decal),disc_ligne$(t)
				Next
				
				For t=1 To nb_rules
					disc_ligne(t)=""
				Next
				mess$="Effets : "+effet$
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
				pos_effets=351+decal
				For t=1 To nb_rules
					disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
					Text 500,(pos_effets+17*t),disc_ligne$(t)
					If MouseX()>500 And MouseY()>pos_effets+17*t And MouseY()<pos_effets+17*(t+1) Then aide_contextuelle$(Int(options#(7)))=rules_description$(t)
				Next
			EndIf
		Case 2
			If num_equi>0
				If num_equi<100
					For arme.arme=Each arme
						If arme\num=num_equi
							name$=arme\name$[Int(options#(7))]
							Select arme\cat
								Case 1
									cat$="Unarmed Strike"
								Case 2
									cat$="Light Sword"
								Case 3
									cat$="Heavy Sword"
								Case 4
									cat$="Light Spear"
								Case 5
									cat$="Heavy Spear"
								Case 6
									cat$="Light Axe"
								Case 7
									cat$="Heavy Axe"
								Case 8
									cat$="Handgun"
									cadence=arme\cadence
								Case 9
									cat$="Battle rifle"
									cadence=arme\cadence
								Case 10
									cat$="Shotgun"
									cadence=arme\cadence
								Default
									cat$="Unknown"
							End Select
							description$=arme\description$[Int(options#(7))]
							effet$="Damages ("+arme\degats$+")"
							rules_description$(1)="Deals damages"
							nb_rules=1
							If cadence>1
								effet$=effet$+",#Burst first ("+cadence+" tirs)"
								rules_description$(2)="The weapon is fast enough to fire multiple times per turns."
								nb_rules=2
							EndIf
							For k=1 To 6
								If arme\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=arme\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											If rules\num=101 Then effet$=effet$+" ("+arme\charge+")"
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If arme\att[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Attack "+a_str$+" "+signed_str$(arme\att[t])
									nb_rules=nb_rules+1
									If arme\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(arme\att[t])+" in "+a_str$+" Attack Rating with this weapon."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If arme\def[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Defense "+a_str$+" "+signed_str$(arme\def[t])
									nb_rules=nb_rules+1
									If arme\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(arme\def[t])+" in Defense Rating against "+c_Str$+" weapons."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If arme\deg[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Damages "+a_str$+" "+signed_str$(arme\deg[t])
									nb_rules=nb_rules+1
									If arme\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(arme\deg[t])+" to "+a_str$+" damages with this weapon."
								EndIf
							Next
							If arme\pv<>0
								If arme\pv>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#HP "+signed_str$(arme\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(arme\pv)+" to the maximum Hit Points of the user."
							EndIf
							If arme\init<>0
								If arme\init>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(arme\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(arme\init)+" to Initiative."
							EndIf
							icone=arme\icone[2]
							stat_caps=arme\caps
							stat_junk=arme\junk
						EndIf
					Next
				ElseIf num_equi<150
					For armure.armure=Each armure
						If armure\num=num_equi
							name$=armure\name$[Int(options#(7))]
							cat$="Armour"
							description$=armure\description$[Int(options#(7))]
							effet$="Protection ("+Int(armure\val#[1])+" / "+Int(armure\val#[2])+" / "+Int(armure\val#[3])+")"
							rules_description$(1)="Reduces damage during combat."
							nb_rules=1
							For k=1 To 3
								If armure\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=armure\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If armure\att[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Attaque "+a_str$+" "+signed_str$(armure\att[t])
									nb_rules=nb_rules+1
									If armure\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(armure\att[t])+" to Attack Rating for all "+c_str$+" weapons."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If armure\def[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Défense "+a_str$+" "+signed_str$(armure\def[t])
									nb_rules=nb_rules+1
									If armure\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(armure\def[t])+" to Defense Rating against all "+c_Str$+" weapons."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If armure\deg[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Dégâts "+a_str$+" "+signed_str$(armure\deg[t])
									nb_rules=nb_rules+1
									If armure\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(armure\deg[t])+" to damages dealt by the user with all "+c_str$+" weapons."
								EndIf
							Next
							icone=armure\icone[2]
							If armure\pv<>0
								If armure\pv>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#HP "+signed_str$(armure\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(armure\pv)+" to the maximum Hit Points of the user."
							EndIf
							If armure\init<>0
								If armure\init>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(armure\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(armure\init)+" to Initiative."
							EndIf
							stat_caps=armure\caps
							stat_junk=armure\junk
						EndIf
					Next
				ElseIf num_equi<200
					For boiler.boiler=Each boiler
						If boiler\num=num_equi
							name$=boiler\name$[Int(options#(7))]
							cat$="Boiler"
							description$=boiler\description$[Int(options#(7))]
							effet$="Steam pressure ("+boiler\capacite+")"
							rules_description$(2)="Produce steam, to use steam-powered equipment in battle."
							nb_rules=1
							For k=1 To 3
								If boiler\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=boiler\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]						
										EndIf
									Next
								EndIf
							Next
							;bonus/malus attaque
							For t=1 To 3
								If boiler\att[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Attaque "+a_str$+" "+signed_str$(boiler\att[t])
									nb_rules=nb_rules+1
									If boiler\att[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(boiler\att[t])+" to Attack Rating for all "+c_str$+" weapons."
								EndIf
							Next
							;bonus/malus défense
							For t=1 To 3
								If boiler\def[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Défense "+a_str$+" "+signed_str$(boiler\def[t])
									nb_rules=nb_rules+1
									If boiler\def[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(boiler\def[t])+" to Defense Rating against all "+c_Str$+" weapons."
								EndIf
							Next
							;bonus/malus dégâts
							For t=1 To 3
								If boiler\deg[t]<>0
									Select t
										Case 1
											a_str$="light":c_str$="light"
										Case 2
											a_str$="heavy":c_str$="heavy"
										Case 3
											a_str$="ranged"::c_str$="ranged"
									End Select
									effet$=effet$+",#Dégâts "+a_str$+" "+signed_str$(boiler\deg[t])
									nb_rules=nb_rules+1
									If boiler\deg[t]>0
										b_str$="Bonus"
									Else
										b_str$="Penalty"
									EndIf
									rules_description$(nb_rules)=b_str$+" of "+signed_str$(boiler\deg[t])+" to damages dealt by the user with all "+c_str$+" weapons."
								EndIf
							Next
							If boiler\pv<>0
								If boiler\pv>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#HP "+signed_str$(boiler\pv)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(boiler\pv)+" to the maximum Hit Points of the user."
							EndIf
							If boiler\init<>0
								If boiler\init>0
									b_str$="Bonus"
								Else
									b_str$="Penalty"
								EndIf
								effet$=effet$+",#Initiative "+signed_str$(boiler\init)
								nb_rules=nb_rules+1
								rules_description$(nb_rules)=b_str$+" of "+signed_str$(boiler\init)+" to Initiative."
							EndIf
							icone=boiler\icone[2]
							stat_caps=boiler\caps
							stat_junk=boiler\junk
						EndIf
					Next
				Else
					For special.special=Each special
						If special\num=num_equi
							name$=special\name$[Int(options#(7))]
							cat$="GearBot"
							description$=special\description$[Int(options#(7))]
							If num=201 Or num=202
								modele$="Aegis"
							EndIf
							effet$="Allied Autonomous Robot (model "+modele$+")"
							rules_description$(2)="The user can call a friendly robot to the battlefield."
							nb_rules=1
							For k=1 To 3
								If special\rules[k]<>0
									For rules.rules=Each rules
										If rules\num=special\rules[k]
											effet$=effet$+",#"+rules\name$[Int(options#(7))]
											nb_rules=nb_rules+1
											rules_description$(nb_rules)=rules\description$[Int(options#(7))]
										EndIf
									Next
								EndIf
							Next
							icone=special\icone[2]
							stat_caps=special\caps
							stat_junk=special\junk
						EndIf
					Next
				EndIf
			EndIf
			
			;afficher les infos collectées
			Color 5,5,5
			Rect 490,115,300,440,0
			
			If name$<>""
				Rect 575-2,130-2,130+4,75+4,0
				If icone<>0
					DrawBlock icone,575,130
				Else
					DrawBlock non_icone2,575,130
				EndIf
				SetFont little_font
				Text 500,225,"Name: "+name$
				Text 500,242,"Class: "+cat$
				If endroit$="Poubelle"
					Text 500,259,"Resell value: "+Str(Int(Ceil(stat_caps/Float(COEFF_MARCHAND))))+" caps"
					Text 500,276,"Recycling value: "+stat_junk+" junks"
					decal=34
				ElseIf endroit$="Acheter"
					Text 500,259,"Price: "+Str(stat_caps*COEFF_MARCHAND)+" caps"
					decal=17
				ElseIf endroit$="Vendre"
					Text 500,259,"Resell value: "+Str(Int(Ceil(stat_caps/Float(COEFF_MARCHAND))))+" caps"
					decal=17
				Else
					decal=0
				EndIf
				
				For t=1 To 7
					disc_ligne(t)=""
				Next
				mess$="Description: "+description$
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
					Text 500,(250+15*t+decal),disc_ligne$(t)
				Next
				
				For t=1 To nb_rules
					disc_ligne(t)=""
				Next
				mess$="Effects: "+effet$
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
				pos_effets=351+decal
				For t=1 To nb_rules
					disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
					Text 500,(pos_effets+17*t),disc_ligne$(t)
					If MouseX()>500 And MouseY()>pos_effets+17*t And MouseY()<pos_effets+17*(t+1) Then aide_contextuelle$(2)=rules_description$(t)
				Next
			EndIf
	End Select
End Function

; Ecrit le texte tapé à la suite de du string mess$.
; Utilise le clavier AZERTY standard (le mien en l'occurence)
Function input_text$(mess$)
	;suppression de lettre
	If keys(27,2)=50 And mess$<>"" Then mess$=Left(mess$,Len(mess$)-1)
	If KeyDown(keys(27,1)) And keys(27,2)=0 And mess$<>"" Then mess$=Left(mess$,Len(mess$)-1)
	
	If keys(88,2)=50 Then mess$=mess$+"²" ; semble ne pas fonctionner mais tant pis
	If keys(15,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"1"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+""
		Else
			mess$=mess$+"&"
		EndIf
	EndIf
	If keys(16,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"2"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"~"
		Else
			mess$=mess$+"é"
		EndIf
	EndIf
	If keys(17,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"3"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"#"
		Else
			mess$=mess$+Chr$(34)
		EndIf
	EndIf
	If keys(18,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"4"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"{"
		Else
			mess$=mess$+"'"
		EndIf
	EndIf
	If keys(19,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"5"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"["
		Else
			mess$=mess$+"("
		EndIf
	EndIf
	If keys(20,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"6"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"|"
		Else
			mess$=mess$+"-"
		EndIf
	EndIf
	If keys(21,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"7"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"`"
		Else
			mess$=mess$+"è"
		EndIf
	EndIf
	If keys(22,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"8"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"\"
		Else
			mess$=mess$+"_"
		EndIf
	EndIf
	If keys(23,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"9"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"^"
		Else
			mess$=mess$+"ç"
		EndIf
	EndIf
	If keys(24,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"0"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"@"
		Else
			mess$=mess$+"à"
		EndIf
	EndIf
	If keys(25,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"°"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"]"
		Else
			mess$=mess$+")"
		EndIf
	EndIf
	If keys(26,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"+"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"}"
		Else
			mess$=mess$+"="
		EndIf
	EndIf

	If keys(3,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"A"
			Else
				mess$=mess$+"a"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ä"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ä"
				EndIf
			ElseIf Right(mess$,1)="^"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Â"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"â"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"A"
				Else
					mess$=mess$+"a"
				EndIf
			EndIf		
		EndIf
	EndIf
	If keys(4,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"Z"
		Else
			mess$=mess$+"z"
		EndIf
	EndIf
	If keys(5,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"E"
			Else
				mess$=mess$+"e"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ë"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ë"
				EndIf
			ElseIf Right(mess$,1)="^"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ê"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ê"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"E"
				Else
					mess$=mess$+"e"
				EndIf
			EndIf		
		EndIf

	EndIf
	If keys(34,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"R"
		Else
			mess$=mess$+"r"
		EndIf
	EndIf
	If keys(89,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"T"
		Else
			mess$=mess$+"t"
		EndIf
	EndIf
	If keys(51,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"Y"
			Else
				mess$=mess$+"y"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"Y"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ÿ"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"Y"
				Else
					mess$=mess$+"y"
				EndIf
			EndIf		
		EndIf

	EndIf
	If keys(52,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"U"
			Else
				mess$=mess$+"u"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ü"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ü"
				EndIf
			ElseIf Right(mess$,1)="^"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Û"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"û"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"U"
				Else
					mess$=mess$+"u"
				EndIf
			EndIf		
		EndIf

	EndIf
	If keys(53,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"I"
			Else
				mess$=mess$+"i"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ï"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ï"
				EndIf
			ElseIf Right(mess$,1)="^"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Î"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"î"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"I"
				Else
					mess$=mess$+"i"
				EndIf
			EndIf		
		EndIf

	EndIf
	If keys(54,2)=50
		If mess$=""
			If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
				mess$=mess$+"O"
			Else
				mess$=mess$+"o"
			EndIf
		Else
			If Right(mess$,1)="¨"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ö"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ö"
				EndIf
			ElseIf Right(mess$,1)="^"
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=Left(mess$,Len(mess$)-1)+"Ô"
				Else
					mess$=Left(mess$,Len(mess$)-1)+"ô"
				EndIf
			Else
				If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
					mess$=mess$+"O"
				Else
					mess$=mess$+"o"
				EndIf
			EndIf		
		EndIf

	EndIf
	If keys(55,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"P"
		Else
			mess$=mess$+"p"
		EndIf
	EndIf
	If keys(56,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"¨"
		Else
			mess$=mess$+"^"
		EndIf
	EndIf
	If keys(57,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"£"
		ElseIf KeyDown(keys(71,1))
			mess$=mess$+"¤"
		Else
			mess$=mess$+"$"
		EndIf
	EndIf
	
	If keys(6,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"Q"
		Else
			mess$=mess$+"q"
		EndIf
	EndIf
	If keys(7,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"S"
		Else
			mess$=mess$+"s"
		EndIf
	EndIf
	If keys(8,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"D"
		Else
			mess$=mess$+"d"
		EndIf
	EndIf
	If keys(29,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"F"
		Else
			mess$=mess$+"f"
		EndIf
	EndIf
	If keys(30,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"G"
		Else
			mess$=mess$+"g"
		EndIf
	EndIf
	If keys(31,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"H"
		Else
			mess$=mess$+"h"
		EndIf
	EndIf
	If keys(32,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"J"
		Else
			mess$=mess$+"j"
		EndIf
	EndIf
	If keys(33,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"K"
		Else
			mess$=mess$+"k"
		EndIf
	EndIf
	If keys(58,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"L"
		Else
			mess$=mess$+"l"
		EndIf
	EndIf
	If keys(59,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"M"
		Else
			mess$=mess$+"m"
		EndIf
	EndIf
	If keys(60,2)=50
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"%"
		Else
			mess$=mess$+"ù"
		EndIf
	EndIf
	If keys(61,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"µ"
		Else
			mess$=mess$+"*"
		EndIf
	EndIf
	
	If keys(62,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+">"
		Else
			mess$=mess$+"<"
		EndIf
	EndIf
	If keys(44,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"W"
		Else
			mess$=mess$+"w"
		EndIf
	EndIf
	If keys(63,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"X"
		Else
			mess$=mess$+"x"
		EndIf
	EndIf
	If keys(28,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"C"
		Else
			mess$=mess$+"c"
		EndIf
	EndIf
	If keys(64,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"V"
		Else
			mess$=mess$+"v"
		EndIf
	EndIf
	If keys(65,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"B"
		Else
			mess$=mess$+"b"
		EndIf
	EndIf
	If keys(42,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"N"
		Else
			mess$=mess$+"n"
		EndIf
	EndIf
	If keys(66,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"?"
		Else
			mess$=mess$+","
		EndIf
	EndIf
	If keys(67,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"."
		Else
			mess$=mess$+";"
		EndIf
	EndIf
	If keys(68,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"/"
		Else
			mess$=mess$+":"
		EndIf
	EndIf
	If keys(69,2)=50 ; problème aussi avec cette touche
		If KeyDown(keys(70,1)) Or KeyDown(keys(10,1))
			mess$=mess$+"§"
		Else
			mess$=mess$+"!"
		EndIf
	EndIf
	
	If keys(72,2)=50 Then mess$=mess$+"."
	If keys(73,2)=50 Then mess$=mess$+"0"
	If keys(74,2)=50 Then mess$=mess$+"1"
	If keys(75,2)=50 Then mess$=mess$+"2"
	If keys(76,2)=50 Then mess$=mess$+"3"
	If keys(77,2)=50 Then mess$=mess$+"4"
	If keys(78,2)=50 Then mess$=mess$+"5"
	If keys(79,2)=50 Then mess$=mess$+"6"
	If keys(80,2)=50 Then mess$=mess$+"7"
	If keys(81,2)=50 Then mess$=mess$+"8"
	If keys(82,2)=50 Then mess$=mess$+"9"
	If keys(83,2)=50 Then mess$=mess$+"/"
	If keys(84,2)=50 Then mess$=mess$+"*"
	If keys(85,2)=50 Then mess$=mess$+"-"
	If keys(86,2)=50 Then mess$=mess$+"+"
	
	If keys(12,2)=50 Then mess$=mess$+" "

	Return mess$
End Function





;Type message :
; 1 - Position perso remontée vers serveur (num::map::x#::y#::z#::yaw#::animation::script::event_action::choix_qcm)
; 2 - Position groupe vers client (num::map::x#::y#::z#::yaw#::animation::script::event_action::choix_qcm)
; 3 - Modif variable groupe *montée* (num::type::number::new_var) (type : 1 script : 2 trigger : 3 nom_action$ : 4 range_trigger)
; 4 - Maj des stats de la map
; 5 - Maj avatar *all* (num::groupe::equi[8]::caps::junk::set::cat::prop::capac[8-10])
; 6 - Maj avatar II *all* (num::car[5]::cmpt[15]::faiblesse#[3])
; 7 - Selection de l'Avatar par un Joueur *all*
; 8 - Fusion de groupe *all* (groupe origine::groupe destination)
; 9 - Un joueur quitte le groupe *all* (num::old_groupe)
; 10 - Changement d'état des portes *all*
;
; 14 - Maj avatar VI *all* (num::charge[3])
; 15 - Nouvel avatar IA (num::cat::grp::set::prop)
; 16 - Maj avatar III *all* (num::anim::capac[8-10]::defense::target)
; 17 - Maj avatar IV *all* (num::k::quest[k*20+1:k*20+20])
; 18 - Maj avatar V *all* (num::name$::classe$::description$)
; 19 - Changement de formation (num::formation[9])
; 20 - Lancer un combat (groupe_A::groupe_B)
; 21 - Un combat est lancé/mis à jour (num_combat::groupe_A::groupe_B::phase::tour)
; 22 - Ordre de combat (num_combat::qui::ordre[19]::tour::phase)
; 23 - Action de combat -annonce- (num_combat::de_qui::type_action::var1$::var2$::var3$::var4$::var5$::var6$) (les varX$ sont retransformés en varX et varX# si besoin)
; 24 - Action de combat -résultat- (num_combat::de_qui::type_action::var$[18])
; 25 - Ping "Ready" (num_combat::de_qui) si de serveur alors "Faïto !"
;
; 30 - Maj position butin (num::prop::kind::hidden::map::x::y::z:alpha)
; 31 - Maj loot (num::k::loot[k*25+1;k*25+25])
; 32 - Maj consommable (num::consommable[10]::caps::junk)
; 33 - Maj quest (num::k::quest[k*25+1;k*25+25])
; 34 - Supprimer butin (num)
; 35 - Donner un objet de quête (num_av::num_quest_item)
;
; 90 - Rejoindre une partie après une déconnection
; 91 - Renvoyer les infos après une déconnection
;
; 96 - Log (forcé par le serveur) (r::g::b::num_combat::msg$)
; 97 - PAUSE serveur (pour par exemple qq1 qui reçoit un coup de fil important)
; 98 - Whisper (num_player::msg$)
; 99 - Chat

Function analyse$(msg_type,msg_data$="",msg_from=0,msg_to=0)
	If msg_to=player_id Or msg_to=0
		Select msg_type
			
			Case 5 ; maj Avatar I
				If traduction("iiiiiiiiiiiiiiiiii",msg_data$,msg_type)=1
					If avatar_exist(var_analyse_int(1))=0
						new_avatar(var_analyse_int(1),var_analyse_int(14),0)
					EndIf
					For av.avatar=Each avatar
						If av\num=var_analyse_int(1)
							av\groupe=var_analyse_int(2)
							For t=1 To 8
								av\equi[t]=var_analyse_int(t+2)
							Next
							av\caps=var_analyse_int(11)
							av\junk=var_analyse_int(12)
							av\set=var_analyse_int(13)
							av\cat=var_analyse_int(14)
							av\prop=var_analyse_int(15)
							av\pv[1]=var_analyse_int(16)
							av\steam=var_analyse_int(17)
						EndIf
					Next
				EndIf
				
			Case 6 ;; maj avatar II
				If traduction("iiiiiiiiiiiiiiiiiiiiifff",msg_data$,msg_type)=1
					For av.avatar=Each avatar
						If av\num=var_analyse_int(1)
							For t=1 To 3
								av\att[t]=var_analyse_int(1+3*(t-1)+1)
								av\def[t]=var_analyse_int(1+3*(t-1)+2)
								av\deg[t]=var_analyse_int(1+3*(t-1)+3)
							Next
							For t=1 To 15
								av\cmpt[t]=var_analyse_int(10+t)
							Next
							For t=1 To 3
								av\faiblesse#[t]=var_analyse_float#(t)
							Next
						EndIf
					Next
				EndIf
				
			Case 14 ; maj charges
				If traduction("iiii",msg_data$,msg_type)=1
					For avatar.avatar=Each avatar
						If avatar\num=var_analyse_int(1)
							avatar\charge[1]=var_analyse_int(2)
							avatar\charge[2]=var_analyse_int(3)
							avatar\charge[3]=var_analyse_int(4)						
						EndIf
					Next
				EndIf

				
			Case 15 ; nouvel avatar	
				If traduction("iiiii",msg_data$,msg_type)=1
					new_avatar(var_analyse_int(1),var_analyse_int(2),var_analyse_int(3),var_analyse_int(4),var_analyse_int(5))
				EndIf
			
			Case 16 ; chgt animation avatar
				If traduction("iiiiiii",msg_data$,msg_type)=1
					For avatar.avatar=Each avatar
						If avatar\num=var_analyse_int(1)
							avatar\animation=var_analyse_int(2)
							av\pv[1]=var_analyse_int(3)
							av\steam=var_analyse_int(4)
							avatar\defense=var_analyse_int(5)
							avatar\target=var_analyse_int(6)							
						EndIf
					Next
				EndIf
			
			Case 17
				RuntimeError "Case 17 ! Ne devrait pas arriver"

			Case 18 ; chgt de titre
				If traduction("issssss",msg_data$,msg_type)=1
					For av.avatar=Each avatar
						If av\num=var_analyse_int(1)
							av\name$[1]=var_analyse_str$(1)
							av\classe$[1]=var_analyse_str$(2)
							av\description$[1]=var_analyse_str$(3)
							av\name$[2]=var_analyse_str$(4)
							av\classe$[2]=var_analyse_str$(5)
							av\description$[2]=Replace(var_analyse_str$(6),"$$","#")
						EndIf
					Next			
				EndIf

				
			Case 19 ; changement de formation
				If traduction("iiiiiiiiii",msg_data$,msg_type)=1
					For gr.groupe=Each groupe
						If gr\num=var_analyse_int(1)
							For t=1 To 9
								gr\formation[t]=var_analyse_int(t+1)
							Next
						EndIf
					Next
				EndIf
				
			Case 21 ; lancer un nouveau combat
				If traduction("iiiii",msg_data$,msg_type)=1
					grp=-1
					good=0
					For combat.combat=Each combat
						If combat\num=var_analyse_int(1)
							combat_vainqueur=0
							good=good+1
							combat\phase=var_analyse_int(4)
							combat\tour=var_analyse_int(5)
							If combat\phase=3
								;sortie_combat=1
								combat_vainqueur=var_analyse_int(3)
							ElseIf combat\groupe[1]=grp Or combat\groupe[2]=grp
								mode_de_jeu=2
								num_combat=var_analyse_int(1)
								If combat\groupe[1]=grp ; intervertir pour que le joueur soit dans le groupe de droite
									ai=combat\groupe[1]
									combat\groupe[1]=combat\groupe[2]
									combat\groupe[2]=ai						
								EndIf			
							Else
								If var_analyse_int(4)=0 Then new_log("Nouveau combat !");"Les groupes "+var_analyse_int(2)+" et "+var_analyse_int(3)+" commencent à se battre !")
							EndIf
							If good>1 Then Delete combat
						EndIf
					Next
				EndIf
				
			Case 22 ; MAJ de l'ordre
				If traduction("iiiiiiiiiiiiiiiiiiiiiii",msg_data$,msg_type)=1
					good=0
					;If var_analyse_int(1)=num_combat
						For combat.combat=Each combat
							If combat\num=var_analyse_int(1)
								combat\qui=var_analyse_int(2)
								;mess$=""
								For t=1 To 19
									combat\ordre[t]=var_analyse_int(2+t)
									;mess$=mess$+combat\ordre[t]+"#"
								Next
								combat\tour=var_analyse_int(22)
								combat\phase=var_analyse_int(23)
								good=1
							EndIf
						Next
						;new_log(mess$)
					;EndIf
					;If good=0 Then new_log("L'ordre n'a pas été effecté")
				Else
					;new_log("L'ordre n'a pas été traduit")
				EndIf
				
			Case 24 ; action de combat (résultat)
				msg_data$=msg_data$+"#s#s#s#s#s#s#s#s#s#s#s#s#s#s#s#s#s#s#"
				If traduction("iiisssssssssssssssssss",msg_data$)=1	
					If var_analyse_int(1)=num_combat
						combat_action=var_analyse_int(3)
						For t=1 To 9
							action_target(t)=0
							action_reussite(9)=0
						Next
						
						Select combat_action
							Case 0 ; pas de réaction
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(3))

							Case 11 ; attaque (cible::réussite) (réussite : 1 pare, 2 touche, deux chiffres pour les doubles)
								combat_from=var_analyse_int(2)
								action_target(1)=Int(var_analyse_str$(1))
								action_reussite(1)=Int(var_analyse_str$(2))
								combat_temp_anim=Int(var_analyse_str$(3))
								combat_dgts(1)=int(var_analyse_str$(4))
								
							Case 12 ; atd (cible::réussite) (réussite : 1 pare, 2 touche, deux chiffres pour les doubles)
								combat_from=var_analyse_int(2)
								action_target(1)=Int(var_analyse_str$(1))
								action_reussite(1)=Int(var_analyse_str$(2))
								combat_temp_anim=Int(var_analyse_str$(3))
								combat_dgts(1)=int(var_analyse_str$(4))
							
							Case 15 ; bangbangbang
								combat_from=var_analyse_int(2)
								action_target(1)=Int(var_analyse_str$(1))
								action_reussite(1)=Int(var_analyse_str$(2))
								combat_dgts(1)=Int(var_analyse_str$(3))
								combat_temp_anim=Int(var_analyse_str$(4))
								For t=2 To 9
									action_target(t)=Int(var_analyse_str$(3*t-1))
									action_reussite(t)=Int(var_analyse_str$(3*t))
									combat_dgts(t)=int(var_analyse_str$(3*t+1))
								Next
								;combat_action=12
								for t=1 to 8
									for k=t+1 to 9
										if action_target(t)=action_target(k) and action_target(t)<>0
											combat_dgts(t)=combat_dgts(t)+combat_dgts(k)
											action_reussite(t)=max(action_reussite(t),action_reussite(k))
											action_target(k)=0
											action_reussite(k)=0
											combat_dgts(k)=0
										EndIf
									next
								next
								
							Case 21 ; defensive
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
								
							Case 22 ; fuite
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
							
							Case 221 ; fuite échouée
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
								For av.avatar=Each avatar
									If av\num=combat_from
										av\animation=1
									EndIf
								Next
							
							Case 222 ; fuite réussie
							
							Case 31 ; attendre
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))

							Case 32 ; passer son tour
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))

							Case 41 ; chgt formation
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
								combat_var2=Int(var_analyse_str$(2)) ; nouvelle place
								action_target(1)=Int(var_analyse_str$(4)) ; gars sur la nouvelle place
								combat_var1=Int(var_analyse_str$(3)) ; ancienne place
							
							Case 51 ; ajout GearBot
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
								action_target(1)=Int(var_analyse_str$(2)) ; gearbot
								
							Case 52 ; rangement GearBot
								combat_from=var_analyse_int(2)
								combat_temp_anim=Int(var_analyse_str$(1))
								action_target(1)=Int(var_analyse_str$(2)) ; gearbot

							Default ; erreur de transmission
								combat_from=var_analyse_int(2)
								combat_temp_anim=1000

						End Select
					EndIf
					timer_animation2#=0
				EndIf
								
			Case 96 ; log (forcé par le serveur)	
				If traduction("iiiia",msg_data$)
					If var_analyse_int(4)=0 Or var_analyse_int(4)=num_combat
						new_log(var_analyse_str$(1),var_analyse_int(1),var_analyse_int(2),var_analyse_int(3))
					EndIf
				EndIf
				
			Case 97 ; pause forcée
				sortie=0
				FlushKeys
				While sortie=0
					If KeyHit(01)
						If mode_debug=1
							End
						Else
							sortie=1
						Endif
					EndIf
					If KeyDown(57) Then sortie=1
					start_loop("pause")
					af#=(Cos(timer_animation#*5)+2)*0.33
					Color 255*af#,255*af#,255*af#
					SetFont big_font
					Text screenwidth*0.5,screenheight*0.5,"PAUSE !",1,1
					SetFont middle_font
					If Int(options#(7))=1 ;français
						Text screenwidth*0.5,screenheight*0.6,"Appuyez sur Espace ou sur Echap pour revenir au jeu",1,1
					Else
						Text screenwidth*0.5,screenheight*0.6,"Press Space or Esc to return to the game",1,1
					Endif
					Flip
					compensation_lag()
				Wend
				FlushKeys
				
			;Case 98 ; whisper
			;	If traduction("ia",msg_data$)=1
			;		If var_analyse_int(1)=num_player
			;			;new_log(NetPlayerName$(msg_from)+" : "+Mid(msg_data$,4),255,75,255)
			;			;Playsound2(whipser)
			;		EndIf
			;	EndIf
				
			;Case 99 ;Chat
				;new_log(NetPlayerName$(msg_from)+" : "+msg_data$,50,50,255)
				;Playsound2(msg_in !)
				
			Case 20 ; un combat a été lancé
				If traduction("ii",msg_data$,msg_type)=1
					num_combat=num_combat+1
					combat.combat=New combat
					combat\num=num_combat
					combat\groupe[1]=var_analyse_int(1)
					combat\groupe[2]=var_analyse_int(2)
					combat\phase=-1
					combat\tour=0
					msg$=combat\num+"#"+combat\groupe[1]+"#"+combat\groupe[2]+"#0#0#"
					;new_log("Un nouveau combat (n°"+num_combat+") a commencé entre "+combat\groupe[1]+" et "+combat\groupe[2]+".",180,180,0)
				EndIf
				
			Case 23 ; action combat (annonce)
				If traduction("iiissssss",msg_data$,msg_type)=1
					good_c = var_analyse_int(1)
					good_av = var_analyse_int(2)
					current_action=var_analyse_int(3)
					For combat.combat=Each combat
						If combat\num=good_c
							For av.avatar=Each avatar
								If av\num=good_av
									Select current_action
										Case -1 ; death
											;retirer le gars de l'ordre
											av\animation=4
											change=0
											ai=combat\ordre[combat\qui]				
											For t=1 To 18
												If t=combat\qui Then change=1
												If change=1 Then combat\ordre[t]=combat\ordre[t+1]
											Next
											combat\ordre[19]=ai
											combat\old_qui=-1
											combat\last_action=MilliSecs()
											
										
										Case 0 ; rien
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" est complètement perdu et passe son tour."
											Else
												mess$=av\name$[1]+" skip his turn."
											Endif
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="180#180#0#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=1000
											mess$=combat\num+"#"+av\num+"#0#"+combat\anim_time+"#s#s#s#s#s#"
											analyse(24,mess$,master_id)

										Case 11 ; attaque normale (cible::bonus_att::bonus_def::alt::mult_att)
											av\animation=1
											target=Int(var_analyse_str$(1))
											bonus_att=0
											bonus_def=0
											bonus_deg=0
											alt=0
											mult_att=0
											bonus_att=Int(var_analyse_str$(2))
											bonus_def=Int(var_analyse_str$(3))
											alt=Int(var_analyse_str$(4))
											mult_att=Int(var_analyse_str$(5))
											For av_t.avatar=Each avatar
												If av_t\num=target
													if av_t\pv[1]>0 ; mémorise si la cible était déjà morte (pour limiter certains logs)
														deja_mort=0
													else
														deja_mort=1
													EndIf
													;calcul de l'attaque
														;chercher l'arme équipée
														compt_att=0
														capac_att=0; (1 mains nues -ATL-, 2 ATL, 3 ATH, 4 ATD, 5 arme à feu -ATD-)
														If av\equi[1]<>0
															; chercher la compétence utilisée par l'arme
															For ca.arme=Each arme
																If ca\num=av\equi[1]
																	compt_att=ca\cat ;(épée, hache, ...)
																	capac_att=ca\classe ;(mains nues, légère, lourde, distance, à feu)
																	scr_dgts=ca\scr_degats
																	;transformer {1,2,3,4,5} en {1,1,2,3,3}
																	style_att=min(3,max(1,capac_att-1))
																	scr_dgts=ca\scr_degats
																	bonus_att=bonus_att+ca\att[style_att] ; bonus d'attaque de l'arme
																	bonus_deg=bonus_deg+av\deg[style_att] ; bonus de dégâts de l'arme
																EndIf
															Next
														Else ; mains nues
															compt_att=1
															capac_att=1
															style_att=1
														EndIf
														;bonus dus aux compétences
														For t=1 To 15
															If av\cmpt[t]=1 And compt_att=1 Then bonus_att=bonus_att+2 ; Mains nues +2
															If av\cmpt[t]=4 And compt_att=8 Then bonus_att=bonus_att+3 ; Pistolero
															If av\cmpt[t]=15 And compt_att=9 Then bonus_att=bonus_att+3 ; Spé fusil (bullet)
															If av\cmpt[t]=9 And compt_att=10 Then bonus_att=bonus_att+3 ; Spé shotgun
															If av\cmpt[t]=7 And compt_att=9 And mult_att=0 Then bonus_att=bonus_att+3 ; Sniper
															If av\cmpt[t]=10 And (compt_att=2 Or compt_att=3) Then bonus_att=bonus_att+2 ; Epée +2
															If av\cmpt[t]=13 And (compt_att=6 Or compt_att=7) Then bonus_att=bonus_att+2 ; Hache +2
															If av\cmpt[t]=11 And (compt_att=4 Or compt_att=5) Then bonus_att=bonus_att+2 ; Lance +2
														Next
														;bonus/malus dus aux états
														calcul_bonus_equi(av\num)
														bonus_att=bonus_att-calcul_encombrement(av\num)+bonus_equi(style_att)
														If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then bonus_att=bonus_att-2
														
														;calcul de l'AT(lhd)
														attaque=av\att[style_att]+bonus_att

													;calcul de la défense
														;chercher l'arme équipée
														compt_def=0
														capac_def=0; (1 DCL, 2 DCH, 3 ESQ)
														If av_t\equi[1]<>0
															; chercher la compétence utilisée par l'arme
															For ca.arme=Each arme
																If ca\num=av_t\equi[1]
																	bonus_def=bonus_def+ca\def[style_att]
																	compt_def=ca\classe
																	capac_def=ca\cat
																EndIf
															Next
														Else ; mains nues
															compt_def=1
															capac_def=1
														EndIf
														;bonus dus aux compétences
														Select compt_def
															Case 1
																For t=1 To 15
																	If av_t\cmpt[t]=1 And compt_def=1 Then bonus_def=bonus_def+2 ; mains nues +2
																Next
														End Select
														;bonus/malus dus aux états
														calcul_bonus_equi(av_t\num)
														bonus_def=bonus_def+av_t\defense-calcul_encombrement(av_t\num)+bonus_equi(3+style_att)
														If av_t\pv[1]<av_t\pv[2]*LIMITE_BLESSURE# Then bonus_def=bonus_def-2
																																										
														;calcul de l'DC(lhe)
														defense=av_t\def[style_att]+bonus_def
														
														;calcul de l'armure
														If av_t\equi[4]<>0
															For ar.armure=Each armure
																If ar\num=av_t\equi[4]
																	For t=1 To 3
																		armure_temp#(t)=ar\val#[t]
																	Next
																EndIf
															Next
														Else
															armure_temp#(1)=0
															armure_temp#(2)=0
															armure_temp#(3)=0
														EndIf

													;calcul si touche (et crit ou parfait)
														nb_attaque=1
														dgts#=0
														animation=0
														If Int(options#(7))=1 ;français 
															mess$=av\name$[1]+" attaque "+av_t\name$[1]
														Else
															mess$=av\name$[1]+" attacks "+av_t\name$[1]
														Endif
														If capac_att=1 Then nb_attaque=2
														crit_happened=0
														While nb_attaque>0
															jet=Rand(1,20)
															;;a enlever
															;new_log("("+Str(av\att[style_att])+signed_str$(bonus_att)+")+"+Str(jet)+" DD"+Str(av_t\def[style_att])+signed_str$(bonus_def))
															If jet+attaque+1>defense ; touche
																touche=1
															Else
																touche=0
															EndIf
															If jet=20 Then touche=2 ; crit
															If jet=0 Then touche=0 ; echec critique
															;calcul des dégâts
															If touche>0
																animation=animation*10+2
																calcul_bonus_equi(av\num)
																dgts_temp#=degats_armes#(scr_dgts,av\num,av_t\num)+av\deg[style_att]+bonus_equi(6+style_att)
																;Attaque sournoise
																If have_rule(av\num,14) ; attaque sournoise
																	sournoise=max(0,-av_t\defense*0.5)*ATT_SOURNOISE
																EndIf
																dgts_temp#=max(0,dgts_temp#-armure_temp#(touche))*av_t\faiblesse#[touche]+max(0,min(sournoise,sournoise+dgts_temp#-armure_temp#(touche)))
																dgts#=dgts#+dgts_temp#
																If Int(options#(7))=1 ;français 
																	Select touche
																		Case 2
																			mess$=mess$+" (Crit)"
																			crit_happened=max(crit_happened,1)
																		Case 3
																			mess$=mess$+" (Parfait)"
																			crit_happened=max(crit_happened,1)
																		Default
																			mess$=mess$+", touche"
																	End Select
																Else
																	Select touche
																		Case 2
																			mess$=mess$+" (Crit)"
																			crit_happened=max(crit_happened,1)
																		Case 3
																			mess$=mess$+" (Perfect)"
																			crit_happened=max(crit_happened,1)
																		Default
																			mess$=mess$+", hits"
																	End Select

																Endif
															Else
																If Int(options#(7))=1 ;français 
																	mess$=mess$+", rate sa cible"
																Else
																	mess$=mess$+", misses"
																EndIf
																animation=animation*10+1
															EndIf
															nb_attaque=nb_attaque-1
														Wend
													;appliquer les dégâts
													dgts#=Int(dgts#)
													av_t\pv[1]=av_t\pv[1]-dgts#
													If av_t\pv[1]<0.1
														animation=Floor(animation*0.1)*10+2 ; si mort, l'animation de la cible est la mort, même si il a paré cette attaque
													EndIf
													;enlever la défense
													If capac_att>3 ; attaque à distance (capac_att=4 ou 5)
														av_t\defense=min(MAX_BONUS_DEF,max(av_t\defense-ATD_MOD_BONUS_DEF,MIN_BONUS_DEF))
													Else ; attaque au corps à corps (capac_att=1, 2 ou 3)
														av_t\defense=min(MAX_BONUS_DEF,max(av_t\defense-ACC_MOD_BONUS_DEF,MIN_BONUS_DEF))
													EndIf
													
													;compte-rendu
													If deja_mort=0
														If Int(options#(7))=1 ;français 
															mess$=mess$+" et inflige "+Str(Int(dgts#))+" dégâts."
														Else
															mess$=mess$+" and inflicts  "+Str(Int(dgts#))+" damages."
														Endif
														;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
														mess$="255#255#255#"+combat\num+"#"+mess$
														analyse(96,mess$,master_id)
														combat\anim_time=1500
														mess$=combat\num+"#"+av\num+"#"+Str(11+alt)+"#"+av_t\num+"#"+animation+"#1500#"+Int(dgts#+10000*crit_happened)+"#s#s#"
														analyse(24,mess$,master_id)
														;maj_avatar(av\num,3)
														;maj_avatar(av_t\num,3)
														If Not(av_t\pv[1]>0)
															If Int(options#(7))=1 ;français 
																mess$=av_t\name$[1]+" est battu."
															Else
																mess$=av_t\name$[1]+" is defeated."
															Endif
															;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
															mess$="255#255#255#"+combat\num+"#"+mess$
															analyse(96,mess$,master_id)
														EndIf
													EndIf
													If mult_att<>0 Then Return Str(av_t\num)+"#"+Str(animation)+"#"+Int(dgts#+10000*crit_happened)+"#"
												EndIf
											Next

										Case 12 ; attaque visée
											av\animation=1
											target=Int(var_analyse_str$(1))
											bonus_att=0
											bonus_def=0
											alt=0
											bonus_att=Int(var_analyse_str$(2))
											bonus_def=Int(var_analyse_str$(3))
											alt=Int(var_analyse_str$(4))
											For av_t.avatar=Each avatar
												If av_t\num=target
													;calcul de l'attaque
														;chercher l'arme équipée
														compt_att=0
														capac_att=0; (1 mains nues -ATL-, 2 ATL, 3 ATH, 4 ATD, 5 arme à feu -ATD-)
														If av\equi[1]<>0
															; chercher la compétence utilisée par l'arme
															For ca.arme=Each arme
																If ca\num=av\equi[1]
																	compt_att=ca\cat ;(épée, hache, ...)
																	capac_att=ca\classe ;(mains nues, légère, lourde, distance, à feu)
																	;transformer {1,2,3,4,5} en {1,1,2,3,3}
																	style_att=min(3,max(1,capac_att-1))
																	scr_dgts=ca\scr_degats
																	bonus_att=bonus_att+ca\att[style_att] ; bonus d'attaque de l'arme
																	bonus_deg=bonus_deg+av\deg[style_att] ; bonus de dégâts de l'arme
																EndIf
															Next
														Else ; mains nues
															compt_att=1
															capac_att=1
															style_att=1
														EndIf
														;bonus dus aux compétences
														For t=1 To 15
															If av\cmpt[t]=1 And compt_att=1 Then bonus_att=bonus_att+2 ; Mains nues +2
															If av\cmpt[t]=4 And compt_att=8 Then bonus_att=bonus_att+3 ; Pistolero
															If av\cmpt[t]=15 And compt_att=9 Then bonus_att=bonus_att+3 ; Spé fusil (bullet)
															If av\cmpt[t]=9 And compt_att=10 Then bonus_att=bonus_att+3 ; Spé shotgun
															If av\cmpt[t]=7 And compt_att=9 And mult_att=0 Then bonus_att=bonus_att+3 ; Sniper
															If av\cmpt[t]=10 And (compt_att=2 Or compt_att=3) Then bonus_att=bonus_att+2 ; Epée +2
															If av\cmpt[t]=13 And (compt_att=6 Or compt_att=7) Then bonus_att=bonus_att+2 ; Hache +2
															If av\cmpt[t]=11 And (compt_att=4 Or compt_att=5) Then bonus_att=bonus_att+2 ; Lance +2
														Next
														;bonus/malus dus aux états
														calcul_bonus_equi(av\num)
														bonus_att=bonus_att-5 ;(visée)
														bonus_att=bonus_att-calcul_encombrement(av\num)+bonus_equi(style_att)
														If av\pv[1]<av\pv[2]*LIMITE_BLESSURE# Then bonus_att=bonus_att-2
														
														;calcul de l'AT(lhd)
														attaque=av\att[style_att]+bonus_att
						
													;chercher l'arme équipée
														compt_def=0
														capac_def=0; (1 DCL, 2 DCH, 3 ESQ)
														If av_t\equi[1]<>0
															; chercher la compétence utilisée par l'arme
															For ca.arme=Each arme
																If ca\num=av_t\equi[1]
																	bonus_def=bonus_def+ca\def[style_att]
																	compt_def=ca\classe
																	capac_def=ca\cat
																EndIf
															Next
														Else ; mains nues
															compt_def=1
															capac_def=1
														EndIf
														;bonus dus aux compétences
														Select compt_def
															Case 1
																For t=1 To 15
																	If av_t\cmpt[t]=1 And compt_def=1 Then bonus_def=bonus_def+2 ; mains nues +2
																Next
														End Select
														;bonus/malus dus aux états
														calcul_bonus_equi(av_t\num)
														bonus_def=bonus_def+av_t\defense-calcul_encombrement(av_t\num)+bonus_equi(3+style_att)
														If av_t\pv[1]<av_t\pv[2]*LIMITE_BLESSURE# Then bonus_def=bonus_def-2
																																										
														;calcul de l'DC(lhe)
														defense=av_t\def[style_att]+bonus_def
														
														;calcul de l'armure
														If av_t\equi[4]<>0
															For ar.armure=Each armure
																If ar\num=av_t\equi[4]
																	For t=1 To 3
																		armure_temp#(t)=ar\val#[t]
																	Next
																EndIf
															Next
														Else
															armure_temp#(1)=0
															armure_temp#(2)=0
															armure_temp#(3)=0
														EndIf

													;calcul si touche (et crit ou parfait)
														nb_attaque=1
														dgts#=0
														animation=0
														If Int(options#(7))=1 ;français 
															mess$=av\name$[1]+" attaque "+av_t\name$[1]
														Else
															mess$=av\name$[1]+" attacks "+av_t\name$[1]
														Endif
														If capac_att=1 Then nb_attaque=2
														crit_happened=0
														While nb_attaque>0
															jet=Rand(1,20)
															;;a enlever
															new_log("("+Str(av\att[style_att])+signed_str$(bonus_att)+")+"+Str(jet)+" DD"+Str(av_t\def[style_att])+signed_str$(bonus_def))
															If jet+attaque+1>defense ; touche
																touche=2 ; attaque visée
															Else
																touche=0
															EndIf
															If jet=20 Then touche=3 ; Parfait
															If jet=0 Then touche=0 ; echec critique
															;calcul des dégâts
															If touche>0
																animation=animation*10+2
																calcul_bonus_equi(av\num)
																dgts_temp#=degats_armes#(scr_dgts,av\num,av_t\num)+av\deg[style_att]+bonus_equi(6+style_att)
																;Attaque sournoise
																If have_rule(av\num,14) ; attaque sournoise
																	sournoise=max(0,-av_t\defense*0.5)*ATT_SOURNOISE
																EndIf
																dgts_temp#=max(0,dgts_temp#-armure_temp#(touche))*av_t\faiblesse#[touche]+max(0,min(sournoise,sournoise+dgts_temp#-armure_temp#(touche)))
																dgts#=dgts#+dgts_temp#
																mess$=mess$+", touche"
																If Int(options#(7))=1 ;français 
																	Select touche
																		Case 2
																			mess$=mess$+" (Crit)"
																			crit_happened=max(crit_happened,1)
																		Case 3
																			mess$=mess$+" (Parfait)"
																			crit_happened=max(crit_happened,1)
																		Default
																			mess$=mess$+", touche"
																	End Select
																Else
																	Select touche
																		Case 2
																			mess$=mess$+" (Crit)"
																			crit_happened=max(crit_happened,1)
																		Case 3
																			mess$=mess$+" (Perfect)"
																			crit_happened=max(crit_happened,1)
																		Default
																			mess$=mess$+", hits"
																	End Select

																EndIf
															Else
																If Int(options#(7))=1 ;français 
																	mess$=mess$+", rate sa cible"
																Else
																	mess$=mess$+", misses"
																EndIf
															EndIf
															nb_attaque=nb_attaque-1
														Wend
													;appliquer les dégâts
													dgts#=Int(dgts#)
													av_t\pv[1]=av_t\pv[1]-dgts#
													If av_t\pv[1]<0.1
														animation=Floor(animation*0.1)*10+2 ; si mort, l'animation de la cible est la mort, même si il a paré cette attaque 
													EndIf
													;enlever la défense
													If capac_att>3 ; attaque à distance (capac_att=4 ou 5)
														av_t\defense=min(MAX_BONUS_DEF,max(av_t\defense-ATD_MOD_BONUS_DEF,MIN_BONUS_DEF))
													Else ; attaque au corps à corps (capac_att=1, 2 ou 3)
														av_t\defense=min(MAX_BONUS_DEF,max(av_t\defense-ACC_MOD_BONUS_DEF,MIN_BONUS_DEF))
													EndIf
													
													;compte-rendu
													if deja_mort=0
														If Int(options#(7))=1 ;français 
															mess$=mess$+" et inflige "+Str(Int(dgts#))+" dégâts."
														Else
															mess$=mess$+" and inflicts  "+Str(Int(dgts#))+" damages."
														Endif
														;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
														mess$="255#255#255#"+combat\num+"#"+mess$
														analyse(96,mess$,master_id)
														combat\anim_time=1500
														mess$=combat\num+"#"+av\num+"#"+Str(11+alt)+"#"+av_t\num+"#"+animation+"#1500#"+int(dgts#+10000*crit_happened)+"#s#s#"
														analyse(24,mess$,master_id)
														;maj_avatar(av\num,3)
														;maj_avatar(av_t\num,3)
														If Not(av_t\pv[1]>0)
															If Int(options#(7))=1 ;français 
																mess$=av_t\name$[1]+" ("+av_t\num+") est battu."
															Else
																mess$=av_t\name$[1]+" ("+av_t\num+") is defeated."
															Endif
															;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
															mess$="255#255#255#"+combat\num+"#"+mess$
															analyse(96,mess$,master_id)
														EndIf
													EndIf
													If mult_att<>0 Then Return Str(av_t\num)+"#"+Str(animation)+"#"+int(dgts#+10000*crit_happened)+"#"
												EndIf
											Next

										Case 13 ; attaque distance (cible1,cible2,cible3,coeff1,coeff2,coeff3) ; coeff<0 <-> contact
											atd_contact=0
											For t=1 To 3
												atd_cible(t)=Int(var_analyse_str$(t))
												atd_coeff#(t)=Float(var_analyse_str$(t+3))
												If atd_coeff#(t)<0 Then atd_contact=1:atd_coeff#(t)=-atd_coeff#(t)
											Next
											jet_cible#=Rnd#(0,1)
											cible=0
											For t=1 To 3
												If atd_coeff#(t)>jet_cible# And cible=0
													cible=atd_cible(t)
												EndIf
											Next
											mess$=var_analyse_int(1)+"#"+var_analyse_int(2)+"#11#"
											mess$=mess$+cible+"#"+Str(-4*atd_contact)+"#0#1#s#s#s#s#"
											analyse(23,mess$)

										Case 14 ; attaque distance visée
											atd_contact=0
											For t=1 To 3
												atd_cible(t)=Int(var_analyse_str$(t))
												atd_coeff#(t)=Float(var_analyse_str$(t+3))
												If atd_coeff#(t)<0 Then atd_contact=1:atd_coeff#(t)=-atd_coeff#(t)
												If atd_coeff#(t)<1
													atd_coeff#(t)=atd_coeff#(t)*0.5
													If have_rule(av\num,6) Then atd_coeff#(t)=0 ; sniper
												EndIf
											Next
											jet_cible#=Rnd#(0,1)
											cible=0
											For t=1 To 3
												If atd_coeff#(t)>jet_cible# And cible=0
													cible=atd_cible(t)
												EndIf
											Next
											If atd_coeff#(1)=1
												mess$=var_analyse_int(1)+"#"+var_analyse_int(2)+"#12#"
												mess$=mess$+cible+"#"+Str(-4*atd_contact)+"#0#1#s#s#s#s#"
											Else
												mess$=var_analyse_int(1)+"#"+var_analyse_int(2)+"#11#"
												mess$=mess$+cible+"#"+Str(-2*atd_contact-2)+"#0#1#s#s#s#s#"
											EndIf
											analyse(23,mess$)
											
										Case 15 ; bangbangbang
											;sélectionner les cibles puis rappeler traduction(11,...) le nombre de fois qu'il faut
											combat_target=Int(var_analyse_str$(1))
											cadence=Int(var_analyse_str$(2))
											surplus=0
											debut_mess$=var_analyse_int(1)+"#"+var_analyse_int(2)+"#11#"
											compte_rendu1$=""
											compte_rendu2$=""
											target_valide_distance(av\num,combat_target,combat\num)
											For k=1 To cadence
												superflu=0
												;If have_rule(av\num,8)
												;	target_valide_distance(av\num,combat_target,combat\num)
												;EndIf
												jet_cible#=Rnd#(0,1)
												cible=0
												For t=1 To 3
													If atd_coeff#(t)>jet_cible# And cible=0
														cible=atd_cible(t)
													EndIf
												Next
												;new_log("Selection : "+cible+" : "+jet_cible#+" - "+atd_coeff#(1)+" / "+atd_coeff#(2)+" / "+atd_coeff#(3))
												For av_cible.avatar=Each avatar
													If av_cible\num=cible
														If av_cible\pv[1]>0
															;cible vivante
														Else
															surplus=surplus+1
															superflu=1
															;new_log("Attaque superflue")
														EndIf
													EndIf
												Next
												mess$=debut_mess$
												If k=2
													If cadence>2
														mess$=mess$+cible+"#"+Str(-4*atd_contact+2-2*k)+"#0#1#3#s#s#s#"
													ElseIf have_rule(av\num,5) ; Pro du double
														mess$=mess$+cible+"#"+Str(-4*atd_contact)+"#0#1#3#s#s#s#"
													Else
														mess$=mess$+cible+"#"+Str(-4*atd_contact+2-2*k)+"#0#1#3#s#s#s#"
													EndIf
												Else
													If cadence>k
														mess$=mess$+cible+"#"+Str(-4*atd_contact+2-2*k)+"#0#1#3#s#s#s#"
													Else
														mess$=mess$+cible+"#"+Str(-4*atd_contact+2-2*k)+"#0#1#3#s#s#s#"
													EndIf
												EndIf
												If superflu=0 Or have_rule(av\num,8)=0
													If k=1
														compte_rendu1$=analyse$(23,mess$)
														;new_log("Oui oui, j'ai tiré : "+mess$)
													Else
														compte_rendu2$=compte_rendu2$+analyse$(23,mess$)
														;new_log("Oui oui, j'ai tiré : "+mess$)
													EndIf
												EndIf
											Next
											If have_rule(av\num,8) Then av\charge[1]=av\charge[1]+surplus		
										
											combat\anim_time=1500
											mess$=combat\num+"#"+av\num+"#15#"+compte_rendu1$+"1500#"+compte_rendu2$
											analyse(24,mess$,master_id)
											mult_att=0

										Case 21 ; défensive
											av\defense=max(MIN_BONUS_DEFENSE,min(MAX_BONUS_DEF,av\defense+BONUS_DEFENDRE))
											av\animation=3				
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" se met en position défensive"
											Else
												mess$=av\name$[1]+" moves to defensive position "
											Endif											
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=1000
											mess$=combat\num+"#"+av\num+"#21#"+combat\anim_time+"#s#s#s#s#s#"
											analyse(24,mess$,master_id)
											;maj_avatar(av\num,3)

										Case 31 ; attendre
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" décide d'attendre le bon moment pour agir."
											Else
												mess$=av\name$[1]+" decides to wait for the right moment to act"
											Endif
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											change=0
											ai=combat\ordre[combat\qui]				
											For t=1 To 18
												If t=combat\qui Then change=1
												If change=1 Then combat\ordre[t]=combat\ordre[t+1]
											Next
											combat\ordre[19]=ai
											combat\old_qui=-1
											combat\last_action=MilliSecs()
											combat\anim_time=1000
											mess$=combat\num+"#"+av\num+"#31#"+combat\anim_time+"#s#s#s#s#s#"
											analyse(24,mess$,master_id)
										
										Case 311 ; revenir
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" se prépare à agir !"
											Else
												mess$=av\name$[1]+" is ready to act !"
											Endif
											
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											;retirer le gars de l'ordre et décaler les inactifs vers le haut à partir du gars
											bi=0
											For t=1 To 19
												If av\num=combat\ordre[t] Then bi=t
											Next
											If bi<>19
												For t=bi To 18
													combat\ordre[t]=combat\ordre[t+1]
												Next
											EndIf
											;décaler l'ordre de 1 vers le bas à partir de combat\qui
											For t=19 To combat\qui+2 Step -1
												combat\ordre[t]=combat\ordre[t-1]
											Next
											;rajouter av\num à combat\qui+1
											combat\ordre[combat\qui+1]=av\num
											;maj ordre
											mess$=combat\num+"#"+combat\qui+"#"
											For t=1 To 19
												mess$=mess$+combat\ordre[t]+"#"
											Next
											mess$=mess$+combat\tour+"#"+combat\phase+"#"
											analyse(22,mess$,master_id)

										Case 32 ; passer son tour
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" décide de passer son tour."
											Else
												mess$=av\name$[1]+" decide to skip turn."
											Endif
											
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=1000
											mess$=combat\num+"#"+av\num+"#32#"+combat\anim_time+"#s#s#s#s#s#"
											analyse(24,mess$,master_id)


										Case 41 ; chgt de formation
											av\animation=1
											For gr.groupe=Each groupe
												If gr\num=av\groupe
													For t=1 To 9
														If gr\formation[t]=av\num Then old_place=t
													Next
													new_place=Int(var_analyse_str$(1))
													action_target(1)=gr\formation[new_place] ; gars sur la nouvelle place
													gr\formation[old_place]=gr\formation[new_place]
													gr\formation[new_place]=av\num
													If Int(options#(7))=1 ;français 
														mess$=av\name$[1]+" change de place !"
													Else
														mess$=av\name$[1]+" switches place !"
													Endif
													;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
													mess$="255#255#255#"+combat\num+"#"+mess$
													analyse(96,mess$,master_id)
													combat\anim_time=1000
													mess$=combat\num+"#"+av\num+"#41#"+combat\anim_time+"#"+new_place+"#"+old_place+"#"+action_target(1)+"#s#s#s#s#"
													analyse(24,mess$,master_id)
													mess$=gr\num+"#" ; nouvel ordre
													For t=1 To 9
														mess$=mess$+gr\formation[t]+"#"
													Next
													analyse(19,mess$,master_id)
												EndIf
											Next
											
										Case 42 ; chgt d'arme
											av\animation=1
											t=var_analyse_str$(1)
											new_equi=av\equi[t]
											new_charge=av\charge[t]
											av\equi[t]=av\equi[1]
											av\charge[t]=av\equi[1]
											av\equi[1]=new_equi
											av\charge[1]=new_charge
											
											img_chgt=0
											For arme.arme = Each arme
												If arme\num=av\equi[1]
													img_chgt=arme\icone[1]
										 		EndIf
										 	Next
											
											ai=0 : bi=0 : dir=0
											For gr.groupe=Each groupe
												If gr\num=av\groupe
													If gr\num=-1 ; joueur
														dir=1
														For i=0 To 2 ; ligne
															For j=1 To 3 ; colonne
																t=i*3+j
																If gr\formation[t]=av\num
																	ai=screenwidth-(100*(3-j)+50)+50
																	bi=(100*i+175)-30
																EndIf
															Next
														Next
													Else
														dir=-1
														For i=0 To 2 ; ligne
															For j=1 To 3 ; colonne
																t=i*3+j
																If gr\formation[t]=av\num
																	ai=(100*(3-j)+50)-50
																	bi=(100*i+175)-30
																EndIf
															Next
														Next
													EndIf
												EndIf
											Next
											
											new_chgt_equi(img_chgt,ai,bi,dir)
											
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" change d'arme !"
											Else
												mess$=av\name$[1]+" changes weapon !"
											Endif
											;new_log("(cmb "+combat\num+"p"+combat\phase+")"+mess$)
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
										
										Case 51 ; déployer le GearBot serveur
											num_bot=(av\equi[6]-180)*-1
											;charger l'image du combattant
											For av_bot.avatar = Each avatar ;récupérer la catégorie d'image du gearbot (et en profiter pour récupérer son nom, changer l'animation et le groupe)
												If av_bot\num=num_bot
													name_bot$=av_bot\name$[Int(options#(7))]
													cat_bot=av_bot\cat
													av_bot\groupe=av\groupe
													av_bot\animation=0
												EndIf
											Next
											good=0
											For cat=1 to 18
												If fighters_utilises(cat)=cat_bot Then good=1 ; on peut se permettre de charger "on the fly" car il ne sera chargé qu'une fois par jeu lancé (donc pas de chargement pour les prochains combats)
											Next
											If good=0
												For cat=1 to 18
													If good=0 And fighters_utilises(cat)=0
														fighters_utilises(cat)=cat_bot
														load_combattant(fighters_utilises(cat))
														good=1
													EndIf
												Next
											EndIf
											;le faire appartenir au groupe de l'appelant (par le av_bot\groupe fait juste avant ET le grp\formation fait ici) et le placer au bon endroit
											For grop.groupe=Each groupe
												If grop\num=av\groupe
													grop\formation[Int(var_analyse_str$(1))]=num_bot
												EndIf
											Next
											;insérer le gearbot dans l'ordre juste avant le déployeur
											good=0
											For cbta=1 to 18
												If combat\ordre[cbta]=av\num and good=0
													For cbtb=17 to cbta Step -1
														combat\ordre[cbtb+1]=combat\ordre[cbtb]
													Next
													combat\ordre[cbta]=num_bot
													good=1
												EndIf
											Next
											;retour
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" déploie son gearbot "+name_bot$+" !"
											Else
												mess$=av\name$[1]+" expands the "+name_bot$+" gearbot !"
											Endif
											
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=2000
											mess$=combat\num+"#"+av\num+"#51#"+combat\anim_time+"#"+num_bot+"#s#s#s#s#"
											analyse(24,mess$,master_id)
											frame_a=MilliSecs()
													
										Case 52 ; ranger le GearBot
											num_bot=(av\equi[6]-180)*-1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" range son gearbot "+name_bot$+" !"
											Else
												mess$=av\name$[1]+" stores the "+name_bot$+" gearbot !"
											Endif
											mess$="255#255#255#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=2000
											mess$=combat\num+"#"+av\num+"#52#"+combat\anim_time+"#"+num_bot+"#s#s#s#s#"
											analyse(24,mess$,master_id)
											;le supprimer sur groupe -1 (par le av\grp ET grp\formation) à la fin de l'animation (dans combat.bb)
											
										Case 99 ; revenir en jeu
											av\animation=1
											
										Default
											av\animation=1
											If Int(options#(7))=1 ;français 
												mess$=av\name$[1]+" tente de faire quelque chose mais on ne comprend pas quoi. ("+current_action+")" 
											Else
												mess$=av\name$[1]+" X tries to do something but we don't know what.. ("+current_action+")" 
											Endif
											
											;new_log("(cmb "+combat_num+")"+mess$,180,0,0)
											mess$="180#0#0#"+combat\num+"#"+mess$
											analyse(96,mess$,master_id)
											combat\anim_time=1000
											mess$=combat\num+"#"+av\num+"#99#"+combat\anim_time+"#s#s#s#s#s#"
											analyse(24,mess$,master_id)

									End Select
									av\last_action=var_analyse_int(3)
								EndIf
							Next
							If current_action<>13 And current_action<>14 And mult_att=0 ; attaque dist/dist visée
								If current_action<99 And current_action<>31 And current_action>-1 ; 31=attendre
									combat\qui=combat\qui+1
								EndIf
								If combat\ordre[combat\qui]=0
									combat\qui=1
									If combat\phase=1 Then combat\phase=2
								EndIf
								combat\last_action=MilliSecs()
								mess$=combat\num+"#"+combat\qui+"#"
								For t=1 To 19
									mess$=mess$+combat\ordre[t]+"#"
								Next
								mess$=mess$+combat\tour+"#"+combat\phase+"#"
								analyse(22,mess$,master_id)
							EndIf
						EndIf
					Next
				EndIf
				
			Default
				new_log("Mauvais type de message reçu : "+msg_type,255,0,0)

		End Select
	EndIf
End Function

;; transforme les infos contenus dans les NetMsgData en variables exploitables
; i : int
; f : float
; s : string
; a : string jusqu'à la fin (ne tient pas compte du # comme séparateur, idéal pour le chat) -a pour "all"-
Function traduction(code$,info$,msg_type$="Unknown")
	If Right(info$,1)<>"#" Then info$=info$+"#"
	t_int=0
	t_str=0
	t_float=0
	
	For t=1 To 15
		var_analyse_str(t)=""
		var_analyse_int(t)=0
		var_analyse_float(t)=0
	Next
	
	While code$<>""
		If info$="" Then RuntimeError "Data trop courte par rapport au code :"+code$+" (msg_type : "+msg_type$+")" ;Return 0 ; ça n'a pas fonctionné
		;; chercher le premier #
		good=0
		Repeat 
			k_max=0
			k=1
			While Mid(info$,k,1)<>"#"
				k=k+1
				k_max=k
			Wend
			If k_max<2 ; cas ##
				info$=Mid(info$,2)
			Else
				;; extrait le mess$ de info$
				mess$=Left(info$,k_max-1)
				good=1
			EndIf
		Until good=1
		
		If k_max<>Len(info$)
			info$=Mid(info$,k_max+1)
		Else
			info$=""
		EndIf
		
		;; traduit mess$ en la variable qui nous interesse
		Select Left(code$,1)
			Case "i"
				t_int=t_int+1
				var_analyse_int(t_int)=Int(Float(mess$)) ; note arrondi au plus proche
			Case "s"
				t_str=t_str+1
				var_analyse_str(t_str)=mess$
			Case "f"
				t_float=t_float+1
				var_analyse_float(t_float)=Float(mess$)
			Case "a"
				t_str=t_str+1
				info$=mess$+info$
				var_analyse_str(t_str)=Left(info$,max(Len(info$)-1,0))
				code$=""
			Default
				RuntimeError "Code faux : "+Left(code$,1) ;Return 0	
		End Select
		If Len(code$)>1
			code$=Right(code$,Len(code$)-1)
		Else
			code$=""
		EndIf
	Wend
	
	Return 1 ; indiquer que c'est bon
	
End Function

Function redonner_pv(num,ratio#=1,maniere=0)
	For av.avatar=Each avatar
		If av\num=num
			calcul_bonus_equi(av\num)
			Select maniere
				Case 1 ; ajouter pv (ratio# de pv_max)
					ratio#=minf(1,maxf(0,ratio#))
					av\pv[1]=min(av\pv[2]+bonus_equi(10),av\pv[1]+Ceil((av\pv[2]+bonus_equi(10))*ratio#))
				Case 2 ; redonner <ratio#> pv
					av\pv[1]=max(min(av\pv[1],av\pv[2]+bonus_equi(10)),Int(ratio#))
				Case 3 ; ajouter <ratio#> pv
					av\pv[1]=min(av\pv[2]+bonus_equi(10),av\pv[1]+Int(ratio#))
				Default ;case 0, type médic
					ratio#=minf(1,maxf(0,ratio#))
					av\pv[1]=max(min(av\pv[1],av\pv[2]+bonus_equi(10)),Ceil(ratio#*(av\pv[2]+bonus_equi(10))))
			End Select
		EndIf
	Next
End Function

Function redonner_pv_pj(ratio#=1,maniere=0)
	For av.avatar=Each avatar
		If av\num>0 And av\num<4 ;entre 1 et 3 sont les PJ
			redonner_pv(av\num,ratio#,maniere)
		EndIf
	Next	
End Function

Function numero_butin()
	num=0
	For butin.butin=Each butin
		num=max(num,butin\num)
	Next
	Return num+1
End Function

Function stack_butin(num,tri_loot=0)
	For butin.butin=Each butin
		If butin\num=num
			If tri_loot=0
				;stack loot
				action=0
				For t=1 To 250
					u=0
					While action=0
						If butin\loot[t]=0
							u=u+1
							If u+t>250 Then action=1
							For k=t To 249
								butin\loot[k]=butin\loot[k+1]
							Next
							butin\loot[250]=0
						Else
							action=-1
						EndIf	
					Wend
					If action=-1 Then action=0                
				Next
			Else
				;tri loot
				clear_liste_tempo()
				curseur_liste_tempo=0
				for k=1 to 250
					for t=1 to 250
						if butin\loot[t]=k
							curseur_liste_tempo=curseur_liste_tempo+1
							liste_tempo(curseur_liste_tempo)=butin\loot[t]
							butin\loot[t]=0
						EndIf
					next
				next
				for t=1 to 250
					butin\loot[t]=liste_tempo(t)
				next
			EndIf
			;tri quest
			nb_quest=0
			nb_item=0
			nb_info=0
			nb_quest_over=0
			clear_liste_tempo()	
			For t=1 To LIMITE_QUEST
				If butin\quest[t]<>0
					For q.quest_item=Each quest_item
						If q\num=butin\quest[t]
							Select q\shareable
								Case -1 ; quête
									nb_quest=nb_quest+1
									liste_tempo(nb_quest)=butin\quest[t]
								Case 0 ; objets de quête
									nb_item=nb_item+1
									liste_tempo(nb_item+LIMITE_QUEST)=butin\quest[t]
								Case 1 ; info
									nb_info=nb_info+1
									liste_tempo(nb_info+LIMITE_QUEST*2)=butin\quest[t]
								Case -2 ; quêtes terminées
									nb_quest_over=nb_quest_over+1
									liste_tempo(nb_quest_over+LIMITE_QUEST*3)=butin\quest[t]
							End Select						
						EndIf
					Next
				EndIf
			Next
			stack_liste_tempo()
			;copier quest
			For t=1 To LIMITE_QUEST
				butin\quest[t]=liste_tempo(t)			
			Next
		EndIf
	Next
End Function

;type_search : 1 ajoute (si pas déjà en stock), 2 enlève -1, 3 possède ?, 4 ajoute +1 (même si déjà en stock), 5 enlève tous
Function item_quest(num_butin,num_item,type_search=3)
	For bu.butin=Each butin
		If bu\num=num_butin
			Select type_search
				Case 1
					good=0
					For t=1 To LIMITE_QUEST
						If bu\quest[t]=num_item Then good=-1
					Next
					For t=1 To LIMITE_QUEST
						If good=0 And bu\quest[t]=0 Then bu\quest[t]=num_item:good=1
					Next
					Return good
				Case 2
					good=0
					For t=1 To LIMITE_QUEST
						If bu\quest[t]=num_item And good=0 Then bu\quest[t]=0:good=1
					Next
					Return good
				Case 4
					good=0
					For t=1 To LIMITE_QUEST
						If good=0 And bu\quest[t]=0 Then bu\quest[t]=num_item:good=1
					Next
					Return good
				Case 5
					good=0
					For t=1 To LIMITE_QUEST
						If bu\quest[t]=num_item Then bu\quest[t]=0:good=1
					Next
					Return good
				Default ; et Case 3
					good=0
					For t=1 To LIMITE_QUEST
						If bu\quest[t]=num_item Then good=good+1
					Next
					Return good
			
			End Select
		EndIf
	Next
End Function

;Reverse le contenu "loot" du butin source dans le butin target. A réserver aux poubelles et comptoirs vers butin
Function reverser_butin(num_source,num_target)
	t_butin=1
	For comptoir.butin=Each butin
		If comptoir\num=num_source
			For butin.butin=Each butin
				If butin\num=num_target
					For t_comptoir=1 to 250
						If comptoir\loot[t_comptoir]>0
							While butin\loot[t_butin]<>0
								t_butin=t_butin+1
								If t_butin=251 ; ne devrait normalement pas arriver, sauf si le butin source est trop lourd pour rentrer dans le butin target
									butin\loot[250]=0
									t_butin=250
								Endif
							Wend
							butin\loot[t_butin]=comptoir\loot[t_comptoir]
							comptoir\loot[t_comptoir]=0
						EndIf
					Next
				EndIf
			Next
		EndIf
	Next
End Function

Function item_give(num,num_objet)
	For butin.butin=Each butin
		If butin\num=num
			good=0
			For t=1 To 250
				If butin\loot[t]=0 And good=0
					butin\loot[t]=num_objet
					good=1
				EndIf
			Next
		EndIf
	Next
	Return good
End Function

;valeur : 1 caps, 2 junk
Function prix(num_butin,type_valeur=1)
	valeur=-1
	For butin.butin=Each butin
		If butin\num=num_butin
			valeur=0
			For t=1 to 250
				If butin\loot[t]>0
					If butin\loot[t]>200
						For special.special=Each special
							If special\num=butin\loot[t]
								If type_valeur=1
									valeur=valeur+special\caps
								Else
									valeur=valeur+special\junk
								EndIf
							EndIf
						Next
					ElseIf butin\loot[t]>150
						For boiler.boiler=Each boiler
							If boiler\num=butin\loot[t]
								If type_valeur=1
									valeur=valeur+boiler\caps
								Else
									valeur=valeur+boiler\junk
								EndIf
							EndIf
						Next
					ElseIf butin\loot[t]>100
						For armure.armure=Each armure
							If armure\num=butin\loot[t]
								If type_valeur=1
									valeur=valeur+armure\caps
								Else
									valeur=valeur+armure\junk
								EndIf
							EndIf
						Next
					Else
						For arme.arme=Each arme
							If arme\num=butin\loot[t]
								If type_valeur=1
									valeur=valeur+arme\caps
								Else
									valeur=valeur+arme\junk
								EndIf
							EndIf
						Next
					EndIf
				EndIf
			Next
		EndIf
	Next
	If valeur=-1
		RunTimeError "Butin n°"+num_butin+" introuvable dans Prix()"
	Else
		Return valeur
	EndIf
End Function

;num de l'avatar
Function calcul_pression(num,val=0)
	pression=0
	For t=1 To 5
		pression=pression+t*have_rule(num,101+t)
	Next
	If val=1 ; juste la valeur totale de pression consommée par l'équipement
		Return pression
	Else ; peut-on porter l'équipement déjà sur soit (niveau vapeur)
		For av.avatar=Each avatar
			If av\num=num
				If av\equi[5]<>0
					For boi.boiler=Each boiler
						If boi\num=av\equi[5]
							If pression>boi\capacite
								Return 0
							Else
								Return 1
							EndIf
						EndIf
					Next
				Else
					If pression>0
						Return 0
					Else
						Return 1
					EndIf
				EndIf
			EndIf
		Next	
	EndIf
	Return 0
End Function

Function calcul_encombrement(num,val=0)
	encombrement=0
	For t=1 To 4
		encombrement=encombrement+t*have_rule(num,149+t,1) ; compter tous les équipements sauf l'arme en main
	Next
	Return encombrement
End Function

Function maj_map(num,val=0,new_val=0)
	For map.map=Each map
		If map\num=num
			If val<>0 Then map\stat[val]=new_val
		;	mess$=num+"#"
		;	For t=1 To 25
		;		mess$=mess$+map\stat[t]+"#"
		;	Next
		;	new_log(mess$)			
		EndIf
	Next
End Function

Function map_stat(num,val)
	val=max(1,min(25,val))
	For map.map=Each map
		If map\num=num
			Return map\stat[val]
		EndIf
	Next
End Function


; attendre un message adressé à une ID particulière et/ou d'un type particulier
; retourne les données du message.
Function wait_for_msg$(msg_to=0,msg_type=0)
	action=0
	While action=0
		While RecvNetMsg()=False
			If KeyHit(01) Then End
		Wend
		;RuntimeError NetMsgTo()
		If NetMsgTo()=msg_to Or msg_to=0
			If msg_type=0 Or msg_type=NetMsgType()
				Return NetMsgData$()
			EndIf
		EndIf
		If KeyHit(01) Then End
	Wend
End Function


;; parce que ça marche pas :'( et que j'en ai marre de retaper le tout à chaque fois
Function var_pl_grp(num,var$,new_var=False,value$="1")
	For gr.groupe=Each groupe
		If gr\num=-1
			Select var$
				Case "map"
					If new_var=True Then gr\map=Int(value$)
					Return gr\map
				Case "animation"
					If new_var=True Then gr\animation=Int(value$)
					Return gr\animation
				Case "num" ; (verrouillé)
					Return gr\num
				Case "pivot" ; (verrouillé)
					Return gr\pivot
				Case "manikin[1]" ; (verouillé)
					Return gr\manikin[1]
				Case "position[4]"
					If new_var=True Then gr\position#[4]=Int(value$)
					Return Int(gr\position#[4])
				Default
					RuntimeError "'"+var$+"' n'est une valeur définie pour var_pl_grp"
			End Select
		EndIf
	Next		
	Return -314	
End Function

;; parce que ça marche pas :'( et que j'en ai marre de retaper le tout à chaque fois
Function var_pl_av(num,var$,new_var=False,value$="1")
	Return -314
End Function

Function var_pl_av_str$(num,var$,new_var=False,value$="none")
	For player.player=Each player
		If player\num=num
			For av.avatar=Each avatar
				If av\num=player\avatar
					Select var$
						Case "name"
							If new_var=True Then av\name$[Int(options#(7))]=value$
							Return av\name$[Int(options#(7))]				
						Default
							RuntimeError "'"+var$+"' n'est une valeur définie pour var_pl_av_str$"
					End Select
				EndIf
			Next		
		EndIf
	Next
	Return -314
End Function


Function start_loop(endroit$="")
	Cls
	WaitTimer frame_timer
	timer_animation#=timer_animation#+delta_frame*0.05
	If timer_animation#>5040 Then timer_animation#=0 ;(2*3*4*5*6*7)
	timer_animation2#=timer_animation2#+delta_frame*0.05
	If timer_animation2#>5040 Then timer_animation2#=0 ;(2*3*4*5*6*7)
	
	activator_actif=0
	
	Select endroit$
		Case "pause"
			If keys(90,2)=50 Then keys(90,2)=49:analyse(97)
		Case "options"
		
		Case "Head_Menu"
			If KeyHit(01)
				reponse=0
				;If mode_debug=1 Then reponse=1
				While reponse=0
					start_loop("sans pause")
					lire_clavier()
					disc_len#=10000
					Select Int(options#(7))
						Case 1
							quitter$="Quitter le jeu ?"
						Case 2
							quitter$="Quit the game and return to Windows?"
					End Select
					reponse=fenetreyn(quitter$)
					DrawImage curseur,MouseX(),MouseY()
					Flip
					compensation_lag()
				Wend
				If reponse=1 Then End
			EndIf
			
		Case "sans pause"
			;ne rien faire ?
		Default
			If keys(90,2)=50 Then keys(90,2)=49:analyse(97)
	End Select	
End Function

Function animation()
	For grp.groupe=Each groupe
		If grp\animation<>grp\old_animation ;animations lancées une seule fois
			Select grp\animation
				Case 1 ;md2 idle
					If grp\manikin[1]>0 Then AnimateMD2 grp\manikin[1],1,0.05*50/Float(nb_frame),0,5
					If grp\manikin[2]>0 Then AnimateMD2 grp\manikin[2],1,0.05*50/Float(nb_frame),0,5
					If grp\manikin[3]>0 Then AnimateMD2 grp\manikin[3],1,0.05*50/Float(nb_frame),0,5
				Case 2 ;md2 walk
					If grp\manikin[1]>0 Then AnimateMD2 grp\manikin[1],1,0.2*50/Float(nb_frame),40,46
					If grp\manikin[2]>0 Then AnimateMD2 grp\manikin[2],1,0.2*50/Float(nb_frame),40,46
					If grp\manikin[3]>0 Then AnimateMD2 grp\manikin[3],1,0.2*50/Float(nb_frame),40,46
				Case 3 ;md2 die
					If grp\manikin[1]>0 Then AnimateMD2 grp\manikin[1],3,0.1*50/Float(nb_frame),178,183
					If grp\manikin[2]>0 Then AnimateMD2 grp\manikin[2],3,0.1*50/Float(nb_frame),178,183
					If grp\manikin[3]>0 Then AnimateMD2 grp\manikin[3],3,0.1*50/Float(nb_frame),178,183
				Case 4 ;md2 fall
					If grp\manikin[1]>0 Then AnimateMD2 grp\manikin[1],3,0.1*50/Float(nb_frame),66,68,2
					If grp\manikin[2]>0 Then AnimateMD2 grp\manikin[2],3,0.1*50/Float(nb_frame),66,68,2
					If grp\manikin[3]>0 Then AnimateMD2 grp\manikin[3],3,0.1*50/Float(nb_frame),66,68,2
				Case 5 ;md2 reception
					If grp\manikin[1]>0 Then AnimateMD2 grp\manikin[1],3,0.2*50/Float(nb_frame),68,71
					If grp\manikin[2]>0 Then AnimateMD2 grp\manikin[2],3,0.2*50/Float(nb_frame),68,71
					If grp\manikin[3]>0 Then AnimateMD2 grp\manikin[3],3,0.2*50/Float(nb_frame),68,71
					
				Case 11;X idle
				
				Case 12;X walk
				
				Default
				
			End Select
		Else ; animation "permanentes"
			Select grp\animation
				Case 13;rat.x die
					If grp\manikin[1]>0
						If Abs(mod360(EntityPitch#(grp\manikin[1],0)))>35 Or EntityRoll#(grp\manikin[1],0)<90
							;new_log("Pitch "+mod360(EntityPitch#(grp\manikin[1],0)))
							;new_log("Roll "+mod360(EntityRoll#(grp\manikin[1],0)))
							TurnEntity grp\manikin[1],15,0,0
						EndIf
					EndIf
					
				Case 21; mongolfière idle
					If grp\manikin[2]>0
						TurnEntity grp\manikin[2],0,0,0.4*delta_frame
						If Rnd#(0,1)>0.5 Then create_smoke(EntityX#(grp\manikin[3],1),EntityY#(grp\manikin[3],1),EntityZ#(grp\manikin[3],1),0.05*(0.7+Rnd#(0,0.6)),0.4*(0.6+Rnd#(0.8)),0,-0.05,0,1000+Rnd(0,2500),80,80,80,2)
					EndIf
				
				Case 22; mongolfière walk
					If grp\manikin[2]>0
						TurnEntity grp\manikin[2],0,0,0.8*delta_frame						
						create_smoke(EntityX#(grp\manikin[3],1),EntityY#(grp\manikin[3],1),EntityZ#(grp\manikin[3],1),0.05*(0.7+Rnd#(0,0.6)),0.5*(0.6+Rnd#(0.8)),0,0,0,1000+Rnd(0,2500),0,0,0,2)
					EndIf
				
				Case 23; mongolfière die
					If grp\pivot<>0
						If Rnd#(0,1)>0.5 Then create_smoke(EntityX#(grp\pivot),EntityY#(grp\pivot),EntityZ#(grp\pivot),0.2*(0.7+Rnd#(0,0.6)),0.4*(0.6+Rnd#(0.8)),0,-0.05,0,1000+Rnd(0,2500),80,80,80,2)
						If grp\manikin[3]<>0
							;supprimer les manikins
							FreeEntity grp\manikin[1]
							;FreeEntity grp\manikin[2]
							;FreeEntity grp\manikin[3]
							grp\manikin[1]=0
							grp\manikin[2]=0
							grp\manikin[3]=0
							;load les débris
							grp\manikin[1]=LoadMesh("objets\Patrouilleur_volant\Patrouilleur_mort.X",grp\pivot)
							a#=1.5
							ScaleEntity grp\manikin[1],a#,a#,a#
							PositionEntity grp\manikin[1],EntityX(grp\manikin[1]),EntityY(grp\manikin[1])+1,EntityZ(grp\manikin[1])
						EndIf
					EndIf
		
		
			End Select			
		EndIf
		If grp\manikin[1]<>0 Then grp\old_animation=grp\animation
		If grp\animation=5 And grp\manikin[1]>0
			If MD2AnimTime(grp\manikin[1])>70.9
				grp\animation=1
			EndIf
		EndIf
	Next
End Function

Function compensation_lag()
	frame_b=MilliSecs()
	delta_frame=frame_b-frame_a
	frame_a=frame_b
	If delta_frame>0 And delta_frame<1000
		coeff_frame#=delta_frame/1000.*nb_frame
	EndIf
	tour_frame=Floor(coeff_frame#+reste_frame#)
	reste_frame#=(coeff_frame#+reste_frame#)-tour_frame
End Function


Function new_log(mess$,r=255,g=255,b=255)
	For t=1 To 7
		disc_ligne(t)=""
	Next
	
	t=0
	k=0
	amax=Len(mess$)	
	max_ligne=64 ;80
	For a=1 To amax
		t=t+1
;		If Mid$(mess$,t,1)="#"
;			k=k+1
;			disc_ligne$(k)=Left$(mess$,t-1)
;			mess$=Right$(mess$,Len(mess$)-t)
;			t=0
;		EndIf
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
	
	For t=1 To 7
		disc_ligne$(t)=Replace(disc_ligne$(t),"~"," ")
		disc_ligne$(t)=Replace(disc_ligne$(t),"\n"," ")
	Next

	For t=200 To k+1 Step -1
		log_mess$(t,1)=log_mess$(t-k,1)
		log_mess$(t,2)=log_mess$(t-k,2)
		log_color(t,1)=log_color(t-k,1)
		log_color(t,2)=log_color(t-k,2)
		log_color(t,3)=log_color(t-k,3)
	Next
	
	For t=1 To k
		log_mess$(k+1-t,1)=disc_ligne$(t)
		log_color(k+1-t,1)=r
		log_color(k+1-t,2)=g
		log_color(k+1-t,3)=b
		log_mess$(k+1-t,2)="#"
	Next
	log_mess$(k,2)=CurrentTime$()
End Function

Function update_boutons_menu()
	For bo.bouton_option=Each bouton_option
		Select bo\num
			Case 2
				Select options_buffer#(1)
					Case 1
						bo\nom$[1] = " Difficulté : Normale "
						bo\desc$[1]="Can I play, Daddy?"
						bo\nom$[2] = " Normal Mode "
						bo\desc$[2]="For those who want the story first."
					Case 2
						bo\nom$[1] = " Difficulté : Hard "
						bo\desc$[1]="Bring'em on!"
						bo\nom$[2] = " Hard Mode "
						bo\desc$[2]="For those who want a reasonnable challenge."
					Case 3
						bo\nom$[1] = " Difficulté : Infernal "
						bo\desc$[1]="I am Death incarnate!"
						bo\nom$[2] = " Infernal Mode "
						bo\desc$[2]="For those who want the opportunity to rage quit due to RNG shenanigans."
				End Select
			Case 3
				If options_buffer#(2)=1
					bo\nom$[1] = " Fréquence : 30 ips "
					bo\nom$[2] = " Framerate : 30 fps "
				Else
					bo\nom$[1] = " Fréquence : 50 ips "
					bo\nom$[2] = " Framerate : 30 fps "
				EndIf
				bo\desc$[1]= "Le nombre d'images par seconde. Le jeu compense le lag donc l'influence n'est qu'esthétique."
				bo\desc$[2]= "The number of frames per seconds. No 120fps/1080p because the game engine is mostly from 1997."
			Case 4
				Select options_buffer#(4)
					Case 1
						bo\nom$[1]= " Mode caméra A : Liée par défaut (alt pour Fixe) "
						bo\nom$[2]= " Camera Mode A: Linked by default (Alt to Set) "
					Case 2
						bo\nom$[1]= " Mode caméra B : Alt pour alterner en Fixe et Liée "
						bo\nom$[2]= " Camera Mode B: Alt to toggle between Linked and Set "
					Case 3
						bo\nom$[1]= " Mode caméra C : Fixe par défaut (alt pour Liée) "
						bo\nom$[2]= " Camera Mode C: Set by default (Alt to Linked) "
				End Select
				bo\desc$[1]="Liée : la caméra bouge avec la souris. Fixe : la caméra est fixe et on peut utiliser la souris."
				bo\desc$[2]="Linked: the camera is directly controled by the mouse. Set: the camera doesn't move and you can use the mouse cursor."
			
			Case 5
				bo\nom$[1] = "  Volume musique : "+Str(Int(options_buffer#(5)*100))+"%  "
				bo\desc$[1]= "Le volume de la musique de fond. Clic gauche pour augmenter, clic droit pour diminuer."
				bo\nom$[2] = "  Music Volume : "+Str(Int(options_buffer#(5)*100))+"%  "
				bo\desc$[2]= "Music Volume. Left mouse to increase, right mouse to decrease."
			
			Case 6
				bo\nom$[1] = "  Volume bruitage : "+Str(Int(options_buffer#(6)*100))+"%  "
				bo\desc$[1]= "Le volume des bruitages. Clic gauche pour augmenter, clic droit pour diminuer."
				bo\nom$[2] = "  SFX Volume : "+Str(Int(options_buffer#(6)*100))+"%  "
				bo\desc$[2]= "SFX Volume. Left mouse to increase, right mouse to decrease."
				
			Case 7
				bo\nom$[1] = "Défilement du texte : "+options_buffer#(3)
				bo\desc$[1]= "La vitesse de défilement des textes. 2,5 est une bonne valeur."
				bo\nom$[2] = "Text scrolling speed: "+options_buffer#(3)
				bo\desc$[2]= "Speed of the scrolling in dialogues. 2,5 is a good value."
			Case 8
				bo\nom$[1]="Français"
				bo\nom$[2]="English"
				bo\desc$[1]="Langue actuellement utilisée."
				bo\desc$[2]="Language currently used."			
		End Select
	Next
End Function

Function menu_option()
	sortie=0
	action=1
	;charger les options actuelles et modifier les boutons en conséquence
	For t=1 To 10
		options_buffer#(t)=options#(t)
	Next
	options_buffer#(1)=DIFFICULTY
	update_boutons_menu()
	
	cuir1=LoadImage("textures\loran\fond-cuir-noir.jpg")
	
	While sortie=0
		Cls
		start_loop("options")
		mouseclic1=MouseHit(01)
		mouseclic2=MouseHit(02)
		description$=""
		
		DrawImage cuir1,0,-5
		frame=reste(Int(timer_animation#*0.25),90)
		If engrenage_deco(frame)>0 Then DrawImage engrenage_deco(frame),31,GraphicsHeight()/2
		
		; Color 200,200,200
		; SetFont big_font
		; Text 400,50,"Options",1,1
		
		For b.bouton_option = Each bouton_option
			;calcul du rectangle
			temp=Int(b\len_max*6*screeny#)
			drawgrey(400-temp-1,(b\y-15)*screeny#-1,temp*2+1,30*screeny#+1,0.66,2)
			
			If MouseX()>400-temp And MouseX()<400+temp And MouseY()>(b\y-13)*screeny# And MouseY()<(b\y+13)*screeny#
				Color b\act_couleur[1],b\act_couleur[2],b\act_couleur[3]
				If mouseclic1 Then action=b\effet:mouseclic1=0
				If mouseclic2 And b\clic2 Then action=-b\effet:mouseclic2=0
				description$=b\desc$[Int(options_buffer#(7))]
			Else
				Color b\ina_couleur[1],b\ina_couleur[2],b\ina_couleur[3]
			EndIf
			Rect 400-temp,(b\y-15)*screeny#,temp*2,30*screeny#,0
			SetFont big_font
			Text 400,b\y*screeny#,b\nom$[Int(options_buffer#(7))],1,1
		Next
		
		If KeyHit(01) Then action=0
		
		Select action
			Case 0 ; quitter sans sauvegarder
				KeyHit(01)
				sortie=1
				Playsound2(sons_menu(01))
				If ch_bgm1>0 Then ChannelVolume ch_bgm1,vol_bgm1#*options#(5)
			Case -1 ; sauvegarder
				For t=1 To 10
					options#(t)=options_buffer#(t)
				Next
				export_options()
				
				DIFFICULTY=options_buffer#(1)
				Playsound2(sons_menu(1))
				;new_log("Options modifiées")
				action=0
			Case 2 ; Fond (+)
				options_buffer#(1)=options_buffer#(1)+1
				If options_buffer#(1)>3 Then options_buffer#(1)=1
				update_boutons_menu()
				Playsound2(sons_menu(11))
				action=1
			Case -2 ; Fond (-)
				options_buffer#(1)=options_buffer#(1)-1
				If options_buffer#(1)<1 Then options_buffer#(1)=3
				update_boutons_menu()
				Playsound2(sons_menu(11))
				action=1
			Case 3 ; Fréquence
				options_buffer#(2)=3-options_buffer#(2)
				update_boutons_menu()
				Playsound2(sons_menu(11))
				action=1
			Case 4 ; Camera (+)
				options_buffer#(4)=options_buffer#(4)+1
				If options_buffer#(4)=4 Then options_buffer#(4)=1
				update_boutons_menu()
				Playsound2(sons_menu(11))
				action=1
			Case -4 ; Caméra (-)
				options_buffer#(4)=options_buffer#(4)-1
				If options_buffer#(4)=0 Then options_buffer#(4)=3
				update_boutons_menu()
				Playsound2(sons_menu(12))
				action=1
			Case 5 ; musique (+)
				options_buffer#(5)=options_buffer#(5)+0.1
				If options_buffer#(5)>1 Then options_buffer#(5)=1
				update_boutons_menu()
				Playsound2(sons_menu(11))
				If ch_bgm1>0 Then ChannelVolume ch_bgm1,vol_bgm1#*options_buffer#(5)
				action=1
			Case -5 ; musique (-)
				options_buffer#(5)=options_buffer#(5)-0.1
				If options_buffer#(5)<0 Then options_buffer#(5)=0
				update_boutons_menu()
				If ch_bgm1>0 Then ChannelVolume ch_bgm1,vol_bgm1#*options_buffer#(5)
				Playsound2(sons_menu(12))
				action=1
			Case 6 ; bruitage (+)
				options_buffer#(6)=options_buffer#(6)+0.1
				If options_buffer#(6)>1 Then options_buffer#(6)=1
				update_boutons_menu()
				ch_son=Playsound(sons_battle(2))
				ChannelVolume ch_son,options_buffer#(6)
				action=1
			Case -6 ; bruitage (-)
				options_buffer#(6)=options_buffer#(6)-0.1
				If options_buffer#(6)<0 Then options_buffer#(6)=0
				update_boutons_menu()
				ch_son=Playsound(sons_battle(2))
				ChannelVolume ch_son,options_buffer#(6)
				action=1			
			Case 7 ; texte (+)
				options_buffer#(3)=options_buffer#(3)+0.5
				If options_buffer#(3)>5 Then options_buffer#(3)=5
				update_boutons_menu()
				Playsound2(sons_menu(11))
				action=1
			Case -7 ; texte (-)
				options_buffer#(3)=options_buffer#(3)-0.5
				If options_buffer#(3)<1.5 Then options_buffer#(3)=1.5
				update_boutons_menu()
				Playsound2(sons_menu(12))
				action=1
			Case 8 ; toogle Langue (FR, EN)
				options_buffer#(7)=3-options_buffer#(7)
				update_boutons_menu()
				Playsound2(sons_menu(12))
				action=1

		End Select
		
		Color 200,200,200
		SetFont little_font
		Text 400,screenheight-20*screeny#,description$,1
		DrawImage curseur,MouseX(),MouseY()

		Flip
	Wend
	
	FlushKeys
	FlushMouse
	lire_clavier()
End Function

Function first_language_choice()
	If middle_font=0
		middle_font=LoadFont("Constantia",25*screeny#)
	EndIf
	options#(7)=0
	While options#(7)=0
		;start_loop("sans pause")
		Cls
		lire_clavier()
		disc_len#=10000
		options#(7)=fenetreyn("Choose your language / Choississez votre langue","English","Français")
		DrawImage curseur,MouseX(),MouseY()
		Flip
		;compensation_lag()
	Wend
	options#(7)=3-options#(7)
End Function

Function export_options()
	If FileType("options.dat")=1 Then DeleteFile "options.dat"
	file_option=WriteFile("options.dat")
	For t=1 To 10
		WriteFloat file_option,options#(t)
	Next
	CloseFile file_option
End Function

Function cinematique_de_fin()
	FlushKeys
	FlushMouse
	StopChannel ch_bgm
	Cls
	Color 255,255,255
	game_over_snd=LoadSound("musiques\victoire2.wav")
	PlaySound game_over_snd
	sortie=0
	While sortie<2
		Cls
		lire_clavier()
		timer_animation#=timer_animation#+1
		delta_frame=20
		If sortie=0
			sortie=fenetre_info("Félicitation, vous vous êtes échappé de FactoryTec !!")
		Else
			sortie=sortie+fenetre_info("Malheureusement, je n'ai pas le temps de faire une cinématique de fin, donc si vous en voulez une, dites nous que vous avez beaucoup aimé Final Whim et que vous aimeriez qu'on se botte les fesses.")
		EndIf
		Flip
		If KeyHit(01) Then sortie=2
	Wend
	quitter_jeu=1
End Function



;===========================================================Faux Serveur===============================================================




Function update_combat()
	For combat.combat=Each combat
		Select combat\phase
			Case -1 ; initialisation
				;new_log("Phase -1 : initialisation")
				;réinit des vars
				For t=1 To 18
					initiative#(t,1)=0
					initiative#(t,2)=0
				Next			
				;créer les avatars non-créés et formation
				For t=1 To 2
					For gr.groupe=Each groupe
						If gr\num=combat\groupe[t]
							;creer le groupe par un script pour les groupes d'IA
							If gr\num>0 Then script_serveur(gr\script[4],gr\num):;new_log("Load groupe script :"+gr\script[4])
							;vérifier si il n'y a pas de doublons (sinon, on enlève le deuxième)						
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
				Next
				;MAJ des avatars pour tout le monde
				ai=0
				For avatar.avatar=Each avatar
					If avatar\groupe=combat\groupe[1] Or avatar\groupe=combat\groupe[2]
						;rajout à l'initiative tant qu'à faire (valeur de base fixe + départageur en cas d'égalité)
						calcul_bonus_equi(avatar\num)
						ai=ai+1
						initiative#(ai,2)=avatar\num
						initiative#(ai,1)=avatar\init+Rnd#(0,1)+bonus_equi(11)
						good=0
						;charger aussi les armes tant qu'à faire
						For q=1 To 3
							If avatar\equi[q]<>0
								For arme.arme=Each arme
									If arme\num=avatar\equi[q]
										avatar\charge[q]=arme\charge
									EndIf
								Next
							EndIf
						Next
					EndIf
				Next			
				;faire l'ordre
				;par ordre décroissante d'initiative (avec un jet de dé pour départager les égalités)
				For t=1 To 17
					For k=t To 18
						If initiative#(k,1)>initiative#(t,1)
							af#=initiative#(k,1)
							ai=initiative#(k,2)
							initiative#(k,1)=initiative#(t,1)
							initiative#(k,2)=initiative#(t,2)
							initiative#(t,1)=af#
							initiative#(t,2)=ai
						EndIf
					Next
				Next
				mess$="Initiative : "
				For t=1 To 18
					combat\ordre[t]=initiative#(t,2)
					mess$=mess$+combat\ordre[t]+", "
				Next
				combat\ordre[19]=0
				;new_log(mess$)
				;analyse(22,mess$,master_id)
				
				;MAJ du combat
				combat\phase=0
				combat\last_action=MilliSecs()
				
			Case 0
				;départ (et avant  : attente des joueurs)
				;sendnetMsg(25,"0#"+Str(combat\num)+"#",master_id,0,1)
				msg$=combat\num+"#"+combat\groupe[1]+"#"+combat\groupe[2]+"#1#0#"
				analyse(21,msg$,master_id)
				combat\qui=1
				combat\old_qui=-1
				;new_log("Le combat n°"+Str(combat\num)+" est lancé.")
				combat\last_action=MilliSecs()
				
				combat\phase=1
				mess$=combat\num+"#1#" ; qui=1
				For t=1 To 18
					combat\ordre[t]=initiative#(t,2)
					mess$=mess$+combat\ordre[t]+"#"
				Next
				combat\ordre[19]=0
				mess$=mess$+"0#0#1#"
				;sendnetMsg(22,mess$,master_id)
				;sendnetMsg(21,combat\num+"#"+combat\groupe[1]+"#"+combat\groupe[2]+"#"+combat\phase+"#"+combat\tour+"#",master_id)

			Case 1 
				If combat\qui<1 Or combat\qui>18 Then combat\qui=1
				If combat\ordre[combat\qui]=0
					combat\qui=1
					combat\phase=2
					combat\tour=combat\tour+1
					;sendnetMsg(21,combat\num+"#"+combat\groupe[1]+"#"+combat\groupe[2]+"#"+combat\phase+"#"+combat\tour+"#",master_id)
				EndIf
				If combat\qui<>combat\old_qui
					combat\last_action=MilliSecs()
					temps_restant=TEMPS_ROUND
					combat_menu_action=0
					combat_target=0
				EndIf
				combat\old_qui=combat\qui
				
				time=MilliSecs()
				If time<combat\last_action Then combat\last_action=time
				
				Color 255,0,255
				Text 105,105,Str(time-combat\last_action)
				
				For av.avatar=Each avatar
					If av\num=combat\ordre[combat\qui]
						If DEFENSE_REGEN_ACTIVE=1
							av\defense=max(min(min(av\defense+DEFENSE_REGEN,DEFENSE_MAX_REGEN),MAX_BONUS_DEF),MIN_BONUS_DEF)
						EndIf
						If Not(av\pv[1]>0) ; si mort
							analyse(23,combat\num+"#"+av\num+"#-1#s#s#s#s#s#s#")
						Else
							If av\prop=0 ; IA
								If time-combat\last_action>combat\anim_time
									If av\activated=0
										If av\last_action=22
											analyse(23,combat\num+"#"+av\num+"#221#s#s#s#s#s#s#")
											av\last_action=0
										EndIf
										If ia(combat\num,av\num,cat_to_ia(av\cat))=-1
											ia(combat\num,av\num,1)
										EndIf
										av\activated=1
										combat\last_action=MilliSecs()
									EndIf
								Else
									av\activated=0
								EndIf
							Else ; Joueur
								If time-combat\last_action>combat\anim_time
									If av\last_action=22
										analyse(23,combat\num+"#"+av\num+"#221#s#s#s#s#s#s#")
										av\last_action=0
									EndIf
								EndIf
							;	If time-combat\last_action>TEMPS_ROUND+4000 ; le joueur met trop de temps (10s plus 2s d'animation plus 2s de large au cas où)
							;		analyse(23,combat\num+"#"+av\num+"#0#s#s#s#s#s#s#")
							;		combat\last_action=MilliSecs()
							;	EndIf
							EndIf
						EndIf
					EndIf
				Next
				
				;condition de victoire
				pas_d_ennemi=1
				For u=1 To 2
					For gr.groupe=Each groupe
						If gr\num=combat\groupe[u]
							if gr\num>1 Then pas_d_ennemi=0;:runtimeerror("Y'a un grp ennemi "+gr\num)
							pv_left=0
							For i=1 To 9
								If gr\formation[i]<>0
									For av.avatar=Each avatar
										If av\num=gr\formation[i]
											pv_left=pv_left+max(0,av\pv[1])
										EndIf
									Next
								Endif
							Next
							If pv_left=0 ; groupe vaincu
								combat\phase=3
								analyse(21,combat\num+"#"+combat\groupe[u]+"#"+combat\groupe[Abs(2-u)+1]+"#3#"+combat\tour,master_id) ; le groupe vaincu en premier
							EndIf
						EndIf
					Next
				Next
				if pas_d_ennemi=1
					analyse(21,combat\num+"#0#-1#3#"+combat\tour,master_id)
				EndIf
				
			Case 2
				If combat\qui<1 Then combat\qui=1
				If combat\ordre[combat\qui]=0
					combat\qui=1
					combat\tour=combat\tour+1
					;sendnetMsg(21,combat\num+"#"+combat\groupe[1]+"#"+combat\groupe[2]+"#"+combat\phase+"#"+combat\tour+"#",master_id)
				EndIf
				If combat\qui<>combat\old_qui Then combat\last_action=MilliSecs()
				combat\old_qui=combat\qui
				
				time=MilliSecs()
				If time<combat\last_action Then combat\last_action=time
				
				Color 255,0,255
				Text 105,105,Str(time-combat\last_action)
				
				For av.avatar=Each avatar
					If av\num=combat\ordre[combat\qui]
						If DEFENSE_REGEN_ACTIVE=1
							av\defense=max(min(min(av\defense+DEFENSE_REGEN,DEFENSE_MAX_REGEN),MAX_BONUS_DEF),MIN_BONUS_DEF)
						EndIf
						If Not(av\pv[1]>0) ; si mort
							analyse(23,combat\num+"#"+av\num+"#-1#s#s#s#s#s#s#")
						Else
							If av\prop=0 ; IA
								If time-combat\last_action>combat\anim_time
									If av\activated=0
										If av\last_action=22
											analyse(23,combat\num+"#"+av\num+"#221#s#s#s#s#s#s#")
											av\last_action=0
										EndIf
										If ia(combat\num,av\num,cat_to_ia(av\cat))=-1
											ia(combat\num,av\num,1)
										EndIf
										av\activated=1
										combat\last_action=MilliSecs()
									EndIf
								Else
									av\activated=0
								EndIf
							Else ; Joueur
								If time-combat\last_action>combat\anim_time
									If av\last_action=22
										analyse(23,combat\num+"#"+av\num+"#221#s#s#s#s#s#s#")
										av\last_action=0
									EndIf
								EndIf
							;	If time-combat\last_action>TEMPS_ROUND+4000 ; le joueur met trop de temps (10s plus 2s d'animation plus 2s de large au cas où)
							;		analyse(23,combat\num+"#"+av\num+"#0#s#s#s#s#s#s#")
							;		combat\last_action=MilliSecs()
							;	EndIf
							EndIf
						EndIf
					EndIf
				Next

				
				;condition de victoire
				pas_d_ennemi=1
				For u=1 To 2
					For gr.groupe=Each groupe
						If gr\num=combat\groupe[u]
							if gr\num>0 Then pas_d_ennemi=0
							pv_left=0
							For i=1 To 9
								For av.avatar=Each avatar
									If av\num=gr\formation[i]
										pv_left=pv_left+max(0,av\pv[1])
									EndIf
								Next
							Next
							If pv_left=0 ; groupe vaincu
								combat\phase=3
							;	new_log("Le combat n°"+combat\num+" est terminé. Victoire pour le groupe n°"+combat\groupe[Abs(2-u)+1],250,100,0)
								analyse(21,combat\num+"#"+combat\groupe[u]+"#"+combat\groupe[Abs(2-u)+1]+"#3#"+combat\tour,master_id) ; le groupe vaincu en premier
							;	grp_vaincu=combat\groupe[u]
							;	grp_victorieux=combat\groupe[2-u]
							;	combat\groupe[1]=grp_vaincu
							;	combat\groupe[2]=grp_victorieux
							EndIf
						EndIf
					Next
				Next
				if pas_d_ennemi=1
					analyse(21,combat\num+"#0#-1#3#"+combat\tour,master_id)
				EndIf
	
						
			Case 3 ; débrief
				;loot
				;butin.butin=New butin
				;butin\num=numero_butin()
				;infirmière
				good=0
				For av.avatar=Each avatar
					If av\groupe=combat\groupe[2]
						If av\pv[1]>0
							If have_rule(av\num,2)
								good=1
							EndIf
						EndIf
					EndIf
				Next
				For av.avatar=Each avatar
					If av\groupe=combat\groupe[2]
						If good=1
							If have_rule(av\num,17)=0 Then av\pv[1]=max(1,av\pv[1])
						EndIf
						If av\pv[1]>0 And have_rule(av\num,3) Then good=2
					EndIf
				Next
				If good=2
					For av.avatar=Each avatar
						If av\groupe=combat\groupe[2]
							If have_rule(av\num,17)=0
								calcul_bonus_equi(av\num)
								av\pv[1]=max(av\pv[1],BONUS_MEDIC#*(av\pv[2]+bonus_equi(10)))
							EndIf
						EndIf
					Next
				EndIf
				If combat\groupe[1]<>-1 And combat\groupe[2]<>-1 Then Delete combat
				
		End Select
	Next
End Function

Function call_serveur()
	update_combat()
End Function

;Change agr\actif
;act=2 -> toggle
Function activate_agresseur(num,act=1)
	For agr.agresseur=Each agresseur
		If agr\num=num
			If act=2
				agr\actif=1-agr\actif
			Else
				agr\actif=act
			Endif
		EndIf
	Next
End Function

Function activate_patrouilleur(num,actif=1,agressif=1)
	For pat.patrouilleur=Each patrouilleur
		If pat\num=num
			If actif=2
				pat\actif=1-pat\actif
			ElseIf actif=3
				activate_agresseur(pat\agresseur,agressif)
			Else
				pat\actif=actif
			EndIf
		EndIf
	Next
End Function

Function modifier_patrouilleur(num,num_action=1,action=0,var1#=0,var2#=0,var3#=0,var4#=0)
	If num_action>NB_ACTION_PAT Or num_action<1 Then RunTimeError Str("num_action out of range ("+num_action+")")
	For pat.patrouilleur=Each patrouilleur
		If pat\num=num
			pat\actions[num_action]=action
			pat\var1#[num_action]=var1#
			pat\var2#[num_action]=var2#
			pat\var3#[num_action]=var3#
			pat\var4#[num_action]=var4#
		EndIf
	Next
End Function

Function attaque_pokemon(grp_num,anim=2)
	PauseChannel ch_bgm1
	;Playsound2(sons_menu(13))
	temps_max=06000
	sortie=0
	combat=0
	move=1
	For grp.groupe=Each groupe
		If grp\num=grp_num
			grp\animation=anim+grp\not_md2*10
			If grp\pivot<>0
				;alpha#=mod360(pointeryaw(EntityX#(grp\pivot,1),EntityZ#(grp\pivot,1),EntityX#(pl_grp_pivot),EntityZ#(pl_grp_pivot)))
				x_cible#=EntityX#(grp\pivot,1)
				z_cible#=EntityZ#(grp\pivot,1)
			Else
				;alpha#=0
				x_cible#=0
				z_cible#=0
			Endif
		EndIf	
		If grp\num=-1 Then grp\animation=1
	Next
	tempo=MilliSecs()
	While sortie=0
		start_loop()
		;bouger le méchant
		For g.groupe=Each groupe
			If g\num=grp_num And g\pivot<>0
				RotateEntity g\pivot,0,180+pointeryaw(EntityX#(g\pivot,1),EntityZ#(g\pivot,1),EntityX#(pl_grp_pivot),EntityZ#(pl_grp_pivot)),0,1
				If move=1
					MoveEntity g\pivot,0,0,0.1*coeff_frame#*(50/Float(nb_frame))
					;gestion de la gravité
					If GetEntityType(g\pivot)=type_perso
						MoveEntity g\pivot,0,2,0
						ResetEntity g\pivot
						MoveEntity g\pivot,0,-4,0
					EndIf
					If (EntityX#(pl_grp_pivot)-EntityX#(g\pivot,1))^2+(EntityZ#(pl_grp_pivot)-EntityZ#(g\pivot,1))^2<1
						move=0
						tempo=MilliSecs()-temps_max-1
					EndIf
				EndIf
			EndIf
		Next
		
		;bouger la cam
		aligner_camera(x_cible#,z_cible#,15,2,1,30,30)
		
		UpdateWorld
		RenderWorld
		
		DrawImage gfx_alerte,400,120
		
		;Hud passif
		HUD(0)
	
		If MilliSecs()-tempo>temps_max And combat=0
			;lancer le combat
			combat=1
			msg$="-1#"+grp_num+"#"
			analyse(20,msg$,player_id,master_id)
		EndIf
		If MilliSecs()-tempo>temps_max+100 Then sortie=1
		
		animation()
		call_serveur()
		compensation_lag()
		Flip
	Wend
	groupe_agresseur=grp_num
End Function

;fonction qui aligne la caméra-joueur sur une frame (il faut donc l'appeler à chaque frame pour que ça marche)
;mode=1 : aligne la caméra en visant un point de coordonnée x#,z# sans changer le pitch (l'orientation verticale)
;mode=2 : aligne la caméra en visant un point de coordonnée x#,z# et avec un pitch de pitch_cible# (0 étant l'horizontale, +plongée, -contre-plongée)
;speed# : le taux par rapport à la vitesse par défaut (speed#=2 fera donc l'alignement 2x plus vite)
;yaw_tolerance# : si la différence entre l'angle 'horizontal' (yaw) désiré et actuel est inférieure à la tolérance, la caméra ne bougera pas
;pitch_tolerance# : idem pour l'angle 'vertical' (pitch)
Function aligner_camera(x#,z#,pitch_cible#=0,mode=1,speed#=1,yaw_tolerance#=30,pitch_tolerance#=15)
	;aligner yaw
	yaw_cible#=mod360#(pointeryaw#(x#,z#,EntityX#(pl_grp_pivot),EntityZ#(pl_grp_pivot)))
	delta_yaw#=mod360#(EntityYaw#(cam_parent,1)-yaw_cible#)
	If Abs(delta_yaw#)>yaw_tolerance#
		vitesse#=-0.1*delta_frame*speed#*((Abs(delta_yaw#)-yaw_tolerance#)*0.03)^(1.5)
		msg$=vitesse#
		vitesse#=signed_norm#(vitesse#,delta_yaw#)
		TurnEntity cam_parent,0,sign(delta_yaw#)*vitesse#,0
	EndIf
	
	;aligner pitch
	If mode=2
		delta_pitch#=mod360#(EntityPitch#(cam_pivot,1)-pitch_cible#)
		If Abs(delta_pitch#)>pitch_tolerance#
		vitesse#=-0.1*delta_frame*speed#*((Abs(delta_pitch#)-pitch_tolerance#)*0.03)^(1.5)
		msg$=vitesse#
		vitesse#=signed_norm#(vitesse#,delta_pitch#)
		TurnEntity cam_pivot,sign(delta_pitch#)*vitesse#,0,0
	EndIf
	
	EndIf	

	HideEntity cam
	PositionEntity cam,0,0,0.15
	ShowEntity cam
	PositionEntity cam,0,0,-zoom_cam#*0.5
	RotateEntity cam,0,0,0,0
End Function



Function update_patrouilleur()
	;verrou pour éviter les soucis suite à un gros framedrop
	delta_frame_min=min(200,delta_frame)
	For pat.patrouilleur=Each patrouilleur
		If pat\map=player_map And pat\actif*pat\vivant=1
			en_cours=pat\en_cours
			For agr.agresseur=Each agresseur
				If agr\num=pat\agresseur
					For grp.groupe=Each groupe
						If grp\num=agr\groupe
							au_suivant=0
							Select pat\actions[en_cours]
								Case 1 ; marcher vers point
								;position d'origine
									orgx#=EntityX#(grp\pivot,1)
									orgy#=EntityY#(grp\pivot,1)
									orgz#=EntityZ#(grp\pivot,1)
								;calculer Yaw
									yaw#=pointeryaw#(orgx#,orgz#,pat\var1#[en_cours],pat\var3#[en_cours])+180
									;calculer Pitch
									dist#=Sqr((orgx#-pat\var1#[en_cours])*(orgx#-pat\var1#[en_cours])+(orgz#-pat\var3#[en_cours])*(orgz#-pat\var3#[en_cours]))
									pitch#=pointerpitch(orgy#,pat\var2#[en_cours],dist#)
								;déplacer le groupe
									grp\position#[1]=orgx#-pat\var4#[en_cours]*Sin(yaw#)*delta_frame_min*0.001
									grp\position#[2]=orgy#;+pat\var4#[en_cours]*Sin(pitch#)*delta_frame_min*0.001
									grp\position#[3]=orgz#+pat\var4#[en_cours]*Cos(yaw#)*delta_frame_min*0.001
									;rotation
										;grp\position#[4]=yaw#
										orgyaw#=EntityYaw#(grp\pivot)
										diff#=mod360(yaw#-orgyaw#)
										sens=sign(diff#)
										vitesse_ang#=200
										grp\position#[4]=orgyaw#+sens*min(vitesse_ang#*delta_frame_min*0.001,vitesse_ang#*0.1)
										If sign(sens*diff#)>-1 And Abs(diff#)<vitesse_ang#*0.1
											grp\position#[4]=yaw#
										EndIf
										yaw#=grp\position#[4]
									If dist#<pat\var4#[en_cours]*0.2
										au_suivant=1
										grp\position#[1]=pat\var1#[en_cours]
										grp\position#[2]=pat\var2#[en_cours]
										grp\position#[3]=pat\var3#[en_cours]
									EndIf
								;gestion de la collision avec un sol pas horizontal (escalier, canyon, ...)
									PositionEntity grp\pivot,grp\position#[1],grp\position#[2]+2,grp\position#[3]
									RotateEntity grp\pivot,0,grp\position#[4],0
									ResetEntity grp\pivot
									TranslateEntity grp\pivot,0,-4,0
								;animation
									grp\animation=2+grp\not_md2*10
								;déplacer l'agresseur
									agr\position#[1]=grp\position#[1]
									agr\position#[2]=grp\position#[2]
									agr\position#[3]=grp\position#[3]
									agr\polyx#[1]=grp\position#[1]
									agr\polyz#[1]=grp\position#[3]
									agr\polyx#[3]=grp\position#[1]-pat\range#*Sin(yaw#)
									agr\polyz#[3]=grp\position#[3]+pat\range#*Cos(yaw#)
									agr\polyx#[2]=grp\position#[1]-pat\range#*Sin(yaw#+pat\cone#)
									agr\polyz#[2]=grp\position#[3]+pat\range#*Cos(yaw#+pat\cone#)
									agr\polyx#[4]=grp\position#[1]-pat\range#*Sin(yaw#-pat\cone#)
									agr\polyz#[4]=grp\position#[3]+pat\range#*Cos(yaw#-pat\cone#)
								;A enlever
									If mode_debug=1
										For q=1 to 3
											PositionEntity pat\sprite[q],agr\polyx#[q+1],grp\position#[2],agr\polyz#[q+1]
										Next
									Endif
									
								Case 2 ; attendre
									If pat\var3#[en_cours]=0
										pat\var3#[en_cours]=Millisecs()
										grp\animation=pat\var2#[en_cours]+grp\not_md2*10
									ElseIf MilliSecs()-pat\var3#[en_cours]>pat\var1#[en_cours]
										pat\var3#[en_cours]=0
										au_suivant=1
									EndIf
								
								Case 3 ; Tourner vers angle (angle, vitesse_absolue, sens -0 si au plus court-)
									orgyaw#=EntityYaw#(grp\pivot)
									diff#=mod360(pat\var1#[en_cours]-orgyaw#)
									sens=pat\var3#[en_cours]
									If sens=0 Then sens=sign(diff#)
								;rotation
									grp\position#[4]=orgyaw#+sens*min(pat\var2#[en_cours]*delta_frame_min*0.001,pat\var2#[en_cours]*0.2)
									If sign(sens*diff#)>-1 And Abs(diff#)<pat\var2#[en_cours]*0.2
										au_suivant=1
										grp\position#[4]=pat\var1#[en_cours]
									EndIf
									RotateEntity grp\pivot,0,grp\position#[4],0,1
									grp\animation=1+grp\not_md2*10
								;déplacer l'agresseur
									agr\position#[1]=grp\position#[1]
									agr\position#[2]=grp\position#[2]
									agr\position#[3]=grp\position#[3]
									agr\polyx#[1]=grp\position#[1]
									agr\polyz#[1]=grp\position#[3]
									agr\polyx#[3]=grp\position#[1]-pat\range#*Sin(grp\position#[4])
									agr\polyz#[3]=grp\position#[3]+pat\range#*Cos(grp\position#[4])
									agr\polyx#[2]=grp\position#[1]-pat\range#*Sin(grp\position#[4]+pat\cone#)
									agr\polyz#[2]=grp\position#[3]+pat\range#*Cos(grp\position#[4]+pat\cone#)
									agr\polyx#[4]=grp\position#[1]-pat\range#*Sin(grp\position#[4]-pat\cone#)
									agr\polyz#[4]=grp\position#[3]+pat\range#*Cos(grp\position#[4]-pat\cone#)
								;A enlever
									If mode_debug=1
										For q=1 to 3
											PositionEntity pat\sprite[q],agr\polyx#[q+1],grp\position#[2],agr\polyz#[q+1]
										Next		
									Endif
								
								Case 4 ; Tourner vers point (x,z,vit_abs,sens)
									orgyaw#=EntityYaw#(grp\pivot)
									angle#=pointeryaw#(EntityX#(grp\pivot),EntityZ#(grp\pivot),pat\var1#[en_cours],pat\var2#[en_cours])+180
									diff#=mod360(angle-orgyaw#)
									sens=pat\var4#[en_cours]
									If sens=0 Then sens=sign(diff#)
								;rotation
									grp\position#[4]=orgyaw#+sens*min(pat\var3#[en_cours]*delta_frame_min*0.001,pat\var3#[en_cours]*0.2)
									If sign(sens*diff#)>-1 And Abs(diff#)<pat\var3#[en_cours]*0.2
										au_suivant=1
										grp\position#[4]=angle#
									EndIf
									RotateEntity grp\pivot,0,grp\position#[4],0,1
									grp\animation=1+grp\not_md2*10
								;déplacer l'agresseur
									agr\position#[1]=grp\position#[1]
									agr\position#[2]=grp\position#[2]
									agr\position#[3]=grp\position#[3]
									agr\polyx#[1]=grp\position#[1]
									agr\polyz#[1]=grp\position#[3]
									agr\polyx#[3]=grp\position#[1]-pat\range#*Sin(grp\position#[4])
									agr\polyz#[3]=grp\position#[3]+pat\range#*Cos(grp\position#[4])
									agr\polyx#[2]=grp\position#[1]-pat\range#*Sin(grp\position#[4]+pat\cone#)
									agr\polyz#[2]=grp\position#[3]+pat\range#*Cos(grp\position#[4]+pat\cone#)
									agr\polyx#[4]=grp\position#[1]-pat\range#*Sin(grp\position#[4]-pat\cone#)
									agr\polyz#[4]=grp\position#[3]+pat\range#*Cos(grp\position#[4]-pat\cone#)
								;A enlever
									If mode_debug=1
										For q=1 to 3
											PositionEntity pat\sprite[q],agr\polyx#[q+1],grp\position#[2],agr\polyz#[q+1]
										Next
									Endif
								Case 5 ; modifier le cône
									au_suivant=1
									pat\cone#=pat\var1#[en_cours]
									pat\range#=pat\var2#[en_cours]
									agr\radius=pat\range#
									
								Case 6 ; Voler (destination x,y,z, vitesse)
								;position d'origine
									orgx#=EntityX#(grp\pivot,1)
									orgy#=EntityY#(grp\pivot,1)
									orgz#=EntityZ#(grp\pivot,1)
								;calculer Yaw
									yaw#=pointeryaw#(orgx#,orgz#,pat\var1#[en_cours],pat\var3#[en_cours])+180
									;calculer Pitch
									dist#=Sqr((orgx#-pat\var1#[en_cours])*(orgx#-pat\var1#[en_cours])+(orgz#-pat\var3#[en_cours])*(orgz#-pat\var3#[en_cours]))
									pitch#=pointerpitch(orgy#,pat\var2#[en_cours],dist#)
								;déplacer le groupe
									grp\position#[1]=orgx#-pat\var4#[en_cours]*Cos(pitch#)*Sin(yaw#)*delta_frame_min*0.001
									grp\position#[2]=orgy#+pat\var4#[en_cours]*Sin(pitch#)*delta_frame_min*0.001
									grp\position#[3]=orgz#+pat\var4#[en_cours]*Cos(pitch#)*Cos(yaw#)*delta_frame_min*0.001
									;rotation
										;grp\position#[4]=yaw#
										orgyaw#=EntityYaw#(grp\pivot)
										diff#=mod360(yaw#-orgyaw#)
										sens=sign(diff#)
										vitesse_ang#=200
										grp\position#[4]=orgyaw#+sens*min(vitesse_ang#*delta_frame_min*0.001,45)
										If sign(sens*diff#)>-1 And Abs(diff#)<vitesse_ang#*0.1
											grp\position#[4]=yaw#
										EndIf
										yaw#=grp\position#[4]
									If dist#<pat\var4#[en_cours]*0.2
										au_suivant=1
										grp\position#[1]=pat\var1#[en_cours]
										grp\position#[2]=pat\var2#[en_cours]
										grp\position#[3]=pat\var3#[en_cours]
									EndIf
									If dist#<pat\var4#[en_cours]*0.2
										au_suivant=1
										grp\position#[1]=pat\var1#[en_cours]
										grp\position#[3]=pat\var3#[en_cours]
									EndIf
								;gestion de la collision avec un sol pas horizontal (escalier, canyon, ...)
									PositionEntity grp\pivot,grp\position#[1],grp\position#[2],grp\position#[3]
									RotateEntity grp\pivot,0,grp\position#[4],0
									ResetEntity grp\pivot
								;animation
									grp\animation=2+grp\not_md2*10
								;déplacer l'agresseur
									agr\position#[1]=grp\position#[1]
									agr\position#[2]=grp\position#[2]
									agr\position#[3]=grp\position#[3]
									agr\polyx#[1]=grp\position#[1]
									agr\polyz#[1]=grp\position#[3]
									agr\polyx#[3]=grp\position#[1]-pat\range#*Sin(yaw#)
									agr\polyz#[3]=grp\position#[3]+pat\range#*Cos(yaw#)
									agr\polyx#[2]=grp\position#[1]-pat\range#*Sin(yaw#+pat\cone#)
									agr\polyz#[2]=grp\position#[3]+pat\range#*Cos(yaw#+pat\cone#)
									agr\polyx#[4]=grp\position#[1]-pat\range#*Sin(yaw#-pat\cone#)
									agr\polyz#[4]=grp\position#[3]+pat\range#*Cos(yaw#-pat\cone#)
								;A enlever
									If mode_debug=1
										For q=1 to 3
											PositionEntity pat\sprite[q],agr\polyx#[q+1],grp\position#[2],agr\polyz#[q+1]
										Next
									Endif
								Case 7 ; Téléporter (destination x,y,z,yaw)
									PositionEntity grp\pivot,pat\var1#[en_cours],pat\var2#[en_cours],pat\var3#[en_cours],1
									RotateEntity grp\pivot,0,pat\var4#[en_cours],0,1
									au_suivant=1
									
								Case 8 ; Toggle agressivité (actif)
									If pat\var1#[en_cours]=2
										agr\actif=1-agr\actif
									Else
										agr\actif=pat\var1#[en_cours]
									EndIf
									au_suivant=1
								
								Case 9 ; Goto (destination)
									pat\en_cours=pat\var1#[en_cours]
								
								Case 10 ; déclencher script sans modifier event_action (temps, script, one_shot, tempo)
									If pat\var4#[en_cours]=0
										pat\var4#[en_cours]=Millisecs()
										If pat\var3#[en_cours]=1 Then script(pat\var2#[en_cours])
									ElseIf MilliSecs()-pat\var4#[en_cours]>pat\var1#[en_cours]
										pat\var4#[en_cours]=0
										au_suivant=1
									EndIf
									If pat\var3#[en_cours]=0 Then script(pat\var2#[en_cours])
								
								Case 11 ; déclencher une fois un script à la première frame puis attendre en modifiant event_action (temps, script, event_action, tempo)
									If pat\var4#[en_cours]=0
										pat\var4#[en_cours]=Millisecs()
										event_action=pat\var3#[en_cours]
										script(pat\var2#[en_cours])
									ElseIf MilliSecs()-pat\var4#[en_cours]>pat\var1#[en_cours]
										pat\var4#[en_cours]=0
										au_suivant=1
									EndIf
								
								Case 12 ; déclencher script pdt une période en modifiant event_action(temps, script, event_action, tempo)
									If pat\var4#[en_cours]=0
										pat\var4#[en_cours]=Millisecs()
										event_action=pat\var3#[en_cours]
									ElseIf MilliSecs()-pat\var4#[en_cours]>pat\var1#[en_cours]
										pat\var4#[en_cours]=0
										au_suivant=1
									EndIf
									script(pat\var2#[en_cours])
								
								Default ; retour au début de la liste d'action
									pat\en_cours=1
							End Select
							
							If au_suivant=1
								pat\en_cours=pat\en_cours+1
								If pat\en_cours>NB_ACTION_PAT Then pat\en_cours=1
							EndIf
						EndIf
					Next
				EndIf
			Next
		EndIf
	Next	
End Function

;Note : telle qu'est fait cette fonction, les PNJ des patrouilles doivent être uniquement des "jetables"
; ennemi=1 : uniquement les patrouilleurs
; ennemi=2 : uniquement les agresseurs (hors patrouilles)
; ennemi=3 : patrouilles et agresseurs
Function reinit_ennemis(ennemi=3)
	;patrouilleurs
	If ennemi=1 Or ennemi=3
		For pat.patrouilleur=Each patrouilleur
			If pat\vivant=-1
				pat\vivant=1
				For agr.agresseur=Each agresseur
					If agr\num=pat\agresseur
						agr\actif=Abs(agr\actif)
						;vider le groupe combattant
						For grp.groupe=Each groupe
							If grp\num=agr\groupe
								For t=1 To 9
									grp\formation[t]=0
								Next
							EndIf
						Next
						;supprimer les avatars
						For av.avatar=Each avatar
							If av\groupe=agr\groupe
								Delete av
							EndIf
						Next
					EndIf
				Next
			EndIf
		Next
	EndIf
	;agresseurs seuls
	If ennemi=2 Or ennemi=3
		For agr.agresseur=Each agresseur
			;mort ?
			If agr\actif=-1
				;vérifier qu'ils sont hors patrouilles
				seul=1
				For pat.patrouilleur=Each patrouilleur
					If pat\agresseur=agr\num Then seul=0
				Next
				If seul=1
					agr\actif=1
					;vider le groupe combattant
					For grp.groupe=Each groupe
						If grp\num=agr\groupe
							For t=1 To 9
								grp\formation[t]=0
								;le repositionner
							Next
						EndIf
					Next
					;supprimer les avatars		
					For av.avatar=Each avatar
						If av\groupe=agr\groupe
							Delete av
						EndIf
					Next
				EndIf
			EndIf
		Next
	EndIf
End Function

Function revenir_au_menu()
	keys(1,2)=49
	reponse=0
	While reponse=0
		start_loop()
		lire_clavier()
		disc_len#=10000
		mult_mess$(1)="Quitter la partie en cours ?#Les progrès depuis la dernière sauvegarde ne seront pas conservés."
		mult_mess$(2)="Quit the game?# All unsaved progress will be lost"
		reponse=fenetreqcm(2,mult_mess$(Int(options#(7))),oui$(Int(options#(7))),non$(Int(options#(7))))
		DrawImage curseur,MouseX(),MouseY()
		Flip
		compensation_lag()
	Wend
	If reponse=1
		quitter_jeu=1
		Return 1
	Else
		Return 0
	Endif
End Function

Function save()
	keys(1,2)=49
	reponse=0
	While reponse=0
		start_loop()
		lire_clavier()
		disc_len#=10000
		mult_mess$(1)="Attention : Il n'y a pour l'instant qu'un seul fichier de sauvegarde. Si vous sauvez, vous supprimez la sauvegarde précédente."
		mult_mess$(2)="Warning: There is currently only one savefile. If you save now, you will erase the previous saved game."
		fenetre_info(mult_mess$(Int(options#(7))),0,100,0)
		mult_mess$(1)="Sauvegarder ?"
		mult_mess$(2)="Save?"
		reponse=fenetreqcm(2,mult_mess$(Int(options#(7))),oui$(Int(options#(7))),non$(Int(options#(7))))
		DrawImage curseur,MouseX(),MouseY()
		Flip
		compensation_lag()
	Wend
	If reponse=1 Then save_server()
	FlushKeys
	FlushMouse
	keys(1,2)=49
End Function

Function save_server()
	xmlSave("Script/Avancement.xml", xml_Avancement)

	If FileType("svg_serveur.dat") Then DeleteFile "svg_serveur.dat"
	
	file=WriteFile("svg_serveur.dat")
	
	;info du joueur
	WriteInt file,player_leader
	WriteInt file,player_avatar(1)
	WriteInt file,player_avatar(2)
	WriteInt file,player_avatar(3)
	WriteInt file,player_map
	WriteInt file,player_junk
	WriteInt file,player_caps
	
	For gr.groupe=Each groupe
		If gr\num=-1
			gr\position#[1]=EntityX#(pl_grp_pivot)
			gr\position#[2]=EntityY#(pl_grp_pivot)
			gr\position#[3]=EntityZ#(pl_grp_pivot)
			gr\position#[4]=EntityYaw#(pl_grp_pivot)
		EndIf
	Next
	
	k=0
	For av.avatar=Each avatar
		k=k+1
	Next
	WriteInt file,k
	For av.avatar=Each avatar
		WriteInt file,av\num
		WriteInt file,av\prop
		WriteInt file,av\groupe
		For t=1 To NB_LANGUES
			WriteString file,av\name$[t]
			WriteString file,av\classe$[t]
			WriteString file,av\description$[t]
		Next
		WriteInt file,av\cat
		For t=1 To 3
			WriteFloat file,av\faiblesse#[t]
		Next
		For t=1 To 15
			WriteInt file,av\cmpt[t]
		Next
		For t=1 To 8
			WriteInt file,av\equi[t]
		Next
		WriteInt file,av\caps
		WriteInt file,av\junk
		WriteInt file,av\set
		WriteInt file,av\animation
		WriteInt file,av\target
		WriteInt file,av\activated
		WriteInt file,av\defense
		WriteInt file,av\last_action
		;====================================[
		For t=1 To 3
			WriteInt file,av\att[t]
			WriteInt file,av\def[t]
			WriteInt file,av\deg[t]
		Next
		For t=1 To 2
			WriteInt file,av\pv[t]
		Next
		WriteInt file,av\init
		;====================================]
	Next
	
	k=0
	For gr.groupe=Each groupe
		k=k+1
	Next
	WriteInt file,k
	For gr.groupe=Each groupe
		WriteInt file,gr\num
		WriteString file,gr\name$
		WriteInt file,gr\map
		WriteInt file,gr\animation
		WriteInt file,gr\not_md2
		For t=1 To 4
			WriteFloat file,gr\position#[t]
			WriteInt file,gr\script[t]
			if t=2 and gr\num=102
				writeint file,0
			else
				WriteInt file,gr\trigger[t]
			EndIf
			WriteInt file,gr\range_trigger[t]
		Next
		For t=1 To 4*NB_LANGUES
			WriteString file,gr\nom_action$[t]
		Next
		For t=1 To 9
			WriteInt file,gr\formation[t]
		Next
	Next
	
	k=0
	For combat.combat=Each combat
		k=k+1
	Next
	WriteInt file,k
	For combat.combat=Each combat
		WriteInt file,combat\num
		WriteInt file,combat\groupe[1]
		WriteInt file,combat\groupe[2]
		WriteInt file,combat\phase
		WriteInt file,combat\tour
		For t=1 To 19
			WriteInt file,combat\ordre[t]
		Next
		WriteInt file,combat\last_action
		WriteInt file,combat\anim_time
		WriteInt file,combat\qui
		WriteInt file,combat\old_qui
	Next
	
	k=0
	For p.porte=Each porte
		k=k+1
	Next
	WriteInt file,k
	For p.porte=Each porte
		WriteInt file,p\num
		WriteInt file,p\etat
	Next
	
	k=0
	For map.map=Each map
		k=k+1
	Next
	WriteInt file,k
	For map.map=Each map
		WriteInt file,map\num
		For t=1 To 25
			WriteInt file,map\stat[t]
		Next
	Next
	
	k=0
	For butin.butin=Each butin
		k=k+1
	Next
	WriteInt file,k
	For butin.butin=Each butin
		WriteInt file,butin\num
		WriteInt file,butin\prop
		WriteInt file,butin\map
		WriteInt file,butin\caps
		WriteInt file,butin\junk
		WriteInt file,butin\hidden
		WriteInt file,butin\kind
		For t=1 To 4
			WriteFloat file,butin\position#[t]
		Next
		For t=1 To 50
			WriteInt file,butin\quest[t]
		Next
		For t=1 To 150
			WriteInt file,butin\loot[t]
		Next
	Next
	
	k=0
	For agr.agresseur=Each agresseur
		k=k+1
	Next
	WriteInt file,k
	For agr.agresseur=Each agresseur
		WriteInt file,agr\num
		WriteInt file,agr\map
		WriteInt file,agr\groupe
		WriteInt file,agr\actif
		WriteFloat file,agr\radius#
		For t=1 To 3
			WriteFloat file,agr\position#[t]
		Next
		For t=1 To 4
			WriteFloat file,agr\polyx#[t]
			WriteFloat file,agr\polyz#[t]
		Next
	Next
	
	k=0
	For pat.patrouilleur=Each patrouilleur
		k=k+1
	Next
	WriteInt file,k
	For pat.patrouilleur=Each patrouilleur
		WriteInt file,pat\num
		WriteInt file,pat\map
		WriteString file,pat\name$
		WriteInt file,pat\agresseur
		WriteInt file,pat\en_cours
		WriteInt file,pat\actif
		WriteInt file,pat\vivant
		WriteFloat file,pat\cone#
		WriteFloat file,pat\range#
		For t=1 To NB_ACTION_PAT
			WriteInt file,pat\actions[t]
			WriteFloat file,pat\var1#[t]
			WriteFloat file,pat\var2#[t]
			WriteFloat file,pat\var3#[t]
			WriteFloat file,pat\var4#[t]
		Next
	Next
	
	CloseFile file 
	
	new_log("Sauvegarde effectuée",180,180,180)
End Function


Function load_server()
	SetFont LoadFont("Constantia",15)
	;Print "Ouverture du fichier."
	If FileType("svg_serveur.dat")=0 Then RuntimeError "Fichier sauvegarde du serveur introuvable"
	xml_Avancement=xmlLoad("Script/Avancement.xml")
	
	file=ReadFile("svg_serveur.dat")
	
;	Color 255,255,255
;	Print "Fichier ouvert"
;	Print ""
	
	;lecture info player
	player_leader=ReadInt(file)
	player_avatar(1)=ReadInt(file)
	player_avatar(2)=ReadInt(file)
	player_avatar(3)=ReadInt(file)
	player_map=ReadInt(file)
	player_junk=ReadInt(file)
	player_caps=ReadInt(file)
	
	k=ReadInt(file)
;	Print "Lectures des "+k+" avatars."
	For t=1 To k
		good=0
		num=ReadInt(file)
		For av.avatar=Each avatar
			If num=av\num And good=0
				good=1
				av\prop=ReadInt(file)
				av\groupe=ReadInt(file)
				For i=1 To NB_LANGUES
					av\name$[i]=ReadString(file)
					av\classe$[i]=ReadString(file)
					av\description$[i]=ReadString(file)
				Next
				av\cat=ReadInt(file)
				For i=1 To 3
					av\faiblesse#[i]=ReadFloat(file)
				Next
				For i=1 To 15
					av\cmpt[i]=ReadInt(file)
				Next
				For i=1 To 8
					av\equi[i]=ReadInt(file)		
				Next
				av\caps=ReadInt(file)
				av\junk=ReadInt(file)
				av\set=ReadInt(file)
				av\animation=ReadInt(file)
				av\target=ReadInt(file)
				av\activated=ReadInt(file)
				av\defense=ReadInt(file)
				av\last_action=ReadInt(file)
				;====================================[
				For i=1 To 3
					av\att[i]=ReadInt(file)
					av\def[i]=ReadInt(file)
					av\deg[i]=ReadInt(file)
				Next
				For i=1 To 2
					av\pv[i]=ReadInt(file)
				Next
				av\init=ReadInt(file)			
				;====================================]
;				Print "     maj avatar n°"++av\num+" ("+t+")"
			EndIf
		Next
		If good=0
			av.avatar=New avatar
			av\num=num
			av\prop=ReadInt(file)
			av\groupe=ReadInt(file)
			For i=1 To NB_LANGUES
				av\name$[i]=ReadString(file)
				av\classe$[i]=ReadString(file)
				av\description$[i]=ReadString(file)
			Next
			av\cat=ReadInt(file)
			;====================================(
		;	For i=1 To 5
		;		av\carac[i]=ReadInt(file)
		;	Next
			;====================================)
			For i=1 To 3
				av\faiblesse#[i]=ReadFloat(file)
			Next
			;====================================(
		;	For i=1 To 10
		;		av\capac[i]=ReadInt(file)
		;	Next
			;====================================)
			For i=1 To 15
				av\cmpt[i]=ReadInt(file)
			Next
			For i=1 To 8
				av\equi[i]=ReadInt(file)		
			Next
			av\caps=ReadInt(file)
			av\junk=ReadInt(file)
			av\set=ReadInt(file)
			av\animation=ReadInt(file)
			av\target=ReadInt(file)
			av\activated=ReadInt(file)
			av\defense=ReadInt(file)
			av\last_action=ReadInt(file)
			;====================================[
			For i=1 To 3
				av\att[i]=ReadInt(file)
				av\def[i]=ReadInt(file)
				av\deg[i]=ReadInt(file)
			Next
			For i=1 To 2
				av\pv[i]=ReadInt(file)
			Next
			av\init=ReadInt(file)			
			;====================================]
;			Print "     new avatar n°"+av\num+" ("+t+")"
		EndIf
		
	Next
;	Print "Fin lecture des avatars."
;	Print ""
;	FlushKeys 
;	waitKey 

	k=ReadInt(file)
	;Print "Lecture des "+k+" groupes"
	For t=1 To k
		good=0
		num=ReadInt(file)
		For gr.groupe=Each groupe
			If gr\num=num And good=0
				good=1
				gr\name$=ReadString(file)
				gr\map=ReadInt(file)
				gr\animation=ReadInt(file)
				gr\not_md2=ReadInt(file)
				For i=1 To 4
					gr\position#[i]=ReadFloat(file)
					gr\script[i]=ReadInt(file)
					gr\trigger[i]=ReadInt(file)
					gr\range_trigger[i]=ReadInt(file)
				Next
				For i=1 To 4*NB_LANGUES
					gr\nom_action$[i]=ReadString(file)		
				Next
				For i=1 To 9
					gr\formation[i]=ReadInt(file)
				Next
				;Print "     maj groupe n°"+gr\num+"  ("+t+")"
			EndIf		
		Next
		If good=0
			gr.groupe=New groupe
			gr\num=num
			gr\name$=ReadString(file)
			gr\map=ReadInt(file)
			gr\animation=ReadInt(file)
			gr\not_md2=ReadInt(file)
			For i=1 To 4
				gr\position#[i]=ReadFloat(file)
				gr\script[i]=ReadInt(file)
				gr\trigger[i]=ReadInt(file)
				gr\range_trigger[i]=ReadInt(file)
				gr\nom_action$[i]=ReadString(file)		
			Next
			For i=1 To 9
				gr\formation[i]=ReadInt(file)
			Next
			;Print "     new groupe n°"+gr\num+"  ("+t+")"
		EndIf
	Next
	;Print "Fin lecture des groupes."
	;Print ""
	;FlushKeys 
	;waitKey 

	k=ReadInt(file)
	;Print "Lecture des "+k+" combats."
	For t=1 To k
		good=0
		num=ReadInt(file)
		For combat.combat=Each combat
			If combat\num=num And good=0
				good=1
				combat\groupe[1]=ReadInt(file)
				combat\groupe[2]=ReadInt(file)
				combat\phase=ReadInt(file)
				combat\tour=ReadInt(file)
				For i=1 To 19
					combat\ordre[i]=ReadInt(file)
				Next
				combat\last_action=ReadInt(file)
				combat\anim_time=ReadInt(file)
				combat\qui=ReadInt(file)
				combat\old_qui=ReadInt(file)
				;Print "     maj combat n°"+combat\num+" ("+t+")"	
			EndIf
		Next
		If good=0
			combat.combat=New combat
			combat\num=num
			combat\groupe[1]=ReadInt(file)
			combat\groupe[2]=ReadInt(file)
			combat\phase=ReadInt(file)
			combat\tour=ReadInt(file)
			For i=1 To 19
				combat\ordre[i]=ReadInt(file)
			Next
			combat\last_action=ReadInt(file)
			combat\anim_time=ReadInt(file)
			combat\qui=ReadInt(file)
			combat\old_qui=ReadInt(file)
			;Print "     new combat n°"+combat\num+" ("+t+")"	
		EndIf
	Next
	;Print "Fin lecture des combats."
	;Print ""
	;FlushKeys 
	;waitKey 

	k=ReadInt(file)
	;Print "Lecture des "+k+" portes."
	For t=1 To k
		good=0
		num=ReadInt(file)
		etat=ReadInt(file)
		For p.porte=Each porte
			If p\num=num
				If good=0
					good=1
					p\etat=etat
					;Print "     maj porte n°"+p\num+" ("+t+") : "+p\etat
				Else
					Delete p
					;Print "     porte en trop supprimée ("+t+")"
				Endif
			EndIf		
		Next
		If good=0
			p.porte=New porte
			p\num=num
			p\etat=etat
			;Print "     new porte n°"+p\num+" ("+t+") : "+p\etat
		EndIf
	Next
	;Print "Fin lecture des portes."
	;waitkey
	;Print ""

	k=ReadInt(file)
	For t=1 To k
		good=0
		num=ReadInt(file)
		For map.map=Each map
			If map\num=num And good=0
				good=1
				For u=1 To 25
					map\stat[u]=ReadInt(file)
				Next
				;Print "     maj map n°"+map\num+" ("+t+")"
			EndIf
		Next
		If good=0
			map.map=New map
			map\num=num
			For u=1 To 25
				map\stat[u]=ReadInt(file)
			Next
			;Print "     new map n°"+map\num+" ("+t+")"
		EndIf
	Next
	;Print "Fin lecture des maps."
	;Print ""
	
	For butin.butin=Each butin
		Delete butin
	Next
	k=ReadInt(file)
	;Print "Lecture des "+k+" butins."
	For q=1 To k
		butin.butin=New butin
		butin\num=ReadInt(file)
		butin\prop=ReadInt(file)
		butin\map=ReadInt(file)
		butin\caps=ReadInt(file)
		butin\junk=ReadInt(file)
		butin\hidden=ReadInt(file)
		butin\kind=ReadInt(file)
		For t=1 To 4
			butin\position#[t]=ReadFloat(file)
		Next
		For t=1 To 50
			butin\quest[t]=ReadInt(file)
		Next
		For t=1 To 150
			butin\loot[t]=ReadInt(file)
		Next
	Next
	;Print ""
	;Print "Fin lecture des butins"
	;Print ""
	
	For agr.agresseur=Each agresseur
		Delete agr
	Next	
	k=ReadInt(file)
	For q=1 To k
		agr.agresseur=New agresseur
		agr\num=ReadInt(file)
		agr\map=ReadInt(file)
		agr\groupe=ReadInt(file)
		agr\actif=ReadInt(file)
		agr\radius#=ReadFloat(file)
		For t=1 To 3
			agr\position#[t]=ReadFloat(file)
		Next
		For t=1 To 4
			agr\polyx#[t]=ReadFloat(file)
			agr\polyz#[t]=ReadFloat(file)
		Next
	Next
	
	For pat.patrouilleur=Each patrouilleur
		Delete pat
	Next
	k=ReadInt(file)
	For q=1 to k
		pat.patrouilleur=New patrouilleur
		pat\num=ReadInt(file)
		pat\map=ReadInt(file)
		pat\name$=ReadString(file)
		pat\agresseur=ReadInt(file)
		pat\en_cours=ReadInt(file)
		pat\actif=ReadInt(file)
		pat\vivant=ReadInt(file)
		pat\cone#=ReadFloat(file)
		pat\range#=ReadFloat(file)
		For t=1 to NB_ACTION_PAT
			pat\actions[t]=ReadInt(file)
			pat\var1#[t]=ReadFloat(file)
			pat\var2#[t]=ReadFloat(file)
			pat\var3#[t]=ReadFloat(file)
			pat\var4#[t]=ReadFloat(file)
		Next
	
	Next
	
	;Print "Fermeture du fichier."
	CloseFile file
	;Print "Fichier fermé."
	;Print ""
	Color 0,255,0
	;Print "Press any key !"
	;FlushKeys 
	;waitKey 

End Function

Function etat_porte(num)
	For porte.porte=each porte
		If porte\num=num Then Return porte\etat
	Next
End Function

Function tutoriel(num)
	PauseChannel ch_bgm1
	son_tuto=LoadSound("musiques\tuto.mp3")
	LoopSound son_tuto
	ch_tuto=PlaySound(son_tuto)
	ChannelVolume ch_tuto,options#(5)
	Select num
		Case 1 ; tuto de base (perso, ordre, vie, attaque, attaque visée)
			fond=LoadImage("sprites\tutoriaux\fond_tuto_01.jpg")
			ScaleImage fond,screenyf#,screenyf#
			MidHandle fond
			action=1
			frame_a=MilliSecs()
			While action>0
				Cls
				start_loop()
				lire_clavier()
				
				If action>1 Then DrawBlock fond,screenwidth*0.5,screenheight*0.5
				If Int(options#(7))=1
					Select action
						Case 1
							mess$="Bonjour et bienvenu dans ce tutoriel !#Dans cette séquence, nous verrons les bases du combat dans Final Whim (tm)."
							If discussion(2,1,"Narrateur",mess$,1,1,1) Then action=2
						Case 2
							mess$="Premièrement, voici à quoi ressemble un champ de bataille classique."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=3
						Case 3
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=150*af#
							For t=1 To 5
								Oval (550-rayon*0.5)*screenyf#-t,(275-rayon*0.5)*screenyf#-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="Voici votre personnage.#La roue dentée jaune en dessous de lui indique que c'est son tour d'agir.#La barre verte représente ses points de vie. Quand elle est entièrement rouge, le personnage meurt."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=4
						Case 4
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=200*af#
							For t=1 To 5
								Oval (200-rayon*0.5)*screenyf#-t,(175-rayon*0.5)*screenyf#-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="Les personnages en face sont les ennemis.#Le but du combat est de les tuer ou de les faire fuir."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=5
						Case 5
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=15*af#
							For t=1 To 4
								Rect (screenwidth*0.5-100*screenyf#-4-t-rayon*0.5),(370*screenyf#-4-t-rayon*0.5),(200*screenyf#+8+2*t+rayon),(10*screenyf#+8+2*t+rayon),0
							Next
							mess$="Ceci est votre barre de temps.#Elle représente combien de temps il vous reste pour agir. Si elle est complètement vide avant que vous ayez agit, vous passez automatiquement votre tour.#Rassurez vous, vous avez quand même "+Str(Int(Float(TEMPS_ROUND)*0.001))+" secondes pour faire toutes vos actions."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=6
						Case 6
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=75*af#*screenyf#
							For t=1 To 5
								Oval screenwidth*0.5-60*screenyf#-rayon*0.5-t,210*screenyf#-rayon*0.5-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="Pour faire une attaque basique, cliquez sur l'icône en forme d'épée, puis sur un des deux type d'attaque (normale ou visée)."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=7
						Case 7
							mess$="Les attaques normales sont, comme leur nom l'indique, des attaques basiques.#Les attaques stratégiques sont des attaques plus difficiles (et donc qui échouent plus facilement) mais qui font plus de dégâts lorsque vous touchez."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=8
						Case 8
							mess$="Cliquez enfin sur la cible que vous souhaitez attaquer.#Si pour x raisons, vous ne pouvez pas l'attaquer, vous entendrez le son ''erreur''."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=9
						Case 9
							mess$="Pour pouvoir attaquer une cible au corps à corps, il faut que votre cible et vous soyez les premiers de vos colonnes respectives. Vous ne pourrez pas l'attaquer au corps à corps si vous ou votre cible êtes caché derrière quelqu'un~!"
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=10
						Case 10
							mess$="D'autre part, au premier tour d'un combat, votre groupe et celui de vos ennemis sont trop éloignés pour attaquer au corps à corps. Seuls les personnages possédant la règle spéciale Célérité (comme les Acrobates) peuvent attaquer au corps à corps au premier tour."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=11
						Case 11
							mess$="Les armes à distance peuvent attaquer dès le premier tour, et peuvent aussi attaquer n'importe quelle cible, même si cette cible ou si le tireur est caché derrière quelqu'un.#Nous verrons les armes à distance plus en détail dans un autre tutoriel."
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=12						
						Case 12
							mess$="Ça sera tout pour le moment.# #Bonne chance~!"
							If discussion(0,0,"Narrateur",mess$,1,1,1) Then action=13
						Case 13
							reponse=fenetreqcm(2,"Revoir le tutoriel ?","Oui","Non")
							If reponse=1 Then action=2
							If reponse=2 Then action=0	
							DrawImage curseur,MouseX(),MouseY()	
					End Select
				Else
					Select action
						Case 1
							mess$="Hello, welcom to this tutorial !#In this sequence, we will see the basic of FinalWhim fight phase."
							If discussion(2,1,"StoryTeller",mess$,1,1,1) Then action=2
						Case 2
							mess$="First, this is what the battle field look like."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=3
						Case 3
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=150*af#
							For t=1 To 5
								Oval (550-rayon*0.5)*screenyf#-t,(275-rayon*0.5)*screenyf#-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="This is your character.#The yellow wheel behind him indicates that it's his turn to act.# The green bar represent his health points. When it's completely red, the character is KO."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=4
						Case 4
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=200*af#
							For t=1 To 5
								Oval (200-rayon*0.5)*screenyf#-t,(175-rayon*0.5)*screenyf#-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="On the other side of the battle field are ennemy.#Defeat them all to win the fight."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=5
						Case 5
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=15*af#
							For t=1 To 4
								Rect (screenwidth*0.5-100*screenyf#-4-t-rayon*0.5),(370*screenyf#-4-t-rayon*0.5),(200*screenyf#+8+2*t+rayon),(10*screenyf#+8+2*t+rayon),0
							Next
							mess$="This is the time bar.#It reprenste the remaining time to act. If it's completly empty before you act, you skip your turn.#In the current mode, you have "+Str(Int(Float(TEMPS_ROUND)*0.001))+" seconds to act."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=6
						Case 6
							Color 255,255,255
							af#=0.75+0.25*Cos(timer_animation#*4)
							rayon=75*af#*screenyf#
							For t=1 To 5
								Oval screenwidth*0.5-60*screenyf#-rayon*0.5-t,210*screenyf#-rayon*0.5-t,rayon+2*t,rayon+2*t,0
							Next
							mess$="To do an attack, you need to clic on the sword icone, then choose between normal attack or strategical attack."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=7
						Case 7
							mess$="(Normal attack has no particularity.#Strategical attack are harder to do (less chance to hit) but do more damage.)"
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=8
						Case 8
							mess$="Then clic on your target.#If, for some reason, you can't attack, you will list to an ''error'' sound."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=9
						Case 9
							mess$="To attack with melee weapon, you need to be the first on your line. You cannot attack with a melee weapon if your hide beyind anyone~!"
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=10
						Case 10
							mess$="The first turn, your groupe and ennemi group are to far from each other to use melee weapon (except people with the rules Swift)."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=11
						Case 11
							mess$="The ranged weapon can attack the first round and attack anyone form evrywhere."
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=12						
						Case 12
							mess$="That's all.# #Good luck~!"
							If discussion(0,0,"StoryTeller",mess$,1,1,1) Then action=13
						Case 13
							reponse=fenetreqcm(2,"Review this tutorial ?","Yes","No")
							If reponse=1 Then action=2
							If reponse=2 Then action=0	
							DrawImage curseur,MouseX(),MouseY()	
					End Select
				EndIf
				Flip
				compensation_lag()
			Wend
			FreeImage fond
	End Select
	StopChannel ch_tuto
	FreeSound son_tuto
	ResumeChannel ch_bgm1
End Function