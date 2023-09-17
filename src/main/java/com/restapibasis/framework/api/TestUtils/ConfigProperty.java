package com.restapibasis.framework.api.TestUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;;

@Sources({
	"file:src/test/resources/propertyFiles/config.Properties"
})

public interface ConfigProperty extends Config{
	
	@Key("baseURI")
    String getBaseURI();
	
	@Key("basePath")
    String getBasePath();
	
	@Key("secretKey")
    String getSecretKey();

	@Key("testReportPath")
	String getTestFilePath();

	@Key("testReportName")
	String getTestReportName();
	
	
	
}
