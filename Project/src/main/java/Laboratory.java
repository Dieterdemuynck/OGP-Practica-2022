package main.java;

import main.java.device.Device;
import main.java.ingredient.AlchemicIngredient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Laboratory {

    private int storeroomCapacity;
    private ArrayList<AlchemicIngredient> storage;
    private ArrayList<Device> devices;
    private RecipeBook recipeBook; //todo is dit nie ook nodig?

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
        this.storeroomCapacity = storeroomCapacity;
    }


    /* *********************************************************
     * STORAGE
     * *********************************************************/
    public ArrayList<AlchemicIngredient> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<AlchemicIngredient> storage) {
        this.storage = storage;
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

    /*
    TODO DIT IS NIET AFGEWERKT EN ZORGT VOOR PROBLEMEN BIJ TESTS ALS NIET IN COMMENTAAR STAAT
    private void store(AlchemicIngredient ingredient) {
        int index = binarySearch(ingredient.getName(), 0, getStorage().size());
        if (getStorage().get(index) == ingredient) {
            ingredient.
        }
        else {
            getStorage().add(index, ingredient);
        }
    }*/



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

    /* *********************************************************
     * DEVICES
     * *********************************************************/
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

    /* *********************************************************
     * RECIPE BOOK
     * todo
     * *********************************************************/
    public void setRecipeBook(RecipeBook recipeBook){
        this.recipeBook = recipeBook;
    }

    public RecipeBook getRecipeBook(){
        return this.recipeBook;
    }

    public void execute(Recipe recipe, int factorIngredients){ // Todo: staat in commentaar, implementatie moet nog gebeuren maar daarvoor moet rest eerst af zijn =(
        // IDEE: elke if-clause schrijven als een aparte functie, want je moet op einde ook nog eens mixen...

        List<AlchemicIngredient> ingredientsRecipe = new LinkedList<>();
        List<AlchemicIngredient> ingredientList = recipe.getIngredientsList();
        List<Operation> operationList = recipe.getOperationsList();
        int indexOperations = 0;
        int indexIngredients = 0;
        while (indexIngredients < ingredientList.size() && indexOperations < operationList.size()){
            Operation operation = operationList.get(indexOperations);
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
            else {break; } // is dit nodig?
            indexOperations +=1;
        }
        // mix nog eens alles volgens mixdinges
        // voeg nieuwe ingredient toe aan storage
        // store(ingredientsRecipe[0]);
    }
}
