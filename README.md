# android-rtlmarqueeview
Use RtlMarqueeView to display rtl (or ltr) text with proper movement direction.

Based on open source lib: https://github.com/MohMah/MarqueeView under the MIT License.

Preview:

<img src="https://github.com/shachar-oren/android-rtlmarqueeview/blob/master/example.gif" alt="demo" height="50px"/>

## Integration

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
	dependencies {
		compile 'com.github.shachar-oren:android-rtlmarqueeview:1.0.4'
	}
```
## Usage

Example usage in XML:

```xml
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:rtlmrq="http://schemas.android.com/apk/res-auto"
    tools:context=".MainActivity"
    android:layout_margin="16dp">

    <com.shacharsoftware.rtlmarqueeview.RtlMarqueeView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:scrollbars="none"
        android:id="@+id/rmv_marquee"
        android:layout_centerInParent="true"
        rtlmrq:marqueeTextSize="18dp"
        rtlmrq:marqueeText="@string/example_sentence"
        rtlmrq:marqueeEndWaitTicks="30"
        rtlmrq:marqueeFadeToColor="@color/white"
        rtlmrq:marqueeIsLooping="true"
        rtlmrq:marqueeStartWaitTicks="30"
        rtlmrq:marqueeLoops="0"
        rtlmrq:marqueeTextColor="@color/colorPrimary"
        />

</RelativeLayout>
```

Layout direction is checked internally. To manually update it, call `updateRtl()`.

Public methods:
```java
void setText(String text)
String getText()
void setTextColor(int color)
int getTextColor()
void setTextSize(float size)
float getTextSize()
void setFadeToColor(int color)
int getFadeToColor()
void setLooping(boolean looping)
boolean getLooping()
void setLoops(int loops)
int getLoops()
void setStartWaitTicks(int ticks)
int getStartWaitTicks()
void setEndWaitTicks(int ticks)
int getEndWaitTicks()
```

## License

```text
Copyright 2017 Shachar Oren

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
