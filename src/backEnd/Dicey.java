package backEnd;

public class Dicey {

    public int Roll(int number, int sides){
        int total = 0;
        for(int i = 0; i < number; i++)
            total += (int)(Math.random()*(sides-1)+1);
        return total;
    }
}
