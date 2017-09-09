;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;R�gles sp�ciales;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;ok
r.rules=New rules
r\num=1
r\name$="Mains nues +2"
r\description$="L'avatar sait se battre � mains nues. +2 aux valeurs d'attaque et de d�fense tant qu'il se bat � mains nues."

;ok
r.rules=New rules
r\num=2
r\name$="Interne en m�decine"
r\description$="L'avatar sait vaguement soigner. Si il est conscient � la fin du combat, tous les avatars alli�s inconscients ont leurs PV remont�s � 1."

;ok
r.rules=New rules
r\num=3
r\name$="M�decin"
r\description$="L'avatar est m�decin. Si il est conscient � la fin du combat, tous les avatars alli�s ont leurs PV remont�s � "+Int(100*BONUS_MEDIC#)+"%."

;ok
r.rules=New rules
r\num=4
r\name$="Pistolero"
r\description$="L'avatar est un fan de pistolet et revolver. +3 aux valeurs d'attaque lorsqu'il utilise une arme de poing."

;ok
r.rules=New rules
r\num=5
r\name="Pro du double"
r\description$="L'avatar n'a pas de malus lors de double tir, quelque soit l'arme (ne marche pas pour les cadences de tir plus grandes)."

;ok ?
r.rules=New rules
r\num=6
r\name="Executeur"
r\description$="Lorsque l'avatar fait un tir vis�, il ne peut pas toucher d'autres cibles que celle d�sign�e."

;ok	
r.rules=New rules
r\num=7
r\name="Sniper"
r\description$="Lorsque l'avatar fait un tir simple ou vis� avec un fusil ou un PM, il a un bonus de +3 en attaque."

;ok
r.rules=New rules
r\num=8
r\name="Econome"
r\description$="Pendant les rafales, si le personnage devant �tre touch� est d�j� mort, l'avatar ne tire pas."

;ok
r.rules=New rules
r\num=9
r\name="Fusil de chasse +2"
r\description$="+2 en attaque � distance avec un fusil de chasse ou apparent�"

;ok
r.rules=New rules
r\num=10
r\name="Ep�e +2"
r\description$="+2 en attaque et d�fense avec une �p�e ou apparent�e"

;ok
r.rules=New rules
r\num=11
r\name="Lance +2"
r\description$="+2 en attaque et d�fense avec une lance ou apparent�e"

;ok
r.rules=New rules
r\num=12
r\name="C�l�rit�"
r\description$="L'avatar peut attaquer au corps � corps d�s le premier tour."

;ok
r.rules=New rules
r\num=13
r\name="Hache +2"
r\description$="+2 en attaque et d�fense avec une hache ou apparent�e"

;ok
r.rules=New rules
r\num=14
r\name="Attaque sournoise"
r\description$="L'avatar a une bonus de "+signed_str(ATT_SOURNOISE)+" d�g�ts (non criticable) pour chaque marqueur d'archarnement de sa cible."

;ok
r.rules=New rules
r\num=15
r\name="Fusil +2"
r\description$="+2 en attaque � distance avec un fusil � canon ray� ou apparent�"

;inclus dans le profil
r.rules=New rules
r\num=16
r\name="Robuste"
r\description$="Diminue les d�g�ts pour les critiques. Ils restent cependant sup�rieurs aux d�g�ts normaux."


r.rules=New rules
r\num=17
r\name$="M�canique"
r\description$="Ce personnage est m�canique et ne peut �tre soign� que par le M�canicien."


;ok
r.rules=New rules
r\num=100
r\name$="Arme � distance"
r\description$="Cette arme est con�ue pour �tre utilis�e � distance."

;ok
r.rules=New rules
r\num=101
r\name$="Charges"
r\description$="Cet �quipement ne peut �tre utilis� qu'un certain nombre de fois par combat. R.A.Z. entre chaque combat."

;ok
	r.rules=New rules
	r\num=102
	r\name$="A vapeur [1]"
	r\description$="Cet objet n�cessite 1 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	
	r.rules=New rules
	r\num=103
	r\name$="A vapeur [2]"
	r\description$="Cet objet n�cessite 2 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	
	r.rules=New rules
	r\num=104
	r\name$="A vapeur [3]"
	r\description$="Cet objet n�cessite 3 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	
	r.rules=New rules
	r\num=105
	r\name$="A vapeur [4]"
	r\description$="Cet objet n�cessite 4 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
	
	r.rules=New rules
	r\num=106
	r\name$="A vapeur [5]"
	r\description$="Cet objet n�cessite 5 de pression dans votre chaudi�re pour fonctionner. Les pressions n�cessaires se cumulent."
		
;ok	
	r.rules=New rules
	r\num=150
	r\name$="Encombrant [1]"
	r\description$="Cet objet donne un malus de -1 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=151
	r\name$="Encombrant [2]"
	r\description$="Cet objet donne un malus de -2 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=152
	r\name$="Encombrant [3]"
	r\description$="Cet objet donne un malus de -3 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=153
	r\name$="Encombrant [4]"
	r\description$="Cet objet donne un malus de -4 (cumulable) � toutes les attaques et d�fenses quand il n'est pas en main."
	


r.rules=New rules
r\num=201
r\name$=""
r\description$="";"Permet d'activer le GearBot Aegis-1"

r.rules=New rules
r\num=202
r\name$=""
r\description$="";"Permet d'activer le GearBot Aegis-2"

r.rules=New rules
r\num=203
r\name$=""
r\description$="";"Permet d'activer le GearBot (mod�le 2)-1"

r.rules=New rules
r\num=204
r\name$=""
r\description$="";"Permet d'activer le GearBot (mod�le 2)-2"



;; test

r.rules=New rules
r\num=314
r\name$="Petite taille"
r\description$="L'objet n'est pas adapt� aux adultes."