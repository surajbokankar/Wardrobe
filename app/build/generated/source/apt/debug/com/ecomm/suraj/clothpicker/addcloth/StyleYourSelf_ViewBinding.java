// Generated code from Butter Knife. Do not modify!
package com.ecomm.suraj.clothpicker.addcloth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ecomm.suraj.clothpicker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StyleYourSelf_ViewBinding<T extends StyleYourSelf> implements Unbinder {
  protected T target;

  @UiThread
  public StyleYourSelf_ViewBinding(T target, View source) {
    this.target = target;

    target.suggestedPantImage = Utils.findRequiredViewAsType(source, R.id.pair_cloth_image_pants, "field 'suggestedPantImage'", AppCompatImageView.class);
    target.suggestedShirtImage = Utils.findRequiredViewAsType(source, R.id.pair_cloth_image_shirts, "field 'suggestedShirtImage'", AppCompatImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.suggestedPantImage = null;
    target.suggestedShirtImage = null;

    this.target = null;
  }
}
