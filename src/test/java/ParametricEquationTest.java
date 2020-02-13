import domain.ParametricFunction;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParametricEquationTest {

  @Test
  void testSolveEquationForASimpleParameterValue() {
    ParametricFunction equation = new ParametricFunction("t*t","t/2");
    Point2D point = equation.solveEquation(4);
    assertEquals(16,point.getX());
    assertEquals(2,point.getY());
  }

  @Test
  void testSolveEquationInAGivenIntervalWithIntegerValues(){
    ParametricFunction equation = new ParametricFunction("t","t");
    List<Point2D> points = equation.solveEquation(-20,20,0.1);
    assertEquals(400, points.size());
    assertEquals((-20+0.1+0.1), points.get(2).getY());
  }

  @Test
  void testSolveEquationInAGivenIntervalWithRealNumbers(){
    ParametricFunction equation = new ParametricFunction("t","t");
    List<Point2D> points = equation.solveEquation(-4.5,2.3,1.4);
    assertEquals(5, points.size());
    assertEquals((-4.5+1.4+1.4), points.get(2).getY());
  }

  @Test
  void testSolveEquationInAGivenIntervalThrowsException(){
    ParametricFunction equation = new ParametricFunction("t","t");
    assertThrows(IllegalArgumentException.class
            ,() -> equation.solveEquation(2,-2,1)
            ,"The interval starting value must be lower that the end value");
    assertThrows(IllegalArgumentException.class
            ,() -> equation.solveEquation(2,-2,-1)
            ,"The step must be higher than 0");
  }
}