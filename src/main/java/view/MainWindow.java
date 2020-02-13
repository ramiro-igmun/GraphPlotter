package view;

import domain.Equation;
import domain.ParametricFunction;

import javax.swing.*;
import java.awt.*;

public class MainWindow implements Runnable{

  private JFrame window;
  private GraphicPanel graphicPanel;
  private JPanel menu;
  private JTextField scaleField;
  private JTextField minTField;
  private JTextField maxTField;
  private JTextField step;
  private JTextField xField;
  private JTextField yField;
  private JButton runButton;

  public MainWindow(GraphicPanel graphicPanel) {
    this.graphicPanel = graphicPanel;
  }

  @Override
  public void run() {

    window = new JFrame("Graph Plotter");
    window.setPreferredSize(new Dimension(1000, 1000));
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    initComponents(window.getContentPane());

    window.pack();
    window.setVisible(true);
  }

  private void initComponents(Container contentPane) {

    menu = new JPanel();

    scaleField = createTextField("3",3);
    addToMenu("Scale",scaleField);

    minTField = createTextField("0", 3);
    addToMenu("min(t)",minTField);

    maxTField = createTextField("10", 3);
    addToMenu("min(t)",maxTField);

    step = createTextField("0.001",7);
    addToMenu("Step",step);

    xField = createTextField("cos(t)-cos(80*t)^3", 10);
    addToMenu("x(t)",xField);

    yField = createTextField("sin(t)-sin(80*t)^3", 10);
    addToMenu("y(t)",yField);

    runButton = new JButton("Plot");
    runButton.addActionListener(actionEvent -> {
      if (!validateInput()) {
        return;
      }

      Equation function = new ParametricFunction(xField.getText(),yField.getText());
      graphicPanel.setScale(Integer.parseInt(scaleField.getText())*50);
      graphicPanel.setPoints(function.solveEquation(
              Double.parseDouble(minTField.getText()),
              Double.parseDouble(maxTField.getText()),
              Double.parseDouble(step.getText())
      ));
      graphicPanel.repaint();
    });
    menu.add(runButton);

    graphicPanel.setBackground(Color.DARK_GRAY);

    contentPane.add(graphicPanel, BorderLayout.CENTER);
    contentPane.add(menu, BorderLayout.SOUTH);

  }

  private boolean validateInput() {
    try {
      if (Integer.parseInt(scaleField.getText()) < 0) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(window,
              "the scale must be a positive number",
              "Incorrect Format",
              JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  private void addToMenu(String label, JTextField scaleField) {
    menu.add(new JLabel(label));
    menu.add(scaleField);
  }

  private JTextField createTextField(String value, int columns) {
    JTextField field = new JTextField(value);
    field.setColumns(columns);
    return field;
  }
}
