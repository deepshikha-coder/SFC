package deepshikha.jangidyahoo.sfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class mainActivity extends AppCompatActivity {

    private TextView tvnext;
    private ViewPager viewPager;
    private LinearLayout layoutdots;
    private IntroPref introPref;
    private int[] layouts;
    private TextView[] dots;
    private MyViewPageAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       introPref = new IntroPref(this);
       if (!introPref.isFirstTimeLaunch()){
            launchhomescreen();
            finish();
        }

        tvnext = findViewById(R.id.tvnext);
        viewPager=findViewById(R.id.viewpager);
        layoutdots=findViewById(R.id.layoutdots);



        layouts = new int[]{
                R.layout.intro1,
                R.layout.intro2,
                R.layout.intro3
        };
        tvnext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                int current = getitem(+1);
                if (current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else {
                    launchhomescreen();
                }
            }
        });
        viewPagerAdapter = new MyViewPageAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        addBottomDots(0);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position){
            addBottomDots(position);
            if (position == layouts.length -1){
                tvnext.setVisibility(View.VISIBLE);
            }else{
                tvnext.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];
        int[] activeColors = getResources().getIntArray(R.array.active);
        int[] inActiveColors = getResources().getIntArray(R.array.inactive);
        layoutdots.removeAllViews();


        for (int i=0; i< dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(inActiveColors[currentPage]);
            layoutdots.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[currentPage].setTextColor(activeColors[currentPage]);
        }
    }
    public class MyViewPageAdapter extends PagerAdapter{

        LayoutInflater layoutInflater;

        public MyViewPageAdapter(){


        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
          View view = (View) object;
          container.removeView(view);
        }
    }
    private int getitem(int i){
        return viewPager.getCurrentItem() +1;
    }

    private void launchhomescreen () {
        introPref.setIsFirstTimeLaunch(false);
        startActivity(new Intent(mainActivity.this,phoneauth.class));
        finish();
    }

}
