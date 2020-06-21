package com.peterabyte.springscripting.core;

import org.springframework.stereotype.Component;

@Component
public class DefaultScriptExecutorFactory implements ScriptExecutorFactory {
    @Override
    public ScriptExecutor create(ScriptType type) {
        switch (type) {
            case GROOVY:
                return new GroovyScriptExecutor();
            default:
                throw new UnsupportedOperationException(String.format("Failed to create script executor for type '%s'!", type));
        }
    }
}
