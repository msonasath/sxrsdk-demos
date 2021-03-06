/* Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsungxr.controls.focus;

import com.samsungxr.SXRContext;
import com.samsungxr.SXRNode;
import com.samsungxr.SXRTexture;
import com.samsungxr.controls.cursor.ControlGazeController;

public abstract class ControlNode extends SXRNode {

    private boolean focus = false;
    public String tag = null;
    public boolean showInteractiveCursor = true;
    protected TouchAndGestureImpl touchAndGesturelistener;
    protected GamepadTouchImpl gamepadActionButtonslistener;

    public ControlNode(SXRContext sxrContext) {
        super(sxrContext);
        ControlNodeBehavior.interactiveObjects.add(this);
    }


    public ControlNode(SXRContext sxrContext, float f, float g, SXRTexture t) {
        super(sxrContext, f, g, t);
        ControlNodeBehavior.interactiveObjects.add(this);
    }

    public void dispatchGainedFocus() {
        gainedFocus();
        if (showInteractiveCursor) {
            ControlGazeController.enableInteractiveCursor();
        }
    }

    public void dispatchLostFocus() {
        lostFocus();
    }

    public void setFocus(boolean state) {
        if (state == true && focus == false) {
            focus = true;
            this.dispatchGainedFocus();
            return;
        }

        if (state == false && focus == true) {
            focus = false;
            this.dispatchLostFocus();
            return;
        }
    }

    public boolean hasFocus() {
        return focus;
    }

    public void onStep() {
    }

    protected abstract void gainedFocus();

    protected abstract void lostFocus();

    protected void singleTap() {

        if (touchAndGesturelistener != null) {

            touchAndGesturelistener.singleTap();

        }
    }

    public void setTouchAndGesturelistener(TouchAndGestureImpl listener) {
        this.touchAndGesturelistener = listener;
    }

    public GamepadTouchImpl getGamepadTouchListener() {
        return gamepadActionButtonslistener;
    }

    public void setGamepadTouchListener(GamepadTouchImpl gamepadActionButtonslistener) {
        this.gamepadActionButtonslistener = gamepadActionButtonslistener;
    }

}