package com.shacharsoftware.rtlmarqueeview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;

class BaseRtlMarqueeView extends HorizontalScrollView {

    //region DM
	private View child;
	private boolean onLayoutExecuted = false;
	private Handler handler;
	private boolean isLooping;
	private int loops = 2;
	private int startWaitTicks = 50;
	private int endWaitTicks = 30;
    private boolean isRtl;
	//endregion

    //region Properties
	public boolean isLooping() {
		return isLooping;
	}

	public void setLooping(boolean looping) {
		isLooping = looping;
        onLayoutExecuted = false;
	}

	public int getLoops() {
		return loops;
	}

	public void setLoops(int loops) {
		this.loops = loops;
        onLayoutExecuted = false;
	}

	public int getStartWaitTicks() {
		return startWaitTicks;
	}

	public void setStartWaitTicks(int startWaitTicks) {
		this.startWaitTicks = startWaitTicks;
	}

	public int getEndWaitTicks() {
		return endWaitTicks;
	}

	public void setEndWaitTicks(int endWaitTicks) {
		this.endWaitTicks = endWaitTicks;
	}

	public void updateRtl(){
        isRtl = Checks.isRtl(getContext());
    }
	//endregion

    //region ctor
	public BaseRtlMarqueeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setSmoothScrollingEnabled(true);
		handler = new Handler();
        updateRtl();
	}
	//endregion

    //region Runnables
	private Runnable little_scroll = new Runnable() {
		@Override
		public void run() {
			BaseRtlMarqueeView.this.smoothScrollBy(isRtl ? -2 : 2, 0);
		}
	};

	private Runnable full_scroll = new Runnable() {
		@Override
		public void run() {
			BaseRtlMarqueeView.this.fullScroll(isRtl ? HorizontalScrollView.FOCUS_RIGHT : HorizontalScrollView.FOCUS_LEFT);
		}
	};

	private Runnable runnable = new Runnable() {
		int wait = 0;
		int repeat = 0;
		int initial_wait = 0;

		@Override
		public void run() {
			if (repeat >= loops && !isLooping) {
				BaseRtlMarqueeView.this.fullScroll(isRtl ? HorizontalScrollView.FOCUS_RIGHT : HorizontalScrollView.FOCUS_LEFT);
				return;
			}
			if (BaseRtlMarqueeView.this.getScrollX() != (isRtl ? 0 : child.getWidth() - BaseRtlMarqueeView.this.getWidth())) {
				if (initial_wait > startWaitTicks) {
					post(little_scroll);
				}
				initial_wait++;
			} else {
				wait++;
				if (wait > endWaitTicks) {
                    post(full_scroll);
                    wait = 0;
                    if (!isLooping) {
                        repeat++;
                    }
					initial_wait = 0;
				}
			}
			handler.postDelayed(runnable, 30);
		}
	};
	//endregion

    //region Logic
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (!onLayoutExecuted) {
			this.fullScroll(isRtl ? HorizontalScrollView.FOCUS_RIGHT : HorizontalScrollView.FOCUS_LEFT);
			child = this.getChildAt(0);
			if (!canScroll()) {
				LayoutParams params = (LayoutParams) child.getLayoutParams();
				params.gravity = isRtl ? Gravity.RIGHT : Gravity.LEFT;
				child.setLayoutParams(params);
			} else {
				handler.postDelayed(runnable, 500);
			}
			onLayoutExecuted = true;
		}
	}

	private boolean canScroll() {
		if (child != null) {
			int childWidth = child.getWidth();
			return getWidth() < childWidth + this.getPaddingRight() + this.getPaddingLeft();
		}
		return false;
	}
	//endregion
}