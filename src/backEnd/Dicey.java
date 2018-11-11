package backEnd;
public class Dicey {

    public static int[] Roll(int number, int sides, int buff){
        int total = 0;
        int[] rolls = new int[number+2];
        for(int i = 0; i < number; i++) {
            rolls[i] = (int) (Math.random() * sides + 1);
            total += rolls[i];
        }
        rolls[number] = total;
        total += buff;
        rolls[number] = total;
        return rolls;
    }

    /**
     * Takes string in the standard format for decided amount of dice and amount of sides and rolls accordingly
     * @param command String in the form #D# or #d#
     * @return Dice roll result
     */
    public static int[] Roll(String command){
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
        int[] zeroArray = {0,0};
        return zeroArray;
    }
}