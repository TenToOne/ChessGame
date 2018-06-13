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
            case "P8" :
                if ((dc==-1) && (dr == 1)) {
                    return true;
                }
            case "P7" :
                if ((dc==1) && (dr == 1)) {
                    return true;
                }
            case "P6" :
                if ((c1 == c2) && (dr == 1)) {
                    return true;
                }
            case "P5" :
            case "D" :
                if ((dc==-1) && (dr == 0)) {
                    return true;
                }
            case "P4":
                if ((dc==1) && (dr == 0)) {
                    return true;
                }
            case "P3":
                if ((dc==-1) && (dr == -1)) {
                    return true;
                }
            case "P2":
                if ((dc==1) && (dr == -1)) {
                    return true;
                }
            case "P1":
                if ((c1 == c2) && (dr == -1)) {
                    return true;
                }
                else {
                    return false;
                }
            case "p8" :
                if ((dc==1) && (dr == -1)) {
                    return true;
                }
            case "p7" :
                if ((dc==-1) && (dr == 1)) {
                    return true;
                }
            case "p6" :
                if ((c1 == c2) && (dr == -1)) {
                    return true;
                }
            case "p5" :
                if ((dc==1) && (dr == 0)) {
                    return true;
                }
            case "p4":
                if ((dc==-1) && (dr == 0)) {
                    return true;
                }
            case "p3":
                if ((dc==1) && (dr == 1)) {
                    return true;
                }
            case "p2":
                if ((dc==-1) && (dr == 1)) {
                    return true;
                }
            case "p1":
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
            case "b8":
            case "B8":
                if ((Math.abs(dc)==1) && (dr == 0)) {
                    return true;
                }
            case "b7":
            case "B7":
                if ((c1 == c2) && (Math.abs(dr) == 1)&&((colour=='w'&&dr>0)||(colour=='b'&&dr<0))) {
                    return true;
                }
            case "b6":
            case "B6":
                if ((c1 == c2) && (Math.abs(dr) == 1)&&((colour=='w'&&dr<0)||(colour=='b'&&dr>0))) {
                    return true;
                }
            case "b5":
            case "B5":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=3&&((colour=='w'&&dr>0)||(colour=='b'&&dr<0))) {
                    if(board[a+(dr/3)*4+(dc/3)].equals("*")&&board[a+(dr/3)*8+(dc/3)*2].equals("*")) return  true;
                }
            case "B4":
            case "b4":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=3&&((colour=='w'&&dr<0)||(colour=='b'&&dr>0))) {
                    if(board[a+(dr/3)*4+(dc/3)].equals("*")&&board[a+(dr/3)*8+(dc/3)*2].equals("*")) return  true;
                }
            case "B3":
            case "b3":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=2&&((colour=='w'&&dr>0)||(colour=='b'&&dr<0))) {
                    if(board[a+(dr*2)+(dc/2)].equals("*")) return  true;
                }
            case "B2":
            case "b2":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=2&&((colour=='w'&&dr<0)||(colour=='b'&&dr>0))) {
                    if(board[a+(dr*2)+(dc/2)].equals("*")) return  true;
                }
            case "B1":
            case "b1":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                    return true;
                }
                else {
                    return false;
                }
            case "N8":
            case "n8":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                    return true;
                }
            case "N7":
            case "n7":
                if (Math.abs(dr)+Math.abs(dc)==1) {
                    return true;
                }
            case "N6":
            case "n6":
                if (Math.abs(dr)+Math.abs(dc)==3&&Math.abs(dr)==1) {
                    return true;
                }
            case "N5":
            case "n5":
                if (Math.abs(dr)+Math.abs(dc)==3&&Math.abs(dr)==2&&((colour=='w'&&dr>0)||(colour=='b'&&dr<0))) {
                    return true;
                }
            case "N4":
            case "n4":
                if (Math.abs(dr)+Math.abs(dc)==3&&Math.abs(dr)==2&&((colour=='w'&&dr<0)||(colour=='b'&&dr>0))) {
                    return true;
                }
            case "N3":
            case "n3":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1)) {
                    if(Math.abs(dr)==2&&board[a+(dr*4)].equals("*")&&board[a+(dr*2)].equals("*")) return true;
                    else if(Math.abs(dc)==2&&board[a+(dc)].equals("*")&&board[a+(dc/2)].equals("*")) return true;
                }
            case "N2":
            case "n2":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==2)&&((colour=='w'&&dr>0)||(colour=='b'&&dr<0))) {
                    if(Math.abs(dr)==2&&board[a+(dr*4)].equals("*")&&board[a+(dr*2)].equals("*")) return true;
                    else if(Math.abs(dc)==2&&board[a+(dc)].equals("*")&&board[a+(dc/2)].equals("*")) return true;
                }
            case "N1":
            case "n1":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==2)&&((colour=='w'&&dr<0)||(colour=='b'&&dr>0))) {
                    if(Math.abs(dr)==2&&board[a+(dr*4)].equals("*")&&board[a+(dr*2)].equals("*")) return true;
                    else if(Math.abs(dc)==2&&board[a+(dc)].equals("*")&&board[a+(dc/2)].equals("*")) return true;
                }
                   return false;
            case "R8":
            case "r8":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=2) {
                    if (Math.abs(dr) == 2 && board[a + (dr * 2) + (dc / 2)].equals("*")) return true;
                }
            case "R7":
            case "r7":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                    return true;
                }
            case "R6":
            case "r6":
                if ((dc==0&&Math.abs(dr)==4)) {
                    if(board[a+(dr/4)*4].equals("*")&&board[a+(dr/3)*4].equals("*")&&board[a+(dr/3)*12].equals("*")) return  true;
                }
            case "R5":
            case "r5":
                if ((dr==0&&Math.abs(dc)==3)) {
                    if(board[a+(dc/3)].equals("*")&&board[a+(dc/3)*2].equals("*")) return  true;
                }
            case "R4":
            case "r4":
                if ((dc==0&&Math.abs(dr)==3)) {
                    if(board[a+(dr/3)*4].equals("*")&&board[a+(dr/3)*8].equals("*")) return  true;
                }
            case "R3":
            case "r3":
                if ((dr==0&&Math.abs(dc)==2)) {
                   if(board[a+dc/2].equals("*")) return  true;
                }
            case "R2":
            case "r2":
                if ((dc==0&&Math.abs(dr)==2)) {
                    if(board[a+dr*2].equals("*")) return  true;
                }
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
