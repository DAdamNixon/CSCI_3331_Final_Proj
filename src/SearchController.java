public class SearchController {

    public SearchController() {

    }

    public boolean parse(String text, String item) {
        String query = text.toLowerCase();
        String itemInfo = item.toLowerCase();
        String[] buffer = query.split(" ");
        Boolean flag = false;

        for (String string : buffer) {
            if (itemInfo.contains(string)) {
                flag = true;
            }
        }

        return flag;
    }
}
