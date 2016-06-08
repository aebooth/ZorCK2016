package items;
import core.*;
import java.util.Random;

public class MrBooth extends Item{
    public MrBooth(){
        super();
        this.name("Mr. Booth")
            .look("Mr. Booth is sitting in his chair in front of his computer.")
            .examine("He is in a Robotics t-shirt and jean.")
            .synonym("mr.booth", "mr booth", "booth", "boof", "booth")
            .smell("He smells like he showered this morning");
    }

    @Override
    public boolean interact(final Command command, final Context context) {
        if(command.getVerb().getTitle().equals("talk")){
            final Random rand = new Random();
            final int n = rand.nextInt(3);
            if(n==0){
                System.out.println("You'll never make me grade, fool!");
            }
            else if(n==1){
                System.out.println("Leave me alone!");
            }
            else{
                System.out.println("Good morning");
            }
            return true;
        }
        return false;
    }
}
