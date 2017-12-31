
Function actionScript(str_action$)
	;parser l'action à faire
	SlashLocation=Instr(str_action$,"/")
	TypeAction$=Left$(str_action$,SlashLocation-1)
	Parametre$=Mid$(str_action$,SlashLocation+1,Len(str_action$))
	Select TypeAction$
		Case "Avancement"
			ChangeAvancement(Parametre$)
		Case "Script"
			SlashLocation=Instr(Parametre$,":")
			Num_script$=Left$(Parametre$,SlashLocation-1)
			event_action= Int(Mid$(Parametre$,SlashLocation+1,Len(Parametre$)))
			DebugLog "/"+Num_script$+":"+event_action
			script(Int(Num_script$))
		Default
			RuntimeError "l'actionScript '"+TypeAction$+"' n'est pas programmé"
	End Select
End Function

;
Function ChangeAvancement(Chemin$)
	;chargement du fichier Avancement
	If xml_Avancement=0 Then xml_Avancement=xmlLoad("Script/Avancement.xml")
	If xml_Avancement=0 Then RuntimeError "Le fichier Script/Avancement.xml n'existe pas"
	;parser le chemin
	Slash1Location=Instr(Chemin$,"/")
	Slash2Location=Instr(Chemin$,":",Slash1Location+1)
	Objet$=Left$(Chemin$,Slash1Location-1)
	Variable$=Mid$(Chemin$,Slash1Location+1,Slash2Location-Slash1Location-1)
	Valeur$=Mid$(Chemin$,Slash2Location+1,Len(chemin))
	DebugLog "/"+Objet$+"/"+Variable$+"::"+Valeur$
	;récupérer le noeud du chemin
	ConditionNode=xmlNodeFind(Variable$, xmlNodeFind(Objet$, xml_Avancement))
	If ConditionNode=0 Then RuntimeError "L'avancement "+Objet$+"/"+Variable$+" n'existe pas"
	;changer la variable
	xmlNodeDataSet(ConditionNode,Valeur$)
	;sauver
	;xmlSave("Script/Avancement.xml", xml_Avancement)
	
End Function

;A débugger: Doit pouvoir tester s'il y a un OUI, un NON ou un 9***********************************************************
Function TestAvancement(ToCheckCondition$)
	If ToCheckCondition$="" Then Return True ;S'il n'y a pas de condition c'est OK
	If ToCheckCondition$="" Then Return True ;S'il n'y a pas de condition c'est OK
	;parser le chemin
	EgaleLocation=Instr(ToCheckCondition$,":")
	Chemin$=Left$(ToCheckCondition$,EgaleLocation-1)
	Valeur$=Mid$(ToCheckCondition$,EgaleLocation+1,Len(ToCheckCondition$))
	;charger le fichier Avancement
	If xml_Avancement=0 Then xml_Avancement=xmlLoad("Script/Avancement.xml")
	If xml_Avancement=0 Then RuntimeError "Le fichier Script/Avancement.xml n'existe pas"
	;récupérer le noeud du chemin
    ConditionNode=xmlAdvNodeFind(Chemin$, xml_Avancement)
	;vérifier si on a le même string (au espace et au majuscule près)
	If Lower$(Trim(xmlNodeDataGet$(ConditionNode)))=Lower$(Trim(Valeur$))
		DebugLog "reponse "+Chemin$+":"+Valeur$+" = TRUE"
        Return True
    Else
		DebugLog "reponse "+Chemin$+":"+Valeur$+" = FALSE ("+xmlNodeDataGet$(ConditionNode)+")"
        Return False
    EndIf
	
End Function


Function TestConditionPourScriptXlm(ToBeTestedNood)
	;récupérer le chemin de la condition
	ToCheckCondition$=xmlNodeAttributeValueGet$(ToBeTestedNood, "condition")
	Return TestAvancement(ToCheckCondition$)

End Function


Function Main_ScriptXml(nomDuScript$, int_i)
		
		;Charge XML
		If nomDuScriptEnCours$<>nomDuScript$
			InitialiserXMLs(nomDuScript$,int_i)
			g_bHUDactif=0
		EndIf
		
		;Charge Variables de dialogue si elles sont vides
		If (disc_ligne(1)="" And disc_ligne(2)="" And disc_ligne(3)="" And disc_ligne(4)="" And disc_ligne(5)="" And disc_ligne(6)="" And disc_ligne(7)="" And disc_ligne(8)="")
			;Si erreur dans le chargement des variables => fin du dialogue
			If ChargerVariablesDialogues()=False Then int_i=0
		EndIf

		;si QCM
		If str_PJ$(2)<>""
			disc_len#=disc_len#+options#(3)*delta_frame*0.02    ; nico
			If (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50) ; nico
				For t=1 To 7                                    ; nico
					len_disc=len_disc+Len(disc_ligne(t))        ; nico
				Next                                         	; nico
				If disc_len#<len_disc+10						; nico
					disc_len#=10000 							; nico
					keys(12,2)=min(keys(12,2),49) 				; nico
					keys(14,2)=min(keys(14,2),49) 				; nico
					keys(1,2)=min(keys(1,2),49) 				; nico
				EndIf 											; nico
			EndIf 												; nico
			AfficherDialogue(1) 								; nico
			bIsAction=False
			If (str_PJ$(2)="" And (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50) )
				keys(14,2)=49
				choix_qcm=-10
				bIsAction=True
			ElseIf(AfficherDialogueQCM())
				bIsAction=True
			EndIf
			If (bIsAction) ;si une réponse a été validée
				;Récupérer la prochaine destination (Attention choix_qcm peut être différent du numéro de réponse)
				xml_phrase=xmlDataNodeFind(str_PJ$(choix_qcm+1),xml_ScriptPartiel)
				xml_reponseX=xmlNodeParent(xml_phrase)
				xml_gotoNode=xmlAdvNodeFind("Goto",xml_reponseX)
				int_i=Int(xmlNodeDataGet$(xml_gotoNode))
				;Forcer le re-chargement du XML
				nomDuScriptEnCours$=""
				;Faire les actions du goto
				str_actionToDo$=xmlNodeAttributeValueGet$(xml_gotoNode, "action")
				If str_actionToDo$<>"" Then actionScript(str_actionToDo$)
				;Afficher le dialogue dans le journal
				If Int(options#(7))=1
					new_log("Vous:"+str_PJ$(choix_qcm+1),0,38,255)
				Else
					new_log("You:"+str_PJ$(choix_qcm+1),0,38,255)
				Endif
				
				;nico : Remettre à zéro la longueur du texte défilant
				disc_len#=0 									; nico
			EndIf
		ElseIf str_PJ$(1)<>""
			disc_len#=disc_len#+options#(3)*delta_frame*0.02 	; nico
			If (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50) ; nico
				For t=1 To 7 									; nico
					len_disc=len_disc+Len(disc_ligne(t)) 		; nico
				Next 											; nico
				If disc_len#<len_disc+10 						; nico
					disc_len#=10000 							; nico
					keys(12,2)=min(keys(12,2),49) 				; nico
					keys(14,2)=min(keys(14,2),49) 				; nico
					keys(1,2)=min(keys(1,2),49) 				; nico
				EndIf 											; nico
			EndIf 												; nico
			AfficherDialogue(1) 								; nico
			If (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50)
				
				;Récupérer la prochaine destination (Attention choix_qcm peut être différent du numéro de réponse)
				xml_phrase=xmlDataNodeFind(str_PJ$(1),xml_ScriptPartiel)
				xml_reponseX=xmlNodeParent(xml_phrase)
				xml_gotoNode=xmlAdvNodeFind("Goto",xml_reponseX)
				int_i=Int(xmlNodeDataGet$(xml_gotoNode))
				;Forcer le re-chargement du XML
				nomDuScriptEnCours$=""
				;Faire les actions du goto
				str_actionToDo$=xmlNodeAttributeValueGet$(xml_gotoNode, "action")
				If str_actionToDo$<>"" Then actionScript(str_actionToDo$)
			EndIf
		Else
			disc_len#=disc_len#+options#(3)*delta_frame*0.02 	; nico
			AfficherDialogue(1) 								; nico
			If (keys(12,2)=50 Or keys(14,2)=50 Or keys(1,2)=50) 
				keys(12,2)=min(keys(12,2),49) 					
				keys(14,2)=min(keys(14,2),49) 					
				keys(1,2)=min(keys(1,2),49) 					
				len_disc=0 										; nico
				For t=1 To 7 									; nico
					len_disc=len_disc+Len(disc_ligne(t)) 		; nico
				Next 											; nico
				If disc_len#>len_disc+10 						; nico
					For j=1 To 8								; nico
						disc_ligne(j)=""						; nico
						disc_len#=0								; nico
					Next										; nico
					phrasesIndex=phrasesIndex+1
				Else 											; nico
					disc_len#=10000 							; nico
				EndIf 											; nico
			EndIf
	
		EndIf
		
		;s'il on sort de la conversation
		If int_i=0
			phrasesIndex=1
			nomDuScriptEnCours$=""
		EndIf
		
		DrawImage curseur,MouseX(),MouseY()
		
		Return(int_i)
		
End Function

Function InitialiserXMLs(nomDuScript$, num_dialogue)

	If xml_Avancement=0 Then xml_Avancement=xmlLoad("Script/Avancement.xml")
	If xml_Avancement=0 Then RuntimeError "Le fichier Script/Avancement.xml n'existe pas"
	
	xml_ScriptEnCours=xmlLoad("Script/"+nomDuScript$+".xml")
	If xml_ScriptEnCours=0 Then RuntimeError "Fichier XML corrompu : "+nomDuScript$
	nomDuScriptEnCours$=nomDuScript$

	;Chargement du sous-script dialogueX.Y (test des conditions de dialogueX.0 sinon test de celle de dialogueX.1 sinon ...)
	i=0
	Repeat
		xml_ScriptPartiel=xmlNodeFind("dialogue"+Str$(num_dialogue)+"."+Str(i), xml_ScriptEnCours)
		If xml_ScriptPartiel=0 Then RuntimeError "Le noeud dialogue"+Str$(num_dialogue)+"."+Str(i)+" n'existe pas"
		i=i+1
	Until(TestConditionPourScriptXlm(xml_ScriptPartiel))
	
	For j=1 To 8
		disc_ligne(j)=""
		str_PJ$(j)=""
	Next
	phrasesIndex=1
	; DebugLog "1."+Str$(i-1)
End Function

Function ChargerVariablesDialogues()

	;Récupérer l'image de fond
	cheminImage$ = xmlNodeAttributeValueGet$(xml_ScriptPartiel,"image")
	If fond_animation<>0
		FreeImage fond_animation
		fond_animation=0
	EndIf
	If cheminImage$<>""
		fond_animation=LoadImage(cheminImage$)
	EndIf
	; DebugLog "2/"+cheminImage$
	;Récupére les phrases et Nom du parleur
	xml_PhraseNode	=	xmlNodeFind("phrases"+Str$(phrasesIndex),xml_ScriptPartiel,False)
	;S'il n'y a plus de phrase, c'est la fin du dialogue
	If xml_PhraseNode=0 Then Return(False)
	str_PJ$(0)		=	xmlNodeAttributeValueGet$(xml_PhraseNode,"name")
	If str_PJ$(0)=""
		str_PJ$(0)="???"
	EndIf
	
	If str_PJ$(0)="Major" ; remplace le nom des PJs	
		For av.avatar=Each avatar
			If av\num = 1
				str_PJ$(0) =av\name$[Int(options#(7))]
			Endif
		Next
	EndIf
	
	If str_PJ$(0)="Leopold" ; remplace le nom des PJs	
		For av.avatar=Each avatar
			If av\num = 2
				str_PJ$(0) =av\name$[Int(options#(7))]
			Endif
		Next
	EndIf
	
	If str_PJ$(0)="Adeline" ; remplace le nom des PJs	
		For av.avatar=Each avatar
			If av\num = 3
				str_PJ$(0) =av\name$[Int(options#(7))]
			Endif
		Next
	EndIf
	
	str_Message$	=	xmlNodeDataGet$(xml_PhraseNode)

	MiseEnFormeMessageDialogue(str_Message$)
	
	;S'il n'y a pas d'autre phrase : charger les réponses QCM (si présent)
	If xmlNodeFind("phrases"+(phrasesIndex+1),xml_ScriptPartiel,False)=0
	choix_qcm=-10
		If xmlNodeFind("reponse1",xml_ScriptPartiel,False)<>0
			;Récupérer les réactions du joueur
			For j=1 To 8
				xml_reponseX=xmlNodeFind("reponse"+Str$(j), xml_ScriptPartiel)
				If xml_reponseX=0 Then Return(True)
				If (TestConditionPourScriptXlm(xml_reponseX));Vérifier si la réaction est possible
					;DebugLog "reponse"+Str$(j)
					xml_phrase=xmlNodeFind("reaction", xml_reponseX)
					If xml_phrase=0 Then RuntimeError "Il manque un 'reaction' dans "+ xml_ScriptEnCours
					str_PJ$(j)=xmlNodeDataGet$(xml_phrase)
					i=i+1
				EndIf
			Next
		EndIf
		
	EndIf
	Return(True)
End Function


;=============================Adavanced BlitzXML=================================
;Permet de retrouner un noeud 'fils' à l'aide de son chemin depuis un noeud 'père' quelque soit la profondeur entre le 'fils' et le 'père'
;Vérifie que le noeud existe sinon affiche un message d'erreur
;(ex: retourne le bon noeud 'Goto' si le chemin$ est "dialogue2/reponse1/Goto" )
	
Function xmlAdvNodeFind(Chemin$, xmlNode)
	;trouver le noeud cible
	SlashLocation=0
	xmlTargetNode=xmlNode
	Repeat
		PrevSlashLocation=SlashLocation
		SlashLocation=Instr(Chemin$,"/",PrevSlashLocation+1)
		str_nodename$=Mid$(Chemin$,PrevSlashLocation+1,SlashLocation-PrevSlashLocation-1)
		xmlTargetNode=xmlNodeFind(str_nodename$, xmlTargetNode)
		If xmlTargetNode=0 Then RuntimeError "Le noeud "+str_nodename$+" n'existe pas"
	Until(SlashLocation=0)
	Return(xmlTargetNode)
End Function

;=============================Adavanced BlitzXML=================================
;Permet de retrouner un noeud contenant la Donnée Contents$. Le mode récurisif permet de chercher dans les sous-fils.

Function xmlDataNodeFind(Contents$, Parent, Recurse = True)
	parentnode.xmlNode = Object.xmlNode(Parent)
	For this.xmlNode = Each xmlNode
		If this\Parent = parentnode Then
			If Lower(this\Contents) = Lower(Contents) Then
				Return Handle(this)
			End If
			If Recurse = True And this\ChildCount > 0 Then
				ret = xmlDataNodeFind(Contents, Handle(this), True)
				If ret <> 0 Then Return ret
			End If
		End If
	Next
End Function

;============================= BlitzXML =================================
;Copyright (C) 2005 John Judnich

;BlitzXML is an XML (eXtendable Markup Language) Function library For
;blitz. You don't even need To have any knowledge of the syntax of XML
;To use BlitzXML, although an understanding of the terms And structure
;is helpful.

;At the user's (programmer's) point of view,
;BlitzXML is a way of storing bits of data similar To the way folders
;are stored on a hard drive. For example an node (item) named "inventory" may contain
;child nodes (sub-items) such as, "key". Each node may have an unlimited
;amount of sub-nodes, And Each sub-node may have sub-sub-nodes, etc.
;Each node may have a name, And a number of attributes. Nodes may
;contain both sub-nodes (called children), And text data. To get a
;better idea how xml works, look at "example.xml".

;When a data structure is constructed with BltizXML, it may be saved
;To a file using XML syntax, which is really just a more strict form of
;HTML. XML files may also be loaded And parsed into BlitzXML just as
;easily. Due To the flexibility of XML, this library should be very
;useful For level editors, games that load levels from XML files,
;Or any program requiring structured Or
;complex data To be saved To And loaded from a file.

;========================================================================



;**** Constant Declarations =============================================

Const MAX_ATTRIBUTES = 32	;The maximum number of attributes a xmlNode may have
Const MAX_ERRORS = 64		;The maximum number of errors And warnings that will be processed until the xml parser aborts the operation
Const PARSER_RECURSE = 1024 ;The maximum number of virtually "recursive" steps For the parser.
Global XML_INDENTATION$ = Chr$(9)	;The character(s) used To indent when saving files

;**** Type Declarations =================================================

;xmlNode Type - This is the main building block of BlitzXML. All xml data
;is stored with this Type, which gets manipulated by the user. The xml data
;can Then be loaded from And saved To files.
Type xmlNode
	Field Name$								;The name of the node
	Field AttributeCount					;The number of attributes For the node
	Field AttributeName$[MAX_ATTRIBUTES]	;The attribute name array
	Field AttributeValue$[MAX_ATTRIBUTES]	;The attribute value array
	Field Contents$							;The data contents of the node
	Field Parent.xmlNode					;This node's parent node. If this is set To Null, Then this node is the "root" node
	Field Level								;This is the node's level
	
	;These fields are manipulated by xml_RegisterChild(), xml_GetChild(), And xml_UnregisterChild()
	Field ChildCount						;The number of the node's children
	Field ChildBank							;A memory bank of handles To the node's children
End Type


;**** Global Declarations ===============================================

Global xml_Error$[MAX_ERRORS]
Global xml_ErrorPos[MAX_ERRORS]
Global xml_ErrorCount


;**** Interface Functions ===============================================
;Interface functions are the functions the user (the programmer) uses
;in their program, unlike the internal functions, which are only called
;by these functions.

;This Function returns the level the node is at. If the node is the
;root node, it is at level 0. If it is a child of the root node,
;the level will be 1. If it is a child of a child of the root node,
;the level of 2 will be returned, etc.
Function xmlNodeLevel(Node)
	this.xmlNode = Object.xmlNode(Node)
	Return this\Level
End Function

;This returns a node's parent node. If the node has no parent (If it's
;the root node), 0 will be returned.
Function xmlNodeParent(Node)
	this.xmlNode = Object.xmlNode(Node)
	If this\Parent = Null Then Return 0
	Return Handle(this\Parent)
End Function

;This returns the number of children the node has. In many cases,
;the node will contain no children, therefore returning 0.
Function xmlNodeChildCount(Node)
	this.xmlNode = Object.xmlNode(Node)
	Return this\ChildCount
End Function

;This returns one of the node's children, specified by ChildIndex.
;ChildIndex may be set anywhere from 1 To the amount of children
;the node has, which can be obtained from the xmlNodeChildCount()
;Function.
Function xmlNodeChild(Node, ChildIndex)
	this.xmlNode = Object.xmlNode(Node)
	Return Handle(xml_GetChild(this, ChildIndex))
End Function

;This Function will search For the first node matching the specified
;name And parent. Specifying a parent (optional) will only search
;nodes that are children of the specified parent node. If you only want
;To find a direct child of this node (not sub-childs), set Recurse To False.
Function xmlNodeFind(Name$, Parent, Recurse = True)
	ret = 0
	parentnode.xmlNode = Object.xmlNode(Parent)
	For this.xmlNode = Each xmlNode
		If this\Parent = parentnode Then
			If Lower(this\Name) = Lower(Name) Then
				Return Handle(this)
			End If
			If Recurse = True And this\ChildCount > 0 Then
				ret = xmlNodeFind(Name, Handle(this), True)
				If ret <> 0 Then Return ret
			End If
		End If
	Next
End Function

;This Function adds a new node To the "tree" of existing xml nodes. Set
;ParentNode To the node you would like this To be a child of, Or set it
;To 0 If this is the "root" node. Note: only one root node is allowed.
;Optionally, Name$ can be set To a name the node will initially be given,
;although the node can be renamed later with xmlNodeNameSet()
Function xmlNodeAdd(ParentNode, Name$="NewNode")
	this.xmlNode = New xmlNode
	parent.xmlNode = Object.xmlNode(ParentNode)
	
	this\Parent = parent
	If parent = Null Then
		this\Level = 0
	Else
		top.xmlNode = parent
		If parent\ChildCount = 0 Then
			top.xmlNode = parent
		Else
			top.xmlNode = Object.xmlNode( xmlNodeChild(ParentNode, 1) )
		End If
		Insert this After top
		this\Level = parent\Level + 1
		xml_RegisterChild(parent, this)
	End If
	
	this\Name = Name
	Return Handle(this)
End Function

;This Function deletes the given node, including all of it's children
;(sub-nodes), If there are any. Ignore the ChildIndex variable, as it
;is used internally when recursively deleting the node's children.
;This can be used To delete an entire XML file in memory by deleting it's
;handle (root node)
Function xmlNodeDelete(Node, ChildIndex = 0)
	this.xmlNode = Object.xmlNode(Node)
	
	For i = 1 To this\ChildCount
		xmlNodeDelete(Handle(xml_GetChild(this, 1)), 1) ;The index is always 1 because the list will keep getting smaller while they are getting deleted - (just like holding down the delete key at the beginning of a document)
	Next
	
	If this\Parent <> Null Then
		If ChildIndex = 0 Then
			For i = 1 To this\Parent\ChildCount
				If xml_GetChild(this\Parent, i) = this Then ChildIndex = i:Exit
			Next
		End If
		xml_UnregisterChild(this\Parent, ChildIndex)
	End If
	
	FreeBank this\ChildBank
	Delete this
End Function

;This sets a node's name. Note: A node's name must not be a blank string
Function xmlNodeNameSet(Node, Name$)
	this.xmlNode = Object.xmlNode(Node)
	this\Name = Name
End Function

;This returns the name of a node
Function xmlNodeNameGet$(Node)
	this.xmlNode = Object.xmlNode(Node)
	Return this\Name
End Function

;This sets the value of an attribute of a node. If the attribute does
;not exist, it will be created. The attribute's value may be any valid
;string of characters, not including double quotes. The value is allowed
;To be a blank string.
;Example:
;xmlNodeAttributeSet(node, "alpha", "0.7")
Function xmlNodeAttributeValueSet(Node, Attribute$, Value$)
	this.xmlNode = Object.xmlNode(Node)
	
	;Check If the attribute exists Or not
	indx = 0
	For i = 1 To this\AttributeCount
		If Attribute = this\AttributeName[i] Then indx = i:Exit
	Next
	
	;Create a new attribute If it doesn't exist
	If indx = 0 Then
		this\AttributeCount = this\AttributeCount + 1
		this\AttributeName[this\AttributeCount] = Attribute
		indx = this\AttributeCount
	End If
	
	;Set the attribute's value
	this\AttributeValue[indx] = Value
End Function

;This returns the value of the specified attribute, If it exists. If it
;doesn't exist, a blank string will be returned.
;Example:
;EntityAlpha Entity\Mesh, xmlNodeAttributeGet(Entity\Node, "alpha")
Function xmlNodeAttributeValueGet$(Node, Attribute$)
	this.xmlNode = Object.xmlNode(Node)
	
	;Find the attribute
	indx=0
	For i = 1 To this\AttributeCount
		If Attribute = this\AttributeName[i] Then indx = i:Exit
	Next
	
	;If the attribute exists, Return it's value. If not, Return a blank string
	If indx = 0 Then
		Return ""
	Else
		Return this\AttributeValue[indx]
	End If
End Function

;This sets the name of an attribute (NOT it's value). Note: attribute
;names are Case sensitive
;Example:
;xmlNodeAttributeNameSet(node,"pitch","Xang")
Function xmlNodeAttributeNameSet(Node, Attribute$, NewName$)
	this.xmlNode = Object.xmlNode(Node)
	
	;Find the attribute
	indx = 0
	For i = 1 To this\AttributeCount
		If Attribute = this\AttributeName[i] Then indx = i:Exit
	Next

	;If the attribute exists, rename it
	If indx <> 0 Then
		this\AttributeName[indx] = NewName
	End If
End Function

;This deletes an attribute. Once a new attribute is created when
;using the xmlNodeAttributeSet() Function, it will continue To
;reside in memory, And be saved To a file even If it's value is 
;blank. To remove an un-used (Or used) attribute of a node, use
;this Function.
;Example:
;xmlNodeAttributeDelete(node, "hidden")
Function xmlNodeAttributeDelete(Node, Attribute$)
	this.xmlNode = Object.xmlNode(Node)
	
	;Find the attribute
	indx = 0
	For i = 1 To this\AttributeCount
		If Attribute = this\AttributeName[i] Then indx = i:Exit
	Next
	
	;Delete the attribute, If it exists
	If indx <> 0 Then
		this\AttributeName[indx] = this\AttributeName[this\AttributeCount]
		this\AttributeValue[indx] = this\AttributeValue[this\AttributeCount]
		this\AttributeCount = this\AttributeCount - 1
	End If
End Function

;This sets a node's data string. A node's data is a string of
;text contained within the opening And closing node tags.
;Example:
;xmlNodeDataSet(titlenode, "BlitzXML")
Function xmlNodeDataSet(Node, NodeData$)
	;DebugLog "xmlNodeDataSet("+Node+","+NodeData$+")"
	this.xmlNode = Object.xmlNode(Node)
	this\Contents = NodeData
End Function

;This returns a node's data string. A node's data is a string
;of text contained within the opening And closing node tags.
Function xmlNodeDataGet$(Node)
	this.xmlNode = Object.xmlNode(Node)
	Return this\Contents
End Function

;This Function saves all XML nodes To the specified file.
;If any errors occur, false will be returned, If not, true
;will be returned.
Function xmlSave(FileName$, Node)
	this.xmlNode = Object.xmlNode(Node)

	file = WriteFile(FileName)
	If file = 0 Then xml_AddError("Error writing XML file (possibly, file is in use, Or is the folder/drive/file is write protected).", 0):Return
	
	WriteLine file, "<?xml version="+Chr(34)+"1.0"+Chr(34)+" ?>"
	xml_WriteNode(file, this)
	
	CloseFile file
End Function

Function xml_WriteNode(File, Node.xmlNode)
	Local NodeContents$, Indent$, Indent2$
	
	NodeContents = Node\Name
	For i = 1 To Node\AttributeCount
		NodeContents = NodeContents + " " + Node\AttributeName[i] + "=" + Chr$(34) + Node\AttributeValue[i] + Chr$(34)
	Next
	
	Indent = String$(XML_INDENTATION$, Node\Level)
	Indent2 = String$(XML_INDENTATION$, Node\Level+1)
	
	If Node\ChildCount = 0 Then
		If Node\Contents = "" Then
			WriteLine File, Indent + "<" + NodeContents + "/>" 
		Else
		    WriteLine File, Indent + "<" + NodeContents + ">" + Node\Contents + "</" + Node\Name + ">"
		End If
	Else
		WriteLine File, Indent + "<" + NodeContents + ">"
		If Node\Contents <> "" Then WriteLine File, Indent2 + Node\Contents
		
		For i = 1 To Node\ChildCount
			xml_WriteNode(File, Object.xmlNode(xmlNodeChild(Handle(Node), i)))
		Next
		
		WriteLine File, Indent + "</" + Node\Name + ">"
	End If
End Function

;This Function loads And parses XML nodes from the specified XML file.
;Note: This (BlitzXML's xml parser) only supports xml files with standard
;xml tags And attributes with values enclosed in quotes. If the file
;is loaded successfully with no errors, a handle To the root node of the file
;will be returned. If not, 0 will be returned.
;Errors can be accessed using the xmlError$() And xmlErrorCount() functions.
Function xmlLoad(FileName$)
	Local attribute$[MAX_ATTRIBUTES]
	Local value$[MAX_ATTRIBUTES]
	Local nodestack[PARSER_RECURSE]
	Local rootnode
	
	DebugLog "Loading XML file: " + FileName
	xml_ClearErrors()
	begintime = MilliSecs()
	
	;Open the file
	file = ReadFile(FileName)
	If file = False Then
		xml_AddError("Error opening XML file: File does not exist.", 0)
		Return 0
	End If
	If Eof(file) = -1 Then
		xml_AddError("Error opening XML file: File is already in use by another program.", 0)
		Return 0
	End If
	
	;Read in all tags
	stacklevel = 0
	While Eof(file) = False
		;Get the Next tag Or data section
		tag$ = xml_NextItem(file)
		
		If tag$ <> "" Then 
					;rajout des lettres ç, à, Ç et À
		;If ch = 231 Then txt$ = txt$ + "ç"
		;If ch = 224 Then txt$ = txt$ + "à"
		;If ch = 199 Then txt$ = txt$ + "Ç"
		;If ch = 192 Then txt$ = txt$ + "À"
			If xml_ItemType = 2 Then
				;Node contents
				xmlNodeDataSet(nodestack[stacklevel - 1], xmlNodeDataGet(nodestack[stacklevel - 1]) + " " + tag)
			Else
				;Check If it's a closing tag, opening tag, Or stand-alone tag
				If Left(tag,1) = "/" Then
					;Closing tag
					stacklevel = stacklevel - 1
					
					tmp.xmlNode = Object.xmlNode(nodestack[stacklevel])
					If tag <> "/" + tmp\Name Then xml_AddError("Unclosed tag (found <"+tag+">, expected </"+tmp\Name+">", FilePos(file))
				Else
					;Create a new node
					If stacklevel > 0 Then parent = nodestack[stacklevel - 1] Else parent = 0
					node = xmlNodeAdd(parent)
					If stacklevel = 0 Then rootnode = node
					
					;Get the name And attributes from the tag
					For i = 0 To attr:attribute[i] = "":value[i] = "":Next:attr = 0:opened = False:name$ = ""
					length = Len(tag)
					For i = 1 To length
						ch$ = Mid(tag, i, 1)
						If attr = 0 And ch = " " Then attr = attr + 1
						If ch = "=" Then attr = -attr
						If ch = Chr(34) Then
							If attr > 0 Then xml_AddError("Expecting equals symbol", FilePos(file))
							opened = 1 - opened
							If opened = False Then attr = Abs(attr):attr = attr + 1
						End If
						If ch <> Chr(34) And attr < 0 And opened Then value[-attr] = value[-attr] + ch
						If attr = 0 Then
							name = name + ch
						Else
							If attr > 0 And ch <> Chr(34) And ch<>" " Then attribute[attr] = attribute[attr] + ch
						End If
					Next
					For i = 1 To attr-1
						xmlNodeAttributeValueSet(node, attribute[i], value[i])
					Next
					xmlNodeNameSet(node, name)
					nodestack[stacklevel] = node
					
					If Right(tag,1) = "/" Then
						;Stand-alone tag
					Else
						;Opening tag
						stacklevel = stacklevel + 1
					End If
				End If
			End If
		End If
	Wend
	
	CloseFile file
	
	endtime = MilliSecs()
	parsetime# = (endtime - begintime) / 1000.0
	If xmlErrorCount > 0 Then DebugLog "Parse failed" Else DebugLog "Parse completed in "+parsetime+" seconds."
	
	If xmlErrorCount > 0 Then Return 0 Else Return rootnode
End Function

;This Function returns the number of errors And warnings from the last
;file parse performed.
Function xmlErrorCount()
	Return xml_ErrorCount
End Function

;This returns the position of the specified error (in characters from
;the beginning of the file
Function xmlErrorPosition(ErrorNumber)
	If ErrorNumber > xml_ErrorCount Then Return 0
	Return xml_ErrorPos[ErrorNumber]
End Function

;This returns the description of the requested error.
Function xmlError$(ErrorNumber)
	If ErrorNumber > xml_ErrorCount Then Return ""
	Return xml_Error[ErrorNumber]
End Function



;**** Internal Functions ================================================
;Internal functions should not be called from ANYWHERE but from other
;BlitzXML functions. These functions are undocumented, And you should
;NOT use them.

Global xml_ItemType
Function xml_NextItem$(file)
	Local tag$
	While Eof(file) = False
		ch = ReadByte(file)
		If txt$ <> "" And (ch = 60 Or ch = 13) Then xml_ItemType = 2:SeekFile file,FilePos(file)-1:Return txt
		If ch <> 13 And ch <> 15 And ch <> 10 Then txt$ = txt$ + Chr(ch)
		If ch = 13 And txt <> "" Then txt = txt + " "
		
		If ch = 60 Then ;<
			If opened = True Then xml_AddError("Expecting closing bracket (>)", FilePos(file))
			opened = True
		End If
		If ch = 62 Then ;>
			txt = ""
			If opened = False Then xml_AddError("Expecting opening bracket (<)", FilePos(file))
			opened = False
			If Left(tag$,4) = "<!--" Or Left(tag$,2) = "<?" Then
				If Left(tag$,4) = "<!--" And Right(tag$,2) <> "--" Then xml_AddError("Expecting correct comment closure (-->)", FilePos(file))
				If Left(tag$,4) = "<?" And Right(tag$,2) <> "?" Then xml_AddError("Expecting correct header closure (?>)", FilePos(file))
				tag = ""
			Else
				xml_ItemType = 1
				Return Right(tag$,Len(tag$)-1)
			End If
		End If
		If opened Then tag$= tag$+ Chr(ch)

	Wend
End Function

Function xml_RegisterChild(Node.xmlNode, Child.xmlNode)
	;Incriment the child count
	Node\ChildCount = Node\ChildCount + 1
	
	;Allocate memory For the data
	If Node\ChildBank = False Then
		Node\ChildBank = CreateBank(4)
	Else
		ResizeBank Node\ChildBank, Node\ChildCount * 4
	End If
	
	;Write the data
	Value = Handle(Child)
	PokeInt Node\ChildBank, (Node\ChildCount - 1) * 4, Value
End Function

Function xml_GetChild.xmlNode(Node.xmlNode, ChildIndex)
	;Check If the ChildIndex is valid
	If ChildIndex > Node\ChildCount Then Return Null
	
	;Get the child xmlNode object And Return it
	Value = PeekInt(Node\ChildBank, (ChildIndex - 1) * 4)
	this.xmlNode = Object.xmlNode(Value)
	Return this
End Function

Function xml_UnregisterChild(Node.xmlNode, ChildIndex)
	;Check If the ChildIndex is valid
	If ChildIndex > Node\ChildCount Then Return False

	;"Swap" the child-To-be-deleted with the last child on the list, so the last child on the list is now the child To be deleted
	;(actually, it doesn't swap - To optimize it a little, the child-To-be-deleted doesn't get copied anywhere because it's not gonna be used)
	Value = PeekInt(Node\ChildBank, (Node\ChildCount - 1) * 4)
	PokeInt Node\ChildBank, (ChildIndex - 1) * 4, Value
	
	;Downsize the bank, erasing the last child on the list which would be the child-To-be-deleted
	ResizeBank Node\ChildBank, (Node\ChildCount - 1) * 4
	Node\ChildCount = Node\ChildCount - 1
	
	Return True
End Function

Function xml_ClearErrors()
	xml_ErrorCount = 0
End Function

Function xml_AddError(Description$, pos)
	xml_ErrorCount = xml_ErrorCount + 1
	xml_ErrorPos[xml_ErrorCount] = pos
	xml_Error[xml_ErrorCount] = Description
	
	DebugLog "Error at char #"+pos+":  "+Description
End Function