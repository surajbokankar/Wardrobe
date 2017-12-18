// Generated code from Butter Knife. Do not modify!
package com.ecomm.suraj.clothpicker;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.resultPair = Utils.findRequiredViewAsType(source, R.id.resultImage, "field 'resultPair'", ImageView.class);
    target.likeImageView = Utils.findRequiredViewAsType(source, R.id.image1, "field 'likeImageView'", ImageView.class);
    target.bookmark = Utils.findRequiredViewAsType(source, R.id.image2, "field 'bookmark'", ImageView.class);
    target.clickPhoto = Utils.findRequiredViewAsType(source, R.id.clickPhoto, "field 'clickPhoto'", AppCompatButton.class);
    target.imagePickerRelativeLayout = Utils.findRequiredViewAsType(source, R.id.imagePickerLayout, "field 'imagePickerRelativeLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.resultPair = null;
    target.likeImageView = null;
    target.bookmark = null;
    target.clickPhoto = null;
    target.imagePickerRelativeLayout = null;

    this.target = null;
  }
}
