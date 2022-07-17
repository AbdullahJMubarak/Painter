package shapes;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends MyShape{
   

    public double startX, startY, endX, endY, width, height;

    private Rectangle rectangle = new Rectangle();

    public MyRectangle(){ }

    

    public void setStartPoint(double startX, double startY){
        this.startX = startX;
        this.startY = startY;

        rectangle.setX(startX);
        rectangle.setY(startY);
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public void setWidth(){
        this.width = Math.abs((endX - startX));

        rectangle.setWidth(Math.abs((endX - startX)));
    }

    public void setHeight(){
        this.height = Math.abs((endY - startY));

        rectangle.setHeight(Math.abs((endY - startY)));
    }

    @Override
    public boolean containsPoint(Point2D point){
        return rectangle.contains(point);
    }

    public void check(){
        if(getX() > endX) {
            rectangle.setX(endX);
        }
        if(getY() > endY) {
            rectangle.setY(endY);
        }
    }

    public double getX(){
        return rectangle.getX();
    }

    public double getY(){
        return rectangle.getY();
    }

    public double getWidth(){
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    

    @Override
    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillRect(getX(), getY(), getWidth(), getHeight());
        graphicsContext.strokeRect(getX(), getY(), getWidth(), getHeight());
    }
}
