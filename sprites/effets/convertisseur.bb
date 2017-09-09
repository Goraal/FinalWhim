AppTitle "Test transparence"
Graphics3D 800,600,32,2
SetBuffer BackBuffer()
SeedRnd (MilliSecs())
Global frame_timer=CreateTimer(50)
HidePointer

img$="curseur"
typ$=".bmp"

image=LoadImage(img$+typ$)
Dim pixel(ImageWidth(image),ImageHeight(image),3)

Cls
DrawImage image,0,0
Flip


For x=0 To ImageWidth(image)
	For y=0 To ImageHeight(image)
		LockBuffer BackBuffer ()
		rgb = ReadPixelFast (x,y)
		pixel(x,y,1)=rgb Shr 16 And %11111111
		pixel(x,y,2)=rgb Shr 8 And %11111111
		pixel(x,y,3)=rgb And %11111111
		UnlockBuffer BackBuffer ()
	Next
Next

;	file=WriteFile(img$+".dat")
;	WriteInt file,ImageWidth(image)
;	WriteInt file,ImageHeight(image)
;	For x=0 To ImageWidth(image)
;		For y=0 To ImageHeight(image)
;			For t=1 To 3
;				WriteInt file,pixel(x,y,t)
;			Next
;		Next
;	Next


	file=ReadFile(img$+".dat")
	img_x=ReadInt(file)
	img_y=ReadInt(file)
	For x=0 To img_x
		For y=0 To img_y
			For t=1 To 3
				pixel(x,y,t)=ReadInt(file)*0.5
			Next
		Next
	Next


;	WaitKey 
;	
	Cls
	LockBuffer BackBuffer ()
	For x=0 To ImageWidth(image)
		For y=0 To ImageHeight(image)
			
			WritePixelFast x+20,y,pixel(x,y,3) Or (pixel(x,y,2) Shl 8) Or (pixel(x,y,1) Shl 16)
			
		Next
	Next
	UnlockBuffer BackBuffer ()
	Flip
	
	WaitKey

End