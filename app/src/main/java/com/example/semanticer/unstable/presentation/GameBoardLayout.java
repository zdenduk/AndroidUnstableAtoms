package com.example.semanticer.unstable.presentation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameBoardLayout extends GridLayout {

    private OnMoveListener listener;

    public GameBoardLayout(Context context) {
        super(context);
    }

    public GameBoardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBoard(GameBoard gameBoard) {
        removeAllViews();
        setRowCount(gameBoard.rows());
        setColumnCount(gameBoard.columns());

        for (int r = 0; r < gameBoard.rows(); r++) {
            for (int c = 0; c < gameBoard.columns(); c++) {
                BoardFieldWidget field = new BoardFieldWidget(getContext(), gameBoard.fields().get(r).get(c), r, c);
                addView(field);
            }
        }

        if (listener != null) {
            setupFieldListeners(listener);
        }
    }

    public void setOnMoveListener(OnMoveListener listener) {
        this.listener = listener;
        setupFieldListeners(listener);
    }

    public void setupFieldListeners(OnMoveListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            BoardFieldWidget child = (BoardFieldWidget) getChildAt(i);
            GridLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.setOnClickListener(v -> listener.onMoveMade(child.getRow(), child.getColumn()));
        }
    }

    interface OnMoveListener {
        void onMoveMade(int x, int y);
    }
}
