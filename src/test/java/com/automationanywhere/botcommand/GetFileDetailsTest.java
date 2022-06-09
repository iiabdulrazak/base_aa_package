package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.impl.StringValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetFileDetailsTest {
    @Test
    public void TestGetFileDetails() throws IOException, InterruptedException {
        String imgUrl = "https://api.ocr.space/parse/imageurl?apikey=helloworld&url=https://courses.cs.vt.edu/csonline/AI/Lessons/VisualProcessing/OCRscans_files/calig.jpg&language=eng&isOverlayRequired=true";

        //Create GetImageText Object
        GetFileDetails getImgDetails = new GetFileDetails();
        //invoke action
        StringValue output = getImgDetails.action(imgUrl);
        Assert.assertEquals(output,output);
    }
}