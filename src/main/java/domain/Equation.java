package domain;

import java.awt.geom.Point2D;
import java.util.List;

public interface Equation {
  Point2D solveEquation(double t);
  List<Point2D> solveEquation(double intervalStart, double intervalEnd, double step) throws IllegalArgumentException;
}
