import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(boolean white){
        super(Type.Knight, white);
    }

    @Override
    public boolean isValid(Game game, Cord from, Cord to){
        boolean valid = false;
        int dx = abs(from.getX() - to.getX());
        int dy = abs(from.getY() - to.getY());
        if(dx == 1 && dy == 2) valid = true;
        if(dx == 2 && dy == 1) valid = true;
        return valid && super.isValid(game, from, to);
    }

    @Override
    public ArrayList<Cord> validMoves(Game game, Cord from){
        ArrayList<Cord> moves = new ArrayList<Cord>();
        if(game.getPiece(from).getType() == Type.Empty)
            return moves;
        
        for(int i = 0; i < game.getBoardSize(); i++)
            for(int j = 0; j < game.getBoardSize(); j++)
                if(isValid(game, from, new Cord(i, j)))
                    moves.add(new Cord(i, j));
        return moves;
    }

    @Override
    public String validMovesToString(Game game, Cord from){
        ArrayList<Cord> moves = validMoves(game, from);
        String movesToString = "";
        String fromString = Converter.CordToUCI(from);
        for(Cord cord : moves)
            movesToString = movesToString + fromString + Converter.CordToUCI(cord) + ", ";
        if(movesToString.length() != 0) movesToString = movesToString.substring(0, movesToString.length() - 2);
        return movesToString;
    }

    @Override
    public char toCharacter(){
        return isWhite? 'N' : 'n';
    }
}
