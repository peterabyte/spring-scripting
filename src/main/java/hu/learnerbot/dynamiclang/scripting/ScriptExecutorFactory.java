package hu.learnerbot.dynamiclang.scripting;

public interface ScriptExecutorFactory {
    ScriptExecutor create(ScriptType type);
}
