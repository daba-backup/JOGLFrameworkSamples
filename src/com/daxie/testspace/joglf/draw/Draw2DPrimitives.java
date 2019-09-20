package com.daxie.testspace.joglf.draw;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions2D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Draw2DPrimitives extends MainBase{
	public static void main(String[] args) {
		new Draw2DPrimitives(GLVersion.GL4);
	}
	public Draw2DPrimitives(GLVersion gl_version) {
		super(gl_version);
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		GLDrawFunctions2D.DrawLine2D(5, 5, 500, 5, ColorU8Functions.GetColorU8(1.0f, 0.0f, 0.0f, 1.0f));
		GLDrawFunctions2D.DrawCircle2D(320, 240, 200, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		GLDrawFunctions2D.DrawRectangle2D(50, 50, 500, 400, ColorU8Functions.GetColorU8(1.0f, 1.0f, 0.0f, 1.0f));
	}
}
