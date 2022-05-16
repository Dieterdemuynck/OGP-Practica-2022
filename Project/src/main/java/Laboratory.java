package main.java;

import main.java.device.Device;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class Laboratory {

    private int storeroomCapacity;
    private ArrayList<AlchemicIngredient> storage;
    private ArrayList<Device> devices;

    public int getStoreroomCapacity() {
        return storeroomCapacity;
    }

    public void setStoreroomCapacity(int storeroomCapacity) {
        this.storeroomCapacity = storeroomCapacity;
    }

    public ArrayList<AlchemicIngredient> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<AlchemicIngredient> storage) {
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

   // public int getIndexofIngredient(AlchemicIngredient ingredient) {
        //int index = storage.size()/2;
        //storage.get(index)

    //}

    //Hier moet er nog rekening worden gehouden met het feit dat verschillende containers verschillend zijn
    // Dimme here: Instead of storing alchemical ingredients, or even just ingredient types, we're storing entire
    // containers? Why... Why containers? Why can we even store the same container multiple times?
    // Are we gonna store an ingredient object 5 times or something and suddenly have 5 times the previous amount out
    // of thin air?

    private void store(AlchemicIngredient ingredient) {
        int index = binarySearch(ingredient.getName(), 0, getStorage().size());
        getStorage().add(index, ingredient);
    }

    

    private int binarySearch(String target, int first, int last) {
        int mid = (first + last) / 2;
        if (first > last) {
            return first;
        }
        if (getStorage().size() == 0) {
            return 0;
        }
        if (target.equals(getStorage().get(mid).getName())) {
            return mid;
        } else {
            if (getStorage().get(mid).getName().compareTo(target) < 0) {
                return binarySearch(target, mid+1,last);
            }
            else{
                return binarySearch(target,first,mid-1);
            }
        }
    }
}
