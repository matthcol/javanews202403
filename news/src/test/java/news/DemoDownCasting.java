package news;

import news.geo.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        wptW2 = WeightedPoint.of("WP3", 18.5, 35.75, -1.0);
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

}
