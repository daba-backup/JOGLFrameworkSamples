package com.daxie.testspace.joglf.sound;

import com.daxie.joglf.al.player.MP3Player;
import com.daxie.joglf.gl.front.KeyboardFront;
import com.daxie.joglf.gl.input.keyboard.KeyboardEnum;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class PlayMP3 extends MainBase{
	public static void main(String[] args) {
		new PlayMP3(GLVersion.GL4);
	}
	public PlayMP3(GLVersion gl_version) {
		super(gl_version);
	}
	
	int sound_handle;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		sound_handle=MP3Player.LoadSound("./Data/Sound/sound.mp3");
		MP3Player.PlaySound(sound_handle);
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_S)==1) {
			MP3Player.DeleteSound(sound_handle);
		}
	}
}
