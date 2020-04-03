package NewBaghbondi;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StageCreator {

    static final int positionSize = 50;
    private Group positionGroup = new Group();
    private Group pieceGroup = new Group();
    private BoardLines lineGroup = new BoardLines();
    private Position[][] board = new Position[5][5];
    private Scene gameScene;
    private int verticalLine = 5;
    private int horizontalLine = 5;

    Stage boardStage = new Stage();
    BoardListener listener = new BoardListener(boardStage, board, pieceGroup);

    private Parent createContent() {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(500, 500);
        drawBoard();
        drawLine();
        configureParent(gamePane);
        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(gamePane);
        return rootPane;
    }

    private void configureParent(Pane gamePane) {
        gamePane.getChildren().addAll(positionGroup, pieceGroup, lineGroup.getLineGroup());
        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);
    }

    public void boardStage() {
        gameScene = new Scene(createContent());
        boardStage.setScene(gameScene);
        boardStage.setTitle("BaghBondi");
        //   boardStage.setFullScreen(true);
        boardStage.show();
    }

    private void drawBoard() {
        System.out.println("****************Called drawBoard()****************");
        int skip, skipCounter;
        int i, j;
        for (i = -verticalLine / 2, skip = -horizontalLine / 2; i < verticalLine / 2 + 1; i++, skip++) {
            for (skipCounter = 2, j = -Math.abs(i); j < Math.abs(i) + 1; j++) {
                if (skipCounter < Math.abs(skip) - 1) {
                    skipCounter++;
                    continue;
                } else skipCounter = 0;
                Position position = createPosition(i, j);
                Piece piece = null;
                if (i >= 0) {
                    piece = makePiece(PieceTypeEnum.GOAT, i + verticalLine / 2, j + horizontalLine / 2);
                    System.out.println("Making piece type :: goat: " + position.getLayoutX() + "," + position.getLayoutY());

                } else if (i == -1 && j == 0) {
                    piece = makePiece(PieceTypeEnum.TIGER, i + verticalLine / 2, j + horizontalLine / 2);
                }
                if (piece != null) {

                    position.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
    }

    private Position createPosition(int vertical, int horizontal) {
        System.out.println("****************Called createPosition()****************");
        Position position = new Position(vertical + verticalLine / 2, horizontal + horizontalLine / 2);
        System.out.println("On board " + (horizontal + horizontalLine / 2) + " " + (vertical + verticalLine / 2));
        board[horizontal + horizontalLine / 2][vertical + verticalLine / 2] = position;
        positionGroup.getChildren().add(position);
        return position;
    }

    private Piece makePiece(PieceTypeEnum pieceTypeEnum, int vertical, int horizontal) {
        System.out.println("-----Called makePiece()-----");
        Piece piece;
        if (pieceTypeEnum == PieceTypeEnum.TIGER)
            piece = new Tiger(vertical, horizontal);
        else
            piece = new Goat(vertical, horizontal);
        listener.addMouseReleaseOptions(piece);
        return piece;

    }


    private void drawLine() {
        lineGroup.setHorizontalLine1(board[0][0], board[4][0]);
        lineGroup.setHorizontalLine2(board[1][1], board[3][1]);
        lineGroup.setHorizontalLine3(board[1][3], board[3][3]);
        lineGroup.setHorizontalLine4(board[0][4], board[4][4]);
        lineGroup.setVerticalLine1(board[0][0], board[4][4]);
        lineGroup.setVerticalLine2(board[2][0], board[2][4]);
        lineGroup.setVerticalLine3(board[4][0], board[0][4]);
    }

    public Stage getStage(){
        return boardStage;
    }
}
