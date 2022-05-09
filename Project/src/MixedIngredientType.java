import java.util.ArrayList;

public class MixedIngredientType extends IngredientType{

    private ArrayList<String> componentNames = new ArrayList<>();
    private static String specialName;


    public static String getSpecialName(){
        return specialName;
    }

    @Override
    public boolean isMixedIngredient(AlchemicIngredient alchemicIngredient) {
        return true;
    }
}
