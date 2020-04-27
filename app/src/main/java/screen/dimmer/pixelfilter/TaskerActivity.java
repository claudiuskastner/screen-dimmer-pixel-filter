package screen.dimmer.pixelfilter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TaskerActivity extends Activity {
    public static final String LOG = "Pixel Filter"; //NON-NLS

    public static final String EXTRA_BUNDLE = "com.twofortyfouram.locale.intent.extra.BUNDLE"; //NON-NLS
    public static final String EXTRA_STRING_BLURB = "com.twofortyfouram.locale.intent.extra.BLURB"; //NON-NLS
    public static final String BUNDLE_PATTERN = "pattern"; //NON-NLS

    int pattern = 0;
    String[] PatternNamesWithOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cfg.Init(this);

        setContentView(R.layout.activity_tasker);

        pattern = Cfg.Pattern;
        PatternNamesWithOff = new String[Grids.PatternNames.length + 1];
        System.arraycopy(Grids.PatternNames, 0, PatternNamesWithOff, 0, Grids.PatternNames.length);
        PatternNamesWithOff[Grids.PatternNames.length] = getString(R.string.turn_off);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, PatternNamesWithOff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(pattern);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pattern = (int) id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void okay(View v)
    {
        final Intent resultIntent = new Intent();
        final Bundle resultBundle = new Bundle();
        resultBundle.putInt(BUNDLE_PATTERN, pattern);
        resultIntent.putExtra(EXTRA_BUNDLE, resultBundle);
        resultIntent.putExtra(EXTRA_STRING_BLURB, PatternNamesWithOff[pattern]);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancel(View v)
    {
        finish();
    }
}
