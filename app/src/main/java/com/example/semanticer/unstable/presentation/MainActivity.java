package com.example.semanticer.unstable.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.semanticer.unstable.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {

    @BindView(R.id.textView)
    TextView a;

    @BindView(R.id.button)
    Button b;

    @BindView(R.id.button2)
    Button c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void startSingle(View view) {
        startGame(false);
    }

    @Override
    public void startMulti(View view) {
        startGame(true);
    }

    private void startGame(boolean gameType) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Type", gameType);
        startActivity(intent);
    }
}
