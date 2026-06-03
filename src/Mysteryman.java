import java.util.Random;
import java.util.Scanner;

public class Mysteryman extends Monster{
    private String image = "\uD83E\uDDB9\u200D♂\uFE0F";

    Mysteryman(int sizeBoard) {
        super(sizeBoard);
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster() {
        return super.taskMonster(0);
    }

}
