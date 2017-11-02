package com.example.zhanghong.dragrecyclerview;

/**
 * Created by zhanghong on 2017/10/31.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * 编辑 下半部分grid adapter
 */
public class RemainListGridAdapter extends AbstractBaseRecycleViewAdapter<String> {

    public interface OnRemainItemViewClickListener {
        public void onRemainItemViewClick(int position);
    }

    private OnRemainItemViewClickListener mOnRemainItemViewClickListener;

    public void setOnRemainItemViewClickListener(OnRemainItemViewClickListener onRemainItemViewClickListener) {
        mOnRemainItemViewClickListener = onRemainItemViewClickListener;
    }

    private View initItemLayoutInflater(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

    public RemainListGridAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RemainDetailViewHolder(initItemLayoutInflater(parent, R.layout.item_detail_remain_layout));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RemainDetailViewHolder remainDetailViewHolder = (RemainDetailViewHolder) holder;
        remainDetailViewHolder.itemTextView.setText("+ " + mDatas.get(position));
        remainDetailViewHolder.itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRemainItemViewClickListener != null) {
                    mOnRemainItemViewClickListener.onRemainItemViewClick(position);
                }
            }
        });
    }

    public class RemainDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTextView;

        public RemainDetailViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.item_edit_remain_choose_view);
        }
    }
}
