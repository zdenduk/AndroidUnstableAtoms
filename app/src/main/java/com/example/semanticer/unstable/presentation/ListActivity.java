package com.example.semanticer.unstable.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;

import java.sql.SQLOutput;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends Activity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Game game;

    @BindView(R.id.game_board_layout_list)
    GameBoardLayout gameBoardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_list);

        ButterKnife.bind(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Intent intent = getIntent();
        mAdapter = new MyAdapter(intent.getStringArrayListExtra("scores"), intent.getParcelableArrayListExtra("gameboards"));


        // set listener for recyclerview
        List<GameBoard> gameBoards = intent.getParcelableArrayListExtra("gameboards");
        mAdapter.setOnClickListener(position -> {
            gameBoardList.setBoard(gameBoards.get(position));
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}