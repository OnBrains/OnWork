package org.onbrains.onwork.util;

import org.omnifaces.util.Ajax;

public class OmnifacesUtils {

	private static final String HIDE_DLG_JS_PATTERN = "$('#%s').modal('hide')";

	public static void closeDlg(String dlgId) {
		Ajax.oncomplete(String.format(HIDE_DLG_JS_PATTERN, dlgId));
	}

}