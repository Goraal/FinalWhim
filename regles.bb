;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Règles spéciales;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;ok
r.rules=New rules
r\num=1
r\name$="Mains nues +2"
r\description$="L'avatar sait se battre à mains nues. +2 aux valeurs d'attaque et de défense tant qu'il se bat à mains nues."

;ok
r.rules=New rules
r\num=2
r\name$="Interne en médecine"
r\description$="L'avatar sait vaguement soigner. Si il est conscient à la fin du combat, tous les avatars alliés inconscients ont leurs PV remontés à 1."

;ok
r.rules=New rules
r\num=3
r\name$="Médecin"
r\description$="L'avatar est médecin. Si il est conscient à la fin du combat, tous les avatars alliés ont leurs PV remontés à "+Int(100*BONUS_MEDIC#)+"%."

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
r\description$="Lorsque l'avatar fait un tir visé, il ne peut pas toucher d'autres cibles que celle désignée."

;ok	
r.rules=New rules
r\num=7
r\name="Sniper"
r\description$="Lorsque l'avatar fait un tir simple ou visé avec un fusil ou un PM, il a un bonus de +3 en attaque."

;ok
r.rules=New rules
r\num=8
r\name="Econome"
r\description$="Pendant les rafales, si le personnage devant être touché est déjà mort, l'avatar ne tire pas."

;ok
r.rules=New rules
r\num=9
r\name="Fusil de chasse +2"
r\description$="+2 en attaque à distance avec un fusil de chasse ou apparenté"

;ok
r.rules=New rules
r\num=10
r\name="Epée +2"
r\description$="+2 en attaque et défense avec une épée ou apparentée"

;ok
r.rules=New rules
r\num=11
r\name="Lance +2"
r\description$="+2 en attaque et défense avec une lance ou apparentée"

;ok
r.rules=New rules
r\num=12
r\name="Célérité"
r\description$="L'avatar peut attaquer au corps à corps dès le premier tour."

;ok
r.rules=New rules
r\num=13
r\name="Hache +2"
r\description$="+2 en attaque et défense avec une hache ou apparentée"

;ok
r.rules=New rules
r\num=14
r\name="Attaque sournoise"
r\description$="L'avatar a une bonus de "+signed_str(ATT_SOURNOISE)+" dégâts (non criticable) pour chaque marqueur d'archarnement de sa cible."

;ok
r.rules=New rules
r\num=15
r\name="Fusil +2"
r\description$="+2 en attaque à distance avec un fusil à canon rayé ou apparenté"

;inclus dans le profil
r.rules=New rules
r\num=16
r\name="Robuste"
r\description$="Diminue les dégâts pour les critiques. Ils restent cependant supérieurs aux dégâts normaux."


r.rules=New rules
r\num=17
r\name$="Mécanique"
r\description$="Ce personnage est mécanique et ne peut être soigné que par le Mécanicien."


;ok
r.rules=New rules
r\num=100
r\name$="Arme à distance"
r\description$="Cette arme est conçue pour être utilisée à distance."

;ok
r.rules=New rules
r\num=101
r\name$="Charges"
r\description$="Cet équipement ne peut être utilisé qu'un certain nombre de fois par combat. R.A.Z. entre chaque combat."

;ok
	r.rules=New rules
	r\num=102
	r\name$="A vapeur [1]"
	r\description$="Cet objet nécessite 1 de pression dans votre chaudière pour fonctionner. Les pressions nécessaires se cumulent."
	
	r.rules=New rules
	r\num=103
	r\name$="A vapeur [2]"
	r\description$="Cet objet nécessite 2 de pression dans votre chaudière pour fonctionner. Les pressions nécessaires se cumulent."
	
	r.rules=New rules
	r\num=104
	r\name$="A vapeur [3]"
	r\description$="Cet objet nécessite 3 de pression dans votre chaudière pour fonctionner. Les pressions nécessaires se cumulent."
	
	r.rules=New rules
	r\num=105
	r\name$="A vapeur [4]"
	r\description$="Cet objet nécessite 4 de pression dans votre chaudière pour fonctionner. Les pressions nécessaires se cumulent."
	
	r.rules=New rules
	r\num=106
	r\name$="A vapeur [5]"
	r\description$="Cet objet nécessite 5 de pression dans votre chaudière pour fonctionner. Les pressions nécessaires se cumulent."
		
;ok	
	r.rules=New rules
	r\num=150
	r\name$="Encombrant [1]"
	r\description$="Cet objet donne un malus de -1 (cumulable) à toutes les attaques et défenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=151
	r\name$="Encombrant [2]"
	r\description$="Cet objet donne un malus de -2 (cumulable) à toutes les attaques et défenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=152
	r\name$="Encombrant [3]"
	r\description$="Cet objet donne un malus de -3 (cumulable) à toutes les attaques et défenses quand il n'est pas en main."
	
	r.rules=New rules
	r\num=153
	r\name$="Encombrant [4]"
	r\description$="Cet objet donne un malus de -4 (cumulable) à toutes les attaques et défenses quand il n'est pas en main."
	


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
r\description$="";"Permet d'activer le GearBot (modèle 2)-1"

r.rules=New rules
r\num=204
r\name$=""
r\description$="";"Permet d'activer le GearBot (modèle 2)-2"



;; test

r.rules=New rules
r\num=314
r\name$="Petite taille"
r\description$="L'objet n'est pas adapté aux adultes."