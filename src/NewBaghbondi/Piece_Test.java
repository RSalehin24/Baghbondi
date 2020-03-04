package NewBaghbondi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Piece_Test {
    Piece tiger =  new Piece(PieceTypeEnum.TIGER,1,2);
    Piece goat = new Piece(PieceTypeEnum.GOAT,2,2);

    @Test
    void goat_move(){
        goat.move(3,4);
        Assertions.assertEquals(300,goat.getOldHorizontal());
        Assertions.assertEquals(400,goat.getOldVertical());
        goat.abortMove();
        Assertions.assertEquals(300,goat.getOldHorizontal());
        Assertions.assertEquals(400,goat.getOldVertical());


    }
    @Test
    void tiger_move(){
        tiger.move(2,3);
        Assertions.assertEquals(300,tiger.getOldVertical());
        Assertions.assertEquals(200,tiger.getOldHorizontal());
        tiger.abortMove();
        Assertions.assertEquals(300,tiger.getOldVertical());
        Assertions.assertEquals(200,tiger.getOldHorizontal());
    }
}