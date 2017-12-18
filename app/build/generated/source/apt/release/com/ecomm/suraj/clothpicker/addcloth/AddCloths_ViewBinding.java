// Generated code from Butter Knife. Do not modify!
package com.ecomm.suraj.clothpicker.addcloth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ecomm.suraj.clothpicker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCloths_ViewBinding<T extends AddCloths> implements Unbinder {
  protected T target;

  @UiThread
  public AddCloths_ViewBinding(T target, View source) {
    this.target = target;

    target.styleYourSelfLayout = Utils.findRequiredViewAsType(source, R.id.style_today_layout, "field 'styleYourSelfLayout'", LinearLayout.class);
    target.addClothsLayout = Utils.findRequiredViewAsType(source, R.id.add_cloths_layout, "field 'addClothsLayout'", LinearLayout.class);
    target.styleIconImageView = Utils.findRequiredViewAsType(source, R.id.style_today, "field 'styleIconImageView'", AppCompatImageView.class);
    target.styleTextView = Utils.findRequiredViewAsType(source, R.id.style_yourself_TextView, "field 'styleTextView'", AppCompatTextView.class);
    target.addClothImageView = Utils.findRequiredViewAsType(source, R.id.add_cloths_new, "field 'addClothImageView'", AppCompatImageView.class);
    target.addNewClothsTextView = Utils.findRequiredViewAsType(source, R.id.add_new_cloth_TextView, "field 'addNewClothsTextView'", AppCompatTextView.class);
    target.toolBarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolBarTitle'", AppCompatTextView.class);
    target.bookMarkImageView = Utils.findRequiredViewAsType(source, R.id.bookmark_icon_view, "field 'bookMarkImageView'", AppCompatImageView.class);
    target.bookMarkTextView = Utils.findRequiredViewAsType(source, R.id.bookmark_TextView, "field 'bookMarkTextView'", AppCompatTextView.class);
    target.homeContainer = Utils.findRequiredViewAsType(source, R.id.loadcontainer, "field 'homeContainer'", FrameLayout.class);
    target.bookmarkLayout = Utils.findRequiredViewAsType(source, R.id.bookmark_pair_layout, "field 'bookmarkLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.styleYourSelfLayout = null;
    target.addClothsLayout = null;
    target.styleIconImageView = null;
    target.styleTextView = null;
    target.addClothImageView = null;
    target.addNewClothsTextView = null;
    target.toolBarTitle = null;
    target.bookMarkImageView = null;
    target.bookMarkTextView = null;
    target.homeContainer = null;
    target.bookmarkLayout = null;

    this.target = null;
  }
}
