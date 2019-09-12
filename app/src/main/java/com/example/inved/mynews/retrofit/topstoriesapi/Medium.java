package com.example.inved.mynews.retrofit.topstoriesapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Medium {

    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadatum> mediaMetadata = null;

    List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }
}
