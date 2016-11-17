package com.example.plugins.keyvaluesurrounded;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

public class KeyValueSurroundedFunctionMetaData implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES = "com.example.plugins.graylog-plugin-function-strlen/graylog-plugin.properties";

    @Override
    public String getUniqueId() {
        return "com.example.plugins.keyvaluesurrounded.KeyValueSurroundedFunctionPlugin";
    }

    @Override
    public String getName() {
        return "Key value surrounded pipeline function";
    }

    @Override
    public String getAuthor() {
        return "Alexander Campos <alexander.campos@mercadolibre.com>";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/alcampos/graylog-plugin-function-key_value_surrounded");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "version", Version.from(0, 0, 1, "unknown"));
    }

    @Override
    public String getDescription() {
        return "Pipeline function that returns key value map delimited by surrounded characters";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "graylog.version", Version.from(2, 1, 1));
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
