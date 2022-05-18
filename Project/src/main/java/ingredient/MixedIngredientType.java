package main.java.ingredient;

import java.util.ArrayList;
import java.util.List;

public class MixedIngredientType extends IngredientType{

    private List<String> componentNames = new ArrayList<>();
    private String specialName = null;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public MixedIngredientType(String name, long[] standardTemperature,  State standardState) {
        super(name, standardTemperature, standardState);
    }

    /* *********************************************************
     * COMPONENTS NAME
     * *********************************************************/
    @Override
    public String getName(){
        return "iets nieuws"; //TODO -> Dieter =)
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
        return true; //TODO DIETER =)
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
