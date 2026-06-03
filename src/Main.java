import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.out.println("Выберите поля игры");


        String castle = "\uD83C\uDFF0";
        Scanner df = new Scanner(System.in);
        int sizeBoard = df.nextInt();
        Person person = new Person(sizeBoard);
        String fakecastle = "\uD83C\uDFF0";




        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }




        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();
        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count <= countMonster){
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            }else if (r.nextBoolean()) {
                test = new BigMonster(sizeBoard);
            }else if (r.nextBoolean()){
                test = new GoodMonst(sizeBoard);

            }else{
                test = new Mysteryman(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")){
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }

        }
        int castleX1 = r.nextInt(sizeBoard);
        int castleY2 = 0;

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;

        board[castleY2][castleX1] = fakecastle;


        board[castleY][castleX] = castle;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА, НЕТ, Число 1 - Гайд по игре");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);
        int difficultGame;




        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive(), difficultGame);
                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt(
);
                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];
                        if (next.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                    "\nХод номер: " + step);

                        }
                        if (next.equals(fakecastle)){
                            System.out.println("О нет это засада!");
                            person.downLive();
                        } if (next.equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            break;
                        }else {
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                    if (monster instanceof GoodMonst){
                                        person.HelpLive();
                                    if (monster instanceof  Mysteryman){
                                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                                        outputBoard(board, person.getLive(), difficultGame);
                                    }
                                    }

                                    } else {
                                        person.downLive();
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Неккоректный ход");
                    }
                }
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены неккоректно");
            case "1" -> System.out.println("Ты должен добраться до замка но тебя будут окружать всякие препятствие:\n" +
                    "\uD83E\uDDDF\u200D Зомби - при неправильном решении отнимает 1 жизнь\n" +
                    "\uD83D\uDC79 Большой зомби! - дает более сложные задачи но также отнимает 1 жизнь\n" +
                    "\uD83E\uDDDD\u200D♂\uFE0F - Эльф твой союзник ! он может тебя вылечить но если ты ему не поможешь сосредоточиться он случайно отравит тебя(\n" +
                    "\uD83C\uDFF0 - Засада ! - внешне ничем не отличается от настоящего замка но может отнять или 1 или аж 2 жизни");
        }

    }

    static void outputBoard(String[][] board, int live, int difficultGame) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";
        if (difficultGame == 5 || difficultGame == 4 ){
            leftBlock = "| ";
            rightBlock = "|";
            wall = "+ —— + —— + —— + —— + —— + —— + —— + —— + —— + —— +";
        }else{
            leftBlock = "| ";
            rightBlock = "|";
            wall = "+ —— + —— + —— + —— + —— +";

        }



        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);


        System.out.println("Количество жизней:\t" + live + "\n");
    }
}