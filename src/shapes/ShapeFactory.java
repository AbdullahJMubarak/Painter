/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author OWNER
 */
public class ShapeFactory {

    public MyShape createShape(String name) {
        switch (name) {
            case "Circle":
                return new MyCircle();
            case "Rectangle":
                return new MyRectangle();
            case "Square":
                return new MySquare();
            case "Ellipse":
                return new MyEllipse();
            case "Line":
                return new MyLine();
            case "Scribble":
                return new MyScribble();
            case "ClosedPolygon":
                return new MyClosedPolygon();
            default:
                return null;
        }
    }
}
