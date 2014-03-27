package ua.khvorov.temperatureconverter.logic;

import java.math.BigDecimal;

public class Calculation {

    public static BigDecimal convertTemperature(TemperatureType type, Double temperature) {
        switch (type) {
            case CELSIUS:
                return BigDecimal.valueOf((temperature - 32) * 5 / 9);
            case FAHRENHEIT:
                return BigDecimal.valueOf(temperature * 9 / 5 + 32);
            default:
                throw new IllegalArgumentException();
        }
    }
}
