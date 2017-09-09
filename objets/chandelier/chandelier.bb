AppTitle "chandeleir"
Graphics3D 800,600,0,2
SetBuffer BackBuffer()
HidePointer


;;;chandelier
xa=0
ya=2
za=0

chandelier=LoadMesh("objets\chandelier\chandelier.x")
ScaleEntity chandelier,0.5,0.5,0.5
PositionEntity chandelier,xa,ya-0.8,za
RotateEntity chandelier,0,22.5,0

chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
SpriteViewMode chaine1,24
EntityFX chaine1,16
EntityAlpha chaine1,1
ScaleSprite chaine1,0.045,0.45
PositionEntity chaine1,xa+0.20,ya-0.4,za
RotateEntity chaine1,0,0,26,6
chaine2=CopyEntity(chaine1)
TurnEntity chaine2,180,90,0

chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
SpriteViewMode chaine1,24
EntityFX chaine1,16
EntityAlpha chaine1,1
ScaleSprite chaine1,0.045,0.45
PositionEntity chaine1,xa-0.20,ya-0.4,za
RotateEntity chaine1,0,0,-26,6
chaine2=CopyEntity(chaine1)
TurnEntity chaine2,180,90,0

chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
SpriteViewMode chaine1,24
EntityFX chaine1,16
EntityAlpha chaine1,1
ScaleSprite chaine1,0.045,0.45
PositionEntity chaine1,xa,ya-0.4,za+.20
RotateEntity chaine1,-26.6,0,0
chaine2=CopyEntity(chaine1)
TurnEntity chaine2,180,90,0

chaine1=LoadSprite("objets\chandelier\chaine.bmp",4)
SpriteViewMode chaine1,24
EntityFX chaine1,16
EntityAlpha chaine1,1
ScaleSprite chaine1,0.045,0.45
PositionEntity chaine1,xa,ya-0.4,za-0.2
RotateEntity chaine1,26.6,0,0
chaine2=CopyEntity(chaine1)
TurnEntity chaine2,180,90,0

For t=1 To 8
	flamme=LoadSprite("objets\chandelier\shot1.bmp")
	ScaleSprite flamme,0.05,0.05
	alpha#=22.5+45*t
	PositionEntity flamme,xa+Cos(alpha#)*0.415,ya-0.66,za+Sin(alpha#)*0.415
Next




;;;fin chandelier

sol=CreateCube()
ScaleEntity sol,10,0.001,10
textu=LoadTexture("sol.bmp")
ScaleTexture textu,0.2,0.2
EntityTexture sol,textu

etalon=CreateCube()
ScaleEntity etalon,0.5,0.5,0.5
PositionEntity etalon,0,0.5,0
EntityAlpha etalon,0;.4


AmbientLight 100,100,100
light=CreateLight()
TurnEntity light,50,-145,0

cam_pivot=CreatePivot()
PositionEntity cam_pivot,0,1,0
cam=CreateCamera(cam_pivot)
PositionEntity cam,0,0,-1.5
CameraRange cam,0.03,50

While Not(KeyHit(01))
Cls

x#=x#+MouseYSpeed()*0.5
y#=y#-MouseXSpeed()
MoveMouse 400,300

RotateEntity cam_pivot,x#,y#,0


UpdateWorld
RenderWorld

Text 5,5,"Esc pour quitter"

Flip
Wend

End