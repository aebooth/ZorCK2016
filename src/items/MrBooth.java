package items;

import core.*;
import verbs.*;

import java.util.Random;

public class MrBooth extends Item {
    public static enum State {
        INITIAL, GAVE_INSTRUCTIONS, UNCONSCIOUS, FALLEN
    }

    private State state = State.INITIAL;

    public MrBooth() {
        super();
        this.name("Mr. Booth")
                .look("Mr. Booth is sitting in his chair in front of his computer.")
                .examine("He is in a Robotics t-shirt and jean.")
                .synonym("mr. booth", "mr booth", "booth", "boof")
                .smell("He smells like he showered this morning")
                .taste("Tastes like the early 2000s.");
    }

    @Override
    public boolean interact(final Command command, final Context context) {
        final Verb verb = command.getVerb();
        switch (this.state) {
            case INITIAL:
                if (verb.getClass().equals(Talk.class)) {
                    System.out.println("Yo, wassup homeslice. If you want me to grade, I need five things:");
                    System.out.println("\ta red pen,");
                    System.out.println("\ta calculator,");
                    System.out.println("\tmy laptop,");
                    System.out.println("\tand your assignment.");
                    this.changeState(State.GAVE_INSTRUCTIONS);
                    return true;
                }
                break;
            case GAVE_INSTRUCTIONS:
                if (verb.getClass().equals(Talk.class)) {
                    final Random rand = new Random();
                    final int n = rand.nextInt(3);
                    if (n == 0) {
                        System.out.println("Waazzzaaaaap?");
                    } else if (n == 1) {
                        System.out.println("What's shizzy in the hizzy, dawg?!");
                    } else {
                        System.out.println("What is the up?");
                    }
                    return true;
                }
                // TODO Remove when we have an actual path to do this
                if (verb.getClass().equals(Poke.class)) {
                    System.out.println("The mighty Booth is felled with a single poke!");
                    this.changeState(State.UNCONSCIOUS);
                    return true;
                }
                if (verb.getClass().equals(FlipOff.class)){
                    System.out.println("Booth is so appalled by your rude gesture he has a heart attack and faints!");
                    this.changeState(State.UNCONSCIOUS);
                    return true;
                }
                break;
            case UNCONSCIOUS:
                break;
            case FALLEN:
                break;
            default:
                System.out.println("Mr. Booth eyes you suspiciously.");
                break;
        }
        return false;
    }

    public void changeState(final State newState) {
        final State oldState = this.state;
        this.state = newState;
        switch (oldState) {
            case INITIAL:
                switch (newState) {
                    case UNCONSCIOUS:
                        this.name("Mr. Booth (unconscious)")
                                .look("Mr. Booth is lying on the floor.")
                                .examine("He looks rather peaceful when unconscious.");
                        this.usage().take(Item.TAKABLE);
                        break;
                    default:
                        MrBooth.stateChangeFailure(oldState, newState);
                        break;
                }
                break;
            case UNCONSCIOUS:
                switch (newState) {
                    case FALLEN:
                        this.name("Mr. Booth (fallen)").look("Here lies Mr. Booth.")
                                .examine("He looks more uncomfortable than peaceful.");
                        this.usage().take(Item.TAKABLE);
                        break;
                    default:
                        MrBooth.stateChangeFailure(oldState, newState);
                        break;
                }
                break;
            default:
                MrBooth.stateChangeFailure(oldState, newState);
                break;
        }
    }

    public static void stateChangeFailure(final State oldState, final State newState) {
        System.out.println("Mr. Booth glows with a heavenly light."
                + " Angellic voices in the distance say the words " + oldState.toString() + " and "
                + newState.toString() + ".");
    }
}
