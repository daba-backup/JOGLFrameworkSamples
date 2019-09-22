package com.daxie.testspace.joglf.hitcheck;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.hitcheck.HitCheckFunctions;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Sphere_Capsule extends MainBase{
	public static void main(String[] args) {
		new Sphere_Capsule(GLVersion.GL4);
	}
	public Sphere_Capsule(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Vector sphere_center;
	private float sphere_radius;
	private Vector capsule_pos_1;
	private Vector capsule_pos_2;
	private float capsule_radius;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		sphere_center=VectorFunctions.VGet(0.0f, 0.0f, 0.0f);
		sphere_radius=15.0f;
		capsule_pos_1=VectorFunctions.VGet(-50.0f, -50.0f, -50.0f);
		capsule_pos_2=VectorFunctions.VGet(-40.0f, -40.0f, -40.0f);
		capsule_radius=10.0f;
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(50.0f, 50.0f, -50.0f), VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		capsule_pos_1=VectorFunctions.VAdd(capsule_pos_1, VectorFunctions.VGet(0.5f, 0.5f, 0.5f));
		capsule_pos_2=VectorFunctions.VAdd(capsule_pos_2, VectorFunctions.VGet(0.5f, 0.5f, 0.5f));
		
		//If the sphere and the capsule hit, then draw the capsule in green.
		if(HitCheckFunctions.HitCheck_Sphere_Capsule(
				sphere_center, sphere_radius, 
				capsule_pos_1, capsule_pos_2, capsule_radius)==true) {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule_pos_1, capsule_pos_2, capsule_radius, 
					32, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		}
		//Otherwise draw the capsule in white.
		else {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule_pos_1, capsule_pos_2, capsule_radius, 
					32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		GLDrawFunctions3D.DrawSphere3D(
				sphere_center, sphere_radius, 
				32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		GLDrawFunctions3D.DrawAxes(100.0f);
	}
}
