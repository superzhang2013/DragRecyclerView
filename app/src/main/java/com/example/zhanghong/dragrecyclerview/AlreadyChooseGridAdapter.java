package com.example.zhanghong.dragrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by zhanghong on 2017/10/31.
 */

/**
 * 上半部分grid adapter
 */
public class AlreadyChooseGridAdapter extends AbstractBaseRecycleViewAdapter<String> {

    public interface OnCloseViewClickListener {
        public void onCloseViewClick(int position);
    }

    public OnCloseViewClickListener mOnCloseViewClickListener;

    public void setOnCloseViewClickListener(OnCloseViewClickListener onCloseViewClickListener) {
        mOnCloseViewClickListener = onCloseViewClickListener;
    }

    private View initItemLayoutInflater(ViewGroup parent, int resourse) {
        return LayoutInflater.from(parent.getContext()).inflate(resourse, parent, false);
    }

    public AlreadyChooseGridAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlreadyChooseItemDetailViewHolder(initItemLayoutInflater(parent, R.layout.item_detail_already_select_layout));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AlreadyChooseItemDetailViewHolder alreadyChooseItemDetailViewHolder = (AlreadyChooseItemDetailViewHolder) holder;
        alreadyChooseItemDetailViewHolder.itemTextView.setText(mDatas.get(position));
        alreadyChooseItemDetailViewHolder.itemCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCloseViewClickListener != null) {
                    mOnCloseViewClickListener.onCloseViewClick(position);
                }
            }
        });
    }

    public class AlreadyChooseItemDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTextView;

        public View itemCloseView;

        public AlreadyChooseItemDetailViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.item_edit_subject_already_choose_view);
            itemCloseView = itemView.findViewById(R.id.item_edit_subject_already_close_view);
        }

    }

}
