package com.glenfordham.webserver.config;

public enum Parameters {

    PORT("p",true,"sets the port to listen on", "81"),
    TEMP_DIR_PREFIX("t", true, "sets Tomcat's temporary directory prefix", "tomcat-base-dir");

    private final String param;
    private final boolean isArgumentRequired;
    private final String helpMessage;
    private final String defaultValue;

    Parameters(String param, boolean isArgumentRequired, String helpMessage, String defaultValue) {
        this.param = param;
        this.isArgumentRequired = isArgumentRequired;
        this.helpMessage = helpMessage;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the name of the parameter
     *
     * @return the parameter name
     */
    public String getParam() {
        return param;
    }

    /**
     * Returns whether an argument (value) is required for the parameter
     *
     * @return true if an argument is required
     */
    public boolean isArgumentRequired() {
        return isArgumentRequired;
    }

    /**
     * Returns the help message for the given parameter, used by commons-cli
     *
     * @return help message
     */
    public String getHelpMessage() {
        return helpMessage;
    }

    /**
     * Returns the default value for the parameter
     *
     * @return default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }
}
