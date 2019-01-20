package backEnd;
public class Dicey {

    public static int[] Roll(int number, int sides, int buff){  // Returns an array of each roll value, a total, and a total with buff added
        int total = 0;
        int[] rolls = new int[number+2];
        for(int i = 0; i < number; i++) {
            rolls[i] = (int) (Math.random() * sides + 1);
            total += rolls[i];
        }
        rolls[number] = total;
        total += buff;
        rolls[number+1] = total;
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
        boolean debuff = false;
        for(int i = 0; i < command.length();i++){
            char letter = command.charAt(i);
            if(letter == 'd'||letter == 'D')
                dpos = i;
            if(letter == '+')
                plusPos = i;
            if(letter == '-'){
                plusPos = i;
                debuff = true;
            }
        }
        if(dpos!=0){  // if a d is not located in statement, return array of zeros
            int buff = 0;
            int num = Integer.parseInt(command.substring(0,dpos));
            int sides;
            if(plusPos != 0) {  // If a + is present, add a buff
                sides = Integer.parseInt(command.substring(dpos+1,plusPos));
                buff = Integer.parseInt(command.substring(plusPos + 1));
                if(debuff)
                    buff = buff*-1;
            }
            else
                sides = Integer.parseInt(command.substring(dpos+1));
            return Roll(num,sides,buff);
        }
        int[] zeroArray = {0,0};
        return zeroArray;
    }

    public static String rollToString(int[] rolls){
        String rollString = String.valueOf(rolls[0]);
        int buff = rolls[rolls.length-1] - rolls[rolls.length-2];
        for(int i=1; i<rolls.length-2;i++){
            rollString += " + " + String.valueOf(rolls[i]);
        }
        rollString += " = " + String.valueOf(rolls[rolls.length-2]);
        if(buff != 0) {
            if (buff > 0)
                rollString += "+";
            rollString += buff + " = " + String.valueOf(rolls[rolls.length - 1]);
        }

        return rollString;
    }

    //public static int
}