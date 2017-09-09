;; MAP TEST SCRIPT ;;


sol=CreateCube()
ScaleEntity sol,5,0.1,5
PositionEntity sol,0,-0.1,0
tex=LoadTexture("textures\environnement\pave10.bmp")
ScaleTexture tex,0.1,0.1
EntityTexture sol,tex
FreeTexture tex
EntityType sol,type_sol(1,1)

sky=CreateSphere()
FlipMesh sky
ScaleEntity sky,30,30,30
tex=LoadTexture("textures\environnement\sky01.png")
EntityTexture sky,tex
FreeTexture tex
EntityFX sky,1

light=CreateLight()
RotateEntity light,45,45,0



pos_entrance#(1)=0
pos_entrance#(2)=0
pos_entrance#(3)=0