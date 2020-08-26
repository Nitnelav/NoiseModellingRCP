package org.noiseplanet.noisemodelling.wps;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class WpsPreferencesInit extends AbstractPreferenceInitializer {

	public WpsPreferencesInit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
        ScopedPreferenceStore scopedPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.noiseplanet.noisemodelling.wps.preferences");
        scopedPreferenceStore.setDefault("WPS_PATH", "/home/valoo/Projects/IFSTTAR/NoiseModelling/wps_scripts/src/main/groovy/org/noise_planet/noisemodelling/wps");
	}

}
