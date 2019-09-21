package com.daxie.testspace.joglf.practical.objviewer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.daxie.joglf.gl.model.Model3D;
import com.daxie.tool.FileFunctions;

public class ModelMgr {
	private List<Integer> model_handles;
	
	public ModelMgr() {
		List<String> lines=null;
		try {
			lines=FileFunctions.GetFileAllLines("./filelist.txt", "UTF-8");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		model_handles=new ArrayList<>();
		for(String filename:lines) {
			int model_handle=Model3D.LoadModel(filename);
			if(model_handle<0)continue;//Error
			
			model_handles.add(model_handle);
		}
	}
	
	public void Draw() {
		for(int model_handle:model_handles) {
			Model3D.DrawModel(model_handle);
		}
	}
}
