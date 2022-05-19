package main.java;

import main.java.device.Device;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;

import java.util.*;

public class Laboratory {

    private int storeroomCapacity;
    private Map<String, AlchemicIngredient> storage = new HashMap<>();
    private Map<Device.DeviceType, Device> devices = new HashMap<>();  // Downside: allows null keys

    /* *********************************************************\
     * CONSTRUCTOR
    \* *********************************************************/
    public Laboratory(int storeroomCapacity){
        setStoreroomCapacity(storeroomCapacity);
    }

    /* *********************************************************\
     * STOREROOM CAPACITY
    \* *********************************************************/

    public int getStoreroomCapacity() {
        return storeroomCapacity;
    }

    public void setStoreroomCapacity(int storeroomCapacity) {
        if (storeroomCapacity < 0)
            throw new IllegalArgumentException(
                    "laboratory storeroom capacity (" + storeroomCapacity + ") must be non negative.");
        // TODO: what if current ingredient storage exceeds newly set capacity?
        //  Should we make storeroom capacity final?
        this.storeroomCapacity = storeroomCapacity;
    }


    /* *********************************************************\
     * STORAGE
    \* *********************************************************/
    public Map<String, AlchemicIngredient> getStorage() {
        // TODO: should we really allow this mutable field's getter to be public?
        return storage;
    }

    public void setStorage(Map<String, AlchemicIngredient> storage) {
        // TODO: should we really allow a set method?
        this.storage = storage;
    }

    /**
     *
     * @pre     container is not null
     * @pre     ingredient inside container is not null
     * @param container
     */
    public void store(IngredientContainer container) {
        store(container.getContent());
    }

    /**
     *
     * @pre     ingredient is not null
     * @param ingredient
     */
    private void store(AlchemicIngredient ingredient) {
        if (getStorage().get(ingredient.getName()) == null)
            getStorage().put(ingredient.getName(), ingredient);
        else {
            // TODO: Mix
        }
    }



    // TODO: either delete or reuse this binary search
//    private int binarySearch(String target, int first, int last) {
//        int mid = (first + last) / 2;
//        if (first > last) {
//            return first;
//        }
//        if (getStorage().size() == 0) {
//            return 0;
//        }
//        if (target.equals(getStorage().get(mid).getName())) {
//            return mid;
//        } else {
//            if (getStorage().get(mid).getName().compareTo(target) < 0) {
//                return binarySearch(target, mid+1,last);
//            }
//            else{
//                return binarySearch(target,first,mid-1);
//            }
//        }
//    }

    /* *********************************************************\
     * DEVICES
    \* *********************************************************/

    private Map<Device.DeviceType, Device> getDevices() {
        return devices;
    }

    public void setDevices(Map<Device.DeviceType, Device> devices) {
        // Do we even need this function?
        this.devices = devices;
    }

    /**
     * 
     * @param device
     */
    public void addDevice(Device device) {
        if (device.getLaboratory() != null) {
            throw new IllegalArgumentException(
                    "Device to be added to laboratory may not already be linked to a Laboratory.");
        }
        if (devices.get(device.getDeviceType()) == null) {
            getDevices().put(device.getDeviceType(), device);
        }
    }

    /* *********************************************************\
     * DEVICE METHODS
     * TODO: Add method bodies + Additional arguments.
     *  Add "storeInDevice" methods, perhaps?
     *  Perhaps a better idea: no arguments -> device.activate ; some arguments -> enter ingredient and activate
    \* *********************************************************/

    public void mix() {

    }

    public void cool() {

    }

    public void heat() {

    }

    public void transmogrify() {

    }

    /* *********************************************************\
     * RECIPE
    \* *********************************************************/

    public void execute(Recipe recipe, int factorIngredients){ // Todo: staat in commentaar, implementatie moet nog gebeuren maar daarvoor moet rest eerst af zijn =(
        // IDEE: elke if-clause schrijven als een aparte functie, want je moet op einde ook nog eens mixen...
        // Dimme here: even better idea: combine that idea with the use switch statements instead of 100 if-clauses.

        List<AlchemicIngredient> ingredientsRecipe = new LinkedList<>();
        List<AlchemicIngredient> ingredientList = recipe.getIngredientsList();
        List<Operation> operationList = recipe.getOperationsList();
        int indexOperations = 0;
        int indexIngredients = 0;
        while (indexIngredients < ingredientList.size() && indexOperations < operationList.size()){
            Operation operation = operationList.get(indexOperations);

            // TODO: use switch instead. Remember to add "break" to each end!
            //  https://www.w3schools.com/java/java_switch.asp
            if (operation == Operation.Add){
                // Zoeken naar ingr = ingredientList.get(indexIngredients).getName()
                // Controleren dat ingr.getCapacity() => ingredientList.get(indexIngredients).getCapacity() * factorIngredients
                // Toevoegen aan ingredientsRecipe en verwijderen uit storage
                // indexIngredients += 1

                // niet aanwezig -> break
            }
            else if (operation == Operation.Heat){
                // controleren ofdat er een oven en coolbox aanwezig is
                // In oven ingr = ingredientsRecipe[-1] opwarmen met 50/0.95 (want ge moet 50 eenheden opwarmen)
                // In coolbox ingr afkoelen tot die effectief 50 is opgewarmt
                // ingredientsRecipe[-1] overschrijven met ingr

                // oven en coolbox niet aanwezig -> break
            }
            else if (operation == Operation.Cool){
                // controleren ofdat er een coolbox aanwezig is
                // In coolbox ingr afkoelen met 50
                // ingredientsRecipe[-1] overschrijven met ingr

                // coolbox niet aanwezig -> break
            }
            else if (operation == Operation.Mix){ //kweet dat if hier nie echt nodig is ma dan ben je zeker...
                // controleren ofdat een kettle aanwezig is
                // In kettle alle ingredienten in ingredientsRecipe mixen
                // ingredientsRecipe = null
                // ingredientsRecipe[0] is ingr in kettle

                // kettle niet aanwezig -> break
            }
            else {break; } // TODO: not needed, pls delete :)
            indexOperations +=1;
        }
        // mix nog eens alles volgens mixdinges
        // voeg nieuwe ingredient toe aan storage
        // store(ingredientsRecipe[0]);
    }
}
