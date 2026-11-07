

class TollBooth {

    record Vehicle(String number, String type) {};

    public static void main(String[] args) {

        final int MAX_VEHICLES = 100;
        final int BIKE_TOLL = 20;
        final int CAR_TOLL = 50;
        final int TRUCK_TOLL = 150;

        int totalToll = 0, bikeCount = 0, carCount = 0, truckCount = 0, objCount = 0;

        String status = "";

        java.util.Scanner sc = new java.util.Scanner(System.in);

        Vehicle[] v = new Vehicle[MAX_VEHICLES];

        do {
            System.out.println("Enter the number of vehicles: ");
            String vNumber = sc.next();

            System.out.println("Enter the vehicle type: ");
            String vType = sc.next();

            v[objCount++] = new Vehicle(vNumber, vType);

            int thisVehicleCount = switch(v[objCount - 1].type().toLowerCase()) {

                case "bike" -> {
                    bikeCount += Integer.parseInt(v[objCount - 1].number());
                    totalToll += Integer.parseInt(v[objCount - 1].number()) * BIKE_TOLL;
                    yield bikeCount;
                }

                case "car" -> {
                    carCount += Integer.parseInt(v[objCount - 1].number());
                    totalToll += Integer.parseInt(v[objCount - 1].number()) * CAR_TOLL;
                    yield carCount;
                }

                case "truck" -> {
                    truckCount += Integer.parseInt(v[objCount - 1].number());
                    totalToll += Integer.parseInt(v[objCount - 1].number()) * TRUCK_TOLL;
                    yield truckCount;
                }

                default -> {
                    yield -1;
                }
            };

            System.out.println("next vehicle status (done -> exit): ");
            status = sc.next();

        } while (!(status.toLowerCase().equals("done")) && objCount != MAX_VEHICLES);

        System.out.println("Total Toll: " + totalToll + " Most frequent: " +
            ((bikeCount > carCount) ?
            ((bikeCount > truckCount) ? ("bike") : ("truck")) :
            ((carCount > truckCount) ? ("car") : ("truck"))));

        sc.close();
    }
}