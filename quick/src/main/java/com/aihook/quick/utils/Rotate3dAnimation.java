package com.aihook.quick.utils;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

public class Rotate3dAnimation extends Animation {

	private final float mFromDegrees;
	private final float mToDegrees;
	private float mCenterX = 0.0f;
	private float mCenterY = 0.0f;
	private float mDepthZ = 300f;
	private final boolean mReverse;
	private Camera mCamera;
	private View mStartAnimView = null;
	private View mContainer = null;
	private int mDuration = 500;
	private int mIndex = 0;
	private View mAnimViewFront = null;
	private View mAnimViewBack = null;
	private boolean isFront = false;

	/**
	 * Creates a new 3D rotation on the Y axis. The rotation is defined by its
	 * start angle and its end angle. Both angles are in degrees. The rotation
	 * is performed around a center point on the 2D space, definied by a pair of
	 * X and Y coordinates, called centerX and centerY. When the animation
	 * starts, a translation on the Z axis (depth) is performed. The length of
	 * the translation can be specified, as well as whether the translation
	 * should be reversed in time.
	 * 
	 * @param fromDegrees
	 *            the start angle of the 3D rotation
	 * @param toDegrees
	 *            the end angle of the 3D rotation
	 * @param centerX
	 *            the X center of the 3D rotation
	 * @param centerY
	 *            the Y center of the 3D rotation
	 * @param reverse
	 *            true if the translation should be reversed, false otherwise
	 */
	public Rotate3dAnimation(float fromDegrees, float toDegrees, float centerX,
			float centerY, float depthZ, boolean reverse, View container,
			View animViewFront, View animViewBack, int duration, boolean isFront) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
		mDepthZ = depthZ;
		mReverse = reverse;
		mContainer = container;
		mAnimViewFront = animViewFront;
		mAnimViewBack = animViewBack;
		mDuration = duration;
		this.isFront = isFront;

		setDuration(duration);
		setFillAfter(true);
		setInterpolator(new AccelerateInterpolator());

		if (isFront) {
			setAnimationListener(new DisplayNextView());
		}

	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		final float fromDegrees = mFromDegrees;
		float degrees = fromDegrees
				+ ((mToDegrees - fromDegrees) * interpolatedTime);
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;
		final Matrix matrix = t.getMatrix();
		camera.save();
		if (mReverse) {
			camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
		} else {
			camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
		}
		camera.rotateY(degrees);
		camera.getMatrix(matrix);
		camera.restore();
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
	}

	private final class DisplayNextView implements AnimationListener {
		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			mContainer.post(new SwapViews());
		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	private final class SwapViews implements Runnable {
		@Override
		public void run() {
			mAnimViewFront.setVisibility(View.GONE);
			mAnimViewBack.setVisibility(View.GONE);
			mIndex++;
			if (0 == mIndex % 2) {
				mStartAnimView = mAnimViewFront;
			} else {
				mStartAnimView = mAnimViewBack;
			}
			mStartAnimView.setVisibility(View.VISIBLE);
			mStartAnimView.requestFocus();
			Rotate3dAnimation rotation = new Rotate3dAnimation(-90, 0,
					mCenterX, mCenterY, mDepthZ, false, mContainer,
					mAnimViewFront, mAnimViewBack, mDuration, false);
			rotation.setDuration(mDuration);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());
			mStartAnimView.startAnimation(rotation);
		}
	}

}
