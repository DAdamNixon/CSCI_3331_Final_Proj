public class SearchController {

    public SearchController() {

    }

    public boolean parse(String text, String item) {
        String query = text;
        String itemInfo = item;
        String[] buffer1 = itemInfo.split(",");
        String[] buffer2 = query.split(" ");
        Boolean flag = false;

        for (int i = 0; i < buffer1.length; i++) {
            for (int j = 0; j < buffer2.length; j++) {

                if (buffer1[i].equalsIgnoreCase(buffer2[j])) {
                    flag = true;
                }
            }
        }

        return flag;
    }
}
