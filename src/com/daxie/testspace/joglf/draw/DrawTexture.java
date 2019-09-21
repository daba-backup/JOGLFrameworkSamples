package com.daxie.testspace.joglf.draw;

import com.daxie.joglf.gl.texture.TextureMgr;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class DrawTexture extends MainBase{
	public static void main(String[] args) {
		new DrawTexture(GLVersion.GL4);
	}
	public DrawTexture(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int texture_handle;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		texture_handle=TextureMgr.LoadTexture("./Data/Texture/picture.jpg");
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		TextureMgr.DrawTexture(texture_handle, 0, 0, 640, 480);
	}
}
