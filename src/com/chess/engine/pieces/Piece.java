package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        // Доделать тут
        this.isFirstMove = false;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    // Список легальных ходов фигуры (должен опредляться каждой фигурой, от того и абстрактный
    public abstract ImmutableList<Move> calculateLegalMoves(final Board board);
}