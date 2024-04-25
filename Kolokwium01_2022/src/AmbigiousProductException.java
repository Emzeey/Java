import java.util.List;

public class AmbigiousProductException extends Exception{
    private final List<String> listOfNames;
    public AmbigiousProductException(List<String> listOfProducts) {
        listOfNames = listOfProducts;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        message.append("More than one products found:\n");
        for(String s : listOfNames) {
            message.append(s).append("\n");
        }
        return message.toString();
    }
}
