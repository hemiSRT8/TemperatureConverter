package ua.khvorov.temperatureconverter.gui;

import javax.swing.*;
import java.awt.*;

public class GraphicInterface {

    private JTextField celsiuses;
    private JTextField fahrenheits;
    private JLabel programMessage;

    public GraphicInterface() {
        constructFrame(constructMainPanel());
    }

    private void constructFrame(JPanel mainPanel) {
        JFrame frame = new JFrame("Temperature converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setIconImage(new ImageIcon("images\\icon.png").getImage());
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(500, 200);
        frame.setLocation(screenSize.width / 2 - (frame.getWidth() / 2), //Center of the screen
                screenSize.height / 2 - (frame.getHeight() / 2));
    }

    private JPanel constructMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        mainPanel.add(createTitlePanel());
        mainPanel.add(constructFieldsPanel());
        mainPanel.add(constructDialogWindowPanel());
        mainPanel.add(constructConvertButtonPanel());

        return mainPanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(FontsAndColors.AZURE2_COLOR);
        titlePanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Temperature converter (fahrenheits <-> celsiuses)");
        label.setFont(FontsAndColors.COMIC_SANS_FONT);
        label.setForeground(Color.BLACK);

        titlePanel.add(label);
        return titlePanel;
    }

    private JPanel constructFieldsPanel() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setBackground(FontsAndColors.AZURE2_COLOR);
        fieldsPanel.setLayout(new GridLayout(1, 4));

        fieldsPanel.add(constructCelsiusSymbol());
        fieldsPanel.add(constructCelsiusTextField());
        fieldsPanel.add(constructFahrenheitTextField());
        fieldsPanel.add(constructFahrenheitSymbol());
        setMouseListenersForTextFields();

        return fieldsPanel;
    }

    private JLabel constructCelsiusSymbol() {
        JLabel celsiusSymbol = new JLabel("°C");
        celsiusSymbol.setFont(FontsAndColors.SYMBOL_FONT);
        celsiusSymbol.setHorizontalAlignment(JLabel.CENTER);
        celsiusSymbol.setForeground(Color.BLACK);

        return celsiusSymbol;
    }

    private JTextField constructCelsiusTextField() {
        celsiuses = new JTextField();
        celsiuses.setFont(FontsAndColors.TEXT_FIELD_FONT);
        celsiuses.setBackground(FontsAndColors.AZURE_COLOR);

        return celsiuses;
    }

    private JTextField constructFahrenheitTextField() {
        fahrenheits = new JTextField();
        fahrenheits.setFont(FontsAndColors.TEXT_FIELD_FONT);
        fahrenheits.setBackground(FontsAndColors.AZURE_COLOR);

        return fahrenheits;
    }

    private void setMouseListenersForTextFields() {
        celsiuses.addMouseListener(Listeners.celsiusMouseListener(fahrenheits));
        fahrenheits.addMouseListener(Listeners.fahrenheitMouseListener(celsiuses));
    }

    private JLabel constructFahrenheitSymbol() {
        JLabel fahrenheitSymbol = new JLabel("°F");
        fahrenheitSymbol.setFont(FontsAndColors.SYMBOL_FONT);
        fahrenheitSymbol.setHorizontalAlignment(JLabel.CENTER);
        fahrenheitSymbol.setForeground(Color.BLACK);

        return fahrenheitSymbol;
    }

    private JPanel constructDialogWindowPanel() {
        JPanel dialogWindowPanel = new JPanel();
        dialogWindowPanel.setBackground(FontsAndColors.AZURE2_COLOR);
        dialogWindowPanel.add(constructDialogWindowLabel());

        return dialogWindowPanel;
    }

    private JLabel constructDialogWindowLabel() {
        programMessage = new JLabel();
        programMessage.setBackground(FontsAndColors.AZURE_COLOR);
        programMessage.setBorder(BorderFactory.createEmptyBorder());
        programMessage.setHorizontalAlignment(JTextField.CENTER);
        programMessage.setForeground(Color.RED);
        programMessage.setFont(FontsAndColors.COMIC_SANS_FONT);

        return programMessage;
    }

    private JPanel constructConvertButtonPanel() {
        JPanel convertButtonPanel = new JPanel();
        convertButtonPanel.setBackground(FontsAndColors.AZURE2_COLOR);
        convertButtonPanel.add(constructConvertButton());

        return convertButtonPanel;
    }

    private JButton constructConvertButton() {
        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(FontsAndColors.GREEN_COLOR);
        convertButton.setFont(FontsAndColors.COMIC_SANS_FONT);
        convertButton.setForeground(Color.BLACK);
        convertButton.addActionListener(Listeners.convertButtonListener(celsiuses, fahrenheits, programMessage));

        return convertButton;
    }
}