class Thermostat {

    private String location;
    private int temp;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String loc, int sTemp) {
        location = loc;

        if (sTemp >= MIN && sTemp <= MAX) {
            temp = sTemp;
        }
        else {
            temp = 22;
        }

        activeCount++;
    }

    public Thermostat(String loc) {
        this(loc, 22);
    }

    public void raise() {
        if (temp < MAX) {
            temp++;
        }
        else {
            System.out.println("Already at Maximum (30)");
        }
    }

    public void lower() {
        if (temp > MIN) {
            temp--;
        }
        else {
            System.out.println("Already at Minimum (16)");
        }
    }

    public int getTemperature() {
        return temp;
    }

    public static int getActiveCount() {
        return activeCount;
    }
}

class Main {
    public static void main(String[] args) {

        Thermostat th1 = new Thermostat("Lab 631", 24);
        Thermostat th2 = new Thermostat("Lab 632");

        for (int i = 0; i < 10; i++) {
            th1.raise();
            System.out.println("Temperature: " + th1.getTemperature());
        }

        for (int i = 0; i < 20; i++) {
            th1.lower();
            System.out.println("Temperature: " + th1.getTemperature());
        }

        System.out.println("Active Count: " + Thermostat.getActiveCount());
    }
}