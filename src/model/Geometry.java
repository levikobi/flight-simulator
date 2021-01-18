package model;

public final class Geometry {

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in km
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    /**
     * Calculate bearing between two points in latitude and longitude.
     * Bearing: The angle between north-south line of earth and the line connecting
     * the target and the reference point.
     *
     * lat1, lon1 Start point lat2, lon2 End point.
     * @returns Bearing in degrees
     */
    public static double bearing(double lat1, double lon1, double lat2, double lon2) {
        double dl = Math.abs(lon2 - lon1);
        double x = Math.cos(Math.toRadians(lat2)) * Math.sin(Math.toRadians(dl));
        double y = Math.cos(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                - Math.sin(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(dl));
        return Math.toDegrees(Math.atan2(x, y));
    }
}
