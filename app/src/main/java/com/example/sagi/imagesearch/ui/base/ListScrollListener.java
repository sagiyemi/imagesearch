package com.example.sagi.imagesearch.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sagiyemini on 31/03/2018.
 */

public class ListScrollListener extends RecyclerView.OnScrollListener {

    private final PaginationListener mPaginationListener;

    public ListScrollListener(PaginationListener mPaginationListener) {
        this.mPaginationListener = mPaginationListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        final int childCount = layoutManager.getChildCount();
        final int itemCount = layoutManager.getItemCount();

        if (childCount > 0 && mPaginationListener != null) {
            final View lastChild = layoutManager.getChildAt(childCount - 1);
            final int lastVisibleItemPosition = layoutManager.getPosition(lastChild);
            mPaginationListener.onScrolled(itemCount, lastVisibleItemPosition, dy > 0);
        }
    }

    public interface PaginationListener {

        void onScrolled(int itemCount, int lastVisibleItemPosition, boolean isScrollingDown);

    }

}
