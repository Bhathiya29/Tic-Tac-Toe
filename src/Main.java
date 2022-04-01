import java.util.*;

public class Main {

    static ArrayList<Integer>playerPositions =new ArrayList<Integer>();
    static ArrayList<Integer>cpuPositions =new ArrayList<Integer>();


    public static void main(String[] args) {


	//           -------------  A TIC TAC TOE GAME WHERE WE PLAY AGAINST THE COMPUTER --------------



        char [][]gameBoard= { {' ','|',' ','|',' '},
                             {'-','+','-','+','-'},
                             {' ','|',' ','|',' '},
                             {'-','+','-','+','-'},
                             {' ','|',' ','|',' '}};




        Scanner scan=new Scanner(System.in);

        printGameBoard(gameBoard);

        while (true) {
            System.out.println("Please enter a Number between (1-9): ");
            int playerPosition = scan.nextInt();

            while (playerPositions.contains(playerPosition)||cpuPositions.contains(playerPosition)){
                playerPosition= scan.nextInt();
            }


            placePiece(gameBoard, playerPosition, "player");
            String result=checkWinner();
            if(result.length()>0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos)||cpuPositions.contains(cpuPos)){
                System.out.println("position taken!! Enter a correct position");
                cpuPos= random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result=checkWinner();
            if(result.length()>0){
            System.out.println(result);
            break;
            }


        }
    }

    public static void printGameBoard(char[][]gameBoard){
        for(char[] row:gameBoard){
            for(char c:row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard,int playerPosition,String user){

        char symbol=' ';

        if(user.equals("player")){
            symbol='x';
            playerPositions.add(playerPosition);
        }else if (user.equals("cpu")){
            symbol='o';
            cpuPositions.add(playerPosition);
        }

        switch (playerPosition){
            case 1:
                gameBoard[0][0]= symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]=symbol;
                break;
            default:
                System.out.println("You Entered a Wrong Number");

        }
    }
    public static String checkWinner(){
        List topRow= Arrays.asList(1,2,3);
        List middleRow= Arrays.asList(4,5,6);
        List bottomRow= Arrays.asList(7,8,9);
        List leftColumn=Arrays.asList(1,4,7);
        List midColumn=Arrays.asList(2,5,8);
        List rightColumn=Arrays.asList(3,6,9);
        List midCrossRight=Arrays.asList(1,5,9);
        List midCrossLeft=Arrays.asList(3,5,7);

        List<List>winningConditions=new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftColumn);
        winningConditions.add(midColumn);
        winningConditions.add(rightColumn);
        winningConditions.add(midCrossRight);
        winningConditions.add(midCrossLeft);

        for(List l:winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congratulation you won! ";
            }else if(cpuPositions.containsAll(l)){
                return "Couldn't beat the computer huh! ";
            }else if(playerPositions.size()+cpuPositions.size()==9){
                return "tied";
            }
        }

        return "";
    }
}
