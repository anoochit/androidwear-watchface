package net.redlinesoft.hellowear;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();
        if (id == R.id.action_share) {
            // share to another app
            shareToText();            
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareToText() {

        String shareString = getString(R.string.share_string);
        PackageInfo packageInfo;
        try {
            // get package info
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            shareString = packageInfo.packageName;
            
            // intent
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareString);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
