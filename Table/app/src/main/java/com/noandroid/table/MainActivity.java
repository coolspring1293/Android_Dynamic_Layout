package com.noandroid.table;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private static int id = 100;
    private Button bt;
    private int count = 0;
    private HashMap<Integer, AutoCompleteTextView> hm = new HashMap<>();

    LinearLayout lin = null;
    LinearLayout.LayoutParams LP_FW = null;
    RelativeLayout newSingleRL = null;

    TextView tt;


    //login
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.add);

        tt = (TextView) findViewById(R.id.tttt);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUI();
            }
        });

        lin = (LinearLayout) findViewById(R.id.list_Lin);
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        newSingleRL = new RelativeLayout(this);

        CreateUI();


//		final LinearLayout root = (LinearLayout) findViewById(R.id.layout_root); //获取总根结点
//		setContentView(root); //这里必须是总根结点
    }


    private void CreateUI() {
        count ++;
        newSingleRL = generateSingleLayout(id);
        lin.addView(newSingleRL, LP_FW);
        getInfo();
    }

    private RelativeLayout generateSingleLayout(int imageID) {
        final RelativeLayout layout_root_relative = new RelativeLayout(this);

        LinearLayout layout_sub_Lin = new LinearLayout(this);
        layout_sub_Lin.setBackgroundColor(Color.argb(0xff, 0xaa, 0xaa, 0xaa));
        layout_sub_Lin.setOrientation(LinearLayout.VERTICAL);
        layout_sub_Lin.setPadding(10, 10, 10, 10);


        final AutoCompleteTextView actv = new AutoCompleteTextView(this);
        LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        actv.setTextColor(Color.argb(0xff, 0x00, 0x00, 0x00));

        actv.setTextSize(20);
        actv.setSingleLine();

        actv.setWidth(960);
        actv.setHeight(120);

        actv.setId(View.generateViewId());;


        actv.setLayoutParams(LP_WW);




        layout_sub_Lin.addView(actv);

        RelativeLayout.LayoutParams RL_MW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);//尤其注意这个位置，用的是父容器的布局参数
        //RL_MW.setMargins(5, 5, 10, 5);
        RL_MW.addRule(RelativeLayout.ALIGN_PARENT_LEFT, imageID);
        layout_root_relative.addView(layout_sub_Lin, RL_MW);





        ImageButton imageView = new ImageButton(this);
        RelativeLayout.LayoutParams RL_WW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        imageView.setId(View.generateViewId());
        RL_WW.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        RL_WW.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(RL_WW);
        imageView.setClickable(true);
        imageView.setId(View.generateViewId());
        imageView.setBackgroundColor(Color.argb(0x00, 0xff, 0xff, 0xff));
        imageView.setImageResource(R.drawable.ic_remove_circle_24dp);

        actv.setText("");




        hm.put(count, actv);
        final int tmp = count;


        layout_root_relative.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                hm.remove(tmp);

                Toast.makeText(getApplicationContext(), "Remove: " + count,
                        Toast.LENGTH_SHORT).show();
                getInfo();
                layout_root_relative.setVisibility(View.GONE);

            }
        });
        return layout_root_relative;



    }

    public void getInfo() {
        String ss = "The hashmap: \n";
        Iterator iter = hm.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, AutoCompleteTextView> entry = (Map.Entry<Integer, AutoCompleteTextView>) iter.next();
            Integer key = entry.getKey();
            AutoCompleteTextView val = entry.getValue();
            ss = ss + ( "|" + key + "----" + val.getText() + "|\n");
        }
        tt.setText(ss);
    }

}
