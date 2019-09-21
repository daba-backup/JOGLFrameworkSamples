package com.daxie.testspace.joglf.sound;

import com.daxie.basis.matrix.Matrix;
import com.daxie.basis.matrix.MatrixFunctions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.al.sound.Sound3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.daxie.tool.MathFunctions;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

//Play a WAV sound.
//Note that 3D sound is disabled for stereo WAV sounds.

public class PlayWAV extends MainBase{
	public static void main(String[] args) {
		new PlayWAV(GLVersion.GL4);
	}
	public PlayWAV(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int sound_handle;
	private Vector source_position;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		sound_handle=Sound3D.LoadSound("./Data/Sound/sound.wav");
		source_position=VectorFunctions.VGet(-30.0f, 0.0f, 0.0f);
		Sound3D.SetSoundSourcePosition(sound_handle, source_position);
		
		Sound3D.PlaySound(sound_handle);
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		Matrix rot_y=MatrixFunctions.MGetRotY(MathFunctions.DegToRad(1.0f));
		source_position=VectorFunctions.VTransform(source_position, rot_y);
		
		Sound3D.SetSoundSourcePosition(sound_handle, source_position);
	}
}
