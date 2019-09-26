package com.daxie.testspace.joglf.model;

import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.model.Model3D;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class ModelOperation extends MainBase{
	public static void main(String[] args) {
		new ModelOperation(GLVersion.GL4);
	}
	public ModelOperation(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int model_handle;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		model_handle=Model3D.LoadModel("./Data/Model/OBJ/map0/temp.obj");
		
		Model3D.SetModelPosition(model_handle, VectorFunctions.VGet(0.0f, -10.0f, 0.0f));
		Model3D.SetModelScale(model_handle, VectorFunctions.VGet(1.0f, 2.0f, 1.0f));
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(20.0f, 20.0f, 20.0f),VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		Model3D.DrawModel(model_handle);
		GLDrawFunctions3D.DrawAxes(100.0f);
	}
}
