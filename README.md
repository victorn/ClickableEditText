# ClickableEditText

[![Release](https://jitpack.io/v/vnidens/ClickableEditText.svg)](https://jitpack.io/#vnidens/ClickableEditText)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/vnidens/clickableedittext/blob/master/LICENSE)

The library extends Android edit text widgets by adding the ability to treat some compound drawables as buttons.

The complete list of extended classes:

 - ClickableEditText
 - ClickableTextInputEditText
 - ClickableAutoCompleteTextView
 - ClickableMultiAutoCompleteTextView

# Table of Contents

1. [Sample Project](https://github.com/vnidens/clickableedittext#sample-project)
2. [Gradle Dependency](https://github.com/vnidens/clickableedittext#sample-project)
3. [Drawables](https://github.com/vnidens/clickableedittext#drawables)
4. [Tinting](https://github.com/vnidens/clickableedittext#tinting)
5. [Callbacks](https://github.com/vnidens/clickableedittext#callbacks)

# Sample Project

You can get the latest APK from [this repo](https://github.com/vnidens/clickableedittext/blob/master/sample/sample.apk) or download it from Google Play:

<a href='https://play.google.com/store/apps/details?id=com.vnidens.clickableedittext.sample'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>

# Gradle Dependency

### Repository

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

### Library dependency

```gradle
dependencies {

    // other dependencies

    compile('com.github.vnidens:ClickableEditText:0.5.0.1'){
        transitive = true
    }
}
```


# Drawables

Drawables for buttons can be set either from code either in XML.

From code:

```java
    ClickableEditText cet = ...;
    Drawable drawable = ...;
    cet.setStartButtonDrawable(R.drawable.ic_icon);
    cet.setEndButtonDrawable(drawable);
```

In XML:
```xml
    <com.vnidens.clickableedittext.ClickableEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="ClickableEditText"
        app:cet_drawableStart="@drawable/ic_icon"
        app:cet_drawableEnd="@drawable/ic_icon_0"/>
```

# Tinting

You can add tinting for each button separately. The tint can be either a simple color either a color recource or a color state list.

From code:

```java
    ClickableEditText cet = ...;
    ColorStateList colorList =  ...;
    
    cet.setStartButtonTintRes(R.color.blue_500);
    cet.setEndButtonTintList(colorList);
    
    ClickableEditText cet1 = ...;
    int color = ...;
    cet1.setStartButtonTint(color);
```

From XML:

```xml
    <com.vnidens.clickableedittext.ClickableEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="ClickableEditText"
        app:cet_drawableStart="@drawable/ic_icon"
        app:cet_drawableStartTint="@color/state_list_blue"
        app:cet_drawableEnd="@drawable/ic_icon_0"
        app:cet_drawableEndTint="@color/blue_500"/>
```

# Callbacks

To get the button click event you should implement `OnCompoundButtonClickListner` interface and register it for start and/or end button click event.

```java
    ClickableEditText cet = ...;
    OnCompoundButtonClickListner listener = new OnCompoundButtonClickListener(){
                @Override
                public void onCompoundButtonClicked(@NonNull EditText view, @IdRes int which){
                    ...      
                }
            };
    cet.setOnStartButtonClickListener(listner);
    cet.setOnEndButtonClickListener(listener);
```

The `view` parameter will tell you from which view the event came from. And the `which` parameter will tell you the button's `id` which has been clicked.