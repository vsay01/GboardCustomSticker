package com.demo.sticker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * See firebase app indexing api code lab
 * https://codelabs.developers.google.com/codelabs/app-indexing/#0
 */

public class AppIndexingUtil {
    private static final String STICKER_URL_PATTERN = "mystickers://sticker/%s";
    private static final String STICKER_PACK_URL_PATTERN = "mystickers://sticker/pack/%s";
    private static final String CONTENT_PROVIDER_STICKER_PACK_NAME = "Firebase Storage Content Pack";
    private static final String TAG = "AppIndexingUtil";
    public static final String FAILED_TO_INSTALL_STICKERS = "Failed to install stickers";

    public static void setStickers(final Context context, FirebaseAppIndex firebaseAppIndex) {
        try {
            List<Indexable> stickers = getIndexableStickers();
            Indexable stickerPack = getIndexableStickerPack(stickers);

            List<Indexable> indexables = new ArrayList<>(stickers);
            indexables.add(stickerPack);

            Task<Void> task = firebaseAppIndex.update(
                    indexables.toArray(new Indexable[indexables.size()]));

            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "Successfully added stickers", Toast.LENGTH_SHORT)
                            .show();
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, FAILED_TO_INSTALL_STICKERS, e);
                    Toast.makeText(context, FAILED_TO_INSTALL_STICKERS, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        } catch (IOException | FirebaseAppIndexingInvalidArgumentException e) {
            Log.e(TAG, "Unable to set stickers", e);
        }
    }

    private static Indexable getIndexableStickerPack(List<Indexable> stickers)
            throws IOException, FirebaseAppIndexingInvalidArgumentException {
        Indexable.Builder indexableBuilder = getIndexableBuilder(StickersDataFactory.getAllStickerReference().get(0).getURL(), STICKER_PACK_URL_PATTERN, stickers.size());
        indexableBuilder.put("hasSticker", stickers.toArray(new Indexable[stickers.size()]));
        return indexableBuilder.build();
    }

    private static List<Indexable> getIndexableStickers() throws IOException,
            FirebaseAppIndexingInvalidArgumentException {
        List<Indexable> indexableStickers = new ArrayList<>();

        for (int i = 1; i < StickersDataFactory.getAllStickerReference().size(); i++) {
            Indexable.Builder indexableStickerBuilder = getIndexableBuilder(StickersDataFactory.getAllStickerReference().get(i).getURL(), STICKER_URL_PATTERN, i);
            indexableStickerBuilder.put("keywords", "tag1_" + i, "tag2_" + i)
                    // StickerPack object that the sticker is part of.
                    .put("partOf", new Indexable.Builder("StickerPack")
                            .setName(CONTENT_PROVIDER_STICKER_PACK_NAME)
                            .build());
            indexableStickers.add(indexableStickerBuilder.build());
        }

        return indexableStickers;
    }

    private static Indexable.Builder getIndexableBuilder(String stickerURL, String urlPattern, int index)
            throws IOException {
        String url = String.format(urlPattern, index);

        Indexable.Builder indexableBuilder = new Indexable.Builder("StickerPack")
                // name of the sticker pack
                .setName(CONTENT_PROVIDER_STICKER_PACK_NAME)
                // Firebase App Indexing unique key that must match an intent-filter
                // (e.g. mystickers://stickers/pack/0)
                .setUrl(url)
                // (Optional) - Defaults to the first sticker in "hasSticker"
                // displayed as a category image to select between sticker packs that should
                // be representative of the sticker pack
                //.setImage(contentUri.toString())
                .setImage(stickerURL)
                // (Optional) - Defaults to a generic phrase
                // content description of the image that is used for accessibility
                // (e.g. TalkBack)
                .setDescription("Indexable description");

        return indexableBuilder;
    }
}