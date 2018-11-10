package backEnd;
public class Dicey {

    public static int Roll(int number, int sides, int buff){
        int total = 0;
        for(int i = 0; i < number; i++)
            total += (int)(Math.random()*(sides)+1);
        total += buff;
        return total;
    }
}