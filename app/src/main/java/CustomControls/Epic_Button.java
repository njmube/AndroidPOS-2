package CustomControls;

import android.util.AttributeSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import android.widget.Button;

import www.epicmyanmar.com.andropos.R;


/**
 * Created by jr on 8/10/14.
 */
public class Epic_Button extends Button {
    public Epic_Button(Context context, AttributeSet attrs, int defStyle){

        super(context,attrs,defStyle);
        init(attrs);
    }

    public Epic_Button(Context context, AttributeSet attrs){

        super(context,attrs);
        init(attrs);
    }

    public Epic_Button(Context context){
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
