package com.daxie.testspace.joglf.fundamental;

import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;

//JOGLFramework version:1.0.0

//Create a window.
//This is the shortest snippet for JOGLFramework.

public class CreateWindow extends MainBase{
	public static void main(String[] args) {
		new CreateWindow(GLVersion.GL4);
	}
	
	public CreateWindow(GLVersion gl_version) {
		super(gl_version);
	}
}
