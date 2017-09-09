AppTitle "A quoi ressemble votre perso ?"
Graphics3D 800,600,0,2
SetBuffer BackBuffer()
HidePointer

perso=LoadMD2("Aude\tris.md2")
ScaleEntity perso,0.03,0.03,0.03
PositionEntity perso,0,0.70,0
tex=LoadTexture("Aude\Aude.pcx");texture_perso.jpg")
EntityTexture perso,tex

AnimateMD2 perso,1,0.1,0,164

tex3=LoadTexture("grass.bmp")
ScaleTexture tex3,0.1,0.1
sol1=CreateCube()
ScaleEntity sol1,20,0.02,20
EntityAlpha sol1,0.2
EntityTexture sol1,tex3
sol2=CreateMirror()

AmbientLight 100,100,100
light=CreateLight()
TurnEntity light,0,-145,0

cam_pivot=CreatePivot()
PositionEntity cam_pivot,0,1,0
cam=CreateCamera(cam_pivot)
PositionEntity cam,0,0,-2
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