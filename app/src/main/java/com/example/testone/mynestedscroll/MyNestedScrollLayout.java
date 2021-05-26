package com.example.testone.mynestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class MyNestedScrollLayout extends NestedScrollView {
    private static final String TAG = "MyNestedScrollLayout";
    private ViewGroup contentView;
    private View topView;

    public MyNestedScrollLayout(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 获取第一个孩子的第一个孩子
        topView = ((ViewGroup) getChildAt(0)).getChildAt(0);
        // 获取第一个孩子的第二个孩子
        contentView = (ViewGroup)((ViewGroup)getChildAt(0)).getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 调整contentView的高度为父容器高度，使之填充布局，避免父容器滚动后出现空白
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams lp = contentView.getLayoutParams();
        lp.height = getMeasuredHeight();
        contentView.setLayoutParams(lp);
    }

    // 让父亲先滑动，至父亲完全滑动不可见，自己再滑动
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        boolean hideTop = dy > 0 && getScrollY() < topView.getMeasuredHeight();
        if (hideTop) {
            scrollBy(0, dy);
            consumed[1] = dy; // 消费了多少，如果没有，就会重复滑动
        }
    }
}
