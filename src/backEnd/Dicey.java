package backEnd;
public class Dicey {

    public static int Roll(int number, int sides, int buff){
        int total = 0;
        for(int i = 0; i < number; i++)
            total += (int)(Math.random()*(sides)+1);
        total += buff;
        return total;
    }

    public static int Roll(String command){
        int dpos = 0;
        int plusPos = 0;
        for(int i = 0; i < command.length();i++){
            char letter = command.charAt(i);
            if(letter == 'd'||letter == 'D')
                dpos = i;
            if(letter == '+')
                plusPos = i;
        }
        if(dpos!=0){
            int buff = 0;
            int num = Integer.parseInt(command.substring(0,dpos));
            int sides = Integer.parseInt(command.substring(dpos+1));
            if(plusPos != 0)
                buff = Integer.parseInt(command.substring(plusPos+1));
            return Roll(num,sides,buff);
        }

        return 0;

    }
}