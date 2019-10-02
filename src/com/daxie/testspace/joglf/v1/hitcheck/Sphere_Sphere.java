package com.daxie.testspace.joglf.v1.hitcheck;

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

public class Sphere_Sphere extends MainBase{
	public static void main(String[] args) {
		new Sphere_Sphere(GLVersion.GL4);
	}
	public Sphere_Sphere(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Vector sphere1_center;
	private float sphere1_radius;
	private Vector sphere2_center;
	private float sphere2_radius;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		sphere1_center=VectorFunctions.VGet(0.0f, 0.0f, 0.0f);
		sphere1_radius=15.0f;
		sphere2_center=VectorFunctions.VGet(-30.0f, 0.0f, -30.0f);
		sphere2_radius=10.0f;
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(50.0f,50.0f,-50.0f), VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		sphere2_center=VectorFunctions.VAdd(sphere2_center, VectorFunctions.VGet(0.5f, 0.0f, 0.5f));
		//If two spheres hit, then draw sphere 2 in green.
		if(HitCheckFunctions.HitCheck_Sphere_Sphere(sphere1_center, sphere1_radius, sphere2_center, sphere2_radius)==true) {
			GLDrawFunctions3D.DrawSphere3D(
					sphere2_center, sphere2_radius, 
					32, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		}
		//Otherwise draw sphere 2 in white.
		else {
			GLDrawFunctions3D.DrawSphere3D(
					sphere2_center, sphere2_radius, 
					32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		GLDrawFunctions3D.DrawAxes(100.0f);
		GLDrawFunctions3D.DrawSphere3D(
				sphere1_center, sphere1_radius, 
				32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
	}
}
