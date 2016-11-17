package com.example.plugins.keyvaluesurrounded;

import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.PluginModule;

import java.util.Collection;
import java.util.Collections;

public class KeyValueSurroundedFunctionPlugin implements Plugin {
    @Override
    public PluginMetaData metadata() {
        return new KeyValueSurroundedFunctionMetaData();
    }

    @Override
    public Collection<PluginModule> modules () {
        return Collections.<PluginModule>singletonList(new KeyValueSurroundedFunctionModule());
    }
}
