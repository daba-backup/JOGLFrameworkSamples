package com.daxie.testspace.joglf.fundamental;

import com.daxie.joglf.gl.front.KeyboardFront;
import com.daxie.joglf.gl.front.WindowFront;
import com.daxie.joglf.gl.input.keyboard.KeyboardEnum;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

//Add event handlers to the previous snippet (CreateWindow.java).

public class EventHandlers extends MainBase{
	public static void main(String[] args) {
		new EventHandlers(GLVersion.GL4);
	}
	public EventHandlers(GLVersion version) {
		super(version);
	}
	
	//init() is called when the OpenGL context is created.
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		System.out.println("init");
	}
	//reshape() is called when the main window is resized.
	@Override
	public void reshape(GLAutoDrawable drawable,int x,int y,int width,int height) {
		super.reshape(drawable, x, y, width, height);
		
		System.out.println("reshape");
	}
	//display() is called every frame.
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		//Close the window if ESC is pressed.
		if(KeyboardFront.GetKeyboardPressingCount(KeyboardEnum.KEY_ESCAPE)==1) {
			WindowFront.CloseWindow();
		}
	}
	//dispose() is called when the OpenGL context is destroyed.
	@Override
	public void dispose(GLAutoDrawable drawable) {
		super.dispose(drawable);
		
		System.out.println("dispose");
	}
}
