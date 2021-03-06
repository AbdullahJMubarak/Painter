package shapes;

import javafx.geometry.Point2D;
import java.util.ArrayList;

public class MyScribble extends MyShape {
    

    private double startX, startY, endX, endY;

    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();

    public MyScribble(){}

    public void setStartPoint(double startX, double startY){
        this.startX = startX;
        this.startY= startY;
    }

    public void setEndPoint(double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
    }

    public void addPoint(double x, double y){
        xValues.add(x);
        yValues.add(y);
    }

    @Override
    public boolean containsPoint(Point2D point){
        for(int i = 0; i < xValues.size(); i++){
            if(xValues.get(i) == point.getX() && yValues.get(i) == point.getY()){
                return true;
            }
        }

        return false;
    }

    public double getStartX(){
        return startX;
    }

    public double getStartY(){
        return startY;
    }

    public double getEndX(){
        return endX;
    }

    public double getEndY(){
        return endY;
    }

    public double[] getAllXValues(){
        double[] xVals = new double[xValues.size()];

        for(int i = 0; i < xValues.size(); i++){
            xVals[i] = xValues.get(i);
        }

        return xVals;
    }

    public double[] getAllYValues(){
        double[] yVals = new double[yValues.size()];

        for(int i = 0; i < yValues.size(); i++){
            yVals[i] = yValues.get(i);
        }

        return yVals;
    }

    

    @Override
    public void draw(){
        graphicsContext.setStroke(getColor().getValue());
        graphicsContext.beginPath();
        graphicsContext.lineTo(getStartX(), getStartY());

        for(int i = 0; i < xValues.size(); i++){
            graphicsContext.lineTo(xValues.get(i), yValues.get(i));
            graphicsContext.stroke();
        }

        graphicsContext.lineTo(getEndX(), getEndY());
        graphicsContext.stroke();

        graphicsContext.closePath();
    }
}
