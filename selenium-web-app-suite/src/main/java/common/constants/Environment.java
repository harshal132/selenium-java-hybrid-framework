package common.constants;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Environment {
    STAGING("staging"), QA("qa"), PROD("prod");

    private static final Map<String, Environment> ENV_MAP;

    private String name;

    public static String getKey(Environment env) {
        return ENV_MAP.keySet().stream().filter(key -> env.equals(ENV_MAP.get(key))).findFirst().get();
    }

    static {
        ENV_MAP = Stream.of(Environment.values()).collect(Collectors.toMap(env -> env.name, Function.identity()));
    }

    Environment(String name) {
        this.name = name;
    }

    public static Environment get(String envType) {

        if (!StringUtils.isEmpty(envType) && ENV_MAP.containsKey(envType.toLowerCase()))
            return ENV_MAP.get(envType.toLowerCase());
        throw new RuntimeException("environment cannot be null or Invalid environment name - " + envType);
    }

    public boolean isQaEnv() {
        return (QA.equals(this));
    }

    public boolean isProdEnv() {
        return (PROD.equals(this));
    }

    public boolean isStageEnv() {
        return (STAGING.equals(this));
    }
}
