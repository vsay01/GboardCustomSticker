package com.demo.sticker;

import java.util.ArrayList;
import java.util.List;

public class StickersDataFactory {

    public static List<Sticker> getAllStickerReference() {
        String[] stickerURLRef = {
                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/sticker.png?alt=media&token=81f7a091-c367-4c19-b59c-3573a3129784",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/sticker.png?alt=media&token=81f7a091-c367-4c19-b59c-3573a3129784",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/5008_9c00_420.gif?alt=media&token=9f00c9c5-558f-4e7a-a013-2463b2d19be1",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/giphy%20(1).gif?alt=media&token=5aed29da-ec27-4026-bc6d-29c6f9735e17",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/sticker2.png?alt=media&token=ab5a572e-5036-4e2f-af37-323e972aad18",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/tumblr_m6vetxlavc1qi0kfro1_500.gif?alt=media&token=2f3093b5-617c-43d7-a46e-5bbea1fc9a47",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/tumblr_mifew0Shb61s0r4jho1_500.gif?alt=media&token=d27837ff-a579-4097-ac7e-b297b92cc779",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/tumblr_n92jxbGsRZ1rsadwno1_250.gif?alt=media&token=05895740-5d65-4431-b496-9e37bdd0c6b2",

                "https://firebasestorage.googleapis.com/v0/b/sticker-5cc08.appspot.com/o/will-troll.gif?alt=media&token=eec0dcc4-ac6d-40b9-a1e4-8409de97b916"
        };
        List<Sticker> stickerList = new ArrayList<>();
        for (int i = 0; i < stickerURLRef.length; i++) {
            stickerList.add(new Sticker(stickerURLRef[i]));
        }
        return stickerList;
    }
}