package areas;

import core.*;

public class CompSciRoom extends Area {

    public CompSciRoom(final World containingWorld) {
        super(containingWorld);
        this.portals().east(new Portal(false, Hallway05.class))
                .south(new Portal(true, WatchmansRoom.class));
        this.title("CompSci Room")
                //This is not a logical sentence structure
                .description("This is the CompSci room, Room 317."
                        + " On the wall, writen in blood, is 'WHY WOULDN'T HE JUST GRADE OUR ASSINMENTS'."
                        + " There is a door to the east that leads to Hallway05 and"
                        + " a locked door to the north that leads to Mrs. Wachtman's room.")
                .shortDescription("This is the CompSci Room, Room 317.").articleThe(true);
    }

}
