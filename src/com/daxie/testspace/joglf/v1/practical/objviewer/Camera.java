package com.daxie.testspace.joglf.v1.practical.objviewer;

import com.daxie.basis.vector.Vector;
import com.daxie.basis.vector.VectorFunctions;
import com.daxie.joglf.gl.front.CameraFront;
import com.daxie.joglf.gl.front.KeyboardFront;
import com.daxie.joglf.gl.front.MouseFront;
import com.daxie.joglf.gl.input.keyboard.KeyboardEnum;
import com.daxie.joglf.gl.input.mouse.MouseEnum;
import com.daxie.tool.MathFunctions;

public class Camera {
	private Vector position;
	private float v_rotation;
	private float h_rotation;
	
	private final float TRANSLATE_SPEED=1.0f;
	private final float ROTATE_SPEED=0.02f;
	private final float V_ANGLE_MIN=MathFunctions.DegToRad(-80.0f);
	private final float V_ANGLE_MAX=MathFunctions.DegToRad(80.0f);
	
	public Camera() {
		position=VectorFunctions.VGet(-50.0f, 50.0f, -50.0f);
		v_rotation=0.0f;
		h_rotation=0.0f;
		
		CameraFront.SetCameraPositionAndAngle(position, v_rotation, h_rotation, 0.0f);
	}
	
	private void TranslateByUserInput() {
		Vector front=CameraFront.GetCameraFrontVector();
		
		Vector right=VectorFunctions.VCross(front, VectorFunctions.VGet(0.0f, 1.0f, 0.0f));
		right=VectorFunctions.VNorm(right);
		
		Vector translate=VectorFunctions.VGet(0.0f, 0.0f, 0.0f);
		//Move forward
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_W)>0) {
			translate=VectorFunctions.VAdd(translate, front);
		}
		//Move backward
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_S)>0) {
			translate=VectorFunctions.VAdd(translate, VectorFunctions.VScale(front, -1.0f));
		}
		//Move right
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_D)>0) {
			translate=VectorFunctions.VAdd(translate, right);
		}
		//Move left
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_A)>0) {
			translate=VectorFunctions.VAdd(translate, VectorFunctions.VScale(right, -1.0f));
		}
		
		if(VectorFunctions.VSize(translate)>1.0E-8f) {
			translate=VectorFunctions.VNorm(translate);
			translate=VectorFunctions.VScale(translate, TRANSLATE_SPEED);
			
			position=VectorFunctions.VAdd(position, translate);
		}
	}
	private void RotateByUserInput() {
		int mouse_click_count=MouseFront.GetMousePressingCount(MouseEnum.MOUSE_LEFT);
		
		if(mouse_click_count==0) {
			MouseFront.SetFixMousePointerFlag(false);
		}
		else if(mouse_click_count<5) {
			MouseFront.SetFixMousePointerFlag(true);
		}
		else {
			int diff_x=MouseFront.GetMouseDiffX();
			int diff_y=MouseFront.GetMouseDiffY();
			
			h_rotation+=(-diff_x)*ROTATE_SPEED;
			v_rotation+=(-diff_y)*ROTATE_SPEED;
			if(h_rotation>Math.PI)h_rotation-=Math.PI*2.0f;
			if(h_rotation<-Math.PI)h_rotation+=Math.PI*2.0f;
			if(v_rotation<V_ANGLE_MIN)v_rotation=V_ANGLE_MIN;
			if(v_rotation>V_ANGLE_MAX)v_rotation=V_ANGLE_MAX;
		}
	}
	
	public void Update() {
		this.TranslateByUserInput();
		this.RotateByUserInput();
		
		CameraFront.SetCameraPositionAndAngle(position, v_rotation, h_rotation, 0.0f);
	}
}
