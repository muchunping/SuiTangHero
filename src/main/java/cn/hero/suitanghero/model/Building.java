package cn.hero.suitanghero.model;

import android.content.res.XmlResourceParser;
import android.util.SparseArray;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

import cn.hero.suitanghero.R;
import cn.hero.suitanghero.util.XmlResourceParserUtils;

/**
 * 建筑物对象
 * Created by hp on 2014/12/15.
 */
public class Building {
    public int nameRes;
    public int iconRes;
    public int descriptionRes;
    public int level;
    public int levelTop;
    public SparseArray<HashMap<String, String>> upgradeMap = new SparseArray<HashMap<String, String>>();

    public void createFromXml(XmlResourceParser xrp) throws IOException, XmlPullParserException {
        nameRes = xrp.getAttributeResourceValue(null, "name", R.string.app_name);
        iconRes = xrp.getAttributeResourceValue(null, "icon", R.drawable.bg_tab_main);
        descriptionRes = xrp.getAttributeResourceValue(null, "description", R.string.app_name);
        level = xrp.getAttributeIntValue(null, "level", 0);
        levelTop = xrp.getAttributeIntValue(null, "level-top", 20);
        XmlResourceParserUtils.readCurrentTagUntilEnd(xrp, new XmlResourceParserUtils.TagHandler() {
            @Override
            public void handleTag(XmlResourceParser xrp, String tagName) throws XmlPullParserException, IOException {
                if(tagName.equals("upgrade-prerequisites-list"))
                    readUpgradePrerequisites(xrp);
            }
        });
    }

    private void readUpgradePrerequisites(XmlResourceParser xrp) throws IOException, XmlPullParserException {
        XmlResourceParserUtils.readCurrentTagUntilEnd(xrp, new XmlResourceParserUtils.TagHandler() {
            @Override
            public void handleTag(XmlResourceParser xrp, String tagName) throws XmlPullParserException, IOException {
                if (tagName.equals("level")) {
                    HashMap<String, String> prerequisites = new HashMap<String, String>();
                    readPrerequisites(xrp, prerequisites);
                }
            }
        });
    }

    private void readPrerequisites(XmlResourceParser xrp, final HashMap<String, String> prerequisites) throws IOException, XmlPullParserException {
        XmlResourceParserUtils.readCurrentTagUntilEnd(xrp, new XmlResourceParserUtils.TagHandler() {
            @Override
            public void handleTag(XmlResourceParser xrp, String tagName) throws XmlPullParserException, IOException {
                if (tagName.equals("building")) {
                    String name = xrp.getAttributeValue(null, "name");
                    String level = xrp.getAttributeValue(null, "level");
                    prerequisites.put(name, level);
                } else if(tagName.equals("user")){
                    String isNewer = xrp.getAttributeValue(null, "isnewer");
                    prerequisites.put("isNewer", isNewer);
                }
            }
        });
    }
}
