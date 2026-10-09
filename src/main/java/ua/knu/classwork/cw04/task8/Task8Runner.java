package ua.knu.classwork.cw04.task8;

public class Task8Runner {
    public static void main(String[] args) {
        System.out.println("~ Connection Manager Demo ~");

        for (int i = 1; i <= 5; i++) {
            Connection conn = ConnectionManager.getConnection();
            if (conn != null) {
                System.out.printf("Request %d: Acquired %s (Available left: %d)%n",
                        i, conn, ConnectionManager.getAvailableCount());
            } else {
                System.out.printf("Request %d: Connection pool exhausted! Returned null.%n", i);
            }
        }
    }
}
