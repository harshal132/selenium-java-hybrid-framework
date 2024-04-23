package common.constants;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Browser {
    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge"), SAFARI("safari"), ANDROID_CHROME("android_chrome"),
    IPHONE_CHROME("iphone_chrome");
    private static final Map<String, Browser> BROWSER_MAP;
    private String name;

    static {
        BROWSER_MAP = Stream.of(Browser.values())
                .collect(Collectors.toMap(browser -> browser.name, Function.identity()));
    }

    Browser(String name) {
        this.name = name;
    }

    public static Browser get(String browser) {
        if (!StringUtils.isEmpty(browser) && BROWSER_MAP.containsKey(browser.toLowerCase()))
            return BROWSER_MAP.get(browser.toLowerCase());
        throw new RuntimeException("Invalid browser name - " + browser);
    }

    public boolean isSafari() {
        return SAFARI.equals(this);
    }

    public boolean isChrome() {
        return CHROME.equals(this);
    }

    public boolean isFirefox() {
        return FIREFOX.equals(this);
    }

    public boolean isEdge() {
        return EDGE.equals(this);
    }

    public boolean isAndroidChrome() {
        return ANDROID_CHROME.equals(this);
    }

    public boolean isIphoneChrome() {
        return IPHONE_CHROME.equals(this);
    }
}
