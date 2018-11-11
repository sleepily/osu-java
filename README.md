# osu!java

osu!java is aspiring to combine the best rhythm games into one big project.
The goal is to provide a highly differenciated gameplay by allowing multiple game modes in one song difficulty.

Also, since many players may not be able to buy expensive controllers or have the technical knowledge to build one themselves, MIDI support is in development to provide the option of playing with third-party DJ- and music software controllers.

### Game modes TODO:
- osu!std, osu!mania *in progress*
- osu!taiko, osu!catch)
- SDVX
- jubeat

### Features TODO:
- MP3 support *in progress*
- make osu!std beatmaps playable
  - beatmap converter (translate .osu file into objects) *done*
  - hitcircles *done*
  - sliders
  - spinners
  - mania LN
- make SDVX/kshootmania beatmaps playable
- MIDI device support: *on hold*
  - use jogwheels (DJ controllers) for SDVX VOL control
  - use launchpad/midifighter for jubeat
- song/beatmap editor
- tatacon controller support?

This project began in mid feb 2018.  
___EDIT:___ This project is currently on hold since I need to revert some changes that lead to the program not working at all. I'll hopefully be able to fix these errors in the next weeks; if I can't, either I'll redo this project from scratch or just throw it away and focus on other things, we'll see.
___EDIT (2018/11/11):___ I won't rework this project anymore. I began working on a rhythm game structure in Unity3D, which will be my main project from now on. Porting osu! into Unity3D won't be a hassle then (since the underlying structure is already done) and it will be much more performant, since my java clone didn't make use of any non-standard libraries really. So look forward to seeing cool multi-platform rhythm games, but this time in Unity.
