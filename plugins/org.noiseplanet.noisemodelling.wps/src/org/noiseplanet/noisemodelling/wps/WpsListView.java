package org.noiseplanet.noisemodelling.wps;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class WpsListView {
	
    private TreeViewer viewer;

	@PostConstruct
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        viewer.setContentProvider(new WpsTreeContentProvider());

        TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
        viewerColumn.getColumn().setWidth(300);
        viewerColumn.getColumn().setText("Wps Scripts");
        viewerColumn.setLabelProvider(new ColumnLabelProvider());

        IPreferenceStore preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.noiseplanet.noisemodelling.wps.preferences");
        String wpsFolder = preferenceStore.getString("WPS_PATH");

        // in case of changes
        preferenceStore.addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty() == "WPS_PATH") {
			        File rootFile = new File(event.getNewValue().toString());
			        viewer.setInput(rootFile);
                }
			}
		});
        File rootFile = new File(wpsFolder);
        viewer.setInput(rootFile);

        GridLayoutFactory.fillDefaults().generateLayout(parent);
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * This method is kept for E3 compatiblity. You can remove it if you do not
	 * mix E3 and E4 code. <br/>
	 * With E4 code you will set directly the selection in ESelectionService and
	 * you do not receive a ISelection
	 * 
	 * @param s
	 *            the selection received from JFace (E3 mode)
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection s) {
		if (s==null || s.isEmpty())
			return;

		if (s instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) s;
			if (iss.size() == 1)
				setSelection(iss.getFirstElement());
			else
				setSelection(iss.toArray());
		}
	}

	/**
	 * This method manages the selection of your current object. In this example
	 * we listen to a single Object (even the ISelection already captured in E3
	 * mode). <br/>
	 * You should change the parameter type of your received Object to manage
	 * your specific selection
	 * 
	 * @param o
	 *            : the current object received
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) {

		// Remove the 2 following lines in pure E4 mode, keep them in mixed mode
		if (o instanceof ISelection) // Already captured
			return;

	}

	/**
	 * This method manages the multiple selection of your current objects. <br/>
	 * You should change the parameter type of your array of Objects to manage
	 * your specific selection
	 * 
	 * @param o
	 *            : the current array of objects received in case of multiple selection
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selectedObjects) {

	}
}
