package domain;

import org.mariuszgromada.math.mxparser.Function;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ParametricFunction implements Equation{

  private Function xT;
  private Function yT;

  public ParametricFunction(String xBody, String yBody) {
    this.xT = new Function("f(t) = " + xBody);
    this.yT = new Function("f(t) = " + yBody);
  }

  @Override
  public Point2D solveEquation(double t) {
    return new Point2D.Double(xT.calculate(t), yT.calculate(t));
  }

  //Solves the equation for a given interval returning an array of results
  @Override
  public List<Point2D> solveEquation(double intervalStart, double intervalEnd, double step) throws IllegalArgumentException{
    checkArguments(intervalStart,intervalEnd,step);
    List<Point2D> points = new ArrayList<>();
    for (double i = intervalStart; i <= intervalEnd; i += step) {
      points.add(solveEquation(i));
    }
    return points;
  }

  private void checkArguments(double intervalStart, double intervalEnd, double step) {
    if (intervalStart > intervalEnd) {
      throw new IllegalArgumentException("The interval starting value must be lower that the end value");
    } else if (step <= 0) {
      throw new IllegalArgumentException("The step must be higher than 0");
    }
  }
}