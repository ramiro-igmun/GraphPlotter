package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public class GraphicPanel extends JPanel {
  double scale;
  List<Point2D> points;

  public GraphicPanel() {
    this.scale = 50;
    points = Collections.emptyList();
  }

  public void setScale(int scale) {
    this.scale = scale;
  }

  public void setPoints(List<Point2D> points) {
    this.points = points;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //moves the origin of the coordinate system to the center of the Panel
    g2d.translate(this.getWidth() / 2, this.getHeight() / 2);

    drawReticule(g2d);

    g2d.setColor(Color.RED);
    //inverts the direction of the y axis to increase when going up
    g2d.scale(1, -1);

    plotPoints(g2d);
  }

  private void plotPoints(Graphics2D g2d) {
    for (int i = 0; i < points.size() - 1; i++) {

      g2d.draw(new Line2D.Double(points.get(i).getX() * scale
              , points.get(i).getY() * scale
              , points.get(i + 1).getX() * scale
              , points.get(i + 1).getY() * scale));
    }
  }

  private void drawReticule(Graphics2D g2d) {

    g2d.setColor(Color.LIGHT_GRAY);

    //draw axis lines
    g2d.drawLine(0, -this.getHeight() / 2, 0, this.getHeight() / 2);
    g2d.drawLine(-this.getWidth() / 2, 0, this.getWidth() / 2, 0);

    //set the stroke for the reticule
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2, 8}, 0);
    g2d.setStroke(dashed);


    //draw vertical lines (reticule)
    for (int i = 0, count = 0; i < getWidth() / 2; i += scale, count++) {
      g2d.drawString("" + count, i + 5, -5);
      if (count != 0) {
        g2d.drawString("" + -count, -i + 5, -5);
      }

      g2d.drawLine(i, -getHeight() / 2, i, getHeight() / 2);
      g2d.drawLine(-i, -getHeight() / 2, -i, getHeight() / 2);
    }

    //draw horizontal lines (reticule)
    for (int i = 0, count = 0; i < getWidth() / 2; i += scale, count++) {

      if (count != 0) {
        g2d.drawString("" + -count, 5, i - 5);
        g2d.drawString("" + count, 5, -i - 5);
      }

      g2d.drawLine(-getWidth() / 2, i, getWidth() / 2, i);
      g2d.drawLine(-getWidth() / 2, -i, getWidth() / 2, -i);
    }

    //reset stroke
    g2d.setStroke(new BasicStroke());
  }
}
