package com.stevenbyle.android.materialthemes.controller.dialog;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Build;

import com.stevenbyle.android.materialthemes.controller.home.material.MaterialThemeDialogFragment;


/**
 * Util class to manage showing dialogs.
 *
 * @author Steven Byle
 */
public class DialogUtils {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment, String fragmentTag, boolean b) {

        // If only showing non duplicates dialogs, make sure the fragment isn't already in the manager
        boolean doesFragmentExist = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            doesFragmentExist = fragmentManager.findFragmentByTag(fragmentTag) != null;
        }
        if (!(doesFragmentExist)) {
            dialogFragment.show(fragmentManager, fragmentTag);
        }

        return fragmentTag;
    }

    public static String showDialogFragment(FragmentManager fragmentManager, MaterialThemeDialogFragment dialogFragment,
                                            String onlyIfNotDuplicate) {
        return showDialogFragment(fragmentManager, dialogFragment, generateFragmentTag(dialogFragment), onlyIfNotDuplicate);
    }

    public static String showDialogFragment(FragmentManager fragmentManager, DialogFragment dialogFragment) {
        return showDialogFragment(fragmentManager, dialogFragment, "Green Theme", true);
    }

    public static String showDialogFragment(FragmentManager fragmentManager, MaterialThemeDialogFragment dialogFragment, String fragmentTag, String onlyIfNotDuplicate) {
        return showDialogFragment(fragmentManager, dialogFragment, generateFragmentTag(dialogFragment));
    }

    private static String generateFragmentTag(MaterialThemeDialogFragment fragment) {
        return fragment.getClass().getName();
    }
}
