package CustomControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import www.epicmyanmar.com.andropos.R;


/**
 * TODO: document your custom view class.
 */
public class Epic_textView extends TextView {
    public Epic_textView(Context context, AttributeSet attrs, int defStyle){

        super(context,attrs,defStyle);
        init(attrs);
    }

    public Epic_textView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public Epic_textView(Context context) {
        super(context);
        init(null);
    }

    public void init(AttributeSet attrs){
            if(attrs!=null){
                TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.font_style);

                String fontname=a.getString(R.styleable.font_style_fontName);

                if(fontname!= null){

                    Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"fonts/"+fontname);

                    setTypeface(tf);
                }
                a.recycle();
            }

    }
}
