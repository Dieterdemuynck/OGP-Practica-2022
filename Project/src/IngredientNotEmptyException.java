import be.kuleuven.cs.som.annotate.*;


//TODO: iemand die dit kan nakijken of dit goed genoeg is? Want ken nie zo veel van exceptions =)
/**
 *
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
class IngredientNotEmtpyException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 1L;


    private final AlchemicIngredient ingredientIn;
    private final AlchemicIngredient ingredientNew;

    public IngredientNotEmtpyException(AlchemicIngredient ingredientIn, AlchemicIngredient ingredientNew) {
        this.ingredientIn = ingredientIn;
        this.ingredientNew = ingredientNew;
    }

    /**
     *
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredientIn(){
        return this.ingredientIn;
    }

    /**
     *
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredientNew(){
        return this.ingredientNew;
    }

}
