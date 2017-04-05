```java
public static Config loadFromAppHomeDir(String appHome) {
        String appConfigDir = appHome + File.separator + "config";
        return loadFromVoldemortHome(appHome, appConfigDir);

    }

    public static VoldemortConfig loadFromVoldemortHome(String appHome,
                                                        String appConfigDir) {
        if(!Utils.isReadableDir(appHome))
            throw new ConfigurationException("Attempt to load configuration from APP_HOME, "
                                             + appHome
                                             + " failed. That is not a readable directory.");

        if(appConfigDir == null) {
            appConfigDir = appHome + File.separator + "config";
        }

        String propertiesFile = appConfigDir + File.separator + "server.properties";
        
        if(!Utils.isReadableFile(propertiesFile))
            throw new ConfigurationException(propertiesFile
                                             + " is not a readable configuration file.");

        Props properties = null;
        try {
            properties = new Props(new File(propertiesFile));
            properties.put(APP_HOME, appHome);
            properties.put(METADATA_DIRECTORY, appConfigDir);
        } catch(IOException e) {
            throw new ConfigurationException(e);
        }

        return new VoldemortConfig(properties);
    }
```