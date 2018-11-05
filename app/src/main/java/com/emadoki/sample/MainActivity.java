package com.emadoki.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private MoveableLayout moveableLayout;
    private CardView cardView;
    private TextView textView;
    private Button button;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        moveableLayout = (MoveableLayout) findViewById(R.id.moveableLayout);
        textView = (TextView) findViewById(R.id.textView);

        cardView = (CardView) findViewById(R.id.cardView);
        cardView.setOnLongClickListener(new LongClickListener());

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ClickListener());
        button.setOnLongClickListener(new LongClickListener());

        // set view for dragging
        moveableLayout.setChild(cardView);
    }

    private class LongClickListener implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v)
        {
            moveableLayout.setIsDraggable(true);
            return false;
        }
    }

    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            counter++;
            textView.setText("Long click to drag\n" + counter);
        }
    }
}
