
import tbge.Area;
import tbge.Door;
import tbge.Game;
import tbge.VerbPhrase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigu3163b
 */
public class BroadCastingRoom extends Area{
    private String laterDescription = "There are some tables in this room, in front of large green walls, with cameras staring at them.";
    private String penDescription = "\nThere seems to be a red pen on one of the tables. ";
    
    public BroadCastingRoom(){
        super("Broadcasting Room");
        this.getDoors().put(Area.Direction.EAST,new Door("Hallway10"));
        this.getInventory().add("Pen");
        
        this.description = "The walls are a lime green that are plastered with light by bright stage lights."
                + "\nTwo desks face three large cameras meant for recording the broadcast for Fridays. On the "
                + "\nother side of the room, there are stairs that lead up to a small balcony, overlooking the "
                + "\nroom. There seems to be a red pen on one of the tables.";
        
        this.getLocalActions().put(new VerbPhrase("description"), (c)->{
            System.out.println(description);
            description = laterDescription;
            if(this.getInventory().contains("Pen")){
                description += penDescription;
            }
            return !Game.GO_TO_NEXT;
        });
        
        
    }
}
