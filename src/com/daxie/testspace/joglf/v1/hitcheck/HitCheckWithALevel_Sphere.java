package com.daxie.testspace.joglf.v1.hitcheck;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.front.KeyboardFront;
import com.daxie.joglf.gl.input.keyboard.KeyboardEnum;
import com.daxie.joglf.gl.model.Model3D;
import com.daxie.joglf.gl.model.collision.CollResult;
import com.daxie.joglf.gl.model.collision.CollResultDim;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class HitCheckWithALevel_Sphere extends MainBase{
	public static void main(String[] args) {
		new HitCheckWithALevel_Sphere(GLVersion.GL4);
	}
	public HitCheckWithALevel_Sphere(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int model_handle;
	private Vector sphere_center;
	private float sphere_radius;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		model_handle=Model3D.LoadModel("./Data/Model/map.obj");
		
		//Collision information has to be set up beforehand to conduct collision check against a level.
		Model3D.SetupCollInfo(model_handle);
		
		sphere_center=VectorFunctions.VGet(0.0f, 0.0f, 0.0f);
		sphere_radius=2.0f;
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(10.0f, 20.0f, 10.0f), VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		//Move the sphere.
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_X)>0) {
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_UP)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(0.1f, 0.0f, 0.0f));
			}
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_DOWN)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(-0.1f, 0.0f, 0.0f));
			}
		}
		else if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_Y)>0) {
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_UP)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(0.0f, 0.1f, 0.0f));
			}
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_DOWN)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(0.0f, -0.1f, 0.0f));
			}
		}
		else if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_Z)>0) {
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_UP)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(0.0f, 0.0f, 0.1f));
			}
			if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_DOWN)>0) {
				sphere_center=VectorFunctions.VAdd(sphere_center, VectorFunctions.VGet(0.0f, 0.0f, -0.1f));
			}
		}
		
		//Hitcheck of the sphere against the level.
		boolean hit_flag=false;
		
		CollResultDim coll_result_dim=Model3D.CollCheck_Sphere(model_handle, sphere_center, sphere_radius);
		int hit_num=coll_result_dim.GetHitNum();
		for(int i=0;i<hit_num;i++) {
			CollResult coll_result=coll_result_dim.GetCollResult(i);
			if(coll_result.GetHitFlag()==false)continue;
			
			hit_flag=true;
			
			//Draw the face normal of the face that the sphere hits.
			Vector[] vertices=coll_result.GetVertices();
			Vector face_center=VectorFunctions.VAverage(vertices);
			Vector face_normal=coll_result.GetFaceNormal();
			
			Vector segment_pos_1=face_center;
			Vector segment_pos_2=VectorFunctions.VAdd(segment_pos_1, VectorFunctions.VScale(face_normal, 4.0f));
			
			GLDrawFunctions3D.DrawLine3D(segment_pos_1, segment_pos_2, ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f));
		}
		
		//If the sphere hits the level, then draw the sphere in blue.
		if(hit_flag==true) {
			GLDrawFunctions3D.DrawSphere3D(
					sphere_center, sphere_radius, 32, 32, ColorU8Functions.GetColorU8(0.0f, 0.0f, 1.0f, 1.0f));
		}
		//Otherwise draw the sphere in white.
		else {
			GLDrawFunctions3D.DrawSphere3D(
					sphere_center, sphere_radius, 32, 32, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
		}
		
		Model3D.DrawModel(model_handle);
	}
}
