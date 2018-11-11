package backEnd;

import java.io.*;

public class NewCharacter {

    public void createNewCharFiles() {
        String charName = "Ikilian";
        File dir = new File("Characters/" + charName);
        dir.mkdir();
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters/" + charName + "/newChar.txt"), "utf-8"));
            writer.write("Something");
        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
