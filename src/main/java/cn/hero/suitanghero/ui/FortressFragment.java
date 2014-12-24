package cn.hero.suitanghero.ui;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hero.suitanghero.R;
import cn.hero.suitanghero.model.Building;
import cn.hero.suitanghero.util.SimpleListAdapter;
import cn.hero.suitanghero.util.XmlResourceParserUtils;

/**
 * Fortress
 * Created by hp on 2014/12/12.
 */
public class FortressFragment extends Fragment {
    private SimpleListAdapter<Building> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fortress, container, false);
        ((TextView)rootView.findViewById(R.id.titleView)).setTypeface(((MainActivity)getActivity()).getTypeface());
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter = new SimpleListAdapter<Building>(getActivity(), R.layout.row_building, new ArrayList<Building>()){
            @Override
            public View newView(ViewGroup viewGroup) {
                View view = super.newView(viewGroup);
                ViewHolder holder =new ViewHolder();
                holder.iconView = (ImageView) view.findViewById(R.id.iconView);
                holder.nameView = (TextView) view.findViewById(R.id.nameView);
                holder.nameView.setTypeface(((MainActivity)getActivity()).getTypeface());
                holder.levelView = (TextView) view.findViewById(R.id.levelView);
                holder.levelView.setTypeface(((MainActivity)getActivity()).getTypeface());
                holder.descriptionView = (TextView) view.findViewById(R.id.descriptionView);
                holder.descriptionView.setTypeface(((MainActivity)getActivity()).getTypeface());
                holder.button = (Button) view.findViewById(R.id.button);
                holder.button.setTypeface(((MainActivity)getActivity()).getTypeface());
                view.setTag(holder);
                return view;
            }

            @Override
            public void bindView(int i, View view, ViewGroup parent, Building building) {
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.nameView.setText(building.nameRes);
                holder.levelView.setText("等级 " + building.level);
                holder.descriptionView.setText(building.descriptionRes);
                holder.iconView.setImageResource(building.iconRes);
                holder.button.setText("升级");
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parserBuildingXML();
    }

    private void parserBuildingXML(){
        List<Building> buildingList = new ArrayList<Building>();
        XmlResourceParser xrp = getResources().getXml(R.xml.buildings);
        try {
            int eventType;
            while ((eventType = xrp.next()) != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    String s = xrp.getName();
                    if(s.equals("building")){
                        Building building = new Building();
                        building.createFromXml(xrp);
                        buildingList.add(building);
                    }
                }
            }
            adapter.clear();
            adapter.addAll(buildingList);
            adapter.notifyDataSetChanged();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ViewHolder{
        private TextView nameView;
        private ImageView iconView;
        private TextView levelView;
        private TextView descriptionView;
        private Button button;
    }
}
