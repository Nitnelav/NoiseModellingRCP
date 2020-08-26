package org.noiseplanet.noisemodelling.wps;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class WpsPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public WpsPreferences() {
        super(GRID);
    }
	
	@Override
	public void init(IWorkbench workbench) {
        // second parameter is typically the plug-in id
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.noiseplanet.noisemodelling.wps.preferences"));
        setDescription("The Wps folder");
	}

	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor("WPS_PATH", "Wps &Directory :", getFieldEditorParent()));
	}


}
