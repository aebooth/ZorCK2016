package core;

import java.util.ArrayList;
import java.util.List;

import core.World.Direction;

public abstract class Item {
    private String name = "Default item name";
    private String examine;
    private String look;

    private final Usage usage = new Usage();

    private String taste;
    private Item inside;
    // if item is read
    private String read;
    private String smell;
    private String sound;
    private final List<Item> received = new ArrayList<>();
    private final List<Class<? extends Item>> keys = new ArrayList<>();
    private Portal portal;
    private final List<String> synonyms = new ArrayList<>();

    public Item() {}

    public Usage usage() {
        return this.usage;
    }

    public String name() {
        return this.name;
    }

    public Item name(final String name) {
        this.name = name;
        return this;
    }

    public String examine() {
        return this.examine;
    }

    public Item examine(final String description) {
        this.examine = description;
        return this;
    }

    public String look() {
        return this.look;
    }

    public Item look(final String look) {
        this.look = look;
        return this;
    }

    public String taste() {
        return this.taste;
    }

    public Item taste(final String taste) {
        this.taste = taste;
        return this;
    }

    public Item inside() {
        return this.inside;
    }

    public Item inside(final Item inside) {
        this.inside = inside;
        return this;
    }

    public String read() {
        return this.read;
    }

    public Item read(final String read) {
        this.read = read;
        return this;
    }

    public boolean active() {
        return this.usage().active() == Usage.Active.ON;
    }

    public Item active(final boolean active) {
        if (active) {
            this.usage().active(Usage.Active.ON);
        } else {
            this.usage().active(Usage.Active.OFF);
        }
        return this;
    }

    public String smell() {
        return this.smell;
    }

    public Item smell(final String smell) {
        this.smell = smell;
        return this;
    }

    public String sound() {
        return this.sound;
    }

    public Item sound(final String sound) {
        this.sound = sound;
        return this;
    }

    public List<Class<? extends Item>> keys() {
        return this.keys;
    }

    public Item key(final Class<? extends Item> key) {
        this.keys.add(key);
        return this;
    }

    public Portal portal() {
        return this.portal;
    }

    public Item portal(final Portal portal) {
        this.portal = portal;
        return this;
    }

    public List<String> synonyms() {
        return this.synonyms;
    }

    public Item synonym(final String... strs) {
        for (final String str : strs) {
            this.synonyms.add(str);
        }
        return this;
    }

    public boolean hasMatching(final String str) {
        return this.name.equals(str) || this.synonyms.contains(str);
    }

    public void drop(final Area<?> area) {
        if (this.inside() != null) {
            area.item(this.inside());
            this.inside(null);
        }
    }

    public void receive(final Item item) {
        this.received.add(item);
    }


    public boolean hasReceived(final String name) {
        return this.received.contains(name);
    }

    public void synchronizeDoor(final World world, final Area<?> currentArea) {
        final Portal portal = this.portal();
        final Area<?> target = world.getArea(portal.getTarget());
        final Direction direction = currentArea.direction(portal);
        final Direction oppDir;
        switch (direction) {
            case NORTH:
                oppDir = Direction.SOUTH;
                break;
            case EAST:
                oppDir = Direction.WEST;
                break;
            case SOUTH:
                oppDir = Direction.NORTH;
                break;
            case WEST:
                oppDir = Direction.EAST;
                break;
            case NORTHEAST:
                oppDir = Direction.SOUTHWEST;
                break;
            case SOUTHEAST:
                oppDir = Direction.NORTHWEST;
                break;
            case SOUTHWEST:
                oppDir = Direction.NORTHEAST;
                break;
            case NORTHWEST:
                oppDir = Direction.SOUTHEAST;
                break;
            case UP:
                oppDir = Direction.DOWN;
                break;
            case DOWN:
                oppDir = Direction.UP;
                break;
            default:
                oppDir = null;
        }

        final Portal oppPortal = target.portals().getPortal(oppDir);
        final Item oppDoor = oppPortal.getDoor(target);

        if (this.usage().lock() == Usage.Lock.LOCKED) {
            oppPortal.lock();
            if (oppDoor != null) {
                oppDoor.usage().lock(Usage.Lock.LOCKED);
            }
        } else {
            oppPortal.unlock();
            if (oppDoor != null) {
                oppDoor.usage().lock(Usage.Lock.UNLOCKED);
            }
        }
    }

    /**
     * @return whether the command processing is done
     */
    public boolean interact(final Command command, final Context context) {
        return false;
    }

    public static final class Usage {
        private Sit can_sit = Sit.CANNOT_SIT;
        private Visible visible = Visible.VISIBLE;
        private Take take = Take.UNTAKABLE;
        private Food food = Food.UNEDIBLE;
        private Drink drink = Drink.UNDRINKABLE;
        private Open open = Open.UNOPENABLE;
        private Lock lock = Lock.NO_LOCK;
        private Read read = Read.UNREADABLE;
        private Active active = Active.STATIC;
        private Move move = Move.IMMOVABLE;
        private Wear wear = Wear.UNWEARABLE;
        private Stab stab = Stab.UNSTABBABLE;
        private Press press = Press.UNPRESSABLE;
        private Climb climb = Climb.UNCLIMBABLE;
        private Recieve recieve = Recieve.NO_RECIEVE;
        private Breakable breakable = Breakable.UNBREAKABLE;
        private Talk talk = Talk.NO_TALK;
        private Use use = Use.NO_USE;
        private Puttable puttable = Puttable.PUTTABLE;
        private Plungable plungable = Plungable.UNPLUNGABLE;

        public Usage() {}

        public static enum Sit {
            CAN_SIT, CANNOT_SIT
        }

        public static enum Visible {
            VISIBLE, HIDDEN
        }
        public static enum Take {
            UNTAKABLE, TAKABLE, TOO_HEAVY, BOLTED_DOWN
        }
        public static enum Food {
            UNEDIBLE, EDIBLE
        }
        public static enum Drink {
            UNDRINKABLE, DRINKABLE, CLOSED_DRINK, EMPTY
        }
        public static enum Open {
            UNOPENABLE, CLOSED, OPEN
        }
        public static enum Lock {
            NO_LOCK, UNLOCKED, LOCKED
        }
        public static enum Read {
            UNREADABLE, READABLE, ILLEGIBLE
        }
        public static enum Active {
            STATIC, OFF, ON
        }
        public static enum Move {
            IMMOVABLE, MOVABLE
        }
        public static enum Wear {
            UNWEARABLE, WEARABLE
        }
        public static enum Stab {
            UNSTABBABLE, STABABBLE
        }
        public static enum Press {
            UNPRESSABLE, UNPRESSED, PRESSED
        }
        public static enum Climb {
            UNCLIMBABLE, CLIMABLE
        }
        public static enum Recieve {
            NO_RECIEVE, RECIEVE
        }
        public static enum Breakable {
            BREAKABLE, UNBREAKABLE, UNBROKEN, BROKEN
        }
        public static enum Talk {
            NO_TALK, TALK
        }

        public static enum Use {
            NO_USE, USABLE
        }

        public Sit can_sit(){
            return this.can_sit;
        }

        public Usage can_sit(final Sit o){
            this.can_sit = o;
            return this;
        }

        public Visible visible() {
            return this.visible;
        }

        public static enum Puttable{
            PUTTABLE,UNPUTTABLE
        }

        public static enum Plungable {
            PLUNGABLE, UNPLUNGABLE
        }

        public Usage visible(final Visible o) {
            this.visible = Visible.VISIBLE;
            return this;
        }

        public Take take() {
            return this.take;
        }

        public Usage take(final Take o) {
            this.take = o;
            return this;
        }

        public Food food() {
            return this.food;
        }

        public Usage food(final Food o) {
            this.food = o;
            return this;
        }

        public Drink drink() {
            return this.drink;
        }

        public Usage drink(final Drink o) {
            this.drink = o;
            return this;
        }

        public Open open() {
            return this.open;
        }

        public Usage open(final Open o) {
            this.open = o;
            return this;
        }

        public Lock lock() {
            return this.lock;
        }

        public Usage lock(final Lock o) {
            this.lock = o;
            return this;
        }

        public Read read() {
            return this.read;
        }

        public Usage read(final Read o) {
            this.read = o;
            return this;
        }

        public Active active() {
            return this.active;
        }

        public Usage active(final Active o) {
            this.active = o;
            return this;
        }

        public Move move() {
            return this.move;
        }

        public Usage move(final Move o) {
            this.move = o;
            return this;
        }

        public Wear wear() {
            return this.wear;
        }

        public Usage wear(final Wear o) {
            this.wear = o;
            return this;
        }

        public Stab stab() {
            return this.stab;
        }

        public Usage stab(final Stab o) {
            this.stab = o;
            return this;
        }

        public Press press() {
            return this.press;
        }

        public Usage press(final Press o) {
            this.press = o;
            return this;
        }

        public Climb climb() {
            return this.climb;
        }

        public Usage climb(final Climb o) {
            this.climb = o;
            return this;
        }

        public Recieve recieve() {
            return this.recieve;
        }

        public Usage recieve(final Recieve o) {
            this.recieve = o;
            return this;
        }

        public Breakable breakable() {
            return this.breakable;
        }

        public Usage breakable(final Breakable o) {
            this.breakable = o;
            return this;
        }

        public Talk talk() {
            return this.talk;
        }

        public Usage talk(final Talk o) {
            this.talk = o;
            return this;
        }

        public Use use() {
            return this.use;
        }

        public Usage use(final Use o) {
            this.use = o;
            return this;
        }

        public Puttable puttable(){
            return this.puttable;
        }

        public Usage puttable(final Puttable o){
            this.puttable = o;
            return this;
        }

        public Plungable plungable() {
            return this.plungable;
        }

        public Usage plungable(final Plungable o) {
            this.plungable = o;
            return this;
        }
    }

    // java: i cry everytim
    public static final Usage.Sit CAN_SIT = Usage.Sit.CAN_SIT;
    public static final Usage.Sit CANNOT_SIT = Usage.Sit.CANNOT_SIT;
    public static final Usage.Visible VISIBLE = Usage.Visible.VISIBLE;
    public static final Usage.Visible HIDDEN = Usage.Visible.HIDDEN;
    public static final Usage.Take UNTAKABLE = Usage.Take.UNTAKABLE;
    public static final Usage.Take TAKABLE = Usage.Take.TAKABLE;
    public static final Usage.Take TOO_HEAVY = Usage.Take.TOO_HEAVY;
    public static final Usage.Take BOLTED_DOWN = Usage.Take.BOLTED_DOWN;
    public static final Usage.Food UNEDIBLE = Usage.Food.UNEDIBLE;
    public static final Usage.Food EDIBLE = Usage.Food.EDIBLE;
    public static final Usage.Drink UNDRINKABLE = Usage.Drink.UNDRINKABLE;
    public static final Usage.Drink DRINKABLE = Usage.Drink.DRINKABLE;
    public static final Usage.Drink CLOSED_DRINK = Usage.Drink.CLOSED_DRINK;
    public static final Usage.Drink EMPTY = Usage.Drink.EMPTY;
    public static final Usage.Open UNOPENABLE = Usage.Open.UNOPENABLE;
    public static final Usage.Open CLOSED = Usage.Open.CLOSED;
    public static final Usage.Open OPEN = Usage.Open.OPEN;
    public static final Usage.Lock NO_LOCK = Usage.Lock.NO_LOCK;
    public static final Usage.Lock UNLOCKED = Usage.Lock.UNLOCKED;
    public static final Usage.Lock LOCKED = Usage.Lock.LOCKED;
    public static final Usage.Read UNREADABLE = Usage.Read.UNREADABLE;
    public static final Usage.Read READABLE = Usage.Read.READABLE;
    public static final Usage.Read ILLEGIBLE = Usage.Read.ILLEGIBLE;
    public static final Usage.Active STATIC = Usage.Active.STATIC;
    public static final Usage.Active OFF = Usage.Active.OFF;
    public static final Usage.Active ON = Usage.Active.ON;
    public static final Usage.Move IMMOVABLE = Usage.Move.IMMOVABLE;
    public static final Usage.Move MOVABLE = Usage.Move.MOVABLE;
    public static final Usage.Wear UNWEARABLE = Usage.Wear.UNWEARABLE;
    public static final Usage.Wear WEARABLE = Usage.Wear.WEARABLE;
    public static final Usage.Stab UNSTABBABLE = Usage.Stab.UNSTABBABLE;
    public static final Usage.Stab STABABBLE = Usage.Stab.STABABBLE;
    public static final Usage.Press UNPRESSABLE = Usage.Press.UNPRESSABLE;
    public static final Usage.Press UNPRESSED = Usage.Press.UNPRESSED;
    public static final Usage.Press PRESSED = Usage.Press.PRESSED;
    public static final Usage.Climb UNCLIMBABLE = Usage.Climb.UNCLIMBABLE;
    public static final Usage.Climb CLIMABLE = Usage.Climb.CLIMABLE;
    public static final Usage.Recieve NO_RECIEVE = Usage.Recieve.NO_RECIEVE;
    public static final Usage.Recieve RECIEVE = Usage.Recieve.RECIEVE;
    public static final Usage.Breakable BREAKABLE = Usage.Breakable.BREAKABLE;
    public static final Usage.Breakable UNBREAKABLE = Usage.Breakable.UNBREAKABLE;
    public static final Usage.Breakable UNBROKEN = Usage.Breakable.UNBROKEN;
    public static final Usage.Breakable BROKEN = Usage.Breakable.BROKEN;
    public static final Usage.Talk NO_TALK = Usage.Talk.NO_TALK;
    public static final Usage.Talk TALK = Usage.Talk.TALK;
    public static final Usage.Use USABLE = Usage.Use.USABLE;
    public static final Usage.Use NO_USE = Usage.Use.NO_USE;
    public static final Usage.Puttable PUTTABLE = Usage.Puttable.PUTTABLE;
    public static final Usage.Puttable UNPUTTABLE = Usage.Puttable.UNPUTTABLE;
    public static final Usage.Plungable PLUNGABLE = Usage.Plungable.PLUNGABLE;
    public static final Usage.Plungable UNPLUNGABLE = Usage.Plungable.UNPLUNGABLE;
}
