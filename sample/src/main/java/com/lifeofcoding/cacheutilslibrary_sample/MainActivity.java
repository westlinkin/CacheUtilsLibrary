package com.lifeofcoding.cacheutilslibrary_sample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lifeofcoding.cacheutlislibrary.CacheUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String CACHE_FILE_STRING = "cache_file_string";
    private static final String CACHE_FILE_MAP = "cache_file_map";
    private static final String CACHE_FILE_LIST_MAP = "cache_file_list_map";
    private static final String CACHE_FILE_OBJECT = "cache_file_object";
    private static final String CACHE_FILE_LIST_OBJECT = "cache_file_list_object";

    private static final String CACHE_FILE_CONTENT_STRING = "CacheUtilsLibrary is a simple util library to write and read cache files in Android.";
    private static Map<String, Object> getCacheFileContentMap() {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("firstItem", "item0");
        mapData.put("secondItem", 1);
        mapData.put("thirdItem", false);
        return mapData;
    }
    private static List<Map<String, Object>> getCacheFileContentListMap() {
        List<Map<String, Object>> listMapData = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 4; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("firstItemAt" + i, "item0At" + i);
            item.put("secondItemAt" + i, 1 + i);
            item.put("thirdItemAt" + i, i % 2 == 0);
            listMapData.add(item);
        }
        return listMapData;
    }
    private static List<MyClass> getCacheFileContentListObject() {
        List<MyClass> listObject = new ArrayList<>();
        listObject.add(MyClass.SAMPLE_MYCLASS_1);
        listObject.add(MyClass.SAMPLE_MYCLASS_2);
        listObject.add(MyClass.SAMPLE_MYCLASS_3);
        return listObject;
    }


    private RelativeLayout rootLayout;
    private int radioCheckId = R.id.string;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (RelativeLayout) findViewById(R.id.root);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Button writeButton = (Button) findViewById(R.id.write);
        Button readButton = (Button) findViewById(R.id.read);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioCheckId = checkedId;
            }
        });
        writeButton.setOnClickListener(writeOnClickListener);
        readButton.setOnClickListener(readOnClickListener);
    }

    private View.OnClickListener writeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (radioCheckId == R.id.string) {
                CacheUtils.writeFile(CACHE_FILE_STRING, CACHE_FILE_CONTENT_STRING);
                Snackbar.make(rootLayout, "Write a String into cache file", Snackbar.LENGTH_SHORT).show();

            } else if (radioCheckId == R.id.map) {
                CacheUtils.writeDataMapFile(CACHE_FILE_MAP, getCacheFileContentMap());
                Snackbar.make(rootLayout, "Write a Map<String, T> into cache file", Snackbar.LENGTH_SHORT).show();

            } else if (radioCheckId == R.id.listmap) {
                CacheUtils.writeDataMapsFile(CACHE_FILE_LIST_MAP, getCacheFileContentListMap());
                Snackbar.make(rootLayout, "Write a List<Map<String, T>> into cache file", Snackbar.LENGTH_SHORT).show();

            } else if (radioCheckId == R.id.object) {
                CacheUtils.writeObjectFile(CACHE_FILE_OBJECT, MyClass.SAMPLE_MYCLASS_1);
                Snackbar.make(rootLayout, "Write a MyClass into cache file", Snackbar.LENGTH_SHORT).show();

            } else if (radioCheckId == R.id.listobject) {
                CacheUtils.writeObjectFile(CACHE_FILE_LIST_OBJECT, getCacheFileContentListObject());
                Snackbar.make(rootLayout, "Write a List<MyClass> into cache file", Snackbar.LENGTH_SHORT).show();

            }
        }
    };

    private View.OnClickListener readOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (radioCheckId == R.id.string) {
                String fileContent = CacheUtils.readFile(CACHE_FILE_STRING);
                snackbar = Snackbar.make(rootLayout, fileContent == null ? "No file" : fileContent, Snackbar.LENGTH_INDEFINITE);
            } else if (radioCheckId == R.id.map) {
                Map<String, Object> mapData = CacheUtils.readDataMapFile(CACHE_FILE_MAP);
                snackbar = Snackbar.make(rootLayout, mapData.isEmpty() ? "No file or empty map" : mapData.toString(), Snackbar.LENGTH_INDEFINITE);
            } else if (radioCheckId == R.id.listmap) {
                List<Map<String, Object>> listMapData = CacheUtils.readDataMapsFile(CACHE_FILE_LIST_MAP);
                snackbar = Snackbar.make(rootLayout, listMapData.isEmpty() ? "No file or empty map" : listMapData.toString(), Snackbar.LENGTH_INDEFINITE);
            } else if (radioCheckId == R.id.object) {
                MyClass myClassSample = CacheUtils.readObjectFile(CACHE_FILE_OBJECT, new TypeToken<MyClass>(){}.getType());
                snackbar = Snackbar.make(rootLayout, myClassSample == null ? "No file" : myClassSample.toString(), Snackbar.LENGTH_INDEFINITE);

            } else if (radioCheckId == R.id.listobject) {
                List<MyClass> myClassList = CacheUtils.readObjectFile(CACHE_FILE_LIST_OBJECT, new TypeToken<List<MyClass>>(){}.getType());
                snackbar = Snackbar.make(rootLayout, myClassList == null ? "No file" : myClassList.toString(), Snackbar.LENGTH_INDEFINITE);

            }

            if (snackbar == null)
                return;
            TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(8);
            snackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
