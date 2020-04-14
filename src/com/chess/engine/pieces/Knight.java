package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};

    // Конструктор создается автоматически
    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }


    // Создается автоматически из абстрактного метода родителя Piece (Фигура)
    @Override
    public ImmutableList<Move> calculateLegalMoves(final Board board) {

        int candidateDestinationCoordinate;
        List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isFirstColumnExclusion(this.piecePosition, currentCandidate) ||
                        isSecondColunmExclusion(this.piecePosition, currentCandidate) ||
                        isSevenColunmExclusion(this.piecePosition, currentCandidate) ||
                        isEigthColumnExclusion(this.piecePosition, currentCandidate))
                    continue;

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) ||
                (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColunmExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSevenColunmExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVEN_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                candidateOffset == 10 || candidateOffset == 17);
    }



}
