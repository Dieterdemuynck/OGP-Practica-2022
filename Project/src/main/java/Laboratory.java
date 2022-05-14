package main.java;

import main.java.device.Device;
import main.java.ingredient.IngredientContainer;

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
    // Dimme here: Instead of storing alchemical ingredients, or even just ingredient types, we're storing entire
    // containers? Why... Why containers? Why can we even store the same container multiple times?
    // Are we gonna store an ingredient object 5 times or something and suddenly have 5 times the previous amount out
    // of thin air?
    public void store(IngredientContainer container, int amount) {
        if (storage.get(container) == null) {
            storage.put(container, amount);
        }
        else {
            storage.replace(container, storage.get(container) + amount);
        }
        /* TODO: Who in the goddamn heck
         *  committed a goddamn
         *  syntax error??
         * Added " 0 );" so this doesn't break
         */
        if (storage.get(container) > 0);
    }

}
