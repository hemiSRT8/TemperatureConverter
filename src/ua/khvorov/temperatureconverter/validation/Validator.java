package ua.khvorov.temperatureconverter.validation;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class Validator {
    private static final Pattern PATTERN = Pattern.compile("^-?\\d+\\.?\\d*$");

    public static boolean inputValidation(JTextField celsiuses, JTextField fahrenheits, JLabel programMessage) {

        final String CELSIUSES_INPUT = celsiuses.getText();
        final String FAHRENHEIS_INPUT = fahrenheits.getText();

        if (Validator.isEmptyInput(CELSIUSES_INPUT, FAHRENHEIS_INPUT)) {
            programMessage.setText("Empty input!");
            return false;
        } else if (Validator.isNotNumber(CELSIUSES_INPUT, FAHRENHEIS_INPUT)) {
            programMessage.setText("Not valid input!");
            return false;
        } else if (Validator.isInBoundary(CELSIUSES_INPUT, FAHRENHEIS_INPUT)) {
            incorrectTemperatureBoundaryMessage();
            return false;
        }
        return true;
    }

    private static boolean isEmptyInput(String celsiuses, String fahrenheits) {
        return celsiuses.isEmpty() && fahrenheits.isEmpty();
    }

    private static boolean isNotNumber(String celsiuses, String fahrenheits) {
        if (celsiuses.isEmpty()) {
            Matcher fahrenheitsMatcher = PATTERN.matcher(fahrenheits);
            return !fahrenheitsMatcher.matches();
        } else {
            Matcher celsiusMatcher = PATTERN.matcher(celsiuses);
            return !celsiusMatcher.matches();
        }
    }

    private static boolean isInBoundary(String celsiuses, String fahrenheits) {
        if (celsiuses.isEmpty()) {
            return parseDouble(fahrenheits) > 110 || parseDouble(fahrenheits) < -50;
        } else
            return parseDouble(celsiuses) > 230 || parseDouble(celsiuses) < -50;
    }

    private static JOptionPane incorrectTemperatureBoundaryMessage() {
        JOptionPane pane = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "  Temperature at celsiuses must be at the boundary between -50 and 110 & in fahrenheits between -50 and 230",
                "",
                JOptionPane.ERROR_MESSAGE);
        return pane;
    }
}
