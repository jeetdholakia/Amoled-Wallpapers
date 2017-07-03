package com.uxgarage.wallpaperlibrary.items;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/*
 * Amoled Wallpapers
 *
 * Copyright (c) 2017 Jeet Dholakia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@JsonObject
public class WallpaperJson {

    @JsonField
    public String name;

    @JsonField
    public String author;

    @JsonField
    public String url;

    @JsonField
    public String thumbUrl;

    @JsonField
    public String category;

    @JsonField(name = "Wallpapers")
    public List<WallpaperJson> getWallpapers;

    @JsonField(name = "Categories")
    public List<WallpaperJson> getCategories;

}
