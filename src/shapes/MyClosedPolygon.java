package shapes;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class MyClosedPolygon extends MyShape {
    
    private ArrayList<Double> polygonX = new ArrayList<>();
    private ArrayList<Double> polygonY = new ArrayList<>();

    public MyClosedPolygon(){}

    public void addPoint(double x, double y){
        polygonX.add(x);
        polygonY.add(y);
    }

    // This wont really work for a polygon unless you click the edges
    @Override
    public boolean containsPoint(Point2D point){
        for(int i = 0; i < polygonX.size(); i++){
            if(polygonX.get(i) == point.getX() && polygonY.get(i) == point.getY()){
                return true;
            }
        }

        return false;
    }

    public ArrayList<Double> getAllXValues(){
        return polygonX;
    }

    public ArrayList<Double> getAllYValues(){
        return polygonY;
    }

    @Override
    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        double[] polyX = new double[polygonX.size()];
        double[] polyY = new double[polygonY.size()];

        for(int i = 0; i < polygonX.size(); i++){
            polyX[i] = polygonX.get(i);
            polyY[i] = polygonY.get(i);
        }

        graphicsContext.strokePolygon(polyX, polyY, polyX.length);
        graphicsContext.fillPolygon(polyX, polyY, polyX.length);
    }
}
