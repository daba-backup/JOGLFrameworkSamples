package com.daxie.testspace.joglf.practical.objviewer;

import com.daxie.joglf.gl.front.KeyboardFront;
import com.daxie.joglf.gl.front.WindowFront;
import com.daxie.joglf.gl.input.keyboard.KeyboardEnum;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Main extends MainBase{
	public static void main(String[] args) {
		new Main(GLVersion.GL4);
	}
	public Main(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Camera camera;
	private ModelMgr model_mgr;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		camera=new Camera();
		model_mgr=new ModelMgr();
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		camera.Update();
		model_mgr.Draw();
		
		GLDrawFunctions3D.DrawAxes(100.0f);
		
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_ESCAPE)==1) {
			WindowFront.CloseWindow();
		}
	}
}
