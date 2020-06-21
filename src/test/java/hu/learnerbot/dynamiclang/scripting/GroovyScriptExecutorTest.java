package hu.learnerbot.dynamiclang.scripting;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroovyScriptExecutorTest {
    private static final String DEFAULT_SOURCE_CODE = "package hu.learnerbot.dynamiclang.scripting; " +
            "import hu.learnerbot.dynamiclang.scripting.Messenger; " +
            "class GroovyMessenger implements Messenger { " +
            "public String getMessage() { return \"hello\"; } " +
            "}";
    private static final String INVALID_SOURCE_CODE = "this is an invalid source code";

    private ScriptExecutor scriptExecutor;

    @Before
    public void setUp() {
        scriptExecutor = new GroovyScriptExecutor();
    }

    @Test
    public void script_executor_should_return_result_when_source_code_is_valid() {
        Object result = scriptExecutor.execute(DEFAULT_SOURCE_CODE);
        assertThat(result).isEqualTo("hello");
    }

    @Test(expected = ScriptExecutionException.class)
    public void script_executor_should_throw_exception_when_source_code_is_invalid() {
        scriptExecutor.execute(INVALID_SOURCE_CODE);
    }
}
