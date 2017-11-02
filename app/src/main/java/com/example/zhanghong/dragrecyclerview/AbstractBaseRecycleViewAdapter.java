
package com.example.zhanghong.dragrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ArrayList<T> mDatas = new ArrayList<T>();

    protected Context mContext;

    public AbstractBaseRecycleViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<T> datas) {
        this.mDatas.clear();
        if (datas != null) {
            this.mDatas.addAll(datas);
        }
    }

    public void setData(T[] datas) {
        this.mDatas.clear();
        if (datas != null) {
            for (T t : datas) {
                this.mDatas.add(t);
            }
        }
    }

    public void addData(List<T> datas) {
        if (datas != null && datas.size() > 0) {
            this.mDatas.addAll(datas);
        }
    }

    public void addData(T[] datas) {
        if (datas != null && datas.length > 0) {
            for (T data : datas) {
                this.mDatas.add(data);
            }
        }
    }

    public void addData(T data) {
        if (data != null) {
            this.mDatas.add(data);
        }
    }

    public void addData(int index, T data) {
        if (index >= 0 && index <= mDatas.size()) {
            if (data != null) {
                this.mDatas.add(index, data);
            }
        }
    }

    public void addData(int index, List<T> datas) {
        if (index >= 0 && index <= mDatas.size()) {
            if (datas != null && datas.size() > 0) {
                this.mDatas.addAll(index, datas);
            }
        }
    }

    public void removeData(T data) {
        if (data != null) {
            this.mDatas.remove(data);
        }
    }

    public void removeData(int index) {
        if (index >= 0 && index < mDatas.size()) {
            this.mDatas.remove(index);
        }
    }

    public void clearData() {
        this.mDatas.clear();
    }

    public ArrayList<T> getDatas() {
        return this.mDatas;
    }
    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }

    public T getItem(int position) {
        if (position >= 0 && position < this.mDatas.size()) {
            return this.mDatas.get(position);
        } else {
            return null;
        }
    }

}
