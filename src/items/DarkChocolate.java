package items;

import core.*;

public class DarkChocolate extends Item{

    public DarkChocolate(){
        super();
        this.usage().take(Item.TAKABLE).food(Item.EDIBLE);
        this.name("Dark Chocolate")
            .examine("Its looks dark and tasty")
            .synonym("dark chocolate", "chocolate", "chocolate bar")
            .taste("It tastes like heaven");
    }
}
