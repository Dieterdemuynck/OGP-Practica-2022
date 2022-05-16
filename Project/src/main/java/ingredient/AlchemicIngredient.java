package main.java.ingredient;

public class AlchemicIngredient {

    private final IngredientType ingredientType;
    private State state;
    private final int quantity;
    private final Unit unit;
    private long temperature;
    public static final int MAX_TEMPERATURE = 10000;


    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/

    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature, String name, State standardState){
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.quantity = quantity;
        this.unit = unit; // TODO Controle ofdat state en unit overeen komen DIETER =)
        setTemperature(standardTemperature);
        changeState(standardState);
    }

    // voor transmogrifier -> standardState != current state
    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature, String name, State standardState, State currentState){
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.quantity = quantity;
        this.unit = unit; // TODO Controle ofdat state en unit overeen komen DIETER =)
        setTemperature(standardTemperature);
        changeState(currentState);
    }

    // voor transmogrifier -> standardState != currentState EN standardTemperature != currentTemperature
    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature, String name, State standardState, State currentState, long[] currentTemperature){
        /* TODO: rather than create a new ingredient type, shouldn't we pass an already existing one?
         * Currently, for the transmogrifier, we need to create a new ingredient with a different state from its
         * standard state. This will be wonky or messy if we pass two states instead.
         * Please fix, so I can work on the transmogrifier. - パリピディーター
         */
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.quantity = quantity;
        this.unit = unit; // TODO Controle ofdat state en unit overeen komen DIETER =)
        setTemperature(currentTemperature);
        changeState(currentState);
    }

    // Default unit: spoon
    public AlchemicIngredient(int quantity, long[] standardTemperature, String name, State standardState) {
        this(quantity, Unit.Spoon, standardTemperature, name, standardState);
    }

    public AlchemicIngredient(int quantity, Unit unit){
        this(quantity, unit, new long[] {0, 20}, "Water", State.Liquid);
    }

    // Default unit: spoon
    public AlchemicIngredient(int quantity) {
        this(quantity, Unit.Spoon);
    }

    /* *********************************************************
     * INGREDIENT TYPE
     * *********************************************************/

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    /* ***************************
     * INGREDIENT TYPE - NAME
     * ***************************/

    public String getName(){
        return ingredientType.getName();
    }

    public String getFullName(){
        String pre = getPreName();
        String post = getPostName();
        if (!ingredientType.isMixedIngredient()){
            return pre + ingredientType.getName() + post;
        }
        else{
            if (ingredientType.hasSpecialName()){
                return getSpecialName() + " (" + pre + getName() + post + ")";
            }
            else{
                return pre + ingredientType.getName() + post;
            }
        }
    }

    private String getPreName(){ // to simplify expansion
        // TODO: could be saved as a field and changed when the respective property has changed [OPTIONAL]
        // If we decide not to do this, change to regular comment and remove the optional tag ^
        String pre = "";
        long temp = asLong(this.getTemperature());
        long standardTemperature = asLong(this.getStandardTemperature());
        if (temp < standardTemperature){
            pre += "Cooled ";
        }
        else if (temp > standardTemperature) {  // Who's the silly goose who forgot that temperatures could be equal? INE =) ma kheb er nu aan gedacht en kgingt aanpassen =)
            pre += "Heated ";
        }
        return pre;
    }

    private String getPostName(){ // to simplify expansion
        String post = "";
        return post;
    }

    private static long asLong(long[] temperature){
        if (temperature[0] != 0){
            return -temperature[0];
        }
        else {
            return temperature[1];
        }
    }

    public String getSpecialName(){
        if (ingredientType.isMixedIngredient()){
            return this.getSpecialName();
        }
        else {
            return null;
        }
    }

    /* ***************************
     * INGREDIENT TYPE - STANDARD TEMPERATURE
     * ***************************/

    public long[] getStandardTemperature(){
        return ingredientType.getStandardTemperature();
    }

    /* ***************************
     * INGREDIENT TYPE - STANDARD STATE
     * ***************************/

    public State getStandardState(){
        return ingredientType.getStandardState();
    }

    /* *********************************************************
     * STATE
     * *********************************************************/
    public State getState() {
        return state;
    }

    // TODO: redundant, as transmogrifier will simply create a new ingredient rather than change the state.
    // This means state can be final! :) - Ya boi Dimme
    public void changeState(State state) {
        this.state = state;
    }

    /* *********************************************************
     * QUANTITY & UNIT
     * *********************************************************/

    public int getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public double getQuantityInSpoons() {  // To simplify a common calculation
        return getQuantity() * getUnit().getValue();
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    public long[] getTemperature() {
        long[] temp = new long[2];
        if (this.temperature < 0) {
            temp[0] = Math.abs(this.temperature);
        } else {
            temp[1] = this.temperature;
        }
        return temp;
    }

    private void setTemperature(long[] temperature) throws IllegalTemperatureException { // input : array
        if (!isValidTemperature(temperature)) {
            throw new IllegalTemperatureException(temperature);
        }
        long temp = 0;
        if (temperature[0] != 0) {
            temp -= temperature[0];
        } else {
            temp = temperature[1];
        }
        this.temperature = temp;
    }

    private static boolean isValidTemperature(long[] temperature) {
        if (temperature[0] != 0 && temperature.length == 2) {
            return temperature[1] == 0 && temperature[0] <= MAX_TEMPERATURE;
        } else
            return temperature.length == 2 && temperature[1] <= MAX_TEMPERATURE; // dus temperature[0] == 0 -> array mag nog steeds maar 2 lang zijn en
    }

    public void heat(long temperature) {
        if (this.temperature + temperature <= MAX_TEMPERATURE) {
            this.temperature += temperature;
        }
    }

    public void cool(long temperature) {
        if (this.temperature - temperature >= -MAX_TEMPERATURE) {
            this.temperature += temperature;
        }
    }

}