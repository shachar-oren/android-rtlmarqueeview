package com.shacharsoftware.rtlmarqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class RtlMarqueeView extends FrameLayout {

    //region DM
    protected BaseRtlMarqueeView baseRtlMarqueeView;
    protected TextView tvMain;
    protected ImageView startFade;
    protected ImageView endFade;
    //endregion

    //region Properties
    public void setText(String text){
        tvMain.setText(text);
    }

    public String getText(){
        return tvMain.getText().toString();
    }

    public void setTextColor(int color){
        tvMain.setTextColor(color);
    }

    public int getTextColor(){
        return tvMain.getCurrentTextColor();
    }

    public void setTextSize(float size){
        tvMain.setTextSize(size);
    }

    public float getTextSize(){
        return tvMain.getTextSize();
    }

    public void setFadeToColor(int color){
        startFade.setColorFilter(color);
        startFade.setTag(color);
        endFade.setColorFilter(color);
    }

    public int getFadeToColor(){
        return (int) startFade.getTag();
    }

    public void setLooping(boolean looping){
        baseRtlMarqueeView.setLooping(looping);
    }

    public boolean getLooping(){
        return baseRtlMarqueeView.isLooping();
    }

    public void setLoops(int loops){
        baseRtlMarqueeView.setLoops(loops);
    }

    public int getLoops(){
        return baseRtlMarqueeView.getLoops();
    }

    public void setStartWaitTicks(int ticks){
        baseRtlMarqueeView.setStartWaitTicks(ticks);
    }

    public int getStartWaitTicks(){
        return baseRtlMarqueeView.getStartWaitTicks();
    }

    public void setEndWaitTicks(int ticks){
        baseRtlMarqueeView.setEndWaitTicks(ticks);
    }

    public int getEndWaitTicks(){
        return baseRtlMarqueeView.getEndWaitTicks();
    }
    //endregion

    //region ctor
    public RtlMarqueeView(Context context) {
        super(context);
        init();
    }

    public RtlMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        parseAttrs(attrs);
    }

    public RtlMarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        parseAttrs(attrs);
    }

    private void parseAttrs(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RtlMarqueeView, 0, 0);
        try{
            tvMain.setText(ta.getString(R.styleable.RtlMarqueeView_marqueeText));
            tvMain.setTextColor(ta.getColor(R.styleable.RtlMarqueeView_marqueeTextColor,
                    ContextCompat.getColor(getContext(), R.color.black_text)));
            tvMain.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    ta.getDimension(R.styleable.RtlMarqueeView_marqueeTextSize,
                    getResources().getDimension(R.dimen.default_text_size)));
            int fadeColor = ta.getColor(R.styleable.RtlMarqueeView_marqueeFadeToColor,
                    ContextCompat.getColor(getContext(),R.color.white));
            startFade.setColorFilter(fadeColor);
            endFade.setColorFilter(fadeColor);
            startFade.setTag(fadeColor);
            baseRtlMarqueeView.setLooping(ta.getBoolean(R.styleable.RtlMarqueeView_marqueeIsLooping,
                    getResources().getBoolean(R.bool.default_looping)));
            baseRtlMarqueeView.setLoops(ta.getInteger(R.styleable.RtlMarqueeView_marqueeLoops,
                    getResources().getInteger(R.integer.default_loops)));
            baseRtlMarqueeView.setStartWaitTicks(ta.getInteger(R.styleable.RtlMarqueeView_marqueeStartWaitTicks,
                    getResources().getInteger(R.integer.default_startWaitTicks)));
            baseRtlMarqueeView.setEndWaitTicks(ta.getInteger(R.styleable.RtlMarqueeView_marqueeEndWaitTicks,
                    getResources().getInteger(R.integer.default_endWaitTicks)));
        }finally {
            ta.recycle();
        }
    }

    private void init(){
        inflate(getContext(), R.layout.view_rtl_marquee, this);
        baseRtlMarqueeView = (BaseRtlMarqueeView)findViewById(R.id.mv_marquee);
        tvMain = (TextView)findViewById(R.id.tv_main);
        startFade = (ImageView)findViewById(R.id.iv_start_fade);
        endFade = (ImageView)findViewById(R.id.iv_end_fade);
    }
    //endregion

}
