Step By Step how to make a Text-Based Game Engine (tbge) game:

Add the tbge.jar file as a library to your project. All of the classes in core
will be available after you do under the package 'tbge'.

Make a world by sub-classing "World". You should then add all of your areas in a
static block that accesses the "areas" instance variable, and "puts" them into a
map of area ids and areas. Finally, set the constructor to take the id of the
area you want your game to start in.(See MyWorld for a good example).

Make dictionary class (Dictionary is a good name) that implements the Interpretable
interface. Your dictionary class should basically have lists of all the relevant
types of words categorized so that the parser will know what to look for. It should
also have a map of synonyms that "mapsTo()" refers to.
(See Dictionary for a good example).

Make a subclass of Game. In its constructor, pass your newly defined world and
dictionary objects from the steps above to the superclass constructor. Aside
from that, you can initialize any extra variables you may want your game to have
(points?). You should override the superclass' captureInput() method with you own
that handles global level commands (i.e. "get inventory", "quit"). Finally, your game
class should have a main method where you construct an instance of your game and
start it with .start().

The rest is all based on areas. In areas, you can define doors to other areas
(thus establishing the connections in your map), capture input with captureInput()
in order to do anything you might need to do before any other local action is run.