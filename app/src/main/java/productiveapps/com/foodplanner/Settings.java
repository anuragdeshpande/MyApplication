package productiveapps.com.foodplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Settings extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_settings;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_settings;
    }

    @Override
    void showContent() {
        Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show();
    }
}
