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
    private final List<String> componentNames = new ArrayList<>();

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
    public MixedIngredientType(List<String> name, long[] standardTemperature,  State standardState) {
        super(standardTemperature, standardState);
        setComponentNames(name);
    }

    /* *********************************************************
     * COMPONENTS NAME
     * *********************************************************/

    /**
     *
     * @return
     */
    @Override
    public String getName(){
        // Since this is a mixture, there must be at least 2 components
        StringBuilder simpleName = new StringBuilder(getComponentNames().get(0) + " mixed with " + getComponentNames().get(1));

        for (int i = 2; i < getComponentNames().size() -1 ; i++) {
            simpleName.append(", ").append(getComponentNames().get(i));
        }
        if (getComponentNames().size() >= 3) {
            simpleName.append(" and ").append(getComponentNames().get(getComponentNames().size()-1));
        }

        return simpleName.toString();
    }

    /**
     *
     * @return
     */
    public List<String> getComponentNames(){
        return componentNames;
    }

    /**
     *
     * @param names
     */
    private void setComponentNames(List<String> names){
        for (String name : names){

            // This is trivial because all names come from existing alchemical ingredients and so are all valid.
            if( isValidName(name)){
                componentNames.add(name);
            }
        }
    }


    /* *********************************************************
     * SPECIAL NAME
     * *********************************************************/

    /**
     *
     * @param   specialName
     *          The special name
     */
    @Override
    public void setSpecialName(String specialName){
        if (! isValidSpecialName(specialName)) {
            throw new IllegalNameException(specialName);
        }
        this.specialName = specialName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getSpecialName(){
        return this.specialName;
    }

    /**
     *
     * @param specialName
     * @return
     */
    public static boolean isValidSpecialName(String specialName){
        return specialName.matches("([A-Z][a-z']+( [A-Z][a-z']+)+|[A-Z][a-z']{2,})");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasSpecialName(){
        return getSpecialName() != null;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isMixedIngredient() {
        return true;
    }
}
