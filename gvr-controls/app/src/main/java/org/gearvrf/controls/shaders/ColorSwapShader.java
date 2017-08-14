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

package org.gearvrf.controls.shaders;

import android.content.Context;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRShader;
import org.gearvrf.GVRShaderData;
import org.gearvrf.controls.R;
import org.gearvrf.utility.TextFile;

public class ColorSwapShader extends GVRShader{

    public static final String TEXTURE_GRAYSCALE = "grayScaleTexture";
    public static final String TEXTURE_DETAILS = "detailsTexture";
    public static final String COLOR = "u_color";

    public ColorSwapShader(GVRContext gvrContext) {

        super("float4 u_color float u_opacity", "sampler2D grayScaleTexture sampler2D detailsTexture", "float3 a_position float2 a_texcoord", GLSLESVersion.VUKAN);
        Context context = gvrContext.getContext();
        setSegment("FragmentTemplate", TextFile.readTextFile(context, R.raw.color_swap_shader_fragment));
        setSegment("VertexTemplate", TextFile.readTextFile(context,R.raw.color_swap_shader_vertex));
    }
    protected void setMaterialDefaults(GVRShaderData material) {
        material.setFloat("u_opacity", 1.0f);
        material.setVec4("u_color", 1.0f, 1.0f, 1.0f, 1.0f);
    }
}