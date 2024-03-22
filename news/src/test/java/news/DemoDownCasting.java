package news;

import news.geo.contract.Mesurable1D;
import news.geo.contract.Mesurable2D;
import news.geo.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class DemoDownCasting {
    Point2D ptO;
    Point2D ptA;
    Point2D ptB;
    Point2D ptC;
    Point2D ptD;
    Point2D ptE;
    Point2D ptF;

    WeightedPoint wptW1;
    WeightedPoint wptW2;
    WeightedPoint wptW3;

    Circle c1;
    Circle c2;

    Segment sAB;
    Segment sBC;

    Polygon triABC;
    Polygon quadriABCD;
    Polygon pentaABCDE;
    Polygon hexaABCDEF;

    List<Shape> shapes;

    @BeforeEach
    void initData(){
        ptO = Point2D.of("O");
        ptA = Point2D.of("A", 1.5, 2.25);
        ptB = Point2D.of("B", 1.5, 5.25);
        ptC = Point2D.of("C", 5.5, 5.25);
        ptD = Point2D.of("D", 5.5, 2.25);
        ptE = Point2D.of("E", 5.5, -0.75);
        ptF = Point2D.of("F", 3.0, 3.0);
        wptW1 = WeightedPoint.of("WP1", 12.5, 15.75, 2.0);
        wptW2 = WeightedPoint.of("WP2", 10.5, 25.75, 3.0);
        wptW3 = WeightedPoint.of("WP3", 18.5, 35.75, -1.0);
        sAB = Segment.of(ptA, ptB);
        sBC = Segment.of(ptB, ptC);
        c1 = Circle.of("C1", 5.5, ptO);
        c2 = Circle.of("C2", 20.0, ptO);
        triABC = Polygon.of(ptA, ptB, ptC);
        quadriABCD = Polygon.of(ptA, ptB, ptC, ptD);
        pentaABCDE = Polygon.of(ptA, ptB, ptC, ptD, ptE);
        hexaABCDEF = Polygon.of(ptA, ptB, ptC, ptD, ptE, ptF);
        shapes = new ArrayList<>();
        Collections.addAll(shapes,
                ptO, ptA, ptB, ptC, ptD, ptE, ptF,
                wptW1, wptW2, wptW3,
                sAB,
                sBC,
                c1, c2,
                triABC,
                quadriABCD,
                pentaABCDE,
                hexaABCDEF
        );
        Collections.shuffle(shapes);
    }

    @Test
    void demoDisplay(){
        shapes.forEach(System.out::println);
    }

    @Test
    void downCasting(){
        // handle points only: old code
        for (Shape shape: shapes){
            // System.out.println(shape);
            if (shape instanceof Point2D){
                Point2D pt = (Point2D) shape;
                System.out.println(MessageFormat.format(
                        "Pt coords: x={0}, y={1}   check: {2})",
                        pt.getX(),
                        pt.getY(),
                        pt
                ));
            }
        }
        System.out.println();
        // modern version
        for (Shape shape: shapes){
            // System.out.println(shape);
            if (shape instanceof Point2D pt){
                System.out.println(MessageFormat.format(
                        "Pt coords: x={0}, y={1}   check: {2})",
                        pt.getX(),
                        pt.getY(),
                        pt
                ));
            }
        }
    }

    @Test
    void demoAveragePointX(){
        double averageX = shapes.stream()
                // TODO: write helper method: ofType<Point2D>
                .filter(shape -> shape instanceof Point2D)
                .map(shape -> (Point2D) shape)

                .mapToDouble(Point2D::getX)
                .average()
                .orElseThrow();
        System.out.println(averageX);
    }

    @Test
    void demoTotalAreaMesurable2D(){
        double totalArea = 0;
        for (var shape: shapes){
            if (shape instanceof Mesurable2D mesurable2D){
                totalArea += mesurable2D.area();
            }
        }
        System.out.println("Total area: "  + totalArea);
    }

    // idem en mode stream avec peek on object mesurable 2D
    @Test
    void demoTotalAreaMesurable2D_stream(){
        double totalArea = shapes.stream()
                .filter(s -> s instanceof Mesurable2D)
                .map(s -> (Mesurable2D) s)
                .peek(System.out::println)
                .mapToDouble(Mesurable2D::area)
                .sum();
        System.out.println("Total area: "  + totalArea);
    }

    // Pattern Matching
    @Test
    void demoPatternMatchingInstruction(){
        for (var shape: shapes){
            switch (shape){
                case Point2D pt:
                    System.out.println(MessageFormat.format(
                            "x = {0} from point {1}",
                            pt.getX(),
                            pt
                    ));
                    break;
                case Segment seg:
                    System.out.println(MessageFormat.format(
                            "x = {0} from point {1}",
                            seg.length(),
                            seg
                    ));
                    break;
                default:
                    // do nothing
            }
        }
    }

    @Test
    void demoPatternMatchingExpression(){
        for (var shape: shapes){
            double indicator = switch (shape){
                // case null ->
                case Mesurable1D m1 -> m1.length();
                case Mesurable2D m2 -> m2.perimeter();
                case Point2D pt when (pt.getX() > 0) -> pt.distance(ptO);
                case Point2D pt  -> - pt.distance(ptO);
                default -> throw new UnsupportedOperationException("cannot compute indicator");
            };
            System.out.println(MessageFormat.format("Indicator = {0} for shape: {1}",
                    indicator, shape));
        }
    }

}
