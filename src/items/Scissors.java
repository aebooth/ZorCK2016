package items;

import core.*;

public class Scissors extends Item {

    public Scissors() {
        super();
        this.usage().take(Item.TAKABLE).read(Item.READABLE);
        this.name("The Scissors")
                .look("A pair of extraordinarily sharp scissors lie here, as if they're waiting for something.")
                .examine("A pair of extraordinarily sharp scissors."
                        + " Just looking at them sends a chill down your spine.")
                .synonym("the scissors", "scissors", "scisors", "scissor")
                .read("You don't want to get your eyes that close to see if anything's there.")
                .sound("You move them and it sounds like the very molecules of the air are being split.")
                .smell("They smell of blood and cold steel.");
    }
}
