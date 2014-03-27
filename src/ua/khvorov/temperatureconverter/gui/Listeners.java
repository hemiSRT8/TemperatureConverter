package ua.khvorov.temperatureconverter.gui;

import ua.khvorov.temperatureconverter.logic.Calculation;
import ua.khvorov.temperatureconverter.logic.TemperatureType;
import ua.khvorov.temperatureconverter.validation.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import static java.lang.Double.parseDouble;

public class Listeners {

    public static MouseAdapter celsiusMouseListener(final JTextField fahrenheitTextField) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fahrenheitTextField.setText("");
            }
        };
    }

    public static MouseAdapter fahrenheitMouseListener(final JTextField celsiusTextField) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                celsiusTextField.setText("");
            }
        };
    }

    public static ActionListener convertButtonListener(final JTextField celsiuses,
                                                       final JTextField fahrenheits,
                                                       final JLabel programMessage) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String CELSIUSES_INPUT = celsiuses.getText();
                final String FAHRENHEITS_INPUT = fahrenheits.getText();

                if (Validator.inputValidation(celsiuses, fahrenheits, programMessage)) {
                    if (!CELSIUSES_INPUT.isEmpty()) {
                        BigDecimal resultBigDecimal = Calculation.convertTemperature(TemperatureType.FAHRENHEIT, parseDouble(CELSIUSES_INPUT));
                        String resultString = String.valueOf(resultBigDecimal);
                        fahrenheits.setText(resultString);
                    } else if (!FAHRENHEITS_INPUT.isEmpty()) {
                        BigDecimal resultBigDecimal = Calculation.convertTemperature(TemperatureType.CELSIUS, parseDouble(FAHRENHEITS_INPUT));
                        String resultString = String.valueOf(resultBigDecimal);
                        celsiuses.setText(resultString);
                    }

                    programMessage.setForeground(FontsAndColors.GREEN_COLOR); //If input is valid
                    programMessage.setText("Converted");

                } else {
                    programMessage.setForeground(Color.RED); //If input is not valid
                }
            }
        };
    }
}
