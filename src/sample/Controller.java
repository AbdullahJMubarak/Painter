package sample;

import actions.ClearAction;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shapes.*;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Controller {

    private String mode, color;

    public GraphicsContext g;
    public ColorPicker cpLine = new ColorPicker(Color.BLACK);
    public ColorPicker cpFill = new ColorPicker(Color.BLACK);
    
    
    public ShapeFactory f = new ShapeFactory();
    public MyScribble myScribble, pasteScribble;
    public MyLine myLine, pasteLine;
    public MyRectangle myRectangle, pasteRectangle;
    public MyEllipse myEllipse, pasteEllipse;
    public MySquare mySquare, pasteSquare;
    public MyCircle myCircle, pasteCircle;
    public MyClosedPolygon myClosedPolygon, pasteClosedPolygon;

    public Stack<MyShape> undoHistory = new Stack<>();
    public Stack<MyShape> redoHistory = new Stack<>();

    public Point2D deletePoint, copyPoint, pastePoint, movePoint;
    
    @FXML
    private Canvas drawingCanvas;

    /*
    * Open getting started information page
    * */
    @FXML
    void openGettingStarted(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Guide");
        alert.setHeaderText("Welcome to Paint Program!");

        alert.setContentText("This tutorial page will run you through how to use the Paint Program! application. \n" +
                "\n The program contains the following modes: \n" +
                "- Scribble/Freehand Lines \n" +
                "- Straight Lines \n" +
                "- Rectangles \n" +
                "- Ellipses \n" +
                "- Squares \n" +
                "- Circles \n" +
                "- Closed Polygons \n" +
                "\n" +
                "Alongside offering a wide range of shapes to draw, " +
                "Paint It! gives you the option to choose a color for each " +
                "individual shape that you draw. \n" +
                "\n" +
                "The program contains the following colors to choose from: \n" +
                "- Black \n" +
                "- Blue \n" +
                "- Red \n" +
                "\n" +
                "The program contains the following fill types for closed shapes: \n" +
                "- Fill \n" +
                "- Transparent \n" +
                "\n" +
                "The following selection methods are available in the program: \n" +
                        "- Move the selected shape to a new location by clicking and dragging it to where you " +
                        "what it to be moved to \n" +
                        "- Delete the object from the canvas by simply clicking the object \n" +
                        "- Copy and paste an object by clicking and dragging the shape where you want" +
                        "to copy it to \n \n" +
                        "" +
                "The more advanced functions that are available are: \n" +
                "- Undo and Redo \n" +
                "- Save an image and load in an image \n" +
                "");

        // Show the dialog
        alert.show();
    }

    /*
     * Start of setFill
     * */
    @FXML
    void setFillYes(ActionEvent event) {
        switch (color) {
            case "black":
                cpFill = new ColorPicker(Color.BLACK);
                break;
            case "blue":
                cpFill = new ColorPicker(Color.BLUE);
                break;
            case "red":
                cpFill = new ColorPicker(Color.RED);
                break;
            case "yellow":
                cpFill = new ColorPicker(Color.YELLOW);
                break;
             case "green":
                cpFill = new ColorPicker(Color.GREEN);
                break;
             case "cyan":
                cpFill = new ColorPicker(Color.CYAN);
                break;
             case "orange":
                cpFill = new ColorPicker(Color.ORANGE);
                break;
             case "pink":
                cpFill = new ColorPicker(Color.PINK);
                break;
             case "magenta":
                cpFill = new ColorPicker(Color.MAGENTA);
                break;
            default:
                break;
        }
    }

    @FXML
    void setFillTransparent(ActionEvent event){
        cpFill = new ColorPicker(Color.TRANSPARENT);
    }
    /*
    * End of setFill
    * */

    /*
    * Start of setColor
    * */
    @FXML
    void setColorBlack(ActionEvent event) {
        color = "black";

        cpLine = new ColorPicker(Color.BLACK);
        cpFill = new ColorPicker(Color.BLACK);
    }

    @FXML
    void setColorBlue(ActionEvent event) {
        color = "blue";

        cpLine = new ColorPicker(Color.BLUE);
        cpFill = new ColorPicker(Color.BLUE);
    }

    @FXML
    void setColorRed(ActionEvent event) {
        color = "red";

        cpLine = new ColorPicker(Color.RED);
        cpFill = new ColorPicker(Color.RED);
    }
    
    @FXML
    void setColorYellow(ActionEvent event) {
        color = "yellow";

        cpLine = new ColorPicker(Color.YELLOW);
        cpFill = new ColorPicker(Color.YELLOW);
    }
    
    @FXML
    void setColorGreen(ActionEvent event) {
        color = "green";

        cpLine = new ColorPicker(Color.GREEN);
        cpFill = new ColorPicker(Color.GREEN);
    }
    
    @FXML
    void setColorCyan(ActionEvent event) {
        color = "cyan";

        cpLine = new ColorPicker(Color.CYAN);
        cpFill = new ColorPicker(Color.CYAN);
    }
    
    @FXML
    void setColorOrange(ActionEvent event) {
        color = "ornage";

        cpLine = new ColorPicker(Color.ORANGE);
        cpFill = new ColorPicker(Color.ORANGE);
    }
    
    @FXML
    void setColorPink(ActionEvent event) {
        color = "pink";

        cpLine = new ColorPicker(Color.PINK);
        cpFill = new ColorPicker(Color.PINK);
    }
    
    @FXML
    void setColorMagenta(ActionEvent event) {
        color = "magenta";

        cpLine = new ColorPicker(Color.MAGENTA);
        cpFill = new ColorPicker(Color.MAGENTA);
    }
    /*
     * End of setColor
     * */

    /*
    * Start of setMode
    * */
    @FXML
    void setModeEllipse(ActionEvent event) {
        mode = "ellipse";
    }
    
    @FXML
    void setModeClosedPolygon(ActionEvent event) {
        mode = "closed polygon";
    }

    @FXML
    void setModeRectangle(ActionEvent event) {
        mode = "rectangle";
    }

    @FXML
    void setModeScribble(ActionEvent event) {
        mode = "scribble";
    }

    @FXML
    void setModeSquare(ActionEvent event) {
        mode = "square";
    }

    @FXML
    void setModeStraightLine(ActionEvent event) {
        mode = "straight line";
    }

    @FXML
    void setModeCircle(ActionEvent event) {
        mode = "circle";
    }

    @FXML
    void setModeDelete(ActionEvent event){
        mode = "delete";
    }

    @FXML
    void setModeCopy(ActionEvent event) {
        mode = "copy";
    }

    @FXML
    void setModeMove(ActionEvent event){
        mode = "move";
    }
    /*
    * End of setMode
    * */

    /*
    * Clear canvas
    * */
    @FXML
    void clearCanvas(ActionEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        undoHistory.push(ClearAction.getInstance());
    }


    /*
    * Start of start draw event
    * */
    @FXML
    void startDraw(MouseEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.setStroke(cpLine.getValue());

        switch (mode) {
            case "scribble":
                // Scribble
                g.setStroke(cpLine.getValue());
                g.beginPath();
                g.lineTo(event.getX(), event.getY());
                myScribble =(MyScribble)f.createShape("Scribble");
                myScribble.setGraphicsContext(drawingCanvas.getGraphicsContext2D());
                myScribble.setColor(cpLine);
                myScribble.setStartPoint(event.getX(), event.getY());
                break;
            case "straight line":
                // Straight Line
                g.setStroke(cpLine.getValue());
                myLine = (MyLine)f.createShape("Line");
                myLine.setGraphicsContext(g);
                myLine.setColor(cpLine);
                myLine.setStartPoint(event.getX(), event.getY());
                break;
            case "rectangle":
                // Rectangle
                g.setStroke(cpLine.getValue());
                g.setFill(cpFill.getValue());
                myRectangle = (MyRectangle) f.createShape("Rectangle");
                myRectangle.setGraphicsContext(g);
                myRectangle.setColor(cpLine);
                myRectangle.setFill(cpFill);
                myRectangle.setStartPoint(event.getX(), event.getY());
                break;
            case "ellipse":
                // Ellipse
                g.setStroke(cpLine.getValue());
                g.setFill(cpFill.getValue());
                myEllipse = (MyEllipse) f.createShape("Ellipse");
                myEllipse.setGraphicsContext(g);
                myEllipse.setColor(cpLine);
                myEllipse.setFill(cpFill);
                myEllipse.setCenterPoint(event.getX(), event.getY());
                break;
            case "square":
                // Square
                g.setStroke(cpLine.getValue());
                g.setFill(cpFill.getValue());
                mySquare = (MySquare)f.createShape("Square");
                mySquare.setGraphicsContext(g);
                mySquare.setColor(cpLine);
                mySquare.setFill(cpFill);
                mySquare.setStartPoint(event.getX(), event.getY());
                break;
            case "circle":
                // Circle
                g.setStroke(cpLine.getValue());
                g.setFill(cpFill.getValue());
                myCircle = (MyCircle)f.createShape("Circle");
                myCircle.setGraphicsContext(g);
                myCircle.setColor(cpLine);
                myCircle.setFill(cpFill);
                myCircle.setCenterPoint(event.getX(), event.getY());
                break;
            case "closed polygon":
                // Closed Polygon
                g.setStroke(cpLine.getValue());
                g.setFill(cpFill.getValue());
                myClosedPolygon = (MyClosedPolygon)f.createShape("ClosedPolygon");
                myClosedPolygon.setGraphicsContext(g);
                myClosedPolygon.setColor(cpLine);
                myClosedPolygon.setFill(cpFill);
                myClosedPolygon.addPoint(event.getX(), event.getY());
                break;
            case "delete":
                // Delete Shape
                deletePoint = new Point2D(event.getX(), event.getY());
                break;
            case "copy":
                // Copy Shape
                copyPoint = new Point2D(event.getX(), event.getY());
                break;
            case "move":
                // Move Shape
                deletePoint = new Point2D(event.getX(), event.getY());
                break;
            default:
                break;
        }
    }
    /*
    * End of start draw event
    * */

    /*
    * Start of drag event
    * */
    @FXML
    void drag(MouseEvent event) {
        if(mode.equals("scribble")){        // Scribble
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.addPoint(event.getX(), event.getY());
        }
        if(mode.equals("closed polygon")){      // Close Polygon
            myClosedPolygon.addPoint(event.getX(), event.getY());

            try{
                Thread.sleep(250);
            }
            catch (InterruptedException e){ }
        }
    }
    /*
    * End of drag event
    * */

    /*
    * Start of end draw event
    * */
    @FXML
    void endDraw(MouseEvent event) {

        switch (mode) {
            case "scribble":
                // Scribble
                g.lineTo(event.getX(), event.getY());
                g.stroke();
                myScribble.setEndPoint(event.getX(), event.getY());
                undoHistory.push(myScribble);
                break;
            case "straight line":
                // Straight Line
                myLine.setEndPoint(event.getX(), event.getY());
                myLine.draw();
                undoHistory.push(myLine);
                break;
            case "rectangle":
                // Rectangle
                myRectangle.setEndPoint(event.getX(), event.getY());
                myRectangle.setWidth();
                myRectangle.setHeight();
                myRectangle.check();
                myRectangle.draw();
                undoHistory.push(myRectangle);
                break;
            case "ellipse":
                // Ellipse
                myEllipse.setEndPoint(event.getX(), event.getY());
                myEllipse.setRadius();
                myEllipse.check();
                myEllipse.draw();
                undoHistory.push(myEllipse);
                break;
            case "square":
                // Square
                mySquare.setEndPoint(event.getX(), event.getY());
                mySquare.setWidth();
                mySquare.setHeight();
                mySquare.check();
                mySquare.draw();
                undoHistory.push(mySquare);
                break;
            case "circle":
                // Circle
                myCircle.setEndPoint(event.getX(), event.getY());
                myCircle.setRadius();
                myCircle.check();
                myCircle.draw();
                undoHistory.push(myCircle);
                break;
            case "closed polygon":
                // Closed Polygon
                myClosedPolygon.addPoint(event.getX(), event.getY());
                myClosedPolygon.draw();
                undoHistory.push(myClosedPolygon);
                break;
            case "delete":
                // Delete Shape
                delete(deletePoint);
                break;
            case "copy":
                {
                    // Copy Shape
                    pastePoint = new Point2D(event.getX(), event.getY());
                    Stack<MyShape> tempUndo = new Stack<>();
                    // Create a copy stack of undoHistory
                    Iterator<MyShape> undoHistoryIterator = undoHistory.iterator();
                    while (undoHistoryIterator.hasNext()) {
                        tempUndo.push(undoHistoryIterator.next());
                    }       // Iterate through copy stack to find the shape, and save it
                    while (!tempUndo.isEmpty()) {
                        MyShape tempShape = tempUndo.pop();
                        
                        if (tempShape.containsPoint(copyPoint)) {
                            if(tempShape.getClass() == MyScribble.class){
                                System.out.println("Copying a scribble...");
                                
                                MyScribble tempScribble = (MyScribble) tempShape;
                                
                                g.setStroke(tempScribble.getColor().getValue());
                                
                                pasteScribble = new MyScribble();
                                
                                pasteScribble.setGraphicsContext(g);
                                pasteScribble.setColor(cpLine);
                                pasteScribble.setStartPoint(pastePoint.getX(), pastePoint.getY());
                                
                                double [] oldX = tempScribble.getAllXValues();
                                double [] oldY = tempScribble.getAllYValues();
                                
                                double xDifference = pastePoint.getX() - tempScribble.getStartX();
                                double yDifference = pastePoint.getY() - tempScribble.getStartY();
                                
                                for(int i = 0; i < oldX.length; i++){
                                    pasteScribble.addPoint(oldX[i] + xDifference, oldY[i] + yDifference);
                                }
                                
                                pasteScribble.setEndPoint(tempScribble.getEndX() + xDifference, tempScribble.getEndY() + yDifference);
                                
                                pasteScribble.draw();
                                
                                undoHistory.push(pasteScribble);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyLine.class){
                                System.out.println("Copying a line...");
                                
                                MyLine tempLine = (MyLine) tempShape;
                                
                                g.setStroke(tempLine.getColor().getValue());
                                
                                pasteLine = new MyLine();
                                
                                pasteLine.setGraphicsContext(g);
                                pasteLine.setColor(tempLine.getColor());
                                
                                double xDifference = pastePoint.getX() - tempLine.getStartX();
                                double yDifference = pastePoint.getY() - tempLine.getStartY();
                                
                                pasteLine.setStartPoint(pastePoint.getX(), pastePoint.getY());
                                pasteLine.setEndPoint(tempLine.getEndX() + xDifference, tempLine.getEndY() + yDifference);
                                
                                pasteLine.draw();
                                
                                undoHistory.push(pasteLine);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyRectangle.class){
                                System.out.println("Copying a rectangle...");
                                
                                MyRectangle tempRectangle = (MyRectangle) tempShape;
                                
                                pasteRectangle = new MyRectangle();
                                
                                pasteRectangle.setGraphicsContext(g);
                                pasteRectangle.setColor(tempRectangle.getColor());
                                pasteRectangle.setFill(tempRectangle.getFill());
                                
                                pasteRectangle.setStartPoint(pastePoint.getX(), pastePoint.getY());
                                pasteRectangle.setEndPoint(pastePoint.getX() + tempRectangle.getWidth(), pastePoint.getY() + tempRectangle.getHeight());
                                pasteRectangle.setWidth();
                                pasteRectangle.setHeight();
                                
                                pasteRectangle.draw();
                                
                                undoHistory.push(pasteRectangle);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyEllipse.class){
                                System.out.println("Copying an ellipse...");
                                
                                MyEllipse tempEllipse = (MyEllipse) tempShape;
                                
                                g.setStroke(tempEllipse.getColor().getValue());
                                g.setFill(tempEllipse.getFill().getValue());
                                
                                pasteEllipse = new MyEllipse();
                                
                                pasteEllipse.setGraphicsContext(g);
                                pasteEllipse.setColor(cpLine);
                                pasteEllipse.setFill(cpFill);
                                
                                pasteEllipse.setCenterPoint(pastePoint.getX(), pastePoint.getY());
                                pasteEllipse.setEndPoint(pasteEllipse.getCenterX() + tempEllipse.getRadiusX(), pasteEllipse.getCenterY() + tempEllipse.getRadiusY());
                                pasteEllipse.setRadius();
                                
                                pasteEllipse.draw();
                                
                                undoHistory.push(pasteEllipse);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MySquare.class){
                                System.out.println("Copying a square...");
                                
                                MySquare tempSquare = (MySquare) tempShape;
                                
                                pasteSquare = new MySquare();
                                
                                pasteSquare.setGraphicsContext(g);
                                pasteSquare.setColor(tempSquare.getColor());
                                pasteSquare.setFill(tempSquare.getFill());
                                
                                pasteSquare.setStartPoint(pastePoint.getX(), pastePoint.getY());
                                pasteSquare.setEndPoint(pastePoint.getX() + tempSquare.getWidth(), pastePoint.getY() + tempSquare.getHeight());
                                pasteSquare.setWidth();
                                pasteSquare.setHeight();
                                
                                pasteSquare.draw();
                                
                                undoHistory.push(pasteSquare);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyCircle.class){
                                System.out.println("Copying a circle...");
                                
                                MyCircle tempCircle = (MyCircle) tempShape;
                                
                                g.setStroke(tempCircle.getColor().getValue());
                                g.setFill(tempCircle.getFill().getValue());
                                
                                pasteCircle = new MyCircle();
                                
                                pasteCircle.setGraphicsContext(g);
                                pasteCircle.setColor(tempCircle.getColor());
                                pasteCircle.setFill(tempCircle.getFill());
                                
                                pasteCircle.setCenterPoint(pastePoint.getX(), pastePoint.getY());
                                pasteCircle.setRadius(tempCircle.getRadius());
                                
                                pasteCircle.draw();
                                
                                undoHistory.push(pasteCircle);
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyClosedPolygon.class){
                                
                                System.out.println("Copying a closed polygon...");
                                
                                MyClosedPolygon tempClosedPolygon = (MyClosedPolygon) tempShape;
                                
                                g.setStroke(tempClosedPolygon.getColor().getValue());
                                
                                pasteClosedPolygon = new MyClosedPolygon();
                                
                                pasteClosedPolygon.setGraphicsContext(g);
                                pasteClosedPolygon.setColor(tempClosedPolygon.getColor());
                                pasteClosedPolygon.setFill(tempClosedPolygon.getFill());
                                
                                ArrayList<Double> polygonX = tempClosedPolygon.getAllXValues();
                                ArrayList<Double> polygonY = tempClosedPolygon.getAllYValues();
                                
                                double xDifference = pastePoint.getX() - polygonX.get(0);
                                double yDifference = pastePoint.getY() - polygonY.get(0);
                                
                                for(int i = 0; i < polygonX.size(); i++){
                                    pasteClosedPolygon.addPoint(polygonX.get(i) + xDifference, polygonY.get(i)+ yDifference);
                                }
                                
                                pasteClosedPolygon.draw();
                                
                                undoHistory.push(pasteClosedPolygon);
                                
                                break;
                            }
                        }
                        else{
                        }
                    }       break;
                }
            case "move":
                {
                    Stack<MyShape> tempUndo = new Stack<>();
                    // Create a copy stack of undoHistory
                    Iterator<MyShape> undoHistoryIterator = undoHistory.iterator();
                    while (undoHistoryIterator.hasNext()) {
                        tempUndo.push(undoHistoryIterator.next());
                    }       // Iterate through copy stack to find the shape, and save it
                    while (!tempUndo.isEmpty()) {
                        movePoint = new Point2D(event.getX(), event.getY());
                        
                        MyShape tempShape = tempUndo.pop();
                        
                        if (tempShape.containsPoint(deletePoint)) {
                            if(tempShape.getClass() == MyScribble.class){
                                System.out.println("Moving a scribble...");
                                
                                MyScribble tempScribble = (MyScribble) tempShape;
                                
                                g.setStroke(tempScribble.getColor().getValue());
                                
                                pasteScribble = new MyScribble();
                                
                                pasteScribble.setGraphicsContext(g);
                                pasteScribble.setColor(cpLine);
                                
                                pasteScribble.setStartPoint(movePoint.getX(), movePoint.getY());
                                
                                double [] oldX = tempScribble.getAllXValues();
                                double [] oldY = tempScribble.getAllYValues();
                                
                                double xDifference = movePoint.getX() - tempScribble.getStartX();
                                double yDifference = movePoint.getY() - tempScribble.getStartY();
                                
                                for(int i = 0; i < oldX.length; i++){
                                    pasteScribble.addPoint(oldX[i] + xDifference, oldY[i] + yDifference);
                                }
                                
                                pasteScribble.setEndPoint(tempScribble.getEndX() + xDifference, tempScribble.getEndY() + yDifference);
                                
                                pasteScribble.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteScribble);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyLine.class){
                                System.out.println("Moving a line...");
                                
                                MyLine tempLine = (MyLine) tempShape;
                                
                                g.setStroke(tempLine.getColor().getValue());
                                
                                pasteLine = new MyLine();
                                
                                pasteLine.setGraphicsContext(g);
                                pasteLine.setColor(tempLine.getColor());
                                
                                double xDifference = movePoint.getX() - tempLine.getStartX();
                                double yDifference = movePoint.getY() - tempLine.getStartY();
                                
                                pasteLine.setStartPoint(movePoint.getX(), movePoint.getY());
                                pasteLine.setEndPoint(tempLine.getEndX() + xDifference, tempLine.getEndY() + yDifference);
                                
                                pasteLine.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteLine);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyRectangle.class){
                                System.out.println("Moving a rectangle...");
                                
                                MyRectangle tempRectangle = (MyRectangle) tempShape;
                                
                                pasteRectangle = new MyRectangle();
                                
                                pasteRectangle.setGraphicsContext(g);
                                pasteRectangle.setColor(tempRectangle.getColor());
                                pasteRectangle.setFill(tempRectangle.getFill());
                                
                                pasteRectangle.setStartPoint(movePoint.getX(), movePoint.getY());
                                pasteRectangle.setEndPoint(movePoint.getX() + tempRectangle.getWidth(), movePoint.getY() + tempRectangle.getHeight());
                                pasteRectangle.setWidth();
                                pasteRectangle.setHeight();
                                
                                pasteRectangle.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteRectangle);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyEllipse.class){
                                MyEllipse tempEllipse = (MyEllipse) tempShape;
                                
                                g.setStroke(tempEllipse.getColor().getValue());
                                g.setFill(tempEllipse.getFill().getValue());
                                
                                pasteEllipse = new MyEllipse();
                                
                                pasteEllipse.setGraphicsContext(g);
                                pasteEllipse.setColor(cpLine);
                                pasteEllipse.setFill(cpFill);
                                
                                pasteEllipse.setCenterPoint(movePoint.getX(), movePoint.getY());
                                pasteEllipse.setEndPoint(pasteEllipse.getCenterX() + tempEllipse.getRadiusX(), pasteEllipse.getCenterY() + tempEllipse.getRadiusY());
                                pasteEllipse.setRadius();
                                
                                pasteEllipse.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteEllipse);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MySquare.class){
                                System.out.println("Moving a square...");
                                
                                MySquare tempSquare = (MySquare) tempShape;
                                
                                pasteSquare = new MySquare();
                                
                                pasteSquare.setGraphicsContext(g);
                                pasteSquare.setColor(tempSquare.getColor());
                                pasteSquare.setFill(tempSquare.getFill());
                                
                                pasteSquare.setStartPoint(movePoint.getX(), movePoint.getY());
                                pasteSquare.setEndPoint(movePoint.getX() + tempSquare.getWidth(), movePoint.getY() + tempSquare.getHeight());
                                pasteSquare.setWidth();
                                pasteSquare.setHeight();
                                
                                pasteSquare.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteSquare);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyCircle.class){
                                System.out.println("Moving a circle...");
                                
                                MyCircle tempCircle = (MyCircle) tempShape;
                                
                                g.setStroke(tempCircle.getColor().getValue());
                                g.setFill(tempCircle.getFill().getValue());
                                
                                pasteCircle = new MyCircle();
                                
                                pasteCircle.setGraphicsContext(g);
                                pasteCircle.setColor(tempCircle.getColor());
                                pasteCircle.setFill(tempCircle.getFill());
                                
                                pasteCircle.setCenterPoint(movePoint.getX(), movePoint.getY());
                                pasteCircle.setRadius(tempCircle.getRadius());
                                
                                pasteCircle.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteCircle);
                                
                                drawAll();
                                
                                break;
                            }
                            else if(tempShape.getClass() == MyClosedPolygon.class){
                                System.out.println("Moving a closed polygon...");
                                
                                MyClosedPolygon tempClosedPolygon = (MyClosedPolygon) tempShape;
                                
                                g.setStroke(tempClosedPolygon.getColor().getValue());
                                
                                pasteClosedPolygon = new MyClosedPolygon();
                                
                                pasteClosedPolygon.setGraphicsContext(g);
                                pasteClosedPolygon.setColor(tempClosedPolygon.getColor());
                                pasteClosedPolygon.setFill(tempClosedPolygon.getFill());
                                
                                ArrayList<Double> polygonX = tempClosedPolygon.getAllXValues();
                                ArrayList<Double> polygonY = tempClosedPolygon.getAllYValues();
                                
                                double xDifference = movePoint.getX() - polygonX.get(0);
                                double yDifference = movePoint.getY() - polygonY.get(0);
                                
                                for(int i = 0; i < polygonX.size(); i++){
                                    pasteClosedPolygon.addPoint(polygonX.get(i) + xDifference, polygonY.get(i)+ yDifference);
                                }
                                
                                pasteClosedPolygon.draw();
                                
                                delete(deletePoint);
                                
                                undoHistory.push(pasteClosedPolygon);
                                
                                drawAll();
                                
                                break;
                            }
                        }
                        else{
                        }
                    }       break;
                }
            default:
                break;
        }
    }
    /*
    * End of end draw event
    * */

    /*
    * Start of undo
    * */
    @FXML
    void undo(ActionEvent event) {
        if(!undoHistory.empty()){
            MyShape removedShape = undoHistory.pop();
            redoHistory.push(removedShape);

            Stack<MyShape> tempStack = new Stack<>();

            g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

            Iterator iterator = undoHistory.iterator();

            while(iterator.hasNext()){
                tempStack.push((MyShape) iterator.next());
            }

            while(!tempStack.isEmpty()){
                MyShape tempShape = tempStack.pop();

                if(tempShape.getClass() == MyScribble.class){
                    MyScribble tempScribble = (MyScribble) tempShape;
                    tempScribble.draw();
                }
                else if(tempShape.getClass() == MyLine.class){
                    MyLine tempLine = (MyLine) tempShape;
                    tempLine.draw();
                }
                else if(tempShape.getClass() == MyRectangle.class){
                    MyRectangle tempRectangle = (MyRectangle) tempShape;
                    tempRectangle.draw();
                }
                else if(tempShape.getClass() == MyEllipse.class)
                {
                    MyEllipse tempEllipse = (MyEllipse) tempShape;
                    tempEllipse.draw();
                }
                else if(tempShape.getClass() == MySquare.class)
                {
                    MySquare tempSquare = (MySquare) tempShape;
                    tempSquare.draw();
                }
                else if(tempShape.getClass() == MyCircle.class)
                {
                    MyCircle tempCircle = (MyCircle) tempShape;
                    tempCircle.draw();
                }
                else if(tempShape.getClass() == MyClosedPolygon.class)
                {
                    MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                    tempClosePolygon.draw();
                }
            }
        }
    }
    /*
    * End of undo
    * */

    /*
    * Start of redo
    * */
    @FXML
    void redo(ActionEvent event) {
        if(!redoHistory.empty()){
            MyShape removedShape = redoHistory.pop();
            undoHistory.push(removedShape);

            Stack<MyShape> tempStack = new Stack<>();

            g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

            Iterator iterator = undoHistory.iterator();

            while(iterator.hasNext()){
                tempStack.push((MyShape) iterator.next());
            }

            while(!tempStack.isEmpty()){
                MyShape tempShape = tempStack.pop();

                if(tempShape.getClass() == MyScribble.class){
                    MyScribble tempScribble = (MyScribble) tempShape;
                    tempScribble.draw();
                }
                else if(tempShape.getClass() == MyLine.class){
                    MyLine tempLine = (MyLine) tempShape;
                    tempLine.draw();
                }
                else if(tempShape.getClass() == MyRectangle.class){
                    MyRectangle tempRectangle = (MyRectangle) tempShape;
                    tempRectangle.draw();
                }
                else if(tempShape.getClass() == MyEllipse.class)
                {
                    MyEllipse tempEllipse = (MyEllipse) tempShape;
                    tempEllipse.draw();
                }
                else if(tempShape.getClass() == MySquare.class)
                {
                    MySquare tempSquare = (MySquare) tempShape;
                    tempSquare.draw();
                }
                else if(tempShape.getClass() == MyCircle.class)
                {
                    MyCircle tempCircle = (MyCircle) tempShape;
                    tempCircle.draw();
                }
                else if(tempShape.getClass() == MyClosedPolygon.class)
                {
                    MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                    tempClosePolygon.draw();
                }
            }
        }
    }
    /*
    * End of redo
    * */

    /*
     * Start of delete
     *
     * Bug #1: Undo and redo do not work with delete, you cannot undo or redo a delete action for now
     * Bug #2: Redraw after object delete may mess up the order in which they were drawn
     * */
    public void delete(Point2D point){
        Stack<MyShape> tempUndo = new Stack<>();

        // Create a copy stack of undoHistory
        Iterator<MyShape> undoHistoryIterator = undoHistory.iterator();

        while(undoHistoryIterator.hasNext()){
            tempUndo.push(undoHistoryIterator.next());
        }

        // Iterate through copy stack to find the shape, and push it on redo stack to save
        while(!tempUndo.isEmpty()){
            MyShape tempShape = tempUndo.pop();

            if(tempShape.containsPoint(point)) {
                redoHistory.push(tempShape);

                // Iterate through undoHistory stack and remove the deleted shape
                Iterator<MyShape> removalIterator = undoHistory.iterator();

                while(removalIterator.hasNext()){
                    if(removalIterator.next() == redoHistory.peek()){
                        removalIterator.remove();
                        break;
                    }
                    else{
                    }
                }

                // Redraw the new canvas without the shape we just deleted
                g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

                Stack<MyShape> drawStack = new Stack<>();

                Iterator iterator = undoHistory.iterator();

                while(iterator.hasNext()){
                    drawStack.push((MyShape) iterator.next());
                }

                while(!drawStack.isEmpty()){
                    tempShape = drawStack.pop();

                    if(tempShape.getClass() == MyScribble.class){
                        MyScribble tempScribble = (MyScribble) tempShape;
                        tempScribble.draw();
                    }
                    else if(tempShape.getClass() == MyLine.class){
                        MyLine tempLine = (MyLine) tempShape;
                        tempLine.draw();
                    }
                    else if(tempShape.getClass() == MyRectangle.class){
                        MyRectangle tempRectangle = (MyRectangle) tempShape;
                        tempRectangle.draw();
                    }
                    else if(tempShape.getClass() == MyEllipse.class)
                    {
                        MyEllipse tempEllipse = (MyEllipse) tempShape;
                        tempEllipse.draw();
                    }
                    else if(tempShape.getClass() == MySquare.class)
                    {
                        MySquare tempSquare = (MySquare) tempShape;
                        tempSquare.draw();
                    }
                    else if(tempShape.getClass() == MyCircle.class)
                    {
                        MyCircle tempCircle = (MyCircle) tempShape;
                        tempCircle.draw();
                    }
                    else if(tempShape.getClass() == MyClosedPolygon.class)
                    {
                        MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                        tempClosePolygon.draw();
                    }
                }

                redoHistory.pop();

                break;
            }
            else{
            }
        }
    }
    /*
     * End of delete
     * */

    /*
    * Start of draw all
    * */
    public void drawAll(){
        if(!undoHistory.empty()){
            Stack<MyShape> tempStack = new Stack<>();

            g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

            Iterator iterator = undoHistory.iterator();

            while(iterator.hasNext()){
                tempStack.push((MyShape) iterator.next());
            }

            while(!tempStack.isEmpty()){
                MyShape tempShape = tempStack.pop();

                if(tempShape.getClass() == MyScribble.class){
                    MyScribble tempScribble = (MyScribble) tempShape;
                    tempScribble.draw();
                }
                else if(tempShape.getClass() == MyLine.class){
                    MyLine tempLine = (MyLine) tempShape;
                    tempLine.draw();
                }
                else if(tempShape.getClass() == MyRectangle.class){
                    MyRectangle tempRectangle = (MyRectangle) tempShape;
                    tempRectangle.draw();
                }
                else if(tempShape.getClass() == MyEllipse.class)
                {
                    MyEllipse tempEllipse = (MyEllipse) tempShape;
                    tempEllipse.draw();
                }
                else if(tempShape.getClass() == MySquare.class)
                {
                    MySquare tempSquare = (MySquare) tempShape;
                    tempSquare.draw();
                }
                else if(tempShape.getClass() == MyCircle.class)
                {
                    MyCircle tempCircle = (MyCircle) tempShape;
                    tempCircle.draw();
                }
                else if(tempShape.getClass() == MyClosedPolygon.class)
                {
                    MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                    tempClosePolygon.draw();
                }
            }
        }
    }
    /*
    * End of draw all
    * */

    /* 
     * Save image
     * */
    @FXML
    void save(ActionEvent event){
        FileChooser savefile = new FileChooser();
        savefile.setTitle("Save File");
        File file = savefile.showSaveDialog(Main.stage);

        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(1500, 1000);
                drawingCanvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }

    /*
     * Open image
     * */
    @FXML
    void open(ActionEvent event){
        FileChooser openFile = new FileChooser();
        openFile.setTitle("Open File");
        File file = openFile.showOpenDialog(Main.stage);

        if (file != null) {
            try {
                InputStream io = new FileInputStream(file);
                Image img = new Image(io);
                drawingCanvas.getGraphicsContext2D().drawImage(img, 0, 0);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }
}
