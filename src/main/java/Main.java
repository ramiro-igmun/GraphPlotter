import view.GraphicPanel;
import view.MainWindow;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {

    MainWindow window = new MainWindow(new GraphicPanel());
    SwingUtilities.invokeLater(window);

  }
}
