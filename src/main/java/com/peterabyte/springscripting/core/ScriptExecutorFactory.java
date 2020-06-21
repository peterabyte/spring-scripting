package com.peterabyte.springscripting.core;

public interface ScriptExecutorFactory {
    ScriptExecutor create(ScriptType type);
}
