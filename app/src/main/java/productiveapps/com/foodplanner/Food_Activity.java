package productiveapps.com.foodplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Food_Activity extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_food_;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_food;
    }

    @Override
    void showContent() {
        Toast.makeText(this, "Activity Changed", Toast.LENGTH_SHORT).show();
    }
}
