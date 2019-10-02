package com.daxie.testspace.joglf.v1.draw;

import com.daxie.basis.coloru8.ColorU8Functions;
import com.daxie.joglf.gl.text.TextMgr;
import com.daxie.joglf.gl.wrapper.GLVersion;
import com.daxie.joglf.main.MainBase;
import com.jogamp.opengl.GLAutoDrawable;

//JOGLFramework version:1.0.0

public class DrawText extends MainBase{
	public static void main(String[] args) {
		new DrawText(GLVersion.GL4);
	}
	public DrawText(GLVersion gl_version) {
		super(gl_version);
	}
	
	private int font_handle;
	
	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		//Load a font (メイリオ) for Japanese characters.
		//I'm using Windows 10. Change the filename if you use other systems.
		font_handle=TextMgr.LoadFont("C:/Windows/Fonts/meiryo.ttc");
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		
		//Draw with a default font.
		TextMgr.DrawText(5, 5, "Default Font", ColorU8Functions.GetColorU8(1.0f, 0.0f, 0.0f, 1.0f), 64, 32);
		//Draw with a user font.
		TextMgr.DrawTextWithFont(5, 100, "メイリオ", font_handle, 
				ColorU8Functions.GetColorU8(0.0f, 1.0f, 0.0f, 1.0f), 64, 32);
	}
}
