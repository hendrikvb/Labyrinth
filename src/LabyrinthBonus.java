import java.util.Random;
import java.util.Scanner;

/**
 * Created by Hendrik von Bongartz on 22.11.2015.
 */
public class LabyrinthBonus {
    static char avatar(int r) {
        switch (r) {
            case 0:
                return '>'; //Ost
            case 1:
                return '^'; //Nord
            case 2:
                return '<'; //West
            case 3:
                return 'v'; //Süd
        }
        return ' ';
    }

    static void print(boolean[][] field, int[] pos, char r) {
        boolean check;

        for (int y = 1; y < field.length - 1; y++) {
            for (int x = 1; x < field.length - 1; x++) {

                check = !(pos[0] == x && pos[1] == y);

                if (field[y][x]) {
                    if (check) {
                        System.out.print("O ");
                    } else {
                        System.out.print(r + " "); //Das geht bestimmt auch irgendwie eleganter...
                    }
                }
                if (!field[y][x]) {
                    if (check) {
                        System.out.print("X ");
                    } else {
                        System.out.print(r + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        int[] pos = {2, 3}; //x, y
        boolean map[][] = {};
        if (args.length == 1) {
            map = Loader.load(args[0]);
        } else {
            System.out.println("Das Programm braucht genau einen Pfad als Argument!");
            System.exit(1);
        }
        int richtung = 1;

        boolean bool1 = pos[1] != 7;
        boolean bool2 = pos[0] != 7;

        while (bool1 || bool2) {
            print(map, pos, avatar(richtung));
            richtung = move(viewport(map, pos));
            switch (richtung) {
                case 0:
                    pos[0] += 1;
                    break;
                case 1:
                    pos[1] -= 1;
                    break;
                case 2:
                    pos[0] -= 1;
                    break;
                case 3:
                    pos[1] += 1;
                    break;
            }
            bool1 = pos[1] != 7;
            bool2 = pos[0] != 7;
        }
        System.out.println("Ausgang bei " + pos[0] + pos[1] + " gefunden!");

    }

    static boolean[] viewport(boolean[][] t, int[] pos) {
        boolean[] array = new boolean[4];

        int x = pos[0], y = pos[1];

        //Osten: x+1
        array[0] = t[y][x + 1] == true;

        //Norden: y - 1, da das Feld von oben nach unten aufgebaut wird
        array[1] = t[y - 1][x] == true;

        //Westen: x - 1
        array[2] = t[y][x - 1] == true;

        //Süden: y + 1
        array[3] = t[y + 1][x] == true;


        return array;
    }

    static int move(boolean[] field) {
        boolean possibleMoves[] = new boolean[4];
        for (int i = 0; i < 4; i++)
            if (field[i]) {
                System.out.println("Bewegung ist möglich nach " + i + ": " + avatar(i));
                possibleMoves[i] = true;
            } else {
                possibleMoves[i] = false;
            }

        int input = 0;

        boolean check = false;
        Random random = new Random();

        while (!check) {
            input = random.nextInt(4);
            if (possibleMoves[input]) {
                check = true;
            } else {
                System.out.println(input + " ist nicht gültig!");
            }
        }


        return input;
    }
}
