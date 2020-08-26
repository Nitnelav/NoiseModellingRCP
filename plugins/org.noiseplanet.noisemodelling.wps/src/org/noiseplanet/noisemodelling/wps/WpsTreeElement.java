package org.noiseplanet.noisemodelling.wps;

import java.io.File;

public class WpsTreeElement {

	static enum Types {
		SCRIPT,
		CATEGORY,
		INVALID
	}
	
	private Types type = Types.INVALID;
	private File file;
	private String name;
	private WpsTreeElement parent = null;
	private WpsTreeElement[] children;

	public WpsTreeElement(File file) {
		this.file = file;
		if (file.isDirectory()) {
			name = file.getName();
			this.type = Types.CATEGORY;
			File[] files = file.listFiles();
			children = new WpsTreeElement[files.length];
			for (int i = 0; i < files.length; i++) {
				children[i] = new WpsTreeElement(files[i], this);
			}
		}
		else if (file.getName().endsWith(".groovy")) {
            name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			this.type = Types.SCRIPT;
		}
	}
	public WpsTreeElement(File file, WpsTreeElement parent) {
		this(file);
		this.parent = parent;
	}
	
	public WpsTreeElement[] getChildren() {
		return children;
	}
	public WpsTreeElement getParent() {
		return parent;
	}
	public Types getType() {
		return type;
	}
	public File getFile() {
		return file;
	}
	public String toString() {
		return name;
	}
}
