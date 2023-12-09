package dev.bulean.tridictir;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String replaceString(String s){
        return s.replace('a','i').replace('e','i').replace('o','i').replace('u','i')
                .replace('á','í').replace('é','í').replace('ó','í').replace('ú','í')
                .replace('à','ì').replace('è','ì').replace('è','ì').replace('ù','ì')
                .replace('ä','ï').replace('ë','ï').replace('ö','ï').replace('ü','ï')
                .replace('A','I').replace('E','I').replace('O','I').replace('U','I')
                .replace('Á','Í').replace('É','Í').replace('Ó','Í').replace('Ú','Í')
                .replace('À','Ì').replace('È','Ì').replace('Ò','Ì').replace('Ù','Ì')
                .replace('Ä','Ï').replace('Ë','Ï').replace('Ö','Ï').replace('Ü','Ï');
    }

    public static void copyToClipboard(Context context, String text){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(context.getString(R.string.labelTextClip), text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, R.string.copy_data, Toast.LENGTH_SHORT).show();
    }
}
