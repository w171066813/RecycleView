package com.example.cwang.recycleview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by cwang on 2019/1/21.
 */

public class RecycleViewHelp {

    String TAG = "RecycleViewHelp";
    MyAdapter myAdapter;

    public RecycleViewHelp(MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int swipeFlag = ItemTouchHelper.START|ItemTouchHelper.END;
            int drawFlag = ItemTouchHelper.RIGHT|1|2|4;
            return makeMovementFlags(drawFlag, 0);
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            Log.d(TAG, "isItemViewSwipeEnabled: ");
            return true ;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.d(TAG, "onMove: ");
//            recyclerView.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//                @Override
//                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
//                    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
//                }
//            });
            myAdapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            myAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //
            Log.d(TAG, "onSwiped: ");
        }

        @Override
        public boolean isLongPressDragEnabled() {
            Log.d(TAG, "isLongPressDragEnabled: ");
            return super.isLongPressDragEnabled();
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            Log.d(TAG, "onSelectedChanged: "+actionState);
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            Log.d(TAG, "clearView: ");
            myAdapter.notifyDataSetChanged();
            super.clearView(recyclerView, viewHolder);
        }

    });
    public void attacthRecycle(RecyclerView rv){
        itemTouchHelper.attachToRecyclerView(rv);
    }

}
