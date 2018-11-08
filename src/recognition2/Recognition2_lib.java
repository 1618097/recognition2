package recognition2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;


public class Recognition2_lib {
	VisualRecognition service;
	IamOptions iamOptions= null;
	InputStream imagesStream = null;
	ClassifiedImages result;

	public Recognition2_lib(){	
		service = new VisualRecognition("2018-03-19");
		iamOptions = new IamOptions.Builder()
			    .apiKey("GfONvmQN0oReyT9Nc8Yk3n30lgC5RxsGDZVGZ2f_fCWB")
			    .build();
		service.setIamCredentials(iamOptions);
	}
	
	public void recognition(String file) {
		// TODO Auto-generated method stub
		try {
			imagesStream = new FileInputStream("img/image.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JsonNode recongnition2() {
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				  .imagesFile(imagesStream)
				  .imagesFilename("image.jpg")
				  .threshold((float) 0.6)
				  .owners(Arrays.asList("IBM"))
				  .build();
		result = service.classify(classifyOptions).execute();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(String.valueOf(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return node;
		}
	public void recognition_json(JsonNode node) {
	String class1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").toString();
	double score1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score").doubleValue();
	String class2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class").toString();
	double score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score").doubleValue();
	String class3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class").toString();
	double score3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score").doubleValue();
	System.out.println(class2);
	Mysql mysql = new Mysql();
	Mysql.updateImage(class1,score1,class2,score2,class3,score3);
	}
	
}

	


