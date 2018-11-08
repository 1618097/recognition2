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

import recognition2.Mysql;

public class Recognition2_lib {
	VisualRecognition service;
	IamOptions iamOptions= null;
	InputStream imagesStream = null;
	ClassifiedImages result;

	public Recognition2_lib(){	
		service = new VisualRecognition("2018-03-19");
		iamOptions = new IamOptions.Builder()
			    .apiKey("1618097")
			    .build();
		service.setIamCredentials(iamOptions);
	}
	
	public void file(File file) {
		try {
			imagesStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void view() {
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				  .imagesFile(imagesStream)
				  .imagesFilename("image.jpg")
				  .threshold((float) 0.6)
				  .owners(Arrays.asList("IBM"))
				  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(String.valueOf(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonNode text1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class");
		JsonNode text2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class");
		JsonNode text3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class");
		JsonNode score1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score");
		JsonNode score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score");
		JsonNode score3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score");
		//System.out.println(result);
		//System.out.println(node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class"));
		Mysql mysql = new Mysql();
		mysql.updateImage(text1,score1,text2,score2,text3,score3);
	}

}