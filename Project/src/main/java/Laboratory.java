package main.java;

import be.kuleuven.cs.som.annotate.Model;
import main.java.device.Device;
import main.java.device.exception.DeviceNotEmptyException;
import main.java.exception.DeviceNotPresentException;
import main.java.ingredient.*;
import main.java.ingredient.exception.IncompatibleUnitException;
import main.java.ingredient.exception.IngredientNotPresentException;
import main.java.ingredient.exception.NotEnoughIngredientException;

import java.util.*;

public class Laboratory {

    private int storeroomCapacity;
    private Map<String, AlchemicIngredient> storage = new HashMap<>();
    private Map<Device.DeviceType, Device> devices = new HashMap<>();  // Downside: allows null keys
    private Map<String, String> specialToSimple = new HashMap<>();

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/
    public Laboratory(int storeroomCapacity){
        setStoreroomCapacity(storeroomCapacity);
    }

    /* *********************************************************
     * STOREROOM CAPACITY
     * *********************************************************/

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


    /* *********************************************************
     * STORAGE
     * *********************************************************/
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
        if (getStorage().get(ingredient.getName()) == null) {
            getStorage().put(ingredient.getName(), ingredient);
            if (ingredient.hasSpecialName()) {
                specialToSimple.put(ingredient.getSpecialName(), ingredient.getName());
            }
        }
        else {
            // TODO: Mix
        }
    }

    /**
     * Retrieves an ingredient from the laboratory's storage, and stores the rest of the ingredient back into storage.
     *
     * @post    After the ingredient is retrieved, the difference will be stored back into storage.
     *          | new.getStorage().get(name) == bulk.copyAllValsExcept(amount, unit)
     * @param name
     *        The name of the ingredient we want to retrieve.
     * @param amount
     *        The amount of ingredient, represented in the given unit, we want to retrieve.
     * @param unit
     *        The unit representation of the amount of the ingredient we want to retrieve.
     * @return  An AlchemicIngredient instance with the given quantity-values and name.
     *          | result == getStorage().get(name).copyAllValsExcept(amount, unit);
     * @throws IngredientNotPresentException
     *         No ingredient with given name is present.
     *         | getStorage().get(name) == null
     * @throws IncompatibleUnitException
     *         The state of the ingredient with the given name has no such Unit.
     *         | !getStorage().get(name).getState().hasUnit(unit)
     * @throws NotEnoughIngredientException
     *         The amount of ingredient requested is larger than the amount stored.
     *         | AlchemicIngredient ingredient = getStorage().get(name);
     *         | ingredient.getState().compareInSameState(ingredient.getAmount(), ingredient.getUnit(), amount, unit)
     *         |    < 0
     */
    @Model
    private AlchemicIngredient retrieveIngredient(String name, int amount, Unit unit) throws IngredientNotPresentException,
            IncompatibleUnitException, NotEnoughIngredientException {
        // This is rather inefficient, as we create 2 new alchemicIngredients instead of just changing a value or 2
        // Better than nothing :)
        AlchemicIngredient bulk = getStoredIngredient(name);

        // Possible exceptions:
        if (bulk == null) {
            // The ingredient must be present in order to take some out
            throw new IngredientNotPresentException(name, this);
        }
        if (!bulk.getState().hasUnit(unit)) {
            throw new IncompatibleUnitException(bulk, unit);
        }
        if (bulk.getState().compareInSameState(bulk.getAmount(), bulk.getUnit(), amount, unit) < 0) {
            throw new NotEnoughIngredientException(bulk, amount, unit, this);
        }

        // Calculate the difference between the two units
        Quantity quantity = bulk.getState().subtract(bulk.getAmount(), bulk.getUnit(), amount, unit);

        // Store the rest of the ingredient back into the laboratory's storage
        AlchemicIngredient newBulk = bulk.copyAllValsExcept(quantity.getAmount(), quantity.getUnit());
        getStorage().put(newBulk.getName(), newBulk);

        // Return the retrieved ingredient, which is a copy of the previous ingredient with a lower quantity
        return bulk.copyAllValsExcept(amount, unit);
    }

    /**
     * TODO: specification (reference retrieveIngredient using @effect) OOK moet het ingredient niet ook uit het laboratory gehaald worden?
     * @param name of the ingredient you want to retrieve
     * @param amount of a certain unit of the ingredient you want to retrieve
     * @param unit the unit of the ingredient you want to retrieve
     * @return a new container, containing the given amount of the ingredient.
     */
    public IngredientContainer retrieve(String name, int amount, Unit unit){
        // This might do double calculation for "find largest fit" but whatever, I guess?
        return new IngredientContainer(retrieveIngredient(name, amount, unit));
    }
    
    private AlchemicIngredient getStoredIngredient(String name) {
        return getStorage().get(name);
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

    /* *********************************************************
     * DEVICES
     * *********************************************************/

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

    /* *********************************************************
     * DEVICE METHODS
     * TODO: Add method bodies + Additional arguments.
     * *********************************************************/

    /**
     * QoL Device method, returns the device that is inside the laboratory
     * @param deviceType
     * @return
     * @throws DeviceNotPresentException
     */
    private Device getDevice(Device.DeviceType deviceType)
            throws DeviceNotPresentException {
        if (getDevices().get(deviceType) == null)
            throw new DeviceNotPresentException(this, deviceType);
        return getDevices().get(deviceType);
    }

    /**
     * adds ingredient to a device in the laboratory
     * @param deviceType
     * @param container
     * @throws DeviceNotEmptyException
     * @throws DeviceNotPresentException
     */
    private void addToDevice(Device.DeviceType deviceType, IngredientContainer container)
            throws DeviceNotEmptyException, DeviceNotPresentException {
        getDevice(deviceType).insert(container);
    }

    /**
     * activates a device in the laboratory
     * @param deviceType
     * @throws DeviceNotPresentException
     */
    private void activateDevice(Device.DeviceType deviceType)
            throws DeviceNotPresentException {
        getDevice(deviceType).activate();
    }

    /**
     * Returns an ingredient container which either fits the ingredient in the device or, if no container fits,
     * returns the largest possible container with the ingredient.
     * @param deviceType
     * @return
     * @throws DeviceNotPresentException
     */
    private IngredientContainer retrieveFromDevice(Device.DeviceType deviceType)
            throws DeviceNotPresentException {
        return getDevice(deviceType).retrieve(getDevice(deviceType).getLargestFittingContainerForContents());
    }

    /**
     * Allows a client to use a device in the laboratory with a given container of ingredient.
     * @param deviceType
     * @param input
     * @return
     * @throws DeviceNotPresentException
     */
    public IngredientContainer useOnDevice(Device.DeviceType deviceType, IngredientContainer input)
            throws DeviceNotPresentException {
        addToDevice(deviceType, input);
        activateDevice(deviceType);
        return retrieveFromDevice(deviceType);
    }

    /* *********************************************************
     * RECIPE
     * *********************************************************/

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
