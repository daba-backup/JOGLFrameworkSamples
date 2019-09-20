package com.daxie.testspace.joglf.fundamental;

import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;

/**
 * Creates a window.<br>
 * This is the smallest snippet for JOGLFramework.
 * @author Daba
 *
 */
public class CreateWindow extends MainBase{
	public static void main(String[] args) {
		new CreateWindow(GLVersion.GL4);
	}
	
	public CreateWindow(GLVersion gl_version) {
		super(gl_version);
	}
}
