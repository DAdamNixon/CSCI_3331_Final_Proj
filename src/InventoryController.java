import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class InventoryController {
    
    public HashMap<Integer, String> inventory;

    public InventoryController(String fileName) {
    inventory = new HashMap<Integer, String>();
    try{
        File inventory = new File(fileName);
    } catch (Exception e) {
        e.printStackTrace();
    }
    

    }

    
    
}
