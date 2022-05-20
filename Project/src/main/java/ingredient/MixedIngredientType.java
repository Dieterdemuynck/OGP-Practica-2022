package main.java.ingredient;

import main.java.ingredient.exception.IllegalNameException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class specifically for ingredients that are the result of mixing multiple ingredients.
 */
public class MixedIngredientType extends IngredientType{

    /**
     * Variable referencing the ingredients this mixed ingredient is made of.
     */
    private List<String> componentNames = new ArrayList<>();

    /**
     * Variable registering the mixed ingredient's special name, in case it has one.
     */
    private String specialName = null;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/

    /**
     * Initialize a new MixedIngredient with given list of ingredient names, standard temperature and standard state.
     *
     * @param name a List of names of the individual ingredients.
     * @param standardTemperature The standard temperature of this mixed ingredient.
     * @param standardState The standard state of this mixed ingredient.
     */
    public MixedIngredientType(List<String > name, long[] standardTemperature,  State standardState) {
        super( standardTemperature, standardState);
    }

    /* *********************************************************
     * COMPONENTS NAME
     * *********************************************************/

    @Override
    public String getName(){
        return "iets nieuws"; //TODO -> Dieter =)
    }

    public List<String> getComponentNames(){
        return componentNames;
    }


    /* *********************************************************
     * SPECIAL NAME
     * *********************************************************/

    @Override
    public void setSpecialName(String specialName){
        if (! isValidSpecialName(specialName)) {
            throw new IllegalNameException(specialName);
        }
        this.specialName = specialName;
    }

    @Override
    public String getSpecialName(){
        return this.specialName;
    }

    public static boolean isValidSpecialName(String specialName){
        return specialName.matches("([A-Z][a-z']+( [A-Z][a-z']+)+|[A-Z][a-z']{2,})");
    }
    @Override
    public boolean hasSpecialName(){
        return specialName != null;
    }

    @Override
    public boolean isMixedIngredient() {
        return true;
    }
}
