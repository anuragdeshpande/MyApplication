package productiveapps.com.foodplanner;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<Food> foodList = new ArrayList<>();

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_food;
    }

    @Override
    protected void showContent() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_main);
        adapter = new FoodAdapter(this, foodList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareFoodItems();

        try{
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop_main));
        }catch (Exception e){
            e.printStackTrace();
        }

        //click action for cover photo
        findViewById(R.id.backdrop_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show current to make recipe
                Toast.makeText(getBaseContext(), "Now Recipie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_main);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_main);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if(scrollRange+verticalOffset == 0 ){
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                }else if(isShow){
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    /**
     * Adding few albums for testing
     */
    private void prepareFoodItems() {
        int[] covers = new int[]{
                R.drawable.food1,
                R.drawable.food2,
                R.drawable.food3,
                R.drawable.food4,
                R.drawable.food5,
                R.drawable.food6,
                R.drawable.food7,
                R.drawable.food8,
                R.drawable.food9,
                R.drawable.food10,
                R.drawable.food11};

        Food a = new Food("True Romance", 13, covers[0], "001");
        foodList.add(a);

        a = new Food("Xscpae", 8, covers[1] , "002");
        foodList.add(a);

        a = new Food("Maroon 5", 11, covers[2], "003");
        foodList.add(a);

        a = new Food("Born to Die", 12, covers[3], "004");
        foodList.add(a);

        a = new Food("Honeymoon", 14, covers[4], "005");
        foodList.add(a);

        a = new Food("I Need a Doctor", 1, covers[5], "006");
        foodList.add(a);

        a = new Food("Loud", 11, covers[6], "007");
        foodList.add(a);

        a = new Food("Legend", 14, covers[7], "008");
        foodList.add(a);

        a = new Food("Hello", 11, covers[8], "009");
        foodList.add(a);

        a = new Food("Greatest Hits", 17, covers[9], "010");
        foodList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
