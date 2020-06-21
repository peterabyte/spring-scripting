package com.peterabyte.springscripting.core;

import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.groovy.GroovyScriptFactory;

public class GroovyScriptExecutor implements ScriptExecutor {
    @Override
    public Object execute(String sourceCode) {
        try {
            ScriptSource scriptSource = new ScriptSource()
            {
                @Override
                public String getScriptAsString()
                {
                    return sourceCode;
                }

                @Override
                public boolean isModified()
                {
                    return true;
                }

                @Override
                public String suggestedClassName()
                {
                    return "GroovyMessenger";
                }
            };
            Object result = new GroovyScriptFactory("classpath").getScriptedObject(scriptSource, Messenger.class);
            return result != null ? ((Messenger) result).getMessage() : null;
        } catch (Exception ex) {
            throw new ScriptExecutionException(String.format("Failed to execute groovy script '%s'! %s", sourceCode, ex.getMessage()), ex);
        }
    }
}
