package screen.dimmer.pixelfilter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public final class TaskerReceiver extends BroadcastReceiver
{
    public static final String LOG = "Pixel Filter"; //NON-NLS

    @Override
    public void onReceive(final Context context, final Intent intent)
    {
        final Bundle bundle = intent.getBundleExtra(TaskerActivity.EXTRA_BUNDLE);
        if (bundle == null) {
            return;
        }

        int pattern = bundle.getInt(TaskerActivity.BUNDLE_PATTERN, Grids.PatternNames.length);
        Log.d(LOG, "Tasker: received pattern " + pattern); //NON-NLS

        Intent service = new Intent(context, FilterService.class);
        if (pattern >= Grids.PatternNames.length) {
            Log.d(LOG, "Tasker: disabling service"); //NON-NLS
            context.stopService(service);
        } else {
            service.putExtra(TaskerActivity.BUNDLE_PATTERN, pattern);
            context.startService(service);
        }
    }
}
