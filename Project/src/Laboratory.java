import java.util.ArrayList;
import java.util.HashMap;

public class Laboratory {

    private int storeroomCapacity;
    private HashMap<IngredientContainer, Integer> storage;
    private ArrayList<Device> devices;

    public int getStoreroomCapacity() {
        return storeroomCapacity;
    }

    public void setStoreroomCapacity(int storeroomCapacity) {
        this.storeroomCapacity = storeroomCapacity;
    }

    public HashMap<IngredientContainer, Integer> getStorage() {
        return storage;
    }

    public void setStorage(HashMap<IngredientContainer, Integer> storage) {
        this.storage = storage;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        for (int i = 0; i == devices.size(); i++) {
            if (devices.get(i) == device) {
                break;
            }
        }
        devices.add(device);
    }

    //Hier moet er nog rekening worden gehouden met het feit dat verschillende containers verschillend zijn
    public void store(IngredientContainer container, int amount) {
        if (storage.get(container) == null) {
            storage.put(container, amount);
        }
        else {
            storage.replace(container, storage.get(container) + amount);
        }
        if (storage.get(container) >
    }

}
