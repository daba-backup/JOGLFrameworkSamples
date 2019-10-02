package com.daxie.testspace.joglf.v1.hitcheck;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.hitcheck.HitCheckFunctions;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class Capsule_Capsule extends MainBase{
	public static void main(String[] args) {
		new Capsule_Capsule(GLVersion.GL4);
	}
	public Capsule_Capsule(GLVersion gl_version) {
		super(gl_version);
	}
	
	private Vector capsule1_pos_1;
	private Vector capsule1_pos_2;
	private float capsule1_radius;
	private Vector capsule2_pos_1;
	private Vector capsule2_pos_2;
	private float capsule2_radius;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		capsule1_pos_1=VectorFunctions.VGet(0.0f, -10.0f, 0.0f);
		capsule1_pos_2=VectorFunctions.VGet(0.0f, 10.0f, 0.0f);
		capsule1_radius=10.0f;
		capsule2_pos_1=VectorFunctions.VGet(10.0f, 0.0f, 50.0f);
		capsule2_pos_2=VectorFunctions.VGet(-10.0f, 0.0f, 50.0f);
		capsule2_radius=10.0f;
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		capsule2_pos_1=VectorFunctions.VAdd(capsule2_pos_1, VectorFunctions.VGet(0.0f, 0.0f, -1.0f));
		capsule2_pos_2=VectorFunctions.VAdd(capsule2_pos_2, VectorFunctions.VGet(0.0f, 0.0f, -1.0f));
		
		//If two capsules hit, the draw capsule 2 in green.
		if(HitCheckFunctions.HitCheck_Capsule_Capsule(
				capsule1_pos_1, capsule1_pos_2, capsule1_radius, 
				capsule2_pos_1, capsule2_pos_2, capsule2_radius)==true) {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule2_pos_1, capsule2_pos_2, capsule2_radius, 32, 32, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		}
		//Otherwise draw capsule 2 in white.
		else {
			GLDrawFunctions3D.DrawCapsule3D(
					capsule2_pos_1, capsule2_pos_2, capsule2_radius, 32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		GLDrawFunctions3D.DrawCapsule3D(
				capsule1_pos_1, capsule1_pos_2, capsule1_radius, 32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		GLDrawFunctions3D.DrawAxes(100.0f);
	}
}
