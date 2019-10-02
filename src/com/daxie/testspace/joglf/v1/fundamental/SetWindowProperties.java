package com.daxie.testspace.joglf.v1.fundamental;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.joglf.gl.front.WindowFront;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class SetWindowProperties extends MainBase{
	public static void main(String[] args) {
		new SetWindowProperties(GLVersion.GL4);
	}
	public SetWindowProperties(GLVersion gl_version) {
		super(gl_version);
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		WindowFront.SetWindowText("Test");
		WindowFront.SetBackgroundColor(ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		WindowFront.SetWindowSize(1280, 960);
		
		super.init(drawable);
	}
}
