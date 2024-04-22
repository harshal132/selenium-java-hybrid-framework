package common.constants;


import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
public enum DriverType {
    REMOTE("remote"), LOCAL("local");

    private static final Map<String, DriverType> DRIVER_MAP;

    private String name;

    static {
        DRIVER_MAP = Stream.of(DriverType.values())
                .collect(Collectors.toMap(driver -> driver.name, Function.identity()));
    }

    DriverType(String name) {
        this.name = name;
    }

    public static DriverType get(String driverType) {
        if (!StringUtils.isEmpty(driverType) && DRIVER_MAP.containsKey(driverType.toLowerCase()))
            return DRIVER_MAP.get(driverType.toLowerCase());
        throw new RuntimeException("Driver type cannot be null or invalid - " + driverType);
    }

    public boolean isLocalDriver() {
        return (LOCAL.equals(this));
    }

    public boolean isRemoteDriver() {
        return (REMOTE.equals(this));
    }
}
