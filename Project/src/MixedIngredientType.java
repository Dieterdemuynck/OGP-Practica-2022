import java.util.ArrayList;

public class MixedIngredientType extends IngredientType{

    private ArrayList<String> componentNames = new ArrayList<>();
    private static String specialName;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public MixedIngredientType(String name, int[] standardTemperature) {
        super(name, standardTemperature);
    }

    /* *********************************************************
     * COMPONENTS NAME
     * *********************************************************/
    @Override
    public static String getName(){
        return "iets nieuws"; //TODO -> Dieter =)
    }


    /* *********************************************************
     * SPECIAL NAME
     * *********************************************************/

    public static String getSpecialName(){
        return specialName;
    }

    @Override
    public boolean isMixedIngredient(AlchemicIngredient alchemicIngredient) {
        return true;
    }
}
