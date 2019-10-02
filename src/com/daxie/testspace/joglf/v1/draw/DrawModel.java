package com.daxie.testspace.joglf.v1.draw;

import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.model.Model3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class DrawModel extends MainBase{
	public static void main(String[] args) {
		new DrawModel(GLVersion.GL4);
	}
	public DrawModel(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int model_handle;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(-50.0f, 50.0f, -50.0f), VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
		
		model_handle=Model3D.LoadModel("./Data/Model/model.obj");
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		Model3D.DrawModel(model_handle);
	}
}
