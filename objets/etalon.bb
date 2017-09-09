ClearWorld
AppTitle "Etalon"
Graphics3D 800,600,0,2
SetBuffer BackBuffer()

pivot=CreatePivot()

;etalon=CreateCube(pivot)
;ScaleEntity etalon,1,0.1,3
;EntityAlpha etalon,0.5

cube=CreateCube(pivot)
;PositionEntity cube,0.3,-1.3,0
;PositionEntity cube,0.02,0.85,-0.38
ScaleEntity cube,.05,.5,.5
EntityAlpha cube,0.5

jack=LoadMesh("Patrouilleur_volant\Patrouilleur_volant.x",pivot)
a#=1.5
ScaleEntity jack,a#,a#,a#
;EntityAlpha jack,0.5

a=CreateLight(1)

cam=CreateCamera()
PositionEntity cam,0,0,-3
CameraClsColor cam,0,150,100
PointEntity cam,jack

While Not KeyDown(01)
	TurnEntity pivot,0,0.5,0	
	
	UpdateWorld
	RenderWorld
	Flip
Wend
ClearWorld
End

;helico
;missile gauche : 1,-.5,0
;missile droit : -1,-.5,0
;tourelle      : 0,-.8,-2
;helice grande : 0,1.1,0
;helice arrière : -.3,.23,4.15 