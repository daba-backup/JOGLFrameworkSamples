package com.daxie.testspace.joglf.hitcheck;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.hitcheck.HitCheckFunctions;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Capsule_Triangle extends MainBase{
	public static void main(String[] args) {
		new Capsule_Triangle(GLVersion.GL4);
	}
	public Capsule_Triangle(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Vector triangle_pos_1;
	private Vector triangle_pos_2;
	private Vector triangle_pos_3;
	private Vector capsule_pos_1;
	private Vector capsule_pos_2;
	private float capsule_radius;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		triangle_pos_1=VectorFunctions.VGet(-20.0f, 0.0f, -20.0f);
		triangle_pos_2=VectorFunctions.VGet(20.0f, 0.0f, -20.0f);
		triangle_pos_3=VectorFunctions.VGet(0.0f, 0.0f, 20.0f);
		capsule_pos_1=VectorFunctions.VGet(0.0f, 50.0f, 0.0f);
		capsule_pos_2=VectorFunctions.VGet(0.0f, 40.0f, 0.0f);
		capsule_radius=10.0f;
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		capsule_pos_1=VectorFunctions.VAdd(capsule_pos_1, VectorFunctions.VGet(0.0f, -0.5f, 0.0f));
		capsule_pos_2=VectorFunctions.VAdd(capsule_pos_2, VectorFunctions.VGet(0.0f, -0.5f, 0.0f));
		//If the capsule and the triangle hit, the draw the capsule in green.
		if(HitCheckFunctions.HitCheck_Capsule_Triangle(
				capsule_pos_1, capsule_pos_2, capsule_radius, triangle_pos_1, triangle_pos_2, triangle_pos_3)==true) {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule_pos_1, capsule_pos_2, capsule_radius, 32, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		}
		//Otherwise draw the capsule in white.
		else {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule_pos_1, capsule_pos_2, capsule_radius, 32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		GLDrawFunctions3D.DrawTriangle3D(
				triangle_pos_1, triangle_pos_2, triangle_pos_3, 
				ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		GLDrawFunctions3D.DrawAxes(100.0f);
	}
}
