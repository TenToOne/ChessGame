package src;

public class Move {
    public boolean validateMove(int a, int b, char colour,String[] board) {
        int r1, c1, r2, c2, dr, dc;

        // Convert start and end positions to rows and columns
        r1 = a / 4 + 1;		// Start row
        c1 = a % 4 + 1;		// Start column
        r2 = b / 4 + 1;		// End row
        c2 = b % 4 + 1;		// End column
        dr = r2 - r1;		// Change of squares between start and end rows
        dc = c2 - c1;		// Change of squares between start and end columns

        if (colour == 'w') {
            if (Character.isLowerCase(board[a].charAt(0)) || Character.isUpperCase(board[b].charAt(0))) {
                return false;
            }
        }
        else if (colour == 'b') {
            if (Character.isUpperCase(board[a].charAt(0)) || Character.isLowerCase(board[b].charAt(0))) {
                return false;
            }
        }

        switch (board[a]) {
            case "*":
                return false;
            case "P5" :
            case "P6" :
            case "P7" :
            case "P8" :
                if(Math.abs(dc)<=1&&dr==-1)
                    return true;
            case "P1":
            case "P2":
            case "P3":
            case "P4":
                if ((c1 == c2) && (dr == -1)) {
                    return true;
                }
                else {
                    return false;
                }
            case "p5" :
            case "p6" :
            case "p7" :
            case "p8" :
                if(Math.abs(dc)<=1&&dr==1)
                    return true;
                else return false;
            case "p1":
            case "p2":
            case "p3":
            case "p4":
                if ((c1 == c2) && (dr == 1)) {
                    return true;
                }
                else {
                    return false;
                }
            case "K":
            case "k":
                if ((dr >= -1) && (dr <= 1) && (dc >= -1) && (dc <= 1)) {
                    return true;
                }
                else {
                    //                 System.out.println(3);
                    return false;
                }
            case "b5":
            case "B5":
            case "b6":
            case "B6":
            case "b7":
            case "B7":
            case "b8":
            case "B8":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=2) {
                    if(Math.abs(dr)==1) return  true;
                    else if(Math.abs(dr)==2&&board[a+(dr*2)+(dc/2)].equals("*")) return  true;
                    else return false;
                }
            case "B1":
            case "b1":
            case "B2":
            case "b2":
            case "B3":
            case "b3":
            case "B4":
            case "b4":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                    return true;
                }
                else {
                    return false;
                }
            case "N5":
            case "n5":
            case "N6":
            case "n6":
            case "N7":
            case "n7":
            case "N8":
            case "n8":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1|Math.abs(dr)==2)) {
                    return true;
                }
            case "N4":
            case "n4":
            case "N3":
            case "n3":
            case "N2":
            case "n2":
            case "N1":
            case "n1":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1|Math.abs(dr)==2)) {
                    if(Math.abs(dr)==2&&board[a+(dr*4)].equals("*")&&board[a+(dr*2)].equals("*")) return true;
                    else if(Math.abs(dc)==2&&board[a+(dc)].equals("*")&&board[a+(dc/2)].equals("*")) return true;
                    else return false;
                }
                else {
                    return false;
                }
            case "R8":
            case "r8":
            case "R7":
            case "r7":
            case "R6":
            case "r6":
            case "R5":
            case "r5":
                if ((dc==0&&Math.abs(dr)<=2)||(dr==0&&Math.abs(dc)<=2)) {
                    if(Math.abs(dr)+Math.abs(dc)==1) return true;
                    else if(Math.abs(dr)==2&&board[a+dr*2].equals("*")) return  true;
                    else if(Math.abs(dc)==2&&board[a+dc/2].equals("*")) return  true;
                    else return false;
                }
            case "R4":
            case "r4":
            case "R3":
            case "r3":
            case "R2":
            case "r2":
            case "R1":
            case "r1":

                if (Math.abs(dr)+Math.abs(dc)==1) {
                    return true;
                }
                else {
                    return false;
                }
            default:
                return false;
        }
    }
}
