package com.daxie.testspace.joglf.hitcheck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.model.Model3D;
import com.daxie.joglf.gl.model.collision.CollResult;
import com.daxie.joglf.gl.wrapper.GLDrawFunctions3D;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class HitCheckWithALevel_Segment extends MainBase{
	public static void main(String[] args) {
		new HitCheckWithALevel_Segment(GLVersion.GL4);
	}
	public HitCheckWithALevel_Segment(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int model_handle;
	
	private Random random;
	private List<Vector> positions;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		model_handle=Model3D.LoadModel("./Data/Model/map.obj");
		
		//Collision information has to be set up beforehand to conduct collision check against a level.
		Model3D.SetupCollInfo(model_handle);
		
		random=new Random();
		positions=new ArrayList<>();
		
		CameraFront.SetCameraPositionAndTarget_UpVecY(
				VectorFunctions.VGet(10.0f, 20.0f, 10.0f), VectorFunctions.VGet(0.0f, 0.0f, 0.0f));
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		//Generate positions.
		float x=-10.0f;
		float z=-10.0f;
		for(int i=0;i<20;i++) {
			x=-10.0f;
			for(int j=0;j<20;j++) {
				int rnd=random.nextInt(3000);
				if(rnd<10) {
					positions.add(VectorFunctions.VGet(x, 30.0f, z));
				}
				
				x+=1.0f;
			}
			z+=1.0f;
		}
		
		//Update positions.
		for(Vector v:positions) {
			v.SetY(v.GetY()-0.5f);
		}
		
		//Remove positions.
		Iterator<Vector> it=positions.iterator();
		while(it.hasNext()) {
			Vector v=it.next();
			if(v.GetY()<-10.0f)it.remove();
		}
		
		//Draw segments.
		for(Vector v:positions) {
			Vector segment_pos_1=v;
			Vector segment_pos_2=VectorFunctions.VAdd(segment_pos_1, VectorFunctions.VGet(0.0f, 5.0f, 0.0f));
			
			CollResult coll_result=Model3D.CollCheck_Segment(model_handle, segment_pos_1, segment_pos_2);
			if(coll_result.GetHitFlag()==true) {
				GLDrawFunctions3D.DrawLine3D(segment_pos_1, segment_pos_2, ColorU8Functions.GetColorU8(0.0f, 0.0f, 1.0f, 1.0f));
			}
			else {
				GLDrawFunctions3D.DrawLine3D(segment_pos_1, segment_pos_2, ColorU8Functions.GetColorU8(1.0f, 1.0f, 1.0f, 1.0f));
			}
		}
		
		Model3D.DrawModel(model_handle);
	}
}
