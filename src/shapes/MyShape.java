package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class MyShape {
    
    
    protected GraphicsContext graphicsContext;
    protected ColorPicker cpLine;
    protected ColorPicker cpFill;
    
    
    public void MyShape(){}
    
    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void setFill(ColorPicker colorPicker){
        cpFill = colorPicker;
    }
    
    public ColorPicker getColor(){
        return cpLine;
    }

    public ColorPicker getFill(){
        return cpFill;
    }

    public boolean containsPoint(Point2D point){
        return false;
    }
    
    public abstract void draw();
    
}
