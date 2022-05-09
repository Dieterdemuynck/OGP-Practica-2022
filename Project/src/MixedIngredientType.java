import java.util.ArrayList;

public class MixedIngredientType extends IngredientType{

    private ArrayList<String> componentNames = new ArrayList<>();
    private static String specialName = null;

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

    public static String getSpecialName(){
        return specialName;
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
