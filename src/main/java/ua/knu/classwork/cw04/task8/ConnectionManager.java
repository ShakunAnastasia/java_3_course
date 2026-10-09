package ua.knu.classwork.cw04.task8;

public class ConnectionManager {
    private static final int POOL_SIZE = 3;
    private static final Connection[] POOL = new Connection[POOL_SIZE];
    private static int currentIndex = 0;

    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            POOL[i] = new Connection(i + 1);
        }
    }

    private ConnectionManager() {}

    public static Connection getConnection() {
        if (currentIndex < POOL_SIZE) {
            return POOL[currentIndex++];
        }
        return null;
    }

    public static int getAvailableCount() {
        return Math.max(0, POOL_SIZE - currentIndex);
    }
}