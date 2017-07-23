package productiveapps.com.foodplanner;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends BaseActivity {

    private RecyclerView recyclerView;
    private IngredientsAdapter adapter;
    private List<Food> ingredientList = new ArrayList<>();

    @Override
    int getContentViewId() {
        return R.layout.activity_ingredients;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_ingredients;
    }

    @Override
    protected void showContent() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ingredients);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_ingredients);
        adapter = new IngredientsAdapter(this, ingredientList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareFoodItems();

        try{
            Glide.with(this).load(R.drawable.ingredients_cover).into((ImageView) findViewById(R.id.backdrop_ingredients));
        }catch (Exception e){
            e.printStackTrace();
        }

        //click action for cover photo
        findViewById(R.id.backdrop_ingredients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show current to make recipe
                Toast.makeText(getBaseContext(), "Now Recipie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_ingredients);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_ingredients);
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
                R.drawable.bokchoy,
                R.drawable.cherry_tomatoes,
                R.drawable.drumsticks,
                R.drawable.lettuce,
                R.drawable.mushroom,
                R.drawable.onion,
                R.drawable.raddish,
                R.drawable.rocket_leaves,
                R.drawable.snake_beans,
                R.drawable.spinach,
                R.drawable.spring_onion};

        Food a = new Food("Bokchoy", 13, covers[0], "001");
        ingredientList.add(a);

        a = new Food("Cherry Tomatoes", 8, covers[1], "002");
        ingredientList.add(a);

        a = new Food("Drumsticks", 11, covers[2], "003");
        ingredientList.add(a);

        a = new Food("Lettuce", 12, covers[3], "004");
        ingredientList.add(a);

        a = new Food("Mushroom", 14, covers[4], "005");
        ingredientList.add(a);

        a = new Food("Onions", 1, covers[5], "006");
        ingredientList.add(a);

        a = new Food("Raddish", 11, covers[6], "007");
        ingredientList.add(a);

        a = new Food("Rocket Leaves", 14, covers[7], "008");
        ingredientList.add(a);

        a = new Food("Snake Beans", 11, covers[8], "009");
        ingredientList.add(a);

        a = new Food("Spring Onion", 17, covers[9], "010");
        ingredientList.add(a);

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
