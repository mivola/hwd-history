package com.voigt.hwd.client;
import com.google.gwt.i18n.client.Constants;

/**
 * reads the Version.properties (case sensitive!) file
 */
public interface Version extends Constants {

	@Key("build.date")
	String buildDate();

	@Key("build.version.major")
	String buildVersionMajor();

	@Key("build.version.minor")
	String buildVersionMinor();

	@Key("build.version.micro")
	String buildVersionMicro();

}
