package model.interpreter.server;

import java.util.LinkedHashMap;
import java.util.Map;

public class VariablesManager {
    public static final Map<String, VariableBinder> map = new LinkedHashMap<String, VariableBinder>(){{
        put("airplane_lon", new VariableBinder(0, "/sim/current-view/viewer-lon-deg"));
        put("airplane_lat", new VariableBinder(0, "/sim/current-view/viewer-lat-deg"));

        put("\"/instrumentation/airspeed-indicator/indicated-speed-kt\"", new VariableBinder(0, "/instrumentation/airspeed-indicator/indicated-speed-kt"));
        put("\"/instrumentation/altimeter/indicated-altitude-ft\"", new VariableBinder(0, "/instrumentation/altimeter/indicated-altitude-ft"));
        put("\"/instrumentation/altimeter/pressure-alt-ft\"", new VariableBinder(0, "/instrumentation/altimeter/pressure-alt-ft"));
        put("\"/instrumentation/attitude-indicator/indicated-pitch-deg\"", new VariableBinder(0, "/instrumentation/attitude-indicator/indicated-pitch-deg"));
        put("\"/instrumentation/attitude-indicator/indicated-roll-deg\"", new VariableBinder(0, "/instrumentation/attitude-indicator/indicated-roll-deg"));
        put("\"/instrumentation/attitude-indicator/internal-pitch-deg\"", new VariableBinder(0, "/instrumentation/attitude-indicator/internal-pitch-deg"));
        put("\"/instrumentation/attitude-indicator/internal-roll-deg\"", new VariableBinder(0, "/instrumentation/attitude-indicator/internal-roll-deg"));
        put("\"/instrumentation/encoder/indicated-altitude-ft\"", new VariableBinder(0, "/instrumentation/encoder/indicated-altitude-ft"));
        put("\"/instrumentation/encoder/pressure-alt-ft\"", new VariableBinder(0, "/instrumentation/encoder/pressure-alt-ft"));
        put("\"/instrumentation/gps/indicated-altitude-ft\"", new VariableBinder(0, "/instrumentation/gps/indicated-altitude-ft"));
        put("\"/instrumentation/gps/indicated-ground-speed-kt\"", new VariableBinder(0, "/instrumentation/gps/indicated-ground-speed-kt"));
        put("\"/instrumentation/gps/indicated-vertical-speed\"", new VariableBinder(0, "/instrumentation/gps/indicated-vertical-speed"));
        put("\"/instrumentation/heading-indicator/indicated-heading-deg\"", new VariableBinder(0, "/instrumentation/heading-indicator/indicated-heading-deg"));
        put("\"/instrumentation/magnetic-compass/indicated-heading-deg\"", new VariableBinder(0, "/instrumentation/magnetic-compass/indicated-heading-deg"));
        put("\"/instrumentation/slip-skid-ball/indicated-slip-skid\"", new VariableBinder(0, "/instrumentation/slip-skid-ball/indicated-slip-skid"));
        put("\"/instrumentation/turn-indicator/indicated-turn-rate\"", new VariableBinder(0, "/instrumentation/turn-indicator/indicated-turn-rate"));
        put("\"/instrumentation/vertical-speed-indicator/indicated-speed-fpm\"", new VariableBinder(0, "/instrumentation/vertical-speed-indicator/indicated-speed-fpm"));
        put("\"/controls/flight/aileron\"", new VariableBinder(0, "/controls/flight/aileron"));
        put("\"/controls/flight/elevator\"", new VariableBinder(0, "/controls/flight/elevator"));
        put("\"/controls/flight/rudder\"", new VariableBinder(0, "/controls/flight/rudder"));
        put("\"/controls/flight/flaps\"", new VariableBinder(0, "/controls/flight/flaps"));
        put("\"/controls/engines/engine/throttle\"", new VariableBinder(0, "/controls/engines/engine/throttle"));
        put("\"/engines/engine/rpm\"", new VariableBinder(0, "/engines/engine/rpm"));


        put("\"/controls/flight/speedbrake\"", new VariableBinder(0, "/controls/flight/speedbrake"));
        put("\"/controls/engines/current-engine/throttle\"", new VariableBinder(0, "/controls/engines/current-engine/throttle"));
        put("\"/instrumentation/heading-indicator/offset-deg\"", new VariableBinder(0, "/instrumentation/heading-indicator/offset-deg"));
    }};

    public static void updateVariables(String commaSeparatedValues) {
        String[] values = commaSeparatedValues.split(",");
        int i = 0;
        for (Map.Entry<String, VariableBinder> entry : map.entrySet()) {
            if (i >= values.length) break;
            entry.getValue().value = Double.parseDouble(values[i++]);
        }
    }
}
