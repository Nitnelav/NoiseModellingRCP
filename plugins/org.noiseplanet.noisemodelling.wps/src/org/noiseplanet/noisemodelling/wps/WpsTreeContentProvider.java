package org.noiseplanet.noisemodelling.wps;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class WpsTreeContentProvider implements ITreeContentProvider {

	private TreeViewer viewer;
	private WpsTreeElement root;
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof File) {
			root = new WpsTreeElement((File) inputElement);
			return root.getChildren();
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		final WpsTreeElement node = (WpsTreeElement) parentElement;
		return node.getChildren();
	}

	@Override
	public Object getParent(Object element) {
		final WpsTreeElement node = (WpsTreeElement) element;
		return node.getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		final WpsTreeElement node = (WpsTreeElement) element;
		return node.getType() == WpsTreeElement.Types.CATEGORY;
	}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput instanceof File) {
			root = new WpsTreeElement((File) newInput);
		}
		else if (newInput instanceof WpsTreeElement) {
			root = (WpsTreeElement) newInput;
		}
		if (viewer instanceof TreeViewer && viewer != null) {
			this.viewer = (TreeViewer) viewer;
		}
	}

}
