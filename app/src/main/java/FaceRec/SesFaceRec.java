package FaceRec;

public class SesFaceRec 
{
	static 
	{
		try
		{
			System.loadLibrary("opencv_java");
			System.loadLibrary("SES_FaceRec");
		}
		catch(Exception e)
		{
			 e.printStackTrace();
		}
        //Log.e("FaceNormal", "loadLibrary TestFaceNormal....END");
    }
	
	public native int sfrInit(String initDir);
	public native void sfrUninit();
	public native float sfsGetFaceSimilarityByPath(String SrcFilePath1, int SexId1,
			                                          String SrcFilePath2, int SexId2);	
}