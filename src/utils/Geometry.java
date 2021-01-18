package utils;

public final class Geometry {

    public static final int EARTH_RADIUS = 6371;

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point ele1 Start altitude in meters
     * ele2 End altitude in meters
     * @return Distance in km
     */
    public static double distance(double lat1, double lat2, double ele1,
                                  double lon1, double lon2, double ele2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        double height = ele1 - ele2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    /**
     * Calculate bearing between two points in latitude and longitude.
     * Bearing: The angle between north-south line of earth and the line connecting
     * the target and the reference point.
     *
     * lat1, lon1 Start point lat2, lon2 End point.
     * @return Bearing in degrees
     */
    public static double bearing(double lat1, double lon1, double lat2, double lon2) {
        double dl = Math.abs(lon2 - lon1);
        double x = Math.cos(Math.toRadians(lat2)) * Math.sin(Math.toRadians(dl));
        double y = Math.cos(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                - Math.sin(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(dl));
        return Math.toDegrees(Math.atan2(x, y));
    }
}
