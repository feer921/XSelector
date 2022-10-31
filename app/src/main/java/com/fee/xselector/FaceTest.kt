package com.fee.xselector

import FaceRec.SesFaceRec
import android.os.Environment
import android.util.Log

/**
 * 人脸测试
 */
class FaceTest {

    val mSesFaceRec: SesFaceRec = SesFaceRec()


    fun getFaceSimila() {
        val faceTestDir =
            Environment.getExternalStorageDirectory().absolutePath + "/facetest/FaceData/"

        val initResult = mSesFaceRec.sfrInit(faceTestDir)


        Log.e("info", "--> getFaceSimila() initResult = $initResult")

        val img1Path = faceTestDir + "a.jpg"
        val img2Path = faceTestDir + "b.jpg"

        val result = mSesFaceRec.sfsGetFaceSimilarityByPath(img1Path, -1, img2Path, -1)

        Log.e("info", "--> getFaceSimila() result = $result")

    }

    fun release() {
        mSesFaceRec.sfrUninit()
    }
}