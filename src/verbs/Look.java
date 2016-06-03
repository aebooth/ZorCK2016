package verbs;

import core.*;

public class Look extends Verb {

    public Look() {
        super("look",
                new String[]{"l"},
                new boolean[]{true, false, true});
    }

    public void run(Command command, Context construct) {
        command.getDirection();
        command.getNoun();

        Player player = construct.getPlayer();
        construct.getWorld();

        String[] desc;
        if (player.getCurrentArea().getItems().size() == 0) {
            desc = new String[1];
            desc[0] = player.getCurrentArea().getDescription();
        } else {
            desc = new String[player.getCurrentArea().getItems().size() + 3];
            desc[0] = player.getCurrentArea().getDescription();
            desc[1] = "";
            desc[2] = "This Area contains:";
            for (int i = 0; i < player.getCurrentArea().getItems().size(); i++) {
                desc[3 + i] = player.getCurrentArea().getItems().get(i).getName();
            }
        }
        if (player.getCurrentArea().getDark() != true) {
            for (String item : desc) {
                System.out.println(item);
            }
        } else if (player.getItem("Lantern") != null) {
            if (player.getItem("Lantern").getActive()) {
                for (String item : desc) {
                    System.out.println(item);
                }
            } else {
                System.out.println("It's too dark to see!");
            }
        } else {
            System.out.println("It's too dark to see!");
        }
    }
}
