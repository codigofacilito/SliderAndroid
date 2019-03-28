package com.codigofacilito.mg.slider;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdaoter adapter;
    private LinearLayout dotsLayout;
    private Button btnBack,btnNext;

    private String[]content={"hola1","hola2","hola3","hola4"};
    private String[]title={"1","2","3","4"};
    private int[]image={R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};
    private int[] colorsBackgroud,colorDot;
    private TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorDot=getResources().getIntArray(R.array.array_dot);
        colorsBackgroud=getResources().getIntArray(R.array.array_background);
        viewPager=findViewById(R.id.viewPager);
        btnBack=findViewById(R.id.btnBack);
        btnNext=findViewById(R.id.btnNext);
        dotsLayout=findViewById(R.id.layoutDots);
        addDots(0);
        loadViewPager();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==title.length-1){
                    finish();
                }else{
                    int back=getItem(-1);
                    viewPager.setCurrentItem(back);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next=getItem(+1);
                if(next<title.length) {
                    viewPager.setCurrentItem(next);
                }else{
                    Toast.makeText(getApplicationContext(),"Obtener Promocion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }
    private void addDots(int currentPage){
        dots=new TextView[title.length];

        dotsLayout.removeAllViews();

        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if(i==currentPage)
                dots[i].setTextColor(colorDot[currentPage]);
            else
                dots[i].setTextColor(Color.LTGRAY);

            dotsLayout.addView(dots[i]);
        }
    }

    private void loadViewPager(){
        adapter=new MyViewPagerAdaoter(getSupportFragmentManager());
        for(int i=0;i<title.length;i++){
            adapter.addFragment(newInstance(title[i],content[i],image[i],colorsBackgroud[i]));
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pagerListener);
    }

    private SliderFragment newInstance(String title,String content,int image,int color){
      Bundle bundle=new Bundle();
      bundle.putString("title",title);
        bundle.putString("content",content);
        bundle.putInt("image",image);
        bundle.putInt("color",color);
        SliderFragment fragment=new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    ViewPager.OnPageChangeListener pagerListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if(position==title.length-1){
                btnNext.setText("Obtener");
                btnBack.setText("Salir");
            }else{
                btnNext.setText("Siguiente");
                btnBack.setText("Atras");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
