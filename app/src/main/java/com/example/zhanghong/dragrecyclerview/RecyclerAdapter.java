package com.example.zhanghong.dragrecyclerview;

import android.app.Service;
import android.content.Context;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhanghong on 2017/10/31.
 */

/**
 * 编辑页面 recycle adapter
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mAlreadyChooseCategoryList = new ArrayList<>();

    private List<String> mRemainCategoryList = new ArrayList<>();

    private Context mContext;

    private String mSecondCategoryName;

    private static final int TYPE_ALREADY_CHOOSE = 0;

    private static final int TYPE_REMAIN_LIST = 1;

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    public RecyclerAdapter(Context context, String secondCategoryName) {
        this(context);
        mSecondCategoryName = secondCategoryName;
    }

    public List<String> getSelectChooseString() {
        return mAlreadyChooseCategoryList;
    }

    public List<String> getRemainStringList() {
        return mRemainCategoryList;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        mSecondCategoryName = secondCategoryName;
    }

    private View initItemLayoutInflater(ViewGroup parent, int resourse) {
        return LayoutInflater.from(parent.getContext()).inflate(resourse, parent, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ALREADY_CHOOSE: {
                return new AlreadyChooseViewHolder(initItemLayoutInflater(parent, R.layout.item_already_choose_category));
            }
            case TYPE_REMAIN_LIST: {
                return new RemainCategoryListViewHolder(initItemLayoutInflater(parent, R.layout.item_reamin_list));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlreadyChooseViewHolder) {
            AlreadyChooseViewHolder alreadyChooseViewHolder = (AlreadyChooseViewHolder) holder;
            AlreadyChooseGridAdapter alreadyChooseGridAdapter = null;
            if (alreadyChooseViewHolder.mAlreadyChooseGridView.getAdapter() == null) {
                alreadyChooseGridAdapter = new AlreadyChooseGridAdapter(mContext);
                alreadyChooseViewHolder.mAlreadyChooseGridView.setAdapter(alreadyChooseGridAdapter);
            } else {
                alreadyChooseGridAdapter = (AlreadyChooseGridAdapter) alreadyChooseViewHolder.mAlreadyChooseGridView.getAdapter();
            }
            alreadyChooseGridAdapter.setData(mAlreadyChooseCategoryList);
            alreadyChooseGridAdapter.setOnCloseViewClickListener(new AlreadyChooseGridAdapter.OnCloseViewClickListener() {
                @Override
                public void onCloseViewClick(int position) {
                    String String = mAlreadyChooseCategoryList.get(position);
                    mAlreadyChooseCategoryList.remove(position);
                    mRemainCategoryList.add(String);
                    notifyDataSetChanged();
                }
            });
            alreadyChooseGridAdapter.notifyDataSetChanged();
        } else if (holder instanceof RemainCategoryListViewHolder) {
            RemainCategoryListViewHolder remainCategoryListViewHolder = (RemainCategoryListViewHolder) holder;

            RemainListGridAdapter remainListGridAdapter = null;
            if (remainCategoryListViewHolder.mRemainListGridView.getAdapter() == null) {
                remainListGridAdapter = new RemainListGridAdapter(mContext);
                remainCategoryListViewHolder.mRemainListGridView.setAdapter(remainListGridAdapter);
            } else {
                remainListGridAdapter = (RemainListGridAdapter) remainCategoryListViewHolder.mRemainListGridView.getAdapter();
            }
            remainCategoryListViewHolder.mRemainListTitleView.setText(mSecondCategoryName);
            remainListGridAdapter.setData(mRemainCategoryList);
            remainListGridAdapter.setOnRemainItemViewClickListener(new RemainListGridAdapter.OnRemainItemViewClickListener() {
                @Override
                public void onRemainItemViewClick(int position) {
                    String String = mRemainCategoryList.get(position);
                    mRemainCategoryList.remove(position);
                    mAlreadyChooseCategoryList.add(String);
                    notifyDataSetChanged();
                }
            });
            remainListGridAdapter.notifyDataSetChanged();
        }
    }

    public void setDatas(List<String> alreadyChooseCategoryList, List<String> remainCategoryList) {
        if (mAlreadyChooseCategoryList != null) {
            mAlreadyChooseCategoryList.clear();
        }
        if (mRemainCategoryList != null) {
            mRemainCategoryList.clear();
        }
        if (alreadyChooseCategoryList != null) {
            assert mAlreadyChooseCategoryList != null;
            mAlreadyChooseCategoryList.addAll(alreadyChooseCategoryList);
        }
        if (remainCategoryList != null) {
            assert mRemainCategoryList != null;
            mRemainCategoryList.addAll(remainCategoryList);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ALREADY_CHOOSE;
        } else {
            return TYPE_REMAIN_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    private ItemTouchHelper mItemTouchHelper;

    public class AlreadyChooseViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView mAlreadyChooseGridView;

        public AlreadyChooseViewHolder(View itemView) {
            super(itemView);

            mAlreadyChooseGridView = (RecyclerView) itemView.findViewById(R.id.already_choose_sub_recycler_view);
            RecyclerView.LayoutManager manager = new GridLayoutManager(mContext, 4);
            manager.setAutoMeasureEnabled(true);
            mAlreadyChooseGridView.addItemDecoration(new GridDividerDecoration(mContext));
            mAlreadyChooseGridView.setLayoutManager(manager);

            mAlreadyChooseGridView.addOnItemTouchListener(new OnRecyclerItemClickListener(mAlreadyChooseGridView) {
                @Override
                public void onItemClick(RecyclerView.ViewHolder vh) {

                }

                @Override
                public void onItemLongClick(RecyclerView.ViewHolder vh) {
                    if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                        mItemTouchHelper.startDrag(vh);

                        //获取系统震动服务
                        Vibrator vib = (Vibrator) mContext.getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                        vib.vibrate(70);

                    }
                }
            });
            mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
                @Override
                public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    //得到当拖拽的viewHolder的Position
                    int fromPosition = viewHolder.getAdapterPosition();
                    //拿到当前拖拽到的item的viewHolder
                    int toPosition = target.getAdapterPosition();
                    if (fromPosition < toPosition) {
                        for (int i = fromPosition; i < toPosition; i++) {
                            Collections.swap(mAlreadyChooseCategoryList, i, i + 1);
                        }
                    } else {
                        for (int i = fromPosition; i > toPosition; i--) {
                            Collections.swap(mAlreadyChooseCategoryList, i, i - 1);
                        }
                    }
                    mAlreadyChooseGridView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                }

                @Override
                public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                        viewHolder.itemView.setSelected(true);
                    }
                    super.onSelectedChanged(viewHolder, actionState);
                }

                @Override
                public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    super.clearView(recyclerView, viewHolder);
                    viewHolder.itemView.setSelected(false);
                }
            });

            mItemTouchHelper.attachToRecyclerView(mAlreadyChooseGridView);
        }
    }

    public class RemainCategoryListViewHolder extends RecyclerView.ViewHolder {

        public TextView mRemainListTitleView;

        public RecyclerView mRemainListGridView;

        public RemainCategoryListViewHolder(View itemView) {
            super(itemView);
            mRemainListTitleView = (TextView) itemView.findViewById(R.id.remain_list_title_view);
            mRemainListGridView = (RecyclerView) itemView.findViewById(R.id.remain_list_sub_recycler_view);
            RecyclerView.LayoutManager manager = new GridLayoutManager(mContext, 4);
            manager.setAutoMeasureEnabled(true);
            mRemainListGridView.addItemDecoration(new GridDividerDecoration(mContext));
            mRemainListGridView.setLayoutManager(manager);
        }

    }

    private static class GridDividerDecoration extends RecyclerView.ItemDecoration {

        private int mInsets;

        public GridDividerDecoration(Context context) {
            mInsets = Utils.dip2px(context, 5);
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //We can supply forced insets for each item view here in the Rect
            if (parent.getChildAdapterPosition(view) % 4 == 0) {
                outRect.set(0, mInsets, mInsets, mInsets);
            } else if ((parent.getChildAdapterPosition(view) + 1) % 4 == 0) {
                outRect.set(mInsets, mInsets, 0, mInsets);
            } else {
                outRect.set(mInsets, mInsets, mInsets, mInsets);
            }
        }
    }
}
