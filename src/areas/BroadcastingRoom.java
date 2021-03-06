package areas;

import core.*;
import items.*;


public class BroadcastingRoom extends Area<NoState> {

    public BroadcastingRoom(final World containingWorld) {
        super(containingWorld);

        this.portals().north(new Portal(false, Hallway11.class))
                .east(new Portal(true, AdamsonsRoom.class));
        this.title("Broadcasting Room").articleThe(true)
                .item(new Door(false, "Northern Door", null, this.portals().north()))
                .item(new Door(true, "Eastern Door", Key.Adamsons.class, this.portals().east()));

        this.description("You eneter a room of depressed possiblitities. "
                + "Great things once happened here, but no longer.");
        this.shortDescription("This was a room used for broadcasting.");
        // Add any necessary doors
        // (Here, the door faces south)
        this.item(new Door(false, "Door to Mr Adamson's room", null, this.portals().east()));


        this.item(new DuckOfDoom());
        this.item(new LaserPointer());
    }

}
