package com.glenfordham.webserver.config;

import com.glenfordham.webserver.Application;
import com.glenfordham.webserver.Log;
import org.apache.commons.cli.*;

public class CliParser {

    private static CliParser instance = null;
    private boolean loaded = false;

    /**
     * Parses command line options and loads into the static ConfigProperties instance
     *
     * @param args application arguments
     * @throws ParseException thrown if an error occurs when parsing command line options
     */
    public synchronized void loadConfig(String[] args) throws ParseException {
        if (!loaded) {
            Options options = new Options();
            for (Parameters param : Parameters.values()) {
                options.addOption(param.getParam(), param.isArgumentRequired(), param.getHelpMessage());
            }

            ConfigProperties configProperties = ConfigProperties.getInstance();
            CommandLine cmd = null;
            CommandLineParser parser = new DefaultParser();

            try {
                cmd = parser.parse(options, args);
            } catch (UnrecognizedOptionException uoe) {
                Application.exit(uoe.getMessage(), uoe);
            }

            // Load command-line arguments into ConfigProperties, set values where provided
            if (cmd != null) {
                for (Parameters param : Parameters.values()) {
                    if (cmd.hasOption(param.getParam())) {
                        if (param.isArgumentRequired()) {
                            configProperties.addProperty(param, cmd.getOptionValue(param.getParam()));
                        } else {
                            configProperties.addProperty(param, null);
                        }
                    }
                }
                loaded = true;
            } else {
                // This should never happen :)
                Application.exit("Command-line arguments unable to initialise.");
            }
        } else {
            Log.error("Unable to load CLI arguments, already loaded.");
        }
    }

    /**
     * Singleton pattern
     * Creates an instance of ConfigProperties if one does not exist
     *
     * @return the singleton instance of ConfigProperties
     */
    public static synchronized CliParser getInstance() {
        if (instance == null) {
            instance = new CliParser();
        }
        return instance;
    }

    private CliParser() {
    }
}
