```java
public static VoldemortConfig loadFromVoldemortHome(String voldemortHome) {
        String voldemortConfigDir = voldemortHome + File.separator + "config";
        return loadFromVoldemortHome(voldemortHome, voldemortConfigDir);

    }

    public static VoldemortConfig loadFromVoldemortHome(String voldemortHome,
                                                        String voldemortConfigDir) {
        if(!Utils.isReadableDir(voldemortHome))
            throw new ConfigurationException("Attempt to load configuration from VOLDEMORT_HOME, "
                                             + voldemortHome
                                             + " failed. That is not a readable directory.");

        if(voldemortConfigDir == null) {
            voldemortConfigDir = voldemortHome + File.separator + "config";
        }
        String propertiesFile = voldemortConfigDir + File.separator + "server.properties";
        if(!Utils.isReadableFile(propertiesFile))
            throw new ConfigurationException(propertiesFile
                                             + " is not a readable configuration file.");

        Props properties = null;
        try {
            properties = new Props(new File(propertiesFile));
            properties.put(VOLDEMORT_HOME, voldemortHome);
            properties.put(METADATA_DIRECTORY, voldemortConfigDir);
        } catch(IOException e) {
            throw new ConfigurationException(e);
        }

        return new VoldemortConfig(properties);
    }
```