package recognition2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

import recognition2.Recognition2_lib;

public class Recognition2_main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recognition2_lib rlib = new Recognition2_lib();
		String file = "img/image.jpg";
		rlib.recognition(file);
		
		JsonNode node = rlib.recongnition2();
		rlib.recognition_json(node);
	}				
}