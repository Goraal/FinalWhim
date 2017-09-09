
EntityPickMode sol(100,100,0,0,1,0,"grass15.bmp",5),2

sol=sol(100,100,0,0,1,0,"grass15.bmp",5)
TurnEntity sol,0,0,15
EntityPickMode sol,2

light=CreateLight()
LightColor light,250,200,200
RotateEntity light,35,-90,0
AmbientLight 150,150,150

;new_smoke_source(1,-5,1,5)


mur(5,0.1,0,0,5,2,0,"brick20.bmp")
mur(5,0.1,-7,0,5,2,0,"brick20.bmp")


;porte
new_porte(101)
new_porte(102)
For p.porte=Each porte
	Select p\num
		Case 101
			p\pivot=CreatePivot()
			p\manikin=CreateCube(p\pivot)
			ScaleEntity p\manikin,0.5,1,0.02
			tex=LoadTexture("textures\environnement\palissade.jpg")
			If tex<>0 Then EntityTexture p\manikin,tex
			EntityType p\manikin,type_mur
			p\pos_init#[1]=-3
			p\pos_init#[2]=1
			p\pos_init#[3]=5
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=-2
			p\pos_final#[2]=1
			p\pos_final#[3]=5
			p\pos_final#[4]=0
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=1
			p\speed#[2]=0
			p\speed#[3]=0
			p\speed#[4]=0
			p\speed#[5]=0
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
		Case 102
			p\pivot=CreatePivot()
			p\manikin=CreateCube(p\pivot)
			ScaleEntity p\manikin,0.5,1,0.02
			tex=LoadTexture("textures\environnement\palissade.jpg")
			If tex<>0 Then EntityTexture p\manikin,tex
			EntityType p\manikin,type_mur
			p\pos_init#[1]=-4
			p\pos_init#[2]=1
			p\pos_init#[3]=5
			p\pos_init#[4]=0
			p\pos_init#[5]=0
			p\pos_init#[6]=0
			p\pos_final#[1]=-5
			p\pos_final#[2]=1
			p\pos_final#[3]=5
			p\pos_final#[4]=0
			p\pos_final#[5]=0
			p\pos_final#[6]=0
			p\speed#[1]=-1
			p\speed#[2]=0
			p\speed#[3]=0
			p\speed#[4]=0
			p\speed#[5]=0
			p\speed#[6]=0
			If p\etat=0
				For t=1 To 6
					p\pos_act#[t]=p\pos_init#[t]
				Next
				PositionEntity p\pivot,p\pos_init#[1],p\pos_init#[2],p\pos_init#[3]
				RotateEntity p\pivot,p\pos_init#[4],p\pos_init#[5],p\pos_init#[6]
			Else
				For t=1 To 6
					p\pos_act#[t]=p\pos_final#[t]
				Next
				PositionEntity p\pivot,p\pos_final#[1],p\pos_final#[2],p\pos_final#[3]
				RotateEntity p\pivot,p\pos_final#[4],p\pos_final#[5],p\pos_final#[6]
			EndIf
	End Select
Next

Select entrance
	Default
		pos_entrance#(1)=-2
		pos_entrance#(2)=0
		pos_entrance#(3)=3
End Select