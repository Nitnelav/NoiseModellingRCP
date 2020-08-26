 
package org.noiseplanet.noisemodelling.wps;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class WpsFormView {
	
	private ScrolledForm viewer;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        
        ScrolledForm form = toolkit.createScrolledForm(parent);
        viewer = form;
        // Lets make a layout for the first section of the screen
        GridLayout layout = new GridLayout();

        layout.numColumns = 1;
        layout.marginWidth = 2;
        layout.marginHeight = 2;
        // Creating the Screen
        Section section = toolkit.createSection(parent, Section.DESCRIPTION
                | Section.TITLE_BAR);
        section.setText("Parameters");
        section.setDescription("Declared Wps Parameters");
	}
	
    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        viewer.setFocus();
    }
	
	
	
	
}