package common.constants;

public enum WaitTime {
    LOW(2000),
    MEDIUM(5000),
    HIGH(8000);

    private final int waitValue;
    private WaitTime(int value) {
        this.waitValue = value;
    }
    public int getTimeInMillis() {
        return waitValue;
    }

    public int getTimeInSeconds() {
        return waitValue/1000;
    }
}
