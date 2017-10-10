package com.shacharsoftware.rtlmarqueeview;

import android.content.Context;
import android.os.Build;

import java.util.Locale;

import static com.shacharsoftware.rtlmarqueeview.ComponentOrientation.LEFT_TO_RIGHT;
import static com.shacharsoftware.rtlmarqueeview.ComponentOrientation.RIGHT_TO_LEFT;

class Checks {
    public static boolean areAllNotNull(Object... objects){
        for(Object obj : objects){
            if(obj == null){
                return false;
            }
        }
        return true;
    }

    private static ComponentOrientation getOrientation(Locale locale)
    {
        // A more flexible implementation would consult a ResourceBundle
        // to find the appropriate orientation.  Until pluggable locales
        // are introduced however, the flexiblity isn't really needed.
        // So we choose efficiency instead.
        String lang = locale.getLanguage();
        if( "iw".equals(lang) || "ar".equals(lang)
                || "fa".equals(lang) || "ur".equals(lang) )
        {
            return RIGHT_TO_LEFT;
        } else {
            return LEFT_TO_RIGHT;
        }
    }

    private static Locale getPrimaryLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        }else{
            return context.getResources().getConfiguration().locale;
        }
    }

    public static boolean isRtl(Context context){
        return getOrientation(getPrimaryLocale(context)) == RIGHT_TO_LEFT;
    }
}
