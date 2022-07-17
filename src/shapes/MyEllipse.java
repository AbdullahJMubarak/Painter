package shapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;

public class MyEllipse extends MyShape {
    

    private double centerX, centerY, endX, endY;

    private Ellipse ellipse = new Ellipse();

    public MyEllipse(){}

    

    public void setCenterPoint(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;

        ellipse.setCenterX(centerX);
        ellipse.setCenterY(centerY);
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public void setRadius(){
        ellipse.setRadiusX(Math.abs((endX - centerX)));
        ellipse.setRadiusY(Math.abs((endY - centerY)));
    }

    @Override
    public boolean containsPoint(Point2D point){
        return ellipse.contains(point);
    }

    public void check(){
        if(centerX > endX) {
            ellipse.setCenterX(endX);
        }
        if(centerY > endY) {
            ellipse.setCenterY(endY);
        }
    }

    public double getCenterX(){
        return ellipse.getCenterX();
    }

    public double getCenterY(){
        return  ellipse.getCenterY();
    }

    public double getRadiusX(){
        return ellipse.getRadiusX();
    }

    public double getRadiusY(){
        return ellipse.getRadiusY();
    }

    @Override
    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.strokeOval(getCenterX(), getCenterY(), getRadiusX(), getRadiusY());
        graphicsContext.fillOval(getCenterX(), getCenterY(), getRadiusX(), getRadiusY());
    }
}
