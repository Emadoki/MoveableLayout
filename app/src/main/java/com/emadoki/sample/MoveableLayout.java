package com.emadoki.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

public class MoveableLayout extends RelativeLayout
{
    private View view;

    private Vector2D vectorStart;
    private Vector2D vectorInitial;
    private float CONTAINER_WIDTH;
    private float CONTAIER_HEIGHT;
    private boolean isDraggable;

    public MoveableLayout(Context context)
    {
        super(context);
        initialize();
    }

    public MoveableLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize();
    }

    public MoveableLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        CONTAINER_WIDTH = w;
        CONTAIER_HEIGHT = h;
    }

    private void initialize()
    {
        vectorStart = new Vector2D(0, 0);
        vectorInitial = new Vector2D(0, 0);

        isDraggable = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return isDraggable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                if (isDraggable)
                {
                    move(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isDraggable)
                {
                    up(x, y);
                    isDraggable = false;
                }
                break;
        }

        return true;
    }

    private void move(float x, float y)
    {
        float rx = view.getWidth() / 2f;
        float ry = view.getHeight() / 2f;

        float dx = x - vectorStart.x;
        float dy = y - vectorStart.y;

        x = vectorInitial.x + dx - rx;
        y = vectorInitial.y + dy - ry;

        if (x < 0)
        {
            x = 0;
        }
        else if (x + view.getWidth() > CONTAINER_WIDTH)
        {
            x = CONTAINER_WIDTH - view.getWidth();
        }
        if (y < 0)
        {
            y = 0;
        }
        else if (y + view.getHeight() > CONTAIER_HEIGHT)
        {
            y = CONTAIER_HEIGHT - view.getHeight();
        }

        view.setX(x);
        view.setY(y);
    }

    private void up(float x, float y)
    {
        if (x < CONTAINER_WIDTH / 2)
        {
            x = 0;
        }
        else
        {
            x = CONTAINER_WIDTH - view.getWidth();
        }

        view.animate()
                .x(x)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }
    
    public void setChild(View view)
    {
        this.view = view;
    }

    public void setIsDraggable(boolean flag)
    {
        this.isDraggable = flag;
        if (isDraggable)
        {
            if (view != null)
            {
                view.animate()
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .setDuration(250)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            }
        }
    }
}
