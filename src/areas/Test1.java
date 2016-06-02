/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package areas;
import core.*;
import items.*;

/**
 *
 * @author pedro
 */
public class Test1 extends Area{

       public Test1(World containingWorld){
            super(containingWorld);
            setPortal(1, new Portal(false, "Test2"));
            setPortal(2, new Portal(true, "Test4"));
            setTitle("Test Area 1");
            setInitialDescription("This is the first test area, there is path to "
                    + "the east and a locked door to the south. There is dark "
                    + "and white chocolate on the ground");
            setDescription("This is the first test area");   
            addItem(new Door(true,"Southern Door", new Key(),getPortal(2)));
            addItem(new DarkChocolate());
            addItem(new WhiteChocolate());
            setState("First",true);
       }
       
    @Override
    public void interact(Command command, Context context){
        Item noun;
        noun = command.getNoun();
        //if you don't have any special interactions, just put this:
        if(command.getNoun() !=  null)
            noun.interact(command,context);
        if(!context.getSkipGeneral())
            command.getVerb().run(command, context);
    }
       
}
