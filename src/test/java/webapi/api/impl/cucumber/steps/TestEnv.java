package webapi.api.impl.cucumber.steps;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.ToString;

public class TestEnv {
    private static final ThreadLocal<TestData> data = new ThreadLocal<>();
    private static final String LAST_EXCEPTION_KEY = TestData.key(TestEnv.class, "lastThrowable");

    private TestEnv() {
    
    }

    public static void reset() {
        data.set(new TestData());
    }

    public static TestData getTestData() {
        return data.get();
    }

    public static void setLastException(final Throwable t) {
        getTestData().set(LAST_EXCEPTION_KEY, t);
    }

    public static Throwable getLastException() {
        return getTestData().get(LAST_EXCEPTION_KEY);
    }

    @ToString
    public static final class TestData {
        private final Map<String, Object> data = new HashMap<>();

        public static String key(final Class<?> klass, final String keyName) {
        return klass.getSimpleName() + "." + keyName;
        }

        @SuppressWarnings("unchecked")
        public <T> T get(final String key) {
        return (T) data.get(key);
        }

        public <T> void set(final String key, final T value) {
        data.put(key, value);
        }
    }
}
