
from gtts import gTTS
import os
mytext = "vineeth is gay"
language = 'en'
myobj = gTTS(text=mytext,lang=language,slow=True)
myobj.save('myself.mp3')
os.system('start myself.mp3')