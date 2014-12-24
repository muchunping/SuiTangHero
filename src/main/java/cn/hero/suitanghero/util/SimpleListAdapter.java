package cn.hero.suitanghero.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

/**
 * 简易的ListAdapter
 * Created by chunping on 2014/9/12.
 */
public class SimpleListAdapter<T> extends BaseAdapter {
    private List<T> datas;
    private Context context;
    private int layoutResId;

    public SimpleListAdapter(Context cxt, int layoutId, List<T> list) {
        datas = list;
        context = cxt;
        layoutResId = layoutId;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (datas != null) count = datas.size();
        return count;
    }

    public void addAll(Collection<T> collections) {
        datas.addAll(collections);
    }

    public void clear() {
        datas.clear();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = newView(viewGroup);
        bindView(i, view, viewGroup, datas.get(i));
        return view;
    }

    public View newView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(layoutResId, viewGroup, false);
    }

    public void bindView(int i, View view, ViewGroup parent, T t) {

    }
}
