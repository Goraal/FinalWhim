;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;R�gles sp�ciales;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;ok
r.rules=New rules
r\num=1
r\name$[1]="Mains nues +2"
r\description$[1]="L'avatar sait se battre � mains nues. +2 aux valeurs d'attaque et de d�fense tant qu'il se bat � mains nues."
r\name$[2]="Unarmed Strike +2"
r\description$[2]="+2 to Att and Def when fighting unarmed."

;ok
r.rules=New rules
r\num=2
r\name$[1]="Interne en m�decine"
r\description$[1]="L'avatar sait vaguement soigner. � la fin du combat, les alli�s vaincus regagnent 1 PV."
r\name$[2]="Intern"
r\description$[2]="The character knows vaguely how to heal. At the end of a battle, vainquished allies regain 1 HP."

;ok
r.rules=New rules
r\num=3
r\name$[1]="M�decin"
r\description$[1]="L'avatar est m�decin. Si il est conscient � la fin du combat, tous les alli�s ont leurs PV remont�s � "+Int(100*BONUS_MEDIC#)+"%."
r\name$[2]="Medic"
r\description$[2]="The character is a medic. At the end of a battle, all allies have their HP rise to "+Int(100*BONUS_MEDIC#)+"% of their max."

;ok
r.rules=New rules
r\num=4
r\name$[1]="Pistolero"
r\description$[1]="L'avatar est un fan de pistolet et revolver. +3 aux valeurs d'attaque lorsqu'il utilise une arme de poing."
r\name$[2]="Pistolero"
r\description$[2]="+3 to Att when using a handgun."

;ok
r.rules=New rules
r\num=5
r\name$[1]="Pro du double"
r\description$[1]="L'avatar n'a pas de malus lors de double tir, quelque soit l'arme (ne marche pas pour les cadences de tir plus grandes)."
r\name$[2]="Double Tap Pro"
r\description$[2]="No malus to Att on the second attack of a double tap (doesn't work for higher rate of fire)."

;ok ?
r.rules=New rules
r\num=6
r\name$[1]="Executeur"
r\description$[1]="Lorsque l'avatar fait un tir strat�gique, il ne peut pas toucher d'autres cibles que celle d�sign�e."
r\name$[2]="Executioner"
r\description$[2]="When making a tactical shot, the character cannot hit target other than the designated one."

;ok	
r.rules=New rules
r\num=7
r\name$[1]="Sniper"
r\description$[1]="Lorsque l'avatar fait un tir simple ou strat�gique avec un fusil ou un PM, il a un bonus de +3 en attaque."
r\name$[2]="Sniper"
r\description$[2]="+3 Att when the character take a single shot (tactical or not) with a battle-rifle."

;ok
r.rules=New rules
r\num=8
r\name$[1]="Econome"
r\description$[1]="Pendant les rafales, si la cible devant �tre touch�e est d�j� hors-combat, l'avatar ne d�pense pas de munitions."
r\name$[2]="Efficient"
r\description$[2]="During a Burst, if the target to be hit is already out of combat, the character does not spend ammunition."

;ok
r.rules=New rules
r\num=9
r\name$[1]="Fusil de chasse +2"
r\description$[1]="+2 en attaque � distance avec un fusil de chasse ou apparent�."
r\name$[2]="Shotgun +2"
r\description$[2]="+2 to Att when using a shotgun."

;ok
r.rules=New rules
r\num=10
r\name$[1]="Ep�e +2"
r\description$[1]="+2 en attaque et d�fense avec une �p�e ou apparent�e."
r\name$[2]="Sword +2"
r\description$[2]="+2 to Att when using a sword."

;ok
r.rules=New rules
r\num=11
r\name$[1]="Lance +2"
r\description$[1]="+2 en attaque et d�fense avec une lance ou apparent�e."
r\name$[2]="Spear +2"
r\description$[2]="+2 to Att when using a spear."

;ok
r.rules=New rules
r\num=12
r\name$[1]="C�l�rit�"
r\description$[1]="L'avatar peut attaquer au corps � corps d�s le premier tour."
r\name$[2]="Swift"
r\description$[2]="The character can use melee attacks in the first round of a battle."

;ok
r.rules=New rules
r\num=13
r\name$[1]="Hache +2"
r\description$[1]="+2 en attaque et d�fense avec une hache ou apparent�e."
r\name$[2]="Axe +2"
r\description$[2]="+2 to Att when using an axe."

;ok
r.rules=New rules
r\num=14
r\name$[1]="Attaque sournoise"
r\description$[1]="L'avatar a une bonus de "+signed_str(ATT_SOURNOISE)+" d�g�ts (non criticable) pour chaque marqueur d'acharnement de sa cible."
r\name$[2]="Sneak attack"
r\description$[2]="The character has a non-criticable damage bonus of "+signed_str(ATT_SOURNOISE)+" per **** marks on their target."

;ok
r.rules=New rules
r\num=15
r\name$[1]="Fusil +2"
r\description$[1]="+2 en attaque � distance avec un fusil � canon ray� ou apparent�"
r\name$[2]="Rifle +2"
r\description$[2]="+2 to Att when using a battle rifle."

;inclus dans le profil
r.rules=New rules
r\num=16
r\name$[1]="Robuste"
r\description$[1]="Diminue les d�g�ts pour les critiques. Ils restent cependant sup�rieurs aux d�g�ts normaux."
r\name$[2]="Strong-built"
r\description$[2]="The character takes less damages from critical hits. Critical hits still hurt more than normal hits."

r.rules=New rules
r\num=17
r\name$[1]="M�canique"
r\description$[1]="Ce personnage est m�canique et ne peut �tre soign� que par le M�canicien."
r\name$[2]="Mechanical"
r\description$[2]="This character is mechanical and can only be mended by the Mechanic."

;ok
r.rules=New rules
r\num=100
r\name$[1]="Arme � distance"
r\description$[1]="Cette arme est con�ue pour �tre utilis�e � distance."
r\name$[2]="Ranged Weapon"
r\description$[2]="This weapon is designed to be used at range."

;ok
r.rules=New rules
r\num=101
r\name$[1]="Charges"
r\description$[1]="Cet �quipement ne peut �tre utilis� qu'un certain nombre de fois par combat. R.A.Z. entre chaque combat."
r\name$[2]="Charges"
r\description$[2]="This equipment can only be used a set number of times per battle. Reset between each battle."

;ok
	r.rules=New rules
	r\num=102
	r\name$[1]="A vapeur [1]"
	r\description$[1]="Cet objet n�cessite 1 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	r\name$[2]="Steam powered [1]"
	r\description$[2]="This objet need 1 unit of steam pressure to fonction. Required pressures cumulate with others."
	
	r.rules=New rules
	r\num=103
	r\name$[1]="A vapeur [2]"
	r\description$[1]="Cet objet n�cessite 2 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	r\name$[2]="Steam powered [2]"
	r\description$[2]="This objet need 2 units of steam pressure to fonction. Required pressures cumulate with others."
		
	r.rules=New rules
	r\num=104
	r\name$[1]="A vapeur [3]"
	r\description$[1]="Cet objet n�cessite 3 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	r\name$[2]="Steam powered [3]"
	r\description$[2]="This objet need 3 units of steam pressure to fonction. Required pressures cumulate with others."
		
	r.rules=New rules
	r\num=105
	r\name$[1]="A vapeur [4]"
	r\description$[1]="Cet objet n�cessite 4 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	r\name$[2]="Steam powered [4]"
	r\description$[2]="This objet need 4 units of steam pressure to fonction. Required pressures cumulate with others."
		
	r.rules=New rules
	r\num=106
	r\name$[1]="A vapeur [5]"
	r\description$[1]="Cet objet n�cessite 5 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	r\name$[2]="Steam powered [5]"
	r\description$[2]="This objet need 5 units of steam pressure to fonction. Required pressures cumulate with others."
			
;ok	
	r.rules=New rules
	r\num=150
	r\name$[1]="Encombrant [1]"
	r\description$[1]="Cet objet donne un malus de -1 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	r\name$[2]="Cumbersome [1]"
	r\description$[2]="This object gives a (cumulable) penalty of -1 to all Att and Def when not in your hands."
		
	r.rules=New rules
	r\num=151
	r\name$[1]="Encombrant [2]"
	r\description$[1]="Cet objet donne un malus de -2 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	r\name$[2]="Cumbersome [2]"
	r\description$[2]="This object gives a (cumulable) penalty of -2 to all Att and Def when not in your hands."
		
	r.rules=New rules
	r\num=152
	r\name$[1]="Encombrant [3]"
	r\description$[1]="Cet objet donne un malus de -3 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	r\name$[2]="Cumbersome [3]"
	r\description$[2]="This object gives a (cumulable) penalty of -3 to all Att and Def when not in your hands."
		
	r.rules=New rules
	r\num=153
	r\name$[1]="Encombrant [4]"
	r\description$[1]="Cet objet donne un malus de -4 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	r\name$[2]="Cumbersome [4]"
	r\description$[2]="This object gives a (cumulable) penalty of -4 to all Att and Def when not in your hands."	


r.rules=New rules
r\num=201
r\name$[1]=""
r\description$[1]="";"Permet d'activer le GearBot Aegis-1"
r\name$[2]=""
r\description$[2]=""

r.rules=New rules
r\num=202
r\name$[1]=""
r\description$[1]="";"Permet d'activer le GearBot Aegis-2"
r\name$[2]=""
r\description$[2]=""

r.rules=New rules
r\num=203
r\name$[1]=""
r\description$[1]="";"Permet d'activer le GearBot (mod�le 2)-1"
r\name$[2]=""
r\description$[2]=""

r.rules=New rules
r\num=204
r\name$[1]=""
r\description$[1]="";"Permet d'activer le GearBot (mod�le 2)-2"
r\name$[2]=""
r\description$[2]=""


;; test

r.rules=New rules
r\num=314
r\name$[1]="Petite taille"
r\description$[1]="L'objet n'est pas adapt� aux adultes."
r\name$[2]="Small size"
r\description$[2]="This equipement isn't sized for an adult."


For r.rules = Each rules
	For t=2 To NB_LANGUES
		If r\name$[t]="" Then r\name$[t]="NTY: "+r\name$[1]
		If r\description$[t]="" Then r\description$[t]="NTY: "+r\description$[1]
	Next
Next