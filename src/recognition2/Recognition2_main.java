package recognition2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition2_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recognition2_lib slib = new Recognition2_lib();
		
		slib.file(new File("img/image.jpg"));
		
		slib.view();


	}

}