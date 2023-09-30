package com.becafe.utils;

import java.util.Locale;


public final class ProjectConstants {



	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final Locale MAROC_LOCALE = new Locale.Builder().setLanguage("fr").setRegion("FR").build();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
