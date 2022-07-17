/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;  //singleton pattern

import shapes.MyShape;

/**
 *
 * @author OWNER
 */
public class ClearAction extends MyShape {
    
    private static ClearAction clearAction = null;
    
    private ClearAction(){
    }
    
     public static ClearAction getInstance()
    {
        if(clearAction==null)
            clearAction=new ClearAction();
        return clearAction;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
