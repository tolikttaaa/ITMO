package moominClasses;

import java.util.ArrayList;

public interface PickUpping {
    boolean pickUp(Item item);

    boolean drop(Item item);

    ArrayList<Item> getItems();
}