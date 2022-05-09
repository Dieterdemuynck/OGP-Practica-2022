public class AlchemicIngredient {

    private final IngredientType ingredientType;
    private State state;
    private final int quantity;
    private long temperature;
    public static final int MAX_TEMPERATURE = 10000;


    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public AlchemicIngredient(int quantity, long[] standardTemperature, String name, State standardState){
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.quantity = quantity;
        setTemperature(standardTemperature);
        changeState(standardState);
    }

    public AlchemicIngredient(int quantity){
        long[] standardTemperature = {0, 20};
        this.ingredientType = new IngredientType("Water", standardTemperature, State.Liquid);
        this.quantity = quantity;
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
        String pre = null;
        long temp = getLong(this.getTemperature());
        long standtemp = getLong(this.getStandardTemperature());
        if (temp < standtemp){
            pre = "Cooled ";
        }
        else {
            pre = "Heated ";
        }
        if (!ingredientType.isMixedIngredient()){
            return pre + ingredientType.getName();
        }
        else{
            if (ingredientType.hasSpecialName()){
                return getSpecialName() + " (" + pre + getName() + ")";
            }
            else{
                return pre + ingredientType.getName();
            }
        }
    }

    private long getLong(long[] temperature){
        if (temperature[0] != 0){
            return -temperature[0];
        }
        else {
            return temperature[1];
        }
    }

    public String getSpecialName(){
        if (ingredientType.isMixedIngredient()){
            return MixedIngredientType.getSpecialName();
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

    public void changeState(State state) { //mag enkel gebruikt worden in Transmogrifier
        this.state = state;
        //todo hoeveelheid wordt ook aangepast -> Dieter =)
    }

    /* *********************************************************
     * QUANTITY
     * *********************************************************/
    public int getQuantity() {
        return quantity;
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