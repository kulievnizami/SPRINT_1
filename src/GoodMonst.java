import java.util.Random;
import java.util.Scanner;

public class GoodMonst extends Monster{
    private String image = "\uD83E\uDDDD\u200D♂\uFE0F";

    GoodMonst(int sizeBoard) {
        super(sizeBoard);
    }
    public String getImage() {
        return image;
    }

    //    @Override
    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public boolean taskMonster(int difficultGame){
        System.out.println("Решите задачу:");
        if (difficultGame == 1){
            return taskMonster();
        }else {
            int x = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int y = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int z = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int trueAnswer = x * y - z;
            System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (trueAnswer == ans) {
                System.out.println("Верно! Эль помог тебе");
                return true;
            }
            System.out.println("'Эльфу не хватило сил помочь и он случацно отравил тебя !");
            return false;
        }

    }

    public boolean taskMonster() {
        return super.taskMonster(0);
    }

}



