// Generated code from Butter Knife. Do not modify!
package com.ecomm.suraj.clothpicker.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ecomm.suraj.clothpicker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  @UiThread
  public LoginActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.faceBookButton = Utils.findRequiredViewAsType(source, R.id.mButtonFacebook, "field 'faceBookButton'", AppCompatButton.class);
    target.googleButton = Utils.findRequiredViewAsType(source, R.id.mButtonGoogle, "field 'googleButton'", AppCompatButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.faceBookButton = null;
    target.googleButton = null;

    this.target = null;
  }
}
