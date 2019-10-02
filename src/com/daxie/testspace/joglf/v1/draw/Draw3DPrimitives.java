package com.daxie.testspace.joglf.v1.draw;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.matrix.Matrix;
import com.daxie.basis.matrix.MatrixFunctions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.daxie.tool.MathFunctions;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Draw3DPrimitives extends MainBase{
	public static void main(String[] args) {
		new Draw3DPrimitives(GLVersion.GL4);
	}
	public Draw3DPrimitives(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Vector camera_position;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		camera_position=VectorFunctions.VGet(-50.0f, 50.0f, -50.0f);
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				camera_position, VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		//Rotate the camera around Y-axis.
		Matrix rot_y=MatrixFunctions.MGetRotY(MathFunctions.DegToRad(1.0f));
		camera_position=VectorFunctions.VTransform(camera_position, rot_y);
		CameraFront.SetCameraPositionAndTarget_UpVecY(camera_position, VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
		
		GLDrawFunctions3D.DrawLine3D(
				VectorFunctions.VGet(-100.0f, 0.0f, 0.0f),
				VectorFunctions.VGet(100.0f, 0.0f, 0.0f),
				ColorU8Functions.GetColorU8(1.0f,0.0f,0.0f,1.0f));
		GLDrawFunctions3D.DrawLine3D(
				VectorFunctions.VGet(0.0f, -100.0f, 0.0f),
				VectorFunctions.VGet(0.0f, 100.0f, 0.0f),
				ColorU8Functions.GetColorU8(0.0f,1.0f,0.0f,1.0f));
		GLDrawFunctions3D.DrawLine3D(
				VectorFunctions.VGet(0.0f, 0.0f, -100.0f),
				VectorFunctions.VGet(0.0f, 0.0f, 100.0f),
				ColorU8Functions.GetColorU8(0.0f,0.0f,1.0f,1.0f));
		
		GLDrawFunctions3D.DrawSphere3D(
				VectorFunctions.VGet(-20.0f, 0.0f, 20.0f), 20.0f, 32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 0.0f, 1.0f));
		GLDrawFunctions3D.DrawCapsule3D(
				VectorFunctions.VGet(-20.0f, -10.0f, -20.0f), VectorFunctions.VGet(-20.0f, 10.0f, -20.0f), 
				15.0f, 32, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 1.0f, 1.0f));
	}
}
